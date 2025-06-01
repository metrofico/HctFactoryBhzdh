package jxl.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.common.Assert;
import jxl.write.biff.File;

public class Fonts {
   private static final int numDefaultFonts = 4;
   private ArrayList fonts = new ArrayList();

   public void addFont(FontRecord var1) {
      if (!var1.isInitialized()) {
         int var3 = this.fonts.size();
         int var2 = var3;
         if (var3 >= 4) {
            var2 = var3 + 1;
         }

         var1.initialize(var2);
         this.fonts.add(var1);
      }

   }

   public FontRecord getFont(int var1) {
      int var2 = var1;
      if (var1 > 4) {
         var2 = var1 - 1;
      }

      return (FontRecord)this.fonts.get(var2);
   }

   IndexMapping rationalize() {
      IndexMapping var6 = new IndexMapping(this.fonts.size() + 1);
      ArrayList var5 = new ArrayList();

      int var1;
      FontRecord var7;
      for(var1 = 0; var1 < 4; ++var1) {
         var7 = (FontRecord)this.fonts.get(var1);
         var5.add(var7);
         var6.setMapping(var7.getFontIndex(), var7.getFontIndex());
      }

      int var2 = 0;

      FontRecord var8;
      for(var1 = 4; var1 < this.fonts.size(); ++var1) {
         var7 = (FontRecord)this.fonts.get(var1);
         Iterator var9 = var5.iterator();
         boolean var3 = false;

         while(var9.hasNext() && !var3) {
            var8 = (FontRecord)var9.next();
            if (var7.equals(var8)) {
               var6.setMapping(var7.getFontIndex(), var6.getNewIndex(var8.getFontIndex()));
               ++var2;
               var3 = true;
            }
         }

         if (!var3) {
            var5.add(var7);
            int var10 = var7.getFontIndex() - var2;
            boolean var4;
            if (var10 > 4) {
               var4 = true;
            } else {
               var4 = false;
            }

            Assert.verify(var4);
            var6.setMapping(var7.getFontIndex(), var10);
         }
      }

      Iterator var11 = var5.iterator();

      while(var11.hasNext()) {
         var8 = (FontRecord)var11.next();
         var8.initialize(var6.getNewIndex(var8.getFontIndex()));
      }

      this.fonts = var5;
      return var6;
   }

   public void write(File var1) throws IOException {
      Iterator var2 = this.fonts.iterator();

      while(var2.hasNext()) {
         var1.write((FontRecord)var2.next());
      }

   }
}
