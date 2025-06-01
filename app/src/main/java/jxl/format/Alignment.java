package jxl.format;

public class Alignment {
   public static Alignment CENTRE = new Alignment(2, "centre");
   public static Alignment FILL = new Alignment(4, "fill");
   public static Alignment GENERAL = new Alignment(0, "general");
   public static Alignment JUSTIFY = new Alignment(5, "justify");
   public static Alignment LEFT = new Alignment(1, "left");
   public static Alignment RIGHT = new Alignment(3, "right");
   private static Alignment[] alignments = new Alignment[0];
   private String string;
   private int value;

   protected Alignment(int var1, String var2) {
      this.value = var1;
      this.string = var2;
      Alignment[] var4 = alignments;
      Alignment[] var3 = new Alignment[var4.length + 1];
      alignments = var3;
      System.arraycopy(var4, 0, var3, 0, var4.length);
      alignments[var4.length] = this;
   }

   public static Alignment getAlignment(int var0) {
      int var1 = 0;

      while(true) {
         Alignment[] var2 = alignments;
         if (var1 >= var2.length) {
            return GENERAL;
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
