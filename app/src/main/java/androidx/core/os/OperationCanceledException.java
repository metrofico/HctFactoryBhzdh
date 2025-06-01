package androidx.core.os;

import androidx.core.util.ObjectsCompat;

public class OperationCanceledException extends RuntimeException {
   public OperationCanceledException() {
      this((String)null);
   }

   public OperationCanceledException(String var1) {
      super(ObjectsCompat.toString(var1, "The operation has been canceled."));
   }
}
