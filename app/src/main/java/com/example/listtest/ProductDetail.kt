package com.example.listtest

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.listtest.adapters.ProductListAdapter
import com.example.listtest.databinding.ActivityMainBinding
import com.example.listtest.databinding.ProductDetailBinding
import com.example.listtest.interfaces.RetrofitService
import com.example.listtest.models.Products
import com.example.listtest.repository.MainRepository
import com.example.listtest.viewModelFactory.MainViewModelFactory
import com.example.listtest.viewModels.ProductViewModel

class ProductDetail : AppCompatActivity() {

    lateinit var product: Products
    lateinit var binding: ProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", Products::class.java)!!
        } else {
            intent.getParcelableExtra("data")!!
        }

        if(product != null){
            updateProductDetail(product)
        }
      /*  viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        )[ProductViewModel::class.java]

*/
        binding.favourite.setOnClickListener {
            product.favorite = !product.favorite!!
            updateFavourite(product)
        }





    }

    private fun updateProductDetail(product: Products) {

        Glide.with(this).load(product.image).into(binding.imageview)
        binding.price.text = product.price.toString()
        binding.title.text = product.title.toString()
        binding.rating.rating = product.rating?.rate?.toFloat()!!
        binding.description.text = product.description.toString()
        binding.price.text = product.price.toString()

        updateFavourite(product)



    }

    private fun updateFavourite(product: Products) {
        if (product.favorite!!){
            binding.favourite.setColorFilter(
                ContextCompat.getColor(this ,
                    R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);

        }else{
            binding.favourite.setColorFilter(
                ContextCompat.getColor(this ,
                    R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }


}