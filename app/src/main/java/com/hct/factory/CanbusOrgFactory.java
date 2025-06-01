package com.hct.factory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.microntek.CarManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CanbusOrgFactory extends Activity {
   private static final int CAR_AUDI = 1;
   private static final int CAR_BENZ = 2;
   private static final int CAR_BMW = 0;
   private static final String TAG = "CanbusOrgFactory";
   private String[] auxPosition = new String[16];
   private String[] benzAux = new String[2];
   private String[] bmwEvo = new String[3];
   private String[] canData = new String[2];
   private String[] carAudio = new String[4];
   private String[] carNavi = new String[2];
   private String[] carType = new String[3];
   private String[] idriveMode = new String[2];
   private boolean isCanA = false;
   private boolean isOrgPanelHighVer;
   private Spinner mAmpGroup;
   private Spinner mAuxMode;
   private Spinner mAuxPositionGroup;
   private Spinner mBackDoorGroup;
   private Spinner mBenzAuxGroup;
   private Spinner mBenzS;
   private Spinner mBmwEvoGroup;
   private Spinner mCanDataGroup;
   private Spinner mCarAudioGroup;
   private CarManager mCarManager = null;
   private Spinner mCarNaviGroup;
   private Spinner mCarTypeGroup;
   private Spinner mCarUiTypeGroup;
   private Spinner mDashboardDisable;
   private Spinner mDashboardLight;
   private Spinner mDashboardMax;
   private Spinner mDvrGroup;
   private Spinner mFmTrans;
   private Spinner mFrontDoorGroup;
   private Spinner mGpsSelect;
   private Spinner mHightScreenSetting;
   private Spinner mIdriveModeGroup;
   private OrgConfig mOrgConfig = null;
   private Spinner mScreenModeGroup;
   private Spinner mShortcutKeysGroup;
   private String[] screenMode = new String[256];
   private String[] shortcutKeys = new String[9];

   private void ExitDialog() {
      (new AlertDialog.Builder(this)).setIcon(17301659).setTitle(this.getString(2131296371)).setView((View)null).setPositiveButton(17039370, new DialogInterface.OnClickListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.this$0.sendBroadcast(new Intent("com.microntek.hctreboot"));
            this.this$0.finish();
         }
      }).setNegativeButton(17039360, new DialogInterface.OnClickListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.this$0.finish();
         }
      }).show();
   }

   private void SaveData() {
      this.mOrgConfig.SaveOrg_config();
   }

   private void initApp() {
      this.mAmpGroup = (Spinner)this.findViewById(2131099655);
      this.mDvrGroup = (Spinner)this.findViewById(2131099753);
      ArrayAdapter var1 = new ArrayAdapter(this, 17367048, new String[]{"NO", "YES"});
      var1.setDropDownViewResource(17367049);
      this.mAmpGroup.setAdapter(var1);
      this.mAmpGroup.setSelection(this.mOrgConfig.mApp & 1);
      this.mAmpGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            OrgConfig var6;
            if (var3 == 1) {
               var6 = this.this$0.mOrgConfig;
               var6.mApp = (byte)(1 | var6.mApp);
            } else {
               var6 = this.this$0.mOrgConfig;
               var6.mApp &= -2;
            }

         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      this.mDvrGroup.setAdapter(var1);
      this.mDvrGroup.setSelection((2 & this.mOrgConfig.mApp) >> 1);
      this.mDvrGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            OrgConfig var6;
            if (var3 == 1) {
               var6 = this.this$0.mOrgConfig;
               var6.mApp = (byte)(var6.mApp | 2);
            } else {
               var6 = this.this$0.mOrgConfig;
               var6.mApp &= -3;
            }

         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
   }

   private void initArmAux() {
      this.mCarTypeGroup = (Spinner)this.findViewById(2131099727);
      this.mShortcutKeysGroup = (Spinner)this.findViewById(2131099854);
      this.mCarNaviGroup = (Spinner)this.findViewById(2131099726);
      this.mCarAudioGroup = (Spinner)this.findViewById(2131099724);
      this.mAuxPositionGroup = (Spinner)this.findViewById(2131099662);
      this.mFmTrans = (Spinner)this.findViewById(2131099766);
      String[] var2 = this.carType;
      var2[0] = "BMW";
      var2[1] = "AUDI";
      var2[2] = "BENZ";
      ArrayAdapter var4 = new ArrayAdapter(this, 17367048, var2);
      var4.setDropDownViewResource(17367049);
      this.mCarTypeGroup.setAdapter(var4);
      if (this.mOrgConfig.mCarType >= this.carType.length) {
         this.mOrgConfig.mCarType = 0;
      }

      this.mCarTypeGroup.setSelection(this.mOrgConfig.mCarType);
      this.mCarTypeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            if (this.this$0.mOrgConfig.mCarType != (byte)var3) {
               this.this$0.mOrgConfig.mCarType = (byte)var3;
               this.this$0.mOrgConfig.mScreenSetting = 0;
               CanbusOrgFactory var6 = this.this$0;
               var6.refreshHightScreenSetting(var6.mOrgConfig.mCarType & 255);
            }

         }

         public void onNothingSelected(AdapterView var1) {
         }
      });

      int var1;
      String[] var3;
      StringBuilder var5;
      for(var1 = 0; var1 < 9; ++var1) {
         var3 = this.shortcutKeys;
         var5 = new StringBuilder();
         var5.append(var1);
         var5.append("");
         var3[var1] = var5.toString();
      }

      var4 = new ArrayAdapter(this, 17367048, this.shortcutKeys);
      var4.setDropDownViewResource(17367049);
      this.mShortcutKeysGroup.setAdapter(var4);
      if (this.mOrgConfig.mShortcutKey >= this.shortcutKeys.length) {
         this.mOrgConfig.mShortcutKey = 0;
      }

      this.mShortcutKeysGroup.setSelection(this.mOrgConfig.mShortcutKey);
      this.mShortcutKeysGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mOrgConfig.mShortcutKey = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = this.carNavi;
      var2[0] = "NO";
      var2[1] = "YES";
      var4 = new ArrayAdapter(this, 17367048, var2);
      var4.setDropDownViewResource(17367049);
      this.mCarNaviGroup.setAdapter(var4);
      if (this.mOrgConfig.mOrgNavi >= this.carNavi.length) {
         this.mOrgConfig.mOrgNavi = 0;
      }

      this.mCarNaviGroup.setSelection(this.mOrgConfig.mOrgNavi);
      this.mCarNaviGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mOrgConfig.mOrgNavi = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = this.carAudio;
      var2[0] = "BT";
      var2[1] = "USB";
      var2[2] = "AUX";
      var2[3] = "TV";
      var4 = new ArrayAdapter(this, 17367048, var2);
      var4.setDropDownViewResource(17367049);
      this.mCarAudioGroup.setAdapter(var4);
      if (this.mOrgConfig.mOrgMedia >= this.carAudio.length) {
         this.mOrgConfig.mOrgMedia = 0;
      }

      this.mCarAudioGroup.setSelection(this.mOrgConfig.mOrgMedia);
      this.mCarAudioGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mOrgConfig.mOrgMedia = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });

      for(var1 = 0; var1 < 16; ++var1) {
         var3 = this.auxPosition;
         var5 = new StringBuilder();
         var5.append(var1);
         var5.append("");
         var3[var1] = var5.toString();
      }

      var4 = new ArrayAdapter(this, 17367048, this.auxPosition);
      var4.setDropDownViewResource(17367049);
      this.mAuxPositionGroup.setAdapter(var4);
      if (this.mOrgConfig.mAuxPosition >= this.auxPosition.length) {
         this.mOrgConfig.mAuxPosition = 0;
      }

      this.mAuxPositionGroup.setSelection(this.mOrgConfig.mAuxPosition);
      this.mAuxPositionGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mOrgConfig.mAuxPosition = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var3 = new String[]{"NO", "YES"};
      var4 = new ArrayAdapter(this, 17367048, var3);
      var4.setDropDownViewResource(17367049);
      this.mFmTrans.setAdapter(var4);
      if ((this.mOrgConfig.mQN8027OnOff & 63) >= var3.length) {
         this.mOrgConfig.mQN8027OnOff = 0;
      }

      this.mFmTrans.setSelection(this.mOrgConfig.mQN8027OnOff & 63);
      this.mFmTrans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mOrgConfig.mQN8027OnOff = (byte)var3;
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
   }

   private void initFactory() {
      this.mScreenModeGroup = (Spinner)this.findViewById(2131099848);
      this.mIdriveModeGroup = (Spinner)this.findViewById(2131099781);
      this.mCanDataGroup = (Spinner)this.findViewById(2131099697);
      this.mBenzAuxGroup = (Spinner)this.findViewById(2131099675);
      this.mBmwEvoGroup = (Spinner)this.findViewById(2131099685);
      this.mCarUiTypeGroup = (Spinner)this.findViewById(2131099728);
      this.mFrontDoorGroup = (Spinner)this.findViewById(2131099698);
      this.mBackDoorGroup = (Spinner)this.findViewById(2131099696);
      this.mDashboardDisable = (Spinner)this.findViewById(2131099743);
      this.mDashboardMax = (Spinner)this.findViewById(2131099745);
      this.mAuxMode = (Spinner)this.findViewById(2131099661);
      this.mGpsSelect = (Spinner)this.findViewById(2131099770);
      this.mDashboardLight = (Spinner)this.findViewById(2131099744);
      this.mHightScreenSetting = (Spinner)this.findViewById(2131099775);
      this.mBenzS = (Spinner)this.findViewById(2131099676);
      String[] var2 = this.getResources().getStringArray(2130771977);
      int var1 = 0;
      if (var2 != null) {
         var1 = var2.length;
         System.arraycopy(var2, 0, this.screenMode, 0, var1);
      }

      while(true) {
         var2 = this.screenMode;
         if (var1 >= var2.length) {
            ArrayAdapter var4 = new ArrayAdapter(this, 17367048, var2);
            var4.setDropDownViewResource(17367049);
            this.mScreenModeGroup.setAdapter(var4);
            if (this.mOrgConfig.mScreenType >= this.screenMode.length) {
               this.mOrgConfig.mScreenType = 0;
            }

            this.mScreenModeGroup.setSelection(this.mOrgConfig.mScreenType);
            this.mScreenModeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mScreenType = (byte)var3;
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = this.idriveMode;
            var2[0] = "IDrive_A（CCC）";
            var2[1] = "IDrive_B（CCC）";
            var4 = new ArrayAdapter(this, 17367048, var2);
            var4.setDropDownViewResource(17367049);
            this.mIdriveModeGroup.setAdapter(var4);
            if (this.mOrgConfig.mBmwIDrive >= this.idriveMode.length) {
               this.mOrgConfig.mBmwIDrive = 0;
            }

            this.mIdriveModeGroup.setSelection(this.mOrgConfig.mBmwIDrive);
            this.mIdriveModeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mBmwIDrive = (byte)var3;
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            this.canData[0] = this.getString(2131296430);
            this.canData[1] = this.getString(2131296431);
            var4 = new ArrayAdapter(this, 17367048, this.canData);
            var4.setDropDownViewResource(17367049);
            this.mCanDataGroup.setAdapter(var4);
            if (this.mOrgConfig.mCanData >= this.canData.length) {
               this.mOrgConfig.mCanData = 0;
            }

            this.mCanDataGroup.setSelection(this.mOrgConfig.mCanData);
            this.mCanDataGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mCanData = (byte)var3;
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            this.benzAux[0] = this.getString(2131296430);
            this.benzAux[1] = this.getString(2131296431);
            var4 = new ArrayAdapter(this, 17367048, this.benzAux);
            var4.setDropDownViewResource(17367049);
            this.mBenzAuxGroup.setAdapter(var4);
            if (this.mOrgConfig.mBenzAux >= this.benzAux.length) {
               this.mOrgConfig.mBenzAux = 0;
            }

            this.mBenzAuxGroup.setSelection(this.mOrgConfig.mBenzAux);
            this.mBenzAuxGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mBenzAux = (byte)var3;
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = this.bmwEvo;
            var2[0] = "6.5”";
            var2[1] = "8.8”";
            var2[2] = "8.8” H";
            var4 = new ArrayAdapter(this, 17367048, var2);
            var4.setDropDownViewResource(17367049);
            this.mBmwEvoGroup.setAdapter(var4);
            if (this.mOrgConfig.mBmwEvo >= this.bmwEvo.length) {
               this.mOrgConfig.mBmwEvo = 0;
            }

            this.mBmwEvoGroup.setSelection(this.mOrgConfig.mBmwEvo);
            this.mBmwEvoGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mBmwEvo = (byte)var3;
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = this.getResources().getStringArray(2130771971);
            ArrayAdapter var5 = new ArrayAdapter(this, 17367048, var2);
            var5.setDropDownViewResource(17367049);
            this.mCarUiTypeGroup.setAdapter(var5);
            if (this.mOrgConfig.mCarUiType >= var2.length) {
               this.mOrgConfig.mCarUiType = 0;
            }

            this.mCarUiTypeGroup.setSelection(this.mOrgConfig.mCarUiType);
            this.mCarUiTypeGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mCarUiType = (byte)var3;
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = this.getResources().getStringArray(2130771972);
            var5 = new ArrayAdapter(this, 17367048, var2);
            var5.setDropDownViewResource(17367049);
            this.mFrontDoorGroup.setAdapter(var5);
            OrgConfig var7;
            if ((this.mOrgConfig.mDoor >> 3 & 7) >= var2.length) {
               var7 = this.mOrgConfig;
               var7.mDoor = (byte)(var7.mDoor & 199);
            }

            this.mFrontDoorGroup.setSelection(this.mOrgConfig.mDoor >> 3 & 7);
            this.mFrontDoorGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  OrgConfig var6 = this.this$0.mOrgConfig;
                  var6.mDoor = (byte)(var6.mDoor & 199);
                  var6 = this.this$0.mOrgConfig;
                  var6.mDoor |= (byte)((var3 & 7) << 3);
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = this.getResources().getStringArray(2130771972);
            var5 = new ArrayAdapter(this, 17367048, var2);
            var5.setDropDownViewResource(17367049);
            this.mBackDoorGroup.setAdapter(var5);
            if ((this.mOrgConfig.mDoor & 7) >= var2.length) {
               var7 = this.mOrgConfig;
               var7.mDoor = (byte)(var7.mDoor & 248);
            }

            this.mBackDoorGroup.setSelection(this.mOrgConfig.mDoor & 7);
            this.mBackDoorGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  OrgConfig var6 = this.this$0.mOrgConfig;
                  var6.mDoor = (byte)(var6.mDoor & 248);
                  var6 = this.this$0.mOrgConfig;
                  var6.mDoor |= (byte)(var3 & 7);
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = new String[]{"YES", "NO"};
            var5 = new ArrayAdapter(this, 17367048, var2);
            var5.setDropDownViewResource(17367049);
            this.mDashboardDisable.setAdapter(var5);
            if ((this.mOrgConfig.mDashboard >> 4 & 15) >= var2.length) {
               var7 = this.mOrgConfig;
               var7.mDashboard = (byte)(var7.mDashboard & 15);
            }

            this.mDashboardDisable.setSelection(this.mOrgConfig.mDashboard >> 4 & 15);
            this.mDashboardDisable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  OrgConfig var6 = this.this$0.mOrgConfig;
                  var6.mDashboard = (byte)(var6.mDashboard & 15);
                  var6 = this.this$0.mOrgConfig;
                  var6.mDashboard |= (byte)((var3 & 15) << 4);
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = new String[]{"330", "260", "240"};
            var5 = new ArrayAdapter(this, 17367048, var2);
            var5.setDropDownViewResource(17367049);
            this.mDashboardMax.setAdapter(var5);
            if ((this.mOrgConfig.mDashboard & 15) >= var2.length) {
               var7 = this.mOrgConfig;
               var7.mDashboard = (byte)(var7.mDashboard & 240);
            }

            this.mDashboardMax.setSelection(this.mOrgConfig.mDashboard & 15);
            this.mDashboardMax.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  OrgConfig var6 = this.this$0.mOrgConfig;
                  var6.mDashboard = (byte)(var6.mDashboard & 240);
                  var6 = this.this$0.mOrgConfig;
                  var6.mDashboard |= (byte)(var3 & 15);
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            String[] var6 = new String[]{this.getString(2131296263), this.getString(2131296398)};
            var4 = new ArrayAdapter(this, 17367048, var6);
            var4.setDropDownViewResource(17367049);
            this.mAuxMode.setAdapter(var4);
            if ((this.mOrgConfig.mAuxMode & 255) >= var6.length) {
               this.mOrgConfig.mAuxMode = 0;
            }

            this.mAuxMode.setSelection(this.mOrgConfig.mAuxMode & 255);
            this.mAuxMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mAuxMode = (byte)(var3 & 255);
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var2 = new String[]{this.getString(2131296343), this.getString(2131296258)};
            var5 = new ArrayAdapter(this, 17367048, var2);
            var5.setDropDownViewResource(17367049);
            this.mGpsSelect.setAdapter(var5);
            if ((this.mOrgConfig.mGpsSelect & 255) >= var2.length) {
               this.mOrgConfig.mGpsSelect = 0;
            }

            this.mGpsSelect.setSelection(this.mOrgConfig.mGpsSelect & 255);
            this.mGpsSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mGpsSelect = (byte)(var3 & 255);
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            var6 = new String[]{"YES", "NO"};
            var4 = new ArrayAdapter(this, 17367048, var6);
            var4.setDropDownViewResource(17367049);
            this.mDashboardLight.setAdapter(var4);
            if ((this.mOrgConfig.mDashboardLight & 255) >= var6.length) {
               this.mOrgConfig.mDashboardLight = 0;
            }

            this.mDashboardLight.setSelection(this.mOrgConfig.mDashboardLight & 255);
            this.mDashboardLight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
               final CanbusOrgFactory this$0;

               {
                  this.this$0 = var1;
               }

               public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
                  this.this$0.mOrgConfig.mDashboardLight = (byte)(var3 & 255);
               }

               public void onNothingSelected(AdapterView var1) {
               }
            });
            return;
         }

         StringBuilder var3 = new StringBuilder();
         var3.append("");
         var3.append(var1);
         var2[var1] = var3.toString();
         ++var1;
      }
   }

   private void refreshHightScreenSetting(int var1) {
      String[] var2 = new String[]{"NO", "Key 1", "Key 2"};
      ArrayAdapter var3 = new ArrayAdapter(this, 17367048, var2);
      var3.setDropDownViewResource(17367049);
      this.mBenzS.setAdapter(var3);
      if ((this.mOrgConfig.mBenzS & 255) >= var2.length || var1 != 2) {
         this.mOrgConfig.mBenzS = 0;
      }

      this.mBenzS.setSelection(this.mOrgConfig.mBenzS & 255);
      this.mBenzS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
            this.this$0.mOrgConfig.mBenzS = (byte)(var3 & 255);
         }

         public void onNothingSelected(AdapterView var1) {
         }
      });
      var2 = null;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               var2 = this.getResources().getStringArray(2130771974);
            }
         } else {
            var2 = this.getResources().getStringArray(2130771973);
         }
      } else {
         var2 = this.getResources().getStringArray(2130771975);
      }

      if (var2 != null) {
         var3 = new ArrayAdapter(this, 17367048, var2);
         var3.setDropDownViewResource(17367049);
         this.mHightScreenSetting.setAdapter(var3);
         if ((this.mOrgConfig.mScreenSetting & 255) >= var2.length) {
            this.mOrgConfig.mScreenSetting = 0;
         }

         this.mHightScreenSetting.setSelection(this.mOrgConfig.mScreenSetting & 255);
         this.mHightScreenSetting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
            final CanbusOrgFactory this$0;

            {
               this.this$0 = var1;
            }

            public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
               this.this$0.mOrgConfig.mScreenSetting = (byte)(var3 & 255);
            }

            public void onNothingSelected(AdapterView var1) {
            }
         });
      }
   }

   private void setPage(int var1) {
      if (2 == var1) {
         this.findViewById(2131099760).setVisibility(8);
         this.findViewById(2131099658).setVisibility(8);
         this.findViewById(2131099657).setVisibility(0);
         this.findViewById(2131099694).setEnabled(true);
         this.findViewById(2131099693).setEnabled(true);
         this.findViewById(2131099692).setEnabled(false);
      } else if (1 == var1) {
         this.findViewById(2131099760).setVisibility(8);
         this.findViewById(2131099658).setVisibility(0);
         this.findViewById(2131099657).setVisibility(8);
         this.findViewById(2131099694).setEnabled(true);
         this.findViewById(2131099693).setEnabled(false);
         this.findViewById(2131099692).setEnabled(true);
      } else {
         this.findViewById(2131099760).setVisibility(0);
         this.findViewById(2131099658).setVisibility(8);
         this.findViewById(2131099657).setVisibility(8);
         this.findViewById(2131099694).setEnabled(false);
         this.findViewById(2131099693).setEnabled(true);
         this.findViewById(2131099692).setEnabled(true);
      }

   }

   public void SysetmClick(View var1) {
      switch (var1.getId()) {
         case 2131099864:
            if (this.isOrgPanelHighVer && this.mOrgConfig.isChange) {
               this.ExitDialog();
            } else {
               this.finish();
            }
         case 2131099865:
         default:
            break;
         case 2131099866:
            this.SaveData();
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.mCarManager = new CarManager();
      this.mOrgConfig = new OrgConfig(this);
      this.setContentView(2131230724);
      this.initFactory();
      this.initArmAux();
      this.initApp();
      this.setPage(this.getIntent().getIntExtra("flag", 0));
      this.findViewById(2131099694).setOnClickListener(new View.OnClickListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.setPage(0);
         }
      });
      this.findViewById(2131099660).setOnClickListener(new View.OnClickListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.mCarManager.setParameters("canbus_rsp=46,0,112,2,16,1,124");
         }
      });
      this.findViewById(2131099693).setOnClickListener(new View.OnClickListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.setPage(1);
         }
      });
      this.findViewById(2131099692).setOnClickListener(new View.OnClickListener(this) {
         final CanbusOrgFactory this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.setPage(2);
         }
      });
      this.isOrgPanelHighVer = "1".equals(this.mCarManager.getParameters("sta_org_res="));
      if (this.isOrgPanelHighVer) {
         this.findViewById(2131099776).setVisibility(0);
         this.findViewById(2131099850).setVisibility(8);
      } else {
         this.findViewById(2131099776).setVisibility(8);
         this.findViewById(2131099850).setVisibility(0);
      }

      this.refreshHightScreenSetting(this.mOrgConfig.mCarType & 255);
   }

   public class OrgConfig {
      public boolean isChange;
      public byte mApp;
      public byte mAuxMode;
      public byte mAuxPosition;
      public byte mBenzAux;
      public byte mBenzS;
      public byte mBmwEvo;
      public byte mBmwIDrive;
      public byte mCanData;
      public byte[] mCanRev;
      public byte mCarType;
      public byte mCarUiType;
      public byte mDashboard;
      public byte mDashboardLight;
      public byte mDoor;
      public byte mGpsSelect;
      public byte mHighResolutionVer;
      public byte mOrgMedia;
      public byte mOrgNavi;
      public byte mPlayMode;
      public byte mQN8027OnOff;
      public byte mScreenSetting;
      public byte mScreenType;
      public byte mShortcutKey;
      public byte mValidFlag;
      private String orgConfig;
      final CanbusOrgFactory this$0;

      public OrgConfig(CanbusOrgFactory var1) {
         this.this$0 = var1;
         this.mCanRev = new byte[9];
         this.isChange = false;
         this.LoadOrg_config();
      }

      public void LoadOrg_config() {
         byte[] var4 = new byte[32];
         this.orgConfig = this.this$0.mCarManager.getParameters("cfg_org=");
         String var5 = this.orgConfig;
         int var1;
         int var2;
         if (var5 != null && var5.contains(",")) {
            String[] var9 = this.orgConfig.split(",");
            var2 = var9.length;

            for(var1 = 0; var1 < var4.length && var1 <= var2; ++var1) {
               try {
                  var4[var1] = (byte)(Integer.parseInt(var9[var1]) & 255);
               } catch (NumberFormatException var7) {
               }
            }
         }

         var2 = 0 + 1;
         this.mValidFlag = var4[0];
         var1 = var2 + 1;
         this.mCarType = var4[var2];
         var2 = var1 + 1;
         this.mShortcutKey = var4[var1];
         var1 = var2 + 1;
         this.mOrgNavi = var4[var2];
         var2 = var1 + 1;
         this.mOrgMedia = var4[var1];
         var1 = var2 + 1;
         this.mAuxPosition = var4[var2];
         var2 = var1 + 1;
         this.mScreenType = var4[var1];
         var1 = var2 + 1;
         this.mBmwIDrive = var4[var2];
         int var3 = var1 + 1;
         this.mCanData = var4[var1];
         var2 = var3 + 1;
         this.mBenzAux = var4[var3];
         var1 = var2 + 1;
         this.mBmwEvo = var4[var2];
         var2 = var1 + 1;
         this.mCarUiType = var4[var1];
         var1 = var2 + 1;
         this.mDoor = var4[var2];
         var3 = var1 + 1;
         this.mDashboard = var4[var1];
         var2 = var3 + 1;
         this.mAuxMode = var4[var3];
         var1 = var2 + 1;
         this.mGpsSelect = var4[var2];
         var3 = var1 + 1;
         this.mPlayMode = var4[var1];
         var2 = var3 + 1;
         this.mDashboardLight = var4[var3];
         var1 = var2 + 1;
         this.mApp = var4[var2];
         var2 = var1 + 1;
         this.mHighResolutionVer = var4[var1];
         var1 = var2 + 1;
         this.mScreenSetting = var4[var2];
         var2 = var1 + 1;
         this.mQN8027OnOff = var4[var1];
         var1 = var2 + 1;
         this.mBenzS = var4[var2];
         var2 = 0;

         while(true) {
            byte[] var10 = this.mCanRev;
            if (var2 >= var10.length) {
               StringBuilder var8 = new StringBuilder();
               var8.append("meng>>>LoadOrg_config:");
               var8.append(this.orgConfig);
               Log.d("CanbusOrgFactory", var8.toString());
               return;
            }

            var10[var2] = var4[var1];
            ++var2;
            ++var1;
         }
      }

      public void SaveOrg_config() {
         byte[] var4 = new byte[32];
         int var2 = 0 + 1;
         var4[0] = this.mValidFlag;
         int var1 = var2 + 1;
         var4[var2] = this.mCarType;
         var2 = var1 + 1;
         var4[var1] = this.mShortcutKey;
         var1 = var2 + 1;
         var4[var2] = this.mOrgNavi;
         var2 = var1 + 1;
         var4[var1] = this.mOrgMedia;
         var1 = var2 + 1;
         var4[var2] = this.mAuxPosition;
         var2 = var1 + 1;
         var4[var1] = this.mScreenType;
         var1 = var2 + 1;
         var4[var2] = this.mBmwIDrive;
         int var3 = var1 + 1;
         var4[var1] = this.mCanData;
         var2 = var3 + 1;
         var4[var3] = this.mBenzAux;
         var1 = var2 + 1;
         var4[var2] = this.mBmwEvo;
         var2 = var1 + 1;
         var4[var1] = this.mCarUiType;
         var3 = var2 + 1;
         var4[var2] = this.mDoor;
         var1 = var3 + 1;
         var4[var3] = this.mDashboard;
         var2 = var1 + 1;
         var4[var1] = this.mAuxMode;
         var1 = var2 + 1;
         var4[var2] = this.mGpsSelect;
         var2 = var1 + 1;
         var4[var1] = this.mPlayMode;
         var1 = var2 + 1;
         var4[var2] = this.mDashboardLight;
         var2 = var1 + 1;
         var4[var1] = this.mApp;
         var1 = var2 + 1;
         var4[var2] = this.mHighResolutionVer;
         var3 = var1 + 1;
         var4[var1] = this.mScreenSetting;
         var2 = var3 + 1;
         var4[var3] = this.mQN8027OnOff;
         var1 = var2 + 1;
         var4[var2] = this.mBenzS;
         var2 = 0;

         while(true) {
            byte[] var5 = this.mCanRev;
            if (var2 >= var5.length) {
               StringBuffer var8 = new StringBuffer();

               for(var1 = 0; var1 < var4.length; ++var1) {
                  var8.append(var4[var1] & 255);
                  if (var1 != var4.length - 1) {
                     var8.append(",");
                  }
               }

               String var7 = var8.toString();
               this.isChange = var7.equals(this.orgConfig) ^ true;
               CarManager var9 = this.this$0.mCarManager;
               StringBuilder var6 = new StringBuilder();
               var6.append("cfg_org=");
               var6.append(var7);
               var9.setParameters(var6.toString());
               StringBuilder var10 = new StringBuilder();
               var10.append("meng>>>SaveOrg_config:");
               var10.append(var7);
               Log.d("CanbusOrgFactory", var10.toString());
               android.provider.Settings.System.putInt(this.this$0.getContentResolver(), "org_car_ui_type", this.mCarUiType);
               android.provider.Settings.System.putInt(this.this$0.getContentResolver(), "canbus_updata", 1);
               return;
            }

            var4[var1] = var5[var2];
            ++var2;
            ++var1;
         }
      }
   }
}
