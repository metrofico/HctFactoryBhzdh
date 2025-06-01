package androidx.core.location;

import android.os.Bundle;

public final class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda6 implements Runnable {
   public final LocationManagerCompat.LocationListenerTransport f$0;
   public final LocationListenerCompat f$1;
   public final String f$2;
   public final int f$3;
   public final Bundle f$4;

   // $FF: synthetic method
   public LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda6(LocationManagerCompat.LocationListenerTransport var1, LocationListenerCompat var2, String var3, int var4, Bundle var5) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
      this.f$3 = var4;
      this.f$4 = var5;
   }

   public final void run() {
      this.f$0.lambda$onStatusChanged$5$androidx_core_location_LocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2, this.f$3, this.f$4);
   }
}
