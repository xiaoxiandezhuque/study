package com.xh.study.base


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


open abstract class BaseAdapter<E> private constructor(
    private val layoutId: Int
) : RecyclerView.Adapter<ViewHolder>() {

    private var mFragment: Fragment? = null
    protected var mContext: Context? = null

    constructor(
        layoutId: Int,
        fragment: Fragment
    ) : this(layoutId) {
        mFragment = fragment
        mContext = fragment.requireContext()
    }

    constructor(
        layoutId: Int,
        context: Context
    ) : this(layoutId) {
        mContext = context
    }




    val mData: MutableList<E> = mutableListOf()

    private var isAddHead: Boolean = false
    private var isAddEnd: Boolean = false
    private var mHeadResId: Int = 0
    private var mEndResId: Int = 0

    private var mLoadMoreListener: (() -> Unit)? = null

    private var animation: Animation? = null
    private var ivLoading: ImageView? = null
    private var tvMore: TextView? = null

    private var mItemClickListener: ((view: View, postion: Int, data: E) -> Unit)? = null

    private var isOpenNullLayout: Boolean = false
    private var mNullLayoutResId: Int = 0


    fun addHeadView(@LayoutRes resId: Int) {
        isAddHead = true
        mHeadResId = resId
    }

    fun addEndView(@LayoutRes resId: Int) {
        isAddEnd = true
        mEndResId = resId
    }

    fun addNullView(@LayoutRes resId: Int) {
        isOpenNullLayout = true
        mNullLayoutResId = resId

    }

    override fun getItemCount(): Int {
        if (isOpenNullLayout && mData.size == 0) {
            return 1
        }

        var count = mData.size
        if (isAddEnd) {
            count++
        }
        if (isAddHead) {
            count++
        }
        return count
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewHolder: ViewHolder?
        var view: View? = when (viewType) {
            LAYOUT_ORDINARY -> LayoutInflater.from(mContext).inflate(
                layoutId,
                parent,
                false
            )
            LAYOUT_END -> LayoutInflater.from(mContext).inflate(
                mEndResId,
                parent,
                false
            )
            LAYOUT_HEAD -> LayoutInflater.from(mContext).inflate(
                mHeadResId,
                parent,
                false
            )
            LAYOUT_NULL -> LayoutInflater.from(mContext).inflate(
                mNullLayoutResId,
                parent,
                false
            )
            else -> null

        }
        if (view == null) {
            view = onMyCreateOtherViewDataBinding(parent, viewType)
        }

        viewHolder = ViewHolder(view!!)

        return viewHolder!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            LAYOUT_ORDINARY -> {
                val myPosition: Int
                if (isAddHead) {
                    myPosition = position - 1
                } else {
                    myPosition = position
                }

                onBindDataBinding(holder, myPosition, getItemData(myPosition))

                holder.convertView.setOnClickListener { v ->
                    mItemClickListener?.invoke(v, position, mData[myPosition])
                }
            }
            LAYOUT_END -> {
                onBindEndBinding(holder)
            }
            LAYOUT_HEAD -> {
                onBindHeadBinding(holder)
            }
            LAYOUT_NULL -> {
                onBindNullBinding(holder)
            }
            else -> {
                onMyBindOtherViewHolder(
                    holder,
                    position,
                    getItemData(position)
                )

            }
        }

    }


    override fun getItemViewType(position: Int): Int {
        if (isOpenNullLayout && mData.size == 0) {
            return LAYOUT_NULL
        }
        if (isAddHead && position == 0) {
            return LAYOUT_HEAD
        }
        if (isAddEnd && position == itemCount - 1) {
            return LAYOUT_END
        }
        return LAYOUT_ORDINARY
    }


    fun stopAnimation() {
        ivLoading?.clearAnimation()

    }


    //重置数据
    fun resetData(dataList: List<E>) {
        mData.clear()
        addAllData(dataList)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

    fun addAllData(dataList: List<E>) {

        mData.addAll(dataList)
        notifyDataSetChanged()
//        notifyItemRangeChanged(mData.size, dataList.size)
    }

    fun addData(data: E) {
        mData.add(data)
        notifyItemChanged(mData.size)

    }

    fun addData(position: Int, data: E) {
        mData.add(position, data)
        notifyItemInserted(position)
    }

    fun replaceData(position: Int, data: E) {
        mData[position] = data
        notifyItemChanged(position)
    }

    fun updateData(position: Int) {
        if (itemCount > position) {
            notifyItemChanged(position)
        }
    }


    fun removeData(data: E) {
        if (mData.contains(data)) {
            val position = mData.indexOf(data)
            this.mData.remove(data)
            notifyItemRemoved(position)
        }
    }

    fun removeData(position: Int) {
        if (this.itemCount > position) {
            this.mData.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItemData(position: Int): E {
        return mData[position]
    }

    fun setLoadMoreListener(listener: () -> Unit) {
        mLoadMoreListener = listener
    }

    fun setOnItemClickListener(listener: (view: View, postion: Int, data: E) -> Unit) {
        mItemClickListener = listener
    }


//    protected abstract val layoutId: Int

    open abstract protected fun onBindDataBinding(holder: ViewHolder, position: Int, data: E)

    open protected fun onBindHeadBinding(holder: ViewHolder) {

    }

    open protected fun onBindEndBinding(holder: ViewHolder) {

    }

    open protected fun onBindNullBinding(holder: ViewHolder) {

    }

    open protected fun onMyCreateOtherViewDataBinding(
        parent: ViewGroup,
        viewType: Int
    ): View? {
        return null
    }

    open protected fun onMyBindOtherViewHolder(holder: ViewHolder, position: Int, data: E) {

    }


    companion object {
        val LAYOUT_ORDINARY = 1
        val LAYOUT_END = 2
        val LAYOUT_HEAD = 3
        val LAYOUT_NULL = 4
    }

}
