package jxl.biff;

import java.util.Collection;
import jxl.Range;
import jxl.biff.drawing.ComboBox;
import jxl.biff.drawing.Comment;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.write.biff.CellValue;

public class BaseCellFeatures {
   public static final ValidationCondition BETWEEN;
   public static final ValidationCondition EQUAL;
   public static final ValidationCondition GREATER_EQUAL;
   public static final ValidationCondition GREATER_THAN;
   public static final ValidationCondition LESS_EQUAL;
   public static final ValidationCondition LESS_THAN;
   public static final ValidationCondition NOT_BETWEEN;
   public static final ValidationCondition NOT_EQUAL;
   private static final double defaultCommentHeight = 4.0;
   private static final double defaultCommentWidth = 3.0;
   public static Logger logger = Logger.getLogger(BaseCellFeatures.class);
   private ComboBox comboBox;
   private String comment;
   private Comment commentDrawing;
   private double commentHeight;
   private double commentWidth;
   private boolean dataValidation;
   private boolean dropDown;
   private DVParser dvParser;
   private DataValiditySettingsRecord validationSettings;
   private CellValue writableCell;

   static {
      BETWEEN = new ValidationCondition(DVParser.BETWEEN);
      NOT_BETWEEN = new ValidationCondition(DVParser.NOT_BETWEEN);
      EQUAL = new ValidationCondition(DVParser.EQUAL);
      NOT_EQUAL = new ValidationCondition(DVParser.NOT_EQUAL);
      GREATER_THAN = new ValidationCondition(DVParser.GREATER_THAN);
      LESS_THAN = new ValidationCondition(DVParser.LESS_THAN);
      GREATER_EQUAL = new ValidationCondition(DVParser.GREATER_EQUAL);
      LESS_EQUAL = new ValidationCondition(DVParser.LESS_EQUAL);
   }

   protected BaseCellFeatures() {
   }

   public BaseCellFeatures(BaseCellFeatures var1) {
      this.comment = var1.comment;
      this.commentWidth = var1.commentWidth;
      this.commentHeight = var1.commentHeight;
      this.dropDown = var1.dropDown;
      this.dataValidation = var1.dataValidation;
      this.validationSettings = var1.validationSettings;
      if (var1.dvParser != null) {
         this.dvParser = new DVParser(var1.dvParser);
      }

   }

   private void clearValidationSettings() {
      this.validationSettings = null;
      this.dvParser = null;
      this.dropDown = false;
      this.comboBox = null;
      this.dataValidation = false;
   }

   protected String getComment() {
      return this.comment;
   }

   public final Comment getCommentDrawing() {
      return this.commentDrawing;
   }

   public double getCommentHeight() {
      return this.commentHeight;
   }

   public double getCommentWidth() {
      return this.commentWidth;
   }

   public DVParser getDVParser() {
      DVParser var1 = this.dvParser;
      if (var1 != null) {
         return var1;
      } else if (this.validationSettings != null) {
         var1 = new DVParser(this.validationSettings.getDVParser());
         this.dvParser = var1;
         return var1;
      } else {
         return null;
      }
   }

   public String getDataValidationList() {
      DataValiditySettingsRecord var1 = this.validationSettings;
      return var1 == null ? null : var1.getValidationFormula();
   }

   public Range getSharedDataValidationRange() {
      if (!this.dataValidation) {
         return null;
      } else {
         DVParser var1 = this.getDVParser();
         return new SheetRangeImpl(this.writableCell.getSheet(), var1.getFirstColumn(), var1.getFirstRow(), var1.getLastColumn(), var1.getLastRow());
      }
   }

   public boolean hasDataValidation() {
      return this.dataValidation;
   }

   public boolean hasDropDown() {
      return this.dropDown;
   }

   public void removeComment() {
      this.comment = null;
      Comment var1 = this.commentDrawing;
      if (var1 != null) {
         this.writableCell.removeComment(var1);
         this.commentDrawing = null;
      }

   }

   public void removeDataValidation() {
      if (this.dataValidation) {
         DVParser var1 = this.getDVParser();
         if (var1.extendedCellsValidation()) {
            logger.warn("Cannot remove data validation from " + jxl.CellReferenceHelper.getCellReference(this.writableCell) + " as it is part of the shared reference " + jxl.CellReferenceHelper.getCellReference(var1.getFirstColumn(), var1.getFirstRow()) + "-" + jxl.CellReferenceHelper.getCellReference(var1.getLastColumn(), var1.getLastRow()));
         } else {
            this.writableCell.removeDataValidation();
            this.clearValidationSettings();
         }
      }
   }

   public void removeSharedDataValidation() {
      if (this.dataValidation) {
         this.writableCell.removeDataValidation();
         this.clearValidationSettings();
      }
   }

   public void setComboBox(ComboBox var1) {
      this.comboBox = var1;
   }

   public void setComment(String var1) {
      this.setComment(var1, 3.0, 4.0);
   }

   public void setComment(String var1, double var2, double var4) {
      this.comment = var1;
      this.commentWidth = var2;
      this.commentHeight = var4;
      Comment var6 = this.commentDrawing;
      if (var6 != null) {
         var6.setCommentText(var1);
         this.commentDrawing.setWidth(var2);
         this.commentDrawing.setWidth(var4);
      }

   }

   public final void setCommentDrawing(Comment var1) {
      this.commentDrawing = var1;
   }

   public void setDataValidationList(Collection var1) {
      if (this.dataValidation && this.getDVParser().extendedCellsValidation()) {
         logger.warn("Cannot set data validation on " + jxl.CellReferenceHelper.getCellReference(this.writableCell) + " as it is part of a shared data validation");
      } else {
         this.clearValidationSettings();
         this.dvParser = new DVParser(var1);
         this.dropDown = true;
         this.dataValidation = true;
      }
   }

   public void setDataValidationRange(int var1, int var2, int var3, int var4) {
      if (this.dataValidation && this.getDVParser().extendedCellsValidation()) {
         logger.warn("Cannot set data validation on " + jxl.CellReferenceHelper.getCellReference(this.writableCell) + " as it is part of a shared data validation");
      } else {
         this.clearValidationSettings();
         this.dvParser = new DVParser(var1, var2, var3, var4);
         this.dropDown = true;
         this.dataValidation = true;
      }
   }

   public void setDataValidationRange(String var1) {
      if (this.dataValidation && this.getDVParser().extendedCellsValidation()) {
         logger.warn("Cannot set data validation on " + jxl.CellReferenceHelper.getCellReference(this.writableCell) + " as it is part of a shared data validation");
      } else {
         this.clearValidationSettings();
         this.dvParser = new DVParser(var1);
         this.dropDown = true;
         this.dataValidation = true;
      }
   }

   public void setNumberValidation(double var1, double var3, ValidationCondition var5) {
      if (this.dataValidation && this.getDVParser().extendedCellsValidation()) {
         logger.warn("Cannot set data validation on " + jxl.CellReferenceHelper.getCellReference(this.writableCell) + " as it is part of a shared data validation");
      } else {
         this.clearValidationSettings();
         this.dvParser = new DVParser(var1, var3, var5.getCondition());
         this.dropDown = false;
         this.dataValidation = true;
      }
   }

   public void setNumberValidation(double var1, ValidationCondition var3) {
      if (this.dataValidation && this.getDVParser().extendedCellsValidation()) {
         logger.warn("Cannot set data validation on " + jxl.CellReferenceHelper.getCellReference(this.writableCell) + " as it is part of a shared data validation");
      } else {
         this.clearValidationSettings();
         this.dvParser = new DVParser(var1, Double.NaN, var3.getCondition());
         this.dropDown = false;
         this.dataValidation = true;
      }
   }

   public void setReadComment(String var1, double var2, double var4) {
      this.comment = var1;
      this.commentWidth = var2;
      this.commentHeight = var4;
   }

   public void setValidationSettings(DataValiditySettingsRecord var1) {
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      this.validationSettings = var1;
      this.dataValidation = true;
   }

   public final void setWritableCell(CellValue var1) {
      this.writableCell = var1;
   }

   public void shareDataValidation(BaseCellFeatures var1) {
      if (this.dataValidation) {
         logger.warn("Attempting to share a data validation on cell " + jxl.CellReferenceHelper.getCellReference(this.writableCell) + " which already has a data validation");
      } else {
         this.clearValidationSettings();
         this.dvParser = var1.getDVParser();
         this.validationSettings = null;
         this.dataValidation = true;
         this.dropDown = var1.dropDown;
         this.comboBox = var1.comboBox;
      }
   }

   protected static class ValidationCondition {
      private static ValidationCondition[] types = new ValidationCondition[0];
      private DVParser.Condition condition;

      ValidationCondition(DVParser.Condition var1) {
         this.condition = var1;
         ValidationCondition[] var2 = types;
         ValidationCondition[] var3 = new ValidationCondition[var2.length + 1];
         types = var3;
         System.arraycopy(var2, 0, var3, 0, var2.length);
         types[var2.length] = this;
      }

      public DVParser.Condition getCondition() {
         return this.condition;
      }
   }
}
