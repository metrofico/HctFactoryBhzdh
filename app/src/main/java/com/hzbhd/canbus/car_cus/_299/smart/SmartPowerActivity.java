package com.hzbhd.canbus.car_cus._299.smart;

import android.view.ViewGroup;
import android.widget.RadioButton;

public class SmartPowerActivity extends com.hzbhd.canbus.activity.SmartPowerActivity {
   protected void scaleRadioButton(RadioButton var1) {
      for(int var2 = 0; var2 < this.radioButtons.size(); ++var2) {
         ViewGroup.MarginLayoutParams var3;
         if (var1 == this.radioButtons.get(var2)) {
            var1.setChecked(true);
            var3 = (ViewGroup.MarginLayoutParams)var1.getLayoutParams();
            var3.width = (int)this.getResources().getDimension(2131169625);
            var3.height = (int)this.getResources().getDimension(2131169685);
            var1.setLayoutParams(var3);
            var1.setPadding(0, 0, 0, (int)this.getResources().getDimension(2131170032));
            var1.setTextSize(this.getResources().getDimension(2131170054));
         } else {
            var3 = (ViewGroup.MarginLayoutParams)((RadioButton)this.radioButtons.get(var2)).getLayoutParams();
            var3.width = (int)this.getResources().getDimension(2131165195);
            var3.height = (int)this.getResources().getDimension(2131165193);
            ((RadioButton)this.radioButtons.get(var2)).setLayoutParams(var3);
            ((RadioButton)this.radioButtons.get(var2)).setPadding(0, 0, 0, (int)this.getResources().getDimension(2131165206));
            ((RadioButton)this.radioButtons.get(var2)).setTextSize(this.getResources().getDimension(2131169588));
         }
      }

   }

   protected void setActivityContentView() {
      this.setContentView(2131558496);
   }
}
