package jxl.write.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class SharedStrings {
   private ArrayList stringList = new ArrayList(100);
   private HashMap strings = new HashMap(100);
   private int totalOccurrences = 0;

   public SharedStrings() {
   }

   private SSTContinueRecord createContinueRecord(String var1, int var2, File var3) throws IOException {
      SSTContinueRecord var5 = null;
      int var4 = var2;

      while(var4 != 0) {
         SSTContinueRecord var6 = new SSTContinueRecord();
         if (var4 != var1.length() && var1.length() != 0) {
            var2 = var6.setFirstString(var1.substring(var1.length() - var4), false);
         } else {
            var2 = var6.setFirstString(var1, true);
         }

         var5 = var6;
         var4 = var2;
         if (var2 != 0) {
            var3.write(var6);
            var5 = new SSTContinueRecord();
            var4 = var2;
         }
      }

      return var5;
   }

   public String get(int var1) {
      return (String)this.stringList.get(var1);
   }

   public int getIndex(String var1) {
      Integer var3 = (Integer)this.strings.get(var1);
      Integer var2 = var3;
      if (var3 == null) {
         var2 = new Integer(this.strings.size());
         this.strings.put(var1, var2);
         this.stringList.add(var1);
      }

      ++this.totalOccurrences;
      return var2;
   }

   public void write(File var1) throws IOException {
      SSTRecord var9 = new SSTRecord(this.totalOccurrences, this.stringList.size());
      ExtendedSSTRecord var7 = new ExtendedSSTRecord(this.stringList.size());
      int var4 = var7.getNumberOfStringsPerBucket();
      Iterator var8 = this.stringList.iterator();
      int var3 = 0;
      String var6 = null;

      int var2;
      int var5;
      for(var2 = 0; var8.hasNext() && var3 == 0; ++var2) {
         var6 = (String)var8.next();
         var5 = var9.getOffset();
         var3 = var9.add(var6);
         if (var2 % var4 == 0) {
            var7.addString(var1.getPos(), var5 + 4);
         }
      }

      var1.write(var9);
      if (var3 != 0 || var8.hasNext()) {
         SSTContinueRecord var10 = this.createContinueRecord(var6, var3, var1);

         while(var8.hasNext()) {
            String var11 = (String)var8.next();
            var3 = var10.getOffset();
            var5 = var10.add(var11);
            if (var2 % var4 == 0) {
               var7.addString(var1.getPos(), var3 + 4);
            }

            var3 = var2 + 1;
            var2 = var3;
            if (var5 != 0) {
               var1.write(var10);
               var10 = this.createContinueRecord(var11, var5, var1);
               var2 = var3;
            }
         }

         var1.write(var10);
      }

      var1.write(var7);
   }
}
