package androidx.lifecycle;

@Deprecated
class ReflectiveGenericLifecycleObserver implements LifecycleEventObserver {
   private final ClassesInfoCache.CallbackInfo mInfo;
   private final Object mWrapped;

   ReflectiveGenericLifecycleObserver(Object var1) {
      this.mWrapped = var1;
      this.mInfo = ClassesInfoCache.sInstance.getInfo(var1.getClass());
   }

   public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
      this.mInfo.invokeCallbacks(var1, var2, this.mWrapped);
   }
}
