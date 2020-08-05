package com.dashingqi.dqpay.callback

import com.dashingqi.dqpay.bean.IPayInfoBean

/**
 * @author : zhangqi
 * @time : 2020/7/30
 * desc : 支付的回调
 */
interface IPayCallback<T : IPayInfoBean> {

    /**
     * 支付失败
     * errorCode:支付失败码
     * errorMsg 支付失败信息
     */
    fun onFail(errorCode: Int, errorMsg: String)

    /**
     * 支付成功
     */
    fun onSuccess(data: T)

    /**
     * 支付取消
     */
    fun onCancel()
}