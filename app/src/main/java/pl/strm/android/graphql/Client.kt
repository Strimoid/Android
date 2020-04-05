package pl.strm.android.graphql

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

object Client {

    private val httpClient = OkHttpClient
        .Builder()
        .build()

    val apollo = ApolloClient.builder()
        .serverUrl("https://new.strm.pl/api")
        .okHttpClient(httpClient)
        .build()

}