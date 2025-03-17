package com.example.unihub.uniclub.presentation.home

import ProductModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unihub.core.domain.util.NetworkError
import com.example.unihub.core.domain.util.onError
import com.example.unihub.core.domain.util.onSuccess
import com.example.unihub.uniclub.domain.UniclubRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.unihub.core.domain.util.Result
import com.example.unihub.uniclub.domain.BrandModel
import timber.log.Timber

class HomeViewModel(
    private val repository: UniclubRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState(isLoading = true))
    val state = _state
        .onStart {
            loadData()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(500L),
            _state.value
        )

    private val _event = Channel<HomeEvent>()
    val event = _event.receiveAsFlow()


    private var cachedProducts = emptyList<String>()

    private var cartJob: Job? = null

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.AddToCart -> addToCart(action.product)
        }
    }

    private fun addToCart(product: ProductModel) {
        // TODO()
    }


    private fun loadData() {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(isLoading = true)
                }

                val brandDeferred: Deferred<Result<List<BrandModel>, NetworkError>> =
                    async { repository.getBrands() }
                val productDeferred: Deferred<Result<List<ProductModel>, NetworkError>> =
                    async { repository.getProducts() }

                val categoryResult = brandDeferred.await()
                val productResult = productDeferred.await()

                categoryResult.onSuccess { brands ->
                    _state.update {
                        Timber.d("Brand: ${it.brand}")
                        it.copy(
                            isLoading = false,
                            brand = brands
                        )
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    _event.send(HomeEvent.Error(error))
                }


                productResult.onSuccess { products ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            products = products
                        )
                    }
                }.onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    _event.send(HomeEvent.Error(error))
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message.toString()
                    )
                }
            }
        }
    }
}