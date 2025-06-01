package com.hzbhd.canbus.car._431;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
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
   public static int now_model1_B;
   public static int now_model1_G;
   public static int now_model1_R;
   public static int now_model2_B;
   public static int now_model2_G;
   public static int now_model2_R;
   public static int now_model3_B;
   public static int now_model3_G;
   public static int now_model3_R;
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   int now360State = 0;
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequest(49);
      }
   };
   OnPanoramicItemClickListener onPanoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           UiMgr var2 = this.this$0;
                           var2.getMsgMgr(var2.mContext).forceReverse(false);
                        }
                     } else if (this.this$0.now360State == 0) {
                        this.this$0.sendPanoramic0xFD(4, 1);
                        this.this$0.now360State = 1;
                     } else {
                        this.this$0.sendPanoramic0xFD(4, 3);
                        this.this$0.now360State = 0;
                     }
                  } else {
                     this.this$0.sendPanoramic0xFD(2, 4);
                  }
               } else {
                  this.this$0.sendPanoramic0xFD(2, 3);
               }
            } else {
               this.this$0.sendPanoramic0xFD(2, 2);
            }
         } else {
            this.this$0.sendPanoramic0xFD(2, 1);
         }

      }
   };
   OnPanoramicItemTouchListener onPanoramicItemTouchListener = new OnPanoramicItemTouchListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      private int getLsb(int var1) {
         return (var1 & '\uffff') >> 0 & 255;
      }

      private int getMsb(int var1) {
         return (var1 & '\uffff') >> 8 & 255;
      }

      public void onTouchItem(MotionEvent var1) {
         int var3 = (int)var1.getX();
         int var2 = (int)var1.getY();
         this.this$0.sendPanoramicXY0x2C(this.getMsb(var3), this.getLsb(var3), this.getMsb(var2), this.getLsb(var2));
      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_431_setting0")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting0", "_431_setting_l")) {
               this.this$0.sendLight0x6F(2, var3, 0, 0);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting0", "_431_setting_r")) {
               UiMgr.now_model1_R = var3;
               this.this$0.sendLight0x6F(1, UiMgr.now_model1_R, UiMgr.now_model1_G, UiMgr.now_model1_B);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting0", "_431_setting_g")) {
               UiMgr.now_model1_G = var3;
               this.this$0.sendLight0x6F(1, UiMgr.now_model1_R, UiMgr.now_model1_G, UiMgr.now_model1_B);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting0", "_431_setting_b")) {
               UiMgr.now_model1_B = var3;
               this.this$0.sendLight0x6F(1, UiMgr.now_model1_R, UiMgr.now_model1_G, UiMgr.now_model1_B);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_431_setting1")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting1", "_431_setting_l")) {
               this.this$0.sendLight0x6F(4, var3, 0, 0);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting1", "_431_setting_r")) {
               UiMgr.now_model2_R = var3;
               this.this$0.sendLight0x6F(3, UiMgr.now_model2_R, UiMgr.now_model2_G, UiMgr.now_model2_B);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting1", "_431_setting_g")) {
               UiMgr.now_model2_G = var3;
               this.this$0.sendLight0x6F(3, UiMgr.now_model2_R, UiMgr.now_model2_G, UiMgr.now_model2_B);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting1", "_431_setting_b")) {
               UiMgr.now_model2_B = var3;
               this.this$0.sendLight0x6F(3, UiMgr.now_model2_R, UiMgr.now_model2_G, UiMgr.now_model2_B);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_431_setting2")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting2", "_431_setting_l")) {
               this.this$0.sendLight0x6F(6, var3, 0, 0);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting2", "_431_setting_r")) {
               UiMgr.now_model3_R = var3;
               this.this$0.sendLight0x6F(5, UiMgr.now_model3_R, UiMgr.now_model3_G, UiMgr.now_model3_B);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting2", "_431_setting_g")) {
               UiMgr.now_model3_G = var3;
               this.this$0.sendLight0x6F(5, UiMgr.now_model3_R, UiMgr.now_model3_G, UiMgr.now_model3_B);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_setting2", "_431_setting_b")) {
               UiMgr.now_model3_B = var3;
               this.this$0.sendLight0x6F(5, UiMgr.now_model3_R, UiMgr.now_model3_G, UiMgr.now_model3_B);
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
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_431_panoramic")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_431_panoramic", "_431_panoramic1")) {
               if (var3 == 1) {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).showButton();
               } else {
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).hideButton();
               }

               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }
         }

      }
   };
   OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequest(97);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      ParkPageUiSet var3 = this.getParkPageUiSet(this.mContext);
      var3.setOnPanoramicItemClickListener(this.onPanoramicItemClickListener);
      var3.setOnPanoramicItemTouchListener(this.onPanoramicItemTouchListener);
      this.getAirUiSet(this.mContext).getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private void activeRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendLight0x6F(int var1, int var2, int var3, int var4) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, (byte)var3, (byte)var4});
   }

   private void sendPanoramicXY0x2C(int var1, int var2, int var3, int var4) {
      byte var6 = (byte)var1;
      byte var8 = (byte)var2;
      byte var5 = (byte)var3;
      byte var7 = (byte)var4;
      CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, var6, var8, var5, var7, 0});
      CanbusMsgSender.sendMsg(new byte[]{22, 44, 1, var6, var8, var5, var7, 0});
   }

   public void carType(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)var1, 15});
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

   public boolean isLandscape() {
      boolean var1;
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void sendMediaInfo0x91(int var1, byte[] var2) {
      CanbusMsgSender.sendMsg(this.SplicingByte(new byte[]{22, -111, (byte)var1}, var2));
   }

   public void sendPanoramic0xFD(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -3, (byte)var1, (byte)var2});
   }

   public void sendTimeInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var1, (byte)var2, 0, 0, (byte)var3, (byte)var4, (byte)var5, (byte)var6, 0});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.getCurrentCarId() == 1) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

   }
}
