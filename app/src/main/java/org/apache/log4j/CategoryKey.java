package org.apache.log4j;

class CategoryKey {
   static Class class$org$apache$log4j$CategoryKey;
   int hashCache;
   String name;

   CategoryKey(String var1) {
      this.name = var1;
      this.hashCache = var1.hashCode();
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         Class var2 = Class.forName(var0);
         return var2;
      } catch (ClassNotFoundException var1) {
         throw new NoClassDefFoundError(var1.getMessage());
      }
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         if (var1 != null) {
            Class var3 = class$org$apache$log4j$CategoryKey;
            Class var2 = var3;
            if (var3 == null) {
               var2 = class$("org.apache.log4j.CategoryKey");
               class$org$apache$log4j$CategoryKey = var2;
            }

            if (var2 == var1.getClass()) {
               return this.name.equals(((CategoryKey)var1).name);
            }
         }

         return false;
      }
   }

   public final int hashCode() {
      return this.hashCache;
   }
}
