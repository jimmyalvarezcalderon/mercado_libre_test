package com.project.mercado_libre_test.view.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.project.mercado_libre_test.R
import com.project.mercado_libre_test.databinding.FragmentProductDetailBinding
import com.project.mercado_libre_test.databinding.ProductItemLayoutBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_detail.*


class ProductDetail : Fragment() {

    private val productArgs: ProductDetailArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProductDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        binding.product = productArgs.product
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(productArgs.product.thumbnail).into(menuImage)
    }
}