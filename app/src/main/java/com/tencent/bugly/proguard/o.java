package com.tencent.bugly.proguard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;

public abstract class o {
   public int id;
   public String moduleName;
   public String version;
   public String versionKey;

   public abstract String[] getTables();

   public abstract void init(Context var1, boolean var2, BuglyStrategy var3);

   public void onDbCreate(SQLiteDatabase var1) {
   }

   public void onDbDowngrade(SQLiteDatabase var1, int var2, int var3) {
      Throwable var10000;
      label225: {
         boolean var10001;
         try {
            if (this.getTables() == null) {
               return;
            }
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label225;
         }

         String[] var4;
         try {
            var4 = this.getTables();
            var3 = var4.length;
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label225;
         }

         for(var2 = 0; var2 < var3; ++var2) {
            try {
               var1.execSQL("DROP TABLE IF EXISTS ".concat(String.valueOf(var4[var2])));
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label225;
            }
         }

         label204:
         try {
            this.onDbCreate(var1);
            return;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            break label204;
         }
      }

      Throwable var25 = var10000;
      if (!al.b(var25)) {
         var25.printStackTrace();
      }

   }

   public void onDbUpgrade(SQLiteDatabase var1, int var2, int var3) {
      Throwable var10000;
      label225: {
         boolean var10001;
         try {
            if (this.getTables() == null) {
               return;
            }
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label225;
         }

         String[] var4;
         try {
            var4 = this.getTables();
            var3 = var4.length;
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label225;
         }

         for(var2 = 0; var2 < var3; ++var2) {
            try {
               var1.execSQL("DROP TABLE IF EXISTS ".concat(String.valueOf(var4[var2])));
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label225;
            }
         }

         label204:
         try {
            this.onDbCreate(var1);
            return;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            break label204;
         }
      }

      Throwable var25 = var10000;
      if (!al.b(var25)) {
         var25.printStackTrace();
      }

   }

   public void onServerStrategyChanged(StrategyBean var1) {
   }
}
