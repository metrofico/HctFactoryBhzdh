package androidx.activity.contextaware;

import android.content.Context;

public interface ContextAware {
   void addOnContextAvailableListener(OnContextAvailableListener var1);

   Context peekAvailableContext();

   void removeOnContextAvailableListener(OnContextAvailableListener var1);
}
