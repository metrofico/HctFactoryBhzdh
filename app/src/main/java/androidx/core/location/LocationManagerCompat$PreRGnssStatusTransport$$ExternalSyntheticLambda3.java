package androidx.core.location;

import java.util.concurrent.Executor;

public final class LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda3 implements Runnable {
   public final LocationManagerCompat.PreRGnssStatusTransport f$0;
   public final Executor f$1;

   // $FF: synthetic method
   public LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda3(LocationManagerCompat.PreRGnssStatusTransport var1, Executor var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void run() {
      this.f$0.lambda$onStopped$1$androidx_core_location_LocationManagerCompat$PreRGnssStatusTransport(this.f$1);
   }
}
