package com.hzbhd.canbus.car_cus._283;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.MainChooseAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.MainMediaAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.SettingDialogAdapter;
import com.hzbhd.canbus.car_cus._283.adapter.TmpsAdapter;
import com.hzbhd.canbus.car_cus._283.utils.Utils;
import com.hzbhd.canbus.car_cus._283.view.CenterControlView;
import java.util.List;

public class DialogUtils {
   private static PopupWindow centerControlPopupWindow;

   public static void chooseTmps(Context var0, View var1, List var2, TmpsAdapter.OnItemClickListener var3) {
      int[] var7 = new int[2];
      var1.getLocationOnScreen(var7);
      PopupWindow var4 = new PopupWindow(var1, -2, -2);
      View var6 = LayoutInflater.from(var0).inflate(2131558454, (ViewGroup)null);
      RecyclerView var5 = (RecyclerView)var6.findViewById(2131363072);
      var4.setContentView(var6);
      var4.setBackgroundDrawable(new ColorDrawable(0));
      var4.setOutsideTouchable(false);
      var4.setFocusable(true);
      var4.showAtLocation(var1, 0, var7[0], var7[1] - var1.getHeight() * 3);
      var5.setLayoutManager(new LinearLayoutManager(var0));
      TmpsAdapter var8 = new TmpsAdapter(var0, var2);
      var5.setAdapter(var8);
      var8.setOnItemClickListener(new DialogUtils$$ExternalSyntheticLambda3(var3, var4));
   }

   public static PopupWindow getCenterControlPopupWindow() {
      return centerControlPopupWindow;
   }

   // $FF: synthetic method
   static void lambda$chooseTmps$0(TmpsAdapter.OnItemClickListener var0, PopupWindow var1, int var2) {
      if (var0 != null) {
         var0.click(var2);
      }

      var1.dismiss();
   }

   // $FF: synthetic method
   static void lambda$mainChoose$1(MainChooseAdapter.OnItemClickListener var0, PopupWindow var1, int var2) {
      if (var0 != null) {
         var0.click(var2);
      }

      var1.dismiss();
   }

   // $FF: synthetic method
   static void lambda$mainMedia$2(MainMediaAdapter.OnItemClickListener var0, PopupWindow var1, int var2, ComponentName var3) {
      if (var0 != null) {
         var0.click(var2, var3);
      }

      var1.dismiss();
   }

   // $FF: synthetic method
   static void lambda$showPopupwindows$3(AirSelectAdapter.OnItemClickListener var0, PopupWindow var1, int var2) {
      if (var0 != null) {
         var0.click(var2);
      }

      var1.dismiss();
   }

   // $FF: synthetic method
   static void lambda$showSettingDialog$4(SettingDialogAdapter.OnItemClickListener var0, PopupWindow var1, int var2) {
      if (var0 != null) {
         var0.onClick(var2);
      }

      var1.dismiss();
   }

   public static void mainChoose(Context var0, View var1, List var2, MainChooseAdapter.OnItemClickListener var3) {
      int[] var6 = new int[2];
      var1.getLocationOnScreen(var6);
      PopupWindow var5 = new PopupWindow(var1, -2, -2);
      View var7 = LayoutInflater.from(var0).inflate(2131558470, (ViewGroup)null);
      RecyclerView var4 = (RecyclerView)var7.findViewById(2131363072);
      var5.setContentView(var7);
      var5.setBackgroundDrawable(new ColorDrawable(0));
      var5.setOutsideTouchable(false);
      var5.setFocusable(true);
      var5.showAtLocation(var1, 0, var6[0], 0);
      var4.setLayoutManager(new LinearLayoutManager(var0));
      MainChooseAdapter var8 = new MainChooseAdapter(var0, var2);
      var4.setAdapter(var8);
      var8.setOnItemClickListener(new DialogUtils$$ExternalSyntheticLambda0(var3, var5));
   }

   public static void mainMedia(Context var0, View var1, List var2, MainMediaAdapter.OnItemClickListener var3) {
      int[] var7 = new int[2];
      var1.getLocationOnScreen(var7);
      PopupWindow var5 = new PopupWindow(var1, -2, -2);
      View var6 = LayoutInflater.from(var0).inflate(2131558471, (ViewGroup)null);
      RecyclerView var4 = (RecyclerView)var6.findViewById(2131363072);
      var5.setContentView(var6);
      var5.setBackgroundDrawable(new ColorDrawable(0));
      var5.setOutsideTouchable(false);
      var5.setFocusable(true);
      var5.showAtLocation(var1, 0, var7[0], 0);
      var4.setLayoutManager(new LinearLayoutManager(var0));
      MainMediaAdapter var8 = new MainMediaAdapter(var0, var2);
      var4.setAdapter(var8);
      var8.setOnItemClickListener(new DialogUtils$$ExternalSyntheticLambda4(var3, var5));
   }

   public static CenterControlView showCenterControl(Context var0, View var1) {
      int[] var4 = new int[2];
      var1.getLocationOnScreen(var4);
      PopupWindow var5 = new PopupWindow(var1, -2, -2);
      CenterControlView var6 = new CenterControlView(var0);
      var5.setContentView(var6);
      var5.setBackgroundDrawable(new ColorDrawable(0));
      var5.setOutsideTouchable(false);
      var5.setFocusable(true);
      var6.measure(0, 0);
      int var3 = var6.getMeasuredHeight();
      int var2 = var6.getMeasuredWidth();
      var6.refreshUi();
      var5.showAtLocation(var1, 80, -var2 / 2 + 55, var4[1] - var3 + 30);
      centerControlPopupWindow = var5;
      return var6;
   }

   public static void showPopupwindows(Context var0, View var1, List var2, String var3, AirSelectAdapter.OnItemClickListener var4) {
      PopupWindow var7 = new PopupWindow(var1, -2, -2);
      View var8 = LayoutInflater.from(var0).inflate(2131558439, (ViewGroup)null);
      RecyclerView var6 = (RecyclerView)var8.findViewById(2131363072);
      TextView var9 = (TextView)var8.findViewById(2131363545);
      var7.setContentView(var8);
      var7.setBackgroundDrawable(new ColorDrawable(0));
      var7.setOutsideTouchable(false);
      var7.setFocusable(true);
      var1.measure(0, 0);
      int var5 = var1.getMeasuredHeight();
      var1.getMeasuredWidth();
      var7.showAsDropDown(var1, 0, -var5);
      if (!TextUtils.isEmpty(var3)) {
         var9.setText(var3);
      }

      var6.setLayoutManager(new LinearLayoutManager(var0));
      AirSelectAdapter var10 = new AirSelectAdapter(var0, var2);
      var6.setAdapter(var10);
      var10.setOnItemClickListener(new DialogUtils$$ExternalSyntheticLambda2(var4, var7));
   }

   public static void showSettingDialog(Context var0, View var1, List var2, int var3, SettingDialogAdapter.OnItemClickListener var4) {
      PopupWindow var5 = new PopupWindow(var1, -2, -2);
      View var7 = LayoutInflater.from(var0).inflate(2131558469, (ViewGroup)null);
      RecyclerView var6 = (RecyclerView)var7.findViewById(2131363072);
      var5.setContentView(var7);
      var5.setBackgroundDrawable(new ColorDrawable(0));
      var5.setOutsideTouchable(false);
      var5.setFocusable(true);
      int[] var9 = Utils.calculatePopWindowPos(var1, var7);
      var5.showAtLocation(var7, 8388659, var9[0], var9[1]);
      var6.setLayoutManager(new LinearLayoutManager(var0));
      SettingDialogAdapter var8 = new SettingDialogAdapter(var0, var2);
      var6.setAdapter(var8);
      var6.scrollToPosition(var3);
      var8.setOnItemClickListener(new DialogUtils$$ExternalSyntheticLambda1(var4, var5));
   }

   public static void showTipsDialog(Context var0, String var1, View.OnClickListener var2) {
      AlertDialog.Builder var3 = new AlertDialog.Builder(var0);
      View var4 = LayoutInflater.from(var0).inflate(2131558461, (ViewGroup)null);
      var3.setView(var4);
      AlertDialog var7 = var3.create();
      var7.show();
      TextView var5 = (TextView)var4.findViewById(2131363608);
      Button var6 = (Button)var4.findViewById(2131362074);
      Button var8 = (Button)var4.findViewById(2131362073);
      if (!TextUtils.isEmpty(var1)) {
         var5.setText(var1);
      }

      var8.setOnClickListener(new View.OnClickListener(var7) {
         final AlertDialog val$alertDialog;

         {
            this.val$alertDialog = var1;
         }

         public void onClick(View var1) {
            this.val$alertDialog.dismiss();
         }
      });
      var6.setOnClickListener(new View.OnClickListener(var2, var7) {
         final AlertDialog val$alertDialog;
         final View.OnClickListener val$onClickListener;

         {
            this.val$onClickListener = var1;
            this.val$alertDialog = var2;
         }

         public void onClick(View var1) {
            View.OnClickListener var2 = this.val$onClickListener;
            if (var2 != null) {
               var2.onClick(var1);
            }

            this.val$alertDialog.dismiss();
         }
      });
   }
}
