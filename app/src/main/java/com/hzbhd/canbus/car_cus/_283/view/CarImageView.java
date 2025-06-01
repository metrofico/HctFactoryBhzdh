package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.hzbhd.canbus.car_cus._283.HandlerUtils;

public class CarImageView extends ImageView {
   public static final String CARTYPE = "CarType";
   public static final String CARTYPE_Main = "car_type";
   HandlerUtils.IFreshUICallback mIFreshUICallback;

   public CarImageView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarImageView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarImageView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mIFreshUICallback = new HandlerUtils.IFreshUICallback(this) {
         final CarImageView this$0;

         {
            this.this$0 = var1;
         }

         public void callback() {
            this.this$0.refreshUi();
         }
      };
   }

   private void refreshUi() {
      int var1 = this.getContext().getSharedPreferences("car_type", 0).getInt("CarType", 0);
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               this.setImageResource(2131233326);
            } else {
               this.setImageResource(2131233327);
            }
         } else {
            this.setImageResource(2131233328);
         }
      } else {
         this.setImageResource(2131233325);
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      HandlerUtils.getInstance().unregisterCallBack(this.mIFreshUICallback);
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.refreshUi();
      HandlerUtils.getInstance().registerCallBack(this.mIFreshUICallback);
   }
}
