package com.hzbhd.canbus.car_cus._446.activity;

import android.app.Activity;
import android.os.Bundle;
import com.hzbhd.canbus.car_cus._446.CanObserver;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.car_cus._446.view.SelectionView;
import com.hzbhd.canbus.car_cus._446.view.TireView;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;

public class WmTireInfoActivity extends Activity {
   private TireView tire_info;
   private SelectionView tire_unit;

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558703);
      this.tire_info = (TireView)this.findViewById(2131363542);
      this.tire_unit = (SelectionView)this.findViewById(2131363544);
      ArrayList var2 = new ArrayList();
      var2.add("PSI");
      var2.add("KPA");
      this.tire_unit.initItem(var2);
      this.tire_unit.getAction(new ActionDo(this) {
         final WmTireInfoActivity this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            Integer var2 = (Integer)var1;
            if (var2 == 0) {
               SharePreUtil.setStringValue(this.this$0, "key.tire.unit", "PSI");
               WmCarData.tire_unit = "PSI";
               this.this$0.tire_unit.setValue("PSI");
               CanObserver.getInstance().dataChange();
            }

            if (var2 == 1) {
               SharePreUtil.setStringValue(this.this$0, "key.tire.unit", "KPA");
               WmCarData.tire_unit = "KPA";
               this.this$0.tire_unit.setValue("KPA");
               CanObserver.getInstance().dataChange();
            }

         }
      });
      this.tire_unit.setValue(WmCarData.tire_unit);
   }

   protected void onDestroy() {
      super.onDestroy();
      CanObserver.getInstance().unRegister(this.tire_info);
   }

   protected void onResume() {
      super.onResume();
      CanObserver.getInstance().register(this.tire_info);
   }
}
