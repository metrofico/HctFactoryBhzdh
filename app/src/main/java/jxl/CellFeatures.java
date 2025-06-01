package jxl;

import jxl.biff.BaseCellFeatures;

public class CellFeatures extends BaseCellFeatures {
   public CellFeatures() {
   }

   protected CellFeatures(CellFeatures var1) {
      super(var1);
   }

   public String getComment() {
      return super.getComment();
   }

   public String getDataValidationList() {
      return super.getDataValidationList();
   }

   public Range getSharedDataValidationRange() {
      return super.getSharedDataValidationRange();
   }
}
