package prictise.com.application1.bean;

public class ProcessInfo {
  public int pid; // 进程id  Android规定android.system.uid=1000
  public int uid; // 进程所在的用户id ，即该进程是有谁启动的 root/普通用户等
  public int memSize; // 进程占用的内存大小,单位为kb
  public String processName; // 进程名
}
