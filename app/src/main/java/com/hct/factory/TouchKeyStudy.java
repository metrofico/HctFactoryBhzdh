package com.hct.factory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.microntek.CarManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings.System;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class TouchKeyStudy extends Activity {
   private static final int KEYNUM = 12;
   private static final int KEYSTUDYMAX = 12;
   private static final int TOUCHNUM = 8;
   private CarManager mCarManager = null;
   private TextView mDot1;
   private TextView mDot2;
   private View mLiatMenuPart;
   private TextView mReport;
   private boolean mStarten = false;
   private int mStep = 0;
   private View mStep1Part;
   private View mStep2Part;
   private View mStep3Part;
   private StudyKeyView[] mStudyKey = new StudyKeyView[12];
   private int[] mTopBottom = new int[4];
   private Hct_Config.ST_TOUCH_CONFIG mTouch_config;
   private int touchcnt = 0;

   private void ChangeStep(int var1) {
      int var2 = var1 & 240;
      if (var2 != 16) {
         if (var2 != 32) {
            if (var2 != 48) {
               if (var2 == 64) {
                  this.mStep1Part.setVisibility(8);
                  this.mStep2Part.setVisibility(8);
                  this.mStep3Part.setVisibility(8);
                  this.mLiatMenuPart.setVisibility(0);
               }
            } else {
               this.mStep1Part.setVisibility(8);
               this.mStep2Part.setVisibility(8);
               this.mStep3Part.setVisibility(0);
               this.mLiatMenuPart.setVisibility(8);
            }
         } else {
            this.mStep1Part.setVisibility(8);
            this.mStep2Part.setVisibility(0);
            this.mStep3Part.setVisibility(8);
            this.mLiatMenuPart.setVisibility(8);
            this.CheckDataAndReport();
         }
      } else {
         this.mDot1.setTextColor(-65536);
         this.mDot2.setTextColor(-1);
         this.mStep1Part.setVisibility(0);
         this.mStep2Part.setVisibility(8);
         this.mStep3Part.setVisibility(8);
         this.mLiatMenuPart.setVisibility(8);
         this.setParameters("rpt_key_mode=touch");
      }

      if ((var1 & 240) >= 32) {
         System.putInt(this.getContentResolver(), "show_touches", 1);
      } else {
         System.putInt(this.getContentResolver(), "show_touches", 0);
      }

      this.mStep = var1;
   }

   private void CheckDataAndReport() {
      int[] var1 = this.mTopBottom;
      if (var1[0] < 12 && var1[1] < 12) {
         var1[0] = 0;
         var1[1] = 0;
      } else {
         var1 = this.mTopBottom;
         if (var1[2] < 12 && var1[3] < 12) {
            var1[2] = 0;
            var1[3] = 0;
         }
      }

      if (Math.abs(this.mTopBottom[0] - this.mTouch_config.mX) < 12) {
         this.mTopBottom[0] = this.mTouch_config.mX;
      } else if (Math.abs(this.mTopBottom[2] - this.mTouch_config.mX) < 12) {
         this.mTopBottom[2] = this.mTouch_config.mX;
      }

      if (Math.abs(this.mTopBottom[1] - this.mTouch_config.mY) < 12) {
         this.mTopBottom[1] = this.mTouch_config.mY;
      } else if (Math.abs(this.mTopBottom[3] - this.mTouch_config.mY) < 12) {
         this.mTopBottom[3] = this.mTouch_config.mY;
      }

      StringBuilder var3 = new StringBuilder();
      var3.append("rpt_touch_win=");
      var3.append(this.mTopBottom[0]);
      var3.append(",");
      var3.append(this.mTopBottom[1]);
      var3.append(",");
      var3.append(this.mTopBottom[2]);
      var3.append(",");
      var3.append(this.mTopBottom[3]);
      this.setParameters(var3.toString());
      int[] var2;
      Hct_Config.ST_TOUCH_CONFIG var4;
      if ("1".equals(SystemProperties.get("hct.appwm.ploy", "0"))) {
         var4 = this.mTouch_config;
         var2 = this.mTopBottom;
         var4.mXStart = var2[2];
         var4.mXEnd = var2[0];
      } else {
         var4 = this.mTouch_config;
         var2 = this.mTopBottom;
         var4.mXStart = var2[0];
         var4.mXEnd = var2[2];
      }

      Hct_Config.ST_TOUCH_CONFIG var5 = this.mTouch_config;
      var1 = this.mTopBottom;
      var5.mYStart = var1[1];
      var5.mYEnd = var1[3];
   }

   private void InitTouchConfig() {
      this.mTouch_config = new Hct_Config.ST_TOUCH_CONFIG();
      int[] var3 = new int[4];
      String var4 = this.getParameters("sta_touch_info=");
      int var1;
      if (var4 != null) {
         String[] var7 = var4.split(",");
         int var2 = var7.length;

         for(var1 = 0; var1 < var2; ++var1) {
            try {
               var3[var1] = Integer.parseInt(var7[var1]);
            } catch (NumberFormatException var6) {
            }
         }
      }

      Hct_Config.ST_TOUCH_CONFIG var8 = this.mTouch_config;
      var8.mConfigId = (byte)var3[0];
      var8.mVendorId = (byte)var3[1];
      var8.mX = var3[2];
      var8.mY = var3[3];

      for(var1 = 0; var1 < this.mTouch_config.mKey.length; ++var1) {
         this.mTouch_config.mKey[var1] = new Hct_Config.ST_TOUCH_KEY();
      }

   }

   private void StudyExit() {
      this.mStudyKey[0].getKeyIndex();
      String var1 = this.getString(2131296389).toString();
      (new AlertDialog.Builder(this)).setTitle(var1).setPositiveButton(this.getString(17039379).toString(), new DialogInterface.OnClickListener(this) {
         final TouchKeyStudy this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.this$0.SaveTouch_config(true);
         }
      }).setNegativeButton(this.getString(17039369).toString(), new DialogInterface.OnClickListener(this) {
         final TouchKeyStudy this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.this$0.finish();
         }
      }).show();
   }

   private void UpdataButton(int var1, int var2, int var3) {
      this.ChangeStep(48);
      if (var1 <= 0 && var2 <= 0) {
         for(var1 = 0; var1 < 12; ++var1) {
            if (this.mStudyKey[var1].getVisibility() != 0) {
               this.mStudyKey[var1].ClearAllData();
               break;
            }
         }
      } else {
         if (var3 != -1) {
            this.mStudyKey[var3].setButtonbg(2131034226);
            this.mStudyKey[var3].setKeyIndex(var3);
            if (var1 > 0) {
               this.mStudyKey[var3].setShortIco(Factory_Util.touchvaules[var1][0]);
               this.mStudyKey[var3].setKeyShortIR(Factory_Util.touchvaules[var1][1]);
            }

            if (var2 > 0) {
               this.mStudyKey[var3].setLongIco(Factory_Util.touchvaules[var2][0]);
               this.mStudyKey[var3].setKeyLongIR(Factory_Util.touchvaules[var2][1]);
            }

            return;
         }

         int var4 = -1;

         int var5;
         for(var3 = 0; var3 < 12 && this.mStudyKey[var3].getVisibility() == 0; var4 = var5) {
            var5 = var4;
            if (this.mStudyKey[var3].getKeyIndex() != -1) {
               var5 = var4;
               if (this.mStudyKey[var3].getKeyShortIR() == var1) {
                  var5 = var4;
                  if (this.mStudyKey[var3].getKeyLongIR() == var2) {
                     var5 = var3;
                  }
               }
            }

            ++var3;
         }

         if (var4 != -1) {
            for(var3 = 0; var3 < 12; ++var3) {
               if (var3 == var4) {
                  this.mStudyKey[var4].setButtonbg(2131034226);
                  this.mStudyKey[var4].setKeyIndex(var4);
                  if (var1 > 0) {
                     this.mStudyKey[var4].setShortIco(Factory_Util.touchvaules[var1][0]);
                     this.mStudyKey[var4].setKeyShortIR(Factory_Util.touchvaules[var1][1]);
                  }

                  if (var2 > 0) {
                     this.mStudyKey[var4].setLongIco(Factory_Util.touchvaules[var2][0]);
                     this.mStudyKey[var4].setKeyLongIR(Factory_Util.touchvaules[var2][1]);
                  }
               } else {
                  this.mStudyKey[var3].setButtonbg(2131034225);
               }

               if (this.mStudyKey[var3].getVisibility() != 0) {
                  break;
               }
            }
         } else {
            for(var3 = 0; var3 < 12; ++var3) {
               this.mStudyKey[var3].setButtonbg(2131034225);
               if (this.mStudyKey[var3].getVisibility() != 0) {
                  this.mStudyKey[var3].setVisibility(0);
                  this.mStudyKey[var3].setButtonbg(2131034226);
                  this.mStudyKey[var3].setKeyIndex(var3);
                  if (var1 > 0) {
                     this.mStudyKey[var3].setShortIco(Factory_Util.touchvaules[var1][0]);
                     this.mStudyKey[var3].setKeyShortIR(Factory_Util.touchvaules[var1][1]);
                  }

                  if (var2 > 0) {
                     this.mStudyKey[var3].setLongIco(Factory_Util.touchvaules[var2][0]);
                     this.mStudyKey[var3].setKeyLongIR(Factory_Util.touchvaules[var2][1]);
                  }
                  break;
               }
            }
         }
      }

   }

   private void checPoint(int var1, int var2) {
      int var3 = this.mStep;
      int[] var7;
      if (var3 == 16) {
         var7 = this.mTopBottom;
         var7[0] = var1;
         var7[1] = var2;
         ++this.touchcnt;
         if (Math.abs(var1 - var7[0]) < 10 && Math.abs(var2 - this.mTopBottom[1]) < 10 && this.touchcnt < 8) {
            return;
         }

         this.mStep = 17;
         this.touchcnt = 0;
         this.mDot2.setTextColor(-65536);
         this.mDot1.setTextColor(-1);
      } else if (var3 == 17) {
         if (Math.abs(var1 - this.mTopBottom[0]) < 10 && Math.abs(var2 - this.mTopBottom[1]) < 10) {
            return;
         }

         var7 = this.mTopBottom;
         var7[2] = var1;
         var7[3] = var2;
         ++this.touchcnt;
         if (Math.abs(var1 - var7[2]) < 10 && Math.abs(var2 - this.mTopBottom[3]) < 10 && this.touchcnt < 8) {
            return;
         }

         var7 = this.mTopBottom;
         if (Math.abs(var7[0] - var7[2]) >= 100) {
            var7 = this.mTopBottom;
            if (Math.abs(var7[1] - var7[3]) >= 100) {
               this.touchcnt = 0;
               this.ChangeStep(32);
               return;
            }
         }

         this.mTopBottom = new int[4];
         this.mDot1.setTextColor(-65536);
         this.mDot2.setTextColor(-1);
         this.mStep = 16;
         this.touchcnt = 0;
         this.ChangeStep(16);
      } else if ((var3 & 240) == 48) {
         if (Math.abs(var1 - this.mTopBottom[2]) < 10 && Math.abs(var2 - this.mTopBottom[3]) < 10) {
            return;
         }

         byte var5 = -1;
         var3 = 0;

         int var4;
         while(true) {
            var4 = var5;
            if (var3 >= 12) {
               break;
            }

            var4 = var5;
            if (this.mStudyKey[var3].getVisibility() != 0) {
               break;
            }

            var4 = this.mStudyKey[var3].getTouchX();
            int var6 = this.mStudyKey[var3].getTouchY();
            if (Math.abs(var1 - var4) < 10 && Math.abs(var2 - var6) < 10 && var4 != -1 || var4 == -1 && var6 != -1 && Math.abs(var2 - var6) == 0) {
               var4 = var3;
               break;
            }

            ++var3;
         }

         if (var4 != -1) {
            this.mReport.setText(2131296424);

            for(var1 = 0; var1 < 12 && this.mStudyKey[var1].getVisibility() == 0; ++var1) {
               if (var1 == var4) {
                  this.mStudyKey[var1].setButtonbg(2131034226);
               } else {
                  this.mStudyKey[var1].setButtonbg(2131034225);
               }
            }
         } else {
            for(var3 = 0; var3 < 12; ++var3) {
               if (this.mStudyKey[var3].getVisibility() != 0) {
                  this.mStudyKey[var3].setTouchX(var1);
                  this.mStudyKey[var3].setTouchY(var2);
                  break;
               }
            }

            this.showListMenu(-1);
         }
      }

   }

   private int getArraryIndex(int var1) {
      for(int var2 = 0; var2 < Factory_Util.touchvaules.length; ++var2) {
         if (Factory_Util.touchvaules[var2][1] == var1) {
            return var2;
         }
      }

      return -1;
   }

   private String getParameters(String var1) {
      return this.mCarManager.getParameters(var1);
   }

   private void setParameters(String var1) {
      this.mCarManager.setParameters(var1);
   }

   private void showListMenu(int var1) {
      this.ChangeStep(64);
      ListView var5 = (ListView)this.mLiatMenuPart.findViewById(2131099855);
      ListView var6 = (ListView)this.mLiatMenuPart.findViewById(2131099805);
      ArrayList var7 = new ArrayList();

      int var2;
      for(var2 = 0; var2 < Factory_Util.touchvaules.length; ++var2) {
         if (var2 == 0) {
            var7.add(2131034190);
         } else {
            var7.add(Factory_Util.touchvaules[var2][0]);
         }
      }

      ListAdapter var3 = new ListAdapter(this, var7);
      ListAdapter var4 = new ListAdapter(this, var7);
      var5.setAdapter(var3);
      var6.setAdapter(var4);
      if (var1 >= 0) {
         var2 = this.getArraryIndex(this.mStudyKey[var1].getKeyShortIR());
         if (var2 != -1) {
            var3.setSelectedItem(var2);
         } else {
            var3.setSelectedItem(0);
         }

         var2 = this.getArraryIndex(this.mStudyKey[var1].getKeyLongIR());
         if (var2 != -1) {
            var4.setSelectedItem(var2);
         } else {
            var4.setSelectedItem(0);
         }
      } else {
         var3.setSelectedItem(0);
         var4.setSelectedItem(0);
      }

      var5.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var7, var4, var6, var3) {
         final TouchKeyStudy this$0;
         final ListView val$longListView;
         final ListAdapter val$longListdapter;
         final ListAdapter val$shortListdapter;
         final ArrayList val$tList;

         {
            this.this$0 = var1;
            this.val$tList = var2;
            this.val$longListdapter = var3;
            this.val$longListView = var4;
            this.val$shortListdapter = var5;
         }

         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            if (2131034222 != (Integer)this.val$tList.get(var3) && 2131034221 != (Integer)this.val$tList.get(var3)) {
               this.val$longListView.setEnabled(true);
            } else {
               this.val$longListdapter.setSelectedItem(0);
               this.val$longListdapter.notifyDataSetChanged();
               this.val$longListView.setEnabled(false);
               this.val$longListView.setSelection(0);
            }

            this.val$shortListdapter.setSelectedItem(var3);
            this.val$shortListdapter.notifyDataSetChanged();
         }
      });
      var6.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var7, var3, var5, var4) {
         final TouchKeyStudy this$0;
         final ListAdapter val$longListdapter;
         final ListView val$shortListView;
         final ListAdapter val$shortListdapter;
         final ArrayList val$tList;

         {
            this.this$0 = var1;
            this.val$tList = var2;
            this.val$shortListdapter = var3;
            this.val$shortListView = var4;
            this.val$longListdapter = var5;
         }

         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            if (2131034222 != (Integer)this.val$tList.get(var3) && 2131034221 != (Integer)this.val$tList.get(var3)) {
               this.val$shortListView.setEnabled(true);
            } else {
               this.val$shortListdapter.setSelectedItem(0);
               this.val$shortListdapter.notifyDataSetChanged();
               this.val$shortListView.setEnabled(false);
               this.val$shortListView.setSelection(0);
            }

            this.val$longListdapter.setSelectedItem(var3);
            this.val$longListdapter.notifyDataSetChanged();
         }
      });
      Button var8 = (Button)this.mLiatMenuPart.findViewById(2131099796);
      Button var9 = (Button)this.mLiatMenuPart.findViewById(2131099795);
      var8.setOnClickListener(new View.OnClickListener(this, var3, var4, var1) {
         final TouchKeyStudy this$0;
         final int val$Index;
         final ListAdapter val$longListdapter;
         final ListAdapter val$shortListdapter;

         {
            this.this$0 = var1;
            this.val$shortListdapter = var2;
            this.val$longListdapter = var3;
            this.val$Index = var4;
         }

         public void onClick(View var1) {
            int var3 = this.val$shortListdapter.getSelectedItem();
            int var2 = this.val$longListdapter.getSelectedItem();
            this.this$0.UpdataButton(var3, var2, this.val$Index);
         }
      });
      var9.setOnClickListener(new View.OnClickListener(this) {
         final TouchKeyStudy this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.UpdataButton(-1, -1, -1);
         }
      });
   }

   public void SaveTouch_config(boolean var1) {
      byte[] var6 = new byte[90];
      int var3 = 0 + 1;
      var6[0] = this.mTouch_config.mConfigId;
      int var2 = var3 + 1;
      var6[var3] = this.mTouch_config.mVendorId;
      if (this.mTouch_config.mX == 0) {
         this.mTouch_config.mX = 1024;
      }

      if (this.mTouch_config.mY == 0) {
         this.mTouch_config.mY = 600;
      }

      int var4 = var2 + 1;
      var6[var2] = (byte)(this.mTouch_config.mX & 255);
      var3 = var4 + 1;
      var6[var4] = (byte)(this.mTouch_config.mX >> 8 & 255);
      var2 = var3 + 1;
      var6[var3] = (byte)(this.mTouch_config.mY & 255);
      var3 = var2 + 1;
      var6[var2] = (byte)(this.mTouch_config.mY >> 8 & 255);
      var2 = var3 + 1;
      var6[var3] = (byte)(this.mTouch_config.mXStart & 255);
      var3 = var2 + 1;
      var6[var2] = (byte)(this.mTouch_config.mXStart >> 8 & 255);
      var2 = var3 + 1;
      var6[var3] = (byte)(this.mTouch_config.mXEnd & 255);
      var3 = var2 + 1;
      var6[var2] = (byte)(this.mTouch_config.mXEnd >> 8 & 255);
      var4 = var3 + 1;
      var6[var3] = (byte)(this.mTouch_config.mYStart & 255);
      var2 = var4 + 1;
      var6[var4] = (byte)(this.mTouch_config.mYStart >> 8 & 255);
      var3 = var2 + 1;
      var6[var2] = (byte)(this.mTouch_config.mYEnd & 255);
      var2 = var3 + 1;
      var6[var3] = (byte)(this.mTouch_config.mYEnd >> 8 & 255);

      for(var3 = 0; var3 < this.mTouch_config.mRev.length; ++var2) {
         var6[var2] = this.mTouch_config.mRev[var3];
         ++var3;
      }

      for(var3 = 0; var3 < 12 && this.mStudyKey[var3].getVisibility() == 0; ++var3) {
         var4 = var2 + 1;
         var6[var2] = (byte)(this.mStudyKey[var3].getKeyShortIR() & 255);
         var2 = var4 + 1;
         var6[var4] = (byte)(this.mStudyKey[var3].getKeyShortIR() >> 8 & 255);
         var4 = var2 + 1;
         var6[var2] = (byte)(this.mStudyKey[var3].getKeyLongIR() & 255);
         var2 = var4 + 1;
         var6[var4] = (byte)(this.mStudyKey[var3].getKeyLongIR() >> 8 & 255);
         if (this.mStudyKey[var3].getTouchX() == -1) {
            var4 = var2 + 1;
            var6[var2] = -1;
            var2 = var4 + 1;
            var6[var4] = (byte)(this.mStudyKey[var3].getTouchY() & 255);
         } else {
            var4 = var2 + 1;
            var6[var2] = (byte)(this.mStudyKey[var3].getTouchX() * 200 / this.mTouch_config.mX);
            var2 = var4 + 1;
            var6[var4] = (byte)(this.mStudyKey[var3].getTouchY() * 200 / this.mTouch_config.mY);
         }
      }

      StringBuffer var5 = new StringBuffer();

      for(var2 = 0; var2 < var6.length; ++var2) {
         var5.append(var6[var2] & 255);
         if (var2 != var6.length - 1) {
            var5.append(",");
         }
      }

      StringBuilder var7 = new StringBuilder();
      var7.append("cfg_touch=");
      var7.append(var5.toString());
      this.setParameters(var7.toString());
      if (var1) {
         this.finish();
      }

   }

   public void hideSystemUI() {
      this.getWindow().getDecorView().setSystemUiVisibility(5894);
   }

   protected void onActivityResult(int var1, int var2, Intent var3) {
      super.onActivityResult(var1, var2, var3);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.mCarManager = new CarManager();
      this.mCarManager.attach(new Handler(this) {
         final TouchKeyStudy this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            if ("TouchStudy".equals((String)var1.obj)) {
               Bundle var4 = var1.getData();
               String var5 = var4.getString("type");
               int var2;
               if ("touch_key".equals(var5)) {
                  var2 = var4.getInt("index");
                  this.this$0.checPoint(-1, var2);
               } else if ("touch_xy".equals(var5)) {
                  var2 = var4.getInt("x");
                  int var3 = var4.getInt("y");
                  this.this$0.checPoint(var2, var3);
               }
            }

         }
      }, "TouchStudy");
      if (this.getIntent().getStringExtra("common").equals("hcttouch")) {
         this.mStarten = true;
         this.setContentView(2131230746);
         this.mStep1Part = this.findViewById(2131099895);
         this.mStep2Part = this.findViewById(2131099896);
         this.mStep3Part = this.findViewById(2131099897);
         this.mLiatMenuPart = this.findViewById(2131099889);
         this.mDot1 = (TextView)this.mStep1Part.findViewById(2131099860);
         this.mDot2 = (TextView)this.mStep1Part.findViewById(2131099861);
         Button var4 = (Button)this.mStep2Part.findViewById(2131099891);
         Button var3 = (Button)this.mStep2Part.findViewById(2131099890);
         var4.setOnClickListener(new View.OnClickListener(this) {
            final TouchKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.ChangeStep(48);
            }
         });
         var3.setOnClickListener(new View.OnClickListener(this) {
            final TouchKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mStep = 16;
               TouchKeyStudy var2 = this.this$0;
               var2.ChangeStep(var2.mStep);
            }
         });
         this.mReport = (TextView)this.mStep3Part.findViewById(2131099894);
         var4 = (Button)this.mStep3Part.findViewById(2131099892);
         var3 = (Button)this.mStep3Part.findViewById(2131099893);
         var4.setOnClickListener(new View.OnClickListener(this) {
            final TouchKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               int var2;
               for(var2 = 0; var2 < this.this$0.mStudyKey.length; ++var2) {
                  this.this$0.mStudyKey[var2].ClearAllData();
               }

               this.this$0.mReport.setText(2131296494);
               byte[] var3 = new byte[90];
               StringBuffer var5 = new StringBuffer();

               for(var2 = 0; var2 < var3.length; ++var2) {
                  var5.append(var3[var2] & 255);
                  if (var2 != var3.length - 1) {
                     var5.append(",");
                  }
               }

               TouchKeyStudy var4 = this.this$0;
               StringBuilder var6 = new StringBuilder();
               var6.append("cfg_touch=");
               var6.append(var5.toString());
               var4.setParameters(var6.toString());
            }
         });
         var3.setOnClickListener(new View.OnClickListener(this) {
            final TouchKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.StudyExit();
            }
         });
         this.mStudyKey[0] = (StudyKeyView)this.mStep3Part.findViewById(2131099898);
         this.mStudyKey[1] = (StudyKeyView)this.mStep3Part.findViewById(2131099899);
         this.mStudyKey[2] = (StudyKeyView)this.mStep3Part.findViewById(2131099900);
         this.mStudyKey[3] = (StudyKeyView)this.mStep3Part.findViewById(2131099901);
         this.mStudyKey[4] = (StudyKeyView)this.mStep3Part.findViewById(2131099902);
         this.mStudyKey[5] = (StudyKeyView)this.mStep3Part.findViewById(2131099903);
         this.mStudyKey[6] = (StudyKeyView)this.mStep3Part.findViewById(2131099904);
         this.mStudyKey[7] = (StudyKeyView)this.mStep3Part.findViewById(2131099905);
         this.mStudyKey[8] = (StudyKeyView)this.mStep3Part.findViewById(2131099906);
         this.mStudyKey[9] = (StudyKeyView)this.mStep3Part.findViewById(2131099907);
         this.mStudyKey[10] = (StudyKeyView)this.mStep3Part.findViewById(2131099908);
         this.mStudyKey[11] = (StudyKeyView)this.mStep3Part.findViewById(2131099909);
         int var2 = 0;

         while(true) {
            StudyKeyView[] var5 = this.mStudyKey;
            if (var2 >= var5.length) {
               this.InitTouchConfig();
               this.ChangeStep(16);
               break;
            }

            var5[var2].ClearAllData();
            ++var2;
         }
      } else {
         this.finish();
      }

   }

   protected void onDestroy() {
      System.putInt(this.getContentResolver(), "show_touches", 0);
      this.setParameters("rpt_key_mode=normal");
      this.mCarManager.detach();
      super.onDestroy();
   }

   protected void onPause() {
      super.onPause();
      this.finish();
   }

   public void onWindowFocusChanged(boolean var1) {
      super.onWindowFocusChanged(var1);
      if (var1) {
         this.hideSystemUI();
      }

   }
}
