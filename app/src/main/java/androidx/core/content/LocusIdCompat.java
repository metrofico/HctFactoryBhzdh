package androidx.core.content;

import android.content.LocusId;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;

public final class LocusIdCompat {
   private final String mId;
   private final LocusId mWrapped;

   public LocusIdCompat(String var1) {
      this.mId = (String)Preconditions.checkStringNotEmpty(var1, "id cannot be empty");
      if (VERSION.SDK_INT >= 29) {
         this.mWrapped = LocusIdCompat.Api29Impl.create(var1);
      } else {
         this.mWrapped = null;
      }

   }

   private String getSanitizedId() {
      int var1 = this.mId.length();
      return var1 + "_chars";
   }

   public static LocusIdCompat toLocusIdCompat(LocusId var0) {
      Preconditions.checkNotNull(var0, "locusId cannot be null");
      return new LocusIdCompat((String)Preconditions.checkStringNotEmpty(var0.getId(), "id cannot be empty"));
   }

   public boolean equals(Object var1) {
      boolean var2 = true;
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         LocusIdCompat var4 = (LocusIdCompat)var1;
         String var3 = this.mId;
         if (var3 == null) {
            if (var4.mId != null) {
               var2 = false;
            }

            return var2;
         } else {
            return var3.equals(var4.mId);
         }
      }
   }

   public String getId() {
      return this.mId;
   }

   public int hashCode() {
      String var2 = this.mId;
      int var1;
      if (var2 == null) {
         var1 = 0;
      } else {
         var1 = var2.hashCode();
      }

      return 31 + var1;
   }

   public LocusId toLocusId() {
      return this.mWrapped;
   }

   public String toString() {
      return "LocusIdCompat[" + this.getSanitizedId() + "]";
   }

   private static class Api29Impl {
      static LocusId create(String var0) {
         return new LocusId(var0);
      }

      static String getId(LocusId var0) {
         return var0.getId();
      }
   }
}
