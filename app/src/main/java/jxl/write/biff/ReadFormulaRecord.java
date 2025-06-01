package jxl.write.biff;

import jxl.CellReferenceHelper;
import jxl.CellType;
import jxl.FormulaCell;
import jxl.Sheet;
import jxl.WorkbookSettings;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.write.WritableCell;

class ReadFormulaRecord extends CellValue implements FormulaData {
   private static Logger logger = Logger.getLogger(ReadFormulaRecord.class);
   private FormulaData formula;
   private FormulaParser parser;

   protected ReadFormulaRecord(FormulaData var1) {
      super(Type.FORMULA, var1);
      this.formula = var1;
   }

   void columnInserted(Sheet var1, int var2, int var3) {
      FormulaException var10000;
      label46: {
         FormulaParser var6;
         boolean var10001;
         try {
            var6 = this.parser;
         } catch (FormulaException var12) {
            var10000 = var12;
            var10001 = false;
            break label46;
         }

         boolean var5 = false;
         if (var6 == null) {
            try {
               byte[] var7 = this.formula.getFormulaData();
               int var4 = var7.length - 16;
               byte[] var14 = new byte[var4];
               System.arraycopy(var7, 16, var14, 0, var4);
               FormulaParser var15 = new FormulaParser(var14, this, this.getSheet().getWorkbook(), this.getSheet().getWorkbook(), this.getSheet().getWorkbookSettings());
               this.parser = var15;
               var15.parse();
            } catch (FormulaException var11) {
               var10000 = var11;
               var10001 = false;
               break label46;
            }
         }

         try {
            var6 = this.parser;
         } catch (FormulaException var10) {
            var10000 = var10;
            var10001 = false;
            break label46;
         }

         label32: {
            try {
               if (var1 != this.getSheet()) {
                  break label32;
               }
            } catch (FormulaException var9) {
               var10000 = var9;
               var10001 = false;
               break label46;
            }

            var5 = true;
         }

         try {
            var6.columnInserted(var2, var3, var5);
            return;
         } catch (FormulaException var8) {
            var10000 = var8;
            var10001 = false;
         }
      }

      FormulaException var13 = var10000;
      logger.warn("cannot insert column within formula:  " + var13.getMessage());
   }

   void columnRemoved(Sheet var1, int var2, int var3) {
      FormulaException var10000;
      label46: {
         FormulaParser var6;
         boolean var10001;
         try {
            var6 = this.parser;
         } catch (FormulaException var12) {
            var10000 = var12;
            var10001 = false;
            break label46;
         }

         boolean var5 = false;
         if (var6 == null) {
            try {
               byte[] var7 = this.formula.getFormulaData();
               int var4 = var7.length - 16;
               byte[] var14 = new byte[var4];
               System.arraycopy(var7, 16, var14, 0, var4);
               FormulaParser var15 = new FormulaParser(var14, this, this.getSheet().getWorkbook(), this.getSheet().getWorkbook(), this.getSheet().getWorkbookSettings());
               this.parser = var15;
               var15.parse();
            } catch (FormulaException var11) {
               var10000 = var11;
               var10001 = false;
               break label46;
            }
         }

         try {
            var6 = this.parser;
         } catch (FormulaException var10) {
            var10000 = var10;
            var10001 = false;
            break label46;
         }

         label32: {
            try {
               if (var1 != this.getSheet()) {
                  break label32;
               }
            } catch (FormulaException var9) {
               var10000 = var9;
               var10001 = false;
               break label46;
            }

            var5 = true;
         }

         try {
            var6.columnRemoved(var2, var3, var5);
            return;
         } catch (FormulaException var8) {
            var10000 = var8;
            var10001 = false;
         }
      }

      FormulaException var13 = var10000;
      logger.warn("cannot remove column within formula:  " + var13.getMessage());
   }

   public WritableCell copyTo(int var1, int var2) {
      return new FormulaRecord(var1, var2, this);
   }

   protected final byte[] getCellData() {
      return super.getData();
   }

   public String getContents() {
      return this.formula.getContents();
   }

   public byte[] getData() {
      byte[] var2 = super.getData();

      FormulaException var10000;
      label38: {
         FormulaParser var1;
         boolean var10001;
         try {
            var1 = this.parser;
         } catch (FormulaException var7) {
            var10000 = var7;
            var10001 = false;
            break label38;
         }

         byte[] var3;
         byte[] var8;
         if (var1 == null) {
            try {
               var8 = this.formula.getFormulaData();
            } catch (FormulaException var6) {
               var10000 = var6;
               var10001 = false;
               break label38;
            }
         } else {
            try {
               var3 = var1.getBytes();
               var8 = new byte[var3.length + 16];
               IntegerHelper.getTwoBytes(var3.length, var8, 14);
               System.arraycopy(var3, 0, var8, 16, var3.length);
            } catch (FormulaException var5) {
               var10000 = var5;
               var10001 = false;
               break label38;
            }
         }

         var8[8] = (byte)(var8[8] | 2);

         try {
            var3 = new byte[var2.length + var8.length];
            System.arraycopy(var2, 0, var3, 0, var2.length);
            System.arraycopy(var8, 0, var3, var2.length, var8.length);
            return var3;
         } catch (FormulaException var4) {
            var10000 = var4;
            var10001 = false;
         }
      }

      FormulaException var9 = var10000;
      logger.warn(CellReferenceHelper.getCellReference(this.getColumn(), this.getRow()) + " " + var9.getMessage());
      return this.handleFormulaException();
   }

   public String getFormula() throws FormulaException {
      return ((FormulaCell)this.formula).getFormula();
   }

   public byte[] getFormulaBytes() throws FormulaException {
      FormulaParser var2 = this.parser;
      if (var2 != null) {
         return var2.getBytes();
      } else {
         byte[] var3 = this.getFormulaData();
         int var1 = var3.length - 16;
         byte[] var4 = new byte[var1];
         System.arraycopy(var3, 16, var4, 0, var1);
         return var4;
      }
   }

   public byte[] getFormulaData() throws FormulaException {
      byte[] var1 = this.formula.getFormulaData();
      byte[] var2 = new byte[var1.length];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      var2[8] = (byte)(var2[8] | 2);
      return var2;
   }

   protected FormulaData getReadFormula() {
      return this.formula;
   }

   public CellType getType() {
      return this.formula.getType();
   }

   protected byte[] handleFormulaException() {
      byte[] var2 = super.getData();
      WritableWorkbookImpl var3 = this.getSheet().getWorkbook();
      FormulaParser var4 = new FormulaParser(this.getContents(), var3, var3, var3.getSettings());
      this.parser = var4;

      try {
         var4.parse();
      } catch (FormulaException var6) {
         logger.warn(var6.getMessage());
         FormulaParser var7 = new FormulaParser("\"ERROR\"", var3, var3, var3.getSettings());
         this.parser = var7;

         try {
            var7.parse();
         } catch (FormulaException var5) {
            Assert.verify(false);
         }
      }

      byte[] var9 = this.parser.getBytes();
      int var1 = var9.length + 16;
      byte[] var8 = new byte[var1];
      IntegerHelper.getTwoBytes(var9.length, var8, 14);
      System.arraycopy(var9, 0, var8, 16, var9.length);
      var8[8] = (byte)(var8[8] | 2);
      var9 = new byte[var2.length + var1];
      System.arraycopy(var2, 0, var9, 0, var2.length);
      System.arraycopy(var8, 0, var9, var2.length, var1);
      return var9;
   }

   public boolean handleImportedCellReferences(ExternalSheet var1, WorkbookMethods var2, WorkbookSettings var3) {
      try {
         if (this.parser == null) {
            byte[] var7 = this.formula.getFormulaData();
            int var4 = var7.length - 16;
            byte[] var6 = new byte[var4];
            System.arraycopy(var7, 16, var6, 0, var4);
            FormulaParser var9 = new FormulaParser(var6, this, var1, var2, var3);
            this.parser = var9;
            var9.parse();
         }

         boolean var5 = this.parser.handleImportedCellReferences();
         return var5;
      } catch (FormulaException var8) {
         logger.warn("cannot import formula:  " + var8.getMessage());
         return false;
      }
   }

   void rowInserted(Sheet var1, int var2, int var3) {
      FormulaException var10000;
      label46: {
         FormulaParser var6;
         boolean var10001;
         try {
            var6 = this.parser;
         } catch (FormulaException var12) {
            var10000 = var12;
            var10001 = false;
            break label46;
         }

         boolean var5 = false;
         if (var6 == null) {
            try {
               byte[] var7 = this.formula.getFormulaData();
               int var4 = var7.length - 16;
               byte[] var14 = new byte[var4];
               System.arraycopy(var7, 16, var14, 0, var4);
               FormulaParser var15 = new FormulaParser(var14, this, this.getSheet().getWorkbook(), this.getSheet().getWorkbook(), this.getSheet().getWorkbookSettings());
               this.parser = var15;
               var15.parse();
            } catch (FormulaException var11) {
               var10000 = var11;
               var10001 = false;
               break label46;
            }
         }

         try {
            var6 = this.parser;
         } catch (FormulaException var10) {
            var10000 = var10;
            var10001 = false;
            break label46;
         }

         label32: {
            try {
               if (var1 != this.getSheet()) {
                  break label32;
               }
            } catch (FormulaException var9) {
               var10000 = var9;
               var10001 = false;
               break label46;
            }

            var5 = true;
         }

         try {
            var6.rowInserted(var2, var3, var5);
            return;
         } catch (FormulaException var8) {
            var10000 = var8;
            var10001 = false;
         }
      }

      FormulaException var13 = var10000;
      logger.warn("cannot insert row within formula:  " + var13.getMessage());
   }

   void rowRemoved(Sheet var1, int var2, int var3) {
      FormulaException var10000;
      label46: {
         FormulaParser var6;
         boolean var10001;
         try {
            var6 = this.parser;
         } catch (FormulaException var12) {
            var10000 = var12;
            var10001 = false;
            break label46;
         }

         boolean var5 = false;
         if (var6 == null) {
            try {
               byte[] var7 = this.formula.getFormulaData();
               int var4 = var7.length - 16;
               byte[] var14 = new byte[var4];
               System.arraycopy(var7, 16, var14, 0, var4);
               FormulaParser var15 = new FormulaParser(var14, this, this.getSheet().getWorkbook(), this.getSheet().getWorkbook(), this.getSheet().getWorkbookSettings());
               this.parser = var15;
               var15.parse();
            } catch (FormulaException var11) {
               var10000 = var11;
               var10001 = false;
               break label46;
            }
         }

         try {
            var6 = this.parser;
         } catch (FormulaException var10) {
            var10000 = var10;
            var10001 = false;
            break label46;
         }

         label32: {
            try {
               if (var1 != this.getSheet()) {
                  break label32;
               }
            } catch (FormulaException var9) {
               var10000 = var9;
               var10001 = false;
               break label46;
            }

            var5 = true;
         }

         try {
            var6.rowRemoved(var2, var3, var5);
            return;
         } catch (FormulaException var8) {
            var10000 = var8;
            var10001 = false;
         }
      }

      FormulaException var13 = var10000;
      logger.warn("cannot remove row within formula:  " + var13.getMessage());
   }

   void setCellDetails(FormattingRecords var1, SharedStrings var2, WritableSheetImpl var3) {
      super.setCellDetails(var1, var2, var3);
      var3.getWorkbook().addRCIRCell(this);
   }
}
