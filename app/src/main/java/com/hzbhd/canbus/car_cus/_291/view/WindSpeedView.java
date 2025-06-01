package com.hzbhd.canbus.car_cus._291.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WindSpeedView extends RelativeLayout {
   private ExecutorService executorService;
   private ImageView iv_wind_1;
   private ImageView iv_wind_2;
   private ImageView iv_wind_3;
   private ImageView iv_wind_4;
   private ImageView iv_wind_5;
   private ImageView iv_wind_6;
   private ImageView iv_wind_7;
   private List listImage;
   private View mView;

   public WindSpeedView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public WindSpeedView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public WindSpeedView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.listImage = new ArrayList();
      this.executorService = Executors.newSingleThreadExecutor();
      this.mView = LayoutInflater.from(var1).inflate(2131558494, this, true);
      this.initView();
   }

   private void initView() {
      this.iv_wind_1 = (ImageView)this.mView.findViewById(2131362672);
      this.iv_wind_2 = (ImageView)this.mView.findViewById(2131362673);
      this.iv_wind_3 = (ImageView)this.mView.findViewById(2131362674);
      this.iv_wind_4 = (ImageView)this.mView.findViewById(2131362675);
      this.iv_wind_5 = (ImageView)this.mView.findViewById(2131362676);
      this.iv_wind_6 = (ImageView)this.mView.findViewById(2131362677);
      this.iv_wind_7 = (ImageView)this.mView.findViewById(2131362678);
      this.executorService.execute(new Runnable(this) {
         final WindSpeedView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.listImage.add(this.this$0.iv_wind_1);
            this.this$0.listImage.add(this.this$0.iv_wind_2);
            this.this$0.listImage.add(this.this$0.iv_wind_3);
            this.this$0.listImage.add(this.this$0.iv_wind_4);
            this.this$0.listImage.add(this.this$0.iv_wind_5);
            this.this$0.listImage.add(this.this$0.iv_wind_6);
            this.this$0.listImage.add(this.this$0.iv_wind_7);
         }
      });
   }

   public void setWindPower(int var1) {
      if (var1 >= 0) {
         if (var1 <= 7) {
            for(int var2 = 0; var2 < this.listImage.size(); ++var2) {
               if (var2 < var1) {
                  ((ImageView)this.listImage.get(var2)).setVisibility(0);
               } else {
                  ((ImageView)this.listImage.get(var2)).setVisibility(8);
               }
            }

         }
      }
   }
}
