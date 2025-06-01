package com.hzbhd.canbus.car._287;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;

public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
   private Button mBtnCamera;
   private Context mContext;

   public MyPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      Button var2 = (Button)LayoutInflater.from(var1).inflate(2131558795, this).findViewById(2131362409);
      this.mBtnCamera = var2;
      var2.setOnClickListener(this);
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362409) {
         CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
      }

   }
}
