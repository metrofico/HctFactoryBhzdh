package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class w {
   public static boolean a;
   private static w b;
   private static x c;

   private w(Context var1, List var2) {
      c = new x(var1, var2);
   }

   private int a(String var1, String var2, String[] var3, v var4) {
      synchronized(this){}
      byte var8 = 0;
      byte var7 = 0;
      int var5 = 0;
      SQLiteDatabase var10 = null;

      int var6;
      label976: {
         Throwable var103;
         Throwable var10000;
         label975: {
            boolean var10001;
            label981: {
               SQLiteDatabase var11;
               label982: {
                  label983: {
                     try {
                        var11 = c.getWritableDatabase();
                     } catch (Throwable var102) {
                        var10000 = var102;
                        var10001 = false;
                        break label983;
                     }

                     if (var11 == null) {
                        break label982;
                     }

                     var10 = var11;

                     label968:
                     try {
                        var5 = var11.delete(var1, var2, var3);
                        break label982;
                     } catch (Throwable var101) {
                        var10000 = var101;
                        var10001 = false;
                        break label968;
                     }
                  }

                  var103 = var10000;
                  boolean var75 = false;

                  try {
                     var75 = true;
                     if (!al.a(var103)) {
                        var103.printStackTrace();
                        var75 = false;
                     } else {
                        var75 = false;
                     }
                  } finally {
                     if (var75) {
                        if (var4 != null) {
                        }

                        label945: {
                           try {
                              if (!a) {
                                 break label945;
                              }
                           } catch (Throwable var96) {
                              var10000 = var96;
                              var10001 = false;
                              break label975;
                           }

                           if (var10 != null) {
                              try {
                                 var10.close();
                              } catch (Throwable var95) {
                                 var10000 = var95;
                                 var10001 = false;
                                 break label975;
                              }
                           }
                        }

                        try {
                           ;
                        } catch (Throwable var94) {
                           var10000 = var94;
                           var10001 = false;
                           break label975;
                        }
                     }
                  }

                  if (var4 != null) {
                  }

                  boolean var9;
                  try {
                     var9 = a;
                  } catch (Throwable var99) {
                     var10000 = var99;
                     var10001 = false;
                     break label975;
                  }

                  var6 = var7;
                  if (!var9) {
                     return var6;
                  }

                  var6 = var7;
                  if (var10 == null) {
                     return var6;
                  }

                  var5 = var8;
                  break label981;
               }

               if (var4 != null) {
               }

               var6 = var5;

               try {
                  if (!a) {
                     return var6;
                  }
               } catch (Throwable var98) {
                  var10000 = var98;
                  var10001 = false;
                  break label975;
               }

               var6 = var5;
               if (var11 == null) {
                  return var6;
               }

               var10 = var11;
            }

            label949:
            try {
               var10.close();
               break label976;
            } catch (Throwable var97) {
               var10000 = var97;
               var10001 = false;
               break label949;
            }
         }

         var103 = var10000;
         throw var103;
      }

      var6 = var5;
      return var6;
   }

   private Cursor a(boolean var1, String var2, String[] var3, String var4, String[] var5, String var6, String var7, String var8, String var9, v var10) {
      synchronized(this){}
      Object var11 = null;

      Throwable var10000;
      Cursor var30;
      label189: {
         SQLiteDatabase var12;
         boolean var10001;
         try {
            var12 = c.getWritableDatabase();
         } catch (Throwable var29) {
            var10000 = var29;
            var10001 = false;
            break label189;
         }

         var30 = (Cursor)var11;
         if (var12 == null) {
            return var30;
         }

         label179:
         try {
            var30 = var12.query(var1, var2, var3, var4, var5, var6, var7, var8, var9);
            return var30;
         } catch (Throwable var28) {
            var10000 = var28;
            var10001 = false;
            break label179;
         }
      }

      Throwable var31 = var10000;
      var30 = (Cursor)var11;

      try {
         if (al.a(var31)) {
            return var30;
         }

         var31.printStackTrace();
      } finally {
         ;
      }

      var30 = (Cursor)var11;
      return var30;
   }

   public static w a() {
      synchronized(w.class){}

      w var0;
      try {
         var0 = b;
      } finally {
         ;
      }

      return var0;
   }

   public static w a(Context var0, List var1) {
      synchronized(w.class){}

      w var5;
      try {
         if (b == null) {
            w var2 = new w(var0, var1);
            b = var2;
         }

         var5 = b;
      } finally {
         ;
      }

      return var5;
   }

   private static y a(Cursor var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            y var1 = new y();
            var1.a = var0.getLong(var0.getColumnIndex("_id"));
            var1.b = var0.getInt(var0.getColumnIndex("_tp"));
            var1.c = var0.getString(var0.getColumnIndex("_pc"));
            var1.d = var0.getString(var0.getColumnIndex("_th"));
            var1.e = var0.getLong(var0.getColumnIndex("_tm"));
            var1.g = var0.getBlob(var0.getColumnIndex("_dt"));
            return var1;
         } catch (Throwable var3) {
            if (!al.a(var3)) {
               var3.printStackTrace();
            }

            return null;
         }
      }
   }

   private boolean a(int var1, String var2, v var3) {
      synchronized(this){}
      Object var9 = null;
      boolean var6 = false;
      boolean var5 = false;

      SQLiteDatabase var8;
      try {
         var8 = c.getWritableDatabase();
      } finally {
         ;
      }

      boolean var4;
      label1977: {
         Throwable var10000;
         label1987: {
            var4 = var5;
            boolean var10001;
            if (var8 != null) {
               label2000: {
                  label1972: {
                     label1971: {
                        try {
                           if (ap.b(var2)) {
                              var2 = "_id = ".concat(String.valueOf(var1));
                              break label1971;
                           }
                        } catch (Throwable var220) {
                           var10000 = var220;
                           var10001 = false;
                           break label1972;
                        }

                        try {
                           StringBuilder var7 = new StringBuilder("_id = ");
                           var2 = var7.append(var1).append(" and _tp = \"").append(var2).append("\"").toString();
                        } catch (Throwable var219) {
                           var10000 = var219;
                           var10001 = false;
                           break label1972;
                        }
                     }

                     label1963:
                     try {
                        var1 = var8.delete("t_pf", var2, (String[])null);
                        al.c("[Database] deleted %s data %d", "t_pf", var1);
                        break label2000;
                     } catch (Throwable var218) {
                        var10000 = var218;
                        var10001 = false;
                        break label1963;
                     }
                  }

                  Throwable var224 = var10000;
                  SQLiteDatabase var221 = var8;
                  boolean var150 = false;

                  Boolean var222;
                  try {
                     var150 = true;
                     if (!al.a(var224)) {
                        var224.printStackTrace();
                        var150 = false;
                     } else {
                        var150 = false;
                     }
                  } finally {
                     if (var150) {
                        if (var3 != null) {
                           try {
                              var222 = Boolean.FALSE;
                           } catch (Throwable var211) {
                              var10000 = var211;
                              var10001 = false;
                              break label1987;
                           }
                        }

                        label1928: {
                           try {
                              if (!a) {
                                 break label1928;
                              }
                           } catch (Throwable var210) {
                              var10000 = var210;
                              var10001 = false;
                              break label1987;
                           }

                           if (var8 != null) {
                              try {
                                 var221.close();
                              } catch (Throwable var209) {
                                 var10000 = var209;
                                 var10001 = false;
                                 break label1987;
                              }
                           }
                        }

                        try {
                           ;
                        } catch (Throwable var208) {
                           var10000 = var208;
                           var10001 = false;
                           break label1987;
                        }
                     }
                  }

                  if (var3 != null) {
                     try {
                        var222 = Boolean.FALSE;
                     } catch (Throwable var214) {
                        var10000 = var214;
                        var10001 = false;
                        break label1987;
                     }
                  }

                  var5 = var6;

                  try {
                     if (!a) {
                        return var5;
                     }
                  } catch (Throwable var213) {
                     var10000 = var213;
                     var10001 = false;
                     break label1987;
                  }

                  var5 = var6;
                  if (var8 == null) {
                     return var5;
                  }

                  try {
                     var221.close();
                  } catch (Throwable var212) {
                     var10000 = var212;
                     var10001 = false;
                     break label1987;
                  }

                  var5 = var6;
                  return var5;
               }

               var4 = var5;
               if (var1 > 0) {
                  var4 = true;
               }
            }

            if (var3 != null) {
            }

            var5 = var4;

            try {
               if (!a) {
                  return var5;
               }
            } catch (Throwable var217) {
               var10000 = var217;
               var10001 = false;
               break label1987;
            }

            var5 = var4;
            if (var8 == null) {
               return var5;
            }

            label1951:
            try {
               var8.close();
               break label1977;
            } catch (Throwable var216) {
               var10000 = var216;
               var10001 = false;
               break label1951;
            }
         }

         Throwable var223 = var10000;
         throw var223;
      }

      var5 = var4;
      return var5;
   }

   private boolean a(int var1, String var2, byte[] var3, v var4) {
      boolean var5;
      boolean var6;
      try {
         y var7 = new y();
         var7.a = (long)var1;
         var7.f = var2;
         var7.e = System.currentTimeMillis();
         var7.g = var3;
         var6 = this.b(var7);
      } catch (Throwable var14) {
         Throwable var15 = var14;
         boolean var10 = false;

         try {
            var10 = true;
            if (!al.a(var15)) {
               var15.printStackTrace();
               var10 = false;
            } else {
               var10 = false;
            }
         } finally {
            if (var10) {
               if (var4 != null) {
                  Boolean var17 = Boolean.FALSE;
               }

            }
         }

         if (var4 != null) {
            Boolean var16 = Boolean.FALSE;
         }

         var5 = false;
         return var5;
      }

      var5 = var6;
      if (var4 != null) {
         var5 = var6;
      }

      return var5;
   }

   private static y b(Cursor var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            y var1 = new y();
            var1.a = var0.getLong(var0.getColumnIndex("_id"));
            var1.e = var0.getLong(var0.getColumnIndex("_tm"));
            var1.f = var0.getString(var0.getColumnIndex("_tp"));
            var1.g = var0.getBlob(var0.getColumnIndex("_dt"));
            return var1;
         } catch (Throwable var3) {
            if (!al.a(var3)) {
               var3.printStackTrace();
            }

            return null;
         }
      }
   }

   private boolean b(y var1) {
      synchronized(this){}
      SQLiteDatabase var4 = null;

      Throwable var293;
      Throwable var10000;
      label2422: {
         boolean var10001;
         label2433: {
            SQLiteDatabase var5;
            try {
               var5 = c.getWritableDatabase();
            } catch (Throwable var292) {
               var10000 = var292;
               var10001 = false;
               break label2433;
            }

            if (var5 != null) {
               var4 = var5;

               ContentValues var6;
               try {
                  var6 = d(var1);
               } catch (Throwable var291) {
                  var10000 = var291;
                  var10001 = false;
                  break label2433;
               }

               if (var6 != null) {
                  var4 = var5;

                  long var2;
                  try {
                     var2 = var5.replace("t_pf", "_id", var6);
                  } catch (Throwable var290) {
                     var10000 = var290;
                     var10001 = false;
                     break label2433;
                  }

                  if (var2 < 0L) {
                     try {
                        if (!a) {
                           return false;
                        }
                     } catch (Throwable var285) {
                        var10000 = var285;
                        var10001 = false;
                        break label2422;
                     }

                     if (var5 != null) {
                        try {
                           var5.close();
                        } catch (Throwable var284) {
                           var10000 = var284;
                           var10001 = false;
                           break label2422;
                        }
                     }

                     return false;
                  }

                  var4 = var5;

                  try {
                     al.c("[Database] insert %s success.", "t_pf");
                  } catch (Throwable var289) {
                     var10000 = var289;
                     var10001 = false;
                     break label2433;
                  }

                  var4 = var5;

                  try {
                     var1.a = var2;
                  } catch (Throwable var288) {
                     var10000 = var288;
                     var10001 = false;
                     break label2433;
                  }

                  try {
                     if (!a) {
                        return true;
                     }
                  } catch (Throwable var283) {
                     var10000 = var283;
                     var10001 = false;
                     break label2422;
                  }

                  if (var5 != null) {
                     try {
                        var5.close();
                     } catch (Throwable var282) {
                        var10000 = var282;
                        var10001 = false;
                        break label2422;
                     }
                  }

                  return true;
               }
            }

            try {
               if (!a) {
                  return false;
               }
            } catch (Throwable var287) {
               var10000 = var287;
               var10001 = false;
               break label2422;
            }

            if (var5 != null) {
               try {
                  var5.close();
               } catch (Throwable var286) {
                  var10000 = var286;
                  var10001 = false;
                  break label2422;
               }
            }

            return false;
         }

         var293 = var10000;

         try {
            if (!al.a(var293)) {
               var293.printStackTrace();
            }
         } finally {
            label2363: {
               try {
                  if (!a) {
                     break label2363;
                  }
               } catch (Throwable var280) {
                  var10000 = var280;
                  var10001 = false;
                  break label2422;
               }

               if (var4 != null) {
                  try {
                     var4.close();
                  } catch (Throwable var279) {
                     var10000 = var279;
                     var10001 = false;
                     break label2422;
                  }
               }
            }

            try {
               ;
            } catch (Throwable var278) {
               var10000 = var278;
               var10001 = false;
               break label2422;
            }
         }

         return false;
      }

      var293 = var10000;
      throw var293;
   }

   private static ContentValues c(y var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            ContentValues var1 = new ContentValues();
            if (var0.a > 0L) {
               var1.put("_id", var0.a);
            }

            var1.put("_tp", var0.b);
            var1.put("_pc", var0.c);
            var1.put("_th", var0.d);
            var1.put("_tm", var0.e);
            if (var0.g != null) {
               var1.put("_dt", var0.g);
            }

            return var1;
         } catch (Throwable var3) {
            if (!al.a(var3)) {
               var3.printStackTrace();
            }

            return null;
         }
      }
   }

   private List c(int param1) {
      // $FF: Couldn't be decompiled
   }

   private static ContentValues d(y var0) {
      if (var0 != null && !ap.b(var0.f)) {
         label52:
         try {
            ContentValues var1 = new ContentValues();
            if (var0.a > 0L) {
               var1.put("_id", var0.a);
            }

            var1.put("_tp", var0.f);
            var1.put("_tm", var0.e);
            if (var0.g != null) {
               var1.put("_dt", var0.g);
            }

            return var1;
         } catch (Throwable var3) {
            if (!al.a(var3)) {
               var3.printStackTrace();
            }
            break label52;
         }
      }

      return null;
   }

   public final int a(String var1, String var2) {
      return this.a((String)var1, var2, (String[])null, (v)null);
   }

   public final long a(String var1, ContentValues var2, v var3) {
      synchronized(this){}
      long var7 = -1L;
      SQLiteDatabase var11 = null;

      long var5;
      long var9;
      label1391: {
         Throwable var10000;
         Throwable var146;
         label1390: {
            boolean var10001;
            label1396: {
               SQLiteDatabase var12;
               label1397: {
                  label1398: {
                     try {
                        var12 = c.getWritableDatabase();
                     } catch (Throwable var145) {
                        var10000 = var145;
                        var10001 = false;
                        break label1398;
                     }

                     var5 = var7;
                     if (var12 == null) {
                        break label1397;
                     }

                     var5 = var7;
                     if (var2 == null) {
                        break label1397;
                     }

                     var11 = var12;

                     try {
                        var5 = var12.replace(var1, "_id", var2);
                     } catch (Throwable var144) {
                        var10000 = var144;
                        var10001 = false;
                        break label1398;
                     }

                     if (var5 >= 0L) {
                        var11 = var12;

                        label1375:
                        try {
                           al.c("[Database] insert %s success.", var1);
                        } catch (Throwable var142) {
                           var10000 = var142;
                           var10001 = false;
                           break label1375;
                        }
                     } else {
                        var11 = var12;

                        label1378:
                        try {
                           al.d("[Database] replace %s error.", var1);
                        } catch (Throwable var143) {
                           var10000 = var143;
                           var10001 = false;
                           break label1378;
                        }
                     }
                     break label1397;
                  }

                  var146 = var10000;
                  boolean var90 = false;

                  try {
                     var90 = true;
                     if (!al.a(var146)) {
                        var146.printStackTrace();
                        var90 = false;
                     } else {
                        var90 = false;
                     }
                  } finally {
                     if (var90) {
                        if (var3 != null) {
                        }

                        label1350: {
                           try {
                              if (!a) {
                                 break label1350;
                              }
                           } catch (Throwable var137) {
                              var10000 = var137;
                              var10001 = false;
                              break label1390;
                           }

                           if (var11 != null) {
                              try {
                                 var11.close();
                              } catch (Throwable var136) {
                                 var10000 = var136;
                                 var10001 = false;
                                 break label1390;
                              }
                           }
                        }

                        try {
                           ;
                        } catch (Throwable var135) {
                           var10000 = var135;
                           var10001 = false;
                           break label1390;
                        }
                     }
                  }

                  if (var3 != null) {
                  }

                  boolean var4;
                  try {
                     var4 = a;
                  } catch (Throwable var140) {
                     var10000 = var140;
                     var10001 = false;
                     break label1390;
                  }

                  var9 = var7;
                  if (!var4) {
                     return var9;
                  }

                  var9 = var7;
                  if (var11 == null) {
                     return var9;
                  }

                  var5 = var7;
                  break label1396;
               }

               if (var3 != null) {
               }

               var9 = var5;

               try {
                  if (!a) {
                     return var9;
                  }
               } catch (Throwable var139) {
                  var10000 = var139;
                  var10001 = false;
                  break label1390;
               }

               var9 = var5;
               if (var12 == null) {
                  return var9;
               }

               var11 = var12;
            }

            label1354:
            try {
               var11.close();
               break label1391;
            } catch (Throwable var138) {
               var10000 = var138;
               var10001 = false;
               break label1354;
            }
         }

         var146 = var10000;
         throw var146;
      }

      var9 = var5;
      return var9;
   }

   public final Cursor a(String var1, String[] var2, String var3) {
      return this.a((String)var1, (String[])var2, var3, (String)null, (String)null);
   }

   public final Cursor a(String var1, String[] var2, String var3, String var4, String var5) {
      return this.a(false, var1, var2, var3, (String[])null, (String)null, (String)null, var4, var5, (v)null);
   }

   public final List a(int var1) {
      synchronized(this){}

      Throwable var10000;
      label3982: {
         SQLiteDatabase var6;
         boolean var10001;
         try {
            var6 = c.getWritableDatabase();
         } catch (Throwable var505) {
            var10000 = var505;
            var10001 = false;
            break label3982;
         }

         if (var6 == null) {
            return null;
         }

         Cursor var514;
         Throwable var5;
         label3983: {
            label3974: {
               label3973: {
                  String var4;
                  if (var1 >= 0) {
                     try {
                        var4 = "_tp = ".concat(String.valueOf(var1));
                     } catch (Throwable var513) {
                        var10000 = var513;
                        var10001 = false;
                        break label3973;
                     }
                  } else {
                     var4 = null;
                  }

                  label3969:
                  try {
                     var514 = var6.query("t_lr", (String[])null, var4, (String[])null, (String)null, (String)null, (String)null);
                     break label3974;
                  } catch (Throwable var512) {
                     var10000 = var512;
                     var10001 = false;
                     break label3969;
                  }
               }

               var5 = var10000;
               var514 = null;
               break label3983;
            }

            if (var514 == null) {
               if (var514 != null) {
                  try {
                     var514.close();
                  } catch (Throwable var500) {
                     var10000 = var500;
                     var10001 = false;
                     break label3982;
                  }
               }

               try {
                  if (!a) {
                     return null;
                  }
               } catch (Throwable var499) {
                  var10000 = var499;
                  var10001 = false;
                  break label3982;
               }

               if (var6 != null) {
                  try {
                     var6.close();
                  } catch (Throwable var498) {
                     var10000 = var498;
                     var10001 = false;
                     break label3982;
                  }
               }

               return null;
            }

            label3962: {
               ArrayList var515;
               StringBuilder var7;
               try {
                  var7 = new StringBuilder();
                  var515 = new ArrayList();
               } catch (Throwable var509) {
                  var10000 = var509;
                  var10001 = false;
                  break label3962;
               }

               while(true) {
                  y var8;
                  label3999: {
                     try {
                        if (var514.moveToNext()) {
                           var8 = a(var514);
                           break label3999;
                        }
                     } catch (Throwable var511) {
                        var10000 = var511;
                        var10001 = false;
                        break;
                     }

                     try {
                        String var517 = var7.toString();
                        if (var517.length() > 0) {
                           al.d("[Database] deleted %s illegal data %d", "t_lr", var6.delete("t_lr", var517.substring(4), (String[])null));
                        }
                     } catch (Throwable var506) {
                        var10000 = var506;
                        var10001 = false;
                        break;
                     }

                     if (var514 != null) {
                        try {
                           var514.close();
                        } catch (Throwable var503) {
                           var10000 = var503;
                           var10001 = false;
                           break label3982;
                        }
                     }

                     try {
                        if (!a) {
                           return var515;
                        }
                     } catch (Throwable var502) {
                        var10000 = var502;
                        var10001 = false;
                        break label3982;
                     }

                     if (var6 != null) {
                        try {
                           var6.close();
                        } catch (Throwable var501) {
                           var10000 = var501;
                           var10001 = false;
                           break label3982;
                        }
                     }

                     return var515;
                  }

                  if (var8 != null) {
                     try {
                        var515.add(var8);
                     } catch (Throwable var508) {
                        var10000 = var508;
                        var10001 = false;
                        break;
                     }
                  } else {
                     boolean var313 = false;

                     try {
                        var313 = true;
                        long var2 = var514.getLong(var514.getColumnIndex("_id"));
                        var7.append(" or _id = ").append(var2);
                        var313 = false;
                     } finally {
                        if (var313) {
                           try {
                              al.d("[Database] unknown id.");
                              continue;
                           } catch (Throwable var507) {
                              var10000 = var507;
                              var10001 = false;
                              break;
                           }
                        }
                     }
                  }
               }
            }

            var5 = var10000;
         }

         try {
            if (!al.a(var5)) {
               var5.printStackTrace();
            }

            return null;
         } finally {
            label3987: {
               if (var514 != null) {
                  try {
                     var514.close();
                  } catch (Throwable var497) {
                     var10000 = var497;
                     var10001 = false;
                     break label3987;
                  }
               }

               label3903: {
                  try {
                     if (!a) {
                        break label3903;
                     }
                  } catch (Throwable var496) {
                     var10000 = var496;
                     var10001 = false;
                     break label3987;
                  }

                  if (var6 != null) {
                     try {
                        var6.close();
                     } catch (Throwable var495) {
                        var10000 = var495;
                        var10001 = false;
                        break label3987;
                     }
                  }
               }

               label3896:
               try {
                  ;
               } catch (Throwable var494) {
                  var10000 = var494;
                  var10001 = false;
                  break label3896;
               }
            }
         }
      }

      Throwable var516 = var10000;
      throw var516;
   }

   public final Map a(int var1, v var2) {
      HashMap var48 = null;
      HashMap var3 = null;

      Throwable var50;
      label457: {
         List var4;
         Throwable var10000;
         boolean var10001;
         label450: {
            label458: {
               try {
                  var4 = this.c(var1);
               } catch (Throwable var47) {
                  var10000 = var47;
                  var10001 = false;
                  break label458;
               }

               if (var4 == null) {
                  return var48;
               }

               label445:
               try {
                  var48 = new HashMap();
                  break label450;
               } catch (Throwable var46) {
                  var10000 = var46;
                  var10001 = false;
                  break label445;
               }
            }

            var50 = var10000;
            break label457;
         }

         label440: {
            Iterator var49;
            try {
               var49 = var4.iterator();
            } catch (Throwable var45) {
               var10000 = var45;
               var10001 = false;
               break label440;
            }

            while(true) {
               y var5;
               byte[] var51;
               try {
                  if (!var49.hasNext()) {
                     return var48;
                  }

                  var5 = (y)var49.next();
                  var51 = var5.g;
               } catch (Throwable var44) {
                  var10000 = var44;
                  var10001 = false;
                  break;
               }

               if (var51 != null) {
                  try {
                     var48.put(var5.f, var51);
                  } catch (Throwable var43) {
                     var10000 = var43;
                     var10001 = false;
                     break;
                  }
               }
            }
         }

         var50 = var10000;
         var3 = var48;
      }

      var48 = var3;

      try {
         if (al.a(var50)) {
            return var48;
         }

         var50.printStackTrace();
      } finally {
         ;
      }

      var48 = var3;
      return var48;
   }

   public final void a(List var1) {
      Throwable var10000;
      Throwable var181;
      label1418: {
         boolean var10001;
         label1404: {
            synchronized(this){}
            if (var1 != null) {
               try {
                  if (var1.size() != 0) {
                     break label1404;
                  }
               } catch (Throwable var177) {
                  var10000 = var177;
                  var10001 = false;
                  break label1418;
               }
            }

            return;
         }

         SQLiteDatabase var3;
         try {
            var3 = c.getWritableDatabase();
         } catch (Throwable var176) {
            var10000 = var176;
            var10001 = false;
            break label1418;
         }

         if (var3 != null) {
            Iterator var2;
            StringBuilder var4;
            try {
               var4 = new StringBuilder();
               var2 = var1.iterator();
            } catch (Throwable var171) {
               var10000 = var171;
               var10001 = false;
               break label1418;
            }

            while(true) {
               try {
                  if (!var2.hasNext()) {
                     break;
                  }

                  y var178 = (y)var2.next();
                  var4.append(" or _id = ").append(var178.a);
               } catch (Throwable var175) {
                  var10000 = var175;
                  var10001 = false;
                  break label1418;
               }
            }

            String var180;
            try {
               var180 = var4.toString();
            } catch (Throwable var170) {
               var10000 = var170;
               var10001 = false;
               break label1418;
            }

            String var179 = var180;

            try {
               if (var180.length() > 0) {
                  var179 = var180.substring(4);
               }
            } catch (Throwable var174) {
               var10000 = var174;
               var10001 = false;
               break label1418;
            }

            try {
               var4.setLength(0);
            } catch (Throwable var169) {
               var10000 = var169;
               var10001 = false;
               break label1418;
            }

            try {
               al.c("[Database] deleted %s data %d", "t_lr", var3.delete("t_lr", var179, (String[])null));
            } catch (Throwable var173) {
               var181 = var173;

               try {
                  if (!al.a(var181)) {
                     var181.printStackTrace();
                  }

                  return;
               } finally {
                  try {
                     if (a) {
                        var3.close();
                     }
                  } catch (Throwable var167) {
                     var10000 = var167;
                     var10001 = false;
                     break label1418;
                  }

                  try {
                     ;
                  } catch (Throwable var166) {
                     var10000 = var166;
                     var10001 = false;
                     break label1418;
                  }
               }
            }

            try {
               if (a) {
                  var3.close();
                  return;
               }
            } catch (Throwable var172) {
               var10000 = var172;
               var10001 = false;
               break label1418;
            }
         }

         return;
      }

      var181 = var10000;
      throw var181;
   }

   public final boolean a(int var1, String var2, byte[] var3, boolean var4) {
      if (!var4) {
         a var5 = new a(this);
         var5.a(var1, var2, var3);
         ak.a().a(var5);
         return true;
      } else {
         return this.a(var1, var2, var3, (v)null);
      }
   }

   public final boolean a(y var1) {
      synchronized(this){}
      SQLiteDatabase var4 = null;

      Throwable var293;
      Throwable var10000;
      label2422: {
         boolean var10001;
         label2433: {
            SQLiteDatabase var5;
            try {
               var5 = c.getWritableDatabase();
            } catch (Throwable var292) {
               var10000 = var292;
               var10001 = false;
               break label2433;
            }

            if (var5 != null) {
               var4 = var5;

               ContentValues var6;
               try {
                  var6 = c(var1);
               } catch (Throwable var291) {
                  var10000 = var291;
                  var10001 = false;
                  break label2433;
               }

               if (var6 != null) {
                  var4 = var5;

                  long var2;
                  try {
                     var2 = var5.replace("t_lr", "_id", var6);
                  } catch (Throwable var290) {
                     var10000 = var290;
                     var10001 = false;
                     break label2433;
                  }

                  if (var2 < 0L) {
                     try {
                        if (!a) {
                           return false;
                        }
                     } catch (Throwable var285) {
                        var10000 = var285;
                        var10001 = false;
                        break label2422;
                     }

                     if (var5 != null) {
                        try {
                           var5.close();
                        } catch (Throwable var284) {
                           var10000 = var284;
                           var10001 = false;
                           break label2422;
                        }
                     }

                     return false;
                  }

                  var4 = var5;

                  try {
                     al.c("[Database] insert %s success.", "t_lr");
                  } catch (Throwable var289) {
                     var10000 = var289;
                     var10001 = false;
                     break label2433;
                  }

                  var4 = var5;

                  try {
                     var1.a = var2;
                  } catch (Throwable var288) {
                     var10000 = var288;
                     var10001 = false;
                     break label2433;
                  }

                  try {
                     if (!a) {
                        return true;
                     }
                  } catch (Throwable var283) {
                     var10000 = var283;
                     var10001 = false;
                     break label2422;
                  }

                  if (var5 != null) {
                     try {
                        var5.close();
                     } catch (Throwable var282) {
                        var10000 = var282;
                        var10001 = false;
                        break label2422;
                     }
                  }

                  return true;
               }
            }

            try {
               if (!a) {
                  return false;
               }
            } catch (Throwable var287) {
               var10000 = var287;
               var10001 = false;
               break label2422;
            }

            if (var5 != null) {
               try {
                  var5.close();
               } catch (Throwable var286) {
                  var10000 = var286;
                  var10001 = false;
                  break label2422;
               }
            }

            return false;
         }

         var293 = var10000;

         try {
            if (!al.a(var293)) {
               var293.printStackTrace();
            }
         } finally {
            label2363: {
               try {
                  if (!a) {
                     break label2363;
                  }
               } catch (Throwable var280) {
                  var10000 = var280;
                  var10001 = false;
                  break label2422;
               }

               if (var4 != null) {
                  try {
                     var4.close();
                  } catch (Throwable var279) {
                     var10000 = var279;
                     var10001 = false;
                     break label2422;
                  }
               }
            }

            try {
               ;
            } catch (Throwable var278) {
               var10000 = var278;
               var10001 = false;
               break label2422;
            }
         }

         return false;
      }

      var293 = var10000;
      throw var293;
   }

   public final void b(int var1) {
      synchronized(this){}

      Throwable var128;
      Throwable var10000;
      label1090: {
         SQLiteDatabase var3;
         boolean var10001;
         try {
            var3 = c.getWritableDatabase();
         } catch (Throwable var127) {
            var10000 = var127;
            var10001 = false;
            break label1090;
         }

         if (var3 == null) {
            return;
         }

         label1091: {
            label1079: {
               String var2;
               if (var1 >= 0) {
                  try {
                     var2 = "_tp = ".concat(String.valueOf(var1));
                  } catch (Throwable var126) {
                     var10000 = var126;
                     var10001 = false;
                     break label1079;
                  }
               } else {
                  var2 = null;
               }

               label1075:
               try {
                  al.c("[Database] deleted %s data %d", "t_lr", var3.delete("t_lr", var2, (String[])null));
                  break label1091;
               } catch (Throwable var125) {
                  var10000 = var125;
                  var10001 = false;
                  break label1075;
               }
            }

            var128 = var10000;

            try {
               if (!al.a(var128)) {
                  var128.printStackTrace();
               }
            } finally {
               label1054: {
                  try {
                     if (!a) {
                        break label1054;
                     }
                  } catch (Throwable var121) {
                     var10000 = var121;
                     var10001 = false;
                     break label1090;
                  }

                  if (var3 != null) {
                     try {
                        var3.close();
                     } catch (Throwable var120) {
                        var10000 = var120;
                        var10001 = false;
                        break label1090;
                     }
                  }
               }

               try {
                  ;
               } catch (Throwable var119) {
                  var10000 = var119;
                  var10001 = false;
                  break label1090;
               }
            }

            return;
         }

         try {
            if (!a) {
               return;
            }
         } catch (Throwable var124) {
            var10000 = var124;
            var10001 = false;
            break label1090;
         }

         if (var3 == null) {
            return;
         }

         try {
            var3.close();
         } catch (Throwable var123) {
            var10000 = var123;
            var10001 = false;
            break label1090;
         }

         return;
      }

      var128 = var10000;
      throw var128;
   }

   final class a extends Thread {
      final w a;
      private int b;
      private v c;
      private String d;
      private ContentValues e;
      private boolean f;
      private String[] g;
      private String h;
      private String[] i;
      private String j;
      private String k;
      private String l;
      private String m;
      private String n;
      private String[] o;
      private int p;
      private String q;
      private byte[] r;

      public a(w var1) {
         this.a = var1;
         this.b = 4;
         this.c = null;
      }

      public final void a(int var1, String var2, byte[] var3) {
         this.p = var1;
         this.q = var2;
         this.r = var3;
      }

      public final void run() {
         switch (this.b) {
            case 1:
               this.a.a(this.d, this.e, this.c);
               break;
            case 2:
               this.a.a(this.d, this.n, this.o, this.c);
               return;
            case 3:
               Cursor var1 = this.a.a(this.f, this.d, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.c);
               if (var1 != null) {
                  var1.close();
                  return;
               }
               break;
            case 4:
               this.a.a(this.p, this.q, this.r, this.c);
               return;
            case 5:
               this.a.a(this.p, this.c);
               return;
            case 6:
               this.a.a(this.p, this.q, this.c);
         }

      }
   }
}
