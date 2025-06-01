package com.android.common;

import android.app.SearchableInfo;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class Search {
   public static final String SOURCE = "source";
   public static final String SUGGEST_COLUMN_LAST_ACCESS_HINT = "suggest_last_access_hint";

   private Search() {
   }

   public static Cursor getSuggestions(Context var0, SearchableInfo var1, String var2) {
      return getSuggestions(var0, var1, var2, -1);
   }

   public static Cursor getSuggestions(Context var0, SearchableInfo var1, String var2, int var3) {
      if (var1 == null) {
         return null;
      } else {
         String var4 = var1.getSuggestAuthority();
         if (var4 == null) {
            return null;
         } else {
            Uri.Builder var8 = (new Uri.Builder()).scheme("content").authority(var4).query("").fragment("");
            String var5 = var1.getSuggestPath();
            if (var5 != null) {
               var8.appendEncodedPath(var5);
            }

            var8.appendPath("search_suggest_query");
            var5 = var1.getSuggestSelection();
            String[] var6;
            if (var5 != null) {
               var6 = new String[]{var2};
            } else {
               var8.appendPath(var2);
               var6 = null;
            }

            if (var3 > 0) {
               var8.appendQueryParameter("limit", String.valueOf(var3));
            }

            Uri var7 = var8.build();
            return var0.getContentResolver().query(var7, (String[])null, var5, var6, (String)null);
         }
      }
   }
}
