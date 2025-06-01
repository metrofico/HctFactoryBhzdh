package jxl.write;

import jxl.biff.DisplayFormat;

public final class DateFormats {
   public static final DisplayFormat DEFAULT;
   public static final DisplayFormat FORMAT1;
   public static final DisplayFormat FORMAT10;
   public static final DisplayFormat FORMAT11;
   public static final DisplayFormat FORMAT12;
   public static final DisplayFormat FORMAT2;
   public static final DisplayFormat FORMAT3;
   public static final DisplayFormat FORMAT4;
   public static final DisplayFormat FORMAT5;
   public static final DisplayFormat FORMAT6;
   public static final DisplayFormat FORMAT7;
   public static final DisplayFormat FORMAT8;
   public static final DisplayFormat FORMAT9;

   static {
      BuiltInFormat var0 = new BuiltInFormat(14, "M/d/yy");
      FORMAT1 = var0;
      DEFAULT = var0;
      FORMAT2 = new BuiltInFormat(15, "d-MMM-yy");
      FORMAT3 = new BuiltInFormat(16, "d-MMM");
      FORMAT4 = new BuiltInFormat(17, "MMM-yy");
      FORMAT5 = new BuiltInFormat(18, "h:mm a");
      FORMAT6 = new BuiltInFormat(19, "h:mm:ss a");
      FORMAT7 = new BuiltInFormat(20, "H:mm");
      FORMAT8 = new BuiltInFormat(21, "H:mm:ss");
      FORMAT9 = new BuiltInFormat(22, "M/d/yy H:mm");
      FORMAT10 = new BuiltInFormat(45, "mm:ss");
      FORMAT11 = new BuiltInFormat(46, "H:mm:ss");
      FORMAT12 = new BuiltInFormat(47, "H:mm:ss");
   }

   private static class BuiltInFormat implements DisplayFormat {
      private String formatString;
      private int index;

      public BuiltInFormat(int var1, String var2) {
         this.index = var1;
         this.formatString = var2;
      }

      public boolean equals(Object var1) {
         boolean var2 = true;
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof BuiltInFormat)) {
            return false;
         } else {
            BuiltInFormat var3 = (BuiltInFormat)var1;
            if (this.index != var3.index) {
               var2 = false;
            }

            return var2;
         }
      }

      public int getFormatIndex() {
         return this.index;
      }

      public String getFormatString() {
         return this.formatString;
      }

      public int hashCode() {
         return this.index;
      }

      public void initialize(int var1) {
      }

      public boolean isBuiltIn() {
         return true;
      }

      public boolean isInitialized() {
         return true;
      }
   }
}
