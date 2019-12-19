package prictise.com.application1.kotlin.greendao;

import android.content.Context;
import org.greenrobot.greendao.database.Database;

/**
 * @Author zhisiyi
 * @Date 2019.12.04 11:28
 * @Comment
 */
public class WxSqlOpenHelper
    extends DaoMaster.DevOpenHelper {

  private static WxSqlOpenHelper sWxSqlOpenHelper;

  private WxSqlOpenHelper(Context context, String name) {
    super(context, name);
  }

  public static WxSqlOpenHelper getWxSqlOpenHelper(Context context) {
    if (sWxSqlOpenHelper == null) {
      sWxSqlOpenHelper = new WxSqlOpenHelper(context, "TestGreenDao.db");
    }
    return sWxSqlOpenHelper;
  }

  @Override
  public void onCreate(Database db) {
    super.onCreate(db);
  }

  @Override
  public void onUpgrade(Database db, int oldVersion, int newVersion) {

  }

}
