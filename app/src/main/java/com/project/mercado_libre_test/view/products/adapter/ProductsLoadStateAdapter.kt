package com.project.mercado_libre_test.view.products.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.project.mercado_libre_test.view.products.viewholder.ProductsLoadStateViewHolder

class ProductsLoadStateAdapter(val retry: () -> Unit) : LoadStateAdapter<ProductsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ProductsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ProductsLoadStateViewHolder {
        return ProductsLoadStateViewHolder.create(parent, retry)
    }
}