package prictise.com.application1.testGreendao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import org.greenrobot.greendao.database.Database;
import prictise.com.application1.BaseActivity;
import prictise.com.application1.kotlin.greendao.DaoMaster;
import prictise.com.application1.kotlin.greendao.DaoSession;

/**
 * @Author zhisiyi
 * @Date 2019.11.21 11:45
 * @Comment
 */
public class TestGreenDaoActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "class.db", null);
    Database db = helper.getWritableDb();
    DaoSession daoSession = new DaoMaster(db).newSession();
    StudentDao studentDao = daoSession.getStudentDao();
    studentDao.insertOrReplaceInTx(new Student(1L,"11","z","f","34"));

    EditText editText = new EditText(this);
    editText.getText().append("sss");
  }
}
