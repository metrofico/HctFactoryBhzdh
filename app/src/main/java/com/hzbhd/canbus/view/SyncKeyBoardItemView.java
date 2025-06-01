package com.hzbhd.canbus.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SyncKeyBoardItemView extends RelativeLayout {
   private ImageButton mIb;
   private TextView mTv;

   public SyncKeyBoardItemView(Context var1) {
      super(var1);
      LayoutInflater.from(var1).inflate(2131558731, this, true);
      this.findView();
   }

   private void findView() {
      this.mIb = (ImageButton)this.findViewById(2131362381);
      this.mTv = (TextView)this.findViewById(2131363578);
   }

   // $FF: synthetic method
   static void lambda$initView$0(OnClickListener var0, View var1) {
      if (var0 != null) {
         var0.onClick();
      }

   }

   // $FF: synthetic method
   static boolean lambda$initView$1(OnClickListener var0, View var1) {
      if (var0 != null) {
         var0.onLongClick();
      }

      return true;
   }

   public void initView(String var1, OnClickListener var2) {
      this.mIb.setOnClickListener(new SyncKeyBoardItemView$$ExternalSyntheticLambda0(var2));
      this.mIb.setOnLongClickListener(new SyncKeyBoardItemView$$ExternalSyntheticLambda1(var2));
      var1.hashCode();
      int var4 = var1.hashCode();
      byte var3 = -1;
      switch (var4) {
         case -1886439238:
            if (var1.equals("number_0")) {
               var3 = 0;
            }
            break;
         case -1886439237:
            if (var1.equals("number_1")) {
               var3 = 1;
            }
            break;
         case -1886439236:
            if (var1.equals("number_2")) {
               var3 = 2;
            }
            break;
         case -1886439235:
            if (var1.equals("number_3")) {
               var3 = 3;
            }
            break;
         case -1886439234:
            if (var1.equals("number_4")) {
               var3 = 4;
            }
            break;
         case -1886439233:
            if (var1.equals("number_5")) {
               var3 = 5;
            }
            break;
         case -1886439232:
            if (var1.equals("number_6")) {
               var3 = 6;
            }
            break;
         case -1886439231:
            if (var1.equals("number_7")) {
               var3 = 7;
            }
            break;
         case -1886439230:
            if (var1.equals("number_8")) {
               var3 = 8;
            }
            break;
         case -1886439229:
            if (var1.equals("number_9")) {
               var3 = 9;
            }
            break;
         case -1335157162:
            if (var1.equals("device")) {
               var3 = 10;
            }
            break;
         case -1224574323:
            if (var1.equals("hangup")) {
               var3 = 11;
            }
            break;
         case -988476804:
            if (var1.equals("pickup")) {
               var3 = 12;
            }
            break;
         case 3548:
            if (var1.equals("ok")) {
               var3 = 13;
            }
            break;
         case 3739:
            if (var1.equals("up")) {
               var3 = 14;
            }
            break;
         case 96964:
            if (var1.equals("aux")) {
               var3 = 15;
            }
            break;
         case 3089570:
            if (var1.equals("down")) {
               var3 = 16;
            }
            break;
         case 3237038:
            if (var1.equals("info")) {
               var3 = 17;
            }
            break;
         case 3317767:
            if (var1.equals("left")) {
               var3 = 18;
            }
            break;
         case 3347807:
            if (var1.equals("menu")) {
               var3 = 19;
            }
            break;
         case 3377907:
            if (var1.equals("next")) {
               var3 = 20;
            }
            break;
         case 3392903:
            if (var1.equals("null")) {
               var3 = 21;
            }
            break;
         case 3449395:
            if (var1.equals("prev")) {
               var3 = 22;
            }
            break;
         case 3540562:
            if (var1.equals("star")) {
               var3 = 23;
            }
            break;
         case 3645646:
            if (var1.equals("well")) {
               var3 = 24;
            }
            break;
         case 108511772:
            if (var1.equals("right")) {
               var3 = 25;
            }
            break;
         case 109418880:
            if (var1.equals("shuff")) {
               var3 = 26;
            }
            break;
         case 1725083810:
            if (var1.equals("btphone_on")) {
               var3 = 27;
            }
            break;
         case 1937990412:
            if (var1.equals("btphone_off")) {
               var3 = 28;
            }
      }

      switch (var3) {
         case 0:
            this.mTv.setText("0");
            break;
         case 1:
            this.mTv.setText("1");
            break;
         case 2:
            this.mTv.setText("2");
            break;
         case 3:
            this.mTv.setText("3");
            break;
         case 4:
            this.mTv.setText("4");
            break;
         case 5:
            this.mTv.setText("5");
            break;
         case 6:
            this.mTv.setText("6");
            break;
         case 7:
            this.mTv.setText("7");
            break;
         case 8:
            this.mTv.setText("8");
            break;
         case 9:
            this.mTv.setText("9");
            break;
         case 10:
            this.mTv.setText("DEV");
            break;
         case 11:
            this.mIb.setImageResource(2131233632);
            break;
         case 12:
            this.mIb.setImageResource(2131233584);
            break;
         case 13:
            this.mTv.setText("OK");
            break;
         case 14:
            this.mIb.setImageResource(2131233642);
            break;
         case 15:
            this.mTv.setText("AUX");
            break;
         case 16:
            this.mIb.setImageResource(2131233631);
            break;
         case 17:
            this.mTv.setText("INFO");
            break;
         case 18:
            this.mIb.setImageResource(2131233635);
            break;
         case 19:
            this.mTv.setText("MENU");
            break;
         case 20:
            this.mIb.setImageResource(2131233637);
            break;
         case 21:
            this.mIb.setEnabled(false);
            break;
         case 22:
            this.mIb.setImageResource(2131233639);
            break;
         case 23:
            this.mTv.setText("*");
            break;
         case 24:
            this.mTv.setText("#");
            break;
         case 25:
            this.mIb.setImageResource(2131233640);
            break;
         case 26:
            this.mIb.setImageResource(2131233464);
            break;
         case 27:
            this.mIb.setImageResource(2131233549);
            break;
         case 28:
            this.mIb.setImageResource(2131233550);
      }

   }

   interface OnClickListener {
      void onClick();

      void onLongClick();
   }
}
