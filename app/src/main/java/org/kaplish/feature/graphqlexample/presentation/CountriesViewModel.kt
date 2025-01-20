package org.kaplish.feature.graphqlexample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.kaplish.feature.graphqlexample.domain.DetailedCountry
import org.kaplish.feature.graphqlexample.domain.GetCountriesUseCase
import org.kaplish.feature.graphqlexample.domain.GetCountryUseCase
import org.kaplish.feature.graphqlexample.domain.SimpleCountry

class CountriesViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase): ViewModel() {

        private val _state = MutableStateFlow(CountriesState()) //private only ViewModel can change the state
        val state = _state.asStateFlow() //For view, read only state

       init {
           viewModelScope.launch {
               _state.update { it.copy(isLoading = true) }
               _state.update { it.copy(countries = getCountriesUseCase.execute(), isLoading = false) }
           }
       }

    fun selectCountry(code: String) {
        viewModelScope.launch { _state.update { it.copy(//viewModelScope because service call
            selectedCountry = getCountryUseCase.execute(
            code
        )) } }
    }

    fun dismissCountryDialog(){
        _state.update { it.copy( //not viewModelScope because no service call
            selectedCountry = null
        )  }
    }

        data class CountriesState(
            val countries: List<SimpleCountry> = emptyList(), //by default empty list
            val isLoading: Boolean = false,
            val selectedCountry: DetailedCountry?=null

        )
}