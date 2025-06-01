package org.apache.log4j.config;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.OptionHandler;

public class PropertySetter {
   static Class class$java$lang$String;
   static Class class$org$apache$log4j$Priority;
   protected Object obj;
   protected PropertyDescriptor[] props;

   public PropertySetter(Object var1) {
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

   public static void setProperties(Object var0, Properties var1, String var2) {
      (new PropertySetter(var0)).setProperties(var1, var2);
   }

   public void activate() {
      Object var1 = this.obj;
      if (var1 instanceof OptionHandler) {
         ((OptionHandler)var1).activateOptions();
      }

   }

   protected Object convertArg(String var1, Class var2) {
      if (var1 == null) {
         return null;
      } else {
         String var5 = var1.trim();
         Class var4 = class$java$lang$String;
         Class var3 = var4;
         if (var4 == null) {
            var3 = class$("java.lang.String");
            class$java$lang$String = var3;
         }

         if (var3.isAssignableFrom(var2)) {
            return var1;
         } else if (Integer.TYPE.isAssignableFrom(var2)) {
            return new Integer(var5);
         } else if (Long.TYPE.isAssignableFrom(var2)) {
            return new Long(var5);
         } else {
            if (Boolean.TYPE.isAssignableFrom(var2)) {
               if ("true".equalsIgnoreCase(var5)) {
                  return Boolean.TRUE;
               }

               if ("false".equalsIgnoreCase(var5)) {
                  return Boolean.FALSE;
               }
            } else {
               var3 = class$org$apache$log4j$Priority;
               Class var6 = var3;
               if (var3 == null) {
                  var6 = class$("org.apache.log4j.Priority");
                  class$org$apache$log4j$Priority = var6;
               }

               if (var6.isAssignableFrom(var2)) {
                  return OptionConverter.toLevel(var5, Level.DEBUG);
               }
            }

            return null;
         }
      }
   }

   protected PropertyDescriptor getPropertyDescriptor(String var1) {
      if (this.props == null) {
         this.introspect();
      }

      int var2 = 0;

      while(true) {
         PropertyDescriptor[] var3 = this.props;
         if (var2 >= var3.length) {
            return null;
         }

         if (var1.equals(var3[var2].getName())) {
            return this.props[var2];
         }

         ++var2;
      }
   }

   protected void introspect() {
      try {
         this.props = Introspector.getBeanInfo(this.obj.getClass()).getPropertyDescriptors();
      } catch (IntrospectionException var2) {
         LogLog.error("Failed to introspect " + this.obj + ": " + var2.getMessage());
         this.props = new PropertyDescriptor[0];
      }

   }

   public void setProperties(Properties var1, String var2) {
      int var3 = var2.length();
      Enumeration var5 = var1.propertyNames();

      while(true) {
         String var4;
         String var6;
         do {
            do {
               do {
                  if (!var5.hasMoreElements()) {
                     this.activate();
                     return;
                  }

                  var6 = (String)var5.nextElement();
               } while(!var6.startsWith(var2));
            } while(var6.indexOf(46, var3 + 1) > 0);

            var4 = OptionConverter.findAndSubst(var6, var1);
            var6 = var6.substring(var3);
         } while("layout".equals(var6) && this.obj instanceof Appender);

         this.setProperty(var6, var4);
      }
   }

   public void setProperty(PropertyDescriptor var1, String var2, String var3) throws PropertySetterException {
      Method var4 = var1.getWriteMethod();
      if (var4 != null) {
         Class[] var9 = var4.getParameterTypes();
         if (var9.length == 1) {
            Object var10;
            try {
               var10 = this.convertArg(var3, var9[0]);
            } catch (Throwable var8) {
               throw new PropertySetterException("Conversion to type [" + var9[0] + "] failed. Reason: " + var8);
            }

            if (var10 != null) {
               LogLog.debug("Setting property [" + var2 + "] to [" + var10 + "].");

               try {
                  var4.invoke(this.obj, var10);
               } catch (Exception var7) {
                  throw new PropertySetterException(var7);
               }
            } else {
               throw new PropertySetterException("Conversion to type [" + var9[0] + "] failed.");
            }
         } else {
            throw new PropertySetterException("#params for setter != 1");
         }
      } else {
         throw new PropertySetterException("No setter for property [" + var2 + "].");
      }
   }

   public void setProperty(String var1, String var2) {
      if (var2 != null) {
         var1 = Introspector.decapitalize(var1);
         PropertyDescriptor var3 = this.getPropertyDescriptor(var1);
         if (var3 == null) {
            LogLog.warn("No such property [" + var1 + "] in " + this.obj.getClass().getName() + ".");
         } else {
            try {
               this.setProperty(var3, var1, var2);
            } catch (PropertySetterException var4) {
               LogLog.warn("Failed to set property [" + var1 + "] to value \"" + var2 + "\". ", var4.rootCause);
            }
         }

      }
   }
}
