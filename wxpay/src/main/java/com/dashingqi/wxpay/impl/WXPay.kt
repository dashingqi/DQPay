package com.dashingqi.wxpay.impl

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.dqpay.strategy.IPayStrategy
import com.dashingqi.wxpay.bean.WXPayErrorCode
import com.dashingqi.wxpay.bean.WXPayErrorCode.WX_IS_NOT_INSTALL
import com.dashingqi.wxpay.bean.WXPayErrorCode.WX_PAY_CALL_BACK_IS_NULL
import com.dashingqi.wxpay.bean.WXPayErrorCode.WX_PAY_INIT_FAIL
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
    lateinit var mPayInfo: WXPayInfoBean
    lateinit var mContext: Context
    lateinit var mAppId: String
    private val mWxApi: IWXAPI by lazy {
        var wxApi = WXAPIFactory.createWXAPI(mContext, mAppId)
        wxApi.registerApp(mAppId)
        wxApi
    }

    /**
     * 初始化微信支付，向微信支付注册app
     * context:上下文环境
     * appId 应用ID
     */
    fun initWxPay(context: Context, appId: String) {
        mContext = context
        mAppId = appId
    }

    fun getWxApi(): IWXAPI {
        return mWxApi
    }

    /**
     * 支付的执行方法
     */
    override fun pay(
        context: Activity,
        payInfo: WXPayInfoBean,
        callBack: IPayCallback<WXPayInfoBean>
    ) {
        mPayInfo = payInfo
        mPayCallBack = callBack

        if (checkPrePay()) {
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

    /**
     * 支付前的检查
     */
    private fun checkPrePay(): Boolean {

        //回调
        if (mPayCallBack == null) {
            Toast.makeText(
                mContext,
                WXPayErrorCode.getErrorStr(WX_PAY_CALL_BACK_IS_NULL),
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        //初始化
        if (mContext == null || mAppId == null) {
            mPayCallBack.onFail(
                WX_PAY_INIT_FAIL,
                WXPayErrorCode.getErrorStr(WX_PAY_INIT_FAIL)
            )
            return false
        }
        //判断一下手机上是否安装微信

        if (!mWxApi.isWXAppInstalled) {
            mPayCallBack.onFail(WX_IS_NOT_INSTALL, WXPayErrorCode.getErrorStr(WX_IS_NOT_INSTALL))
            return false
        }

        //支付参数的验证
        if (mPayInfo != null) {
            if (mPayInfo.appId == "" || mPayInfo.partnerId == "" || mPayInfo.sign == "" || mPayInfo.prepayId == "") {
                mPayCallBack.onFail(
                    WXPayErrorCode.WX_PAY_ORDER_PARAMS_ERROR,
                    WXPayErrorCode.getErrorStr(WXPayErrorCode.WX_PAY_ORDER_PARAMS_ERROR)!!
                )
                return false
            }
        } else {
            mPayCallBack.onFail(
                WXPayErrorCode.WX_PAY_DATA_BEAN_IS_NULL,
                WXPayErrorCode.getErrorStr(WXPayErrorCode.WX_PAY_DATA_BEAN_IS_NULL)
            )
            return false
        }

        return true
    }
}