package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public final class a {
   public static String a(ArrayList var0) {
      StringBuffer var5 = new StringBuffer();
      int var1 = 0;

      while(true) {
         int var2 = var0.size();
         String var3 = "map";
         if (var1 >= var2) {
            Collections.reverse(var0);

            for(var1 = 0; var1 < var0.size(); ++var1) {
               var3 = (String)var0.get(var1);
               if (var3.equals("list")) {
                  var2 = var1 - 1;
                  var0.set(var2, "<" + (String)var0.get(var2));
                  var0.set(0, (String)var0.get(0) + ">");
               } else if (var3.equals("map")) {
                  var2 = var1 - 1;
                  var0.set(var2, "<" + (String)var0.get(var2) + ",");
                  var0.set(0, (String)var0.get(0) + ">");
               } else if (var3.equals("Array")) {
                  var2 = var1 - 1;
                  var0.set(var2, "<" + (String)var0.get(var2));
                  var0.set(0, (String)var0.get(0) + ">");
               }
            }

            Collections.reverse(var0);
            Iterator var6 = var0.iterator();

            while(var6.hasNext()) {
               var5.append((String)var6.next());
            }

            return var5.toString();
         }

         String var4 = (String)var0.get(var1);
         if (!var4.equals("java.lang.Integer") && !var4.equals("int")) {
            if (!var4.equals("java.lang.Boolean") && !var4.equals("boolean")) {
               if (!var4.equals("java.lang.Byte") && !var4.equals("byte")) {
                  if (!var4.equals("java.lang.Double") && !var4.equals("double")) {
                     if (!var4.equals("java.lang.Float") && !var4.equals("float")) {
                        if (!var4.equals("java.lang.Long") && !var4.equals("long")) {
                           if (!var4.equals("java.lang.Short") && !var4.equals("short")) {
                              if (var4.equals("java.lang.Character")) {
                                 throw new IllegalArgumentException("can not support java.lang.Character");
                              }

                              if (var4.equals("java.lang.String")) {
                                 var3 = "string";
                              } else if (var4.equals("java.util.List")) {
                                 var3 = "list";
                              } else if (!var4.equals("java.util.Map")) {
                                 var3 = var4;
                              }
                           } else {
                              var3 = "short";
                           }
                        } else {
                           var3 = "int64";
                        }
                     } else {
                        var3 = "float";
                     }
                  } else {
                     var3 = "double";
                  }
               } else {
                  var3 = "char";
               }
            } else {
               var3 = "bool";
            }
         } else {
            var3 = "int32";
         }

         var0.set(var1, var3);
         ++var1;
      }
   }
}
