package androidx.core.content;

import android.content.SharedPreferences;

@Deprecated
public final class SharedPreferencesCompat {
   private SharedPreferencesCompat() {
   }

   @Deprecated
   public static final class EditorCompat {
      private static EditorCompat sInstance;
      private final Helper mHelper = new Helper();

      private EditorCompat() {
      }

      @Deprecated
      public static EditorCompat getInstance() {
         if (sInstance == null) {
            sInstance = new EditorCompat();
         }

         return sInstance;
      }

      @Deprecated
      public void apply(SharedPreferences.Editor var1) {
         this.mHelper.apply(var1);
      }

      private static class Helper {
         Helper() {
         }

         public void apply(SharedPreferences.Editor var1) {
            try {
               var1.apply();
            } catch (AbstractMethodError var3) {
               var1.commit();
            }

         }
      }
   }
}
