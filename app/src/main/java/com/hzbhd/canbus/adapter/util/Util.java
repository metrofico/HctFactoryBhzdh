package com.hzbhd.canbus.adapter.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import java.util.Arrays;

public class Util {
   public static String CANBUS_AMP_VAL_BAL;
   public static String CANBUS_AMP_VAL_BASS;
   public static String CANBUS_AMP_VAL_EFFECT;
   public static String CANBUS_AMP_VAL_FAD;
   public static String CANBUS_AMP_VAL_MID;
   public static String CANBUS_AMP_VAL_TRE;
   public static String CANBUS_AMP_VAL_VOL;
   public static String CANBUS_IS_DATA_PORT;
   public static final int MCU_HARDWARE_CURRENTMAX = 10;
   public static final int MCU_HARDWARE_stm32f030c8 = 1;
   public static final int MCU_HARDWARE_stm32f030rc = 3;
   public static final int MCU_HARDWARE_stm32f072c8 = 2;
   public static final int MCU_HARDWARE_stm8s208 = 0;
   public static boolean isCanbusCmdStyleSt32;
   private static String oldMsg;
   private static long oneTime;
   private static Toast toast;
   private static long twoTime;

   public static byte[] CompensateZero(byte[] var0, int var1) {
      byte[] var3 = new byte[var1];
      System.arraycopy(var0, 0, var3, 0, var0.length);

      for(int var2 = var0.length; var2 < var1; ++var2) {
         var3[var2] = 0;
      }

      return var3;
   }

   public static boolean GetBooleanValue(Context var0, String var1, boolean var2) {
      return var0.getSharedPreferences("wujay", 0).getBoolean(var1, var2);
   }

   public static int GetIntValue(Context var0, String var1, int var2) {
      return var0.getSharedPreferences("wujay", 0).getInt(var1, var2);
   }

   public static void SaveBooleanValue(Context var0, String var1, boolean var2) {
      SharedPreferences.Editor var3 = var0.getSharedPreferences("wujay", 0).edit();
      var3.putBoolean(var1, var2);
      var3.commit();
   }

   public static void SaveIntValue(Context var0, String var1, int var2) {
      SharedPreferences.Editor var3 = var0.getSharedPreferences("wujay", 0).edit();
      var3.putInt(var1, var2);
      var3.commit();
   }

   public static void WLog(Context var0, String var1, String var2) {
      if (android.provider.Settings.System.getInt(var0.getContentResolver(), var1, 0) == 1) {
         Log.i(var1, var2);
      }

   }

   public static byte[] addBytes(byte[] var0, byte var1) {
      int var2 = 0;
      if (var0 == null) {
         var0 = new byte[]{var1};
      } else {
         int var3 = var0.length + 1;

         byte[] var4;
         for(var4 = new byte[var3]; var2 < var3; ++var2) {
            if (var2 < var0.length) {
               var4[var2] = var0[var2];
            } else {
               var4[var2] = var1;
            }
         }

         var0 = var4;
      }

      return var0;
   }

   private static byte ascToBcd(byte var0) {
      byte var1;
      if (var0 >= 48 && var0 <= 57) {
         var1 = (byte)(var0 - 48);
      } else if (var0 == 42) {
         var1 = 10;
      } else if (var0 == 35) {
         var1 = 11;
      } else if (var0 == 43) {
         var1 = 12;
      } else {
         var1 = 15;
      }

      return var1;
   }

   public static byte[] asciiToBcd(byte[] var0, int var1) {
      int var3 = (var1 + 1) / 2;
      byte[] var6 = new byte[var3];

      for(int var2 = 0; var2 < var3; ++var2) {
         int var5 = var2 * 2;
         int var4 = var5 + 1;
         if (var4 >= var1) {
            var6[var2] = (byte)(ascToBcd(var0[var5]) << 4 | 15);
         } else {
            var6[var2] = (byte)(ascToBcd(var0[var5]) << 4 | ascToBcd(var0[var4]));
         }
      }

      return var6;
   }

   public static byte byte2Bcd(byte var0) {
      return (byte)(var0 % 10 & 15 | var0 / 10 << 4);
   }

   public static String byte2HexString(byte var0) {
      String var2 = Integer.toHexString(var0 & 255);
      String var1 = var2;
      if (var2.length() == 1) {
         var1 = '0' + var2;
      }

      return var1;
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

   public static byte byteNum2AscByte(byte var0) {
      return var0 >= 0 && var0 <= 9 ? (byte)(var0 + 48) : 48;
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

   public static int castByte2LegalNum(byte var0) {
      int var1 = var0;
      if ((var0 & 128) != 0) {
         var1 = var0 & 255;
      }

      return var1;
   }

   public static boolean compare(Object var0, Object... var1) {
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         if (var1[var2].equals(var0)) {
            return true;
         }
      }

      return false;
   }

   public static boolean contains(String var0, String... var1) {
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         if (var0.contains(var1[var2])) {
            return true;
         }
      }

      return false;
   }

   public static byte[] deal0x57Data(byte[] var0) {
      if (var0.length > 2) {
         var0 = Arrays.copyOfRange(var0, 2, var0.length - 1);
         return byteMerger(new byte[]{87}, var0);
      } else {
         return new byte[0];
      }
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

   public static Boolean getBooleanFromByteWithBit(int var0, int var1, int var2) {
      return ((var0 & 255) >> var1 & (1 << var2) - 1) == 1 ? true : false;
   }

   public static int getDecimalBit(int var0, int var1) {
      return var0 / (int)Math.pow(10.0, (double)var1) % 10;
   }

   public static String getFileName(int var0) {
      String var1;
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 == 2 || var0 == 3) {
               var1 = "mcu-can-upd-golf.bin";
               return var1;
            }

            if (var0 != 4) {
               if (var0 != 5) {
                  if (var0 != 21 && var0 != 22) {
                     if (var0 != 141 && var0 != 142) {
                        switch (var0) {
                           case 11:
                           case 13:
                           case 14:
                           case 15:
                           case 16:
                           case 17:
                              var1 = "mcu-can-upd-toyota.bin";
                              return var1;
                           default:
                              switch (var0) {
                                 case 30:
                                    var1 = "mcu-can-upd-kia-rs.bin";
                                    return var1;
                                 case 31:
                                 case 32:
                                 case 33:
                                 case 34:
                                 case 35:
                                 case 36:
                                 case 37:
                                 case 38:
                                    var1 = "mcu-can-upd-kia.bin";
                                    return var1;
                                 case 39:
                                    var1 = "mcu-can-upd-kia-xb.bin";
                                    return var1;
                                 case 40:
                                    var1 = "mcu-can-upd-sorento-xb.bin";
                                    return var1;
                                 case 41:
                                 case 46:
                                 case 47:
                                    var1 = "mcu-can-upd-honda.bin";
                                    return var1;
                                 case 42:
                                    var1 = "mcu-can-upd-crv12.bin";
                                    return var1;
                                 case 43:
                                    var1 = "mcu-can-upd-honda-xb.bin";
                                    return var1;
                                 case 44:
                                    var1 = "mcu-can-upd-crv15-xb.bin";
                                    return var1;
                                 case 45:
                                    var1 = "mcu-can-upd-crv12-xb.bin";
                                    return var1;
                                 case 48:
                                 case 49:
                                 case 50:
                                    var1 = "mcu-can-upd-accord.bin";
                                    return var1;
                                 case 51:
                                    var1 = "mcu-can-upd-accord20-xb.bin";
                                    return var1;
                                 case 52:
                                    var1 = "mcu-can-upd-accord24-xb.bin";
                                    return var1;
                                 case 151:
                                    break;
                                 default:
                                    switch (var0) {
                                       case 60:
                                          var1 = "mcu-can-upd-opel.bin";
                                          return var1;
                                       case 61:
                                       case 66:
                                       case 68:
                                          var1 = "mcu-can-upd-gm.bin";
                                          return var1;
                                       case 62:
                                       case 71:
                                          var1 = "mcu-can-upd-gm-xb.bin";
                                          return var1;
                                       case 63:
                                       case 69:
                                          var1 = "mcu-can-upd-encore.bin";
                                          return var1;
                                       case 64:
                                          var1 = "mcu-can-upd-malibu.bin";
                                          return var1;
                                       case 65:
                                          var1 = "mcu-can-upd-astra.bin";
                                          return var1;
                                       case 67:
                                       case 70:
                                          var1 = "mcu-can-upd-lacrosse.bin";
                                          return var1;
                                       default:
                                          switch (var0) {
                                             case 81:
                                             case 82:
                                             case 83:
                                             case 84:
                                             case 94:
                                                var1 = "mcu-can-upd-ford.bin";
                                                return var1;
                                             case 85:
                                             case 95:
                                                var1 = "mcu-can-upd-edge.bin";
                                                return var1;
                                             case 86:
                                                var1 = "mcu-can-upd-mondeo.bin";
                                                return var1;
                                             case 87:
                                             case 88:
                                             case 89:
                                             case 90:
                                                var1 = "mcu-can-upd-ford-xb.bin";
                                                return var1;
                                             case 91:
                                             case 92:
                                                var1 = "mcu-can-upd-edge-xb.bin";
                                                return var1;
                                             case 93:
                                                var1 = "mcu-can-upd-mondeo17-xb.bin";
                                                return var1;
                                             default:
                                                switch (var0) {
                                                   case 101:
                                                      var1 = "mcu-can-upd-benzb200.bin";
                                                      return var1;
                                                   case 102:
                                                      var1 = "mcu-can-upd-benzvito.bin";
                                                      return var1;
                                                   case 103:
                                                      var1 = "mcu-can-upd-mitsubishi.bin";
                                                      return var1;
                                                   case 104:
                                                      var1 = "mcu-can-upd-bmwe90.bin";
                                                      return var1;
                                                   case 105:
                                                      var1 = "mcu-can-upd-audi.bin";
                                                      return var1;
                                                   case 106:
                                                      var1 = "mcu-can-upd-subaru.bin";
                                                      return var1;
                                                   case 107:
                                                      var1 = "mcu-can-upd-dacia.bin";
                                                      return var1;
                                                   default:
                                                      switch (var0) {
                                                         case 110:
                                                         case 111:
                                                         case 112:
                                                         case 113:
                                                         case 114:
                                                            var1 = "mcu-can-upd-jeep.bin";
                                                            return var1;
                                                         case 115:
                                                         case 116:
                                                         case 117:
                                                            var1 = "mcu-can-upd-jeep-xb.bin";
                                                            return var1;
                                                         case 118:
                                                         case 119:
                                                         case 120:
                                                            var1 = "mcu-can-upd-jeep-rs.bin";
                                                            return var1;
                                                         case 121:
                                                            break;
                                                         default:
                                                            switch (var0) {
                                                               case 131:
                                                                  var1 = "mcu-can-upd-mazda13.bin";
                                                                  return var1;
                                                               case 132:
                                                                  var1 = "mcu-can-upd-mazda17.bin";
                                                                  return var1;
                                                               case 133:
                                                                  var1 = "mcu-can-upd-mazda10-xb.bin";
                                                                  return var1;
                                                               case 134:
                                                                  var1 = "mcu-can-upd-mazda17-xb.bin";
                                                                  return var1;
                                                               default:
                                                                  var1 = "No file";
                                                                  return var1;
                                                            }
                                                      }
                                                }
                                          }
                                    }
                              }
                           case 12:
                              var1 = "mcu-can-upd-toyota-hw.bin";
                        }
                     } else {
                        var1 = "mcu-can-upd-psa.bin";
                     }

                     return var1;
                  } else {
                     var1 = "mcu-can-upd-nissan.bin";
                     return var1;
                  }
               } else {
                  var1 = "mcu-can-upd-vw-hw.bin";
                  return var1;
               }
            }
         }

         var1 = "mcu-can-upd-vw.bin";
      } else {
         var1 = "mcu-can-upd-none.bin";
      }

      return var1;
   }

   public static int getIntByBit(byte var0, int var1) {
      boolean var2;
      label62: {
         if (var1 == 0) {
            if ((var0 & 1) != 0) {
               break label62;
            }
         } else if (1 == var1) {
            if ((var0 & 2) != 0) {
               break label62;
            }
         } else if (2 == var1) {
            if ((var0 & 4) != 0) {
               break label62;
            }
         } else if (3 == var1) {
            if ((var0 & 8) != 0) {
               break label62;
            }
         } else if (4 == var1) {
            if ((var0 & 16) != 0) {
               break label62;
            }
         } else if (5 == var1) {
            if ((var0 & 32) != 0) {
               break label62;
            }
         } else if (6 == var1) {
            if ((var0 & 64) != 0) {
               break label62;
            }
         } else if (7 == var1 && (var0 & 128) != 0) {
            break label62;
         }

         var2 = false;
         return var2 ? 1 : 0;
      }

      var2 = true;
      return var2 ? 1 : 0;
   }

   public static boolean getIntByteWithBit(byte var0, int var1) {
      boolean var7 = false;
      boolean var9 = false;
      boolean var6 = false;
      boolean var8 = false;
      boolean var2 = false;
      boolean var4 = false;
      boolean var3 = false;
      boolean var5 = false;
      if (var1 == 0) {
         var2 = var5;
         if ((var0 & 1) != 0) {
            var2 = true;
         }

         return var2;
      } else if (1 == var1) {
         var2 = var7;
         if ((var0 & 2) != 0) {
            var2 = true;
         }

         return var2;
      } else if (2 == var1) {
         var2 = var9;
         if ((var0 & 4) != 0) {
            var2 = true;
         }

         return var2;
      } else if (3 == var1) {
         var2 = var6;
         if ((var0 & 8) != 0) {
            var2 = true;
         }

         return var2;
      } else if (4 == var1) {
         var2 = var8;
         if ((var0 & 16) != 0) {
            var2 = true;
         }

         return var2;
      } else if (5 == var1) {
         if ((var0 & 32) != 0) {
            var2 = true;
         }

         return var2;
      } else if (6 == var1) {
         var2 = var4;
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

   public static boolean getIntByteWithBit(int var0, int var1) {
      boolean var8 = false;
      boolean var4 = false;
      boolean var7 = false;
      boolean var6 = false;
      boolean var2 = false;
      boolean var5 = false;
      boolean var3 = false;
      boolean var9 = false;
      if (var1 == 0) {
         var2 = var9;
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
         var2 = var4;
         if ((var0 & 4) != 0) {
            var2 = true;
         }

         return var2;
      } else if (3 == var1) {
         var2 = var7;
         if ((var0 & 8) != 0) {
            var2 = true;
         }

         return var2;
      } else if (4 == var1) {
         var2 = var6;
         if ((var0 & 16) != 0) {
            var2 = true;
         }

         return var2;
      } else if (5 == var1) {
         if ((var0 & 32) != 0) {
            var2 = true;
         }

         return var2;
      } else if (6 == var1) {
         var2 = var5;
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

   public static int getIntFronmDimen(Context var0, int var1) {
      var1 = var0.getResources().getIdentifier("dp" + var1, "dimen", var0.getPackageName());
      return var0.getResources().getDimensionPixelSize(var1);
   }

   public static boolean getIsDebug(Context var0) {
      if (var0 == null) {
         return false;
      } else {
         int var1 = android.provider.Settings.System.getInt(var0.getContentResolver(), "isDebugModel", 0);
         if (var1 == 0) {
            return false;
         } else {
            return var1 == 1;
         }
      }
   }

   public static int getMcuChipType(Context var0) {
      return android.provider.Settings.System.getInt(var0.getContentResolver(), "McuHardWare", 0);
   }

   public static String getProtocolCompany(String var0) {
      if (var0.contains("Simple")) {
         return "SIMPLE";
      } else if (var0.contains("Hiworld")) {
         return "HIWORLD";
      } else if (var0.contains("Raise")) {
         return "RAISE";
      } else if (var0.contains("Xinbas")) {
         return "XINBAS";
      } else if (var0.contains("Oudi")) {
         return "OUDI";
      } else if (var0.contains("Luzheng")) {
         return "LUZHENG";
      } else if (var0.contains("XFY")) {
         return "XFY";
      } else if (var0.contains("Binary")) {
         return "BINARY";
      } else if (var0.contains("CYT")) {
         return "CYT";
      } else {
         return var0.contains("Dogen") ? "DOGEN" : "OTHER";
      }
   }

   public static boolean getStopAllFuncFlag(Context var0) {
      StringBuilder var3 = (new StringBuilder()).append("isNeedStopAllFunc=");
      ContentResolver var2 = var0.getContentResolver();
      boolean var1 = false;
      Log.e("getStopAllFuncFlag", var3.append(android.provider.Settings.System.getInt(var2, "PRJ_3163_STOP_FUNC_FLAG", 0)).toString());
      if (android.provider.Settings.System.getInt(var0.getContentResolver(), "PRJ_3163_STOP_FUNC_FLAG", 0) == 1) {
         var1 = true;
      }

      return var1;
   }

   public static boolean isCanbusCmdPorting(Context var0) {
      return true;
   }

   public static boolean isOriginalScreen() {
      return ConfigUtil.getDeviceId().contains("N60") || ConfigUtil.getDeviceId().contains("N74") || ConfigUtil.getDeviceId().contains("N52") || ConfigUtil.getDeviceId().contains("N95") || ConfigUtil.getDeviceId().contains("N0B") || ConfigUtil.getDeviceId().contains("N0J") || ConfigUtil.getDeviceId().contains("NZ1");
   }

   public static boolean isOriginalScreenCanSwitchDspAndOriginalAmp() {
      return ConfigUtil.getDeviceId().contains("N95") || ConfigUtil.getDeviceId().contains("N0B") || ConfigUtil.getDeviceId().contains("N52") || ConfigUtil.getDeviceId().contains("NZ1");
   }

   public static boolean isOriginalScreenN60N74() {
      return ConfigUtil.getDeviceId().contains("N60") || ConfigUtil.getDeviceId().contains("N74") || ConfigUtil.getDeviceId().contains("N95") || ConfigUtil.getDeviceId().contains("N0B") || ConfigUtil.getDeviceId().contains("N0J") || ConfigUtil.getDeviceId().contains("NZ1");
   }

   public static boolean isOriginalSreenNeedBt() {
      return ConfigUtil.getDeviceId().contains("N52");
   }

   public static boolean isPowerOffStatus(Context var0) {
      ContentResolver var2 = var0.getContentResolver();
      boolean var1 = false;
      if (android.provider.Settings.System.getInt(var2, "PowerIsOff", 0) == 1) {
         var1 = true;
      }

      return var1;
   }

   public static Boolean isSaleInside(Context var0) {
      ContentResolver var4 = var0.getContentResolver();
      boolean var3 = false;
      int var1 = android.provider.Settings.System.getInt(var4, "McuHardWare", 0);
      boolean var2 = var3;
      if (var1 >= 1) {
         var2 = var3;
         if (var1 <= 10) {
            var2 = true;
         }
      }

      return var2;
   }

   public static SingleSpinMachine isYHT(Context var0) {
      int var1 = android.provider.Settings.System.getInt(var0.getContentResolver(), "LCD_MCU_VERSION", 0);
      if (var1 != 16) {
         if (var1 != 34) {
            if (var1 != 49) {
               if (var1 != 50) {
                  if (var1 != 64) {
                     if (var1 != 65) {
                        if (var1 != 73) {
                           return var1 != 74 ? Util.SingleSpinMachine.PUBLIC : Util.SingleSpinMachine.HTT2;
                        } else {
                           return Util.SingleSpinMachine.HTT2;
                        }
                     } else {
                        return Util.SingleSpinMachine.HTT2;
                     }
                  } else {
                     return Util.SingleSpinMachine.HONG;
                  }
               } else {
                  return Util.SingleSpinMachine.KT2;
               }
            } else {
               return Util.SingleSpinMachine.LK;
            }
         } else {
            return Util.SingleSpinMachine.YHT;
         }
      } else {
         return Util.SingleSpinMachine.KT;
      }
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

   public static String makeMediaInfoCenteredInBytes(int var0, String var1) {
      String var5 = "";
      if (var1 != null && var1.length() <= var0) {
         int var3 = var0 - var1.length();
         int var4 = var3 / 2;
         byte var2 = 0;

         for(var0 = 0; var0 < var4; ++var0) {
            var5 = var5 + " ";
         }

         var1 = var5 + var1;

         for(var0 = var2; var0 < var3 - var4; ++var0) {
            var1 = var1 + " ";
         }

         return var1;
      } else {
         return "";
      }
   }

   public static byte[] mergeByteArrayAndAscii(byte[] var0, String var1) {
      return byteMerger(var0, var1.getBytes());
   }

   public static String numCompensateZero(int var0) {
      return var0 < 10 ? "0" + var0 : "" + var0;
   }

   public static byte[] phoneNum2Ascii(byte[] var0) {
      byte[] var2 = new byte[var0.length];

      for(int var1 = 0; var1 < var0.length; ++var1) {
         var0[var1] = 48;
         var2[var1] = 48;
      }

      return var2;
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

   public static byte[] phoneNum2UnicodeLittleExtra(byte[] var0) {
      byte[] var3 = new byte[var0.length * 2];

      for(int var1 = 0; var1 < var0.length; ++var1) {
         int var2 = var1 * 2;
         var3[var2] = (byte)(var0[var1] & 255);
         var3[var2 + 1] = 0;
      }

      return var3;
   }

   public static void reportCanbusInfo(byte[] var0) {
      try {
         FutureUtil.instance.reportCanbusInfo(var0);
      } catch (Exception var1) {
      }

   }

   public static void reportHiworldMideaInfo(Context var0, CanTypeMsg.ENUM_CANSBUS_HIWORLD_MEDIA_TYPE var1, boolean var2, byte[] var3) {
      SourceConstantsDef.SOURCE_ID var8 = SourceConstantsDef.SOURCE_ID.NULL;
      int var5 = 12;
      byte[] var9 = new byte[12];
      byte var6 = 0;
      byte var4 = (byte)var1.ordinal();
      if (var3.length < 12) {
         var5 = var3.length;
      }

      byte var7 = (byte)var5;
      if (var3 != null) {
         for(var5 = var6; var5 < var7; ++var5) {
            var9[var5] = var3[var5];
         }
      }

      SourceConstantsDef.SOURCE_ID var11;
      switch (null.$SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_HIWORLD_MEDIA_TYPE[var1.ordinal()]) {
         case 1:
            var11 = SourceConstantsDef.SOURCE_ID.NULL;
            break;
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
            var11 = SourceConstantsDef.SOURCE_ID.FM;
            break;
         case 7:
         case 8:
            var11 = SourceConstantsDef.SOURCE_ID.MPEG;
            break;
         case 9:
            var11 = SourceConstantsDef.SOURCE_ID.DTV;
            break;
         case 10:
            var11 = SourceConstantsDef.SOURCE_ID.NULL;
            break;
         case 11:
            var11 = SourceConstantsDef.SOURCE_ID.AUX1;
            break;
         case 12:
         case 13:
            if (var2) {
               var11 = SourceConstantsDef.SOURCE_ID.VIDEO;
            } else {
               var11 = SourceConstantsDef.SOURCE_ID.MUSIC;
            }
            break;
         default:
            var11 = var8;
      }

      var3 = byteMerger(new byte[]{22, -46, var4}, var9);

      try {
         sndSrcMediaInfo(var0, var11, var3);
      } catch (RemoteException var10) {
         var10.printStackTrace();
      }

   }

   public static void reportSimpleMediaInfo(Context var0, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE var1, int var2, boolean var3, boolean var4) {
      byte var6 = (byte)var1.ordinal();
      SourceConstantsDef.SOURCE_ID var8 = SourceConstantsDef.SOURCE_ID.NULL;
      switch (null.$SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[var1.ordinal()]) {
         case 1:
            var8 = SourceConstantsDef.SOURCE_ID.NULL;
            break;
         case 2:
            var8 = SourceConstantsDef.SOURCE_ID.FM;
            break;
         case 3:
            var8 = SourceConstantsDef.SOURCE_ID.AUX1;
            break;
         case 4:
            var8 = SourceConstantsDef.SOURCE_ID.NULL;
            break;
         case 5:
            var8 = SourceConstantsDef.SOURCE_ID.BTAUDIO;
            break;
         case 6:
            var8 = SourceConstantsDef.SOURCE_ID.DTV;
            break;
         case 7:
            var8 = SourceConstantsDef.SOURCE_ID.ATV;
            break;
         case 8:
         case 9:
            var8 = SourceConstantsDef.SOURCE_ID.MPEG;
            break;
         case 10:
            var8 = SourceConstantsDef.SOURCE_ID.VIDEO;
            break;
         case 11:
         case 12:
            if (var3) {
               var8 = SourceConstantsDef.SOURCE_ID.VIDEO;
            } else {
               var8 = SourceConstantsDef.SOURCE_ID.MUSIC;
            }
      }

      byte var5 = var6;
      int var7 = var2;
      if (var2 == 255) {
         label48: {
            switch (null.$SwitchMap$com$hzbhd$canbus$adapter$util$CanTypeMsg$ENUM_CANSBUS_SIMPLE_MEDIA_TYPE[var1.ordinal()]) {
               case 1:
               case 6:
               case 7:
                  var7 = 0;
                  var5 = var6;
                  break label48;
               case 2:
                  var7 = 1;
                  var5 = var6;
                  break label48;
               case 3:
                  var7 = 48;
                  var5 = var6;
                  break label48;
               case 4:
               case 5:
                  var7 = 64;
                  var5 = var6;
                  break label48;
               case 8:
                  var7 = 16;
                  var5 = 16;
                  break label48;
               case 9:
                  var7 = 33;
                  break;
               case 10:
                  var7 = 35;
                  break;
               default:
                  var5 = var6;
                  var7 = var2;
                  break label48;
            }

            var5 = 17;
         }
      }

      byte[] var10;
      if (var4) {
         var10 = new byte[]{22, -64, var5, (byte)var7, 0, 0, 0, 0, 0, 0};
      } else {
         var10 = new byte[]{22, -64, var5, (byte)var7};
      }

      try {
         sndSrcMediaInfo(var0, var8, var10);
      } catch (RemoteException var9) {
         var9.printStackTrace();
      }

   }

   public static byte setByteFromByteWithBitFromOtherByte(int var0, int var1, int var2, int var3, int var4) {
      return (byte)(setIntFromByteWithBit(var0, getIntFromByteWithBit(var2, var3, var4), var1, var4) & 255);
   }

   public static byte setByteWithBit(byte var0, int var1, boolean var2) {
      byte var3;
      if (var2) {
         if (var1 == 0) {
            var1 = var0 | 1;
         } else if (1 == var1) {
            var1 = var0 | 2;
         } else if (2 == var1) {
            var1 = var0 | 4;
         } else if (3 == var1) {
            var1 = var0 | 8;
         } else if (4 == var1) {
            var1 = var0 | 16;
         } else if (5 == var1) {
            var1 = var0 | 32;
         } else if (6 == var1) {
            var1 = var0 | 64;
         } else {
            var3 = var0;
            if (7 != var1) {
               return var3;
            }

            var1 = var0 | 128;
         }
      } else if (var1 == 0) {
         var1 = var0 & 254;
      } else if (1 == var1) {
         var1 = var0 & 253;
      } else if (2 == var1) {
         var1 = var0 & 251;
      } else if (3 == var1) {
         var1 = var0 & 247;
      } else if (4 == var1) {
         var1 = var0 & 239;
      } else if (5 == var1) {
         var1 = var0 & 223;
      } else if (6 == var1) {
         var1 = var0 & 191;
      } else {
         var3 = var0;
         if (7 != var1) {
            return var3;
         }

         var1 = var0 & 127;
      }

      var3 = (byte)var1;
      return var3;
   }

   public static byte setByteWithBitFromOtherByte(int var0, int var1, int var2, int var3) {
      return (byte)(setIntByteWithBit(var0, var1, getIntByteWithBit(var2, var3)) & 255);
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

   public static void setStopAllFuncFlag(Context var0, boolean var1) {
      Log.e("getStopAllFuncFlag", " setStopAllFuncFlag isNeedStopAllFunc=" + var1);
      android.provider.Settings.System.putInt(var0.getContentResolver(), "PRJ_3163_STOP_FUNC_FLAG", var1);
   }

   public static void showToast(Context var0, String var1) {
      if (toast == null) {
         Toast var2 = Toast.makeText(var0, var1, 0);
         toast = var2;
         var2.show();
         oneTime = System.currentTimeMillis();
      } else {
         twoTime = System.currentTimeMillis();
         if (var1.equals(oldMsg)) {
            if (twoTime - oneTime > 0L) {
               toast.show();
            }
         } else {
            oldMsg = var1;
            toast.setText(var1);
            toast.show();
         }
      }

      oneTime = twoTime;
   }

   private static void sndSrcMediaInfo(Context var0, SourceConstantsDef.SOURCE_ID var1, byte[] var2) throws RemoteException {
      if (var1 != SourceConstantsDef.SOURCE_ID.NULL) {
         int var3 = null.$SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (android.provider.Settings.System.getInt(var0.getContentResolver(), "btState", 1) == 1 && (FutureUtil.instance.getCurrentValidSource() == var1 || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE)) {
                  FutureUtil.instance.reportCanbusInfo(var2);
                  android.provider.Settings.System.putString(var0.getContentResolver(), "currentReport", Base64.encodeToString(var2, 0));
                  android.provider.Settings.System.putString(var0.getContentResolver(), "CANBUS_SRC_REP", Base64.encodeToString(var2, 0));
               }
            } else if (android.provider.Settings.System.getInt(var0.getContentResolver(), "btState", 1) == 1 && (FutureUtil.instance.getCurrentValidSource() == var1 || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BACKCAMERA || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE)) {
               FutureUtil.instance.reportCanbusInfo(var2);
               android.provider.Settings.System.putString(var0.getContentResolver(), "currentReport", Base64.encodeToString(var2, 0));
               android.provider.Settings.System.putString(var0.getContentResolver(), "CANBUS_SRC_REP", Base64.encodeToString(var2, 0));
            }
         } else if (android.provider.Settings.System.getInt(var0.getContentResolver(), "btState", 1) == 1 && (FutureUtil.instance.getCurrentValidSource() == var1 || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.AUX2 || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE)) {
            FutureUtil.instance.reportCanbusInfo(var2);
            android.provider.Settings.System.putString(var0.getContentResolver(), "currentReport", Base64.encodeToString(var2, 0));
            android.provider.Settings.System.putString(var0.getContentResolver(), "CANBUS_SRC_REP", Base64.encodeToString(var2, 0));
         }
      } else {
         FutureUtil.instance.reportCanbusInfo(var2);
      }

   }

   public static byte[] string2UnicodeBIG(String var0) {
      byte[] var4 = new byte[var0.length() * 2];

      for(int var1 = 0; var1 < var0.length(); ++var1) {
         char var3 = var0.charAt(var1);
         int var2 = var1 * 2;
         var4[var2] = (byte)(var3 >> 8 & 255);
         var4[var2 + 1] = (byte)(var3 & 255);
      }

      return var4;
   }

   public static byte[] string2UnicodeSMALL(String var0) {
      byte[] var4 = new byte[var0.length() * 2];

      for(int var1 = 0; var1 < var0.length(); ++var1) {
         char var2 = var0.charAt(var1);
         int var3 = var1 * 2;
         var4[var3] = (byte)(var2 & 255);
         var4[var3 + 1] = (byte)(var2 >> 8 & 255);
      }

      return var4;
   }

   public static String switchInteToHex(Integer var0) {
      String var1 = Integer.toHexString(var0);
      String var2 = var1;
      if (var1.length() == 1) {
         var2 = "0" + var1;
      }

      return var2;
   }

   public static enum SingleSpinMachine {
      private static final SingleSpinMachine[] $VALUES;
      HONG,
      HTT2,
      KT,
      KT2,
      LK,
      PUBLIC,
      YHT;

      static {
         SingleSpinMachine var2 = new SingleSpinMachine("PUBLIC", 0);
         PUBLIC = var2;
         SingleSpinMachine var5 = new SingleSpinMachine("HONG", 1);
         HONG = var5;
         SingleSpinMachine var0 = new SingleSpinMachine("YHT", 2);
         YHT = var0;
         SingleSpinMachine var1 = new SingleSpinMachine("KT", 3);
         KT = var1;
         SingleSpinMachine var3 = new SingleSpinMachine("LK", 4);
         LK = var3;
         SingleSpinMachine var6 = new SingleSpinMachine("KT2", 5);
         KT2 = var6;
         SingleSpinMachine var4 = new SingleSpinMachine("HTT2", 6);
         HTT2 = var4;
         $VALUES = new SingleSpinMachine[]{var2, var5, var0, var1, var3, var6, var4};
      }
   }
}
