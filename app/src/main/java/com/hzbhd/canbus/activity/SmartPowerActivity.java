package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.smartpower.SmartPowerManager;
import com.hzbhd.canbus.smartpower.SmartPowerMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.view.NumberInputView;
import java.util.ArrayList;
import java.util.List;

public class SmartPowerActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
   protected RelativeLayout bottomRelative;
   protected Button btnChooseVersion;
   protected boolean isClick = true;
   private Context mContext;
   protected TextView mVersion;
   protected RadioButton radioButtonComfort;
   protected RadioButton radioButtonEconomics;
   protected RadioButton radioButtonOriginal;
   protected RadioButton radioButtonSport;
   protected RadioButton radioButtonTrack;
   protected List radioButtons = new ArrayList();
   protected RadioGroup radioGroup;
   protected SeekBar seekBar;

   private String getPowerModel(int var1) {
      return var1 != 2000 && var1 < 1000 ? "-" + var1 : "";
   }

   private String getVersionText(int var1) {
      if (var1 <= 0) {
         return "";
      } else {
         return var1 < 10 ? "v0." + var1 : "v" + var1 / 10 + "." + var1 % 10;
      }
   }

   private void initData() {
      this.radioGroup.setOnCheckedChangeListener(new SmartPowerActivity$$ExternalSyntheticLambda2(this));
      this.seekBar.setOnSeekBarChangeListener(this);
      this.btnChooseVersion.setOnClickListener(new SmartPowerActivity$$ExternalSyntheticLambda3(this));
   }

   private void initView() {
      this.seekBar = (SeekBar)this.findViewById(2131363296);
      this.bottomRelative = (RelativeLayout)this.findViewById(2131361986);
      this.radioGroup = (RadioGroup)this.findViewById(2131363038);
      this.radioButtonComfort = (RadioButton)this.findViewById(2131363029);
      this.radioButtonOriginal = (RadioButton)this.findViewById(2131363033);
      this.radioButtonSport = (RadioButton)this.findViewById(2131363035);
      this.radioButtonEconomics = (RadioButton)this.findViewById(2131363032);
      this.radioButtonTrack = (RadioButton)this.findViewById(2131363037);
      this.mVersion = (TextView)this.findViewById(2131363715);
      this.btnChooseVersion = (Button)this.findViewById(2131362013);
      this.setEnabled(false);
      this.radioButtons.add(this.radioButtonEconomics);
      this.radioButtons.add(this.radioButtonOriginal);
      this.radioButtons.add(this.radioButtonComfort);
      this.radioButtons.add(this.radioButtonSport);
      this.radioButtons.add(this.radioButtonTrack);
      if (GeneralDzSmartData.isHasData) {
         this.refreshUi();
      }

   }

   // $FF: synthetic method
   static void lambda$initData$2(AlertDialog var0, String var1) {
      int var2 = Integer.parseInt(var1);
      sendDzMsg(21, var2 % 256, var2 / 256, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      var0.dismiss();
   }

   public static void sendDzMsg(int var0, int var1, int var2, int var3, int var4) {
      byte var9 = (byte)var0;
      byte var7 = (byte)var1;
      byte var8 = (byte)var2;
      byte var5 = (byte)var3;
      byte var6 = (byte)var4;
      FutureUtil.instance.reportSmartPowerInfo(new byte[]{var9, var7, var8, var5, var6});
   }

   private void sendModeValue(int var1) {
      int var4 = GeneralDzSmartData.mode;
      byte var3 = 0;
      boolean var2;
      if (var4 != 3 && var4 != 4) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var4 == 3 || var4 == 0) {
         var3 = 4;
      }

      if (var2) {
         sendDzMsg(var4, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data3, var1 + 1, var3, 4), GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      } else {
         sendDzMsg(var4, GeneralDzSmartData.data3, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data4, var1 + 1, var3, 4), GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      }

   }

   private void setEnabled(boolean var1) {
      this.radioButtonEconomics.setEnabled(var1);
      this.radioButtonOriginal.setEnabled(var1);
      this.radioButtonComfort.setEnabled(var1);
      this.radioButtonSport.setEnabled(var1);
      this.radioButtonTrack.setEnabled(var1);
      this.seekBar.setEnabled(var1);
      this.btnChooseVersion.setEnabled(var1);
   }

   // $FF: synthetic method
   void lambda$initData$1$com_hzbhd_canbus_activity_SmartPowerActivity(RadioGroup var1, int var2) {
      byte var4 = 0;
      byte var3 = var4;
      switch (var2) {
         case 2131363029:
            var3 = 2;
            break;
         case 2131363030:
         case 2131363031:
         case 2131363034:
         case 2131363036:
         default:
            var3 = var4;
         case 2131363032:
            break;
         case 2131363033:
            var3 = 1;
            break;
         case 2131363035:
            var3 = 3;
            break;
         case 2131363037:
            var3 = 4;
      }

      if (this.isClick) {
         sendDzMsg(var3, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      }

   }

   // $FF: synthetic method
   void lambda$initData$3$com_hzbhd_canbus_activity_SmartPowerActivity(View var1) {
      AlertDialog.Builder var2 = new AlertDialog.Builder(this, 2131820805);
      NumberInputView var3 = new NumberInputView(this);
      var2.setView(var3);
      AlertDialog var4 = var2.create();
      var4.show();
      var3.setOnOkClickListener(true, "2691", new SmartPowerActivity$$ExternalSyntheticLambda0(var4));
   }

   // $FF: synthetic method
   void lambda$onCreate$0$com_hzbhd_canbus_activity_SmartPowerActivity(byte[] var1) {
      SmartPowerMsgMgr.getInstance().canbusInfoChange(this.mContext, var1, new SmartPowerActivity$$ExternalSyntheticLambda1(this));
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.mContext = this;
      this.setActivityContentView();
      this.initView();
      this.initData();
      SmartPowerManager.getInstance().addOnSmartPowerChangeListener(new SmartPowerActivity$$ExternalSyntheticLambda4(this));
   }

   protected void onDestroy() {
      this.mContext = null;
      super.onDestroy();
   }

   public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
      if (var3) {
         this.sendModeValue(var2);
      }

   }

   public void onStartTrackingTouch(SeekBar var1) {
   }

   public void onStopTrackingTouch(SeekBar var1) {
   }

   public void refreshUi() {
      this.setEnabled(true);
      this.isClick = false;
      this.scaleRadioButton((RadioButton)this.radioButtons.get(GeneralDzSmartData.mode));
      if (GeneralDzSmartData.mode_int >= 0) {
         this.seekBar.setProgress(GeneralDzSmartData.mode_int);
      }

      this.mVersion.setText(this.getVersionText(GeneralDzSmartData.version) + this.getPowerModel(GeneralDzSmartData.power_model));
      if (GeneralDzSmartData.mode == 1) {
         this.bottomRelative.setVisibility(8);
      } else {
         this.bottomRelative.setVisibility(0);
      }

      this.isClick = true;
      if (GeneralDzSmartData.data7_3) {
         Toast.makeText(this, 2131769945, 0).show();
      }

      if (GeneralDzSmartData.data7_4) {
         Toast.makeText(this, 2131769947, 0).show();
      }

   }

   protected void scaleRadioButton(RadioButton var1) {
      for(int var2 = 0; var2 < this.radioButtons.size(); ++var2) {
         ViewGroup.MarginLayoutParams var3;
         if (var1 == this.radioButtons.get(var2)) {
            var1.setChecked(true);
            var3 = (ViewGroup.MarginLayoutParams)var1.getLayoutParams();
            var3.width = (int)this.getResources().getDimension(2131169483);
            var3.height = (int)this.getResources().getDimension(2131169569);
            var1.setLayoutParams(var3);
            var1.setPadding(0, 0, 0, (int)this.getResources().getDimension(2131168747));
         } else {
            var3 = (ViewGroup.MarginLayoutParams)((RadioButton)this.radioButtons.get(var2)).getLayoutParams();
            var3.width = (int)this.getResources().getDimension(2131169058);
            var3.height = (int)this.getResources().getDimension(2131169493);
            ((RadioButton)this.radioButtons.get(var2)).setLayoutParams(var3);
            ((RadioButton)this.radioButtons.get(var2)).setPadding(0, 0, 0, (int)this.getResources().getDimension(2131167415));
         }
      }

   }

   protected void setActivityContentView() {
      this.setContentView(2131558693);
   }
}
