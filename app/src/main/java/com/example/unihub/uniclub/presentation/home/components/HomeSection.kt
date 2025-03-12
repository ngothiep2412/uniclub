package com.example.unihub.uniclub.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun HomeSection(
    title: String,
    title2: String = "See All",
    content: @Composable () -> Unit,
    navigateToSeeAll: () -> Unit,
) {
    Column {
        if (title != ""){
            SectionText(title, title2, navigateToSeeAll)
        }
        content()
    }
}



@Composable
fun SectionText(
    text: String,
    text2: String,
    navigateToMyProduct: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .weight(1f),
            fontSize = 18.sp
        )
        Text(
            text = text2,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {
                    navigateToMyProduct()
                }
        )
    }
}

@Preview
@Composable
fun SectionTextPreview() {
    HomeSection(
        title = "Title",
        title2 = "See All",
        content = {
            Column {
                Text("Content")
            }
        },
        navigateToSeeAll = {}
    )
}