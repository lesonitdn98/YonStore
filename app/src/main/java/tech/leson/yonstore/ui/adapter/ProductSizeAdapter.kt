package tech.leson.yonstore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import tech.leson.yonstore.R
import tech.leson.yonstore.ui.adapter.viewholder.ProductSizeViewHolder
import tech.leson.yonstore.ui.base.BaseAdapter
import tech.leson.yonstore.ui.product.model.ProductStyle

class ProductSizeAdapter(data: MutableList<ProductStyle>) :
    BaseAdapter<ProductSizeViewHolder, ProductStyle>(data) {

    lateinit var listener: OnSizeStyleClick
    var currentItem = MutableLiveData(0)

    override fun addData(data: ProductStyle) {
        this.data.add(data)
        notifyItemChanged(this.data.size - 1)
    }

    override fun addAllData(data: MutableList<ProductStyle>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun clearData() {
        this.data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductSizeViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_size, parent, false), currentItem, listener)

    override fun onBindViewHolder(holder: ProductSizeViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount() = data.size

    interface OnSizeStyleClick {
        fun onSizeClick(position: Int)
    }
}
