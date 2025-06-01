package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;

@Metadata(
   d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00060\u0006j\u0002`\u0007:\u0001VB\u0007\b\u0016¢\u0006\u0002\u0010\bB\u000f\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bBM\b\u0002\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r\u0012\u0006\u0010\u000e\u001a\u00020\n\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000¢\u0006\u0002\u0010\u0014J\u0015\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u001d\u0010\u001b\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J\u0016\u0010!\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J&\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010&\u001a\u00020\nH\u0002J\u001d\u0010'\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010 J\f\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)J\b\u0010*\u001a\u00020\u001eH\u0002J\b\u0010+\u001a\u00020\u001eH\u0016J\u0014\u0010,\u001a\u00020\u00112\n\u0010-\u001a\u0006\u0012\u0002\b\u00030)H\u0002J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\nH\u0002J\u0010\u00100\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\nH\u0002J\u0013\u00101\u001a\u00020\u00112\b\u0010-\u001a\u0004\u0018\u000102H\u0096\u0002J\u0016\u00103\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\nH\u0096\u0002¢\u0006\u0002\u00104J\b\u00105\u001a\u00020\nH\u0016J\u0015\u00106\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J\u0018\u00108\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0002J\b\u00109\u001a\u00020\u0011H\u0016J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;H\u0096\u0002J\u0015\u0010<\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J\u000e\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>H\u0016J\u0016\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>2\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u0015\u0010?\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u0016\u0010@\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J\u0015\u0010A\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\nH\u0016¢\u0006\u0002\u00104J\u0015\u0010B\u001a\u00028\u00002\u0006\u0010%\u001a\u00020\nH\u0002¢\u0006\u0002\u00104J\u0018\u0010C\u001a\u00020\u001e2\u0006\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020\nH\u0002J\u0016\u0010F\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J.\u0010G\u001a\u00020\n2\u0006\u0010D\u001a\u00020\n2\u0006\u0010E\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#2\u0006\u0010H\u001a\u00020\u0011H\u0002J\u001e\u0010I\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010JJ\u001e\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010L\u001a\u00020\n2\u0006\u0010M\u001a\u00020\nH\u0016J\u0015\u0010N\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\rH\u0016¢\u0006\u0002\u0010OJ'\u0010N\u001a\b\u0012\u0004\u0012\u0002HP0\r\"\u0004\b\u0001\u0010P2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002HP0\rH\u0016¢\u0006\u0002\u0010RJ\b\u0010S\u001a\u00020TH\u0016J\b\u0010U\u001a\u000202H\u0002R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006W"},
   d2 = {"Lkotlin/collections/builders/ListBuilder;", "E", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "initialCapacity", "", "(I)V", "array", "", "offset", "length", "isReadOnly", "", "backing", "root", "([Ljava/lang/Object;IIZLkotlin/collections/builders/ListBuilder;Lkotlin/collections/builders/ListBuilder;)V", "[Ljava/lang/Object;", "isEffectivelyReadOnly", "()Z", "size", "getSize", "()I", "add", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "", "addAllInternal", "i", "n", "addAtInternal", "build", "", "checkIsMutable", "clear", "contentEquals", "other", "ensureCapacity", "minCapacity", "ensureExtraCapacity", "equals", "", "get", "(I)Ljava/lang/Object;", "hashCode", "indexOf", "(Ljava/lang/Object;)I", "insertAtInternal", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "removeAtInternal", "removeRangeInternal", "rangeOffset", "rangeLength", "retainAll", "retainOrRemoveAllInternal", "retain", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "fromIndex", "toIndex", "toArray", "()[Ljava/lang/Object;", "T", "destination", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "writeReplace", "Itr", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ListBuilder extends AbstractMutableList implements List, RandomAccess, Serializable, KMutableList {
   private Object[] array;
   private final ListBuilder backing;
   private boolean isReadOnly;
   private int length;
   private int offset;
   private final ListBuilder root;

   public ListBuilder() {
      this(10);
   }

   public ListBuilder(int var1) {
      this(ListBuilderKt.arrayOfUninitializedElements(var1), 0, 0, false, (ListBuilder)null, (ListBuilder)null);
   }

   private ListBuilder(Object[] var1, int var2, int var3, boolean var4, ListBuilder var5, ListBuilder var6) {
      this.array = var1;
      this.offset = var2;
      this.length = var3;
      this.isReadOnly = var4;
      this.backing = var5;
      this.root = var6;
   }

   private final void addAllInternal(int var1, Collection var2, int var3) {
      ListBuilder var5 = this.backing;
      if (var5 != null) {
         var5.addAllInternal(var1, var2, var3);
         this.array = this.backing.array;
         this.length += var3;
      } else {
         this.insertAtInternal(var1, var3);
         int var4 = 0;

         for(Iterator var6 = var2.iterator(); var4 < var3; ++var4) {
            this.array[var1 + var4] = var6.next();
         }
      }

   }

   private final void addAtInternal(int var1, Object var2) {
      ListBuilder var3 = this.backing;
      if (var3 != null) {
         var3.addAtInternal(var1, var2);
         this.array = this.backing.array;
         ++this.length;
      } else {
         this.insertAtInternal(var1, 1);
         this.array[var1] = var2;
      }

   }

   private final void checkIsMutable() {
      if (this.isEffectivelyReadOnly()) {
         throw new UnsupportedOperationException();
      }
   }

   private final boolean contentEquals(List var1) {
      return ListBuilderKt.access$subarrayContentEquals(this.array, this.offset, this.length, var1);
   }

   private final void ensureCapacity(int var1) {
      if (this.backing == null) {
         if (var1 >= 0) {
            if (var1 > this.array.length) {
               var1 = ArrayDeque.Companion.newCapacity$kotlin_stdlib(this.array.length, var1);
               this.array = ListBuilderKt.copyOfUninitializedElements(this.array, var1);
            }

         } else {
            throw new OutOfMemoryError();
         }
      } else {
         throw new IllegalStateException();
      }
   }

   private final void ensureExtraCapacity(int var1) {
      this.ensureCapacity(this.length + var1);
   }

   private final void insertAtInternal(int var1, int var2) {
      this.ensureExtraCapacity(var2);
      Object[] var3 = this.array;
      ArraysKt.copyInto(var3, var3, var1 + var2, var1, this.offset + this.length);
      this.length += var2;
   }

   private final boolean isEffectivelyReadOnly() {
      boolean var1;
      if (!this.isReadOnly) {
         ListBuilder var2 = this.root;
         if (var2 == null || !var2.isReadOnly) {
            var1 = false;
            return var1;
         }
      }

      var1 = true;
      return var1;
   }

   private final Object removeAtInternal(int var1) {
      ListBuilder var2 = this.backing;
      if (var2 != null) {
         Object var5 = var2.removeAtInternal(var1);
         --this.length;
         return var5;
      } else {
         Object[] var4 = this.array;
         Object var3 = var4[var1];
         ArraysKt.copyInto(var4, var4, var1, var1 + 1, this.offset + this.length);
         ListBuilderKt.resetAt(this.array, this.offset + this.length - 1);
         --this.length;
         return var3;
      }
   }

   private final void removeRangeInternal(int var1, int var2) {
      ListBuilder var3 = this.backing;
      if (var3 != null) {
         var3.removeRangeInternal(var1, var2);
      } else {
         Object[] var4 = this.array;
         ArraysKt.copyInto(var4, var4, var1, var1 + var2, this.length);
         var4 = this.array;
         var1 = this.length;
         ListBuilderKt.resetRange(var4, var1 - var2, var1);
      }

      this.length -= var2;
   }

   private final int retainOrRemoveAllInternal(int var1, int var2, Collection var3, boolean var4) {
      ListBuilder var8 = this.backing;
      if (var8 != null) {
         var1 = var8.retainOrRemoveAllInternal(var1, var2, var3, var4);
         this.length -= var1;
         return var1;
      } else {
         int var5 = 0;
         int var6 = 0;

         while(var5 < var2) {
            Object[] var10 = this.array;
            int var7 = var1 + var5;
            if (var3.contains(var10[var7]) == var4) {
               var10 = this.array;
               ++var5;
               var10[var6 + var1] = var10[var7];
               ++var6;
            } else {
               ++var5;
            }
         }

         var5 = var2 - var6;
         Object[] var9 = this.array;
         ArraysKt.copyInto(var9, var9, var1 + var6, var2 + var1, this.length);
         var9 = this.array;
         var1 = this.length;
         ListBuilderKt.resetRange(var9, var1 - var5, var1);
         this.length -= var5;
         return var5;
      }
   }

   private final Object writeReplace() {
      if (this.isEffectivelyReadOnly()) {
         return new SerializedCollection((Collection)this, 0);
      } else {
         throw new NotSerializableException("The list cannot be serialized while it is being built.");
      }
   }

   public void add(int var1, Object var2) {
      this.checkIsMutable();
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(var1, this.length);
      this.addAtInternal(this.offset + var1, var2);
   }

   public boolean add(Object var1) {
      this.checkIsMutable();
      this.addAtInternal(this.offset + this.length, var1);
      return true;
   }

   public boolean addAll(int var1, Collection var2) {
      Intrinsics.checkNotNullParameter(var2, "elements");
      this.checkIsMutable();
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(var1, this.length);
      int var3 = var2.size();
      this.addAllInternal(this.offset + var1, var2, var3);
      boolean var4;
      if (var3 > 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4;
   }

   public boolean addAll(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      this.checkIsMutable();
      int var2 = var1.size();
      this.addAllInternal(this.offset + this.length, var1, var2);
      boolean var3;
      if (var2 > 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public final List build() {
      if (this.backing == null) {
         this.checkIsMutable();
         this.isReadOnly = true;
         return (List)this;
      } else {
         throw new IllegalStateException();
      }
   }

   public void clear() {
      this.checkIsMutable();
      this.removeRangeInternal(this.offset, this.length);
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 == this || var1 instanceof List && this.contentEquals((List)var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public Object get(int var1) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(var1, this.length);
      return this.array[this.offset + var1];
   }

   public int getSize() {
      return this.length;
   }

   public int hashCode() {
      return ListBuilderKt.access$subarrayContentHashCode(this.array, this.offset, this.length);
   }

   public int indexOf(Object var1) {
      for(int var2 = 0; var2 < this.length; ++var2) {
         if (Intrinsics.areEqual(this.array[this.offset + var2], var1)) {
            return var2;
         }
      }

      return -1;
   }

   public boolean isEmpty() {
      boolean var1;
      if (this.length == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Iterator iterator() {
      return (Iterator)(new Itr(this, 0));
   }

   public int lastIndexOf(Object var1) {
      for(int var2 = this.length - 1; var2 >= 0; --var2) {
         if (Intrinsics.areEqual(this.array[this.offset + var2], var1)) {
            return var2;
         }
      }

      return -1;
   }

   public ListIterator listIterator() {
      return (ListIterator)(new Itr(this, 0));
   }

   public ListIterator listIterator(int var1) {
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(var1, this.length);
      return (ListIterator)(new Itr(this, var1));
   }

   public boolean remove(Object var1) {
      this.checkIsMutable();
      int var2 = this.indexOf(var1);
      if (var2 >= 0) {
         this.remove(var2);
      }

      boolean var3;
      if (var2 >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean removeAll(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      this.checkIsMutable();
      int var3 = this.offset;
      int var2 = this.length;
      boolean var4 = false;
      if (this.retainOrRemoveAllInternal(var3, var2, var1, false) > 0) {
         var4 = true;
      }

      return var4;
   }

   public Object removeAt(int var1) {
      this.checkIsMutable();
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(var1, this.length);
      return this.removeAtInternal(this.offset + var1);
   }

   public boolean retainAll(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      this.checkIsMutable();
      int var3 = this.offset;
      int var2 = this.length;
      boolean var4 = true;
      if (this.retainOrRemoveAllInternal(var3, var2, var1, true) <= 0) {
         var4 = false;
      }

      return var4;
   }

   public Object set(int var1, Object var2) {
      this.checkIsMutable();
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(var1, this.length);
      Object[] var4 = this.array;
      int var3 = this.offset;
      Object var5 = var4[var3 + var1];
      var4[var3 + var1] = var2;
      return var5;
   }

   public List subList(int var1, int var2) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(var1, var2, this.length);
      Object[] var6 = this.array;
      int var3 = this.offset;
      boolean var4 = this.isReadOnly;
      ListBuilder var5 = this.root;
      if (var5 == null) {
         var5 = this;
      }

      return (List)(new ListBuilder(var6, var3 + var1, var2 - var1, var4, this, var5));
   }

   public Object[] toArray() {
      Object[] var2 = this.array;
      int var1 = this.offset;
      var2 = ArraysKt.copyOfRange(var2, var1, this.length + var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
      return var2;
   }

   public Object[] toArray(Object[] var1) {
      Intrinsics.checkNotNullParameter(var1, "destination");
      int var3 = var1.length;
      int var2 = this.length;
      Object[] var4;
      if (var3 < var2) {
         var4 = this.array;
         var3 = this.offset;
         var1 = Arrays.copyOfRange(var4, var3, var2 + var3, var1.getClass());
         Intrinsics.checkNotNullExpressionValue(var1, "copyOfRange(array, offse…h, destination.javaClass)");
         return var1;
      } else {
         var4 = this.array;
         Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.builders.ListBuilder.toArray>");
         var2 = this.offset;
         ArraysKt.copyInto(var4, var1, 0, var2, this.length + var2);
         var3 = var1.length;
         var2 = this.length;
         if (var3 > var2) {
            var1[var2] = null;
         }

         return var1;
      }
   }

   public String toString() {
      return ListBuilderKt.access$subarrayContentToString(this.array, this.offset, this.length);
   }

   @Metadata(
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000e\u0010\u0010\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\r\u0010\u0013\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u0015\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"},
      d2 = {"Lkotlin/collections/builders/ListBuilder$Itr;", "E", "", "list", "Lkotlin/collections/builders/ListBuilder;", "index", "", "(Lkotlin/collections/builders/ListBuilder;I)V", "lastIndex", "add", "", "element", "(Ljava/lang/Object;)V", "hasNext", "", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "previous", "previousIndex", "remove", "set", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class Itr implements ListIterator, KMutableListIterator {
      private int index;
      private int lastIndex;
      private final ListBuilder list;

      public Itr(ListBuilder var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "list");
         super();
         this.list = var1;
         this.index = var2;
         this.lastIndex = -1;
      }

      public void add(Object var1) {
         ListBuilder var3 = this.list;
         int var2 = this.index++;
         var3.add(var2, var1);
         this.lastIndex = -1;
      }

      public boolean hasNext() {
         boolean var1;
         if (this.index < this.list.length) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean hasPrevious() {
         boolean var1;
         if (this.index > 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public Object next() {
         if (this.index < this.list.length) {
            int var1 = this.index++;
            this.lastIndex = var1;
            return this.list.array[this.list.offset + this.lastIndex];
         } else {
            throw new NoSuchElementException();
         }
      }

      public int nextIndex() {
         return this.index;
      }

      public Object previous() {
         int var1 = this.index;
         if (var1 > 0) {
            --var1;
            this.index = var1;
            this.lastIndex = var1;
            return this.list.array[this.list.offset + this.lastIndex];
         } else {
            throw new NoSuchElementException();
         }
      }

      public int previousIndex() {
         return this.index - 1;
      }

      public void remove() {
         int var2 = this.lastIndex;
         boolean var1;
         if (var2 != -1) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            this.list.remove(var2);
            this.index = this.lastIndex;
            this.lastIndex = -1;
         } else {
            throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
         }
      }

      public void set(Object var1) {
         int var3 = this.lastIndex;
         boolean var2;
         if (var3 != -1) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            this.list.set(var3, var1);
         } else {
            throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
         }
      }
   }
}
