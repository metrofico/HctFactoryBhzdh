package jxl.write;

import java.text.SimpleDateFormat;
import jxl.biff.DisplayFormat;
import jxl.write.biff.DateFormatRecord;

public class DateFormat extends DateFormatRecord implements DisplayFormat {
   public DateFormat(String var1) {
      super(var1);
      new SimpleDateFormat(var1);
   }
}
