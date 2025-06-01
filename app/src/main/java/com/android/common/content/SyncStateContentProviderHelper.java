package com.android.common.content;

import android.accounts.Account;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class SyncStateContentProviderHelper {
   private static final String[] ACCOUNT_PROJECTION = new String[]{"account_name", "account_type"};
   private static long DB_VERSION = 1L;
   public static final String PATH = "syncstate";
   private static final String QUERY_COUNT_SYNC_STATE_ROWS = "SELECT count(*) FROM _sync_state WHERE _id=?";
   private static final String SELECT_BY_ACCOUNT = "account_name=? AND account_type=?";
   private static final String SYNC_STATE_META_TABLE = "_sync_state_metadata";
   private static final String SYNC_STATE_META_VERSION_COLUMN = "version";
   private static final String SYNC_STATE_TABLE = "_sync_state";

   private static boolean contains(Object[] var0, Object var1) {
      int var3 = var0.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Object var4 = var0[var2];
         if (var4 == null) {
            if (var1 == null) {
               return true;
            }
         } else if (var1 != null && var4.equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public void createDatabase(SQLiteDatabase var1) {
      var1.execSQL("DROP TABLE IF EXISTS _sync_state");
      var1.execSQL("CREATE TABLE _sync_state (_id INTEGER PRIMARY KEY,account_name TEXT NOT NULL,account_type TEXT NOT NULL,data TEXT,UNIQUE(account_name, account_type));");
      var1.execSQL("DROP TABLE IF EXISTS _sync_state_metadata");
      var1.execSQL("CREATE TABLE _sync_state_metadata (version INTEGER);");
      ContentValues var2 = new ContentValues();
      var2.put("version", DB_VERSION);
      var1.insert("_sync_state_metadata", "version", var2);
   }

   public int delete(SQLiteDatabase var1, String var2, String[] var3) {
      return var1.delete("_sync_state", var2, var3);
   }

   public long insert(SQLiteDatabase var1, ContentValues var2) {
      return var1.replace("_sync_state", "account_name", var2);
   }

   public void onAccountsChanged(SQLiteDatabase var1, Account[] var2) {
      Cursor var3 = var1.query("_sync_state", ACCOUNT_PROJECTION, (String)null, (String[])null, (String)null, (String)null, (String)null);

      while(true) {
         boolean var8 = false;

         try {
            var8 = true;
            if (!var3.moveToNext()) {
               var8 = false;
               break;
            }

            String var4 = var3.getString(0);
            String var5 = var3.getString(1);
            Account var6 = new Account(var4, var5);
            if (!contains(var2, var6)) {
               var1.delete("_sync_state", "account_name=? AND account_type=?", new String[]{var4, var5});
               var8 = false;
            } else {
               var8 = false;
            }
         } finally {
            if (var8) {
               var3.close();
            }
         }
      }

      var3.close();
   }

   public void onDatabaseOpened(SQLiteDatabase var1) {
      if (DatabaseUtils.longForQuery(var1, "SELECT version FROM _sync_state_metadata", (String[])null) != DB_VERSION) {
         this.createDatabase(var1);
      }

   }

   public Cursor query(SQLiteDatabase var1, String[] var2, String var3, String[] var4, String var5) {
      return var1.query("_sync_state", var2, var3, var4, (String)null, (String)null, var5);
   }

   public int update(SQLiteDatabase var1, long var2, Object var4) {
      if (DatabaseUtils.longForQuery(var1, "SELECT count(*) FROM _sync_state WHERE _id=?", new String[]{Long.toString(var2)}) < 1L) {
         return 0;
      } else {
         StringBuilder var5 = new StringBuilder();
         var5.append("UPDATE _sync_state SET data=? WHERE _id=");
         var5.append(var2);
         var1.execSQL(var5.toString(), new Object[]{var4});
         return 1;
      }
   }

   public int update(SQLiteDatabase var1, ContentValues var2, String var3, String[] var4) {
      return var1.update("_sync_state", var2, var3, var4);
   }
}
