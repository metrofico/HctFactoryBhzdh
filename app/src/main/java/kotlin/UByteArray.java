package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00012B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0004H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b!\u0010\u000bJ\u000f\u0010\"\u001a\u00020\u000fH\u0016¢\u0006\u0004\b#\u0010$J\u0019\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020&H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J#\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020/HÖ\u0001¢\u0006\u0004\b0\u00101R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r\u0088\u0001\u0007\u0092\u0001\u00020\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u00063"},
   d2 = {"Lkotlin/UByteArray;", "", "Lkotlin/UByte;", "size", "", "constructor-impl", "(I)[B", "storage", "", "([B)[B", "getSize-impl", "([B)I", "getStorage$annotations", "()V", "contains", "", "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "equals", "other", "", "equals-impl", "([BLjava/lang/Object;)Z", "get", "index", "get-w2LRezQ", "([BI)B", "hashCode", "hashCode-impl", "isEmpty", "isEmpty-impl", "([B)Z", "iterator", "", "iterator-impl", "([B)Ljava/util/Iterator;", "set", "", "value", "set-VurrAj0", "([BIB)V", "toString", "", "toString-impl", "([B)Ljava/lang/String;", "Iterator", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
@JvmInline
public final class UByteArray implements Collection, KMappedMarker {
   private final byte[] storage;

   // $FF: synthetic method
   private UByteArray(byte[] var1) {
      this.storage = var1;
   }

   // $FF: synthetic method
   public static final UByteArray box_impl(byte[] var0) {
      return new UByteArray(var0);
   }

   public static byte[] constructor_impl(int var0) {
      return constructor_impl(new byte[var0]);
   }

   public static byte[] constructor_impl(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "storage");
      return var0;
   }

   public static boolean contains_7apg3OU(byte[] var0, byte var1) {
      return ArraysKt.contains(var0, var1);
   }

   public static boolean containsAll_impl(byte[] var0, Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      Iterable var6 = (Iterable)var1;
      boolean var4 = ((Collection)var6).isEmpty();
      boolean var3 = false;
      if (!var4) {
         java.util.Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            Object var5 = var7.next();
            boolean var2;
            if (var5 instanceof UByte && ArraysKt.contains(var0, ((UByte)var5).unbox_impl())) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (!var2) {
               return var3;
            }
         }
      }

      var3 = true;
      return var3;
   }

   public static boolean equals_impl(byte[] var0, Object var1) {
      if (!(var1 instanceof UByteArray)) {
         return false;
      } else {
         return Intrinsics.areEqual((Object)var0, (Object)((UByteArray)var1).unbox_impl());
      }
   }

   public static final boolean equals_impl0(byte[] var0, byte[] var1) {
      return Intrinsics.areEqual((Object)var0, (Object)var1);
   }

   public static final byte get_w2LRezQ(byte[] var0, int var1) {
      return UByte.constructor_impl(var0[var1]);
   }

   public static int getSize_impl(byte[] var0) {
      return var0.length;
   }

   // $FF: synthetic method
   public static void getStorage$annotations() {
   }

   public static int hashCode_impl(byte[] var0) {
      return Arrays.hashCode(var0);
   }

   public static boolean isEmpty_impl(byte[] var0) {
      boolean var1;
      if (var0.length == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static java.util.Iterator iterator_impl(byte[] var0) {
      return (java.util.Iterator)(new Iterator(var0));
   }

   public static final void set_VurrAj0(byte[] var0, int var1, byte var2) {
      var0[var1] = var2;
   }

   public static String toString_impl(byte[] var0) {
      return "UByteArray(storage=" + Arrays.toString(var0) + ')';
   }

   public boolean add_7apg3OU(byte var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean addAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public void clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean contains_7apg3OU(byte var1) {
      return contains_7apg3OU(this.storage, var1);
   }

   public boolean containsAll(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "elements");
      return containsAll_impl(this.storage, var1);
   }

   public boolean equals(Object var1) {
      return equals_impl(this.storage, var1);
   }

   public int getSize() {
      return getSize_impl(this.storage);
   }

   public int hashCode() {
      return hashCode_impl(this.storage);
   }

   public boolean isEmpty() {
      return isEmpty_impl(this.storage);
   }

   public java.util.Iterator iterator() {
      return iterator_impl(this.storage);
   }

   public boolean remove(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean removeAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public boolean retainAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   public Object[] toArray() {
      return CollectionToArray.toArray((Collection)this);
   }

   public Object[] toArray(Object[] var1) {
      Intrinsics.checkNotNullParameter(var1, "array");
      return CollectionToArray.toArray((Collection)this, var1);
   }

   public String toString() {
      return toString_impl(this.storage);
   }

   // $FF: synthetic method
   public final byte[] unbox_impl() {
      return this.storage;
   }

   @Metadata(
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\tH\u0096\u0002J\u0016\u0010\n\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000ø\u0001\u0001\u0082\u0002\b\n\u0002\b!\n\u0002\b\u0019¨\u0006\r"},
      d2 = {"Lkotlin/UByteArray$Iterator;", "", "Lkotlin/UByte;", "array", "", "([B)V", "index", "", "hasNext", "", "next", "next-w2LRezQ", "()B", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class Iterator implements java.util.Iterator, KMappedMarker {
      private final byte[] array;
      private int index;

      public Iterator(byte[] var1) {
         Intrinsics.checkNotNullParameter(var1, "array");
         super();
         this.array = var1;
      }

      public boolean hasNext() {
         boolean var1;
         if (this.index < this.array.length) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public byte next_w2LRezQ() {
         int var1 = this.index;
         byte[] var2 = this.array;
         if (var1 < var2.length) {
            this.index = var1 + 1;
            return UByte.constructor_impl(var2[var1]);
         } else {
            throw new NoSuchElementException(String.valueOf(this.index));
         }
      }

      public void remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
