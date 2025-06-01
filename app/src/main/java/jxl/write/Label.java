package jxl.write;

import jxl.LabelCell;
import jxl.format.CellFormat;
import jxl.write.biff.LabelRecord;

public class Label extends LabelRecord implements WritableCell, LabelCell {
   public Label(int var1, int var2, String var3) {
      super(var1, var2, var3);
   }

   public Label(int var1, int var2, String var3, CellFormat var4) {
      super(var1, var2, var3, var4);
   }

   protected Label(int var1, int var2, Label var3) {
      super(var1, var2, (LabelRecord)var3);
   }

   public Label(LabelCell var1) {
      super(var1);
   }

   public WritableCell copyTo(int var1, int var2) {
      return new Label(var1, var2, this);
   }

   public void setString(String var1) {
      super.setString(var1);
   }
}
