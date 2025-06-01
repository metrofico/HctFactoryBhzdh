package com.hzbhd.canbus.config;

import com.hzbhd.commontools.utils.bhdGsonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004\u0012\u0013\u0014\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"},
   d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig;", "", "()V", "CONFIG_FILE_PATH", "", "TAG", "bean", "Lcom/hzbhd/canbus/config/CustomKeyConfig$CustomKey;", "getKeyList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "getLongKey", "", "key", "getShortKey", "setKeyList", "", "list", "CanIdKeyMap", "CustomKey", "Key", "KeyMap", "CanBusInfo_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CustomKeyConfig {
   private static final String CONFIG_FILE_PATH = "/bhd/appconfig/CanBus/CustomKey.json";
   public static final CustomKeyConfig INSTANCE = new CustomKeyConfig();
   private static final String TAG = "CustomKeyConfig";
   private static final CustomKey bean;

   // $FF: synthetic method
   public static boolean $r8$lambda$VQFvvUzcsy1hW7VCJi_vmGh034o(CanIdKeyMap var0) {
      return setKeyList$lambda_1(var0);
   }

   static {
      CustomKey var1 = (CustomKey)bhdGsonUtils.fromFilePath("/bhd/appconfig/CanBus/CustomKey.json", CustomKey.class);
      CustomKey var0 = var1;
      if (var1 == null) {
         var0 = new CustomKey((List)null, 1, (DefaultConstructorMarker)null);
      }

      bean = var0;
   }

   private CustomKeyConfig() {
   }

   private static final boolean setKeyList$lambda_1(CanIdKeyMap var0) {
      Intrinsics.checkNotNullParameter(var0, "it");
      boolean var1;
      if (var0.getCanId() == CanbusConfig.INSTANCE.getCanType()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final List getKeyList() {
      Iterator var5 = ((Iterable)bean.getCanIdList()).iterator();

      boolean var1;
      Object var3;
      Object var4;
      do {
         boolean var2 = var5.hasNext();
         var4 = null;
         if (!var2) {
            var3 = null;
            break;
         }

         var3 = var5.next();
         if (((CanIdKeyMap)var3).getCanId() == CanbusConfig.INSTANCE.getCanType()) {
            var1 = true;
         } else {
            var1 = false;
         }
      } while(!var1);

      CanIdKeyMap var6 = (CanIdKeyMap)var3;
      List var7 = (List)var4;
      if (var6 != null) {
         var7 = var6.getKeyList();
      }

      return var7;
   }

   public final int getLongKey(int var1) {
      List var4 = this.getKeyList();
      byte var3 = 0;
      int var2 = var3;
      if (var4 != null) {
         Iterator var5 = ((Iterable)var4).iterator();

         Object var7;
         label27: {
            while(var5.hasNext()) {
               var7 = var5.next();
               boolean var6;
               if (((KeyMap)var7).getInput() == var1) {
                  var6 = true;
               } else {
                  var6 = false;
               }

               if (var6) {
                  break label27;
               }
            }

            var7 = null;
         }

         KeyMap var8 = (KeyMap)var7;
         var2 = var3;
         if (var8 != null) {
            Key var9 = var8.getOutput();
            var2 = var3;
            if (var9 != null) {
               var2 = var9.getLong();
            }
         }
      }

      return var2;
   }

   public final int getShortKey(int var1) {
      List var3 = this.getKeyList();
      int var2 = var1;
      if (var3 != null) {
         Iterator var4 = ((Iterable)var3).iterator();

         Object var6;
         label27: {
            while(var4.hasNext()) {
               var6 = var4.next();
               boolean var5;
               if (((KeyMap)var6).getInput() == var1) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               if (var5) {
                  break label27;
               }
            }

            var6 = null;
         }

         KeyMap var7 = (KeyMap)var6;
         var2 = var1;
         if (var7 != null) {
            Key var8 = var7.getOutput();
            var2 = var1;
            if (var8 != null) {
               var2 = var8.getShort();
            }
         }
      }

      return var2;
   }

   public final void setKeyList(List var1) {
      Intrinsics.checkNotNullParameter(var1, "list");
      CustomKey var2 = bean;
      var2.getCanIdList().removeIf(new CustomKeyConfig$$ExternalSyntheticLambda0());
      if (((Collection)var1).isEmpty() ^ true) {
         var2.getCanIdList().add(new CanIdKeyMap(CanbusConfig.INSTANCE.getCanType(), var1));
      }

      bhdGsonUtils.toFileJson("/bhd/appconfig/CanBus/CustomKey.json", var2);
   }

   @Metadata(
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"},
      d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$CanIdKeyMap;", "", "canId", "", "keyList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "(ILjava/util/List;)V", "getCanId", "()I", "getKeyList", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class CanIdKeyMap {
      private final int canId;
      private final List keyList;

      public CanIdKeyMap(int var1, List var2) {
         Intrinsics.checkNotNullParameter(var2, "keyList");
         super();
         this.canId = var1;
         this.keyList = var2;
      }

      // $FF: synthetic method
      public static CanIdKeyMap copy$default(CanIdKeyMap var0, int var1, List var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = var0.canId;
         }

         if ((var3 & 2) != 0) {
            var2 = var0.keyList;
         }

         return var0.copy(var1, var2);
      }

      public final int component1() {
         return this.canId;
      }

      public final List component2() {
         return this.keyList;
      }

      public final CanIdKeyMap copy(int var1, List var2) {
         Intrinsics.checkNotNullParameter(var2, "keyList");
         return new CanIdKeyMap(var1, var2);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof CanIdKeyMap)) {
            return false;
         } else {
            CanIdKeyMap var2 = (CanIdKeyMap)var1;
            if (this.canId != var2.canId) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.keyList, (Object)var2.keyList);
            }
         }
      }

      public final int getCanId() {
         return this.canId;
      }

      public final List getKeyList() {
         return this.keyList;
      }

      public int hashCode() {
         return Integer.hashCode(this.canId) * 31 + this.keyList.hashCode();
      }

      public String toString() {
         return "CanIdKeyMap(canId=" + this.canId + ", keyList=" + this.keyList + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"},
      d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$CustomKey;", "", "canIdList", "", "Lcom/hzbhd/canbus/config/CustomKeyConfig$CanIdKeyMap;", "(Ljava/util/List;)V", "getCanIdList", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class CustomKey {
      private final List canIdList;

      public CustomKey() {
         this((List)null, 1, (DefaultConstructorMarker)null);
      }

      public CustomKey(List var1) {
         Intrinsics.checkNotNullParameter(var1, "canIdList");
         super();
         this.canIdList = var1;
      }

      // $FF: synthetic method
      public CustomKey(List var1, int var2, DefaultConstructorMarker var3) {
         if ((var2 & 1) != 0) {
            var1 = (List)(new ArrayList());
         }

         this(var1);
      }

      // $FF: synthetic method
      public static CustomKey copy$default(CustomKey var0, List var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.canIdList;
         }

         return var0.copy(var1);
      }

      public final List component1() {
         return this.canIdList;
      }

      public final CustomKey copy(List var1) {
         Intrinsics.checkNotNullParameter(var1, "canIdList");
         return new CustomKey(var1);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof CustomKey)) {
            return false;
         } else {
            CustomKey var2 = (CustomKey)var1;
            return Intrinsics.areEqual((Object)this.canIdList, (Object)var2.canIdList);
         }
      }

      public final List getCanIdList() {
         return this.canIdList;
      }

      public int hashCode() {
         return this.canIdList.hashCode();
      }

      public String toString() {
         return "CustomKey(canIdList=" + this.canIdList + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u0014"},
      d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;", "", "short", "", "long", "(II)V", "getLong", "()I", "setLong", "(I)V", "getShort", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Key {
      private int long;
      private final int short;

      public Key(int var1, int var2) {
         this.short = var1;
         this.long = var2;
      }

      // $FF: synthetic method
      public Key(int var1, int var2, int var3, DefaultConstructorMarker var4) {
         if ((var3 & 2) != 0) {
            var2 = 0;
         }

         this(var1, var2);
      }

      // $FF: synthetic method
      public static Key copy$default(Key var0, int var1, int var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = var0.short;
         }

         if ((var3 & 2) != 0) {
            var2 = var0.long;
         }

         return var0.copy(var1, var2);
      }

      public final int component1() {
         return this.short;
      }

      public final int component2() {
         return this.long;
      }

      public final Key copy(int var1, int var2) {
         return new Key(var1, var2);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Key)) {
            return false;
         } else {
            Key var2 = (Key)var1;
            if (this.short != var2.short) {
               return false;
            } else {
               return this.long == var2.long;
            }
         }
      }

      public final int getLong() {
         return this.long;
      }

      public final int getShort() {
         return this.short;
      }

      public int hashCode() {
         return Integer.hashCode(this.short) * 31 + Integer.hashCode(this.long);
      }

      public final void setLong(int var1) {
         this.long = var1;
      }

      public String toString() {
         return "Key(short=" + this.short + ", long=" + this.long + ')';
      }
   }

   @Metadata(
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"},
      d2 = {"Lcom/hzbhd/canbus/config/CustomKeyConfig$KeyMap;", "", "input", "", "output", "Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;", "(ILcom/hzbhd/canbus/config/CustomKeyConfig$Key;)V", "getInput", "()I", "setInput", "(I)V", "getOutput", "()Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;", "setOutput", "(Lcom/hzbhd/canbus/config/CustomKeyConfig$Key;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CanBusInfo_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class KeyMap {
      private int input;
      private Key output;

      public KeyMap(int var1, Key var2) {
         Intrinsics.checkNotNullParameter(var2, "output");
         super();
         this.input = var1;
         this.output = var2;
      }

      // $FF: synthetic method
      public KeyMap(int var1, Key var2, int var3, DefaultConstructorMarker var4) {
         if ((var3 & 1) != 0) {
            var1 = 0;
         }

         this(var1, var2);
      }

      // $FF: synthetic method
      public static KeyMap copy$default(KeyMap var0, int var1, Key var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = var0.input;
         }

         if ((var3 & 2) != 0) {
            var2 = var0.output;
         }

         return var0.copy(var1, var2);
      }

      public final int component1() {
         return this.input;
      }

      public final Key component2() {
         return this.output;
      }

      public final KeyMap copy(int var1, Key var2) {
         Intrinsics.checkNotNullParameter(var2, "output");
         return new KeyMap(var1, var2);
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof KeyMap)) {
            return false;
         } else {
            KeyMap var2 = (KeyMap)var1;
            if (this.input != var2.input) {
               return false;
            } else {
               return Intrinsics.areEqual((Object)this.output, (Object)var2.output);
            }
         }
      }

      public final int getInput() {
         return this.input;
      }

      public final Key getOutput() {
         return this.output;
      }

      public int hashCode() {
         return Integer.hashCode(this.input) * 31 + this.output.hashCode();
      }

      public final void setInput(int var1) {
         this.input = var1;
      }

      public final void setOutput(Key var1) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.output = var1;
      }

      public String toString() {
         return "KeyMap(input=" + this.input + ", output=" + this.output + ')';
      }
   }
}
