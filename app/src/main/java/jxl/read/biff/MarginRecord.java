package jxl.read.biff;

import jxl.biff.DoubleHelper;
import jxl.biff.RecordData;
import jxl.biff.Type;

abstract class MarginRecord extends RecordData {
   private double margin;

   protected MarginRecord(Type var1, Record var2) {
      super(var1);
      this.margin = DoubleHelper.getIEEEDouble(var2.getData(), 0);
   }

   double getMargin() {
      return this.margin;
   }
}
