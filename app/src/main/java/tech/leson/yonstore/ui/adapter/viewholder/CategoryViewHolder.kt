package tech.leson.yonstore.ui.adapter.viewholder

import android.view.View
import kotlinx.android.synthetic.main.item_category.view.*
import tech.leson.yonstore.data.model.Category
import tech.leson.yonstore.ui.base.BaseViewHolder
import tech.leson.yonstore.utils.OnCategoryClickListener

class CategoryViewHolder(itemView: View, val listener: OnCategoryClickListener) :
    BaseViewHolder<Category>(itemView) {
    override fun onBind(data: Category) {
        itemView.btnCategoryItem.text = data.name
        itemView.btnCategoryItem.setCompoundDrawablesRelativeWithIntrinsicBounds(data.getIcon(),
            0,
            0,
            0)
        itemView.btnCategoryItem.setOnClickListener { listener.onClick(data) }
    }
}
