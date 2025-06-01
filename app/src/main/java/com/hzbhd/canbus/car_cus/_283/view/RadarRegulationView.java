package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;

public class RadarRegulationView extends RelativeLayout {
   private SeekBar colorSeekBar;
   private SeekBar contrastSeekBar;
   private boolean isFirstOpen;
   private SeekBar lightSeekBar;
   private Context mContext;
   private Handler mHandler;
   private View mView;
   private TextView tv_color;
   private TextView tv_contrast;
   private TextView tv_light;

   public RadarRegulationView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public RadarRegulationView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public RadarRegulationView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.isFirstOpen = true;
      this.mHandler = new Handler(this, Looper.getMainLooper()) {
         final RadarRegulationView this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            if (var1.what == 0) {
               this.this$0.setVisibility(8);
            }

         }
      };
      this.mView = LayoutInflater.from(var1).inflate(2131558460, this, true);
      this.mContext = var1;
      this.initView();
   }

   private void hideThis() {
      this.mHandler.removeMessages(0);
      this.mHandler.sendEmptyMessageDelayed(0, 3000L);
   }

   private void initView() {
      this.tv_color = (TextView)this.mView.findViewById(2131363607);
      this.tv_contrast = (TextView)this.mView.findViewById(2131363609);
      this.tv_light = (TextView)this.mView.findViewById(2131363647);
      this.lightSeekBar = (SeekBar)this.mView.findViewById(2131362752);
      this.contrastSeekBar = (SeekBar)this.mView.findViewById(2131362158);
      this.colorSeekBar = (SeekBar)this.mView.findViewById(2131362148);
      this.lightSeekBar.setOnSeekBarChangeListener(new MySeekBarChangeListener(this) {
         final RadarRegulationView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            this.this$0.tv_light.setText(String.valueOf(var2));
            MessageSender.sendMsg(new byte[]{22, -14, 10, (byte)var2});
            this.this$0.hideThis();
         }
      });
      this.contrastSeekBar.setOnSeekBarChangeListener(new MySeekBarChangeListener(this) {
         final RadarRegulationView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            this.this$0.tv_contrast.setText(String.valueOf(var2));
            MessageSender.sendMsg(new byte[]{22, -14, 11, (byte)var2});
            this.this$0.hideThis();
         }
      });
      this.colorSeekBar.setOnSeekBarChangeListener(new MySeekBarChangeListener(this) {
         final RadarRegulationView this$0;

         {
            this.this$0 = var1;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            this.this$0.tv_color.setText(String.valueOf(var2));
            MessageSender.sendMsg(new byte[]{22, -14, 12, (byte)var2});
            this.this$0.hideThis();
         }
      });
      this.hideThis();
   }

   public void setVisibility(int var1) {
      super.setVisibility(var1);
      if (var1 == 0 && this.isFirstOpen) {
         this.tv_color.setText(String.valueOf(GeneralDzData.camera_color));
         this.tv_contrast.setText(String.valueOf(GeneralDzData.camera_contrast));
         this.tv_light.setText(String.valueOf(GeneralDzData.camera_light));
         this.lightSeekBar.setProgress(GeneralDzData.camera_light);
         this.contrastSeekBar.setProgress(GeneralDzData.camera_contrast);
         this.colorSeekBar.setProgress(GeneralDzData.camera_color);
         this.isFirstOpen = false;
      }

   }

   class MySeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
      final RadarRegulationView this$0;

      MySeekBarChangeListener(RadarRegulationView var1) {
         this.this$0 = var1;
      }

      public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
      }

      public void onStartTrackingTouch(SeekBar var1) {
      }

      public void onStopTrackingTouch(SeekBar var1) {
      }
   }
}
