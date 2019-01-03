package hackvent2018.evil;

import java.lang.reflect.Field;
import java.io.*;

public class EvilLoader extends ClassLoader { public EvilLoader(ClassLoader parent) { super(parent); }
  
  private Class getClass(String name) throws ClassNotFoundException
  {
    byte[] b = loadEvilClass(name);
    return defineClass(name, b, 0, b.length);
  }
  
  public Class<?> loadClass(String name)
    throws ClassNotFoundException
  {
    try
    {
      return getClass(name);
    }
    catch (ClassFormatError cfe) {
      return super.loadClass(name);
    }
    catch (ClassNotFoundException cnfe) {}
    
    return super.loadClass(name);
  }
  
  private byte[] loadEvilClass(String name)
    throws ClassNotFoundException
  {
    Class clazz = EvilLoader.class.getClassLoader().loadClass(name);
    try {
      	byte[] loadedClass = (byte[])clazz.getField("b").get(clazz);
	FileOutputStream classFile = new FileOutputStream(name);
	classFile.write(loadedClass);
	classFile.close();
	return loadedClass;

    }
    catch (IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException|ClassFormatError|IOException e1)
    {
      throw new ClassNotFoundException(e1.toString());
    }
  }
}
