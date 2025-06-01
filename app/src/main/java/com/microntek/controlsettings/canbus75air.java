package com.microntek.controlsettings;

import android.os.Bundle;
import android.provider.Settings.System;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class canbus75air extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
   private boolean isOnOff = false;
   private ImageButton mAc;
   private ImageButton mBtn_rearview;
   private ImageButton mBtn_seatlefthot;
   private ImageButton mBtn_seatrighthot;
   private ImageButton mFront;
   private int mID = -1;
   private ImageButton mLevel;
   private ImageButton mLevel_max;
   private ImageButton mLevel_min;
   private ImageButton mLf_temp_down;
   private ImageButton mLf_temp_up;
   private TextView mLf_txt_temp;
   private ImageButton mLoop;
   private ImageButton mMax_ac;
   private ImageButton mMod;
   private ImageButton mMod2;
   private ImageButton mMod3;
   private ImageButton mMod4;
   private ImageButton mOn;
   private ImageButton mOut;
   private ImageButton mRear;
   private TextView mRearview;
   private ImageButton mRi_temp_down;
   private ImageButton mRi_temp_up;
   private TextView mRi_txt_temp;
   private TextView mSeatlefthot;
   private TextView mSeatrighthot;
   private ImageButton mSync;
   private byte[] setData = new byte[30];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-107, new byte[]{var1, var2}, 2);
   }

   private void findView() {
      this.mMax_ac = (ImageButton)this.findViewById(2131165229);
      this.mMax_ac.setOnTouchListener(this);
      this.mAc = (ImageButton)this.findViewById(2131165184);
      this.mAc.setOnTouchListener(this);
      this.mLoop = (ImageButton)this.findViewById(2131165228);
      this.mLoop.setOnTouchListener(this);
      this.mOut = (ImageButton)this.findViewById(2131165248);
      this.mOut.setOnTouchListener(this);
      this.mFront = (ImageButton)this.findViewById(2131165218);
      this.mFront.setOnTouchListener(this);
      this.mRear = (ImageButton)this.findViewById(2131165249);
      this.mRear.setOnTouchListener(this);
      this.mOn = (ImageButton)this.findViewById(2131165247);
      this.mOn.setOnTouchListener(this);
      this.mLevel_min = (ImageButton)this.findViewById(2131165224);
      this.mLevel_min.setOnTouchListener(this);
      this.mLevel_max = (ImageButton)this.findViewById(2131165223);
      this.mLevel_max.setOnTouchListener(this);
      this.mSync = (ImageButton)this.findViewById(2131165263);
      this.mSync.setOnTouchListener(this);
      this.mLf_temp_up = (ImageButton)this.findViewById(2131165226);
      this.mLf_temp_up.setOnTouchListener(this);
      this.mLf_temp_down = (ImageButton)this.findViewById(2131165225);
      this.mLf_temp_down.setOnTouchListener(this);
      this.mRi_temp_up = (ImageButton)this.findViewById(2131165253);
      this.mRi_temp_up.setOnTouchListener(this);
      this.mRi_temp_down = (ImageButton)this.findViewById(2131165252);
      this.mRi_temp_down.setOnTouchListener(this);
      this.mMod = (ImageButton)this.findViewById(2131165232);
      this.mMod.setOnTouchListener(this);
      this.mMod2 = (ImageButton)this.findViewById(2131165233);
      this.mMod2.setOnTouchListener(this);
      this.mMod3 = (ImageButton)this.findViewById(2131165234);
      this.mMod3.setOnTouchListener(this);
      this.mMod4 = (ImageButton)this.findViewById(2131165235);
      this.mMod4.setOnTouchListener(this);
      this.mBtn_seatlefthot = (ImageButton)this.findViewById(2131165196);
      this.mBtn_seatlefthot.setOnTouchListener(this);
      this.mBtn_seatrighthot = (ImageButton)this.findViewById(2131165197);
      this.mBtn_seatrighthot.setOnTouchListener(this);
      this.mLevel = (ImageButton)this.findViewById(2131165222);
      this.mLf_txt_temp = (TextView)this.findViewById(2131165227);
      this.mRi_txt_temp = (TextView)this.findViewById(2131165254);
      this.mSeatlefthot = (TextView)this.findViewById(2131165258);
      this.mSeatrighthot = (TextView)this.findViewById(2131165259);
      this.mRearview = (TextView)this.findViewById(2131165250);
      this.mBtn_rearview = (ImageButton)this.findViewById(2131165195);
      this.mBtn_rearview.setOnClickListener(new View.OnClickListener(this) {
         final canbus75air this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            String var2 = this.this$0.mRearview.getText().toString().trim();
            if (var2 != null && var2.equals("ON")) {
               this.this$0.SendCanBusCmdData2E((byte)-105, new byte[]{81, 1}, 2);
            } else {
               this.this$0.SendCanBusCmdData2E((byte)-105, new byte[]{81, 2}, 2);
            }

         }
      });
   }

   private String getAcTemp(int var1) {
      String var2 = "";
      if (var1 == 0) {
         var2 = "LO";
      } else if (var1 == 127) {
         var2 = "HI";
      } else {
         StringBuilder var3;
         if (var1 <= 30) {
            var3 = new StringBuilder();
            var3.append(var1);
            var3.append("");
            var3.append(this.getString(2131296562));
            var2 = var3.toString();
         } else if (var1 <= 84) {
            var3 = new StringBuilder();
            var3.append(var1);
            var3.append("F");
            var2 = var3.toString();
         }
      }

      return var2;
   }

   private String getSeat(int var1) {
      if (var1 == 2) {
         return "HI";
      } else {
         return var1 == 1 ? "LO" : "";
      }
   }

   private void updataSettings() {
      boolean var2;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.isOnOff = var2;
      ImageButton var3 = this.mMax_ac;
      int var1;
      if ((this.setData[0] & 32) != 0) {
         var1 = 2131099734;
      } else {
         var1 = 2131099668;
      }

      var3.setBackgroundResource(var1);
      var3 = this.mAc;
      if ((this.setData[0] & 64) != 0) {
         var1 = 2131099714;
      } else {
         var1 = 2131099662;
      }

      var3.setBackgroundResource(var1);
      var3 = this.mLoop;
      if ((this.setData[0] >> 2 & 3) != 0) {
         var1 = 2131099732;
      } else {
         var1 = 2131099667;
      }

      var3.setBackgroundResource(var1);
      var3 = this.mOut;
      if ((this.setData[0] & 16) != 0) {
         var1 = 2131099746;
      } else {
         var1 = 2131099674;
      }

      var3.setBackgroundResource(var1);
      var3 = this.mFront;
      if ((this.setData[0] & 1) != 0) {
         var1 = 2131099718;
      } else {
         var1 = 2131099664;
      }

      var3.setBackgroundResource(var1);
      var3 = this.mRear;
      if ((this.setData[0] & 2) != 0) {
         var1 = 2131099748;
      } else {
         var1 = 2131099675;
      }

      var3.setBackgroundResource(var1);
      var3 = this.mOn;
      if (this.isOnOff) {
         var1 = 2131099744;
      } else {
         var1 = 2131099673;
      }

      var3.setBackgroundResource(var1);
      var3 = this.mSync;
      if ((this.setData[1] & 128) != 0) {
         var1 = 2131099756;
      } else {
         var1 = 2131099679;
      }

      var3.setBackgroundResource(var1);
      this.mLevel.setBackgroundResource((this.setData[1] & 15) + 2131099719);
      TextView var4 = this.mLf_txt_temp;
      StringBuilder var6 = new StringBuilder();
      var6.append("");
      var6.append(this.getAcTemp(this.setData[3] & 255));
      var4.setText(var6.toString());
      var4 = this.mRi_txt_temp;
      var6 = new StringBuilder();
      var6.append("");
      var6.append(this.getAcTemp(this.setData[4] & 255));
      var4.setText(var6.toString());
      TextView var7 = this.mSeatrighthot;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      var5.append(this.getSeat(this.setData[5] >> 2 & 3));
      var7.setText(var5.toString());
      var4 = this.mSeatlefthot;
      var6 = new StringBuilder();
      var6.append("");
      var6.append(this.getSeat(this.setData[5] >> 6 & 3));
      var4.setText(var6.toString());
      byte[] var8 = this.setData;
      if ((var8[2] >> 5 & 3) == 3) {
         this.mMod.setBackgroundResource(2131099736);
         this.mMod2.setBackgroundResource(2131099670);
         this.mMod3.setBackgroundResource(2131099671);
         this.mMod4.setBackgroundResource(2131099672);
      } else if ((var8[2] >> 4 & 3) == 3) {
         this.mMod.setBackgroundResource(2131099669);
         this.mMod2.setBackgroundResource(2131099670);
         this.mMod3.setBackgroundResource(2131099740);
         this.mMod4.setBackgroundResource(2131099672);
      } else if ((var8[2] >> 4 & 1) == 1) {
         this.mMod.setBackgroundResource(2131099669);
         this.mMod2.setBackgroundResource(2131099738);
         this.mMod3.setBackgroundResource(2131099671);
         this.mMod4.setBackgroundResource(2131099672);
      } else if ((var8[2] >> 5 & 1) == 1) {
         this.mMod.setBackgroundResource(2131099669);
         this.mMod2.setBackgroundResource(2131099670);
         this.mMod3.setBackgroundResource(2131099671);
         this.mMod4.setBackgroundResource(2131099742);
      } else {
         this.mMod.setBackgroundResource(2131099669);
         this.mMod2.setBackgroundResource(2131099670);
         this.mMod3.setBackgroundResource(2131099671);
         this.mMod4.setBackgroundResource(2131099672);
      }

   }

   protected void ProcessData(byte[] var1) {
      if (var1 != null && var1.length > 1 && var1.length <= 30) {
         byte var3 = var1[0];
         int var2 = 2;
         if (var3 != 5) {
            if (var3 == 7) {
               while(var2 < var1.length) {
                  this.setData[var2 - 2] = var1[var2];
                  ++var2;
               }

               TextView var4 = this.mRearview;
               String var5;
               if ((this.setData[6] & 128) != 0) {
                  var5 = "ON";
               } else {
                  var5 = "OFF";
               }

               var4.setText(var5);
            }
         } else {
            while(var2 < var1.length) {
               this.setData[var2 - 2] = var1[var2];
               ++var2;
            }

            this.updataSettings();
         }
      }

   }

   public void onClick(View var1) {
      var1.getId();
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131230727);
      this.findView();
      this.SendCanBusCmdData2E((byte)-15, new byte[]{5}, 1);
      this.SendCanBusCmdData2E((byte)-15, new byte[]{7}, 1);
   }

   protected void onDestroy() {
      System.putInt(this.getContentResolver(), "com.microntek.hiworld.ari", 0);
      super.onDestroy();
   }

   protected void onResume() {
      super.onResume();
      System.putInt(this.getContentResolver(), "com.microntek.hiworld.ari", 1);
   }

   public boolean onTouch(View var1, MotionEvent var2) {
      int var4 = var2.getAction();
      byte var3;
      if (var4 != 3 && var4 != 1) {
         if (var4 == 0 && this.mID == -1) {
            this.mID = var1.getId();
            switch (var1.getId()) {
               case 2131165184:
                  this.SetCmdkey((byte)17, (byte)1);
                  break;
               case 2131165196:
                  this.SetCmdkey((byte)48, (byte)1);
                  break;
               case 2131165197:
                  this.SetCmdkey((byte)50, (byte)1);
                  break;
               case 2131165218:
                  this.SetCmdkey((byte)21, (byte)1);
                  break;
               case 2131165223:
                  this.SetCmdkey((byte)29, (byte)1);
                  break;
               case 2131165224:
                  this.SetCmdkey((byte)28, (byte)1);
                  break;
               case 2131165225:
                  this.SetCmdkey((byte)30, (byte)1);
                  break;
               case 2131165226:
                  this.SetCmdkey((byte)31, (byte)1);
                  break;
               case 2131165228:
                  this.SetCmdkey((byte)19, (byte)1);
                  break;
               case 2131165229:
                  this.SetCmdkey((byte)18, (byte)1);
                  break;
               case 2131165232:
                  this.SetCmdkey((byte)27, (byte)1);
                  break;
               case 2131165233:
                  this.SetCmdkey((byte)24, (byte)1);
                  break;
               case 2131165234:
                  this.SetCmdkey((byte)25, (byte)1);
                  break;
               case 2131165235:
                  this.SetCmdkey((byte)26, (byte)1);
                  break;
               case 2131165247:
                  if (this.isOnOff) {
                     var3 = 16;
                  } else {
                     var3 = 9;
                  }

                  this.SetCmdkey(var3, (byte)1);
                  break;
               case 2131165248:
                  this.SetCmdkey((byte)20, (byte)1);
                  break;
               case 2131165249:
                  this.SetCmdkey((byte)22, (byte)1);
                  break;
               case 2131165252:
                  this.SetCmdkey((byte)32, (byte)1);
                  break;
               case 2131165253:
                  this.SetCmdkey((byte)33, (byte)1);
                  break;
               case 2131165263:
                  this.SetCmdkey((byte)23, (byte)1);
                  break;
               default:
                  return false;
            }
         }
      } else if (this.mID == var1.getId()) {
         this.mID = -1;
         switch (var1.getId()) {
            case 2131165184:
               this.SetCmdkey((byte)17, (byte)0);
               break;
            case 2131165196:
               this.SetCmdkey((byte)48, (byte)0);
               break;
            case 2131165197:
               this.SetCmdkey((byte)50, (byte)0);
               break;
            case 2131165218:
               this.SetCmdkey((byte)21, (byte)0);
               break;
            case 2131165223:
               this.SetCmdkey((byte)29, (byte)0);
               break;
            case 2131165224:
               this.SetCmdkey((byte)28, (byte)0);
               break;
            case 2131165225:
               this.SetCmdkey((byte)30, (byte)0);
               break;
            case 2131165226:
               this.SetCmdkey((byte)31, (byte)0);
               break;
            case 2131165228:
               this.SetCmdkey((byte)19, (byte)0);
               break;
            case 2131165229:
               this.SetCmdkey((byte)18, (byte)0);
               break;
            case 2131165232:
               this.SetCmdkey((byte)27, (byte)0);
               break;
            case 2131165233:
               this.SetCmdkey((byte)24, (byte)0);
               break;
            case 2131165234:
               this.SetCmdkey((byte)25, (byte)0);
               break;
            case 2131165235:
               this.SetCmdkey((byte)26, (byte)0);
               break;
            case 2131165247:
               if (this.isOnOff) {
                  var3 = 16;
               } else {
                  var3 = 9;
               }

               this.SetCmdkey(var3, (byte)0);
               break;
            case 2131165248:
               this.SetCmdkey((byte)20, (byte)0);
               break;
            case 2131165249:
               this.SetCmdkey((byte)22, (byte)0);
               break;
            case 2131165252:
               this.SetCmdkey((byte)32, (byte)0);
               break;
            case 2131165253:
               this.SetCmdkey((byte)33, (byte)0);
               break;
            case 2131165263:
               this.SetCmdkey((byte)23, (byte)0);
               break;
            default:
               return false;
         }
      }

      return false;
   }
}
