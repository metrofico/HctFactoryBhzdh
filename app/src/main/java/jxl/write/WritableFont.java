package jxl.write;

import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.write.biff.WritableFontRecord;

public class WritableFont extends WritableFontRecord {
   public static final FontName ARIAL = new FontName("Arial");
   public static final BoldStyle BOLD = new BoldStyle(700);
   public static final FontName COURIER = new FontName("Courier New");
   public static final int DEFAULT_POINT_SIZE = 10;
   public static final BoldStyle NO_BOLD = new BoldStyle(400);
   public static final FontName TAHOMA = new FontName("Tahoma");
   public static final FontName TIMES = new FontName("Times New Roman");

   public WritableFont(jxl.format.Font var1) {
      super(var1);
   }

   public WritableFont(FontName var1) {
      this(var1, 10, NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
   }

   public WritableFont(FontName var1, int var2) {
      this(var1, var2, NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
   }

   public WritableFont(FontName var1, int var2, BoldStyle var3) {
      this(var1, var2, var3, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
   }

   public WritableFont(FontName var1, int var2, BoldStyle var3, boolean var4) {
      this(var1, var2, var3, var4, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
   }

   public WritableFont(FontName var1, int var2, BoldStyle var3, boolean var4, UnderlineStyle var5) {
      this(var1, var2, var3, var4, var5, jxl.format.Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
   }

   public WritableFont(FontName var1, int var2, BoldStyle var3, boolean var4, UnderlineStyle var5, jxl.format.Colour var6) {
      this(var1, var2, var3, var4, var5, var6, ScriptStyle.NORMAL_SCRIPT);
   }

   public WritableFont(FontName var1, int var2, BoldStyle var3, boolean var4, UnderlineStyle var5, jxl.format.Colour var6, ScriptStyle var7) {
      super(var1.name, var2, var3.value, var4, var5.getValue(), var6.getValue(), var7.getValue());
   }

   public static FontName createFont(String var0) {
      return new FontName(var0);
   }

   public boolean isStruckout() {
      return super.isStruckout();
   }

   public void setBoldStyle(BoldStyle var1) throws WriteException {
      super.setBoldStyle(var1.value);
   }

   public void setColour(jxl.format.Colour var1) throws WriteException {
      super.setColour(var1.getValue());
   }

   public void setItalic(boolean var1) throws WriteException {
      super.setItalic(var1);
   }

   public void setPointSize(int var1) throws WriteException {
      super.setPointSize(var1);
   }

   public void setScriptStyle(ScriptStyle var1) throws WriteException {
      super.setScriptStyle(var1.getValue());
   }

   public void setStruckout(boolean var1) throws WriteException {
      super.setStruckout(var1);
   }

   public void setUnderlineStyle(UnderlineStyle var1) throws WriteException {
      super.setUnderlineStyle(var1.getValue());
   }

   static class BoldStyle {
      public int value;

      BoldStyle(int var1) {
         this.value = var1;
      }
   }

   public static class FontName {
      String name;

      FontName(String var1) {
         this.name = var1;
      }
   }
}
