package androidx.recyclerview.widget;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DiffUtil {
   private static final Comparator DIAGONAL_COMPARATOR = new Comparator() {
      public int compare(Diagonal var1, Diagonal var2) {
         return var1.x - var2.x;
      }
   };

   private DiffUtil() {
   }

   private static Snake backward(Range var0, Callback var1, CenteredArray var2, CenteredArray var3, int var4) {
      boolean var6;
      if ((var0.oldSize() - var0.newSize()) % 2 == 0) {
         var6 = true;
      } else {
         var6 = false;
      }

      int var12 = var0.oldSize();
      int var13 = var0.newSize();
      int var11 = -var4;

      for(int var7 = var11; var7 <= var4; var7 += 2) {
         int var5;
         int var8;
         if (var7 == var11 || var7 != var4 && var3.get(var7 + 1) < var3.get(var7 - 1)) {
            var8 = var3.get(var7 + 1);
            var5 = var8;
         } else {
            var8 = var3.get(var7 - 1);
            var5 = var8 - 1;
         }

         int var10 = var0.newListEnd - (var0.oldListEnd - var5 - var7);
         int var9;
         if (var4 != 0 && var5 == var8) {
            var9 = var10 + 1;
         } else {
            var9 = var10;
         }

         while(var5 > var0.oldListStart && var10 > var0.newListStart && var1.areItemsTheSame(var5 - 1, var10 - 1)) {
            --var5;
            --var10;
         }

         var3.set(var7, var5);
         if (var6) {
            int var14 = var12 - var13 - var7;
            if (var14 >= var11 && var14 <= var4 && var2.get(var14) >= var5) {
               Snake var15 = new Snake();
               var15.startX = var5;
               var15.startY = var10;
               var15.endX = var8;
               var15.endY = var9;
               var15.reverse = true;
               return var15;
            }
         }
      }

      return null;
   }

   public static DiffResult calculateDiff(Callback var0) {
      return calculateDiff(var0, true);
   }

   public static DiffResult calculateDiff(Callback var0, boolean var1) {
      int var2 = var0.getOldListSize();
      int var3 = var0.getNewListSize();
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();
      var7.add(new Range(0, var2, 0, var3));
      var2 = (var2 + var3 + 1) / 2 * 2 + 1;
      CenteredArray var9 = new CenteredArray(var2);
      CenteredArray var10 = new CenteredArray(var2);
      ArrayList var5 = new ArrayList();

      while(!var7.isEmpty()) {
         Range var8 = (Range)var7.remove(var7.size() - 1);
         Snake var11 = midPoint(var8, var0, var9, var10);
         if (var11 != null) {
            if (var11.diagonalSize() > 0) {
               var6.add(var11.toDiagonal());
            }

            Range var4;
            if (var5.isEmpty()) {
               var4 = new Range();
            } else {
               var4 = (Range)var5.remove(var5.size() - 1);
            }

            var4.oldListStart = var8.oldListStart;
            var4.newListStart = var8.newListStart;
            var4.oldListEnd = var11.startX;
            var4.newListEnd = var11.startY;
            var7.add(var4);
            var8.oldListEnd = var8.oldListEnd;
            var8.newListEnd = var8.newListEnd;
            var8.oldListStart = var11.endX;
            var8.newListStart = var11.endY;
            var7.add(var8);
         } else {
            var5.add(var8);
         }
      }

      Collections.sort(var6, DIAGONAL_COMPARATOR);
      return new DiffResult(var0, var6, var9.backingData(), var10.backingData(), var1);
   }

   private static Snake forward(Range var0, Callback var1, CenteredArray var2, CenteredArray var3, int var4) {
      int var5 = Math.abs(var0.oldSize() - var0.newSize());
      boolean var6 = true;
      if (var5 % 2 != 1) {
         var6 = false;
      }

      int var12 = var0.oldSize();
      int var13 = var0.newSize();
      int var11 = -var4;

      for(int var7 = var11; var7 <= var4; var7 += 2) {
         int var8;
         if (var7 == var11 || var7 != var4 && var2.get(var7 + 1) > var2.get(var7 - 1)) {
            var8 = var2.get(var7 + 1);
            var5 = var8;
         } else {
            var8 = var2.get(var7 - 1);
            var5 = var8 + 1;
         }

         int var10 = var0.newListStart + (var5 - var0.oldListStart) - var7;
         int var9;
         if (var4 != 0 && var5 == var8) {
            var9 = var10 - 1;
         } else {
            var9 = var10;
         }

         while(var5 < var0.oldListEnd && var10 < var0.newListEnd && var1.areItemsTheSame(var5, var10)) {
            ++var5;
            ++var10;
         }

         var2.set(var7, var5);
         if (var6) {
            int var14 = var12 - var13 - var7;
            if (var14 >= var11 + 1 && var14 <= var4 - 1 && var3.get(var14) <= var5) {
               Snake var15 = new Snake();
               var15.startX = var8;
               var15.startY = var9;
               var15.endX = var5;
               var15.endY = var10;
               var15.reverse = false;
               return var15;
            }
         }
      }

      return null;
   }

   private static Snake midPoint(Range var0, Callback var1, CenteredArray var2, CenteredArray var3) {
      if (var0.oldSize() >= 1 && var0.newSize() >= 1) {
         int var5 = (var0.oldSize() + var0.newSize() + 1) / 2;
         var2.set(1, var0.oldListStart);
         var3.set(1, var0.oldListEnd);

         for(int var4 = 0; var4 < var5; ++var4) {
            Snake var6 = forward(var0, var1, var2, var3, var4);
            if (var6 != null) {
               return var6;
            }

            var6 = backward(var0, var1, var2, var3, var4);
            if (var6 != null) {
               return var6;
            }
         }
      }

      return null;
   }

   public abstract static class Callback {
      public abstract boolean areContentsTheSame(int var1, int var2);

      public abstract boolean areItemsTheSame(int var1, int var2);

      public Object getChangePayload(int var1, int var2) {
         return null;
      }

      public abstract int getNewListSize();

      public abstract int getOldListSize();
   }

   static class CenteredArray {
      private final int[] mData;
      private final int mMid;

      CenteredArray(int var1) {
         int[] var2 = new int[var1];
         this.mData = var2;
         this.mMid = var2.length / 2;
      }

      int[] backingData() {
         return this.mData;
      }

      public void fill(int var1) {
         Arrays.fill(this.mData, var1);
      }

      int get(int var1) {
         return this.mData[var1 + this.mMid];
      }

      void set(int var1, int var2) {
         this.mData[var1 + this.mMid] = var2;
      }
   }

   static class Diagonal {
      public final int size;
      public final int x;
      public final int y;

      Diagonal(int var1, int var2, int var3) {
         this.x = var1;
         this.y = var2;
         this.size = var3;
      }

      int endX() {
         return this.x + this.size;
      }

      int endY() {
         return this.y + this.size;
      }
   }

   public static class DiffResult {
      private static final int FLAG_CHANGED = 2;
      private static final int FLAG_MASK = 15;
      private static final int FLAG_MOVED = 12;
      private static final int FLAG_MOVED_CHANGED = 4;
      private static final int FLAG_MOVED_NOT_CHANGED = 8;
      private static final int FLAG_NOT_CHANGED = 1;
      private static final int FLAG_OFFSET = 4;
      public static final int NO_POSITION = -1;
      private final Callback mCallback;
      private final boolean mDetectMoves;
      private final List mDiagonals;
      private final int[] mNewItemStatuses;
      private final int mNewListSize;
      private final int[] mOldItemStatuses;
      private final int mOldListSize;

      DiffResult(Callback var1, List var2, int[] var3, int[] var4, boolean var5) {
         this.mDiagonals = var2;
         this.mOldItemStatuses = var3;
         this.mNewItemStatuses = var4;
         Arrays.fill(var3, 0);
         Arrays.fill(var4, 0);
         this.mCallback = var1;
         this.mOldListSize = var1.getOldListSize();
         this.mNewListSize = var1.getNewListSize();
         this.mDetectMoves = var5;
         this.addEdgeDiagonals();
         this.findMatchingItems();
      }

      private void addEdgeDiagonals() {
         Diagonal var1;
         if (this.mDiagonals.isEmpty()) {
            var1 = null;
         } else {
            var1 = (Diagonal)this.mDiagonals.get(0);
         }

         if (var1 == null || var1.x != 0 || var1.y != 0) {
            this.mDiagonals.add(0, new Diagonal(0, 0, 0));
         }

         this.mDiagonals.add(new Diagonal(this.mOldListSize, this.mNewListSize, 0));
      }

      private void findMatchingAddition(int var1) {
         int var4 = this.mDiagonals.size();
         int var3 = 0;

         for(int var2 = 0; var3 < var4; ++var3) {
            Diagonal var5;
            for(var5 = (Diagonal)this.mDiagonals.get(var3); var2 < var5.y; ++var2) {
               if (this.mNewItemStatuses[var2] == 0 && this.mCallback.areItemsTheSame(var1, var2)) {
                  byte var6;
                  if (this.mCallback.areContentsTheSame(var1, var2)) {
                     var6 = 8;
                  } else {
                     var6 = 4;
                  }

                  this.mOldItemStatuses[var1] = var2 << 4 | var6;
                  this.mNewItemStatuses[var2] = var1 << 4 | var6;
                  return;
               }
            }

            var2 = var5.endY();
         }

      }

      private void findMatchingItems() {
         Iterator var5 = this.mDiagonals.iterator();

         while(var5.hasNext()) {
            Diagonal var6 = (Diagonal)var5.next();

            for(int var1 = 0; var1 < var6.size; ++var1) {
               int var4 = var6.x + var1;
               int var3 = var6.y + var1;
               byte var2;
               if (this.mCallback.areContentsTheSame(var4, var3)) {
                  var2 = 1;
               } else {
                  var2 = 2;
               }

               this.mOldItemStatuses[var4] = var3 << 4 | var2;
               this.mNewItemStatuses[var3] = var4 << 4 | var2;
            }
         }

         if (this.mDetectMoves) {
            this.findMoveMatches();
         }

      }

      private void findMoveMatches() {
         Iterator var2 = this.mDiagonals.iterator();

         Diagonal var3;
         for(int var1 = 0; var2.hasNext(); var1 = var3.endX()) {
            for(var3 = (Diagonal)var2.next(); var1 < var3.x; ++var1) {
               if (this.mOldItemStatuses[var1] == 0) {
                  this.findMatchingAddition(var1);
               }
            }
         }

      }

      private static PostponedUpdate getPostponedUpdate(Collection var0, int var1, boolean var2) {
         Iterator var3 = var0.iterator();

         PostponedUpdate var5;
         while(true) {
            if (var3.hasNext()) {
               var5 = (PostponedUpdate)var3.next();
               if (var5.posInOwnerList != var1 || var5.removal != var2) {
                  continue;
               }

               var3.remove();
               break;
            }

            var5 = null;
            break;
         }

         while(var3.hasNext()) {
            PostponedUpdate var4 = (PostponedUpdate)var3.next();
            if (var2) {
               --var4.currentPos;
            } else {
               ++var4.currentPos;
            }
         }

         return var5;
      }

      public int convertNewPositionToOld(int var1) {
         if (var1 >= 0 && var1 < this.mNewListSize) {
            var1 = this.mNewItemStatuses[var1];
            return (var1 & 15) == 0 ? -1 : var1 >> 4;
         } else {
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + var1 + ", new list size = " + this.mNewListSize);
         }
      }

      public int convertOldPositionToNew(int var1) {
         if (var1 >= 0 && var1 < this.mOldListSize) {
            var1 = this.mOldItemStatuses[var1];
            return (var1 & 15) == 0 ? -1 : var1 >> 4;
         } else {
            throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + var1 + ", old list size = " + this.mOldListSize);
         }
      }

      public void dispatchUpdatesTo(ListUpdateCallback var1) {
         BatchingListUpdateCallback var15;
         if (var1 instanceof BatchingListUpdateCallback) {
            var15 = (BatchingListUpdateCallback)var1;
         } else {
            var15 = new BatchingListUpdateCallback(var1);
         }

         int var2 = this.mOldListSize;
         ArrayDeque var13 = new ArrayDeque();
         int var4 = this.mOldListSize;
         int var3 = this.mNewListSize;

         for(int var5 = this.mDiagonals.size() - 1; var5 >= 0; --var5) {
            Diagonal var12 = (Diagonal)this.mDiagonals.get(var5);
            int var10 = var12.endX();
            int var9 = var12.endY();
            int var6 = var4;
            var4 = var2;

            while(true) {
               int var8 = 0;
               var2 = var4;
               int var7 = var3;
               PostponedUpdate var14;
               if (var6 <= var10) {
                  while(var7 > var9) {
                     var3 = var7 - 1;
                     var10 = this.mNewItemStatuses[var3];
                     if ((var10 & 12) != 0) {
                        var4 = var10 >> 4;
                        var14 = getPostponedUpdate(var13, var4, true);
                        if (var14 == null) {
                           var13.add(new PostponedUpdate(var3, var2 - var6, false));
                           var7 = var3;
                        } else {
                           var15.onMoved(var2 - var14.currentPos - 1, var6);
                           var7 = var3;
                           if ((var10 & 4) != 0) {
                              var15.onChanged(var6, 1, this.mCallback.getChangePayload(var4, var3));
                              var7 = var3;
                           }
                        }
                     } else {
                        var15.onInserted(var6, 1);
                        ++var2;
                        var7 = var3;
                     }
                  }

                  var6 = var12.x;
                  var4 = var12.y;

                  for(var3 = var8; var3 < var12.size; ++var3) {
                     if ((this.mOldItemStatuses[var6] & 15) == 2) {
                        var15.onChanged(var6, 1, this.mCallback.getChangePayload(var6, var4));
                     }

                     ++var6;
                     ++var4;
                  }

                  var4 = var12.x;
                  var3 = var12.y;
                  break;
               }

               var2 = var6 - 1;
               int var11 = this.mOldItemStatuses[var2];
               if ((var11 & 12) != 0) {
                  var8 = var11 >> 4;
                  var14 = getPostponedUpdate(var13, var8, false);
                  if (var14 != null) {
                     var7 = var4 - var14.currentPos - 1;
                     var15.onMoved(var2, var7);
                     var6 = var2;
                     if ((var11 & 4) != 0) {
                        var15.onChanged(var7, 1, this.mCallback.getChangePayload(var2, var8));
                        var6 = var2;
                     }
                  } else {
                     var13.add(new PostponedUpdate(var2, var4 - var2 - 1, true));
                     var6 = var2;
                  }
               } else {
                  var15.onRemoved(var2, 1);
                  --var4;
                  var6 = var2;
               }
            }
         }

         var15.dispatchLastEvent();
      }

      public void dispatchUpdatesTo(RecyclerView.Adapter var1) {
         this.dispatchUpdatesTo((ListUpdateCallback)(new AdapterListUpdateCallback(var1)));
      }
   }

   public abstract static class ItemCallback {
      public abstract boolean areContentsTheSame(Object var1, Object var2);

      public abstract boolean areItemsTheSame(Object var1, Object var2);

      public Object getChangePayload(Object var1, Object var2) {
         return null;
      }
   }

   private static class PostponedUpdate {
      int currentPos;
      int posInOwnerList;
      boolean removal;

      PostponedUpdate(int var1, int var2, boolean var3) {
         this.posInOwnerList = var1;
         this.currentPos = var2;
         this.removal = var3;
      }
   }

   static class Range {
      int newListEnd;
      int newListStart;
      int oldListEnd;
      int oldListStart;

      public Range() {
      }

      public Range(int var1, int var2, int var3, int var4) {
         this.oldListStart = var1;
         this.oldListEnd = var2;
         this.newListStart = var3;
         this.newListEnd = var4;
      }

      int newSize() {
         return this.newListEnd - this.newListStart;
      }

      int oldSize() {
         return this.oldListEnd - this.oldListStart;
      }
   }

   static class Snake {
      public int endX;
      public int endY;
      public boolean reverse;
      public int startX;
      public int startY;

      int diagonalSize() {
         return Math.min(this.endX - this.startX, this.endY - this.startY);
      }

      boolean hasAdditionOrRemoval() {
         boolean var1;
         if (this.endY - this.startY != this.endX - this.startX) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean isAddition() {
         boolean var1;
         if (this.endY - this.startY > this.endX - this.startX) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      Diagonal toDiagonal() {
         if (this.hasAdditionOrRemoval()) {
            if (this.reverse) {
               return new Diagonal(this.startX, this.startY, this.diagonalSize());
            } else {
               return this.isAddition() ? new Diagonal(this.startX, this.startY + 1, this.diagonalSize()) : new Diagonal(this.startX + 1, this.startY, this.diagonalSize());
            }
         } else {
            int var1 = this.startX;
            return new Diagonal(var1, this.startY, this.endX - var1);
         }
      }
   }
}
