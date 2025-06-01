package kotlin.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KClass;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 O2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001OB\u0011\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010F\u001a\u00020\u00122\b\u0010G\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020KH\u0016J\u0012\u0010L\u001a\u00020\u00122\b\u0010M\u001a\u0004\u0018\u00010\u0002H\u0017J\b\u0010N\u001a\u000201H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0018\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0018\u0010\u0015R\u001a\u0010\u001a\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001a\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001e\u0010\u0015R\u001a\u0010 \u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b!\u0010\u0014\u001a\u0004\b \u0010\u0015R\u001a\u0010\"\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b#\u0010\u0014\u001a\u0004\b\"\u0010\u0015R\u001a\u0010$\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b%\u0010\u0014\u001a\u0004\b$\u0010\u0015R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001e\u0010(\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030)0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0010R\u001e\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0010R\u0016\u0010-\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0016\u00100\u001a\u0004\u0018\u0001018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R(\u00104\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b5\u0010\u0014\u001a\u0004\b6\u0010\u000bR\u0016\u00107\u001a\u0004\u0018\u0001018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00103R \u00109\u001a\b\u0012\u0004\u0012\u00020:0\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b;\u0010\u0014\u001a\u0004\b<\u0010\u000bR \u0010=\u001a\b\u0012\u0004\u0012\u00020>0\b8VX\u0097\u0004¢\u0006\f\u0012\u0004\b?\u0010\u0014\u001a\u0004\b@\u0010\u000bR\u001c\u0010A\u001a\u0004\u0018\u00010B8VX\u0097\u0004¢\u0006\f\u0012\u0004\bC\u0010\u0014\u001a\u0004\bD\u0010E¨\u0006P"},
   d2 = {"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", "", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isFun", "isFun$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "isValue", "isValue$annotations", "getJClass", "()Ljava/lang/Class;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "getSealedSubclasses$annotations", "getSealedSubclasses", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "error", "", "hashCode", "", "isInstance", "value", "toString", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ClassReference implements KClass, ClassBasedDeclarationContainer {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final Map FUNCTION_CLASSES;
   private static final HashMap classFqNames;
   private static final HashMap primitiveFqNames;
   private static final HashMap primitiveWrapperFqNames;
   private static final Map simpleNames;
   private final Class jClass;

   static {
      int var0 = 0;
      Iterable var2 = (Iterable)CollectionsKt.listOf(new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
      Collection var1 = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault(var2, 10)));

      Iterator var8;
      for(var8 = var2.iterator(); var8.hasNext(); ++var0) {
         Object var3 = var8.next();
         if (var0 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         var1.add(TuplesKt.to((Class)var3, var0));
      }

      FUNCTION_CLASSES = MapsKt.toMap((Iterable)((List)var1));
      HashMap var9 = new HashMap();
      var9.put("boolean", "kotlin.Boolean");
      var9.put("char", "kotlin.Char");
      var9.put("byte", "kotlin.Byte");
      var9.put("short", "kotlin.Short");
      var9.put("int", "kotlin.Int");
      var9.put("float", "kotlin.Float");
      var9.put("long", "kotlin.Long");
      var9.put("double", "kotlin.Double");
      primitiveFqNames = var9;
      HashMap var11 = new HashMap();
      var11.put("java.lang.Boolean", "kotlin.Boolean");
      var11.put("java.lang.Character", "kotlin.Char");
      var11.put("java.lang.Byte", "kotlin.Byte");
      var11.put("java.lang.Short", "kotlin.Short");
      var11.put("java.lang.Integer", "kotlin.Int");
      var11.put("java.lang.Float", "kotlin.Float");
      var11.put("java.lang.Long", "kotlin.Long");
      var11.put("java.lang.Double", "kotlin.Double");
      primitiveWrapperFqNames = var11;
      HashMap var6 = new HashMap();
      var6.put("java.lang.Object", "kotlin.Any");
      var6.put("java.lang.String", "kotlin.String");
      var6.put("java.lang.CharSequence", "kotlin.CharSequence");
      var6.put("java.lang.Throwable", "kotlin.Throwable");
      var6.put("java.lang.Cloneable", "kotlin.Cloneable");
      var6.put("java.lang.Number", "kotlin.Number");
      var6.put("java.lang.Comparable", "kotlin.Comparable");
      var6.put("java.lang.Enum", "kotlin.Enum");
      var6.put("java.lang.annotation.Annotation", "kotlin.Annotation");
      var6.put("java.lang.Iterable", "kotlin.collections.Iterable");
      var6.put("java.util.Iterator", "kotlin.collections.Iterator");
      var6.put("java.util.Collection", "kotlin.collections.Collection");
      var6.put("java.util.List", "kotlin.collections.List");
      var6.put("java.util.Set", "kotlin.collections.Set");
      var6.put("java.util.ListIterator", "kotlin.collections.ListIterator");
      var6.put("java.util.Map", "kotlin.collections.Map");
      var6.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
      var6.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
      var6.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
      var6.putAll((Map)var9);
      var6.putAll((Map)var11);
      Collection var10 = var9.values();
      Intrinsics.checkNotNullExpressionValue(var10, "primitiveFqNames.values");
      var8 = ((Iterable)var10).iterator();

      while(var8.hasNext()) {
         Object var4 = var8.next();
         Map var13 = (Map)var6;
         String var5 = (String)var4;
         StringBuilder var16 = (new StringBuilder()).append("kotlin.jvm.internal.");
         Intrinsics.checkNotNullExpressionValue(var5, "kotlinName");
         Pair var17 = TuplesKt.to(var16.append(StringsKt.substringAfterLast$default(var5, '.', (String)null, 2, (Object)null)).append("CompanionObject").toString(), var5 + ".Companion");
         var13.put(var17.getFirst(), var17.getSecond());
      }

      Map var12 = (Map)var6;
      Iterator var14 = FUNCTION_CLASSES.entrySet().iterator();

      Map.Entry var15;
      while(var14.hasNext()) {
         var15 = (Map.Entry)var14.next();
         Class var18 = (Class)var15.getKey();
         var0 = ((Number)var15.getValue()).intValue();
         var6.put(var18.getName(), "kotlin.Function" + var0);
      }

      classFqNames = var6;
      var12 = (Map)var6;
      Map var7 = (Map)(new LinkedHashMap(MapsKt.mapCapacity(var12.size())));
      var14 = ((Iterable)var12.entrySet()).iterator();

      while(var14.hasNext()) {
         var15 = (Map.Entry)var14.next();
         var7.put(var15.getKey(), StringsKt.substringAfterLast$default((String)var15.getValue(), '.', (String)null, 2, (Object)null));
      }

      simpleNames = var7;
   }

   public ClassReference(Class var1) {
      Intrinsics.checkNotNullParameter(var1, "jClass");
      super();
      this.jClass = var1;
   }

   private final Void error() {
      throw new KotlinReflectionNotSupportedError();
   }

   // $FF: synthetic method
   public static void getSealedSubclasses$annotations() {
   }

   // $FF: synthetic method
   public static void getSupertypes$annotations() {
   }

   // $FF: synthetic method
   public static void getTypeParameters$annotations() {
   }

   // $FF: synthetic method
   public static void getVisibility$annotations() {
   }

   // $FF: synthetic method
   public static void isAbstract$annotations() {
   }

   // $FF: synthetic method
   public static void isCompanion$annotations() {
   }

   // $FF: synthetic method
   public static void isData$annotations() {
   }

   // $FF: synthetic method
   public static void isFinal$annotations() {
   }

   // $FF: synthetic method
   public static void isFun$annotations() {
   }

   // $FF: synthetic method
   public static void isInner$annotations() {
   }

   // $FF: synthetic method
   public static void isOpen$annotations() {
   }

   // $FF: synthetic method
   public static void isSealed$annotations() {
   }

   // $FF: synthetic method
   public static void isValue$annotations() {
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof ClassReference && Intrinsics.areEqual((Object)JvmClassMappingKt.getJavaObjectType((KClass)this), (Object)JvmClassMappingKt.getJavaObjectType((KClass)var1))) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public List getAnnotations() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public Collection getConstructors() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public Class getJClass() {
      return this.jClass;
   }

   public Collection getMembers() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public Collection getNestedClasses() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public Object getObjectInstance() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public String getQualifiedName() {
      return Companion.getClassQualifiedName(this.getJClass());
   }

   public List getSealedSubclasses() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public String getSimpleName() {
      return Companion.getClassSimpleName(this.getJClass());
   }

   public List getSupertypes() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public List getTypeParameters() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public KVisibility getVisibility() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public int hashCode() {
      return JvmClassMappingKt.getJavaObjectType((KClass)this).hashCode();
   }

   public boolean isAbstract() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isCompanion() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isData() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isFinal() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isFun() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isInner() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isInstance(Object var1) {
      return Companion.isInstance(var1, this.getJClass());
   }

   public boolean isOpen() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isSealed() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public boolean isValue() {
      this.error();
      throw new KotlinNothingValueException();
   }

   public String toString() {
      return this.getJClass().toString() + " (Kotlin reflection is not available)";
   }

   @Metadata(
      d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0005R&\u0010\u0003\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"},
      d2 = {"Lkotlin/jvm/internal/ClassReference$Companion;", "", "()V", "FUNCTION_CLASSES", "", "Ljava/lang/Class;", "Lkotlin/Function;", "", "classFqNames", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "primitiveFqNames", "primitiveWrapperFqNames", "simpleNames", "getClassQualifiedName", "jClass", "getClassSimpleName", "isInstance", "", "value", "kotlin-stdlib"},
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

      public final String getClassQualifiedName(Class var1) {
         Intrinsics.checkNotNullParameter(var1, "jClass");
         boolean var2 = var1.isAnonymousClass();
         Class var4 = null;
         String var3 = null;
         if (var2) {
            var3 = var4;
         } else if (var1.isLocalClass()) {
            var3 = var4;
         } else {
            String var6;
            if (var1.isArray()) {
               var4 = var1.getComponentType();
               String var5 = var3;
               if (var4.isPrimitive()) {
                  var6 = (String)ClassReference.classFqNames.get(var4.getName());
                  var5 = var3;
                  if (var6 != null) {
                     var5 = var6 + "Array";
                  }
               }

               var3 = var5;
               if (var5 == null) {
                  var3 = "kotlin.Array";
               }
            } else {
               var6 = (String)ClassReference.classFqNames.get(var1.getName());
               var3 = var6;
               if (var6 == null) {
                  var3 = var1.getCanonicalName();
               }
            }
         }

         return var3;
      }

      public final String getClassSimpleName(Class var1) {
         Intrinsics.checkNotNullParameter(var1, "jClass");
         boolean var2 = var1.isAnonymousClass();
         String var4 = "Array";
         String var3 = null;
         String var5 = null;
         if (var2) {
            var3 = var5;
         } else if (var1.isLocalClass()) {
            var5 = var1.getSimpleName();
            Method var8 = var1.getEnclosingMethod();
            if (var8 != null) {
               Intrinsics.checkNotNullExpressionValue(var5, "name");
               var4 = StringsKt.substringAfter$default(var5, var8.getName() + '$', (String)null, 2, (Object)null);
               var3 = var4;
               if (var4 != null) {
                  return var3;
               }
            }

            Constructor var6 = var1.getEnclosingConstructor();
            if (var6 != null) {
               Intrinsics.checkNotNullExpressionValue(var5, "name");
               var3 = StringsKt.substringAfter$default(var5, var6.getName() + '$', (String)null, 2, (Object)null);
            } else {
               Intrinsics.checkNotNullExpressionValue(var5, "name");
               var3 = StringsKt.substringAfter$default(var5, '$', (String)null, 2, (Object)null);
            }
         } else if (var1.isArray()) {
            Class var9 = var1.getComponentType();
            String var7 = var3;
            if (var9.isPrimitive()) {
               var5 = (String)ClassReference.simpleNames.get(var9.getName());
               var7 = var3;
               if (var5 != null) {
                  var7 = var5 + "Array";
               }
            }

            var3 = var7;
            if (var7 == null) {
               var3 = var4;
            }
         } else {
            var4 = (String)ClassReference.simpleNames.get(var1.getName());
            var3 = var4;
            if (var4 == null) {
               var3 = var1.getSimpleName();
            }
         }

         return var3;
      }

      public final boolean isInstance(Object var1, Class var2) {
         Intrinsics.checkNotNullParameter(var2, "jClass");
         Map var3 = ClassReference.FUNCTION_CLASSES;
         Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
         Integer var4 = (Integer)var3.get(var2);
         if (var4 != null) {
            return TypeIntrinsics.isFunctionOfArity(var1, ((Number)var4).intValue());
         } else {
            Class var5 = var2;
            if (var2.isPrimitive()) {
               var5 = JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(var2));
            }

            return var5.isInstance(var1);
         }
      }
   }
}
