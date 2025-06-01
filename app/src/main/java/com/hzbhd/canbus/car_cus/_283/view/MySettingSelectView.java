package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.hzbhd.canbus.car_cus._283.MessageSender;

public class MySettingSelectView extends SettingSelectView {
   public MySettingSelectView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MySettingSelectView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MySettingSelectView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.setOnItemClickListener(new SettingSelectView.OnItemClickListener(this) {
         final MySettingSelectView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1, boolean var2) {
            this.this$0.onClick1(var1);
         }
      });
   }

   public void onClick1(View var1) {
      switch (var1.getId()) {
         case 2131361805:
            MessageSender.sendMsg(new byte[]{22, -117, 1, 0, 0});
            break;
         case 2131361809:
            MessageSender.sendMsg(new byte[]{22, -117, 4, 0, 0});
            break;
         case 2131361824:
            MessageSender.sendMsg(new byte[]{22, -117, 5, 0, 0});
            break;
         case 2131361827:
            MessageSender.sendMsg(new byte[]{22, -117, 2, 0, 0});
            break;
         case 2131361830:
            MessageSender.sendMsg(new byte[]{22, -117, 3, 0, 0});
            break;
         case 2131363801:
            MessageSender.sendMsg(new byte[]{22, -117, 6, 0, 0});
            break;
         case 2131363805:
            MessageSender.sendMsg(new byte[]{22, -117, 7, 0, 0});
            break;
         case 2131363806:
            MessageSender.sendMsg(new byte[]{22, -117, 8, 0, 0});
      }

   }
}
