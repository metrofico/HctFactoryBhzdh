package com.android.common.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class CompositeCursorAdapter extends BaseAdapter {
   private static final int INITIAL_CAPACITY = 2;
   private boolean mCacheValid;
   private final Context mContext;
   private int mCount;
   private boolean mNotificationNeeded;
   private boolean mNotificationsEnabled;
   private ArrayList mPartitions;

   public CompositeCursorAdapter(Context var1) {
      this(var1, 2);
   }

   public CompositeCursorAdapter(Context var1, int var2) {
      this.mCount = 0;
      this.mCacheValid = true;
      this.mNotificationsEnabled = true;
      this.mContext = var1;
      this.mPartitions = new ArrayList();
   }

   public void addPartition(int var1, Partition var2) {
      this.mPartitions.add(var1, var2);
      this.invalidate();
      this.notifyDataSetChanged();
   }

   public void addPartition(Partition var1) {
      this.mPartitions.add(var1);
      this.invalidate();
      this.notifyDataSetChanged();
   }

   public void addPartition(boolean var1, boolean var2) {
      this.addPartition(new Partition(var1, var2));
   }

   public boolean areAllItemsEnabled() {
      Iterator var1 = this.mPartitions.iterator();

      do {
         if (!var1.hasNext()) {
            return true;
         }
      } while(!((Partition)var1.next()).hasHeader);

      return false;
   }

   protected void bindHeaderView(View var1, int var2, Cursor var3) {
   }

   protected abstract void bindView(View var1, int var2, Cursor var3, int var4);

   public void changeCursor(int var1, Cursor var2) {
      Cursor var3 = ((Partition)this.mPartitions.get(var1)).cursor;
      if (var3 != var2) {
         if (var3 != null && !var3.isClosed()) {
            var3.close();
         }

         ((Partition)this.mPartitions.get(var1)).cursor = var2;
         if (var2 != null && !var2.isClosed()) {
            ((Partition)this.mPartitions.get(var1)).idColumnIndex = var2.getColumnIndex("_id");
         }

         this.invalidate();
         this.notifyDataSetChanged();
      }

   }

   public void clearPartitions() {
      for(Iterator var1 = this.mPartitions.iterator(); var1.hasNext(); ((Partition)var1.next()).cursor = null) {
      }

      this.invalidate();
      this.notifyDataSetChanged();
   }

   public void close() {
      Iterator var2 = this.mPartitions.iterator();

      while(var2.hasNext()) {
         Cursor var1 = ((Partition)var2.next()).cursor;
         if (var1 != null && !var1.isClosed()) {
            var1.close();
         }
      }

      this.mPartitions.clear();
      this.invalidate();
      this.notifyDataSetChanged();
   }

   protected void ensureCacheValid() {
      if (!this.mCacheValid) {
         this.mCount = 0;

         int var2;
         for(Iterator var3 = this.mPartitions.iterator(); var3.hasNext(); this.mCount += var2) {
            Partition var5 = (Partition)var3.next();
            Cursor var4 = var5.cursor;
            int var1;
            if (var4 != null && !var4.isClosed()) {
               var1 = var4.getCount();
            } else {
               var1 = 0;
            }

            var2 = var1;
            if (var5.hasHeader) {
               label41: {
                  if (var1 == 0) {
                     var2 = var1;
                     if (!var5.showIfEmpty) {
                        break label41;
                     }
                  }

                  var2 = var1 + 1;
               }
            }

            var5.count = var2;
         }

         this.mCacheValid = true;
      }
   }

   public Context getContext() {
      return this.mContext;
   }

   public int getCount() {
      this.ensureCacheValid();
      return this.mCount;
   }

   public Cursor getCursor(int var1) {
      return ((Partition)this.mPartitions.get(var1)).cursor;
   }

   protected View getHeaderView(int var1, Cursor var2, View var3, ViewGroup var4) {
      if (var3 == null) {
         var3 = this.newHeaderView(this.mContext, var1, var2, var4);
      }

      this.bindHeaderView(var3, var1, var2);
      return var3;
   }

   public Object getItem(int var1) {
      this.ensureCacheValid();
      int var2 = 0;

      int var3;
      for(Iterator var5 = this.mPartitions.iterator(); var5.hasNext(); var2 = var3) {
         Partition var4 = (Partition)var5.next();
         var3 = var4.count + var2;
         if (var1 >= var2 && var1 < var3) {
            var2 = var1 - var2;
            var1 = var2;
            if (var4.hasHeader) {
               var1 = var2 - 1;
            }

            if (var1 == -1) {
               return null;
            }

            Cursor var6 = var4.cursor;
            if (var6 != null && !var6.isClosed() && var6.moveToPosition(var1)) {
               return var6;
            }

            return null;
         }
      }

      return null;
   }

   public long getItemId(int var1) {
      this.ensureCacheValid();
      int var2 = 0;

      int var3;
      for(Iterator var5 = this.mPartitions.iterator(); var5.hasNext(); var2 = var3) {
         Partition var4 = (Partition)var5.next();
         var3 = var4.count + var2;
         if (var1 >= var2 && var1 < var3) {
            var2 = var1 - var2;
            var1 = var2;
            if (var4.hasHeader) {
               var1 = var2 - 1;
            }

            if (var1 == -1) {
               return 0L;
            }

            if (var4.idColumnIndex == -1) {
               return 0L;
            }

            Cursor var6 = var4.cursor;
            if (var6 != null && !var6.isClosed() && var6.moveToPosition(var1)) {
               return var6.getLong(var4.idColumnIndex);
            }

            return 0L;
         }
      }

      return 0L;
   }

   public int getItemViewType(int var1) {
      this.ensureCacheValid();
      int var3 = 0;
      int var2 = 0;

      for(int var5 = this.mPartitions.size(); var2 < var5; ++var2) {
         int var4 = ((Partition)this.mPartitions.get(var2)).count + var3;
         if (var1 >= var3 && var1 < var4) {
            var3 = var1 - var3;
            var1 = var3;
            if (((Partition)this.mPartitions.get(var2)).hasHeader) {
               var1 = var3 - 1;
            }

            if (var1 == -1) {
               return -1;
            }

            return this.getItemViewType(var2, var1);
         }

         var3 = var4;
      }

      throw new ArrayIndexOutOfBoundsException(var1);
   }

   protected int getItemViewType(int var1, int var2) {
      return 1;
   }

   public int getItemViewTypeCount() {
      return 1;
   }

   public int getOffsetInPartition(int var1) {
      this.ensureCacheValid();
      int var2 = 0;

      int var3;
      for(Iterator var4 = this.mPartitions.iterator(); var4.hasNext(); var2 = var3) {
         Partition var5 = (Partition)var4.next();
         var3 = var5.count + var2;
         if (var1 >= var2 && var1 < var3) {
            var2 = var1 - var2;
            var1 = var2;
            if (var5.hasHeader) {
               var1 = var2 - 1;
            }

            return var1;
         }
      }

      return -1;
   }

   public Partition getPartition(int var1) {
      return (Partition)this.mPartitions.get(var1);
   }

   public int getPartitionCount() {
      return this.mPartitions.size();
   }

   public int getPartitionForPosition(int var1) {
      this.ensureCacheValid();
      int var3 = 0;
      int var2 = 0;

      for(int var5 = this.mPartitions.size(); var2 < var5; ++var2) {
         int var4 = ((Partition)this.mPartitions.get(var2)).count + var3;
         if (var1 >= var3 && var1 < var4) {
            return var2;
         }

         var3 = var4;
      }

      return -1;
   }

   public int getPositionForPartition(int var1) {
      this.ensureCacheValid();
      int var3 = 0;

      for(int var2 = 0; var2 < var1; ++var2) {
         var3 += ((Partition)this.mPartitions.get(var2)).count;
      }

      return var3;
   }

   protected View getView(int var1, Cursor var2, int var3, View var4, ViewGroup var5) {
      if (var4 == null) {
         var4 = this.newView(this.mContext, var1, var2, var3, var5);
      }

      this.bindView(var4, var1, var2, var3);
      return var4;
   }

   public View getView(int var1, View var2, ViewGroup var3) {
      this.ensureCacheValid();
      int var5 = 0;
      int var4 = 0;

      for(int var7 = this.mPartitions.size(); var4 < var7; ++var4) {
         int var6 = var5 + ((Partition)this.mPartitions.get(var4)).count;
         if (var1 >= var5 && var1 < var6) {
            var1 -= var5;
            if (((Partition)this.mPartitions.get(var4)).hasHeader) {
               --var1;
            }

            StringBuilder var8;
            if (var1 == -1) {
               var2 = this.getHeaderView(var4, ((Partition)this.mPartitions.get(var4)).cursor, var2, var3);
            } else {
               if (!((Partition)this.mPartitions.get(var4)).cursor.moveToPosition(var1)) {
                  var8 = new StringBuilder();
                  var8.append("Couldn't move cursor to position ");
                  var8.append(var1);
                  throw new IllegalStateException(var8.toString());
               }

               var2 = this.getView(var4, ((Partition)this.mPartitions.get(var4)).cursor, var1, var2, var3);
            }

            if (var2 != null) {
               return var2;
            }

            var8 = new StringBuilder();
            var8.append("View should not be null, partition: ");
            var8.append(var4);
            var8.append(" position: ");
            var8.append(var1);
            throw new NullPointerException(var8.toString());
         }

         var5 = var6;
      }

      throw new ArrayIndexOutOfBoundsException(var1);
   }

   public int getViewTypeCount() {
      return this.getItemViewTypeCount() + 1;
   }

   public boolean hasHeader(int var1) {
      return ((Partition)this.mPartitions.get(var1)).hasHeader;
   }

   protected void invalidate() {
      this.mCacheValid = false;
   }

   public boolean isEnabled(int var1) {
      this.ensureCacheValid();
      int var3 = 0;
      int var2 = 0;

      for(int var5 = this.mPartitions.size(); var2 < var5; ++var2) {
         int var4 = ((Partition)this.mPartitions.get(var2)).count + var3;
         if (var1 >= var3 && var1 < var4) {
            var1 -= var3;
            if (((Partition)this.mPartitions.get(var2)).hasHeader && var1 == 0) {
               return false;
            }

            return this.isEnabled(var2, var1);
         }

         var3 = var4;
      }

      return false;
   }

   protected boolean isEnabled(int var1, int var2) {
      return true;
   }

   public boolean isPartitionEmpty(int var1) {
      Cursor var3 = ((Partition)this.mPartitions.get(var1)).cursor;
      boolean var2;
      if (var3 != null && !var3.isClosed() && var3.getCount() != 0) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   protected View newHeaderView(Context var1, int var2, Cursor var3, ViewGroup var4) {
      return null;
   }

   protected abstract View newView(Context var1, int var2, Cursor var3, int var4, ViewGroup var5);

   public void notifyDataSetChanged() {
      if (this.mNotificationsEnabled) {
         this.mNotificationNeeded = false;
         super.notifyDataSetChanged();
      } else {
         this.mNotificationNeeded = true;
      }

   }

   public void removePartition(int var1) {
      Cursor var2 = ((Partition)this.mPartitions.get(var1)).cursor;
      if (var2 != null && !var2.isClosed()) {
         var2.close();
      }

      this.mPartitions.remove(var1);
      this.invalidate();
      this.notifyDataSetChanged();
   }

   public void setHasHeader(int var1, boolean var2) {
      ((Partition)this.mPartitions.get(var1)).hasHeader = var2;
      this.invalidate();
   }

   public void setNotificationsEnabled(boolean var1) {
      this.mNotificationsEnabled = var1;
      if (var1 && this.mNotificationNeeded) {
         this.notifyDataSetChanged();
      }

   }

   public void setShowIfEmpty(int var1, boolean var2) {
      ((Partition)this.mPartitions.get(var1)).showIfEmpty = var2;
      this.invalidate();
   }

   public static class Partition {
      int count;
      Cursor cursor;
      boolean hasHeader;
      int idColumnIndex;
      boolean showIfEmpty;

      public Partition(boolean var1, boolean var2) {
         this.showIfEmpty = var1;
         this.hasHeader = var2;
      }

      public boolean getHasHeader() {
         return this.hasHeader;
      }

      public boolean getShowIfEmpty() {
         return this.showIfEmpty;
      }

      public boolean isEmpty() {
         boolean var1;
         if (this.count == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }
   }
}
