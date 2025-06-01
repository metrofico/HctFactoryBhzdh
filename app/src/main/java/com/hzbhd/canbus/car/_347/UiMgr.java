package com.hzbhd.canbus.car._347;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private static final int CONFIGURATION_camera_452 = 1;
   private static final int CONFIGURATION_defualt_452 = -1;
   private static final int CONFIGURATION_front_camera_452 = 0;
   private String BALANCE = "BALANCE";
   private String BASS = "BASS";
   public String CAMERA_FLAG_TAG_452 = "ORIGINAL.MEDIA.CAMERA.FLAG_452";
   private String FADER = "FADER";
   private String MIDTONE = "MIDTONE";
   private String TREBLE = "TREBLE";
   private String VOL = "VOL";
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr msgMgr;
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(33);
      }
   };
   OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
         this.this$0.initAmplifierInfo();
      }

      public void destroy() {
      }
   };
   OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               MyLog.temporaryTracking("左右" + var2 + "");
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 2, (byte)var2});
            }
         } else {
            MyLog.temporaryTracking("前后" + var2 + "");
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 1, (byte)var2});
         }

      }
   };
   OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -96, 3, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -96, 5, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -96, 4, (byte)var2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -96, 0, (byte)var2});
         }

      }
   };
   OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(20);
         this.this$0.activeRequestData(22);
         this.this$0.activeRequestData(36);
         this.this$0.activeRequestData(37);
         this.this$0.activeRequestData(65, 2);
      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_246_paring_info") && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var3});
            var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.differentId + "" + this.this$0.eachId + "" + var1 + "" + var2, var1, var2, var3);
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_246_setting_car_info32")) {
            if (var2 != 0) {
               if (var2 != 1) {
                  if (var2 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.differentId + "" + this.this$0.eachId + "" + var1 + "" + var2, var1, var2, var3);
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)(var3 + 1)});
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.differentId + "" + this.this$0.eachId + "" + var1 + "" + var2, var1, var2, var3);
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, this.this$0.differentId + "" + this.this$0.eachId + "" + var1 + "" + var2, var1, var2, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_347_setting_0_10") && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_347_setting_0_8") && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var3});
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_347_settings")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_347_settings", "_347_settings_0")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)(var3 + 1)});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_347_settings", "_347_settings_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)(var3 + 1)});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_347_settings", "_347_settings_2")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)(var3 + 6)});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_347_settings", "_451_reverse_flag")) {
               if (var3 == 0) {
                  CanbusConfig.INSTANCE.setCameraConfiguration(true);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettingsUi(this.this$0.mContext, var1, var2, this.this$0.CAMERA_FLAG_TAG_452, 0);
               } else if (var3 == 1) {
                  CanbusConfig.INSTANCE.setCameraConfiguration(false);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettingsUi(this.this$0.mContext, var1, var2, this.this$0.CAMERA_FLAG_TAG_452, 1);
               }
            }
         }

      }
   };
   private int volume;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      if (this.eachId == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.getCarModelData(0)});
      }

      if (this.eachId == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.getCarModelData(1)});
      }

      if (this.eachId == 3) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.getCarModelData(2)});
      }

      if (this.eachId == 4) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.getCarModelData(3)});
      }

      if (this.eachId == 5) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.getCarModelData(4)});
      }

      if (this.eachId == 6) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)this.getCarModelData(5)});
      }

      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var3) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            String var6 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            var6.hashCode();
            if (var6.equals("_330_amplifier_info")) {
               var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var6.hashCode();
               switch (var6) {
                  case "_347_setting_0_4":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 65, (byte)var3});
                     break;
                  case "_347_setting_0_5":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 66, (byte)var3});
                     break;
                  case "_347_setting_0_6":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 67, (byte)var3});
                     break;
                  case "_347_setting_0_7":
                     CanbusMsgSender.sendMsg(new byte[]{22, -58, 68, (byte)var3});
                     break;
                  case "_347_setting_0_9":
                     CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var3});
                     break;
                  case "_330_Vehicle_volume_follows_ASL":
                     CanbusMsgSender.sendMsg(new byte[]{22, -96, 6, (byte)var3});
               }
            }

         }
      });
      AmplifierPageUiSet var4 = this.getAmplifierPageUiSet(var1);
      var4.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var4.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      var4.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
      this.getAirUiSet(this.mContext).getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
      int var2 = this.eachId;
      if (var2 == 1 || var2 == 2 || var2 == 3) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

      if (this.eachId != 5) {
         this.removeMainPrjBtnByName(var1, "air");
         this.removeSettingRightItemByNameList(var1, new String[]{"_246_paring_info1"});
      }

      this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_347_setting_0_10"});
      this.activeRequestData(48);
      this.initCamera(var1);
   }

   private void activeRequestData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
   }

   private void activeRequestData(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1, (byte)var2});
   }

   private int getCarModelData(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     return var1 != 5 ? 0 : 6;
                  } else {
                     return 5;
                  }
               } else {
                  return 4;
               }
            } else {
               return 3;
            }
         } else {
            return 2;
         }
      } else {
         return 1;
      }
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void getOtherSettingInfo() {
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 0, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "" + 0, 0));
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 1, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "" + 1, 0));
      this.getMsgMgr(this.mContext).updateSettings(this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 2, SharePreUtil.getIntValue(this.mContext, this.differentId + "" + this.eachId + "" + this.getSettingLeftIndexes(this.mContext, "_246_setting_car_info") + "" + 2, 0));
   }

   private void saveAmplifierInfo(int var1, int var2) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 == 5) {
                        SharePreUtil.setIntValue(this.mContext, this.TREBLE, var2);
                     }
                  } else {
                     SharePreUtil.setIntValue(this.mContext, this.MIDTONE, var2);
                  }
               } else {
                  SharePreUtil.setIntValue(this.mContext, this.BASS, var2);
               }
            } else {
               SharePreUtil.setIntValue(this.mContext, this.FADER, var2);
            }
         } else {
            SharePreUtil.setIntValue(this.mContext, this.BALANCE, var2);
         }
      } else {
         SharePreUtil.setIntValue(this.mContext, this.VOL, var2);
      }

   }

   private void sendAmplifierInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -96, (byte)var1, (byte)var2});
      this.saveAmplifierInfo(var1, var2);
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var7.get(var4)).getTitleSrn().equals(var2)) {
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
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var8 = var7.getItemList();
            Iterator var10 = var8.iterator();

            for(int var5 = 0; var5 < var8.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var10.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   void initAmplifierInfo() {
      GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(this.mContext, this.BALANCE, 0);
      GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(this.mContext, this.FADER, 0);
      GeneralAmplifierData.volume = SharePreUtil.getIntValue(this.mContext, this.VOL, 28);
      GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(this.mContext, this.BASS, 0) + 9;
      GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(this.mContext, this.MIDTONE, 0) + 9;
      GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(this.mContext, this.TREBLE, 0) + 9;
      this.sendAmplifierInfo(0, SharePreUtil.getIntValue(this.mContext, this.VOL, 28));
      this.sendAmplifierInfo(1, SharePreUtil.getIntValue(this.mContext, this.BALANCE, 0));
      this.sendAmplifierInfo(2, SharePreUtil.getIntValue(this.mContext, this.FADER, 0));
      this.sendAmplifierInfo(3, SharePreUtil.getIntValue(this.mContext, this.BASS, 0));
      this.sendAmplifierInfo(4, SharePreUtil.getIntValue(this.mContext, this.MIDTONE, 0));
      this.sendAmplifierInfo(5, SharePreUtil.getIntValue(this.mContext, this.TREBLE, 0));
      this.getMsgMgr(this.mContext).updateAmplifier();
   }

   public void initCamera(Context var1) {
      int var2 = SharePreUtil.getIntValue(var1, this.CAMERA_FLAG_TAG_452, -1);
      boolean var3 = true;
      if (var2 == -1) {
         byte var5 = CanbusConfig.INSTANCE.getCameraConfiguration();
         this.getMsgMgr(var1).updateSettingsUi(var1, this.getSettingLeftIndexes(var1, "_347_settings"), this.getSettingRightIndex(var1, "_347_settings", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_452, 1 ^ var5);
      } else {
         this.getMsgMgr(var1).updateSettingsUi(var1, this.getSettingLeftIndexes(var1, "_347_settings"), this.getSettingRightIndex(var1, "_347_settings", "_451_reverse_flag"), this.CAMERA_FLAG_TAG_452, var2);
         CanbusConfig var4 = CanbusConfig.INSTANCE;
         if (var2 != 0) {
            var3 = false;
         }

         var4.setCameraConfiguration(var3);
      }

   }

   public void sendCarType() {
      switch (this.eachId) {
         case 1:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 4});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 5});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 6});
      }

   }

   public void sendIconInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte)this.getDecimalFrom8Bit(0, 0, 0, 0, 0, var1, var2, 0)});
   }

   public void sendMediaPalyInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void sendPhoneNumber() {
      CanbusMsgSender.sendMsg(new byte[]{22, -59, 6, 1, 84, 101, 108, 32, 79, 102, 102});
   }

   public void sendPhoneNumber(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendRadioInfo(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, -62, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   public void sendSourceInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void sendVol(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte)var1});
   }
}
