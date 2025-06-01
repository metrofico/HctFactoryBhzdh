package org.apache.log4j.helpers;

public class Transform {
   private static final String CDATA_EMBEDED_END = "]]>]]&gt;<![CDATA[";
   private static final String CDATA_END = "]]>";
   private static final int CDATA_END_LEN = 3;
   private static final String CDATA_PSEUDO_END = "]]&gt;";
   private static final String CDATA_START = "<![CDATA[";

   public static void appendEscapingCDATA(StringBuffer var0, String var1) {
      if (var1 == null) {
         var0.append("");
      } else {
         int var2 = var1.indexOf("]]>");
         if (var2 < 0) {
            var0.append(var1);
         } else {
            int var3;
            for(var3 = 0; var2 > -1; var2 = var1.indexOf("]]>", var3)) {
               var0.append(var1.substring(var3, var2));
               var0.append("]]>]]&gt;<![CDATA[");
               var3 = CDATA_END_LEN + var2;
               if (var3 >= var1.length()) {
                  return;
               }
            }

            var0.append(var1.substring(var3));
         }
      }
   }

   public static String escapeTags(String var0) {
      if (var0 != null && var0.length() != 0) {
         StringBuffer var4 = new StringBuffer(var0.length() + 6);
         int var3 = var0.length();

         for(int var2 = 0; var2 < var3; ++var2) {
            char var1 = var0.charAt(var2);
            if (var1 == '<') {
               var4.append("&lt;");
            } else if (var1 == '>') {
               var4.append("&gt;");
            } else {
               var4.append(var1);
            }
         }

         return var4.toString();
      } else {
         return var0;
      }
   }
}
