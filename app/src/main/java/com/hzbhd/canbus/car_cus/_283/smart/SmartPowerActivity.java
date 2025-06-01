package com.hzbhd.canbus.car_cus._283.smart;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartPowerActivity extends AbstractBaseActivity {
   private RelativeLayout bottomRelative;
   private boolean isClick = true;
   private TextView mTitle;
   private TextView mVersion;
   private RadioButton radioButtonComfort;
   private RadioButton radioButtonEconomics;
   private RadioButton radioButtonOriginal;
   private RadioButton radioButtonSport;
   private RadioButton radioButtonTrack;
   private List radioButtons = new ArrayList();
   private RadioGroup radioGroup;
   private SeekBar seekBar;
   private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();

   private String getVersionText(int var1) {
      if (var1 <= 0) {
         return "";
      } else {
         return var1 < 10 ? "v 0." + var1 : "v " + var1 / 10 + "." + var1 % 10;
      }
   }

   private void initData() {
      this.radioGroup.setOnCheckedChangeListener(new SmartPowerActivity$$ExternalSyntheticLambda0(this));
      this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final SmartPowerActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            if (var3) {
               this.this$0.sendModeValue(var2);
            }

         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      });
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
      this.mTitle = (TextView)this.findViewById(2131363710);
      this.mVersion = (TextView)this.findViewById(2131363715);
      this.setEnabled(false);
      this.radioButtons.add(this.radioButtonEconomics);
      this.radioButtons.add(this.radioButtonOriginal);
      this.radioButtons.add(this.radioButtonComfort);
      this.radioButtons.add(this.radioButtonSport);
      this.radioButtons.add(this.radioButtonTrack);
   }

   private void scaleRadioButton(RadioButton var1) {
      for(int var2 = 0; var2 < this.radioButtons.size(); ++var2) {
         if (var1 == this.radioButtons.get(var2)) {
            var1.setScaleX(1.195F);
            var1.setScaleY(1.195F);
            var1.setChecked(true);
         } else {
            ((RadioButton)this.radioButtons.get(var2)).setScaleX(1.0F);
            ((RadioButton)this.radioButtons.get(var2)).setScaleY(1.0F);
         }
      }

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
         MessageSender.sendDzMsg(var4, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data3, var1 + 1, var3, 4), GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      } else {
         MessageSender.sendDzMsg(var4, GeneralDzSmartData.data3, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data4, var1 + 1, var3, 4), GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      }

   }

   private void setEnabled(boolean var1) {
      this.radioButtonEconomics.setEnabled(var1);
      this.radioButtonOriginal.setEnabled(var1);
      this.radioButtonComfort.setEnabled(var1);
      this.radioButtonSport.setEnabled(var1);
      this.radioButtonTrack.setEnabled(var1);
      this.seekBar.setEnabled(var1);
   }

   // $FF: synthetic method
   void lambda$initData$0$com_hzbhd_canbus_car_cus__283_smart_SmartPowerActivity(RadioGroup var1, int var2) {
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
         MessageSender.sendDzMsg(var3, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558432);
      this.initView();
      this.initData();
      if (GeneralDzSmartData.show) {
         this.refreshUi((Bundle)null);
      }

   }

   public void refreshUi(Bundle var1) {
      this.setEnabled(true);
      this.isClick = false;
      this.scaleRadioButton((RadioButton)this.radioButtons.get(GeneralDzSmartData.mode));
      if (GeneralDzSmartData.mode_int >= 0) {
         this.seekBar.setProgress(GeneralDzSmartData.mode_int);
      }

      this.mVersion.setText(this.getVersionText(GeneralDzSmartData.version));
      if (GeneralDzSmartData.mode == 1) {
         this.bottomRelative.setVisibility(8);
      } else {
         this.bottomRelative.setVisibility(0);
      }

      this.isClick = true;
   }
}
