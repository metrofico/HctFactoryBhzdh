package jxl.format;

public class Border {
   public static final Border ALL = new Border("all");
   public static final Border BOTTOM = new Border("bottom");
   public static final Border LEFT = new Border("left");
   public static final Border NONE = new Border("none");
   public static final Border RIGHT = new Border("right");
   public static final Border TOP = new Border("top");
   private String string;

   protected Border(String var1) {
      this.string = var1;
   }

   public String getDescription() {
      return this.string;
   }
}
