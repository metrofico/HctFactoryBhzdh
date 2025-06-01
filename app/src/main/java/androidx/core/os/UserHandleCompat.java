package androidx.core.os;

import android.os.UserHandle;
import android.os.Build.VERSION;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserHandleCompat {
   private static Method sGetUserIdMethod;
   private static Constructor sUserHandleConstructor;

   private UserHandleCompat() {
   }

   private static Method getGetUserIdMethod() throws NoSuchMethodException {
      if (sGetUserIdMethod == null) {
         Method var0 = UserHandle.class.getDeclaredMethod("getUserId", Integer.TYPE);
         sGetUserIdMethod = var0;
         var0.setAccessible(true);
      }

      return sGetUserIdMethod;
   }

   private static Constructor getUserHandleConstructor() throws NoSuchMethodException {
      if (sUserHandleConstructor == null) {
         Constructor var0 = UserHandle.class.getDeclaredConstructor(Integer.TYPE);
         sUserHandleConstructor = var0;
         var0.setAccessible(true);
      }

      return sUserHandleConstructor;
   }

   public static UserHandle getUserHandleForUid(int var0) {
      if (VERSION.SDK_INT >= 24) {
         return UserHandle.getUserHandleForUid(var0);
      } else {
         try {
            Integer var9 = (Integer)getGetUserIdMethod().invoke((Object)null, var0);
            UserHandle var10 = (UserHandle)getUserHandleConstructor().newInstance(var9);
            return var10;
         } catch (NoSuchMethodException var3) {
            NoSuchMethodError var8 = new NoSuchMethodError();
            var8.initCause(var3);
            throw var8;
         } catch (IllegalAccessException var4) {
            IllegalAccessError var7 = new IllegalAccessError();
            var7.initCause(var4);
            throw var7;
         } catch (InstantiationException var5) {
            InstantiationError var1 = new InstantiationError();
            var1.initCause(var5);
            throw var1;
         } catch (InvocationTargetException var6) {
            throw new RuntimeException(var6);
         }
      }
   }

   private static class Api24Impl {
      static UserHandle getUserHandleForUid(int var0) {
         return UserHandle.getUserHandleForUid(var0);
      }
   }
}
