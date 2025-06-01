package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.util.SelectItemUtil;
import java.util.List;

public class SelectionView extends LinearLayout {
   private LinearLayout all_action_lin;
   private TextView big_title;
   private Context context;
   private List itemList;
   private TextView now_mode;
   private TextView small_title;
   private View view;

   public SelectionView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SelectionView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SelectionView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.context = var1;
      View var4 = LayoutInflater.from(var1).inflate(2131558598, this, true);
      this.view = var4;
      this.now_mode = (TextView)var4.findViewById(2131362905);
      this.all_action_lin = (LinearLayout)this.view.findViewById(2131361919);
      this.big_title = (TextView)this.view.findViewById(2131361981);
      this.small_title = (TextView)this.view.findViewById(2131363336);
      TypedArray var5 = var1.obtainStyledAttributes(var2, R.styleable.MD_446_title_attr);
      this.big_title.setText(var5.getString(0));
      if (var5.getString(1).equals("GONE")) {
         this.small_title.setVisibility(8);
      } else {
         this.small_title.setText(var5.getString(1));
      }

   }

   public void getAction(ActionDo var1) {
      this.all_action_lin.setOnClickListener(new View.OnClickListener(this, var1) {
         final SelectionView this$0;
         final ActionDo val$actionDo;

         {
            this.this$0 = var1;
            this.val$actionDo = var2;
         }

         public void onClick(View var1) {
            if (this.this$0.itemList == null) {
               this.val$actionDo.toDo("ON_TITLE_CLICK");
            } else {
               (new SelectItemUtil(this.this$0.context)).show(this.this$0.itemList, this.val$actionDo);
            }

         }
      });
   }

   public void initItem(List var1) {
      this.itemList = var1;
   }

   public void setValue(String var1) {
      if (var1.equals("GONE")) {
         this.now_mode.setVisibility(8);
      } else {
         this.now_mode.setText(var1);
      }

   }
}
