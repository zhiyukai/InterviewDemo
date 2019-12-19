package prictise.com.application1.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @Author zhisiyi
 * @Date 2019.11.07
 * @Comment
 */
public class UnZipFile {

  public static String ungzip(String compressedStr) {
    if (compressedStr == null) {
      return null;
    }
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ByteArrayInputStream in = null;
    GZIPInputStream ginzip = null;
    byte[] compressed = null;
    String decompressed = null;
    try {
      compressed = compressedStr.getBytes("ISO-8859-1");
      in = new ByteArrayInputStream(compressed);
      ginzip = new GZIPInputStream(in);
      byte[] buffer = new byte[1024];
      int offset = -1;
      while ((offset = ginzip.read(buffer)) != -1) {
        out.write(buffer, 0, offset);
      }
      out.flush();
      decompressed = out.toString();
      if (ginzip != null) {
        ginzip.close();
      }
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return decompressed;
  }

  public static String getPrefix(String path) {
    int index = path.lastIndexOf('.');
    return path.substring(0, index);
  }

  public static void unGzip(String srcPath) {
    unGzip(new File(srcPath));

  }

  public static void unGzip(File src) {

    String path = getPrefix(src.getAbsolutePath());
    GZIPInputStream gzs = null;
    BufferedOutputStream bos = null;
    try {
      gzs = new GZIPInputStream(new FileInputStream(src));
      bos = new BufferedOutputStream(new FileOutputStream(path));

      byte[] buf = new byte[102400];
      int len = -1;
      while ((len = gzs.read(buf)) != -1) {
        bos.write(buf, 0, len);
      }
      bos.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // FileUtil.close(gzs, bos);
      if (gzs != null) {
        try {
          gzs.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (bos != null) {
        try {
          bos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    }

  }

  /**
   * 存放压缩文件的目录
   * @param dirPath
   */
  public static void mutiputeFilesZip(String dirPath) {
    if (dirPath != null) {
      File fDir = new File(dirPath);
      if (fDir.isDirectory()) {
        File[] fList = fDir.listFiles();
        int fListLen = fList.length;
        for (int i = 0; i < fListLen; i++) {
          unGzip(fList[i]);
        }
      }
    }
  }

  public static void main(String[] args) {
    try {
      String sourceDir = "C:\\Users/zhishaoju\\Documents\\SunLands_File\\logs\\wangzhaosheng13_20191106";
      mutiputeFilesZip(sourceDir);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
