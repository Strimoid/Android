package pl.strm.android.ui.entries.list

import androidx.paging.DataSource
import pl.strm.android.EntriesQuery

class EntryListDataSourceFactory : DataSource.Factory<String, EntriesQuery.Entry>() {

    override fun create(): DataSource<String, EntriesQuery.Entry> {
        return EntryListDataSource()
    }

}