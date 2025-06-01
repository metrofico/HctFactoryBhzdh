package androidx.recyclerview.widget;

import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AdapterHelper implements OpReorderer.Callback {
   private static final boolean DEBUG = false;
   static final int POSITION_TYPE_INVISIBLE = 0;
   static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
   private static final String TAG = "AHT";
   final Callback mCallback;
   final boolean mDisableRecycler;
   private int mExistingUpdateTypes;
   Runnable mOnItemProcessedCallback;
   final OpReorderer mOpReorderer;
   final ArrayList mPendingUpdates;
   final ArrayList mPostponedList;
   private Pools.Pool mUpdateOpPool;

   AdapterHelper(Callback var1) {
      this(var1, false);
   }

   AdapterHelper(Callback var1, boolean var2) {
      this.mUpdateOpPool = new Pools.SimplePool(30);
      this.mPendingUpdates = new ArrayList();
      this.mPostponedList = new ArrayList();
      this.mExistingUpdateTypes = 0;
      this.mCallback = var1;
      this.mDisableRecycler = var2;
      this.mOpReorderer = new OpReorderer(this);
   }

   private void applyAdd(UpdateOp var1) {
      this.postponeAndUpdateViewHolders(var1);
   }

   private void applyMove(UpdateOp var1) {
      this.postponeAndUpdateViewHolders(var1);
   }

   private void applyRemove(UpdateOp var1) {
      int var8 = var1.positionStart;
      int var5 = var1.positionStart + var1.itemCount;
      int var2 = var1.positionStart;
      byte var3 = -1;

      int var6;
      int var11;
      for(var6 = 0; var2 < var5; var6 = var11) {
         boolean var4;
         if (this.mCallback.findViewHolder(var2) == null && !this.canFindInPreLayout(var2)) {
            boolean var10;
            if (var3 == 1) {
               this.postponeAndUpdateViewHolders(this.obtainUpdateOp(2, var8, var6, (Object)null));
               var10 = true;
            } else {
               var10 = false;
            }

            byte var7 = 0;
            var4 = var10;
            var3 = var7;
         } else {
            if (var3 == 0) {
               this.dispatchAndUpdateViewHolders(this.obtainUpdateOp(2, var8, var6, (Object)null));
               var4 = true;
            } else {
               var4 = false;
            }

            var3 = 1;
         }

         if (var4) {
            var2 -= var6;
            var5 -= var6;
            var11 = 1;
         } else {
            var11 = var6 + 1;
         }

         ++var2;
      }

      UpdateOp var9 = var1;
      if (var6 != var1.itemCount) {
         this.recycleUpdateOp(var1);
         var9 = this.obtainUpdateOp(2, var8, var6, (Object)null);
      }

      if (var3 == 0) {
         this.dispatchAndUpdateViewHolders(var9);
      } else {
         this.postponeAndUpdateViewHolders(var9);
      }

   }

   private void applyUpdate(UpdateOp var1) {
      int var4 = var1.positionStart;
      int var9 = var1.positionStart;
      int var8 = var1.itemCount;
      int var2 = var1.positionStart;
      byte var7 = -1;

      int var3;
      int var5;
      for(var3 = 0; var2 < var9 + var8; var3 = var5) {
         int var6;
         byte var11;
         if (this.mCallback.findViewHolder(var2) == null && !this.canFindInPreLayout(var2)) {
            var6 = var4;
            var5 = var3;
            if (var7 == 1) {
               this.postponeAndUpdateViewHolders(this.obtainUpdateOp(4, var4, var3, var1.payload));
               var6 = var2;
               var5 = 0;
            }

            var11 = 0;
            var4 = var6;
            var6 = var5;
         } else {
            var5 = var4;
            var6 = var3;
            if (var7 == 0) {
               this.dispatchAndUpdateViewHolders(this.obtainUpdateOp(4, var4, var3, var1.payload));
               var5 = var2;
               var6 = 0;
            }

            var11 = 1;
            var4 = var5;
         }

         var5 = var6 + 1;
         ++var2;
         var7 = var11;
      }

      UpdateOp var10 = var1;
      if (var3 != var1.itemCount) {
         Object var12 = var1.payload;
         this.recycleUpdateOp(var1);
         var10 = this.obtainUpdateOp(4, var4, var3, var12);
      }

      if (var7 == 0) {
         this.dispatchAndUpdateViewHolders(var10);
      } else {
         this.postponeAndUpdateViewHolders(var10);
      }

   }

   private boolean canFindInPreLayout(int var1) {
      int var4 = this.mPostponedList.size();

      for(int var2 = 0; var2 < var4; ++var2) {
         UpdateOp var7 = (UpdateOp)this.mPostponedList.get(var2);
         if (var7.cmd == 8) {
            if (this.findPositionOffset(var7.itemCount, var2 + 1) == var1) {
               return true;
            }
         } else if (var7.cmd == 1) {
            int var5 = var7.positionStart;
            int var6 = var7.itemCount;

            for(int var3 = var7.positionStart; var3 < var5 + var6; ++var3) {
               if (this.findPositionOffset(var3, var2 + 1) == var1) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private void dispatchAndUpdateViewHolders(UpdateOp var1) {
      if (var1.cmd != 1 && var1.cmd != 8) {
         int var4 = this.updatePositionWithPostponed(var1.positionStart, var1.cmd);
         int var2 = var1.positionStart;
         int var3 = var1.cmd;
         byte var5;
         if (var3 != 2) {
            if (var3 != 4) {
               throw new IllegalArgumentException("op should be remove or update." + var1);
            }

            var5 = 1;
         } else {
            var5 = 0;
         }

         int var6 = 1;

         for(var3 = 1; var6 < var1.itemCount; ++var6) {
            int var8;
            boolean var10;
            label50: {
               label49: {
                  var8 = this.updatePositionWithPostponed(var1.positionStart + var5 * var6, var1.cmd);
                  int var7 = var1.cmd;
                  if (var7 != 2) {
                     if (var7 != 4 || var8 != var4 + 1) {
                        break label49;
                     }
                  } else if (var8 != var4) {
                     break label49;
                  }

                  var10 = true;
                  break label50;
               }

               var10 = false;
            }

            if (var10) {
               ++var3;
            } else {
               UpdateOp var9 = this.obtainUpdateOp(var1.cmd, var4, var3, var1.payload);
               this.dispatchFirstPassAndUpdateViewHolders(var9, var2);
               this.recycleUpdateOp(var9);
               var4 = var2;
               if (var1.cmd == 4) {
                  var4 = var2 + var3;
               }

               var3 = 1;
               var2 = var4;
               var4 = var8;
            }
         }

         Object var11 = var1.payload;
         this.recycleUpdateOp(var1);
         if (var3 > 0) {
            var1 = this.obtainUpdateOp(var1.cmd, var4, var3, var11);
            this.dispatchFirstPassAndUpdateViewHolders(var1, var2);
            this.recycleUpdateOp(var1);
         }

      } else {
         throw new IllegalArgumentException("should not dispatch add or move for pre layout");
      }
   }

   private void postponeAndUpdateViewHolders(UpdateOp var1) {
      this.mPostponedList.add(var1);
      int var2 = var1.cmd;
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 4) {
               if (var2 != 8) {
                  throw new IllegalArgumentException("Unknown update op type for " + var1);
               }

               this.mCallback.offsetPositionsForMove(var1.positionStart, var1.itemCount);
            } else {
               this.mCallback.markViewHoldersUpdated(var1.positionStart, var1.itemCount, var1.payload);
            }
         } else {
            this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(var1.positionStart, var1.itemCount);
         }
      } else {
         this.mCallback.offsetPositionsForAdd(var1.positionStart, var1.itemCount);
      }

   }

   private int updatePositionWithPostponed(int var1, int var2) {
      int var3 = this.mPostponedList.size() - 1;

      int var4;
      UpdateOp var6;
      for(var4 = var1; var3 >= 0; var4 = var1) {
         var6 = (UpdateOp)this.mPostponedList.get(var3);
         if (var6.cmd == 8) {
            int var5;
            if (var6.positionStart < var6.itemCount) {
               var1 = var6.positionStart;
               var5 = var6.itemCount;
            } else {
               var1 = var6.itemCount;
               var5 = var6.positionStart;
            }

            if (var4 >= var1 && var4 <= var5) {
               if (var1 == var6.positionStart) {
                  if (var2 == 1) {
                     ++var6.itemCount;
                  } else if (var2 == 2) {
                     --var6.itemCount;
                  }

                  var1 = var4 + 1;
               } else {
                  if (var2 == 1) {
                     ++var6.positionStart;
                  } else if (var2 == 2) {
                     --var6.positionStart;
                  }

                  var1 = var4 - 1;
               }
            } else {
               var1 = var4;
               if (var4 < var6.positionStart) {
                  if (var2 == 1) {
                     ++var6.positionStart;
                     ++var6.itemCount;
                     var1 = var4;
                  } else {
                     var1 = var4;
                     if (var2 == 2) {
                        --var6.positionStart;
                        --var6.itemCount;
                        var1 = var4;
                     }
                  }
               }
            }
         } else if (var6.positionStart <= var4) {
            if (var6.cmd == 1) {
               var1 = var4 - var6.itemCount;
            } else {
               var1 = var4;
               if (var6.cmd == 2) {
                  var1 = var4 + var6.itemCount;
               }
            }
         } else if (var2 == 1) {
            ++var6.positionStart;
            var1 = var4;
         } else {
            var1 = var4;
            if (var2 == 2) {
               --var6.positionStart;
               var1 = var4;
            }
         }

         --var3;
      }

      for(var1 = this.mPostponedList.size() - 1; var1 >= 0; --var1) {
         var6 = (UpdateOp)this.mPostponedList.get(var1);
         if (var6.cmd == 8) {
            if (var6.itemCount == var6.positionStart || var6.itemCount < 0) {
               this.mPostponedList.remove(var1);
               this.recycleUpdateOp(var6);
            }
         } else if (var6.itemCount <= 0) {
            this.mPostponedList.remove(var1);
            this.recycleUpdateOp(var6);
         }
      }

      return var4;
   }

   AdapterHelper addUpdateOp(UpdateOp... var1) {
      Collections.addAll(this.mPendingUpdates, var1);
      return this;
   }

   public int applyPendingUpdatesToPosition(int var1) {
      int var5 = this.mPendingUpdates.size();
      int var4 = 0;

      int var2;
      for(var2 = var1; var4 < var5; var2 = var1) {
         UpdateOp var6 = (UpdateOp)this.mPendingUpdates.get(var4);
         var1 = var6.cmd;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 8) {
                  var1 = var2;
               } else if (var6.positionStart == var2) {
                  var1 = var6.itemCount;
               } else {
                  int var3 = var2;
                  if (var6.positionStart < var2) {
                     var3 = var2 - 1;
                  }

                  var1 = var3;
                  if (var6.itemCount <= var3) {
                     var1 = var3 + 1;
                  }
               }
            } else {
               var1 = var2;
               if (var6.positionStart <= var2) {
                  if (var6.positionStart + var6.itemCount > var2) {
                     return -1;
                  }

                  var1 = var2 - var6.itemCount;
               }
            }
         } else {
            var1 = var2;
            if (var6.positionStart <= var2) {
               var1 = var2 + var6.itemCount;
            }
         }

         ++var4;
      }

      return var2;
   }

   void consumePostponedUpdates() {
      int var2 = this.mPostponedList.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         this.mCallback.onDispatchSecondPass((UpdateOp)this.mPostponedList.get(var1));
      }

      this.recycleUpdateOpsAndClearList(this.mPostponedList);
      this.mExistingUpdateTypes = 0;
   }

   void consumeUpdatesInOnePass() {
      this.consumePostponedUpdates();
      int var2 = this.mPendingUpdates.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         UpdateOp var4 = (UpdateOp)this.mPendingUpdates.get(var1);
         int var3 = var4.cmd;
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 4) {
                  if (var3 == 8) {
                     this.mCallback.onDispatchSecondPass(var4);
                     this.mCallback.offsetPositionsForMove(var4.positionStart, var4.itemCount);
                  }
               } else {
                  this.mCallback.onDispatchSecondPass(var4);
                  this.mCallback.markViewHoldersUpdated(var4.positionStart, var4.itemCount, var4.payload);
               }
            } else {
               this.mCallback.onDispatchSecondPass(var4);
               this.mCallback.offsetPositionsForRemovingInvisible(var4.positionStart, var4.itemCount);
            }
         } else {
            this.mCallback.onDispatchSecondPass(var4);
            this.mCallback.offsetPositionsForAdd(var4.positionStart, var4.itemCount);
         }

         Runnable var5 = this.mOnItemProcessedCallback;
         if (var5 != null) {
            var5.run();
         }
      }

      this.recycleUpdateOpsAndClearList(this.mPendingUpdates);
      this.mExistingUpdateTypes = 0;
   }

   void dispatchFirstPassAndUpdateViewHolders(UpdateOp var1, int var2) {
      this.mCallback.onDispatchFirstPass(var1);
      int var3 = var1.cmd;
      if (var3 != 2) {
         if (var3 != 4) {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
         }

         this.mCallback.markViewHoldersUpdated(var2, var1.itemCount, var1.payload);
      } else {
         this.mCallback.offsetPositionsForRemovingInvisible(var2, var1.itemCount);
      }

   }

   int findPositionOffset(int var1) {
      return this.findPositionOffset(var1, 0);
   }

   int findPositionOffset(int var1, int var2) {
      int var5 = this.mPostponedList.size();
      int var4 = var2;

      for(var2 = var1; var4 < var5; var2 = var1) {
         UpdateOp var6 = (UpdateOp)this.mPostponedList.get(var4);
         if (var6.cmd == 8) {
            if (var6.positionStart == var2) {
               var1 = var6.itemCount;
            } else {
               int var3 = var2;
               if (var6.positionStart < var2) {
                  var3 = var2 - 1;
               }

               var1 = var3;
               if (var6.itemCount <= var3) {
                  var1 = var3 + 1;
               }
            }
         } else {
            var1 = var2;
            if (var6.positionStart <= var2) {
               if (var6.cmd == 2) {
                  if (var2 < var6.positionStart + var6.itemCount) {
                     return -1;
                  }

                  var1 = var2 - var6.itemCount;
               } else {
                  var1 = var2;
                  if (var6.cmd == 1) {
                     var1 = var2 + var6.itemCount;
                  }
               }
            }
         }

         ++var4;
      }

      return var2;
   }

   boolean hasAnyUpdateTypes(int var1) {
      boolean var2;
      if ((var1 & this.mExistingUpdateTypes) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   boolean hasPendingUpdates() {
      boolean var1;
      if (this.mPendingUpdates.size() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   boolean hasUpdates() {
      boolean var1;
      if (!this.mPostponedList.isEmpty() && !this.mPendingUpdates.isEmpty()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public UpdateOp obtainUpdateOp(int var1, int var2, int var3, Object var4) {
      UpdateOp var5 = (UpdateOp)this.mUpdateOpPool.acquire();
      UpdateOp var6;
      if (var5 == null) {
         var6 = new UpdateOp(var1, var2, var3, var4);
      } else {
         var5.cmd = var1;
         var5.positionStart = var2;
         var5.itemCount = var3;
         var5.payload = var4;
         var6 = var5;
      }

      return var6;
   }

   boolean onItemRangeChanged(int var1, int var2, Object var3) {
      boolean var4 = false;
      if (var2 < 1) {
         return false;
      } else {
         this.mPendingUpdates.add(this.obtainUpdateOp(4, var1, var2, var3));
         this.mExistingUpdateTypes |= 4;
         if (this.mPendingUpdates.size() == 1) {
            var4 = true;
         }

         return var4;
      }
   }

   boolean onItemRangeInserted(int var1, int var2) {
      boolean var3 = false;
      if (var2 < 1) {
         return false;
      } else {
         this.mPendingUpdates.add(this.obtainUpdateOp(1, var1, var2, (Object)null));
         this.mExistingUpdateTypes |= 1;
         if (this.mPendingUpdates.size() == 1) {
            var3 = true;
         }

         return var3;
      }
   }

   boolean onItemRangeMoved(int var1, int var2, int var3) {
      boolean var4 = false;
      if (var1 == var2) {
         return false;
      } else if (var3 == 1) {
         this.mPendingUpdates.add(this.obtainUpdateOp(8, var1, var2, (Object)null));
         this.mExistingUpdateTypes |= 8;
         if (this.mPendingUpdates.size() == 1) {
            var4 = true;
         }

         return var4;
      } else {
         throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
      }
   }

   boolean onItemRangeRemoved(int var1, int var2) {
      boolean var3 = false;
      if (var2 < 1) {
         return false;
      } else {
         this.mPendingUpdates.add(this.obtainUpdateOp(2, var1, var2, (Object)null));
         this.mExistingUpdateTypes |= 2;
         if (this.mPendingUpdates.size() == 1) {
            var3 = true;
         }

         return var3;
      }
   }

   void preProcess() {
      this.mOpReorderer.reorderOps(this.mPendingUpdates);
      int var2 = this.mPendingUpdates.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         UpdateOp var4 = (UpdateOp)this.mPendingUpdates.get(var1);
         int var3 = var4.cmd;
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 4) {
                  if (var3 == 8) {
                     this.applyMove(var4);
                  }
               } else {
                  this.applyUpdate(var4);
               }
            } else {
               this.applyRemove(var4);
            }
         } else {
            this.applyAdd(var4);
         }

         Runnable var5 = this.mOnItemProcessedCallback;
         if (var5 != null) {
            var5.run();
         }
      }

      this.mPendingUpdates.clear();
   }

   public void recycleUpdateOp(UpdateOp var1) {
      if (!this.mDisableRecycler) {
         var1.payload = null;
         this.mUpdateOpPool.release(var1);
      }

   }

   void recycleUpdateOpsAndClearList(List var1) {
      int var3 = var1.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         this.recycleUpdateOp((UpdateOp)var1.get(var2));
      }

      var1.clear();
   }

   void reset() {
      this.recycleUpdateOpsAndClearList(this.mPendingUpdates);
      this.recycleUpdateOpsAndClearList(this.mPostponedList);
      this.mExistingUpdateTypes = 0;
   }

   interface Callback {
      RecyclerView.ViewHolder findViewHolder(int var1);

      void markViewHoldersUpdated(int var1, int var2, Object var3);

      void offsetPositionsForAdd(int var1, int var2);

      void offsetPositionsForMove(int var1, int var2);

      void offsetPositionsForRemovingInvisible(int var1, int var2);

      void offsetPositionsForRemovingLaidOutOrNewView(int var1, int var2);

      void onDispatchFirstPass(UpdateOp var1);

      void onDispatchSecondPass(UpdateOp var1);
   }

   static final class UpdateOp {
      static final int ADD = 1;
      static final int MOVE = 8;
      static final int POOL_SIZE = 30;
      static final int REMOVE = 2;
      static final int UPDATE = 4;
      int cmd;
      int itemCount;
      Object payload;
      int positionStart;

      UpdateOp(int var1, int var2, int var3, Object var4) {
         this.cmd = var1;
         this.positionStart = var2;
         this.itemCount = var3;
         this.payload = var4;
      }

      String cmdToString() {
         int var1 = this.cmd;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 4) {
                  return var1 != 8 ? "??" : "mv";
               } else {
                  return "up";
               }
            } else {
               return "rm";
            }
         } else {
            return "add";
         }
      }

      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof UpdateOp)) {
            return false;
         } else {
            UpdateOp var3 = (UpdateOp)var1;
            int var2 = this.cmd;
            if (var2 != var3.cmd) {
               return false;
            } else if (var2 == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == var3.positionStart && this.positionStart == var3.itemCount) {
               return true;
            } else if (this.itemCount != var3.itemCount) {
               return false;
            } else if (this.positionStart != var3.positionStart) {
               return false;
            } else {
               var1 = this.payload;
               if (var1 != null) {
                  if (!var1.equals(var3.payload)) {
                     return false;
                  }
               } else if (var3.payload != null) {
                  return false;
               }

               return true;
            }
         }
      }

      public int hashCode() {
         return (this.cmd * 31 + this.positionStart) * 31 + this.itemCount;
      }

      public String toString() {
         return Integer.toHexString(System.identityHashCode(this)) + "[" + this.cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
      }
   }
}
