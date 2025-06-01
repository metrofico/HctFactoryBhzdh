package androidx.core.location;

import android.location.LocationListener;
import android.os.Bundle;

public interface LocationListenerCompat extends LocationListener {
   default void onProviderDisabled(String var1) {
   }

   default void onProviderEnabled(String var1) {
   }

   default void onStatusChanged(String var1, int var2, Bundle var3) {
   }
}
