package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build.VERSION;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class PackageInfoCompat {
   private PackageInfoCompat() {
   }

   private static boolean byteArrayContains(byte[][] var0, byte[] var1) {
      int var3 = var0.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         if (Arrays.equals(var1, var0[var2])) {
            return true;
         }
      }

      return false;
   }

   private static byte[] computeSHA256Digest(byte[] var0) {
      try {
         var0 = MessageDigest.getInstance("SHA256").digest(var0);
         return var0;
      } catch (NoSuchAlgorithmException var1) {
         throw new RuntimeException("Device doesn't support SHA256 cert checking", var1);
      }
   }

   public static long getLongVersionCode(PackageInfo var0) {
      return VERSION.SDK_INT >= 28 ? var0.getLongVersionCode() : (long)var0.versionCode;
   }

   public static List getSignatures(PackageManager var0, String var1) throws PackageManager.NameNotFoundException {
      Signature[] var3;
      if (VERSION.SDK_INT >= 28) {
         SigningInfo var2 = var0.getPackageInfo(var1, 134217728).signingInfo;
         if (var2.hasMultipleSigners()) {
            var3 = var2.getApkContentsSigners();
         } else {
            var3 = var2.getSigningCertificateHistory();
         }
      } else {
         var3 = var0.getPackageInfo(var1, 64).signatures;
      }

      return var3 == null ? Collections.emptyList() : Arrays.asList(var3);
   }

   public static boolean hasSignatures(PackageManager var0, String var1, Map var2, boolean var3) throws PackageManager.NameNotFoundException {
      if (var2.isEmpty()) {
         return false;
      } else {
         Set var5 = var2.keySet();
         Iterator var6 = var5.iterator();

         int var4;
         while(var6.hasNext()) {
            byte[] var7 = (byte[])var6.next();
            if (var7 == null) {
               throw new IllegalArgumentException("Cert byte array cannot be null when verifying " + var1);
            }

            Integer var16 = (Integer)var2.get(var7);
            if (var16 == null) {
               throw new IllegalArgumentException("Type must be specified for cert when verifying " + var1);
            }

            var4 = var16;
            if (var4 != 0 && var4 != 1) {
               throw new IllegalArgumentException("Unsupported certificate type " + var16 + " when verifying " + var1);
            }
         }

         List var14 = getSignatures(var0, var1);
         if (!var3 && VERSION.SDK_INT >= 28) {
            Iterator var13 = var5.iterator();

            byte[] var15;
            do {
               if (!var13.hasNext()) {
                  return true;
               }

               var15 = (byte[])var13.next();
            } while(var0.hasSigningCertificate(var1, var15, (Integer)var2.get(var15)));

            return false;
         } else {
            if (var14.size() != 0 && var2.size() <= var14.size() && (!var3 || var2.size() == var14.size())) {
               var3 = var2.containsValue(1);
               byte[][] var8 = null;
               if (var3) {
                  byte[][] var9 = new byte[var14.size()][];
                  var4 = 0;

                  while(true) {
                     var8 = var9;
                     if (var4 >= var14.size()) {
                        break;
                     }

                     var9[var4] = computeSHA256Digest(((Signature)var14.get(var4)).toByteArray());
                     ++var4;
                  }
               }

               Iterator var10 = var5.iterator();
               if (var10.hasNext()) {
                  byte[] var11 = (byte[])var10.next();
                  Integer var12 = (Integer)var2.get(var11);
                  var4 = var12;
                  if (var4 != 0) {
                     if (var4 != 1) {
                        throw new IllegalArgumentException("Unsupported certificate type " + var12);
                     }

                     if (!byteArrayContains(var8, var11)) {
                        return false;
                     }
                  } else if (!var14.contains(new Signature(var11))) {
                     return false;
                  }

                  return true;
               }
            }

            return false;
         }
      }
   }

   private static class Api28Impl {
      static Signature[] getApkContentsSigners(SigningInfo var0) {
         return var0.getApkContentsSigners();
      }

      static Signature[] getSigningCertificateHistory(SigningInfo var0) {
         return var0.getSigningCertificateHistory();
      }

      static boolean hasMultipleSigners(SigningInfo var0) {
         return var0.hasMultipleSigners();
      }

      static boolean hasSigningCertificate(PackageManager var0, String var1, byte[] var2, int var3) {
         return var0.hasSigningCertificate(var1, var2, var3);
      }
   }
}
