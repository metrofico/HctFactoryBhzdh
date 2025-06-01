package com.hzbhd.canbus.car._389;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;

public class MsgMgr extends AbstractMsgMgr {
   int differentId;
   byte[] mCanBusInfoByte;
   int[] mCanBusInfoInt;
   Context mContext;
   private UiMgr uiMgr;

   private int getBandData(String var1) {
      if ("FM1".equals(var1)) {
         return 1;
      } else if ("FM2".equals(var1)) {
         return 2;
      } else if ("FM3".equals(var1)) {
         return 3;
      } else if ("AM1".equals(var1)) {
         return 4;
      } else {
         return "AM2".equals(var1) ? 5 : 0;
      }
   }

   private UiMgr getUiMgr(Context var1) {
      UiMgr var2 = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      this.uiMgr = var2;
      return var2;
   }

   private void realKeyClick0x11(int var1) {
      this.realKeyLongClick1(this.mContext, var1, this.mCanBusInfoInt[5]);
   }

   private void setVersionInfo0xF0() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWheelKey0x11() {
      int var1 = this.mCanBusInfoInt[4];
      if (var1 != 8) {
         if (var1 != 9) {
            if (var1 != 80) {
               switch (var1) {
                  case 0:
                     this.realKeyClick0x11(0);
                     break;
                  case 1:
                     this.realKeyClick0x11(7);
                     break;
                  case 2:
                     this.realKeyClick0x11(8);
                     break;
                  case 3:
                     this.realKeyClick0x11(3);
                     break;
                  case 4:
                     this.realKeyClick0x11(187);
                     break;
                  case 5:
                     this.realKeyClick0x11(14);
                     break;
                  case 6:
                     this.realKeyClick0x11(15);
                     break;
                  default:
                     switch (var1) {
                        case 12:
                           this.realKeyClick0x11(2);
                           break;
                        case 13:
                           this.realKeyClick0x11(45);
                           break;
                        case 14:
                           this.realKeyClick0x11(46);
                           break;
                        case 15:
                           this.realKeyClick0x11(49);
                           break;
                        case 16:
                           this.realKeyClick0x11(50);
                     }
               }
            } else {
               this.realKeyClick0x11(1);
            }
         } else {
            this.realKeyClick0x11(20);
         }
      } else {
         this.realKeyClick0x11(21);
      }

   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 1, 12}, 34));
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 1, 11}, 34));
   }

   public void btPhoneHangUpInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneHangUpInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -105, 9});
      this.getUiMgr(this.mContext).sendMediaInfo0x93(1, var1);
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -105, 7});
      this.getUiMgr(this.mContext).sendMediaInfo0x93(1, var1);
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -105, 6});
      this.getUiMgr(this.mContext).sendMediaInfo0x93(1, var1);
   }

   public void btPhoneTalkingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneTalkingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -105, 8});
      this.getUiMgr(this.mContext).sendMediaInfo0x93(1, var1);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mContext = var1;
      int[] var4 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var4;
      int var3 = var4[1];
      if (var3 != 17) {
         if (var3 == 240) {
            this.setVersionInfo0xF0();
         }
      } else {
         this.setWheelKey0x11();
         this.setBacklightLevel(this.mCanBusInfoInt[7] / 20 + 1);
      }

   }

   public void dateTimeRepCanbus(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, boolean var10, boolean var11, boolean var12, int var13) {
      super.dateTimeRepCanbus(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 0, (byte)var5, (byte)var6, 0, 0, 1, (byte)var2, (byte)var3, (byte)var4, (byte)var9}, 12));
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

      byte var27 = 0;
      var11 = String.format("     %04d   ", var9 << 8 | var3);
      var1 = (byte)var25;
      byte[] var26 = var11.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 1, var1}, var26), 34));
      var11 = var5 + ":" + var12 + ":" + var13;
      var4 = var11.length();

      for(var3 = var27; var3 < 101 - var4; ++var3) {
         var11 = var11 + "";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(1, var11.getBytes());
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      if (var2.equals("FM1")) {
         var3 = var3 + "MHz";
      } else if (var2.equals("FM2")) {
         var3 = var3 + "MHz";
      } else if (var2.equals("FM3")) {
         var3 = var3 + "MHz";
      } else if (var2.equals("AM1")) {
         var3 = var3 + "KHz";
      } else if (var2.equals("AM2")) {
         var3 = var3 + "KHz";
      } else {
         var3 = "";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(1, var3.getBytes());
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 1, (byte)this.getBandData(var2)}, 34));
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -111, 0}, 32));
   }

   public void updateSettings(int var1, int var2, int var3) {
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
         var18 = 13;
      }

      byte var20 = 0;
      var8 = String.format("     %04d   ", var9 << 8 | var3);
      var1 = (byte)var18;
      byte[] var19 = var8.getBytes();
      CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 1, var1}, var19), 34));
      var8 = var5 + ":" + var12 + ":" + var13;
      var4 = var8.length();

      for(var3 = var20; var3 < 101 - var4; ++var3) {
         var8 = var8 + "";
      }

      this.getUiMgr(this.mContext).sendMediaInfo0x91(1, var8.getBytes());
   }
}
