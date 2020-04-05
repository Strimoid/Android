package pl.strm.android.ui.contents

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.apollographql.apollo.api.Input
import pl.strm.android.ContentsQuery
import pl.strm.android.ContentsQuery.Content
import pl.strm.android.graphql.Client

class ContentListDataSource : ItemKeyedDataSource<String, Content>() {
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<Content>
    ) {
        Client.runQuery(ContentsQuery()) {
            it.data()?.contents?.let {
                callback.onResult(it)
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Content>) {
        val query = ContentsQuery(Input.optional(params.key))

        Log.i("GraphQL", params.key)

        Client.runQuery(query) {
            it.data()?.contents?.let {
                callback.onResult(it)
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Content>) {
        // unused
    }

    override fun getKey(item: Content): String {
        return item.createdAt as String
    }
}