package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.canbus.util.SharePreUtil;

public class FrontCameraSettingActivity extends AppCompatActivity {
   private void initView() {
      TextView var1 = (TextView)this.findViewById(2131362508);
      if (SharePreUtil.getBoolValue(this, "share_is_open_F.Camera", false)) {
         var1.setText(2131755270);
      } else {
         var1.setText(2131755271);
      }

      var1.setOnClickListener(new View.OnClickListener(this, var1) {
         final FrontCameraSettingActivity this$0;
         final TextView val$is_open_F_Camera_textview;

         {
            this.this$0 = var1;
            this.val$is_open_F_Camera_textview = var2;
         }

         public void onClick(View var1) {
            if (SharePreUtil.getBoolValue(this.this$0, "share_is_open_F.Camera", false)) {
               SharePreUtil.setBoolValue(this.this$0, "share_is_open_F.Camera", false);
               this.val$is_open_F_Camera_textview.setText(2131755271);
            } else {
               SharePreUtil.setBoolValue(this.this$0, "share_is_open_F.Camera", true);
               this.val$is_open_F_Camera_textview.setText(2131755270);
            }

         }
      });
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558661);
      this.initView();
   }
}
