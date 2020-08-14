package com.project.mercado_libre_test.view.products.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.mercado_libre_test.R
import com.project.mercado_libre_test.databinding.ProductItemLayoutBinding
import com.project.mercado_libre_test.services.api.products.model.Product
import com.project.mercado_libre_test.view.products.adapter.ProductAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item_layout.view.*
import java.lang.Exception

class ProductViewHolder(private val view: ProductItemLayoutBinding) :
    RecyclerView.ViewHolder(view.root) {

    fun bind(
        product: Product,
        productClickListener: ProductAdapter.ProductClickListener
    ) {
        view.root.setOnClickListener { productClickListener.onProductClick(product) }
        Picasso.get().load(product.thumbnail)
            .error(R.drawable.ic_launcher_background)
            .into(view.productImage)
        view.product = product
    }

    companion object {
        fun create(parent: ViewGroup): ProductViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ProductItemLayoutBinding =
                DataBindingUtil.inflate(inflater, R.layout.product_item_layout, parent, false)
            return ProductViewHolder(binding)
        }
    }
}