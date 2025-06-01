package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\b'\u0018\u0000 )*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001)B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0015\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0016H\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\u0018\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\rH\u0016J#\u0010#\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020'H\u0016J\u0012\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u001fH\u0002J\u001c\u0010&\u001a\u00020'2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016H\bR\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bX\u0088\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006*"},
   d2 = {"Lkotlin/collections/AbstractMap;", "K", "V", "", "()V", "_keys", "", "_values", "", "keys", "getKeys", "()Ljava/util/Set;", "size", "", "getSize", "()I", "values", "getValues", "()Ljava/util/Collection;", "containsEntry", "", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "key", "(Ljava/lang/Object;)Z", "containsValue", "value", "equals", "other", "", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "implFindEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", "isEmpty", "toString", "", "o", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class AbstractMap implements Map, KMappedMarker {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private volatile Set _keys;
   private volatile Collection _values;

   protected AbstractMap() {
   }

   private final Entry implFindEntry(Object var1) {
      Iterator var3 = ((Iterable)this.entrySet()).iterator();

      while(true) {
         if (var3.hasNext()) {
            Object var2 = var3.next();
            if (!Intrinsics.areEqual(((Entry)var2).getKey(), var1)) {
               continue;
            }

            var1 = var2;
            break;
         }

         var1 = null;
         break;
      }

      return (Entry)var1;
   }

   private final String toString(Object var1) {
      String var2;
      if (var1 == this) {
         var2 = "(this Map)";
      } else {
         var2 = String.valueOf(var1);
      }

      return var2;
   }

   private final String toString(Entry var1) {
      return this.toString(var1.getKey()) + '=' + this.toString(var1.getValue());
   }

   public void clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public final boolean containsEntry$kotlin_stdlib(Entry var1) {
      if (var1 == null) {
         return false;
      } else {
         Object var2 = var1.getKey();
         Object var3 = var1.getValue();
         Map var5 = (Map)this;
         Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
         Object var4 = var5.get(var2);
         if (!Intrinsics.areEqual(var3, var4)) {
            return false;
         } else {
            if (var4 == null) {
               Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.containsKey, *>");
               if (!var5.containsKey(var2)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean containsKey(Object var1) {
      boolean var2;
      if (this.implFindEntry(var1) != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean containsValue(Object var1) {
      Iterable var4 = (Iterable)this.entrySet();
      boolean var2 = var4 instanceof Collection;
      boolean var3 = false;
      if (var2 && ((Collection)var4).isEmpty()) {
         var2 = var3;
      } else {
         Iterator var5 = var4.iterator();

         while(true) {
            var2 = var3;
            if (!var5.hasNext()) {
               break;
            }

            if (Intrinsics.areEqual(((Entry)var5.next()).getValue(), var1)) {
               var2 = true;
               break;
            }
         }
      }

      return var2;
   }

   public boolean equals(Object var1) {
      boolean var4 = true;
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Map)) {
         return false;
      } else {
         int var2 = this.size();
         Map var5 = (Map)var1;
         if (var2 != var5.size()) {
            return false;
         } else {
            Iterable var6 = (Iterable)var5.entrySet();
            boolean var3;
            if (var6 instanceof Collection && ((Collection)var6).isEmpty()) {
               var3 = var4;
            } else {
               Iterator var7 = var6.iterator();

               while(true) {
                  var3 = var4;
                  if (!var7.hasNext()) {
                     break;
                  }

                  if (!this.containsEntry$kotlin_stdlib((Entry)var7.next())) {
                     var3 = false;
                     break;
                  }
               }
            }

            return var3;
         }
      }
   }

   public Object get(Object var1) {
      Entry var2 = this.implFindEntry(var1);
      if (var2 != null) {
         var1 = var2.getValue();
      } else {
         var1 = null;
      }

      return var1;
   }

   public abstract Set getEntries();

   public Set getKeys() {
      if (this._keys == null) {
         this._keys = (Set)(new AbstractSet(this) {
            final AbstractMap this$0;

            {
               this.this$0 = var1;
            }

            public boolean contains(Object var1) {
               return this.this$0.containsKey(var1);
            }

            public int getSize() {
               return this.this$0.size();
            }

            public Iterator iterator() {
               return (Iterator)(new Iterator(this.this$0.entrySet().iterator()) {
                  final Iterator $entryIterator;

                  {
                     this.$entryIterator = var1;
                  }

                  public boolean hasNext() {
                     return this.$entryIterator.hasNext();
                  }

                  public Object next() {
                     return ((Entry)this.$entryIterator.next()).getKey();
                  }

                  public void remove() {
                     throw new UnsupportedOperationException("Operation is not supported for read-only collection");
                  }
               });
            }
         });
      }

      Set var1 = this._keys;
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   public int getSize() {
      return this.entrySet().size();
   }

   public Collection getValues() {
      if (this._values == null) {
         this._values = (Collection)(new AbstractCollection(this) {
            final AbstractMap this$0;

            {
               this.this$0 = var1;
            }

            public boolean contains(Object var1) {
               return this.this$0.containsValue(var1);
            }

            public int getSize() {
               return this.this$0.size();
            }

            public Iterator iterator() {
               return (Iterator)(new Iterator(this.this$0.entrySet().iterator()) {
                  final Iterator $entryIterator;

                  {
                     this.$entryIterator = var1;
                  }

                  public boolean hasNext() {
                     return this.$entryIterator.hasNext();
                  }

                  public Object next() {
                     return ((Entry)this.$entryIterator.next()).getValue();
                  }

                  public void remove() {
                     throw new UnsupportedOperationException("Operation is not supported for read-only collection");
                  }
               });
            }
         });
      }

      Collection var1 = this._values;
      Intrinsics.checkNotNull(var1);
      return var1;
   }

   public int hashCode() {
      return this.entrySet().hashCode();
   }

   public boolean isEmpty() {
      boolean var1;
      if (this.size() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Object put(Object var1, Object var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public void putAll(Map var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public Object remove(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public String toString() {
      return CollectionsKt.joinToString$default((Iterable)this.entrySet(), (CharSequence)", ", (CharSequence)"{", (CharSequence)"}", 0, (CharSequence)null, (Function1)(new Function1(this) {
         final AbstractMap this$0;

         {
            this.this$0 = var1;
         }

         public final CharSequence invoke(Entry var1) {
            Intrinsics.checkNotNullParameter(var1, "it");
            return (CharSequence)this.this$0.toString(var1);
         }
      }), 24, (Object)null);
   }

   @Metadata(
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b\bJ\u001d\u0010\t\u001a\u00020\n2\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u000e\u0010\u0005\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000e¨\u0006\u000f"},
      d2 = {"Lkotlin/collections/AbstractMap$Companion;", "", "()V", "entryEquals", "", "e", "", "other", "entryEquals$kotlin_stdlib", "entryHashCode", "", "entryHashCode$kotlin_stdlib", "entryToString", "", "entryToString$kotlin_stdlib", "kotlin-stdlib"},
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

      public final boolean entryEquals$kotlin_stdlib(Entry var1, Object var2) {
         Intrinsics.checkNotNullParameter(var1, "e");
         boolean var3 = var2 instanceof Map.Entry;
         boolean var4 = false;
         if (!var3) {
            return false;
         } else {
            Object var5 = var1.getKey();
            Entry var6 = (Entry)var2;
            var3 = var4;
            if (Intrinsics.areEqual(var5, var6.getKey())) {
               var3 = var4;
               if (Intrinsics.areEqual(var1.getValue(), var6.getValue())) {
                  var3 = true;
               }
            }

            return var3;
         }
      }

      public final int entryHashCode$kotlin_stdlib(Entry var1) {
         Intrinsics.checkNotNullParameter(var1, "e");
         Object var4 = var1.getKey();
         int var3 = 0;
         int var2;
         if (var4 != null) {
            var2 = var4.hashCode();
         } else {
            var2 = 0;
         }

         Object var5 = var1.getValue();
         if (var5 != null) {
            var3 = var5.hashCode();
         }

         return var2 ^ var3;
      }

      public final String entryToString$kotlin_stdlib(Entry var1) {
         Intrinsics.checkNotNullParameter(var1, "e");
         return "" + var1.getKey() + '=' + var1.getValue();
      }
   }
}
