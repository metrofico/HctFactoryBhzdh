package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Handler;
import android.os.Build.VERSION;
import androidx.core.os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@Deprecated
public class FingerprintManagerCompat {
   private final Context mContext;

   private FingerprintManagerCompat(Context var1) {
      this.mContext = var1;
   }

   public static FingerprintManagerCompat from(Context var0) {
      return new FingerprintManagerCompat(var0);
   }

   private static FingerprintManager getFingerprintManagerOrNull(Context var0) {
      if (VERSION.SDK_INT == 23) {
         return (FingerprintManager)var0.getSystemService(FingerprintManager.class);
      } else {
         return VERSION.SDK_INT > 23 && var0.getPackageManager().hasSystemFeature("android.hardware.fingerprint") ? (FingerprintManager)var0.getSystemService(FingerprintManager.class) : null;
      }
   }

   static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject var0) {
      CryptoObject var1 = null;
      if (var0 == null) {
         return null;
      } else if (var0.getCipher() != null) {
         return new CryptoObject(var0.getCipher());
      } else if (var0.getSignature() != null) {
         return new CryptoObject(var0.getSignature());
      } else {
         if (var0.getMac() != null) {
            var1 = new CryptoObject(var0.getMac());
         }

         return var1;
      }
   }

   private static FingerprintManager.AuthenticationCallback wrapCallback(AuthenticationCallback var0) {
      return new FingerprintManager.AuthenticationCallback(var0) {
         final AuthenticationCallback val$callback;

         {
            this.val$callback = var1;
         }

         public void onAuthenticationError(int var1, CharSequence var2) {
            this.val$callback.onAuthenticationError(var1, var2);
         }

         public void onAuthenticationFailed() {
            this.val$callback.onAuthenticationFailed();
         }

         public void onAuthenticationHelp(int var1, CharSequence var2) {
            this.val$callback.onAuthenticationHelp(var1, var2);
         }

         public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult var1) {
            this.val$callback.onAuthenticationSucceeded(new AuthenticationResult(FingerprintManagerCompat.unwrapCryptoObject(var1.getCryptoObject())));
         }
      };
   }

   private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject var0) {
      FingerprintManager.CryptoObject var1 = null;
      if (var0 == null) {
         return null;
      } else if (var0.getCipher() != null) {
         return new FingerprintManager.CryptoObject(var0.getCipher());
      } else if (var0.getSignature() != null) {
         return new FingerprintManager.CryptoObject(var0.getSignature());
      } else {
         if (var0.getMac() != null) {
            var1 = new FingerprintManager.CryptoObject(var0.getMac());
         }

         return var1;
      }
   }

   public void authenticate(CryptoObject var1, int var2, CancellationSignal var3, AuthenticationCallback var4, Handler var5) {
      if (VERSION.SDK_INT >= 23) {
         FingerprintManager var6 = getFingerprintManagerOrNull(this.mContext);
         if (var6 != null) {
            android.os.CancellationSignal var7;
            if (var3 != null) {
               var7 = (android.os.CancellationSignal)var3.getCancellationSignalObject();
            } else {
               var7 = null;
            }

            var6.authenticate(wrapCryptoObject(var1), var7, var2, wrapCallback(var4), var5);
         }
      }

   }

   public boolean hasEnrolledFingerprints() {
      int var1 = VERSION.SDK_INT;
      boolean var3 = false;
      boolean var2 = var3;
      if (var1 >= 23) {
         FingerprintManager var4 = getFingerprintManagerOrNull(this.mContext);
         var2 = var3;
         if (var4 != null) {
            var2 = var3;
            if (var4.hasEnrolledFingerprints()) {
               var2 = true;
            }
         }
      }

      return var2;
   }

   public boolean isHardwareDetected() {
      int var1 = VERSION.SDK_INT;
      boolean var3 = false;
      boolean var2 = var3;
      if (var1 >= 23) {
         FingerprintManager var4 = getFingerprintManagerOrNull(this.mContext);
         var2 = var3;
         if (var4 != null) {
            var2 = var3;
            if (var4.isHardwareDetected()) {
               var2 = true;
            }
         }
      }

      return var2;
   }

   public abstract static class AuthenticationCallback {
      public void onAuthenticationError(int var1, CharSequence var2) {
      }

      public void onAuthenticationFailed() {
      }

      public void onAuthenticationHelp(int var1, CharSequence var2) {
      }

      public void onAuthenticationSucceeded(AuthenticationResult var1) {
      }
   }

   public static final class AuthenticationResult {
      private final CryptoObject mCryptoObject;

      public AuthenticationResult(CryptoObject var1) {
         this.mCryptoObject = var1;
      }

      public CryptoObject getCryptoObject() {
         return this.mCryptoObject;
      }
   }

   public static class CryptoObject {
      private final Cipher mCipher;
      private final Mac mMac;
      private final Signature mSignature;

      public CryptoObject(Signature var1) {
         this.mSignature = var1;
         this.mCipher = null;
         this.mMac = null;
      }

      public CryptoObject(Cipher var1) {
         this.mCipher = var1;
         this.mSignature = null;
         this.mMac = null;
      }

      public CryptoObject(Mac var1) {
         this.mMac = var1;
         this.mCipher = null;
         this.mSignature = null;
      }

      public Cipher getCipher() {
         return this.mCipher;
      }

      public Mac getMac() {
         return this.mMac;
      }

      public Signature getSignature() {
         return this.mSignature;
      }
   }
}
