package prictise.com.application1.map;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import prictise.com.application1.BaseActivity;
import prictise.com.application1.R;
import prictise.com.application1.utils.LogcatUtils;

/**
 * @Author zsj
 * @Date 2018-12-29 7:17
 * @Commit
 */
public class MapActivity extends BaseActivity {
    static int hashMapW = 0;
    static int hashMapR = 0;
    static int linkMapW = 0;
    static int linkMapR = 0;
    static int treeMapW = 0;
    static int treeMapR = 0;
    static int hashTableW = 0;
    static int hashTableR = 0;
    private String TAG = MapActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    test(10 * 10000);
                }

                LogcatUtils.showELog(TAG, "hashMapW = " + hashMapW / 10);
                LogcatUtils.showELog(TAG, "hashMapR = " + hashMapR / 10);
                LogcatUtils.showELog(TAG, "linkMapW = " + linkMapW / 10);
                LogcatUtils.showELog(TAG, "linkMapR = " + linkMapR / 10);
                LogcatUtils.showELog(TAG, "treeMapW = " + treeMapW / 10);
                LogcatUtils.showELog(TAG, "treeMapR = " + treeMapR / 10);
                LogcatUtils.showELog(TAG, "hashTableW = " + hashTableW / 10);
                LogcatUtils.showELog(TAG, "hashTableR = " + hashTableR / 10);
            }
        }).start();
    }

    public void test(int size) {
        int index;
        Random random = new Random();
        String[] key = new String[size];

        // HashMap 插入
        Map<String, String> map = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        long end = System.currentTimeMillis();
        hashMapW += (end - start);
        LogcatUtils.showELog(TAG, "HashMap插入耗时 = " + (end - start) + " ms");

        // HashMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashMapR += (end - start);
        LogcatUtils.showELog(TAG, "HashMap读取耗时 = " + (end - start) + " ms");

        // LinkedHashMap 插入
        map = new LinkedHashMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        linkMapW += (end - start);
        LogcatUtils.showELog(TAG, "LinkedHashMap插入耗时 = " + (end - start) + " ms");

        // LinkedHashMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        linkMapR += (end - start);
        LogcatUtils.showELog(TAG, "LinkedHashMap读取耗时 = " + (end - start) + " ms");

        // TreeMap 插入
        key = new String[size];
        map = new TreeMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        treeMapW += (end - start);
        LogcatUtils.showELog(TAG, "TreeMap插入耗时 = " + (end - start) + " ms");

        // TreeMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        treeMapR += (end - start);
        LogcatUtils.showELog(TAG, "TreeMap读取耗时 = " + (end - start) + " ms");

        // Hashtable 插入
        key = new String[size];
        map = new Hashtable<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        hashTableW += (end - start);
        LogcatUtils.showELog(TAG, "Hashtable插入耗时 = " + (end - start) + " ms");

        // Hashtable 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashTableR += (end - start);
        LogcatUtils.showELog(TAG, "Hashtable读取耗时 = " + (end - start) + " ms");
    }
}
