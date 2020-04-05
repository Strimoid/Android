package pl.strm.android.graphql

import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Operation.Variables
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import okhttp3.OkHttpClient
import pl.strm.android.ContentsQuery

object Client {

    private val httpClient: OkHttpClient = OkHttpClient
        .Builder()
        .build()

    val apollo: ApolloClient = ApolloClient.builder()
        .serverUrl("https://new.strm.pl/api")
        .okHttpClient(httpClient)
        .build()

    fun <D : Operation.Data?, T, V : Variables?> runQuery(query: Query<D, T, V>, handler: (Response<T>) -> Unit) {
        val callback = object : ApolloCall.Callback<T>() {
            override fun onFailure(e: ApolloException) {
                Log.e("GraphQL", e.message.orEmpty())
            }

            override fun onResponse(response: Response<T>) {
                handler(response)
            }
        }

        apollo.query(query).enqueue(callback)
    }

}