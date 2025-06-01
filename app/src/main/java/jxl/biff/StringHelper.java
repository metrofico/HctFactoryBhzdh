package jxl.biff;

import java.io.UnsupportedEncodingException;
import jxl.WorkbookSettings;
import jxl.common.Logger;

public final class StringHelper {
   public static String UNICODE_ENCODING = "UnicodeLittle";
   private static Logger logger = Logger.getLogger(StringHelper.class);

   private StringHelper() {
   }

   public static void getBytes(String var0, byte[] var1, int var2) {
      byte[] var3 = getBytes(var0);
      System.arraycopy(var3, 0, var1, var2, var3.length);
   }

   public static byte[] getBytes(String var0) {
      return var0.getBytes();
   }

   public static byte[] getBytes(String var0, WorkbookSettings var1) {
      try {
         byte[] var3 = var0.getBytes(var1.getEncoding());
         return var3;
      } catch (UnsupportedEncodingException var2) {
         return null;
      }
   }

   public static String getString(byte[] var0, int var1, int var2, WorkbookSettings var3) {
      if (var1 == 0) {
         return "";
      } else {
         try {
            String var5 = new String(var0, var2, var1, var3.getEncoding());
            return var5;
         } catch (UnsupportedEncodingException var4) {
            logger.warn(var4.toString());
            return "";
         }
      }
   }

   public static void getUnicodeBytes(String var0, byte[] var1, int var2) {
      byte[] var3 = getUnicodeBytes(var0);
      System.arraycopy(var3, 0, var1, var2, var3.length);
   }

   public static byte[] getUnicodeBytes(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static String getUnicodeString(byte[] var0, int var1, int var2) {
      var1 *= 2;

      try {
         byte[] var3 = new byte[var1];
         System.arraycopy(var0, var2, var3, 0, var1);
         String var5 = new String(var3, UNICODE_ENCODING);
         return var5;
      } catch (UnsupportedEncodingException var4) {
         return "";
      }
   }

   public static final String replace(String var0, String var1, String var2) {
      for(int var3 = var0.indexOf(var1); var3 != -1; var3 = var0.indexOf(var1, var3 + var2.length())) {
         StringBuffer var4 = new StringBuffer(var0.substring(0, var3));
         var4.append(var2);
         var4.append(var0.substring(var1.length() + var3));
         var0 = var4.toString();
      }

      return var0;
   }
}
