package androidx.lifecycle;

import android.content.Context;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;

public final class ProcessLifecycleInitializer implements Initializer {
   public LifecycleOwner create(Context var1) {
      LifecycleDispatcher.init(var1);
      ProcessLifecycleOwner.init(var1);
      return ProcessLifecycleOwner.get();
   }

   public List dependencies() {
      return Collections.emptyList();
   }
}
