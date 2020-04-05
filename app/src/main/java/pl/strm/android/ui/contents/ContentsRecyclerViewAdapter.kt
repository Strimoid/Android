package pl.strm.android.ui.contents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_content.view.*
import pl.strm.android.ContentsQuery
import pl.strm.android.R

class ContentsRecyclerViewAdapter(
    private val values: ArrayList<ContentsQuery.Content> = ArrayList()
) : RecyclerView.Adapter<ContentsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
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

    override fun getItemCount(): Int = values.size

    fun replace(newList: List<ContentsQuery.Content>) {
        values.clear()
        values.addAll(newList)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.title
        val descriptionView: TextView = view.description
        val thumbnailView: ImageView = view.thumbnail

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }
}