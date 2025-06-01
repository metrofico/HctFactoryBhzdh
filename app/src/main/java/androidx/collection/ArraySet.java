package androidx.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class ArraySet implements Collection, Set {
   private static final int BASE_SIZE = 4;
   private static final int CACHE_SIZE = 10;
   private static final boolean DEBUG = false;
   private static final int[] INT = new int[0];
   private static final Object[] OBJECT = new Object[0];
   private static final String TAG = "ArraySet";
   private static Object[] sBaseCache;
   private static int sBaseCacheSize;
   private static Object[] sTwiceBaseCache;
   private static int sTwiceBaseCacheSize;
   Object[] mArray;
   private MapCollections mCollections;
   private int[] mHashes;
   int mSize;

   public ArraySet() {
      this(0);
   }

   public ArraySet(int var1) {
      if (var1 == 0) {
         this.mHashes = INT;
         this.mArray = OBJECT;
      } else {
         this.allocArrays(var1);
      }

      this.mSize = 0;
   }

   public ArraySet(ArraySet var1) {
      this();
      if (var1 != null) {
         this.addAll(var1);
      }

   }

   public ArraySet(Collection var1) {
      this();
      if (var1 != null) {
         this.addAll(var1);
      }

   }

   private void allocArrays(int var1) {
      Throwable var10000;
      boolean var10001;
      Throwable var113;
      label951: {
         Object[] var2;
         if (var1 == 8) {
            synchronized(ArraySet.class){}

            try {
               var2 = sTwiceBaseCache;
            } catch (Throwable var110) {
               var10000 = var110;
               var10001 = false;
               break label951;
            }

            if (var2 != null) {
               try {
                  this.mArray = var2;
                  sTwiceBaseCache = (Object[])var2[0];
                  this.mHashes = (int[])var2[1];
               } catch (Throwable var106) {
                  var10000 = var106;
                  var10001 = false;
                  break label951;
               }

               var2[1] = null;
               var2[0] = null;

               try {
                  --sTwiceBaseCacheSize;
                  return;
               } catch (Throwable var105) {
                  var10000 = var105;
                  var10001 = false;
                  break label951;
               }
            }

            try {
               ;
            } catch (Throwable var109) {
               var10000 = var109;
               var10001 = false;
               break label951;
            }
         } else if (var1 == 4) {
            label950: {
               synchronized(ArraySet.class){}

               label936: {
                  try {
                     var2 = sBaseCache;
                  } catch (Throwable var112) {
                     var10000 = var112;
                     var10001 = false;
                     break label936;
                  }

                  if (var2 != null) {
                     label948: {
                        try {
                           this.mArray = var2;
                           sBaseCache = (Object[])var2[0];
                           this.mHashes = (int[])var2[1];
                        } catch (Throwable var108) {
                           var10000 = var108;
                           var10001 = false;
                           break label948;
                        }

                        var2[1] = null;
                        var2[0] = null;

                        label916:
                        try {
                           --sBaseCacheSize;
                           return;
                        } catch (Throwable var107) {
                           var10000 = var107;
                           var10001 = false;
                           break label916;
                        }
                     }
                  } else {
                     label932:
                     try {
                        break label950;
                     } catch (Throwable var111) {
                        var10000 = var111;
                        var10001 = false;
                        break label932;
                     }
                  }
               }

               while(true) {
                  var113 = var10000;

                  try {
                     throw var113;
                  } catch (Throwable var104) {
                     var10000 = var104;
                     var10001 = false;
                     continue;
                  }
               }
            }
         }

         this.mHashes = new int[var1];
         this.mArray = new Object[var1];
         return;
      }

      while(true) {
         var113 = var10000;

         try {
            throw var113;
         } catch (Throwable var103) {
            var10000 = var103;
            var10001 = false;
            continue;
         }
      }
   }

   private static void freeArrays(int[] var0, Object[] var1, int var2) {
      Throwable var75;
      Throwable var10000;
      boolean var10001;
      if (var0.length == 8) {
         synchronized(ArraySet.class){}

         label811: {
            label838: {
               try {
                  if (sTwiceBaseCacheSize >= 10) {
                     break label838;
                  }

                  var1[0] = sTwiceBaseCache;
               } catch (Throwable var71) {
                  var10000 = var71;
                  var10001 = false;
                  break label811;
               }

               var1[1] = var0;
               --var2;

               while(true) {
                  if (var2 < 2) {
                     try {
                        sTwiceBaseCache = var1;
                        ++sTwiceBaseCacheSize;
                        break;
                     } catch (Throwable var70) {
                        var10000 = var70;
                        var10001 = false;
                        break label811;
                     }
                  }

                  var1[var2] = null;
                  --var2;
               }
            }

            label798:
            try {
               return;
            } catch (Throwable var69) {
               var10000 = var69;
               var10001 = false;
               break label798;
            }
         }

         while(true) {
            var75 = var10000;

            try {
               throw var75;
            } catch (Throwable var67) {
               var10000 = var67;
               var10001 = false;
               continue;
            }
         }
      } else if (var0.length == 4) {
         synchronized(ArraySet.class){}

         label829: {
            label839: {
               try {
                  if (sBaseCacheSize >= 10) {
                     break label839;
                  }

                  var1[0] = sBaseCache;
               } catch (Throwable var74) {
                  var10000 = var74;
                  var10001 = false;
                  break label829;
               }

               var1[1] = var0;
               --var2;

               while(true) {
                  if (var2 < 2) {
                     try {
                        sBaseCache = var1;
                        ++sBaseCacheSize;
                        break;
                     } catch (Throwable var73) {
                        var10000 = var73;
                        var10001 = false;
                        break label829;
                     }
                  }

                  var1[var2] = null;
                  --var2;
               }
            }

            label816:
            try {
               return;
            } catch (Throwable var72) {
               var10000 = var72;
               var10001 = false;
               break label816;
            }
         }

         while(true) {
            var75 = var10000;

            try {
               throw var75;
            } catch (Throwable var68) {
               var10000 = var68;
               var10001 = false;
               continue;
            }
         }
      }
   }

   private MapCollections getCollection() {
      if (this.mCollections == null) {
         this.mCollections = new MapCollections(this) {
            final ArraySet this$0;

            {
               this.this$0 = var1;
            }

            protected void colClear() {
               this.this$0.clear();
            }

            protected Object colGetEntry(int var1, int var2) {
               return this.this$0.mArray[var1];
            }

            protected Map colGetMap() {
               throw new UnsupportedOperationException("not a map");
            }

            protected int colGetSize() {
               return this.this$0.mSize;
            }

            protected int colIndexOfKey(Object var1) {
               return this.this$0.indexOf(var1);
            }

            protected int colIndexOfValue(Object var1) {
               return this.this$0.indexOf(var1);
            }

            protected void colPut(Object var1, Object var2) {
               this.this$0.add(var1);
            }

            protected void colRemoveAt(int var1) {
               this.this$0.removeAt(var1);
            }

            protected Object colSetValue(int var1, Object var2) {
               throw new UnsupportedOperationException("not a map");
            }
         };
      }

      return this.mCollections;
   }

   private int indexOf(Object var1, int var2) {
      int var4 = this.mSize;
      if (var4 == 0) {
         return -1;
      } else {
         int var5 = ContainerHelpers.binarySearch(this.mHashes, var4, var2);
         if (var5 < 0) {
            return var5;
         } else if (var1.equals(this.mArray[var5])) {
            return var5;
         } else {
            int var3;
            for(var3 = var5 + 1; var3 < var4 && this.mHashes[var3] == var2; ++var3) {
               if (var1.equals(this.mArray[var3])) {
                  return var3;
               }
            }

            for(var4 = var5 - 1; var4 >= 0 && this.mHashes[var4] == var2; --var4) {
               if (var1.equals(this.mArray[var4])) {
                  return var4;
               }
            }

            return ~var3;
         }
      }
   }

   private int indexOfNull() {
      int var2 = this.mSize;
      if (var2 == 0) {
         return -1;
      } else {
         int var3 = ContainerHelpers.binarySearch(this.mHashes, var2, 0);
         if (var3 < 0) {
            return var3;
         } else if (this.mArray[var3] == null) {
            return var3;
         } else {
            int var1;
            for(var1 = var3 + 1; var1 < var2 && this.mHashes[var1] == 0; ++var1) {
               if (this.mArray[var1] == null) {
                  return var1;
               }
            }

            for(var2 = var3 - 1; var2 >= 0 && this.mHashes[var2] == 0; --var2) {
               if (this.mArray[var2] == null) {
                  return var2;
               }
            }

            return ~var1;
         }
      }
   }

   public boolean add(Object var1) {
      int var2;
      int var3;
      if (var1 == null) {
         var2 = this.indexOfNull();
         var3 = 0;
      } else {
         var3 = var1.hashCode();
         var2 = this.indexOf(var1, var3);
      }

      if (var2 >= 0) {
         return false;
      } else {
         int var4 = ~var2;
         int var5 = this.mSize;
         int[] var8 = this.mHashes;
         Object[] var6;
         if (var5 >= var8.length) {
            var2 = 4;
            if (var5 >= 8) {
               var2 = (var5 >> 1) + var5;
            } else if (var5 >= 4) {
               var2 = 8;
            }

            var6 = this.mArray;
            this.allocArrays(var2);
            int[] var7 = this.mHashes;
            if (var7.length > 0) {
               System.arraycopy(var8, 0, var7, 0, var8.length);
               System.arraycopy(var6, 0, this.mArray, 0, var6.length);
            }

            freeArrays(var8, var6, this.mSize);
         }

         var5 = this.mSize;
         if (var4 < var5) {
            int[] var9 = this.mHashes;
            var2 = var4 + 1;
            System.arraycopy(var9, var4, var9, var2, var5 - var4);
            var6 = this.mArray;
            System.arraycopy(var6, var4, var6, var2, this.mSize - var4);
         }

         this.mHashes[var4] = var3;
         this.mArray[var4] = var1;
         ++this.mSize;
         return true;
      }
   }

   public void addAll(ArraySet var1) {
      int var3 = var1.mSize;
      this.ensureCapacity(this.mSize + var3);
      int var4 = this.mSize;
      int var2 = 0;
      if (var4 == 0) {
         if (var3 > 0) {
            System.arraycopy(var1.mHashes, 0, this.mHashes, 0, var3);
            System.arraycopy(var1.mArray, 0, this.mArray, 0, var3);
            this.mSize = var3;
         }
      } else {
         while(var2 < var3) {
            this.add(var1.valueAt(var2));
            ++var2;
         }
      }

   }

   public boolean addAll(Collection var1) {
      this.ensureCapacity(this.mSize + var1.size());
      Iterator var3 = var1.iterator();

      boolean var2;
      for(var2 = false; var3.hasNext(); var2 |= this.add(var3.next())) {
      }

      return var2;
   }

   public void clear() {
      int var1 = this.mSize;
      if (var1 != 0) {
         freeArrays(this.mHashes, this.mArray, var1);
         this.mHashes = INT;
         this.mArray = OBJECT;
         this.mSize = 0;
      }

   }

   public boolean contains(Object var1) {
      boolean var2;
      if (this.indexOf(var1) >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean containsAll(Collection var1) {
      Iterator var2 = var1.iterator();

      do {
         if (!var2.hasNext()) {
            return true;
         }
      } while(this.contains(var2.next()));

      return false;
   }

   public void ensureCapacity(int var1) {
      int[] var3 = this.mHashes;
      if (var3.length < var1) {
         Object[] var2 = this.mArray;
         this.allocArrays(var1);
         var1 = this.mSize;
         if (var1 > 0) {
            System.arraycopy(var3, 0, this.mHashes, 0, var1);
            System.arraycopy(var2, 0, this.mArray, 0, this.mSize);
         }

         freeArrays(var3, var2, this.mSize);
      }

   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         if (var1 instanceof Set) {
            Set var5 = (Set)var1;
            if (this.size() != var5.size()) {
               return false;
            }

            int var2 = 0;

            while(true) {
               boolean var3;
               try {
                  if (var2 >= this.mSize) {
                     return true;
                  }

                  var3 = var5.contains(this.valueAt(var2));
               } catch (ClassCastException | NullPointerException var4) {
                  break;
               }

               if (!var3) {
                  return false;
               }

               ++var2;
            }
         }

         return false;
      }
   }

   public int hashCode() {
      int[] var4 = this.mHashes;
      int var3 = this.mSize;
      int var2 = 0;

      int var1;
      for(var1 = 0; var2 < var3; ++var2) {
         var1 += var4[var2];
      }

      return var1;
   }

   public int indexOf(Object var1) {
      int var2;
      if (var1 == null) {
         var2 = this.indexOfNull();
      } else {
         var2 = this.indexOf(var1, var1.hashCode());
      }

      return var2;
   }

   public boolean isEmpty() {
      boolean var1;
      if (this.mSize <= 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Iterator iterator() {
      return this.getCollection().getKeySet().iterator();
   }

   public boolean remove(Object var1) {
      int var2 = this.indexOf(var1);
      if (var2 >= 0) {
         this.removeAt(var2);
         return true;
      } else {
         return false;
      }
   }

   public boolean removeAll(ArraySet var1) {
      int var3 = var1.mSize;
      int var4 = this.mSize;
      boolean var5 = false;

      for(int var2 = 0; var2 < var3; ++var2) {
         this.remove(var1.valueAt(var2));
      }

      if (var4 != this.mSize) {
         var5 = true;
      }

      return var5;
   }

   public boolean removeAll(Collection var1) {
      Iterator var3 = var1.iterator();

      boolean var2;
      for(var2 = false; var3.hasNext(); var2 |= this.remove(var3.next())) {
      }

      return var2;
   }

   public Object removeAt(int var1) {
      Object[] var6 = this.mArray;
      Object var5 = var6[var1];
      int var3 = this.mSize;
      if (var3 <= 1) {
         freeArrays(this.mHashes, var6, var3);
         this.mHashes = INT;
         this.mArray = OBJECT;
         this.mSize = 0;
      } else {
         int[] var7 = this.mHashes;
         int var4 = var7.length;
         int var2 = 8;
         if (var4 > 8 && var3 < var7.length / 3) {
            if (var3 > 8) {
               var2 = var3 + (var3 >> 1);
            }

            this.allocArrays(var2);
            --this.mSize;
            if (var1 > 0) {
               System.arraycopy(var7, 0, this.mHashes, 0, var1);
               System.arraycopy(var6, 0, this.mArray, 0, var1);
            }

            var2 = this.mSize;
            if (var1 < var2) {
               var3 = var1 + 1;
               System.arraycopy(var7, var3, this.mHashes, var1, var2 - var1);
               System.arraycopy(var6, var3, this.mArray, var1, this.mSize - var1);
            }
         } else {
            --var3;
            this.mSize = var3;
            if (var1 < var3) {
               var2 = var1 + 1;
               System.arraycopy(var7, var2, var7, var1, var3 - var1);
               var6 = this.mArray;
               System.arraycopy(var6, var2, var6, var1, this.mSize - var1);
            }

            this.mArray[this.mSize] = null;
         }
      }

      return var5;
   }

   public boolean retainAll(Collection var1) {
      int var2 = this.mSize - 1;

      boolean var3;
      for(var3 = false; var2 >= 0; --var2) {
         if (!var1.contains(this.mArray[var2])) {
            this.removeAt(var2);
            var3 = true;
         }
      }

      return var3;
   }

   public int size() {
      return this.mSize;
   }

   public Object[] toArray() {
      int var1 = this.mSize;
      Object[] var2 = new Object[var1];
      System.arraycopy(this.mArray, 0, var2, 0, var1);
      return var2;
   }

   public Object[] toArray(Object[] var1) {
      Object[] var4 = var1;
      if (var1.length < this.mSize) {
         var4 = (Object[])Array.newInstance(var1.getClass().getComponentType(), this.mSize);
      }

      System.arraycopy(this.mArray, 0, var4, 0, this.mSize);
      int var2 = var4.length;
      int var3 = this.mSize;
      if (var2 > var3) {
         var4[var3] = null;
      }

      return var4;
   }

   public String toString() {
      if (this.isEmpty()) {
         return "{}";
      } else {
         StringBuilder var3 = new StringBuilder(this.mSize * 14);
         var3.append('{');

         for(int var1 = 0; var1 < this.mSize; ++var1) {
            if (var1 > 0) {
               var3.append(", ");
            }

            Object var2 = this.valueAt(var1);
            if (var2 != this) {
               var3.append(var2);
            } else {
               var3.append("(this Set)");
            }
         }

         var3.append('}');
         return var3.toString();
      }
   }

   public Object valueAt(int var1) {
      return this.mArray[var1];
   }
}
