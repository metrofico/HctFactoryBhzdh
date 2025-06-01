package com.hzbhd.canbus.car._403;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"},
   d2 = {"Lcom/hzbhd/canbus/car/_403/UiMgr;", "Lcom/hzbhd/canbus/ui_mgr/AbstractUiMgr;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mParkPageUiSet", "Lcom/hzbhd/canbus/ui_set/ParkPageUiSet;", "kotlin.jvm.PlatformType", "mSettingHandleListener", "Lcom/hzbhd/canbus/car/_403/UiMgr$SettingHandleListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "SettingHandleListener", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UiMgr extends AbstractUiMgr {
   private final ParkPageUiSet mParkPageUiSet;
   private final SettingHandleListener mSettingHandleListener;
   private final SettingPageUiSet mSettingPageUiSet;

   // $FF: synthetic method
   public static void $r8$lambda$EYSUdYRG4ynqJqIsToBXUT96_ms(int var0) {
      lambda_1$lambda_0(var0);
   }

   public UiMgr(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      super();
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "getSettingUiSet(context)");
      this.mSettingPageUiSet = var3;
      SettingHandleListener var2 = new SettingHandleListener(this, var3);
      this.mSettingHandleListener = var2;
      ParkPageUiSet var4 = this.getParkPageUiSet(var1);
      this.mParkPageUiSet = var4;
      var4.setOnPanoramicItemClickListener(new UiMgr$$ExternalSyntheticLambda0());
      var3.setOnSettingItemSelectListener((OnSettingItemSelectListener)var2);
      var3.setOnSettingItemSwitchListener((OnSettingItemSwitchListener)var2);
      var3.setOnSettingItemSeekbarSelectListener((OnSettingItemSeekbarSelectListener)var2);
   }

   private static final void lambda_1$lambda_0(int var0) {
      switch (var0) {
         case 0:
            lambda_1$sendPanoramaCmd(10, 1);
            break;
         case 1:
            lambda_1$sendPanoramaCmd(10, 2);
            break;
         case 2:
            lambda_1$sendPanoramaCmd(10, 3);
            break;
         case 3:
            lambda_1$sendPanoramaCmd(10, 4);
            break;
         case 4:
            lambda_1$sendPanoramaCmd(10, 5);
            break;
         case 5:
            lambda_1$sendPanoramaCmd(11, 1);
            break;
         case 6:
            lambda_1$sendPanoramaCmd(11, 2);
      }

   }

   private static final void lambda_1$sendPanoramaCmd(int var0, int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte)var0, (byte)var1});
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J \u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"},
      d2 = {"Lcom/hzbhd/canbus/car/_403/UiMgr$SettingHandleListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSelectListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSwitchListener;", "Lcom/hzbhd/canbus/interfaces/OnSettingItemSeekbarSelectListener;", "mSettingPageUiSet", "Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "(Lcom/hzbhd/canbus/car/_403/UiMgr;Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;)V", "getMSettingPageUiSet", "()Lcom/hzbhd/canbus/ui_set/SettingPageUiSet;", "onClickItem", "", "leftPos", "", "rightPos", "value", "onSwitch", "selectSettingsBtn", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class SettingHandleListener implements OnSettingItemSelectListener, OnSettingItemSwitchListener, OnSettingItemSeekbarSelectListener {
      private final SettingPageUiSet mSettingPageUiSet;
      final UiMgr this$0;

      public SettingHandleListener(UiMgr var1, SettingPageUiSet var2) {
         Intrinsics.checkNotNullParameter(var2, "mSettingPageUiSet");
         this.this$0 = var1;
         super();
         this.mSettingPageUiSet = var2;
      }

      private final void selectSettingsBtn(int var1, int var2, int var3) {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.mSettingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         if (var4 != null) {
            var1 = var4.hashCode();
            switch (var1) {
               case 1544023514:
                  if (var4.equals("S403_0x67_10")) {
                     selectSettingsBtn$send0x6C(6, var3);
                  }
                  break;
               case 1544023515:
                  if (var4.equals("S403_0x67_11")) {
                     selectSettingsBtn$send0x6C(3, var3);
                  }
                  break;
               case 1544023516:
                  if (var4.equals("S403_0x67_12")) {
                     selectSettingsBtn$send0x6C(14, var3);
                  }
                  break;
               case 1544023517:
                  if (var4.equals("S403_0x67_13")) {
                     selectSettingsBtn$send0x6C(13, var3);
                  }
                  break;
               case 1544023518:
                  if (var4.equals("S403_0x67_14")) {
                     selectSettingsBtn$send0x6C(12, var3);
                  }
                  break;
               default:
                  switch (var1) {
                     case 1573826901:
                        if (var4.equals("S403_0x66_1")) {
                           selectSettingsBtn$send0x6B(6, var3);
                        }
                        break;
                     case 1573826902:
                        if (var4.equals("S403_0x66_2")) {
                           selectSettingsBtn$send0x6B(5, var3);
                        }
                        break;
                     case 1573826903:
                        if (var4.equals("S403_0x66_3")) {
                           selectSettingsBtn$send0x6B(3, var3);
                        }
                        break;
                     case 1573826904:
                        if (var4.equals("S403_0x66_4")) {
                           selectSettingsBtn$send0x6B(2, var3);
                        }
                        break;
                     case 1573826905:
                        if (var4.equals("S403_0x66_5")) {
                           selectSettingsBtn$send0x6B(1, var3);
                        }
                        break;
                     default:
                        switch (var1) {
                           case 1573827862:
                              if (var4.equals("S403_0x67_1")) {
                                 selectSettingsBtn$send0x6C(5, var3);
                              }
                              break;
                           case 1573827863:
                              if (var4.equals("S403_0x67_2")) {
                                 selectSettingsBtn$send0x6C(4, var3);
                              }
                              break;
                           case 1573827864:
                              if (var4.equals("S403_0x67_3")) {
                                 selectSettingsBtn$send0x6C(2, var3);
                              }
                              break;
                           case 1573827865:
                              if (var4.equals("S403_0x67_4")) {
                                 selectSettingsBtn$send0x6C(1, var3);
                              }
                              break;
                           case 1573827866:
                              if (var4.equals("S403_0x67_5")) {
                                 selectSettingsBtn$send0x6C(11, var3);
                              }
                              break;
                           case 1573827867:
                              if (var4.equals("S403_0x67_6")) {
                                 selectSettingsBtn$send0x6C(10, var3);
                              }
                              break;
                           case 1573827868:
                              if (var4.equals("S403_0x67_7")) {
                                 selectSettingsBtn$send0x6C(9, var3);
                              }
                              break;
                           case 1573827869:
                              if (var4.equals("S403_0x67_8")) {
                                 selectSettingsBtn$send0x6C(8, var3);
                              }
                              break;
                           case 1573827870:
                              if (var4.equals("S403_0x67_9")) {
                                 selectSettingsBtn$send0x6C(7, var3);
                              }
                              break;
                           default:
                              switch (var1) {
                                 case 1573828823:
                                    if (var4.equals("S403_0x68_1")) {
                                       selectSettingsBtn$send0x6D(5, var3);
                                    }
                                    break;
                                 case 1573828824:
                                    if (var4.equals("S403_0x68_2")) {
                                       selectSettingsBtn$send0x6D(4, var3);
                                    }
                                    break;
                                 case 1573828825:
                                    if (var4.equals("S403_0x68_3")) {
                                       selectSettingsBtn$send0x6D(3, var3);
                                    }
                                    break;
                                 case 1573828826:
                                    if (var4.equals("S403_0x68_4")) {
                                       selectSettingsBtn$send0x6D(2, var3);
                                    }
                                    break;
                                 case 1573828827:
                                    if (var4.equals("S403_0x68_5")) {
                                       selectSettingsBtn$send0x6D(1, var3);
                                    }
                              }
                        }
                  }
            }
         }

      }

      private static final void selectSettingsBtn$send0x6B(int var0, int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 107, (byte)var0, (byte)var1});
      }

      private static final void selectSettingsBtn$send0x6C(int var0, int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 108, (byte)var0, (byte)var1});
      }

      private static final void selectSettingsBtn$send0x6D(int var0, int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 109, (byte)var0, (byte)var1});
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
