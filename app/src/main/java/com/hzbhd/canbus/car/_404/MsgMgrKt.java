package com.hzbhd.canbus.car._404;

import com.hzbhd.canbus.CanbusMsgSender;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003¨\u0006\n"},
   d2 = {"sendTextInfo", "", "dataType", "", "text", "", "charsets", "Ljava/nio/charset/Charset;", "dataLength", "textLength", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   public static final void sendTextInfo(int var0, String var1, Charset var2, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "text");
      Intrinsics.checkNotNullParameter(var2, "charsets");
      byte[] var6;
      if (var1.length() >= var4) {
         var6 = (StringsKt.substring(var1, new IntRange(0, var4)) + "...").getBytes(var2);
         Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String).getBytes(charset)");
      } else {
         var6 = var1.getBytes(var2);
         Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String).getBytes(charset)");
      }

      byte var5 = (byte)var0;
      var6 = com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(var6, var3, 0, 4, (Object)null);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, var5}, var6));
   }

   // $FF: synthetic method
   public static void sendTextInfo$default(int var0, String var1, Charset var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var1 = "Unknown";
      }

      sendTextInfo(var0, var1, var2, var3, var4);
   }
}
