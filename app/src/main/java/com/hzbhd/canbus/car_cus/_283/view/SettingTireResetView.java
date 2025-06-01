package com.hzbhd.canbus.car_cus._283.view;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import java.util.List;

public class SettingTireResetView extends RelativeLayout implements View.OnClickListener {
   AlertDialog dialog;
   protected int id;
   protected List lists;
   public Context mContext;
   private View mView;
   Button ok_reset;
   private RelativeLayout relativeLayout;
   Thread tireResetThread;
   View view;

   public SettingTireResetView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SettingTireResetView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SettingTireResetView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      View var4 = LayoutInflater.from(var1).inflate(2131558477, this, true);
      this.mView = var4;
      RelativeLayout var5 = (RelativeLayout)var4.findViewById(2131363090);
      this.relativeLayout = var5;
      var5.setOnClickListener(this);
   }

   public void onClick(View var1) {
      if (var1.getId() == 2131363090) {
         this.showDialog(this.mContext);
      }

   }

   public void showDialog(Context var1) {
      if (this.view == null) {
         this.view = LayoutInflater.from(var1).inflate(2131558481, (ViewGroup)null, true);
      }

      if (this.dialog == null) {
         this.dialog = (new AlertDialog.Builder(var1)).setView(this.view).create();
      }

      if (this.ok_reset == null) {
         this.ok_reset = (Button)this.view.findViewById(2131362920);
      }

      this.ok_reset.setOnClickListener(new View.OnClickListener(this) {
         final SettingTireResetView this$0;

         {
            this.this$0 = var1;
         }

         private void resetTire() {
            this.this$0.tireResetThread = this.this$0.new TireResetThread(this.this$0);
            this.this$0.tireResetThread.start();
         }

         public void onClick(View var1) {
            this.this$0.dialog.dismiss();
            this.resetTire();
         }
      });
      this.dialog.setCancelable(true);
      this.dialog.getWindow().setBackgroundDrawableResource(17170445);
      this.dialog.show();
   }

   public class TireResetThread extends Thread {
      final SettingTireResetView this$0;

      public TireResetThread(SettingTireResetView var1) {
         this.this$0 = var1;
      }

      public void run() {
         for(int var1 = 0; var1 < 10; ++var1) {
            try {
               Thread.sleep(500L);
            } catch (InterruptedException var3) {
               var3.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 75, 1, 1});
         }

         this.this$0.tireResetThread.interrupt();
      }
   }
}
