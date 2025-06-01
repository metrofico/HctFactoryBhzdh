package jxl.write.biff;

import jxl.biff.DisplayFormat;
import jxl.biff.FontRecord;
import jxl.biff.XFRecord;

public class StyleXFRecord extends XFRecord {
   public StyleXFRecord(FontRecord var1, DisplayFormat var2) {
      super(var1, var2);
      this.setXFDetails(XFRecord.style, 65520);
   }

   public final void setCellOptions(int var1) {
      super.setXFCellOptions(var1);
   }

   public void setLocked(boolean var1) {
      super.setXFLocked(var1);
   }
}
