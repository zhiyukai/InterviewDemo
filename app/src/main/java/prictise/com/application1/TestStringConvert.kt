package prictise.com.application1

import android.graphics.Bitmap
import android.os.Environment
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.ArrayList

/**
 * @Author zhisiyi
 * @Date 2019.08.26 22:24
 * @Comment
 */
class TestStringConvert {

    @Throws(IOException::class)
    fun saveFile(bm: Bitmap, fileName: String): File {//将Bitmap类型的图片转化成file类型，便于上传到服务器
        val path = Environment.getExternalStorageDirectory().toString() + "/Ask"
        val dirFile = File(path)
        if (!dirFile.exists()) {
            dirFile.mkdir()
        }
        val myCaptureFile = File(path + fileName)
        val bos = BufferedOutputStream(FileOutputStream(myCaptureFile))
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos)
        bos.flush()
        bos.close()
        return myCaptureFile

    }
}
