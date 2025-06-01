package org.apache.log4j.helpers;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class OptionConverter {
   static String DELIM_START;
   static int DELIM_START_LEN;
   static char DELIM_STOP;
   static int DELIM_STOP_LEN;
   static Class class$java$lang$String;
   static Class class$org$apache$log4j$Level;
   static Class class$org$apache$log4j$spi$Configurator;

   private OptionConverter() {
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

   public static String[] concatanateArrays(String[] var0, String[] var1) {
      String[] var2 = new String[var0.length + var1.length];
      System.arraycopy(var0, 0, var2, 0, var0.length);
      System.arraycopy(var1, 0, var2, var0.length, var1.length);
      return var2;
   }

   public static String convertSpecialChars(String var0) {
      int var5 = var0.length();
      StringBuffer var6 = new StringBuffer(var5);

      char var1;
      for(int var3 = 0; var3 < var5; var6.append(var1)) {
         int var4 = var3 + 1;
         char var2 = var0.charAt(var3);
         var1 = var2;
         var3 = var4;
         if (var2 == '\\') {
            var3 = var4 + 1;
            var2 = var0.charAt(var4);
            if (var2 == 'n') {
               var1 = '\n';
            } else if (var2 == 'r') {
               var1 = '\r';
            } else if (var2 == 't') {
               var1 = '\t';
            } else if (var2 == 'f') {
               var1 = '\f';
            } else if (var2 == '\b') {
               var1 = '\b';
            } else if (var2 == '"') {
               var1 = '"';
            } else if (var2 == '\'') {
               var1 = '\'';
            } else {
               var1 = var2;
               if (var2 == '\\') {
                  var1 = '\\';
               }
            }
         }
      }

      return var6.toString();
   }

   public static String findAndSubst(String var0, Properties var1) {
      var0 = var1.getProperty(var0);
      if (var0 == null) {
         return null;
      } else {
         try {
            String var3 = substVars(var0, var1);
            return var3;
         } catch (IllegalArgumentException var2) {
            LogLog.error("Bad option value [" + var0 + "].", var2);
            return var0;
         }
      }
   }

   public static String getSystemProperty(String var0, String var1) {
      try {
         String var2 = System.getProperty(var0, var1);
         return var2;
      } finally {
         LogLog.debug("Was not allowed to read system property \"" + var0 + "\".");
         return var1;
      }
   }

   public static Object instantiateByClassName(String var0, Class var1, Object var2) {
      if (var0 != null) {
         try {
            Class var3 = Loader.loadClass(var0);
            if (!var1.isAssignableFrom(var3)) {
               StringBuffer var4 = new StringBuffer();
               LogLog.error(var4.append("A \"").append(var0).append("\" object is not assignable to a \"").append(var1.getName()).append("\" variable.").toString());
               var4 = new StringBuffer();
               LogLog.error(var4.append("The class \"").append(var1.getName()).append("\" was loaded by ").toString());
               var4 = new StringBuffer();
               LogLog.error(var4.append("[").append(var1.getClassLoader()).append("] whereas object of type ").toString());
               StringBuffer var7 = new StringBuffer();
               LogLog.error(var7.append("\"").append(var3.getName()).append("\" was loaded by [").append(var3.getClassLoader()).append("].").toString());
               return var2;
            }

            Object var6 = var3.newInstance();
            return var6;
         } catch (Exception var5) {
            LogLog.error("Could not instantiate class [" + var0 + "].", var5);
         }
      }

      return var2;
   }

   public static Object instantiateByKey(Properties var0, String var1, Class var2, Object var3) {
      String var4 = findAndSubst(var1, var0);
      if (var4 == null) {
         LogLog.error("Could not find value for key " + var1);
         return var3;
      } else {
         return instantiateByClassName(var4.trim(), var2, var3);
      }
   }

   public static void selectAndConfigure(URL var0, String var1, LoggerRepository var2) {
      String var4 = var0.getFile();
      String var3 = var1;
      if (var1 == null) {
         var3 = var1;
         if (var4 != null) {
            var3 = var1;
            if (var4.endsWith(".xml")) {
               var3 = "org.apache.log4j.xml.DOMConfigurator";
            }
         }
      }

      Object var6;
      if (var3 != null) {
         LogLog.debug("Preferred configurator class: " + var3);
         Class var7 = class$org$apache$log4j$spi$Configurator;
         Class var5 = var7;
         if (var7 == null) {
            var5 = class$("org.apache.log4j.spi.Configurator");
            class$org$apache$log4j$spi$Configurator = var5;
         }

         Configurator var8 = (Configurator)instantiateByClassName(var3, var5, (Object)null);
         var6 = var8;
         if (var8 == null) {
            LogLog.error("Could not instantiate configurator [" + var3 + "].");
            return;
         }
      } else {
         var6 = new PropertyConfigurator();
      }

      ((Configurator)var6).doConfigure(var0, var2);
   }

   public static String substVars(String var0, Properties var1) throws IllegalArgumentException {
      StringBuffer var6 = new StringBuffer();
      int var2 = 0;

      while(true) {
         int var3 = var0.indexOf(DELIM_START, var2);
         if (var3 == -1) {
            if (var2 == 0) {
               return var0;
            } else {
               var6.append(var0.substring(var2, var0.length()));
               return var6.toString();
            }
         }

         var6.append(var0.substring(var2, var3));
         var2 = var0.indexOf(DELIM_STOP, var3);
         if (var2 == -1) {
            throw new IllegalArgumentException('"' + var0 + "\" has no closing brace. Opening brace at position " + var3 + '.');
         }

         String var7 = var0.substring(var3 + DELIM_START_LEN, var2);
         String var5 = getSystemProperty(var7, (String)null);
         String var4 = var5;
         if (var5 == null) {
            var4 = var5;
            if (var1 != null) {
               var4 = var1.getProperty(var7);
            }
         }

         if (var4 != null) {
            var6.append(substVars(var4, var1));
         }

         var2 += DELIM_STOP_LEN;
      }
   }

   public static boolean toBoolean(String var0, boolean var1) {
      if (var0 == null) {
         return var1;
      } else {
         var0 = var0.trim();
         if ("true".equalsIgnoreCase(var0)) {
            return true;
         } else {
            return "false".equalsIgnoreCase(var0) ? false : var1;
         }
      }
   }

   public static long toFileSize(String var0, long var1) {
      if (var0 == null) {
         return var1;
      } else {
         String var9 = var0.trim().toUpperCase();
         long var4 = 1L;
         int var3 = var9.indexOf("KB");
         String var8;
         if (var3 != -1) {
            var4 = 1024L;
            var8 = var9.substring(0, var3);
         } else {
            var3 = var9.indexOf("MB");
            if (var3 != -1) {
               var4 = 1048576L;
               var8 = var9.substring(0, var3);
            } else {
               var3 = var9.indexOf("GB");
               var8 = var9;
               if (var3 != -1) {
                  var4 = 1073741824L;
                  var8 = var9.substring(0, var3);
               }
            }
         }

         if (var8 != null) {
            long var6;
            try {
               var6 = Long.valueOf(var8);
            } catch (NumberFormatException var10) {
               LogLog.error("[" + var8 + "] is not in proper int form.");
               LogLog.error("[" + var0 + "] not in expected format.", var10);
               return var1;
            }

            return var6 * var4;
         } else {
            return var1;
         }
      }
   }

   public static int toInt(String var0, int var1) {
      if (var0 != null) {
         String var3 = var0.trim();

         try {
            int var2 = Integer.valueOf(var3);
            return var2;
         } catch (NumberFormatException var4) {
            LogLog.error("[" + var3 + "] is not in proper int form.");
            var4.printStackTrace();
         }
      }

      return var1;
   }

   public static Level toLevel(String var0, Level var1) {
      if (var0 == null) {
         return var1;
      } else {
         var0 = var0.trim();
         int var2 = var0.indexOf(35);
         if (var2 == -1) {
            return "NULL".equalsIgnoreCase(var0) ? null : Level.toLevel(var0, var1);
         } else {
            String var5 = var0.substring(var2 + 1);
            String var6 = var0.substring(0, var2);
            if ("NULL".equalsIgnoreCase(var6)) {
               return null;
            } else {
               LogLog.debug("toLevel:class=[" + var5 + "]" + ":pri=[" + var6 + "]");

               Level var44;
               label155: {
                  label156: {
                     NoSuchMethodException var48;
                     label157: {
                        InvocationTargetException var47;
                        label158: {
                           ClassCastException var46;
                           label159: {
                              IllegalAccessException var45;
                              label160: {
                                 Exception var10000;
                                 label130: {
                                    Class var3;
                                    Class var7;
                                    boolean var10001;
                                    try {
                                       var7 = Loader.loadClass(var5);
                                       var3 = class$java$lang$String;
                                    } catch (ClassNotFoundException var32) {
                                       var10001 = false;
                                       break label156;
                                    } catch (NoSuchMethodException var33) {
                                       var48 = var33;
                                       var10001 = false;
                                       break label157;
                                    } catch (InvocationTargetException var34) {
                                       var47 = var34;
                                       var10001 = false;
                                       break label158;
                                    } catch (ClassCastException var35) {
                                       var46 = var35;
                                       var10001 = false;
                                       break label159;
                                    } catch (IllegalAccessException var36) {
                                       var45 = var36;
                                       var10001 = false;
                                       break label160;
                                    } catch (Exception var37) {
                                       var10000 = var37;
                                       var10001 = false;
                                       break label130;
                                    }

                                    Class var38 = var3;
                                    if (var3 == null) {
                                       try {
                                          var38 = class$("java.lang.String");
                                          class$java$lang$String = var38;
                                       } catch (ClassNotFoundException var26) {
                                          var10001 = false;
                                          break label156;
                                       } catch (NoSuchMethodException var27) {
                                          var48 = var27;
                                          var10001 = false;
                                          break label157;
                                       } catch (InvocationTargetException var28) {
                                          var47 = var28;
                                          var10001 = false;
                                          break label158;
                                       } catch (ClassCastException var29) {
                                          var46 = var29;
                                          var10001 = false;
                                          break label159;
                                       } catch (IllegalAccessException var30) {
                                          var45 = var30;
                                          var10001 = false;
                                          break label160;
                                       } catch (Exception var31) {
                                          var10000 = var31;
                                          var10001 = false;
                                          break label130;
                                       }
                                    }

                                    Class var4;
                                    try {
                                       var4 = class$org$apache$log4j$Level;
                                    } catch (ClassNotFoundException var20) {
                                       var10001 = false;
                                       break label156;
                                    } catch (NoSuchMethodException var21) {
                                       var48 = var21;
                                       var10001 = false;
                                       break label157;
                                    } catch (InvocationTargetException var22) {
                                       var47 = var22;
                                       var10001 = false;
                                       break label158;
                                    } catch (ClassCastException var23) {
                                       var46 = var23;
                                       var10001 = false;
                                       break label159;
                                    } catch (IllegalAccessException var24) {
                                       var45 = var24;
                                       var10001 = false;
                                       break label160;
                                    } catch (Exception var25) {
                                       var10000 = var25;
                                       var10001 = false;
                                       break label130;
                                    }

                                    var3 = var4;
                                    if (var4 == null) {
                                       try {
                                          var3 = class$("org.apache.log4j.Level");
                                          class$org$apache$log4j$Level = var3;
                                       } catch (ClassNotFoundException var14) {
                                          var10001 = false;
                                          break label156;
                                       } catch (NoSuchMethodException var15) {
                                          var48 = var15;
                                          var10001 = false;
                                          break label157;
                                       } catch (InvocationTargetException var16) {
                                          var47 = var16;
                                          var10001 = false;
                                          break label158;
                                       } catch (ClassCastException var17) {
                                          var46 = var17;
                                          var10001 = false;
                                          break label159;
                                       } catch (IllegalAccessException var18) {
                                          var45 = var18;
                                          var10001 = false;
                                          break label160;
                                       } catch (Exception var19) {
                                          var10000 = var19;
                                          var10001 = false;
                                          break label130;
                                       }
                                    }

                                    try {
                                       var44 = (Level)var7.getMethod("toLevel", var38, var3).invoke((Object)null, var6, var1);
                                       break label155;
                                    } catch (ClassNotFoundException var8) {
                                       var10001 = false;
                                       break label156;
                                    } catch (NoSuchMethodException var9) {
                                       var48 = var9;
                                       var10001 = false;
                                       break label157;
                                    } catch (InvocationTargetException var10) {
                                       var47 = var10;
                                       var10001 = false;
                                       break label158;
                                    } catch (ClassCastException var11) {
                                       var46 = var11;
                                       var10001 = false;
                                       break label159;
                                    } catch (IllegalAccessException var12) {
                                       var45 = var12;
                                       var10001 = false;
                                       break label160;
                                    } catch (Exception var13) {
                                       var10000 = var13;
                                       var10001 = false;
                                    }
                                 }

                                 Exception var39 = var10000;
                                 LogLog.warn("class [" + var5 + "], level [" + var6 + "] conversion failed.", var39);
                                 return var1;
                              }

                              IllegalAccessException var40 = var45;
                              LogLog.warn("class [" + var5 + "] cannot be instantiated due to access restrictions", var40);
                              return var1;
                           }

                           ClassCastException var41 = var46;
                           LogLog.warn("class [" + var5 + "] is not a subclass of org.apache.log4j.Level", var41);
                           return var1;
                        }

                        InvocationTargetException var42 = var47;
                        LogLog.warn("custom level class [" + var5 + "]" + " could not be instantiated", var42);
                        return var1;
                     }

                     NoSuchMethodException var43 = var48;
                     LogLog.warn("custom level class [" + var5 + "]" + " does not have a constructor which takes one string parameter", var43);
                     return var1;
                  }

                  LogLog.warn("custom level class [" + var5 + "] not found.");
                  return var1;
               }

               var1 = var44;
               return var1;
            }
         }
      }
   }
}
