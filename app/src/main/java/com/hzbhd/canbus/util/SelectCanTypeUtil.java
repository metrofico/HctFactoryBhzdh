package com.hzbhd.canbus.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgService;
import com.hzbhd.canbus.activity.UPDProgressActivity;
import com.hzbhd.canbus.adapter.SelectedListAdapter;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.config.McuVehicleConfig;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.cantype.CanTypeUtil;
import com.hzbhd.config.use.CanBus;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.softwinner.SystemMix;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectCanTypeUtil {
   private static int mSelectPosition;

   public static void disableApp(Context var0, ComponentName var1) {
      Log.i("CanbusInfo", "CANBUS unShow disableApp: " + var1);

      try {
         var0.getPackageManager().setComponentEnabledSetting(var1, 2, 1);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static void enableApp(Context var0, ComponentName var1) {
      Log.i("CanbusInfo", "CANBUS show enableApp: " + var1);

      try {
         var0.getPackageManager().setComponentEnabledSetting(var1, 1, 1);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static int getCurrentCanDiffId() {
      return CanbusConfig.INSTANCE.getDifferentId();
   }

   public static int getCurrentCanTypeId(Context var0) {
      return CanbusConfig.INSTANCE.getCanType();
   }

   private static void goUpdProgress(Context var0, int var1, boolean var2) {
      LogUtil.showLog("goUpdProgress");
      UPDProgressActivity.openActivity(var0, var1, var2);
   }

   public static void resetApp(Application var0) {
      LogUtil.showLog("resetApp");
      ((MyApplication)var0).removeAllActivity();
      MsgMgrFactory.setThisNull();
      UiMgrFactory.setThisNull();
      MyApplication.IS_SET = false;
      FutureUtil.instance.initCanbusAmp(var0);
      System.putString(var0.getContentResolver(), "can_system_version", "--");
      MsgMgrFactory.getCanMsgMgrUtil(var0).initCommand(var0);
      var0.stopService(new Intent(var0, BackCameraUiService.class));
      LogUtil.showLog("stop BackCameraUiService");
   }

   private static void saveCanDiffId(Context var0, int var1) {
      CanBus.INSTANCE.setDifferentId(var1);
      LogUtil.showLog("saveCanDiffId:" + var1);
      SharePreUtil.setIntValue(var0, "share_pre_can_different_id", var1);
   }

   public static void setCanTypeNotResetMpu(int var0) {
      LogUtil.showLog("切换can不重启");
      setFactoryCanType(var0);
      CanbusMsgService.mIsHaveDealWithReportCanTypeId = false;
      CanbusMsgService.mIsNeedSystemExit = true;
   }

   private static boolean setFactoryCanType(int var0) {
      LogUtil.showLog("setFactoryCanType：" + var0);
      CanbusConfig.INSTANCE.setCanType(var0);
      return true;
   }

   private static void setSpecifyCanTypeIdAndRestMpu(int var0) {
      LogUtil.showLog("切换can重启");
      if (setFactoryCanType(var0)) {
         SystemMix.bhd_sync();
         SendKeyManager.getInstance().resetMpu(HotKeyConstant.RESET_MODE.NORMAL, 1);
      }

   }

   public static void showDialogToUpdate(Activity var0, CanTypeAllEntity var1, String var2) {
      View var4 = LayoutInflater.from(var0).inflate(2131558760, (ViewGroup)null);
      TextView var5 = (TextView)var4.findViewById(2131363704);
      ListView var3 = (ListView)var4.findViewById(2131362821);
      if (!TextUtils.isEmpty(var2)) {
         var5.setText(var2);
      } else {
         Locale var6 = Locale.getDefault();
         if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("showDialogToUpdate(): ----" + var6);
         }

         if (var6.toString().equals("en_US")) {
            var2 = var0.getResources().getString(2131770082) + " " + var1.getCan_type_id() + "  " + var1.getEnglish_protocol_company() + "  " + var1.getEnglish_car_category();
         } else {
            var2 = var0.getResources().getString(2131770082) + " " + var1.getCan_type_id() + "  " + var1.getProtocol_company() + "  " + var1.getCar_category();
         }

         var5.setText(var2);
      }

      ArrayList var8 = CanTypeUtil.INSTANCE.getCanType(var1.getCan_type_id()).getList();
      if (var8 == null) {
         LogUtil.showLog("list from CanTypeDao is null, can type id:" + var1.getCan_type_id());
      } else {
         if (var1.getCan_type_id() == getCurrentCanTypeId(var0)) {
            mSelectPosition = CanbusConfig.INSTANCE.getSelectCarPosition();
         } else {
            mSelectPosition = 0;
         }

         SelectedListAdapter var7 = new SelectedListAdapter(var0, var8);
         var7.setSelectPosition(mSelectPosition);
         var3.setAdapter(var7);
         var3.setOnItemClickListener(new AdapterView.OnItemClickListener(var7) {
            final SelectedListAdapter val$selectedListAdapter;

            {
               this.val$selectedListAdapter = var1;
            }

            public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
               SelectCanTypeUtil.mSelectPosition = var3;
               this.val$selectedListAdapter.setSelectPosition(var3);
               this.val$selectedListAdapter.notifyDataSetChanged();
            }
         });
         (new AlertDialog.Builder(var0, 2131820748)).setNegativeButton(2131767924, (DialogInterface.OnClickListener)null).setPositiveButton(2131768068, new DialogInterface.OnClickListener(var1, var0, var8) {
            final Activity val$activity;
            final CanTypeAllEntity val$entity;
            final List val$list;

            {
               this.val$entity = var1;
               this.val$activity = var2;
               this.val$list = var3;
            }

            public void onClick(DialogInterface var1, int var2) {
               boolean var6;
               if (this.val$entity.getCan_type_id() == SelectCanTypeUtil.getCurrentCanTypeId(this.val$activity)) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               if (!var6 || CanbusConfig.INSTANCE.getSelectCarPosition() != SelectCanTypeUtil.mSelectPosition) {
                  CanTypeAllEntity var5 = (CanTypeAllEntity)this.val$list.get(SelectCanTypeUtil.mSelectPosition);
                  CanbusConfig.INSTANCE.setEachId(var5.getEach_can_id());
                  CanbusConfig.INSTANCE.setDifferentId(var5.getCan_different_id());
                  CanbusConfig.INSTANCE.setSelectCarPosition(SelectCanTypeUtil.mSelectPosition);
                  CanbusConfig var4 = CanbusConfig.INSTANCE;
                  boolean var3;
                  if (var5.getIs_show_app() == 1) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var4.setIsShowApp(var3);
                  if (var6) {
                     if (com.hzbhd.util.LogUtil.log5()) {
                        com.hzbhd.util.LogUtil.d("can type id equal");
                     }

                     Toast.makeText(this.val$activity, 2131768706, 1).show();
                     java.lang.System.exit(0);
                  } else {
                     if (com.hzbhd.util.LogUtil.log5()) {
                        com.hzbhd.util.LogUtil.d("is app handle key equal :" + this.val$entity.getIs_app_handle_key());
                     }

                     if (this.val$entity.getIs_app_handle_key() == 1) {
                        Log.i("SelectCanTypeUtil", "onClick: [" + McuVehicleConfig.INSTANCE.getBean() + "]");
                        McuVehicleConfig.INSTANCE.getBean().setBrand(this.val$entity.getCan_type_id() >> 8 & 255);
                        McuVehicleConfig.INSTANCE.getBean().setModel(this.val$entity.getCan_type_id() & 255);
                        McuVehicleConfig.INSTANCE.getBean().setBaud_rate(this.val$entity.getBaud_rate());
                        McuVehicleConfig.INSTANCE.getBean().setCan_protocol(this.val$entity.getPack_type());
                        CanbusConfig.INSTANCE.setBaud_Rate(this.val$entity.getBaud_rate());
                        CanbusConfig.INSTANCE.setCanPackType(this.val$entity.getPack_type());
                        CanbusConfig.INSTANCE.setCanType(this.val$entity.getCan_type_id());
                        McuVehicleConfig.INSTANCE.setMcu();
                        SelectCanTypeUtil.setSpecifyCanTypeIdAndRestMpu(this.val$entity.getCan_type_id());
                        Toast.makeText(this.val$activity, 2131769791, 1).show();
                     } else {
                        if (com.hzbhd.util.LogUtil.log5()) {
                           com.hzbhd.util.LogUtil.d("<confirm> " + this.val$entity.getCan_type_id());
                        }

                        Toast.makeText(this.val$activity, "Unsupported ID", 0).show();
                     }
                  }

               }
            }
         }).setView(var4).create().show();
      }
   }
}
