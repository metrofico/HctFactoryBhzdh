package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;

public final class ResolvableFuture extends AbstractResolvableFuture {
   private ResolvableFuture() {
   }

   public static ResolvableFuture create() {
      return new ResolvableFuture();
   }

   public boolean set(Object var1) {
      return super.set(var1);
   }

   public boolean setException(Throwable var1) {
      return super.setException(var1);
   }

   public boolean setFuture(ListenableFuture var1) {
      return super.setFuture(var1);
   }
}
