package com.dashingqi.alipay.impl

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.alipay.sdk.app.PayTask
import com.dashingqi.alipay.bean.AliPayBean
import com.dashingqi.alipay.bean.AliPayErrorCode
import com.dashingqi.alipay.constant.AliConstant
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.dqpay.strategy.IPayStrategy

/**
 * @author : zhangqi
 * @time : 2020/8/4
 * desc : 支付宝支付的工具类
 */
class AliPay : IPayStrategy<AliPayBean> {


    lateinit var mAliPayCallback: IPayCallback<AliPayBean>
    lateinit var mActivity: Activity

    lateinit var mPayInfo: AliPayBean

    /**
     * 支付的入口
     */
    override fun pay(activity: Activity, payInfo: AliPayBean, callBack: IPayCallback<AliPayBean>) {
        mActivity = activity
        mAliPayCallback = callBack
        mPayInfo = payInfo

        if (checkPrePay()) {
            //支付的任务
            var payRunnable = Runnable {
                var payTask = PayTask(mActivity)
                var messageData = payTask.payV2(payInfo.orderInfo, true)

                //构造消息，携带支付数据
                var payMessage = Message()
                payMessage.what = AliConstant.SDK_PAY_FLAG
                payMessage.obj = messageData
                aliPayHandler.sendMessage(payMessage)
            }
            var aliPayThread = Thread(payRunnable)
            aliPayThread.start()
        }
    }

    private var aliPayHandler = Handler() { it ->
        when (it.what) {
            AliConstant.SDK_PAY_FLAG -> {
                //处理支付的结果
                var payMapData = it.obj as Map<String, String>
                for (keyStr in payMapData.keys) {
                    if (keyStr == "resultStatus") {
                        //拿到当前支付的结果码
                        var resultStatus = payMapData[keyStr]

                        //支付成功
                        when (resultStatus) {
                            AliConstant.ALI_PAY_SUCCESS -> {
                                mAliPayCallback?.let {
                                    it.onSuccess(mPayInfo)
                                }
                            }
                            AliConstant.ALI_PAY_CANCEL -> {
                                mAliPayCallback?.let {
                                    it.onCancel()
                                }
                            }
                            else -> {
                                mAliPayCallback?.let {
                                    it.onFail(
                                        resultStatus?.toInt() ?: -1000,
                                        AliConstant.getConstantCodeMSg(resultStatus!!)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        true
    }


    /**
     * 支付之前的检查
     */
    private fun checkPrePay(): Boolean {
        /**
         * 回调的检查
         */
        if (mAliPayCallback == null) {
            Toast.makeText(
                mActivity,
                AliPayErrorCode.getErrorStr(AliPayErrorCode.ALI_PAY_CALL_BACK_IS_NULL),
                Toast.LENGTH_LONG
            ).show()
            return false
        }

        /**
         * 检查是否安装支付宝
         */

        if (!isAliPayInstalled(mActivity)) {
            mAliPayCallback.onFail(
                AliPayErrorCode.ALI_IS_NOT_INSTALL,
                AliPayErrorCode.getErrorStr(AliPayErrorCode.ALI_IS_NOT_INSTALL)
            )
            return false
        }

        /**
         * 订单数据bean的检查
         */
        if (mPayInfo != null) {
            if (mPayInfo.orderInfo.isNullOrEmpty()) {
                mAliPayCallback.onFail(
                    AliPayErrorCode.ALI_PAY_ORDER_PARAMS_ERROR,
                    AliPayErrorCode.getErrorStr(AliPayErrorCode.ALI_PAY_ORDER_PARAMS_ERROR)
                )
                return false
            }
        } else {
            mAliPayCallback.onFail(
                AliPayErrorCode.ALI_PAY_DATA_BEAN_IS_NULL,
                AliPayErrorCode.getErrorStr(AliPayErrorCode.ALI_PAY_DATA_BEAN_IS_NULL)
            )
            return false
        }
        return true
    }


    /**
     * 检测是否安装支付宝
     * @param context
     * @return
     */
    private fun isAliPayInstalled(context: Context): Boolean {
        var uri = Uri.parse("alipays://platformapi/startApp");
        var intent = Intent(Intent.ACTION_VIEW, uri);
        var componentName = intent.resolveActivity(context.packageManager);
        return componentName != null
    }
}