package prictise.com.application1;

import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import org.junit.Test;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import prictise.com.application1.multithreading.Consumer;
import prictise.com.application1.multithreading.Producter;
import prictise.com.application1.multithreading.SyncStack;

/**
 * @author zhisiyi
 * @date 18.6.24 13:15
 * @comment
 */
public class TestCase {

    @Test
    public void test1() {
        String s1 = "a";
        String s2 = new String("a");

        float a = 10.222222225f, b = 10.222222229f;

        System.out.println((s1 == s2));
        System.out.println(s1.equals(s2));

        System.out.println("-------使用“==”符号比较是否相等：" + (a == b));
        System.out.println("-------使用“Math.abs()”方法比较是否相等：" + (Math.abs(a - b) > 0));
        System.out.println("-------使用“Math.abs()”方法比较是否相等(比较宽容的限制)：" + (Math.abs(a - b) < 0.00000001));
        System.out.println("-------比较大小“<”：" + (a < b));
        System.out.println("-------比较大小“>”：" + (a > b));
    }

    @Test
    public void test2() {
        Integer i1 = 200;
        Integer i2 = 200;
        System.out.println("i1.equals(i2):" + (i1.equals(i2)));
        System.out.println("i1 == i2:" + (i1 == i2));

        Integer i3 = 100;
        Integer i4 = 100;
        System.out.println("i3.equals(i4):" + (i3.equals(i4)));
        System.out.println("i3 == i4:" + (i3 == i4));

        Long l1 = 200l;
        Long l2 = 200l;
        System.out.println("l1 == l2:" + (l1.equals(l2)));


        Double d1 = 200d;
        Double d2 = 200d;
        System.out.println("d1 == d2:" + (d1.equals(d2)));

        Float f1 = 200f;
        Float f2 = 200f;
        System.out.println("f1 == f2:" + (f1.equals(f2)));
    }

    private String[][] s = {{"北京", "海南"},
            {"上海", "南京"},
            {"广州", "福建"},
            {"海南", "浙江"},
            {"浙江", "上海"},
            {"南京", "广州"}};

    String[] result = {"北京", "海南", "浙江", "上海", "南京", "广州"};

    @Test
    public void test3() {
        String start = "北京";
        int len = s.length;
        int indexResult = 0;
        int indexTwoArray = 0;
        String[] result = new String[len];
        result[indexResult] = start;

        while (indexResult < s.length) {
            if (indexTwoArray == s.length) {
                indexTwoArray = 0;
            }
            if (s[indexTwoArray][0].equals(start)) {
                indexResult++;
                if (indexResult < s.length) {
                    result[indexResult] = s[indexTwoArray][1];
                    start = result[indexResult];
                }
            }
            indexTwoArray++;
        }
        printResult(result);
    }

    @Test
    public void printArray() {
        int len = s.length;
        for (int i = 0; i < len; i++) {
            System.out.print(s[i][0] + " ");
        }
    }

    private void printResult(String[] result) {
        int resultlen = result.length;
        for (int i = 0; i < resultlen; i++) {
            System.out.print(result[i] + " ");
        }
    }

    @Test
    public void producterCusumer() {
        SyncStack stack = new SyncStack();
        Consumer c = new Consumer(stack);
        Producter p = new Producter(stack);

        new Thread(p).start();
//        new Thread(c).start();
    }

    @Test
    public void treeMap() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        TreeMap treeMapObject = new TreeMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        HashMap hashMapObject = new HashMap<>();
        hashMapObject.put(null, "zhangsan");
        treeMapObject.put("name", null);
        treeMapObject.put("desc", null);
        treeMap.put(2, 1);
        treeMap.put(1, 10);
        treeMap.put(10, 4);
        treeMap.put(4, 2);
        for (Integer integer : treeMap.keySet()) {
            System.out.println(integer + ":" + treeMap.get(integer));
        }
    }

    @Test
    public void testAdd() {
        int i = 0;
        System.out.println(i++);
        System.out.println(i);
        int j = 0;
        System.out.println(++j);
    }

    // 这是写入异常
    @Test
    public void bufferOverflowException() {
        //  这里只分配了2个字节，下面的params.put(tmp);却put了3个字节的数据。
        // 所以导致 java.nio.BufferOverflowException 异常
        ByteBuffer params = ByteBuffer.allocate(2);
        params.order(ByteOrder.LITTLE_ENDIAN);
        byte[] tmp = new byte[3];
        tmp[0] = 'a';
        tmp[1] = 'b';
        tmp[2] = 'c';
        params.put(tmp);
    }

    // 这是读取异常
    @Test
    public void bufferUnderflowException() {
        //  这里只分配了2个字节，下面的params.put(tmp);却put了3个字节的数据。
        // 所以导致 java.nio.BufferOverflowException 异常
        ByteBuffer params = ByteBuffer.allocate(2);
        params.order(ByteOrder.LITTLE_ENDIAN);
        byte[] tmp = new byte[3];
        params.get(tmp);
    }

    @Test
    public void linkedList() {
        Deque queue = new LinkedList();
    }


    @Test
    public void deleteArrayElements() {
        int[] s = {5, 7, 2, 9};

        int lastRet = -1; // index of last element returned; -1 if no such
        int k = 3;

        System.out.println("值为：" + s[lastRet = k]);
        System.out.println("lastRet = " + lastRet);


        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            arrayList.add(s[i]);
        }

        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            int r = it.next();
            if (r > 2) {
                it.remove();
            }
        }

        int len = arrayList.size();
        for (int i = 0; i < len; i++) {
            System.out.print("剩余的值：" + arrayList.get(i) + " ");
        }
    }

    @Test
    public void splitString() {
        String s = "/sdcard/picture_pad/1534237896388+91.96875+.jpg";
        String[] sT = s.split("/");
        int len = sT.length;
        for (int i = 0; i < len; i++) {
            System.out.println("sT[" + i + "] = " + sT[i]);
        }
        String dest = sT[3].split("\\+")[0];
        long time = Long.parseLong(dest);
        long t = 10 * 60 * 1000;
        long endTime = System.currentTimeMillis();

        System.out.print(endTime - time);
    }


    @Test
    public void printFileName() {
        String path = Environment.getExternalStorageDirectory() + "/picture_pad";
        File file = new File(path);
        File[] files = file.listFiles();
        int len = files.length;

        for (int i = 0; i < len; i++) {
            System.out.println(files[i].getPath());
        }
    }

    @Test
    public void analyzeString() {
        String s = "从推动“绿色浙江”建设到担任总书记期间@startEye(love)，\" " +
                "        \"习近平一直将绿色发展理念贯穿于治国理政思想之中@startEye(angry)。\" " +
                "        \"党的十八大以来，以习近平同志@startEye(shy)为核心的党中央把生态文\" " +
                "        \"明建设摆在改革发展和@startEye(look)现代化建设全局位置，坚定贯彻新发\" " +
                "        \"展理念@startEye(see)，不断深化生态文明体制改革@startEye(default)，" +
                "        \"开创了生态文明建设和环境保护新局面。";


        System.out.println(s.indexOf("@"));
        System.out.println(s.substring(0,s.indexOf("@")));
        System.out.println(s.indexOf("("));
        System.out.println(s.substring(s.indexOf("@"), s.indexOf(")")+1));

        s = s.substring(s.indexOf(")")+1);
        System.out.println(s);
    }

    @Test
    public void testNum() {
        int a = 10_000;
        System.out.println("a = " + a);
    }
}
