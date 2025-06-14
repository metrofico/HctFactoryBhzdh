package androidx.collection;

public class SparseArrayCompat implements Cloneable {
   private static final Object DELETED = new Object();
   private boolean mGarbage;
   private int[] mKeys;
   private int mSize;
   private Object[] mValues;

   public SparseArrayCompat() {
      this(10);
   }

   public SparseArrayCompat(int var1) {
      this.mGarbage = false;
      if (var1 == 0) {
         this.mKeys = ContainerHelpers.EMPTY_INTS;
         this.mValues = ContainerHelpers.EMPTY_OBJECTS;
      } else {
         var1 = ContainerHelpers.idealIntArraySize(var1);
         this.mKeys = new int[var1];
         this.mValues = new Object[var1];
      }

   }

   private void gc() {
      int var4 = this.mSize;
      int[] var5 = this.mKeys;
      Object[] var6 = this.mValues;
      int var1 = 0;

      int var2;
      int var3;
      for(var2 = 0; var1 < var4; var2 = var3) {
         Object var7 = var6[var1];
         var3 = var2;
         if (var7 != DELETED) {
            if (var1 != var2) {
               var5[var2] = var5[var1];
               var6[var2] = var7;
               var6[var1] = null;
            }

            var3 = var2 + 1;
         }

         ++var1;
      }

      this.mGarbage = false;
      this.mSize = var2;
   }

   public void append(int var1, Object var2) {
      int var3 = this.mSize;
      if (var3 != 0 && var1 <= this.mKeys[var3 - 1]) {
         this.put(var1, var2);
      } else {
         if (this.mGarbage && var3 >= this.mKeys.length) {
            this.gc();
         }

         var3 = this.mSize;
         if (var3 >= this.mKeys.length) {
            int var4 = ContainerHelpers.idealIntArraySize(var3 + 1);
            int[] var5 = new int[var4];
            Object[] var6 = new Object[var4];
            int[] var7 = this.mKeys;
            System.arraycopy(var7, 0, var5, 0, var7.length);
            Object[] var8 = this.mValues;
            System.arraycopy(var8, 0, var6, 0, var8.length);
            this.mKeys = var5;
            this.mValues = var6;
         }

         this.mKeys[var3] = var1;
         this.mValues[var3] = var2;
         this.mSize = var3 + 1;
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

   public SparseArrayCompat clone() {
      try {
         SparseArrayCompat var1 = (SparseArrayCompat)super.clone();
         var1.mKeys = (int[])this.mKeys.clone();
         var1.mValues = (Object[])this.mValues.clone();
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new AssertionError(var2);
      }
   }

   public boolean containsKey(int var1) {
      boolean var2;
      if (this.indexOfKey(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
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
   public void delete(int var1) {
      this.remove(var1);
   }

   public Object get(int var1) {
      return this.get(var1, (Object)null);
   }

   public Object get(int var1, Object var2) {
      var1 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
      if (var1 >= 0) {
         Object var3 = this.mValues[var1];
         if (var3 != DELETED) {
            return var3;
         }
      }

      return var2;
   }

   public int indexOfKey(int var1) {
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

   public int keyAt(int var1) {
      if (this.mGarbage) {
         this.gc();
      }

      return this.mKeys[var1];
   }

   public void put(int var1, Object var2) {
      int var3 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
      if (var3 >= 0) {
         this.mValues[var3] = var2;
      } else {
         int var4 = ~var3;
         int var5 = this.mSize;
         Object[] var6;
         if (var4 < var5) {
            var6 = this.mValues;
            if (var6[var4] == DELETED) {
               this.mKeys[var4] = var1;
               var6[var4] = var2;
               return;
            }
         }

         var3 = var4;
         if (this.mGarbage) {
            var3 = var4;
            if (var5 >= this.mKeys.length) {
               this.gc();
               var3 = ~ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
            }
         }

         var4 = this.mSize;
         int[] var9;
         if (var4 >= this.mKeys.length) {
            var4 = ContainerHelpers.idealIntArraySize(var4 + 1);
            var9 = new int[var4];
            Object[] var7 = new Object[var4];
            int[] var8 = this.mKeys;
            System.arraycopy(var8, 0, var9, 0, var8.length);
            Object[] var10 = this.mValues;
            System.arraycopy(var10, 0, var7, 0, var10.length);
            this.mKeys = var9;
            this.mValues = var7;
         }

         var5 = this.mSize;
         if (var5 - var3 != 0) {
            var9 = this.mKeys;
            var4 = var3 + 1;
            System.arraycopy(var9, var3, var9, var4, var5 - var3);
            var6 = this.mValues;
            System.arraycopy(var6, var3, var6, var4, this.mSize - var3);
         }

         this.mKeys[var3] = var1;
         this.mValues[var3] = var2;
         ++this.mSize;
      }

   }

   public void putAll(SparseArrayCompat var1) {
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         this.put(var1.keyAt(var2), var1.valueAt(var2));
      }

   }

   public Object putIfAbsent(int var1, Object var2) {
      Object var3 = this.get(var1);
      if (var3 == null) {
         this.put(var1, var2);
      }

      return var3;
   }

   public void remove(int var1) {
      var1 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, var1);
      if (var1 >= 0) {
         Object[] var2 = this.mValues;
         Object var4 = var2[var1];
         Object var3 = DELETED;
         if (var4 != var3) {
            var2[var1] = var3;
            this.mGarbage = true;
         }
      }

   }

   public boolean remove(int var1, Object var2) {
      var1 = this.indexOfKey(var1);
      if (var1 >= 0) {
         Object var3 = this.valueAt(var1);
         if (var2 == var3 || var2 != null && var2.equals(var3)) {
            this.removeAt(var1);
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

   public void removeAtRange(int var1, int var2) {
      for(var2 = Math.min(this.mSize, var2 + var1); var1 < var2; ++var1) {
         this.removeAt(var1);
      }

   }

   public Object replace(int var1, Object var2) {
      var1 = this.indexOfKey(var1);
      if (var1 >= 0) {
         Object[] var3 = this.mValues;
         Object var4 = var3[var1];
         var3[var1] = var2;
         return var4;
      } else {
         return null;
      }
   }

   public boolean replace(int var1, Object var2, Object var3) {
      var1 = this.indexOfKey(var1);
      if (var1 >= 0) {
         Object var4 = this.mValues[var1];
         if (var4 == var2 || var2 != null && var2.equals(var4)) {
            this.mValues[var1] = var3;
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
         StringBuilder var3 = new StringBuilder(this.mSize * 28);
         var3.append('{');

         for(int var1 = 0; var1 < this.mSize; ++var1) {
            if (var1 > 0) {
               var3.append(", ");
            }

            var3.append(this.keyAt(var1));
            var3.append('=');
            Object var2 = this.valueAt(var1);
            if (var2 != this) {
               var3.append(var2);
            } else {
               var3.append("(this Map)");
            }
         }

         var3.append('}');
         return var3.toString();
      }
   }

   public Object valueAt(int var1) {
      if (this.mGarbage) {
         this.gc();
      }

      return this.mValues[var1];
   }
}
