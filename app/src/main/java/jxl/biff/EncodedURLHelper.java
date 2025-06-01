package jxl.biff;

import jxl.WorkbookSettings;
import jxl.common.Logger;

public class EncodedURLHelper {
   private static byte endOfSubdirectory = 3;
   private static Logger logger = Logger.getLogger(EncodedURLHelper.class);
   private static byte msDosDriveLetter = 1;
   private static byte parentDirectory = 4;
   private static byte sameDrive = 2;
   private static byte unencodedUrl = 5;

   public static byte[] getEncodedURL(String var0, WorkbookSettings var1) {
      return var0.startsWith("http:") ? getURL(var0, var1) : getFile(var0, var1);
   }

   private static byte[] getFile(String var0, WorkbookSettings var1) {
      ByteArray var6 = new ByteArray();
      int var2;
      if (var0.charAt(1) == ':') {
         var6.add(msDosDriveLetter);
         var6.add((byte)var0.charAt(0));
         var2 = 2;
      } else {
         if (var0.charAt(0) == '\\' || var0.charAt(0) == '/') {
            var6.add(sameDrive);
         }

         var2 = 0;
      }

      while(true) {
         if (var0.charAt(var2) != '\\') {
            int var3 = var2;
            if (var0.charAt(var2) != '/') {
               for(; var3 < var0.length(); var3 = var2) {
                  var2 = var0.indexOf(47, var3);
                  int var4 = var0.indexOf(92, var3);
                  if (var2 != -1 && var4 != -1) {
                     var2 = Math.min(var2, var4);
                  } else if (var2 != -1 && var4 != -1) {
                     var2 = 0;
                  } else {
                     var2 = Math.max(var2, var4);
                  }

                  String var5;
                  if (var2 == -1) {
                     var5 = var0.substring(var3);
                     var2 = var0.length();
                  } else {
                     var5 = var0.substring(var3, var2);
                     ++var2;
                  }

                  if (!var5.equals(".")) {
                     if (var5.equals("..")) {
                        var6.add(parentDirectory);
                     } else {
                        var6.add(StringHelper.getBytes(var5, var1));
                     }
                  }

                  if (var2 < var0.length()) {
                     var6.add(endOfSubdirectory);
                  }
               }

               return var6.getBytes();
            }
         }

         ++var2;
      }
   }

   private static byte[] getURL(String var0, WorkbookSettings var1) {
      ByteArray var2 = new ByteArray();
      var2.add(unencodedUrl);
      var2.add((byte)var0.length());
      var2.add(StringHelper.getBytes(var0, var1));
      return var2.getBytes();
   }
}
