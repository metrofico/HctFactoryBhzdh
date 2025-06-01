package jxl.format;

public final class ScriptStyle {
   public static final ScriptStyle NORMAL_SCRIPT = new ScriptStyle(0, "normal");
   public static final ScriptStyle SUBSCRIPT = new ScriptStyle(2, "sub");
   public static final ScriptStyle SUPERSCRIPT = new ScriptStyle(1, "super");
   private static ScriptStyle[] styles = new ScriptStyle[0];
   private String string;
   private int value;

   protected ScriptStyle(int var1, String var2) {
      this.value = var1;
      this.string = var2;
      ScriptStyle[] var4 = styles;
      ScriptStyle[] var3 = new ScriptStyle[var4.length + 1];
      styles = var3;
      System.arraycopy(var4, 0, var3, 0, var4.length);
      styles[var4.length] = this;
   }

   public static ScriptStyle getStyle(int var0) {
      int var1 = 0;

      while(true) {
         ScriptStyle[] var2 = styles;
         if (var1 >= var2.length) {
            return NORMAL_SCRIPT;
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
