package jxl.biff.formula;

import jxl.WorkbookSettings;
import jxl.biff.StringHelper;
import jxl.common.Logger;

class StringValue extends Operand implements ParsedThing {
   private static final Logger logger = Logger.getLogger(StringValue.class);
   private WorkbookSettings settings;
   private String value;

   public StringValue(String var1) {
      this.value = var1;
   }

   public StringValue(WorkbookSettings var1) {
      this.settings = var1;
   }

   byte[] getBytes() {
      byte[] var1 = new byte[this.value.length() * 2 + 3];
      var1[0] = Token.STRING.getCode();
      var1[1] = (byte)this.value.length();
      var1[2] = 1;
      StringHelper.getUnicodeBytes(this.value, var1, 3);
      return var1;
   }

   public void getString(StringBuffer var1) {
      var1.append("\"");
      var1.append(this.value);
      var1.append("\"");
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      int var3 = var1[var2] & 255;
      if ((var1[var2 + 1] & 1) == 0) {
         this.value = StringHelper.getString(var1, var3, var2 + 2, this.settings);
         var2 = var3;
      } else {
         this.value = StringHelper.getUnicodeString(var1, var3, var2 + 2);
         var2 = var3 * 2;
      }

      return var2 + 2;
   }
}
