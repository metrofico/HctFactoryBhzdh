package com.hzbhd.canbus.car._451;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._451.util.CycleRequestSpeed;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.ui.util.BaseUtil;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class UiMgr extends AbstractUiMgr {
   private static final int CONFIGURATION_camera_451 = 1;
   private static final int CONFIGURATION_defualt_451 = -1;
   private static final int CONFIGURATION_front_camera_451 = 0;
   public String CAMERA_FLAG_TAG_451 = "ORIGINAL.MEDIA.CAMERA.FLAG_451";
   private final int DriverDataPageUiSet_ON_CREATE = -1;
   private final int DriverDataPageUiSet_ON_DESTROY = -2;
   int differentId;
   int eachId;
   Context mContext;
   private MsgMgr mMsgMgr;
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_451_resolving_power")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_451_resolving_power", "_451_resolving_power_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)(var3 - 1)});
            }
         }

      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_451_activation")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_451_activation", "_451_activation_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -119, 1});
               BaseUtil.INSTANCE.runUi(new Function0(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public Unit invoke() {
                     Toast.makeText(this.this$1.this$0.mContext, this.this$1.this$0.mContext.getString(2131766206), 0).show();
                     return null;
                  }
               });
            }
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_451_host_settings")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_451_host_settings", "_451_host_settings_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -110, (byte)var3, 0});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_451_resolving_power")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_451_resolving_power", "_451_reverse_flag")) {
               if (var3 == 0) {
                  CanbusConfig.INSTANCE.setCameraConfiguration(true);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettingsUi(this.this$0.mContext, var1, var2, this.this$0.CAMERA_FLAG_TAG_451, 0);
               } else if (var3 == 1) {
                  CanbusConfig.INSTANCE.setCameraConfiguration(false);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettingsUi(this.this$0.mContext, var1, var2, this.this$0.CAMERA_FLAG_TAG_451, 1);
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_451_resolving_power", "_451_benz_fbv")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var3});
               SharePreUtil.setIntValue(this.this$0.mContext, this.this$0.resolution_ratio_tag, var3);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_451_resolving_power", "_451_bmw_fbv")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var3});
               SharePreUtil.setIntValue(this.this$0.mContext, this.this$0.resolution_ratio_tag, var3);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_451_resolving_power", "_451_audi_fbv")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var3});
               SharePreUtil.setIntValue(this.this$0.mContext, this.this$0.resolution_ratio_tag, var3);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }
         }

      }
   };
   DecimalFormat oneDecimal = new DecimalFormat("###0.0");
   private String resolution_ratio_tag = "resolution.ratio.tag";
   DecimalFormat timeFormat = new DecimalFormat("00");
   DecimalFormat towDecimal = new DecimalFormat("###0.00");

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      if (this.eachId == 1) {
         this.removeFrontAirButtonByName(var1, "in_out_cycle");
         this.removeFrontAirButtonByName(var1, "ac");
         this.getAirUiSet(var1).getFrontArea().setShowSeatHeat(false);
      }

      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      var2.setOnSettingItemClickListener(this.onSettingItemClickListener);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               CycleRequestSpeed.getInstance().start(0, new ActionCallback(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void toDo(Object var1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -112, 22});
                     CycleRequestSpeed.getInstance().reset(500);
                  }
               });
            }

            if (var1 == -2) {
               CycleRequestSpeed.getInstance().stop();
            }

         }
      });
      this.removeSettingLeftItemByNameList(var1, new String[]{"_451_host_settings"});
      this.removeSettingLeftItemByNameList(var1, new String[]{"_451_activation"});
      this.removeDriveDateItemForTitles(var1, new String[]{"_451_acc_status", "_451_gear_status", "_451_current_oil_quantity", "_451_small_lamp_status"});
      this.initCamera(var1);
      this.removeSettingRightItemByNameList(var1, new String[]{"_451_benz_fbv", "_451_bmw_fbv"});
      this.initCamera(var1);
      this.initResolutionAatio();
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initCamera(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, this.CAMERA_FLAG_TAG_451, -1);
      boolean var3 = true;
      if (var2 == -1) {
         byte var5 = CanbusConfig.INSTANCE.getCameraConfiguration();
         this.getMsgMgr(var1).updateSettingsUi(var1, this.getSettingLeftIndexes(var1, "_451_resolving_power"), this.getSettingRightIndex(var1, "_451_resolving_power", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_451, 1 ^ var5);
      } else {
         this.getMsgMgr(var1).updateSettingsUi(var1, this.getSettingLeftIndexes(var1, "_451_resolving_power"), this.getSettingRightIndex(var1, "_451_resolving_power", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_451, var2);
         CanbusConfig var4 = CanbusConfig.INSTANCE;
         if (var2 != 0) {
            var3 = false;
         }

         var4.setCameraConfiguration(var3);
      }

   }

   private void initResolutionAatio() {
      int var1 = SharePreUtil.getIntValue(this.mContext, this.resolution_ratio_tag, 0);
      CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var1});
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_451_resolving_power"), this.getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_benz_fbv"), var1);
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_451_resolving_power"), this.getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_bmw_fbv"), var1);
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_451_resolving_power"), this.getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_audi_fbv"), var1);
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var6 = var7.iterator();

         while(var6.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

            for(int var4 = 0; var4 < var5.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var5.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
