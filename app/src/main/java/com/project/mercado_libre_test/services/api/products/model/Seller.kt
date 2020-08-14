package com.project.mercado_libre_test.services.api.products.model

data class Seller(
    val car_dealer: Boolean,
    val id: Int,
    val power_seller_status: String,
    val real_estate_agency: Boolean,
    val tags: List<Any>
)