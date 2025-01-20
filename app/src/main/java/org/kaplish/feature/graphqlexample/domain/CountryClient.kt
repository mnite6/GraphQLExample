package org.kaplish.feature.graphqlexample.domain

interface CountryClient {

    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry? //? means code may not exist
}