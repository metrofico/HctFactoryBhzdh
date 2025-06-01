package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;

public class SwitchView extends LinearLayout {
   private LinearLayout all_action_lin;
   private TextView big_title;
   private TextView small_title;
   private ImageView switch_img;
   private View view;

   public SwitchView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SwitchView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SwitchView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558599, this, true);
      this.view = var4;
      this.big_title = (TextView)var4.findViewById(2131361981);
      this.small_title = (TextView)this.view.findViewById(2131363336);
      this.switch_img = (ImageView)this.view.findViewById(2131363479);
      this.all_action_lin = (LinearLayout)this.view.findViewById(2131361919);
      TypedArray var5 = var1.obtainStyledAttributes(var2, R.styleable.MD_446_title_attr);
      this.big_title.setText(var5.getString(0));
      if (var5.getString(1).equals("GONE")) {
         this.small_title.setVisibility(8);
      } else {
         this.small_title.setText(var5.getString(1));
      }

      this.select(false);
   }

   public void getAction(ActionDo var1) {
      this.all_action_lin.setOnClickListener(new View.OnClickListener(this, var1) {
         final SwitchView this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            this.val$actionDo.toDo((Object)null);
         }
      });
   }

   public void select(boolean var1) {
      if (var1) {
         this.switch_img.setBackgroundResource(2131231555);
      } else {
         this.switch_img.setBackgroundResource(2131231554);
      }

   }
}
