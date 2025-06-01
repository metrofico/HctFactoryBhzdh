package com.hzbhd.canbus.car._247;

import android.content.Context;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import java.text.DecimalFormat;

public class MsgMgr extends AbstractMsgMgr {
   private static byte[] mCdInfo = new byte[]{22, -125, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private Context mContext;
   private boolean mPreMuteStatus;

   private byte getAllBandTypeData(String var1) {
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
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
            return 17;
         case 1:
            return 18;
         case 2:
            return 33;
         case 3:
            return 34;
         case 4:
            return 35;
         default:
            return 0;
      }
   }

   private byte getBandUnit(String var1) {
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
         case 69706:
            if (var1.equals("FM1")) {
               var2 = 2;
            }
            break;
         case 69707:
            if (var1.equals("FM2")) {
               var2 = 3;
            }
            break;
         case 69708:
            if (var1.equals("FM3")) {
               var2 = 4;
            }
      }

      switch (var2) {
         case 0:
         case 1:
            return 32;
         case 2:
         case 3:
         case 4:
            return 16;
         default:
            return 0;
      }
   }

   private String getMinute(int var1) {
      var1 /= 60;
      return (new DecimalFormat("00")).format((long)(var1 % 60));
   }

   private String getSecond(int var1) {
      return (new DecimalFormat("00")).format((long)(var1 % 60));
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

   private void sendContentStr(String var1) {
      this.setMediaInfo07(var1.getBytes());
      byte[] var2 = mCdInfo;
      var2[12] = 0;
      var2[15] = 0;
      CanbusMsgSender.sendMsg(var2);
   }

   private void setMediaInfo07(byte[] var1) {
      if (var1.length == 8) {
         for(int var2 = 0; var2 < 8; ++var2) {
            mCdInfo[var2 + 2] = var1[var2];
         }
      }

   }

   private void setVersionInfo() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      byte[] var1 = mCdInfo;
      var1[12] = 0;
      var1[15] = 0;
      this.sendContentStr("  AUX   ");
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      this.mContext = var1;
      if (var3[1] == 255) {
         this.setVersionInfo();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      if (this.mPreMuteStatus != var2) {
         this.mPreMuteStatus = var2;
         if (var2) {
            this.sendContentStr("   MUTE   ");
         } else {
            try {
               String var3 = System.getString(this.mContext.getContentResolver(), "reportAfterHangUp");
               if (!TextUtils.isEmpty(var3)) {
                  CanbusMsgSender.sendMsg(Base64.decode(var3, 0));
               }
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }

      }
   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var14 = mCdInfo;
      var14[10] = (byte)var6;
      var14[11] = (byte)var5;
      CanbusMsgSender.sendMsg(var14);
   }

   public void destroyCommand() {
      super.destroyCommand();
      this.sendContentStr(" WELCOME");
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      byte[] var14 = mCdInfo;
      var14[12] = 0;
      var14[15] = 0;
      this.sendContentStr("  DISC  ");
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.mContext = var1;
      (new Thread(this) {
         final MsgMgr this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            try {
               CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
               sleep(100L);
               CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
               sleep(200L);
               this.this$0.sendContentStr(" WELCOME");
               System.putString(this.this$0.mContext.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(MsgMgr.mCdInfo, 0));
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      }).start();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 == 9) {
         mCdInfo[12] = 64;
      } else {
         mCdInfo[12] = -128;
      }

      mCdInfo[15] = 0;
      this.sendContentStr(" MUSIC  ");
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      mCdInfo[12] = this.getAllBandTypeData(var2);
      mCdInfo[15] = this.getBandUnit(var2);
      this.sendContentStr(" RADIO  ");
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.sendContentStr("  OFF   ");
      System.putString(this.mContext.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(mCdInfo, 0));
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 == 9) {
         mCdInfo[12] = 64;
      } else {
         mCdInfo[12] = -128;
      }

      mCdInfo[15] = 0;
      this.sendContentStr(" VIDEO  ");
   }
}
