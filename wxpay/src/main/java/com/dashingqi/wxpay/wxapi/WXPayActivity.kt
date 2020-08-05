package com.dashingqi.wxpay.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.dashingqi.wxpay.impl.WXPay
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

/**
 * @author : zhangqi
 * @time : 2020/7/31
 * desc : 微信支付回调预处理Activity
 */
open class WXPayActivity : Activity(), IWXAPIEventHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WXPay.getWxApi().handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        WXPay.getWxApi().handleIntent(intent, this)
    }

    /**
     * 支付结果的回调
     */
    override fun onResp(p0: BaseResp?) {
        if (p0?.type == ConstantsAPI.COMMAND_PAY_BY_WX) {
            when (p0.errCode) {
                BaseResp.ErrCode.ERR_OK -> {
                    WXPay.mPayCallBack.onSuccess(WXPay.mPayInfo)
                }
                BaseResp.ErrCode.ERR_USER_CANCEL -> {
                    WXPay.mPayCallBack.onCancel()
                }
                BaseResp.ErrCode.ERR_COMM -> {
                    WXPay.mPayCallBack.onFail(p0.errCode, p0.errStr)
                }
            }
            finish()
        }
    }

    override fun onReq(p0: BaseReq?) {

    }
}