package com.hzbhd.canbus.car._60;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private DecimalFormat mDecimalFormat00;
   private String mMediaId;
   private byte[] mMediaInfo;
   private TimerUtil mMeidaInfoTimer;
   private TimerUtil mPhoneInfoTimer;
   private int mWheelKey;

   private int[] getTime(int var1) {
      int var2 = var1 / 60;
      return new int[]{var2 / 60, var2 % 60, var1 % 60};
   }

   private void sendMediaInfo(String var1, String var2) {
      this.mMediaId = var1;
      byte[] var3 = DataHandleUtils.makeBytesFixedLength(var2.getBytes(), 12, 32);
      System.arraycopy(var3, 0, this.mMediaInfo, 2, var3.length);
   }

   private void set0x01Wheelkey(Context var1) {
      int var2 = this.mWheelKey;
      int var3 = this.mCanBusInfoInt[4];
      if (var2 != var3) {
         this.mWheelKey = var3;
         switch (var3) {
            case 0:
               this.realKeyLongClick2(var1, 0);
               break;
            case 1:
               this.realKeyLongClick2(var1, 7);
               break;
            case 2:
               this.realKeyLongClick2(var1, 8);
               break;
            case 3:
               this.realKeyLongClick2(var1, 14);
               break;
            case 4:
               this.realKeyLongClick2(var1, 3);
               break;
            case 5:
               this.realKeyLongClick2(var1, 45);
               break;
            case 6:
               this.realKeyLongClick2(var1, 46);
               break;
            case 7:
               this.realKeyLongClick2(var1, 2);
               break;
            case 8:
               this.realKeyLongClick2(var1, 48);
               break;
            case 9:
               this.realKeyLongClick2(var1, 47);
               break;
            case 10:
               this.realKeyLongClick2(var1, 49);
         }

      }
   }

   private void setDriveData() {
      int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
      String var2;
      if (var1 == 0) {
         var2 = this.mContext.getResources().getString(2131769315);
      } else if (var1 == 255) {
         var2 = this.mContext.getResources().getString(2131769174);
      } else {
         var2 = var1 + "";
      }

      Log.d("cwh", "str = " + var2);
      ArrayList var3 = new ArrayList();
      var3.add(new DriverUpdateEntity(0, 0, var2));
      this.updateGeneralDriveData(var3);
      this.updateDriveDataActivity((Bundle)null);
   }

   private void startPhoneInfoTimer(byte[] var1) {
      byte[] var2 = new byte[14];
      var2[0] = 22;
      var2[1] = 0;
      var1 = DataHandleUtils.makeBytesFixedLength(var1, 12, 32);
      System.arraycopy(var1, 0, var2, 2, var1.length);
      this.mPhoneInfoTimer.startTimer(new TimerTask(this, var2) {
         final MsgMgr this$0;
         final byte[] val$bytes;

         {
            this.this$0 = var1;
            this.val$bytes = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(this.val$bytes);
         }
      }, 0L, 300L);
   }

   private void stopPhoneInfoTimer() {
      this.mPhoneInfoTimer.stopTimer();
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      String var2 = SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name();
      this.mMediaId = var2;
      byte[] var3 = new byte[14];
      this.mMediaInfo = var3;
      var3[0] = 22;
      var3[1] = 0;
      this.sendMediaMsg(var1, var2, var3);
      TimerUtil var4 = new TimerUtil();
      this.mMeidaInfoTimer = var4;
      var4.startTimer(new TimerTask(this, var1) {
         final MsgMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void run() {
            MsgMgr var1 = this.this$0;
            var1.sendMediaMsg(this.val$context, var1.mMediaId, this.this$0.mMediaInfo);
         }
      }, 300L, 300L);
      this.mPhoneInfoTimer = new TimerUtil();
      this.mDecimalFormat00 = new DecimalFormat("00");
   }

   public void atvDestdroy() {
      super.atvDestdroy();
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.ATV.name(), "ATV");
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.AUX1.name(), "AUX");
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), "BT MUSIC");
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      this.stopPhoneInfoTimer();
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      this.startPhoneInfoTimer(var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      this.startPhoneInfoTimer(var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      this.mContext = var1;
      if (var3[1] == 0) {
         this.set0x01Wheelkey(var1);
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      int[] var14 = this.getTime(var2);
      var11 = "DISC " + this.mDecimalFormat00.format((long)var14[1]) + ":" + this.mDecimalFormat00.format((long)var14[2]);
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.MPEG.name(), var11);
   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.DTV.name(), "DTV");
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var11 = "MUSIC " + var12 + ":" + var13;
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.MUSIC.name(), var11);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var2 = var2 + " " + var3;
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.FM.name(), var2);
   }

   public void sourceSwitchChange(String var1) {
      super.sourceSwitchChange(var1);
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), "NO SOURCE");
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      StringBuilder var3 = (new StringBuilder()).append("isPowerOff   ");
      String var2;
      if (var1) {
         var2 = "关机";
      } else {
         var2 = "开机";
      }

      Log.d("cwh", var3.append(var2).toString());
      if (var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 4, 1});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 4, 0});
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var8 = "VIDEO " + var12 + ":" + var13;
      this.sendMediaInfo(SourceConstantsDef.SOURCE_ID.VIDEO.name(), var8);
   }
}
