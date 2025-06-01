package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.activity.WmTireInfoActivity;

public class Page4View extends LinearLayout {
   private Context context;
   private SelectionView selection1;
   private SelectionView selection2;
   private View view;

   public Page4View(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public Page4View(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public Page4View(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.context = var1;
      View var4 = LayoutInflater.from(var1).inflate(2131558596, this, true);
      this.view = var4;
      this.selection1 = (SelectionView)var4.findViewById(2131363542);
      this.selection2 = (SelectionView)this.view.findViewById(2131363102);
      this.selection1.setValue("GONE");
      this.selection2.setValue("GONE");
      this.initAction();
   }

   private void initAction() {
      this.selection1.getAction(new ActionDo(this) {
         final Page4View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            this.this$0.context.startActivity(new Intent(this.this$0.context, WmTireInfoActivity.class));
         }
      });
      this.selection2.getAction(new ActionDo(this) {
         final Page4View this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
         }
      });
   }
}
