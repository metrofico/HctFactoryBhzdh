package com.hzbhd.canbus.car_cus._283.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.car_cus._283.HandlerUtils;
import com.hzbhd.canbus.car_cus._283.utils._283_SharedPreferencesUtils;
import com.hzbhd.canbus.util.SharePreUtil;

public class DyReverseSettingActivity extends AppCompatActivity implements View.OnClickListener {
   private int carTypeCount;
   private int[] carTypes = new int[]{2131760592, 2131760593, 2131760594, 2131760595};
   private CheckBox dialog_radar_cb;
   private LinearLayout dialog_radar_lin;
   private CheckBox jzx_cb;
   private LinearLayout jzx_lay;
   private CheckBox ld_cb;
   private LinearLayout ld_lay;
   private ImageView leftChooseImage;
   private SharedPreferences.Editor mEditor;
   private ImageView rightChooseImage;
   private TextView textCarType;
   private CheckBox zjx_cb;
   private LinearLayout zjx_lay;

   private boolean getDialogRadar() {
      return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_DIALOG_RADAR_SWITCH, false);
   }

   private boolean getJZX() {
      return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_JZX_SWITCH, true);
   }

   private boolean getLD() {
      return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_LD_SWITCH, true);
   }

   private boolean getZJX() {
      return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_ZJX_SWITCH, true);
   }

   private void initView() {
      this.setZJX(this.getZJX());
      this.setJZX(this.getJZX());
      this.setLD(this.getLD());
      this.setDialogRadarSwitch(this.getDialogRadar());
   }

   private void setDialogRadarSwitch(boolean var1) {
      if (var1) {
         this.dialog_radar_cb.setChecked(true);
      } else {
         this.dialog_radar_cb.setChecked(false);
      }

      SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_DIALOG_RADAR_SWITCH, var1);
   }

   private void setJZX(boolean var1) {
      if (var1) {
         this.jzx_cb.setChecked(true);
      } else {
         this.jzx_cb.setChecked(false);
      }

      SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_JZX_SWITCH, var1);
   }

   private void setLD(boolean var1) {
      if (var1) {
         this.ld_cb.setChecked(true);
      } else {
         this.ld_cb.setChecked(false);
      }

      SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_LD_SWITCH, var1);
   }

   private void setZJX(boolean var1) {
      if (var1) {
         this.zjx_cb.setChecked(true);
      } else {
         this.zjx_cb.setChecked(false);
      }

      SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_ZJX_SWITCH, var1);
   }

   public void onClick(View var1) {
      int var2;
      switch (var1.getId()) {
         case 2131362191:
         case 2131362192:
            if (this.getDialogRadar()) {
               this.setDialogRadarSwitch(false);
            } else {
               this.setDialogRadarSwitch(true);
            }
            break;
         case 2131362682:
         case 2131362683:
            if (this.getJZX()) {
               this.setJZX(false);
            } else {
               this.setJZX(true);
            }
            break;
         case 2131362716:
         case 2131362717:
            if (this.getLD()) {
               this.setLD(false);
            } else {
               this.setLD(true);
            }
            break;
         case 2131362719:
            var2 = this.carTypeCount - 1;
            this.carTypeCount = var2;
            if (var2 < 0) {
               this.carTypeCount = this.carTypes.length - 1;
            }

            this.textCarType.setText(this.carTypes[this.carTypeCount]);
            HandlerUtils.getInstance().refreshUI();
            this.mEditor.putInt("CarType", this.carTypeCount);
            this.mEditor.apply();
            break;
         case 2131363147:
            var2 = this.carTypeCount + 1;
            this.carTypeCount = var2;
            int[] var3 = this.carTypes;
            if (var2 >= var3.length) {
               this.carTypeCount = 0;
            }

            this.textCarType.setText(var3[this.carTypeCount]);
            HandlerUtils.getInstance().refreshUI();
            this.mEditor.putInt("CarType", this.carTypeCount);
            this.mEditor.apply();
            break;
         case 2131363809:
         case 2131363810:
            if (this.getZJX()) {
               this.setZJX(false);
            } else {
               this.setZJX(true);
            }
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558659);
      this.zjx_lay = (LinearLayout)this.findViewById(2131363810);
      this.jzx_lay = (LinearLayout)this.findViewById(2131362683);
      this.ld_lay = (LinearLayout)this.findViewById(2131362717);
      this.dialog_radar_lin = (LinearLayout)this.findViewById(2131362192);
      this.zjx_cb = (CheckBox)this.findViewById(2131363809);
      this.jzx_cb = (CheckBox)this.findViewById(2131362682);
      this.ld_cb = (CheckBox)this.findViewById(2131362716);
      this.dialog_radar_cb = (CheckBox)this.findViewById(2131362191);
      this.leftChooseImage = (ImageView)this.findViewById(2131362719);
      this.rightChooseImage = (ImageView)this.findViewById(2131363147);
      this.textCarType = (TextView)this.findViewById(2131363515);
      this.leftChooseImage.setOnClickListener(this);
      this.rightChooseImage.setOnClickListener(this);
      this.zjx_lay.setOnClickListener(this);
      this.zjx_cb.setOnClickListener(this);
      this.jzx_lay.setOnClickListener(this);
      this.jzx_cb.setOnClickListener(this);
      this.ld_lay.setOnClickListener(this);
      this.dialog_radar_lin.setOnClickListener(this);
      this.ld_cb.setOnClickListener(this);
      this.dialog_radar_cb.setOnClickListener(this);
      SharedPreferences var3 = this.getSharedPreferences("car_type", 0);
      this.mEditor = var3.edit();
      int var2 = var3.getInt("CarType", 0);
      this.carTypeCount = var2;
      this.textCarType.setText(this.carTypes[var2]);
      this.initView();
   }
}
