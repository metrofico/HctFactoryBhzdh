package com.microntek.controlsettings;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus58drivingMode extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private static boolean topFlag;
   private HCheckBoxPreference mComfort;
   private PreferenceScreen mCross;
   private SwitchboxPreference mCross_adaptive;
   private SwitchboxPreference mCross_air;
   private HCheckBoxPreference mCross_country;
   private HCheckBoxPreference mCross_downhill;
   private SwitchboxPreference mCross_engine;
   private SwitchboxPreference mCross_follow;
   private HCheckBoxPreference mCross_parking_assist;
   private HCheckBoxPreference mCross_ramp;
   private SwitchboxPreference mCross_steering;
   private SwitchboxPreference mCross_wheel_drive;
   private HCheckBoxPreference mDriv_eco;
   private Handler mHandler = new Handler(this) {
      final canbus58drivingMode this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 0) {
            this.this$0.finish();
         }

      }
   };
   private PreferenceScreen mIndividual;
   private SwitchboxPreference mIndividual_acc;
   private SwitchboxPreference mIndividual_air;
   private SwitchboxPreference mIndividual_dcc;
   private SwitchboxPreference mIndividual_dynamic;
   private SwitchboxPreference mIndividual_engine;
   private PreferenceScreen mIndividual_reset;
   private SwitchboxPreference mIndividual_steering;
   private HCheckBoxPreference mNormal;
   private HCheckBoxPreference mSnow_land;
   private HCheckBoxPreference mSport;

   private void SetCmdkey(byte var1, byte var2) {
      this.SetCmdkey(var1, var2, (byte)0);
   }

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData5AA5((byte)-117, new byte[]{var1, var2, var3}, 3);
   }

   private void sendMsgFinish() {
      this.mHandler.removeMessages(0);
      if (this.getIntent().getIntExtra("cftype", 0) == 1) {
         this.mHandler.sendEmptyMessageDelayed(0, 6000L);
      }

   }

   protected void ProcessData(byte[] var1) {
      boolean var6 = false;
      boolean var5 = false;
      int var2 = var1[0] & 255;
      boolean var4;
      SwitchboxPreference var7;
      byte var9;
      PreferenceScreen var10;
      SwitchboxPreference var11;
      HCheckBoxPreference var12;
      StringBuilder var14;
      StringBuilder var15;
      if (var2 != 134) {
         if (var2 == 135) {
            PreferenceScreen var8 = this.mIndividual;
            var7 = this.mIndividual_dcc;
            if ((var1[6] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var8, var7, var4);
            var8 = this.mIndividual;
            var7 = this.mIndividual_dynamic;
            if ((var1[6] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var8, var7, var4);
            var10 = this.mIndividual;
            var11 = this.mIndividual_engine;
            if ((var1[6] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var10 = this.mIndividual;
            var11 = this.mIndividual_acc;
            if ((var1[6] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var8 = this.mIndividual;
            var7 = this.mIndividual_air;
            if ((var1[6] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var8, var7, var4);
            var8 = this.mIndividual;
            var7 = this.mIndividual_steering;
            if ((var1[6] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var8, var7, var4);
            var8 = this.mIndividual;
            var10 = this.mIndividual_reset;
            if ((var1[6] & 252) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var8, var10, var4);
            var10 = this.mCross;
            var11 = this.mCross_steering;
            if ((var1[7] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var10 = this.mCross;
            var11 = this.mCross_engine;
            if ((var1[7] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var10 = this.mCross;
            var11 = this.mCross_wheel_drive;
            if ((var1[7] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var10 = this.mCross;
            var11 = this.mCross_adaptive;
            if ((var1[7] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var10 = this.mCross;
            var11 = this.mCross_follow;
            if ((var1[7] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var10 = this.mCross;
            var11 = this.mCross_air;
            if ((var1[7] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var11, var4);
            var10 = this.mCross;
            HCheckBoxPreference var13 = this.mCross_downhill;
            if ((var1[7] & 2) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var13, var4);
            var10 = this.mCross;
            var13 = this.mCross_ramp;
            if ((var1[7] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var10, var13, var4);
            var8 = this.mCross;
            var12 = this.mCross_parking_assist;
            if ((var1[8] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreferenceGroup(var8, var12, var4);
            var9 = var1[5];
            var11 = this.mCross_steering;
            var14 = new StringBuilder();
            var14.append("");
            var14.append(var9 >> 6 & 1);
            var11.setValue(var14.toString());
            var9 = var1[4];
            var7 = this.mCross_engine;
            var15 = new StringBuilder();
            var15.append("");
            var15.append(var9 >> 2 & 1);
            var7.setValue(var15.toString());
            var9 = var1[5];
            var11 = this.mCross_wheel_drive;
            var14 = new StringBuilder();
            var14.append("");
            var14.append(var9 >> 5 & 1);
            var11.setValue(var14.toString());
            var9 = var1[4];
            var7 = this.mCross_adaptive;
            var15 = new StringBuilder();
            var15.append("");
            var15.append(var9 >> 0 & 3);
            var7.setValue(var15.toString());
            var9 = var1[4];
            var7 = this.mCross_follow;
            var15 = new StringBuilder();
            var15.append("");
            var15.append(var9 >> 4 & 3);
            var7.setValue(var15.toString());
            var9 = var1[5];
            var11 = this.mCross_air;
            var14 = new StringBuilder();
            var14.append("");
            var14.append(var9 >> 7 & 1);
            var11.setValue(var14.toString());
            var12 = this.mCross_downhill;
            if ((var1[5] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var12.setChecked(var4);
            var12 = this.mCross_ramp;
            if ((var1[5] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var12.setChecked(var4);
            var12 = this.mCross_parking_assist;
            var4 = var5;
            if ((var1[5] & 4) != 0) {
               var4 = true;
            }

            var12.setChecked(var4);
         }
      } else {
         var12 = this.mComfort;
         if ((var1[2] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var12, var4);
         var12 = this.mNormal;
         if ((var1[2] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var12, var4);
         var12 = this.mSport;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var12, var4);
         var12 = this.mDriv_eco;
         if ((var1[2] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var12, var4);
         var10 = this.mIndividual;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var10, var4);
         var12 = this.mSnow_land;
         if ((var1[2] & 4) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var12, var4);
         var12 = this.mCross_country;
         if ((var1[2] & 2) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var12, var4);
         var10 = this.mCross;
         if ((var1[2] & 1) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var10, var4);
         var12 = this.mComfort;
         if ((var1[3] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var12.setChecked(var4);
         var12 = this.mNormal;
         if ((var1[3] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var12.setChecked(var4);
         var12 = this.mSport;
         if ((var1[3] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var12.setChecked(var4);
         var12 = this.mDriv_eco;
         if ((var1[3] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var12.setChecked(var4);
         var10 = this.mIndividual;
         var9 = var1[3];
         int var3 = 2131296511;
         if ((var9 & 8) != 0) {
            var2 = 2131296959;
         } else {
            var2 = 2131296511;
         }

         var10.setSummary(var2);
         var9 = var1[4];
         var11 = this.mIndividual_dcc;
         var14 = new StringBuilder();
         var14.append("");
         var14.append(var9 >> 6 & 3);
         var11.setValue(var14.toString());
         var9 = var1[4];
         var11 = this.mIndividual_dynamic;
         var14 = new StringBuilder();
         var14.append("");
         var14.append(var9 >> 4 & 3);
         var11.setValue(var14.toString());
         var9 = var1[4];
         var7 = this.mIndividual_engine;
         var15 = new StringBuilder();
         var15.append("");
         var15.append(var9 >> 2 & 3);
         var7.setValue(var15.toString());
         var9 = var1[4];
         var7 = this.mIndividual_acc;
         var15 = new StringBuilder();
         var15.append("");
         var15.append(var9 >> 0 & 3);
         var7.setValue(var15.toString());
         var9 = var1[5];
         var7 = this.mIndividual_air;
         var15 = new StringBuilder();
         var15.append("");
         var15.append(var9 >> 7 & 1);
         var7.setValue(var15.toString());
         var9 = var1[5];
         var7 = this.mIndividual_steering;
         var15 = new StringBuilder();
         var15.append("");
         var15.append(var9 >> 6 & 1);
         var7.setValue(var15.toString());
         var12 = this.mSnow_land;
         if ((var1[3] & 4) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var12.setChecked(var4);
         var12 = this.mCross_country;
         var4 = var6;
         if ((var1[3] & 2) != 0) {
            var4 = true;
         }

         var12.setChecked(var4);
         var10 = this.mCross;
         var2 = var3;
         if ((var1[3] & 1) != 0) {
            var2 = 2131296959;
         }

         var10.setSummary(var2);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492929);
      this.mComfort = this.findHCheckBoxPreference("comfort_mode");
      this.mNormal = this.findHCheckBoxPreference("normal");
      this.mSport = this.findHCheckBoxPreference("sport");
      this.mDriv_eco = this.findHCheckBoxPreference("driv_eco");
      this.mSnow_land = this.findHCheckBoxPreference("snow_land");
      this.mCross_country = this.findHCheckBoxPreference("cross_country");
      this.mCross = this.findPreferenceScreen("cross");
      this.mCross_steering = this.findSwitchboxPreference("cross_steering");
      this.mCross_engine = this.findSwitchboxPreference("cross_engine");
      this.mCross_wheel_drive = this.findSwitchboxPreference("cross_wheel_drive");
      this.mCross_adaptive = this.findSwitchboxPreference("cross_adaptive");
      this.mCross_follow = this.findSwitchboxPreference("cross_follow");
      this.mCross_air = this.findSwitchboxPreference("cross_air");
      this.mCross_downhill = this.findHCheckBoxPreference("cross_downhill");
      this.mCross_ramp = this.findHCheckBoxPreference("cross_ramp");
      this.mCross_parking_assist = this.findHCheckBoxPreference("cross_parking_assist");
      this.mIndividual = this.findPreferenceScreen("individual");
      this.mIndividual_dcc = this.findSwitchboxPreference("individual_dcc");
      this.mIndividual_dynamic = this.findSwitchboxPreference("individual_dynamic");
      this.mIndividual_engine = this.findSwitchboxPreference("individual_engine");
      this.mIndividual_acc = this.findSwitchboxPreference("individual_acc");
      this.mIndividual_air = this.findSwitchboxPreference("individual_air");
      this.mIndividual_steering = this.findSwitchboxPreference("individual_steering");
      this.mIndividual_reset = this.findPreferenceScreen("individual_reset");
      this.ProcessData(this.GetCarByteArrayState(134));
      this.ProcessData(this.GetCarByteArrayState(135));
      this.SendCanBusCmdData5AA5((byte)10, new byte[]{1, -122}, 2);
      this.SendCanBusCmdData5AA5((byte)10, new byte[]{1, -121}, 2);
      this.sendMsgFinish();
   }

   protected void onDestroy() {
      this.mHandler.removeCallbacksAndMessages((Object)null);
      super.onDestroy();
   }

   protected void onNewIntent(Intent var1) {
      super.onNewIntent(var1);
      this.setIntent(var1);
      if (topFlag && var1.getIntExtra("cftype", 0) == 1) {
         this.finish();
      }

      this.sendMsgFinish();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      this.sendMsgFinish();

      int var3;
      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var4;
      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var5) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mIndividual_dcc) {
            this.SetCmdkey((byte)5, (byte)1, (byte)var3);
         } else if (var1 == this.mIndividual_dynamic) {
            this.SetCmdkey((byte)5, (byte)2, (byte)var3);
         } else if (var1 == this.mIndividual_engine) {
            this.SetCmdkey((byte)5, (byte)3, (byte)var3);
         } else if (var1 == this.mIndividual_acc) {
            this.SetCmdkey((byte)5, (byte)4, (byte)var3);
         } else if (var1 == this.mIndividual_air) {
            this.SetCmdkey((byte)5, (byte)5, (byte)var3);
         } else if (var1 == this.mIndividual_steering) {
            this.SetCmdkey((byte)5, (byte)6, (byte)var3);
         } else if (var1 == this.mCross_steering) {
            this.SetCmdkey((byte)9, (byte)1, (byte)var3);
         } else if (var1 == this.mCross_engine) {
            this.SetCmdkey((byte)9, (byte)2, (byte)var3);
         } else if (var1 == this.mCross_wheel_drive) {
            this.SetCmdkey((byte)9, (byte)3, (byte)var3);
         } else if (var1 == this.mCross_adaptive) {
            this.SetCmdkey((byte)9, (byte)4, (byte)var3);
         } else if (var1 == this.mCross_follow) {
            this.SetCmdkey((byte)9, (byte)5, (byte)var3);
         } else if (var1 == this.mCross_air) {
            this.SetCmdkey((byte)9, (byte)6, (byte)var3);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      this.sendMsgFinish();
      boolean var5;
      if (var1 instanceof HCheckBoxPreference) {
         var5 = ((HCheckBoxPreference)var1).mChecked;
      } else {
         var5 = false;
      }

      HCheckBoxPreference var6 = this.mComfort;
      byte var2 = 1;
      byte var4 = 1;
      byte var3 = 1;
      if (var1 == var6) {
         this.SetCmdkey((byte)1, (byte)0);
      } else if (var1 == this.mNormal) {
         this.SetCmdkey((byte)2, (byte)0);
      } else if (var1 == this.mSport) {
         this.SetCmdkey((byte)3, (byte)0);
      } else if (var1 == this.mDriv_eco) {
         this.SetCmdkey((byte)4, (byte)0);
      } else if (var1 == this.mSnow_land) {
         this.SetCmdkey((byte)6, (byte)0);
      } else if (var1 == this.mCross_country) {
         this.SetCmdkey((byte)8, (byte)0);
      } else if (var1 == this.mCross_downhill) {
         var2 = var3;
         if (var5) {
            var2 = 0;
         }

         this.SetCmdkey((byte)9, (byte)7, (byte)var2);
      } else if (var1 == this.mCross_ramp) {
         if (var5) {
            var2 = 0;
         }

         this.SetCmdkey((byte)9, (byte)8, (byte)var2);
      } else if (var1 == this.mCross_parking_assist) {
         var2 = var4;
         if (var5) {
            var2 = 0;
         }

         this.SetCmdkey((byte)9, (byte)9, (byte)var2);
      }

      return false;
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      this.sendMsgFinish();
      if (var2 == this.mIndividual_reset) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus58drivingMode this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)7, (byte)0);
            }
         }, this.mIndividual_reset.getTitle().toString());
      } else if (var2 == this.mIndividual) {
         this.SetCmdkey((byte)5, (byte)0);
      } else if (var2 == this.mCross) {
         this.SetCmdkey((byte)9, (byte)0);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }

   protected void onResume() {
      super.onResume();
      topFlag = true;
   }

   protected void onStop() {
      super.onStop();
      topFlag = false;
   }
}
