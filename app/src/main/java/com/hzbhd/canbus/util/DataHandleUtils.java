package com.hzbhd.canbus.util;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class DataHandleUtils {
   public static String Byte2Unicode(byte[] var0, boolean var1) {
      StringBuffer var5 = new StringBuffer("");

      int var4;
      byte var6;
      for(int var2 = 0; var2 < var0.length; var5.append((char)((var6 & 255) + (var4 << 8 & '\uff00')))) {
         if (var1) {
            int var3 = var2 + 1;
            var4 = var0[var2];
            var2 = var3 + 1;
            var6 = var0[var3];
         } else {
            var4 = var2 + 1;
            var6 = var0[var2];
            var2 = var4 + 1;
            var4 = var0[var4];
         }
      }

      return var5.toString();
   }

   public static String bcd2Str(byte[] var0) {
      char[] var4 = new char[var0.length * 2];

      for(int var1 = 0; var1 < var0.length; ++var1) {
         char var2 = (char)var0[var1];
         char var3 = (char)((var2 & 240) >> 4 & 15);
         if (var3 >= 0 && var3 <= '\t') {
            var4[var1 * 2] = (char)(var3 + 48);
         } else if (var3 == '\n') {
            var4[var1 * 2] = '*';
         } else if (var3 == 11) {
            var4[var1 * 2] = '#';
         } else if (var3 == 15) {
            break;
         }

         var2 = (char)(var2 & 15);
         if (var2 >= 0 && var2 <= '\t') {
            var4[var1 * 2 + 1] = (char)(var2 + 48);
         } else if (var2 == '\n') {
            var4[var1 * 2 + 1] = '*';
         } else if (var2 == 11) {
            var4[var1 * 2 + 1] = '#';
         } else if (var2 == 15) {
            break;
         }
      }

      return new String(var4);
   }

   public static int blockBit(int var0, int var1) {
      if (var1 == 0) {
         return (getIntFromByteWithBit(var0, 1, 7) & 255) << 1;
      } else if (var1 == 7) {
         return getIntFromByteWithBit(var0, 0, 7);
      } else {
         int var2 = var1 + 1;
         int var3 = getIntFromByteWithBit(var0, var2, 7 - var1);
         return getIntFromByteWithBit(var0, 0, var1) & 255 & 255 ^ (var3 & 255) << var2;
      }
   }

   public static byte[] byteMerger(byte[] var0, byte[] var1) {
      byte[] var2 = new byte[var0.length + var1.length];
      System.arraycopy(var0, 0, var2, 0, var0.length);
      System.arraycopy(var1, 0, var2, var0.length, var1.length);
      return var2;
   }

   public static byte[] byteMerger(byte[] var0, byte[] var1, byte var2) {
      return byteMerger(byteMerger(var0, var1), new byte[]{var2});
   }

   public static String byteToHexString(byte var0) {
      return Integer.toHexString(var0 & 255 | -256).substring(6);
   }

   public static String bytes2HexString(byte[] var0, int var1) {
      String var3 = "";

      for(int var2 = 0; var2 < var1; ++var2) {
         String var5 = Integer.toHexString(var0[var2] & 255);
         String var4 = var5;
         if (var5.length() == 1) {
            var4 = '0' + var5;
         }

         var3 = var3 + var4.toUpperCase() + " ";
      }

      return var3;
   }

   public static String bytes2HexString(int[] var0, int var1) {
      String var3 = "";

      for(int var2 = 0; var2 < var1; ++var2) {
         String var5 = Integer.toHexString(var0[var2] & 255);
         String var4 = var5;
         if (var5.length() == 1) {
            var4 = '0' + var5;
         }

         var3 = var3 + var4.toUpperCase() + " ";
      }

      return var3;
   }

   public static byte[] compensateZero(byte[] var0, int var1) {
      byte[] var3 = new byte[var1];
      System.arraycopy(var0, 0, var3, 0, var0.length);

      for(int var2 = var0.length; var2 < var1; ++var2) {
         var3[var2] = 0;
      }

      return var3;
   }

   public static byte[] exceptBOMHead(byte[] var0) {
      while(true) {
         if (var0.length > 2) {
            byte var1 = var0[0];
            if (var1 == -2 && var0[1] == -1 || var1 == -1 && var0[1] == -2) {
               var0 = Arrays.copyOfRange(var0, 2, var0.length);
               continue;
            }
         }

         return var0;
      }
   }

   public static boolean getBoolBit(int var0, int var1) {
      switch (var0) {
         case 0:
            return getBoolBit0(var1);
         case 1:
            return getBoolBit1(var1);
         case 2:
            return getBoolBit2(var1);
         case 3:
            return getBoolBit3(var1);
         case 4:
            return getBoolBit4(var1);
         case 5:
            return getBoolBit5(var1);
         case 6:
            return getBoolBit6(var1);
         case 7:
            return getBoolBit7(var1);
         default:
            return false;
      }
   }

   public static boolean getBoolBit0(int var0) {
      boolean var1 = true;
      if ((var0 & 1) == 0) {
         var1 = false;
      }

      return var1;
   }

   public static boolean getBoolBit1(int var0) {
      boolean var1;
      if ((var0 & 2) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean getBoolBit2(int var0) {
      boolean var1;
      if ((var0 & 4) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean getBoolBit3(int var0) {
      boolean var1;
      if ((var0 & 8) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean getBoolBit4(int var0) {
      boolean var1;
      if ((var0 & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean getBoolBit5(int var0) {
      boolean var1;
      if ((var0 & 32) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean getBoolBit6(int var0) {
      boolean var1;
      if ((var0 & 64) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean getBoolBit7(int var0) {
      boolean var1;
      if ((var0 & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static byte[] getBytesEndWithAssign(byte[] var0, int var1, byte var2) {
      int var4 = var0.length;
      int var7 = var1 + 2;
      byte[] var8 = new byte[var4 - var7];
      byte var6 = 0;
      var4 = 0;

      int var5;
      for(var5 = 0; var4 < var0.length - var7; var5 = var1) {
         byte var3 = var0[var4 + var7];
         var8[var4] = var3;
         var1 = var5;
         if (var3 != var2) {
            var1 = var5 + 1;
         }

         ++var4;
      }

      if (var5 == 0) {
         var0 = new byte[1];
         var1 = var6;
      } else {
         var0 = new byte[var5];
         var1 = var6;
      }

      while(var1 < var0.length) {
         var0[var1] = var8[var1];
         ++var1;
      }

      return var0;
   }

   public static int getDecimalFrom8Bit(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      return Integer.parseInt(var0 + "" + var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7, 2);
   }

   public static boolean getIntByteWithBit(int var0, int var1) {
      boolean var8 = false;
      boolean var5 = false;
      boolean var9 = false;
      boolean var7 = false;
      boolean var4 = false;
      boolean var6 = false;
      boolean var3 = false;
      boolean var2 = false;
      if (var1 == 0) {
         if ((var0 & 1) != 0) {
            var2 = true;
         }

         return var2;
      } else if (1 == var1) {
         var2 = var8;
         if ((var0 & 2) != 0) {
            var2 = true;
         }

         return var2;
      } else if (2 == var1) {
         var2 = var5;
         if ((var0 & 4) != 0) {
            var2 = true;
         }

         return var2;
      } else if (3 == var1) {
         var2 = var9;
         if ((var0 & 8) != 0) {
            var2 = true;
         }

         return var2;
      } else if (4 == var1) {
         var2 = var7;
         if ((var0 & 16) != 0) {
            var2 = true;
         }

         return var2;
      } else if (5 == var1) {
         var2 = var4;
         if ((var0 & 32) != 0) {
            var2 = true;
         }

         return var2;
      } else if (6 == var1) {
         var2 = var6;
         if ((var0 & 64) != 0) {
            var2 = true;
         }

         return var2;
      } else {
         var2 = var3;
         if (7 == var1) {
            var2 = var3;
            if ((var0 & 128) != 0) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   public static int getIntFromByteWithBit(int var0, int var1, int var2) {
      return (var0 & 255) >> var1 & (1 << var2) - 1;
   }

   public static int getLsb(int var0) {
      return (var0 & '\uffff') >> 0 & 255;
   }

   public static int getMsb(int var0) {
      return (var0 & '\uffff') >> 8 & 255;
   }

   public static int getMsbLsbResult(int var0, int var1) {
      return (var0 & 255) << 8 | var1 & 255;
   }

   public static int getMsbLsbResult_4bit(int var0, int var1) {
      return (var0 & 255) << 4 | var1 & 255;
   }

   public static float getRound(float var0, int var1) {
      double var2 = Math.pow(10.0, (double)var1);
      return (float)((double)Math.round((double)var0 * var2) / var2);
   }

   public static boolean isBackground(Context var0, String var1) {
      if (var0 != null && !TextUtils.isEmpty(var1)) {
         List var2 = ((ActivityManager)var0.getSystemService("activity")).getRunningTasks(1);
         return var2 == null || var2.size() <= 0 || !var1.equals(((ActivityManager.RunningTaskInfo)var2.get(0)).topActivity.getClassName());
      } else {
         return false;
      }
   }

   public static void knob(Context var0, int var1, int var2) {
      for(int var3 = 0; var3 < var2; ++var3) {
         RealKeyUtil.realKeyClick(var0, var1);
      }

   }

   public static byte[] limitDataLength(byte[] var0, int var1) {
      return Arrays.copyOf(var0, var1);
   }

   public static byte[] makeBytesFixedLength(byte[] var0, int var1) {
      byte[] var3 = new byte[var1];

      for(int var2 = 0; var2 < var1; ++var2) {
         if (var2 >= var0.length) {
            var3[var2] = 0;
         } else {
            var3[var2] = var0[var2];
         }
      }

      return var3;
   }

   public static byte[] makeBytesFixedLength(byte[] var0, int var1, int var2) {
      byte[] var4 = new byte[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         if (var3 >= var0.length) {
            var4[var3] = (byte)var2;
         } else {
            var4[var3] = var0[var3];
         }
      }

      return var4;
   }

   public static String makeMediaInfoCenteredInBytes(int var0, String var1) {
      String var5 = "";
      if (var1 != null && var1.length() <= var0) {
         int var4 = var0 - var1.length();
         int var3 = var4 / 2;
         byte var2 = 0;

         for(var0 = 0; var0 < var3; ++var0) {
            var5 = var5 + " ";
         }

         var1 = var5 + var1;

         for(var0 = var2; var0 < var4 - var3; ++var0) {
            var1 = var1 + " ";
         }

         return var1;
      } else {
         return "";
      }
   }

   public static byte[] phoneNum2UnicodeBig(byte[] var0) {
      byte[] var3 = new byte[var0.length * 2];

      for(int var1 = 0; var1 < var0.length; ++var1) {
         int var2 = var1 * 2;
         var3[var2] = 0;
         var3[var2 + 1] = (byte)(var0[var1] & 255);
      }

      return var3;
   }

   public static int rangeNumber(int var0, int var1) {
      return rangeNumber(var0, 0, var1);
   }

   public static int rangeNumber(int var0, int var1, int var2) {
      int var3 = var0;
      if (var0 > var2) {
         var3 = var2;
      }

      if (var3 >= var1) {
         var1 = var3;
      }

      return var1;
   }

   public static int[] restructureArray(int[] var0, int[] var1) {
      int var3 = var0.length;
      int var2 = var1.length;
      int[] var6 = Arrays.copyOf(var0, var0.length);
      int[] var7 = new int[var3 - var2];
      var2 = 0;

      int var4;
      for(var3 = 0; var2 < var0.length; var3 = var4) {
         for(var4 = 0; var4 < var1.length; ++var4) {
            if (var1[var4] == var2) {
               var6[var2] = 65535;
            }
         }

         int var5 = var6[var2];
         var4 = var3;
         if (var5 != 65535) {
            var7[var3] = var5;
            var4 = var3 + 1;
         }

         ++var2;
      }

      return var7;
   }

   public static int setIntByteWithBit(int var0, int var1, boolean var2) {
      int var3;
      if (var2) {
         if (var1 == 0) {
            var3 = var0 | 1;
         } else if (1 == var1) {
            var3 = var0 | 2;
         } else if (2 == var1) {
            var3 = var0 | 4;
         } else if (3 == var1) {
            var3 = var0 | 8;
         } else if (4 == var1) {
            var3 = var0 | 16;
         } else if (5 == var1) {
            var3 = var0 | 32;
         } else if (6 == var1) {
            var3 = var0 | 64;
         } else {
            var3 = var0;
            if (7 == var1) {
               var3 = var0 | 128;
            }
         }
      } else if (var1 == 0) {
         var3 = var0 & 254;
      } else if (1 == var1) {
         var3 = var0 & 253;
      } else if (2 == var1) {
         var3 = var0 & 251;
      } else if (3 == var1) {
         var3 = var0 & 247;
      } else if (4 == var1) {
         var3 = var0 & 239;
      } else if (5 == var1) {
         var3 = var0 & 223;
      } else if (6 == var1) {
         var3 = var0 & 191;
      } else {
         var3 = var0;
         if (7 == var1) {
            var3 = var0 & 127;
         }
      }

      return var3;
   }

   public static int setIntByteWithBitFromOtherInt(int var0, int var1, int var2, int var3) {
      return setIntByteWithBit(var0, var1, getIntByteWithBit(var2, var3));
   }

   public static int setIntFromByteWithBit(int var0, int var1, int var2, int var3) {
      return var0 & ~((1 << var3) - 1 << var2) | var1 << var2;
   }

   public static int setIntFromByteWithBitFromOtherInt(int var0, int var1, int var2, int var3, int var4) {
      return setIntFromByteWithBit(var0, getIntFromByteWithBit(var2, var3, var4), var1, var4);
   }

   public static int setOneBit(int var0, int var1, int var2) {
      return var0 ^ (var2 ^ var0 >> var1 & 1) << var1;
   }

   public static byte[] setReportHiworldSrcInfoData(byte var0, byte var1) {
      byte[] var3 = new byte[15];
      var3[0] = 22;
      var3[1] = var0;
      var3[2] = var1;

      for(int var2 = 3; var2 < 15; ++var2) {
         var3[var2] = 32;
      }

      return var3;
   }

   public static int setWidgetVisiable(Boolean var0) {
      byte var1;
      if (var0) {
         var1 = 0;
      } else {
         var1 = 4;
      }

      return var1;
   }

   public static byte[] splicingByte(byte[] var0, byte[] var1) {
      byte[] var2 = new byte[var0.length + var1.length];
      System.arraycopy(var0, 0, var2, 0, var0.length);
      System.arraycopy(var1, 0, var2, var0.length, var1.length);
      return var2;
   }

   public static byte[] stringGetBytes(String var0, String var1) {
      byte[] var2 = new byte[0];

      byte[] var4;
      try {
         var4 = var0.getBytes(var1);
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
         var4 = var2;
      }

      return var4;
   }

   public static String toTimeFormat(int var0, int var1, int var2, boolean var3) {
      StringBuilder var4 = new StringBuilder();
      if (var3) {
         if (var0 != 0) {
            if (var0 < 10) {
               var4.append("0");
            }

            var4.append(var0);
            var4.append(":");
         } else {
            var4.append("00:");
         }
      }

      if (var1 != 0) {
         if (var1 < 10) {
            var4.append("0");
         }

         var4.append(var1);
         var4.append(":");
      } else {
         var4.append("00:");
      }

      if (var2 != 0) {
         if (var2 < 10) {
            var4.append("0");
         }

         var4.append(var2);
      } else {
         var4.append("00");
      }

      return var4.toString();
   }
}
