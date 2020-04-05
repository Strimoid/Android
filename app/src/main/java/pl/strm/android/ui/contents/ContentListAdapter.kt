package pl.strm.android.ui.contents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_content.view.*
import pl.strm.android.ContentsQuery.Content
import pl.strm.android.R
import pl.strm.android.ui.contents.ContentListAdapter.ViewHolder

class ContentListAdapter() : PagedListAdapter<Content, ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) as Content
        holder.titleView.text = item.title

        if (item.thumbnail !== null) {
            Glide.with(holder.titleView)
                .load("https://img.strm.pl/400x300/thumbnails/" + item.thumbnail)
                .centerCrop()
                .into(holder.thumbnailView)
        } else {
            holder.thumbnailView.visibility = View.GONE
        }

        if (item.description.isNullOrEmpty()) {
            holder.descriptionView.visibility = View.GONE
        } else {
            holder.descriptionView.text = item.description
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title
        val descriptionView: TextView = view.description
        val thumbnailView: ImageView = view.thumbnail

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }
        }
    }
}