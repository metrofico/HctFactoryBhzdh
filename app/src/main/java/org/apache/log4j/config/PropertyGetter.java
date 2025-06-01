package org.apache.log4j.config;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import org.apache.log4j.helpers.LogLog;

public class PropertyGetter {
   protected static final Object[] NULL_ARG = new Object[0];
   static Class class$java$lang$String;
   static Class class$org$apache$log4j$Priority;
   protected Object obj;
   protected PropertyDescriptor[] props;

   public PropertyGetter(Object var1) throws IntrospectionException {
      this.props = Introspector.getBeanInfo(var1.getClass()).getPropertyDescriptors();
      this.obj = var1;
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public static void getProperties(Object var0, PropertyCallback var1, String var2) {
      try {
         PropertyGetter var3 = new PropertyGetter(var0);
         var3.getProperties(var1, var2);
      } catch (IntrospectionException var4) {
         LogLog.error("Failed to introspect object " + var0, var4);
      }

   }

   public void getProperties(PropertyCallback var1, String var2) {
      int var3 = 0;

      while(true) {
         PropertyDescriptor[] var4 = this.props;
         if (var3 >= var4.length) {
            return;
         }

         Method var5 = var4[var3].getReadMethod();
         if (var5 != null && this.isHandledType(var5.getReturnType())) {
            label46: {
               String var8 = this.props[var3].getName();

               label44: {
                  boolean var10001;
                  Object var9;
                  try {
                     var9 = var5.invoke(this.obj, NULL_ARG);
                  } catch (Exception var7) {
                     var10001 = false;
                     break label44;
                  }

                  if (var9 == null) {
                     break label46;
                  }

                  try {
                     var1.foundProperty(this.obj, var2, var8, var9);
                     break label46;
                  } catch (Exception var6) {
                     var10001 = false;
                  }
               }

               LogLog.warn("Failed to get value of property " + var8);
            }
         }

         ++var3;
      }
   }

   protected boolean isHandledType(Class var1) {
      Class var4 = class$java$lang$String;
      Class var3 = var4;
      if (var4 == null) {
         var3 = class$("java.lang.String");
         class$java$lang$String = var3;
      }

      boolean var2;
      if (!var3.isAssignableFrom(var1) && !Integer.TYPE.isAssignableFrom(var1) && !Long.TYPE.isAssignableFrom(var1) && !Boolean.TYPE.isAssignableFrom(var1)) {
         var4 = class$org$apache$log4j$Priority;
         var3 = var4;
         if (var4 == null) {
            var3 = class$("org.apache.log4j.Priority");
            class$org$apache$log4j$Priority = var3;
         }

         if (!var3.isAssignableFrom(var1)) {
            var2 = false;
            return var2;
         }
      }

      var2 = true;
      return var2;
   }

   public interface PropertyCallback {
      void foundProperty(Object var1, String var2, String var3, Object var4);
   }
}
