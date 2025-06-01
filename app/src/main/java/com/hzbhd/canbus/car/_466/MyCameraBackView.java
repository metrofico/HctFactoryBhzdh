package com.hzbhd.canbus.car._466;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.proxy.share.ShareDataManager;

public class MyCameraBackView extends RelativeLayout {
   private View cameraBack;
   private Context mContext;

   public MyCameraBackView(Context var1) {
      super(var1);
      this.mContext = var1;
      View var2 = LayoutInflater.from(var1).inflate(2131558706, this, true).findViewById(2131361960);
      this.cameraBack = var2;
      var2.setOnClickListener(new View.OnClickListener(this) {
         final MyCameraBackView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            ShareDataManager.getInstance().reportInt("user.Reverse", 0);
         }
      });
   }

   public MyCameraBackView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyCameraBackView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }
}
