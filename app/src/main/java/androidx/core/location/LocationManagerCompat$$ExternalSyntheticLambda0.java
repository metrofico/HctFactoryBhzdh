package androidx.core.location;

import android.location.LocationManager;
import java.util.concurrent.Callable;

public final class LocationManagerCompat$$ExternalSyntheticLambda0 implements Callable {
   public final LocationManager f$0;
   public final LocationManagerCompat.GpsStatusTransport f$1;

   // $FF: synthetic method
   public LocationManagerCompat$$ExternalSyntheticLambda0(LocationManager var1, LocationManagerCompat.GpsStatusTransport var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final Object call() {
      return LocationManagerCompat.lambda$registerGnssStatusCallback$1(this.f$0, this.f$1);
   }
}
