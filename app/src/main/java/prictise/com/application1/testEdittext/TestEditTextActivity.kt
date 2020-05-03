package prictise.com.application1.testEdittext

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import prictise.com.application1.BaseActivity
import prictise.com.application1.R
import prictise.com.application1.utils.LogcatUtils

/**
 * @Author zhisiyi
 * @Date 2019.11.22 08:57
 * @Comment
 */
class TestEditTextActivity : BaseActivity() {
    private val TAG = TestEditTextActivity::class.simpleName

    var mTestET: EditText? = null
    var mAddTestET: EditText? = null
    var mInitEtBT: Button? = null
    var mAddEtBT: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_edittext)
        initView()
        initListener()
    }

    private fun initListener() {
        mInitEtBT?.setOnClickListener {
            LogcatUtils.showDLog(TAG, "mInitEtBT?.setOnClickListener")
            mTestET?.setText("")
            LogcatUtils.showDLog(TAG, "mTestET? = ${mTestET?.text}")
        }

        mAddEtBT?.setOnClickListener {
            LogcatUtils.showDLog(TAG, "mAddEtBT?.setOnClickListener")
            var addString = mAddTestET?.text
            mTestET?.text?.append(addString)
        }

    }

    private fun initView() {
        mTestET = findViewById(R.id.et_test)
        mAddTestET = findViewById(R.id.et_test_add)
        mInitEtBT = findViewById(R.id.bt_init_et)
        mAddEtBT = findViewById(R.id.bt_add_string)
    }
}