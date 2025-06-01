package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;

@Metadata(
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u00020\u001d2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\b\u0010\u001e\u001a\u00020\u0005H\u0016R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\f8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006 "},
   d2 = {"Lkotlin/jvm/internal/TypeParameterReference;", "Lkotlin/reflect/KTypeParameter;", "container", "", "name", "", "variance", "Lkotlin/reflect/KVariance;", "isReified", "", "(Ljava/lang/Object;Ljava/lang/String;Lkotlin/reflect/KVariance;Z)V", "bounds", "", "Lkotlin/reflect/KType;", "()Z", "getName", "()Ljava/lang/String;", "upperBounds", "getUpperBounds$annotations", "()V", "getUpperBounds", "()Ljava/util/List;", "getVariance", "()Lkotlin/reflect/KVariance;", "equals", "other", "hashCode", "", "setUpperBounds", "", "toString", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class TypeParameterReference implements KTypeParameter {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private volatile List bounds;
   private final Object container;
   private final boolean isReified;
   private final String name;
   private final KVariance variance;

   public TypeParameterReference(Object var1, String var2, KVariance var3, boolean var4) {
      Intrinsics.checkNotNullParameter(var2, "name");
      Intrinsics.checkNotNullParameter(var3, "variance");
      super();
      this.container = var1;
      this.name = var2;
      this.variance = var3;
      this.isReified = var4;
   }

   // $FF: synthetic method
   public static void getUpperBounds$annotations() {
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof TypeParameterReference) {
         Object var3 = this.container;
         TypeParameterReference var4 = (TypeParameterReference)var1;
         if (Intrinsics.areEqual(var3, var4.container) && Intrinsics.areEqual((Object)this.getName(), (Object)var4.getName())) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public String getName() {
      return this.name;
   }

   public List getUpperBounds() {
      List var2 = this.bounds;
      List var1 = var2;
      if (var2 == null) {
         var1 = CollectionsKt.listOf(Reflection.nullableTypeOf(Object.class));
         this.bounds = var1;
      }

      return var1;
   }

   public KVariance getVariance() {
      return this.variance;
   }

   public int hashCode() {
      Object var2 = this.container;
      int var1;
      if (var2 != null) {
         var1 = var2.hashCode();
      } else {
         var1 = 0;
      }

      return var1 * 31 + this.getName().hashCode();
   }

   public boolean isReified() {
      return this.isReified;
   }

   public final void setUpperBounds(List var1) {
      Intrinsics.checkNotNullParameter(var1, "upperBounds");
      if (this.bounds == null) {
         this.bounds = var1;
      } else {
         throw new IllegalStateException(("Upper bounds of type parameter '" + this + "' have already been initialized.").toString());
      }
   }

   public String toString() {
      return Companion.toString((KTypeParameter)this);
   }

   @Metadata(
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"},
      d2 = {"Lkotlin/jvm/internal/TypeParameterReference$Companion;", "", "()V", "toString", "", "typeParameter", "Lkotlin/reflect/KTypeParameter;", "kotlin-stdlib"},
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

      public final String toString(KTypeParameter var1) {
         Intrinsics.checkNotNullParameter(var1, "typeParameter");
         StringBuilder var4 = new StringBuilder();
         KVariance var3 = var1.getVariance();
         int var2 = WhenMappings.$EnumSwitchMapping$0[var3.ordinal()];
         if (var2 != 2) {
            if (var2 == 3) {
               var4.append("out ");
            }
         } else {
            var4.append("in ");
         }

         var4.append(var1.getName());
         String var5 = var4.toString();
         Intrinsics.checkNotNullExpressionValue(var5, "StringBuilder().apply(builderAction).toString()");
         return var5;
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
}
