package jxl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import jxl.read.biff.BiffException;
import jxl.read.biff.PasswordException;
import jxl.read.biff.WorkbookParser;
import jxl.write.WritableWorkbook;
import jxl.write.biff.WritableWorkbookImpl;

public abstract class Workbook {
   private static final String VERSION = "2.6.12";

   protected Workbook() {
   }

   public static WritableWorkbook createWorkbook(File var0) throws IOException {
      return createWorkbook(var0, new WorkbookSettings());
   }

   public static WritableWorkbook createWorkbook(File var0, Workbook var1) throws IOException {
      return createWorkbook(var0, var1, new WorkbookSettings());
   }

   public static WritableWorkbook createWorkbook(File var0, Workbook var1, WorkbookSettings var2) throws IOException {
      return new WritableWorkbookImpl(new FileOutputStream(var0), var1, true, var2);
   }

   public static WritableWorkbook createWorkbook(File var0, WorkbookSettings var1) throws IOException {
      return new WritableWorkbookImpl(new FileOutputStream(var0), true, var1);
   }

   public static WritableWorkbook createWorkbook(OutputStream var0) throws IOException {
      return createWorkbook(var0, new WorkbookSettings());
   }

   public static WritableWorkbook createWorkbook(OutputStream var0, Workbook var1) throws IOException {
      return createWorkbook(var0, var1, ((WorkbookParser)var1).getSettings());
   }

   public static WritableWorkbook createWorkbook(OutputStream var0, Workbook var1, WorkbookSettings var2) throws IOException {
      return new WritableWorkbookImpl(var0, var1, false, var2);
   }

   public static WritableWorkbook createWorkbook(OutputStream var0, WorkbookSettings var1) throws IOException {
      return new WritableWorkbookImpl(var0, false, var1);
   }

   public static String getVersion() {
      return "2.6.12";
   }

   public static Workbook getWorkbook(File var0) throws IOException, BiffException {
      return getWorkbook(var0, new WorkbookSettings());
   }

   public static Workbook getWorkbook(File var0, WorkbookSettings var1) throws IOException, BiffException {
      FileInputStream var5 = new FileInputStream(var0);

      jxl.read.biff.File var2;
      try {
         var2 = new jxl.read.biff.File(var5, var1);
      } catch (IOException var3) {
         var5.close();
         throw var3;
      } catch (BiffException var4) {
         var5.close();
         throw var4;
      }

      var5.close();
      WorkbookParser var6 = new WorkbookParser(var2, var1);
      var6.parse();
      return var6;
   }

   public static Workbook getWorkbook(InputStream var0) throws IOException, BiffException {
      return getWorkbook(var0, new WorkbookSettings());
   }

   public static Workbook getWorkbook(InputStream var0, WorkbookSettings var1) throws IOException, BiffException {
      WorkbookParser var2 = new WorkbookParser(new jxl.read.biff.File(var0, var1), var1);
      var2.parse();
      return var2;
   }

   public abstract void close();

   public abstract Range[] findByName(String var1);

   public abstract Cell findCellByName(String var1);

   public abstract Cell getCell(String var1);

   public abstract int getNumberOfSheets();

   public abstract String[] getRangeNames();

   public abstract Sheet getSheet(int var1) throws IndexOutOfBoundsException;

   public abstract Sheet getSheet(String var1);

   public abstract String[] getSheetNames();

   public abstract Sheet[] getSheets();

   public abstract boolean isProtected();

   protected abstract void parse() throws BiffException, PasswordException;
}
