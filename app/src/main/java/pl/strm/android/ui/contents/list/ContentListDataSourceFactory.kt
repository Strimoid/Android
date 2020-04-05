package pl.strm.android.ui.contents.list

import androidx.paging.DataSource
import pl.strm.android.ContentsQuery.Content
import pl.strm.android.ui.contents.list.ContentListDataSource

class ContentListDataSourceFactory : DataSource.Factory<String, Content>() {

    override fun create(): DataSource<String, Content> {
        return ContentListDataSource()
    }

}