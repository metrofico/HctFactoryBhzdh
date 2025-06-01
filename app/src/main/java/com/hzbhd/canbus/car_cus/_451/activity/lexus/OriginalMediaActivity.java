package com.hzbhd.canbus.car_cus._451.activity.lexus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._451.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._451.data.EquipData;
import com.hzbhd.canbus.car_cus._451.observer.ObserverBuilding451;
import com.hzbhd.canbus.car_cus._451.view.AmplifierView;
import com.hzbhd.canbus.car_cus._451.view.AuxView;
import com.hzbhd.canbus.car_cus._451.view.BasicInfoView;
import com.hzbhd.canbus.car_cus._451.view.BtInfoView;
import com.hzbhd.canbus.car_cus._451.view.CdInfoView;
import com.hzbhd.canbus.car_cus._451.view.LyricTextViewView;
import com.hzbhd.canbus.car_cus._451.view.PhoneView;
import com.hzbhd.canbus.car_cus._451.view.UsbInfoView;
import com.hzbhd.canbus.car_cus._451.view.VolumeView;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class OriginalMediaActivity extends Activity implements CanInfoObserver {
   private AmplifierView amplifierView;
   private AuxView auxView;
   private BasicInfoView basicInfoView;
   private BtInfoView btInfoView;
   private CdInfoView cdInfoView;
   private LyricTextViewView lyricTextViewView;
   private PhoneView phoneView;
   private UsbInfoView usbInfoView;
   private VolumeView volumeView;
   private LinearLayout volume_lin;

   private void initView() {
      this.lyricTextViewView = (LyricTextViewView)this.findViewById(2131362825);
      this.amplifierView = (AmplifierView)this.findViewById(2131361922);
      this.cdInfoView = (CdInfoView)this.findViewById(2131362128);
      this.auxView = (AuxView)this.findViewById(2131361929);
      this.btInfoView = (BtInfoView)this.findViewById(2131361991);
      this.usbInfoView = (UsbInfoView)this.findViewById(2131363730);
      this.phoneView = (PhoneView)this.findViewById(2131362975);
      this.basicInfoView = (BasicInfoView)this.findViewById(2131361969);
      this.volume_lin = (LinearLayout)this.findViewById(2131363782);
      VolumeView var1 = (VolumeView)this.findViewById(2131363783);
      this.volumeView = var1;
      var1.setMax(63);
      this.updateUi();
   }

   private void updateUi() {
      if (EquipData.mode.equals(EquipData.MODE.AMPL)) {
         this.amplifierView.setVisibility(0);
         this.cdInfoView.setVisibility(8);
         this.auxView.setVisibility(8);
         this.btInfoView.setVisibility(8);
         this.usbInfoView.setVisibility(8);
         this.phoneView.setVisibility(8);
      } else if (EquipData.mode.equals(EquipData.MODE.CD)) {
         this.amplifierView.setVisibility(8);
         this.cdInfoView.setVisibility(0);
         this.auxView.setVisibility(8);
         this.btInfoView.setVisibility(8);
         this.usbInfoView.setVisibility(8);
         this.phoneView.setVisibility(8);
      } else if (EquipData.mode.equals(EquipData.MODE.AUX)) {
         this.amplifierView.setVisibility(8);
         this.cdInfoView.setVisibility(8);
         this.auxView.setVisibility(0);
         this.btInfoView.setVisibility(8);
         this.usbInfoView.setVisibility(8);
         this.phoneView.setVisibility(8);
      } else if (EquipData.mode.equals(EquipData.MODE.BT)) {
         this.amplifierView.setVisibility(8);
         this.cdInfoView.setVisibility(8);
         this.auxView.setVisibility(8);
         this.btInfoView.setVisibility(0);
         this.usbInfoView.setVisibility(8);
         this.phoneView.setVisibility(8);
      } else if (EquipData.mode.equals(EquipData.MODE.USB)) {
         this.amplifierView.setVisibility(8);
         this.cdInfoView.setVisibility(8);
         this.auxView.setVisibility(8);
         this.btInfoView.setVisibility(8);
         this.usbInfoView.setVisibility(0);
         this.phoneView.setVisibility(8);
      } else if (EquipData.mode.equals(EquipData.MODE.PHONE)) {
         this.amplifierView.setVisibility(8);
         this.cdInfoView.setVisibility(8);
         this.auxView.setVisibility(8);
         this.btInfoView.setVisibility(8);
         this.usbInfoView.setVisibility(8);
         this.phoneView.setVisibility(0);
      }

      LinearLayout var2 = this.volume_lin;
      byte var1;
      if (EquipData.isShowVolume) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var2.setVisibility(var1);
      this.volumeView.setProgress(EquipData.volume);
      this.volumeView.setMute(EquipData.isMute);
      this.lyricTextViewView.setTxt(EquipData.txtInfo);
      this.amplifierView.setData(EquipData.bas, EquipData.mid, EquipData.tre, EquipData.fad, EquipData.bal, EquipData.asl);
      this.cdInfoView.setData(EquipData.cdNumber, EquipData.cdSongNumber, EquipData.cdTimeStr);
      this.btInfoView.setData(EquipData.btSongNumber, EquipData.btTimeStr);
      this.usbInfoView.setData(EquipData.usbFileNumber, EquipData.usbSongNumber, EquipData.usbTimeStr);
      this.basicInfoView.band_txt.setText(EquipData.band);
      this.basicInfoView.band_value_txt.setText(EquipData.frequency);
      TextView var3 = this.basicInfoView.st_txt;
      if (EquipData.isShowSt) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var3.setVisibility(var1);
      this.basicInfoView.preset_txt.setText(EquipData.presetStr);
      var3 = this.basicInfoView.asl_txt;
      if (EquipData.isShowAslIcon) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var3.setVisibility(var1);
      var3 = this.basicInfoView.bt_txt;
      if (EquipData.isShowBtIcon) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var3.setVisibility(var1);
      var3 = this.basicInfoView.phone_txt;
      if (EquipData.isShowPhoneIcon) {
         var1 = 0;
      } else {
         var1 = 8;
      }

      var3.setVisibility(var1);
      if (EquipData.scan == 0) {
         this.basicInfoView.scan_txt.setVisibility(8);
      } else if (EquipData.scan == 1) {
         this.basicInfoView.scan_txt.setText("多碟+SCAN");
      } else if (EquipData.scan == 2) {
         this.basicInfoView.scan_txt.setText("SCAN");
      }

      if (EquipData.rpt == 0) {
         this.basicInfoView.rpt_txt.setVisibility(8);
      } else if (EquipData.rpt == 1) {
         this.basicInfoView.rpt_txt.setText("单碟+RPT");
      } else if (EquipData.rpt == 2) {
         this.basicInfoView.rpt_txt.setText("RPT");
      }

      if (EquipData.rand == 0) {
         this.basicInfoView.rand_txt.setVisibility(8);
      } else if (EquipData.rand == 1) {
         this.basicInfoView.rand_txt.setText("多碟+RAND");
      } else if (EquipData.rand == 2) {
         this.basicInfoView.rand_txt.setText("RADN");
      }

      if (EquipData.signal == 0) {
         this.basicInfoView.signal_txt.setBackgroundResource(2131231333);
      } else if (EquipData.signal == 1) {
         this.basicInfoView.signal_txt.setBackgroundResource(2131231334);
      } else if (EquipData.signal == 2) {
         this.basicInfoView.signal_txt.setBackgroundResource(2131231335);
      } else if (EquipData.signal == 3) {
         this.basicInfoView.signal_txt.setBackgroundResource(2131231336);
      } else if (EquipData.signal == 4) {
         this.basicInfoView.signal_txt.setBackgroundResource(2131231337);
      } else if (EquipData.signal == 5) {
         this.basicInfoView.signal_txt.setBackgroundResource(2131231338);
      }

      if (EquipData.disc_in == 0) {
         this.basicInfoView.disc_in.setVisibility(8);
         this.basicInfoView.disc_in.setTwinkle(false);
      } else if (EquipData.disc_in == 1) {
         this.basicInfoView.disc_in.setVisibility(0);
         this.basicInfoView.disc_in.setTwinkle(true);
      } else if (EquipData.disc_in == 2) {
         this.basicInfoView.disc_in.setVisibility(0);
         this.basicInfoView.disc_in.setTwinkle(false);
      }

      if (EquipData.disc1 == 0) {
         this.basicInfoView.disc1.setVisibility(8);
         this.basicInfoView.disc1.setTwinkle(false);
      } else if (EquipData.disc1 == 1) {
         this.basicInfoView.disc1.setVisibility(0);
         this.basicInfoView.disc1.setTwinkle(true);
      } else if (EquipData.disc1 == 2) {
         this.basicInfoView.disc1.setVisibility(0);
         this.basicInfoView.disc1.setTwinkle(false);
      }

      if (EquipData.disc2 == 0) {
         this.basicInfoView.disc2.setVisibility(8);
         this.basicInfoView.disc2.setTwinkle(false);
      } else if (EquipData.disc2 == 1) {
         this.basicInfoView.disc2.setVisibility(0);
         this.basicInfoView.disc2.setTwinkle(true);
      } else if (EquipData.disc2 == 2) {
         this.basicInfoView.disc2.setVisibility(0);
         this.basicInfoView.disc2.setTwinkle(false);
      }

      if (EquipData.disc3 == 0) {
         this.basicInfoView.disc3.setVisibility(8);
         this.basicInfoView.disc3.setTwinkle(false);
      } else if (EquipData.disc3 == 1) {
         this.basicInfoView.disc3.setVisibility(0);
         this.basicInfoView.disc3.setTwinkle(true);
      } else if (EquipData.disc3 == 2) {
         this.basicInfoView.disc3.setVisibility(0);
         this.basicInfoView.disc3.setTwinkle(false);
      }

      if (EquipData.disc4 == 0) {
         this.basicInfoView.disc4.setVisibility(8);
         this.basicInfoView.disc4.setTwinkle(false);
      } else if (EquipData.disc4 == 1) {
         this.basicInfoView.disc4.setVisibility(0);
         this.basicInfoView.disc4.setTwinkle(true);
      } else if (EquipData.disc4 == 2) {
         this.basicInfoView.disc4.setVisibility(0);
         this.basicInfoView.disc4.setTwinkle(false);
      }

      if (EquipData.disc5 == 0) {
         this.basicInfoView.disc5.setVisibility(8);
         this.basicInfoView.disc5.setTwinkle(false);
      } else if (EquipData.disc5 == 1) {
         this.basicInfoView.disc5.setVisibility(0);
         this.basicInfoView.disc5.setTwinkle(true);
      } else if (EquipData.disc5 == 2) {
         this.basicInfoView.disc5.setVisibility(0);
         this.basicInfoView.disc5.setTwinkle(false);
      }

      if (EquipData.disc6 == 0) {
         this.basicInfoView.disc6.setVisibility(8);
         this.basicInfoView.disc6.setTwinkle(false);
      } else if (EquipData.disc6 == 1) {
         this.basicInfoView.disc6.setVisibility(0);
         this.basicInfoView.disc6.setTwinkle(true);
      } else if (EquipData.disc6 == 2) {
         this.basicInfoView.disc6.setVisibility(0);
         this.basicInfoView.disc6.setTwinkle(false);
      }

   }

   public void dataChange() {
      BaseUtil.INSTANCE.runUi(new Function0(this) {
         final OriginalMediaActivity this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            this.this$0.updateUi();
            return null;
         }
      });
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558688);
      this.initView();
   }

   protected void onDestroy() {
      super.onDestroy();
      ObserverBuilding451.getInstance().unRegister(new OriginalMediaActivity$$ExternalSyntheticLambda0(this));
   }

   protected void onResume() {
      super.onResume();
      ObserverBuilding451.getInstance().register(new OriginalMediaActivity$$ExternalSyntheticLambda0(this));
   }
}
