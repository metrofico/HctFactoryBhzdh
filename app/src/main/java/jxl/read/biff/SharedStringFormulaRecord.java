package jxl.read.biff;

import jxl.CellType;
import jxl.LabelCell;
import jxl.StringFormulaCell;
import jxl.WorkbookSettings;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Assert;
import jxl.common.Logger;

public class SharedStringFormulaRecord extends BaseSharedFormulaRecord implements LabelCell, FormulaData, StringFormulaCell {
   protected static final EmptyString EMPTY_STRING = new EmptyString();
   private static Logger logger = Logger.getLogger(SharedStringFormulaRecord.class);
   private String value;

   public SharedStringFormulaRecord(Record var1, File var2, FormattingRecords var3, ExternalSheet var4, WorkbookMethods var5, SheetImpl var6, WorkbookSettings var7) {
      super(var1, var3, var4, var5, var6, var2.getPos());
      int var10 = var2.getPos();
      int var11 = var2.getPos();
      var1 = var2.next();
      boolean var9 = false;

      int var8;
      for(var8 = 0; var1.getType() != Type.STRING && var8 < 4; ++var8) {
         var1 = var2.next();
      }

      boolean var13;
      if (var8 < 4) {
         var13 = true;
      } else {
         var13 = false;
      }

      Assert.verify(var13, " @ " + var10);
      byte[] var14 = var1.getData();

      Record var17;
      for(Record var15 = var2.peek(); var15.getType() == Type.CONTINUE; var15 = var17) {
         var17 = var2.next();
         byte[] var16 = new byte[var14.length + var17.getLength() - 1];
         System.arraycopy(var14, 0, var16, 0, var14.length);
         System.arraycopy(var17.getData(), 1, var16, var14.length, var17.getLength() - 1);
         var17 = var2.peek();
         var14 = var16;
      }

      int var12 = IntegerHelper.getInt(var14[0], var14[1]);
      var8 = var14.length;
      byte var19 = 3;
      byte var18;
      if (var8 == var12 + 2) {
         var18 = 2;
      } else {
         var18 = var19;
         if (var14[2] == 1) {
            var9 = true;
            var18 = var19;
         }
      }

      if (!var9) {
         this.value = StringHelper.getString(var14, var12, var18, var7);
      } else {
         this.value = StringHelper.getUnicodeString(var14, var12, var18);
      }

      var2.setPos(var11);
   }

   public SharedStringFormulaRecord(Record var1, File var2, FormattingRecords var3, ExternalSheet var4, WorkbookMethods var5, SheetImpl var6, EmptyString var7) {
      super(var1, var3, var4, var5, var6, var2.getPos());
      this.value = "";
   }

   public String getContents() {
      return this.value;
   }

   public byte[] getFormulaData() throws FormulaException {
      if (this.getSheet().getWorkbookBof().isBiff8()) {
         FormulaParser var2 = new FormulaParser(this.getTokens(), this, this.getExternalSheet(), this.getNameTable(), this.getSheet().getWorkbook().getSettings());
         var2.parse();
         byte[] var3 = var2.getBytes();
         int var1 = var3.length + 22;
         byte[] var4 = new byte[var1];
         IntegerHelper.getTwoBytes(this.getRow(), var4, 0);
         IntegerHelper.getTwoBytes(this.getColumn(), var4, 2);
         IntegerHelper.getTwoBytes(this.getXFIndex(), var4, 4);
         var4[6] = 0;
         var4[12] = -1;
         var4[13] = -1;
         System.arraycopy(var3, 0, var4, 22, var3.length);
         IntegerHelper.getTwoBytes(var3.length, var4, 20);
         var1 -= 6;
         var3 = new byte[var1];
         System.arraycopy(var4, 6, var3, 0, var1);
         return var3;
      } else {
         throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
      }
   }

   public String getString() {
      return this.value;
   }

   public CellType getType() {
      return CellType.STRING_FORMULA;
   }

   private static final class EmptyString {
      private EmptyString() {
      }

      // $FF: synthetic method
      EmptyString(Object var1) {
         this();
      }
   }
}
