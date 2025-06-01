package com.hct.factory;

import java.io.File;
import java.util.Comparator;

public class FileNameComarator implements Comparator {
   public int compare(Object var1, Object var2) {
      if (var1 instanceof File && var2 instanceof File) {
         byte var3 = 0;
         File var4 = (File)var1;
         File var5 = (File)var2;
         if (var4.getName().compareTo(var5.getName()) > 0) {
            var3 = 1;
         } else if (var4.getName().compareTo(var5.getName()) < 0) {
            var3 = -1;
         }

         return var3;
      } else {
         return 0;
      }
   }
}
