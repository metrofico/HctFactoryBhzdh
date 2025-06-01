package jxl.demo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import jxl.Cell;
import jxl.CellType;
import jxl.Workbook;
import jxl.common.Logger;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Blank;
import jxl.write.DateFormat;
import jxl.write.DateFormats;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ReadWrite {
   private static Logger logger = Logger.getLogger(ReadWrite.class);
   private File inputWorkbook;
   private File outputWorkbook;

   public ReadWrite(String var1, String var2) {
      this.inputWorkbook = new File(var1);
      this.outputWorkbook = new File(var2);
      logger.setSuppressWarnings(Boolean.getBoolean("jxl.nowarnings"));
      logger.info("Input file:  " + var1);
      logger.info("Output file:  " + var2);
   }

   private void modify(WritableWorkbook var1) throws WriteException {
      logger.info("Modifying...");
      WritableSheet var9 = var1.getSheet("modified");
      var9.getWritableCell(1, 3).setCellFormat(new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD)));
      var9.getWritableCell(1, 4).setCellFormat(new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.SINGLE)));
      var9.getWritableCell(1, 5).setCellFormat(new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10)));
      WritableCell var4 = var9.getWritableCell(1, 6);
      if (var4.getType() == CellType.LABEL) {
         Label var10 = (Label)var4;
         var10.setString(var10.getString() + " - mod");
      }

      var9.getWritableCell(1, 9).setCellFormat(new WritableCellFormat(new NumberFormat("#.0000000")));
      var9.getWritableCell(1, 10).setCellFormat(new WritableCellFormat(new NumberFormat("0.####E0")));
      var9.getWritableCell(1, 11).setCellFormat(WritableWorkbook.NORMAL_STYLE);
      var4 = var9.getWritableCell(1, 12);
      if (var4.getType() == CellType.NUMBER) {
         ((Number)var4).setValue(42.0);
      }

      var4 = var9.getWritableCell(1, 13);
      if (var4.getType() == CellType.NUMBER) {
         Number var11 = (Number)var4;
         var11.setValue(var11.getValue() + 0.1);
      }

      var9.getWritableCell(1, 16).setCellFormat(new WritableCellFormat(new DateFormat("dd MMM yyyy HH:mm:ss")));
      WritableCell var5 = var9.getWritableCell(1, 17);
      WritableCellFormat var12 = new WritableCellFormat(DateFormats.FORMAT9);
      var5.setCellFormat(var12);
      var5 = var9.getWritableCell(1, 18);
      if (var5.getType() == CellType.DATE) {
         DateTime var13 = (DateTime)var5;
         Calendar var6 = Calendar.getInstance();
         var6.set(1998, 1, 18, 11, 23, 28);
         var13.setDate(var6.getTime());
      }

      var5 = var9.getWritableCell(1, 22);
      if (var5.getType() == CellType.NUMBER) {
         ((Number)var5).setValue(6.8);
      }

      var5 = var9.getWritableCell(1, 29);
      if (var5.getType() == CellType.LABEL) {
         ((Label)var5).setString("Modified string contents");
      }

      var9.insertRow(34);
      var9.removeRow(38);
      var9.insertColumn(9);
      var9.removeColumn(11);
      var9.removeRow(43);
      var9.insertRow(43);
      WritableHyperlink[] var17 = var9.getWritableHyperlinks();

      int var2;
      for(var2 = 0; var2 < var17.length; ++var2) {
         WritableHyperlink var7 = var17[var2];
         if (var7.getColumn() == 1 && var7.getRow() == 39) {
            try {
               URL var16 = new URL("http://www.andykhan.com/jexcelapi/index.html");
               var7.setURL(var16);
            } catch (MalformedURLException var8) {
               logger.warn(var8.toString());
            }
         } else if (var7.getColumn() == 1 && var7.getRow() == 40) {
            var7.setFile(new File("../jexcelapi/docs/overview-summary.html"));
         } else if (var7.getColumn() == 1 && var7.getRow() == 41) {
            var7.setFile(new File("d:/home/jexcelapi/docs/jxl/package-summary.html"));
         } else if (var7.getColumn() == 1 && var7.getRow() == 44) {
            var9.removeHyperlink(var7);
         }
      }

      WritableCell var18 = var9.getWritableCell(5, 30);
      WritableCellFormat var20 = new WritableCellFormat(var18.getCellFormat());
      var20.setBackground(Colour.RED);
      var18.setCellFormat(var20);
      var9.addCell(new Label(0, 49, "Modified merged cells"));
      ((Number)var9.getWritableCell(0, 70)).setValue(9.0);
      ((Number)var9.getWritableCell(0, 71)).setValue(10.0);
      ((Number)var9.getWritableCell(0, 73)).setValue(4.0);
      var9.addCell(new Formula(1, 80, "ROUND(COS(original!B10),2)"));
      var9.addCell(new Formula(1, 83, "value1+value2"));
      var9.addCell(new Formula(1, 84, "AVERAGE(value1,value1*4,value2)"));
      var9.addCell(new Label(0, 88, "Some copied cells", var12));
      var9.addCell(new Label(0, 89, "Number from B9"));
      var9.addCell(var9.getWritableCell(1, 9).copyTo(1, 89));
      var9.addCell(new Label(0, 90, "Label from B4 (modified format)"));
      var9.addCell(var9.getWritableCell(1, 3).copyTo(1, 90));
      var9.addCell(new Label(0, 91, "Date from B17"));
      var9.addCell(var9.getWritableCell(1, 16).copyTo(1, 91));
      var9.addCell(new Label(0, 92, "Boolean from E16"));
      var9.addCell(var9.getWritableCell(4, 15).copyTo(1, 92));
      var9.addCell(new Label(0, 93, "URL from B40"));
      var9.addCell(var9.getWritableCell(1, 39).copyTo(1, 93));

      int var3;
      for(var2 = 0; var2 < 6; var2 = var3) {
         var3 = var2 + 1;
         var9.addCell(new Number(1, var2 + 94, (double)var3 + (double)var2 / 8.0));
      }

      var9.addCell(new Label(0, 100, "Formula from B27"));
      var9.addCell(var9.getWritableCell(1, 26).copyTo(1, 100));
      var9.addCell(new Label(0, 101, "A brand new formula"));
      var9.addCell(new Formula(1, 101, "SUM(B94:B96)"));
      var9.addCell(new Label(0, 102, "A copy of it"));
      var9.addCell(var9.getWritableCell(1, 101).copyTo(1, 102));
      var9.removeImage(var9.getImage(1));
      var9.addImage(new WritableImage(1.0, 116.0, 2.0, 9.0, new File("resources/littlemoretonhall.png")));
      var9.addCell(new Label(0, 151, "Added drop down validation"));
      Blank var22 = new Blank(1, 151);
      WritableCellFeatures var21 = new WritableCellFeatures();
      ArrayList var14 = new ArrayList();
      var14.add("The Fellowship of the Ring");
      var14.add("The Two Towers");
      var14.add("The Return of the King");
      var21.setDataValidationList(var14);
      var22.setCellFeatures(var21);
      var9.addCell(var22);
      var9.addCell(new Label(0, 152, "Added number validation 2.718 < x < 3.142"));
      Blank var15 = new Blank(1, 152);
      WritableCellFeatures var25 = new WritableCellFeatures();
      var25.setNumberValidation(2.718, 3.142, WritableCellFeatures.BETWEEN);
      var15.setCellFeatures(var25);
      var9.addCell(var15);
      ((Label)var9.getWritableCell(0, 156)).setString("Label text modified");
      var9.getWritableCell(0, 157).getWritableCellFeatures().setComment("modified comment text");
      var9.getWritableCell(0, 158).getWritableCellFeatures().removeComment();
      WritableCell var23 = var9.getWritableCell(0, 172);
      var25 = var23.getWritableCellFeatures();
      Cell var19 = var25.getSharedDataValidationRange().getBottomRight();
      var9.removeSharedDataValidation(var23);
      ArrayList var24 = new ArrayList();
      var24.add("Stanley Featherstonehaugh Ukridge");
      var24.add("Major Plank");
      var24.add("Earl of Ickenham");
      var24.add("Sir Gregory Parsloe-Parsloe");
      var24.add("Honoria Glossop");
      var24.add("Stiffy Byng");
      var24.add("Bingo Little");
      var25.setDataValidationList(var24);
      var23.setCellFeatures(var25);
      var9.applySharedDataValidation(var23, var19.getColumn() - var23.getColumn(), 1);
   }

   public void readWrite() throws IOException, BiffException, WriteException {
      logger.info("Reading...");
      Workbook var1 = Workbook.getWorkbook(this.inputWorkbook);
      logger.info("Copying...");
      WritableWorkbook var2 = Workbook.createWorkbook(this.outputWorkbook, var1);
      if (this.inputWorkbook.getName().equals("jxlrwtest.xls")) {
         this.modify(var2);
      }

      var2.write();
      var2.close();
      logger.info("Done");
   }
}
