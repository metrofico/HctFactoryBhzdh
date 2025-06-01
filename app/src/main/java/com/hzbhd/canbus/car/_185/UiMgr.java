package com.hzbhd.canbus.car._185;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   String K_LANGUAGE_TAG = "key.language.tag";
   int a = 0;
   int eachId;
   int i = 0;
   Context mContext;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.SendMsg((byte)5);
               }
            } else {
               this.this$0.SendMsg((byte)30);
            }
         } else {
            this.this$0.SendMsg((byte)3);
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
      this.eachId = this.getEachId();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var1, var2) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$settingPageUiSet = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
            String var4 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_1193_setting_4_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -116, 8, (byte)var3});
            }

            var4 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_1193_setting_4")) {
               var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var4.hashCode();
               if (var4.equals("_1193_setting_1_DDD")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -116, 3, (byte)var3});
               }
            }

         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1, var2) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$context = var2;
            this.val$settingPageUiSet = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            byte var4;
            String var6;
            label236: {
               this.this$0.getMsgMgr(this.val$context).updateSetting(var1, var2, var3);
               var6 = ((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getTitleSrn();
               var6.hashCode();
               switch (var6) {
                  case "_1193_setting_1":
                     var4 = 0;
                     break label236;
                  case "_1193_setting_2":
                     var4 = 1;
                     break label236;
                  case "_1193_setting_3":
                     var4 = 2;
                     break label236;
                  case "_1193_setting_4":
                     var4 = 3;
                     break label236;
                  case "_185_Original_vehicle_screen_status":
                     var4 = 4;
                     break label236;
               }

               var4 = -1;
            }

            byte var5 = 5;
            byte var7;
            switch (var4) {
               case 0:
                  label221: {
                     var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var6.hashCode();
                     switch (var6) {
                        case "_1193_setting_1_0":
                           var7 = 0;
                           break label221;
                        case "_1193_setting_1_1":
                           var7 = 1;
                           break label221;
                        case "_1193_setting_1_2":
                           var7 = 2;
                           break label221;
                        case "_1193_setting_1_3":
                           var7 = 3;
                           break label221;
                        case "_1193_setting_1_4":
                           var7 = 4;
                           break label221;
                        case "_1193_setting_1_6":
                           var7 = var5;
                        case "_1193_setting_1_7":
                           var7 = 6;
                           break label221;
                        case "_1193_setting_1_8":
                           var7 = 7;
                           break label221;
                        case "_1193_setting_1_9":
                           var7 = 8;
                           break label221;
                        case "_1193_setting_1_A":
                           var7 = 9;
                           break label221;
                        case "_1193_setting_6_0":
                           var7 = 10;
                           break label221;
                        case "_1193_setting_6_1":
                           var7 = 11;
                           break label221;
                        case "_185_setting_0":
                           var7 = 12;
                           break label221;
                        case "_1193_setting_bu_2":
                           var7 = 13;
                           break label221;
                     }

                     var7 = -1;
                  }

                  switch (var7) {
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
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 27, (byte)var3});
                        return;
                     case 11:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 28, (byte)var3});
                        return;
                     case 12:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 2, (byte)var3});
                        return;
                     case 13:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 23, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 1:
                  label201: {
                     var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var6.hashCode();
                     switch (var6) {
                        case "_1193_setting_2_0":
                           var7 = 0;
                           break label201;
                        case "_1193_setting_2_1":
                           var7 = 1;
                           break label201;
                        case "_1193_setting_2_2":
                           var7 = 2;
                           break label201;
                     }

                     var7 = -1;
                  }

                  switch (var7) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 5, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 6, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 26, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 2:
                  label192: {
                     var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var6.hashCode();
                     switch (var6) {
                        case "_1193_setting_3_0":
                           var7 = 0;
                           break label192;
                        case "_1193_setting_3_1":
                           var7 = 1;
                           break label192;
                        case "_1193_setting_3_2":
                           var7 = 2;
                           break label192;
                     }

                     var7 = -1;
                  }

                  switch (var7) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 9, (byte)var3});
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 10, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 11, (byte)var3});
                        return;
                     default:
                        return;
                  }
               case 3:
                  label183: {
                     var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var6.hashCode();
                     switch (var6) {
                        case "_1193_setting_0_0":
                           var7 = 0;
                           break label183;
                        case "_1193_setting_4_0":
                           var7 = 1;
                           break label183;
                        case "_1193_setting_4_1":
                           var7 = 2;
                           break label183;
                     }

                     var7 = -1;
                  }

                  switch (var7) {
                     case 0:
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)((byte)var3 + 1)});
                        SharePreUtil.setIntValue(this.this$0.mContext, this.this$0.K_LANGUAGE_TAG, var3);
                        return;
                     case 1:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 7, (byte)var3});
                        return;
                     case 2:
                        CanbusMsgSender.sendMsg(new byte[]{22, -116, 8, (byte)((byte)var3 + 1)});
                        return;
                     default:
                        return;
                  }
               case 4:
                  label174: {
                     var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
                     var6.hashCode();
                     switch (var6) {
                        case "_185_Right_camera_status":
                           var7 = 0;
                           break label174;
                        case "_185_Left_camera_status":
                           var7 = 1;
                           break label174;
                        case "_185_Front_camera_status":
                           var7 = 2;
                           break label174;
                        case "_185_Rear_camera_status":
                           var7 = 3;
                           break label174;
                     }

                     var7 = -1;
                  }

                  switch (var7) {
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
            }

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
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 == 3) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 7});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 6});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 5});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 4});
            }

         }
      });
      var3.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onTouchItem(MotionEvent var1) {
            int var2 = (int)var1.getX();
            int var3 = (int)var1.getY();
            CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, (byte)DataHandleUtils.getMsb(var2), (byte)DataHandleUtils.getLsb(var2), (byte)DataHandleUtils.getMsb(var3), (byte)DataHandleUtils.getLsb(var3), 0});
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
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequestData(17);
            this.this$0.activeRequestData(18);
            this.this$0.activeRequestData(135);
            this.this$0.activeRequestData(38);
            this.this$0.activeRequestData(232);
         }
      });
   }

   private void SendMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void activeRequestData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var7 = var5.iterator();

         while(var7.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
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

   public void initSettings() {
      int var1 = SharePreUtil.getIntValue(this.mContext, this.K_LANGUAGE_TAG, 0);
      CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)((byte)var1 + 1)});
      this.getMsgMgr(this.mContext).updateSetting(this.getSettingLeftIndexes(this.mContext, "_1193_setting_4"), this.getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_0_0"), var1);
   }

   public void sendMediaInfo0x91(int var1, byte[] var2) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -111, (byte)var1}, var2));
   }

   public void sendSourceInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte)var1, (byte)var2});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.eachId;
      if (var2 == 1 || var2 == 2 || var2 == 6) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_0_0"});
      }

      var2 = this.eachId;
      if (var2 == 3 || var2 == 6) {
         this.removeMainPrjBtnByName(this.mContext, "air");
      }

      var2 = this.eachId;
      if (var2 != 5 && var2 != 7) {
         this.removeSettingLeftItemByNameList(this.mContext, new String[]{"_185_Original_vehicle_screen_status"});
      }

      var2 = this.eachId;
      if (var2 == 1 || var2 == 2) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_1"});
      }

      var2 = this.eachId;
      if (var2 == 1 || var2 == 2) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_185_setting_0"});
      }

      var2 = this.eachId;
      if (var2 != 5 && var2 != 7 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_2"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_2_1"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_4_0"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_4_1"});
      }

      var2 = this.eachId;
      if (var2 != 5 && var2 != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_2_0"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_3_0"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_3_1"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_3_2"});
      }

      if (this.eachId != 6) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_4"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_3"});
      }

      var2 = this.eachId;
      if (var2 != 1 && var2 != 2 && var2 != 5 && var2 != 7 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_8"});
      }

      var2 = this.eachId;
      if (var2 != 7 && var2 != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_0"});
      }

      if (this.eachId != 7) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_6"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_7"});
      }

      if (this.eachId != 5) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_9"});
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_1_A"});
      }

      if (this.eachId != 8) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_1193_setting_bu_2", "_1193_setting_6_0", "_1193_setting_6_1"});
      }

   }
}
