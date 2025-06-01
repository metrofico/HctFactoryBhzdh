package jxl.write;

import java.text.DecimalFormat;
import jxl.biff.DisplayFormat;
import jxl.write.biff.NumberFormatRecord;

public class NumberFormat extends NumberFormatRecord implements DisplayFormat {
   public static final NonValidatingFormat COMPLEX_FORMAT = new NonValidatingFormat();
   public static final String CURRENCY_DOLLAR = "[$$-409]";
   public static final String CURRENCY_EURO_PREFIX = "[$�-2]";
   public static final String CURRENCY_EURO_SUFFIX = "[$�-1]";
   public static final String CURRENCY_JAPANESE_YEN = "[$�-411]";
   public static final String CURRENCY_POUND = "�";
   public static final String FRACTIONS_EIGHTHS = "?/8";
   public static final String FRACTION_HALVES = "?/2";
   public static final String FRACTION_HUNDREDTHS = "?/100";
   public static final String FRACTION_QUARTERS = "?/4";
   public static final String FRACTION_SIXTEENTHS = "?/16";
   public static final String FRACTION_TENTHS = "?/10";
   public static final String FRACTION_THREE_DIGITS = "???/???";

   public NumberFormat(String var1) {
      super(var1);
      new DecimalFormat(var1);
   }

   public NumberFormat(String var1, NonValidatingFormat var2) {
      super(var1, var2);
   }
}
