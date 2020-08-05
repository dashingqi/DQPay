package com.dashingqi.wxpay.bean

/**
 * @author : zhangqi
 * @time : 2020/8/5
 * desc :
 */
object WXPayErrorCode {

    /**
     * 订单参数错误
     */
    const val WX_PAY_ORDER_PARAMS_ERROR = -100

    private const val WX_PAY_ORDER_PARAMS_ERROR_STR = "支付信息参数错误"


    private val errorParamsMap by lazy {
        var errorMap = mutableMapOf<Int, String>()
        errorMap?.apply {
            put(WX_PAY_ORDER_PARAMS_ERROR, WX_PAY_ORDER_PARAMS_ERROR_STR)
        }
        errorMap
    }

    /**
     * 获取到对应错误码的信息
     */
    fun getErrorStr(errorCode: Int): String {
        return errorParamsMap[errorCode]!!
    }

}