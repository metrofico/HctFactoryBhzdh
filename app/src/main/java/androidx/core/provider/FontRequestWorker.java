package androidx.core.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

class FontRequestWorker {
   private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = RequestExecutor.createDefaultExecutor("fonts-androidx", 10, 10000);
   static final Object LOCK = new Object();
   static final SimpleArrayMap PENDING_REPLIES = new SimpleArrayMap();
   static final LruCache sTypefaceCache = new LruCache(16);

   private FontRequestWorker() {
   }

   private static String createCacheId(FontRequest var0, int var1) {
      return var0.getId() + "-" + var1;
   }

   private static int getFontFamilyResultStatus(FontsContractCompat.FontFamilyResult var0) {
      int var2 = var0.getStatusCode();
      byte var3 = -3;
      int var1 = 1;
      if (var2 != 0) {
         return var0.getStatusCode() != 1 ? -3 : -2;
      } else {
         FontsContractCompat.FontInfo[] var6 = var0.getFonts();
         byte var7 = (byte)var1;
         if (var6 != null) {
            if (var6.length == 0) {
               var7 = (byte)var1;
            } else {
               int var5 = var6.length;
               byte var4 = 0;
               var1 = 0;

               while(true) {
                  var7 = var4;
                  if (var1 >= var5) {
                     break;
                  }

                  var2 = var6[var1].getResultCode();
                  if (var2 != 0) {
                     if (var2 < 0) {
                        var1 = var3;
                     } else {
                        var1 = var2;
                     }

                     return var1;
                  }

                  ++var1;
               }
            }
         }

         return var7;
      }
   }

   static TypefaceResult getFontSync(String var0, Context var1, FontRequest var2, int var3) {
      LruCache var5 = sTypefaceCache;
      Typeface var6 = (Typeface)var5.get(var0);
      if (var6 != null) {
         return new TypefaceResult(var6);
      } else {
         FontsContractCompat.FontFamilyResult var9;
         try {
            var9 = FontProvider.getFontFamilyResult(var1, var2, (CancellationSignal)null);
         } catch (PackageManager.NameNotFoundException var7) {
            return new TypefaceResult(-1);
         }

         int var4 = getFontFamilyResultStatus(var9);
         if (var4 != 0) {
            return new TypefaceResult(var4);
         } else {
            Typeface var8 = TypefaceCompat.createFromFontInfo(var1, (CancellationSignal)null, var9.getFonts(), var3);
            if (var8 != null) {
               var5.put(var0, var8);
               return new TypefaceResult(var8);
            } else {
               return new TypefaceResult(-3);
            }
         }
      }
   }

   static Typeface requestFontAsync(Context var0, FontRequest var1, int var2, Executor var3, CallbackWithHandler var4) {
      String var5 = createCacheId(var1, var2);
      Typeface var6 = (Typeface)sTypefaceCache.get(var5);
      if (var6 != null) {
         var4.onTypefaceResult(new TypefaceResult(var6));
         return var6;
      } else {
         Consumer var7 = new Consumer(var4) {
            final CallbackWithHandler val$callback;

            {
               this.val$callback = var1;
            }

            public void accept(TypefaceResult var1) {
               TypefaceResult var2 = var1;
               if (var1 == null) {
                  var2 = new TypefaceResult(-3);
               }

               this.val$callback.onTypefaceResult(var2);
            }
         };
         Object var32 = LOCK;
         synchronized(var32){}

         Throwable var10000;
         boolean var10001;
         label215: {
            SimpleArrayMap var33;
            ArrayList var8;
            try {
               var33 = PENDING_REPLIES;
               var8 = (ArrayList)var33.get(var5);
            } catch (Throwable var28) {
               var10000 = var28;
               var10001 = false;
               break label215;
            }

            if (var8 != null) {
               label208:
               try {
                  var8.add(var7);
                  return null;
               } catch (Throwable var26) {
                  var10000 = var26;
                  var10001 = false;
                  break label208;
               }
            } else {
               label221: {
                  try {
                     var8 = new ArrayList();
                     var8.add(var7);
                     var33.put(var5, var8);
                  } catch (Throwable var27) {
                     var10000 = var27;
                     var10001 = false;
                     break label221;
                  }

                  Callable var31 = new Callable(var5, var0, var1, var2) {
                     final Context val$context;
                     final String val$id;
                     final FontRequest val$request;
                     final int val$style;

                     {
                        this.val$id = var1;
                        this.val$context = var2;
                        this.val$request = var3;
                        this.val$style = var4;
                     }

                     public TypefaceResult call() {
                        try {
                           TypefaceResult var1 = FontRequestWorker.getFontSync(this.val$id, this.val$context, this.val$request, this.val$style);
                           return var1;
                        } finally {
                           return new TypefaceResult(-3);
                        }
                     }
                  };
                  Object var30 = var3;
                  if (var3 == null) {
                     var30 = DEFAULT_EXECUTOR_SERVICE;
                  }

                  RequestExecutor.execute((Executor)var30, var31, new Consumer(var5) {
                     final String val$id;

                     {
                        this.val$id = var1;
                     }

                     public void accept(TypefaceResult var1) {
                        Object var3 = FontRequestWorker.LOCK;
                        synchronized(var3){}

                        Throwable var10000;
                        boolean var10001;
                        label222: {
                           ArrayList var4;
                           try {
                              var4 = (ArrayList)FontRequestWorker.PENDING_REPLIES.get(this.val$id);
                           } catch (Throwable var24) {
                              var10000 = var24;
                              var10001 = false;
                              break label222;
                           }

                           if (var4 == null) {
                              label215:
                              try {
                                 return;
                              } catch (Throwable var22) {
                                 var10000 = var22;
                                 var10001 = false;
                                 break label215;
                              }
                           } else {
                              label226: {
                                 try {
                                    FontRequestWorker.PENDING_REPLIES.remove(this.val$id);
                                 } catch (Throwable var23) {
                                    var10000 = var23;
                                    var10001 = false;
                                    break label226;
                                 }

                                 for(int var2 = 0; var2 < var4.size(); ++var2) {
                                    ((Consumer)var4.get(var2)).accept(var1);
                                 }

                                 return;
                              }
                           }
                        }

                        while(true) {
                           Throwable var25 = var10000;

                           try {
                              throw var25;
                           } catch (Throwable var21) {
                              var10000 = var21;
                              var10001 = false;
                              continue;
                           }
                        }
                     }
                  });
                  return null;
               }
            }
         }

         while(true) {
            Throwable var29 = var10000;

            try {
               throw var29;
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               continue;
            }
         }
      }
   }

   static Typeface requestFontSync(Context var0, FontRequest var1, CallbackWithHandler var2, int var3, int var4) {
      String var5 = createCacheId(var1, var3);
      Typeface var6 = (Typeface)sTypefaceCache.get(var5);
      if (var6 != null) {
         var2.onTypefaceResult(new TypefaceResult(var6));
         return var6;
      } else {
         TypefaceResult var9;
         if (var4 == -1) {
            var9 = getFontSync(var5, var0, var1, var3);
            var2.onTypefaceResult(var9);
            return var9.mTypeface;
         } else {
            Callable var8 = new Callable(var5, var0, var1, var3) {
               final Context val$context;
               final String val$id;
               final FontRequest val$request;
               final int val$style;

               {
                  this.val$id = var1;
                  this.val$context = var2;
                  this.val$request = var3;
                  this.val$style = var4;
               }

               public TypefaceResult call() {
                  return FontRequestWorker.getFontSync(this.val$id, this.val$context, this.val$request, this.val$style);
               }
            };

            try {
               var9 = (TypefaceResult)RequestExecutor.submit(DEFAULT_EXECUTOR_SERVICE, var8, var4);
               var2.onTypefaceResult(var9);
               Typeface var10 = var9.mTypeface;
               return var10;
            } catch (InterruptedException var7) {
               var2.onTypefaceResult(new TypefaceResult(-3));
               return null;
            }
         }
      }
   }

   static void resetTypefaceCache() {
      sTypefaceCache.evictAll();
   }

   static final class TypefaceResult {
      final int mResult;
      final Typeface mTypeface;

      TypefaceResult(int var1) {
         this.mTypeface = null;
         this.mResult = var1;
      }

      TypefaceResult(Typeface var1) {
         this.mTypeface = var1;
         this.mResult = 0;
      }

      boolean isSuccess() {
         boolean var1;
         if (this.mResult == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }
   }
}
