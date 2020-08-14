package com.project.mercado_libre_test.view.products.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.project.mercado_libre_test.R
import kotlinx.android.synthetic.main.products_load_state.view.*

class ProductsLoadStateViewHolder(val view: View, retry: () -> Unit) :
    RecyclerView.ViewHolder(view) {

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            view.error_msg.text = loadState.error.localizedMessage
        }
        view.progress_bar.isVisible = loadState is LoadState.Loading
        view.retry_button.isVisible = loadState !is LoadState.Loading
        view.error_msg.isVisible = loadState !is LoadState.Loading
    }


    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ProductsLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.products_load_state, parent, false)
            return ProductsLoadStateViewHolder(view, retry)
        }
    }
}