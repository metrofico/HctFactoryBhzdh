package com.android.common.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class GroupingListAdapter extends BaseAdapter {
   private static final long EXPANDED_GROUP_MASK = Long.MIN_VALUE;
   private static final int GROUP_METADATA_ARRAY_INCREMENT = 128;
   private static final int GROUP_METADATA_ARRAY_INITIAL_SIZE = 16;
   private static final long GROUP_OFFSET_MASK = 4294967295L;
   private static final long GROUP_SIZE_MASK = 9223372032559808512L;
   public static final int ITEM_TYPE_GROUP_HEADER = 1;
   public static final int ITEM_TYPE_IN_GROUP = 2;
   public static final int ITEM_TYPE_STANDALONE = 0;
   protected ContentObserver mChangeObserver = new ContentObserver(this, new Handler()) {
      final GroupingListAdapter this$0;

      {
         this.this$0 = var1;
      }

      public boolean deliverSelfNotifications() {
         return true;
      }

      public void onChange(boolean var1) {
         this.this$0.onContentChanged();
      }
   };
   private Context mContext;
   private int mCount;
   private Cursor mCursor;
   protected DataSetObserver mDataSetObserver = new DataSetObserver(this) {
      final GroupingListAdapter this$0;

      {
         this.this$0 = var1;
      }

      public void onChanged() {
         this.this$0.notifyDataSetChanged();
      }

      public void onInvalidated() {
         this.this$0.notifyDataSetInvalidated();
      }
   };
   private int mGroupCount;
   private long[] mGroupMetadata;
   private int mLastCachedCursorPosition;
   private int mLastCachedGroup;
   private int mLastCachedListPosition;
   private SparseIntArray mPositionCache = new SparseIntArray();
   private PositionMetadata mPositionMetadata = new PositionMetadata();
   private int mRowIdColumnIndex;

   public GroupingListAdapter(Context var1) {
      this.mContext = var1;
      this.resetCache();
   }

   private void findGroups() {
      this.mGroupCount = 0;
      this.mGroupMetadata = new long[16];
      Cursor var1 = this.mCursor;
      if (var1 != null) {
         this.addGroups(var1);
      }
   }

   private int idealByteArraySize(int var1) {
      for(int var2 = 4; var2 < 32; ++var2) {
         if (var1 <= (1 << var2) - 12) {
            return (1 << var2) - 12;
         }
      }

      return var1;
   }

   private int idealLongArraySize(int var1) {
      return this.idealByteArraySize(var1 * 8) / 8;
   }

   private void resetCache() {
      this.mCount = -1;
      this.mLastCachedListPosition = -1;
      this.mLastCachedCursorPosition = -1;
      this.mLastCachedGroup = -1;
      this.mPositionMetadata.listPosition = -1;
      this.mPositionCache.clear();
   }

   protected void addGroup(int var1, int var2, boolean var3) {
      int var4 = this.mGroupCount;
      long[] var9 = this.mGroupMetadata;
      if (var4 >= var9.length) {
         var9 = new long[this.idealLongArraySize(var9.length + 128)];
         System.arraycopy(this.mGroupMetadata, 0, var9, 0, this.mGroupCount);
         this.mGroupMetadata = var9;
      }

      long var7 = (long)var2 << 32 | (long)var1;
      long var5 = var7;
      if (var3) {
         var5 = var7 | Long.MIN_VALUE;
      }

      var9 = this.mGroupMetadata;
      var1 = this.mGroupCount++;
      var9[var1] = var5;
   }

   protected abstract void addGroups(Cursor var1);

   protected abstract void bindChildView(View var1, Context var2, Cursor var3);

   protected abstract void bindGroupView(View var1, Context var2, Cursor var3, int var4, boolean var5);

   protected abstract void bindStandAloneView(View var1, Context var2, Cursor var3);

   public void changeCursor(Cursor var1) {
      Cursor var2 = this.mCursor;
      if (var1 != var2) {
         if (var2 != null) {
            var2.unregisterContentObserver(this.mChangeObserver);
            this.mCursor.unregisterDataSetObserver(this.mDataSetObserver);
            this.mCursor.close();
         }

         this.mCursor = var1;
         this.resetCache();
         this.findGroups();
         if (var1 != null) {
            var1.registerContentObserver(this.mChangeObserver);
            var1.registerDataSetObserver(this.mDataSetObserver);
            this.mRowIdColumnIndex = var1.getColumnIndexOrThrow("_id");
            this.notifyDataSetChanged();
         } else {
            this.notifyDataSetInvalidated();
         }

      }
   }

   public int getCount() {
      if (this.mCursor == null) {
         return 0;
      } else {
         int var1 = this.mCount;
         if (var1 != -1) {
            return var1;
         } else {
            int var3 = 0;
            var1 = 0;

            for(int var2 = 0; var2 < this.mGroupCount; ++var2) {
               long var7 = this.mGroupMetadata[var2];
               int var5 = (int)(4294967295L & var7);
               boolean var4;
               if ((Long.MIN_VALUE & var7) != 0L) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               int var6 = (int)((9223372032559808512L & var7) >> 32);
               var1 += var5 - var3;
               if (var4) {
                  var1 += var6 + 1;
               } else {
                  ++var1;
               }

               var3 = var5 + var6;
            }

            this.mCount = this.mCursor.getCount() + var1 - var3;
            return this.mCount;
         }
      }
   }

   public Cursor getCursor() {
      return this.mCursor;
   }

   public int getGroupSize(int var1) {
      this.obtainPositionMetadata(this.mPositionMetadata, var1);
      return this.mPositionMetadata.childCount;
   }

   public Object getItem(int var1) {
      if (this.mCursor == null) {
         return null;
      } else {
         this.obtainPositionMetadata(this.mPositionMetadata, var1);
         return this.mCursor.moveToPosition(this.mPositionMetadata.cursorPosition) ? this.mCursor : null;
      }
   }

   public long getItemId(int var1) {
      return this.getItem(var1) != null ? this.mCursor.getLong(this.mRowIdColumnIndex) : -1L;
   }

   public int getItemViewType(int var1) {
      this.obtainPositionMetadata(this.mPositionMetadata, var1);
      return this.mPositionMetadata.itemType;
   }

   public View getView(int var1, View var2, ViewGroup var3) {
      this.obtainPositionMetadata(this.mPositionMetadata, var1);
      View var4 = var2;
      var2 = var2;
      if (var4 == null) {
         var1 = this.mPositionMetadata.itemType;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  var2 = var4;
               } else {
                  var2 = this.newChildView(this.mContext, var3);
               }
            } else {
               var2 = this.newGroupView(this.mContext, var3);
            }
         } else {
            var2 = this.newStandAloneView(this.mContext, var3);
         }
      }

      this.mCursor.moveToPosition(this.mPositionMetadata.cursorPosition);
      var1 = this.mPositionMetadata.itemType;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 == 2) {
               this.bindChildView(var2, this.mContext, this.mCursor);
            }
         } else {
            this.bindGroupView(var2, this.mContext, this.mCursor, this.mPositionMetadata.childCount, this.mPositionMetadata.isExpanded);
         }
      } else {
         this.bindStandAloneView(var2, this.mContext, this.mCursor);
      }

      return var2;
   }

   public int getViewTypeCount() {
      return 3;
   }

   public boolean isGroupHeader(int var1) {
      this.obtainPositionMetadata(this.mPositionMetadata, var1);
      var1 = this.mPositionMetadata.itemType;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   protected abstract View newChildView(Context var1, ViewGroup var2);

   protected abstract View newGroupView(Context var1, ViewGroup var2);

   protected abstract View newStandAloneView(Context var1, ViewGroup var2);

   public void obtainPositionMetadata(PositionMetadata var1, int var2) {
      if (var1.listPosition != var2) {
         int var3 = 0;
         int var6 = 0;
         int var4 = 0;
         byte var8 = 0;
         int var5 = 0;
         byte var7 = 0;
         int var9 = this.mLastCachedListPosition;
         if (var9 != -1) {
            if (var2 <= var9) {
               var4 = this.mPositionCache.indexOfKey(var2);
               var3 = var4;
               if (var4 < 0) {
                  var4 = ~var4 - 1;
                  var3 = var4;
                  if (var4 >= this.mPositionCache.size()) {
                     var3 = var4 - 1;
                  }
               }

               var4 = var8;
               var5 = var7;
               if (var3 >= 0) {
                  var6 = this.mPositionCache.keyAt(var3);
                  var5 = this.mPositionCache.valueAt(var3);
                  var4 = (int)(this.mGroupMetadata[var5] & 4294967295L);
               }

               var3 = var6;
            } else {
               var5 = this.mLastCachedGroup;
               var3 = this.mLastCachedListPosition;
               var4 = this.mLastCachedCursorPosition;
            }
         }

         while(true) {
            var6 = this.mGroupCount;
            boolean var10 = false;
            if (var5 >= var6) {
               var1.itemType = 0;
               var1.cursorPosition = var2 - var3 + var4;
               return;
            }

            long var11 = this.mGroupMetadata[var5];
            var6 = (int)(var11 & 4294967295L);
            var3 += var6 - var4;
            if (var5 > this.mLastCachedGroup) {
               this.mPositionCache.append(var3, var5);
               this.mLastCachedListPosition = var3;
               this.mLastCachedCursorPosition = var6;
               this.mLastCachedGroup = var5;
            }

            if (var2 < var3) {
               var1.itemType = 0;
               var1.cursorPosition = var6 - (var3 - var2);
               return;
            }

            if ((Long.MIN_VALUE & var11) != 0L) {
               var10 = true;
            }

            var4 = (int)((9223372032559808512L & var11) >> 32);
            if (var2 == var3) {
               var1.itemType = 1;
               var1.groupPosition = var5;
               var1.isExpanded = var10;
               var1.childCount = var4;
               var1.cursorPosition = var6;
               return;
            }

            if (var10) {
               if (var2 < var3 + var4 + 1) {
                  var1.itemType = 2;
                  var1.cursorPosition = var2 - var3 + var6 - 1;
                  return;
               }

               var3 += var4 + 1;
            } else {
               ++var3;
            }

            var4 += var6;
            ++var5;
         }
      }
   }

   protected void onContentChanged() {
   }

   public void toggleGroup(int var1) {
      this.obtainPositionMetadata(this.mPositionMetadata, var1);
      if (this.mPositionMetadata.itemType == 1) {
         long[] var3;
         if (this.mPositionMetadata.isExpanded) {
            var3 = this.mGroupMetadata;
            var1 = this.mPositionMetadata.groupPosition;
            var3[var1] &= Long.MAX_VALUE;
         } else {
            var3 = this.mGroupMetadata;
            var1 = this.mPositionMetadata.groupPosition;
            var3[var1] |= Long.MIN_VALUE;
         }

         this.resetCache();
         this.notifyDataSetChanged();
      } else {
         StringBuilder var2 = new StringBuilder();
         var2.append("Not a group at position ");
         var2.append(var1);
         throw new IllegalArgumentException(var2.toString());
      }
   }

   protected static class PositionMetadata {
      int childCount;
      int cursorPosition;
      private int groupPosition;
      boolean isExpanded;
      int itemType;
      private int listPosition = -1;
   }
}
