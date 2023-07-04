package com.example.listtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listtest.MainActivity
import com.example.listtest.R
import com.example.listtest.models.Products
import com.example.listtest.databinding.ProductListAdapterBinding

class ProductListAdapter(var context: MainActivity) : RecyclerView.Adapter<ProductViewHolder>() {
    var list = mutableListOf<Products>()
    var favList = mutableListOf<Products>()
    fun updateProducts(list: List<Products>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductListAdapterBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]
        holder.binding.title.text = product.title
        holder.binding.price.text = ""+product.price.toString()
        holder.binding.ratingBar.rating = product.rating?.rate?.toFloat()!!
        holder.binding.description.text = product.description.toString()
        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.imageview)
        holder.binding.favourite.setOnClickListener {
            addToFavourite(product)
        }

        holder?.itemView!!.setOnClickListener {
            context.productDetail(product)
        }
        if (product.favorite!!){
            holder.binding.favourite.setColorFilter(ContextCompat.getColor(context ,
                R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);

        }else{
            holder.binding.favourite.setColorFilter(ContextCompat.getColor(context ,
                R.color.gray), android.graphics.PorterDuff.Mode.SRC_IN);
        }

    }

    //this code is only for if you want to display the favourite list.
    // But favourite list task is not given in assignment
    private fun addToFavourite(product: Products) {
        product.favorite = !product.favorite!!
        if(favList.size == 0){
            if(product.favorite!!){
                favList.add(product)
            }
        }else{
            var isFav = false
            for (i in 0 until favList.size){
                if(product.id == favList[i].id){
                    favList.removeAt(i)
                    isFav = true
                    break
                }
            }
            if(!isFav){
                favList.add(product)
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
class ProductViewHolder(val binding: ProductListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
}