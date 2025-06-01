package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class SetErrorProgressView {
   private static AlertDialog dialog;
   private static View view;
   Button cancel_btn;
   Button continue_btn;
   ProgressBar mProgressBar;
   Button ok_success;
   Button pause;
   LinearLayout running_view;
   LinearLayout success_view;

   public void showDialog(Context var1, boolean var2, boolean var3, ThisInterface var4) {
      if (view == null) {
         view = LayoutInflater.from(var1).inflate(2131558621, (ViewGroup)null, true);
      }

      if (dialog == null) {
         dialog = (new AlertDialog.Builder(var1)).setView(view).create();
      }

      if (this.pause == null) {
         this.pause = (Button)view.findViewById(2131362969);
      }

      if (this.continue_btn == null) {
         this.continue_btn = (Button)view.findViewById(2131362157);
      }

      if (this.cancel_btn == null) {
         this.cancel_btn = (Button)view.findViewById(2131362094);
      }

      if (this.mProgressBar == null) {
         this.mProgressBar = (ProgressBar)view.findViewById(2131362826);
      }

      if (this.success_view == null) {
         this.success_view = (LinearLayout)view.findViewById(2131363442);
      }

      if (this.running_view == null) {
         this.running_view = (LinearLayout)view.findViewById(2131363207);
      }

      if (this.ok_success == null) {
         this.ok_success = (Button)view.findViewById(2131362922);
      }

      this.pause.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetErrorProgressView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            if (this.val$thisInterface.onPause()) {
               this.this$0.pause.setVisibility(8);
               this.this$0.continue_btn.setVisibility(0);
               this.this$0.mProgressBar.setVisibility(8);
            }

         }
      });
      this.continue_btn.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetErrorProgressView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            if (this.val$thisInterface.onContinue()) {
               this.this$0.pause.setVisibility(0);
               this.this$0.continue_btn.setVisibility(8);
               this.this$0.mProgressBar.setVisibility(0);
            }

         }
      });
      this.cancel_btn.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetErrorProgressView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            this.this$0.pause.setVisibility(0);
            this.this$0.continue_btn.setVisibility(8);
            this.this$0.mProgressBar.setVisibility(0);
            this.val$thisInterface.onCancel();
         }
      });
      this.ok_success.setOnClickListener(new View.OnClickListener(this, var4) {
         final SetErrorProgressView this$0;
         final ThisInterface val$thisInterface;

         {
            this.this$0 = var1;
            this.val$thisInterface = var2;
         }

         public void onClick(View var1) {
            this.this$0.success_view.setVisibility(8);
            this.val$thisInterface.onSuccessButtonClick();
         }
      });
      dialog.setCancelable(false);
      dialog.getWindow().setBackgroundDrawableResource(17170445);
      dialog.getWindow().setType(2003);
      if (var2) {
         dialog.show();
      } else {
         dialog.dismiss();
      }

      if (var3) {
         this.success_view.setVisibility(0);
         this.running_view.setVisibility(8);
      } else {
         this.success_view.setVisibility(8);
         this.running_view.setVisibility(0);
      }

   }

   public interface ThisInterface {
      boolean onCancel();

      boolean onContinue();

      boolean onPause();

      boolean onSuccessButtonClick();
   }
}
