package androidx.lifecycle;

public interface DefaultLifecycleObserver extends FullLifecycleObserver {
   default void onCreate(LifecycleOwner var1) {
   }

   default void onDestroy(LifecycleOwner var1) {
   }

   default void onPause(LifecycleOwner var1) {
   }

   default void onResume(LifecycleOwner var1) {
   }

   default void onStart(LifecycleOwner var1) {
   }

   default void onStop(LifecycleOwner var1) {
   }
}
