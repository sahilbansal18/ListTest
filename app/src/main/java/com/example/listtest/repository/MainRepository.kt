package com.example.listtest.repository

import com.example.listtest.interfaces.RetrofitService


class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllProducts() = retrofitService.getAllProducts()

}