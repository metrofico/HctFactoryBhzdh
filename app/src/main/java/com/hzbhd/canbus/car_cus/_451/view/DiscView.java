package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiscView extends LinearLayout {
   private Handler handler;
   private ImageView img;
   private boolean imgTag;
   private boolean isTwinkle;
   private TextView txt;
   private View view;

   public DiscView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public DiscView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public DiscView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.isTwinkle = false;
      this.imgTag = true;
      this.handler = new Handler(this, Looper.getMainLooper()) {
         final DiscView this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 65535) {
               if (this.this$0.isTwinkle) {
                  if (this.this$0.imgTag) {
                     this.this$0.img.setVisibility(8);
                     this.this$0.imgTag = false;
                  } else {
                     this.this$0.img.setVisibility(0);
                     this.this$0.imgTag = true;
                  }

                  var1 = new Message();
                  var1.what = 65535;
                  this.this$0.handler.sendMessageDelayed(var1, 500L);
               } else {
                  this.this$0.img.setVisibility(0);
               }
            }

         }
      };
      View var4 = LayoutInflater.from(var1).inflate(2131558555, this, true);
      this.view = var4;
      this.txt = (TextView)var4.findViewById(2131363721);
      this.img = (ImageView)this.view.findViewById(2131362487);
   }

   public void setText(String var1) {
      this.txt.setText(var1);
   }

   public void setTwinkle(boolean var1) {
      this.isTwinkle = var1;
      Message var2 = new Message();
      var2.what = 65535;
      this.handler.sendMessageDelayed(var2, 500L);
   }
}
