package com.dashingqi.alipay.constant

/**
 * @author : zhangqi
 * @time : 2020/8/5
 * desc :
 */
object AliConstant {


    val codeMap by lazy {
        var codeMaps = mutableMapOf<String, String>()
        codeMaps[ALI_PAY_ING] = ALI_PAY_ING_MSG
        codeMaps[ALI_PAY_FAIL] = ALI_PAY_FAIL_MSG
        codeMaps[ALI_PAY_NET_WORK_ERROR] = ALI_PAY_NET_WORK_ERROR_MSG
        codeMaps[ALI_PAY_NOT_GET_RESULT] = ALI_PAY_NOT_GET_RESULT_MSG
        codeMaps
    }

    //支付信息的标志
    const val SDK_PAY_FLAG: Int = 1


    // https://opendocs.alipay.com/open/59/103671 支付宝支付的就结果码
    /**
     * 支付成功的状态
     */
    const val ALI_PAY_SUCCESS: String = "9000"

    /**
     * 支付中途的取消
     */
    const val ALI_PAY_CANCEL: String = "6001"

    /**
     * 订单正在处理中
     */
    const val ALI_PAY_ING = "8000"
    private const val ALI_PAY_ING_MSG = "订单正在处理中"

    /**
     * 支付失败
     */
    const val ALI_PAY_FAIL = "4000"
    private const val ALI_PAY_FAIL_MSG = "支付失败"

    /**
     * 支付网络错误
     */
    const val ALI_PAY_NET_WORK_ERROR = "6002"
    private const val ALI_PAY_NET_WORK_ERROR_MSG = "网络错误"

    /**
     *  支付没有得到结果
     */
    const val ALI_PAY_NOT_GET_RESULT = "6004"
    private const val ALI_PAY_NOT_GET_RESULT_MSG = "没有得到支付结果"


    fun getConstantCodeMSg(code: String): String {
        return codeMap[code] ?: ""
    }

}