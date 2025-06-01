package com.hzbhd.canbus.car._446;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._446.CanObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MsgMgr extends AbstractMsgMgr {
   private String airJsonStr;
   Context context;
   DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
   private boolean last0x1CA = false;
   private int[] last0x217;
   private int lastBwhjd = 0;
   private boolean lastDaoCheTiShiYin = false;
   private boolean lastDingDengMenKong = false;
   private int lastDouPoHuanJiang = 0;
   private int lastEsp = 0;
   boolean lastLogo = false;
   private boolean lastQianZhuangShiDeng = false;
   int lastRiJianXingChe = 0;
   private boolean lastSouCheTianChuanZiDong = false;
   private boolean lastZiDongZhuChe = false;
   int lastZxld = 0;
   private int[] mCanBusInfoInt;
   private UiMgr mUiMgr;

   private int getMsDataType(int[] var1) {
      int var3 = var1[2];
      int var4 = var1[3];
      int var2 = var1[4];
      return var1[5] & 255 | (var3 & 255) << 24 | (var4 & 255) << 16 | (var2 & 255) << 8;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private void initDPHJ(Context var1) {
      if (SharePreUtil.getBoolValue(var1, "KEY_DPHJ_SWITCH_STATE", false)) {
         Log.d("fxHouDPHJ", "ON");
         WmCarData.douPoHuanJiang = true;
         CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 1, 0, 0, 0, 0, 0, 0, 0, 1});
      } else {
         Log.d("fxHouDPHJ", "OFF");
         WmCarData.douPoHuanJiang = false;
         CanbusMsgSender.sendMsg(new byte[]{22, -52, -52, -52, -52, 0, 0, 0, 0, 0, 0, 0, 0, 1});
      }

   }

   private String intArrayToJsonStr(int[] var1) {
      this.airJsonStr = "{";

      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var2 == var1.length - 1) {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + "}";
         } else {
            this.airJsonStr = this.airJsonStr + "\"Data" + var2 + "\":" + var1[var2] + ",";
         }
      }

      return this.airJsonStr;
   }

   private void set0x125(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[12], 6, 2);
      if (this.lastDouPoHuanJiang != var2) {
         if (var2 == 1) {
            WmCarData.douPoHuanJiang = true;
         } else if (var2 == 0) {
            WmCarData.douPoHuanJiang = false;
         }

         CanObserver.getInstance().dataChange();
         this.lastDouPoHuanJiang = var2;
      }

      boolean var4 = this.lastZiDongZhuChe;
      boolean var3;
      if (DataHandleUtils.getIntFromByteWithBit(var1[10], 4, 2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var4 != var3) {
         if (DataHandleUtils.getIntFromByteWithBit(var1[10], 4, 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         WmCarData.ziDongZhuChe = var3;
         CanObserver.getInstance().dataChange();
         this.lastZiDongZhuChe = WmCarData.ziDongZhuChe;
      }

      var2 = DataHandleUtils.getIntFromByteWithBit(var1[11], 0, 2);
      if (this.lastEsp != var2) {
         if (var2 == 1) {
            WmCarData.EspWengDing = this.context.getString(2131766046);
         } else if (var2 == 2) {
            WmCarData.EspWengDing = this.context.getString(2131766047);
         } else if (var2 == 3) {
            WmCarData.EspWengDing = this.context.getString(2131766058);
         }

         CanObserver.getInstance().dataChange();
         this.lastEsp = var2;
      }

   }

   private void set0x16F(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[9], 6, 2);
      if (this.lastZxld != var2) {
         if (var2 == 1) {
            WmCarData.zhuanXiangLiDu = this.context.getString(2131766079);
         } else if (var2 == 2) {
            WmCarData.zhuanXiangLiDu = this.context.getString(2131766081);
         } else if (var2 == 3) {
            WmCarData.zhuanXiangLiDu = this.context.getString(2131766082);
         }

         CanObserver.getInstance().dataChange();
         this.lastZxld = var2;
      }

   }

   private void set0x173(int[] var1) {
      boolean var5 = this.lastSouCheTianChuanZiDong;
      int var2 = var1[14];
      boolean var4 = false;
      boolean var3;
      if (DataHandleUtils.getIntFromByteWithBit(var2, 0, 2) == 2) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var5 != var3) {
         var3 = var4;
         if (DataHandleUtils.getIntFromByteWithBit(var1[14], 0, 2) == 2) {
            var3 = true;
         }

         WmCarData.suoCheTianChaungZiDongGuanBi = var3;
         CanObserver.getInstance().dataChange();
         this.lastSouCheTianChuanZiDong = WmCarData.suoCheTianChaungZiDongGuanBi;
      }

      var3 = DataHandleUtils.getBoolBit3(var1[14]);
      if (this.lastQianZhuangShiDeng != var3) {
         WmCarData.qianZhuangShiDeng = var3;
         CanObserver.getInstance().dataChange();
         this.lastQianZhuangShiDeng = var3;
      }

   }

   private void set0x1CA(int[] var1) {
      if (this.last0x1CA != (DataHandleUtils.getBoolBit5(var1[11]) ^ true)) {
         WmCarData.daiSuHuanXing = DataHandleUtils.getBoolBit5(var1[11]) ^ true;
         CanObserver.getInstance().dataChange();
         this.last0x1CA = WmCarData.daiSuHuanXing;
      }

   }

   private void set0x216(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[12], 3, 3);
      if (this.lastBwhjd != var2) {
         if (var2 == 0) {
            WmCarData.banWoHuiJiaDeng = "OFF";
         } else if (var2 == 1) {
            WmCarData.banWoHuiJiaDeng = "30s";
         } else if (var2 == 2) {
            WmCarData.banWoHuiJiaDeng = "60s";
         } else if (var2 == 3) {
            WmCarData.banWoHuiJiaDeng = "90s";
         } else if (var2 == 4) {
            WmCarData.banWoHuiJiaDeng = "120s";
         }

         CanObserver.getInstance().dataChange();
         this.lastBwhjd = var2;
      }

   }

   private void set0x217(int[] var1) {
      if (!Arrays.equals(this.last0x217, var1)) {
         WmCarData.tire_front_left = Double.parseDouble(this.df_2Decimal.format((double)var1[7] * 2.75));
         WmCarData.tire_front_right = Double.parseDouble(this.df_2Decimal.format((double)var1[8] * 2.75));
         WmCarData.tire_rear_left = Double.parseDouble(this.df_2Decimal.format((double)var1[9] * 2.75));
         WmCarData.tire_rear_right = Double.parseDouble(this.df_2Decimal.format((double)var1[10] * 2.75));
         WmCarData.tire_temp_front_left = var1[11] - 60 + this.getTempUnitC(this.context);
         WmCarData.tire_temp_front_right = var1[12] - 60 + this.getTempUnitC(this.context);
         WmCarData.tire_temp_rear_left = var1[13] - 60 + this.getTempUnitC(this.context);
         WmCarData.tire_temp_rear_right = var1[14] - 60 + this.getTempUnitC(this.context);
         CanObserver.getInstance().dataChange();
         this.last0x217 = var1;
      }

   }

   private void set0x235(int[] var1) {
      if (this.lastDingDengMenKong != DataHandleUtils.getBoolBit3(var1[10])) {
         WmCarData.dingDengMenKong = DataHandleUtils.getBoolBit3(var1[10]);
         CanObserver.getInstance().dataChange();
         this.lastDingDengMenKong = WmCarData.dingDengMenKong;
      }

   }

   private void set0x29C(int[] var1) {
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[7], 5, 2);
      if (this.lastRiJianXingChe != var2) {
         if (var2 == 1) {
            WmCarData.riJianXingCheDeng = true;
         }

         if (var2 == 2) {
            WmCarData.riJianXingCheDeng = false;
         }

         CanObserver.getInstance().dataChange();
         this.lastRiJianXingChe = var2;
      }

   }

   private void set0x38C(int[] var1) {
      if (this.lastDaoCheTiShiYin != DataHandleUtils.getBoolBit2(var1[7])) {
         WmCarData.daoCheTiShiYin = DataHandleUtils.getBoolBit2(var1[7]);
         CanObserver.getInstance().dataChange();
         this.lastDaoCheTiShiYin = WmCarData.daoCheTiShiYin;
      }

   }

   private void setLinBus0x20(byte[] var1) {
      boolean var5 = this.lastLogo;
      int var2 = DataHandleUtils.getIntFromByteWithBit(var1[3], 3, 3);
      boolean var4 = false;
      boolean var3;
      if (var2 == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var5 != var3) {
         var3 = var4;
         if (DataHandleUtils.getIntFromByteWithBit(var1[3], 3, 3) == 1) {
            var3 = true;
         }

         WmCarData.logo = var3;
         CanObserver.getInstance().dataChange();
         this.lastLogo = WmCarData.logo;
      }

   }

   private void setSWC(int[] var1) {
      if (DataHandleUtils.getBoolBit4(var1[10])) {
         Log.d("fxHouSwc", "K_DOME");
         this.realKeyClick4(this.context, 46);
      } else if (DataHandleUtils.getBoolBit5(var1[10])) {
         Log.d("fxHouSwc", "K_UP");
         this.realKeyClick4(this.context, 45);
      } else if (DataHandleUtils.getBoolBit6(var1[10])) {
         Log.d("fxHouSwc", "K_VOL_DN");
         this.realKeyClick4(this.context, 8);
      } else if (DataHandleUtils.getBoolBit7(var1[10])) {
         Log.d("fxHouSwc", "K_VOL_UP");
         this.realKeyClick4(this.context, 7);
      } else if (DataHandleUtils.getBoolBit0(var1[12])) {
         Log.d("fxHouSwc", "K_HOME");
         this.realKeyClick4(this.context, 52);
      } else if (DataHandleUtils.getBoolBit7(var1[13])) {
         Log.d("fxHouSwc", "K_MUTE");
         this.realKeyClick4(this.context, 3);
      } else {
         Log.d("fxHouSwc", "K_NONE");
         this.realKeyClick4(this.context, 0);
      }

   }

   private void setTrack(int[] var1) {
      int var2 = DataHandleUtils.getMsbLsbResult(var1[7], var1[8]);
      if (var2 > 60000) {
         GeneralParkData.trackAngle = ('\uffff' - var2) / 200 + 1;
      } else {
         GeneralParkData.trackAngle = -(var2 / 200) + 1;
      }

      this.updateParkUi((Bundle)null, this.context);
   }

   public void ShareCanInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportAllCanBusData(this.intArrayToJsonStr(var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      int[] var3 = this.getByteArrayToIntArray(var2);
      this.mCanBusInfoInt = var3;
      switch (this.getMsDataType(var3)) {
         case 160:
            this.setTrack(this.mCanBusInfoInt);
            break;
         case 293:
            this.set0x125(this.mCanBusInfoInt);
            break;
         case 367:
            this.set0x16F(this.mCanBusInfoInt);
            break;
         case 371:
            this.set0x173(this.mCanBusInfoInt);
            break;
         case 458:
            this.set0x1CA(this.mCanBusInfoInt);
            break;
         case 529:
            CanbusInfoChangeListener.getInstance().reportMsBasicInfo(this.intArrayToJsonStr(this.mCanBusInfoInt));
            break;
         case 534:
            this.set0x216(this.mCanBusInfoInt);
            break;
         case 535:
            this.set0x217(this.mCanBusInfoInt);
            break;
         case 565:
            this.set0x235(this.mCanBusInfoInt);
            break;
         case 567:
            this.setSWC(this.mCanBusInfoInt);
            break;
         case 668:
            this.set0x29C(this.mCanBusInfoInt);
            break;
         case 908:
            this.set0x38C(this.mCanBusInfoInt);
            break;
         case 931:
            this.ShareCanInfo(this.mCanBusInfoInt);
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.context = var1;
      CanbusMsgSender.sendMsg(new byte[]{33, 3, 35, -1, -1, 0, 0, 0, 0, 0, 0});
      WmCarData.tire_unit = SharePreUtil.getStringValue(var1, "key.tire.unit", "PSI");
      this.getUiMgr(var1).registerCanBusAirControlListener();
      this.initDPHJ(var1);
   }

   public void linInfoChange(Context var1, byte[] var2) {
      super.linInfoChange(var1, var2);
      if (var2[2] == 32) {
         this.setLinBus0x20(var2);
      }

   }

   public void onAccOn() {
      super.onAccOn();
      this.initDPHJ(this.context);
   }

   public void onHandshake(Context var1) {
      super.onHandshake(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 0, 0, 0, 0, 1});
      this.initDPHJ(var1);
   }
}
