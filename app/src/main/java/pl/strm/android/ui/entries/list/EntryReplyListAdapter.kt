package pl.strm.android.ui.entries.list

import android.content.Context
import android.database.DataSetObserver
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import pl.strm.android.EntriesQuery
import pl.strm.android.R

class EntryReplyListAdapter(context: Context, replies: List<EntriesQuery.Reply>)
    : ArrayAdapter<EntriesQuery.Reply>(context, 0, replies) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val reply: EntriesQuery.Reply = getItem(position) as EntriesQuery.Reply

        val view: LinearLayout = (convertView?: LayoutInflater
            .from(context)
            .inflate(R.layout.fragment_entry_reply, parent, false)) as LinearLayout

        val authorView = view.findViewById<TextView>(R.id.author)
        val contentView = view.findViewById<TextView>(R.id.content)

        authorView.text = reply.user?.name
        contentView.text = Html.fromHtml(reply.text, Html.FROM_HTML_MODE_COMPACT)

        return view
    }

}