package androidx.recyclerview.widget;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList {
   private static final int CAPACITY_GROWTH = 10;
   private static final int DELETION = 2;
   private static final int INSERTION = 1;
   public static final int INVALID_POSITION = -1;
   private static final int LOOKUP = 4;
   private static final int MIN_CAPACITY = 10;
   private BatchedCallback mBatchedCallback;
   private Callback mCallback;
   Object[] mData;
   private int mNewDataStart;
   private Object[] mOldData;
   private int mOldDataSize;
   private int mOldDataStart;
   private int mSize;
   private final Class mTClass;

   public SortedList(Class var1, Callback var2) {
      this(var1, var2, 10);
   }

   public SortedList(Class var1, Callback var2, int var3) {
      this.mTClass = var1;
      this.mData = (Object[])Array.newInstance(var1, var3);
      this.mCallback = var2;
      this.mSize = 0;
   }

   private int add(Object var1, boolean var2) {
      int var4 = this.findIndexOf(var1, this.mData, 0, this.mSize, 1);
      int var3;
      if (var4 == -1) {
         var3 = 0;
      } else {
         var3 = var4;
         if (var4 < this.mSize) {
            Object var5 = this.mData[var4];
            var3 = var4;
            if (this.mCallback.areItemsTheSame(var5, var1)) {
               if (this.mCallback.areContentsTheSame(var5, var1)) {
                  this.mData[var4] = var1;
                  return var4;
               }

               this.mData[var4] = var1;
               Callback var6 = this.mCallback;
               var6.onChanged(var4, 1, var6.getChangePayload(var5, var1));
               return var4;
            }
         }
      }

      this.addToData(var3, var1);
      if (var2) {
         this.mCallback.onInserted(var3, 1);
      }

      return var3;
   }

   private void addAllInternal(Object[] var1) {
      if (var1.length >= 1) {
         int var2 = this.sortAndDedup(var1);
         if (this.mSize == 0) {
            this.mData = var1;
            this.mSize = var2;
            this.mCallback.onInserted(0, var2);
         } else {
            this.merge(var1, var2);
         }

      }
   }

   private void addToData(int var1, Object var2) {
      int var3 = this.mSize;
      if (var1 <= var3) {
         Object[] var4 = this.mData;
         if (var3 == var4.length) {
            var4 = (Object[])Array.newInstance(this.mTClass, var4.length + 10);
            System.arraycopy(this.mData, 0, var4, 0, var1);
            var4[var1] = var2;
            System.arraycopy(this.mData, var1, var4, var1 + 1, this.mSize - var1);
            this.mData = var4;
         } else {
            System.arraycopy(var4, var1, var4, var1 + 1, var3 - var1);
            this.mData[var1] = var2;
         }

         ++this.mSize;
      } else {
         throw new IndexOutOfBoundsException("cannot add item to " + var1 + " because size is " + this.mSize);
      }
   }

   private Object[] copyArray(Object[] var1) {
      Object[] var2 = (Object[])Array.newInstance(this.mTClass, var1.length);
      System.arraycopy(var1, 0, var2, 0, var1.length);
      return var2;
   }

   private int findIndexOf(Object var1, Object[] var2, int var3, int var4, int var5) {
      while(var3 < var4) {
         int var6 = (var3 + var4) / 2;
         Object var8 = var2[var6];
         int var7 = this.mCallback.compare(var8, var1);
         if (var7 < 0) {
            var3 = var6 + 1;
         } else {
            if (var7 == 0) {
               if (this.mCallback.areItemsTheSame(var8, var1)) {
                  return var6;
               }

               var3 = this.linearEqualitySearch(var1, var6, var3, var4);
               if (var5 == 1) {
                  if (var3 == -1) {
                     var3 = var6;
                  }

                  return var3;
               }

               return var3;
            }

            var4 = var6;
         }
      }

      if (var5 != 1) {
         var3 = -1;
      }

      return var3;
   }

   private int findSameItem(Object var1, Object[] var2, int var3, int var4) {
      while(var3 < var4) {
         if (this.mCallback.areItemsTheSame(var2[var3], var1)) {
            return var3;
         }

         ++var3;
      }

      return -1;
   }

   private int linearEqualitySearch(Object var1, int var2, int var3, int var4) {
      int var6 = var2 - 1;

      int var5;
      Object var7;
      while(true) {
         var5 = var2;
         if (var6 < var3) {
            break;
         }

         var7 = this.mData[var6];
         if (this.mCallback.compare(var7, var1) != 0) {
            var5 = var2;
            break;
         }

         if (this.mCallback.areItemsTheSame(var7, var1)) {
            return var6;
         }

         --var6;
      }

      while(true) {
         var2 = var5 + 1;
         if (var2 >= var4) {
            break;
         }

         var7 = this.mData[var2];
         if (this.mCallback.compare(var7, var1) != 0) {
            break;
         }

         var5 = var2;
         if (this.mCallback.areItemsTheSame(var7, var1)) {
            return var2;
         }
      }

      return -1;
   }

   private void merge(Object[] var1, int var2) {
      boolean var5 = this.mCallback instanceof BatchedCallback ^ true;
      if (var5) {
         this.beginBatchedUpdates();
      }

      this.mOldData = this.mData;
      int var3 = 0;
      this.mOldDataStart = 0;
      int var4 = this.mSize;
      this.mOldDataSize = var4;
      this.mData = (Object[])Array.newInstance(this.mTClass, var4 + var2 + 10);
      this.mNewDataStart = 0;

      while(true) {
         var4 = this.mOldDataStart;
         int var6 = this.mOldDataSize;
         if (var4 >= var6 && var3 >= var2) {
            break;
         }

         if (var4 == var6) {
            var2 -= var3;
            System.arraycopy(var1, var3, this.mData, this.mNewDataStart, var2);
            var3 = this.mNewDataStart + var2;
            this.mNewDataStart = var3;
            this.mSize += var2;
            this.mCallback.onInserted(var3 - var2, var2);
            break;
         }

         if (var3 == var2) {
            var2 = var6 - var4;
            System.arraycopy(this.mOldData, var4, this.mData, this.mNewDataStart, var2);
            this.mNewDataStart += var2;
            break;
         }

         Object var8 = this.mOldData[var4];
         Object var7 = var1[var3];
         var4 = this.mCallback.compare(var8, var7);
         if (var4 > 0) {
            Object[] var11 = this.mData;
            var6 = this.mNewDataStart;
            var4 = var6 + 1;
            this.mNewDataStart = var4;
            var11[var6] = var7;
            ++this.mSize;
            ++var3;
            this.mCallback.onInserted(var4 - 1, 1);
         } else if (var4 == 0 && this.mCallback.areItemsTheSame(var8, var7)) {
            Object[] var9 = this.mData;
            var4 = this.mNewDataStart++;
            var9[var4] = var7;
            var4 = var3 + 1;
            ++this.mOldDataStart;
            var3 = var4;
            if (!this.mCallback.areContentsTheSame(var8, var7)) {
               Callback var12 = this.mCallback;
               var12.onChanged(this.mNewDataStart - 1, 1, var12.getChangePayload(var8, var7));
               var3 = var4;
            }
         } else {
            Object[] var10 = this.mData;
            var4 = this.mNewDataStart++;
            var10[var4] = var8;
            ++this.mOldDataStart;
         }
      }

      this.mOldData = null;
      if (var5) {
         this.endBatchedUpdates();
      }

   }

   private boolean remove(Object var1, boolean var2) {
      int var3 = this.findIndexOf(var1, this.mData, 0, this.mSize, 2);
      if (var3 == -1) {
         return false;
      } else {
         this.removeItemAtIndex(var3, var2);
         return true;
      }
   }

   private void removeItemAtIndex(int var1, boolean var2) {
      Object[] var4 = this.mData;
      System.arraycopy(var4, var1 + 1, var4, var1, this.mSize - var1 - 1);
      int var3 = this.mSize - 1;
      this.mSize = var3;
      this.mData[var3] = null;
      if (var2) {
         this.mCallback.onRemoved(var1, 1);
      }

   }

   private void replaceAllInsert(Object var1) {
      Object[] var3 = this.mData;
      int var2 = this.mNewDataStart;
      var3[var2] = var1;
      ++var2;
      this.mNewDataStart = var2;
      ++this.mSize;
      this.mCallback.onInserted(var2 - 1, 1);
   }

   private void replaceAllInternal(Object[] var1) {
      boolean var2 = this.mCallback instanceof BatchedCallback ^ true;
      if (var2) {
         this.beginBatchedUpdates();
      }

      this.mOldDataStart = 0;
      this.mOldDataSize = this.mSize;
      this.mOldData = this.mData;
      this.mNewDataStart = 0;
      int var4 = this.sortAndDedup(var1);
      this.mData = (Object[])Array.newInstance(this.mTClass, var4);

      while(true) {
         int var3 = this.mNewDataStart;
         if (var3 >= var4 && this.mOldDataStart >= this.mOldDataSize) {
            break;
         }

         int var5 = this.mOldDataStart;
         int var6 = this.mOldDataSize;
         if (var5 >= var6) {
            var4 -= var3;
            System.arraycopy(var1, var3, this.mData, var3, var4);
            this.mNewDataStart += var4;
            this.mSize += var4;
            this.mCallback.onInserted(var3, var4);
            break;
         }

         if (var3 >= var4) {
            var4 = var6 - var5;
            this.mSize -= var4;
            this.mCallback.onRemoved(var3, var4);
            break;
         }

         Object var8 = this.mOldData[var5];
         Object var7 = var1[var3];
         var3 = this.mCallback.compare(var8, var7);
         if (var3 < 0) {
            this.replaceAllRemove();
         } else if (var3 > 0) {
            this.replaceAllInsert(var7);
         } else if (!this.mCallback.areItemsTheSame(var8, var7)) {
            this.replaceAllRemove();
            this.replaceAllInsert(var7);
         } else {
            Object[] var9 = this.mData;
            var3 = this.mNewDataStart;
            var9[var3] = var7;
            ++this.mOldDataStart;
            this.mNewDataStart = var3 + 1;
            if (!this.mCallback.areContentsTheSame(var8, var7)) {
               Callback var10 = this.mCallback;
               var10.onChanged(this.mNewDataStart - 1, 1, var10.getChangePayload(var8, var7));
            }
         }
      }

      this.mOldData = null;
      if (var2) {
         this.endBatchedUpdates();
      }

   }

   private void replaceAllRemove() {
      --this.mSize;
      ++this.mOldDataStart;
      this.mCallback.onRemoved(this.mNewDataStart, 1);
   }

   private int sortAndDedup(Object[] var1) {
      if (var1.length == 0) {
         return 0;
      } else {
         Arrays.sort(var1, this.mCallback);
         int var3 = 1;
         int var4 = 0;

         int var2;
         for(var2 = 1; var3 < var1.length; ++var3) {
            Object var6 = var1[var3];
            if (this.mCallback.compare(var1[var4], var6) == 0) {
               int var5 = this.findSameItem(var6, var1, var4, var2);
               if (var5 != -1) {
                  var1[var5] = var6;
               } else {
                  if (var2 != var3) {
                     var1[var2] = var6;
                  }

                  ++var2;
               }
            } else {
               if (var2 != var3) {
                  var1[var2] = var6;
               }

               var4 = var2++;
            }
         }

         return var2;
      }
   }

   private void throwIfInMutationOperation() {
      if (this.mOldData != null) {
         throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
      }
   }

   public int add(Object var1) {
      this.throwIfInMutationOperation();
      return this.add(var1, true);
   }

   public void addAll(Collection var1) {
      this.addAll(var1.toArray((Object[])Array.newInstance(this.mTClass, var1.size())), true);
   }

   public void addAll(Object... var1) {
      this.addAll(var1, false);
   }

   public void addAll(Object[] var1, boolean var2) {
      this.throwIfInMutationOperation();
      if (var1.length != 0) {
         if (var2) {
            this.addAllInternal(var1);
         } else {
            this.addAllInternal(this.copyArray(var1));
         }

      }
   }

   public void beginBatchedUpdates() {
      this.throwIfInMutationOperation();
      if (!(this.mCallback instanceof BatchedCallback)) {
         if (this.mBatchedCallback == null) {
            this.mBatchedCallback = new BatchedCallback(this.mCallback);
         }

         this.mCallback = this.mBatchedCallback;
      }
   }

   public void clear() {
      this.throwIfInMutationOperation();
      int var1 = this.mSize;
      if (var1 != 0) {
         Arrays.fill(this.mData, 0, var1, (Object)null);
         this.mSize = 0;
         this.mCallback.onRemoved(0, var1);
      }
   }

   public void endBatchedUpdates() {
      this.throwIfInMutationOperation();
      Callback var1 = this.mCallback;
      if (var1 instanceof BatchedCallback) {
         ((BatchedCallback)var1).dispatchLastEvent();
      }

      Callback var2 = this.mCallback;
      BatchedCallback var3 = this.mBatchedCallback;
      if (var2 == var3) {
         this.mCallback = var3.mWrappedCallback;
      }

   }

   public Object get(int var1) throws IndexOutOfBoundsException {
      if (var1 < this.mSize && var1 >= 0) {
         Object[] var3 = this.mOldData;
         if (var3 != null) {
            int var2 = this.mNewDataStart;
            if (var1 >= var2) {
               return var3[var1 - var2 + this.mOldDataStart];
            }
         }

         return this.mData[var1];
      } else {
         throw new IndexOutOfBoundsException("Asked to get item at " + var1 + " but size is " + this.mSize);
      }
   }

   public int indexOf(Object var1) {
      if (this.mOldData != null) {
         int var2 = this.findIndexOf(var1, this.mData, 0, this.mNewDataStart, 4);
         if (var2 != -1) {
            return var2;
         } else {
            var2 = this.findIndexOf(var1, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
            return var2 != -1 ? var2 - this.mOldDataStart + this.mNewDataStart : -1;
         }
      } else {
         return this.findIndexOf(var1, this.mData, 0, this.mSize, 4);
      }
   }

   public void recalculatePositionOfItemAt(int var1) {
      this.throwIfInMutationOperation();
      Object var3 = this.get(var1);
      this.removeItemAtIndex(var1, false);
      int var2 = this.add(var3, false);
      if (var1 != var2) {
         this.mCallback.onMoved(var1, var2);
      }

   }

   public boolean remove(Object var1) {
      this.throwIfInMutationOperation();
      return this.remove(var1, true);
   }

   public Object removeItemAt(int var1) {
      this.throwIfInMutationOperation();
      Object var2 = this.get(var1);
      this.removeItemAtIndex(var1, true);
      return var2;
   }

   public void replaceAll(Collection var1) {
      this.replaceAll(var1.toArray((Object[])Array.newInstance(this.mTClass, var1.size())), true);
   }

   public void replaceAll(Object... var1) {
      this.replaceAll(var1, false);
   }

   public void replaceAll(Object[] var1, boolean var2) {
      this.throwIfInMutationOperation();
      if (var2) {
         this.replaceAllInternal(var1);
      } else {
         this.replaceAllInternal(this.copyArray(var1));
      }

   }

   public int size() {
      return this.mSize;
   }

   public void updateItemAt(int var1, Object var2) {
      this.throwIfInMutationOperation();
      Object var4 = this.get(var1);
      boolean var3;
      if (var4 != var2 && this.mCallback.areContentsTheSame(var4, var2)) {
         var3 = false;
      } else {
         var3 = true;
      }

      Callback var5;
      if (var4 != var2 && this.mCallback.compare(var4, var2) == 0) {
         this.mData[var1] = var2;
         if (var3) {
            var5 = this.mCallback;
            var5.onChanged(var1, 1, var5.getChangePayload(var4, var2));
         }

      } else {
         if (var3) {
            var5 = this.mCallback;
            var5.onChanged(var1, 1, var5.getChangePayload(var4, var2));
         }

         this.removeItemAtIndex(var1, false);
         int var6 = this.add(var2, false);
         if (var1 != var6) {
            this.mCallback.onMoved(var1, var6);
         }

      }
   }

   public static class BatchedCallback extends Callback {
      private final BatchingListUpdateCallback mBatchingListUpdateCallback;
      final Callback mWrappedCallback;

      public BatchedCallback(Callback var1) {
         this.mWrappedCallback = var1;
         this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(var1);
      }

      public boolean areContentsTheSame(Object var1, Object var2) {
         return this.mWrappedCallback.areContentsTheSame(var1, var2);
      }

      public boolean areItemsTheSame(Object var1, Object var2) {
         return this.mWrappedCallback.areItemsTheSame(var1, var2);
      }

      public int compare(Object var1, Object var2) {
         return this.mWrappedCallback.compare(var1, var2);
      }

      public void dispatchLastEvent() {
         this.mBatchingListUpdateCallback.dispatchLastEvent();
      }

      public Object getChangePayload(Object var1, Object var2) {
         return this.mWrappedCallback.getChangePayload(var1, var2);
      }

      public void onChanged(int var1, int var2) {
         this.mBatchingListUpdateCallback.onChanged(var1, var2, (Object)null);
      }

      public void onChanged(int var1, int var2, Object var3) {
         this.mBatchingListUpdateCallback.onChanged(var1, var2, var3);
      }

      public void onInserted(int var1, int var2) {
         this.mBatchingListUpdateCallback.onInserted(var1, var2);
      }

      public void onMoved(int var1, int var2) {
         this.mBatchingListUpdateCallback.onMoved(var1, var2);
      }

      public void onRemoved(int var1, int var2) {
         this.mBatchingListUpdateCallback.onRemoved(var1, var2);
      }
   }

   public abstract static class Callback implements Comparator, ListUpdateCallback {
      public abstract boolean areContentsTheSame(Object var1, Object var2);

      public abstract boolean areItemsTheSame(Object var1, Object var2);

      public abstract int compare(Object var1, Object var2);

      public Object getChangePayload(Object var1, Object var2) {
         return null;
      }

      public abstract void onChanged(int var1, int var2);

      public void onChanged(int var1, int var2, Object var3) {
         this.onChanged(var1, var2);
      }
   }
}
