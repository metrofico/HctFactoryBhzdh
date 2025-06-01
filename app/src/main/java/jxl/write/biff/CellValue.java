package jxl.write.biff;

import jxl.Cell;
import jxl.CellFeatures;
import jxl.CellReferenceHelper;
import jxl.Sheet;
import jxl.biff.DVParser;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;
import jxl.biff.NumFormatRecordsException;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.biff.XFRecord;
import jxl.biff.drawing.ComboBox;
import jxl.biff.drawing.Comment;
import jxl.biff.formula.FormulaException;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.format.CellFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableWorkbook;

public abstract class CellValue extends WritableRecordData implements WritableCell {
   private static Logger logger = Logger.getLogger(CellValue.class);
   private int column;
   private boolean copied;
   private WritableCellFeatures features;
   private XFRecord format;
   private FormattingRecords formattingRecords;
   private boolean referenced;
   private int row;
   private WritableSheetImpl sheet;

   protected CellValue(Type var1, int var2, int var3) {
      this(var1, var2, var3, (CellFormat)WritableWorkbook.NORMAL_STYLE);
      this.copied = false;
   }

   protected CellValue(Type var1, int var2, int var3, CellFormat var4) {
      super(var1);
      this.row = var3;
      this.column = var2;
      this.format = (XFRecord)var4;
      this.referenced = false;
      this.copied = false;
   }

   protected CellValue(Type var1, int var2, int var3, CellValue var4) {
      super(var1);
      this.row = var3;
      this.column = var2;
      this.format = var4.format;
      this.referenced = false;
      this.copied = false;
      if (var4.features != null) {
         WritableCellFeatures var5 = new WritableCellFeatures(var4.features);
         this.features = var5;
         var5.setWritableCell(this);
      }

   }

   protected CellValue(Type var1, Cell var2) {
      this(var1, var2.getColumn(), var2.getRow());
      this.copied = true;
      this.format = (XFRecord)var2.getCellFormat();
      if (var2.getCellFeatures() != null) {
         WritableCellFeatures var3 = new WritableCellFeatures(var2.getCellFeatures());
         this.features = var3;
         var3.setWritableCell(this);
      }

   }

   private void addCellFormat() {
      Styles var1 = this.sheet.getWorkbook().getStyles();
      XFRecord var2 = var1.getFormat(this.format);
      this.format = var2;

      try {
         if (!var2.isInitialized()) {
            this.formattingRecords.addStyle(this.format);
         }
      } catch (NumFormatRecordsException var3) {
         logger.warn("Maximum number of format records exceeded.  Using default format.");
         this.format = var1.getNormalStyle();
      }

   }

   public final void addCellFeatures() {
      WritableCellFeatures var1 = this.features;
      if (var1 != null) {
         if (this.copied) {
            this.copied = false;
         } else {
            if (var1.getComment() != null) {
               Comment var3 = new Comment(this.features.getComment(), this.column, this.row);
               var3.setWidth(this.features.getCommentWidth());
               var3.setHeight(this.features.getCommentHeight());
               this.sheet.addDrawing(var3);
               this.sheet.getWorkbook().addDrawing(var3);
               this.features.setCommentDrawing(var3);
            }

            if (this.features.hasDataValidation()) {
               try {
                  this.features.getDVParser().setCell(this.column, this.row, this.sheet.getWorkbook(), this.sheet.getWorkbook(), this.sheet.getWorkbookSettings());
               } catch (FormulaException var2) {
                  Assert.verify(false);
               }

               this.sheet.addValidationCell(this);
               if (!this.features.hasDropDown()) {
                  return;
               }

               if (this.sheet.getComboBox() == null) {
                  ComboBox var4 = new ComboBox();
                  this.sheet.addDrawing(var4);
                  this.sheet.getWorkbook().addDrawing(var4);
                  this.sheet.setComboBox(var4);
               }

               this.features.setComboBox(this.sheet.getComboBox());
            }

         }
      }
   }

   void columnInserted(Sheet var1, int var2, int var3) {
   }

   void columnRemoved(Sheet var1, int var2, int var3) {
   }

   void decrementColumn() {
      --this.column;
      WritableCellFeatures var1 = this.features;
      if (var1 != null) {
         Comment var2 = var1.getCommentDrawing();
         if (var2 != null) {
            var2.setX((double)this.column);
            var2.setY((double)this.row);
         }
      }

   }

   void decrementRow() {
      --this.row;
      WritableCellFeatures var1 = this.features;
      if (var1 != null) {
         Comment var2 = var1.getCommentDrawing();
         if (var2 != null) {
            var2.setX((double)this.column);
            var2.setY((double)this.row);
         }

         if (this.features.hasDropDown()) {
            logger.warn("need to change value for drop down drawing");
         }
      }

   }

   public CellFeatures getCellFeatures() {
      return this.features;
   }

   public CellFormat getCellFormat() {
      return this.format;
   }

   public int getColumn() {
      return this.column;
   }

   public byte[] getData() {
      byte[] var1 = new byte[6];
      IntegerHelper.getTwoBytes(this.row, var1, 0);
      IntegerHelper.getTwoBytes(this.column, var1, 2);
      IntegerHelper.getTwoBytes(this.format.getXFIndex(), var1, 4);
      return var1;
   }

   public int getRow() {
      return this.row;
   }

   public WritableSheetImpl getSheet() {
      return this.sheet;
   }

   public WritableCellFeatures getWritableCellFeatures() {
      return this.features;
   }

   final int getXFIndex() {
      return this.format.getXFIndex();
   }

   void incrementColumn() {
      ++this.column;
      WritableCellFeatures var1 = this.features;
      if (var1 != null) {
         Comment var2 = var1.getCommentDrawing();
         if (var2 != null) {
            var2.setX((double)this.column);
            var2.setY((double)this.row);
         }
      }

   }

   void incrementRow() {
      ++this.row;
      WritableCellFeatures var1 = this.features;
      if (var1 != null) {
         Comment var2 = var1.getCommentDrawing();
         if (var2 != null) {
            var2.setX((double)this.column);
            var2.setY((double)this.row);
         }
      }

   }

   public boolean isHidden() {
      ColumnInfoRecord var1 = this.sheet.getColumnInfo(this.column);
      if (var1 != null && var1.getWidth() == 0) {
         return true;
      } else {
         RowRecord var2 = this.sheet.getRowInfo(this.row);
         return var2 != null && (var2.getRowHeight() == 0 || var2.isCollapsed());
      }
   }

   final boolean isReferenced() {
      return this.referenced;
   }

   public final void removeCellFeatures() {
      this.features = null;
   }

   public final void removeComment(Comment var1) {
      this.sheet.removeDrawing(var1);
   }

   public final void removeDataValidation() {
      this.sheet.removeDataValidation(this);
   }

   void rowInserted(Sheet var1, int var2, int var3) {
   }

   void rowRemoved(Sheet var1, int var2, int var3) {
   }

   void setCellDetails(FormattingRecords var1, SharedStrings var2, WritableSheetImpl var3) {
      this.referenced = true;
      this.sheet = var3;
      this.formattingRecords = var1;
      this.addCellFormat();
      this.addCellFeatures();
   }

   public void setCellFeatures(WritableCellFeatures var1) {
      if (this.features != null) {
         logger.warn("current cell features for " + CellReferenceHelper.getCellReference(this) + " not null - overwriting");
         if (this.features.hasDataValidation() && this.features.getDVParser() != null && this.features.getDVParser().extendedCellsValidation()) {
            DVParser var2 = this.features.getDVParser();
            logger.warn("Cannot add cell features to " + CellReferenceHelper.getCellReference(this) + " because it is part of the shared cell validation " + "group " + CellReferenceHelper.getCellReference(var2.getFirstColumn(), var2.getFirstRow()) + "-" + CellReferenceHelper.getCellReference(var2.getLastColumn(), var2.getLastRow()));
            return;
         }
      }

      this.features = var1;
      var1.setWritableCell(this);
      if (this.referenced) {
         this.addCellFeatures();
      }

   }

   public void setCellFormat(CellFormat var1) {
      this.format = (XFRecord)var1;
      if (this.referenced) {
         boolean var2;
         if (this.formattingRecords != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         this.addCellFormat();
      }
   }

   final void setCopied(boolean var1) {
      this.copied = var1;
   }
}
