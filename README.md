## DQPay使用文档
- 目前该项目集成了微信支付和支付宝支付

#### 微信支付使用步骤
###### 引入依赖
```
implementation 'com.dashingqi:wxpay:0.9.9.3'
```
###### 新建WxEntryActivity（支付回调的Activity）
- 新建Activity
**注意：该Activity所存放的包名是 applicationId.wxapi下的**
```kotlin
// 继承 WXPayActivity 该Activity在对应的声明周期方法和回调方法中做了回调处理，只需继承即可不用做其他处理
class WxEntryActivity: WXPayActivity() {
}
```
- 在AndroidManifest中注册该Activity
```xml
 <!--        注册微信支付的回调处理的Activity-->
        <activity
            android:name=".wxapi.WxEntryActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:exported="true"
            android:launchMode="singleTop" />
```
###### 发起微信支付
- 步骤1：初始化
```kotlin
 //初始化微信支付
        WXPay.initWxPay(this, "appid")
```
- 步骤2：拿到支付请求数据实体
```kotlin
    //微信支付请求参数
        var wxPayInfoBean = WXPayInfoBean()
        wxPayInfoBean.appId = ""
        wxPayInfoBean.nonceStr=""
```
- 步骤3：传入回调，发起支付请求
```kotlin
        //回调
        var wxPayCall = object : IPayCallback<WXPayInfoBean> {
            override fun onFail() {

            }

            override fun onSuccess(data: WXPayInfoBean) {

            }

            override fun onCancel() {

            }
        }

        //发起微信支付
        PayUtils.pay(WXPay, this, wxPayInfoBean, wxPayCall)

```
###### 微信支付失败结果码
|结果码|解释|
｜:-----|:-----|
｜-100|微信支付信息参数错误|
|-50|微信支付实体数据为空｜
｜-200｜微信支付回调为空|
|-300|微信支付初始化失败|
|-400|没有安装微信|
#### 支付宝支付使用步骤

###### 引入依赖
```
implementation 'com.dashingqi:alipay:0.9.9.3'
```
###### 发起支付宝支付请求
- 步骤1：拿到支付请求数据实体
```kotlin
   var aliPayBean = AliPayBean()
   aliPayBean.orderInfo = ""
```
- 步骤2：传入支付回调，发起支付宝支付请求
```kotlin
        // 支付的回调
        var aliPayCallback = object : IPayCallback<AliPayBean> {
            override fun onCancel() {

            }

            override fun onFail() {

            }

            override fun onSuccess(data: AliPayBean) {

            }

        }

        //发起支付宝支付
        PayUtils.pay(AliPay(), this, aliPayBean, aliPayCallback)
```
###### 支付宝支付失败结果码
|结果码|解释|
｜:-----|:-----|
｜-100|支付宝支付信息参数错误|
|-50|支付宝支付实体数据为空｜
｜-200｜支付宝支付回调为空|
|-400|没有安装微信|
|8000|订单正在处理中|
|4000|支付失败|
|6002|网络错误|
|6004|没有得到支付结果|