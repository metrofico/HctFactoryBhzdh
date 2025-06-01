package com.hzbhd.canbus.car_cus._446.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import java.util.List;

public class SelectItemUtil {
   private boolean addTag = false;
   private TextView item1;
   private TextView item2;
   private TextView item3;
   private TextView item4;
   private TextView item5;
   private WindowManager.LayoutParams layoutParams;
   private Context mContext;
   private WindowManager mWindowManager;
   private LinearLayout out_view;
   private View view;
   private View view2;
   private View view3;
   private View view4;
   private View view5;

   public SelectItemUtil(Context var1) {
      this.initWindow(var1);
   }

   private void initView() {
      View var1 = LayoutInflater.from(this.mContext).inflate(2131558597, (ViewGroup)null);
      this.view = var1;
      this.item1 = (TextView)var1.findViewById(2131362510);
      this.item2 = (TextView)this.view.findViewById(2131362512);
      this.item3 = (TextView)this.view.findViewById(2131362514);
      this.item4 = (TextView)this.view.findViewById(2131362516);
      this.item5 = (TextView)this.view.findViewById(2131362518);
      this.view2 = this.view.findViewById(2131363768);
      this.view3 = this.view.findViewById(2131363769);
      this.view4 = this.view.findViewById(2131363770);
      this.view5 = this.view.findViewById(2131363771);
      LinearLayout var2 = (LinearLayout)this.view.findViewById(2131362931);
      this.out_view = var2;
      var2.setOnClickListener(new View.OnClickListener(this) {
         final SelectItemUtil this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.hide();
         }
      });
   }

   private void initWindow(Context var1) {
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var2 = new WindowManager.LayoutParams();
      this.layoutParams = var2;
      var2.gravity = 80;
      this.layoutParams.format = 1;
      this.layoutParams.type = 2038;
      this.layoutParams.x = 0;
      this.layoutParams.y = 0;
      this.layoutParams.width = -1;
      this.layoutParams.height = -1;
      this.initView();
   }

   public void hide() {
      if (this.addTag) {
         this.mWindowManager.removeView(this.view);
         this.addTag = false;
      }

   }

   public void show(List var1, ActionDo var2) {
      if (var1.size() == 3) {
         this.item4.setVisibility(8);
         this.item5.setVisibility(8);
         this.view4.setVisibility(8);
         this.view5.setVisibility(8);
         this.item1.setText((CharSequence)var1.get(0));
         this.item2.setText((CharSequence)var1.get(1));
         this.item3.setText((CharSequence)var1.get(2));
      } else if (var1.size() == 2) {
         this.item3.setVisibility(8);
         this.item4.setVisibility(8);
         this.item5.setVisibility(8);
         this.view3.setVisibility(8);
         this.view4.setVisibility(8);
         this.view5.setVisibility(8);
         this.item1.setText((CharSequence)var1.get(0));
         this.item2.setText((CharSequence)var1.get(1));
      } else if (var1.size() == 5) {
         this.item1.setText((CharSequence)var1.get(0));
         this.item2.setText((CharSequence)var1.get(1));
         this.item3.setText((CharSequence)var1.get(2));
         this.item4.setText((CharSequence)var1.get(3));
         this.item5.setText((CharSequence)var1.get(4));
      }

      if (!this.addTag) {
         this.mWindowManager.addView(this.view, this.layoutParams);
         this.addTag = true;
      }

      this.item1.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectItemUtil this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            this.val$actionDo.toDo(0);
            this.this$0.hide();
         }
      });
      this.item2.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectItemUtil this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            this.val$actionDo.toDo(1);
            this.this$0.hide();
         }
      });
      this.item3.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectItemUtil this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            this.val$actionDo.toDo(2);
            this.this$0.hide();
         }
      });
      this.item4.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectItemUtil this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            this.val$actionDo.toDo(3);
            this.this$0.hide();
         }
      });
      this.item5.setOnClickListener(new View.OnClickListener(this, var2) {
         final SelectItemUtil this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            this.val$actionDo.toDo(4);
            this.this$0.hide();
         }
      });
   }
}
