package kotlin.reflect;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"},
   d2 = {"Lkotlin/reflect/KTypeProjection;", "", "variance", "Lkotlin/reflect/KVariance;", "type", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KVariance;Lkotlin/reflect/KType;)V", "getType", "()Lkotlin/reflect/KType;", "getVariance", "()Lkotlin/reflect/KVariance;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class KTypeProjection {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final KTypeProjection star = new KTypeProjection((KVariance)null, (KType)null);
   private final KType type;
   private final KVariance variance;

   public KTypeProjection(KVariance var1, KType var2) {
      this.variance = var1;
      this.type = var2;
      boolean var5 = true;
      boolean var3;
      if (var1 == null) {
         var3 = true;
      } else {
         var3 = false;
      }

      boolean var4;
      if (var2 == null) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (var3 == var4) {
         var3 = var5;
      } else {
         var3 = false;
      }

      if (!var3) {
         String var6;
         if (var1 == null) {
            var6 = "Star projection must have no type specified.";
         } else {
            var6 = "The projection variance " + var1 + " requires type to be specified.";
         }

         throw new IllegalArgumentException(var6.toString());
      }
   }

   @JvmStatic
   public static final KTypeProjection contravariant(KType var0) {
      return Companion.contravariant(var0);
   }

   // $FF: synthetic method
   public static KTypeProjection copy$default(KTypeProjection var0, KVariance var1, KType var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.variance;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.type;
      }

      return var0.copy(var1, var2);
   }

   @JvmStatic
   public static final KTypeProjection covariant(KType var0) {
      return Companion.covariant(var0);
   }

   @JvmStatic
   public static final KTypeProjection invariant(KType var0) {
      return Companion.invariant(var0);
   }

   public final KVariance component1() {
      return this.variance;
   }

   public final KType component2() {
      return this.type;
   }

   public final KTypeProjection copy(KVariance var1, KType var2) {
      return new KTypeProjection(var1, var2);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof KTypeProjection)) {
         return false;
      } else {
         KTypeProjection var2 = (KTypeProjection)var1;
         if (this.variance != var2.variance) {
            return false;
         } else {
            return Intrinsics.areEqual((Object)this.type, (Object)var2.type);
         }
      }
   }

   public final KType getType() {
      return this.type;
   }

   public final KVariance getVariance() {
      return this.variance;
   }

   public int hashCode() {
      KVariance var3 = this.variance;
      int var2 = 0;
      int var1;
      if (var3 == null) {
         var1 = 0;
      } else {
         var1 = var3.hashCode();
      }

      KType var4 = this.type;
      if (var4 != null) {
         var2 = var4.hashCode();
      }

      return var1 * 31 + var2;
   }

   public String toString() {
      KVariance var2 = this.variance;
      int var1;
      if (var2 == null) {
         var1 = -1;
      } else {
         var1 = WhenMappings.$EnumSwitchMapping$0[var2.ordinal()];
      }

      String var3;
      if (var1 != -1) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  throw new NoWhenBranchMatchedException();
               }

               var3 = "out " + this.type;
            } else {
               var3 = "in " + this.type;
            }
         } else {
            var3 = String.valueOf(this.type);
         }
      } else {
         var3 = "*";
      }

      return var3;
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002¨\u0006\u000e"},
      d2 = {"Lkotlin/reflect/KTypeProjection$Companion;", "", "()V", "STAR", "Lkotlin/reflect/KTypeProjection;", "getSTAR", "()Lkotlin/reflect/KTypeProjection;", "star", "getStar$annotations", "contravariant", "type", "Lkotlin/reflect/KType;", "covariant", "invariant", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      // $FF: synthetic method
      public static void getStar$annotations() {
      }

      @JvmStatic
      public final KTypeProjection contravariant(KType var1) {
         Intrinsics.checkNotNullParameter(var1, "type");
         return new KTypeProjection(KVariance.IN, var1);
      }

      @JvmStatic
      public final KTypeProjection covariant(KType var1) {
         Intrinsics.checkNotNullParameter(var1, "type");
         return new KTypeProjection(KVariance.OUT, var1);
      }

      public final KTypeProjection getSTAR() {
         return KTypeProjection.star;
      }

      @JvmStatic
      public final KTypeProjection invariant(KType var1) {
         Intrinsics.checkNotNullParameter(var1, "type");
         return new KTypeProjection(KVariance.INVARIANT, var1);
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
         int[] var0 = new int[KVariance.values().length];
         var0[KVariance.INVARIANT.ordinal()] = 1;
         var0[KVariance.IN.ordinal()] = 2;
         var0[KVariance.OUT.ordinal()] = 3;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
