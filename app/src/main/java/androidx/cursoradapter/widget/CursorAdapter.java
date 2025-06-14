package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

public abstract class CursorAdapter extends BaseAdapter implements Filterable, CursorFilter.CursorFilterClient {
   @Deprecated
   public static final int FLAG_AUTO_REQUERY = 1;
   public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
   protected boolean mAutoRequery;
   protected ChangeObserver mChangeObserver;
   protected Context mContext;
   protected Cursor mCursor;
   protected CursorFilter mCursorFilter;
   protected DataSetObserver mDataSetObserver;
   protected boolean mDataValid;
   protected FilterQueryProvider mFilterQueryProvider;
   protected int mRowIDColumn;

   @Deprecated
   public CursorAdapter(Context var1, Cursor var2) {
      this.init(var1, var2, 1);
   }

   public CursorAdapter(Context var1, Cursor var2, int var3) {
      this.init(var1, var2, var3);
   }

   public CursorAdapter(Context var1, Cursor var2, boolean var3) {
      byte var4;
      if (var3) {
         var4 = 1;
      } else {
         var4 = 2;
      }

      this.init(var1, var2, var4);
   }

   public abstract void bindView(View var1, Context var2, Cursor var3);

   public void changeCursor(Cursor var1) {
      var1 = this.swapCursor(var1);
      if (var1 != null) {
         var1.close();
      }

   }

   public CharSequence convertToString(Cursor var1) {
      String var2;
      if (var1 == null) {
         var2 = "";
      } else {
         var2 = var1.toString();
      }

      return var2;
   }

   public int getCount() {
      if (this.mDataValid) {
         Cursor var1 = this.mCursor;
         if (var1 != null) {
            return var1.getCount();
         }
      }

      return 0;
   }

   public Cursor getCursor() {
      return this.mCursor;
   }

   public View getDropDownView(int var1, View var2, ViewGroup var3) {
      if (this.mDataValid) {
         this.mCursor.moveToPosition(var1);
         View var4 = var2;
         if (var2 == null) {
            var4 = this.newDropDownView(this.mContext, this.mCursor, var3);
         }

         this.bindView(var4, this.mContext, this.mCursor);
         return var4;
      } else {
         return null;
      }
   }

   public Filter getFilter() {
      if (this.mCursorFilter == null) {
         this.mCursorFilter = new CursorFilter(this);
      }

      return this.mCursorFilter;
   }

   public FilterQueryProvider getFilterQueryProvider() {
      return this.mFilterQueryProvider;
   }

   public Object getItem(int var1) {
      if (this.mDataValid) {
         Cursor var2 = this.mCursor;
         if (var2 != null) {
            var2.moveToPosition(var1);
            return this.mCursor;
         }
      }

      return null;
   }

   public long getItemId(int var1) {
      if (this.mDataValid) {
         Cursor var2 = this.mCursor;
         if (var2 != null && var2.moveToPosition(var1)) {
            return this.mCursor.getLong(this.mRowIDColumn);
         }
      }

      return 0L;
   }

   public View getView(int var1, View var2, ViewGroup var3) {
      if (this.mDataValid) {
         if (this.mCursor.moveToPosition(var1)) {
            View var4 = var2;
            if (var2 == null) {
               var4 = this.newView(this.mContext, this.mCursor, var3);
            }

            this.bindView(var4, this.mContext, this.mCursor);
            return var4;
         } else {
            throw new IllegalStateException("couldn't move cursor to position " + var1);
         }
      } else {
         throw new IllegalStateException("this should only be called when the cursor is valid");
      }
   }

   public boolean hasStableIds() {
      return true;
   }

   void init(Context var1, Cursor var2, int var3) {
      boolean var5 = false;
      if ((var3 & 1) == 1) {
         var3 |= 2;
         this.mAutoRequery = true;
      } else {
         this.mAutoRequery = false;
      }

      if (var2 != null) {
         var5 = true;
      }

      this.mCursor = var2;
      this.mDataValid = var5;
      this.mContext = var1;
      int var4;
      if (var5) {
         var4 = var2.getColumnIndexOrThrow("_id");
      } else {
         var4 = -1;
      }

      this.mRowIDColumn = var4;
      if ((var3 & 2) == 2) {
         this.mChangeObserver = new ChangeObserver(this);
         this.mDataSetObserver = new MyDataSetObserver(this);
      } else {
         this.mChangeObserver = null;
         this.mDataSetObserver = null;
      }

      if (var5) {
         ChangeObserver var6 = this.mChangeObserver;
         if (var6 != null) {
            var2.registerContentObserver(var6);
         }

         DataSetObserver var7 = this.mDataSetObserver;
         if (var7 != null) {
            var2.registerDataSetObserver(var7);
         }
      }

   }

   @Deprecated
   protected void init(Context var1, Cursor var2, boolean var3) {
      byte var4;
      if (var3) {
         var4 = 1;
      } else {
         var4 = 2;
      }

      this.init(var1, var2, var4);
   }

   public View newDropDownView(Context var1, Cursor var2, ViewGroup var3) {
      return this.newView(var1, var2, var3);
   }

   public abstract View newView(Context var1, Cursor var2, ViewGroup var3);

   protected void onContentChanged() {
      if (this.mAutoRequery) {
         Cursor var1 = this.mCursor;
         if (var1 != null && !var1.isClosed()) {
            this.mDataValid = this.mCursor.requery();
         }
      }

   }

   public Cursor runQueryOnBackgroundThread(CharSequence var1) {
      FilterQueryProvider var2 = this.mFilterQueryProvider;
      return var2 != null ? var2.runQuery(var1) : this.mCursor;
   }

   public void setFilterQueryProvider(FilterQueryProvider var1) {
      this.mFilterQueryProvider = var1;
   }

   public Cursor swapCursor(Cursor var1) {
      Cursor var2 = this.mCursor;
      if (var1 == var2) {
         return null;
      } else {
         ChangeObserver var3;
         DataSetObserver var4;
         if (var2 != null) {
            var3 = this.mChangeObserver;
            if (var3 != null) {
               var2.unregisterContentObserver(var3);
            }

            var4 = this.mDataSetObserver;
            if (var4 != null) {
               var2.unregisterDataSetObserver(var4);
            }
         }

         this.mCursor = var1;
         if (var1 != null) {
            var3 = this.mChangeObserver;
            if (var3 != null) {
               var1.registerContentObserver(var3);
            }

            var4 = this.mDataSetObserver;
            if (var4 != null) {
               var1.registerDataSetObserver(var4);
            }

            this.mRowIDColumn = var1.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            this.notifyDataSetChanged();
         } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            this.notifyDataSetInvalidated();
         }

         return var2;
      }
   }

   private class ChangeObserver extends ContentObserver {
      final CursorAdapter this$0;

      ChangeObserver(CursorAdapter var1) {
         super(new Handler());
         this.this$0 = var1;
      }

      public boolean deliverSelfNotifications() {
         return true;
      }

      public void onChange(boolean var1) {
         this.this$0.onContentChanged();
      }
   }

   private class MyDataSetObserver extends DataSetObserver {
      final CursorAdapter this$0;

      MyDataSetObserver(CursorAdapter var1) {
         this.this$0 = var1;
      }

      public void onChanged() {
         this.this$0.mDataValid = true;
         this.this$0.notifyDataSetChanged();
      }

      public void onInvalidated() {
         this.this$0.mDataValid = false;
         this.this$0.notifyDataSetInvalidated();
      }
   }
}
