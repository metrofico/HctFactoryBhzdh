package androidx.fragment.app;

import androidx.collection.SimpleArrayMap;
import java.lang.reflect.InvocationTargetException;

public class FragmentFactory {
   private static final SimpleArrayMap sClassCacheMap = new SimpleArrayMap();

   static boolean isFragmentClass(ClassLoader var0, String var1) {
      try {
         boolean var2 = Fragment.class.isAssignableFrom(loadClass(var0, var1));
         return var2;
      } catch (ClassNotFoundException var3) {
         return false;
      }
   }

   private static Class loadClass(ClassLoader var0, String var1) throws ClassNotFoundException {
      SimpleArrayMap var4 = sClassCacheMap;
      SimpleArrayMap var3 = (SimpleArrayMap)var4.get(var0);
      SimpleArrayMap var2 = var3;
      if (var3 == null) {
         var2 = new SimpleArrayMap();
         var4.put(var0, var2);
      }

      Class var6 = (Class)var2.get(var1);
      Class var5 = var6;
      if (var6 == null) {
         var5 = Class.forName(var1, false, var0);
         var2.put(var1, var5);
      }

      return var5;
   }

   public static Class loadFragmentClass(ClassLoader var0, String var1) {
      try {
         Class var4 = loadClass(var0, var1);
         return var4;
      } catch (ClassNotFoundException var2) {
         throw new Fragment.InstantiationException("Unable to instantiate fragment " + var1 + ": make sure class name exists", var2);
      } catch (ClassCastException var3) {
         throw new Fragment.InstantiationException("Unable to instantiate fragment " + var1 + ": make sure class is a valid subclass of Fragment", var3);
      }
   }

   public Fragment instantiate(ClassLoader var1, String var2) {
      try {
         Fragment var7 = (Fragment)loadFragmentClass(var1, var2).getConstructor().newInstance();
         return var7;
      } catch (InstantiationException var3) {
         throw new Fragment.InstantiationException("Unable to instantiate fragment " + var2 + ": make sure class name exists, is public, and has an empty constructor that is public", var3);
      } catch (IllegalAccessException var4) {
         throw new Fragment.InstantiationException("Unable to instantiate fragment " + var2 + ": make sure class name exists, is public, and has an empty constructor that is public", var4);
      } catch (NoSuchMethodException var5) {
         throw new Fragment.InstantiationException("Unable to instantiate fragment " + var2 + ": could not find Fragment constructor", var5);
      } catch (InvocationTargetException var6) {
         throw new Fragment.InstantiationException("Unable to instantiate fragment " + var2 + ": calling Fragment constructor caused an exception", var6);
      }
   }
}
