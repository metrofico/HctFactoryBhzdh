package jxl.format;

public class BoldStyle {
   public static final BoldStyle BOLD = new BoldStyle(700, "Bold");
   public static final BoldStyle NORMAL = new BoldStyle(400, "Normal");
   private String string;
   private int value;

   protected BoldStyle(int var1, String var2) {
      this.value = var1;
      this.string = var2;
   }

   public String getDescription() {
      return this.string;
   }

   public int getValue() {
      return this.value;
   }
}
