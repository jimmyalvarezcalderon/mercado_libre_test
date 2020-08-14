package com.project.mercado_libre_test.services.api.products.model

data class Installments(
    val amount: Double,
    val currency_id: String,
    val quantity: Int,
    val rate: Double
)