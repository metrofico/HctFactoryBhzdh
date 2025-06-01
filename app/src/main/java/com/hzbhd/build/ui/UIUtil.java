package com.hzbhd.build.ui;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R-\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"},
   d2 = {"Lcom/hzbhd/build/ui/UIUtil;", "", "()V", "uiMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/build/ui/UIUtil$UIMix;", "Lkotlin/collections/HashMap;", "getUiMap", "()Ljava/util/HashMap;", "UIMix", "UI-config_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UIUtil {
   public static final UIUtil INSTANCE = new UIUtil();
   private static final HashMap uiMap = MapsKt.hashMapOf(new Pair[]{new Pair("ui_a1", new UIMix("a1", "a1", "a1", "a1", "a1", "a1", "a1", "a1", "a1", "a1", "01")), new Pair("ui_h2", new UIMix("h2", "h2", "h2", "h2", "01", "f5", "h2", "h2", "h2", "01", "01")), new Pair("ui_0g", new UIMix("0g", "0g", "0g", "01", "0g", "01", "0g", "0g", "0g", "01", "01")), new Pair("ui_c3", new UIMix("h3", "h3", "h3", "01", "01", "01", "c3", "c3", "c3", "01", "01")), new Pair("ui_69", new UIMix("69", "69", "69", "01", "01", "01", "69", "69", "69", "01", "01")), new Pair("ui_55", new UIMix("55", "55", "55", "01", "01", "01", "55", "55", "55", "01", "01")), new Pair("ui_i1", new UIMix("i1", "i1", "i1", "01", "01", "01", "i1", "i1", "i1", "01", "01")), new Pair("ui_e3", new UIMix("e3", "e3", "e3", "e3", "e3", "01", "e3", "e3", "e3", "01", "01")), new Pair("ui_f3", new UIMix("f3", "f3", "f3", "f3", "01", "f5", "f3", "f3", "f3", "01", "01"))});

   private UIUtil() {
   }

   public final HashMap getUiMap() {
      return uiMap;
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003Jw\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0010\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010¨\u0006/"},
      d2 = {"Lcom/hzbhd/build/ui/UIUtil$UIMix;", "", "media", "", "radio", "bt", "dsp", "misc", "camera", "widget", "launcher", "systemui", "settings", "dab", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBt", "()Ljava/lang/String;", "getCamera", "getDab", "setDab", "(Ljava/lang/String;)V", "getDsp", "getLauncher", "getMedia", "getMisc", "getRadio", "getSettings", "getSystemui", "getWidget", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "UI-config_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class UIMix {
      private final String bt;
      private final String camera;
      private String dab;
      private final String dsp;
      private final String launcher;
      private final String media;
      private final String misc;
      private final String radio;
      private final String settings;
      private final String systemui;
      private final String widget;

      public UIMix(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11) {
         Intrinsics.checkNotNullParameter(var1, "media");
         Intrinsics.checkNotNullParameter(var2, "radio");
         Intrinsics.checkNotNullParameter(var3, "bt");
         Intrinsics.checkNotNullParameter(var4, "dsp");
         Intrinsics.checkNotNullParameter(var5, "misc");
         Intrinsics.checkNotNullParameter(var6, "camera");
         Intrinsics.checkNotNullParameter(var7, "widget");
         Intrinsics.checkNotNullParameter(var8, "launcher");
         Intrinsics.checkNotNullParameter(var9, "systemui");
         Intrinsics.checkNotNullParameter(var10, "settings");
         Intrinsics.checkNotNullParameter(var11, "dab");
         super();
         this.media = var1;
         this.radio = var2;
         this.bt = var3;
         this.dsp = var4;
         this.misc = var5;
         this.camera = var6;
         this.widget = var7;
         this.launcher = var8;
         this.systemui = var9;
         this.settings = var10;
         this.dab = var11;
      }

      // $FF: synthetic method
      public UIMix(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, int var12, DefaultConstructorMarker var13) {
         if ((var12 & 1024) != 0) {
            var11 = "01";
         }

         this(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      }

      // $FF: synthetic method
      public static UIMix copy$default(UIMix var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, int var12, Object var13) {
         if ((var12 & 1) != 0) {
            var1 = var0.media;
         }

         if ((var12 & 2) != 0) {
            var2 = var0.radio;
         }

         if ((var12 & 4) != 0) {
            var3 = var0.bt;
         }

         if ((var12 & 8) != 0) {
            var4 = var0.dsp;
         }

         if ((var12 & 16) != 0) {
            var5 = var0.misc;
         }

         if ((var12 & 32) != 0) {
            var6 = var0.camera;
         }

         if ((var12 & 64) != 0) {
            var7 = var0.widget;
         }

         if ((var12 & 128) != 0) {
            var8 = var0.launcher;
         }

         if ((var12 & 256) != 0) {
            var9 = var0.systemui;
         }

         if ((var12 & 512) != 0) {
            var10 = var0.settings;
         }

         if ((var12 & 1024) != 0) {
            var11 = var0.dab;
         }

         return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      }

      public final String component1() {
         return this.media;
      }

      public final String component10() {
         return this.settings;
      }

      public final String component11() {
         return this.dab;
      }

      public final String component2() {
         return this.radio;
      }

      public final String component3() {
         return this.bt;
      }

      public final String component4() {
         return this.dsp;
      }

      public final String component5() {
         return this.misc;
      }

      public final String component6() {
         return this.camera;
      }

      public final String component7() {
         return this.widget;
      }

      public final String component8() {
         return this.launcher;
      }

      public final String component9() {
         return this.systemui;
      }

      public final UIMix copy(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11) {
         Intrinsics.checkNotNullParameter(var1, "media");
         Intrinsics.checkNotNullParameter(var2, "radio");
         Intrinsics.checkNotNullParameter(var3, "bt");
         Intrinsics.checkNotNullParameter(var4, "dsp");
         Intrinsics.checkNotNullParameter(var5, "misc");
         Intrinsics.checkNotNullParameter(var6, "camera");
         Intrinsics.checkNotNullParameter(var7, "widget");
         Intrinsics.checkNotNullParameter(var8, "launcher");
         Intrinsics.checkNotNullParameter(var9, "systemui");
         Intrinsics.checkNotNullParameter(var10, "settings");
         Intrinsics.checkNotNullParameter(var11, "dab");
         return new UIMix(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof UIMix)) {
            return false;
         } else {
            UIMix var2 = (UIMix)var1;
            if (!Intrinsics.areEqual((Object)this.media, (Object)var2.media)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.radio, (Object)var2.radio)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.bt, (Object)var2.bt)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.dsp, (Object)var2.dsp)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.misc, (Object)var2.misc)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.camera, (Object)var2.camera)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.widget, (Object)var2.widget)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.launcher, (Object)var2.launcher)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.systemui, (Object)var2.systemui)) {
               return false;
            } else if (!Intrinsics.areEqual((Object)this.settings, (Object)var2.settings)) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.dab, (Object)var2.dab);
            }
         }
      }

      public final String getBt() {
         return this.bt;
      }

      public final String getCamera() {
         return this.camera;
      }

      public final String getDab() {
         return this.dab;
      }

      public final String getDsp() {
         return this.dsp;
      }

      public final String getLauncher() {
         return this.launcher;
      }

      public final String getMedia() {
         return this.media;
      }

      public final String getMisc() {
         return this.misc;
      }

      public final String getRadio() {
         return this.radio;
      }

      public final String getSettings() {
         return this.settings;
      }

      public final String getSystemui() {
         return this.systemui;
      }

      public final String getWidget() {
         return this.widget;
      }

      public int hashCode() {
         return (((((((((this.media.hashCode() * 31 + this.radio.hashCode()) * 31 + this.bt.hashCode()) * 31 + this.dsp.hashCode()) * 31 + this.misc.hashCode()) * 31 + this.camera.hashCode()) * 31 + this.widget.hashCode()) * 31 + this.launcher.hashCode()) * 31 + this.systemui.hashCode()) * 31 + this.settings.hashCode()) * 31 + this.dab.hashCode();
      }

      public final void setDab(String var1) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.dab = var1;
      }

      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("UIMix(media=").append(this.media).append(", radio=").append(this.radio).append(", bt=").append(this.bt).append(", dsp=").append(this.dsp).append(", misc=").append(this.misc).append(", camera=").append(this.camera).append(", widget=").append(this.widget).append(", launcher=").append(this.launcher).append(", systemui=").append(this.systemui).append(", settings=").append(this.settings).append(", dab=").append(this.dab).append(')');
         return var1.toString();
      }
   }
}
