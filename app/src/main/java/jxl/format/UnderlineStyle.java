package jxl.format;

public final class UnderlineStyle {
   public static final UnderlineStyle DOUBLE = new UnderlineStyle(2, "double");
   public static final UnderlineStyle DOUBLE_ACCOUNTING = new UnderlineStyle(34, "double accounting");
   public static final UnderlineStyle NO_UNDERLINE = new UnderlineStyle(0, "none");
   public static final UnderlineStyle SINGLE = new UnderlineStyle(1, "single");
   public static final UnderlineStyle SINGLE_ACCOUNTING = new UnderlineStyle(33, "single accounting");
   private static UnderlineStyle[] styles = new UnderlineStyle[0];
   private String string;
   private int value;

   protected UnderlineStyle(int var1, String var2) {
      this.value = var1;
      this.string = var2;
      UnderlineStyle[] var3 = styles;
      UnderlineStyle[] var4 = new UnderlineStyle[var3.length + 1];
      styles = var4;
      System.arraycopy(var3, 0, var4, 0, var3.length);
      styles[var3.length] = this;
   }

   public static UnderlineStyle getStyle(int var0) {
      int var1 = 0;

      while(true) {
         UnderlineStyle[] var2 = styles;
         if (var1 >= var2.length) {
            return NO_UNDERLINE;
         }

         if (var2[var1].getValue() == var0) {
            return styles[var1];
         }

         ++var1;
      }
   }

   public String getDescription() {
      return this.string;
   }

   public int getValue() {
      return this.value;
   }
}
