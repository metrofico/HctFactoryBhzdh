package com.hzbhd.canbus.car._462;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.proxy.share.ShareDataManager;

public class UiMgr extends AbstractUiMgr {
   private MyPanoramicView panoramicView;
   float screenHeight = 0.0F;
   float screenWidth = 0.0F;

   public UiMgr(Context var1) {
      this.screenWidth = (float)getScreenWidth(var1);
      this.screenHeight = (float)getScreenHeight(var1);
      this.getParkPageUiSet(var1).setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onTouchItem(MotionEvent var1) {
            if (this.this$0.panoramicView == null) {
               this.this$0.panoramicView = new MyPanoramicView(this.val$context);
            }

            if (this.this$0.panoramicView.manualLock) {
               float var2;
               int var3;
               int var4;
               int var5;
               int var6;
               if (var1.getAction() == 0) {
                  var2 = var1.getX();
                  var4 = (int)(10000.0F / this.this$0.screenWidth * var2);
                  var3 = DataHandleUtils.getMsb(var4);
                  var4 = DataHandleUtils.getLsb(var4);
                  var2 = var1.getY();
                  var6 = (int)(10000.0F / this.this$0.screenHeight * var2);
                  var5 = DataHandleUtils.getMsb(var6);
                  var6 = DataHandleUtils.getLsb(var6);
                  CanbusMsgSender.sendMsg(new byte[]{22, 24, -38, 65, 40, 1, (byte)var4, (byte)var3, (byte)var6, (byte)var5, 0, 0, 0, 1});
               } else if (var1.getAction() == 1) {
                  var2 = var1.getX();
                  var4 = (int)(10000.0F / this.this$0.screenWidth * var2);
                  var3 = DataHandleUtils.getMsb(var4);
                  var4 = DataHandleUtils.getLsb(var4);
                  var6 = (int)(var1.getY() * (10000.0F / this.this$0.screenHeight));
                  var5 = DataHandleUtils.getMsb(var6);
                  var6 = DataHandleUtils.getLsb(var6);
                  CanbusMsgSender.sendMsg(new byte[]{22, 24, -38, 65, 40, 1, (byte)var4, (byte)var3, (byte)var6, (byte)var5, 0, 0, 0, 1});
               }
            }

         }
      });
   }

   private static int getScreenHeight(Context var0) {
      return var0.getResources().getDisplayMetrics().heightPixels;
   }

   private static int getScreenWidth(Context var0) {
      return var0.getResources().getDisplayMetrics().widthPixels;
   }

   public View getCusPanoramicView(Context var1) {
      if (this.panoramicView == null) {
         this.panoramicView = new MyPanoramicView(var1);
      }

      return this.panoramicView;
   }

   // $FF: synthetic method
   void lambda$registerEvent$0$com_hzbhd_canbus_car__462_UiMgr(Context var1, String var2) {
      if (var2.equals("OPEN_360_PANOROMIC_PAGE")) {
         if (this.panoramicView == null) {
            this.panoramicView = new MyPanoramicView(var1);
         }

         this.panoramicView.openManual();
      }

   }

   public void registerEvent(Context var1) {
      ShareDataManager.getInstance().registerShareStringListener("can.request.data.share", new UiMgr$$ExternalSyntheticLambda0(this, var1));
   }
}
