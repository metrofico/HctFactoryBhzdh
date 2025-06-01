package androidx.collection;

public class LongSparseArray implements Cloneable {
   private static final Object DELETED = new Object();
   private boolean mGarbage;
   private long[] mKeys;
   private int mSize;
   private Object[] mValues;

   public LongSparseArray() {
      this(10);
   }

   public LongSparseArray(int var1) {
      this.mGarbage = false;
      if (var1 == 0) {
         this.mKeys = ContainerHelpers.EMPTY_LONGS;
         this.mValues = ContainerHelpers.EMPTY_OBJECTS;
      } else {
         var1 = ContainerHelpers.idealLongArraySize(var1);
         this.mKeys = new long[var1];
         this.mValues = new Object[var1];
      }

   }

   private void gc() {
      int var4 = this.mSize;
      long[] var6 = this.mKeys;
      Object[] var7 = this.mValues;
      int var3 = 0;

      int var1;
      int var2;
      for(var1 = 0; var3 < var4; var1 = var2) {
         Object var5 = var7[var3];
         var2 = var1;
         if (var5 != DELETED) {
            if (var3 != var1) {
               var6[var1] = var6[var3];
               var7[var1] = var5;
               var7[var3] = null;
            }

            var2 = var1 + 1;
         }

         ++var3;
      }

      this.mGarbage = false;
      this.mSize = var1;
   }

   public void append(long var1, Object var3) {
      int var4 = this.mSize;
      if (var4 != 0 && var1 <= this.mKeys[var4 - 1]) {
         this.put(var1, var3);
      } else {
         if (this.mGarbage && var4 >= this.mKeys.length) {
            this.gc();
         }

         int var5 = this.mSize;
         if (var5 >= this.mKeys.length) {
            var4 = ContainerHelpers.idealLongArraySize(var5 + 1);
            long[] var7 = new long[var4];
            Object[] var6 = new Object[var4];
            long[] var8 = this.mKeys;
            System.arraycopy(var8, 0, var7, 0, var8.length);
            Object[] var9 = this.mValues;
            System.arraycopy(var9, 0, var6, 0, var9.length);
            this.mKeys = var7;
            this.mValues = var6;
         }

         this.mKeys[var5] = var1;
         this.mValues[var5] = var3;
         this.mSize = var5 + 1;
      }
   }

   public void clear() {
      int var2 = this.mSize;
      Object[] var3 = this.mValues;

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = null;
      }

      this.mSize = 0;
      this.mGarbage = false;
   }

   public LongSparseArray clone() {
      try {
         LongSparseArray var1 = (LongSparseArray)super.clone();
         var1.mKeys = (long[])this.mKeys.clone();
         var1.mValues = (Object[])this.mValues.clone();
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError(var2);
      }
   }

   public boolean containsKey(long var1) {
      boolean var3;
      if (this.indexOfKey(var1) >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean containsValue(Object var1) {
      boolean var2;
      if (this.indexOfValue(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   @Deprecated
   public void delete(long var1) {
      this.remove(var1);
   }

   public Object get(long var1) {
      return this.get(var1, (Object)null);
   }

   public Object get(long var1, Object var3) {
      int var4 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
      if (var4 >= 0) {
         Object var5 = this.mValues[var4];
         if (var5 != DELETED) {
            return var5;
         }
      }

      return var3;
   }

   public int indexOfKey(long var1) {
      if (this.mGarbage) {
         this.gc();
      }

      return ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
   }

   public int indexOfValue(Object var1) {
      if (this.mGarbage) {
         this.gc();
      }

      for(int var2 = 0; var2 < this.mSize; ++var2) {
         if (this.mValues[var2] == var1) {
            return var2;
         }
      }

      return -1;
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

   public long keyAt(int var1) {
      if (this.mGarbage) {
         this.gc();
      }

      return this.mKeys[var1];
   }

   public void put(long var1, Object var3) {
      int var4 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
      if (var4 >= 0) {
         this.mValues[var4] = var3;
      } else {
         int var5 = ~var4;
         int var6 = this.mSize;
         Object[] var7;
         if (var5 < var6) {
            var7 = this.mValues;
            if (var7[var5] == DELETED) {
               this.mKeys[var5] = var1;
               var7[var5] = var3;
               return;
            }
         }

         var4 = var5;
         if (this.mGarbage) {
            var4 = var5;
            if (var6 >= this.mKeys.length) {
               this.gc();
               var4 = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
            }
         }

         var5 = this.mSize;
         if (var5 >= this.mKeys.length) {
            var5 = ContainerHelpers.idealLongArraySize(var5 + 1);
            long[] var8 = new long[var5];
            var7 = new Object[var5];
            long[] var9 = this.mKeys;
            System.arraycopy(var9, 0, var8, 0, var9.length);
            Object[] var11 = this.mValues;
            System.arraycopy(var11, 0, var7, 0, var11.length);
            this.mKeys = var8;
            this.mValues = var7;
         }

         var6 = this.mSize;
         if (var6 - var4 != 0) {
            long[] var10 = this.mKeys;
            var5 = var4 + 1;
            System.arraycopy(var10, var4, var10, var5, var6 - var4);
            var7 = this.mValues;
            System.arraycopy(var7, var4, var7, var5, this.mSize - var4);
         }

         this.mKeys[var4] = var1;
         this.mValues[var4] = var3;
         ++this.mSize;
      }

   }

   public void putAll(LongSparseArray var1) {
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         this.put(var1.keyAt(var2), var1.valueAt(var2));
      }

   }

   public Object putIfAbsent(long var1, Object var3) {
      Object var4 = this.get(var1);
      if (var4 == null) {
         this.put(var1, var3);
      }

      return var4;
   }

   public void remove(long var1) {
      int var3 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
      if (var3 >= 0) {
         Object[] var5 = this.mValues;
         Object var4 = var5[var3];
         Object var6 = DELETED;
         if (var4 != var6) {
            var5[var3] = var6;
            this.mGarbage = true;
         }
      }

   }

   public boolean remove(long var1, Object var3) {
      int var4 = this.indexOfKey(var1);
      if (var4 >= 0) {
         Object var5 = this.valueAt(var4);
         if (var3 == var5 || var3 != null && var3.equals(var5)) {
            this.removeAt(var4);
            return true;
         }
      }

      return false;
   }

   public void removeAt(int var1) {
      Object[] var3 = this.mValues;
      Object var4 = var3[var1];
      Object var2 = DELETED;
      if (var4 != var2) {
         var3[var1] = var2;
         this.mGarbage = true;
      }

   }

   public Object replace(long var1, Object var3) {
      int var4 = this.indexOfKey(var1);
      if (var4 >= 0) {
         Object[] var5 = this.mValues;
         Object var6 = var5[var4];
         var5[var4] = var3;
         return var6;
      } else {
         return null;
      }
   }

   public boolean replace(long var1, Object var3, Object var4) {
      int var5 = this.indexOfKey(var1);
      if (var5 >= 0) {
         Object var6 = this.mValues[var5];
         if (var6 == var3 || var3 != null && var3.equals(var6)) {
            this.mValues[var5] = var4;
            return true;
         }
      }

      return false;
   }

   public void setValueAt(int var1, Object var2) {
      if (this.mGarbage) {
         this.gc();
      }

      this.mValues[var1] = var2;
   }

   public int size() {
      if (this.mGarbage) {
         this.gc();
      }

      return this.mSize;
   }

   public String toString() {
      if (this.size() <= 0) {
         return "{}";
      } else {
         StringBuilder var2 = new StringBuilder(this.mSize * 28);
         var2.append('{');

         for(int var1 = 0; var1 < this.mSize; ++var1) {
            if (var1 > 0) {
               var2.append(", ");
            }

            var2.append(this.keyAt(var1));
            var2.append('=');
            Object var3 = this.valueAt(var1);
            if (var3 != this) {
               var2.append(var3);
            } else {
               var2.append("(this Map)");
            }
         }

         var2.append('}');
         return var2.toString();
      }
   }

   public Object valueAt(int var1) {
      if (this.mGarbage) {
         this.gc();
      }

      return this.mValues[var1];
   }
}
