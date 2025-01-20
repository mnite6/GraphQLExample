package org.kaplish.feature.graphqlexample.domain

class GetCountryUseCase(private val countryClient: CountryClient) {

    suspend fun execute(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}