package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.lang.reflect.Array;

class TileList {
   Tile mLastAccessedTile;
   final int mTileSize;
   private final SparseArray mTiles = new SparseArray(10);

   public TileList(int var1) {
      this.mTileSize = var1;
   }

   public Tile addOrReplace(Tile var1) {
      int var2 = this.mTiles.indexOfKey(var1.mStartPosition);
      if (var2 < 0) {
         this.mTiles.put(var1.mStartPosition, var1);
         return null;
      } else {
         Tile var3 = (Tile)this.mTiles.valueAt(var2);
         this.mTiles.setValueAt(var2, var1);
         if (this.mLastAccessedTile == var3) {
            this.mLastAccessedTile = var1;
         }

         return var3;
      }
   }

   public void clear() {
      this.mTiles.clear();
   }

   public Tile getAtIndex(int var1) {
      return var1 >= 0 && var1 < this.mTiles.size() ? (Tile)this.mTiles.valueAt(var1) : null;
   }

   public Object getItemAt(int var1) {
      Tile var3 = this.mLastAccessedTile;
      if (var3 == null || !var3.containsPosition(var1)) {
         int var2 = this.mTileSize;
         var2 = this.mTiles.indexOfKey(var1 - var1 % var2);
         if (var2 < 0) {
            return null;
         }

         this.mLastAccessedTile = (Tile)this.mTiles.valueAt(var2);
      }

      return this.mLastAccessedTile.getByPosition(var1);
   }

   public Tile removeAtPos(int var1) {
      Tile var2 = (Tile)this.mTiles.get(var1);
      if (this.mLastAccessedTile == var2) {
         this.mLastAccessedTile = null;
      }

      this.mTiles.delete(var1);
      return var2;
   }

   public int size() {
      return this.mTiles.size();
   }

   public static class Tile {
      public int mItemCount;
      public final Object[] mItems;
      Tile mNext;
      public int mStartPosition;

      public Tile(Class var1, int var2) {
         this.mItems = (Object[])Array.newInstance(var1, var2);
      }

      boolean containsPosition(int var1) {
         int var2 = this.mStartPosition;
         boolean var3;
         if (var2 <= var1 && var1 < var2 + this.mItemCount) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      Object getByPosition(int var1) {
         return this.mItems[var1 - this.mStartPosition];
      }
   }
}
