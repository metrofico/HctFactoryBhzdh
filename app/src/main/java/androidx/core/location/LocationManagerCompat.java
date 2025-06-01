package androidx.core.location;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.Consumer;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class LocationManagerCompat {
   private static final long GET_CURRENT_LOCATION_TIMEOUT_MS = 30000L;
   private static final long MAX_CURRENT_LOCATION_AGE_MS = 10000L;
   private static final long PRE_N_LOOPER_TIMEOUT_S = 5L;
   private static Field sContextField;
   static final WeakHashMap sLocationListeners = new WeakHashMap();
   private static Method sRequestLocationUpdatesExecutorMethod;
   private static Method sRequestLocationUpdatesLooperMethod;

   private LocationManagerCompat() {
   }

   public static void getCurrentLocation(LocationManager var0, String var1, CancellationSignal var2, Executor var3, Consumer var4) {
      if (VERSION.SDK_INT >= 30) {
         LocationManagerCompat.Api30Impl.getCurrentLocation(var0, var1, var2, var3, var4);
      } else {
         if (var2 != null) {
            var2.throwIfCanceled();
         }

         Location var5 = var0.getLastKnownLocation(var1);
         if (var5 != null && SystemClock.elapsedRealtime() - LocationCompat.getElapsedRealtimeMillis(var5) < 10000L) {
            var3.execute(new LocationManagerCompat$$ExternalSyntheticLambda1(var4, var5));
         } else {
            CancellableLocationListener var6 = new CancellableLocationListener(var0, var3, var4);
            var0.requestLocationUpdates(var1, 0L, 0.0F, var6, Looper.getMainLooper());
            if (var2 != null) {
               var2.setOnCancelListener(new CancellationSignal.OnCancelListener(var6) {
                  final CancellableLocationListener val$listener;

                  {
                     this.val$listener = var1;
                  }

                  public void onCancel() {
                     this.val$listener.cancel();
                  }
               });
            }

            var6.startTimeout(30000L);
         }
      }
   }

   public static String getGnssHardwareModelName(LocationManager var0) {
      return VERSION.SDK_INT >= 28 ? var0.getGnssHardwareModelName() : null;
   }

   public static int getGnssYearOfHardware(LocationManager var0) {
      return VERSION.SDK_INT >= 28 ? var0.getGnssYearOfHardware() : 0;
   }

   public static boolean hasProvider(LocationManager var0, String var1) {
      if (VERSION.SDK_INT >= 31) {
         return var0.hasProvider(var1);
      } else {
         boolean var3 = var0.getAllProviders().contains(var1);
         boolean var2 = true;
         if (var3) {
            return true;
         } else {
            LocationProvider var5;
            try {
               var5 = var0.getProvider(var1);
            } catch (SecurityException var4) {
               return false;
            }

            if (var5 == null) {
               var2 = false;
            }

            return var2;
         }
      }
   }

   public static boolean isLocationEnabled(LocationManager var0) {
      if (VERSION.SDK_INT >= 28) {
         return var0.isLocationEnabled();
      } else {
         int var1 = VERSION.SDK_INT;
         boolean var3 = false;
         boolean var2 = false;
         if (var1 <= 19) {
            label72: {
               boolean var10001;
               try {
                  if (sContextField == null) {
                     Field var4 = LocationManager.class.getDeclaredField("mContext");
                     sContextField = var4;
                     var4.setAccessible(true);
                  }
               } catch (SecurityException | NoSuchFieldException | IllegalAccessException | ClassCastException var9) {
                  var10001 = false;
                  break label72;
               }

               Context var10;
               try {
                  var10 = (Context)sContextField.get(var0);
               } catch (SecurityException | NoSuchFieldException | IllegalAccessException | ClassCastException var8) {
                  var10001 = false;
                  break label72;
               }

               if (var10 != null) {
                  label75: {
                     label81: {
                        try {
                           if (VERSION.SDK_INT != 19) {
                              break label81;
                           }
                        } catch (SecurityException | NoSuchFieldException | IllegalAccessException | ClassCastException var7) {
                           var10001 = false;
                           break label75;
                        }

                        try {
                           if (Secure.getInt(var10.getContentResolver(), "location_mode", 0) == 0) {
                              return var2;
                           }
                        } catch (SecurityException | NoSuchFieldException | IllegalAccessException | ClassCastException var5) {
                           var10001 = false;
                           break label75;
                        }

                        var2 = true;
                        return var2;
                     }

                     try {
                        var2 = TextUtils.isEmpty(Secure.getString(var10.getContentResolver(), "location_providers_allowed"));
                     } catch (SecurityException | NoSuchFieldException | IllegalAccessException | ClassCastException var6) {
                        var10001 = false;
                        break label75;
                     }

                     return var2 ^ true;
                  }
               }
            }
         }

         if (!var0.isProviderEnabled("network")) {
            var2 = var3;
            if (!var0.isProviderEnabled("gps")) {
               return var2;
            }
         }

         var2 = true;
         return var2;
      }
   }

   // $FF: synthetic method
   static void lambda$getCurrentLocation$0(Consumer var0, Location var1) {
      var0.accept(var1);
   }

   // $FF: synthetic method
   static Boolean lambda$registerGnssStatusCallback$1(LocationManager var0, GpsStatusTransport var1) throws Exception {
      return var0.addGpsStatusListener(var1);
   }

   private static boolean registerGnssStatusCallback(LocationManager param0, Handler param1, Executor param2, GnssStatusCompat.Callback param3) {
      // $FF: Couldn't be decompiled
   }

   public static boolean registerGnssStatusCallback(LocationManager var0, GnssStatusCompat.Callback var1, Handler var2) {
      return VERSION.SDK_INT >= 30 ? registerGnssStatusCallback(var0, ExecutorCompat.create(var2), var1) : registerGnssStatusCallback(var0, (Executor)(new InlineHandlerExecutor(var2)), (GnssStatusCompat.Callback)var1);
   }

   public static boolean registerGnssStatusCallback(LocationManager var0, Executor var1, GnssStatusCompat.Callback var2) {
      if (VERSION.SDK_INT >= 30) {
         return registerGnssStatusCallback(var0, (Handler)null, var1, var2);
      } else {
         Looper var4 = Looper.myLooper();
         Looper var3 = var4;
         if (var4 == null) {
            var3 = Looper.getMainLooper();
         }

         return registerGnssStatusCallback(var0, new Handler(var3), var1, var2);
      }
   }

   public static void removeUpdates(LocationManager var0, LocationListenerCompat var1) {
      WeakHashMap var2 = sLocationListeners;
      synchronized(var2){}

      label440: {
         Throwable var10000;
         boolean var10001;
         label441: {
            List var3;
            try {
               var3 = (List)var2.remove(var1);
            } catch (Throwable var46) {
               var10000 = var46;
               var10001 = false;
               break label441;
            }

            if (var3 != null) {
               Iterator var48;
               try {
                  var48 = var3.iterator();
               } catch (Throwable var43) {
                  var10000 = var43;
                  var10001 = false;
                  break label441;
               }

               label429:
               while(true) {
                  while(true) {
                     LocationListenerTransport var4;
                     do {
                        try {
                           if (!var48.hasNext()) {
                              break label429;
                           }

                           var4 = (LocationListenerTransport)((WeakReference)var48.next()).get();
                        } catch (Throwable var44) {
                           var10000 = var44;
                           var10001 = false;
                           break label441;
                        }
                     } while(var4 == null);

                     try {
                        if (var4.unregister()) {
                           var0.removeUpdates(var4);
                        }
                     } catch (Throwable var45) {
                        var10000 = var45;
                        var10001 = false;
                        break label441;
                     }
                  }
               }
            }

            label411:
            try {
               break label440;
            } catch (Throwable var42) {
               var10000 = var42;
               var10001 = false;
               break label411;
            }
         }

         while(true) {
            Throwable var47 = var10000;

            try {
               throw var47;
            } catch (Throwable var41) {
               var10000 = var41;
               var10001 = false;
               continue;
            }
         }
      }

      var0.removeUpdates(var1);
   }

   public static void requestLocationUpdates(LocationManager var0, String var1, LocationRequestCompat var2, LocationListenerCompat var3, Looper var4) {
      if (VERSION.SDK_INT >= 31) {
         var0.requestLocationUpdates(var1, var2.toLocationRequest(), ExecutorCompat.create(new Handler(var4)), var3);
      } else {
         if (VERSION.SDK_INT >= 19) {
            label46: {
               try {
                  if (sRequestLocationUpdatesLooperMethod == null) {
                     Method var5 = LocationManager.class.getDeclaredMethod("requestLocationUpdates", LocationRequest.class, LocationListener.class, Looper.class);
                     sRequestLocationUpdatesLooperMethod = var5;
                     var5.setAccessible(true);
                  }
               } catch (InvocationTargetException | IllegalAccessException | UnsupportedOperationException | NoSuchMethodException var8) {
                  break label46;
               }

               boolean var10001;
               LocationRequest var9;
               try {
                  var9 = var2.toLocationRequest(var1);
               } catch (InvocationTargetException | IllegalAccessException | UnsupportedOperationException | NoSuchMethodException var7) {
                  var10001 = false;
                  break label46;
               }

               if (var9 != null) {
                  try {
                     sRequestLocationUpdatesLooperMethod.invoke(var0, var9, var3, var4);
                     return;
                  } catch (InvocationTargetException | IllegalAccessException | UnsupportedOperationException | NoSuchMethodException var6) {
                     var10001 = false;
                  }
               }
            }
         }

         var0.requestLocationUpdates(var1, var2.getIntervalMillis(), var2.getMinUpdateDistanceMeters(), var3, var4);
      }
   }

   public static void requestLocationUpdates(LocationManager param0, String param1, LocationRequestCompat param2, Executor param3, LocationListenerCompat param4) {
      // $FF: Couldn't be decompiled
   }

   public static void unregisterGnssStatusCallback(LocationManager var0, GnssStatusCompat.Callback var1) {
      SimpleArrayMap var2;
      Throwable var10000;
      boolean var10001;
      Throwable var159;
      if (VERSION.SDK_INT >= 30) {
         var2 = LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners;
         synchronized(var2){}

         label1377: {
            GnssStatusTransport var162;
            try {
               var162 = (GnssStatusTransport)LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners.remove(var1);
            } catch (Throwable var152) {
               var10000 = var152;
               var10001 = false;
               break label1377;
            }

            if (var162 != null) {
               try {
                  var0.unregisterGnssStatusCallback(var162);
               } catch (Throwable var151) {
                  var10000 = var151;
                  var10001 = false;
                  break label1377;
               }
            }

            label1336:
            try {
               return;
            } catch (Throwable var150) {
               var10000 = var150;
               var10001 = false;
               break label1336;
            }
         }

         while(true) {
            var159 = var10000;

            try {
               throw var159;
            } catch (Throwable var149) {
               var10000 = var149;
               var10001 = false;
               continue;
            }
         }
      } else if (VERSION.SDK_INT >= 24) {
         var2 = LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners;
         synchronized(var2){}

         label1379: {
            PreRGnssStatusTransport var161;
            try {
               var161 = (PreRGnssStatusTransport)LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners.remove(var1);
            } catch (Throwable var155) {
               var10000 = var155;
               var10001 = false;
               break label1379;
            }

            if (var161 != null) {
               try {
                  var161.unregister();
                  var0.unregisterGnssStatusCallback(var161);
               } catch (Throwable var154) {
                  var10000 = var154;
                  var10001 = false;
                  break label1379;
               }
            }

            label1349:
            try {
               return;
            } catch (Throwable var153) {
               var10000 = var153;
               var10001 = false;
               break label1349;
            }
         }

         while(true) {
            var159 = var10000;

            try {
               throw var159;
            } catch (Throwable var148) {
               var10000 = var148;
               var10001 = false;
               continue;
            }
         }
      } else {
         var2 = LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners;
         synchronized(var2){}

         label1380: {
            GpsStatusTransport var160;
            try {
               var160 = (GpsStatusTransport)LocationManagerCompat.GnssLazyLoader.sGnssStatusListeners.remove(var1);
            } catch (Throwable var158) {
               var10000 = var158;
               var10001 = false;
               break label1380;
            }

            if (var160 != null) {
               try {
                  var160.unregister();
                  var0.removeGpsStatusListener(var160);
               } catch (Throwable var157) {
                  var10000 = var157;
                  var10001 = false;
                  break label1380;
               }
            }

            label1361:
            try {
               return;
            } catch (Throwable var156) {
               var10000 = var156;
               var10001 = false;
               break label1361;
            }
         }

         while(true) {
            var159 = var10000;

            try {
               throw var159;
            } catch (Throwable var147) {
               var10000 = var147;
               var10001 = false;
               continue;
            }
         }
      }
   }

   private static class Api28Impl {
      static String getGnssHardwareModelName(LocationManager var0) {
         return var0.getGnssHardwareModelName();
      }

      static int getGnssYearOfHardware(LocationManager var0) {
         return var0.getGnssYearOfHardware();
      }

      static boolean isLocationEnabled(LocationManager var0) {
         return var0.isLocationEnabled();
      }
   }

   private static class Api30Impl {
      static void getCurrentLocation(LocationManager var0, String var1, CancellationSignal var2, Executor var3, Consumer var4) {
         android.os.CancellationSignal var5;
         if (var2 != null) {
            var5 = (android.os.CancellationSignal)var2.getCancellationSignalObject();
         } else {
            var5 = null;
         }

         Objects.requireNonNull(var4);
         var0.getCurrentLocation(var1, var5, var3, new LocationManagerCompat$Api30Impl$$ExternalSyntheticLambda0(var4));
      }
   }

   private static class Api31Impl {
      static boolean hasProvider(LocationManager var0, String var1) {
         return var0.hasProvider(var1);
      }

      static void requestLocationUpdates(LocationManager var0, String var1, LocationRequest var2, Executor var3, LocationListener var4) {
         var0.requestLocationUpdates(var1, var2, var3, var4);
      }
   }

   private static final class CancellableLocationListener implements LocationListener {
      private Consumer mConsumer;
      private final Executor mExecutor;
      private final LocationManager mLocationManager;
      private final Handler mTimeoutHandler;
      Runnable mTimeoutRunnable;
      private boolean mTriggered;

      CancellableLocationListener(LocationManager var1, Executor var2, Consumer var3) {
         this.mLocationManager = var1;
         this.mExecutor = var2;
         this.mTimeoutHandler = new Handler(Looper.getMainLooper());
         this.mConsumer = var3;
      }

      private void cleanup() {
         this.mConsumer = null;
         this.mLocationManager.removeUpdates(this);
         Runnable var1 = this.mTimeoutRunnable;
         if (var1 != null) {
            this.mTimeoutHandler.removeCallbacks(var1);
            this.mTimeoutRunnable = null;
         }

      }

      // $FF: synthetic method
      static void lambda$onLocationChanged$0(Consumer var0, Location var1) {
         var0.accept(var1);
      }

      public void cancel() {
         synchronized(this){}

         Throwable var10000;
         boolean var10001;
         label137: {
            try {
               if (this.mTriggered) {
                  return;
               }
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label137;
            }

            try {
               this.mTriggered = true;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break label137;
            }

            this.cleanup();
            return;
         }

         while(true) {
            Throwable var1 = var10000;

            try {
               throw var1;
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               continue;
            }
         }
      }

      public void onLocationChanged(Location var1) {
         synchronized(this){}

         Throwable var10000;
         boolean var10001;
         label137: {
            try {
               if (this.mTriggered) {
                  return;
               }
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label137;
            }

            try {
               this.mTriggered = true;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               break label137;
            }

            Consumer var2 = this.mConsumer;
            this.mExecutor.execute(new LocationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda0(var2, var1));
            this.cleanup();
            return;
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
      }

      public void onProviderDisabled(String var1) {
         Location var2 = (Location)null;
         this.onLocationChanged((Location)null);
      }

      public void onProviderEnabled(String var1) {
      }

      public void onStatusChanged(String var1, int var2, Bundle var3) {
      }

      public void startTimeout(long var1) {
         synchronized(this){}

         Throwable var10000;
         boolean var10001;
         label123: {
            try {
               if (this.mTriggered) {
                  return;
               }
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label123;
            }

            label117:
            try {
               Runnable var16 = new Runnable(this) {
                  final CancellableLocationListener this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void run() {
                     this.this$0.mTimeoutRunnable = null;
                     CancellableLocationListener var1 = this.this$0;
                     Location var2 = (Location)null;
                     var1.onLocationChanged((Location)null);
                  }
               };
               this.mTimeoutRunnable = var16;
               this.mTimeoutHandler.postDelayed(var16, var1);
               return;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label117;
            }
         }

         while(true) {
            Throwable var3 = var10000;

            try {
               throw var3;
            } catch (Throwable var13) {
               var10000 = var13;
               var10001 = false;
               continue;
            }
         }
      }
   }

   private static class GnssLazyLoader {
      static final SimpleArrayMap sGnssStatusListeners = new SimpleArrayMap();
   }

   private static class GnssStatusTransport extends GnssStatus.Callback {
      final GnssStatusCompat.Callback mCallback;

      GnssStatusTransport(GnssStatusCompat.Callback var1) {
         boolean var2;
         if (var1 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Preconditions.checkArgument(var2, "invalid null callback");
         this.mCallback = var1;
      }

      public void onFirstFix(int var1) {
         this.mCallback.onFirstFix(var1);
      }

      public void onSatelliteStatusChanged(GnssStatus var1) {
         this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(var1));
      }

      public void onStarted() {
         this.mCallback.onStarted();
      }

      public void onStopped() {
         this.mCallback.onStopped();
      }
   }

   private static class GpsStatusTransport implements GpsStatus.Listener {
      final GnssStatusCompat.Callback mCallback;
      volatile Executor mExecutor;
      private final LocationManager mLocationManager;

      GpsStatusTransport(LocationManager var1, GnssStatusCompat.Callback var2) {
         boolean var3;
         if (var2 != null) {
            var3 = true;
         } else {
            var3 = false;
         }

         Preconditions.checkArgument(var3, "invalid null callback");
         this.mLocationManager = var1;
         this.mCallback = var2;
      }

      // $FF: synthetic method
      void lambda$onGpsStatusChanged$0$androidx_core_location_LocationManagerCompat$GpsStatusTransport(Executor var1) {
         if (this.mExecutor == var1) {
            this.mCallback.onStarted();
         }
      }

      // $FF: synthetic method
      void lambda$onGpsStatusChanged$1$androidx_core_location_LocationManagerCompat$GpsStatusTransport(Executor var1) {
         if (this.mExecutor == var1) {
            this.mCallback.onStopped();
         }
      }

      // $FF: synthetic method
      void lambda$onGpsStatusChanged$2$androidx_core_location_LocationManagerCompat$GpsStatusTransport(Executor var1, int var2) {
         if (this.mExecutor == var1) {
            this.mCallback.onFirstFix(var2);
         }
      }

      // $FF: synthetic method
      void lambda$onGpsStatusChanged$3$androidx_core_location_LocationManagerCompat$GpsStatusTransport(Executor var1, GnssStatusCompat var2) {
         if (this.mExecutor == var1) {
            this.mCallback.onSatelliteStatusChanged(var2);
         }
      }

      public void onGpsStatusChanged(int var1) {
         Executor var2 = this.mExecutor;
         if (var2 != null) {
            if (var1 != 1) {
               if (var1 != 2) {
                  GpsStatus var3;
                  if (var1 != 3) {
                     if (var1 == 4) {
                        var3 = this.mLocationManager.getGpsStatus((GpsStatus)null);
                        if (var3 != null) {
                           var2.execute(new LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda3(this, var2, GnssStatusCompat.wrap(var3)));
                        }
                     }
                  } else {
                     var3 = this.mLocationManager.getGpsStatus((GpsStatus)null);
                     if (var3 != null) {
                        var2.execute(new LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda2(this, var2, var3.getTimeToFirstFix()));
                     }
                  }
               } else {
                  var2.execute(new LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda1(this, var2));
               }
            } else {
               var2.execute(new LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda0(this, var2));
            }

         }
      }

      public void register(Executor var1) {
         boolean var2;
         if (this.mExecutor == null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Preconditions.checkState(var2);
         this.mExecutor = var1;
      }

      public void unregister() {
         this.mExecutor = null;
      }
   }

   private static final class InlineHandlerExecutor implements Executor {
      private final Handler mHandler;

      InlineHandlerExecutor(Handler var1) {
         this.mHandler = (Handler)Preconditions.checkNotNull(var1);
      }

      public void execute(Runnable var1) {
         if (Looper.myLooper() == this.mHandler.getLooper()) {
            var1.run();
         } else if (!this.mHandler.post((Runnable)Preconditions.checkNotNull(var1))) {
            throw new RejectedExecutionException(this.mHandler + " is shutting down");
         }

      }
   }

   private static class LocationListenerTransport implements LocationListener {
      final Executor mExecutor;
      volatile LocationListenerCompat mListener;

      LocationListenerTransport(LocationListenerCompat var1, Executor var2) {
         this.mListener = (LocationListenerCompat)ObjectsCompat.requireNonNull(var1, "invalid null listener");
         this.mExecutor = var2;
      }

      // $FF: synthetic method
      static boolean lambda$register$0(WeakReference var0) {
         boolean var1;
         if (var0.get() == null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      // $FF: synthetic method
      static boolean lambda$unregister$1(WeakReference var0) {
         boolean var1;
         if (var0.get() == null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      // $FF: synthetic method
      void lambda$onFlushComplete$4$androidx_core_location_LocationManagerCompat$LocationListenerTransport(LocationListenerCompat var1, int var2) {
         if (this.mListener == var1) {
            var1.onFlushComplete(var2);
         }
      }

      // $FF: synthetic method
      void lambda$onLocationChanged$2$androidx_core_location_LocationManagerCompat$LocationListenerTransport(LocationListenerCompat var1, Location var2) {
         if (this.mListener == var1) {
            var1.onLocationChanged(var2);
         }
      }

      // $FF: synthetic method
      void lambda$onLocationChanged$3$androidx_core_location_LocationManagerCompat$LocationListenerTransport(LocationListenerCompat var1, List var2) {
         if (this.mListener == var1) {
            var1.onLocationChanged(var2);
         }
      }

      // $FF: synthetic method
      void lambda$onProviderDisabled$7$androidx_core_location_LocationManagerCompat$LocationListenerTransport(LocationListenerCompat var1, String var2) {
         if (this.mListener == var1) {
            var1.onProviderDisabled(var2);
         }
      }

      // $FF: synthetic method
      void lambda$onProviderEnabled$6$androidx_core_location_LocationManagerCompat$LocationListenerTransport(LocationListenerCompat var1, String var2) {
         if (this.mListener == var1) {
            var1.onProviderEnabled(var2);
         }
      }

      // $FF: synthetic method
      void lambda$onStatusChanged$5$androidx_core_location_LocationManagerCompat$LocationListenerTransport(LocationListenerCompat var1, String var2, int var3, Bundle var4) {
         if (this.mListener == var1) {
            var1.onStatusChanged(var2, var3, var4);
         }
      }

      public void onFlushComplete(int var1) {
         LocationListenerCompat var2 = this.mListener;
         if (var2 != null) {
            this.mExecutor.execute(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda2(this, var2, var1));
         }
      }

      public void onLocationChanged(Location var1) {
         LocationListenerCompat var2 = this.mListener;
         if (var2 != null) {
            this.mExecutor.execute(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda5(this, var2, var1));
         }
      }

      public void onLocationChanged(List var1) {
         LocationListenerCompat var2 = this.mListener;
         if (var2 != null) {
            this.mExecutor.execute(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda3(this, var2, var1));
         }
      }

      public void onProviderDisabled(String var1) {
         LocationListenerCompat var2 = this.mListener;
         if (var2 != null) {
            this.mExecutor.execute(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda4(this, var2, var1));
         }
      }

      public void onProviderEnabled(String var1) {
         LocationListenerCompat var2 = this.mListener;
         if (var2 != null) {
            this.mExecutor.execute(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda0(this, var2, var1));
         }
      }

      public void onStatusChanged(String var1, int var2, Bundle var3) {
         LocationListenerCompat var4 = this.mListener;
         if (var4 != null) {
            this.mExecutor.execute(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda6(this, var4, var1, var2, var3));
         }
      }

      public void register() {
         List var2 = (List)LocationManagerCompat.sLocationListeners.get(this.mListener);
         Object var1;
         if (var2 == null) {
            var1 = new ArrayList(1);
            LocationManagerCompat.sLocationListeners.put(this.mListener, var1);
         } else if (VERSION.SDK_INT >= 24) {
            var2.removeIf(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda7());
            var1 = var2;
         } else {
            Iterator var3 = var2.iterator();

            while(true) {
               var1 = var2;
               if (!var3.hasNext()) {
                  break;
               }

               if (((WeakReference)var3.next()).get() == null) {
                  var3.remove();
               }
            }
         }

         ((List)var1).add(new WeakReference(this));
      }

      public boolean unregister() {
         LocationListenerCompat var1 = this.mListener;
         if (var1 == null) {
            return false;
         } else {
            this.mListener = null;
            List var3 = (List)LocationManagerCompat.sLocationListeners.get(var1);
            if (var3 != null) {
               if (VERSION.SDK_INT >= 24) {
                  var3.removeIf(new LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda1());
               } else {
                  Iterator var2 = var3.iterator();

                  while(var2.hasNext()) {
                     if (((WeakReference)var2.next()).get() == null) {
                        var2.remove();
                     }
                  }
               }

               if (var3.isEmpty()) {
                  LocationManagerCompat.sLocationListeners.remove(var1);
               }
            }

            return true;
         }
      }
   }

   private static class PreRGnssStatusTransport extends GnssStatus.Callback {
      final GnssStatusCompat.Callback mCallback;
      volatile Executor mExecutor;

      PreRGnssStatusTransport(GnssStatusCompat.Callback var1) {
         boolean var2;
         if (var1 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Preconditions.checkArgument(var2, "invalid null callback");
         this.mCallback = var1;
      }

      // $FF: synthetic method
      void lambda$onFirstFix$2$androidx_core_location_LocationManagerCompat$PreRGnssStatusTransport(Executor var1, int var2) {
         if (this.mExecutor == var1) {
            this.mCallback.onFirstFix(var2);
         }
      }

      // $FF: synthetic method
      void lambda$onSatelliteStatusChanged$3$androidx_core_location_LocationManagerCompat$PreRGnssStatusTransport(Executor var1, GnssStatus var2) {
         if (this.mExecutor == var1) {
            this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(var2));
         }
      }

      // $FF: synthetic method
      void lambda$onStarted$0$androidx_core_location_LocationManagerCompat$PreRGnssStatusTransport(Executor var1) {
         if (this.mExecutor == var1) {
            this.mCallback.onStarted();
         }
      }

      // $FF: synthetic method
      void lambda$onStopped$1$androidx_core_location_LocationManagerCompat$PreRGnssStatusTransport(Executor var1) {
         if (this.mExecutor == var1) {
            this.mCallback.onStopped();
         }
      }

      public void onFirstFix(int var1) {
         Executor var2 = this.mExecutor;
         if (var2 != null) {
            var2.execute(new LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda2(this, var2, var1));
         }
      }

      public void onSatelliteStatusChanged(GnssStatus var1) {
         Executor var2 = this.mExecutor;
         if (var2 != null) {
            var2.execute(new LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda1(this, var2, var1));
         }
      }

      public void onStarted() {
         Executor var1 = this.mExecutor;
         if (var1 != null) {
            var1.execute(new LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda0(this, var1));
         }
      }

      public void onStopped() {
         Executor var1 = this.mExecutor;
         if (var1 != null) {
            var1.execute(new LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda3(this, var1));
         }
      }

      public void register(Executor var1) {
         boolean var3 = true;
         boolean var2;
         if (var1 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Preconditions.checkArgument(var2, "invalid null executor");
         if (this.mExecutor == null) {
            var2 = var3;
         } else {
            var2 = false;
         }

         Preconditions.checkState(var2);
         this.mExecutor = var1;
      }

      public void unregister() {
         this.mExecutor = null;
      }
   }
}
