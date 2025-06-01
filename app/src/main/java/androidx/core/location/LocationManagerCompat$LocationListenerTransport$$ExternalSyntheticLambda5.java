package androidx.core.location;

import android.location.Location;

public final class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda5 implements Runnable {
   public final LocationManagerCompat.LocationListenerTransport f$0;
   public final LocationListenerCompat f$1;
   public final Location f$2;

   // $FF: synthetic method
   public LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda5(LocationManagerCompat.LocationListenerTransport var1, LocationListenerCompat var2, Location var3) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
   }

   public final void run() {
      this.f$0.lambda$onLocationChanged$2$androidx_core_location_LocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2);
   }
}
