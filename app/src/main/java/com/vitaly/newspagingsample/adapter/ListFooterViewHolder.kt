package com.vitaly.newspagingsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitaly.newspagingsample.R
import com.vitaly.newspagingsample.entity.State
import kotlinx.android.synthetic.main.item_list_footer.view.txt_error
import kotlinx.android.synthetic.main.item_list_footer.view.progress_bar

class ListFooterViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {
        with(itemView) {
            progress_bar.visibility = if (status == State.LOADING) VISIBLE else GONE
            txt_error.visibility = if (status == State.ERROR) VISIBLE else GONE
        }
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }
}