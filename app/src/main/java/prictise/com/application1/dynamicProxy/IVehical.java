package prictise.com.application1.dynamicProxy;

public interface IVehical {

  @POST("aa")
  void run(@BODY String s, @GET int a);
}
