package com.hzbhd.canbus.car._394;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int a = 0;
   int i = 0;
   Context mContext;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendMsg((byte)5);
            }
         } else {
            this.this$0.SendMsg((byte)30);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SendMsg((byte)1);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SendMsg((byte)7);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendMsg((byte)4);
            }
         } else {
            this.this$0.SendMsg((byte)2);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendMsg((byte)14);
      }

      public void onClickUp() {
         this.this$0.SendMsg((byte)13);
      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendMsg((byte)16);
      }

      public void onClickUp() {
         this.this$0.SendMsg((byte)15);
      }
   };
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_1193_setting_4_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -116, 8, (byte)var3});
            }

         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            byte var5;
            byte var6;
            String var7;
            byte var9;
            label271: {
               var7 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
               var7.hashCode();
               int var4 = var7.hashCode();
               var6 = 6;
               var5 = -1;
               switch (var4) {
                  case -1658358948:
                     if (var7.equals("_1193_setting_1")) {
                        var9 = 0;
                        break label271;
                     }
                     break;
                  case -1658358947:
                     if (var7.equals("_1193_setting_2")) {
                        var9 = 1;
                        break label271;
                     }
                     break;
                  case -1658358946:
                     if (var7.equals("_1193_setting_3")) {
                        var9 = 2;
                        break label271;
                     }
                     break;
                  case -1658358945:
                     if (var7.equals("_1193_setting_4")) {
                        var9 = 3;
                        break label271;
                     }
                     break;
                  case -11891515:
                     if (var7.equals("car_type")) {
                        var9 = 4;
                        break label271;
                     }
                     break;
                  case 1731424148:
                     if (var7.equals("_185_Original_vehicle_screen_status")) {
                        var9 = 5;
                        break label271;
                     }
                     break;
                  case 2059086768:
                     if (var7.equals("_394_dvr")) {
                        var9 = 6;
                        break label271;
                     }
               }

               var9 = -1;
            }

            byte var8;
            UiMgr var10;
            switch (var9) {
               case 0:
                  label254: {
                     var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var7.hashCode();
                     switch (var7) {
                        case "_1193_setting_1_0":
                           var8 = 0;
                           break label254;
                        case "_1193_setting_1_1":
                           var8 = 1;
                           break label254;
                        case "_1193_setting_1_2":
                           var8 = 2;
                           break label254;
                        case "_1193_setting_1_3":
                           var8 = 3;
                           break label254;
                        case "_1193_setting_1_4":
                           var8 = 4;
                           break label254;
                        case "_1193_setting_1_6":
                           var8 = 5;
                           break label254;
                        case "_1193_setting_1_7":
                           var8 = var6;
                        case "_1193_setting_1_8":
                           var8 = 7;
                           break label254;
                        case "_1193_setting_1_9":
                           var8 = 8;
                           break label254;
                        case "_1193_setting_1_A":
                           var8 = 9;
                           break label254;
                        case "_185_setting_0":
                           var8 = 10;
                           break label254;
                        case "_1193_setting_bu_0":
                           var8 = 11;
                           break label254;
                        case "_1193_setting_bu_1":
                           var8 = 12;
                           break label254;
                        case "_1193_setting_bu_2":
                           var8 = 13;
                           break label254;
                        case "_1193_setting_bu_3":
                           var8 = 14;
                           break label254;
                     }

                     var8 = -1;
                  }

                  switch (var8) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 17, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 1, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 4, (byte)var3});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 13, (byte)var3});
                        return;
                     case 4:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 12, (byte)var3});
                        return;
                     case 5:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 15, (byte)var3});
                        return;
                     case 6:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 16, (byte)var3});
                        return;
                     case 7:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 14, (byte)var3});
                        return;
                     case 8:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 19, (byte)var3});
                        return;
                     case 9:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 20, (byte)var3});
                        return;
                     case 10:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 2, (byte)var3});
                        return;
                     case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 21, (byte)var3});
                        return;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 22, (byte)var3});
                        return;
                     case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 23, (byte)var3});
                        return;
                     case 14:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 24, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 1:
                  var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var7.hashCode();
                  if (!var7.equals("_1193_setting_2_0")) {
                     if (var7.equals("_1193_setting_2_1")) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 6, (byte)var3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -116, 5, (byte)var3});
                  }
                  break;
               case 2:
                  var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var7.hashCode();
                  switch (var7.hashCode()) {
                     case -1526347264:
                        if (!var7.equals("_394_p")) {
                           var9 = var5;
                        } else {
                           var9 = 0;
                        }
                        break;
                     case -250077297:
                        if (!var7.equals("_1193_setting_3_0")) {
                           var9 = var5;
                        } else {
                           var9 = 1;
                        }
                        break;
                     case -250077296:
                        if (!var7.equals("_1193_setting_3_1")) {
                           var9 = var5;
                        } else {
                           var9 = 2;
                        }
                        break;
                     case -250077295:
                        if (!var7.equals("_1193_setting_3_2")) {
                           var9 = var5;
                        } else {
                           var9 = 3;
                        }
                        break;
                     default:
                        var9 = var5;
                  }

                  switch (var9) {
                     case 0:
                        if (var3 == 0) {
                           var10 = this.this$0;
                           var10.getMsgMgr(var10.mContext).hideP360Button();
                        }

                        if (var3 == 1) {
                           var10 = this.this$0;
                           var10.getMsgMgr(var10.mContext).showP360Button();
                        }

                        var10 = this.this$0;
                        var10.getMsgMgr(var10.mContext).updateSetting(var1, var2, var3);
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 9, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 10, (byte)var3});
                        return;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 11, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 3:
                  var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var7.hashCode();
                  switch (var7.hashCode()) {
                     case -250080180:
                        if (!var7.equals("_1193_setting_0_0")) {
                           var8 = var5;
                        } else {
                           var8 = 0;
                        }
                        break;
                     case -250076336:
                        if (!var7.equals("_1193_setting_4_0")) {
                           var8 = var5;
                        } else {
                           var8 = 1;
                        }
                        break;
                     case -250076335:
                        if (!var7.equals("_1193_setting_4_1")) {
                           var8 = var5;
                        } else {
                           var8 = 2;
                        }
                        break;
                     default:
                        var8 = var5;
                  }

                  switch (var8) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)((byte)var3 + 1)});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 7, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 8, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 5:
                  var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var7.hashCode();
                  switch (var7.hashCode()) {
                     case -1826083015:
                        if (!var7.equals("_185_Right_camera_status")) {
                           var9 = var5;
                        } else {
                           var9 = 0;
                        }
                        break;
                     case -1547947964:
                        if (!var7.equals("_185_Left_camera_status")) {
                           var9 = var5;
                        } else {
                           var9 = 1;
                        }
                        break;
                     case -1492733818:
                        if (!var7.equals("_185_Front_camera_status")) {
                           var9 = var5;
                        } else {
                           var9 = 2;
                        }
                        break;
                     case 597119777:
                        if (!var7.equals("_185_Rear_camera_status")) {
                           var9 = var5;
                        } else {
                           var9 = 3;
                        }
                        break;
                     default:
                        var9 = var5;
                  }

                  switch (var9) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 7, (byte)((byte)var3 + 1)});
                        break;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 6, (byte)var3});
                        break;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 4, (byte)var3});
                        break;
                     case 3:
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 5, (byte)var3});
                  }
               case 4:
                  var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var7.hashCode();
                  if (var7.equals("car_type")) {
                     if (var3 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 36, 18, 53});
                     }

                     if (var3 == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 36, 11, 53});
                     }

                     if (var3 == 2) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 36, 14, 53});
                     }

                     if (var3 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 36, 19, 53});
                     }

                     if (var3 == 4) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 36, 20, 53});
                     }

                     if (var3 == 5) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 36, 21, 53});
                     }

                     if (var3 == 6) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 36, 22, 53});
                     }
                  }
                  break;
               case 6:
                  var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                  var7.hashCode();
                  if (var7.equals("_394_dvr_switch")) {
                     if (var3 == 0) {
                        var10 = this.this$0;
                        var10.getMsgMgr(var10.mContext).hideDvrButton();
                     }

                     if (var3 == 1) {
                        var10 = this.this$0;
                        var10.getMsgMgr(var10.mContext).showDvrButton();
                     }

                     var10 = this.this$0;
                     var10.getMsgMgr(var10.mContext).updateSetting(var1, var2, var3);
                  }
            }

         }
      });
      var2.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(135);
         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            var3.hashCode();
            if (var3.equals("_1193_setting_1")) {
               var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var3.hashCode();
               if (var3.equals("_1193_setting_1_EEE")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -116, 18, 1});
               }
            }

         }
      });
      ParkPageUiSet var3 = this.getParkPageUiSet(this.mContext);
      var3.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               UiMgr var2 = this.this$0;
               var2.getMsgMgr(var2.mContext).toast("Not Supported");
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 3});
            }

         }
      });
      var3.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onTouchItem(MotionEvent var1) {
            if (var1.getAction() == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte)this.this$0.getMsb((int)var1.getX()), (byte)this.this$0.getLsb((int)var1.getX()), (byte)this.this$0.getMsb((int)var1.getY()), (byte)this.this$0.getLsb((int)var1.getY()), 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 44, 0, (byte)this.this$0.getMsb((int)var1.getX()), (byte)this.this$0.getLsb((int)var1.getX()), (byte)this.this$0.getMsb((int)var1.getY()), (byte)this.this$0.getLsb((int)var1.getY()), 0});
            }

         }
      });
      AirPageUiSet var4 = this.getAirUiSet(this.mContext);
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.SendMsg((byte)12);
         }

         public void onClickRight() {
            this.this$0.SendMsg((byte)11);
         }
      });
      var4.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            if (this.this$0.a == 0) {
               this.this$0.SendMsg((byte)25);
               this.this$0.a = 1;
            } else if (this.this$0.a == 1) {
               this.this$0.SendMsg((byte)26);
               this.this$0.a = 2;
            } else if (this.this$0.a == 2) {
               this.this$0.SendMsg((byte)27);
               this.this$0.a = 3;
            } else if (this.this$0.a == 3) {
               this.this$0.SendMsg((byte)28);
               this.this$0.a = 0;
            }

         }

         public void onRightSeatClick() {
            if (this.this$0.a == 0) {
               this.this$0.SendMsg((byte)25);
               this.this$0.a = 1;
            } else if (this.this$0.a == 1) {
               this.this$0.SendMsg((byte)26);
               this.this$0.a = 2;
            } else if (this.this$0.a == 2) {
               this.this$0.SendMsg((byte)27);
               this.this$0.a = 3;
            } else if (this.this$0.a == 3) {
               this.this$0.SendMsg((byte)28);
               this.this$0.a = 0;
            }

         }
      });
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
      var4.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(49);
         }
      });
   }

   private void SendMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
   }

   private int getLsb(int var1) {
      return (var1 & '\uffff') >> 0 & 255;
   }

   private int getMsb(int var1) {
      return (var1 & '\uffff') >> 8 & 255;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   public void activeRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
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

   public void sendSourceInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
