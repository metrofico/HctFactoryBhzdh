package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(
   d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u001f\u0010\u001f\u001a\u00020 \"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0014*\u0006\u0012\u0002\b\u00030!¢\u0006\u0002\u0010\"\"'\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"5\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u000e\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t*\u0002H\b8Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"-\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018G¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"&\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\u0002H\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0015\";\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018Ç\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012\"+\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0012\"-\u0010\u001a\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0012\"+\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0014*\b\u0012\u0004\u0012\u0002H\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006#"},
   d2 = {"annotationClass", "Lkotlin/reflect/KClass;", "T", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "declaringJavaClass", "Ljava/lang/Class;", "E", "", "getDeclaringJavaClass$annotations", "(Ljava/lang/Enum;)V", "getDeclaringJavaClass", "(Ljava/lang/Enum;)Ljava/lang/Class;", "java", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class JvmClassMappingKt {
   public static final KClass getAnnotationClass(Annotation var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Class var1 = var0.annotationType();
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.annota…otation).annotationType()");
      KClass var2 = getKotlinClass(var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.reflect.KClass<out T of kotlin.jvm.JvmClassMappingKt.<get-annotationClass>>");
      return var2;
   }

   private static final Class getDeclaringJavaClass(Enum var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Class var1 = var0.getDeclaringClass();
      Intrinsics.checkNotNullExpressionValue(var1, "this as java.lang.Enum<E>).declaringClass");
      return var1;
   }

   // $FF: synthetic method
   public static void getDeclaringJavaClass$annotations(Enum var0) {
   }

   public static final Class getJavaClass(Object var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Class var1 = var0.getClass();
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaClass>>");
      return var1;
   }

   public static final Class getJavaClass(KClass var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Class var1 = ((ClassBasedDeclarationContainer)var0).getJClass();
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
      return var1;
   }

   // $FF: synthetic method
   public static void getJavaClass$annotations(KClass var0) {
   }

   public static final Class getJavaObjectType(KClass var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Class var1 = ((ClassBasedDeclarationContainer)var0).getJClass();
      if (!var1.isPrimitive()) {
         Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
         return var1;
      } else {
         String var2 = var1.getName();
         Class var3 = var1;
         if (var2 != null) {
            switch (var2.hashCode()) {
               case -1325958191:
                  if (!var2.equals("double")) {
                     var3 = var1;
                  } else {
                     var3 = Double.class;
                  }
                  break;
               case 104431:
                  if (!var2.equals("int")) {
                     var3 = var1;
                  } else {
                     var3 = Integer.class;
                  }
                  break;
               case 3039496:
                  if (!var2.equals("byte")) {
                     var3 = var1;
                  } else {
                     var3 = Byte.class;
                  }
                  break;
               case 3052374:
                  if (!var2.equals("char")) {
                     var3 = var1;
                  } else {
                     var3 = Character.class;
                  }
                  break;
               case 3327612:
                  if (!var2.equals("long")) {
                     var3 = var1;
                  } else {
                     var3 = Long.class;
                  }
                  break;
               case 3625364:
                  if (!var2.equals("void")) {
                     var3 = var1;
                  } else {
                     var3 = Void.class;
                  }
                  break;
               case 64711720:
                  if (!var2.equals("boolean")) {
                     var3 = var1;
                  } else {
                     var3 = Boolean.class;
                  }
                  break;
               case 97526364:
                  if (!var2.equals("float")) {
                     var3 = var1;
                  } else {
                     var3 = Float.class;
                  }
                  break;
               case 109413500:
                  if (!var2.equals("short")) {
                     var3 = var1;
                  } else {
                     var3 = Short.class;
                  }
                  break;
               default:
                  var3 = var1;
            }
         }

         Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaObjectType>>");
         return var3;
      }
   }

   public static final Class getJavaPrimitiveType(KClass var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Class var1 = ((ClassBasedDeclarationContainer)var0).getJClass();
      if (var1.isPrimitive()) {
         Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-javaPrimitiveType>>");
         return var1;
      } else {
         if (var2 != null) {
            switch (var2) {
               case "java.lang.Integer":
                  var1 = Integer.TYPE;
                  return var1;
               case "java.lang.Float":
                  var1 = Float.TYPE;
                  return var1;
               case "java.lang.Short":
                  var1 = Short.TYPE;
                  return var1;
               case "java.lang.Character":
                  var1 = Character.TYPE;
                  return var1;
               case "java.lang.Boolean":
                  var1 = Boolean.TYPE;
                  return var1;
               case "java.lang.Byte":
                  var1 = Byte.TYPE;
                  return var1;
               case "java.lang.Long":
                  var1 = Long.TYPE;
                  return var1;
               case "java.lang.Void":
                  var1 = Void.TYPE;
                  return var1;
               case "java.lang.Double":
                  var1 = Double.TYPE;
                  return var1;
            }
         }

         var1 = null;
         return var1;
      }
   }

   public static final KClass getKotlinClass(Class var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return Reflection.getOrCreateKotlinClass(var0);
   }

   public static final Class getRuntimeClassOfKClassInstance(KClass var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Class var1 = ((Object)var0).getClass();
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T of kotlin.jvm.JvmClassMappingKt.<get-javaClass>>>");
      return var1;
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.",
      replaceWith = @ReplaceWith(
   expression = "(this as Any).javaClass",
   imports = {}
)
   )
   public static void getRuntimeClassOfKClassInstance$annotations(KClass var0) {
   }

   // $FF: synthetic method
   public static final boolean isArrayOf(Object[] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.reifiedOperationMarker(4, "T");
      Class var1 = (Class)Object.class;
      Class var2 = var0.getClass();
      var1 = (Class)var2;
      return Object.class.isAssignableFrom(var2.getComponentType());
   }
}
