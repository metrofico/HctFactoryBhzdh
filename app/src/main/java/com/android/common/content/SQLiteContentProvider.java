package com.android.common.content;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import java.util.ArrayList;

public abstract class SQLiteContentProvider extends ContentProvider implements SQLiteTransactionListener {
   private static final int MAX_OPERATIONS_PER_YIELD_POINT = 500;
   private static final int SLEEP_AFTER_YIELD_DELAY = 4000;
   private static final String TAG = "SQLiteContentProvider";
   private final ThreadLocal mApplyingBatch = new ThreadLocal();
   protected SQLiteDatabase mDb;
   private volatile boolean mNotifyChange;
   private SQLiteOpenHelper mOpenHelper;

   private boolean applyingBatch() {
      boolean var1;
      if (this.mApplyingBatch.get() != null && (Boolean)this.mApplyingBatch.get()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public ContentProviderResult[] applyBatch(ArrayList var1) throws OperationApplicationException {
      int var4 = 0;
      int var2 = 0;
      this.mDb = this.mOpenHelper.getWritableDatabase();
      this.mDb.beginTransactionWithListener(this);

      Throwable var10000;
      label744: {
         int var7;
         ContentProviderResult[] var9;
         boolean var10001;
         try {
            this.mApplyingBatch.set(true);
            var7 = var1.size();
            var9 = new ContentProviderResult[var7];
         } catch (Throwable var82) {
            var10000 = var82;
            var10001 = false;
            break label744;
         }

         int var3 = 0;

         while(true) {
            if (var3 >= var7) {
               try {
                  this.mDb.setTransactionSuccessful();
               } catch (Throwable var76) {
                  var10000 = var76;
                  var10001 = false;
                  break;
               }

               this.mApplyingBatch.set(false);
               this.mDb.endTransaction();
               this.onEndTransaction();
               return var9;
            }

            int var6 = var2 + 1;

            ContentProviderOperation var10;
            label731: {
               try {
                  if (var6 <= this.getMaxOperationsPerYield()) {
                     var10 = (ContentProviderOperation)var1.get(var3);
                     break label731;
                  }
               } catch (Throwable var81) {
                  var10000 = var81;
                  var10001 = false;
                  break;
               }

               try {
                  OperationApplicationException var83 = new OperationApplicationException("Too many content provider operations between yield points. The maximum number of operations per yield point is 500", var4);
                  throw var83;
               } catch (Throwable var75) {
                  var10000 = var75;
                  var10001 = false;
                  break;
               }
            }

            int var5 = var4;
            var2 = var6;
            if (var3 > 0) {
               label747: {
                  var5 = var4;
                  var2 = var6;

                  try {
                     if (!var10.isYieldAllowed()) {
                        break label747;
                     }
                  } catch (Throwable var80) {
                     var10000 = var80;
                     var10001 = false;
                     break;
                  }

                  byte var85 = 0;

                  boolean var8;
                  try {
                     var8 = this.mNotifyChange;
                  } catch (Throwable var78) {
                     var10000 = var78;
                     var10001 = false;
                     break;
                  }

                  var5 = var4;
                  var2 = var85;

                  try {
                     if (!this.mDb.yieldIfContendedSafely(4000L)) {
                        break label747;
                     }

                     this.mDb = this.mOpenHelper.getWritableDatabase();
                     this.mNotifyChange = var8;
                  } catch (Throwable var79) {
                     var10000 = var79;
                     var10001 = false;
                     break;
                  }

                  var5 = var4 + 1;
                  var2 = var85;
               }
            }

            try {
               var9[var3] = var10.apply(this, var9, var3);
            } catch (Throwable var77) {
               var10000 = var77;
               var10001 = false;
               break;
            }

            ++var3;
            var4 = var5;
         }
      }

      Throwable var84 = var10000;
      this.mApplyingBatch.set(false);
      this.mDb.endTransaction();
      this.onEndTransaction();
      throw var84;
   }

   protected void beforeTransactionCommit() {
   }

   public int bulkInsert(Uri var1, ContentValues[] var2) {
      int var4 = var2.length;
      this.mDb = this.mOpenHelper.getWritableDatabase();
      this.mDb.beginTransactionWithListener(this);
      int var3 = 0;

      while(true) {
         Throwable var10000;
         boolean var10001;
         if (var3 < var4) {
            label140: {
               try {
                  if (this.insertInTransaction(var1, var2[var3]) != null) {
                     this.mNotifyChange = true;
                  }
               } catch (Throwable var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label140;
               }

               try {
                  boolean var5 = this.mNotifyChange;
                  SQLiteDatabase var6 = this.mDb;
                  this.mDb.yieldIfContendedSafely();
                  this.mDb = var6;
                  this.mNotifyChange = var5;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label140;
               }

               ++var3;
               continue;
            }
         } else {
            label133:
            try {
               this.mDb.setTransactionSuccessful();
               break;
            } catch (Throwable var18) {
               var10000 = var18;
               var10001 = false;
               break label133;
            }
         }

         Throwable var19 = var10000;
         this.mDb.endTransaction();
         throw var19;
      }

      this.mDb.endTransaction();
      this.onEndTransaction();
      return var4;
   }

   public int delete(Uri var1, String var2, String[] var3) {
      int var4;
      if (!this.applyingBatch()) {
         this.mDb = this.mOpenHelper.getWritableDatabase();
         this.mDb.beginTransactionWithListener(this);

         label149: {
            Throwable var10000;
            label156: {
               boolean var10001;
               try {
                  var4 = this.deleteInTransaction(var1, var2, var3);
               } catch (Throwable var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label156;
               }

               if (var4 > 0) {
                  try {
                     this.mNotifyChange = true;
                  } catch (Throwable var16) {
                     var10000 = var16;
                     var10001 = false;
                     break label156;
                  }
               }

               label141:
               try {
                  this.mDb.setTransactionSuccessful();
                  break label149;
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break label141;
               }
            }

            Throwable var18 = var10000;
            this.mDb.endTransaction();
            throw var18;
         }

         this.mDb.endTransaction();
         this.onEndTransaction();
      } else {
         int var5 = this.deleteInTransaction(var1, var2, var3);
         var4 = var5;
         if (var5 > 0) {
            this.mNotifyChange = true;
            var4 = var5;
         }
      }

      return var4;
   }

   protected abstract int deleteInTransaction(Uri var1, String var2, String[] var3);

   public SQLiteOpenHelper getDatabaseHelper() {
      return this.mOpenHelper;
   }

   protected abstract SQLiteOpenHelper getDatabaseHelper(Context var1);

   public int getMaxOperationsPerYield() {
      return 500;
   }

   public Uri insert(Uri var1, ContentValues var2) {
      if (!this.applyingBatch()) {
         this.mDb = this.mOpenHelper.getWritableDatabase();
         this.mDb.beginTransactionWithListener(this);

         label149: {
            Throwable var10000;
            label156: {
               boolean var10001;
               try {
                  var1 = this.insertInTransaction(var1, var2);
               } catch (Throwable var14) {
                  var10000 = var14;
                  var10001 = false;
                  break label156;
               }

               if (var1 != null) {
                  try {
                     this.mNotifyChange = true;
                  } catch (Throwable var13) {
                     var10000 = var13;
                     var10001 = false;
                     break label156;
                  }
               }

               label141:
               try {
                  this.mDb.setTransactionSuccessful();
                  break label149;
               } catch (Throwable var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label141;
               }
            }

            Throwable var16 = var10000;
            this.mDb.endTransaction();
            throw var16;
         }

         this.mDb.endTransaction();
         this.onEndTransaction();
      } else {
         Uri var15 = this.insertInTransaction(var1, var2);
         var1 = var15;
         if (var15 != null) {
            this.mNotifyChange = true;
            var1 = var15;
         }
      }

      return var1;
   }

   protected abstract Uri insertInTransaction(Uri var1, ContentValues var2);

   protected abstract void notifyChange();

   public void onBegin() {
      this.onBeginTransaction();
   }

   protected void onBeginTransaction() {
   }

   public void onCommit() {
      this.beforeTransactionCommit();
   }

   public boolean onCreate() {
      this.mOpenHelper = this.getDatabaseHelper(this.getContext());
      return true;
   }

   protected void onEndTransaction() {
      if (this.mNotifyChange) {
         this.mNotifyChange = false;
         this.notifyChange();
      }

   }

   public void onRollback() {
   }

   public int update(Uri var1, ContentValues var2, String var3, String[] var4) {
      int var5;
      if (!this.applyingBatch()) {
         this.mDb = this.mOpenHelper.getWritableDatabase();
         this.mDb.beginTransactionWithListener(this);

         label149: {
            Throwable var10000;
            label156: {
               boolean var10001;
               try {
                  var5 = this.updateInTransaction(var1, var2, var3, var4);
               } catch (Throwable var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label156;
               }

               if (var5 > 0) {
                  try {
                     this.mNotifyChange = true;
                  } catch (Throwable var17) {
                     var10000 = var17;
                     var10001 = false;
                     break label156;
                  }
               }

               label141:
               try {
                  this.mDb.setTransactionSuccessful();
                  break label149;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label141;
               }
            }

            Throwable var19 = var10000;
            this.mDb.endTransaction();
            throw var19;
         }

         this.mDb.endTransaction();
         this.onEndTransaction();
      } else {
         int var6 = this.updateInTransaction(var1, var2, var3, var4);
         var5 = var6;
         if (var6 > 0) {
            this.mNotifyChange = true;
            var5 = var6;
         }
      }

      return var5;
   }

   protected abstract int updateInTransaction(Uri var1, ContentValues var2, String var3, String[] var4);
}
