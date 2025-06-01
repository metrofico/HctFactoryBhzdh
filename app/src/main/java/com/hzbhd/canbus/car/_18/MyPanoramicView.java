package com.hzbhd.canbus.car._18;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.util.LogUtil;

public class MyPanoramicView extends RelativeLayout {
   private Button mBtnTest;
   private Context mContext;

   public MyPanoramicView(Context var1) {
      super(var1);
      this.mContext = var1;
      Button var2 = (Button)LayoutInflater.from(var1).inflate(2131558798, this).findViewById(2131362039);
      this.mBtnTest = var2;
      var2.setOnClickListener(new OnClickListener(this) {
         final MyPanoramicView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            LogUtil.showLog("click");
         }
      });
   }

   public MyPanoramicView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public MyPanoramicView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }
}
