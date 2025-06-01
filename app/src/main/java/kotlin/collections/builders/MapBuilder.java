package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 {*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u00060\u0004j\u0002`\u0005:\u0007{|}~\u007f\u0080\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tBE\b\u0002\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0011\u001a\u00020\b¢\u0006\u0002\u0010\u0012J\u0017\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0000¢\u0006\u0004\b4\u00105J\u0013\u00106\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0002¢\u0006\u0002\u00107J\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000109J\r\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\b\u0010=\u001a\u00020;H\u0016J\b\u0010>\u001a\u00020;H\u0002J\u0019\u0010?\u001a\u00020!2\n\u0010@\u001a\u0006\u0012\u0002\b\u00030AH\u0000¢\u0006\u0002\bBJ!\u0010C\u001a\u00020!2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0000¢\u0006\u0002\bFJ\u0015\u0010G\u001a\u00020!2\u0006\u00103\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010HJ\u0015\u0010I\u001a\u00020!2\u0006\u0010J\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010HJ\u0018\u0010K\u001a\u00020!2\u000e\u0010L\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u000309H\u0002J\u0010\u0010M\u001a\u00020;2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u0010\u0010N\u001a\u00020;2\u0006\u0010O\u001a\u00020\bH\u0002J\u0019\u0010P\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010QH\u0000¢\u0006\u0002\bRJ\u0013\u0010S\u001a\u00020!2\b\u0010L\u001a\u0004\u0018\u00010TH\u0096\u0002J\u0015\u0010U\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00105J\u0015\u0010V\u001a\u00020\b2\u0006\u0010J\u001a\u00028\u0001H\u0002¢\u0006\u0002\u00105J\u0018\u0010W\u001a\u0004\u0018\u00018\u00012\u0006\u00103\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010XJ\u0015\u0010Y\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00105J\b\u0010Z\u001a\u00020\bH\u0016J\b\u0010[\u001a\u00020!H\u0016J\u0019\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010]H\u0000¢\u0006\u0002\b^J\u001f\u0010_\u001a\u0004\u0018\u00018\u00012\u0006\u00103\u001a\u00028\u00002\u0006\u0010J\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010`J\u001e\u0010a\u001a\u00020;2\u0014\u0010b\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000109H\u0016J\"\u0010c\u001a\u00020!2\u0018\u0010b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010E0AH\u0002J\u001c\u0010d\u001a\u00020!2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0002J\u0010\u0010e\u001a\u00020!2\u0006\u0010f\u001a\u00020\bH\u0002J\u0010\u0010g\u001a\u00020;2\u0006\u0010h\u001a\u00020\bH\u0002J\u0017\u0010i\u001a\u0004\u0018\u00018\u00012\u0006\u00103\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010XJ!\u0010j\u001a\u00020!2\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0000¢\u0006\u0002\bkJ\u0010\u0010l\u001a\u00020;2\u0006\u0010m\u001a\u00020\bH\u0002J\u0017\u0010n\u001a\u00020\b2\u0006\u00103\u001a\u00028\u0000H\u0000¢\u0006\u0004\bo\u00105J\u0010\u0010p\u001a\u00020;2\u0006\u0010q\u001a\u00020\bH\u0002J\u0017\u0010r\u001a\u00020!2\u0006\u0010s\u001a\u00028\u0001H\u0000¢\u0006\u0004\bt\u0010HJ\b\u0010u\u001a\u00020vH\u0016J\u0019\u0010w\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010xH\u0000¢\u0006\u0002\byJ\b\u0010z\u001a\u00020TH\u0002R\u0014\u0010\u0013\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R&\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00180\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0015R\u001e\u0010\"\u001a\u00020!2\u0006\u0010 \u001a\u00020!@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u001aR\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010'R\u0016\u0010(\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010*\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0015R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010'R\u0016\u00100\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0081\u0001"},
   d2 = {"Lkotlin/collections/builders/MapBuilder;", "K", "V", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "initialCapacity", "", "(I)V", "keysArray", "", "valuesArray", "presenceArray", "", "hashArray", "maxProbeDistance", "length", "([Ljava/lang/Object;[Ljava/lang/Object;[I[III)V", "capacity", "getCapacity", "()I", "entries", "", "", "getEntries", "()Ljava/util/Set;", "entriesView", "Lkotlin/collections/builders/MapBuilderEntries;", "hashShift", "hashSize", "getHashSize", "<set-?>", "", "isReadOnly", "isReadOnly$kotlin_stdlib", "()Z", "keys", "getKeys", "[Ljava/lang/Object;", "keysView", "Lkotlin/collections/builders/MapBuilderKeys;", "size", "getSize", "values", "", "getValues", "()Ljava/util/Collection;", "valuesView", "Lkotlin/collections/builders/MapBuilderValues;", "addKey", "key", "addKey$kotlin_stdlib", "(Ljava/lang/Object;)I", "allocateValuesArray", "()[Ljava/lang/Object;", "build", "", "checkIsMutable", "", "checkIsMutable$kotlin_stdlib", "clear", "compact", "containsAllEntries", "m", "", "containsAllEntries$kotlin_stdlib", "containsEntry", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "(Ljava/lang/Object;)Z", "containsValue", "value", "contentEquals", "other", "ensureCapacity", "ensureExtraCapacity", "n", "entriesIterator", "Lkotlin/collections/builders/MapBuilder$EntriesItr;", "entriesIterator$kotlin_stdlib", "equals", "", "findKey", "findValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hash", "hashCode", "isEmpty", "keysIterator", "Lkotlin/collections/builders/MapBuilder$KeysItr;", "keysIterator$kotlin_stdlib", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "putAllEntries", "putEntry", "putRehash", "i", "rehash", "newHashSize", "remove", "removeEntry", "removeEntry$kotlin_stdlib", "removeHashAt", "removedHash", "removeKey", "removeKey$kotlin_stdlib", "removeKeyAt", "index", "removeValue", "element", "removeValue$kotlin_stdlib", "toString", "", "valuesIterator", "Lkotlin/collections/builders/MapBuilder$ValuesItr;", "valuesIterator$kotlin_stdlib", "writeReplace", "Companion", "EntriesItr", "EntryRef", "Itr", "KeysItr", "ValuesItr", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MapBuilder implements Map, Serializable, KMutableMap {
   private static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @Deprecated
   private static final int INITIAL_CAPACITY = 8;
   @Deprecated
   private static final int INITIAL_MAX_PROBE_DISTANCE = 2;
   @Deprecated
   private static final int MAGIC = -1640531527;
   @Deprecated
   private static final int TOMBSTONE = -1;
   private MapBuilderEntries entriesView;
   private int[] hashArray;
   private int hashShift;
   private boolean isReadOnly;
   private Object[] keysArray;
   private MapBuilderKeys keysView;
   private int length;
   private int maxProbeDistance;
   private int[] presenceArray;
   private int size;
   private Object[] valuesArray;
   private MapBuilderValues valuesView;

   public MapBuilder() {
      this(8);
   }

   public MapBuilder(int var1) {
      this(ListBuilderKt.arrayOfUninitializedElements(var1), (Object[])null, new int[var1], new int[Companion.computeHashSize(var1)], 2, 0);
   }

   private MapBuilder(Object[] var1, Object[] var2, int[] var3, int[] var4, int var5, int var6) {
      this.keysArray = var1;
      this.valuesArray = var2;
      this.presenceArray = var3;
      this.hashArray = var4;
      this.maxProbeDistance = var5;
      this.length = var6;
      this.hashShift = Companion.computeShift(this.getHashSize());
   }

   private final Object[] allocateValuesArray() {
      Object[] var1 = this.valuesArray;
      if (var1 != null) {
         return var1;
      } else {
         var1 = ListBuilderKt.arrayOfUninitializedElements(this.getCapacity());
         this.valuesArray = var1;
         return var1;
      }
   }

   private final void compact() {
      Object[] var4 = this.valuesArray;
      int var2 = 0;
      int var1 = 0;

      while(true) {
         int var3 = this.length;
         if (var2 >= var3) {
            ListBuilderKt.resetRange(this.keysArray, var1, var3);
            if (var4 != null) {
               ListBuilderKt.resetRange(var4, var1, this.length);
            }

            this.length = var1;
            return;
         }

         var3 = var1;
         if (this.presenceArray[var2] >= 0) {
            Object[] var5 = this.keysArray;
            var5[var1] = var5[var2];
            if (var4 != null) {
               var4[var1] = var4[var2];
            }

            var3 = var1 + 1;
         }

         ++var2;
         var1 = var3;
      }
   }

   private final boolean contentEquals(Map var1) {
      boolean var2;
      if (this.size() == var1.size() && this.containsAllEntries$kotlin_stdlib((Collection)var1.entrySet())) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private final void ensureCapacity(int var1) {
      if (var1 >= 0) {
         if (var1 > this.getCapacity()) {
            int var2 = this.getCapacity() * 3 / 2;
            if (var1 <= var2) {
               var1 = var2;
            }

            this.keysArray = ListBuilderKt.copyOfUninitializedElements(this.keysArray, var1);
            Object[] var3 = this.valuesArray;
            if (var3 != null) {
               var3 = ListBuilderKt.copyOfUninitializedElements(var3, var1);
            } else {
               var3 = null;
            }

            this.valuesArray = var3;
            int[] var4 = Arrays.copyOf(this.presenceArray, var1);
            Intrinsics.checkNotNullExpressionValue(var4, "copyOf(this, newSize)");
            this.presenceArray = var4;
            var1 = Companion.computeHashSize(var1);
            if (var1 > this.getHashSize()) {
               this.rehash(var1);
            }
         } else if (this.length + var1 - this.size() > this.getCapacity()) {
            this.rehash(this.getHashSize());
         }

      } else {
         throw new OutOfMemoryError();
      }
   }

   private final void ensureExtraCapacity(int var1) {
      this.ensureCapacity(this.length + var1);
   }

   private final int findKey(Object var1) {
      int var2 = this.hash(var1);
      int var3 = this.maxProbeDistance;

      while(true) {
         int var4 = this.hashArray[var2];
         if (var4 == 0) {
            return -1;
         }

         if (var4 > 0) {
            Object[] var5 = this.keysArray;
            --var4;
            if (Intrinsics.areEqual(var5[var4], var1)) {
               return var4;
            }
         }

         --var3;
         if (var3 < 0) {
            return -1;
         }

         if (var2 == 0) {
            var2 = this.getHashSize() - 1;
         } else {
            --var2;
         }
      }
   }

   private final int findValue(Object var1) {
      int var2 = this.length;

      while(true) {
         int var3 = var2 - 1;
         if (var3 < 0) {
            return -1;
         }

         var2 = var3;
         if (this.presenceArray[var3] >= 0) {
            Object[] var4 = this.valuesArray;
            Intrinsics.checkNotNull(var4);
            var2 = var3;
            if (Intrinsics.areEqual(var4[var3], var1)) {
               return var3;
            }
         }
      }
   }

   private final int getCapacity() {
      return this.keysArray.length;
   }

   private final int getHashSize() {
      return this.hashArray.length;
   }

   private final int hash(Object var1) {
      int var2;
      if (var1 != null) {
         var2 = var1.hashCode();
      } else {
         var2 = 0;
      }

      return var2 * -1640531527 >>> this.hashShift;
   }

   private final boolean putAllEntries(Collection var1) {
      boolean var3 = var1.isEmpty();
      boolean var2 = false;
      if (var3) {
         return false;
      } else {
         this.ensureExtraCapacity(var1.size());
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            if (this.putEntry((Map.Entry)var4.next())) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   private final boolean putEntry(Map.Entry var1) {
      int var2 = this.addKey$kotlin_stdlib(var1.getKey());
      Object[] var3 = this.allocateValuesArray();
      if (var2 >= 0) {
         var3[var2] = var1.getValue();
         return true;
      } else {
         var2 = -var2 - 1;
         Object var4 = var3[var2];
         if (!Intrinsics.areEqual(var1.getValue(), var4)) {
            var3[var2] = var1.getValue();
            return true;
         } else {
            return false;
         }
      }
   }

   private final boolean putRehash(int var1) {
      int var2 = this.hash(this.keysArray[var1]);
      int var3 = this.maxProbeDistance;

      while(true) {
         int[] var4 = this.hashArray;
         if (var4[var2] == 0) {
            var4[var2] = var1 + 1;
            this.presenceArray[var1] = var2;
            return true;
         }

         --var3;
         if (var3 < 0) {
            return false;
         }

         if (var2 == 0) {
            var2 = this.getHashSize() - 1;
         } else {
            --var2;
         }
      }
   }

   private final void rehash(int var1) {
      if (this.length > this.size()) {
         this.compact();
      }

      int var3 = this.getHashSize();
      byte var2 = 0;
      if (var1 != var3) {
         this.hashArray = new int[var1];
         this.hashShift = Companion.computeShift(var1);
         var1 = var2;
      } else {
         ArraysKt.fill(this.hashArray, 0, 0, this.getHashSize());
         var1 = var2;
      }

      while(var1 < this.length) {
         if (!this.putRehash(var1)) {
            throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
         }

         ++var1;
      }

   }

   private final void removeHashAt(int var1) {
      int var3 = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);
      int var2 = 0;
      int var4 = var1;

      int var5;
      int var6;
      do {
         if (var1 == 0) {
            var1 = this.getHashSize() - 1;
         } else {
            --var1;
         }

         var6 = var2 + 1;
         if (var6 > this.maxProbeDistance) {
            this.hashArray[var4] = 0;
            return;
         }

         int[] var9 = this.hashArray;
         int var7 = var9[var1];
         if (var7 == 0) {
            var9[var4] = 0;
            return;
         }

         label30: {
            if (var7 < 0) {
               var9[var4] = -1;
            } else {
               Object[] var10 = this.keysArray;
               int var8 = var7 - 1;
               var5 = var4;
               var2 = var6;
               if ((this.hash(var10[var8]) - var1 & this.getHashSize() - 1) < var6) {
                  break label30;
               }

               this.hashArray[var4] = var7;
               this.presenceArray[var8] = var4;
            }

            var5 = var1;
            var2 = 0;
         }

         var6 = var3 - 1;
         var4 = var5;
         var3 = var6;
      } while(var6 >= 0);

      this.hashArray[var5] = -1;
   }

   private final void removeKeyAt(int var1) {
      ListBuilderKt.resetAt(this.keysArray, var1);
      this.removeHashAt(this.presenceArray[var1]);
      this.presenceArray[var1] = -1;
      this.size = this.size() - 1;
   }

   private final Object writeReplace() {
      if (this.isReadOnly) {
         return new SerializedMap((Map)this);
      } else {
         throw new NotSerializableException("The map cannot be serialized while it is being built.");
      }
   }

   public final int addKey$kotlin_stdlib(Object var1) {
      this.checkIsMutable$kotlin_stdlib();

      while(true) {
         while(true) {
            int var2 = this.hash(var1);
            int var4 = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);
            int var3 = 0;

            while(true) {
               int var5 = this.hashArray[var2];
               if (var5 <= 0) {
                  if (this.length < this.getCapacity()) {
                     var5 = this.length;
                     var4 = var5 + 1;
                     this.length = var4;
                     this.keysArray[var5] = var1;
                     this.presenceArray[var5] = var2;
                     this.hashArray[var2] = var4;
                     this.size = this.size() + 1;
                     if (var3 > this.maxProbeDistance) {
                        this.maxProbeDistance = var3;
                     }

                     return var5;
                  }

                  this.ensureExtraCapacity(1);
                  break;
               }

               if (Intrinsics.areEqual(this.keysArray[var5 - 1], var1)) {
                  return -var5;
               }

               ++var3;
               if (var3 > var4) {
                  this.rehash(this.getHashSize() * 2);
                  break;
               }

               if (var2 == 0) {
                  var2 = this.getHashSize() - 1;
               } else {
                  --var2;
               }
            }
         }
      }
   }

   public final Map build() {
      this.checkIsMutable$kotlin_stdlib();
      this.isReadOnly = true;
      return (Map)this;
   }

   public final void checkIsMutable$kotlin_stdlib() {
      if (this.isReadOnly) {
         throw new UnsupportedOperationException();
      }
   }

   public void clear() {
      this.checkIsMutable$kotlin_stdlib();
      IntIterator var3 = (new IntRange(0, this.length - 1)).iterator();

      while(var3.hasNext()) {
         int var1 = var3.nextInt();
         int[] var4 = this.presenceArray;
         int var2 = var4[var1];
         if (var2 >= 0) {
            this.hashArray[var2] = 0;
            var4[var1] = -1;
         }
      }

      ListBuilderKt.resetRange(this.keysArray, 0, this.length);
      Object[] var5 = this.valuesArray;
      if (var5 != null) {
         ListBuilderKt.resetRange(var5, 0, this.length);
      }

      this.size = 0;
      this.length = 0;
   }

   public final boolean containsAllEntries$kotlin_stdlib(Collection var1) {
      Intrinsics.checkNotNullParameter(var1, "m");
      Iterator var5 = var1.iterator();

      boolean var2;
      do {
         if (!var5.hasNext()) {
            return true;
         }

         Object var3 = var5.next();
         if (var3 == null) {
            break;
         }

         try {
            var2 = this.containsEntry$kotlin_stdlib((Map.Entry)var3);
         } catch (ClassCastException var4) {
            break;
         }
      } while(var2);

      return false;
   }

   public final boolean containsEntry$kotlin_stdlib(Map.Entry var1) {
      Intrinsics.checkNotNullParameter(var1, "entry");
      int var2 = this.findKey(var1.getKey());
      if (var2 < 0) {
         return false;
      } else {
         Object[] var3 = this.valuesArray;
         Intrinsics.checkNotNull(var3);
         return Intrinsics.areEqual(var3[var2], var1.getValue());
      }
   }

   public boolean containsKey(Object var1) {
      boolean var2;
      if (this.findKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean containsValue(Object var1) {
      boolean var2;
      if (this.findValue(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public final EntriesItr entriesIterator$kotlin_stdlib() {
      return new EntriesItr(this);
   }

   public boolean equals(Object var1) {
      boolean var2;
      if (var1 == this || var1 instanceof Map && this.contentEquals((Map)var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public Object get(Object var1) {
      int var2 = this.findKey(var1);
      if (var2 < 0) {
         return null;
      } else {
         Object[] var3 = this.valuesArray;
         Intrinsics.checkNotNull(var3);
         return var3[var2];
      }
   }

   public Set getEntries() {
      MapBuilderEntries var1 = this.entriesView;
      if (var1 == null) {
         var1 = new MapBuilderEntries(this);
         this.entriesView = var1;
         return (Set)var1;
      } else {
         return (Set)var1;
      }
   }

   public Set getKeys() {
      MapBuilderKeys var1 = this.keysView;
      Set var2;
      if (var1 == null) {
         var1 = new MapBuilderKeys(this);
         this.keysView = var1;
         var2 = (Set)var1;
      } else {
         var2 = (Set)var1;
      }

      return var2;
   }

   public int getSize() {
      return this.size;
   }

   public Collection getValues() {
      MapBuilderValues var1 = this.valuesView;
      Collection var2;
      if (var1 == null) {
         var1 = new MapBuilderValues(this);
         this.valuesView = var1;
         var2 = (Collection)var1;
      } else {
         var2 = (Collection)var1;
      }

      return var2;
   }

   public int hashCode() {
      EntriesItr var2 = this.entriesIterator$kotlin_stdlib();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 += var2.nextHashCode$kotlin_stdlib()) {
      }

      return var1;
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

   public final boolean isReadOnly$kotlin_stdlib() {
      return this.isReadOnly;
   }

   public final KeysItr keysIterator$kotlin_stdlib() {
      return new KeysItr(this);
   }

   public Object put(Object var1, Object var2) {
      this.checkIsMutable$kotlin_stdlib();
      int var3 = this.addKey$kotlin_stdlib(var1);
      Object[] var5 = this.allocateValuesArray();
      if (var3 < 0) {
         var3 = -var3 - 1;
         Object var4 = var5[var3];
         var5[var3] = var2;
         return var4;
      } else {
         var5[var3] = var2;
         return null;
      }
   }

   public void putAll(Map var1) {
      Intrinsics.checkNotNullParameter(var1, "from");
      this.checkIsMutable$kotlin_stdlib();
      this.putAllEntries((Collection)var1.entrySet());
   }

   public Object remove(Object var1) {
      int var2 = this.removeKey$kotlin_stdlib(var1);
      if (var2 < 0) {
         return null;
      } else {
         Object[] var4 = this.valuesArray;
         Intrinsics.checkNotNull(var4);
         Object var3 = var4[var2];
         ListBuilderKt.resetAt(var4, var2);
         return var3;
      }
   }

   public final boolean removeEntry$kotlin_stdlib(Map.Entry var1) {
      Intrinsics.checkNotNullParameter(var1, "entry");
      this.checkIsMutable$kotlin_stdlib();
      int var2 = this.findKey(var1.getKey());
      if (var2 < 0) {
         return false;
      } else {
         Object[] var3 = this.valuesArray;
         Intrinsics.checkNotNull(var3);
         if (!Intrinsics.areEqual(var3[var2], var1.getValue())) {
            return false;
         } else {
            this.removeKeyAt(var2);
            return true;
         }
      }
   }

   public final int removeKey$kotlin_stdlib(Object var1) {
      this.checkIsMutable$kotlin_stdlib();
      int var2 = this.findKey(var1);
      if (var2 < 0) {
         return -1;
      } else {
         this.removeKeyAt(var2);
         return var2;
      }
   }

   public final boolean removeValue$kotlin_stdlib(Object var1) {
      this.checkIsMutable$kotlin_stdlib();
      int var2 = this.findValue(var1);
      if (var2 < 0) {
         return false;
      } else {
         this.removeKeyAt(var2);
         return true;
      }
   }

   public String toString() {
      StringBuilder var3 = new StringBuilder(this.size() * 3 + 2);
      var3.append("{");
      EntriesItr var2 = this.entriesIterator$kotlin_stdlib();

      for(int var1 = 0; var2.hasNext(); ++var1) {
         if (var1 > 0) {
            var3.append(", ");
         }

         var2.nextAppendString(var3);
      }

      var3.append("}");
      String var4 = var3.toString();
      Intrinsics.checkNotNullExpressionValue(var4, "sb.toString()");
      return var4;
   }

   public final ValuesItr valuesIterator$kotlin_stdlib() {
      return new ValuesItr(this);
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$Companion;", "", "()V", "INITIAL_CAPACITY", "", "INITIAL_MAX_PROBE_DISTANCE", "MAGIC", "TOMBSTONE", "computeHashSize", "capacity", "computeShift", "hashSize", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      private final int computeHashSize(int var1) {
         return Integer.highestOneBit(RangesKt.coerceAtLeast(var1, 1) * 3);
      }

      private final int computeShift(int var1) {
         return Integer.numberOfLeadingZeros(var1) + 1;
      }
   }

   @Metadata(
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00050\u0004B\u0019\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\nH\u0096\u0002J\u0012\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012¨\u0006\u0013"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$EntriesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "Lkotlin/collections/builders/MapBuilder$EntryRef;", "nextAppendString", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nextHashCode", "", "nextHashCode$kotlin_stdlib", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class EntriesItr extends Itr implements Iterator, KMutableIterator {
      public EntriesItr(MapBuilder var1) {
         Intrinsics.checkNotNullParameter(var1, "map");
         super(var1);
      }

      public EntryRef next() {
         if (this.getIndex$kotlin_stdlib() < this.getMap$kotlin_stdlib().length) {
            int var1 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var1 + 1);
            this.setLastIndex$kotlin_stdlib(var1);
            EntryRef var2 = new EntryRef(this.getMap$kotlin_stdlib(), this.getLastIndex$kotlin_stdlib());
            this.initNext$kotlin_stdlib();
            return var2;
         } else {
            throw new NoSuchElementException();
         }
      }

      public final void nextAppendString(StringBuilder var1) {
         Intrinsics.checkNotNullParameter(var1, "sb");
         if (this.getIndex$kotlin_stdlib() < this.getMap$kotlin_stdlib().length) {
            int var2 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var2 + 1);
            this.setLastIndex$kotlin_stdlib(var2);
            Object var3 = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            if (Intrinsics.areEqual((Object)var3, (Object)this.getMap$kotlin_stdlib())) {
               var1.append("(this Map)");
            } else {
               var1.append(var3);
            }

            var1.append('=');
            Object[] var4 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(var4);
            var3 = var4[this.getLastIndex$kotlin_stdlib()];
            if (Intrinsics.areEqual((Object)var3, (Object)this.getMap$kotlin_stdlib())) {
               var1.append("(this Map)");
            } else {
               var1.append(var3);
            }

            this.initNext$kotlin_stdlib();
         } else {
            throw new NoSuchElementException();
         }
      }

      public final int nextHashCode$kotlin_stdlib() {
         if (this.getIndex$kotlin_stdlib() < this.getMap$kotlin_stdlib().length) {
            int var1 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var1 + 1);
            this.setLastIndex$kotlin_stdlib(var1);
            Object var3 = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            int var2 = 0;
            if (var3 != null) {
               var1 = var3.hashCode();
            } else {
               var1 = 0;
            }

            Object[] var4 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(var4);
            var3 = var4[this.getLastIndex$kotlin_stdlib()];
            if (var3 != null) {
               var2 = var3.hashCode();
            }

            this.initNext$kotlin_stdlib();
            return var1 ^ var2;
         } else {
            throw new NoSuchElementException();
         }
      }
   }

   @Metadata(
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B!\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\u0015\u0010\u0013\u001a\u00028\u00032\u0006\u0010\u0014\u001a\u00028\u0003H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00028\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0018"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$EntryRef;", "K", "V", "", "map", "Lkotlin/collections/builders/MapBuilder;", "index", "", "(Lkotlin/collections/builders/MapBuilder;I)V", "key", "getKey", "()Ljava/lang/Object;", "value", "getValue", "equals", "", "other", "", "hashCode", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class EntryRef implements Map.Entry, KMutableMap.Entry {
      private final int index;
      private final MapBuilder map;

      public EntryRef(MapBuilder var1, int var2) {
         Intrinsics.checkNotNullParameter(var1, "map");
         super();
         this.map = var1;
         this.index = var2;
      }

      public boolean equals(Object var1) {
         boolean var2;
         if (var1 instanceof Map.Entry) {
            Map.Entry var3 = (Map.Entry)var1;
            if (Intrinsics.areEqual(var3.getKey(), this.getKey()) && Intrinsics.areEqual(var3.getValue(), this.getValue())) {
               var2 = true;
               return var2;
            }
         }

         var2 = false;
         return var2;
      }

      public Object getKey() {
         return this.map.keysArray[this.index];
      }

      public Object getValue() {
         Object[] var1 = this.map.valuesArray;
         Intrinsics.checkNotNull(var1);
         return var1[this.index];
      }

      public int hashCode() {
         Object var3 = this.getKey();
         int var2 = 0;
         int var1;
         if (var3 != null) {
            var1 = var3.hashCode();
         } else {
            var1 = 0;
         }

         var3 = this.getValue();
         if (var3 != null) {
            var2 = var3.hashCode();
         }

         return var1 ^ var2;
      }

      public Object setValue(Object var1) {
         this.map.checkIsMutable$kotlin_stdlib();
         Object[] var4 = this.map.allocateValuesArray();
         int var2 = this.index;
         Object var3 = var4[var2];
         var4[var2] = var1;
         return var3;
      }

      public String toString() {
         return "" + this.getKey() + '=' + this.getValue();
      }
   }

   @Metadata(
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0006\u0010\u0017\u001a\u00020\u0015R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$Itr;", "K", "V", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "index", "", "getIndex$kotlin_stdlib", "()I", "setIndex$kotlin_stdlib", "(I)V", "lastIndex", "getLastIndex$kotlin_stdlib", "setLastIndex$kotlin_stdlib", "getMap$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "hasNext", "", "initNext", "", "initNext$kotlin_stdlib", "remove", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static class Itr {
      private int index;
      private int lastIndex;
      private final MapBuilder map;

      public Itr(MapBuilder var1) {
         Intrinsics.checkNotNullParameter(var1, "map");
         super();
         this.map = var1;
         this.lastIndex = -1;
         this.initNext$kotlin_stdlib();
      }

      public final int getIndex$kotlin_stdlib() {
         return this.index;
      }

      public final int getLastIndex$kotlin_stdlib() {
         return this.lastIndex;
      }

      public final MapBuilder getMap$kotlin_stdlib() {
         return this.map;
      }

      public final boolean hasNext() {
         boolean var1;
         if (this.index < this.map.length) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public final void initNext$kotlin_stdlib() {
         while(true) {
            if (this.index < this.map.length) {
               int[] var2 = this.map.presenceArray;
               int var1 = this.index;
               if (var2[var1] < 0) {
                  this.index = var1 + 1;
                  continue;
               }
            }

            return;
         }
      }

      public final void remove() {
         boolean var1;
         if (this.lastIndex != -1) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (var1) {
            this.map.checkIsMutable$kotlin_stdlib();
            this.map.removeKeyAt(this.lastIndex);
            this.lastIndex = -1;
         } else {
            throw new IllegalStateException("Call next() before removing element from the iterator.".toString());
         }
      }

      public final void setIndex$kotlin_stdlib(int var1) {
         this.index = var1;
      }

      public final void setLastIndex$kotlin_stdlib(int var1) {
         this.lastIndex = var1;
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00028\u0002H\u0096\u0002¢\u0006\u0002\u0010\t¨\u0006\n"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$KeysItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class KeysItr extends Itr implements Iterator, KMutableIterator {
      public KeysItr(MapBuilder var1) {
         Intrinsics.checkNotNullParameter(var1, "map");
         super(var1);
      }

      public Object next() {
         if (this.getIndex$kotlin_stdlib() < this.getMap$kotlin_stdlib().length) {
            int var1 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var1 + 1);
            this.setLastIndex$kotlin_stdlib(var1);
            Object var2 = this.getMap$kotlin_stdlib().keysArray[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return var2;
         } else {
            throw new NoSuchElementException();
         }
      }
   }

   @Metadata(
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00028\u0003H\u0096\u0002¢\u0006\u0002\u0010\t¨\u0006\n"},
      d2 = {"Lkotlin/collections/builders/MapBuilder$ValuesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class ValuesItr extends Itr implements Iterator, KMutableIterator {
      public ValuesItr(MapBuilder var1) {
         Intrinsics.checkNotNullParameter(var1, "map");
         super(var1);
      }

      public Object next() {
         if (this.getIndex$kotlin_stdlib() < this.getMap$kotlin_stdlib().length) {
            int var1 = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(var1 + 1);
            this.setLastIndex$kotlin_stdlib(var1);
            Object[] var2 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(var2);
            Object var3 = var2[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return var3;
         } else {
            throw new NoSuchElementException();
         }
      }
   }
}
