package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus100settings extends Base2PreferenceActivity {
   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   private void SetCmdkey2(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-123, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 82) {
         int var2 = var1[2];
         boolean var40 = false;
         boolean var43 = false;
         boolean var37 = false;
         boolean var39 = false;
         boolean var21 = false;
         boolean var29 = false;
         boolean var34 = false;
         boolean var26 = false;
         boolean var35 = false;
         boolean var44 = false;
         boolean var15 = false;
         boolean var45 = false;
         boolean var20 = false;
         boolean var24 = false;
         boolean var8 = false;
         boolean var12 = false;
         boolean var33 = false;
         boolean var22 = false;
         boolean var19 = false;
         boolean var4 = false;
         boolean var30 = false;
         boolean var27 = false;
         boolean var36 = false;
         boolean var38 = false;
         boolean var6 = false;
         boolean var5 = false;
         boolean var18 = false;
         boolean var41 = false;
         boolean var9 = false;
         boolean var42 = false;
         boolean var32 = false;
         boolean var25 = false;
         boolean var3 = false;
         boolean var17 = false;
         boolean var11 = false;
         boolean var23 = false;
         boolean var14 = false;
         boolean var13 = false;
         boolean var31 = false;
         boolean var16 = false;
         boolean var10 = false;
         boolean var28 = false;
         boolean var7 = false;
         HCheckBoxPreference var46;
         switch (var2 & 255) {
            case 1:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "rear_view");
               var3 = var28;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 2:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "wiperssettings");
               var3 = var10;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 3:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "remote_unlock");
               var3 = var16;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 4:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "driving_auto");
               var3 = var31;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 5:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "flameout_lock");
               var3 = var13;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 6:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "wipers_window");
               var3 = var14;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 7:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "unlock_ventilation");
               var3 = var23;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 8:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "unlock_air");
               var3 = var11;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 9:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "air_drying");
               var3 = var17;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 10:
               this.findSwitchboxPreferenceScreen("car_settings", "headlight_off").setValue(var1[3] & 15);
               break;
            case 11:
               this.findSwitchboxPreferenceScreen("car_settings", "one_steering").setValue(var1[3] & 15);
            case 12:
            case 57:
            case 68:
            case 69:
            case 70:
            case 74:
            default:
               break;
            case 13:
               this.findSwitchboxPreferenceScreen("vol", "welcome_voice").setValue((var1[3] & 3) - 1);
               break;
            case 14:
               this.findSwitchboxPreferenceScreen("vol", "prompt_sound").setValue((var1[3] & 3) - 1);
               break;
            case 15:
               this.findSwitchboxPreferenceScreen("vol", "alarm_volume").setValue((var1[3] & 3) - 1);
               break;
            case 16:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "parkunlock");
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 17:
               var46 = this.findHCheckBoxPreferenceScreen("ari_cmd", "ion_purifier");
               var3 = var25;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 18:
               this.findSwitchboxPreferenceScreen("car_settings", "window_delay").setValue(var1[3] & 3);
               break;
            case 19:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "window_lamp");
               var3 = var32;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 20:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "window_remote");
               var3 = var42;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 21:
               this.findSwitchboxPreferenceScreen("car_settings", "sound_horn").setValue((var1[3] & 15) - 1);
               break;
            case 22:
               this.findSwitchboxPreferenceScreen("car_settings", "remoteunlock").setValue((var1[3] & 3) - 1);
               break;
            case 23:
               var46 = this.findHCheckBoxPreferenceScreen("ari_cmd", "bt_speed");
               var3 = var9;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 24:
               var46 = this.findHCheckBoxPreferenceScreen("light_cmd", "running_lights");
               var3 = var41;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 25:
               var46 = this.findHCheckBoxPreferenceScreen("light_cmd", "car_in_light");
               var3 = var18;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 26:
               var46 = this.findHCheckBoxPreferenceScreen("light_cmd", "automatic_lamp");
               var3 = var5;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 27:
               var46 = this.findHCheckBoxPreferenceScreen("cruise_control", "adaptive_cruise");
               var3 = var6;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 28:
               this.findSwitchboxPreferenceScreen("cruise_control", "target_vol").setValue(var1[3] & 3);
               break;
            case 29:
               var46 = this.findHCheckBoxPreferenceScreen("forward_system", "for_warning");
               var3 = var38;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 30:
               this.findSwitchboxPreferenceScreen("forward_system", "collision_sens").setValue(var1[3] & 3);
               break;
            case 31:
               var46 = this.findHCheckBoxPreferenceScreen("forward_system", "auto_braking");
               var3 = var36;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 32:
               this.findSwitchboxPreferenceScreen("lanesystem", "sensitivity").setValue(var1[3] & 3);
               break;
            case 33:
               this.findSwitchboxPreferenceScreen("lanesystem", "selection").setValue((var1[3] & 15) - 1);
               break;
            case 34:
               this.findSwitchboxPreferenceScreen("lanesystem", "warning").setValue((var1[3] & 3) - 1);
               break;
            case 35:
               var46 = this.findHCheckBoxPreferenceScreen("lanesystem", "speed_ign");
               var3 = var27;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 36:
               var46 = this.findHCheckBoxPreferenceScreen("lanesystem", "over_speed");
               var3 = var30;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 37:
               var46 = this.findHCheckBoxPreferenceScreen("avm_settings", "low_speed");
               var3 = var4;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 38:
               var46 = this.findHCheckBoxPreferenceScreen("avm_settings", "front_radar");
               var3 = var19;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 39:
               this.findSwitchboxPreferenceScreen("light_cmd", "light_brightness").setValue(var1[3] & 7);
               break;
            case 40:
               this.findSwitchboxPreferenceScreen("lanesystem", "vibration_intensity").setValue(var1[3] & 3);
               break;
            case 41:
               this.findSwitchboxPreferenceScreen("lanesystem", "overspeed").setValue(var1[3] & 31);
               break;
            case 42:
               var46 = this.findHCheckBoxPreferenceScreen("lanesystem", "overspeed_vol");
               var3 = var22;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 43:
               var46 = this.findHCheckBoxPreferenceScreen("rear_warning", "doubling");
               var3 = var33;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 44:
               var46 = this.findHCheckBoxPreferenceScreen("rear_warning", "reverse_warning");
               var3 = var12;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 45:
               var46 = this.findHCheckBoxPreferenceScreen("rear_warning", "rear_end_warning");
               var3 = var8;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 46:
               var46 = this.findHCheckBoxPreferenceScreen("rear_warning", "rear_warning_voice");
               var3 = var24;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 47:
               var46 = this.findHCheckBoxPreferenceScreen("skylight", "voice_skylight");
               var3 = var20;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 48:
               var46 = this.findHCheckBoxPreferenceScreen("skylight", "remote_skylight");
               var3 = var45;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 49:
               var46 = this.findHCheckBoxPreferenceScreen("skylight", "rain_skylight");
               var3 = var15;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 50:
               this.findSwitchboxPreferenceScreen("light_cmd", "light_color").setValue((var1[3] & 7) - 1);
               break;
            case 51:
               var46 = this.findHCheckBoxPreferenceScreen("right_area", "right_low_speed");
               var3 = var44;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 52:
               var46 = this.findHCheckBoxPreferenceScreen("right_area", "right_front_radar");
               var3 = var35;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 53:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "driving_memory");
               var3 = var26;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 54:
               var46 = this.findHCheckBoxPreferenceScreen("light_cmd", "light_auto");
               var3 = var34;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 55:
               this.findSwitchboxPreferenceScreen("light_cmd", "light_auto_mode").setValue((var1[3] & 3) - 1);
               break;
            case 56:
               this.findSwitchboxPreferenceScreen("other", "instrument_volume").setValue((var1[3] & 3) - 1);
               break;
            case 58:
               this.findSwitchboxPreferenceScreen("other", "instrument_style").setValue((var1[3] & 7) - 1);
               break;
            case 59:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "two_locks");
               var3 = var29;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 60:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "welcome_light");
               var3 = var21;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 61:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "clos_door_window");
               var3 = var39;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 62:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "driving_unlock");
               var3 = var37;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 63:
               this.findSwitchboxPreferenceScreen("driving_mode", "dynamic_mode").setValue((var1[3] & 7) - 1);
               break;
            case 64:
               this.findSwitchboxPreferenceScreen("driving_mode", "steering_mode").setValue((var1[3] & 7) - 1);
               break;
            case 65:
               this.findSwitchboxPreferenceScreen("quick_control", "car_window").setValue((var1[3] & 7) - 1);
               break;
            case 66:
               this.findSwitchboxPreferenceScreen("quick_control", "driving_mode").setValue((var1[3] & 7) - 1);
               break;
            case 67:
               this.findSwitchboxPreferenceScreen("ari_cmd", "air_conditioning").setValue((var1[3] & 7) - 1);
               break;
            case 71:
               var46 = this.findHCheckBoxPreferenceScreen("light_cmd", "car_in_light47");
               var3 = var43;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 72:
               var46 = this.findHCheckBoxPreferenceScreen("light_cmd", "light_music");
               var3 = var40;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
               break;
            case 73:
               var46 = this.findHCheckBoxPreferenceScreen("car_settings", "wireless_charging");
               var3 = var7;
               if ((var1[3] & 1) > 0) {
                  var3 = true;
               }

               var46.setChecked(var3);
         }
      }

   }

   protected int getPreferencesResId() {
      return 2131492864;
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      HCheckBoxPreference var6 = this.findHCheckBoxPreference("maintenance");
      int var2 = 0;
      boolean var3;
      if ((this.getInt("mMaintenance", 0) & 128) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var6.setChecked(var3);
      SwitchboxPreference var7 = this.findSwitchboxPreference("parking_mode");
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(this.getInt("mParking_mode", 0));
      var7.setValue(var4.toString());
      CharSequence[] var8 = new CharSequence[21];

      CharSequence[] var9;
      for(var9 = new CharSequence[21]; var2 < var8.length; ++var2) {
         var9[var2] = Integer.toString(var2);
         StringBuilder var5 = new StringBuilder();
         var5.append(Integer.toString(var2 - 10));
         var5.append(" KM/H");
         var8[var2] = var5.toString();
      }

      SwitchboxPreference var10 = this.findSwitchboxPreference("overspeed");
      var10.setEntries(var8);
      var10.setEntryValues(var9);
      (new Thread(new Runnable(this) {
         final canbus100settings this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            int var1 = 0;

            while(true) {
               this.this$0.SendCanBusCmdData2E((byte)-112, new byte[]{82, (byte)var1}, 2);

               try {
                  Thread.sleep(20L);
               } catch (InterruptedException var3) {
                  var3.printStackTrace();
               }

               if (var1 >= 80) {
                  return;
               }

               ++var1;
            }
         }
      })).start();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var3;
      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var5) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         byte var10;
         label136: {
            switch (var8) {
               case "parking_mode":
                  var10 = 0;
                  break label136;
               case "selection":
                  var10 = 12;
                  break label136;
               case "light_brightness":
                  var10 = 14;
                  break label136;
               case "prompt_sound":
                  var10 = 4;
                  break label136;
               case "window_delay":
                  var10 = 6;
                  break label136;
               case "steering_mode":
                  var10 = 22;
                  break label136;
               case "overspeed":
                  var10 = 16;
                  break label136;
               case "driving_mode":
                  var10 = 24;
                  break label136;
               case "instrument_style":
                  var10 = 20;
                  break label136;
               case "sound_horn":
                  var10 = 7;
                  break label136;
               case "collision_sens":
                  var10 = 10;
                  break label136;
               case "headlight_off":
                  var10 = 1;
                  break label136;
               case "alarm_volume":
                  var10 = 5;
                  break label136;
               case "one_steering":
                  var10 = 2;
                  break label136;
               case "vibration_intensity":
                  var10 = 15;
                  break label136;
               case "target_vol":
                  var10 = 9;
                  break label136;
               case "sensitivity":
                  var10 = 11;
                  break label136;
               case "dynamic_mode":
                  var10 = 21;
                  break label136;
               case "air_conditioning":
                  var10 = 25;
                  break label136;
               case "light_auto_mode":
                  var10 = 18;
                  break label136;
               case "remoteunlock":
                  var10 = 8;
                  break label136;
               case "welcome_voice":
                  var10 = 3;
                  break label136;
               case "warning":
                  var10 = 13;
                  break label136;
               case "car_window":
                  var10 = 23;
                  break label136;
               case "instrument_volume":
                  var10 = 19;
                  break label136;
               case "light_color":
                  var10 = 17;
                  break label136;
            }

            var10 = -1;
         }

         switch (var10) {
            case 0:
               var3 = var4 + 1;
               this.SetCmdkey2((byte)var3, (byte)this.getInt("mMaintenance", 0));
               SwitchboxPreference var9 = (SwitchboxPreference)var1;
               StringBuilder var7 = new StringBuilder();
               var7.append("");
               var7.append(var4);
               var9.setValue(var7.toString());
               this.putInt("mParking_mode", var3);
               break;
            case 1:
               this.SetCmdkey(10, var4);
               break;
            case 2:
               this.SetCmdkey(11, var4);
               break;
            case 3:
               this.SetCmdkey(13, var4 + 1);
               break;
            case 4:
               this.SetCmdkey(14, var4 + 1);
               break;
            case 5:
               this.SetCmdkey(15, var4 + 1);
               break;
            case 6:
               this.SetCmdkey(18, var4);
               break;
            case 7:
               this.SetCmdkey(21, var4 + 1);
               break;
            case 8:
               this.SetCmdkey(22, var4 + 1);
               break;
            case 9:
               this.SetCmdkey(28, var4);
               break;
            case 10:
               this.SetCmdkey(30, var4);
               break;
            case 11:
               this.SetCmdkey(32, var4);
               break;
            case 12:
               this.SetCmdkey(33, var4 + 1);
               break;
            case 13:
               this.SetCmdkey(34, var4 + 1);
               break;
            case 14:
               this.SetCmdkey(39, var4);
               break;
            case 15:
               this.SetCmdkey(40, var4);
               break;
            case 16:
               this.SetCmdkey(41, var4);
               break;
            case 17:
               this.SetCmdkey(50, var4 + 1);
               break;
            case 18:
               this.SetCmdkey(55, var4 + 1);
               break;
            case 19:
               this.SetCmdkey(56, var4 + 1);
               break;
            case 20:
               this.SetCmdkey(58, var4 + 1);
               break;
            case 21:
               this.SetCmdkey(63, var4 + 1);
               break;
            case 22:
               this.SetCmdkey(64, var4 + 1);
               break;
            case 23:
               this.SetCmdkey(65, var4 + 1);
               break;
            case 24:
               this.SetCmdkey(66, var4 + 1);
               break;
            case 25:
               this.SetCmdkey(67, var4 + 1);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      String var47 = var1.getKey();
      if ("tpms".equals(var47)) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus100settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey(12, 1);
            }
         }, var1.getTitle().toString());
      } else if ("default_all".equals(var47)) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus100settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey(0, 1);
            }
         }, var1.getTitle().toString());
      } else if (var1 instanceof HCheckBoxPreference) {
         byte var3;
         byte var4;
         byte var5;
         byte var6;
         byte var7;
         byte var8;
         byte var9;
         byte var10;
         byte var11;
         byte var12;
         byte var13;
         byte var14;
         byte var15;
         byte var16;
         byte var17;
         byte var18;
         byte var19;
         byte var20;
         byte var21;
         byte var22;
         byte var23;
         byte var24;
         byte var25;
         byte var26;
         byte var27;
         byte var28;
         byte var29;
         byte var30;
         byte var31;
         byte var32;
         byte var33;
         byte var34;
         byte var35;
         byte var36;
         byte var37;
         byte var38;
         byte var39;
         byte var40;
         byte var41;
         byte var42;
         byte var43;
         byte var44;
         boolean var45;
         boolean var46;
         HCheckBoxPreference var48;
         short var49;
         label382: {
            var48 = (HCheckBoxPreference)var1;
            var46 = var48.mChecked;
            int var2 = var47.hashCode();
            var34 = 1;
            var43 = 1;
            var26 = 1;
            var18 = 1;
            var16 = 1;
            var27 = 1;
            var30 = 1;
            var33 = 1;
            var8 = 1;
            var23 = 1;
            var39 = 1;
            var5 = 1;
            var7 = 1;
            var24 = 1;
            var21 = 1;
            var6 = 1;
            var35 = 1;
            var36 = 1;
            var42 = 1;
            var14 = 1;
            var12 = 1;
            var19 = 1;
            var4 = 1;
            var28 = 1;
            var44 = 1;
            var15 = 1;
            var41 = 1;
            var38 = 1;
            var32 = 1;
            var29 = 1;
            var40 = 1;
            var10 = 1;
            var13 = 1;
            var11 = 1;
            var37 = 1;
            var3 = 1;
            var9 = 1;
            var31 = 1;
            var22 = 1;
            var20 = 1;
            var25 = 1;
            var45 = true;
            var17 = 1;
            switch (var2) {
               case -2037600616:
                  if (var47.equals("auto_braking")) {
                     var49 = 20;
                     break label382;
                  }
                  break;
               case -1642181864:
                  if (var47.equals("speed_ign")) {
                     var49 = 21;
                     break label382;
                  }
                  break;
               case -1497482005:
                  if (var47.equals("wiperssettings")) {
                     var49 = 2;
                     break label382;
                  }
                  break;
               case -1457237648:
                  if (var47.equals("front_radar")) {
                     var49 = 24;
                     break label382;
                  }
                  break;
               case -1360453817:
                  if (var47.equals("wipers_window")) {
                     var49 = 6;
                     break label382;
                  }
                  break;
               case -1264713415:
                  if (var47.equals("right_low_speed")) {
                     var49 = 33;
                     break label382;
                  }
                  break;
               case -1108971294:
                  if (var47.equals("unlock_ventilation")) {
                     var49 = 7;
                     break label382;
                  }
                  break;
               case -954876430:
                  if (var47.equals("wireless_charging")) {
                     var49 = 41;
                     break label382;
                  }
                  break;
               case -899075872:
                  if (var47.equals("rain_skylight")) {
                     var49 = 32;
                     break label382;
                  }
                  break;
               case -859093553:
                  if (var47.equals("unlock_air")) {
                     var49 = 8;
                     break label382;
                  }
                  break;
               case -712572832:
                  if (var47.equals("rear_view")) {
                     var49 = 1;
                     break label382;
                  }
                  break;
               case -684968036:
                  if (var47.equals("low_speed")) {
                     var49 = 23;
                     break label382;
                  }
                  break;
               case -463773913:
                  if (var47.equals("driving_auto")) {
                     var49 = 4;
                     break label382;
                  }
                  break;
               case -458918326:
                  if (var47.equals("car_in_light47")) {
                     var49 = 42;
                     break label382;
                  }
                  break;
               case -246798387:
                  if (var47.equals("right_front_radar")) {
                     var49 = 34;
                     break label382;
                  }
                  break;
               case -233623395:
                  if (var47.equals("running_lights")) {
                     var49 = 15;
                     break label382;
                  }
                  break;
               case -209117384:
                  if (var47.equals("light_auto")) {
                     var49 = 36;
                     break label382;
                  }
                  break;
               case -188806420:
                  if (var47.equals("air_drying")) {
                     var49 = 9;
                     break label382;
                  }
                  break;
               case -51497443:
                  if (var47.equals("rear_end_warning")) {
                     var49 = 28;
                     break label382;
                  }
                  break;
               case 281287925:
                  if (var47.equals("two_locks")) {
                     var49 = 37;
                     break label382;
                  }
                  break;
               case 317649683:
                  if (var47.equals("maintenance")) {
                     var49 = 0;
                     break label382;
                  }
                  break;
               case 352717350:
                  if (var47.equals("for_warning")) {
                     var49 = 19;
                     break label382;
                  }
                  break;
               case 465530503:
                  if (var47.equals("overspeed_vol")) {
                     var49 = 25;
                     break label382;
                  }
                  break;
               case 472206196:
                  if (var47.equals("rear_warning_voice")) {
                     var49 = 29;
                     break label382;
                  }
                  break;
               case 473360387:
                  if (var47.equals("ion_purifier")) {
                     var49 = 11;
                     break label382;
                  }
                  break;
               case 646458479:
                  if (var47.equals("clos_door_window")) {
                     var49 = 39;
                     break label382;
                  }
                  break;
               case 718809437:
                  if (var47.equals("remote_unlock")) {
                     var49 = 3;
                     break label382;
                  }
                  break;
               case 821867911:
                  if (var47.equals("car_in_light")) {
                     var49 = 16;
                     break label382;
                  }
                  break;
               case 927873967:
                  if (var47.equals("flameout_lock")) {
                     var49 = 5;
                     break label382;
                  }
                  break;
               case 980954030:
                  if (var47.equals("parkunlock")) {
                     var49 = 10;
                     break label382;
                  }
                  break;
               case 1015128569:
                  if (var47.equals("welcome_light")) {
                     var49 = 38;
                     break label382;
                  }
                  break;
               case 1152847106:
                  if (var47.equals("voice_skylight")) {
                     var49 = 30;
                     break label382;
                  }
                  break;
               case 1184545644:
                  if (var47.equals("automatic_lamp")) {
                     var49 = 17;
                     break label382;
                  }
                  break;
               case 1318436985:
                  if (var47.equals("driving_memory")) {
                     var49 = 35;
                     break label382;
                  }
                  break;
               case 1359472718:
                  if (var47.equals("doubling")) {
                     var49 = 26;
                     break label382;
                  }
                  break;
               case 1378032318:
                  if (var47.equals("adaptive_cruise")) {
                     var49 = 18;
                     break label382;
                  }
                  break;
               case 1555751612:
                  if (var47.equals("driving_unlock")) {
                     var49 = 40;
                     break label382;
                  }
                  break;
               case 1697344026:
                  if (var47.equals("bt_speed")) {
                     var49 = 14;
                     break label382;
                  }
                  break;
               case 1791806325:
                  if (var47.equals("window_remote")) {
                     var49 = 13;
                     break label382;
                  }
                  break;
               case 1914528967:
                  if (var47.equals("window_lamp")) {
                     var49 = 12;
                     break label382;
                  }
                  break;
               case 1931192127:
                  if (var47.equals("reverse_warning")) {
                     var49 = 27;
                     break label382;
                  }
                  break;
               case 1986375964:
                  if (var47.equals("over_speed")) {
                     var49 = 22;
                     break label382;
                  }
                  break;
               case 2097523822:
                  if (var47.equals("remote_skylight")) {
                     var49 = 31;
                     break label382;
                  }
                  break;
               case 2118376892:
                  if (var47.equals("light_music")) {
                     var49 = 43;
                     break label382;
                  }
            }

            var49 = -1;
         }

         switch (var49) {
            case 0:
               if (var46) {
                  var49 = 0;
               } else {
                  var49 = 128;
               }

               this.SetCmdkey2((byte)this.getInt("mParking_mode", 0), (byte)var49);
               if ((var49 & 128) == 0) {
                  var45 = false;
               }

               var48.setChecked(var45);
               this.putInt("mMaintenance", var49);
               break;
            case 1:
               var49 = 2;
               if (!var46) {
                  var49 = 1;
               }

               this.SetCmdkey(1, var49);
               break;
            case 2:
               var49 = var25;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(2, var49);
               break;
            case 3:
               var49 = var20;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(3, var49);
               break;
            case 4:
               var49 = var22;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(4, var49);
               break;
            case 5:
               var49 = var31;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(5, var49);
               break;
            case 6:
               var49 = var9;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(6, var49);
               break;
            case 7:
               var49 = var3;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(7, var49);
               break;
            case 8:
               var49 = var37;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(8, var49);
               break;
            case 9:
               var49 = var11;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(9, var49);
               break;
            case 10:
               var49 = var13;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(16, var49);
               break;
            case 11:
               var49 = var10;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(17, var49);
               break;
            case 12:
               var49 = var40;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(19, var49);
               break;
            case 13:
               var49 = var29;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(20, var49);
               break;
            case 14:
               var49 = var32;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(23, var49);
               break;
            case 15:
               var49 = var38;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(24, var49);
               break;
            case 16:
               var49 = var41;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(25, var49);
               break;
            case 17:
               var49 = var15;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(26, var49);
               break;
            case 18:
               var49 = var44;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(27, var49);
               break;
            case 19:
               var49 = var28;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(29, var49);
               break;
            case 20:
               var49 = var4;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(31, var49);
               break;
            case 21:
               var49 = var19;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(35, var49);
               break;
            case 22:
               var49 = var12;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(36, var49);
               break;
            case 23:
               var49 = var14;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(37, var49);
               break;
            case 24:
               var49 = var42;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(38, var49);
               break;
            case 25:
               var49 = var36;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(42, var49);
               break;
            case 26:
               var49 = var35;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(43, var49);
               break;
            case 27:
               var49 = var6;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(44, var49);
               break;
            case 28:
               var49 = var21;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(45, var49);
               break;
            case 29:
               var49 = var24;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(46, var49);
               break;
            case 30:
               var49 = var7;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(47, var49);
               break;
            case 31:
               var49 = var5;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(48, var49);
               break;
            case 32:
               var49 = var39;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(49, var49);
               break;
            case 33:
               var49 = var23;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(51, var49);
               break;
            case 34:
               var49 = var8;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(52, var49);
               break;
            case 35:
               var49 = var33;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(53, var49);
               break;
            case 36:
               var49 = var30;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(54, var49);
               break;
            case 37:
               var49 = var27;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(59, var49);
               break;
            case 38:
               var49 = var16;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(60, var49);
               break;
            case 39:
               var49 = var18;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(61, var49);
               break;
            case 40:
               var49 = var26;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(62, var49);
               break;
            case 41:
               var49 = var43;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(73, var49);
               break;
            case 42:
               var49 = var34;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(71, var49);
               break;
            case 43:
               var49 = var17;
               if (var46) {
                  var49 = 2;
               }

               this.SetCmdkey(72, var49);
         }
      }

      return false;
   }
}
