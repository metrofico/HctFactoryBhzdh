package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

public class KeyButton extends LinearLayout {
   private ActionCallback actionCallback;
   private Button btn;
   private View view;

   public KeyButton(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public KeyButton(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public KeyButton(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558609, this, true);
      this.view = var4;
      Button var5 = (Button)var4.findViewById(2131361993);
      this.btn = var5;
      var5.setOnTouchListener(new View.OnTouchListener(this) {
         final KeyButton this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.this$0.btn.setBackgroundResource(2131231573);
               return true;
            } else if (var2.getAction() == 4) {
               this.this$0.btn.setBackgroundResource(2131231572);
               return true;
            } else {
               if (var2.getAction() == 1) {
                  this.this$0.btn.setBackgroundResource(2131231572);
                  if (this.this$0.actionCallback != null) {
                     this.this$0.actionCallback.toDo((Object)null);
                  }
               }

               if (var2.getAction() == 3) {
                  this.this$0.btn.setBackgroundResource(2131231572);
               }

               return true;
            }
         }
      });
   }

   public void getEventUpAction(ActionCallback var1) {
      this.actionCallback = var1;
   }

   public void setBackgroundResource(int var1) {
      this.btn.setBackgroundResource(var1);
   }

   public void setText(String var1) {
      this.btn.setText(var1);
   }

   public void setTextValue(String var1) {
      this.btn.setText(var1);
   }
}
