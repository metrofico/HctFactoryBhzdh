package com.android.common.content;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProjectionMap extends HashMap {
   private String[] mColumns;

   public static Builder builder() {
      return new Builder();
   }

   private void putColumn(String var1, String var2) {
      super.put(var1, var2);
   }

   public String[] getColumnNames() {
      return this.mColumns;
   }

   public String put(String var1, String var2) {
      throw new UnsupportedOperationException();
   }

   public void putAll(Map var1) {
      throw new UnsupportedOperationException();
   }

   public static class Builder {
      private ProjectionMap mMap = new ProjectionMap();

      public Builder add(String var1) {
         this.mMap.putColumn(var1, var1);
         return this;
      }

      public Builder add(String var1, String var2) {
         ProjectionMap var3 = this.mMap;
         StringBuilder var4 = new StringBuilder();
         var4.append(var2);
         var4.append(" AS ");
         var4.append(var1);
         var3.putColumn(var1, var4.toString());
         return this;
      }

      public Builder addAll(ProjectionMap var1) {
         Iterator var3 = var1.entrySet().iterator();

         while(var3.hasNext()) {
            Entry var2 = (Entry)var3.next();
            this.mMap.putColumn((String)var2.getKey(), (String)var2.getValue());
         }

         return this;
      }

      public Builder addAll(String[] var1) {
         int var3 = var1.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            this.add(var1[var2]);
         }

         return this;
      }

      public ProjectionMap build() {
         String[] var1 = new String[this.mMap.size()];
         this.mMap.keySet().toArray(var1);
         Arrays.sort(var1);
         this.mMap.mColumns = var1;
         return this.mMap;
      }
   }
}
