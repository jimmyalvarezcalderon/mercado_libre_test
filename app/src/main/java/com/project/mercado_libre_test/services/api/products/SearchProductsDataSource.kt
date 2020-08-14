package com.project.mercado_libre_test.services.api.products

import androidx.paging.PagingSource
import com.project.mercado_libre_test.services.api.RetrofitUtils
import com.project.mercado_libre_test.services.api.products.model.Product
import retrofit2.HttpException
import java.io.IOException

class SearchProductsDataSource(private val query: String) : PagingSource<Int, Product>() {

    private fun createPageResult(results: List<Product>, page: Int): LoadResult<Int, Product> {
        return LoadResult.Page(
            data = results,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (results.isEmpty()) null else page + 1
        )
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1
        val limit = params.loadSize
        return try {
            val results = RetrofitUtils.getRetrofitClient(RetrofitUtils.BASE_URL)
                .create(ProductsApi::class.java)
                .searchProducts(query, page, limit).results
            createPageResult(results, page)

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}