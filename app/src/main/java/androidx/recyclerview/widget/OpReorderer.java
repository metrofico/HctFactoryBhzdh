package androidx.recyclerview.widget;

import java.util.List;

class OpReorderer {
   final Callback mCallback;

   OpReorderer(Callback var1) {
      this.mCallback = var1;
   }

   private int getLastMoveOutOfOrder(List var1) {
      int var3 = var1.size() - 1;

      boolean var2;
      for(boolean var4 = false; var3 >= 0; var4 = var2) {
         if (((AdapterHelper.UpdateOp)var1.get(var3)).cmd == 8) {
            var2 = var4;
            if (var4) {
               return var3;
            }
         } else {
            var2 = true;
         }

         --var3;
      }

      return -1;
   }

   private void swapMoveAdd(List var1, int var2, AdapterHelper.UpdateOp var3, int var4, AdapterHelper.UpdateOp var5) {
      byte var6;
      if (var3.itemCount < var5.positionStart) {
         var6 = -1;
      } else {
         var6 = 0;
      }

      int var7 = var6;
      if (var3.positionStart < var5.positionStart) {
         var7 = var6 + 1;
      }

      if (var5.positionStart <= var3.positionStart) {
         var3.positionStart += var5.itemCount;
      }

      if (var5.positionStart <= var3.itemCount) {
         var3.itemCount += var5.itemCount;
      }

      var5.positionStart += var7;
      var1.set(var2, var5);
      var1.set(var4, var3);
   }

   private void swapMoveOp(List var1, int var2, int var3) {
      AdapterHelper.UpdateOp var6 = (AdapterHelper.UpdateOp)var1.get(var2);
      AdapterHelper.UpdateOp var5 = (AdapterHelper.UpdateOp)var1.get(var3);
      int var4 = var5.cmd;
      if (var4 != 1) {
         if (var4 != 2) {
            if (var4 == 4) {
               this.swapMoveUpdate(var1, var2, var6, var3, var5);
            }
         } else {
            this.swapMoveRemove(var1, var2, var6, var3, var5);
         }
      } else {
         this.swapMoveAdd(var1, var2, var6, var3, var5);
      }

   }

   void reorderOps(List var1) {
      while(true) {
         int var2 = this.getLastMoveOutOfOrder(var1);
         if (var2 == -1) {
            return;
         }

         this.swapMoveOp(var1, var2, var2 + 1);
      }
   }

   void swapMoveRemove(List var1, int var2, AdapterHelper.UpdateOp var3, int var4, AdapterHelper.UpdateOp var5) {
      int var8 = var3.positionStart;
      int var6 = var3.itemCount;
      boolean var7 = false;
      boolean var12;
      if (var8 < var6) {
         if (var5.positionStart == var3.positionStart && var5.itemCount == var3.itemCount - var3.positionStart) {
            var12 = false;
            var7 = true;
         } else {
            var12 = false;
         }
      } else if (var5.positionStart == var3.itemCount + 1 && var5.itemCount == var3.positionStart - var3.itemCount) {
         var12 = true;
         var7 = true;
      } else {
         var12 = true;
      }

      if (var3.itemCount < var5.positionStart) {
         --var5.positionStart;
      } else if (var3.itemCount < var5.positionStart + var5.itemCount) {
         --var5.itemCount;
         var3.cmd = 2;
         var3.itemCount = 1;
         if (var5.itemCount == 0) {
            var1.remove(var4);
            this.mCallback.recycleUpdateOp(var5);
         }

         return;
      }

      var8 = var3.positionStart;
      int var9 = var5.positionStart;
      AdapterHelper.UpdateOp var11 = null;
      if (var8 <= var9) {
         ++var5.positionStart;
      } else if (var3.positionStart < var5.positionStart + var5.itemCount) {
         var9 = var5.positionStart;
         var8 = var5.itemCount;
         int var10 = var3.positionStart;
         var11 = this.mCallback.obtainUpdateOp(2, var3.positionStart + 1, var9 + var8 - var10, (Object)null);
         var5.itemCount = var3.positionStart - var5.positionStart;
      }

      if (var7) {
         var1.set(var2, var5);
         var1.remove(var4);
         this.mCallback.recycleUpdateOp(var3);
      } else {
         if (var12) {
            if (var11 != null) {
               if (var3.positionStart > var11.positionStart) {
                  var3.positionStart -= var11.itemCount;
               }

               if (var3.itemCount > var11.positionStart) {
                  var3.itemCount -= var11.itemCount;
               }
            }

            if (var3.positionStart > var5.positionStart) {
               var3.positionStart -= var5.itemCount;
            }

            if (var3.itemCount > var5.positionStart) {
               var3.itemCount -= var5.itemCount;
            }
         } else {
            if (var11 != null) {
               if (var3.positionStart >= var11.positionStart) {
                  var3.positionStart -= var11.itemCount;
               }

               if (var3.itemCount >= var11.positionStart) {
                  var3.itemCount -= var11.itemCount;
               }
            }

            if (var3.positionStart >= var5.positionStart) {
               var3.positionStart -= var5.itemCount;
            }

            if (var3.itemCount >= var5.positionStart) {
               var3.itemCount -= var5.itemCount;
            }
         }

         var1.set(var2, var5);
         if (var3.positionStart != var3.itemCount) {
            var1.set(var4, var3);
         } else {
            var1.remove(var4);
         }

         if (var11 != null) {
            var1.add(var2, var11);
         }

      }
   }

   void swapMoveUpdate(List var1, int var2, AdapterHelper.UpdateOp var3, int var4, AdapterHelper.UpdateOp var5) {
      int var6;
      AdapterHelper.UpdateOp var8;
      AdapterHelper.UpdateOp var9;
      label33: {
         var6 = var3.itemCount;
         int var7 = var5.positionStart;
         var9 = null;
         if (var6 < var7) {
            --var5.positionStart;
         } else if (var3.itemCount < var5.positionStart + var5.itemCount) {
            --var5.itemCount;
            var8 = this.mCallback.obtainUpdateOp(4, var3.positionStart, 1, var5.payload);
            break label33;
         }

         var8 = null;
      }

      if (var3.positionStart <= var5.positionStart) {
         ++var5.positionStart;
      } else if (var3.positionStart < var5.positionStart + var5.itemCount) {
         var6 = var5.positionStart + var5.itemCount - var3.positionStart;
         var9 = this.mCallback.obtainUpdateOp(4, var3.positionStart + 1, var6, var5.payload);
         var5.itemCount -= var6;
      }

      var1.set(var4, var3);
      if (var5.itemCount > 0) {
         var1.set(var2, var5);
      } else {
         var1.remove(var2);
         this.mCallback.recycleUpdateOp(var5);
      }

      if (var8 != null) {
         var1.add(var2, var8);
      }

      if (var9 != null) {
         var1.add(var2, var9);
      }

   }

   interface Callback {
      AdapterHelper.UpdateOp obtainUpdateOp(int var1, int var2, int var3, Object var4);

      void recycleUpdateOp(AdapterHelper.UpdateOp var1);
   }
}
