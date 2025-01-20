package org.kaplish.feature.graphqlexample.data

import org.kaplish.CountriesQuery
import org.kaplish.CountryQuery
import org.kaplish.feature.graphqlexample.domain.DetailedCountry
import org.kaplish.feature.graphqlexample.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",//in case capital is null
        currency = currency ?: "No Currency",//in case currency is null
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",//in case capital is null
    )
}