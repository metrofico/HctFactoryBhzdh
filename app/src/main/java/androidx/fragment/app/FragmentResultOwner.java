package androidx.fragment.app;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;

public interface FragmentResultOwner {
   void clearFragmentResult(String var1);

   void clearFragmentResultListener(String var1);

   void setFragmentResult(String var1, Bundle var2);

   void setFragmentResultListener(String var1, LifecycleOwner var2, FragmentResultListener var3);
}
