package com.hzbhd.canbus.car._430;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class SetTimeView {
   Button cancel;
   Button hours_down;
   Button hours_up;
   CheckBox is24;
   Button minute_down;
   Button minute_up;
   Button ok;

   public void showDialog(Context var1, ThisInterface var2) {
      View var3 = LayoutInflater.from(var1).inflate(2131558515, (ViewGroup)null, true);
      AlertDialog var4 = (new AlertDialog.Builder(var1)).setView(var3).create();
      if (this.hours_up == null) {
         this.hours_up = (Button)var3.findViewById(2131362375);
      }

      if (this.hours_down == null) {
         this.hours_down = (Button)var3.findViewById(2131362374);
      }

      if (this.minute_up == null) {
         this.minute_up = (Button)var3.findViewById(2131362858);
      }

      if (this.minute_down == null) {
         this.minute_down = (Button)var3.findViewById(2131362857);
      }

      if (this.ok == null) {
         this.ok = (Button)var3.findViewById(2131362918);
      }

      if (this.cancel == null) {
         this.cancel = (Button)var3.findViewById(2131362092);
      }

      if (this.is24 == null) {
         this.is24 = (CheckBox)var3.findViewById(2131362506);
      }

      this.ok.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetTimeView this$0;
         final AlertDialog val$dialog;

         {
            this.this$0 = var1;
            this.val$dialog = var2;
         }

         public void onClick(View var1) {
            this.val$dialog.dismiss();
         }
      });
      this.cancel.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetTimeView this$0;
         final AlertDialog val$dialog;

         {
            this.this$0 = var1;
            this.val$dialog = var2;
         }

         public void onClick(View var1) {
            this.val$dialog.dismiss();
         }
      });
      this.hours_up.setOnClickListener(new View.OnClickListener(this, var2) {
         final SetTimeView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            this.val$thisInterface.hourUp();
         }
      });
      this.hours_down.setOnClickListener(new View.OnClickListener(this, var2) {
         final SetTimeView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            this.val$thisInterface.hourDown();
         }
      });
      this.minute_up.setOnClickListener(new View.OnClickListener(this, var2) {
         final SetTimeView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            this.val$thisInterface.minUp();
         }
      });
      this.minute_down.setOnClickListener(new View.OnClickListener(this, var2) {
         final SetTimeView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            this.val$thisInterface.minDown();
         }
      });
      this.is24.setOnClickListener(new View.OnClickListener(this, var2) {
         final SetTimeView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            this.val$thisInterface.timeFormat(this.this$0.is24.isChecked());
         }
      });
      var4.setCancelable(false);
      var4.getWindow().setBackgroundDrawableResource(17170445);
      var4.show();
   }

   public interface ThisInterface {
      void hourDown();

      void hourUp();

      void minDown();

      void minUp();

      void timeFormat(boolean var1);
   }
}
