package prictise.com.application1;

import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
        System.out.println(s.substring(0, s.indexOf("@")));
        System.out.println(s.indexOf("("));
        System.out.println(s.substring(s.indexOf("@"), s.indexOf(")") + 1));

        s = s.substring(s.indexOf(")") + 1);
        System.out.println(s);
    }

    @Test
    public void testNum() {
        int a = 10_000;
        System.out.println("a = " + a);
    }

    @Test
    public void testMapRemoveKey() {
        Map<String, String> params = new TreeMap<>();
        params.put("ts", "" + System.currentTimeMillis());
        params.put("key", "higgs_robot_03214");

        params.put("quest_id", "qstUpdateTable.getQuest_id()");
        params.put("name", "qstUpdateTable.getName()");
        params.put("pos_start", "qstUpdateTable.getPos_start()");
        params.put("pos_end", "qstUpdateTable.getPos_end()");
        params.put("mileage", "qstUpdateTable.getMileage()");//里程
        params.put("ts_start", "qstUpdateTable.getTs_start()");
        params.put("ts_arrv", "getTs_arrv");
        params.put("ts_end", "qstUpdateTable.getTs_end()");

//        System.out.print("字符串：" + getJsonDataToMD5(params, "utf-8").toString());
        System.out.print("字符串：" + params.toString());

    }

    public static StringBuffer getJsonDataToMD5(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            stringBuffer.append("{\"");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("\":\"")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("\",\"");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个","
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    @Test
    public void json() throws JSONException {
        String data = "{\"code\":\"10001\","
                + "\"message\":\"token_required\",\"data\":\"[]\"}";
        JSONObject jsonObject = new JSONObject(data);
        jsonObject.getString("data");
    }

    @Test
    public void time() {
        long time = System.currentTimeMillis();
        System.out.println(encryption(time + ""));
    }

    public String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    @Test
    public void splitString2() {
        String s = "/sdcard/crash/crash-2018-11-08+12-30-23.log";
        String s2 = s.split("/")[3];
        int startIndex = s2.indexOf("-");
        int endIndex = s2.indexOf(".");
        System.out.println(s2.substring(startIndex + 1, endIndex));
    }

    @Test
    public void dateStringToMillis() {
        String dateTime = "2018-11-08+12-30-23";
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd+HH-mm-ss").parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("日期[2018-11-08]对应毫秒：" + calendar.getTimeInMillis());
    }

    @Test
    public void testReturn() {
        testReturn3();
        int a = 3;
        if (a == 3) {
            System.out.println("a == 3");
            while (true) {
                if (a < 20) {
                    System.out.println("reconnectSocket 次数 " + a);
                } else {
                    System.out.println("return");
                    a = 0;
                    return;
                }
                if (a == 10) {
                    return;
                }
                a++;
            }
        }
    }

    @Test
    public void testReturn3() {
        String s2 = "1|2";
        String[] cruise = s2.split("\\|");
        StringBuilder cruisePoints = new StringBuilder();
        int size = cruise.length;
        for (int i = 0; i < size; i++) {
            System.out.println("cruise[" + i + "]" + cruise[i]);
            cruisePoints.append(cruise[i]);
            cruisePoints.append(",");
        }
        cruisePoints.deleteCharAt(cruisePoints.length() - 1);
        System.out.println("cruisePoints = " + cruisePoints.toString());
    }

    @Test
    public void testTreeSet() {
        TreeSet<Person> ts = new TreeSet<>();
        ts.add(new Person("张三", 23));
        ts.add(new Person("李四", 13));
        ts.add(new Person("周七", 13));
        ts.add(new Person("王五", 43));
        ts.add(new Person("赵六", 33));

        System.out.println(ts);
    }

    class Person {
        String name;
        int age;

        public Person(String n, int a) {
            name = n;
            age = a;
        }
    }

}
