package pl.strm.android.ui.entries.list

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_entry.view.*
import pl.strm.android.EntriesQuery
import pl.strm.android.R

class EntryListAdapter() : PagedListAdapter<EntriesQuery.Entry, EntryListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: EntriesQuery.Entry = getItem(position) as EntriesQuery.Entry
        //holder.idView.text = item.id
        holder.authorView.text = item.user?.name
        holder.contentView.text = Html.fromHtml(item.text, Html.FROM_HTML_MODE_COMPACT)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.item_number
        val authorView: TextView = view.author
        val contentView: TextView = view.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<EntriesQuery.Entry>() {
            override fun areItemsTheSame(
                oldItem: EntriesQuery.Entry,
                newItem: EntriesQuery.Entry
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: EntriesQuery.Entry,
                newItem: EntriesQuery.Entry
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}