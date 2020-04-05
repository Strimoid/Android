package pl.strm.android.ui.contents

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import pl.strm.android.ContentsQuery

@Parcelize
data class Content(
    val id: String,
    val title: String,
    val url: String
) : Parcelable {

    constructor(content: ContentsQuery.Content) : this(
        content.id.orEmpty(),
        content.title.orEmpty(),
        content.url.orEmpty()
    )

}