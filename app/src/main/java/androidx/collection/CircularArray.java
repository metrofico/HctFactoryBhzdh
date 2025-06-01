package androidx.collection;

public final class CircularArray {
   private int mCapacityBitmask;
   private Object[] mElements;
   private int mHead;
   private int mTail;

   public CircularArray() {
      this(8);
   }

   public CircularArray(int var1) {
      if (var1 >= 1) {
         if (var1 <= 1073741824) {
            int var2 = var1;
            if (Integer.bitCount(var1) != 1) {
               var2 = Integer.highestOneBit(var1 - 1) << 1;
            }

            this.mCapacityBitmask = var2 - 1;
            this.mElements = (Object[])(new Object[var2]);
         } else {
            throw new IllegalArgumentException("capacity must be <= 2^30");
         }
      } else {
         throw new IllegalArgumentException("capacity must be >= 1");
      }
   }

   private void doubleCapacity() {
      Object[] var5 = this.mElements;
      int var4 = var5.length;
      int var3 = this.mHead;
      int var2 = var4 - var3;
      int var1 = var4 << 1;
      if (var1 >= 0) {
         Object[] var6 = new Object[var1];
         System.arraycopy(var5, var3, var6, 0, var2);
         System.arraycopy(this.mElements, 0, var6, var2, this.mHead);
         this.mElements = (Object[])var6;
         this.mHead = 0;
         this.mTail = var4;
         this.mCapacityBitmask = var1 - 1;
      } else {
         throw new RuntimeException("Max array capacity exceeded");
      }
   }

   public void addFirst(Object var1) {
      int var2 = this.mHead - 1 & this.mCapacityBitmask;
      this.mHead = var2;
      this.mElements[var2] = var1;
      if (var2 == this.mTail) {
         this.doubleCapacity();
      }

   }

   public void addLast(Object var1) {
      Object[] var3 = this.mElements;
      int var2 = this.mTail;
      var3[var2] = var1;
      var2 = this.mCapacityBitmask & var2 + 1;
      this.mTail = var2;
      if (var2 == this.mHead) {
         this.doubleCapacity();
      }

   }

   public void clear() {
      this.removeFromStart(this.size());
   }

   public Object get(int var1) {
      if (var1 >= 0 && var1 < this.size()) {
         Object[] var3 = this.mElements;
         int var2 = this.mHead;
         return var3[this.mCapacityBitmask & var2 + var1];
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public Object getFirst() {
      int var1 = this.mHead;
      if (var1 != this.mTail) {
         return this.mElements[var1];
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public Object getLast() {
      int var2 = this.mHead;
      int var1 = this.mTail;
      if (var2 != var1) {
         return this.mElements[var1 - 1 & this.mCapacityBitmask];
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public boolean isEmpty() {
      boolean var1;
      if (this.mHead == this.mTail) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Object popFirst() {
      int var1 = this.mHead;
      if (var1 != this.mTail) {
         Object[] var2 = this.mElements;
         Object var3 = var2[var1];
         var2[var1] = null;
         this.mHead = var1 + 1 & this.mCapacityBitmask;
         return var3;
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public Object popLast() {
      int var1 = this.mHead;
      int var2 = this.mTail;
      if (var1 != var2) {
         var1 = this.mCapacityBitmask & var2 - 1;
         Object[] var3 = this.mElements;
         Object var4 = var3[var1];
         var3[var1] = null;
         this.mTail = var1;
         return var4;
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   }

   public void removeFromEnd(int var1) {
      if (var1 > 0) {
         if (var1 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
         } else {
            int var2 = 0;
            int var3 = this.mTail;
            if (var1 < var3) {
               var2 = var3 - var1;
            }

            var3 = var2;

            while(true) {
               int var4 = this.mTail;
               if (var3 >= var4) {
                  var2 = var4 - var2;
                  var1 -= var2;
                  this.mTail = var4 - var2;
                  if (var1 > 0) {
                     var2 = this.mElements.length;
                     this.mTail = var2;
                     var2 -= var1;

                     for(var1 = var2; var1 < this.mTail; ++var1) {
                        this.mElements[var1] = null;
                     }

                     this.mTail = var2;
                  }

                  return;
               }

               this.mElements[var3] = null;
               ++var3;
            }
         }
      }
   }

   public void removeFromStart(int var1) {
      if (var1 > 0) {
         if (var1 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
         } else {
            int var5 = this.mElements.length;
            int var4 = this.mHead;
            int var2 = var5;
            int var3 = var4;
            if (var1 < var5 - var4) {
               var2 = var4 + var1;
               var3 = var4;
            }

            while(var3 < var2) {
               this.mElements[var3] = null;
               ++var3;
            }

            var3 = this.mHead;
            var4 = var2 - var3;
            var2 = var1 - var4;
            this.mHead = this.mCapacityBitmask & var3 + var4;
            if (var2 > 0) {
               for(var1 = 0; var1 < var2; ++var1) {
                  this.mElements[var1] = null;
               }

               this.mHead = var2;
            }

         }
      }
   }

   public int size() {
      return this.mTail - this.mHead & this.mCapacityBitmask;
   }
}
