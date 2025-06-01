package com.hzbhd.canbus.car._58;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import java.util.ArrayList;

public class UiMgr extends AbstractUiMgr {
   private OnOriginalCarDeviceBackPressedListener mBack = new OnOriginalCarDeviceBackPressedListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onBackPressed() {
         CanbusMsgSender.sendMsg(new byte[]{22, -84, 2, 0});
         GeneralOriginalCarDeviceData.power = false;
      }
   };
   private OnOriginalBottomBtnClickListener mBottomBtn = new OnOriginalBottomBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickBottomBtnItem(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -84, 7, (byte)var1});
      }
   };
   private Context mContext;
   private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
   private OnOriginalTopBtnClickListener mTopBtn = new OnOriginalTopBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickTopBtnItem(int var1) {
         if (GeneralOriginalCarDeviceData.power) {
            CanbusMsgSender.sendMsg(new byte[]{22, -84, 2, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -84, 1, 0});
         }

      }
   };

   public UiMgr(Context var1) {
      try {
         this.mContext = var1;
         OriginalCarDevicePageUiSet var4 = this.getOriginalCarDevicePageUiSet(var1);
         this.mOriginalCarDevicePageUiSet = var4;
         var4.setOnOriginalTopBtnClickListeners(this.mTopBtn);
         OriginalCarDevicePageUiSet var2 = this.mOriginalCarDevicePageUiSet;
         OnOriginalBottomBtnClickListener var5 = new OnOriginalBottomBtnClickListener(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void onClickBottomBtnItem(int var1) {
               String var2 = this.this$0.mOriginalCarDevicePageUiSet.getRowBottomBtnAction()[var1];
               var2.hashCode();
               if (!var2.equals("up")) {
                  if (var2.equals("down")) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -84, 7, 1});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -84, 7, 0});
               }

            }
         };
         var2.setOnOriginalBottomBtnClickListeners(var5);
         this.mOriginalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.mBack);
         ArrayList var6 = new ArrayList();
         OriginalCarDevicePageUiSet.Item var7 = new OriginalCarDevicePageUiSet.Item("music_list", "_55_0xa4_item", "");
         var6.add(var7);
         var7 = new OriginalCarDevicePageUiSet.Item("music_list", "total_file", "");
         var6.add(var7);
         var7 = new OriginalCarDevicePageUiSet.Item("music_list", "current_song_list", "");
         var6.add(var7);
         var7 = new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_104", "");
         var6.add(var7);
         var7 = new OriginalCarDevicePageUiSet.Item("music_list", "_55_0xa4_item5", "");
         var6.add(var7);
         this.mOriginalCarDevicePageUiSet.setItems(var6);
         this.mOriginalCarDevicePageUiSet.setRowTopBtnAction(new String[]{"power"});
         this.mOriginalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"up", "down"});
         this.mOriginalCarDevicePageUiSet.setHaveSongList(false);
      } catch (Exception var3) {
         var3.printStackTrace();
         Log.d("cwh", "错误报告：" + var3);
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
