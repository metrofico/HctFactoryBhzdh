package androidx.core.location;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;
import java.util.Iterator;

class GpsStatusWrapper extends GnssStatusCompat {
   private static final int BEIDOU_PRN_COUNT = 35;
   private static final int BEIDOU_PRN_OFFSET = 200;
   private static final int GLONASS_PRN_COUNT = 24;
   private static final int GLONASS_PRN_OFFSET = 64;
   private static final int GPS_PRN_COUNT = 32;
   private static final int GPS_PRN_OFFSET = 0;
   private static final int QZSS_SVID_MAX = 200;
   private static final int QZSS_SVID_MIN = 193;
   private static final int SBAS_PRN_MAX = 64;
   private static final int SBAS_PRN_MIN = 33;
   private static final int SBAS_PRN_OFFSET = -87;
   private Iterator mCachedIterator;
   private int mCachedIteratorPosition;
   private GpsSatellite mCachedSatellite;
   private int mCachedSatelliteCount;
   private final GpsStatus mWrapped;

   GpsStatusWrapper(GpsStatus var1) {
      var1 = (GpsStatus)Preconditions.checkNotNull(var1);
      this.mWrapped = var1;
      this.mCachedSatelliteCount = -1;
      this.mCachedIterator = var1.getSatellites().iterator();
      this.mCachedIteratorPosition = -1;
      this.mCachedSatellite = null;
   }

   private static int getConstellationFromPrn(int var0) {
      if (var0 > 0 && var0 <= 32) {
         return 1;
      } else if (var0 >= 33 && var0 <= 64) {
         return 2;
      } else if (var0 > 64 && var0 <= 88) {
         return 3;
      } else if (var0 > 200 && var0 <= 235) {
         return 5;
      } else {
         return var0 >= 193 && var0 <= 200 ? 4 : 0;
      }
   }

   private GpsSatellite getSatellite(int var1) {
      GpsStatus var3 = this.mWrapped;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label437: {
         try {
            if (var1 < this.mCachedIteratorPosition) {
               this.mCachedIterator = this.mWrapped.getSatellites().iterator();
               this.mCachedIteratorPosition = -1;
            }
         } catch (Throwable var44) {
            var10000 = var44;
            var10001 = false;
            break label437;
         }

         while(true) {
            int var2;
            try {
               var2 = this.mCachedIteratorPosition;
            } catch (Throwable var43) {
               var10000 = var43;
               var10001 = false;
               break;
            }

            if (var2 < var1) {
               label447: {
                  try {
                     this.mCachedIteratorPosition = var2 + 1;
                     if (!this.mCachedIterator.hasNext()) {
                        this.mCachedSatellite = null;
                        break label447;
                     }
                  } catch (Throwable var46) {
                     var10000 = var46;
                     var10001 = false;
                     break;
                  }

                  try {
                     this.mCachedSatellite = (GpsSatellite)this.mCachedIterator.next();
                     continue;
                  } catch (Throwable var45) {
                     var10000 = var45;
                     var10001 = false;
                     break;
                  }
               }
            }

            try {
               GpsSatellite var4 = this.mCachedSatellite;
               return (GpsSatellite)Preconditions.checkNotNull(var4);
            } catch (Throwable var42) {
               var10000 = var42;
               var10001 = false;
               break;
            }
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

   private static int getSvidFromPrn(int var0) {
      int var1 = getConstellationFromPrn(var0);
      if (var1 != 2) {
         if (var1 != 3) {
            if (var1 == 5) {
               var0 -= 200;
            }
         } else {
            var0 -= 64;
         }
      } else {
         var0 += 87;
      }

      return var0;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof GpsStatusWrapper)) {
         return false;
      } else {
         GpsStatusWrapper var2 = (GpsStatusWrapper)var1;
         return this.mWrapped.equals(var2.mWrapped);
      }
   }

   public float getAzimuthDegrees(int var1) {
      return this.getSatellite(var1).getAzimuth();
   }

   public float getBasebandCn0DbHz(int var1) {
      throw new UnsupportedOperationException();
   }

   public float getCarrierFrequencyHz(int var1) {
      throw new UnsupportedOperationException();
   }

   public float getCn0DbHz(int var1) {
      return this.getSatellite(var1).getSnr();
   }

   public int getConstellationType(int var1) {
      return VERSION.SDK_INT < 24 ? 1 : getConstellationFromPrn(this.getSatellite(var1).getPrn());
   }

   public float getElevationDegrees(int var1) {
      return this.getSatellite(var1).getElevation();
   }

   public int getSatelliteCount() {
      GpsStatus var2 = this.mWrapped;
      synchronized(var2){}

      Throwable var10000;
      boolean var10001;
      label286: {
         label285: {
            Iterator var3;
            try {
               if (this.mCachedSatelliteCount != -1) {
                  break label285;
               }

               var3 = this.mWrapped.getSatellites().iterator();
            } catch (Throwable var33) {
               var10000 = var33;
               var10001 = false;
               break label286;
            }

            while(true) {
               try {
                  if (var3.hasNext()) {
                     GpsSatellite var4 = (GpsSatellite)var3.next();
                     ++this.mCachedSatelliteCount;
                     continue;
                  }
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label286;
               }

               try {
                  ++this.mCachedSatelliteCount;
                  break;
               } catch (Throwable var32) {
                  var10000 = var32;
                  var10001 = false;
                  break label286;
               }
            }
         }

         label269:
         try {
            int var1 = this.mCachedSatelliteCount;
            return var1;
         } catch (Throwable var31) {
            var10000 = var31;
            var10001 = false;
            break label269;
         }
      }

      while(true) {
         Throwable var35 = var10000;

         try {
            throw var35;
         } catch (Throwable var30) {
            var10000 = var30;
            var10001 = false;
            continue;
         }
      }
   }

   public int getSvid(int var1) {
      return VERSION.SDK_INT < 24 ? this.getSatellite(var1).getPrn() : getSvidFromPrn(this.getSatellite(var1).getPrn());
   }

   public boolean hasAlmanacData(int var1) {
      return this.getSatellite(var1).hasAlmanac();
   }

   public boolean hasBasebandCn0DbHz(int var1) {
      return false;
   }

   public boolean hasCarrierFrequencyHz(int var1) {
      return false;
   }

   public boolean hasEphemerisData(int var1) {
      return this.getSatellite(var1).hasEphemeris();
   }

   public int hashCode() {
      return this.mWrapped.hashCode();
   }

   public boolean usedInFix(int var1) {
      return this.getSatellite(var1).usedInFix();
   }
}
