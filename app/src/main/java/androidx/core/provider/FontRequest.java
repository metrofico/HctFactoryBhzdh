package androidx.core.provider;

import android.util.Base64;
import androidx.core.util.Preconditions;
import java.util.List;

public final class FontRequest {
   private final List mCertificates;
   private final int mCertificatesArray;
   private final String mIdentifier;
   private final String mProviderAuthority;
   private final String mProviderPackage;
   private final String mQuery;

   public FontRequest(String var1, String var2, String var3, int var4) {
      this.mProviderAuthority = (String)Preconditions.checkNotNull(var1);
      this.mProviderPackage = (String)Preconditions.checkNotNull(var2);
      this.mQuery = (String)Preconditions.checkNotNull(var3);
      this.mCertificates = null;
      boolean var5;
      if (var4 != 0) {
         var5 = true;
      } else {
         var5 = false;
      }

      Preconditions.checkArgument(var5);
      this.mCertificatesArray = var4;
      this.mIdentifier = this.createIdentifier(var1, var2, var3);
   }

   public FontRequest(String var1, String var2, String var3, List var4) {
      this.mProviderAuthority = (String)Preconditions.checkNotNull(var1);
      this.mProviderPackage = (String)Preconditions.checkNotNull(var2);
      this.mQuery = (String)Preconditions.checkNotNull(var3);
      this.mCertificates = (List)Preconditions.checkNotNull(var4);
      this.mCertificatesArray = 0;
      this.mIdentifier = this.createIdentifier(var1, var2, var3);
   }

   private String createIdentifier(String var1, String var2, String var3) {
      return var1 + "-" + var2 + "-" + var3;
   }

   public List getCertificates() {
      return this.mCertificates;
   }

   public int getCertificatesArrayResId() {
      return this.mCertificatesArray;
   }

   String getId() {
      return this.mIdentifier;
   }

   @Deprecated
   public String getIdentifier() {
      return this.mIdentifier;
   }

   public String getProviderAuthority() {
      return this.mProviderAuthority;
   }

   public String getProviderPackage() {
      return this.mProviderPackage;
   }

   public String getQuery() {
      return this.mQuery;
   }

   public String toString() {
      StringBuilder var4 = new StringBuilder();
      var4.append("FontRequest {mProviderAuthority: " + this.mProviderAuthority + ", mProviderPackage: " + this.mProviderPackage + ", mQuery: " + this.mQuery + ", mCertificates:");

      for(int var1 = 0; var1 < this.mCertificates.size(); ++var1) {
         var4.append(" [");
         List var3 = (List)this.mCertificates.get(var1);

         for(int var2 = 0; var2 < var3.size(); ++var2) {
            var4.append(" \"");
            var4.append(Base64.encodeToString((byte[])var3.get(var2), 0));
            var4.append("\"");
         }

         var4.append(" ]");
      }

      var4.append("}");
      var4.append("mCertificatesArray: " + this.mCertificatesArray);
      return var4.toString();
   }
}
