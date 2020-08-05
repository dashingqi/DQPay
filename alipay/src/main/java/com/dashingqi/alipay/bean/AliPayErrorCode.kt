package com.dashingqi.alipay.bean

/**
 * @author : zhangqi
 * @time : 2020/8/5
 * desc : 支付宝支付
 */
object AliPayErrorCode {

    /**
     * 订单参数错误
     */
    const val ALI_PAY_ORDER_PARAMS_ERROR = -100

    private const val ALI_PAY_ORDER_PARAMS_ERROR_STR = "支付信息参数错误"

    const val ALI_PAY_FAILED_ERROR = -101
    private const val ALI_PAY_FAILED_ERROR_STR = "支付失败"

    private val errorMsgMap by lazy {
        var errorMap = mutableMapOf<Int, String>()
        errorMap[ALI_PAY_ORDER_PARAMS_ERROR] = ALI_PAY_ORDER_PARAMS_ERROR_STR
        errorMap[ALI_PAY_FAILED_ERROR] = ALI_PAY_FAILED_ERROR_STR
        errorMap
    }

    fun getErrorStr(code: Int): String {
        return errorMsgMap[ALI_PAY_ORDER_PARAMS_ERROR]!!
    }
}