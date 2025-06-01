package com.hct.factory.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hct.factory.FactoryFun;
import com.hct.factory.Hct_Config;

public class VoiceFragment extends Fragment {
   private static final int INPUT_AJX = 9;
   private static final int INPUT_AUX = 3;
   private static final int INPUT_BT = 0;
   private static final int INPUT_DAB = 8;
   private static final int INPUT_DTV = 5;
   private static final int INPUT_DVD = 2;
   private static final int INPUT_DVR = 7;
   private static final int INPUT_IPOD = 6;
   private static final int INPUT_MAX = 12;
   private static final int INPUT_MICGAIN = 11;
   private static final int INPUT_RADIO = 4;
   private static final int INPUT_SYNC = 10;
   private static final int INPUT_SYS = 1;
   private View.OnClickListener AddSub = new View.OnClickListener(this) {
      final VoiceFragment this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1) {
         TextView var2;
         byte[] var3;
         StringBuilder var4;
         StringBuilder var5;
         TextView var6;
         switch (var1.getId()) {
            case 2131099667:
               if (this.this$0.mConfig.mChannelGain[3] < 20) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[3];
                  var6 = this.this$0.avinvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[3]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099668:
               if (this.this$0.mConfig.mChannelGain[3] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[3];
                  var6 = this.this$0.avinvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[3]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099683:
               if (this.this$0.mConfig.mChannelGain[0] < 20) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[0];
                  var2 = this.this$0.btvol;
                  var4 = new StringBuilder();
                  var4.append("");
                  var4.append(this.this$0.mConfig.mChannelGain[0]);
                  var2.setText(var4.toString());
               }
               break;
            case 2131099684:
               if (this.this$0.mConfig.mChannelGain[0] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[0];
                  var6 = this.this$0.btvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[0]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099750:
               if (this.this$0.mConfig.mChannelGain[2] < 20) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[2];
                  var6 = this.this$0.dvdvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[2]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099751:
               if (this.this$0.mConfig.mChannelGain[2] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[2];
                  var6 = this.this$0.dvdvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[2]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099784:
               if (this.this$0.mConfig.mChannelGain[6] < 20) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[6];
                  var6 = this.this$0.ipodvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[6]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099785:
               if (this.this$0.mConfig.mChannelGain[6] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[6];
                  var6 = this.this$0.ipodvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[6]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099808:
               if (this.this$0.mConfig.mChannelGain[11] < 12) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[11];
                  var6 = this.this$0.micgainvol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[11]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099809:
               if (this.this$0.mConfig.mChannelGain[11] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[11];
                  var2 = this.this$0.micgainvol;
                  var4 = new StringBuilder();
                  var4.append("");
                  var4.append(this.this$0.mConfig.mChannelGain[11]);
                  var2.setText(var4.toString());
               }
               break;
            case 2131099829:
               if (this.this$0.mConfig.mChannelGain[4] < 20) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[4];
                  var2 = this.this$0.radiovol;
                  var4 = new StringBuilder();
                  var4.append("");
                  var4.append(this.this$0.mConfig.mChannelGain[4]);
                  var2.setText(var4.toString());
               }
               break;
            case 2131099831:
               if (this.this$0.mConfig.mChannelGain[4] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[4];
                  var2 = this.this$0.radiovol;
                  var4 = new StringBuilder();
                  var4.append("");
                  var4.append(this.this$0.mConfig.mChannelGain[4]);
                  var2.setText(var4.toString());
               }
               break;
            case 2131099863:
               if (this.this$0.mConfig.mChannelGain[1] < 20) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[1];
                  var6 = this.this$0.mediavol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[1]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099867:
               if (this.this$0.mConfig.mChannelGain[1] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[1];
                  var6 = this.this$0.mediavol;
                  var5 = new StringBuilder();
                  var5.append("");
                  var5.append(this.this$0.mConfig.mChannelGain[1]);
                  var6.setText(var5.toString());
               }
               break;
            case 2131099932:
               if (this.this$0.mConfig.mChannelGain[5] < 20) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  ++var3[5];
                  var2 = this.this$0.tvvol;
                  var4 = new StringBuilder();
                  var4.append("");
                  var4.append(this.this$0.mConfig.mChannelGain[5]);
                  var2.setText(var4.toString());
               }
               break;
            case 2131099933:
               if (this.this$0.mConfig.mChannelGain[5] > 0) {
                  var3 = this.this$0.mConfig.mChannelGain;
                  --var3[5];
                  var2 = this.this$0.tvvol;
                  var4 = new StringBuilder();
                  var4.append("");
                  var4.append(this.this$0.mConfig.mChannelGain[5]);
                  var2.setText(var4.toString());
               }
         }

      }
   };
   private TextView avinvol;
   private TextView btvol;
   private TextView dvdvol;
   private TextView ipodvol;
   private Hct_Config.ST_FACTORY_CONFIG mConfig = null;
   private FactoryFun mFactoryFun;
   private TextView mediavol;
   private TextView micgainvol;
   private TextView radiovol;
   private TextView tvvol;

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
      this.mConfig = this.mFactoryFun.getFactoryConfig();
      TextView var3 = this.tvvol;
      StringBuilder var2 = new StringBuilder();
      var2.append("");
      var2.append(this.mConfig.mChannelGain[5]);
      var3.setText(var2.toString());
      TextView var5 = this.dvdvol;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(this.mConfig.mChannelGain[2]);
      var5.setText(var4.toString());
      var3 = this.ipodvol;
      var2 = new StringBuilder();
      var2.append("");
      var2.append(this.mConfig.mChannelGain[6]);
      var3.setText(var2.toString());
      var3 = this.mediavol;
      var2 = new StringBuilder();
      var2.append("");
      var2.append(this.mConfig.mChannelGain[1]);
      var3.setText(var2.toString());
      var5 = this.avinvol;
      var4 = new StringBuilder();
      var4.append("");
      var4.append(this.mConfig.mChannelGain[3]);
      var5.setText(var4.toString());
      var3 = this.radiovol;
      var2 = new StringBuilder();
      var2.append("");
      var2.append(this.mConfig.mChannelGain[4]);
      var3.setText(var2.toString());
      var5 = this.btvol;
      var4 = new StringBuilder();
      var4.append("");
      var4.append(this.mConfig.mChannelGain[0]);
      var5.setText(var4.toString());
      var5 = this.micgainvol;
      var4 = new StringBuilder();
      var4.append("");
      var4.append(this.mConfig.mChannelGain[11]);
      var5.setText(var4.toString());
   }

   public void onAttach(Activity var1) {
      super.onAttach(var1);

      try {
         this.mFactoryFun = (FactoryFun)var1;
      } catch (Exception var2) {
      }

   }

   public void onCreate(@Nullable Bundle var1) {
      super.onCreate(var1);
   }

   public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      View var4 = var1.inflate(2131230747, var2, false);
      this.tvvol = (TextView)var4.findViewById(2131099884);
      this.dvdvol = (TextView)var4.findViewById(2131099879);
      this.ipodvol = (TextView)var4.findViewById(2131099880);
      this.mediavol = (TextView)var4.findViewById(2131099883);
      this.avinvol = (TextView)var4.findViewById(2131099877);
      this.radiovol = (TextView)var4.findViewById(2131099882);
      this.btvol = (TextView)var4.findViewById(2131099878);
      this.micgainvol = (TextView)var4.findViewById(2131099881);
      ((Button)var4.findViewById(2131099932)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099933)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099667)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099668)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099750)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099751)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099829)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099831)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099784)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099785)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099683)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099684)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099863)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099867)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099808)).setOnClickListener(this.AddSub);
      ((Button)var4.findViewById(2131099809)).setOnClickListener(this.AddSub);
      return var4;
   }

   public void onDestroy() {
      super.onDestroy();
   }

   public void onResume() {
      super.onResume();
   }
}
