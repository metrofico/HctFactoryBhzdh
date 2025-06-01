package androidx.core.location;

public final class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda0 implements Runnable {
   public final LocationManagerCompat.LocationListenerTransport f$0;
   public final LocationListenerCompat f$1;
   public final String f$2;

   // $FF: synthetic method
   public LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda0(LocationManagerCompat.LocationListenerTransport var1, LocationListenerCompat var2, String var3) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
   }

   public final void run() {
      this.f$0.lambda$onProviderEnabled$6$androidx_core_location_LocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2);
   }
}
