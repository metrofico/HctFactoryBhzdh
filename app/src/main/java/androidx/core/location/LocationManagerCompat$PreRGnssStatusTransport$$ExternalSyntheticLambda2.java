package androidx.core.location;

import java.util.concurrent.Executor;

public final class LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda2 implements Runnable {
   public final LocationManagerCompat.PreRGnssStatusTransport f$0;
   public final Executor f$1;
   public final int f$2;

   // $FF: synthetic method
   public LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda2(LocationManagerCompat.PreRGnssStatusTransport var1, Executor var2, int var3) {
      this.f$0 = var1;
      this.f$1 = var2;
      this.f$2 = var3;
   }

   public final void run() {
      this.f$0.lambda$onFirstFix$2$androidx_core_location_LocationManagerCompat$PreRGnssStatusTransport(this.f$1, this.f$2);
   }
}
