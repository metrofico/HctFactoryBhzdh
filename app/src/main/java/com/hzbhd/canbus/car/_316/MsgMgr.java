package com.hzbhd.canbus.car._316;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   private final String TAG = "_316_MsgMgr";
   private int m0x82Data0;
   private byte[] mCanbusInfoByte;
   private int[] mCanbusInfoInt;
   private Context mContext;
   private DecimalFormat mDecimalFormat00;

   private String resolveLeftAndRightAutoTemp(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = "LO";
      } else if (255 == var1) {
         var2 = "HI";
      } else if (var1 >= 31 && var1 <= 63) {
         var2 = (float)var1 * 0.5F + this.getTempUnitC(this.mContext);
      } else {
         var2 = "";
      }

      return var2;
   }

   private int resolveSpeedData(int var1, int var2) {
      return var1 * 256 + var2;
   }

   private void set0x01WheelKeyData(Context var1) {
      int[] var4 = this.mCanbusInfoInt;
      short var2 = 2;
      int var3 = var4[2];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 3) {
               if (var3 != 4) {
                  if (var3 != 7) {
                     label29:
                     switch (var3) {
                        case 20:
                           var2 = 14;
                           break;
                        case 21:
                           var2 = 15;
                           break;
                        default:
                           switch (var3) {
                              case 32:
                                 break;
                              case 33:
                                 var2 = 49;
                                 break label29;
                              case 34:
                                 var2 = 68;
                                 break label29;
                              case 35:
                                 var2 = 59;
                                 break label29;
                              case 36:
                                 var2 = 128;
                                 break label29;
                              case 37:
                                 var2 = 129;
                                 break label29;
                              default:
                                 var2 = 0;
                                 break label29;
                           }
                        case 19:
                           var2 = 3;
                     }
                  }
               } else {
                  var2 = 45;
               }
            } else {
               var2 = 46;
            }
         } else {
            var2 = 8;
         }
      } else {
         var2 = 7;
      }

      this.realKeyLongClick1(var1, var2, var4[3]);
   }

   private void set0x02AirInfo(Context var1) {
      GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
      GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
      GeneralAirData.eco = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
      GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
      GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
      GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
      GeneralAirData.dual = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
      GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
      GeneralAirData.amb = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
      GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
      GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
      GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[3]);
      GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
      GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
      GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
      GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
      GeneralAirData.front_left_temperature = this.resolveLeftAndRightAutoTemp(this.mCanbusInfoInt[4]);
      GeneralAirData.front_right_temperature = this.resolveLeftAndRightAutoTemp(this.mCanbusInfoInt[5]);
      int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 7);
      String var3;
      if (var2 >= 0 && var2 <= 85) {
         var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 7) + this.getTempUnitC(var1);
      } else {
         var3 = "";
      }

      String var4 = var3;
      if (DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6])) {
         var4 = "-" + var3;
      }

      this.updateOutDoorTemp(var1, var4);
      this.updateAirActivity(this.mContext, 1001);
   }

   private void set0x65DriverData() {
      ArrayList var1 = new ArrayList();
      StringBuilder var3 = new StringBuilder();
      int[] var2 = this.mCanbusInfoInt;
      var1.add(new DriverUpdateEntity(0, 0, var3.append(this.resolveSpeedData(var2[2], var2[3])).append(" RPM").toString()));
      var3 = new StringBuilder();
      var2 = this.mCanbusInfoInt;
      var1.add(new DriverUpdateEntity(0, 1, var3.append(this.resolveSpeedData(var2[4], var2[5])).append(" Km/h").toString()));
      this.updateGeneralDriveData(var1);
      this.updateDriveDataActivity((Bundle)null);
      int[] var4 = this.mCanbusInfoInt;
      this.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(var4[4], var4[5]));
   }

   private void set0x7fVersionData() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanbusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      this.mDecimalFormat00 = new DecimalFormat("00");
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = "ATV         ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = "AUX         ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      byte[] var1 = "BTMUSIC     ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      var1 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      var1 = DataHandleUtils.makeBytesFixedLength(var1, 12, 32);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      var1 = DataHandleUtils.makeBytesFixedLength(var1, 12, 32);
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanbusInfoInt = this.getByteArrayToIntArray(var2);
      this.mCanbusInfoByte = var2;
      byte var3 = var2[1];
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 101) {
               if (var3 != 127) {
                  return;
               }
            } else {
               this.set0x65DriverData();
            }

            this.set0x7fVersionData();
         } else {
            this.set0x02AirInfo(var1);
         }
      } else {
         this.set0x01WheelKeyData(var1);
      }

   }

   public boolean customLongClick(Context var1, int var2) {
      if (var2 != 3) {
         return false;
      } else {
         this.realKeyClick(var1, 134);
         return true;
      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(new byte[]{22, -1, 127, 0});
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = "DTV         ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var1));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
   }

   public void musicDestroy() {
      super.musicDestroy();
      int var1 = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, false);
      this.m0x82Data0 = var1;
      var1 = DataHandleUtils.setIntByteWithBit(var1, 3, false);
      this.m0x82Data0 = var1;
      var1 = DataHandleUtils.setIntByteWithBit(var1, 4, false);
      this.m0x82Data0 = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1, 0});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var3 = DataHandleUtils.rangeNumber(var9 << 8 | var3, 99);
      var4 = DataHandleUtils.rangeNumber(var4, 99);
      byte[] var25 = ("MUSIC" + "  " + this.mDecimalFormat00.format((long)var3) + "/" + this.mDecimalFormat00.format((long)var4)).getBytes();
      var25 = DataHandleUtils.byteMerger(new byte[]{22, -125}, var25);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var25);
      var3 = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, true);
      this.m0x82Data0 = var3;
      if (var16 != 1) {
         var18 = true;
      } else {
         var18 = false;
      }

      var3 = DataHandleUtils.setIntByteWithBit(var3, 3, var18);
      this.m0x82Data0 = var3;
      if (var16 == 1) {
         var18 = true;
      } else {
         var18 = false;
      }

      var3 = DataHandleUtils.setIntByteWithBit(var3, 4, var18);
      this.m0x82Data0 = var3;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var3, 0});
   }

   public void radioDestroy() {
      super.radioDestroy();
      int var1 = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 5, false);
      this.m0x82Data0 = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1, 0});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      Log.i("cbc", "radioInfoChange: " + var5);
      var4 = var3;
      if (var2.contains("FM")) {
         var4 = Integer.toString((int)(Float.parseFloat(var3) * 10.0F));
      }

      var1 = var2.length() + var4.length();

      for(var3 = var2; var1 < 12; ++var1) {
         var3 = var3 + " ";
      }

      var3 = var3 + var4;
      Log.i("_316_MsgMgr", "radioInfoChange: \".." + var3 + "\"");
      byte[] var7 = var3.getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -125}, var7);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
      var1 = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 5, var2.contains("FM"));
      this.m0x82Data0 = var1;
      boolean var6;
      if (var5 == 1) {
         var6 = true;
      } else {
         var6 = false;
      }

      var1 = DataHandleUtils.setIntByteWithBit(var1, 2, var6);
      this.m0x82Data0 = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1, 0});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      byte[] var2 = "            ".getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -125}, var2));
   }

   public void videoDestroy() {
      super.videoDestroy();
      int var1 = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, false);
      this.m0x82Data0 = var1;
      var1 = DataHandleUtils.setIntByteWithBit(var1, 3, false);
      this.m0x82Data0 = var1;
      var1 = DataHandleUtils.setIntByteWithBit(var1, 4, false);
      this.m0x82Data0 = var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1, 0});
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var3 = DataHandleUtils.rangeNumber(var9 << 8 | var3, 99);
      var4 = DataHandleUtils.rangeNumber(var4, 99);
      byte[] var18 = ("VIDEO" + "  " + this.mDecimalFormat00.format((long)var3) + "/" + this.mDecimalFormat00.format((long)var4)).getBytes();
      var18 = DataHandleUtils.byteMerger(new byte[]{22, -125}, var18);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var18);
      var3 = DataHandleUtils.setIntByteWithBit(this.m0x82Data0, 1, true);
      this.m0x82Data0 = var3;
      if (var15 != 1) {
         var16 = true;
      } else {
         var16 = false;
      }

      var3 = DataHandleUtils.setIntByteWithBit(var3, 3, var16);
      this.m0x82Data0 = var3;
      if (var15 == 1) {
         var16 = true;
      } else {
         var16 = false;
      }

      var3 = DataHandleUtils.setIntByteWithBit(var3, 4, var16);
      this.m0x82Data0 = var3;
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var3, 0});
   }
}
