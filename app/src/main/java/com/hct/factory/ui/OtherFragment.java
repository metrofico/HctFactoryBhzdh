package com.hct.factory.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.hct.RkSystemProp;
import com.hct.factory.FactoryFun;
import com.hct.factory.Factory_Util;
import com.hct.factory.Hct_Config;

public class OtherFragment extends Fragment {
   private Spinner m3PartyCallGainGroup;
   private Spinner mAmpVolumeGroup;
   private Spinner mAppDisableGroup;
   private Spinner mBackviewDisableGroup;
   private Spinner mBackviewLightGroup;
   private Spinner mBatteryVolGroup;
   private Spinner mBoxLed;
   private Spinner mBrightnessGroup;
   private Spinner mBtPanGroup;
   private Hct_Config.ST_FACTORY_CONFIG mConfig = null;
   private Spinner mContrastGroup;
   private Spinner mDefaultPowerStateGroup;
   private Hct_Config.ST_FACTORY_EXT_CONFIG mExtConfig = null;
   private FactoryFun mFactoryFun = null;
   private Spinner mFrontViewDelayGroup;
   private Spinner mFrontViewGroup;
   private Spinner mGpsSelectGroup;
   private Spinner mHueGroup;
   private Spinner mIllBrightnessGroup;
   private Spinner mKnobModeGroup;
   private Spinner mLedKeyModeGroup;
   private Spinner mLedTypeGroup;
   private EditText mModelEdit;
   private Spinner mRadioGainGroup;
   private Spinner mRdsSwitchGroup;
   private Spinner mRearPanelGroup;
   private Spinner mSaturationGroup;
   private Spinner mSubVersionGroup;
   private Spinner mTef6686;
   private Spinner mVolmaxdefaultGroup;
   private Spinner mWheelstudyMenuGroup;

   private void InitViewData() {
      this.mModelEdit.setText((new String(this.mConfig.mModel)).trim());
      this.mModelEdit.setOnEditorActionListener(new TextView.OnEditorActionListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public boolean onEditorAction(TextView var1, int var2, KeyEvent var3) {
            if (var2 == 6) {
               String var4 = this.this$0.mModelEdit.getText().toString();
               this.this$0.mConfig.mModel = new byte[24];

               for(var2 = 0; var2 < var4.length(); ++var2) {
                  this.this$0.mConfig.mModel[var2] = (byte)var4.charAt(var2);
               }
            }

            return false;
         }
      });
      String[] var4 = new String[]{this.getString(2131296430), this.getString(2131296431)};
      ArrayAdapter var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mRdsSwitchGroup.setAdapter(var3);
      this.mRdsSwitchGroup.setSelection(this.mConfig.mRds);
      this.mRdsSwitchGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mRds = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      String var10 = this.getString(2131296364);
      String var5 = this.getString(2131296369);
      var3 = new ArrayAdapter(this.getActivity(), 17367048, new String[]{var10, var5});
      var3.setDropDownViewResource(17367049);
      this.mIllBrightnessGroup.setAdapter(var3);
      this.mIllBrightnessGroup.setSelection(this.mConfig.mIllBrightness);
      this.mIllBrightnessGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mIllBrightness = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      String[] var12 = new String[]{this.getString(2131296364), this.getString(2131296368)};
      ArrayAdapter var13 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var13.setDropDownViewResource(17367049);
      this.mFrontViewGroup.setAdapter(var13);
      this.mFrontViewGroup.setSelection(this.mConfig.mFrontView);
      this.mFrontViewGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mFrontView = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var4 = new String[11];

      int var1;
      for(var1 = 0; var1 < var4.length; ++var1) {
         if (var1 == 0) {
            var4[var1] = this.getString(2131296364);
         } else if (var1 == 1) {
            var4[var1] = this.getString(2131296432);
         } else {
            var4[var1] = this.getString(2131296426, new Object[]{var1});
         }
      }

      ArrayAdapter var14 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var14.setDropDownViewResource(17367049);
      this.mFrontViewDelayGroup.setAdapter(var14);
      this.mFrontViewDelayGroup.setSelection(this.mConfig.mFrontviewDelay);
      this.mFrontViewDelayGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mFrontviewDelay = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var14 = new ArrayAdapter(this.getActivity(), 17367048, var12);
      var14.setDropDownViewResource(17367049);
      this.mAppDisableGroup.setAdapter(var14);
      this.mAppDisableGroup.setSelection(this.mConfig.mAppDisable);
      this.mAppDisableGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mAppDisable = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.mFactoryFun.getParameters("sta_mcu_ver=").compareTo("V3.00") > 0) {
         var12 = new String[]{this.getString(2131296364), "SWC Key", "Can Key"};
      }

      var14 = new ArrayAdapter(this.getActivity(), 17367048, var12);
      var14.setDropDownViewResource(17367049);
      this.mWheelstudyMenuGroup.setAdapter(var14);
      if (this.mConfig.mWheelstudyMenu >= var12.length) {
         this.mConfig.mWheelstudyMenu = 0;
      }

      this.mWheelstudyMenuGroup.setSelection(this.mConfig.mWheelstudyMenu);
      this.mWheelstudyMenuGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mWheelstudyMenu = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var12 = new String[]{this.getString(2131296483), this.getString(2131296467), this.getString(2131296469), this.getString(2131296468), this.getString(2131296466), this.getString(2131296465), this.getString(2131296273)};
      var14 = new ArrayAdapter(this.getActivity(), 17367048, var12);
      var14.setDropDownViewResource(17367049);
      this.mLedTypeGroup.setAdapter(var14);
      if (this.mConfig.mLedType >= var12.length) {
         this.mConfig.mLedType = 0;
      }

      this.mLedTypeGroup.setSelection(this.mConfig.mLedType);
      this.mLedTypeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mLedType = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      String var6 = this.getString(2131296436);
      var5 = this.getString(2131296437);
      var10 = this.getString(2131296438);
      var3 = new ArrayAdapter(this.getActivity(), 17367048, new String[]{var6, var5, var10});
      var3.setDropDownViewResource(17367049);
      this.mLedKeyModeGroup.setAdapter(var3);
      this.mLedKeyModeGroup.setSelection(this.mConfig.mKeyLedMode);
      this.mLedKeyModeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mKeyLedMode = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var5 = this.getString(2131296430);
      var6 = this.getString(2131296431);
      var10 = this.getString(2131296275);
      var3 = new ArrayAdapter(this.getActivity(), 17367048, new String[]{var5, var6, var10});
      var3.setDropDownViewResource(17367049);
      this.mDefaultPowerStateGroup.setAdapter(var3);
      this.mDefaultPowerStateGroup.setSelection(this.mConfig.mDefaultPowerState);
      this.mDefaultPowerStateGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mDefaultPowerState = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var10 = this.mFactoryFun.getParameters("cfg_maxversion=");
      var1 = 1;

      int var2;
      label174: {
         try {
            var2 = Integer.parseInt(var10);
         } catch (Exception var9) {
            break label174;
         }

         var1 = var2;
      }

      String[] var16 = new String[var1];

      for(var2 = 0; var2 < var1; ++var2) {
         var16[var2] = this.getString(2131296429, new Object[]{var2 + 1});
      }

      var13 = new ArrayAdapter(this.getActivity(), 17367048, var16);
      var13.setDropDownViewResource(17367049);
      this.mSubVersionGroup.setAdapter(var13);
      if ((this.mConfig.mSubVersion & 255) >= var16.length) {
         this.mConfig.mSubVersion = 0;
      }

      this.mSubVersionGroup.setSelection(this.mConfig.mSubVersion);
      this.mSubVersionGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mSubVersion = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.mConfig.mBackviewBrightness < -120 || this.mConfig.mBackviewBrightness > 120) {
         this.mConfig.mBackviewBrightness = 0;
      }

      String[] var18 = new String[31];

      StringBuilder var17;
      for(var1 = 0; var1 < 31; ++var1) {
         var17 = new StringBuilder();
         var17.append("");
         var17.append(var1 - 15);
         var18[var1] = var17.toString();
      }

      var3 = new ArrayAdapter(this.getActivity(), 17367048, var18);
      var3.setDropDownViewResource(17367049);
      this.mBackviewLightGroup.setAdapter(var3);
      this.mBackviewLightGroup.setSelection((this.mConfig.mBackviewBrightness + 120) / 8);
      this.mBackviewLightGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mBackviewBrightness = (byte)((var3 - 15) * 8);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });

      byte var11;
      label151: {
         label150: {
            label149: {
               FactoryFun var19;
               try {
                  var19 = this.mFactoryFun;
               } catch (Exception var8) {
                  break label149;
               }

               try {
                  var1 = Integer.parseInt(var19.getParameters("cfg_maxvolume="));
                  break label150;
               } catch (Exception var7) {
               }
            }

            var11 = 0;
            break label151;
         }

         var11 = (byte)var1;
      }

      if (this.mConfig.mVolMaxDefault > var11) {
         this.mConfig.mVolMaxDefault = 0;
      }

      var4 = new String[var11 + 1];

      StringBuilder var20;
      for(var2 = 0; var2 < var11 + 1; ++var2) {
         if (var2 == 0) {
            var4[var2] = "NO";
         } else {
            var20 = new StringBuilder();
            var20.append("");
            var20.append(var2);
            var4[var2] = var20.toString();
         }
      }

      var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mVolmaxdefaultGroup.setAdapter(var3);
      this.mVolmaxdefaultGroup.setSelection(this.mConfig.mVolMaxDefault & 255);
      this.mVolmaxdefaultGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mVolMaxDefault = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var12 = this.getActivity().getResources().getStringArray(2130771976);
      var13 = new ArrayAdapter(this.getActivity(), 17367048, var12);
      var13.setDropDownViewResource(17367049);
      this.mKnobModeGroup.setAdapter(var13);
      if ((this.mConfig.mKnobMode & 255) >= var12.length) {
         this.mConfig.mKnobMode = 0;
      }

      this.mKnobModeGroup.setSelection(this.mConfig.mKnobMode & 255);
      this.mKnobModeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mKnobMode = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var10 = this.getString(2131296368);
      String var15 = this.getString(2131296364);
      var3 = new ArrayAdapter(this.getActivity(), 17367048, new String[]{var10, var15});
      var3.setDropDownViewResource(17367049);
      this.mBackviewDisableGroup.setAdapter(var3);
      this.mBackviewDisableGroup.setSelection(this.mConfig.mBackviewDisable & 255);
      this.mBackviewDisableGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mBackviewDisable = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var12 = new String[]{" 0db", " -3db", " -6db", " -9db", " -12db", " -15db"};
      var13 = new ArrayAdapter(this.getActivity(), 17367048, var12);
      var13.setDropDownViewResource(17367049);
      this.mAmpVolumeGroup.setAdapter(var13);
      this.mAmpVolumeGroup.setSelection(this.mConfig.mAmpVolume & 255);
      this.mAmpVolumeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mAmpVolume = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var18 = new String[21];

      for(var1 = 0; var1 < var18.length; ++var1) {
         var17 = new StringBuilder();
         var17.append(var1 + 20);
         var17.append(" db");
         var18[var1] = var17.toString();
      }

      var3 = new ArrayAdapter(this.getActivity(), 17367048, var18);
      var3.setDropDownViewResource(17367049);
      this.mRadioGainGroup.setAdapter(var3);
      this.mRadioGainGroup.setSelection(this.mConfig.mRadioGain & 255);
      this.mRadioGainGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mRadioGain = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var3 = new ArrayAdapter(this.getActivity(), 17367048, new String[]{"NO", "YES"});
      var3.setDropDownViewResource(17367049);
      this.m3PartyCallGainGroup.setAdapter(var3);
      this.m3PartyCallGainGroup.setSelection(this.mConfig.m3PartyCall & 255);
      this.m3PartyCallGainGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.m3PartyCall = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var4 = new String[]{"12 V", "24 V"};
      var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mBatteryVolGroup.setAdapter(var3);
      if (this.mConfig.mBattVol >= var4.length) {
         this.mConfig.mBattVol = 0;
      }

      this.mBatteryVolGroup.setSelection(this.mConfig.mBattVol & 255);
      this.mBatteryVolGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mBattVol = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var3 = new ArrayAdapter(this.getActivity(), 17367048, Factory_Util.rearpaneltype);
      var3.setDropDownViewResource(17367049);
      this.mRearPanelGroup.setAdapter(var3);
      if (this.mConfig.mRearPanel >= Factory_Util.rearpaneltype.length) {
         this.mConfig.mRearPanel = 0;
      }

      this.mRearPanelGroup.setSelection(this.mConfig.mRearPanel & 255);
      this.mRearPanelGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mRearPanel = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.mConfig.mContrast < -30 || this.mConfig.mContrast > 30) {
         this.mConfig.mContrast = 0;
      }

      var4 = new String[61];

      for(var1 = 0; var1 < var4.length; ++var1) {
         var20 = new StringBuilder();
         var20.append("");
         var20.append(var1 + 20);
         var4[var1] = var20.toString();
      }

      var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mContrastGroup.setAdapter(var3);
      this.mContrastGroup.setSelection(this.mConfig.mContrast + 30);
      this.mContrastGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mContrast = (byte)(var3 - 30);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.mConfig.mSaturation < -30 || this.mConfig.mSaturation > 30) {
         this.mConfig.mSaturation = 0;
      }

      var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mSaturationGroup.setAdapter(var3);
      this.mSaturationGroup.setSelection(this.mConfig.mSaturation + 30);
      this.mSaturationGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mSaturation = (byte)(var3 - 30);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.mConfig.mBrightness < -30 || this.mConfig.mBrightness > 30) {
         this.mConfig.mBrightness = 0;
      }

      var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mBrightnessGroup.setAdapter(var3);
      this.mBrightnessGroup.setSelection(this.mConfig.mBrightness + 30);
      this.mBrightnessGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mBrightness = (byte)(var3 - 30);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      if (this.mConfig.mHue < -30 || this.mConfig.mHue > 30) {
         this.mConfig.mHue = 0;
      }

      var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mHueGroup.setAdapter(var3);
      this.mHueGroup.setSelection(this.mConfig.mHue + 30);
      this.mHueGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mConfig.mHue = (byte)(var3 - 30);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var3 = new ArrayAdapter(this.getActivity(), 17367048, new String[]{"NO", "YES"});
      var3.setDropDownViewResource(17367049);
      this.mBtPanGroup.setAdapter(var3);
      this.mBtPanGroup.setSelection(this.mConfig.mExtCfg & 1);
      this.mBtPanGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            Hct_Config.ST_FACTORY_CONFIG var6;
            if (var3 == 0) {
               var6 = this.this$0.mConfig;
               var6.mExtCfg &= -2;
            } else if (var3 == 1) {
               var6 = this.this$0.mConfig;
               var6.mExtCfg |= (byte)var3;
            }

         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var12 = new String[]{"GPS+GLONASS", "GPS+BD", "BD+GLONASS"};
      var13 = new ArrayAdapter(this.getActivity(), 17367048, var12);
      var13.setDropDownViewResource(17367049);
      this.mGpsSelectGroup.setAdapter(var13);
      if ((this.mConfig.mExtCfg >> 3 & 3) < var12.length) {
         this.mGpsSelectGroup.setSelection(this.mConfig.mExtCfg >> 3 & 3);
      }

      this.mGpsSelectGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            Hct_Config.ST_FACTORY_CONFIG var6 = this.this$0.mConfig;
            var6.mExtCfg &= -25;
            var6 = this.this$0.mConfig;
            var6.mExtCfg = (byte)(var6.mExtCfg | var3 << 3);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var12 = new String[]{"NO", "4M", "9M"};
      var13 = new ArrayAdapter(this.getActivity(), 17367048, var12);
      var13.setDropDownViewResource(17367049);
      this.mTef6686.setAdapter(var13);
      if (this.mExtConfig.mTef6686_EXT >= var12.length) {
         this.mExtConfig.mTef6686_EXT = 0;
      }

      this.mTef6686.setSelection(this.mExtConfig.mTef6686_EXT & 255);
      this.mTef6686.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mExtConfig.mTef6686_EXT = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var4 = new String[]{this.getString(2131296465), this.getString(2131296273)};
      var3 = new ArrayAdapter(this.getActivity(), 17367048, var4);
      var3.setDropDownViewResource(17367049);
      this.mBoxLed.setAdapter(var3);
      if (this.mExtConfig.mBoxLed >= var4.length) {
         this.mExtConfig.mBoxLed = 0;
      }

      this.mBoxLed.setSelection(this.mExtConfig.mBoxLed & 255);
      this.mBoxLed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final OtherFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mExtConfig.mBoxLed = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
   }

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
      this.mConfig = this.mFactoryFun.getFactoryConfig();
      this.mExtConfig = this.mFactoryFun.getFactoryExtConfig();
      this.InitViewData();
      if ("1".equals(this.mFactoryFun.getParameters("sta_radio_chip="))) {
         this.getView().findViewById(2131099872).setVisibility(0);
      }

      if ("BOX".equals(RkSystemProp.get("ro.product.project", ""))) {
         this.getView().findViewById(2131099688).setVisibility(0);
      }

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
      View var4 = var1.inflate(2131230736, var2, false);
      this.mRdsSwitchGroup = (Spinner)var4.findViewById(2131099836);
      this.mIllBrightnessGroup = (Spinner)var4.findViewById(2131099690);
      this.mFrontViewGroup = (Spinner)var4.findViewById(2131099769);
      this.mFrontViewDelayGroup = (Spinner)var4.findViewById(2131099768);
      this.mAppDisableGroup = (Spinner)var4.findViewById(2131099747);
      this.mWheelstudyMenuGroup = (Spinner)var4.findViewById(2131099943);
      this.mLedTypeGroup = (Spinner)var4.findViewById(2131099736);
      this.mLedKeyModeGroup = (Spinner)var4.findViewById(2131099820);
      this.mDefaultPowerStateGroup = (Spinner)var4.findViewById(2131099824);
      this.mSubVersionGroup = (Spinner)var4.findViewById(2131099740);
      this.mModelEdit = (EditText)var4.findViewById(2131099757);
      this.mBackviewLightGroup = (Spinner)var4.findViewById(2131099671);
      this.mVolmaxdefaultGroup = (Spinner)var4.findViewById(2131099941);
      this.mKnobModeGroup = (Spinner)var4.findViewById(2131099789);
      this.mBackviewDisableGroup = (Spinner)var4.findViewById(2131099670);
      this.mAmpVolumeGroup = (Spinner)var4.findViewById(2131099656);
      this.mRadioGainGroup = (Spinner)var4.findViewById(2131099828);
      this.m3PartyCallGainGroup = (Spinner)var4.findViewById(2131099885);
      this.mBatteryVolGroup = (Spinner)var4.findViewById(2131099674);
      this.mRearPanelGroup = (Spinner)var4.findViewById(2131099837);
      this.mContrastGroup = (Spinner)var4.findViewById(2131099737);
      this.mSaturationGroup = (Spinner)var4.findViewById(2131099844);
      this.mBrightnessGroup = (Spinner)var4.findViewById(2131099689);
      this.mHueGroup = (Spinner)var4.findViewById(2131099777);
      this.mBtPanGroup = (Spinner)var4.findViewById(2131099691);
      this.mGpsSelectGroup = (Spinner)var4.findViewById(2131099771);
      this.mTef6686 = (Spinner)var4.findViewById(2131099871);
      this.mBoxLed = (Spinner)var4.findViewById(2131099687);
      return var4;
   }

   public void onDestroy() {
      super.onDestroy();
   }

   public void onResume() {
      super.onResume();
   }
}
