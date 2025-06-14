package kotlin.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0096\u0002J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lkotlin/reflect/GenericArrayTypeImpl;", "Ljava/lang/reflect/GenericArrayType;", "Lkotlin/reflect/TypeImpl;", "elementType", "Ljava/lang/reflect/Type;", "(Ljava/lang/reflect/Type;)V", "equals", "", "other", "", "getGenericComponentType", "getTypeName", "", "hashCode", "", "toString", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class GenericArrayTypeImpl implements GenericArrayType, TypeImpl {
   private final Type elementType;

   public GenericArrayTypeImpl(Type var1) {
      Intrinsics.checkNotNullParameter(var1, "elementType");
      super();
      this.elementType = var1;
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof GenericArrayType && Intrinsics.areEqual((Object)this.getGenericComponentType(), (Object)((GenericArrayType)var1).getGenericComponentType())) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public Type getGenericComponentType() {
      return this.elementType;
   }

   public String getTypeName() {
      return TypesJVMKt.access$typeToString(this.elementType) + "[]";
   }

   public int hashCode() {
      return this.getGenericComponentType().hashCode();
   }

   public String toString() {
      return this.getTypeName();
   }
}
