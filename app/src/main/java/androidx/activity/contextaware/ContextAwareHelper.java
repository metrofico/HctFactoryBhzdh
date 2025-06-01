package androidx.activity.contextaware;

import android.content.Context;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public final class ContextAwareHelper {
   private volatile Context mContext;
   private final Set mListeners = new CopyOnWriteArraySet();

   public void addOnContextAvailableListener(OnContextAvailableListener var1) {
      if (this.mContext != null) {
         var1.onContextAvailable(this.mContext);
      }

      this.mListeners.add(var1);
   }

   public void clearAvailableContext() {
      this.mContext = null;
   }

   public void dispatchOnContextAvailable(Context var1) {
      this.mContext = var1;
      Iterator var2 = this.mListeners.iterator();

      while(var2.hasNext()) {
         ((OnContextAvailableListener)var2.next()).onContextAvailable(var1);
      }

   }

   public Context peekAvailableContext() {
      return this.mContext;
   }

   public void removeOnContextAvailableListener(OnContextAvailableListener var1) {
      this.mListeners.remove(var1);
   }
}
