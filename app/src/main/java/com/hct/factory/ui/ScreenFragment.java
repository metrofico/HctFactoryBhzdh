package com.hct.factory.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hct.factory.FactoryFun;
import com.hct.factory.Hct_Config;
import java.util.Locale;

public class ScreenFragment extends Fragment {
   private Hct_Config.ST_FACTORY_CONFIG mConfig = null;
   private CheckBox mDith;
   private FactoryFun mFactoryFun;
   private View.OnClickListener mOnClick = new View.OnClickListener(this) {
      final ScreenFragment this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1) {
         int var2;
         int var3;
         StringBuilder var5;
         ScreenFragment var6;
         StringBuilder var7;
         ScreenFragment var8;
         switch (var1.getId()) {
            case 2131099663:
            case 2131099666:
               if (this.this$0.mSbAvdd.getProgress() > 0) {
                  var2 = this.this$0.mSbAvdd.getProgress() - 1;
                  this.this$0.mConfig.mAvdd = (byte)var2;
                  var8 = this.this$0;
                  var7 = new StringBuilder();
                  var7.append("cfg_avdd=");
                  var7.append(var2);
                  var8.setParameters(var7.toString());
                  this.this$0.mSbAvdd.setProgress(var2);
                  var7 = new StringBuilder();
                  var7.append("cfg_avdd=");
                  var7.append(var2);
                  Log.i("ScreenFragment", var7.toString());
               }
               break;
            case 2131099665:
            case 2131099934:
               if (this.this$0.mSbAvdd.getProgress() < this.this$0.mSbAvdd.getMax()) {
                  var2 = this.this$0.mSbAvdd.getProgress() + 1;
                  this.this$0.mConfig.mAvdd = (byte)var2;
                  var6 = this.this$0;
                  var5 = new StringBuilder();
                  var5.append("cfg_avdd=");
                  var5.append(var2);
                  var6.setParameters(var5.toString());
                  this.this$0.mSbAvdd.setProgress(var2);
                  var7 = new StringBuilder();
                  var7.append("cfg_avdd=");
                  var7.append(var2);
                  Log.i("ScreenFragment", var7.toString());
               }
               break;
            case 2131099679:
            case 2131099681:
               var2 = this.this$0.mSbBlMax.getProgress();
               if (this.this$0.mSbBlMax.getProgress() > 0) {
                  for(var3 = var2 * 130 / this.this$0.mSbBlMax.getMax(); ((var2 - 1) * 130 / this.this$0.mSbBlMax.getMax() + 140) / 10 >= (var3 + 140) / 10 - 1 && var2 != 0; --var2) {
                  }

                  this.this$0.mConfig.mBrightnessMax = (byte)(var2 + 42);
                  var8 = this.this$0;
                  var7 = new StringBuilder();
                  var7.append("cfg_brightness_max=");
                  var7.append(this.this$0.mConfig.mBrightnessMax & 255);
                  var8.setParameters(var7.toString());
                  this.this$0.mSbBlMax.setProgress(var2);
                  var7 = new StringBuilder();
                  var7.append("progress:");
                  var7.append(var2);
                  var7.append("cfg_vcom=");
                  var7.append(this.this$0.mConfig.mBrightnessMax & 255);
                  Log.i("ScreenFragment", var7.toString());
               }
               break;
            case 2131099680:
            case 2131099935:
               var2 = this.this$0.mSbBlMax.getProgress();
               if (this.this$0.mSbBlMax.getProgress() < this.this$0.mSbBlMax.getMax()) {
                  int var4 = var2 * 130 / this.this$0.mSbBlMax.getMax();

                  do {
                     var3 = var2 + 1;
                     if ((var3 * 130 / this.this$0.mSbBlMax.getMax() + 140) / 10 > (var4 + 140) / 10) {
                        break;
                     }

                     var2 = var3;
                  } while(this.this$0.mSbBlMax.getMax() != var3);

                  this.this$0.mConfig.mBrightnessMax = (byte)(var3 + 42);
                  var6 = this.this$0;
                  var5 = new StringBuilder();
                  var5.append("cfg_brightness_max=");
                  var5.append(this.this$0.mConfig.mBrightnessMax & 255);
                  var6.setParameters(var5.toString());
                  this.this$0.mSbBlMax.setProgress(var3);
                  var7 = new StringBuilder();
                  var7.append("progress:");
                  var7.append(var3);
                  var7.append("cfg_vcom=");
                  var7.append(this.this$0.mConfig.mBrightnessMax & 255);
                  Log.i("ScreenFragment", var7.toString());
               }
               break;
            case 2131099936:
            case 2131099939:
               if (this.this$0.mSbVcom.getProgress() < this.this$0.mSbVcom.getMax()) {
                  var2 = this.this$0.mSbVcom.getProgress() + 1;
                  this.this$0.mConfig.mVcom = (byte)var2;
                  var8 = this.this$0;
                  var7 = new StringBuilder();
                  var7.append("cfg_vcom=");
                  var7.append(var2);
                  var8.setParameters(var7.toString());
                  this.this$0.mSbVcom.setProgress(var2);
                  var7 = new StringBuilder();
                  var7.append("cfg_vcom=");
                  var7.append(var2);
                  Log.i("ScreenFragment", var7.toString());
               }
               break;
            case 2131099937:
            case 2131099940:
               if (this.this$0.mSbVcom.getProgress() > 0) {
                  var2 = this.this$0.mSbVcom.getProgress() - 1;
                  this.this$0.mConfig.mVcom = (byte)var2;
                  var6 = this.this$0;
                  var5 = new StringBuilder();
                  var5.append("cfg_vcom=");
                  var5.append(var2);
                  var6.setParameters(var5.toString());
                  this.this$0.mSbVcom.setProgress(var2);
                  var7 = new StringBuilder();
                  var7.append("cfg_vcom=");
                  var7.append(var2);
                  Log.i("ScreenFragment", var7.toString());
               }
         }

         this.this$0.updateText();
      }
   };
   private SeekBar mSbAvdd;
   private SeekBar mSbBlMax;
   private SeekBar mSbVcom;
   private CheckBox mSelb;
   private TextView mTxtAvdd;
   private TextView mTxtBlMax;
   private TextView mTxtVcom;

   private void setParameters(String var1) {
      this.mFactoryFun.setParameters(var1);
   }

   private void updateText() {
      this.mTxtAvdd.setText(String.format(Locale.US, "%.1fV", (float)(this.mSbAvdd.getProgress() + 90) / 10.0F));
      this.mTxtVcom.setText(String.format(Locale.US, "%.2fV", (float)(this.mSbVcom.getProgress() * 5 + 320) / 100.0F));
      int var1 = this.mSbBlMax.getProgress() * 130 / this.mSbBlMax.getMax() + 140;
      TextView var3 = this.mTxtBlMax;
      StringBuilder var2 = new StringBuilder();
      var2.append(var1 - var1 % 10);
      var2.append("MA");
      var3.setText(var2.toString());
   }

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
      this.mSbAvdd.setProgress(this.mConfig.mAvdd & 255);
      this.mSbVcom.setProgress(this.mConfig.mVcom & 255);
      if ((this.mConfig.mBrightnessMax & 255) < 42 || (this.mConfig.mBrightnessMax & 255) > 100) {
         this.mConfig.mBrightnessMax = 42;
      }

      this.mSbBlMax.setProgress((this.mConfig.mBrightnessMax & 255) - 42);
      CheckBox var5 = this.mDith;
      byte var2 = this.mConfig.mExtCfg;
      boolean var4 = true;
      boolean var3;
      if ((var2 & 4) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var5.setChecked(var3);
      var5 = this.mSelb;
      if ((this.mConfig.mExtCfg & 2) != 0) {
         var3 = var4;
      } else {
         var3 = false;
      }

      var5.setChecked(var3);
      this.mSbAvdd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final ScreenFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            if (var3) {
               this.this$0.mConfig.mAvdd = (byte)var2;
               ScreenFragment var4 = this.this$0;
               StringBuilder var5 = new StringBuilder();
               var5.append("cfg_avdd=");
               var5.append(var2);
               var4.setParameters(var5.toString());
               var5 = new StringBuilder();
               var5.append("seekBar cfg_avdd=");
               var5.append(var2);
               Log.i("ScreenFragment", var5.toString());
               this.this$0.updateText();
            }

         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      });
      this.mSbVcom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final ScreenFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            if (var3) {
               this.this$0.mConfig.mVcom = (byte)var2;
               ScreenFragment var5 = this.this$0;
               StringBuilder var4 = new StringBuilder();
               var4.append("cfg_vcom=");
               var4.append(var2);
               var5.setParameters(var4.toString());
               StringBuilder var6 = new StringBuilder();
               var6.append("seekBar cfg_vcom=");
               var6.append(var2);
               Log.i("ScreenFragment", var6.toString());
               this.this$0.updateText();
            }

         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      });
      this.mSbBlMax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this) {
         final ScreenFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            if (var3) {
               int var5 = var2 * 130 / this.this$0.mSbBlMax.getMax() + 140;
               int var4 = var2;
               if (var5 % 10 < 5) {
                  while(true) {
                     var4 = var2;
                     if (((var2 - 1) * 130 / this.this$0.mSbBlMax.getMax() + 140) / 10 < var5 / 10) {
                        break;
                     }

                     if (var2 == 0) {
                        var4 = var2;
                        break;
                     }

                     --var2;
                  }
               } else {
                  while(true) {
                     var2 = var4 + 1;
                     var4 = var2;
                     if ((var2 * 130 / this.this$0.mSbBlMax.getMax() + 140) / 10 > var5 / 10) {
                        break;
                     }

                     var4 = var2;
                     if (this.this$0.mSbBlMax.getMax() == var2) {
                        var4 = var2;
                        break;
                     }
                  }
               }

               this.this$0.mSbBlMax.setProgress(var4);
               this.this$0.mConfig.mBrightnessMax = (byte)(var4 + 42);
               ScreenFragment var7 = this.this$0;
               StringBuilder var6 = new StringBuilder();
               var6.append("cfg_brightness_max=");
               var6.append(this.this$0.mConfig.mBrightnessMax & 255);
               var7.setParameters(var6.toString());
               StringBuilder var8 = new StringBuilder();
               var8.append("temp:");
               var8.append(var5);
               var8.append("seekBar progress:");
               var8.append(var4);
               var8.append(" cfg_brightness_max=");
               var8.append(this.this$0.mConfig.mBrightnessMax & 255);
               Log.i("ScreenFragment", var8.toString());
               this.this$0.updateText();
            }

         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      });
      this.mDith.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
         final ScreenFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onCheckedChanged(CompoundButton var1, boolean var2) {
            Hct_Config.ST_FACTORY_CONFIG var3;
            StringBuilder var4;
            if (var2) {
               var3 = this.this$0.mConfig;
               var3.mExtCfg = (byte)(var3.mExtCfg | 4);
               this.this$0.setParameters("cfg_panel_io2=1");
               var4 = new StringBuilder();
               var4.append("CheckBox cfg_panel_io2=1 ");
               var4.append(this.this$0.mConfig.mExtCfg & 255);
               Log.i("ScreenFragment", var4.toString());
            } else {
               var3 = this.this$0.mConfig;
               var3.mExtCfg &= -5;
               this.this$0.setParameters("cfg_panel_io2=0");
               var4 = new StringBuilder();
               var4.append("CheckBox cfg_panel_io2=0 ");
               var4.append(this.this$0.mConfig.mExtCfg & 255);
               Log.i("ScreenFragment", var4.toString());
            }

         }
      });
      this.mSelb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
         final ScreenFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onCheckedChanged(CompoundButton var1, boolean var2) {
            Hct_Config.ST_FACTORY_CONFIG var3;
            StringBuilder var4;
            if (var2) {
               var3 = this.this$0.mConfig;
               var3.mExtCfg = (byte)(var3.mExtCfg | 2);
               this.this$0.setParameters("cfg_panel_io1=1");
               var4 = new StringBuilder();
               var4.append("CheckBox cfg_panel_io1=1 ");
               var4.append(this.this$0.mConfig.mExtCfg & 255);
               Log.i("ScreenFragment", var4.toString());
            } else {
               var3 = this.this$0.mConfig;
               var3.mExtCfg &= -3;
               this.this$0.setParameters("cfg_panel_io1=0");
               var4 = new StringBuilder();
               var4.append("CheckBox cfg_panel_io1=0 ");
               var4.append(this.this$0.mConfig.mExtCfg & 255);
               Log.i("ScreenFragment", var4.toString());
            }

         }
      });
      this.updateText();
   }

   public void onAttach(Activity var1) {
      super.onAttach(var1);

      try {
         this.mFactoryFun = (FactoryFun)var1;
         this.mConfig = this.mFactoryFun.getFactoryConfig();
      } catch (Exception var2) {
      }

   }

   public void onCreate(@Nullable Bundle var1) {
      super.onCreate(var1);
   }

   @Nullable
   public View onCreateView(LayoutInflater var1, @Nullable ViewGroup var2, @Nullable Bundle var3) {
      View var5 = var1.inflate(2131230740, var2, false);
      this.mSbAvdd = (SeekBar)var5.findViewById(2131099845);
      this.mSbVcom = (SeekBar)var5.findViewById(2131099847);
      this.mSbBlMax = (SeekBar)var5.findViewById(2131099846);
      this.mTxtAvdd = (TextView)var5.findViewById(2131099934);
      this.mTxtVcom = (TextView)var5.findViewById(2131099936);
      this.mTxtBlMax = (TextView)var5.findViewById(2131099935);
      var5.findViewById(2131099665).setOnClickListener(this.mOnClick);
      var5.findViewById(2131099666).setOnClickListener(this.mOnClick);
      var5.findViewById(2131099939).setOnClickListener(this.mOnClick);
      var5.findViewById(2131099940).setOnClickListener(this.mOnClick);
      var5.findViewById(2131099663).setOnClickListener(this.mOnClick);
      this.mTxtAvdd.setOnClickListener(this.mOnClick);
      var5.findViewById(2131099937).setOnClickListener(this.mOnClick);
      this.mTxtVcom.setOnClickListener(this.mOnClick);
      var5.findViewById(2131099680).setOnClickListener(this.mOnClick);
      var5.findViewById(2131099681).setOnClickListener(this.mOnClick);
      var5.findViewById(2131099679).setOnClickListener(this.mOnClick);
      this.mTxtBlMax.setOnClickListener(this.mOnClick);
      this.mDith = (CheckBox)var5.findViewById(2131099748);
      this.mSelb = (CheckBox)var5.findViewById(2131099852);
      if (!this.mFactoryFun.isMcuG48Ver()) {
         int var4 = this.mFactoryFun.getPanelCtl();
         if ((var4 & 1) == 0) {
            var5.findViewById(2131099938).setVisibility(4);
         }

         if ((var4 & 2) == 0) {
            var5.findViewById(2131099664).setVisibility(4);
         }

         if ((var4 & 4) == 0) {
            this.mSelb.setVisibility(4);
         }

         if ((var4 & 8) == 0) {
            this.mDith.setVisibility(4);
         }

         var5.findViewById(2131099678).setVisibility(4);
      }

      return var5;
   }

   public void onDestroy() {
      super.onDestroy();
   }
}
