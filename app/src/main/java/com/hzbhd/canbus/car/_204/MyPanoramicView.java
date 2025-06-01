package com.hzbhd.canbus.car._204;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;

public class MyPanoramicView extends RelativeLayout {
   private Button mBtnTest;
   private Context mContext;

   public MyPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      View var3 = LayoutInflater.from(var1).inflate(2131558794, this);
      OnClickListener var2 = new OnClickListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            switch (var1.getId()) {
               case 2131362264:
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
                  break;
               case 2131362718:
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
                  break;
               case 2131363042:
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
                  break;
               case 2131363146:
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
            }

         }
      };
      var3.findViewById(2131362264).setOnClickListener(var2);
      var3.findViewById(2131362718).setOnClickListener(var2);
      var3.findViewById(2131363146).setOnClickListener(var2);
      var3.findViewById(2131363042).setOnClickListener(var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }
}
