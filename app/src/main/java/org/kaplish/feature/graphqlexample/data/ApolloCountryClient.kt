package org.kaplish.feature.graphqlexample.data

import com.apollographql.apollo.ApolloClient
import org.kaplish.CountriesQuery
import org.kaplish.CountryQuery
import org.kaplish.feature.graphqlexample.domain.CountryClient
import org.kaplish.feature.graphqlexample.domain.DetailedCountry
import org.kaplish.feature.graphqlexample.domain.SimpleCountry

class ApolloCountryClient(

    private val _apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return _apolloClient.query(CountriesQuery())
            .execute()
            .data?.countries?.map {
                it.toSimpleCountry()

            } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return _apolloClient.query(CountryQuery(code))
            .execute()
            .data?.country?.toDetailedCountry()
    }
}