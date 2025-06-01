package jxl.write.biff;

import jxl.biff.Fonts;
import jxl.biff.FormattingRecords;
import jxl.biff.NumFormatRecordsException;
import jxl.common.Assert;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;

public class WritableFormattingRecords extends FormattingRecords {
   public static WritableCellFormat normalStyle;

   public WritableFormattingRecords(Fonts var1, Styles var2) {
      super(var1);

      try {
         StyleXFRecord var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(2), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(3), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         var4 = new StyleXFRecord(var2.getArial10Pt(), NumberFormats.DEFAULT);
         var4.setLocked(true);
         var4.setCellOptions(62464);
         this.addStyle(var4);
         this.addStyle(var2.getNormalStyle());
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.FORMAT7);
         var4.setLocked(true);
         var4.setCellOptions(63488);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.FORMAT5);
         var4.setLocked(true);
         var4.setCellOptions(63488);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.FORMAT8);
         var4.setLocked(true);
         var4.setCellOptions(63488);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.FORMAT6);
         var4.setLocked(true);
         var4.setCellOptions(63488);
         this.addStyle(var4);
         var4 = new StyleXFRecord(this.getFonts().getFont(1), NumberFormats.PERCENT_INTEGER);
         var4.setLocked(true);
         var4.setCellOptions(63488);
         this.addStyle(var4);
      } catch (NumFormatRecordsException var3) {
         Assert.verify(false, var3.getMessage());
      }

   }
}
