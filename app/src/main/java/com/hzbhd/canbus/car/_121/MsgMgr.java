package com.hzbhd.canbus.car._121;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.TimerTask;

public class MsgMgr extends AbstractMsgMgr {
   private final String TAG = "_1121_MsgMgr";
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;

   private byte getAllBandTypeData(String var1, byte var2, byte var3, byte var4, byte var5, byte var6) {
      var1.hashCode();
      int var8 = var1.hashCode();
      byte var7 = -1;
      switch (var8) {
         case 2443:
            if (var1.equals("LW")) {
               var7 = 0;
            }
            break;
         case 2474:
            if (var1.equals("MW")) {
               var7 = 1;
            }
            break;
         case 64901:
            if (var1.equals("AM1")) {
               var7 = 2;
            }
            break;
         case 64902:
            if (var1.equals("AM2")) {
               var7 = 3;
            }
            break;
         case 69706:
            if (var1.equals("FM1")) {
               var7 = 4;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var7 = 5;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var7 = 6;
            }
            break;
         case 2426268:
            if (var1.equals("OIRT")) {
               var7 = 7;
            }
      }

      switch (var7) {
         case 0:
         case 2:
            return var5;
         case 1:
         case 3:
            return var6;
         case 4:
            return var2;
         case 5:
            return var3;
         case 6:
         case 7:
            return var4;
         default:
            return 0;
      }
   }

   private void realKeyClick2(Context var1, int var2) {
      this.realKeyLongClick1(var1, var2, this.mCanBusInfoInt[5]);
   }

   private void set0x20CarBase(Context var1) {
      switch (this.mCanBusInfoInt[4]) {
         case 0:
            this.realKeyClick2(this.mContext, 0);
            break;
         case 1:
            this.realKeyClick2(this.mContext, 7);
            break;
         case 2:
            this.realKeyClick2(this.mContext, 8);
            break;
         case 3:
            this.realKeyClick2(this.mContext, 3);
         case 4:
         case 7:
         default:
            break;
         case 5:
            this.realKeyClick2(this.mContext, 14);
            break;
         case 6:
            this.realKeyClick2(this.mContext, 15);
            break;
         case 8:
            this.realKeyClick2(this.mContext, 45);
            break;
         case 9:
            this.realKeyClick2(this.mContext, 46);
            break;
         case 10:
            this.realKeyClick2(this.mContext, 2);
      }

   }

   private void set0xf0VersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      this.mContext = var1;
      (new TimerUtil()).startTimer(new TimerTask(this) {
         final MsgMgr this$0;
         int time;
         int track;

         {
            this.this$0 = var1;
            this.time = 0;
            this.track = 0;
         }

         public void run() {
            this.this$0.discInfoChange((byte)0, this.time, 0, 0, this.track, 0, 0, true, true, 0, "", "", "");
            this.time += 3600;
            ++this.track;
         }
      }, 0L, 1000L);
   }

   public void atvInfoChange() {
      super.atvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -46, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 == 240) {
            this.set0xf0VersionInfo();
         }
      } else {
         this.set0x20CarBase(var1);
      }

   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      if (var7 != 0 && var7 != 15) {
         var1 = (byte)DataHandleUtils.rangeNumber(var2 / 3600, 9);
         byte var14 = (byte)(var2 / 60 % 60);
         byte var15 = (byte)(var2 % 60);
         if (var7 == 1 || var7 == 5) {
            var4 = var5;
         }

         var11 = String.format("%1d%02d%02d%04d   ", var1, var14, var15, var4);
         byte var16 = 6;
         if (var7 == 1) {
            var16 = 7;
         } else if (var7 == 5) {
            var16 = 24;
         } else if (var7 != 6) {
            var16 = 0;
         }

         var1 = (byte)var16;
         byte[] var17 = var11.getBytes();
         var17 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var17);
         this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), var17);
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      byte var25;
      if (var1 == 9) {
         var25 = 14;
      } else {
         var25 = 13;
      }

      var11 = String.format("     %04d   ", var9 << 8 | var3);
      var1 = (byte)var25;
      byte[] var26 = var11.getBytes();
      var26 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var26);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), var26);
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var6 = this.getAllBandTypeData(var2, (byte)1, (byte)2, (byte)3, (byte)4, (byte)5);
      if (this.isBandAm(var2)) {
         for(var5 = var3.length(); var5 < 5; ++var5) {
            var3 = " " + var3;
         }

         var2 = var3 + "KHZ";
      } else {
         for(var5 = var3.length(); var5 < 6; ++var5) {
            var3 = " " + var3;
         }

         var2 = var3 + "MHZ";
      }

      var3 = var2;
      if (var1 >= 1) {
         var3 = var2;
         if (var1 <= 6) {
            var3 = var2 + var1;
         }
      }

      for(var1 = var3.length(); var1 < 12; ++var1) {
         var3 = var3 + " ";
      }

      byte[] var7 = var3.getBytes();
      var7 = DataHandleUtils.byteMerger(new byte[]{22, -46, var6}, var7);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), var7);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      byte var18;
      if (var1 == 9) {
         var18 = 14;
      } else {
         var18 = 22;
      }

      var8 = String.format("     %04d   ", var9 << 8 | var3);
      var1 = (byte)var18;
      byte[] var19 = var8.getBytes();
      var19 = DataHandleUtils.byteMerger(new byte[]{22, -46, var1}, var19);
      this.sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), var19);
   }
}
