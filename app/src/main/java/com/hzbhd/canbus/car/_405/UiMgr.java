package com.hzbhd.canbus.car._405;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"},
   d2 = {"Lcom/hzbhd/canbus/car/_405/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   // $FF: synthetic method
   public static void $r8$lambda$BI2eSOl31OAC_ghw5BsMNnyqFm4(int var0) {
      lambda_2$lambda_1(var0);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "this");
      SettingHandleListener var2 = new SettingHandleListener(var3);
      var3.setOnSettingItemSelectListener((OnSettingItemSelectListener)var2);
      var3.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var2);
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda0());
   }

   private static final void lambda_2$lambda_1(int var0) {
      if (var0 != 0) {
         if (var0 != 1) {
            if (var0 == 2) {
               lambda_2$sendPanoramaCmd(2);
            }
         } else {
            lambda_2$sendPanoramaCmd(1);
         }
      } else {
         lambda_2$sendPanoramaCmd(0);
      }

   }

   private static final void lambda_2$sendPanoramaCmd(int var0) {
      CanbusMsgSender.sendMsg(new byte[]{22, 74, 1, (byte)var0});
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J \u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"},
      d2 = {"Lcom/hzbhd/canbus/car/_405/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "progressORvalue", "onSwitch", "value", "selectSettingsBtn", "param", "sendSettingsCmd", "d0", "d1", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
      private final SettingPageUiSet mSettingPageUiSet;

      public SettingHandleListener(SettingPageUiSet var1) {
         Intrinsics.checkNotNullParameter(var1, "mSettingPageUiSet");
         super();
         this.mSettingPageUiSet = var1;
      }

      private final void selectSettingsBtn(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         if (var4 != null) {
            var1 = var4.hashCode();
            switch (var1) {
               case -1528225728:
                  if (var4.equals("S405_x87_10")) {
                     this.sendSettingsCmd(10, var3);
                  }
                  break;
               case -1528225727:
                  if (var4.equals("S405_x87_11")) {
                     this.sendSettingsCmd(15, var3);
                  }
                  break;
               case -1528225726:
                  if (var4.equals("S405_x87_12")) {
                     this.sendSettingsCmd(14, var3);
                  }
                  break;
               default:
                  switch (var1) {
                     case 1751817712:
                        if (var4.equals("S405_x87_1")) {
                           this.sendSettingsCmd(5, var3);
                        }
                        break;
                     case 1751817713:
                        if (var4.equals("S405_x87_2")) {
                           this.sendSettingsCmd(4, var3);
                        }
                        break;
                     case 1751817714:
                        if (var4.equals("S405_x87_3")) {
                           this.sendSettingsCmd(3, var3);
                        }
                        break;
                     case 1751817715:
                        if (var4.equals("S405_x87_4")) {
                           this.sendSettingsCmd(2, var3);
                        }
                        break;
                     case 1751817716:
                        if (var4.equals("S405_x87_5")) {
                           this.sendSettingsCmd(9, var3);
                        }
                        break;
                     case 1751817717:
                        if (var4.equals("S405_x87_6")) {
                           this.sendSettingsCmd(8, var3);
                        }
                        break;
                     case 1751817718:
                        if (var4.equals("S405_x87_7")) {
                           this.sendSettingsCmd(13, var3);
                        }
                        break;
                     case 1751817719:
                        if (var4.equals("S405_x87_8")) {
                           this.sendSettingsCmd(12, var3);
                        }
                        break;
                     case 1751817720:
                        if (var4.equals("S405_x87_9")) {
                           this.sendSettingsCmd(11, var3);
                        }
                  }
            }
         }

      }

      private final void sendSettingsCmd(int var1, int var2) {
         CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)var1, (byte)var2});
      }

      public final SettingPageUiSet getMSettingPageUiSet() {
         return this.mSettingPageUiSet;
      }

      public void onClickItem(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }

      public void onSwitch(int var1, int var2, int var3) {
         this.selectSettingsBtn(var1, var2, var3);
      }
   }
}
