package com.hzbhd.canbus.car._176;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   public int currentClickFront;
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   public Context mContext;
   MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontBottom[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontRight[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontLeft[var1]);
      }
   };
   OnAirSeatClickListener mOnAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         this.this$0.setFrontSearClick();
      }

      public void onRightSeatClick() {
         this.this$0.setFrontSearClick();
      }
   };
   OnConfirmDialogListener mOnConfirmDialogListener = new OnConfirmDialogListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onConfirmClick(int var1, int var2) {
         if (var1 == 1) {
            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 1});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 2});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 4});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 5});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 6});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 7});
            }
         }

      }
   };
   OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 != 2) {
               if (var1 == 4) {
                  if (var2 != 2) {
                     if (var2 != 3) {
                        if (var2 == 4) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, (byte)var3});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, (byte)var3});
                  }
               }
            } else if (var2 == 11) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
            }
         } else if (var2 == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
         }

      }
   };
   OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 == 4) {
                        if (var2 != 0) {
                           if (var2 == 1) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, (byte)var3});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, (byte)var3});
                        }
                     }
                  } else if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 != 2) {
                           if (var2 == 3) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -125, -109, (byte)(MsgMgr.ONE + var3)});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, -110, (byte)(MsgMgr.ONE + var3)});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, -111, (byte)(MsgMgr.ONE + var3)});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -112, (byte)var3});
                  }
               } else {
                  switch (var2) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte)var3});
                        break;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte)var3});
                        break;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
                        break;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var3});
                        break;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var3});
                        break;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var3});
                        break;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var3});
                        break;
                     case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte)var3});
                        break;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var3});
                        break;
                     case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
                        break;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)(MsgMgr.ONE + var3)});
                     case 11:
                     default:
                        break;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)var3});
                        break;
                     case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)(MsgMgr.ONE + var3)});
                        break;
                     case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte)(MsgMgr.ONE + var3)});
                        break;
                     case 15:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte)var3});
                        break;
                     case 16:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte)var3});
                        break;
                     case 17:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)(MsgMgr.ONE + var3)});
                        break;
                     case 18:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)var3});
                        break;
                     case 19:
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)var3});
                  }
               }
            } else {
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 1});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 2});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 3});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 4});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 5});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 6});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 7});
               }
            }
         } else {
            switch (var2) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)(MsgMgr.ONE + var3)});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)(MsgMgr.ONE + var3)});
               case 12:
               default:
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)(MsgMgr.ONE + var3)});
                  break;
               case 14:
                  if (this.this$0.getCurrentCarId() != 11 && this.this$0.getCurrentCarId() != 12) {
                     if (var3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, -127});
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                     }
                  } else {
                     switch (var3) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
                           break;
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)(var3 + 2)});
                           break;
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)(var3 + 3)});
                           break;
                        case 35:
                        case 36:
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)(var3 + 4)});
                           break;
                        case 37:
                           CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, -127});
                     }
                  }
            }
         }

         UiMgr var4 = this.this$0;
         if (var1 == var4.getLeftIndexes(var4.mContext, "_176_add_function")) {
            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_language_title")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function2")) {
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, 0});
               } else if (var3 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, 6});
               } else if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, 7});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function3")) {
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, 0});
               } else if (var3 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, 6});
               } else if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, 7});
               }
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function33")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 47, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function36")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 45, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function39")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 46, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function43")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function44")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 48, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getRightIndex(var4.mContext, "_176_add_function", "_176_add_function45")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 49, (byte)(var3 + 1)});
            }
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(14);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (this.this$0.getEachId() == 1) {
            UiMgr var1 = this.this$0;
            var1.getMsgMgr(var1.mContext).toast("Original car not support!");
         } else {
            this.this$0.sendAirCommand(16);
         }

      }

      public void onClickUp() {
         if (this.this$0.getEachId() == 1) {
            UiMgr var1 = this.this$0;
            var1.getMsgMgr(var1.mContext).toast("Original car not support!");
         } else {
            this.this$0.sendAirCommand(15);
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mSetWindSpeedView = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (GeneralAirData.front_wind_level == 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, 8});
         } else if (GeneralAirData.front_wind_level > MsgMgr.WIND_LEVEL_LOW) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte)(GeneralAirData.front_wind_level - 1)});
         }

      }

      public void onClickRight() {
         if (GeneralAirData.front_wind_level < MsgMgr.WIND_LEVEL_HIGH) {
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 18, (byte)(GeneralAirData.front_wind_level + 1)});
         }

      }
   };
   OnPanoramicItemClickListener onPanoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -80, (byte)(var1 + 1)});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var3 = this.getAirUiSet(var1);
      String[][] var2 = var3.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var2[0];
      this.mAirBtnListFrontLeft = var2[1];
      this.mAirBtnListFrontRight = var2[2];
      this.mAirBtnListFrontBottom = var2[3];
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedView);
      var3.getFrontArea().setOnAirSeatClickListener(this.mOnAirSeatClickListener);
      SettingPageUiSet var4 = this.getSettingUiSet(var1);
      var4.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var4.setOnSettingConfirmDialogListener(this.mOnConfirmDialogListener);
      var4.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      var4.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_176_Request_vehicle_information")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 1});
            }

         }
      });
      var4.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var4) {
         public String leftTitle;
         public String rightTitle;
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onSwitch(int var1, int var2, int var3) {
            this.leftTitle = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            this.rightTitle = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            String var4 = this.leftTitle;
            var4.hashCode();
            if (var4.equals("_176_setting_0")) {
               var4 = this.rightTitle;
               var4.hashCode();
               switch (var4) {
                  case "_176_setting_0_0":
                     this.this$0.sendSettingSwitchInfo(43, var3);
                     break;
                  case "_176_setting_0_1":
                     this.this$0.sendSettingSwitchInfo(42, var3);
                     break;
                  case "_176_setting_0_2":
                     this.this$0.sendSettingSwitchInfo(41, var3);
                     break;
                  case "_176_setting_0_3":
                     this.this$0.sendSettingSwitchInfo(40, var3);
                     break;
                  case "_176_setting_0_4":
                     this.this$0.sendSettingSwitchInfo(39, var3);
                     break;
                  case "_176_setting_0_5":
                     this.this$0.sendSettingSwitchInfo(38, var3);
                     break;
                  case "_176_setting_0_6":
                     this.this$0.sendSettingSwitchInfo(37, var3);
               }
            }

         }
      });
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendAirCommand(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var2, 0});
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case -1039745817:
            if (var1.equals("normal")) {
               var2 = 1;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 2;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 3;
            }
            break;
         case -424438238:
            if (var1.equals("blow_negative")) {
               var2 = 4;
            }
            break;
         case -54617514:
            if (var1.equals("auto_cycle")) {
               var2 = 5;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 6;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 7;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 8;
            }
            break;
         case 3135580:
            if (var1.equals("fast")) {
               var2 = 9;
            }
            break;
         case 3535914:
            if (var1.equals("soft")) {
               var2 = 10;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 11;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 12;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(6);
            break;
         case 1:
            this.sendAirCommand(27);
            break;
         case 2:
            this.sendAirCommand(23);
            break;
         case 3:
            this.sendAirCommand(28);
            break;
         case 4:
            this.sendAirCommand(29);
            break;
         case 5:
            this.sendAirCommand(22);
            break;
         case 6:
            this.sendAirCommand(1);
            break;
         case 7:
            this.sendAirCommand(2);
            break;
         case 8:
            this.sendAirCommand(3);
            break;
         case 9:
            this.sendAirCommand(26);
            break;
         case 10:
            this.sendAirCommand(25);
            break;
         case 11:
            this.sendAirCommand(0);
            break;
         case 12:
            this.sendAirCommand(21);
      }

   }

   private void setFrontSearClick() {
      if (GeneralAirData.front_right_blow_window) {
         this.currentClickFront = 3;
         if (GeneralAirData.front_right_blow_foot) {
            this.currentClickFront = 4;
            if (GeneralAirData.front_right_blow_head) {
               this.currentClickFront = 5;
            }
         }
      }

      if (!GeneralAirData.front_right_blow_window) {
         this.currentClickFront = 2;
         if (!GeneralAirData.front_right_blow_foot) {
            this.currentClickFront = 1;
            if (!GeneralAirData.front_right_blow_head) {
               this.currentClickFront = 0;
            }
         }
      }

      label61: {
         int var1 = this.currentClickFront;
         if (var1 != 0) {
            if (var1 == 1) {
               break label61;
            }

            if (var1 == 2 || var1 == 3) {
               this.sendAirCommand(24);
               return;
            }

            if (var1 == 4) {
               break label61;
            }

            if (var1 != 5) {
               return;
            }
         }

         this.sendAirCommand(7);
         return;
      }

      this.sendAirCommand(9);
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var5 = var6.iterator();

         while(var5.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

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

   protected int getLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getRightIndex(Context var1, String var2, String var3) {
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

   void sendSettingSwitchInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      switch (this.getCurrentCarId()) {
         case 0:
            this.removeMainPrjBtnByName(var1, "air");
            this.removeMainPrjBtnByName(var1, "tire_info");
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33_1"});
            this.removeSettingLeftItem(var1, 2, 0);
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
            break;
         case 1:
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeMainPrjBtnByName(var1, "setting");
            this.removeMainPrjBtnByName(var1, "drive_data");
            this.removeMainPrjBtnByName(var1, "tire_info");
            this.removeFrontAirButton(var1, 1, 1, 0);
            this.removeFrontAirButton(var1, 2, 1, 0);
            this.removeFrontAirButton(var1, 0, 4, 0);
            this.removeFrontAirButton(var1, 3, 4, 0);
            this.removeFrontAirButton(var1, 3, 3, 0);
            this.removeFrontAirButton(var1, 3, 2, 0);
            this.removeFrontAirButton(var1, 3, 1, 0);
            break;
         case 2:
         case 4:
         case 9:
            this.removeMainPrjBtnByName(var1, "tire_info");
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33_1"});
            this.removeSettingLeftItem(var1, 2, 0);
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
            this.removeFrontAirButton(var1, 3, 3, 0);
            break;
         case 3:
            this.removeMainPrjBtnByName(var1, "air");
            this.removeMainPrjBtnByName(var1, "setting");
            this.removeMainPrjBtnByName(var1, "tire_info");
            this.removeMainPrjBtnByName(var1, "drive_data");
            break;
         case 5:
         case 10:
            this.removeMainPrjBtnByName(var1, "tire_info");
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33_1"});
            this.removeSettingLeftItem(var1, 2, 0);
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
            this.removeFrontAirButton(var1, 1, 1, 0);
            this.removeFrontAirButton(var1, 2, 1, 0);
            this.removeFrontAirButton(var1, 3, 3, 0);
            break;
         case 6:
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33_1"});
            this.removeFrontAirButton(var1, 1, 1, 0);
            this.removeFrontAirButton(var1, 2, 1, 0);
            this.removeSettingLeftItem(var1, 3, 3);
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_41", "_176_car_setting_title_42"});
            break;
         case 7:
            this.removeMainPrjBtnByName(var1, "air");
            this.removeMainPrjBtnByName(var1, "tire_info");
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33_1"});
            this.removeSettingLeftItemByNameList(var1, new String[]{"_176_car_setting_title_big_2", "_176_car_setting_title_44", "_176_car_setting_title_48"});
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_37", "_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_6", "_176_car_setting_title_8", "_176_car_setting_title_9", "_176_car_setting_title_10", "_176_car_setting_title_11", "_176_car_setting_title_12", "_176_car_setting_title_13", "_176_car_setting_title_14", "_176_car_setting_title_15", "_176_car_setting_title_16", "_176_car_setting_title_27", "_176_car_setting_title_28", "_176_car_setting_title_35", "_176_car_setting_title_33"});
            break;
         case 8:
            this.removeMainPrjBtnByName(var1, "tire_info");
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeFrontAirButton(var1, 0, 4, 0);
            this.removeFrontAirButton(var1, 1, 1, 0);
            this.removeFrontAirButton(var1, 2, 1, 0);
            this.removeFrontAirButton(var1, 3, 4, 0);
            this.removeFrontAirButton(var1, 3, 3, 0);
            this.removeFrontAirButton(var1, 3, 2, 0);
            this.removeFrontAirButton(var1, 3, 1, 0);
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33_1"});
            this.removeSettingLeftItemByNameList(var1, new String[]{"_176_car_setting_title_big_2", "_176_car_setting_title_44", "_176_car_setting_title_48"});
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_37", "_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_6", "_176_car_setting_title_8", "_176_car_setting_title_9", "_176_car_setting_title_10", "_176_car_setting_title_11", "_176_car_setting_title_12", "_176_car_setting_title_13", "_176_car_setting_title_14", "_176_car_setting_title_15", "_176_car_setting_title_16", "_176_car_setting_title_27", "_176_car_setting_title_28", "_176_car_setting_title_35", "_176_car_setting_title_33"});
            break;
         case 11:
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33"});
            this.removeFrontAirButton(var1, 1, 1, 0);
            this.removeFrontAirButton(var1, 2, 1, 0);
            break;
         case 12:
            this.removeMainPrjBtnByName(var1, "original_car_device");
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_33"});
            this.removeSettingLeftItem(var1, 2, 0);
            this.removeSettingRightItemByNameList(var1, new String[]{"_176_car_setting_title_38", "_176_car_setting_title_39", "_176_car_setting_title_40", "_176_car_setting_title_41", "_176_car_setting_title_42"});
            this.removeFrontAirButton(var1, 0, 4, 0);
            this.removeFrontAirButton(var1, 3, 4, 0);
            this.removeFrontAirButton(var1, 3, 3, 0);
            this.removeFrontAirButton(var1, 3, 2, 0);
            this.removeFrontAirButton(var1, 3, 1, 0);
      }

   }
}
