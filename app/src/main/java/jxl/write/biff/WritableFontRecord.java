package jxl.write.biff;

import jxl.biff.FontRecord;
import jxl.format.Font;
import jxl.write.WriteException;

public class WritableFontRecord extends FontRecord {
   protected WritableFontRecord(String var1, int var2, int var3, boolean var4, int var5, int var6, int var7) {
      super(var1, var2, var3, var4, var5, var6, var7);
   }

   protected WritableFontRecord(Font var1) {
      super(var1);
   }

   protected void setBoldStyle(int var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setFontBoldStyle(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   protected void setColour(int var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setFontColour(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   protected void setItalic(boolean var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setFontItalic(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   protected void setPointSize(int var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setFontPointSize(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   protected void setScriptStyle(int var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setFontScriptStyle(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   protected void setStruckout(boolean var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setFontStruckout(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   protected void setUnderlineStyle(int var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setFontUnderlineStyle(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }
}
