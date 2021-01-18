package prictise.com.application1.dynamicProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author zhisiyi
 * @Date 2019.12.02 20:47
 * @Comment
 */
public class TestDynamic {

  public static void main(String[] args) throws Exception {
//    Car c = new Car();
//    IVehical iVehical = (IVehical) Proxy
//        .newProxyInstance(IVehical.class.getClassLoader(), new Class[]{IVehical.class},
//            new VehicalInvacationHandler(c));
//    iVehical.run("sss",4);

    //1.加载Class对象
    Class clazz = Class.forName("prictise.com.application1.dynamicProxy.DynamicProxyActivity");

    Annotation[] declaredAnnotations = clazz.getDeclaredAnnotations();
    for (Annotation annotation: declaredAnnotations) {
      System.out.println(annotation);
    }
    System.out.println("---------------------");

//java反射机制获取所有方法名
    Method[] declaredMethods = clazz.getDeclaredMethods();
    //遍历循环方法并获取对应的注解名称
    for (Method declaredMethod : declaredMethods) {
      System.out.println(declaredMethod);
      boolean annotationPresent = declaredMethod.isAnnotationPresent(POST.class);
      if (annotationPresent) {
        // 获取自定义注解对象
        POST methodAnno = declaredMethod.getAnnotation(POST.class);
        // 根据对象获取注解值
        String v = methodAnno.value();
        System.out.println("v = " + v);
      }
//      String isNotNullStr = "";
      // 判断是否方法上存在注解  MethodInterface
//      boolean annotationPresent = declaredMethod.isAnnotationPresent(MethodInterface.class);
//      if (annotationPresent) {
//        // 获取自定义注解对象
//        MethodInterface methodAnno = declaredMethod.getAnnotation(MethodInterface.class);
//        // 根据对象获取注解值
//        isNotNullStr = methodAnno.name();
//      }
//      list.add(new KeyValueDto(declaredMethod.getName(),isNotNullStr));
    }
  }

}
