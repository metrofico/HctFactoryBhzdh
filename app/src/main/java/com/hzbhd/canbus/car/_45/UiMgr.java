package com.hzbhd.canbus.car._45;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.getOriginalCarDevicePageUiSet(var1).setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickBottomBtnItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           this.this$0.send0x90HostControlInfo(3);
                        }
                     } else {
                        this.this$0.send0x90HostControlInfo(8);
                     }
                  } else {
                     this.this$0.send0x90HostControlInfo(1);
                  }
               } else {
                  this.this$0.send0x90HostControlInfo(9);
               }
            } else {
               this.this$0.send0x90HostControlInfo(4);
            }

         }
      });
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

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

   public void makeConnection() {
      CanbusMsgSender.sendMsg(new byte[]{22, -128, 1});
   }

   public void send0x82MediaType(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1});
   }

   public void send0x83RadioInfo(int var1, int var2, int var3, int var4, int var5, int var6) {
      CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6});
   }

   public void send0x84DiscInfo(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8, (byte)var9, (byte)var10, (byte)var11, (byte)var12});
   }

   public void send0x86VolControl(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte)this.getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(var1, 6, 1), DataHandleUtils.getIntFromByteWithBit(var1, 5, 1), DataHandleUtils.getIntFromByteWithBit(var1, 4, 1), DataHandleUtils.getIntFromByteWithBit(var1, 3, 1), DataHandleUtils.getIntFromByteWithBit(var1, 2, 1), DataHandleUtils.getIntFromByteWithBit(var1, 1, 1), DataHandleUtils.getIntFromByteWithBit(var1, 0, 1))});
   }

   public void send0x90HostControlInfo(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, -112, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -112, var2, 0});
   }

   public void send0x91UsbControlInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -111, (byte)var1, (byte)var2});
   }

   public void send0x92TimeInfo(int var1, int var2, int var3) {
      CanbusMsgSender.sendMsg(new byte[]{22, -110, (byte)var1, (byte)var2, (byte)var3});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
