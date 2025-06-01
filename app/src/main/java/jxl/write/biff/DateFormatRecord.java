package jxl.write.biff;

import jxl.biff.FormatRecord;

public class DateFormatRecord extends FormatRecord {
   protected DateFormatRecord(String var1) {
      this.setFormatString(this.replace(this.replace(var1, "a", "AM/PM"), "S", "0"));
   }
}
