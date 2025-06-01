package com.hzbhd.config.use;

import android.app.DefaultSharedUtil;
import android.content.Context;
import android.provider.Settings.System;
import android.text.TextUtils;
import com.hzbhd.build.ui.UIUtil;
import com.hzbhd.config.bean.UIBean;
import com.hzbhd.constant.config.ConfigConstant;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\fJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\fR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0018"},
   d2 = {"Lcom/hzbhd/config/use/UI;", "", "()V", "value", "", "restartWhenSelectUI", "getRestartWhenSelectUI", "()Z", "setRestartWhenSelectUI", "(Z)V", "getSelectUI", "", "", "()[Ljava/lang/String;", "getUI", "context", "Landroid/content/Context;", "appName", "Lcom/hzbhd/config/bean/UIBean$AppName;", "getUIId", "setUI", "", "uiId", "UIKey", "UI-config_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UI {
   public static final UI INSTANCE = new UI();

   private UI() {
   }

   public final boolean getRestartWhenSelectUI() {
      return DefaultSharedUtil.getBool(UI.UIKey.ui_b_restart_when_select_ui.name(), UIDefault.INSTANCE.getRestartWhenSelectUI());
   }

   public final String[] getSelectUI() {
      String var3 = DefaultSharedUtil.getStr(ConfigConstant.SharedKey.ui_s_select_ui_array.name(), "");
      CharSequence var4 = (CharSequence)var3;
      String[] var5;
      if (TextUtils.isEmpty(var4)) {
         var5 = UIDefault.INSTANCE.getSelectUI();
      } else {
         Intrinsics.checkNotNullExpressionValue(var3, "configSelectUI");
         List var6 = StringsKt.split$default(var4, new String[]{"___"}, false, 0, 6, (Object)null);
         int var1 = 0;
         int var2 = var6.size();

         for(var5 = new String[var2]; var1 < var2; ++var1) {
            var5[var1] = UIEncrypt.INSTANCE.changeStringToUI((String)var6.get(var1));
         }
      }

      return var5;
   }

   public final String getUI(Context var1, UIBean.AppName var2) {
      Intrinsics.checkNotNullParameter(var1, "context");
      Intrinsics.checkNotNullParameter(var2, "appName");
      String var3;
      if (TextUtils.isEmpty((CharSequence)System.getString(var1.getContentResolver(), UI.UIKey.ui_s_id.name()))) {
         var3 = this.getUI(var2);
      } else {
         var3 = System.getString(var1.getContentResolver(), UI.UIKey.ui_s_id.name());
         Intrinsics.checkNotNullExpressionValue(var3, "{\n            Settings.S…y.ui_s_id.name)\n        }");
      }

      return var3;
   }

   public final String getUI(UIBean.AppName var1) {
      Intrinsics.checkNotNullParameter(var1, "appName");
      String var4 = this.getUIId();
      int var2 = UI.WhenMappings.$EnumSwitchMapping$0[var1.ordinal()];
      String var3 = "01";
      String var5;
      UIUtil.UIMix var6;
      switch (var2) {
         case 1:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getMedia();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 2:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getRadio();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 3:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getBt();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 4:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getDsp();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 5:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getMisc();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 6:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getCamera();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 7:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getWidget();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 8:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getLauncher();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 9:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getSystemui();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 10:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getDab();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         case 11:
            var6 = (UIUtil.UIMix)UIUtil.INSTANCE.getUiMap().get("ui_" + var4);
            var5 = var3;
            if (var6 != null) {
               var4 = var6.getSettings();
               var5 = var4;
               if (var4 == null) {
                  var5 = var3;
               }
            }
            break;
         default:
            var5 = var3;
      }

      return var5;
   }

   public final String getUIId() {
      String var1 = DefaultSharedUtil.getStr(UI.UIKey.ui_s_id_encrypt.name(), "");
      if (TextUtils.isEmpty((CharSequence)var1)) {
         var1 = DefaultSharedUtil.getStr(UI.UIKey.ui_s_id.name(), UIDefault.INSTANCE.getCurrUI());
         Intrinsics.checkNotNullExpressionValue(var1, "{\n            DefaultSha…Default.currUI)\n        }");
      } else {
         UIEncrypt var2 = UIEncrypt.INSTANCE;
         Intrinsics.checkNotNullExpressionValue(var1, "saveUI");
         var1 = var2.changeStringToUI(var1);
      }

      return var1;
   }

   public final void setRestartWhenSelectUI(boolean var1) {
      DefaultSharedUtil.setBool(UI.UIKey.ui_b_restart_when_select_ui.name(), var1);
   }

   public final void setUI(String var1) {
      Intrinsics.checkNotNullParameter(var1, "uiId");
      DefaultSharedUtil.setStr(UI.UIKey.ui_s_id_encrypt.name(), UIEncrypt.INSTANCE.changeUIToString(var1));
   }

   @Metadata(
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"},
      d2 = {"Lcom/hzbhd/config/use/UI$UIKey;", "", "(Ljava/lang/String;I)V", "ui_s_id", "ui_s_id_encrypt", "ui_b_restart_when_select_ui", "UI-config_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static enum UIKey {
      private static final UIKey[] $VALUES = $values();
      ui_b_restart_when_select_ui,
      ui_s_id,
      ui_s_id_encrypt;

      // $FF: synthetic method
      private static final UIKey[] $values() {
         return new UIKey[]{ui_s_id, ui_s_id_encrypt, ui_b_restart_when_select_ui};
      }
   }

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class WhenMappings {
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[UIBean.AppName.values().length];
         var0[UIBean.AppName.media.ordinal()] = 1;
         var0[UIBean.AppName.radio.ordinal()] = 2;
         var0[UIBean.AppName.bt.ordinal()] = 3;
         var0[UIBean.AppName.dsp.ordinal()] = 4;
         var0[UIBean.AppName.misc.ordinal()] = 5;
         var0[UIBean.AppName.camera.ordinal()] = 6;
         var0[UIBean.AppName.widget.ordinal()] = 7;
         var0[UIBean.AppName.launcher.ordinal()] = 8;
         var0[UIBean.AppName.systemui.ordinal()] = 9;
         var0[UIBean.AppName.dab.ordinal()] = 10;
         var0[UIBean.AppName.settings.ordinal()] = 11;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
