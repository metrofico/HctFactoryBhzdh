package com.hzbhd.canbus.car._28;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   static final int AMPLIFIER_HALF_MAX = 10;
   private static final int AMPLIFIER_MAX = 20;
   static final int AMPLIFIER_OFFSET = 6;
   private final int DATA_TYPE = 1;
   private final int MEDIA_DATA_LENGTH = 15;
   private final int SEND_GIVEN_MEDIA_MESSAGE = 1;
   private final int SEND_NORMAL_MESSAGE = 2;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private int mCanId;
   private SparseArray mCanbusDataArray;
   private Context mContext;
   private DecimalFormat mDecimalFormat00;
   private DecimalFormat mDecimalFormat0000;
   private Handler mHandler;
   private boolean mIsFirstAirData = true;

   private int getBand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case 64901:
            if (var1.equals("AM1")) {
               var2 = 0;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var2 = 1;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 2;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 3;
            }
      }

      switch (var2) {
         case 0:
            return 4;
         case 1:
            return 5;
         case 2:
            return 2;
         case 3:
            return 3;
         default:
            return 1;
      }
   }

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private void initAmplifierData(Context var1) {
      ArrayList var3 = new ArrayList();
      int var2 = 0;
      if (var1 != null) {
         this.getAmplifierData(var1, this.mCanId, UiMgrFactory.getCanUiMgr(var1).getAmplifierPageUiSet(var1));
         var3.add(new byte[]{22, -83, 7, (byte)SharePreUtil.getIntValue(var1, "_28_amplifier_mute", 0)});
      }

      var3.add(new byte[]{22, -83, 1, (byte)GeneralAmplifierData.volume});

      while(var2 < var3.size()) {
         this.sendNormalMessage(var3.get(var2), (long)(var2 * 80));
         ++var2;
      }

   }

   private void initData(Context var1) {
      this.mCanId = this.getCurrentEachCanId();
      this.mCanbusDataArray = new SparseArray();
      this.mDecimalFormat00 = new DecimalFormat("00");
      this.mDecimalFormat0000 = new DecimalFormat("0000");
      this.mHandler = new Handler(this, Looper.getMainLooper(), var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var3;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 1) {
               this.this$0.sendMediaMsg(this.val$context, SourceConstantsDef.SOURCE_ID.values()[var1.arg1].name(), (byte[])var1.obj);
            } else if (var1.what == 2) {
               CanbusMsgSender.sendMsg((byte[])var1.obj);
            }

         }
      };
   }

   private boolean isBlowModeMatch(int var1, int... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1 == var2[var3]) {
            return true;
         }
      }

      return false;
   }

   private String resolveAirTemperature(Context var1, int var2) {
      if (var2 == 254) {
         return "LOW";
      } else {
         return var2 == 255 ? "HIGH" : (float)var2 / 2.0F + this.getTempUnitC(var1);
      }
   }

   private String resolveOutDoorTemperature(Context var1, int var2) {
      return (float)var2 / 2.0F - 40.0F + this.getTempUnitC(var1);
   }

   private void sendMediaMessage(int var1, Object var2) {
      this.sendMediaMessage(var1, var2, 0L);
   }

   private void sendMediaMessage(int var1, Object var2, long var3) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var5 = Message.obtain();
         var5.what = 1;
         var5.arg1 = var1;
         var5.obj = var2;
         this.mHandler.sendMessageDelayed(var5, var3);
      }
   }

   private void sendNormalMessage(Object var1) {
      this.sendNormalMessage(var1, 0L);
   }

   private void sendNormalMessage(Object var1, long var2) {
      if (this.mHandler == null) {
         Log.i("ljq", "sendMediaMessage: mHandler is null");
      } else {
         Message var4 = Message.obtain();
         var4.what = 2;
         var4.obj = var1;
         this.mHandler.sendMessageDelayed(var4, var2);
      }
   }

   private void setAirData0x31(Context var1) {
      this.updateOutDoorTemp(var1, this.resolveOutDoorTemperature(var1, this.mCanBusInfoInt[13]));
      byte[] var2 = this.mCanBusInfoByte;
      var2[13] = 0;
      if (!this.isAirMsgRepeat(var2)) {
         if (this.mIsFirstAirData) {
            this.mIsFirstAirData = false;
         } else {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = this.isBlowModeMatch(this.mCanBusInfoInt[6], 2, 12);
            GeneralAirData.front_left_blow_foot = this.isBlowModeMatch(this.mCanBusInfoInt[6], 3, 5, 12);
            GeneralAirData.front_left_blow_head = this.isBlowModeMatch(this.mCanBusInfoInt[6], 5, 6);
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = this.resolveAirTemperature(var1, this.mCanBusInfoInt[9]);
            this.updateAirActivity(var1, 1001);
         }
      }
   }

   private void setAmplifierData0xA6(Context var1) {
      GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
      GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 6 - 10;
      GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 6 - 10;
      GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 6;
      GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 6;
      GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 6;
      this.saveAmplifierData(var1, this.mCanId);
      this.updateAmplifierActivity((Bundle)null);
   }

   private void setVersionInfo0xF0(Context var1) {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         this.updateVersionInfo(var1, this.getVersionStr(this.mCanBusInfoByte));
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      byte[] var1 = DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)8}, 15, 32);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)12}, 15, 32);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), var1);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      super.auxInInfoChange();
      byte[] var1 = DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)21}, 15, 32);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)10}, 15, 32));
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)10}, 15, 32));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 49) {
         if (var3 != 166) {
            if (var3 == 240) {
               this.setVersionInfo0xF0(var1);
            }
         } else {
            this.setAmplifierData0xA6(var1);
         }
      } else {
         this.setAirData0x31(var1);
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte var15;
      if (var7 == 1) {
         var15 = 7;
         var4 = var5;
      } else {
         var15 = 6;
      }

      int[] var17 = this.getTime(var2);
      var12 = Integer.toString(DataHandleUtils.rangeNumber(var17[0], 0, 9));
      var11 = this.mDecimalFormat00.format((long)var17[1]);
      var13 = this.mDecimalFormat00.format((long)var17[2]);
      String var14 = this.mDecimalFormat0000.format((long)var4);
      var11 = var12 + var11 + var13 + var14 + "   ";
      var1 = (byte)var15;
      byte[] var16 = var11.getBytes();
      var16 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var16);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), var16);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      byte[] var1 = DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)8}, 15, 32);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), var1);
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.initData(var1);
      this.initAmplifierData(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 14;
      } else {
         var25 = 13;
      }

      var11 = "     " + this.mDecimalFormat0000.format((long)(var9 * 256 + var3));
      var11 = var11 + "   ";
      var1 = (byte)var25;
      byte[] var26 = var11.getBytes();
      var26 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var26);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = (byte)this.getBand(var2);
      var4 = var3;
      if (var2.contains("FM")) {
         var4 = var3;
         if (var3.length() == 5) {
            var4 = " " + var3;
         }

         var3 = var4 + "MHz";
         var4 = var3 + var1;
      }

      var3 = var4;
      if (var2.contains("AM")) {
         var3 = " " + var4;
         var2 = var3;
         if (var3.length() == 4) {
            var2 = " " + var3;
         }

         var2 = var2 + "KHz";
         var2 = var2 + " ";
         var3 = var2 + var1;
      }

      byte[] var7 = var3.getBytes();
      var7 = DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -46, var6}, var7), 15, 32);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), var7);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(var1)) {
         this.sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte)0}, 15, 32));
      }

   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (!var1) {
         this.initAmplifierData(this.mContext);
      }

   }

   void updateSetting(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 14;
      } else {
         var18 = 22;
      }

      var8 = var5 + var12 + var13;
      var8 = var8 + this.mDecimalFormat0000.format((long)(var9 * 256 + var3));
      var8 = var8 + "   ";
      var1 = (byte)var18;
      byte[] var19 = var8.getBytes();
      var19 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var19);
      this.sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), var19);
   }
}
