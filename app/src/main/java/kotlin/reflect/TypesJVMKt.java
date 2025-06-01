package kotlin.reflect;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a\"\u0010\n\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0003\u001a\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0003\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00078BX\u0083\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\b\u001a\u0004\b\u0005\u0010\t¨\u0006\u0015"},
   d2 = {"javaType", "Ljava/lang/reflect/Type;", "Lkotlin/reflect/KType;", "getJavaType$annotations", "(Lkotlin/reflect/KType;)V", "getJavaType", "(Lkotlin/reflect/KType;)Ljava/lang/reflect/Type;", "Lkotlin/reflect/KTypeProjection;", "(Lkotlin/reflect/KTypeProjection;)V", "(Lkotlin/reflect/KTypeProjection;)Ljava/lang/reflect/Type;", "createPossiblyInnerType", "jClass", "Ljava/lang/Class;", "arguments", "", "typeToString", "", "type", "computeJavaType", "forceWrapper", "", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class TypesJVMKt {
   // $FF: synthetic method
   public static final Type access$computeJavaType(KType var0, boolean var1) {
      return computeJavaType(var0, var1);
   }

   // $FF: synthetic method
   public static final String access$typeToString(Type var0) {
      return typeToString(var0);
   }

   private static final Type computeJavaType(KType var0, boolean var1) {
      KClassifier var3 = var0.getClassifier();
      if (var3 instanceof KTypeParameter) {
         return (Type)(new TypeVariableImpl((KTypeParameter)var3));
      } else if (var3 instanceof KClass) {
         KClass var7 = (KClass)var3;
         Object var8;
         if (var1) {
            var8 = JvmClassMappingKt.getJavaObjectType(var7);
         } else {
            var8 = JvmClassMappingKt.getJavaClass(var7);
         }

         List var4 = var0.getArguments();
         if (var4.isEmpty()) {
            return (Type)var8;
         } else if (((Class)var8).isArray()) {
            if (((Class)var8).getComponentType().isPrimitive()) {
               return (Type)var8;
            } else {
               KTypeProjection var9 = (KTypeProjection)CollectionsKt.singleOrNull(var4);
               if (var9 != null) {
                  KVariance var5 = var9.component1();
                  KType var10 = var9.component2();
                  int var2;
                  if (var5 == null) {
                     var2 = -1;
                  } else {
                     var2 = WhenMappings.$EnumSwitchMapping$0[var5.ordinal()];
                  }

                  Type var6;
                  if (var2 != -1 && var2 != 1) {
                     if (var2 != 2 && var2 != 3) {
                        throw new NoWhenBranchMatchedException();
                     }

                     Intrinsics.checkNotNull(var10);
                     var6 = computeJavaType$default(var10, false, 1, (Object)null);
                     if (!(var6 instanceof Class)) {
                        var8 = new GenericArrayTypeImpl(var6);
                     }

                     var6 = (Type)var8;
                  } else {
                     var6 = (Type)var8;
                  }

                  return var6;
               } else {
                  throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + var0);
               }
            }
         } else {
            return createPossiblyInnerType((Class)var8, var4);
         }
      } else {
         throw new UnsupportedOperationException("Unsupported type classifier: " + var0);
      }
   }

   // $FF: synthetic method
   static Type computeJavaType$default(KType var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      return computeJavaType(var0, var1);
   }

   private static final Type createPossiblyInnerType(Class var0, List var1) {
      Class var3 = var0.getDeclaringClass();
      Collection var5;
      if (var3 == null) {
         Iterable var7 = (Iterable)var1;
         var5 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var7, 10)));
         Iterator var8 = var7.iterator();

         while(var8.hasNext()) {
            var5.add(getJavaType((KTypeProjection)var8.next()));
         }

         return (Type)(new ParameterizedTypeImpl(var0, (Type)null, (List)var5));
      } else {
         Iterable var4;
         Type var6;
         Iterator var9;
         if (Modifier.isStatic(var0.getModifiers())) {
            var6 = (Type)var3;
            var4 = (Iterable)var1;
            var5 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var4, 10)));
            var9 = var4.iterator();

            while(var9.hasNext()) {
               var5.add(getJavaType((KTypeProjection)var9.next()));
            }

            return (Type)(new ParameterizedTypeImpl(var0, var6, (List)var5));
         } else {
            int var2 = var0.getTypeParameters().length;
            var6 = createPossiblyInnerType(var3, var1.subList(var2, var1.size()));
            var4 = (Iterable)var1.subList(0, var2);
            var5 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var4, 10)));
            var9 = var4.iterator();

            while(var9.hasNext()) {
               var5.add(getJavaType((KTypeProjection)var9.next()));
            }

            return (Type)(new ParameterizedTypeImpl(var0, var6, (List)var5));
         }
      }
   }

   public static final Type getJavaType(KType var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0 instanceof KTypeBase) {
         Type var1 = ((KTypeBase)var0).getJavaType();
         if (var1 != null) {
            return var1;
         }
      }

      return computeJavaType$default(var0, false, 1, (Object)null);
   }

   private static final Type getJavaType(KTypeProjection var0) {
      KVariance var2 = var0.getVariance();
      if (var2 == null) {
         return (Type)WildcardTypeImpl.Companion.getSTAR();
      } else {
         KType var3 = var0.getType();
         Intrinsics.checkNotNull(var3);
         int var1 = WhenMappings.$EnumSwitchMapping$0[var2.ordinal()];
         Type var4;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  throw new NoWhenBranchMatchedException();
               }

               var4 = (Type)(new WildcardTypeImpl(computeJavaType(var3, true), (Type)null));
            } else {
               var4 = computeJavaType(var3, true);
            }
         } else {
            var4 = (Type)(new WildcardTypeImpl((Type)null, computeJavaType(var3, true)));
         }

         return var4;
      }
   }

   // $FF: synthetic method
   public static void getJavaType$annotations(KType var0) {
   }

   // $FF: synthetic method
   private static void getJavaType$annotations(KTypeProjection var0) {
   }

   private static final String typeToString(Type var0) {
      String var3;
      if (var0 instanceof Class) {
         Class var1 = (Class)var0;
         if (var1.isArray()) {
            Sequence var2 = SequencesKt.generateSequence(var0, (Function1)null.INSTANCE);
            var3 = ((Class)SequencesKt.last(var2)).getName() + StringsKt.repeat((CharSequence)"[]", SequencesKt.count(var2));
         } else {
            var3 = var1.getName();
         }

         Intrinsics.checkNotNullExpressionValue(var3, "{\n        if (type.isArr…   } else type.name\n    }");
      } else {
         var3 = var0.toString();
      }

      return var3;
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
         var0[KVariance.IN.ordinal()] = 1;
         var0[KVariance.INVARIANT.ordinal()] = 2;
         var0[KVariance.OUT.ordinal()] = 3;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
