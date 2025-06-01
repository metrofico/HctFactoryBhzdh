package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;

public abstract class ActivityResultContract {
   public abstract Intent createIntent(Context var1, Object var2);

   public SynchronousResult getSynchronousResult(Context var1, Object var2) {
      return null;
   }

   public abstract Object parseResult(int var1, Intent var2);

   public static final class SynchronousResult {
      private final Object mValue;

      public SynchronousResult(Object var1) {
         this.mValue = var1;
      }

      public Object getValue() {
         return this.mValue;
      }
   }
}
