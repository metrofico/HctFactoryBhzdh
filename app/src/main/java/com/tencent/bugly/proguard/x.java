package com.tencent.bugly.proguard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class x extends SQLiteOpenHelper {
   public static String a;
   public static int b;
   protected Context c;
   private List d;

   public x(Context var1, List var2) {
      StringBuilder var3 = (new StringBuilder()).append(a).append("_");
      aa.a(var1).getClass();
      super(var1, var3.toString(), (SQLiteDatabase.CursorFactory)null, b);
      this.c = var1;
      this.d = var2;
   }

   private boolean a(SQLiteDatabase var1) {
      synchronized(this){}

      for(int var2 = 0; var2 < 3; ++var2) {
         try {
            var1.execSQL("DROP TABLE IF EXISTS ".concat(String.valueOf((new String[]{"t_lr", "t_ui", "t_pf"})[var2])), new String[0]);
         } catch (Throwable var8) {
            Throwable var9 = var8;

            try {
               if (!al.b(var9)) {
                  var9.printStackTrace();
               }
            } finally {
               ;
            }

            return false;
         }
      }

      return true;
   }

   public final SQLiteDatabase getReadableDatabase() {
      // $FF: Couldn't be decompiled
   }

   public final SQLiteDatabase getWritableDatabase() {
      // $FF: Couldn't be decompiled
   }

   public final void onCreate(SQLiteDatabase var1) {
      synchronized(this){}

      Throwable var10000;
      label498: {
         boolean var10001;
         try {
            StringBuilder var61 = new StringBuilder();
            var61.setLength(0);
            var61.append(" CREATE TABLE IF NOT EXISTS t_ui ( _id INTEGER PRIMARY KEY , _tm int , _ut int , _tp int , _dt blob , _pc text ) ");
            al.c(var61.toString());
            var1.execSQL(var61.toString(), new String[0]);
            var61.setLength(0);
            var61.append(" CREATE TABLE IF NOT EXISTS t_lr ( _id INTEGER PRIMARY KEY , _tp int , _tm int , _pc text , _th text , _dt blob ) ");
            al.c(var61.toString());
            var1.execSQL(var61.toString(), new String[0]);
            var61.setLength(0);
            var61.append(" CREATE TABLE IF NOT EXISTS t_pf ( _id integer , _tp text , _tm int , _dt blob,primary key(_id,_tp )) ");
            al.c(var61.toString());
            var1.execSQL(var61.toString(), new String[0]);
            var61.setLength(0);
            var61.append(" CREATE TABLE IF NOT EXISTS t_cr ( _id INTEGER PRIMARY KEY , _tm int , _s1 text , _up int , _me int , _uc int , _dt blob ) ");
            al.c(var61.toString());
            var1.execSQL(var61.toString(), new String[0]);
            var61.setLength(0);
            var61.append(" CREATE TABLE IF NOT EXISTS dl_1002 (_id integer primary key autoincrement, _dUrl varchar(100), _sFile varchar(100), _sLen INTEGER, _tLen INTEGER, _MD5 varchar(100), _DLTIME INTEGER)");
            al.c(var61.toString());
            var1.execSQL(var61.toString(), new String[0]);
            var61.setLength(0);
            var61.append("CREATE TABLE IF NOT EXISTS ge_1002 (_id integer primary key autoincrement, _time INTEGER, _datas blob)");
            al.c(var61.toString());
            var1.execSQL(var61.toString(), new String[0]);
            var61.setLength(0);
            var61.append(" CREATE TABLE IF NOT EXISTS st_1002 ( _id integer , _tp text , _tm int , _dt blob,primary key(_id,_tp )) ");
            al.c(var61.toString());
            var1.execSQL(var61.toString(), new String[0]);
            var61.setLength(0);
            var61.append(" CREATE TABLE IF NOT EXISTS t_sla ( _id TEXT NOT NULL , _tm INTEGER NOT NULL , _dt TEXT NOT NULL , PRIMARY KEY(_id) ) ");
            String var62 = var61.toString();
            al.c(var62);
            var1.execSQL(var62, new String[0]);
         } catch (Throwable var59) {
            Throwable var2 = var59;

            label490:
            try {
               if (!al.b(var2)) {
                  var2.printStackTrace();
               }
               break label490;
            } catch (Throwable var58) {
               var10000 = var58;
               var10001 = false;
               break label498;
            }
         }

         List var63;
         try {
            var63 = this.d;
         } catch (Throwable var57) {
            var10000 = var57;
            var10001 = false;
            break label498;
         }

         if (var63 == null) {
            return;
         }

         Iterator var64;
         try {
            var64 = var63.iterator();
         } catch (Throwable var56) {
            var10000 = var56;
            var10001 = false;
            break label498;
         }

         while(true) {
            o var3;
            try {
               if (!var64.hasNext()) {
                  return;
               }

               var3 = (o)var64.next();
            } catch (Throwable var55) {
               var10000 = var55;
               var10001 = false;
               break;
            }

            try {
               var3.onDbCreate(var1);
            } catch (Throwable var54) {
               Throwable var65 = var54;

               try {
                  if (!al.b(var65)) {
                     var65.printStackTrace();
                  }
                  continue;
               } catch (Throwable var53) {
                  var10000 = var53;
                  var10001 = false;
                  break;
               }
            }
         }
      }

      Throwable var60 = var10000;
      throw var60;
   }

   public final void onDowngrade(SQLiteDatabase var1, int var2, int var3) {
      synchronized(this){}

      Throwable var10000;
      label702: {
         List var4;
         boolean var10001;
         try {
            if (ab.c() < 11) {
               return;
            }

            al.d("[Database] Downgrade %d to %d drop tables.", var2, var3);
            var4 = this.d;
         } catch (Throwable var73) {
            var10000 = var73;
            var10001 = false;
            break label702;
         }

         if (var4 != null) {
            Iterator var80;
            try {
               var80 = var4.iterator();
            } catch (Throwable var72) {
               var10000 = var72;
               var10001 = false;
               break label702;
            }

            while(true) {
               o var5;
               try {
                  if (!var80.hasNext()) {
                     break;
                  }

                  var5 = (o)var80.next();
               } catch (Throwable var75) {
                  var10000 = var75;
                  var10001 = false;
                  break label702;
               }

               try {
                  var5.onDbDowngrade(var1, var2, var3);
               } catch (Throwable var77) {
                  Throwable var81 = var77;

                  try {
                     if (!al.b(var81)) {
                        var81.printStackTrace();
                     }
                     continue;
                  } catch (Throwable var76) {
                     var10000 = var76;
                     var10001 = false;
                     break label702;
                  }
               }
            }
         }

         try {
            if (this.a(var1)) {
               this.onCreate(var1);
               return;
            }
         } catch (Throwable var74) {
            var10000 = var74;
            var10001 = false;
            break label702;
         }

         File var78;
         try {
            al.d("[Database] Failed to drop, delete db.");
            var78 = this.c.getDatabasePath(a);
         } catch (Throwable var71) {
            var10000 = var71;
            var10001 = false;
            break label702;
         }

         if (var78 == null) {
            return;
         }

         label666:
         try {
            if (var78.canWrite()) {
               var78.delete();
            }

            return;
         } catch (Throwable var70) {
            var10000 = var70;
            var10001 = false;
            break label666;
         }
      }

      Throwable var79 = var10000;
      throw var79;
   }

   public final void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
      synchronized(this){}

      Throwable var10000;
      label661: {
         List var4;
         boolean var10001;
         try {
            al.d("[Database] Upgrade %d to %d , drop tables!", var2, var3);
            var4 = this.d;
         } catch (Throwable var73) {
            var10000 = var73;
            var10001 = false;
            break label661;
         }

         if (var4 != null) {
            Iterator var80;
            try {
               var80 = var4.iterator();
            } catch (Throwable var72) {
               var10000 = var72;
               var10001 = false;
               break label661;
            }

            while(true) {
               o var5;
               try {
                  if (!var80.hasNext()) {
                     break;
                  }

                  var5 = (o)var80.next();
               } catch (Throwable var75) {
                  var10000 = var75;
                  var10001 = false;
                  break label661;
               }

               try {
                  var5.onDbUpgrade(var1, var2, var3);
               } catch (Throwable var77) {
                  Throwable var81 = var77;

                  try {
                     if (!al.b(var81)) {
                        var81.printStackTrace();
                     }
                     continue;
                  } catch (Throwable var76) {
                     var10000 = var76;
                     var10001 = false;
                     break label661;
                  }
               }
            }
         }

         try {
            if (this.a(var1)) {
               this.onCreate(var1);
               return;
            }
         } catch (Throwable var74) {
            var10000 = var74;
            var10001 = false;
            break label661;
         }

         File var78;
         try {
            al.d("[Database] Failed to drop, delete db.");
            var78 = this.c.getDatabasePath(a);
         } catch (Throwable var71) {
            var10000 = var71;
            var10001 = false;
            break label661;
         }

         if (var78 != null) {
            try {
               if (var78.canWrite()) {
                  var78.delete();
               }
            } catch (Throwable var70) {
               var10000 = var70;
               var10001 = false;
               break label661;
            }
         }

         return;
      }

      Throwable var79 = var10000;
      throw var79;
   }
}
