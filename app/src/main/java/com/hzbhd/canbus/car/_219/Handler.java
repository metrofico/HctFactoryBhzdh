package com.hzbhd.canbus.car._219;

import com.hzbhd.canbus.CanbusMsgSender;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J.\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nJ\u0016\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011¨\u0006\u0013"},
   d2 = {"Lcom/hzbhd/canbus/car/_219/Handler;", "", "()V", "sendMediaSourceData", "", "list", "", "", "sendMusicOrVideoData", "storagePath", "", "totalNumber", "currentNumber", "min", "sec", "sendRadioData", "band", "", "freq", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class Handler {
   public static final Handler INSTANCE = new Handler();

   private Handler() {
   }

   private final void sendMediaSourceData(List var1) {
      byte[] var2 = CollectionsKt.toByteArray((Collection)var1);
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 9}, var2));
   }

   public final void sendMusicOrVideoData(int var1, int var2, int var3, int var4, int var5) {
      List var7 = (List)(new ArrayList());
      byte var6;
      if (var1 == 9) {
         var6 = 12;
      } else {
         var6 = 13;
      }

      var7.add(var6);
      var7.add((byte)var2);
      var7.add((byte)var3);
      var7.add((byte)var4);
      var7.add((byte)var5);
      this.sendMediaSourceData(var7);
   }

   public final void sendRadioData(String var1, String var2) {
      byte var3;
      int var4;
      List var5;
      label51: {
         Intrinsics.checkNotNullParameter(var1, "band");
         Intrinsics.checkNotNullParameter(var2, "freq");
         var5 = (List)(new ArrayList());
         var3 = 2;
         var5.add((byte)2);
         var4 = var1.hashCode();
         if (var4 != 2092) {
            if (var4 != 2443) {
               if (var4 != 2763) {
                  if (var4 != 64901) {
                     switch (var4) {
                        case 69706:
                           if (var1.equals("FM1")) {
                              var3 = 0;
                              break label51;
                           }
                           break;
                        case 69707:
                           if (var1.equals("FM2")) {
                              var3 = 1;
                              break label51;
                           }
                           break;
                        case 69708:
                           if (var1.equals("FM3")) {
                              break label51;
                           }
                     }
                  } else if (var1.equals("AM1")) {
                     var3 = 4;
                     break label51;
                  }
               } else if (var1.equals("WB")) {
                  var3 = 6;
                  break label51;
               }
            } else if (var1.equals("LW")) {
               var3 = 5;
               break label51;
            }
         } else if (var1.equals("AM")) {
            var3 = 3;
            break label51;
         }

         return;
      }

      var5.add(var3);
      CharSequence var7 = (CharSequence)var2;
      var4 = StringsKt.indexOf$default(var7, ".", 0, false, 6, (Object)null);
      if (var4 != -1) {
         var1 = var2.substring(0, var4);
         Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String…ing(startIndex, endIndex)");
         var5.add(Byte.parseByte(var1));
         var1 = var2.substring(var4 + 1);
         Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).substring(startIndex)");
         var5.add(Byte.parseByte(var1));
      } else {
         String var6 = var2.substring(0, StringsKt.getLastIndex(var7) - 1);
         Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String…ing(startIndex, endIndex)");
         var5.add(Byte.parseByte(var6));
         var1 = var2.substring(StringsKt.getLastIndex(var7) - 1);
         Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.String).substring(startIndex)");
         var5.add(Byte.parseByte(var1));
      }

      this.sendMediaSourceData(var5);
   }
}
