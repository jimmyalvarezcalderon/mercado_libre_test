package com.project.mercado_libre_test

import android.app.Application
import com.project.mercado_libre_test.repository.product.IProductRepository
import com.project.mercado_libre_test.repository.product.impl.ProductRepository
import com.project.mercado_libre_test.view.products.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MercadoLibreApplication : Application() {

    val module = module {
        single<IProductRepository> { ProductRepository() }
        viewModel { ProductViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MercadoLibreApplication)
            modules(module)
        }
    }

}