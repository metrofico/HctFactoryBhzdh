package androidx.core.content;

public final class PackageManagerCompat$$ExternalSyntheticLambda0 implements Runnable {
   public final UnusedAppRestrictionsBackportServiceConnection f$0;

   // $FF: synthetic method
   public PackageManagerCompat$$ExternalSyntheticLambda0(UnusedAppRestrictionsBackportServiceConnection var1) {
      this.f$0 = var1;
   }

   public final void run() {
      this.f$0.disconnectFromService();
   }
}
