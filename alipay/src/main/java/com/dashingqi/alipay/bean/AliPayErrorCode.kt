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
    private const val ALI_PAY_ORDER_PARAMS_ERROR_MSG = "支付信息参数错误"

    const val ALI_PAY_FAILED_ERROR = -101
    private const val ALI_PAY_FAILED_ERROR_MSG = "支付失败"

    /**
     * 数据实体为null
     */
    const val ALI_PAY_DATA_BEAN_IS_NULL = -50
    private const val ALI_PAY_DATE_BEAN_IS_NULL_MSG = "微信支付实体数据为空了哟！"


    /**
     * 传入的回调为null
     */
    const val ALI_PAY_CALL_BACK_IS_NULL = -200
    private const val ALI_PAY_CALL_BACK_IS_NULL_MSG = "微信支付回调不能为空哟！"


    /**
     * 没有安装支付宝
     */
    const val ALI_IS_NOT_INSTALL = -400
    private const val ALI_IS_NOT_INSTALL_MSG = "请安装支付宝！"

    private val errorMsgMap by lazy {
        var errorMap = mutableMapOf<Int, String>()
        errorMap[ALI_PAY_ORDER_PARAMS_ERROR] = ALI_PAY_ORDER_PARAMS_ERROR_MSG
        errorMap[ALI_PAY_FAILED_ERROR] = ALI_PAY_FAILED_ERROR_MSG
        errorMap[ALI_PAY_DATA_BEAN_IS_NULL] = ALI_PAY_DATE_BEAN_IS_NULL_MSG
        errorMap[ALI_PAY_CALL_BACK_IS_NULL] = ALI_PAY_CALL_BACK_IS_NULL_MSG
        errorMap[ALI_IS_NOT_INSTALL] = ALI_IS_NOT_INSTALL_MSG
        errorMap
    }

    fun getErrorStr(code: Int): String {
        return errorMsgMap[ALI_PAY_ORDER_PARAMS_ERROR]!!
    }
}