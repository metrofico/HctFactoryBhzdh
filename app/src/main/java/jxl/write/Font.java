package jxl.write;

import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;

public class Font extends WritableFont {
   public static final FontName ARIAL;
   public static final BoldStyle BOLD;
   public static final UnderlineStyle DOUBLE;
   public static final UnderlineStyle DOUBLE_ACCOUNTING;
   public static final ScriptStyle NORMAL_SCRIPT;
   public static final BoldStyle NO_BOLD;
   public static final UnderlineStyle NO_UNDERLINE;
   public static final UnderlineStyle SINGLE;
   public static final UnderlineStyle SINGLE_ACCOUNTING;
   public static final ScriptStyle SUBSCRIPT;
   public static final ScriptStyle SUPERSCRIPT;
   public static final FontName TIMES;

   static {
      ARIAL = WritableFont.ARIAL;
      TIMES = WritableFont.TIMES;
      NO_BOLD = WritableFont.NO_BOLD;
      BOLD = WritableFont.BOLD;
      NO_UNDERLINE = UnderlineStyle.NO_UNDERLINE;
      SINGLE = UnderlineStyle.SINGLE;
      DOUBLE = UnderlineStyle.DOUBLE;
      SINGLE_ACCOUNTING = UnderlineStyle.SINGLE_ACCOUNTING;
      DOUBLE_ACCOUNTING = UnderlineStyle.DOUBLE_ACCOUNTING;
      NORMAL_SCRIPT = ScriptStyle.NORMAL_SCRIPT;
      SUPERSCRIPT = ScriptStyle.SUPERSCRIPT;
      SUBSCRIPT = ScriptStyle.SUBSCRIPT;
   }

   public Font(FontName var1) {
      super(var1);
   }

   public Font(FontName var1, int var2) {
      super(var1, var2);
   }

   public Font(FontName var1, int var2, BoldStyle var3) {
      super(var1, var2, var3);
   }

   public Font(FontName var1, int var2, BoldStyle var3, boolean var4) {
      super(var1, var2, var3, var4);
   }

   public Font(FontName var1, int var2, BoldStyle var3, boolean var4, UnderlineStyle var5) {
      super(var1, var2, var3, var4, var5);
   }

   public Font(FontName var1, int var2, BoldStyle var3, boolean var4, UnderlineStyle var5, jxl.format.Colour var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public Font(FontName var1, int var2, BoldStyle var3, boolean var4, UnderlineStyle var5, jxl.format.Colour var6, ScriptStyle var7) {
      super(var1, var2, var3, var4, var5, var6, var7);
   }
}
