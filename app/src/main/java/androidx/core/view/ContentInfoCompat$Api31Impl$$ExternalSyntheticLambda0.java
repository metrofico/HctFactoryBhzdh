package androidx.core.view;

import android.content.ClipData;
import androidx.core.util.Predicate;

public final class ContentInfoCompat$Api31Impl$$ExternalSyntheticLambda0 implements Predicate {
   public final java.util.function.Predicate f$0;

   // $FF: synthetic method
   public ContentInfoCompat$Api31Impl$$ExternalSyntheticLambda0(java.util.function.Predicate var1) {
      this.f$0 = var1;
   }

   public final boolean test(Object var1) {
      return this.f$0.test((ClipData.Item)var1);
   }
}
