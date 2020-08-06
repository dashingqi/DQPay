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
    private const val WX_PAY_ORDER_PARAMS_ERROR_MSG = "微信支付信息参数错误！"

    /**
     * 数据实体为null
     */
    const val WX_PAY_DATA_BEAN_IS_NULL = -50
    private const val WX_PAY_DATE_BEAN_IS_NULL_MSG = "微信支付实体数据为空了哟！"


    /**
     * 传入的回调为null
     */
    const val WX_PAY_CALL_BACK_IS_NULL = -200
    private const val WX_PAY_CALL_BACK_IS_NULL_MSG = "微信支付回调不能为空哟！"

    /**
     * 没有进行初始化操作
     */
    const val WX_PAY_INIT_FAIL = -300
    private const val WX_PAY_INIT_FAIL_MSG = "微信支付初始化失败了哟！"

    /**
     * 没有安装微信
     */
    const val WX_IS_NOT_INSTALL = -400
    private const val WX_IS_NOT_INSTALL_MSG = "请安装微信！"


    private val errorParamsMap by lazy {
        var errorMap = mutableMapOf<Int, String>()
        errorMap?.apply {
            put(WX_PAY_ORDER_PARAMS_ERROR, WX_PAY_ORDER_PARAMS_ERROR_MSG)
            put(WX_PAY_CALL_BACK_IS_NULL, WX_PAY_CALL_BACK_IS_NULL_MSG)
            put(WX_PAY_INIT_FAIL, WX_PAY_INIT_FAIL_MSG)
            put(WX_PAY_DATA_BEAN_IS_NULL, WX_PAY_DATE_BEAN_IS_NULL_MSG)
            put(WX_IS_NOT_INSTALL, WX_IS_NOT_INSTALL_MSG)
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