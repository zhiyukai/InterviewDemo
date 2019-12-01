package prictise.com.application1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import gdut.bsx.share2.demo.QueRenZhiFuActivity;

/**
 * @Author zhisiyi
 * @Date 2019.11.24 20:47
 * @Comment
 */
public class TestPay extends BaseActivity {

  //  private IWXAPI api;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//    api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
//    api.registerApp(Constants.APP_ID);
    Intent intent = new Intent(this, QueRenZhiFuActivity.class);
    startActivity(intent);
  }

//  private void pay() {
//    String url = "http://pay.qiaobahuyu.com/jf/pay/wx/result";
////    Button payBtn = (Button) findViewById(R.id.appay_btn);
////    payBtn.setEnabled(false);
////    Toast.makeText(QueRenZhiFuActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
//    try {
//      PayReq req = new PayReq();
//      //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//      req.appId = Constants.APP_ID;
//      req.partnerId = "1";
//      req.prepayId = "wx23225303144921a446d9bdf81164949200";
//      req.nonceStr = "test";
//      req.timeStamp = String.valueOf(SystemClock.elapsedRealtime());
//      req.packageValue = "test package";
//      req.sign = "test sign";
//      req.extData = "app data"; // optional
//      Toast.makeText(QueRenZhiFuActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//      // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//      api.sendReq(req);
//    } catch (Exception e) {
//      Log.e("PAY_GET", "异常：" + e.getMessage());
//      Toast.makeText(this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//    }
////    payBtn.setEnabled(true);
//  }
}
