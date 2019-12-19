package prictise.com.application1;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Environment;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import prictise.com.application1.bean.Student;

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
        System.out
            .println("-------使用“Math.abs()”方法比较是否相等(比较宽容的限制)：" + (Math.abs(a - b) < 0.00000001));
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

        Long l1 = 200L;
        Long l2 = 200L;
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
//    SyncStack stack = new SyncStack();
//    Consumer c = new Consumer(stack);
//    Producter p = new Producter(stack);

//    new Thread(p).start();
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
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
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

    @Test
    public void testReplace() {
        String a = "abc";
        String b = a.replace('a', 'A');
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    @Test
    public void testHashMap() {
        HashMap<String, String> hm = new HashMap<>(30);
        for (int i = 0; i < 30; i++) {
            hm.put("key" + i, "value" + i);
        }

        Iterator<Map.Entry<String, String>> iterator = hm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key = " + entry.getKey() + "; value = " + entry.getValue());
        }
    }

    @Test
    public void testLinkedAccessFalseHashMap() { //
        // false 是访问排序 true 是插入排序
        LinkedHashMap<String, String> hm = new LinkedHashMap<>(30, 0.75f, false);
        for (int i = 0; i < 30; i++) {
            hm.put("key" + i, "value" + i);
        }

        Iterator<Map.Entry<String, String>> iterator = hm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key = " + entry.getKey() + "; value = " + entry.getValue());
        }
    }

    @Test
    public void testLinkedAccessTrueHashMap() { //
        // false 是访问排序 true 是插入排序
        LinkedHashMap<String, String> hm = new LinkedHashMap<>(30, 0.75f, true);
        for (int i = 0; i < 30; i++) {
            hm.put("key" + i, "value" + i);
        }

        String value2 = hm.get("key2");

        Iterator<Map.Entry<String, String>> iterator = hm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key = " + entry.getKey() + "; value = " + entry.getValue());
        }
    }


    @Test
    public void randomDigit() {
        Random random = new Random();
        int ran = random.nextInt(10);

        System.out.println("randomDigit = " + ran);
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            System.out.println("randomDigit1 = " + ThreadLocalRandom.current().nextInt());
        }
    }

    @Test
    public void testSplit() {
        String[] s = {"a", "b", "c", "d"};
        String result = "";
        for (int i = 0; i < 4; i++) {
            result = result + s[i] + ";";
        }

        result = result.substring(0, result.length() - 1);
        String[] r = result.split(";");

        System.out.println("testSplit() result = " + result);
        for (int i = 0; i < r.length; i++) {
            System.out.println("testSplit() r [ " + i + " ] = " + r[i]);
        }


    }

    /**
     * 整型转化成字符串
     */
    private String intToString(int num) {
        String result = "";
        while (num > 0) {

        }
        return result;
    }

    @Test
    public void testSub() {
        String test = "春风轻轻地吹过南国大地，树儿长出绿叶，花儿竞相绽放，湖水泛起波纹，天空蓝蓝，白云飘飘，阳光明媚，洒向人间，整个世界和暖而明亮。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　冬日的南国，不时落下潇潇冷雨，冬风吹过，一阵寒冷迎面扑来。那些行色匆匆的女子，也不禁裹紧外套。冬日不是不好，景色也很美，冰条挂满枝头，全世界耀眼的白。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　雨，随风潜入夜，润物细无声。而我觉得，春风是有生命的，一滴、两滴，轻轻地落在叶子上，让叶子散发出翠绿的光泽。春雨，轻吻花朵，让花儿更加美艳。春雨用它的指尖在湖面作画，在女子心里作诗。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春日时光，是一段暖洋洋的日子。阳光，在对我们微笑，我们伸出双手，让光线穿透指尖，来一场温暖的对话。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春天里，芒果花耀眼的开着，一朵朵，一簇簇，微笑着点缀着枝头。紫荆花也开得如火如荼，像一束束紫色的火焰，燃烧着妩媚与辉煌。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春天，是我最喜欢的季节，柳绿花红，充满色彩，一切都那么生机勃勃。走在长长的林荫道上，抬头望望天，不禁向着阳光微笑。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春风是四季最温柔的风。当我在烟雨中，穿着紫色的长裙，踏着高跟鞋，风轻轻吹起我的秀发，仿佛我深爱的你为我温柔地梳着头发。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春天里，那一望无际的浅蓝，像你宽广的胸怀，让我依靠，让我撒娇，让我在滚滚红尘里找到可以依靠的怀抱。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春雨里，我若如一朵粉色的桃花，尽管我没有倾城的容貌，没有寒梅的坚强，没有夏竹的高雅，没有秋菊的多姿，可我的心是粉红的，在你的怀抱里温柔舞蹈，只有在你的泪水里，我看见你对我的千倍怜爱，万般地疼惜。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春天，是踏青的季节。我与你在水云间度过欢乐、美好的日子。年轻的心飞扬，你是风儿，我是沙，缠缠绵绵走天涯。我们种下桃花，你与我定下三生三世的誓言，只有天地和，才敢与君绝。蒲草韧如丝，磐石无转移。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　春天，是属于恋爱的季节。水云间好山好水好风光。与云为友，与花为怑，享受着明媚的春光，在珠帘般的春雨里吟诗作画，在春风细雨里编织美好的未来。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　爱在春色深深处。大胆去爱吧，这是个自由的年代；深情去爱吧，不要辜负了这美好的季节，这美丽动人的风景，不要错过了如此美好的时光。\\n\" +\n"
            + "            \"\\n\" +\n"
            + "            \"　　爱在春色深深处。天上人间几回闻？我们的青春应该用热忱洋溢、不忘初心来形容。爱着风、爱着雨、爱着阳光、爱着春花、爱着蓝天，彼此不离不弃，为爱付出，";
        String init = "冬天的第一场雪";
        String c0 = test.substring(0, 1);
        String c1 = test.substring(1);
        System.out.println("c0 = " + (c0 + ",") + "| c1 = " + c1);
    }

    @Test
    public void testAddFriend() {
        HashMap<String, String> map = new HashMap<>();
        for (Entry<String, String> entry : map.entrySet()) {

        }

        Set<Entry<String, String>> set = map.entrySet();
        int len = set.size();
        for (int i = 0; i < len; i++) {
            Entry<String, String> entry = set.iterator().next();
        }

        Set set1 = new HashSet();

    }

    @Test
    public void testComparable() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("b", 19));
        students.add(new Student("c", 28));
        students.add(new Student("a", 18));

        Collections.sort(students, new Comparator<Student>() {

            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    @Test
    public void testAtomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.incrementAndGet();
        atomicInteger.incrementAndGet();
        atomicInteger.incrementAndGet();
        atomicInteger.set(0);
        System.out.println(atomicInteger.get());
    }

    @Test
    public void testContains() {
        ArrayList<Student> alStu1 = new ArrayList<>();
        Student student1 = new Student("zs", 13);
        alStu1.add(student1);
        alStu1.add(new Student("ls", 14));

        ArrayList<Student> alStu2 = new ArrayList<>();
        alStu2.add(new Student("zs", 13));
        alStu2.add(new Student("ls2", 14));

        System.out.println(alStu2.contains(student1));
    }

    @Test
    public void testQCCodeName() {
        String qccode = "mmqrcode1576224273562.png";

        System.out.println(qccode.indexOf("e"));

        System.out.println(qccode.substring(qccode.indexOf("e")+1,qccode.lastIndexOf(".")));

        System.out.println(qccode.startsWith("mmqrcode"));


    }

}
