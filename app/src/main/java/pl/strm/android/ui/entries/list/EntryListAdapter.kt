package pl.strm.android.ui.entries.list

import android.content.Context
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_entry.view.*
import kotlinx.android.synthetic.main.fragment_entry.view.author
import kotlinx.android.synthetic.main.fragment_entry.view.content
import kotlinx.android.synthetic.main.fragment_entry_reply.view.*
import pl.strm.android.EntriesQuery
import pl.strm.android.R
import pl.strm.android.ui.StaticListView

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

        val replies = item.replies as List<EntriesQuery.Reply>

        holder.repliesView.removeAllViews()

        replies.takeLast(2)?.withIndex().forEach {
            Log.i("list", it.index.toString())
            val replyView = createReplyView(holder.view.context, it.value)
            holder.repliesView.addView(replyView, it.index)
        }

        if (item.replies.size <= 2) {
            holder.showMoreView.visibility = View.GONE
        } else {
            holder.showMoreView.visibility = View.VISIBLE
            holder.showMoreView.setOnClickListener {
                replies.dropLast(2).reversed().forEach {
                    val replyView = createReplyView(holder.view.context, it)
                    holder.repliesView.addView(replyView, 0)
                }

                holder.showMoreView.visibility = View.GONE
                holder.view.invalidate()
            }
        }

        /*
        holder.repliesView.adapter = EntryReplyListAdapter(
            holder.view.context,
            item.replies as List<EntriesQuery.Reply>
        )
        */
    }

    private fun createReplyView(context: Context, reply: EntriesQuery.Reply): View {
        val replyView = LayoutInflater.from(context)
            .inflate(R.layout.fragment_entry_reply, null)
        replyView.author.text = reply.user?.name
        replyView.content.text = Html.fromHtml(reply.text, Html.FROM_HTML_MODE_COMPACT)

        return replyView
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.item_number
        val authorView: TextView = view.author
        val contentView: TextView = view.content
        // val repliesView: StaticListView = view.replies
        val repliesView: LinearLayout = view.replies
        val showMoreView: Button = view.showMore

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