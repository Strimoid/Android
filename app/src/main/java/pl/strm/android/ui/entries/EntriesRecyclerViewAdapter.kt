package pl.strm.android.ui.entries

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pl.strm.android.R

import pl.strm.android.ui.entries.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_entry.view.*
import pl.strm.android.ContentsQuery
import pl.strm.android.EntriesQuery

class EntriesRecyclerViewAdapter(
    private val values: ArrayList<EntriesQuery.Entry> = ArrayList()
) : RecyclerView.Adapter<EntriesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.idView.text = item.id
        holder.authorView.text = item.user?.name
        holder.contentView.text = Html.fromHtml(item.text, Html.FROM_HTML_MODE_COMPACT)
    }

    override fun getItemCount(): Int = values.size

    fun replace(newList: List<EntriesQuery.Entry>) {
        values.clear()
        values.addAll(newList)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.item_number
        val authorView: TextView = view.author
        val contentView: TextView = view.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}