package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class OriginalBottomBtnItemView extends RelativeLayout {
   private int mCurrentClick = 0;
   private ImageButton mIbtn;
   private OnItemClickListener mOnItemClickListener;

   public OriginalBottomBtnItemView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public OriginalBottomBtnItemView(Context var1, String var2) {
      super(var1);
      this.initViews(var1, var2);
   }

   private void initViews(Context var1, String var2) {
      ImageButton var5 = (ImageButton)LayoutInflater.from(var1).inflate(2131558861, this).findViewById(2131362009);
      this.mIbtn = var5;
      var5.setOnClickListener(new View.OnClickListener(this) {
         final OriginalBottomBtnItemView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mOnItemClickListener != null) {
               this.this$0.mOnItemClickListener.onClick();
            }

         }
      });
      var2.hashCode();
      int var4 = var2.hashCode();
      byte var3 = -1;
      switch (var4) {
         case -1071542307:
            if (var2.equals("preset_scan")) {
               var3 = 0;
            }
            break;
         case -938285885:
            if (var2.equals("random")) {
               var3 = 1;
            }
            break;
         case -934531685:
            if (var2.equals("repeat")) {
               var3 = 2;
            }
            break;
         case -841905119:
            if (var2.equals("prev_disc")) {
               var3 = 3;
            }
            break;
         case -39978415:
            if (var2.equals("auto_store")) {
               var3 = 4;
            }
            break;
         case -2052031:
            if (var2.equals("radio_scan")) {
               var3 = 5;
            }
            break;
         case 3739:
            if (var2.equals("up")) {
               var3 = 6;
            }
            break;
         case 3016245:
            if (var2.equals("band")) {
               var3 = 7;
            }
            break;
         case 3089570:
            if (var2.equals("down")) {
               var3 = 8;
            }
            break;
         case 3317767:
            if (var2.equals("left")) {
               var3 = 9;
            }
            break;
         case 3357091:
            if (var2.equals("mode")) {
               var3 = 10;
            }
            break;
         case 3443508:
            if (var2.equals("play")) {
               var3 = 11;
            }
            break;
         case 3540994:
            if (var2.equals("stop")) {
               var3 = 12;
            }
            break;
         case 95131878:
            if (var2.equals("cycle")) {
               var3 = 13;
            }
            break;
         case 106440182:
            if (var2.equals("pause")) {
               var3 = 14;
            }
            break;
         case 108511772:
            if (var2.equals("right")) {
               var3 = 15;
            }
            break;
         case 1216748385:
            if (var2.equals("next_disc")) {
               var3 = 16;
            }
            break;
         case 1922620715:
            if (var2.equals("play_pause")) {
               var3 = 17;
            }
      }

      switch (var3) {
         case 0:
            this.mIbtn.setImageResource(2131234259);
            break;
         case 1:
            this.mIbtn.setImageResource(2131234255);
            break;
         case 2:
            this.mIbtn.setImageResource(2131234057);
            break;
         case 3:
            this.mIbtn.setImageResource(2131234253);
            break;
         case 4:
            this.mIbtn.setImageResource(2131234261);
            break;
         case 5:
            this.mIbtn.setImageResource(2131234262);
            break;
         case 6:
            this.mIbtn.setImageResource(2131234395);
            break;
         case 7:
            this.mIbtn.setImageResource(2131234257);
            break;
         case 8:
            this.mIbtn.setImageResource(2131234394);
            break;
         case 9:
            this.mIbtn.setImageResource(2131234396);
            break;
         case 10:
            this.mIbtn.setImageResource(2131234011);
            break;
         case 11:
            this.mIbtn.setImageResource(2131234401);
            break;
         case 12:
            this.mIbtn.setImageResource(2131234402);
            break;
         case 13:
            this.mIbtn.setImageResource(2131234256);
            break;
         case 14:
            this.mIbtn.setImageResource(2131234400);
            break;
         case 15:
            this.mIbtn.setImageResource(2131234398);
            break;
         case 16:
            this.mIbtn.setImageResource(2131234254);
            break;
         case 17:
            this.mIbtn.setImageResource(2131234401);
            this.mIbtn.setOnClickListener(new View.OnClickListener(this) {
               final OriginalBottomBtnItemView this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(View var1) {
                  if (this.this$0.mCurrentClick == 0) {
                     this.this$0.mIbtn.setImageResource(2131234400);
                     this.this$0.mCurrentClick = 1;
                  } else {
                     this.this$0.mIbtn.setImageResource(2131234401);
                     this.this$0.mCurrentClick = 0;
                  }

               }
            });
      }

   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public void turn(boolean var1) {
      if (var1) {
         this.mIbtn.setBackgroundResource(2131234415);
      } else {
         this.mIbtn.setBackgroundResource(2131234459);
      }

   }

   public interface OnItemClickListener {
      void onClick();
   }
}
