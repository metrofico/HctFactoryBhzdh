package org.apache.log4j.helpers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

public class Loader {
   static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
   static Class class$java$lang$Thread;
   static Class class$org$apache$log4j$helpers$Loader;
   private static boolean ignoreTCL;
   private static boolean java1;

   static {
      String var1 = OptionConverter.getSystemProperty("java.version", (String)null);
      if (var1 != null) {
         int var0 = var1.indexOf(46);
         if (var0 != -1 && var1.charAt(var0 + 1) != '1') {
            java1 = false;
         }
      }

      var1 = OptionConverter.getSystemProperty("log4j.ignoreTCL", (String)null);
      if (var1 != null) {
         ignoreTCL = OptionConverter.toBoolean(var1, true);
      }

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

   public static URL getResource(String var0) {
      label404: {
         URL var45;
         label403: {
            Throwable var10000;
            label406: {
               ClassLoader var1;
               StringBuffer var2;
               boolean var10001;
               label401: {
                  try {
                     if (java1) {
                        break label401;
                     }

                     var1 = getTCL();
                  } catch (Throwable var44) {
                     var10000 = var44;
                     var10001 = false;
                     break label406;
                  }

                  if (var1 != null) {
                     try {
                        var2 = new StringBuffer();
                        LogLog.debug(var2.append("Trying to find [").append(var0).append("] using context classloader ").append(var1).append(".").toString());
                        var45 = var1.getResource(var0);
                     } catch (Throwable var43) {
                        var10000 = var43;
                        var10001 = false;
                        break label406;
                     }

                     if (var45 != null) {
                        return var45;
                     }
                  }
               }

               Class var47;
               try {
                  var47 = class$org$apache$log4j$helpers$Loader;
               } catch (Throwable var42) {
                  var10000 = var42;
                  var10001 = false;
                  break label406;
               }

               Class var46 = var47;
               if (var47 == null) {
                  try {
                     var46 = class$("org.apache.log4j.helpers.Loader");
                     class$org$apache$log4j$helpers$Loader = var46;
                  } catch (Throwable var41) {
                     var10000 = var41;
                     var10001 = false;
                     break label406;
                  }
               }

               try {
                  var1 = var46.getClassLoader();
               } catch (Throwable var40) {
                  var10000 = var40;
                  var10001 = false;
                  break label406;
               }

               if (var1 == null) {
                  break label404;
               }

               label381:
               try {
                  var2 = new StringBuffer();
                  LogLog.debug(var2.append("Trying to find [").append(var0).append("] using ").append(var1).append(" class loader.").toString());
                  var45 = var1.getResource(var0);
                  break label403;
               } catch (Throwable var39) {
                  var10000 = var39;
                  var10001 = false;
                  break label381;
               }
            }

            Throwable var48 = var10000;
            LogLog.warn("Caught Exception while in Loader.getResource. This may be innocuous.", var48);
            break label404;
         }

         if (var45 != null) {
            return var45;
         }
      }

      LogLog.debug("Trying to find [" + var0 + "] using ClassLoader.getSystemResource().");
      return ClassLoader.getSystemResource(var0);
   }

   public static URL getResource(String var0, Class var1) {
      return getResource(var0);
   }

   private static ClassLoader getTCL() throws IllegalAccessException, InvocationTargetException {
      Object var1 = null;

      ClassLoader var6;
      Method var7;
      label40: {
         label35: {
            boolean var10001;
            Class var2;
            try {
               var2 = class$java$lang$Thread;
            } catch (NoSuchMethodException var5) {
               var10001 = false;
               break label35;
            }

            Class var0 = var2;
            if (var2 == null) {
               try {
                  var0 = class$("java.lang.Thread");
                  class$java$lang$Thread = var0;
               } catch (NoSuchMethodException var4) {
                  var10001 = false;
                  break label35;
               }
            }

            try {
               var7 = var0.getMethod("getContextClassLoader", (Class[])null);
               break label40;
            } catch (NoSuchMethodException var3) {
               var10001 = false;
            }
         }

         var6 = (ClassLoader)var1;
         return var6;
      }

      var6 = (ClassLoader)var7.invoke(Thread.currentThread(), (Object[])null);
      return var6;
   }

   public static boolean isJava1() {
      return java1;
   }

   public static Class loadClass(String var0) throws ClassNotFoundException {
      if (!java1 && !ignoreTCL) {
         try {
            Class var1 = getTCL().loadClass(var0);
            return var1;
         } finally {
            return Class.forName(var0);
         }
      } else {
         return Class.forName(var0);
      }
   }
}
