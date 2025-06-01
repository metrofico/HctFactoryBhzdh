package com.microntek.controlsettings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class MainActivity extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private int canbustype = 0;
   private PreferenceScreen factory_settings;

   private void initCanbus() {
      int var1 = this.canbustype;
      if (var1 != 44) {
         if (var1 == 45) {
            this.addPreferencesFromResource(2131492995);
         } else if (var1 != 49) {
            if (var1 == 50) {
               this.addPreferencesFromResource(2131492999);
            } else if (var1 != 54) {
               if (var1 == 55) {
                  this.addPreferencesFromResource(2131493001);
               } else {
                  label384: {
                     label385: {
                        if (var1 != 68) {
                           if (var1 == 69) {
                              break label385;
                           }

                           if (var1 == 75) {
                              this.addPreferencesFromResource(2131493013);
                              if (BaseApplication.getInstance().getCarType() == 1) {
                                 this.getPreferenceScreen().removePreference(this.findPreference("ari_cmd"));
                              }

                              return;
                           }

                           if (var1 == 76) {
                              this.addPreferencesFromResource(2131493014);
                              return;
                           }

                           if (var1 == 78) {
                              this.addPreferencesFromResource(2131493015);
                              return;
                           }

                           if (var1 == 79) {
                              this.addPreferencesFromResource(2131493016);
                              return;
                           }

                           switch (var1) {
                              case 4:
                                 break;
                              case 5:
                                 break label385;
                              case 6:
                                 this.addPreferencesFromResource(2131493005);
                                 return;
                              case 7:
                                 this.addPreferencesFromResource(2131493011);
                                 return;
                              case 8:
                                 break label384;
                              case 9:
                                 this.addPreferencesFromResource(2131493023);
                                 return;
                              default:
                                 switch (var1) {
                                    case 14:
                                       this.addPreferencesFromResource(2131492969);
                                       return;
                                    case 17:
                                       this.addPreferencesFromResource(2131492979);
                                       return;
                                    case 19:
                                       this.addPreferencesFromResource(2131492980);
                                       return;
                                    case 21:
                                       this.addPreferencesFromResource(2131492981);
                                       return;
                                    case 42:
                                       this.addPreferencesFromResource(2131492993);
                                       return;
                                    case 47:
                                       this.addPreferencesFromResource(2131492996);
                                       return;
                                    case 57:
                                       this.addPreferencesFromResource(2131493002);
                                       return;
                                    case 58:
                                       this.addPreferencesFromResource(2131493003);
                                       return;
                                    case 59:
                                       this.addPreferencesFromResource(2131493004);
                                       return;
                                    case 60:
                                       this.addPreferencesFromResource(2131493006);
                                       return;
                                    case 61:
                                       this.addPreferencesFromResource(2131493007);
                                       if (BaseApplication.getInstance().getCarType2() == 1) {
                                          this.removePreference(this.findPreference("controlsettings"));
                                          this.removePreference(this.findPreference("speed_cmd"));
                                       } else {
                                          this.removePreference(this.findPreference("panel_buttons"));
                                       }

                                       return;
                                    case 62:
                                       this.addPreferencesFromResource(2131493008);
                                       return;
                                    case 63:
                                       this.addPreferencesFromResource(2131493009);
                                       return;
                                    case 64:
                                       this.addPreferencesFromResource(2131493010);
                                       return;
                                    case 73:
                                       this.addPreferencesFromResource(2131493012);
                                       return;
                                    case 82:
                                       this.addPreferencesFromResource(2131493018);
                                       return;
                                    case 83:
                                       this.addPreferencesFromResource(2131493019);
                                       if (BaseApplication.getInstance().getCarType() != 2) {
                                          this.removePreference(this.findPreference("panel_buttons"));
                                       } else {
                                          this.removePreference(this.findPreference("controlsettings"));
                                       }

                                       return;
                                    case 84:
                                       this.addPreferencesFromResource(2131493020);
                                       return;
                                    case 85:
                                       this.addPreferencesFromResource(2131493021);
                                       return;
                                    case 88:
                                       this.addPreferencesFromResource(2131493022);
                                       return;
                                    case 92:
                                       this.addPreferencesFromResource(2131493024);
                                       return;
                                    case 94:
                                       this.addPreferencesFromResource(2131493025);
                                       return;
                                    case 98:
                                       this.addPreferencesFromResource(2131493026);
                                       return;
                                    case 99:
                                       this.addPreferencesFromResource(2131493027);
                                       return;
                                    case 100:
                                       this.addPreferencesFromResource(2131492953);
                                       return;
                                    case 103:
                                       this.addPreferencesFromResource(2131492954);
                                       return;
                                    case 105:
                                       this.addPreferencesFromResource(2131492955);
                                       return;
                                    case 107:
                                       this.addPreferencesFromResource(2131492956);
                                       return;
                                    case 110:
                                       this.addPreferencesFromResource(2131492957);
                                       return;
                                    case 115:
                                    case 147:
                                       this.addPreferencesFromResource(2131492958);
                                       return;
                                    case 119:
                                    case 148:
                                       this.addPreferencesFromResource(2131492959);
                                       return;
                                    case 122:
                                       this.addPreferencesFromResource(2131492960);
                                       return;
                                    case 125:
                                       this.addPreferencesFromResource(2131492961);
                                       return;
                                    case 128:
                                       this.addPreferencesFromResource(2131492962);
                                       return;
                                    case 129:
                                       this.addPreferencesFromResource(2131492963);
                                       return;
                                    case 132:
                                       this.addPreferencesFromResource(2131492964);
                                       return;
                                    case 134:
                                       this.addPreferencesFromResource(2131492965);
                                       return;
                                    case 135:
                                       this.addPreferencesFromResource(2131492966);
                                       return;
                                    case 137:
                                       this.addPreferencesFromResource(2131492967);
                                       return;
                                    case 138:
                                       this.addPreferencesFromResource(2131492968);
                                       if (!canbus138keyMode.isKeyMode()) {
                                          this.removePreference(this.findPreference("key_mode"));
                                       }

                                       return;
                                    case 140:
                                       this.addPreferencesFromResource(2131492970);
                                       return;
                                    case 141:
                                       break label384;
                                    case 146:
                                       this.addPreferencesFromResource(2131492971);
                                       return;
                                    case 152:
                                       this.addPreferencesFromResource(2131492972);
                                       return;
                                    case 153:
                                       this.addPreferencesFromResource(2131492973);
                                       return;
                                    case 155:
                                       this.addPreferencesFromResource(2131492974);
                                       return;
                                    case 156:
                                       this.addPreferencesFromResource(2131492975);
                                       return;
                                    case 157:
                                       this.addPreferencesFromResource(2131492976);
                                       return;
                                    case 158:
                                       this.addPreferencesFromResource(2131492977);
                                       return;
                                    case 160:
                                       this.addPreferencesFromResource(2131492978);
                                       return;
                                    default:
                                       switch (var1) {
                                          case 24:
                                             this.addPreferencesFromResource(2131492982);
                                             return;
                                          case 25:
                                             this.addPreferencesFromResource(2131492983);
                                             return;
                                          case 26:
                                             this.addPreferencesFromResource(2131492984);
                                             return;
                                          case 27:
                                             this.addPreferencesFromResource(2131492985);
                                             this.factory_settings = (PreferenceScreen)this.findPreference("factory_settings");
                                             return;
                                          default:
                                             switch (var1) {
                                                case 33:
                                                   break label384;
                                                case 34:
                                                   this.addPreferencesFromResource(2131492987);
                                                   return;
                                                case 35:
                                                   this.addPreferencesFromResource(2131492988);
                                                   return;
                                                case 36:
                                                   this.addPreferencesFromResource(2131492989);
                                                   return;
                                                case 37:
                                                   this.addPreferencesFromResource(2131492990);
                                                   return;
                                                default:
                                                   this.finish();
                                                   return;
                                             }
                                       }
                                 }
                           }
                        }

                        this.addPreferencesFromResource(2131492991);
                        return;
                     }

                     this.addPreferencesFromResource(2131492998);
                     return;
                  }

                  this.addPreferencesFromResource(2131493017);
               }
            } else {
               this.addPreferencesFromResource(2131493000);
            }
         } else {
            this.addPreferencesFromResource(2131492997);
         }
      } else {
         this.addPreferencesFromResource(2131492994);
      }

   }

   private void initCanbusOrgPanel() {
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.canbustype = BaseApplication.getInstance().getCanbustype();
      if (BaseApplication.getInstance().isOrgPanel()) {
         this.initCanbusOrgPanel();
      } else {
         this.initCanbus();
      }

      if (this.getPreferenceScreen().getPreferenceCount() == 1) {
         Intent var2 = this.getPreferenceScreen().getPreference(0).getIntent();
         if (var2 != null) {
            var2.addFlags(539164672);
            this.startActivity(var2);
            this.finish();
         }
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return false;
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.factory_settings) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final MainActivity this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SendCanBusCmdData2E((byte)26, new byte[]{0}, 1);
            }
         }, this.factory_settings.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
