package com.android.common.contacts;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import com.android.common.widget.CompositeCursorAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseEmailAddressAdapter extends CompositeCursorAdapter implements Filterable {
   private static final int ALLOWANCE_FOR_DUPLICATES = 5;
   private static final int DEFAULT_PREFERRED_MAX_RESULT_COUNT = 10;
   private static final long DIRECTORY_LOCAL_INVISIBLE = 1L;
   private static final String DIRECTORY_PARAM_KEY = "directory";
   private static final String LIMIT_PARAM_KEY = "limit";
   private static final int MESSAGE_SEARCH_PENDING = 1;
   private static final int MESSAGE_SEARCH_PENDING_DELAY = 1000;
   private static final String PRIMARY_ACCOUNT_NAME = "name_for_primary_account";
   private static final String PRIMARY_ACCOUNT_TYPE = "type_for_primary_account";
   private static final String SEARCHING_CURSOR_MARKER = "searching";
   private static final String TAG = "BaseEmailAddressAdapter";
   private Account mAccount;
   protected final ContentResolver mContentResolver;
   private boolean mDirectoriesLoaded;
   private Handler mHandler;
   private int mPreferredMaxResultCount;

   public BaseEmailAddressAdapter(Context var1) {
      this(var1, 10);
   }

   public BaseEmailAddressAdapter(Context var1, int var2) {
      super(var1);
      this.mContentResolver = var1.getContentResolver();
      this.mPreferredMaxResultCount = var2;
      this.mHandler = new Handler(this) {
         final BaseEmailAddressAdapter this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            this.this$0.showSearchPendingIfNotComplete(var1.arg1);
         }
      };
   }

   private Cursor createLoadingCursor() {
      MatrixCursor var1 = new MatrixCursor(new String[]{"searching"});
      var1.addRow(new Object[]{""});
      return var1;
   }

   private boolean hasDuplicates(Cursor var1, int var2) {
      var1.moveToPosition(-1);

      do {
         if (!var1.moveToNext()) {
            return false;
         }
      } while(!this.isDuplicate(var1.getString(1), var2));

      return true;
   }

   private boolean isDuplicate(String var1, int var2) {
      int var4 = this.getPartitionCount();

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var3 != var2 && !this.isLoading(var3)) {
            Cursor var5 = this.getCursor(var3);
            if (var5 != null) {
               var5.moveToPosition(-1);

               while(var5.moveToNext()) {
                  if (TextUtils.equals(var1, var5.getString(1))) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private boolean isLoading(int var1) {
      return ((DirectoryPartition)this.getPartition(var1)).loading;
   }

   private final String makeDisplayString(Cursor var1) {
      if (var1.getColumnName(0).equals("searching")) {
         return "";
      } else {
         String var2 = var1.getString(0);
         String var3 = var1.getString(1);
         return !TextUtils.isEmpty(var2) && !TextUtils.equals(var2, var3) ? (new Rfc822Token(var2, var3, (String)null)).toString() : var3;
      }
   }

   private Cursor removeDuplicatesAndTruncate(int var1, Cursor var2) {
      if (var2 == null) {
         return null;
      } else if (var2.getCount() <= 10 && !this.hasDuplicates(var2, var1)) {
         return var2;
      } else {
         int var4 = 0;
         MatrixCursor var6 = new MatrixCursor(EmailQuery.PROJECTION);
         var2.moveToPosition(-1);

         int var3;
         for(; var2.moveToNext() && var4 < 10; var4 = var3) {
            String var5 = var2.getString(0);
            String var7 = var2.getString(1);
            var3 = var4;
            if (!this.isDuplicate(var7, var1)) {
               var6.addRow(new Object[]{var5, var7});
               var3 = var4 + 1;
            }
         }

         var2.close();
         return var6;
      }
   }

   public boolean areAllItemsEnabled() {
      return false;
   }

   protected void bindView(View var1, int var2, Cursor var3, int var4) {
      DirectoryPartition var5 = (DirectoryPartition)this.getPartition(var2);
      String var7 = var5.directoryType;
      String var8 = var5.displayName;
      if (var5.loading) {
         this.bindViewLoading(var1, var7, var8);
      } else {
         String var6 = var3.getString(0);
         String var10 = var3.getString(1);
         String var9;
         if (!TextUtils.isEmpty(var6) && !TextUtils.equals(var6, var10)) {
            var9 = var6;
         } else {
            var9 = var10;
            var10 = null;
         }

         this.bindView(var1, var7, var8, var9, var10);
      }

   }

   protected abstract void bindView(View var1, String var2, String var3, String var4, String var5);

   protected abstract void bindViewLoading(View var1, String var2, String var3);

   public Filter getFilter() {
      return new DefaultPartitionFilter(this);
   }

   protected int getItemViewType(int var1, int var2) {
      return ((DirectoryPartition)this.getPartition(var1)).loading;
   }

   protected abstract View inflateItemView(ViewGroup var1);

   protected abstract View inflateItemViewLoading(ViewGroup var1);

   protected boolean isEnabled(int var1, int var2) {
      return this.isLoading(var1) ^ true;
   }

   protected View newView(Context var1, int var2, Cursor var3, int var4, ViewGroup var5) {
      return ((DirectoryPartition)this.getPartition(var2)).loading ? this.inflateItemViewLoading(var5) : this.inflateItemView(var5);
   }

   protected void onDirectoryLoadFinished(CharSequence var1, Cursor var2, Cursor var3) {
      int var4;
      if (var2 != null) {
         PackageManager var12 = this.getContext().getPackageManager();
         ArrayList var11 = new ArrayList();
         DirectoryPartition var9 = null;

         label893:
         while(true) {
            while(true) {
               long var7;
               do {
                  if (!var2.moveToNext()) {
                     if (var9 != null) {
                        var11.add(1, var9);
                     }

                     Iterator var66 = var11.iterator();

                     while(var66.hasNext()) {
                        this.addPartition((DirectoryPartition)var66.next());
                     }
                     break label893;
                  }

                  var7 = var2.getLong(0);
               } while(var7 == 1L);

               DirectoryPartition var10 = new DirectoryPartition();
               var10.directoryId = var7;
               var10.displayName = var2.getString(3);
               var10.accountName = var2.getString(1);
               var10.accountType = var2.getString(2);
               String var13 = var2.getString(4);
               var4 = var2.getInt(5);
               if (var13 != null && var4 != 0) {
                  StringBuilder var14;
                  try {
                     var10.directoryType = var12.getResourcesForApplication(var13).getString(var4);
                     if (var10.directoryType == null) {
                        var14 = new StringBuilder();
                        var14.append("Cannot resolve directory name: ");
                        var14.append(var4);
                        var14.append("@");
                        var14.append(var13);
                        Log.e("BaseEmailAddressAdapter", var14.toString());
                     }
                  } catch (PackageManager.NameNotFoundException var58) {
                     var14 = new StringBuilder();
                     var14.append("Cannot resolve directory name: ");
                     var14.append(var4);
                     var14.append("@");
                     var14.append(var13);
                     Log.e("BaseEmailAddressAdapter", var14.toString(), var58);
                  }
               }

               Account var70 = this.mAccount;
               if (var70 != null && var70.name.equals(var10.accountName) && this.mAccount.type.equals(var10.accountType)) {
                  var9 = var10;
               } else {
                  var11.add(var10);
               }
            }
         }
      }

      Throwable var10000;
      label901: {
         int var5 = this.getPartitionCount();
         this.setNotificationsEnabled(false);
         boolean var10001;
         if (var3 != null) {
            try {
               if (this.getPartitionCount() > 0) {
                  this.changeCursor(0, var3);
               }
            } catch (Throwable var64) {
               var10000 = var64;
               var10001 = false;
               break label901;
            }
         }

         if (var3 == null) {
            var4 = 0;
         } else {
            try {
               var4 = var3.getCount();
            } catch (Throwable var63) {
               var10000 = var63;
               var10001 = false;
               break label901;
            }
         }

         int var6;
         try {
            var6 = this.mPreferredMaxResultCount - var4;
         } catch (Throwable var62) {
            var10000 = var62;
            var10001 = false;
            break label901;
         }

         var4 = 1;

         while(true) {
            if (var4 >= var5) {
               this.setNotificationsEnabled(true);

               for(var4 = 1; var4 < var5; ++var4) {
                  DirectoryPartition var69 = (DirectoryPartition)this.getPartition(var4);
                  if (var69.loading) {
                     this.mHandler.removeMessages(1, var69);
                     Message var68 = this.mHandler.obtainMessage(1, var4, 0, var69);
                     this.mHandler.sendMessageDelayed(var68, 1000L);
                     if (var69.filter == null) {
                        var69.filter = new DirectoryPartitionFilter(this, var4, var69.directoryId);
                     }

                     var69.filter.setLimit(var6);
                     var69.filter.filter(var1);
                  } else if (var69.filter != null) {
                     var69.filter.filter((CharSequence)null);
                  }
               }

               return;
            }

            DirectoryPartition var67;
            try {
               var67 = (DirectoryPartition)this.getPartition(var4);
               var67.constraint = var1;
            } catch (Throwable var61) {
               var10000 = var61;
               var10001 = false;
               break;
            }

            if (var6 > 0) {
               try {
                  if (!var67.loading) {
                     var67.loading = true;
                     this.changeCursor(var4, (Cursor)null);
                  }
               } catch (Throwable var59) {
                  var10000 = var59;
                  var10001 = false;
                  break;
               }
            } else {
               try {
                  var67.loading = false;
                  this.changeCursor(var4, (Cursor)null);
               } catch (Throwable var60) {
                  var10000 = var60;
                  var10001 = false;
                  break;
               }
            }

            ++var4;
         }
      }

      Throwable var65 = var10000;
      this.setNotificationsEnabled(true);
      throw var65;
   }

   public void onPartitionLoadFinished(CharSequence var1, int var2, Cursor var3) {
      if (var2 < this.getPartitionCount()) {
         DirectoryPartition var4 = (DirectoryPartition)this.getPartition(var2);
         if (var4.loading && TextUtils.equals(var1, var4.constraint)) {
            var4.loading = false;
            this.mHandler.removeMessages(1, var4);
            this.changeCursor(var2, this.removeDuplicatesAndTruncate(var2, var3));
         } else if (var3 != null) {
            var3.close();
         }
      } else if (var3 != null) {
         var3.close();
      }

   }

   public void setAccount(Account var1) {
      this.mAccount = var1;
   }

   void showSearchPendingIfNotComplete(int var1) {
      if (var1 < this.getPartitionCount() && ((DirectoryPartition)this.getPartition(var1)).loading) {
         this.changeCursor(var1, this.createLoadingCursor());
      }

   }

   private final class DefaultPartitionFilter extends Filter {
      final BaseEmailAddressAdapter this$0;

      private DefaultPartitionFilter(BaseEmailAddressAdapter var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      DefaultPartitionFilter(BaseEmailAddressAdapter var1, Object var2) {
         this(var1);
      }

      public CharSequence convertResultToString(Object var1) {
         return this.this$0.makeDisplayString((Cursor)var1);
      }

      protected FilterResults performFiltering(CharSequence var1) {
         Cursor var2 = null;
         if (!this.this$0.mDirectoriesLoaded) {
            var2 = this.this$0.mContentResolver.query(DirectoryListQuery.URI, DirectoryListQuery.PROJECTION, (String)null, (String[])null, (String)null);
            this.this$0.mDirectoriesLoaded = true;
         }

         FilterResults var4 = new FilterResults();
         Cursor var3 = null;
         if (!TextUtils.isEmpty(var1)) {
            Uri.Builder var5 = Email.CONTENT_FILTER_URI.buildUpon().appendPath(var1.toString()).appendQueryParameter("limit", String.valueOf(this.this$0.mPreferredMaxResultCount));
            if (this.this$0.mAccount != null) {
               var5.appendQueryParameter("name_for_primary_account", this.this$0.mAccount.name);
               var5.appendQueryParameter("type_for_primary_account", this.this$0.mAccount.type);
            }

            Uri var6 = var5.build();
            var3 = this.this$0.mContentResolver.query(var6, EmailQuery.PROJECTION, (String)null, (String[])null, (String)null);
            var4.count = var3.getCount();
         }

         var4.values = new Cursor[]{var2, var3};
         return var4;
      }

      protected void publishResults(CharSequence var1, FilterResults var2) {
         if (var2.values != null) {
            Cursor[] var3 = (Cursor[])var2.values;
            this.this$0.onDirectoryLoadFinished(var1, var3[0], var3[1]);
         }

         var2.count = this.this$0.getCount();
      }
   }

   private static class DirectoryListQuery {
      public static final int ACCOUNT_NAME = 1;
      public static final int ACCOUNT_TYPE = 2;
      private static final String DIRECTORY_ACCOUNT_NAME = "accountName";
      private static final String DIRECTORY_ACCOUNT_TYPE = "accountType";
      private static final String DIRECTORY_DISPLAY_NAME = "displayName";
      private static final String DIRECTORY_ID = "_id";
      private static final String DIRECTORY_PACKAGE_NAME = "packageName";
      private static final String DIRECTORY_TYPE_RESOURCE_ID = "typeResourceId";
      public static final int DISPLAY_NAME = 3;
      public static final int ID = 0;
      public static final int PACKAGE_NAME = 4;
      public static final String[] PROJECTION;
      public static final int TYPE_RESOURCE_ID = 5;
      public static final Uri URI;

      static {
         URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories");
         PROJECTION = new String[]{"_id", "accountName", "accountType", "displayName", "packageName", "typeResourceId"};
      }
   }

   public static final class DirectoryPartition extends Partition {
      public String accountName;
      public String accountType;
      public CharSequence constraint;
      public long directoryId;
      public String directoryType;
      public String displayName;
      public DirectoryPartitionFilter filter;
      public boolean loading;

      public DirectoryPartition() {
         super(false, false);
      }
   }

   private final class DirectoryPartitionFilter extends Filter {
      private final long mDirectoryId;
      private int mLimit;
      private final int mPartitionIndex;
      final BaseEmailAddressAdapter this$0;

      public DirectoryPartitionFilter(BaseEmailAddressAdapter var1, int var2, long var3) {
         this.this$0 = var1;
         this.mPartitionIndex = var2;
         this.mDirectoryId = var3;
      }

      public int getLimit() {
         synchronized(this){}

         int var1;
         try {
            var1 = this.mLimit;
         } finally {
            ;
         }

         return var1;
      }

      protected FilterResults performFiltering(CharSequence var1) {
         FilterResults var2 = new FilterResults();
         if (!TextUtils.isEmpty(var1)) {
            Uri var3 = Email.CONTENT_FILTER_URI.buildUpon().appendPath(var1.toString()).appendQueryParameter("directory", String.valueOf(this.mDirectoryId)).appendQueryParameter("limit", String.valueOf(this.getLimit() + 5)).build();
            var2.values = this.this$0.mContentResolver.query(var3, EmailQuery.PROJECTION, (String)null, (String[])null, (String)null);
         }

         return var2;
      }

      protected void publishResults(CharSequence var1, FilterResults var2) {
         Cursor var3 = (Cursor)var2.values;
         this.this$0.onPartitionLoadFinished(var1, this.mPartitionIndex, var3);
         var2.count = this.this$0.getCount();
      }

      public void setLimit(int var1) {
         synchronized(this){}

         try {
            this.mLimit = var1;
         } finally {
            ;
         }

      }
   }

   private static class EmailQuery {
      public static final int ADDRESS = 1;
      public static final int NAME = 0;
      public static final String[] PROJECTION = new String[]{"display_name", "data1"};
   }
}
