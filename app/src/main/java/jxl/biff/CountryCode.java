package jxl.biff;

import jxl.common.Logger;

public class CountryCode {
   public static final CountryCode BELGIUM = new CountryCode(32, "BE", "Belgium");
   public static final CountryCode CANADA = new CountryCode(2, "CA", "Canada");
   public static final CountryCode CHINA = new CountryCode(86, "CN", "China");
   public static final CountryCode DENMARK = new CountryCode(45, "DK", "Denmark");
   public static final CountryCode FRANCE = new CountryCode(33, "FR", "France");
   public static final CountryCode GERMANY = new CountryCode(49, "DE", "Germany");
   public static final CountryCode GREECE = new CountryCode(30, "GR", "Greece");
   public static final CountryCode INDIA = new CountryCode(91, "IN", "India");
   public static final CountryCode ITALY = new CountryCode(39, "IT", "Italy");
   public static final CountryCode NETHERLANDS = new CountryCode(31, "NE", "Netherlands");
   public static final CountryCode NORWAY = new CountryCode(47, "NO", "Norway");
   public static final CountryCode PHILIPPINES = new CountryCode(63, "PH", "Philippines");
   public static final CountryCode SPAIN = new CountryCode(34, "ES", "Spain");
   public static final CountryCode SWEDEN = new CountryCode(46, "SE", "Sweden");
   public static final CountryCode SWITZERLAND = new CountryCode(41, "CH", "Switzerland");
   public static final CountryCode UK = new CountryCode(44, "UK", "United Kingdowm");
   public static final CountryCode UNKNOWN = new CountryCode(65535, "??", "Unknown");
   public static final CountryCode USA = new CountryCode(1, "US", "USA");
   private static CountryCode[] codes = new CountryCode[0];
   private static Logger logger = Logger.getLogger(CountryCode.class);
   private String code;
   private String description;
   private int value;

   private CountryCode(int var1) {
      this.value = var1;
      this.description = "Arbitrary";
      this.code = "??";
   }

   private CountryCode(int var1, String var2, String var3) {
      this.value = var1;
      this.code = var2;
      this.description = var3;
      CountryCode[] var5 = codes;
      CountryCode[] var4 = new CountryCode[var5.length + 1];
      System.arraycopy(var5, 0, var4, 0, var5.length);
      var4[codes.length] = this;
      codes = var4;
   }

   public static CountryCode createArbitraryCode(int var0) {
      return new CountryCode(var0);
   }

   public static CountryCode getCountryCode(String var0) {
      if (var0 != null && var0.length() == 2) {
         CountryCode var2 = UNKNOWN;
         int var1 = 0;

         while(true) {
            CountryCode[] var3 = codes;
            if (var1 >= var3.length || var2 != UNKNOWN) {
               return var2;
            }

            if (var3[var1].code.equals(var0)) {
               var2 = codes[var1];
            }

            ++var1;
         }
      } else {
         logger.warn("Please specify two character ISO 3166 country code");
         return USA;
      }
   }

   public String getCode() {
      return this.code;
   }

   public int getValue() {
      return this.value;
   }
}
