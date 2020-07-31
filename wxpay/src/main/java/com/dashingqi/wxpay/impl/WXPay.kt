package com.dashingqi.wxpay.impl

import android.content.Context
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.dqpay.strategy.IPayStrategy
import com.dashingqi.wxpay.bean.WXPayInfoBean
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory


/**
 * @author : zhangqi
 * @time : 2020/7/30
 * desc : 微信支付
 */
object WXPay : IPayStrategy<WXPayInfoBean> {

    lateinit var mPayCallBack: IPayCallback<WXPayInfoBean>
    lateinit var mWxApi: IWXAPI
    var isHasInitial: Boolean = false
    lateinit var mPayInfo: WXPayInfoBean


    /**
     * 初始化微信支付，向微信支付注册app
     */
    fun initWxPay(context: Context, appId: String) {
        mWxApi = WXAPIFactory.createWXAPI(context, appId)
        mWxApi.registerApp(appId)
        isHasInitial = true
    }

    fun getWxApi(): IWXAPI {
        return mWxApi
    }

    /**
     * 支付的执行方法
     */
    override fun pay(
        context: Context,
        payInfo: WXPayInfoBean,
        callBack: IPayCallback<WXPayInfoBean>
    ) {
        mPayInfo = payInfo
        mPayCallBack = callBack
        if (callBack == null) {
            return
        }

        if (payInfo.appId == "" || payInfo.partnerId == "" || payInfo.sign == "" || payInfo.prepayId == "") {
            mPayCallBack.onFail()
            return
        }

        if (!isHasInitial) {
            mWxApi = WXAPIFactory.createWXAPI(context, payInfo.appId)
            mWxApi.registerApp(payInfo.appId)
        }

        //发起微信支付
        val request = PayReq()
        request.appId = payInfo.appId
        request.partnerId = payInfo.partnerId
        request.sign = payInfo.sign
        request.timeStamp = payInfo.timeStamp
        request.prepayId = payInfo.prepayId
        request.nonceStr = payInfo.nonceStr
        request.packageValue = payInfo.packageValue
        mWxApi.sendReq(request)
    }
}