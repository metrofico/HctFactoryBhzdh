package kotlin.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u0000 \u00142\u00020\u00012\u00020\u0002:\u0001\u0014B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0016¢\u0006\u0002\u0010\rJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"Lkotlin/reflect/WildcardTypeImpl;", "Ljava/lang/reflect/WildcardType;", "Lkotlin/reflect/TypeImpl;", "upperBound", "Ljava/lang/reflect/Type;", "lowerBound", "(Ljava/lang/reflect/Type;Ljava/lang/reflect/Type;)V", "equals", "", "other", "", "getLowerBounds", "", "()[Ljava/lang/reflect/Type;", "getTypeName", "", "getUpperBounds", "hashCode", "", "toString", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class WildcardTypeImpl implements WildcardType, TypeImpl {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final WildcardTypeImpl STAR = new WildcardTypeImpl((Type)null, (Type)null);
   private final Type lowerBound;
   private final Type upperBound;

   public WildcardTypeImpl(Type var1, Type var2) {
      this.upperBound = var1;
      this.lowerBound = var2;
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof WildcardType) {
         Type[] var3 = this.getUpperBounds();
         WildcardType var4 = (WildcardType)var1;
         if (Arrays.equals(var3, var4.getUpperBounds()) && Arrays.equals(this.getLowerBounds(), var4.getLowerBounds())) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public Type[] getLowerBounds() {
      Type var1 = this.lowerBound;
      Type[] var2;
      if (var1 == null) {
         var2 = (Type[])((Object[])(new Type[0]));
      } else {
         var2 = new Type[]{var1};
      }

      return var2;
   }

   public String getTypeName() {
      String var1;
      if (this.lowerBound != null) {
         var1 = "? super " + TypesJVMKt.access$typeToString(this.lowerBound);
      } else {
         Type var2 = this.upperBound;
         if (var2 != null && !Intrinsics.areEqual((Object)var2, (Object)Object.class)) {
            var1 = "? extends " + TypesJVMKt.access$typeToString(this.upperBound);
         } else {
            var1 = "?";
         }
      }

      return var1;
   }

   public Type[] getUpperBounds() {
      Type var2 = this.upperBound;
      Type var1 = var2;
      if (var2 == null) {
         var1 = (Type)Object.class;
      }

      return new Type[]{var1};
   }

   public int hashCode() {
      return Arrays.hashCode(this.getUpperBounds()) ^ Arrays.hashCode(this.getLowerBounds());
   }

   public String toString() {
      return this.getTypeName();
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/reflect/WildcardTypeImpl$Companion;", "", "()V", "STAR", "Lkotlin/reflect/WildcardTypeImpl;", "getSTAR", "()Lkotlin/reflect/WildcardTypeImpl;", "kotlin-stdlib"},
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

      public final WildcardTypeImpl getSTAR() {
         return WildcardTypeImpl.STAR;
      }
   }
}
