package jxl.write.biff;

import jxl.biff.Fonts;
import jxl.write.WritableFont;

public class WritableFonts extends Fonts {
   public WritableFonts(WritableWorkbookImpl var1) {
      this.addFont(var1.getStyles().getArial10Pt());
      this.addFont(new WritableFont(WritableFont.ARIAL));
      this.addFont(new WritableFont(WritableFont.ARIAL));
      this.addFont(new WritableFont(WritableFont.ARIAL));
   }
}
