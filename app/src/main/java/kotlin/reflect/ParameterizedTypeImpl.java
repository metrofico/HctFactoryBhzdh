package kotlin.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\u0010\tJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0016¢\u0006\u0002\u0010\u0011J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0019"},
   d2 = {"Lkotlin/reflect/ParameterizedTypeImpl;", "Ljava/lang/reflect/ParameterizedType;", "Lkotlin/reflect/TypeImpl;", "rawType", "Ljava/lang/Class;", "ownerType", "Ljava/lang/reflect/Type;", "typeArguments", "", "(Ljava/lang/Class;Ljava/lang/reflect/Type;Ljava/util/List;)V", "", "[Ljava/lang/reflect/Type;", "equals", "", "other", "", "getActualTypeArguments", "()[Ljava/lang/reflect/Type;", "getOwnerType", "getRawType", "getTypeName", "", "hashCode", "", "toString", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class ParameterizedTypeImpl implements ParameterizedType, TypeImpl {
   private final Type ownerType;
   private final Class rawType;
   private final Type[] typeArguments;

   public ParameterizedTypeImpl(Class var1, Type var2, List var3) {
      Intrinsics.checkNotNullParameter(var1, "rawType");
      Intrinsics.checkNotNullParameter(var3, "typeArguments");
      super();
      this.rawType = var1;
      this.ownerType = var2;
      Object[] var4 = ((Collection)var3).toArray(new Type[0]);
      Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
      this.typeArguments = (Type[])var4;
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof ParameterizedType) {
         Class var3 = this.rawType;
         ParameterizedType var4 = (ParameterizedType)var1;
         if (Intrinsics.areEqual((Object)var3, (Object)var4.getRawType()) && Intrinsics.areEqual((Object)this.ownerType, (Object)var4.getOwnerType()) && Arrays.equals(this.getActualTypeArguments(), var4.getActualTypeArguments())) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public Type[] getActualTypeArguments() {
      return this.typeArguments;
   }

   public Type getOwnerType() {
      return this.ownerType;
   }

   public Type getRawType() {
      return (Type)this.rawType;
   }

   public String getTypeName() {
      StringBuilder var2 = new StringBuilder();
      Type var3 = this.ownerType;
      if (var3 != null) {
         var2.append(TypesJVMKt.access$typeToString(var3));
         var2.append("$");
         var2.append(this.rawType.getSimpleName());
      } else {
         var2.append(TypesJVMKt.access$typeToString((Type)this.rawType));
      }

      Type[] var5 = this.typeArguments;
      boolean var1;
      if (var5.length == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (var1 ^ true) {
         ArraysKt.joinTo$default(var5, (Appendable)var2, (CharSequence)null, (CharSequence)"<", (CharSequence)">", 0, (CharSequence)null, (Function1)null.INSTANCE, 50, (Object)null);
      }

      String var4 = var2.toString();
      Intrinsics.checkNotNullExpressionValue(var4, "StringBuilder().apply(builderAction).toString()");
      return var4;
   }

   public int hashCode() {
      int var2 = this.rawType.hashCode();
      Type var3 = this.ownerType;
      int var1;
      if (var3 != null) {
         var1 = var3.hashCode();
      } else {
         var1 = 0;
      }

      return var2 ^ var1 ^ Arrays.hashCode(this.getActualTypeArguments());
   }

   public String toString() {
      return this.getTypeName();
   }
}
