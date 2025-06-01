package com.hzbhd.canbus.car._260;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UiMgr extends AbstractUiMgr {
   public boolean haveProblem = true;
   public int[] index;
   public String leftTitle;
   private Context mContext;
   private OnAirBtnClickListener mOnAirBottomBtnClickListener = new UiMgr$$ExternalSyntheticLambda2(this);
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new UiMgr$$ExternalSyntheticLambda1(this);
   private OnAirBtnClickListener mOnAirTopBtnClickListener = new UiMgr$$ExternalSyntheticLambda0(this);
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendMsg((byte)28);
      }

      public void onClickRight() {
         this.this$0.sendMsg((byte)29);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         String var3 = GeneralAirData.center_wheel;
         var3.hashCode();
         int var2 = var3.hashCode();
         byte var1 = -1;
         switch (var2) {
            case -1955878649:
               if (var3.equals("Normal")) {
                  var1 = 0;
               }
               break;
            case 0:
               if (var3.equals("")) {
                  var1 = 1;
               }
               break;
            case 2182268:
               if (var3.equals("Fast")) {
                  var1 = 2;
               }
               break;
            case 2582602:
               if (var3.equals("Soft")) {
                  var1 = 3;
               }
         }

         switch (var1) {
            case 0:
               this.this$0.sendMsg((byte)66);
               break;
            case 1:
               this.this$0.sendMsg((byte)65);
               break;
            case 2:
               this.this$0.sendMsg((byte)64);
               break;
            case 3:
               this.this$0.sendMsg((byte)65);
         }

      }

      public void onClickUp() {
         String var3 = GeneralAirData.center_wheel;
         var3.hashCode();
         int var2 = var3.hashCode();
         byte var1 = -1;
         switch (var2) {
            case -1955878649:
               if (var3.equals("Normal")) {
                  var1 = 0;
               }
               break;
            case 0:
               if (var3.equals("")) {
                  var1 = 1;
               }
               break;
            case 2182268:
               if (var3.equals("Fast")) {
                  var1 = 2;
               }
               break;
            case 2582602:
               if (var3.equals("Soft")) {
                  var1 = 3;
               }
         }

         switch (var1) {
            case 0:
               this.this$0.sendMsg((byte)64);
               break;
            case 1:
               this.this$0.sendMsg((byte)65);
               break;
            case 2:
               this.this$0.sendMsg((byte)65);
               break;
            case 3:
               this.this$0.sendMsg((byte)66);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendMsg((byte)30);
      }

      public void onClickUp() {
         this.this$0.sendMsg((byte)31);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendMsg((byte)32);
      }

      public void onClickUp() {
         this.this$0.sendMsg((byte)33);
      }
   };
   public String rightTitle;
   public HashMap settingPageIndex;
   public SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      this.settingPageUiSet = var3;
      var3.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda3(this));
      this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this) {
         public String leftTitle;
         public String rightTitle;
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onSwitch(int var1, int var2, int var3) {
            byte var4;
            String var5;
            byte var6;
            byte var7;
            label182: {
               this.leftTitle = ((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getTitleSrn();
               this.rightTitle = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var5 = this.leftTitle;
               var5.hashCode();
               var1 = var5.hashCode();
               var4 = 4;
               var7 = 3;
               switch (var1) {
                  case -2019177410:
                     if (var5.equals("_260_settingInfo_1")) {
                        var6 = 0;
                        break label182;
                     }
                     break;
                  case -2019177409:
                     if (var5.equals("_260_settingInfo_2")) {
                        var6 = 1;
                        break label182;
                     }
                     break;
                  case -2019177408:
                     if (var5.equals("_260_settingInfo_3")) {
                        var6 = 2;
                        break label182;
                     }
                     break;
                  case -2019177407:
                     if (var5.equals("_260_settingInfo_4")) {
                        var6 = 3;
                        break label182;
                     }
                     break;
                  case -2019177406:
                     if (var5.equals("_260_settingInfo_5")) {
                        var6 = 4;
                        break label182;
                     }
               }

               var6 = -1;
            }

            switch (var6) {
               case 0:
                  label167: {
                     var5 = this.rightTitle;
                     var5.hashCode();
                     switch (var5) {
                        case "_260_settingInfo_1_1":
                           var6 = 0;
                           break label167;
                        case "_260_settingInfo_1_2":
                           var6 = 1;
                           break label167;
                        case "_260_settingInfo_1_3":
                           var6 = 2;
                           break label167;
                        case "_260_settingInfo_1_4":
                           var6 = 3;
                           break label167;
                        case "_260_settingInfo_1_5":
                           var6 = var4;
                     }

                     var6 = -1;
                  }

                  switch (var6) {
                     case 0:
                        this.this$0.sendSettingData(33, var3);
                        return;
                     case 1:
                        this.this$0.sendSettingData(34, var3);
                        return;
                     case 2:
                        this.this$0.sendSettingData(35, var3);
                        return;
                     case 3:
                        this.this$0.sendSettingData(36, var3);
                        return;
                     case 4:
                        this.this$0.sendSettingData(37, var3);
                        return;
                     default:
                        return;
                  }
               case 1:
                  label156: {
                     var5 = this.rightTitle;
                     var5.hashCode();
                     switch (var5) {
                        case "_260_settingInfo_2_2":
                           var6 = 0;
                           break label156;
                        case "_260_settingInfo_2_3":
                           var6 = 1;
                           break label156;
                        case "_260_settingInfo_2_4":
                           var6 = 2;
                           break label156;
                        case "_260_settingInfo_2_5":
                           var6 = var7;
                     }

                     var6 = -1;
                  }

                  switch (var6) {
                     case 0:
                        this.this$0.sendSettingData(16, var3);
                        return;
                     case 1:
                        this.this$0.sendSettingData(17, var3);
                        return;
                     case 2:
                        this.this$0.sendSettingData(18, var3);
                        return;
                     case 3:
                        this.this$0.sendSettingData(19, var3);
                        return;
                     default:
                        return;
                  }
               case 2:
                  label146: {
                     var5 = this.rightTitle;
                     var5.hashCode();
                     switch (var5) {
                        case "_260_settingInfo_3_0":
                           var6 = 0;
                           break label146;
                        case "_260_settingInfo_3_1":
                           var6 = 1;
                           break label146;
                        case "_260_settingInfo_3_2":
                           var6 = 2;
                           break label146;
                        case "_260_settingInfo_3_3":
                           var6 = 3;
                           break label146;
                        case "_260_settingInfo_3_4":
                           var6 = var4;
                     }

                     var6 = -1;
                  }

                  switch (var6) {
                     case 0:
                        this.this$0.sendSettingData(20, var3);
                        return;
                     case 1:
                        this.this$0.sendSettingData(21, var3);
                        return;
                     case 2:
                        this.this$0.sendSettingData(38, var3);
                        return;
                     case 3:
                        this.this$0.sendSettingData(39, var3);
                        return;
                     case 4:
                        this.this$0.sendSettingData(40, var3);
                        return;
                     default:
                        return;
                  }
               case 3:
                  label135: {
                     var5 = this.rightTitle;
                     var5.hashCode();
                     switch (var5) {
                        case "_260_settingInfo_4_0":
                           var6 = 0;
                           break label135;
                        case "_260_settingInfo_4_1":
                           var6 = 1;
                           break label135;
                        case "_260_settingInfo_4_2":
                           var6 = 2;
                           break label135;
                        case "_260_settingInfo_4_3":
                           var6 = var7;
                     }

                     var6 = -1;
                  }

                  switch (var6) {
                     case 0:
                        this.this$0.sendSettingData(41, var3);
                        return;
                     case 1:
                        this.this$0.sendSettingData(42, var3);
                        return;
                     case 2:
                        this.this$0.sendSettingData(43, var3);
                        return;
                     case 3:
                        this.this$0.sendSettingData(44, var3);
                        return;
                     default:
                        return;
                  }
               case 4:
                  var5 = this.rightTitle;
                  var5.hashCode();
                  if (!var5.equals("_260_settingInfo_5_0")) {
                     if (var5.equals("_260_settingInfo_5_1")) {
                        this.this$0.sendSettingData(49, var3);
                     }
                  } else {
                     this.this$0.sendSettingData(48, var3);
                  }
            }

         }
      });
      AirPageUiSet var4 = this.getAirUiSet(var1);
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, null, this.mOnAirBottomBtnClickListener});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, this.mTempSetViewOnUpDownClickListenerCenter, this.mTempSetViewOnUpDownClickListenerRight});
      var4.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendMsg((byte)25);
         }

         public void onRightSeatClick() {
            this.this$0.sendMsg((byte)25);
         }
      });
      if (this.haveProblem) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_5_2"});
      }

      int var2 = this.getCurrentCarId();
      if (var2 != 2) {
         this.removeDriveData(var1, "_260_driveInfo_1");
      }

      if (var2 == 1 || var2 == 2) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_0_1"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_260_settingInfo_1"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_260_settingInfo_2"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_260_settingInfo_3"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_260_settingInfo_4"});
         this.removeSettingLeftItemByNameList(var1, new String[]{"_260_settingInfo_5"});
      }

      if (var2 == 4 || var2 == 5 || var2 == 6) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_0_0"});
      }

      if (var2 == 3) {
         this.removeMainPrjBtnByName(var1, "setting");
      }

      if (var2 != 1 && var2 != 2) {
         this.removeMainPrjBtnByName(var1, "air");
      }

      if (var2 != 5 && var2 != 6) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_2_5"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_3_0"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_3_1"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_1_0"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_1_2"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_1_3"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_1_4"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_3_3"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_260_settingInfo_4_1"});
      }

      this.settingPageIndex = new HashMap();
      this.initSettingPageIndex();
      this.settingPageIndex.get("_260_settingInfo_0_0");
   }

   private void sendAirConditionData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1});
   }

   private void sendMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, 0});
   }

   private void sendSettingData(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2});
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
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

   public Map initSettingPageIndex() {
      List var4 = this.settingPageUiSet.getList();
      Iterator var3 = var4.iterator();

      for(int var1 = 0; var1 < var4.size(); ++var1) {
         SettingPageUiSet.ListBean var5 = (SettingPageUiSet.ListBean)var3.next();
         this.settingPageIndex.put(var5.getTitleSrn(), var1);
         List var8 = var5.getItemList();
         Iterator var7 = var8.iterator();

         for(int var2 = 0; var2 < var8.size(); ++var2) {
            SettingPageUiSet.ListBean.ItemListBean var6 = (SettingPageUiSet.ListBean.ItemListBean)var7.next();
            this.settingPageIndex.put(var6.getTitleSrn(), var2);
         }
      }

      return this.settingPageIndex;
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__260_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.sendMsg((byte)23);
            }
         } else {
            this.sendMsg((byte)21);
         }
      } else {
         this.sendMsg((byte)22);
      }

   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__260_UiMgr(int var1) {
      this.sendMsg((byte)17);
   }

   // $FF: synthetic method
   void lambda$new$2$com_hzbhd_canbus_car__260_UiMgr(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.sendMsg((byte)34);
            }
         } else {
            this.sendMsg((byte)19);
         }
      } else {
         this.sendMsg((byte)20);
      }

   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__260_UiMgr(int var1, int var2, int var3) {
      this.leftTitle = ((SettingPageUiSet.ListBean)this.settingPageUiSet.getList().get(var1)).getTitleSrn();
      this.rightTitle = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      String var4 = this.leftTitle;
      var4.hashCode();
      var2 = var4.hashCode();
      byte var5 = -1;
      switch (var2) {
         case -2019177411:
            if (var4.equals("_260_settingInfo_0")) {
               var5 = 0;
            }
            break;
         case -2019177410:
            if (var4.equals("_260_settingInfo_1")) {
               var5 = 1;
            }
            break;
         case -2019177409:
            if (var4.equals("_260_settingInfo_2")) {
               var5 = 2;
            }
         case -2019177408:
         case -2019177407:
         default:
            break;
         case -2019177406:
            if (var4.equals("_260_settingInfo_5")) {
               var5 = 3;
            }
      }

      switch (var5) {
         case 0:
            var4 = this.rightTitle;
            var4.hashCode();
            if (!var4.equals("_260_settingInfo_0_0")) {
               if (var4.equals("_260_settingInfo_0_1")) {
                  this.sendSettingData(1, var3);
               }
            } else if (var3 != 0) {
               if (var3 == 1) {
                  this.sendAirConditionData(2);
               }
            } else {
               this.sendAirConditionData(0);
            }
            break;
         case 1:
            var4 = this.rightTitle;
            var4.hashCode();
            if (var4.equals("_260_settingInfo_1_0")) {
               this.sendSettingData(32, var3);
            }
            break;
         case 2:
            var4 = this.rightTitle;
            var4.hashCode();
            if (!var4.equals("_260_settingInfo_2_0")) {
               if (var4.equals("_260_settingInfo_2_1")) {
                  this.sendSettingData(23, var3);
               }
            } else {
               this.sendSettingData(22, var3);
            }
            break;
         case 3:
            var4 = this.rightTitle;
            var4.hashCode();
            if (var4.equals("_260_settingInfo_5_2")) {
               this.sendSettingData(64, var3);
            }
      }

   }

   public void sendMediaInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void sendPhoneNumber(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void sendPhoneNumberState(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initSettingPageIndex();
   }
}
