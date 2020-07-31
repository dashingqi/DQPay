package com.dashingqi.dqpay.strategy

import android.content.Context
import com.dashingqi.dqpay.bean.IPayInfoBean
import com.dashingqi.dqpay.callback.IPayCallback

/**
 * @author : zhangqi
 * @time : 2020/7/30
 * desc :
 */
interface IPayStrategy<T : IPayInfoBean> {

    fun pay(context: Context, payInfo: T, callBack: IPayCallback<T>)
}