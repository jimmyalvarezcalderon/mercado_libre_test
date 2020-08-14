package com.project.mercado_libre_test.view.products

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.project.mercado_libre_test.R
import com.project.mercado_libre_test.services.api.products.model.Product
import com.project.mercado_libre_test.view.products.adapter.ProductAdapter
import com.project.mercado_libre_test.view.products.adapter.ProductsLoadStateAdapter
import kotlinx.android.synthetic.main.fragment_product_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ProductSearchFragment : Fragment(), ProductAdapter.ProductClickListener {

    private val productViewModel: ProductViewModel by viewModel()
    private val productsAdapter = ProductAdapter(this)

    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        setUpAdapter(decoration)
        setUpSearch()
        onRetry()
        savedInstanceState?.getString(LAST_SEARCH_QUERY)?.let {
            search(it)
        }

    }

    private fun onRetry() {
        retry_button.setOnClickListener { productsAdapter.retry() }
    }

    private fun setUpAdapter(decoration: DividerItemDecoration) {
        productsAdapter.addLoadStateListener {
            productsList.isVisible = it.source.refresh is LoadState.NotLoading
            progress_bar.isVisible = it.source.refresh is LoadState.Loading
            retry_button.isVisible = it.source.refresh is LoadState.Error

            val errorState = it.source.append as? LoadState.Error
                ?: it.source.prepend as? LoadState.Error
                ?: it.append as? LoadState.Error
                ?: it.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    this.context,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        productsList.adapter = productsAdapter.withLoadStateHeaderAndFooter(
            header = ProductsLoadStateAdapter { productsAdapter.retry() },
            footer = ProductsLoadStateAdapter { productsAdapter.retry() }
        )
        productsList.addItemDecoration(decoration)
        lifecycleScope.launch {
            productsAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { productsList.scrollToPosition(0) }
        }
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            productViewModel.searchRepo(query).collectLatest {
                productsAdapter.submitData(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        searchInput?.let {
            outState.putString(
                LAST_SEARCH_QUERY,
                it.text?.trim().toString()
            )
        }
    }

    private fun setUpSearch() {
        searchInput.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    performSearch()
                    true
                }
                EditorInfo.IME_ACTION_GO -> {
                    performSearch()
                    true
                }
                else -> false

            }
        }
        searchInput.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        searchInput.text?.trim().let {
            if (it?.isNotEmpty() == true) {
                search(it.toString())
            }
        }
    }

    override fun onProductClick(product: Product) {
        ProductSearchFragmentDirections.actionProductSearchFragmentToProductDetail(product)
            .also {
                findNavController().navigate(it)
            }
    }

    companion object {
        const val LAST_SEARCH_QUERY = "last_search_query"
    }

}