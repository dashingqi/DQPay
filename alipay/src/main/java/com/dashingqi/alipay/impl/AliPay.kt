package com.dashingqi.alipay.impl

import android.content.Context
import com.dashingqi.alipay.bean.AliPayBean
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.dqpay.strategy.IPayStrategy

/**
 * @author : zhangqi
 * @time : 2020/8/4
 * desc : 支付宝支付的工具类
 */
class AliPay : IPayStrategy<AliPayBean> {

    override fun pay(context: Context, payInfo: AliPayBean, callBack: IPayCallback<AliPayBean>) {

    }
}