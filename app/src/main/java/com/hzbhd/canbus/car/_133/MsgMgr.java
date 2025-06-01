package com.hzbhd.canbus.car._133;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;

public class MsgMgr extends AbstractMsgMgr {
   private int FreqInt;
   private byte freqHi;
   private byte freqLo;
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

   private void getFreqByteHiLo(String var1, String var2) {
      if (!var1.equals("AM2") && !var1.equals("MW") && !var1.equals("AM1") && !var1.equals("LW")) {
         if (var1.equals("FM1") || var1.equals("FM2") || var1.equals("FM3") || var1.equals("OIRT")) {
            int var3 = (int)(Double.parseDouble(var2) * 100.0);
            this.FreqInt = var3;
            this.freqHi = (byte)(var3 >> 8);
            this.freqLo = (byte)(var3 & 255);
         }
      } else {
         this.freqHi = (byte)(Integer.parseInt(var2) >> 8);
         this.freqLo = (byte)(Integer.parseInt(var2) & 255);
      }

   }

   private void setWheelKeyInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[2];
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 6) {
                        if (var1 == 7) {
                           this.realKeyLongClick1(this.mContext, 2, var2[3]);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 3, var2[3]);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 46, var2[3]);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 45, var2[3]);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 8, var2[3]);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 7, var2[3]);
         }
      } else {
         this.realKeyLongClick1(this.mContext, 0, var2[3]);
      }

   }

   public void atvInfoChange() {
      super.atvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 0, 0, 0, 0});
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, 0, 0, 0, 0});
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 0, 0, 0, 0});
   }

   public void btPhoneIncomingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneIncomingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, 0, 0, 0, 0});
   }

   public void btPhoneOutGoingInfoChange(byte[] var1, boolean var2, boolean var3) {
      super.btPhoneOutGoingInfoChange(var1, var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, 0, 0, 0, 0});
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      if (var3[1] == 1) {
         this.setWheelKeyInfo();
      }

   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      super.currentVolumeInfoChange(var1, var2);
      short var3;
      if (var2) {
         var3 = 128;
      } else {
         var3 = 0;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -124, 13, (byte)(var3 + (var1 & 127)), 0, 0, 0});
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      var3 = var5 + 1;
      byte var18 = (byte)(var3 >> 8 & 255);
      byte var19 = (byte)(var2 % 60);
      var1 = (byte)(var3 & 255);
      byte var14 = (byte)(var2 / 60 % 60);
      boolean var16;
      if (var7 == 1) {
         var16 = true;
      } else {
         var16 = false;
      }

      byte var17 = var18;
      if (var18 > 3) {
         var17 = 3;
      }

      byte var15 = (byte)(var17 << 6 | var19);
      if (var16) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, 3, var1, var15, var14});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, 4, var1, var15, var14});
      }

   }

   public void dtvInfoChange() {
      super.dtvInfoChange();
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 0, 0, 0, 0});
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      var5 = (byte)(var9 << 6 | var7);
      var2 = 9;
      if (var1 == 9) {
         var1 = var2;
      } else {
         var1 = 8;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -124, var1, 0, (byte)var3, var5, var6});
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      var5 = FutureUtil.instance.getStereoStatus();
      byte var6 = (byte)(var1 << 3 | this.getAllBandTypeData(var2, (byte)0, (byte)1, (byte)2, (byte)3, (byte)4));
      this.getFreqByteHiLo(var2, var3);
      CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte)var5, var6, this.freqHi, this.freqLo});
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      if (var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -124, 0, 0, 0, 0, 0});
      }

   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      var5 = (byte)(var9 << 6 | var7);
      var2 = 9;
      if (var1 == 9) {
         var1 = var2;
      } else {
         var1 = 8;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -124, var1, 0, (byte)var3, var5, var6});
   }
}
