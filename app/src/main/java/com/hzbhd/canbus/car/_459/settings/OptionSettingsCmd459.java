package com.hzbhd.canbus.car._459.settings;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.mcu.McuConstants;
import com.hzbhd.setting.proxy.SettingsManager;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;

public class OptionSettingsCmd459 {
   public String TAG_AWS;
   public String TAG_BL;
   public String TAG_CW;
   public String TAG_DCM;
   public String TAG_ER;
   public String TAG_FL;
   public String TAG_FLWSL;
   public String TAG_LD;
   public String TAG_SA;
   public String TAG_SPS;
   public String TAG_VDM;
   public boolean awsState;
   private int blState;
   public boolean cwState;
   private boolean dcmState;
   private int ecoCmd;
   private boolean erState;
   private boolean flState;
   private boolean flwslState;
   boolean isShoeAWS;
   boolean isShowEBS;
   boolean isShowFCW;
   boolean isShowFHAL;
   boolean isShowHW;
   boolean isShowLDW;
   boolean isShowTSR;
   public boolean ldState;
   public boolean saState;
   private byte[] settingsCmd;
   public boolean spsState;
   public String vdmState;

   private OptionSettingsCmd459() {
      this.isShoeAWS = false;
      this.isShowEBS = false;
      this.isShowLDW = false;
      this.isShowFCW = false;
      this.isShowTSR = false;
      this.isShowHW = false;
      this.isShowFHAL = false;
      this.blState = 160;
      this.dcmState = false;
      this.flState = true;
      this.flwslState = true;
      this.spsState = false;
      this.erState = true;
      this.saState = true;
      this.ldState = true;
      this.awsState = false;
      this.cwState = true;
      this.vdmState = "MID";
      this.ecoCmd = 3;
      this.TAG_BL = "can_settings_switch_bl";
      this.TAG_DCM = "can_settings_switch_dcm";
      this.TAG_FL = "can_settings_switch_fl";
      this.TAG_FLWSL = "can_settings_switch_flwsl";
      this.TAG_SPS = "can_settings_switch_sps";
      this.TAG_ER = "can_settings_switch_er";
      this.TAG_SA = "can_settings_switch_sa";
      this.TAG_LD = "can_settings_switch_ld";
      this.TAG_CW = "can_settings_switch_cw";
      this.TAG_AWS = "can_settings_switch_aws";
      this.TAG_VDM = "can_settings_switch_vdm";
      this.settingsCmd = new byte[]{22, 127, 127, 127, 127, 0, 0, 0, 0, 0, 0, 0, 0, 1};
   }

   // $FF: synthetic method
   OptionSettingsCmd459(Object var1) {
      this();
   }

   public static OptionSettingsCmd459 getInstance() {
      return OptionSettingsCmd459.Holder.INSTANCE;
   }

   private void sender() {
      CanbusMsgSender.sendMsg(this.settingsCmd);
   }

   public void booleanSaver(String var1, Boolean var2) {
      SharePreUtil.setBoolValue(BaseUtil.INSTANCE.getContext(), var1, var2);
   }

   public boolean getAWS() {
      return this.isShoeAWS ? this.awsState : false;
   }

   public int getBL() {
      return this.blState;
   }

   protected int[] getByteArrayToIntArray(byte[] var1) {
      int[] var4 = new int[var1.length];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         byte var3 = var1[var2];
         if ((var3 & 128) == 0) {
            var4[var2] = var3;
         } else {
            var4[var2] = var3 & 255;
         }
      }

      return var4;
   }

   public boolean getCW() {
      return this.isShowFCW ? this.cwState : false;
   }

   public boolean getDCM() {
      return this.dcmState;
   }

   public boolean getER() {
      return this.isShowEBS ? this.erState : false;
   }

   public boolean getFL() {
      return this.isShowFHAL ? this.flState : false;
   }

   public boolean getFLWSL() {
      return this.isShowFHAL ? this.flwslState : false;
   }

   public boolean getLD() {
      return this.isShowLDW ? this.ldState : false;
   }

   public boolean getSA() {
      return this.isShowTSR ? this.saState : false;
   }

   public boolean getSPS() {
      return this.spsState;
   }

   public String getVDM() {
      return this.isShowHW ? this.vdmState : "MID";
   }

   public void initState() {
      byte[] var1 = SettingsManager.getInstance().getBytes(SourceConstantsDef.MODULE_ID.MCU, BodaSysContant.TypeDef.SETTING, McuConstants.SETTING_GET.getMcuDtcMsg.ordinal(), 2);
      int[] var2;
      if (var1 != null) {
         var2 = this.getByteArrayToIntArray(var1);
      } else {
         var2 = null;
      }

      this.isShoeAWS = DataHandleUtils.getBoolBit4(var2[7]) ^ true;
      this.isShowEBS = DataHandleUtils.getBoolBit5(var2[7]) ^ true;
      this.isShowLDW = DataHandleUtils.getBoolBit0(var2[8]) ^ true;
      this.isShowFCW = DataHandleUtils.getBoolBit2(var2[8]) ^ true;
      this.isShowTSR = DataHandleUtils.getBoolBit0(var2[9]) ^ true;
      this.isShowHW = DataHandleUtils.getBoolBit5(var2[11]) ^ true;
      this.isShowFHAL = DataHandleUtils.getBoolBit6(var2[11]) ^ true;
      if (LogUtil.log5()) {
         LogUtil.d("initState(): --" + this.isShoeAWS + "-" + this.isShowEBS + "-" + this.isShowLDW + "-" + this.isShowFCW + "-" + this.isShowTSR + "-" + this.isShowHW + "-" + this.isShowFHAL);
      }

      this.blState = SharePreUtil.getIntValue(BaseUtil.INSTANCE.getContext(), this.TAG_BL, 160);
      this.dcmState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_DCM, false);
      this.flState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_FL, true);
      this.flwslState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_FLWSL, true);
      this.awsState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_AWS, false);
      this.saState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_SA, true);
      this.vdmState = SharePreUtil.getStringValue(BaseUtil.INSTANCE.getContext(), this.TAG_VDM, "MID");
      this.renewInitializationState();
      this.optionCmd();
   }

   public void intSaver(String var1, int var2) {
      SharePreUtil.setIntValue(BaseUtil.INSTANCE.getContext(), var1, var2);
   }

   public void optionCmd() {
      int var4 = this.blState;
      byte var6 = this.dcmState;
      byte var8 = this.flState;
      byte var10 = this.spsState;
      int var9 = this.erState ^ 1;
      byte var5 = this.saState;
      byte var11 = this.ldState;
      byte var2 = this.cwState;
      byte var3 = this.flwslState;
      if (LogUtil.log5()) {
         LogUtil.d("optionCmd(): -----------" + var10);
      }

      int var7 = DataHandleUtils.getDecimalFrom8Bit(var3, var2, var11, var5, var9, var10, var8, var6);
      boolean var14 = this.vdmState.equals("FAR");
      byte var1 = 0;
      if (!var14) {
         if (this.vdmState.equals("MID")) {
            var1 = 1;
         } else if (this.vdmState.equals("NEAR")) {
            var1 = 2;
         }
      }

      int var13 = this.ecoCmd;
      byte var12 = this.awsState;
      if (LogUtil.log5()) {
         LogUtil.d("setAutomaticWiperSystem()bit: ----" + var6 + "--" + var8 + "--" + var10 + "--" + var9 + "--" + var5 + "--" + var11 + "--" + var2 + "--" + var3 + "--" + var1 + "---" + var12);
      }

      byte[] var15 = this.settingsCmd;
      var15[5] = (byte)var4;
      var15[6] = (byte)var7;
      var15[7] = (byte)var1;
      var15[8] = (byte)var13;
      var15[9] = (byte)var12;
      this.sender();
   }

   public void renewInitializationState() {
      if (!this.isShowFHAL) {
         this.flState = false;
         this.flwslState = false;
      }

      if (!this.isShowEBS) {
         this.erState = false;
      }

      if (!this.isShowTSR) {
         this.saState = false;
      }

      if (!this.isShoeAWS) {
         this.awsState = false;
      }

      if (!this.isShowLDW) {
         this.ldState = false;
      }

      if (!this.isShowFCW) {
         this.cwState = false;
      }

      if (!this.isShowHW) {
         this.vdmState = "MID";
      }

   }

   public void setAWS(boolean var1) {
      if (LogUtil.log5()) {
         LogUtil.d("setAutomaticWiperSystem(): ----" + var1);
      }

      if (this.isShoeAWS) {
         this.awsState = var1;
      } else {
         this.awsState = false;
      }

      this.optionCmd();
   }

   public void setBL(int var1) {
      this.blState = var1;
      this.intSaver(this.TAG_BL, var1);
      this.optionCmd();
   }

   public void setCW(Boolean var1) {
      if (this.isShowFCW) {
         this.cwState = var1;
      } else {
         this.cwState = false;
      }

      this.optionCmd();
   }

   public void setDCM(boolean var1) {
      this.dcmState = var1;
      this.booleanSaver(this.TAG_DCM, var1);
      this.optionCmd();
   }

   public void setER(boolean var1) {
      if (this.isShowEBS) {
         this.erState = var1;
      } else {
         this.erState = false;
      }

      this.optionCmd();
   }

   public void setEcoRequestCmd(int var1) {
      this.ecoCmd = var1;
      this.optionCmd();
      this.ecoCmd = 3;
   }

   public void setFL(boolean var1) {
      if (this.isShowFHAL) {
         this.flState = var1;
      } else {
         this.flState = false;
      }

      this.booleanSaver(this.TAG_FL, this.flState);
      this.optionCmd();
   }

   public void setFLWSL(boolean var1) {
      if (this.isShowFHAL) {
         this.flwslState = var1;
      } else {
         this.flwslState = false;
      }

      this.booleanSaver(this.TAG_FLWSL, this.flwslState);
      this.optionCmd();
   }

   public void setLD(Boolean var1) {
      if (this.isShowLDW) {
         this.ldState = var1;
      } else {
         this.ldState = false;
      }

      this.optionCmd();
   }

   public void setSA(boolean var1) {
      if (this.isShowTSR) {
         this.saState = var1;
      } else {
         this.saState = false;
      }

      this.optionCmd();
   }

   public void setSPS(boolean var1) {
      this.spsState = var1;
      this.optionCmd();
   }

   public void setVDM(String var1) {
      if (this.isShowHW) {
         this.vdmState = var1;
      } else {
         this.vdmState = "MID";
      }

      this.stringSaver(this.TAG_VDM, this.vdmState);
      this.optionCmd();
   }

   public void stringSaver(String var1, String var2) {
      SharePreUtil.setStringValue(BaseUtil.INSTANCE.getContext(), var1, var2);
   }

   private static class Holder {
      private static final OptionSettingsCmd459 INSTANCE = new OptionSettingsCmd459();
   }
}
