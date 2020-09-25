package com.xh.study


import android.content.Context
import android.widget.TextView
import com.xh.study.base.BaseAdapter
import com.xh.study.base.ViewHolder

class TextAdapter(context: Context) : BaseAdapter<String>(R.layout.item_text, context) {
    override fun onBindDataBinding(holder: ViewHolder, position: Int, data: String) {

        holder.getView<TextView>(R.id.tv_name).setText(data)
    }
}