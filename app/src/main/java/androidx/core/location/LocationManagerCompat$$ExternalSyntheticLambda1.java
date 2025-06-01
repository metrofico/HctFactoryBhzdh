package androidx.core.location;

import android.location.Location;
import androidx.core.util.Consumer;

public final class LocationManagerCompat$$ExternalSyntheticLambda1 implements Runnable {
   public final Consumer f$0;
   public final Location f$1;

   // $FF: synthetic method
   public LocationManagerCompat$$ExternalSyntheticLambda1(Consumer var1, Location var2) {
      this.f$0 = var1;
      this.f$1 = var2;
   }

   public final void run() {
      LocationManagerCompat.lambda$getCurrentLocation$0(this.f$0, this.f$1);
   }
}
