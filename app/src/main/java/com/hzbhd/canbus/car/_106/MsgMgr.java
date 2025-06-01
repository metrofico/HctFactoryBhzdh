package com.hzbhd.canbus.car._106;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import java.nio.charset.StandardCharsets;

public class MsgMgr extends AbstractMsgMgr {
   private Context mContext;
   private UiMgr mUiMgr;

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
   }

   public void auxInInfoChange() {
      super.auxInInfoChange();
      this.getUiMgr(this.mContext).send0xC0MediaInfo(7, 0, 0, 0, 0, 0);
   }

   public void btMusicInfoChange() {
      super.btMusicInfoChange();
      this.getUiMgr(this.mContext).send0xC0MediaInfo(9, 0, 0, 0, 0, 0);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
   }

   public void currentVolumeInfoChange(int var1, boolean var2) {
      super.currentVolumeInfoChange(var1, var2);
      this.getUiMgr(this.mContext).send0x86VolControl(var1);
   }

   public void discInfoChange(byte var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, String var11, String var12, String var13) {
      super.discInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
      this.getUiMgr(this.mContext).send0xC0MediaInfo(2, 5, this.getMsb(var4), this.getLsb(var4), this.getMsb(var6), this.getLsb(var6));
      byte[] var14 = var11.getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xC1Info(this.SplicingByte(new byte[]{22, -63, 1}, var14));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).makeConnection();
   }

   public void musicInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, byte var8, byte var9, byte var10, String var11, String var12, String var13, long var14, byte var16, int var17, boolean var18, long var19, String var21, String var22, String var23, boolean var24) {
      super.musicInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var16, var17, var18, var19, var21, var22, var23, var24);
      if (var1 != 9) {
         this.getUiMgr(this.mContext).send0xC0MediaInfo(8, 4, this.getMsb(var3), this.getLsb(var3), this.getMsb(var4), this.getLsb(var4));
      }

      byte[] var25 = var21.getBytes(StandardCharsets.UTF_8);
      this.getUiMgr(this.mContext).send0xC1Info(this.SplicingByte(new byte[]{22, -63, 1}, var25));
   }

   public void radioInfoChange(int var1, String var2, String var3, String var4, int var5) {
      super.radioInfoChange(var1, var2, var3, var4, var5);
      byte var7;
      if (var2.equals("FM1")) {
         var7 = 1;
      } else if (!var2.equals("FM2") && !var2.equals("FM3")) {
         var7 = 3;
      } else {
         var7 = 2;
      }

      int var6;
      if (!var2.equals("FM1") && !var2.equals("FM2") && !var2.equals("FM3")) {
         var6 = Integer.parseInt(var3);
      } else {
         var6 = Integer.parseInt(var3) * 100;
      }

      this.getUiMgr(this.mContext).send0xC0MediaInfo(1, var7, var1, this.getMsb(var6), this.getLsb(var6), 0);
   }

   public void sourceSwitchNoMediaInfoChange(boolean var1) {
      super.sourceSwitchNoMediaInfoChange(var1);
      this.getUiMgr(this.mContext).send0xC0MediaInfo(0, 0, 0, 0, 0, 0);
   }

   public void videoInfoChange(byte var1, byte var2, int var3, int var4, byte var5, byte var6, byte var7, String var8, byte var9, byte var10, String var11, String var12, String var13, int var14, byte var15, boolean var16, int var17) {
      super.videoInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
      if (var1 != 9) {
         this.getUiMgr(this.mContext).send0xC0MediaInfo(8, 4, this.getMsb(var3), this.getLsb(var3), this.getMsb(var4), this.getLsb(var4));
      }

   }
}
