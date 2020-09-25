package com.xh.study.activity.scrolltb

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xh.study.R
import com.xh.study.TextAdapter
import com.xh.study.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class ScrollTbFragment : BaseFragment() {
    private val mAdapter by lazy {
        TextAdapter(requireContext())
    }


    override fun getLayoutId(): Int = R.layout.view_recycler


    override fun initView() {
        val type = arguments?.getInt(EXTRA_TYPE) ?: 0
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        recycler_view.adapter = mAdapter

        val list = mutableListOf<String>()
        for (i in 0 until 100) {
            list.add("我是第$type  第$i 个数据")
        }
//        recycler_view.setRecycledViewPool(RecyclerView.RecycledViewPool())
        mAdapter.resetData(list)

    }


    companion object {
        private const val EXTRA_TYPE = "extra_type"
        fun create(type: Int): ScrollTbFragment {
            val fragment = ScrollTbFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

}