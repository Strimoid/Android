package pl.strm.android.ui.contents

import androidx.paging.DataSource
import pl.strm.android.ContentsQuery.Content

class ContentListDataSourceFactory : DataSource.Factory<String, Content>() {
    override fun create(): DataSource<String, Content> {
        return ContentListDataSource()
    }
}