package com.hzbhd.canbus.car._328;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000)\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B(\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001a\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007HÆ\u0003J3\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\"\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"},
   d2 = {"com/hzbhd/canbus/car/_328/MsgMgr$initParsers$1$SettingsParseHelper", "", "title", "", "parse", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getParse", "()Lkotlin/jvm/functions/Function1;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lcom/hzbhd/canbus/car/_328/MsgMgr$initParsers$1$SettingsParseHelper;", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgr$initParsers$1$SettingsParseHelper {
   private final Function1 parse;
   private final String title;

   public MsgMgr$initParsers$1$SettingsParseHelper(String var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "parse");
      super();
      this.title = var1;
      this.parse = var2;
   }

   // $FF: synthetic method
   public MsgMgr$initParsers$1$SettingsParseHelper(String var1, Function1 var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      this(var1, var2);
   }

   // $FF: synthetic method
   public static MsgMgr$initParsers$1$SettingsParseHelper copy$default(MsgMgr$initParsers$1$SettingsParseHelper var0, String var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.title;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.parse;
      }

      return var0.copy(var1, var2);
   }

   public final String component1() {
      return this.title;
   }

   public final Function1 component2() {
      return this.parse;
   }

   public final MsgMgr$initParsers$1$SettingsParseHelper copy(String var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var1, "title");
      Intrinsics.checkNotNullParameter(var2, "parse");
      return new MsgMgr$initParsers$1$SettingsParseHelper(var1, var2);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MsgMgr$initParsers$1$SettingsParseHelper)) {
         return false;
      } else {
         MsgMgr$initParsers$1$SettingsParseHelper var2 = (MsgMgr$initParsers$1$SettingsParseHelper)var1;
         if (!Intrinsics.areEqual((Object)this.title, (Object)var2.title)) {
            return false;
         } else {
            return Intrinsics.areEqual((Object)this.parse, (Object)var2.parse);
         }
      }
   }

   public final Function1 getParse() {
      return this.parse;
   }

   public final String getTitle() {
      return this.title;
   }

   public int hashCode() {
      return this.title.hashCode() * 31 + this.parse.hashCode();
   }

   public String toString() {
      return "SettingsParseHelper(title=" + this.title + ", parse=" + this.parse + ')';
   }
}
