package com.project.mercado_libre_test.services.api.products.model

data class Paging(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: Int
)