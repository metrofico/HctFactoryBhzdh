package com.hzbhd.config.use;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004¨\u0006\u000e"},
   d2 = {"Lcom/hzbhd/config/use/UIEncrypt;", "", "()V", "changeByteArrayToUI", "", "byteArray", "", "changeStringToUI", "str", "changeUIToByteArray", "ui1", "ui2", "changeUIToString", "ui", "UI-config_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class UIEncrypt {
   public static final UIEncrypt INSTANCE = new UIEncrypt();

   private UIEncrypt() {
   }

   public final String changeByteArrayToUI(byte[] var1) {
      Intrinsics.checkNotNullParameter(var1, "byteArray");
      int var4 = var1.length;
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var4; ++var2) {
         var3 += (var1[var2] & 255) << (var1.length - 1 - var2) * 8;
      }

      StringBuilder var5 = (new StringBuilder()).append("changeByteArrayToUI:").append(Integer.toHexString(var3)).append("    ");
      String var6 = Integer.toString(var3, CharsKt.checkRadix(36));
      Intrinsics.checkNotNullExpressionValue(var6, "toString(this, checkRadix(radix))");
      var6 = var5.append(var6).toString();
      System.out.println(var6);
      StringBuilder var7 = (new StringBuilder()).append("0000");
      String var8 = Integer.toString(var3, CharsKt.checkRadix(36));
      Intrinsics.checkNotNullExpressionValue(var8, "toString(this, checkRadix(radix))");
      var6 = var7.append(var8).toString();
      var6 = var6.substring(var6.length() - 4, var6.length());
      Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String…ing(startIndex, endIndex)");
      return var6;
   }

   public final String changeStringToUI(String var1) {
      Intrinsics.checkNotNullParameter(var1, "str");
      String var2 = Integer.toString(~(Integer.parseInt(var1, 16) | -4096), CharsKt.checkRadix(36));
      Intrinsics.checkNotNullExpressionValue(var2, "toString(this, checkRadix(radix))");
      var1 = var2;
      if (var2.length() < 2) {
         var1 = '0' + var2;
      }

      return var1;
   }

   public final byte[] changeUIToByteArray(String var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "ui1");
      Intrinsics.checkNotNullParameter(var2, "ui2");
      int var3 = Integer.parseInt(var1 + var2, 36);
      var1 = "changeUIToByteArray  " + var1 + var2 + ':' + Integer.toHexString(var3);
      System.out.println(var1);
      return new byte[]{(byte)(var3 >> 16 & 255), (byte)(var3 >> 8 & 255), (byte)(var3 & 255)};
   }

   public final String changeUIToString(String var1) {
      Intrinsics.checkNotNullParameter(var1, "ui");
      var1 = Integer.toHexString(~Integer.parseInt(var1, 36) & 4095);
      Intrinsics.checkNotNullExpressionValue(var1, "toHexString(Integer.pars…(ui, 36).inv() and 0xfff)");
      return var1;
   }
}
