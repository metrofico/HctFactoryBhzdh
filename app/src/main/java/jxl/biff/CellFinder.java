package jxl.biff;

import java.util.regex.Pattern;
import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;

public class CellFinder {
   private Sheet sheet;

   public CellFinder(Sheet var1) {
      this.sheet = var1;
   }

   public Cell findCell(String var1) {
      Cell var5 = null;
      int var2 = 0;

      for(boolean var4 = false; var2 < this.sheet.getRows() && !var4; ++var2) {
         Cell[] var6 = this.sheet.getRow(var2);

         for(int var3 = 0; var3 < var6.length && !var4; ++var3) {
            if (var6[var3].getContents().equals(var1)) {
               var5 = var6[var3];
               var4 = true;
            }
         }
      }

      return var5;
   }

   public Cell findCell(String var1, int var2, int var3, int var4, int var5, boolean var6) {
      int var7 = var3;
      if (var6) {
         var7 = var5;
      }

      int var12 = var7;
      var7 = var2;
      if (var6) {
         var7 = var4;
      }

      int var13 = var7;
      byte var19;
      if (var6) {
         var19 = -1;
      } else {
         var19 = 1;
      }

      Cell var17 = null;
      int var8 = 0;

      boolean var11;
      for(boolean var9 = false; var8 <= var4 - var2 && !var9; ++var8) {
         for(int var10 = 0; var10 <= var5 - var3 && !var9; var9 = var11) {
            int var15 = var8 * var19 + var13;
            int var14 = var10 * var19 + var12;
            Cell var16 = var17;
            var11 = var9;
            if (var15 < this.sheet.getColumns()) {
               var16 = var17;
               var11 = var9;
               if (var14 < this.sheet.getRows()) {
                  Cell var18 = this.sheet.getCell(var15, var14);
                  var16 = var17;
                  var11 = var9;
                  if (var18.getType() != CellType.EMPTY) {
                     var16 = var17;
                     var11 = var9;
                     if (var18.getContents().equals(var1)) {
                        var11 = true;
                        var16 = var18;
                     }
                  }
               }
            }

            ++var10;
            var17 = var16;
         }
      }

      return var17;
   }

   public Cell findCell(Pattern var1, int var2, int var3, int var4, int var5, boolean var6) {
      int var7 = var3;
      if (var6) {
         var7 = var5;
      }

      int var12 = var7;
      var7 = var2;
      if (var6) {
         var7 = var4;
      }

      int var13 = var7;
      byte var19;
      if (var6) {
         var19 = -1;
      } else {
         var19 = 1;
      }

      Cell var16 = null;
      int var8 = 0;

      boolean var11;
      for(boolean var9 = false; var8 <= var4 - var2 && !var9; ++var8) {
         for(int var10 = 0; var10 <= var5 - var3 && !var9; var9 = var11) {
            int var15 = var8 * var19 + var13;
            int var14 = var10 * var19 + var12;
            Cell var17 = var16;
            var11 = var9;
            if (var15 < this.sheet.getColumns()) {
               var17 = var16;
               var11 = var9;
               if (var14 < this.sheet.getRows()) {
                  Cell var18 = this.sheet.getCell(var15, var14);
                  var17 = var16;
                  var11 = var9;
                  if (var18.getType() != CellType.EMPTY) {
                     var17 = var16;
                     var11 = var9;
                     if (var1.matcher(var18.getContents()).matches()) {
                        var11 = true;
                        var17 = var18;
                     }
                  }
               }
            }

            ++var10;
            var16 = var17;
         }
      }

      return var16;
   }

   public LabelCell findLabelCell(String var1) {
      LabelCell var6 = null;
      int var2 = 0;

      for(boolean var4 = false; var2 < this.sheet.getRows() && !var4; ++var2) {
         Cell[] var8 = this.sheet.getRow(var2);

         boolean var5;
         for(int var3 = 0; var3 < var8.length && !var4; var4 = var5) {
            LabelCell var7;
            label30: {
               if (var8[var3].getType() != CellType.LABEL) {
                  var7 = var6;
                  var5 = var4;
                  if (var8[var3].getType() != CellType.STRING_FORMULA) {
                     break label30;
                  }
               }

               var7 = var6;
               var5 = var4;
               if (var8[var3].getContents().equals(var1)) {
                  var7 = (LabelCell)var8[var3];
                  var5 = true;
               }
            }

            ++var3;
            var6 = var7;
         }
      }

      return var6;
   }
}
