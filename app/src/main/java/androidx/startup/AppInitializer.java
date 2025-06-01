package androidx.startup;

import android.content.Context;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AppInitializer {
   private static final String SECTION_NAME = "Startup";
   private static volatile AppInitializer sInstance;
   private static final Object sLock = new Object();
   final Context mContext;
   final Set mDiscovered;
   final Map mInitialized;

   AppInitializer(Context var1) {
      this.mContext = var1.getApplicationContext();
      this.mDiscovered = new HashSet();
      this.mInitialized = new HashMap();
   }

   public static AppInitializer getInstance(Context var0) {
      if (sInstance == null) {
         Object var1 = sLock;
         synchronized(var1){}

         Throwable var10000;
         boolean var10001;
         label144: {
            try {
               if (sInstance == null) {
                  AppInitializer var2 = new AppInitializer(var0);
                  sInstance = var2;
               }
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label144;
            }

            label141:
            try {
               return sInstance;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label141;
            }
         }

         while(true) {
            Throwable var15 = var10000;

            try {
               throw var15;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               continue;
            }
         }
      } else {
         return sInstance;
      }
   }

   void discoverAndInitialize() {
      // $FF: Couldn't be decompiled
   }

   Object doInitialize(Class var1, Set var2) {
      Object var5 = sLock;
      synchronized(var5){}

      Throwable var10000;
      boolean var10001;
      Throwable var165;
      label1411: {
         boolean var3;
         try {
            var3 = Trace.isEnabled();
         } catch (Throwable var163) {
            var10000 = var163;
            var10001 = false;
            break label1411;
         }

         label1412: {
            if (var3) {
               try {
                  Trace.beginSection(var1.getSimpleName());
               } catch (Throwable var158) {
                  var10000 = var158;
                  var10001 = false;
                  break label1412;
               }
            }

            label1401: {
               Object var164;
               label1413: {
                  label1399: {
                     try {
                        if (var2.contains(var1)) {
                           break label1401;
                        }

                        if (!this.mInitialized.containsKey(var1)) {
                           var2.add(var1);
                           break label1399;
                        }
                     } catch (Throwable var162) {
                        var10000 = var162;
                        var10001 = false;
                        break label1412;
                     }

                     try {
                        var164 = this.mInitialized.get(var1);
                        break label1413;
                     } catch (Throwable var157) {
                        var10000 = var157;
                        var10001 = false;
                        break label1412;
                     }
                  }

                  Object var169;
                  label1415: {
                     label1390: {
                        Initializer var4;
                        label1389: {
                           Iterator var170;
                           try {
                              var4 = (Initializer)var1.getDeclaredConstructor().newInstance();
                              List var6 = var4.dependencies();
                              if (var6.isEmpty()) {
                                 break label1389;
                              }

                              var170 = var6.iterator();
                           } catch (Throwable var160) {
                              var10000 = var160;
                              var10001 = false;
                              break label1390;
                           }

                           label1388:
                           while(true) {
                              try {
                                 Class var7;
                                 do {
                                    if (!var170.hasNext()) {
                                       break label1388;
                                    }

                                    var7 = (Class)var170.next();
                                 } while(this.mInitialized.containsKey(var7));

                                 this.doInitialize(var7, var2);
                              } catch (Throwable var161) {
                                 var10000 = var161;
                                 var10001 = false;
                                 break label1390;
                              }
                           }
                        }

                        label1374:
                        try {
                           var169 = var4.create(this.mContext);
                           var2.remove(var1);
                           this.mInitialized.put(var1, var169);
                           break label1415;
                        } catch (Throwable var159) {
                           var10000 = var159;
                           var10001 = false;
                           break label1374;
                        }
                     }

                     var165 = var10000;

                     try {
                        StartupException var166 = new StartupException(var165);
                        throw var166;
                     } catch (Throwable var154) {
                        var10000 = var154;
                        var10001 = false;
                        break label1412;
                     }
                  }

                  var164 = var169;
               }

               try {
                  Trace.endSection();
                  return var164;
               } catch (Throwable var155) {
                  var10000 = var155;
                  var10001 = false;
                  break label1411;
               }
            }

            label1361:
            try {
               String var168 = String.format("Cannot initialize %s. Cycle detected.", var1.getName());
               IllegalStateException var167 = new IllegalStateException(var168);
               throw var167;
            } catch (Throwable var156) {
               var10000 = var156;
               var10001 = false;
               break label1361;
            }
         }

         var165 = var10000;

         label1353:
         try {
            Trace.endSection();
            throw var165;
         } catch (Throwable var153) {
            var10000 = var153;
            var10001 = false;
            break label1353;
         }
      }

      while(true) {
         var165 = var10000;

         try {
            throw var165;
         } catch (Throwable var152) {
            var10000 = var152;
            var10001 = false;
            continue;
         }
      }
   }

   public Object initializeComponent(Class var1) {
      return this.doInitialize(var1, new HashSet());
   }

   public boolean isEagerlyInitialized(Class var1) {
      return this.mDiscovered.contains(var1);
   }
}
