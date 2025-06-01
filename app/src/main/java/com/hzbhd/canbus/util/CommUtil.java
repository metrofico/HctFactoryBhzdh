package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.sourcemanager.SourceManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommUtil {
   private static int calculateInSampleSize(BitmapFactory.Options var0, int var1, int var2) {
      int var5 = var0.outHeight;
      int var6 = var0.outWidth;
      int var4 = 1;
      int var3 = 1;
      if (var5 > var2 || var6 > var1) {
         var5 /= 2;
         var6 /= 2;

         while(true) {
            var4 = var3;
            if (var5 / var3 < var2) {
               break;
            }

            var4 = var3;
            if (var6 / var3 < var1) {
               break;
            }

            var3 *= 2;
         }
      }

      return var4;
   }

   public static Bitmap decodeSampleBitmapFromResource(Resources var0, int var1, int var2, int var3) {
      BitmapFactory.Options var4 = new BitmapFactory.Options();
      var4.inJustDecodeBounds = true;
      BitmapFactory.decodeResource(var0, var1, var4);
      var4.inSampleSize = calculateInSampleSize(var4, var2, var3);
      var4.inJustDecodeBounds = false;
      return BitmapFactory.decodeResource(var0, var1);
   }

   public static boolean equal(Object var0, Object... var1) {
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         if (var0.equals(var1[var2])) {
            return true;
         }
      }

      return false;
   }

   public static String getAssetsString(Context var0, String var1) {
      AssetManager var9 = var0.getResources().getAssets();

      IOException var10000;
      label34: {
         InputStreamReader var2;
         InputStream var3;
         BufferedReader var4;
         StringBuilder var10;
         boolean var10001;
         try {
            var3 = var9.open(var1);
            var2 = new InputStreamReader(var3, "UTF-8");
            var4 = new BufferedReader(var2);
            var10 = new StringBuilder();
         } catch (IOException var8) {
            var10000 = var8;
            var10001 = false;
            break label34;
         }

         while(true) {
            try {
               var1 = var4.readLine();
            } catch (IOException var6) {
               var10000 = var6;
               var10001 = false;
               break;
            }

            if (var1 == null) {
               try {
                  var4.close();
                  var2.close();
                  var3.close();
                  String var12 = var10.toString();
                  return var12;
               } catch (IOException var5) {
                  var10000 = var5;
                  var10001 = false;
                  break;
               }
            }

            try {
               var10.append(var1);
            } catch (IOException var7) {
               var10000 = var7;
               var10001 = false;
               break;
            }
         }
      }

      IOException var11 = var10000;
      var11.printStackTrace();
      return "";
   }

   public static int getDimenByResId(Context var0, String var1) {
      int var3 = 2131167412;
      int var2;
      if (var0 != null) {
         try {
            var2 = var0.getResources().getIdentifier(var1, "dimen", var0.getPackageName());
         } catch (Exception var4) {
            LogUtil.showLog("getDimenByResId:" + var4.toString());
            var2 = 2131167412;
         }
      } else {
         var2 = 0;
      }

      if (var2 == 0) {
         var2 = var3;
      }

      return var0.getResources().getDimensionPixelSize(var2);
   }

   public static int getImgIdByResId(Context var0, String var1) {
      int var3 = 2131234410;
      int var2;
      if (var0 != null) {
         try {
            var2 = var0.getResources().getIdentifier(var1, "drawable", var0.getPackageName());
         } catch (Exception var4) {
            LogUtil.showLog("getImgIdByResId:" + var4.toString());
            var2 = 2131234410;
         }
      } else {
         var2 = 0;
      }

      if (var2 == 0) {
         var2 = var3;
      }

      return var2;
   }

   public static byte getRadioCurrentBand(String var0, byte var1, byte var2, byte var3, byte var4, byte var5) {
      var0.hashCode();
      int var7 = var0.hashCode();
      byte var6 = -1;
      switch (var7) {
         case 2443:
            if (var0.equals("LW")) {
               var6 = 0;
            }
            break;
         case 2474:
            if (var0.equals("MW")) {
               var6 = 1;
            }
            break;
         case 64901:
            if (var0.equals("AM1")) {
               var6 = 2;
            }
            break;
         case 64902:
            if (var0.equals("AM2")) {
               var6 = 3;
            }
            break;
         case 69706:
            if (var0.equals("FM1")) {
               var6 = 4;
            }
            break;
         case 69707:
            if (var0.equals("FM2")) {
               var6 = 5;
            }
            break;
         case 69708:
            if (var0.equals("FM3")) {
               var6 = 6;
            }
            break;
         case 2426268:
            if (var0.equals("OIRT")) {
               var6 = 7;
            }
      }

      switch (var6) {
         case 0:
         case 2:
            return var4;
         case 1:
         case 3:
            return var5;
         case 4:
            return var1;
         case 5:
            return var2;
         case 6:
         case 7:
            return var3;
         default:
            return 0;
      }
   }

   public static byte getRadioFreqHiOrLow(String var0, String var1, boolean var2) {
      int var3;
      label48: {
         int var4;
         if (!var0.equals("AM2") && !var0.equals("MW") && !var0.equals("AM1") && !var0.equals("LW")) {
            if (!var0.equals("FM1") && !var0.equals("FM2") && !var0.equals("FM3") && !var0.equals("OIRT")) {
               return 0;
            }

            var3 = (int)(Double.parseDouble(var1) * 100.0);
            var4 = var3;
            if (var2) {
               break label48;
            }
         } else {
            if (var2) {
               var3 = Integer.parseInt(var1);
               break label48;
            }

            var4 = Integer.parseInt(var1);
         }

         var3 = var4 & 255;
         return (byte)var3;
      }

      var3 >>= 8;
      return (byte)var3;
   }

   public static String getStrByResId(Context var0, String var1) {
      int var3 = 2131770013;

      int var2;
      try {
         var2 = var0.getResources().getIdentifier(var1, "string", var0.getPackageName());
      } catch (Exception var4) {
         LogUtil.showLog("getStrByResId:" + var4.toString());
         var2 = 2131770013;
      }

      if (var2 == 0) {
         var2 = var3;
      }

      return var0.getString(var2);
   }

   public static int getStrIdByResId(Context var0, String var1) {
      int var3 = 2131770013;
      int var2;
      if (var0 != null) {
         try {
            var2 = var0.getResources().getIdentifier(var1, "string", var0.getPackageName());
         } catch (Exception var4) {
            LogUtil.showLog("getStrIdByResId:" + var4.toString());
            var2 = 2131770013;
         }
      } else {
         var2 = 0;
      }

      if (var2 == 0) {
         var2 = var3;
      }

      return var2;
   }

   public static String getVersionName(Context var0) {
      return "20240311-1942";
   }

   public static int isAirTemperatureSwitch(Context var0) {
      return ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
   }

   public static boolean isBackCamera(Context var0) {
      boolean var1;
      if (isMiscReverse() && !isPanoramic(var0)) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean isMiscReverse() {
      int var0 = MediaShareData.Misc.INSTANCE.getMiscReverse();
      boolean var1 = true;
      if (var0 != 1) {
         var1 = false;
      }

      return var1;
   }

   public static boolean isPanoramic(Context var0) {
      return SharePreUtil.getBoolValue(var0, "share_is_panoramic", false);
   }

   public static void playBeep() {
      SendKeyManager.getInstance().playBeep(0);
   }

   public static void printHexString(String var0, byte[] var1) {
      StringBuilder var5 = new StringBuilder();
      var5.append(var0);
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         String var4 = Integer.toHexString(var1[var2] & 255);
         var0 = var4;
         if (var4.length() == 1) {
            var0 = '0' + var4;
         }

         var5.append(var0.toUpperCase());
         var5.append(" ");
      }

      LogUtil.showLog(var5.toString());
   }

   public static void printHexStringByTag(String var0, String var1, byte[] var2) {
      StringBuilder var6 = new StringBuilder();
      var6.append(var1);
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         String var5 = Integer.toHexString(var2[var3] & 255);
         var1 = var5;
         if (var5.length() == 1) {
            var1 = '0' + var5;
         }

         var6.append(var1.toUpperCase());
         var6.append(" ");
      }

      LogUtil.showLog(var0, var6.toString());
   }

   public static void printScreenWidthHeigh(Context var0) {
      WindowManager var4 = (WindowManager)var0.getSystemService("window");
      DisplayMetrics var3 = new DisplayMetrics();
      var4.getDefaultDisplay().getMetrics(var3);
      int var2 = var3.widthPixels;
      int var1 = var3.heightPixels;
      LogUtil.showLog("screenWidth:" + var2);
      LogUtil.showLog("screenHeight:" + var1);
   }

   public static void releaseAudioChannel(SourceConstantsDef.SOURCE_ID var0, SourceConstantsDef.DISP_ID var1) {
      SourceManager.getInstance().releaseAudioChannel(var0, var1);
   }

   public static void requestAudioChannel(SourceConstantsDef.SOURCE_ID var0, SourceConstantsDef.DISP_ID var1, Bundle var2) {
      SourceManager.getInstance().requestAudioChannel(var0, var1, var2);
   }

   public static void showToast(Context var0, String var1) {
      Toast.makeText(var0, var1, 1).show();
   }

   public static String subArrayToString(byte[] var0, int var1, int var2) {
      byte[] var4 = new byte[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         var4[var3] = var0[var1 + var3];
      }

      return new String(var4);
   }

   public static String temperatureUnitSwitch(String var0) {
      if (TextUtils.isEmpty(var0)) {
         return "";
      } else {
         Exception var10000;
         label59: {
            int var1;
            boolean var10001;
            try {
               var1 = ((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getTemperatureUnit();
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label59;
            }

            if (var1 == 1) {
               label45: {
                  try {
                     if (var0.endsWith("℃")) {
                        return String.format("%.1f℉", Float.parseFloat(var0.substring(0, var0.length() - 1)) * 9.0F / 5.0F + 32.0F);
                     }
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                     break label45;
                  }

                  try {
                     var0.endsWith("℉");
                     return var0;
                  } catch (Exception var3) {
                     var10000 = var3;
                     var10001 = false;
                  }
               }
            } else {
               label61: {
                  try {
                     if (var0.endsWith("℃")) {
                        return var0;
                     }
                  } catch (Exception var7) {
                     var10000 = var7;
                     var10001 = false;
                     break label61;
                  }

                  String var2 = var0;

                  try {
                     if (var0.endsWith("℉")) {
                        var2 = String.format("%.1f℃", (Float.parseFloat(var0.substring(0, var0.length() - 1)) - 32.0F) * 5.0F / 9.0F);
                     }

                     return var2;
                  } catch (Exception var4) {
                     var10000 = var4;
                     var10001 = false;
                  }
               }
            }
         }

         Exception var8 = var10000;
         Log.i("CommUtil", "temperatureUnitSwitch failed [" + var0 + "], message: " + var8.getMessage());
         return var0;
      }
   }
}
