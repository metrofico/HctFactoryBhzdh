package jxl.write;

import java.util.Collection;
import jxl.CellFeatures;
import jxl.biff.BaseCellFeatures;

public class WritableCellFeatures extends CellFeatures {
   public static final ValidationCondition BETWEEN;
   public static final ValidationCondition EQUAL;
   public static final ValidationCondition GREATER_EQUAL;
   public static final ValidationCondition GREATER_THAN;
   public static final ValidationCondition LESS_EQUAL;
   public static final ValidationCondition LESS_THAN;
   public static final ValidationCondition NOT_BETWEEN;
   public static final ValidationCondition NOT_EQUAL;

   static {
      BETWEEN = BaseCellFeatures.BETWEEN;
      NOT_BETWEEN = BaseCellFeatures.NOT_BETWEEN;
      EQUAL = BaseCellFeatures.EQUAL;
      NOT_EQUAL = BaseCellFeatures.NOT_EQUAL;
      GREATER_THAN = BaseCellFeatures.GREATER_THAN;
      LESS_THAN = BaseCellFeatures.LESS_THAN;
      GREATER_EQUAL = BaseCellFeatures.GREATER_EQUAL;
      LESS_EQUAL = BaseCellFeatures.LESS_EQUAL;
   }

   public WritableCellFeatures() {
   }

   public WritableCellFeatures(CellFeatures var1) {
      super(var1);
   }

   public void removeComment() {
      super.removeComment();
   }

   public void removeDataValidation() {
      super.removeDataValidation();
   }

   public void setComment(String var1) {
      super.setComment(var1);
   }

   public void setComment(String var1, double var2, double var4) {
      super.setComment(var1, var2, var4);
   }

   public void setDataValidationList(Collection var1) {
      super.setDataValidationList(var1);
   }

   public void setDataValidationRange(int var1, int var2, int var3, int var4) {
      super.setDataValidationRange(var1, var2, var3, var4);
   }

   public void setDataValidationRange(String var1) {
      super.setDataValidationRange(var1);
   }

   public void setNumberValidation(double var1, double var3, ValidationCondition var5) {
      super.setNumberValidation(var1, var3, var5);
   }

   public void setNumberValidation(double var1, ValidationCondition var3) {
      super.setNumberValidation(var1, var3);
   }
}
