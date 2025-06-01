package jxl.format;

public class VerticalAlignment {
   public static VerticalAlignment BOTTOM = new VerticalAlignment(2, "bottom");
   public static VerticalAlignment CENTRE = new VerticalAlignment(1, "centre");
   public static VerticalAlignment JUSTIFY = new VerticalAlignment(3, "Justify");
   public static VerticalAlignment TOP = new VerticalAlignment(0, "top");
   private static VerticalAlignment[] alignments = new VerticalAlignment[0];
   private String string;
   private int value;

   protected VerticalAlignment(int var1, String var2) {
      this.value = var1;
      this.string = var2;
      VerticalAlignment[] var4 = alignments;
      VerticalAlignment[] var3 = new VerticalAlignment[var4.length + 1];
      alignments = var3;
      System.arraycopy(var4, 0, var3, 0, var4.length);
      alignments[var4.length] = this;
   }

   public static VerticalAlignment getAlignment(int var0) {
      int var1 = 0;

      while(true) {
         VerticalAlignment[] var2 = alignments;
         if (var1 >= var2.length) {
            return BOTTOM;
         }

         if (var2[var1].getValue() == var0) {
            return alignments[var1];
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
