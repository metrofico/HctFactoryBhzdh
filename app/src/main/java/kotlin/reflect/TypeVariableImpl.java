package kotlin.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\u0011\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\b\b\u0000\u0010\u0010*\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0012¢\u0006\u0002\u0010\u0013J\u0011\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\fH\u0016¢\u0006\u0002\u0010\u0017J\u0011\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u0019\u001a\u00020\u0002H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "},
   d2 = {"Lkotlin/reflect/TypeVariableImpl;", "Ljava/lang/reflect/TypeVariable;", "Ljava/lang/reflect/GenericDeclaration;", "Lkotlin/reflect/TypeImpl;", "typeParameter", "Lkotlin/reflect/KTypeParameter;", "(Lkotlin/reflect/KTypeParameter;)V", "equals", "", "other", "", "getAnnotatedBounds", "", "", "()[Ljava/lang/annotation/Annotation;", "getAnnotation", "T", "annotationClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;", "getAnnotations", "getBounds", "Ljava/lang/reflect/Type;", "()[Ljava/lang/reflect/Type;", "getDeclaredAnnotations", "getGenericDeclaration", "getName", "", "getTypeName", "hashCode", "", "toString", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class TypeVariableImpl implements TypeVariable, TypeImpl {
   private final KTypeParameter typeParameter;

   public TypeVariableImpl(KTypeParameter var1) {
      Intrinsics.checkNotNullParameter(var1, "typeParameter");
      super();
      this.typeParameter = var1;
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof TypeVariable) {
         String var3 = this.getName();
         TypeVariable var4 = (TypeVariable)var1;
         if (Intrinsics.areEqual((Object)var3, (Object)var4.getName()) && Intrinsics.areEqual((Object)this.getGenericDeclaration(), (Object)var4.getGenericDeclaration())) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public final Annotation[] getAnnotatedBounds() {
      return (Annotation[])((Object[])(new Annotation[0]));
   }

   public final Annotation getAnnotation(Class var1) {
      Intrinsics.checkNotNullParameter(var1, "annotationClass");
      return null;
   }

   public final Annotation[] getAnnotations() {
      return (Annotation[])((Object[])(new Annotation[0]));
   }

   public Type[] getBounds() {
      Iterable var2 = (Iterable)this.typeParameter.getUpperBounds();
      Collection var1 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var2, 10)));
      Iterator var4 = var2.iterator();

      while(var4.hasNext()) {
         var1.add(TypesJVMKt.access$computeJavaType((KType)var4.next(), true));
      }

      Object[] var3 = ((Collection)((List)var1)).toArray(new Type[0]);
      Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
      return (Type[])var3;
   }

   public final Annotation[] getDeclaredAnnotations() {
      return (Annotation[])((Object[])(new Annotation[0]));
   }

   public GenericDeclaration getGenericDeclaration() {
      String var1 = "getGenericDeclaration() is not yet supported for type variables created from KType: " + this.typeParameter;
      throw new NotImplementedError("An operation is not implemented: " + var1);
   }

   public String getName() {
      return this.typeParameter.getName();
   }

   public String getTypeName() {
      return this.getName();
   }

   public int hashCode() {
      return this.getName().hashCode() ^ this.getGenericDeclaration().hashCode();
   }

   public String toString() {
      return this.getTypeName();
   }
}
