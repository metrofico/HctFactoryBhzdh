package com.hzbhd.canbus.util;

import android.content.ComponentName;
import android.content.Context;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.commontools.utils.ConfigUtil;

public class AppEnableUtil {
   public static void cusProjectSetting(Context var0, int var1) {
      if (var1 == 273) {
         enableApp(var0, Constant.ChengWeiMainActivity);
      } else {
         disableApp(var0, Constant.ChengWeiMainActivity);
      }

      if (var1 == 277) {
         enableApp(var0, Constant.VehicleMonitorActivity);
         enableApp(var0, Constant.VehicleDiagnosisActivity);
         enableApp(var0, Constant.EnergyFeedbackActivity);
         if (SharePreUtil.getBoolValue(var0, "share_pre_is_first_time_launcher", true)) {
            SharePreUtil.setBoolValue(var0, "share_pre_is_first_time_launcher", false);
            SystemUtil.cleanLauncher(var0);
         }
      } else {
         disableApp(var0, Constant.VehicleMonitorActivity);
         disableApp(var0, Constant.VehicleDiagnosisActivity);
         disableApp(var0, Constant.EnergyFeedbackActivity);
         if (ConfigUtil.getDeviceId().contains("YHTC05W1")) {
            LogUtil.showLog("setSpecifyCanTypeIdAndRestMpu");
            SelectCanTypeUtil.setCanTypeNotResetMpu(277);
         }
      }

      if (var1 == 283) {
         enableApp(var0, Constant.DZMainActivity);
      } else {
         disableApp(var0, Constant.DZMainActivity);
      }

      if (var1 == 291) {
         enableApp(var0, Constant.DZMainActivity291);
      } else {
         disableApp(var0, Constant.DZMainActivity291);
      }

      if (var1 == 290) {
         enableApp(var0, Constant.ChengWeiMainActivity290);
      } else {
         disableApp(var0, Constant.ChengWeiMainActivity290);
      }

      if (var1 == 304) {
         enableApp(var0, HzbhdComponentName.YuanYiYuanMainActivity);
      } else {
         disableApp(var0, HzbhdComponentName.YuanYiYuanMainActivity);
      }

      if (var1 == 436) {
         enableApp(var0, Constant.MdDvrActivity);
      } else if (var1 == 441) {
         if (CanbusConfig.INSTANCE.getEachId() == 1) {
            enableApp(var0, Constant.MdDvrActivity);
         } else if (CanbusConfig.INSTANCE.getEachId() == 2) {
            disableApp(var0, Constant.MdDvrActivity);
         }
      } else if (var1 == 442) {
         enableApp(var0, Constant.MdDvrActivity);
      } else {
         disableApp(var0, Constant.MdDvrActivity);
      }

      if (var1 == 446) {
         enableApp(var0, HzbhdComponentName.WmCarSettings);
      } else {
         disableApp(var0, HzbhdComponentName.WmCarSettings);
      }

      if (var1 == 448) {
         enableApp(var0, Constant.ID448DvrActivity);
      } else {
         disableApp(var0, Constant.ID448DvrActivity);
      }

      if (var1 == 452 && CanbusConfig.INSTANCE.getEachId() == 3) {
         enableApp(var0, HzbhdComponentName.OriginalMediaActivity_LEXUS);
      } else {
         disableApp(var0, HzbhdComponentName.OriginalMediaActivity_LEXUS);
      }

      if (var1 == 451 || var1 == 452 && CanbusConfig.INSTANCE.getEachId() != 3) {
         enableApp(var0, HzbhdComponentName.Aux2Activity);
      } else {
         disableApp(var0, HzbhdComponentName.Aux2Activity);
      }

      if (var1 == 460) {
         enableApp(var0, HzbhdComponentName.AirActivity460);
         enableApp(var0, HzbhdComponentName.SettingActivity460);
      } else {
         disableApp(var0, HzbhdComponentName.AirActivity460);
         disableApp(var0, HzbhdComponentName.SettingActivity460);
      }

      boolean var2 = SharePreUtil.getBoolValue(var0, "td.462.avm.show.tag", false);
      if (var1 == 462 && var2) {
         enableApp(var0, HzbhdComponentName.AvmActivityD1);
      } else {
         disableApp(var0, HzbhdComponentName.AvmActivityD1);
      }

      if (var1 == 462) {
         enableApp(var0, HzbhdComponentName.CarBodyActivityD1);
      } else {
         disableApp(var0, HzbhdComponentName.CarBodyActivityD1);
      }

      if (var1 == 439) {
         enableApp(var0, HzbhdComponentName.ID439MainActivity);
         enableApp(var0, HzbhdComponentName.ID439PanelActivity);
      } else {
         disableApp(var0, HzbhdComponentName.ID439MainActivity);
         disableApp(var0, HzbhdComponentName.ID439PanelActivity);
      }

      if (var1 == 467) {
         enableApp(var0, HzbhdComponentName.ID439MainActivity1280x720);
         enableApp(var0, HzbhdComponentName.ID439PanelActivity1280x720);
      } else {
         disableApp(var0, HzbhdComponentName.ID439MainActivity1280x720);
         disableApp(var0, HzbhdComponentName.ID439PanelActivity1280x720);
      }

      if (var1 == 324 && CanbusConfig.INSTANCE.getDifferentId() == 1) {
         enableApp(var0, HzbhdComponentName.Id324AuxVideoActivity);
      } else {
         disableApp(var0, HzbhdComponentName.Id324AuxVideoActivity);
      }

      if (var1 == 466) {
         enableApp(var0, HzbhdComponentName.SettingsActivity466);
      } else {
         disableApp(var0, HzbhdComponentName.SettingsActivity466);
      }

      if (var1 == 471) {
         enableApp(var0, HzbhdComponentName.CarBodyActivityE1);
      } else {
         disableApp(var0, HzbhdComponentName.CarBodyActivityE1);
      }

      if (var1 == 473) {
         enableApp(var0, HzbhdComponentName.CarBodyActivityA3);
      } else {
         disableApp(var0, HzbhdComponentName.CarBodyActivityA3);
      }

   }

   public static void disableApp(Context var0, ComponentName var1) {
      SelectCanTypeUtil.disableApp(var0, var1);
   }

   public static void disableAux2Activity(Context var0) {
      SelectCanTypeUtil.disableApp(var0, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
      SelectCanTypeUtil.disableApp(var0, HzbhdComponentName.NewCanbusOnStarActivity);
      SelectCanTypeUtil.disableApp(var0, HzbhdComponentName.NewCanbusSyncActivity);
   }

   public static void enableApp(Context var0, ComponentName var1) {
      SelectCanTypeUtil.enableApp(var0, var1);
   }

   public static void isShowApp(Context var0, int var1) {
      if (var1 == 1) {
         SelectCanTypeUtil.enableApp(var0, HzbhdComponentName.NewCanBusMainActivity);
      } else {
         SelectCanTypeUtil.disableApp(var0, HzbhdComponentName.NewCanBusMainActivity);
      }

   }

   public static void isUseNewApp(Context var0, int var1) {
      if (var1 == 1) {
         SelectCanTypeUtil.disableApp(var0, HzbhdComponentName.CanbusCarInfoMainActivity);
      }

   }
}
