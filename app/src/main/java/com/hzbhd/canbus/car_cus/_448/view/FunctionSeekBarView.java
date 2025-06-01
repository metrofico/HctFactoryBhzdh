package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

public class FunctionSeekBarView extends LinearLayout {
   private SeekBar my_seek;
   private TextView title_txt;
   private TextView value_txt;
   private View view;

   public FunctionSeekBarView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FunctionSeekBarView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FunctionSeekBarView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558607, this, true);
      this.view = var4;
      this.title_txt = (TextView)var4.findViewById(2131363550);
      this.value_txt = (TextView)this.view.findViewById(2131363750);
      this.my_seek = (SeekBar)this.view.findViewById(2131362885);
   }

   public void getAction(ActionCallback var1) {
      this.my_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this, var1) {
         final FunctionSeekBarView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            this.val$actionCallback.toDo(var2);
            this.this$0.value_txt.setText(String.valueOf(var2));
         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      });
   }

   public void setMax(int var1) {
      this.my_seek.setMax(var1);
   }

   public void setTitleStr(String var1) {
      this.title_txt.setText(var1);
   }

   public void setValue(int var1) {
      this.value_txt.setText(String.valueOf(var1));
      this.my_seek.setProgress(var1);
   }
}
