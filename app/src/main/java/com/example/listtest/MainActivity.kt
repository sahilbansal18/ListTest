package com.example.listtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listtest.viewModelFactory.MainViewModelFactory
import com.example.listtest.adapters.ProductListAdapter
import com.example.listtest.databinding.ActivityMainBinding
import com.example.listtest.interfaces.RetrofitService
import com.example.listtest.models.Products
import com.example.listtest.repository.MainRepository
import com.example.listtest.viewModels.ProductViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ProductViewModel
    private val adapter = ProductListAdapter(this)
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recycleview.adapter = adapter
        binding.recycleview.layoutManager = LinearLayoutManager(this);

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        )[ProductViewModel::class.java]


        viewModel.productList.observe(this) {
            adapter.updateProducts(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllProducts()

    }

    fun productDetail(product: Products) {
        var i = Intent(this,ProductDetail::class.java)
        i.putExtra("data",product)
        startActivity(i)

    }


}