package com.example.unihub.uniclub.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unihub.uniclub.presentation.home.components.Product
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState(isLoading = true))
    val state = _state
        .onStart {
            loadProducts()
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
        when(action) {
            is HomeAction.AddToCart -> addToCart(action.product)
        }
    }

    private fun addToCart(product: Product) {
       // TODO()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            try {
                // Simulate network delay
                delay(1500)
                // Replace with actual API call
                // val products = repository.getProducts()

                // Update state with loaded products
                _state.update {
                    it.copy(
                        isLoading = false,
                        // products = products
                    )
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