package com.hzbhd.canbus.car._450;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   public static int data0bit3;
   public static int data0bit4;
   public static int data0bit5;
   public static int data0bit6;
   public static int data0bit7;
   int differentId;
   int eachId;
   Context mContext;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "_450_bmw_car_10_left1")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_10_left1", "_450_bmw_car_11")) {
                  UiMgr.data0bit7 = var3;
                  this.this$0.send0x9A_0x01_bit3(var3);
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_10_left1", "_450_bmw_car_12")) {
                  UiMgr.data0bit6 = var3;
                  this.this$0.send0x9A_0x01_bit3(var3);
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_10_left1", "_450_bmw_car_13")) {
                  UiMgr.data0bit5 = var3;
                  this.this$0.send0x9A_0x01_bit3(var3);
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_10_left1", "_450_bmw_car_14")) {
                  UiMgr.data0bit4 = var3;
                  this.this$0.send0x9A_0x01_bit3(var3);
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_10_left1", "_450_bmw_car_15")) {
                  UiMgr.data0bit3 = var3;
                  this.this$0.send0x9A_0x01_bit3(var3);
               }
            }

            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "_450_bmw_car_18_left2")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_19")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 18, (byte)DataHandleUtils.getDecimalFrom8Bit(var3, 0, 0, 0, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_20")) {
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 != 2) {
                           if (var3 == 3) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 1, 0, 0, 0, 0)});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 0, 0, 0, 0, 0)});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 0, 0)});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -102, 20, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, 0, 0, 0)});
                  }
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_21")) {
                  if (var3 != 0) {
                     if (var3 != 1) {
                        if (var3 == 2) {
                           CanbusMsgSender.sendMsg(new byte[]{22, -102, 24, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 1, 0, 0, 0, 0)});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -102, 24, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 1, 0, 0, 0, 0, 0)});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -102, 24, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 0, 0)});
                  }
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_22")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 25, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, var3, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_23")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 26, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, 0, 0, var3)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_24")) {
                  byte[] var4 = new byte[]{22, -102, 33, (byte)DataHandleUtils.getDecimalFrom8Bit(var1, 0, 0, 0, 0, 0, 0, 0)};
                  CanbusMsgSender.sendMsg(var4);
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_25")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 34, (byte)DataHandleUtils.getDecimalFrom8Bit(0, var3, 0, 0, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_26")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 35, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, var3, 0, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_27")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 33, (byte)DataHandleUtils.getDecimalFrom8Bit(var3, 0, 0, 0, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_29")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 49, (byte)DataHandleUtils.getDecimalFrom8Bit(var3, 0, 0, 0, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_30")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 50, (byte)DataHandleUtils.getDecimalFrom8Bit(0, var3, 0, 0, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_31")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 51, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, var3, 0, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_32")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 52, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, var3, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_33")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 53, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, var3, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_34")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 54, (byte)DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, var3, 0, 0, 0, 0)});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_35")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 65, (byte)DataHandleUtils.getDecimalFrom8Bit(var3, 0, 0, 0, 0, 0, 0, 0)});
               }
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "_450_bmw_car_18_left2")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_28")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 36, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_450_bmw_car_18_left2", "_450_bmw_car_36")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -102, 66, (byte)DataHandleUtils.getMsb(var3), (byte)DataHandleUtils.getLsb(var3)});
               }
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void send0x9A_0x01_bit3(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)DataHandleUtils.getDecimalFrom8Bit(data0bit7, data0bit6, data0bit5, data0bit4, data0bit3, 0, 0, 0)});
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
}
