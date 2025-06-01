package jxl.read.biff;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import jxl.WorkbookSettings;
import jxl.biff.BaseCompoundFile;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.common.Logger;

public class File {
   private static Logger logger = Logger.getLogger(File.class);
   private int arrayGrowSize;
   private CompoundFile compoundFile;
   private byte[] data;
   private int filePos;
   private int initialFileSize;
   private int oldPos;
   private WorkbookSettings workbookSettings;

   public File(InputStream var1, WorkbookSettings var2) throws IOException, BiffException {
      this.workbookSettings = var2;
      this.initialFileSize = var2.getInitialFileSize();
      this.arrayGrowSize = this.workbookSettings.getArrayGrowSize();
      byte[] var5 = new byte[this.initialFileSize];
      int var4 = var1.read(var5);
      if (!Thread.currentThread().isInterrupted()) {
         int var3;
         byte[] var6;
         for(var3 = var4; var4 != -1; var5 = var6) {
            var6 = var5;
            if (var3 >= var5.length) {
               var6 = new byte[var5.length + this.arrayGrowSize];
               System.arraycopy(var5, 0, var6, 0, var5.length);
            }

            var4 = var1.read(var6, var3, var6.length - var3);
            var3 += var4;
            if (Thread.currentThread().isInterrupted()) {
               throw new InterruptedIOException();
            }
         }

         if (var3 + 1 != 0) {
            CompoundFile var8 = new CompoundFile(var5, var2);

            try {
               this.data = var8.getStream("workbook");
            } catch (BiffException var7) {
               this.data = var8.getStream("book");
            }

            if (!this.workbookSettings.getPropertySetsDisabled() && var8.getNumberOfPropertySets() > BaseCompoundFile.STANDARD_PROPERTY_SETS.length) {
               this.compoundFile = var8;
            }

            if (!this.workbookSettings.getGCDisabled()) {
               System.gc();
            }

         } else {
            throw new BiffException(BiffException.excelFileNotFound);
         }
      } else {
         throw new InterruptedIOException();
      }
   }

   public File(byte[] var1) {
      this.data = var1;
   }

   private void moveToFirstBof() {
      boolean var1 = false;

      while(!var1) {
         byte[] var3 = this.data;
         int var2 = this.filePos;
         if (IntegerHelper.getInt(var3[var2], var3[var2 + 1]) == Type.BOF.value) {
            var1 = true;
         } else {
            this.skip(128);
         }
      }

   }

   public void clear() {
      this.data = null;
   }

   public void close() {
   }

   CompoundFile getCompoundFile() {
      return this.compoundFile;
   }

   public int getPos() {
      return this.filePos;
   }

   public boolean hasNext() {
      boolean var1;
      if (this.filePos < this.data.length - 4) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   Record next() {
      return new Record(this.data, this.filePos, this);
   }

   Record peek() {
      int var1 = this.filePos;
      Record var2 = new Record(this.data, this.filePos, this);
      this.filePos = var1;
      return var2;
   }

   public byte[] read(int var1, int var2) {
      byte[] var3 = new byte[var2];

      try {
         System.arraycopy(this.data, var1, var3, 0, var2);
         return var3;
      } catch (ArrayIndexOutOfBoundsException var4) {
         logger.error("Array index out of bounds at position " + var1 + " record length " + var2);
         throw var4;
      }
   }

   public void restorePos() {
      this.filePos = this.oldPos;
   }

   public void setPos(int var1) {
      this.oldPos = this.filePos;
      this.filePos = var1;
   }

   public void skip(int var1) {
      this.filePos += var1;
   }
}
