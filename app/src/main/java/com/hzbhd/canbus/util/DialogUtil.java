package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;

public class DialogUtil {
   private static DialogUtil mDialogUtil;
   private final String TAG = "DialogUtil";
   private AlertDialog mAlertDialog;

   public static DialogUtil getInstance() {
      if (mDialogUtil == null) {
         mDialogUtil = new DialogUtil();
      }

      return mDialogUtil;
   }

   // $FF: synthetic method
   static void lambda$showListDialog$0(DialogListAdapter var0, int var1) {
      var0.setSelectedIndex(var1);
      var0.notifyDataSetChanged();
   }

   // $FF: synthetic method
   static void lambda$showSeekDialog$1(SeekBar var0, View var1) {
      var0.setProgress(var0.getProgress() + 1);
   }

   // $FF: synthetic method
   static void lambda$showSeekDialog$2(SeekBar var0, View var1) {
      var0.setProgress(var0.getProgress() - 1);
   }

   // $FF: synthetic method
   static void lambda$showSeekDialog$3(SeekbarDialogCallBak var0, SeekBar var1, int var2, DialogInterface var3, int var4) {
      if (var0 != null) {
         var0.callBack(var1.getProgress() + var2);
      }

   }

   public AlertDialog getAlertDialog() {
      return this.mAlertDialog;
   }

   public void showConfirmDialog(Context var1, String var2, int var3, int var4, OnConfirmDialogListener var5) {
      View var6 = LayoutInflater.from(var1).inflate(2131558755, (ViewGroup)null);
      ((TextView)var6.findViewById(2131363578)).setText(CommUtil.getStrByResId(var1, var2));
      AlertDialog var7 = (new AlertDialog.Builder(var1, 2131820748)).setView(var6).setPositiveButton(2131768068, new DialogInterface.OnClickListener(this, var5, var3, var4) {
         final DialogUtil this$0;
         final int val$leftPos;
         final OnConfirmDialogListener val$onSettingConfirmDialogListener;
         final int val$rightPos;

         {
            this.this$0 = var1;
            this.val$onSettingConfirmDialogListener = var2;
            this.val$leftPos = var3;
            this.val$rightPos = var4;
         }

         public void onClick(DialogInterface var1, int var2) {
            OnConfirmDialogListener var3 = this.val$onSettingConfirmDialogListener;
            if (var3 != null) {
               var3.onConfirmClick(this.val$leftPos, this.val$rightPos);
            }

         }
      }).setNegativeButton(2131767924, (DialogInterface.OnClickListener)null).create();
      this.mAlertDialog = var7;
      var7.show();
   }

   public void showListDialog(Context var1, String[] var2, int var3, ListDialogCallBak var4) {
      View var5 = LayoutInflater.from(var1).inflate(2131558758, (ViewGroup)null);
      DialogListAdapter var6 = new DialogListAdapter(var1, var2);
      ListView var8 = (ListView)var5.findViewById(2131362821);
      var8.setAdapter(var6);
      var8.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var4) {
         final DialogUtil this$0;
         final ListDialogCallBak val$listDialogCallBak;

         {
            this.this$0 = var1;
            this.val$listDialogCallBak = var2;
         }

         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            ListDialogCallBak var6 = this.val$listDialogCallBak;
            if (var6 != null) {
               var6.callBack(var3);
            }

            this.this$0.mAlertDialog.dismiss();
         }
      });
      var8.post(new DialogUtil$$ExternalSyntheticLambda0(var6, var3));
      AlertDialog var7 = (new AlertDialog.Builder(var1, 2131820748)).setView(var5).create();
      this.mAlertDialog = var7;
      var7.show();
   }

   public void showSeekDialog(Context var1, int var2, int var3, int var4, boolean var5, SeekbarDialogCallBak var6, SeekbarSetTextListener var7) {
      View var9 = LayoutInflater.from(var1).inflate(2131558759, (ViewGroup)null);
      TextView var10 = (TextView)var9.findViewById(2131363714);
      var10.setText(var7.onSetText(var4 + var2));
      SeekBar var8 = (SeekBar)var9.findViewById(2131363304);
      var8.setMax(var3 - var2);
      var8.setProgress(var4);
      var8.setEnabled(var5);
      var8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(this, var10, var7, var2, var5, var6) {
         final DialogUtil this$0;
         final boolean val$draggable;
         final int val$min;
         final SeekbarDialogCallBak val$seekbarDialogCallBak;
         final SeekbarSetTextListener val$seekbarSetTextListener;
         final TextView val$textView;

         {
            this.this$0 = var1;
            this.val$textView = var2;
            this.val$seekbarSetTextListener = var3;
            this.val$min = var4;
            this.val$draggable = var5;
            this.val$seekbarDialogCallBak = var6;
         }

         public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
            this.val$textView.setText(this.val$seekbarSetTextListener.onSetText(var2 + this.val$min));
            if (!this.val$draggable) {
               SeekbarDialogCallBak var4 = this.val$seekbarDialogCallBak;
               if (var4 != null) {
                  var4.callBack(var1.getProgress() + this.val$min);
               }
            }

         }

         public void onStartTrackingTouch(SeekBar var1) {
         }

         public void onStopTrackingTouch(SeekBar var1) {
         }
      });
      var9.findViewById(2131362400).setOnClickListener(new DialogUtil$$ExternalSyntheticLambda1(var8));
      var9.findViewById(2131362386).setOnClickListener(new DialogUtil$$ExternalSyntheticLambda2(var8));
      AlertDialog.Builder var11 = new AlertDialog.Builder(var1, 2131820748);
      var11.setView(var9);
      var11.setPositiveButton(2131768068, new DialogUtil$$ExternalSyntheticLambda3(var6, var8, var2));
      AlertDialog var12 = var11.create();
      this.mAlertDialog = var12;
      var12.show();
   }

   public interface ListDialogCallBak {
      void callBack(int var1);
   }

   public interface SeekbarDialogCallBak {
      void callBack(int var1);
   }

   public interface SeekbarSetTextListener {
      String onSetText(int var1);
   }
}
