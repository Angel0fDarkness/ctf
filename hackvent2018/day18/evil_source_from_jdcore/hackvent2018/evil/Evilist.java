package hackvent2018.evil;

public class Evilist {
  public Evilist() {}
  
  public static void main(String[] args) throws Exception {
    EvilLoader evilLoader = new EvilLoader(Evilist.class.getClassLoader());
    



    EvilLoader loader = new EvilLoader(Evilist.class.getClassLoader());
    Class<?> clazz = loader.loadClass("hackvent2018.evil.EvilWindow");
    Class<?> action = loader.loadClass("hackvent2018.evil.EvilAction");
    Class<?> evil = loader.loadClass("hackvent2018.evil.Evil");
    Class<?> event = loader.loadClass("hackvent2018.evil.EvilEvent");
    Class<?> handler = loader.loadClass("hackvent2018.evil.EvilHandler");
    Class<?> images = loader.loadClass("hackvent2018.evil.EvilImages");
    Class<?> type = loader.loadClass("hackvent2018.evil.EvilType");
    Class<?> notEvil = loader.loadClass("hackvent2018.evil.NotEvil");
    Class<?> question = loader.loadClass("hackvent2018.evil.Question");
    Class<?> sad = loader.loadClass("hackvent2018.evil.Sad");
    clazz.newInstance();
  }
}
