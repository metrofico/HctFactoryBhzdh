package jxl.write.biff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import jxl.Cell;
import jxl.CellType;
import jxl.Range;
import jxl.biff.SheetRangeImpl;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.write.Blank;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

class MergedCells {
   private static Logger logger = Logger.getLogger(MergedCells.class);
   private static final int maxRangesPerSheet = 1020;
   private ArrayList ranges = new ArrayList();
   private WritableSheet sheet;

   public MergedCells(WritableSheet var1) {
      this.sheet = var1;
   }

   private void checkIntersections() {
      ArrayList var4 = new ArrayList(this.ranges.size());
      Iterator var3 = this.ranges.iterator();

      while(var3.hasNext()) {
         SheetRangeImpl var5 = (SheetRangeImpl)var3.next();
         Iterator var2 = var4.iterator();
         boolean var1 = false;

         while(var2.hasNext() && !var1) {
            if (((SheetRangeImpl)var2.next()).intersects(var5)) {
               logger.warn("Could not merge cells " + var5 + " as they clash with an existing set of merged cells.");
               var1 = true;
            }
         }

         if (!var1) {
            var4.add(var5);
         }
      }

      this.ranges = var4;
   }

   private void checkRanges() {
      int var1 = 0;

      while(true) {
         label62: {
            label68: {
               int var2;
               Cell var6;
               SheetRangeImpl var7;
               Cell var8;
               boolean var10001;
               try {
                  if (var1 >= this.ranges.size()) {
                     break;
                  }

                  var7 = (SheetRangeImpl)this.ranges.get(var1);
                  var8 = var7.getTopLeft();
                  var6 = var7.getBottomRight();
                  var2 = var8.getColumn();
               } catch (WriteException var15) {
                  var10001 = false;
                  break label68;
               }

               boolean var3 = false;

               label57:
               while(true) {
                  int var4;
                  try {
                     if (var2 > var6.getColumn()) {
                        break label62;
                     }

                     var4 = var8.getRow();
                  } catch (WriteException var11) {
                     var10001 = false;
                     break;
                  }

                  while(true) {
                     try {
                        if (var4 > var6.getRow()) {
                           break;
                        }
                     } catch (WriteException var13) {
                        var10001 = false;
                        break label57;
                     }

                     boolean var5 = var3;

                     label51: {
                        try {
                           if (this.sheet.getCell(var2, var4).getType() == CellType.EMPTY) {
                              break label51;
                           }
                        } catch (WriteException var14) {
                           var10001 = false;
                           break label57;
                        }

                        if (!var3) {
                           var5 = true;
                        } else {
                           try {
                              Logger var10 = logger;
                              StringBuilder var9 = new StringBuilder();
                              var10.warn(var9.append("Range ").append(var7).append(" contains more than one data cell.  ").append("Setting the other cells to blank.").toString());
                              Blank var16 = new Blank(var2, var4);
                              this.sheet.addCell(var16);
                           } catch (WriteException var12) {
                              var10001 = false;
                              break label57;
                           }

                           var5 = var3;
                        }
                     }

                     ++var4;
                     var3 = var5;
                  }

                  ++var2;
               }
            }

            Assert.verify(false);
            break;
         }

         ++var1;
      }

   }

   void add(Range var1) {
      this.ranges.add(var1);
   }

   Range[] getMergedCells() {
      int var2 = this.ranges.size();
      Range[] var3 = new Range[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = (Range)this.ranges.get(var1);
      }

      return var3;
   }

   void insertColumn(int var1) {
      Iterator var2 = this.ranges.iterator();

      while(var2.hasNext()) {
         ((SheetRangeImpl)var2.next()).insertColumn(var1);
      }

   }

   void insertRow(int var1) {
      Iterator var2 = this.ranges.iterator();

      while(var2.hasNext()) {
         ((SheetRangeImpl)var2.next()).insertRow(var1);
      }

   }

   void removeColumn(int var1) {
      Iterator var2 = this.ranges.iterator();

      while(true) {
         while(var2.hasNext()) {
            SheetRangeImpl var3 = (SheetRangeImpl)var2.next();
            if (var3.getTopLeft().getColumn() == var1 && var3.getBottomRight().getColumn() == var1) {
               var2.remove();
            } else {
               var3.removeColumn(var1);
            }
         }

         return;
      }
   }

   void removeRow(int var1) {
      Iterator var2 = this.ranges.iterator();

      while(true) {
         while(var2.hasNext()) {
            SheetRangeImpl var3 = (SheetRangeImpl)var2.next();
            if (var3.getTopLeft().getRow() == var1 && var3.getBottomRight().getRow() == var1) {
               var2.remove();
            } else {
               var3.removeRow(var1);
            }
         }

         return;
      }
   }

   void unmergeCells(Range var1) {
      int var2 = this.ranges.indexOf(var1);
      if (var2 != -1) {
         this.ranges.remove(var2);
      }

   }

   void write(File var1) throws IOException {
      if (this.ranges.size() != 0) {
         if (!((WritableSheetImpl)this.sheet).getWorkbookSettings().getMergedCellCheckingDisabled()) {
            this.checkIntersections();
            this.checkRanges();
         }

         if (this.ranges.size() < 1020) {
            var1.write(new MergedCellsRecord(this.ranges));
         } else {
            int var5 = this.ranges.size() / 1020;
            int var2 = 0;

            for(int var3 = 0; var2 < var5 + 1; ++var2) {
               int var6 = Math.min(1020, this.ranges.size() - var3);
               ArrayList var7 = new ArrayList(var6);

               for(int var4 = 0; var4 < var6; ++var4) {
                  var7.add(this.ranges.get(var3 + var4));
               }

               var1.write(new MergedCellsRecord(var7));
               var3 += var6;
            }

         }
      }
   }
}
