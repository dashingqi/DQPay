package com.dashingqi.dqpay.utils

import android.app.Activity
import android.content.Context
import com.dashingqi.dqpay.bean.IPayInfoBean
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.dqpay.strategy.IPayStrategy

/**
 * @author : zhangqi
 * @time : 2020/7/30
 * desc :
 */
object PayUtils {

    fun <T : IPayInfoBean> pay(
        payWay: IPayStrategy<T>,
        context: Activity,
        payInfoBean: T,
        callback: IPayCallback<T>
    ) {
        payWay.pay(context, payInfoBean, callback)
    }

}