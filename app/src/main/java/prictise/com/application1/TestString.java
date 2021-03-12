package prictise.com.application1;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author zhisiyi
 * @Date 2019.08.26 22:24
 * @Comment
 */
public class TestString {

  public synchronized static String[] result(String[] array1, String[] array2) {

    if (array1 == null) {
      return null;
    }
//    ArrayList<String> resultArray = new ArrayList<>();
//    if (array1 != null && array2 == null) {
//      int len = array1.length;
//      for (int i = 0; i < len; i++) {
//        resultArray.add(array1[i]);
//      }
//      return resultArray;
//    }
    if (array1 != null && array2 == null) {
      return array1;
    }

    ArrayList<String> resultArray = new ArrayList<>();
    int lenArray1 = array1.length;
    int lenArray2 = array2.length;

    for (int i = 0; i < lenArray1; i++) {
      boolean isExits = false;
      for (int j = 0; j < lenArray2; j++) {
        if (array1[i].equals(array2[j])) {
          isExits = true;
          break;
        }
      }
      if (!isExits) {
        resultArray.add(array1[i]);
      }
    }

    int lenResult = resultArray.size();
    String[] resultArr = new String[lenResult];
    for (int i = 0; i < lenResult; i++) {
      resultArr[i] = resultArray.get(i);
    }
    return resultArr;
  }

  public static void main(String[] args) {
    String[] array1 = {"Alex", "Bob", "John"};
    String[] array2 = {"Bob", "Alan", "David"};

    Object[] arrayo = {new String("对象1"), new String("对象2")};

    Class aClazz = array1.getClass();
    Class aClazz2 = arrayo.getClass();
    Class a = String.class.getClass();
    System.out.println("aClazz = " + aClazz);
    System.out.println("aClazz2 = " + aClazz2);
    System.out.println("a = " + a);
  }

  public File saveFile(Bitmap bm, String fileName)
          throws IOException {//将Bitmap类型的图片转化成file类型，便于上传到服务器
    String path = Environment.getExternalStorageDirectory() + "/Ask";
    File dirFile = new File(path);
    if (!dirFile.exists()) {
      dirFile.mkdir();
    }
    File myCaptureFile = new File(path + fileName);
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
    bos.flush();
    bos.close();
    return myCaptureFile;

  }
}
