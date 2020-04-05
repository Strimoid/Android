package pl.strm.android.ui.contents

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import pl.strm.android.ContentsQuery
import pl.strm.android.R
import pl.strm.android.graphql.Client
import java.util.ArrayList

class ContentListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_content_list, container, false)
        val contentsAdapter = ContentsRecyclerViewAdapter(ArrayList())

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = contentsAdapter
            }
        }

        getContents(contentsAdapter)

        return view
    }

    private fun getContents(adapter: ContentsRecyclerViewAdapter) {
        val query = ContentsQuery()

        Client.apollo.query(query)
            .enqueue(object : ApolloCall.Callback<ContentsQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.e("GraphQL", e.message.orEmpty())
                }

                override fun onResponse(response: Response<ContentsQuery.Data>) {
                    response.data()?.let {
                        activity?.runOnUiThread {
                            adapter.replace(it.contents as List<ContentsQuery.Content>)
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            })
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ContentListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}