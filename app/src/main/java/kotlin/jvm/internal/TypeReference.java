package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

@Metadata(
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\bH\u0002J\u0013\u0010$\u001a\u00020\b2\b\u0010%\u001a\u0004\u0018\u00010&H\u0096\u0002J\b\u0010'\u001a\u00020\fH\u0016J\b\u0010(\u001a\u00020\u001eH\u0016J\f\u0010\"\u001a\u00020\u001e*\u00020\u0006H\u0002R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u000b\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0019R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006*"},
   d2 = {"Lkotlin/jvm/internal/TypeReference;", "Lkotlin/reflect/KType;", "classifier", "Lkotlin/reflect/KClassifier;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "isMarkedNullable", "", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Z)V", "platformTypeUpperBound", "flags", "", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Lkotlin/reflect/KType;I)V", "annotations", "", "getAnnotations", "()Ljava/util/List;", "getArguments", "getClassifier", "()Lkotlin/reflect/KClassifier;", "getFlags$kotlin_stdlib$annotations", "()V", "getFlags$kotlin_stdlib", "()I", "()Z", "getPlatformTypeUpperBound$kotlin_stdlib$annotations", "getPlatformTypeUpperBound$kotlin_stdlib", "()Lkotlin/reflect/KType;", "arrayClassName", "", "Ljava/lang/Class;", "getArrayClassName", "(Ljava/lang/Class;)Ljava/lang/String;", "asString", "convertPrimitiveToWrapper", "equals", "other", "", "hashCode", "toString", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class TypeReference implements KType {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final int IS_MARKED_NULLABLE = 1;
   public static final int IS_MUTABLE_COLLECTION_TYPE = 2;
   public static final int IS_NOTHING_TYPE = 4;
   private final List arguments;
   private final KClassifier classifier;
   private final int flags;
   private final KType platformTypeUpperBound;

   public TypeReference(KClassifier var1, List var2, KType var3, int var4) {
      Intrinsics.checkNotNullParameter(var1, "classifier");
      Intrinsics.checkNotNullParameter(var2, "arguments");
      super();
      this.classifier = var1;
      this.arguments = var2;
      this.platformTypeUpperBound = var3;
      this.flags = var4;
   }

   public TypeReference(KClassifier var1, List var2, boolean var3) {
      Intrinsics.checkNotNullParameter(var1, "classifier");
      Intrinsics.checkNotNullParameter(var2, "arguments");
      this(var1, var2, (KType)null, var3);
   }

   private final String asString(KTypeProjection var1) {
      if (var1.getVariance() == null) {
         return "*";
      } else {
         KType var3 = var1.getType();
         TypeReference var7;
         if (var3 instanceof TypeReference) {
            var7 = (TypeReference)var3;
         } else {
            var7 = null;
         }

         String var8;
         label28: {
            if (var7 != null) {
               String var4 = var7.asString(true);
               var8 = var4;
               if (var4 != null) {
                  break label28;
               }
            }

            var8 = String.valueOf(var1.getType());
         }

         KVariance var5 = var1.getVariance();
         int var2 = WhenMappings.$EnumSwitchMapping$0[var5.ordinal()];
         String var6 = var8;
         if (var2 != 1) {
            if (var2 != 2) {
               if (var2 != 3) {
                  throw new NoWhenBranchMatchedException();
               }

               var6 = "out " + var8;
            } else {
               var6 = "in " + var8;
            }
         }

         return var6;
      }
   }

   private final String asString(boolean var1) {
      KClassifier var3 = this.getClassifier();
      boolean var2 = var3 instanceof KClass;
      Class var4 = null;
      KClass var6;
      if (var2) {
         var6 = (KClass)var3;
      } else {
         var6 = null;
      }

      if (var6 != null) {
         var4 = JvmClassMappingKt.getJavaClass(var6);
      }

      String var7;
      if (var4 == null) {
         var7 = this.getClassifier().toString();
      } else if ((this.flags & 4) != 0) {
         var7 = "kotlin.Nothing";
      } else if (var4.isArray()) {
         var7 = this.getArrayClassName(var4);
      } else if (var1 && var4.isPrimitive()) {
         var3 = this.getClassifier();
         Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
         var7 = JvmClassMappingKt.getJavaObjectType((KClass)var3).getName();
      } else {
         var7 = var4.getName();
      }

      var1 = this.getArguments().isEmpty();
      String var5 = "";
      String var8;
      if (var1) {
         var8 = "";
      } else {
         var8 = CollectionsKt.joinToString$default((Iterable)this.getArguments(), (CharSequence)", ", (CharSequence)"<", (CharSequence)">", 0, (CharSequence)null, (Function1)(new Function1(this) {
            final TypeReference this$0;

            {
               this.this$0 = var1;
            }

            public final CharSequence invoke(KTypeProjection var1) {
               Intrinsics.checkNotNullParameter(var1, "it");
               return (CharSequence)this.this$0.asString(var1);
            }
         }), 24, (Object)null);
      }

      if (this.isMarkedNullable()) {
         var5 = "?";
      }

      var8 = var7 + var8 + var5;
      KType var9 = this.platformTypeUpperBound;
      var7 = var8;
      if (var9 instanceof TypeReference) {
         var7 = ((TypeReference)var9).asString(true);
         if (Intrinsics.areEqual((Object)var7, (Object)var8)) {
            var7 = var8;
         } else if (Intrinsics.areEqual((Object)var7, (Object)(var8 + '?'))) {
            var7 = var8 + '!';
         } else {
            var7 = '(' + var8 + ".." + var7 + ')';
         }
      }

      return var7;
   }

   private final String getArrayClassName(Class var1) {
      String var2;
      if (Intrinsics.areEqual((Object)var1, (Object)boolean[].class)) {
         var2 = "kotlin.BooleanArray";
      } else if (Intrinsics.areEqual((Object)var1, (Object)char[].class)) {
         var2 = "kotlin.CharArray";
      } else if (Intrinsics.areEqual((Object)var1, (Object)byte[].class)) {
         var2 = "kotlin.ByteArray";
      } else if (Intrinsics.areEqual((Object)var1, (Object)short[].class)) {
         var2 = "kotlin.ShortArray";
      } else if (Intrinsics.areEqual((Object)var1, (Object)int[].class)) {
         var2 = "kotlin.IntArray";
      } else if (Intrinsics.areEqual((Object)var1, (Object)float[].class)) {
         var2 = "kotlin.FloatArray";
      } else if (Intrinsics.areEqual((Object)var1, (Object)long[].class)) {
         var2 = "kotlin.LongArray";
      } else if (Intrinsics.areEqual((Object)var1, (Object)double[].class)) {
         var2 = "kotlin.DoubleArray";
      } else {
         var2 = "kotlin.Array";
      }

      return var2;
   }

   // $FF: synthetic method
   public static void getFlags$kotlin_stdlib$annotations() {
   }

   // $FF: synthetic method
   public static void getPlatformTypeUpperBound$kotlin_stdlib$annotations() {
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof TypeReference) {
         KClassifier var3 = this.getClassifier();
         TypeReference var4 = (TypeReference)var1;
         if (Intrinsics.areEqual((Object)var3, (Object)var4.getClassifier()) && Intrinsics.areEqual((Object)this.getArguments(), (Object)var4.getArguments()) && Intrinsics.areEqual((Object)this.platformTypeUpperBound, (Object)var4.platformTypeUpperBound) && this.flags == var4.flags) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   public List getAnnotations() {
      return CollectionsKt.emptyList();
   }

   public List getArguments() {
      return this.arguments;
   }

   public KClassifier getClassifier() {
      return this.classifier;
   }

   public final int getFlags$kotlin_stdlib() {
      return this.flags;
   }

   public final KType getPlatformTypeUpperBound$kotlin_stdlib() {
      return this.platformTypeUpperBound;
   }

   public int hashCode() {
      return (this.getClassifier().hashCode() * 31 + this.getArguments().hashCode()) * 31 + Integer.valueOf(this.flags).hashCode();
   }

   public boolean isMarkedNullable() {
      int var1 = this.flags;
      boolean var2 = true;
      if ((var1 & 1) == 0) {
         var2 = false;
      }

      return var2;
   }

   public String toString() {
      return this.asString(false) + " (Kotlin reflection is not available)";
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0007"},
      d2 = {"Lkotlin/jvm/internal/TypeReference$Companion;", "", "()V", "IS_MARKED_NULLABLE", "", "IS_MUTABLE_COLLECTION_TYPE", "IS_NOTHING_TYPE", "kotlin-stdlib"},
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
