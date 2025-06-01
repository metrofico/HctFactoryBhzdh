package androidx.core.location;

import android.location.GnssStatus;
import android.location.GpsStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class GnssStatusCompat {
   public static final int CONSTELLATION_BEIDOU = 5;
   public static final int CONSTELLATION_GALILEO = 6;
   public static final int CONSTELLATION_GLONASS = 3;
   public static final int CONSTELLATION_GPS = 1;
   public static final int CONSTELLATION_IRNSS = 7;
   public static final int CONSTELLATION_QZSS = 4;
   public static final int CONSTELLATION_SBAS = 2;
   public static final int CONSTELLATION_UNKNOWN = 0;

   GnssStatusCompat() {
   }

   public static GnssStatusCompat wrap(GnssStatus var0) {
      return new GnssStatusWrapper(var0);
   }

   public static GnssStatusCompat wrap(GpsStatus var0) {
      return new GpsStatusWrapper(var0);
   }

   public abstract float getAzimuthDegrees(int var1);

   public abstract float getBasebandCn0DbHz(int var1);

   public abstract float getCarrierFrequencyHz(int var1);

   public abstract float getCn0DbHz(int var1);

   public abstract int getConstellationType(int var1);

   public abstract float getElevationDegrees(int var1);

   public abstract int getSatelliteCount();

   public abstract int getSvid(int var1);

   public abstract boolean hasAlmanacData(int var1);

   public abstract boolean hasBasebandCn0DbHz(int var1);

   public abstract boolean hasCarrierFrequencyHz(int var1);

   public abstract boolean hasEphemerisData(int var1);

   public abstract boolean usedInFix(int var1);

   public abstract static class Callback {
      public void onFirstFix(int var1) {
      }

      public void onSatelliteStatusChanged(GnssStatusCompat var1) {
      }

      public void onStarted() {
      }

      public void onStopped() {
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface ConstellationType {
   }
}
