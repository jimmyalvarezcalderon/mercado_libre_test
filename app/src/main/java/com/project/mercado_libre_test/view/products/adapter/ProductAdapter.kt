package com.project.mercado_libre_test.view.products.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.project.mercado_libre_test.services.api.products.model.Product
import com.project.mercado_libre_test.view.products.viewholder.ProductViewHolder

class ProductAdapter(private val productClickListener: ProductClickListener) :
    PagingDataAdapter<Product, ProductViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, productClickListener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent)
    }

    interface ProductClickListener {
        fun onProductClick(product: Product)
    }
}