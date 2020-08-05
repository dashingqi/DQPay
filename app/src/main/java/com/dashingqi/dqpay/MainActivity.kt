package com.dashingqi.dqpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dashingqi.alipay.bean.AliPayBean
import com.dashingqi.alipay.impl.AliPay
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.dqpay.utils.PayUtils
import com.dashingqi.wxpay.bean.WXPayInfoBean
import com.dashingqi.wxpay.impl.WXPay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * 微信支付
     */
    private fun wxPay() {
        //初始化微信支付
        WXPay.initWxPay(this, "appid")

        //微信支付请求参数
        var wxPayInfoBean = WXPayInfoBean()
        wxPayInfoBean.appId = ""
        wxPayInfoBean.nonceStr = ""

        //回调
        var wxPayCall = object : IPayCallback<WXPayInfoBean> {
            override fun onFail() {

            }

            override fun onSuccess(data: WXPayInfoBean) {

            }

            override fun onCancel() {

            }
        }

        //发起微信支付
        PayUtils.pay(WXPay, this, wxPayInfoBean, wxPayCall)
    }

    /**
     * 支付宝支付
     */
    private fun aliPay() {

        var aliPayBean = AliPayBean()
        aliPayBean.orderInfo = ""

        // 支付的回调
        var aliPayCallback = object : IPayCallback<AliPayBean> {
            override fun onCancel() {

            }

            override fun onFail() {

            }

            override fun onSuccess(data: AliPayBean) {

            }

        }

        //发起支付宝支付
        PayUtils.pay(AliPay(), this, aliPayBean, aliPayCallback)
    }
}