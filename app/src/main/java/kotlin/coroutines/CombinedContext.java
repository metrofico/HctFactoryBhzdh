package kotlin.coroutines;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001!B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0000H\u0002J\u0013\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J5\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u00102\u0006\u0010\u0011\u001a\u0002H\u00102\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00100\u0013H\u0016¢\u0006\u0002\u0010\u0014J(\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\b\b\u0000\u0010\u0016*\u00020\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00160\u0018H\u0096\u0002¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u00020\u00012\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0018H\u0016J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""},
   d2 = {"Lkotlin/coroutines/CombinedContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "left", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext$Element;)V", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "hashCode", "", "minusKey", "size", "toString", "", "writeReplace", "Serialized", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class CombinedContext implements CoroutineContext, Serializable {
   private final Element element;
   private final CoroutineContext left;

   public CombinedContext(CoroutineContext var1, Element var2) {
      Intrinsics.checkNotNullParameter(var1, "left");
      Intrinsics.checkNotNullParameter(var2, "element");
      super();
      this.left = var1;
      this.element = var2;
   }

   private final boolean contains(Element var1) {
      return Intrinsics.areEqual((Object)this.get(var1.getKey()), (Object)var1);
   }

   private final boolean containsAll(CombinedContext var1) {
      while(this.contains(var1.element)) {
         CoroutineContext var2 = var1.left;
         if (!(var2 instanceof CombinedContext)) {
            Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
            return this.contains((Element)var2);
         }

         var1 = (CombinedContext)var2;
      }

      return false;
   }

   private final int size() {
      int var1 = 2;
      CombinedContext var2 = this;

      while(true) {
         CoroutineContext var3 = var2.left;
         if (var3 instanceof CombinedContext) {
            var2 = (CombinedContext)var3;
         } else {
            var2 = null;
         }

         if (var2 == null) {
            return var1;
         }

         ++var1;
      }
   }

   private final Object writeReplace() {
      int var1 = this.size();
      CoroutineContext[] var2 = new CoroutineContext[var1];
      Ref.IntRef var3 = new Ref.IntRef();
      this.fold(Unit.INSTANCE, (Function2)(new Function2(var2, var3) {
         final CoroutineContext[] $elements;
         final Ref.IntRef $index;

         {
            this.$elements = var1;
            this.$index = var2;
         }

         public final void invoke(Unit var1, Element var2) {
            Intrinsics.checkNotNullParameter(var1, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(var2, "element");
            CoroutineContext[] var4 = this.$elements;
            int var3 = this.$index.element++;
            var4[var3] = (CoroutineContext)var2;
         }
      }));
      boolean var4;
      if (var3.element == var1) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (var4) {
         return new Serialized(var2);
      } else {
         throw new IllegalStateException("Check failed.".toString());
      }
   }

   public boolean equals(Object var1) {
      boolean var2;
      label28: {
         if (this != var1) {
            if (!(var1 instanceof CombinedContext)) {
               break label28;
            }

            CombinedContext var3 = (CombinedContext)var1;
            if (var3.size() != this.size() || !var3.containsAll(this)) {
               break label28;
            }
         }

         var2 = true;
         return var2;
      }

      var2 = false;
      return var2;
   }

   public Object fold(Object var1, Function2 var2) {
      Intrinsics.checkNotNullParameter(var2, "operation");
      return var2.invoke(this.left.fold(var1, var2), this.element);
   }

   public Element get(Key var1) {
      Intrinsics.checkNotNullParameter(var1, "key");
      CombinedContext var2 = this;

      while(true) {
         Element var3 = var2.element.get(var1);
         if (var3 != null) {
            return var3;
         }

         CoroutineContext var4 = var2.left;
         if (!(var4 instanceof CombinedContext)) {
            return var4.get(var1);
         }

         var2 = (CombinedContext)var4;
      }
   }

   public int hashCode() {
      return this.left.hashCode() + this.element.hashCode();
   }

   public CoroutineContext minusKey(Key var1) {
      Intrinsics.checkNotNullParameter(var1, "key");
      if (this.element.get(var1) != null) {
         return this.left;
      } else {
         CoroutineContext var2 = this.left.minusKey(var1);
         if (var2 == this.left) {
            var2 = (CoroutineContext)this;
         } else if (var2 == EmptyCoroutineContext.INSTANCE) {
            var2 = (CoroutineContext)this.element;
         } else {
            var2 = (CoroutineContext)(new CombinedContext(var2, this.element));
         }

         return var2;
      }
   }

   public CoroutineContext plus(CoroutineContext var1) {
      return DefaultImpls.plus(this, var1);
   }

   public String toString() {
      return '[' + (String)this.fold("", (Function2)null.INSTANCE) + ']';
   }

   @Metadata(
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \f2\u00060\u0001j\u0002`\u0002:\u0001\fB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0002R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\r"},
      d2 = {"Lkotlin/coroutines/CombinedContext$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "elements", "", "Lkotlin/coroutines/CoroutineContext;", "([Lkotlin/coroutines/CoroutineContext;)V", "getElements", "()[Lkotlin/coroutines/CoroutineContext;", "[Lkotlin/coroutines/CoroutineContext;", "readResolve", "", "Companion", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class Serialized implements Serializable {
      public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
      private static final long serialVersionUID = 0L;
      private final CoroutineContext[] elements;

      public Serialized(CoroutineContext[] var1) {
         Intrinsics.checkNotNullParameter(var1, "elements");
         super();
         this.elements = var1;
      }

      private final Object readResolve() {
         CoroutineContext[] var4 = this.elements;
         Object var3 = EmptyCoroutineContext.INSTANCE;
         int var2 = var4.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            CoroutineContext var5 = var4[var1];
            var3 = ((CoroutineContext)var3).plus(var5);
         }

         return var3;
      }

      public final CoroutineContext[] getElements() {
         return this.elements;
      }

      @Metadata(
         d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"},
         d2 = {"Lkotlin/coroutines/CombinedContext$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"},
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
   }
}
