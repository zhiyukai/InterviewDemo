package prictise.com.application1.suanFa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author zhisiyi
 * @Date 2019.10.29 11:21
 * @Comment
 */
public class HashMapIte {

  public static void main(String[] args) {
//    HashMap<String, String> map = new HashMap<>();
//    for (Map.Entry<String, String> entry : map.entrySet()) {
//      System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//
//    }
//    Iterator iterator = map.keySet().iterator();

//    List<String> list = new ArrayList<String>();
//    list.add("hello");
//    list.add("world");
//    list.add("helloworld");
//    for (int i = 0; i < list.size(); i++) {
//      list.remove(i);
//    }
//    System.out.println(list);

    List<String> list = new ArrayList<String>();
    list.add("hello");
    list.add("world");
    list.add("helloworld");

    for (Iterator<String> ite = list.iterator(); ite.hasNext();) {
      String next = ite.next();
      ite.remove(); //关键看此处
    }
    System.out.println(list.size());
  }
}
