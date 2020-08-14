package com.project.mercado_libre_test.services.api.products.model

data class ProductSearchResponse(
    val paging: Paging,
    val query: String,
    val results: List<Product>,
    val site_id: String
)