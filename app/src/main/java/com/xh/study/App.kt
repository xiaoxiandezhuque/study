package com.xh.study

import android.app.Application
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhy.changeskin.SkinManager

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
//        SkinManager.getInstance().init(this);
//        SkinManager.getInstance().register(this);
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.refresh_bg, R.color.refresh_text);//全局设置主题颜色
            return@setDefaultRefreshHeaderCreator ClassicsHeader(context)
        }
        val key = "4OYy/Obh0V6/dpXa"

        val jiami = AESCryptoSecurity.encrypt(
            "{\"msgContent\":{\"giftIcon\":\"https://meiban-1255871614.cos.ap-guangzhou.myqcloud.com/gift/webp/%E6%91%A9%E6%89%98%E8%BD%A6.webp\",\"giftId\":11,\"giftJsonUrl\":\"https://meiban-1255871614.cos.ap-guangzhou.myqcloud.com/gift/json/%E6%91%A9%E6%89%98%E8%BD%A6.json\",\"giftName\":\"摩托车\",\"giftPrice\":36888,\"giftSort\":11,\"giftSvgaUrl\":\"https://meiban-1255871614.cos.ap-guangzhou.myqcloud.com/gift/svga/%E6%91%A9%E6%89%98%E8%BD%A6.svga\",\"giftType\":0},\"msgType\":101,\"receiveUserId\":\"10111134\",\"sendUserId\":\"10111132\"}",
            key
        )
        LogUtils.e(jiami)
        LogUtils.e(AESCryptoSecurity.decrypt(jiami, key))

    }
}