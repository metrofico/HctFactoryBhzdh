package androidx.core.location;

import android.location.Location;
import androidx.core.util.Consumer;

public final class LocationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda0 implements Runnable {
   public final Consumer f$0;
   public final Location f$1;

   // $FF: synthetic method
   public LocationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda0(Consumer var1, Location var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void run() {
      LocationManagerCompat.CancellableLocationListener.lambda$onLocationChanged$0(this.f$0, this.f$1);
   }
}
