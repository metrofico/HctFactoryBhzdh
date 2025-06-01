package com.hzbhd.canbus.car_cus._467.drive.viiew;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._467.drive.data.DriveData;
import com.hzbhd.canbus.comm.ScreenConfig;
import com.hzbhd.canbus.interfaces.ActionDo;
import com.hzbhd.ui.util.BaseUtil;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class DriveDateView extends RelativeLayout implements ActionDo {
   private TextView a2;
   private TextView a5;
   private TextView b2;
   private TextView b5;
   private TextView c2;
   private TextView c5;
   private TextView d2;
   private TextView d5;
   private TextView tempEvaporation;
   private TextView tempIn;
   private TextView tempOut;
   private View view;

   public DriveDateView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public DriveDateView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public DriveDateView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
         this.view = LayoutInflater.from(var1).inflate(2131558579, this, true);
      } else {
         this.view = LayoutInflater.from(var1).inflate(2131558580, this, true);
      }

      this.a2 = (TextView)this.view.findViewById(2131361837);
      this.a5 = (TextView)this.view.findViewById(2131361840);
      this.b2 = (TextView)this.view.findViewById(2131361932);
      this.b5 = (TextView)this.view.findViewById(2131361935);
      this.c2 = (TextView)this.view.findViewById(2131362077);
      this.c5 = (TextView)this.view.findViewById(2131362080);
      this.d2 = (TextView)this.view.findViewById(2131362165);
      this.d5 = (TextView)this.view.findViewById(2131362170);
      this.tempOut = (TextView)this.view.findViewById(2131363508);
      this.tempIn = (TextView)this.view.findViewById(2131363507);
      this.tempEvaporation = (TextView)this.view.findViewById(2131363506);
      this.updateUI();
   }

   private void updateUI() {
      BaseUtil.INSTANCE.runUi(new Function0(this) {
         final DriveDateView this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            this.this$0.a2.setText(DriveData.dataA2);
            this.this$0.a5.setText(DriveData.dataA5);
            this.this$0.b2.setText(DriveData.dataB2);
            this.this$0.b5.setText(DriveData.dataB5);
            this.this$0.c2.setText(DriveData.dataC2);
            this.this$0.c5.setText(DriveData.dataC5);
            this.this$0.d2.setText(DriveData.dataD2);
            this.this$0.d5.setText(DriveData.dataD5);
            this.this$0.tempOut.setText(DriveData.tempOut);
            this.this$0.tempIn.setText(DriveData.tempIn);
            this.this$0.tempEvaporation.setText(DriveData.tempEvaporation);
            return null;
         }
      });
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
   }

   public void todo(List var1) {
      this.updateUI();
   }
}
