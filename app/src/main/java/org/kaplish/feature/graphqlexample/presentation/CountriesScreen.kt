package org.kaplish.feature.graphqlexample.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kaplish.feature.graphqlexample.domain.DetailedCountry
import org.kaplish.feature.graphqlexample.domain.SimpleCountry
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit
) {
Box(modifier = Modifier.fillMaxSize())
{
    if(state.isLoading){
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
    else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.countries) { country ->
CountryItem(country = country,modifier = Modifier.fillMaxWidth().clickable {onSelectCountry(country.code)}
    .padding(16.dp))
            }
        }
    }
}
}

@Composable
private fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier){
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically)
    {
        Text(text = country.emoji,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        )
        {
            Text(text = country.name,
                fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital)
        }
    }
}