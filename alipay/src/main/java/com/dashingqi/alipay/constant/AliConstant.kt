package com.dashingqi.alipay.constant

/**
 * @author : zhangqi
 * @time : 2020/8/5
 * desc :
 */
object AliConstant {

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


}