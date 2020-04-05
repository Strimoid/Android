package pl.strm.android.ui.entries.list

import androidx.paging.ItemKeyedDataSource
import com.apollographql.apollo.api.Input
import pl.strm.android.ContentsQuery
import pl.strm.android.EntriesQuery
import pl.strm.android.graphql.Client

class EntryListDataSource : ItemKeyedDataSource<String, EntriesQuery.Entry>() {
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<EntriesQuery.Entry>
    ) {
        Client.runQuery(EntriesQuery()) {
            it.data()?.entries?.let {
                callback.onResult(it)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<EntriesQuery.Entry>
    ) {
        val query = EntriesQuery(Input.optional(params.key))

        Client.runQuery(query) {
            it.data()?.entries?.let {
                callback.onResult(it)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<EntriesQuery.Entry>
    ) {
        // unused
    }

    override fun getKey(item: EntriesQuery.Entry): String {
        return item.createdAt as String
    }

}