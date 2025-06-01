package androidx.startup;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public final class InitializationProvider extends ContentProvider {
   public int delete(Uri var1, String var2, String[] var3) {
      throw new IllegalStateException("Not allowed.");
   }

   public String getType(Uri var1) {
      throw new IllegalStateException("Not allowed.");
   }

   public Uri insert(Uri var1, ContentValues var2) {
      throw new IllegalStateException("Not allowed.");
   }

   public boolean onCreate() {
      Context var1 = this.getContext();
      if (var1 != null) {
         AppInitializer.getInstance(var1).discoverAndInitialize();
         return true;
      } else {
         throw new StartupException("Context cannot be null");
      }
   }

   public Cursor query(Uri var1, String[] var2, String var3, String[] var4, String var5) {
      throw new IllegalStateException("Not allowed.");
   }

   public int update(Uri var1, ContentValues var2, String var3, String[] var4) {
      throw new IllegalStateException("Not allowed.");
   }
}
