package androidx.core.location;

import java.util.List;

public final class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda3 implements Runnable {
   public final LocationManagerCompat.LocationListenerTransport f$0;
   public final LocationListenerCompat f$1;
   public final List f$2;

   // $FF: synthetic method
   public LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda3(LocationManagerCompat.LocationListenerTransport var1, LocationListenerCompat var2, List var3) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
   }

   public final void run() {
      this.f$0.lambda$onLocationChanged$3$androidx_core_location_LocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2);
   }
}
