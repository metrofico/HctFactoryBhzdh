package androidx.collection;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class SimpleArrayMap {
   private static final int BASE_SIZE = 4;
   private static final int CACHE_SIZE = 10;
   private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
   private static final boolean DEBUG = false;
   private static final String TAG = "ArrayMap";
   static Object[] mBaseCache;
   static int mBaseCacheSize;
   static Object[] mTwiceBaseCache;
   static int mTwiceBaseCacheSize;
   Object[] mArray;
   int[] mHashes;
   int mSize;

   public SimpleArrayMap() {
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      this.mSize = 0;
   }

   public SimpleArrayMap(int var1) {
      if (var1 == 0) {
         this.mHashes = ContainerHelpers.EMPTY_INTS;
         this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      } else {
         this.allocArrays(var1);
      }

      this.mSize = 0;
   }

   public SimpleArrayMap(SimpleArrayMap var1) {
      this();
      if (var1 != null) {
         this.putAll(var1);
      }

   }

   private void allocArrays(int var1) {
      Throwable var10000;
      boolean var10001;
      Throwable var113;
      label939: {
         label935: {
            Object[] var2;
            if (var1 == 8) {
               synchronized(SimpleArrayMap.class){}

               try {
                  var2 = mTwiceBaseCache;
               } catch (Throwable var110) {
                  var10000 = var110;
                  var10001 = false;
                  break label939;
               }

               if (var2 != null) {
                  try {
                     this.mArray = var2;
                     mTwiceBaseCache = (Object[])var2[0];
                     this.mHashes = (int[])var2[1];
                  } catch (Throwable var106) {
                     var10000 = var106;
                     var10001 = false;
                     break label939;
                  }

                  var2[1] = null;
                  var2[0] = null;

                  try {
                     --mTwiceBaseCacheSize;
                     return;
                  } catch (Throwable var105) {
                     var10000 = var105;
                     var10001 = false;
                     break label939;
                  }
               }

               try {
                  ;
               } catch (Throwable var109) {
                  var10000 = var109;
                  var10001 = false;
                  break label939;
               }
            } else if (var1 == 4) {
               synchronized(SimpleArrayMap.class){}

               try {
                  var2 = mBaseCache;
               } catch (Throwable var112) {
                  var10000 = var112;
                  var10001 = false;
                  break label935;
               }

               if (var2 != null) {
                  try {
                     this.mArray = var2;
                     mBaseCache = (Object[])var2[0];
                     this.mHashes = (int[])var2[1];
                  } catch (Throwable var108) {
                     var10000 = var108;
                     var10001 = false;
                     break label935;
                  }

                  var2[1] = null;
                  var2[0] = null;

                  try {
                     --mBaseCacheSize;
                     return;
                  } catch (Throwable var107) {
                     var10000 = var107;
                     var10001 = false;
                     break label935;
                  }
               }

               try {
                  ;
               } catch (Throwable var111) {
                  var10000 = var111;
                  var10001 = false;
                  break label935;
               }
            }

            this.mHashes = new int[var1];
            this.mArray = new Object[var1 << 1];
            return;
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

   private static int binarySearchHashes(int[] var0, int var1, int var2) {
      try {
         var1 = ContainerHelpers.binarySearch(var0, var1, var2);
         return var1;
      } catch (ArrayIndexOutOfBoundsException var3) {
         throw new ConcurrentModificationException();
      }
   }

   private static void freeArrays(int[] var0, Object[] var1, int var2) {
      Throwable var75;
      Throwable var10000;
      boolean var10001;
      if (var0.length == 8) {
         synchronized(SimpleArrayMap.class){}

         label811: {
            label838: {
               try {
                  if (mTwiceBaseCacheSize >= 10) {
                     break label838;
                  }

                  var1[0] = mTwiceBaseCache;
               } catch (Throwable var71) {
                  var10000 = var71;
                  var10001 = false;
                  break label811;
               }

               var1[1] = var0;
               var2 = (var2 << 1) - 1;

               while(true) {
                  if (var2 < 2) {
                     try {
                        mTwiceBaseCache = var1;
                        ++mTwiceBaseCacheSize;
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
            } catch (Throwable var68) {
               var10000 = var68;
               var10001 = false;
               continue;
            }
         }
      } else if (var0.length == 4) {
         synchronized(SimpleArrayMap.class){}

         label829: {
            label839: {
               try {
                  if (mBaseCacheSize >= 10) {
                     break label839;
                  }

                  var1[0] = mBaseCache;
               } catch (Throwable var74) {
                  var10000 = var74;
                  var10001 = false;
                  break label829;
               }

               var1[1] = var0;
               var2 = (var2 << 1) - 1;

               while(true) {
                  if (var2 < 2) {
                     try {
                        mBaseCache = var1;
                        ++mBaseCacheSize;
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
            } catch (Throwable var67) {
               var10000 = var67;
               var10001 = false;
               continue;
            }
         }
      }
   }

   public void clear() {
      int var1 = this.mSize;
      if (var1 > 0) {
         int[] var2 = this.mHashes;
         Object[] var3 = this.mArray;
         this.mHashes = ContainerHelpers.EMPTY_INTS;
         this.mArray = ContainerHelpers.EMPTY_OBJECTS;
         this.mSize = 0;
         freeArrays(var2, var3, var1);
      }

      if (this.mSize > 0) {
         throw new ConcurrentModificationException();
      }
   }

   public boolean containsKey(Object var1) {
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

   public void ensureCapacity(int var1) {
      int var2 = this.mSize;
      int[] var3 = this.mHashes;
      if (var3.length < var1) {
         Object[] var4 = this.mArray;
         this.allocArrays(var1);
         if (this.mSize > 0) {
            System.arraycopy(var3, 0, this.mHashes, 0, var2);
            System.arraycopy(var4, 0, this.mArray, 0, var2 << 1);
         }

         freeArrays(var3, var4, var2);
      }

      if (this.mSize != var2) {
         throw new ConcurrentModificationException();
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         boolean var10001;
         int var2;
         boolean var3;
         Object var6;
         if (var1 instanceof SimpleArrayMap) {
            SimpleArrayMap var14 = (SimpleArrayMap)var1;
            if (this.size() != var14.size()) {
               return false;
            } else {
               var2 = 0;

               while(true) {
                  Object var13;
                  try {
                     if (var2 >= this.mSize) {
                        return true;
                     }

                     var13 = this.keyAt(var2);
                     var6 = this.valueAt(var2);
                     var1 = var14.get(var13);
                  } catch (ClassCastException | NullPointerException var9) {
                     var10001 = false;
                     break;
                  }

                  if (var6 == null) {
                     label67: {
                        if (var1 == null) {
                           try {
                              if (var14.containsKey(var13)) {
                                 break label67;
                              }
                           } catch (ClassCastException | NullPointerException var8) {
                              var10001 = false;
                              break;
                           }
                        }

                        return false;
                     }
                  } else {
                     try {
                        var3 = var6.equals(var1);
                     } catch (ClassCastException | NullPointerException var7) {
                        var10001 = false;
                        break;
                     }

                     if (!var3) {
                        return false;
                     }
                  }

                  ++var2;
               }

               return false;
            }
         } else {
            if (var1 instanceof Map) {
               Map var4 = (Map)var1;
               if (this.size() != var4.size()) {
                  return false;
               }

               var2 = 0;

               while(true) {
                  Object var5;
                  try {
                     if (var2 >= this.mSize) {
                        return true;
                     }

                     var5 = this.keyAt(var2);
                     var1 = this.valueAt(var2);
                     var6 = var4.get(var5);
                  } catch (ClassCastException | NullPointerException var12) {
                     var10001 = false;
                     break;
                  }

                  if (var1 == null) {
                     label91: {
                        if (var6 == null) {
                           try {
                              if (var4.containsKey(var5)) {
                                 break label91;
                              }
                           } catch (ClassCastException | NullPointerException var11) {
                              var10001 = false;
                              break;
                           }
                        }

                        return false;
                     }
                  } else {
                     try {
                        var3 = var1.equals(var6);
                     } catch (ClassCastException | NullPointerException var10) {
                        var10001 = false;
                        break;
                     }

                     if (!var3) {
                        return false;
                     }
                  }

                  ++var2;
               }
            }

            return false;
         }
      }
   }

   public Object get(Object var1) {
      return this.getOrDefault(var1, (Object)null);
   }

   public Object getOrDefault(Object var1, Object var2) {
      int var3 = this.indexOfKey(var1);
      if (var3 >= 0) {
         var2 = this.mArray[(var3 << 1) + 1];
      }

      return var2;
   }

   public int hashCode() {
      int[] var9 = this.mHashes;
      Object[] var8 = this.mArray;
      int var5 = this.mSize;
      int var3 = 1;
      int var1 = 0;

      int var2;
      for(var2 = 0; var1 < var5; var3 += 2) {
         Object var7 = var8[var3];
         int var6 = var9[var1];
         int var4;
         if (var7 == null) {
            var4 = 0;
         } else {
            var4 = var7.hashCode();
         }

         var2 += var4 ^ var6;
         ++var1;
      }

      return var2;
   }

   int indexOf(Object var1, int var2) {
      int var5 = this.mSize;
      if (var5 == 0) {
         return -1;
      } else {
         int var4 = binarySearchHashes(this.mHashes, var5, var2);
         if (var4 < 0) {
            return var4;
         } else if (var1.equals(this.mArray[var4 << 1])) {
            return var4;
         } else {
            int var3;
            for(var3 = var4 + 1; var3 < var5 && this.mHashes[var3] == var2; ++var3) {
               if (var1.equals(this.mArray[var3 << 1])) {
                  return var3;
               }
            }

            --var4;

            while(var4 >= 0 && this.mHashes[var4] == var2) {
               if (var1.equals(this.mArray[var4 << 1])) {
                  return var4;
               }

               --var4;
            }

            return ~var3;
         }
      }
   }

   public int indexOfKey(Object var1) {
      int var2;
      if (var1 == null) {
         var2 = this.indexOfNull();
      } else {
         var2 = this.indexOf(var1, var1.hashCode());
      }

      return var2;
   }

   int indexOfNull() {
      int var2 = this.mSize;
      if (var2 == 0) {
         return -1;
      } else {
         int var3 = binarySearchHashes(this.mHashes, var2, 0);
         if (var3 < 0) {
            return var3;
         } else if (this.mArray[var3 << 1] == null) {
            return var3;
         } else {
            int var1;
            for(var1 = var3 + 1; var1 < var2 && this.mHashes[var1] == 0; ++var1) {
               if (this.mArray[var1 << 1] == null) {
                  return var1;
               }
            }

            for(var2 = var3 - 1; var2 >= 0 && this.mHashes[var2] == 0; --var2) {
               if (this.mArray[var2 << 1] == null) {
                  return var2;
               }
            }

            return ~var1;
         }
      }
   }

   int indexOfValue(Object var1) {
      int var3 = this.mSize * 2;
      Object[] var4 = this.mArray;
      int var2;
      if (var1 == null) {
         for(var2 = 1; var2 < var3; var2 += 2) {
            if (var4[var2] == null) {
               return var2 >> 1;
            }
         }
      } else {
         for(var2 = 1; var2 < var3; var2 += 2) {
            if (var1.equals(var4[var2])) {
               return var2 >> 1;
            }
         }
      }

      return -1;
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

   public Object keyAt(int var1) {
      return this.mArray[var1 << 1];
   }

   public Object put(Object var1, Object var2) {
      int var5 = this.mSize;
      int var3;
      int var4;
      if (var1 == null) {
         var3 = this.indexOfNull();
         var4 = 0;
      } else {
         var4 = var1.hashCode();
         var3 = this.indexOf(var1, var4);
      }

      Object[] var10;
      if (var3 >= 0) {
         var3 = (var3 << 1) + 1;
         var10 = this.mArray;
         var1 = var10[var3];
         var10[var3] = var2;
         return var1;
      } else {
         int var6 = ~var3;
         int[] var8 = this.mHashes;
         int[] var7;
         if (var5 >= var8.length) {
            var3 = 4;
            if (var5 >= 8) {
               var3 = (var5 >> 1) + var5;
            } else if (var5 >= 4) {
               var3 = 8;
            }

            Object[] var9 = this.mArray;
            this.allocArrays(var3);
            if (var5 != this.mSize) {
               throw new ConcurrentModificationException();
            }

            var7 = this.mHashes;
            if (var7.length > 0) {
               System.arraycopy(var8, 0, var7, 0, var8.length);
               System.arraycopy(var9, 0, this.mArray, 0, var9.length);
            }

            freeArrays(var8, var9, var5);
         }

         if (var6 < var5) {
            var7 = this.mHashes;
            var3 = var6 + 1;
            System.arraycopy(var7, var6, var7, var3, var5 - var6);
            var10 = this.mArray;
            System.arraycopy(var10, var6 << 1, var10, var3 << 1, this.mSize - var6 << 1);
         }

         var3 = this.mSize;
         if (var5 == var3) {
            var7 = this.mHashes;
            if (var6 < var7.length) {
               var7[var6] = var4;
               var10 = this.mArray;
               var4 = var6 << 1;
               var10[var4] = var1;
               var10[var4 + 1] = var2;
               this.mSize = var3 + 1;
               return null;
            }
         }

         throw new ConcurrentModificationException();
      }
   }

   public void putAll(SimpleArrayMap var1) {
      int var3 = var1.mSize;
      this.ensureCapacity(this.mSize + var3);
      int var4 = this.mSize;
      int var2 = 0;
      if (var4 == 0) {
         if (var3 > 0) {
            System.arraycopy(var1.mHashes, 0, this.mHashes, 0, var3);
            System.arraycopy(var1.mArray, 0, this.mArray, 0, var3 << 1);
            this.mSize = var3;
         }
      } else {
         while(var2 < var3) {
            this.put(var1.keyAt(var2), var1.valueAt(var2));
            ++var2;
         }
      }

   }

   public Object putIfAbsent(Object var1, Object var2) {
      Object var4 = this.get(var1);
      Object var3 = var4;
      if (var4 == null) {
         var3 = this.put(var1, var2);
      }

      return var3;
   }

   public Object remove(Object var1) {
      int var2 = this.indexOfKey(var1);
      return var2 >= 0 ? this.removeAt(var2) : null;
   }

   public boolean remove(Object var1, Object var2) {
      int var3 = this.indexOfKey(var1);
      if (var3 >= 0) {
         var1 = this.valueAt(var3);
         if (var2 == var1 || var2 != null && var2.equals(var1)) {
            this.removeAt(var3);
            return true;
         }
      }

      return false;
   }

   public Object removeAt(int var1) {
      Object[] var10 = this.mArray;
      int var5 = var1 << 1;
      Object var7 = var10[var5 + 1];
      int var4 = this.mSize;
      int var2 = 0;
      if (var4 <= 1) {
         freeArrays(this.mHashes, var10, var4);
         this.mHashes = ContainerHelpers.EMPTY_INTS;
         this.mArray = ContainerHelpers.EMPTY_OBJECTS;
         var1 = var2;
      } else {
         int var3 = var4 - 1;
         int[] var9 = this.mHashes;
         int var6 = var9.length;
         var2 = 8;
         if (var6 > 8 && var4 < var9.length / 3) {
            if (var4 > 8) {
               var2 = var4 + (var4 >> 1);
            }

            this.allocArrays(var2);
            if (var4 != this.mSize) {
               throw new ConcurrentModificationException();
            }

            if (var1 > 0) {
               System.arraycopy(var9, 0, this.mHashes, 0, var1);
               System.arraycopy(var10, 0, this.mArray, 0, var5);
            }

            if (var1 < var3) {
               var6 = var1 + 1;
               int[] var11 = this.mHashes;
               var2 = var3 - var1;
               System.arraycopy(var9, var6, var11, var1, var2);
               System.arraycopy(var10, var6 << 1, this.mArray, var5, var2 << 1);
            }
         } else {
            Object[] var8;
            if (var1 < var3) {
               var6 = var1 + 1;
               var2 = var3 - var1;
               System.arraycopy(var9, var6, var9, var1, var2);
               var8 = this.mArray;
               System.arraycopy(var8, var6 << 1, var8, var5, var2 << 1);
            }

            var8 = this.mArray;
            var1 = var3 << 1;
            var8[var1] = null;
            var8[var1 + 1] = null;
         }

         var1 = var3;
      }

      if (var4 == this.mSize) {
         this.mSize = var1;
         return var7;
      } else {
         throw new ConcurrentModificationException();
      }
   }

   public Object replace(Object var1, Object var2) {
      int var3 = this.indexOfKey(var1);
      return var3 >= 0 ? this.setValueAt(var3, var2) : null;
   }

   public boolean replace(Object var1, Object var2, Object var3) {
      int var4 = this.indexOfKey(var1);
      if (var4 >= 0) {
         var1 = this.valueAt(var4);
         if (var1 == var2 || var2 != null && var2.equals(var1)) {
            this.setValueAt(var4, var3);
            return true;
         }
      }

      return false;
   }

   public Object setValueAt(int var1, Object var2) {
      var1 = (var1 << 1) + 1;
      Object[] var3 = this.mArray;
      Object var4 = var3[var1];
      var3[var1] = var2;
      return var4;
   }

   public int size() {
      return this.mSize;
   }

   public String toString() {
      if (this.isEmpty()) {
         return "{}";
      } else {
         StringBuilder var2 = new StringBuilder(this.mSize * 28);
         var2.append('{');

         for(int var1 = 0; var1 < this.mSize; ++var1) {
            if (var1 > 0) {
               var2.append(", ");
            }

            Object var3 = this.keyAt(var1);
            if (var3 != this) {
               var2.append(var3);
            } else {
               var2.append("(this Map)");
            }

            var2.append('=');
            var3 = this.valueAt(var1);
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
      return this.mArray[(var1 << 1) + 1];
   }
}
