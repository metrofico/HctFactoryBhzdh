package com.hct.factory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.microntek.CarManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class PannelKeyStudy extends Activity {
   private static final int DOT = 10;
   private static final int KEYNUM = 40;
   private static final int KEYSTUDYMAX = 30;
   private CarManager mCarManager = null;
   private View mKeyPartPart;
   private View mListMenuPart;
   private TextView mReport;
   private StudyKeyView[] mStudyKey = new StudyKeyView[30];
   private String mTmpKey = null;

   private void LoadKey_config() {
      int[] var4 = new int[120];
      String var5 = this.getParameters("cfg_key=");
      this.mTmpKey = var5;
      int var1;
      int var2;
      if (var5 != null) {
         String[] var6 = var5.split(",");
         var2 = var6.length;

         for(var1 = 0; var1 < var2 && var1 <= var2; ++var1) {
            try {
               var4[var1] = Integer.parseInt(var6[var1]);
            } catch (NumberFormatException var7) {
            }
         }
      }

      var2 = 0;

      int var3;
      for(var1 = 0; var1 < 30; var2 = var3) {
         label57: {
            if (var4[var1 * 3 + 1] == 0) {
               var3 = var2;
               if (var4[var1 * 3 + 2] == 0) {
                  break label57;
               }
            }

            this.mStudyKey[var2].setKeyVaule(var4[var1 * 3]);
            if (var4[var1 * 3 + 1] != 0) {
               var3 = this.getKeyResID(var4[var1 * 3 + 1]);
               if (var3 != -1) {
                  this.mStudyKey[var2].setShortIco(var3);
                  this.mStudyKey[var2].setKeyShortIR(var4[var1 * 3 + 1]);
               }
            }

            if (var4[var1 * 3 + 2] != 0) {
               var3 = this.getKeyResID(var4[var1 * 3 + 2]);
               if (var3 != -1) {
                  this.mStudyKey[var2].setLongIco(var3);
                  this.mStudyKey[var2].setKeyLongIR(var4[var1 * 3 + 2]);
               }
            }

            this.mStudyKey[var2].setKeyIndex(var2);
            var3 = var2 + 1;
         }

         ++var1;
      }

   }

   private String LoadSaveKey_config() {
      boolean var2 = true;
      int[] var5 = new int[120];

      int var1;
      boolean var3;
      for(var1 = 0; var1 < 30; var2 = var3) {
         var3 = var2;
         if (this.mStudyKey[var1].getVisibility() == 0) {
            label60: {
               if (this.mStudyKey[var1].getKeyShortIR() == 0) {
                  var3 = var2;
                  if (this.mStudyKey[var1].getKeyLongIR() == 0) {
                     break label60;
                  }
               }

               var5[var1 * 3] = this.mStudyKey[var1].getKeyVaule();
               var5[var1 * 3 + 1] = this.mStudyKey[var1].getKeyShortIR();
               var5[var1 * 3 + 2] = this.mStudyKey[var1].getKeyLongIR();
               var3 = false;
            }
         }

         ++var1;
      }

      StringBuffer var4 = new StringBuffer();
      String var6;
      if (!var2) {
         for(var1 = 0; var1 < var5.length; ++var1) {
            var4.append(var5[var1]);
            if (var1 != var5.length - 1) {
               var4.append(",");
            }
         }

         var6 = var4.toString();
      } else {
         var5 = new int[120];

         for(var1 = 0; var1 < var5.length; ++var1) {
            var4.append(var5[var1]);
            if (var1 != var5.length - 1) {
               var4.append(",");
            }
         }

         var6 = var4.toString();
      }

      return var6;
   }

   private void StudyExit() {
      String var1 = this.LoadSaveKey_config();
      if (!var1.equals(this.mTmpKey)) {
         this.mTmpKey = var1;
         var1 = this.getString(2131296389).toString();
         (new AlertDialog.Builder(this)).setTitle(var1).setPositiveButton(this.getString(17039379).toString(), new DialogInterface.OnClickListener(this) {
            final PannelKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               PannelKeyStudy var3 = this.this$0;
               StringBuilder var4 = new StringBuilder();
               var4.append("cfg_key=");
               var4.append(this.this$0.mTmpKey);
               var3.setParameters(var4.toString());
               this.this$0.finish();
            }
         }).setNegativeButton(this.getString(17039369).toString(), new DialogInterface.OnClickListener(this) {
            final PannelKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.finish();
            }
         }).show();
      } else {
         this.finish();
      }

   }

   private void UpdataButton(int var1, int var2, int var3) {
      if (var1 <= 0 && var2 <= 0) {
         if (var3 >= 0) {
            this.mStudyKey[var3].setLongIco(0);
            this.mStudyKey[var3].setKeyLongIR(0);
            this.mStudyKey[var3].setShortIco(0);
            this.mStudyKey[var3].setKeyShortIR(0);
         }

         for(var1 = 0; var1 < 30; ++var1) {
            if (this.mStudyKey[var1].getVisibility() != 0) {
               this.mStudyKey[var1].ClearAllData();
               break;
            }
         }
      } else {
         if (var3 >= 0) {
            this.mStudyKey[var3].setButtonbg(2131034226);
            this.mStudyKey[var3].setKeyIndex(var3);
            if (var1 > 0) {
               this.mStudyKey[var3].setShortIco(Factory_Util.studykeyvaules[var1][0]);
               this.mStudyKey[var3].setKeyShortIR(Factory_Util.studykeyvaules[var1][1]);
            } else {
               this.mStudyKey[var3].setShortIco(0);
               this.mStudyKey[var3].setKeyShortIR(0);
            }

            if (var2 > 0) {
               this.mStudyKey[var3].setLongIco(Factory_Util.studykeyvaules[var2][0]);
               this.mStudyKey[var3].setKeyLongIR(Factory_Util.studykeyvaules[var2][1]);
            } else {
               this.mStudyKey[var3].setLongIco(0);
               this.mStudyKey[var3].setKeyLongIR(0);
            }

            this.mKeyPartPart.setVisibility(0);
            this.mListMenuPart.setVisibility(8);
            return;
         }

         int var4 = -1;

         int var5;
         for(var3 = 0; var3 < 30 && this.mStudyKey[var3].getVisibility() == 0; var4 = var5) {
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
            for(var3 = 0; var3 < 30; ++var3) {
               if (var3 == var4) {
                  this.mStudyKey[var4].setButtonbg(2131034226);
                  this.mStudyKey[var4].setKeyIndex(var4);
                  if (var1 > 0) {
                     this.mStudyKey[var4].setShortIco(Factory_Util.studykeyvaules[var1][0]);
                     this.mStudyKey[var4].setKeyShortIR(Factory_Util.studykeyvaules[var1][1]);
                  }

                  if (var2 > 0) {
                     this.mStudyKey[var4].setLongIco(Factory_Util.studykeyvaules[var2][0]);
                     this.mStudyKey[var4].setKeyLongIR(Factory_Util.studykeyvaules[var2][1]);
                  }
               } else {
                  this.mStudyKey[var3].setButtonbg(2131034225);
               }

               if (this.mStudyKey[var3].getVisibility() != 0) {
                  break;
               }
            }
         } else {
            for(var3 = 0; var3 < 30; ++var3) {
               this.mStudyKey[var3].setButtonbg(2131034225);
               if (this.mStudyKey[var3].getVisibility() != 0) {
                  this.mStudyKey[var3].setVisibility(0);
                  this.mStudyKey[var3].setButtonbg(2131034226);
                  this.mStudyKey[var3].setKeyIndex(var3);
                  if (var1 > 0) {
                     this.mStudyKey[var3].setShortIco(Factory_Util.studykeyvaules[var1][0]);
                     this.mStudyKey[var3].setKeyShortIR(Factory_Util.studykeyvaules[var1][1]);
                  }

                  if (var2 > 0) {
                     this.mStudyKey[var3].setLongIco(Factory_Util.studykeyvaules[var2][0]);
                     this.mStudyKey[var3].setKeyLongIR(Factory_Util.studykeyvaules[var2][1]);
                  }
                  break;
               }
            }
         }
      }

      this.mReport.setText(2131296419);
      this.mKeyPartPart.setVisibility(0);
      this.mListMenuPart.setVisibility(8);
   }

   private void checkPoint(int var1) {
      if (this.mListMenuPart.getVisibility() != 0) {
         byte var4 = -1;
         int var2 = 0;

         int var3;
         while(true) {
            var3 = var4;
            if (var2 >= 30) {
               break;
            }

            var3 = var4;
            if (this.mStudyKey[var2].getVisibility() != 0) {
               break;
            }

            var3 = this.mStudyKey[var2].getKeyVaule();
            if (this.mStudyKey[var2].getKeyIndex() != -1 && var3 != 0 && Math.abs(var3 - var1) < 10) {
               var3 = var2;
               if (2131034226 == this.mStudyKey[var2].getButtonbg()) {
                  this.showListMenu(var2);
                  return;
               }
               break;
            }

            ++var2;
         }

         if (var3 != -1) {
            this.mReport.setText(2131296417);

            for(var1 = 0; var1 < 30 && this.mStudyKey[var1].getVisibility() == 0; ++var1) {
               if (var1 == var3) {
                  this.mStudyKey[var1].setButtonbg(2131034226);
               } else {
                  this.mStudyKey[var1].setButtonbg(2131034225);
               }
            }
         } else {
            for(var2 = 0; var2 < 30; ++var2) {
               if (this.mStudyKey[var2].getVisibility() != 0) {
                  this.mStudyKey[var2].setKeyVaule(var1);
                  break;
               }
            }

            this.showListMenu(-1);
         }

      }
   }

   private int getArraryIndex(int var1) {
      for(int var2 = 0; var2 < Factory_Util.studykeyvaules.length; ++var2) {
         if (Factory_Util.studykeyvaules[var2][1] == var1) {
            return var2;
         }
      }

      return -1;
   }

   private String getIRTableKeyName(int param1) {
      // $FF: Couldn't be decompiled
   }

   private int getKeyResID(int var1) {
      byte var4 = -1;
      int var3 = 0;

      int var2;
      while(true) {
         var2 = var4;
         if (var3 >= Factory_Util.studykeyvaules.length) {
            break;
         }

         if (Factory_Util.studykeyvaules[var3][1] == var1) {
            var2 = Factory_Util.studykeyvaules[var3][0];
            break;
         }

         ++var3;
      }

      return var2;
   }

   private String getParameters(String var1) {
      return this.mCarManager.getParameters(var1);
   }

   private void hideSystemUI() {
      this.getWindow().getDecorView().setSystemUiVisibility(5894);
   }

   private void resetDataView() {
      this.mReport.setText(2131296419);
      this.mKeyPartPart.setVisibility(0);
      this.mListMenuPart.setVisibility(8);

      for(int var1 = 0; var1 < 30; ++var1) {
         this.mStudyKey[var1].ClearAllData();
      }

   }

   private void setParameters(String var1) {
      this.mCarManager.setParameters(var1);
   }

   private void showListMenu(int var1) {
      this.mReport.setText(2131296419);
      this.mKeyPartPart.setVisibility(8);
      this.mListMenuPart.setVisibility(0);
      ListView var5 = (ListView)this.mListMenuPart.findViewById(2131099855);
      ListView var7 = (ListView)this.mListMenuPart.findViewById(2131099805);
      ArrayList var6 = new ArrayList();

      int var2;
      for(var2 = 0; var2 < Factory_Util.studykeyvaules.length; ++var2) {
         if (var2 == 0) {
            var6.add(2131034190);
         } else {
            var6.add(Factory_Util.studykeyvaules[var2][0]);
         }
      }

      ListAdapter var3 = new ListAdapter(this, var6);
      ListAdapter var4 = new ListAdapter(this, var6);
      var5.setAdapter(var3);
      var7.setAdapter(var4);
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

      var5.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var6, var4, var7, var3) {
         final PannelKeyStudy this$0;
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
      var7.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var6, var3, var5, var4) {
         final PannelKeyStudy this$0;
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
      Button var8 = (Button)this.mListMenuPart.findViewById(2131099796);
      Button var9 = (Button)this.mListMenuPart.findViewById(2131099795);
      var8.setOnClickListener(new View.OnClickListener(this, var3, var4, var1) {
         final PannelKeyStudy this$0;
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
            int var2 = this.val$shortListdapter.getSelectedItem();
            int var3 = this.val$longListdapter.getSelectedItem();
            this.this$0.UpdataButton(var2, var3, this.val$Index);
         }
      });
      var9.setOnClickListener(new View.OnClickListener(this) {
         final PannelKeyStudy this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.UpdataButton(0, 0, -1);
         }
      });
   }

   private void updatView(int var1) {
      for(int var2 = 0; var2 < 30; ++var2) {
         if (this.mStudyKey[var2].getKeyIndex() == var1 && var1 != -1) {
            this.mStudyKey[var2].setVisibility(0);
            this.mStudyKey[var2].setButtonbg(2131034226);
         } else {
            if (this.mStudyKey[var2].getKeyIndex() != -1) {
               this.mStudyKey[var2].setVisibility(0);
            } else {
               this.mStudyKey[var2].setVisibility(4);
            }

            this.mStudyKey[var2].setButtonbg(2131034225);
         }
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.mCarManager = new CarManager();
      this.mCarManager.attach(new Handler(this) {
         final PannelKeyStudy this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            if ("PanelStudyKey".equals((String)var1.obj)) {
               Bundle var3 = var1.getData();
               if ("key".equals(var3.getString("type"))) {
                  int var2 = var3.getInt("value");
                  this.this$0.checkPoint(var2);
               }
            }

         }
      }, "PanelStudyKey");
      if (this.getIntent().getStringExtra("common").equals("hctpannel")) {
         this.setContentView(2131230737);
         this.mKeyPartPart = this.findViewById(2131099821);
         this.mListMenuPart = this.findViewById(2131099822);
         this.mReport = (TextView)this.mKeyPartPart.findViewById(2131099838);
         this.mStudyKey[0] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099898);
         this.mStudyKey[1] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099899);
         this.mStudyKey[2] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099900);
         this.mStudyKey[3] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099901);
         this.mStudyKey[4] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099902);
         this.mStudyKey[5] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099903);
         this.mStudyKey[6] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099904);
         this.mStudyKey[7] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099905);
         this.mStudyKey[8] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099906);
         this.mStudyKey[9] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099907);
         this.mStudyKey[10] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099908);
         this.mStudyKey[11] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099909);
         this.mStudyKey[12] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099910);
         this.mStudyKey[13] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099911);
         this.mStudyKey[14] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099912);
         this.mStudyKey[15] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099913);
         this.mStudyKey[16] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099914);
         this.mStudyKey[17] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099915);
         this.mStudyKey[18] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099916);
         this.mStudyKey[19] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099917);
         this.mStudyKey[20] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099918);
         this.mStudyKey[21] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099919);
         this.mStudyKey[22] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099920);
         this.mStudyKey[23] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099921);
         this.mStudyKey[24] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099922);
         this.mStudyKey[25] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099923);
         this.mStudyKey[26] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099924);
         this.mStudyKey[27] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099925);
         this.mStudyKey[28] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099926);
         this.mStudyKey[29] = (StudyKeyView)this.mKeyPartPart.findViewById(2131099927);
         this.resetDataView();
         Button var2 = (Button)this.mKeyPartPart.findViewById(2131099862);
         Button var3 = (Button)this.mKeyPartPart.findViewById(2131099819);
         var2.setOnClickListener(new View.OnClickListener(this) {
            final PannelKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.resetDataView();
            }
         });
         var3.setOnClickListener(new View.OnClickListener(this) {
            final PannelKeyStudy this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.StudyExit();
            }
         });
         this.LoadKey_config();
         this.updatView(-1);
      } else {
         this.finish();
      }

   }

   public void onDestroy() {
      this.mCarManager.detach();
      super.onDestroy();
   }

   protected void onPause() {
      super.onPause();
      this.setParameters("rpt_key_mode=normal");
   }

   public void onResume() {
      super.onResume();
      this.setParameters("rpt_key_mode=key");
   }

   public void onWindowFocusChanged(boolean var1) {
      super.onWindowFocusChanged(var1);
      if (var1) {
         this.hideSystemUI();
      }

   }
}
