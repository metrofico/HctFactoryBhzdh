package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;

class GnssStatusWrapper extends GnssStatusCompat {
   private final GnssStatus mWrapped;

   GnssStatusWrapper(GnssStatus var1) {
      this.mWrapped = (GnssStatus)Preconditions.checkNotNull(var1);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof GnssStatusWrapper)) {
         return false;
      } else {
         GnssStatusWrapper var2 = (GnssStatusWrapper)var1;
         return this.mWrapped.equals(var2.mWrapped);
      }
   }

   public float getAzimuthDegrees(int var1) {
      return this.mWrapped.getAzimuthDegrees(var1);
   }

   public float getBasebandCn0DbHz(int var1) {
      if (VERSION.SDK_INT >= 30) {
         return this.mWrapped.getBasebandCn0DbHz(var1);
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public float getCarrierFrequencyHz(int var1) {
      if (VERSION.SDK_INT >= 26) {
         return this.mWrapped.getCarrierFrequencyHz(var1);
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public float getCn0DbHz(int var1) {
      return this.mWrapped.getCn0DbHz(var1);
   }

   public int getConstellationType(int var1) {
      return this.mWrapped.getConstellationType(var1);
   }

   public float getElevationDegrees(int var1) {
      return this.mWrapped.getElevationDegrees(var1);
   }

   public int getSatelliteCount() {
      return this.mWrapped.getSatelliteCount();
   }

   public int getSvid(int var1) {
      return this.mWrapped.getSvid(var1);
   }

   public boolean hasAlmanacData(int var1) {
      return this.mWrapped.hasAlmanacData(var1);
   }

   public boolean hasBasebandCn0DbHz(int var1) {
      return VERSION.SDK_INT >= 30 ? this.mWrapped.hasBasebandCn0DbHz(var1) : false;
   }

   public boolean hasCarrierFrequencyHz(int var1) {
      return VERSION.SDK_INT >= 26 ? this.mWrapped.hasCarrierFrequencyHz(var1) : false;
   }

   public boolean hasEphemerisData(int var1) {
      return this.mWrapped.hasEphemerisData(var1);
   }

   public int hashCode() {
      return this.mWrapped.hashCode();
   }

   public boolean usedInFix(int var1) {
      return this.mWrapped.usedInFix(var1);
   }
}
