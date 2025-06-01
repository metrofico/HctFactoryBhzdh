package jxl.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import jxl.WorkbookSettings;
import jxl.biff.BaseCompoundFile;
import jxl.read.biff.BiffException;
import jxl.read.biff.CompoundFile;

class PropertySetsReader {
   private CompoundFile compoundFile;
   private BufferedWriter writer;

   public PropertySetsReader(File var1, String var2, OutputStream var3) throws IOException, BiffException {
      this.writer = new BufferedWriter(new OutputStreamWriter(var3));
      FileInputStream var7 = new FileInputStream(var1);
      byte[] var8 = new byte[1048576];
      int var5 = var7.read(var8);

      byte[] var6;
      for(int var4 = var5; var5 != -1; var8 = var6) {
         var6 = var8;
         if (var4 >= var8.length) {
            var6 = new byte[var8.length + 1048576];
            System.arraycopy(var8, 0, var6, 0, var8.length);
         }

         var5 = var7.read(var6, var4, var6.length - var4);
         var4 += var5;
      }

      this.compoundFile = new CompoundFile(var8, new WorkbookSettings());
      var7.close();
      if (var2 == null) {
         this.displaySets();
      } else {
         this.displayPropertySet(var2, var3);
      }

   }

   void displayPropertySet(String var1, OutputStream var2) throws IOException, BiffException {
      String var3;
      if (var1.equalsIgnoreCase("SummaryInformation")) {
         var3 = "\u0005SummaryInformation";
      } else if (var1.equalsIgnoreCase("DocumentSummaryInformation")) {
         var3 = "\u0005DocumentSummaryInformation";
      } else {
         var3 = var1;
         if (var1.equalsIgnoreCase("CompObj")) {
            var3 = "\u0001CompObj";
         }
      }

      var2.write(this.compoundFile.getStream(var3));
   }

   void displaySets() throws IOException {
      int var2 = this.compoundFile.getNumberOfPropertySets();

      for(int var1 = 0; var1 < var2; ++var1) {
         BaseCompoundFile.PropertyStorage var3 = this.compoundFile.getPropertySet(var1);
         this.writer.write(Integer.toString(var1));
         this.writer.write(") ");
         this.writer.write(var3.name);
         this.writer.write("(type ");
         this.writer.write(Integer.toString(var3.type));
         this.writer.write(" size ");
         this.writer.write(Integer.toString(var3.size));
         this.writer.write(" prev ");
         this.writer.write(Integer.toString(var3.previous));
         this.writer.write(" next ");
         this.writer.write(Integer.toString(var3.next));
         this.writer.write(" child ");
         this.writer.write(Integer.toString(var3.child));
         this.writer.write(" start block ");
         this.writer.write(Integer.toString(var3.startBlock));
         this.writer.write(")");
         this.writer.newLine();
      }

      this.writer.flush();
      this.writer.close();
   }
}
