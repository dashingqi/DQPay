package com.dashingqi.wxpay.bean

import com.dashingqi.dqpay.bean.IPayInfoBean

/**
 * @author : zhangqi
 * @time : 2020/7/30
 * desc : 支付信息的数据bean
 */
class WXPayInfoBean : IPayInfoBean {
    var appId = ""
    var partnerId = ""
    var prepayId = ""
    var packageValue = "Sign=WXPay"
    var nonceStr = ""
    var timeStamp = ""
    var sign = ""
}