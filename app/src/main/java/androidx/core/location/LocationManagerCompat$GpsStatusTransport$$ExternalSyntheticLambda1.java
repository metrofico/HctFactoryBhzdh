package androidx.core.location;

import java.util.concurrent.Executor;

public final class LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda1 implements Runnable {
   public final LocationManagerCompat.GpsStatusTransport f$0;
   public final Executor f$1;

   // $FF: synthetic method
   public LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda1(LocationManagerCompat.GpsStatusTransport var1, Executor var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void run() {
      this.f$0.lambda$onGpsStatusChanged$1$androidx_core_location_LocationManagerCompat$GpsStatusTransport(this.f$1);
   }
}
