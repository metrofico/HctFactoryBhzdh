package jxl.format;

public class BorderLineStyle {
   public static final BorderLineStyle DASHED = new BorderLineStyle(3, "dashed");
   public static final BorderLineStyle DASH_DOT = new BorderLineStyle(9, "dash dot");
   public static final BorderLineStyle DASH_DOT_DOT = new BorderLineStyle(11, "Dash dot dot");
   public static final BorderLineStyle DOTTED = new BorderLineStyle(4, "dotted");
   public static final BorderLineStyle DOUBLE = new BorderLineStyle(6, "double");
   public static final BorderLineStyle HAIR = new BorderLineStyle(7, "hair");
   public static final BorderLineStyle MEDIUM = new BorderLineStyle(2, "medium");
   public static final BorderLineStyle MEDIUM_DASHED = new BorderLineStyle(8, "medium dashed");
   public static final BorderLineStyle MEDIUM_DASH_DOT = new BorderLineStyle(10, "medium dash dot");
   public static final BorderLineStyle MEDIUM_DASH_DOT_DOT = new BorderLineStyle(12, "Medium dash dot dot");
   public static final BorderLineStyle NONE = new BorderLineStyle(0, "none");
   public static final BorderLineStyle SLANTED_DASH_DOT = new BorderLineStyle(13, "Slanted dash dot");
   public static final BorderLineStyle THICK = new BorderLineStyle(5, "thick");
   public static final BorderLineStyle THIN = new BorderLineStyle(1, "thin");
   private static BorderLineStyle[] styles = new BorderLineStyle[0];
   private String string;
   private int value;

   protected BorderLineStyle(int var1, String var2) {
      this.value = var1;
      this.string = var2;
      BorderLineStyle[] var4 = styles;
      BorderLineStyle[] var3 = new BorderLineStyle[var4.length + 1];
      styles = var3;
      System.arraycopy(var4, 0, var3, 0, var4.length);
      styles[var4.length] = this;
   }

   public static BorderLineStyle getStyle(int var0) {
      int var1 = 0;

      while(true) {
         BorderLineStyle[] var2 = styles;
         if (var1 >= var2.length) {
            return NONE;
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
