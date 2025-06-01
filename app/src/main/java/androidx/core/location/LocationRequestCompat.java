package androidx.core.location;

import android.location.LocationRequest;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;
import androidx.core.util.TimeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class LocationRequestCompat {
   private static final long IMPLICIT_MIN_UPDATE_INTERVAL = -1L;
   public static final long PASSIVE_INTERVAL = Long.MAX_VALUE;
   public static final int QUALITY_BALANCED_POWER_ACCURACY = 102;
   public static final int QUALITY_HIGH_ACCURACY = 100;
   public static final int QUALITY_LOW_POWER = 104;
   private static Method sCreateFromDeprecatedProviderMethod;
   private static Method sSetExpireInMethod;
   private static Method sSetFastestIntervalMethod;
   private static Method sSetNumUpdatesMethod;
   private static Method sSetQualityMethod;
   final long mDurationMillis;
   final long mIntervalMillis;
   final long mMaxUpdateDelayMillis;
   final int mMaxUpdates;
   final float mMinUpdateDistanceMeters;
   final long mMinUpdateIntervalMillis;
   final int mQuality;

   LocationRequestCompat(long var1, int var3, long var4, int var6, long var7, float var9, long var10) {
      this.mIntervalMillis = var1;
      this.mQuality = var3;
      this.mMinUpdateIntervalMillis = var7;
      this.mDurationMillis = var4;
      this.mMaxUpdates = var6;
      this.mMinUpdateDistanceMeters = var9;
      this.mMaxUpdateDelayMillis = var10;
   }

   public boolean equals(Object var1) {
      boolean var2 = true;
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof LocationRequestCompat)) {
         return false;
      } else {
         LocationRequestCompat var3 = (LocationRequestCompat)var1;
         if (this.mQuality != var3.mQuality || this.mIntervalMillis != var3.mIntervalMillis || this.mMinUpdateIntervalMillis != var3.mMinUpdateIntervalMillis || this.mDurationMillis != var3.mDurationMillis || this.mMaxUpdates != var3.mMaxUpdates || Float.compare(var3.mMinUpdateDistanceMeters, this.mMinUpdateDistanceMeters) != 0 || this.mMaxUpdateDelayMillis != var3.mMaxUpdateDelayMillis) {
            var2 = false;
         }

         return var2;
      }
   }

   public long getDurationMillis() {
      return this.mDurationMillis;
   }

   public long getIntervalMillis() {
      return this.mIntervalMillis;
   }

   public long getMaxUpdateDelayMillis() {
      return this.mMaxUpdateDelayMillis;
   }

   public int getMaxUpdates() {
      return this.mMaxUpdates;
   }

   public float getMinUpdateDistanceMeters() {
      return this.mMinUpdateDistanceMeters;
   }

   public long getMinUpdateIntervalMillis() {
      long var3 = this.mMinUpdateIntervalMillis;
      long var1 = var3;
      if (var3 == -1L) {
         var1 = this.mIntervalMillis;
      }

      return var1;
   }

   public int getQuality() {
      return this.mQuality;
   }

   public int hashCode() {
      int var1 = this.mQuality;
      long var3 = this.mIntervalMillis;
      int var2 = (int)(var3 ^ var3 >>> 32);
      var3 = this.mMinUpdateIntervalMillis;
      return (var1 * 31 + var2) * 31 + (int)(var3 ^ var3 >>> 32);
   }

   public LocationRequest toLocationRequest() {
      return (new LocationRequest.Builder(this.mIntervalMillis)).setQuality(this.mQuality).setMinUpdateIntervalMillis(this.mMinUpdateIntervalMillis).setDurationMillis(this.mDurationMillis).setMaxUpdates(this.mMaxUpdates).setMinUpdateDistanceMeters(this.mMinUpdateDistanceMeters).setMaxUpdateDelayMillis(this.mMaxUpdateDelayMillis).build();
   }

   public LocationRequest toLocationRequest(String var1) {
      if (VERSION.SDK_INT >= 31) {
         return this.toLocationRequest();
      } else {
         boolean var10001;
         Method var2;
         try {
            if (sCreateFromDeprecatedProviderMethod == null) {
               var2 = LocationRequest.class.getDeclaredMethod("createFromDeprecatedProvider", String.class, Long.TYPE, Float.TYPE, Boolean.TYPE);
               sCreateFromDeprecatedProviderMethod = var2;
               var2.setAccessible(true);
            }
         } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var12) {
            var10001 = false;
            return null;
         }

         LocationRequest var13;
         try {
            var13 = (LocationRequest)sCreateFromDeprecatedProviderMethod.invoke((Object)null, var1, this.mIntervalMillis, this.mMinUpdateDistanceMeters, false);
         } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var11) {
            var10001 = false;
            return null;
         }

         if (var13 == null) {
            return null;
         } else {
            try {
               if (sSetQualityMethod == null) {
                  var2 = LocationRequest.class.getDeclaredMethod("setQuality", Integer.TYPE);
                  sSetQualityMethod = var2;
                  var2.setAccessible(true);
               }
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var10) {
               var10001 = false;
               return null;
            }

            label86: {
               try {
                  sSetQualityMethod.invoke(var13, this.mQuality);
                  if (this.getMinUpdateIntervalMillis() == this.mIntervalMillis) {
                     break label86;
                  }

                  if (sSetFastestIntervalMethod == null) {
                     var2 = LocationRequest.class.getDeclaredMethod("setFastestInterval", Long.TYPE);
                     sSetFastestIntervalMethod = var2;
                     var2.setAccessible(true);
                  }
               } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var9) {
                  var10001 = false;
                  return null;
               }

               try {
                  sSetFastestIntervalMethod.invoke(var13, this.mMinUpdateIntervalMillis);
               } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var8) {
                  var10001 = false;
                  return null;
               }
            }

            label78: {
               try {
                  if (this.mMaxUpdates >= Integer.MAX_VALUE) {
                     break label78;
                  }

                  if (sSetNumUpdatesMethod == null) {
                     var2 = LocationRequest.class.getDeclaredMethod("setNumUpdates", Integer.TYPE);
                     sSetNumUpdatesMethod = var2;
                     var2.setAccessible(true);
                  }
               } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var7) {
                  var10001 = false;
                  return null;
               }

               try {
                  sSetNumUpdatesMethod.invoke(var13, this.mMaxUpdates);
               } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var6) {
                  var10001 = false;
                  return null;
               }
            }

            label70: {
               try {
                  if (this.mDurationMillis >= Long.MAX_VALUE) {
                     break label70;
                  }

                  if (sSetExpireInMethod == null) {
                     var2 = LocationRequest.class.getDeclaredMethod("setExpireIn", Long.TYPE);
                     sSetExpireInMethod = var2;
                     var2.setAccessible(true);
                  }
               } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var5) {
                  var10001 = false;
                  return null;
               }

               try {
                  sSetExpireInMethod.invoke(var13, this.mDurationMillis);
               } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var4) {
                  var10001 = false;
                  return null;
               }
            }

            try {
               return var13;
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var3) {
               var10001 = false;
               return null;
            }
         }
      }
   }

   public String toString() {
      StringBuilder var4 = new StringBuilder();
      var4.append("Request[");
      if (this.mIntervalMillis != Long.MAX_VALUE) {
         var4.append("@");
         TimeUtils.formatDuration(this.mIntervalMillis, var4);
         int var1 = this.mQuality;
         if (var1 != 100) {
            if (var1 != 102) {
               if (var1 == 104) {
                  var4.append(" LOW_POWER");
               }
            } else {
               var4.append(" BALANCED");
            }
         } else {
            var4.append(" HIGH_ACCURACY");
         }
      } else {
         var4.append("PASSIVE");
      }

      if (this.mDurationMillis != Long.MAX_VALUE) {
         var4.append(", duration=");
         TimeUtils.formatDuration(this.mDurationMillis, var4);
      }

      if (this.mMaxUpdates != Integer.MAX_VALUE) {
         var4.append(", maxUpdates=").append(this.mMaxUpdates);
      }

      long var2 = this.mMinUpdateIntervalMillis;
      if (var2 != -1L && var2 < this.mIntervalMillis) {
         var4.append(", minUpdateInterval=");
         TimeUtils.formatDuration(this.mMinUpdateIntervalMillis, var4);
      }

      if ((double)this.mMinUpdateDistanceMeters > 0.0) {
         var4.append(", minUpdateDistance=").append(this.mMinUpdateDistanceMeters);
      }

      if (this.mMaxUpdateDelayMillis / 2L > this.mIntervalMillis) {
         var4.append(", maxUpdateDelay=");
         TimeUtils.formatDuration(this.mMaxUpdateDelayMillis, var4);
      }

      var4.append(']');
      return var4.toString();
   }

   public static final class Builder {
      private long mDurationMillis;
      private long mIntervalMillis;
      private long mMaxUpdateDelayMillis;
      private int mMaxUpdates;
      private float mMinUpdateDistanceMeters;
      private long mMinUpdateIntervalMillis;
      private int mQuality;

      public Builder(long var1) {
         this.setIntervalMillis(var1);
         this.mQuality = 102;
         this.mDurationMillis = Long.MAX_VALUE;
         this.mMaxUpdates = Integer.MAX_VALUE;
         this.mMinUpdateIntervalMillis = -1L;
         this.mMinUpdateDistanceMeters = 0.0F;
         this.mMaxUpdateDelayMillis = 0L;
      }

      public Builder(LocationRequestCompat var1) {
         this.mIntervalMillis = var1.mIntervalMillis;
         this.mQuality = var1.mQuality;
         this.mDurationMillis = var1.mDurationMillis;
         this.mMaxUpdates = var1.mMaxUpdates;
         this.mMinUpdateIntervalMillis = var1.mMinUpdateIntervalMillis;
         this.mMinUpdateDistanceMeters = var1.mMinUpdateDistanceMeters;
         this.mMaxUpdateDelayMillis = var1.mMaxUpdateDelayMillis;
      }

      public LocationRequestCompat build() {
         boolean var3;
         if (this.mIntervalMillis == Long.MAX_VALUE && this.mMinUpdateIntervalMillis == -1L) {
            var3 = false;
         } else {
            var3 = true;
         }

         Preconditions.checkState(var3, "passive location requests must have an explicit minimum update interval");
         long var1 = this.mIntervalMillis;
         return new LocationRequestCompat(var1, this.mQuality, this.mDurationMillis, this.mMaxUpdates, Math.min(this.mMinUpdateIntervalMillis, var1), this.mMinUpdateDistanceMeters, this.mMaxUpdateDelayMillis);
      }

      public Builder clearMinUpdateIntervalMillis() {
         this.mMinUpdateIntervalMillis = -1L;
         return this;
      }

      public Builder setDurationMillis(long var1) {
         this.mDurationMillis = Preconditions.checkArgumentInRange(var1, 1L, Long.MAX_VALUE, "durationMillis");
         return this;
      }

      public Builder setIntervalMillis(long var1) {
         this.mIntervalMillis = Preconditions.checkArgumentInRange(var1, 0L, Long.MAX_VALUE, "intervalMillis");
         return this;
      }

      public Builder setMaxUpdateDelayMillis(long var1) {
         this.mMaxUpdateDelayMillis = var1;
         this.mMaxUpdateDelayMillis = Preconditions.checkArgumentInRange(var1, 0L, Long.MAX_VALUE, "maxUpdateDelayMillis");
         return this;
      }

      public Builder setMaxUpdates(int var1) {
         this.mMaxUpdates = Preconditions.checkArgumentInRange(var1, 1, Integer.MAX_VALUE, "maxUpdates");
         return this;
      }

      public Builder setMinUpdateDistanceMeters(float var1) {
         this.mMinUpdateDistanceMeters = var1;
         this.mMinUpdateDistanceMeters = Preconditions.checkArgumentInRange(var1, 0.0F, Float.MAX_VALUE, "minUpdateDistanceMeters");
         return this;
      }

      public Builder setMinUpdateIntervalMillis(long var1) {
         this.mMinUpdateIntervalMillis = Preconditions.checkArgumentInRange(var1, 0L, Long.MAX_VALUE, "minUpdateIntervalMillis");
         return this;
      }

      public Builder setQuality(int var1) {
         boolean var2;
         if (var1 != 104 && var1 != 102 && var1 != 100) {
            var2 = false;
         } else {
            var2 = true;
         }

         Preconditions.checkArgument(var2, "quality must be a defined QUALITY constant, not %d", var1);
         this.mQuality = var1;
         return this;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Quality {
   }
}
