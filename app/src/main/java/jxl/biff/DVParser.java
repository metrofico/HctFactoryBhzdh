package jxl.biff;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import jxl.WorkbookSettings;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.biff.formula.ParseContext;
import jxl.common.Assert;
import jxl.common.Logger;

public class DVParser {
   public static final DVType ANY = new DVType(0, "any");
   public static final Condition BETWEEN = new Condition(0, "{0} <= x <= {1}");
   public static final DVType DATE = new DVType(4, "date");
   public static final DVType DECIMAL = new DVType(2, "dec");
   private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#");
   private static final int EMPTY_CELLS_ALLOWED_MASK = 256;
   public static final Condition EQUAL = new Condition(2, "x == {0}");
   public static final DVType FORMULA = new DVType(7, "form");
   public static final Condition GREATER_EQUAL = new Condition(6, "x >= {0}");
   public static final Condition GREATER_THAN = new Condition(4, "x > {0}");
   public static final ErrorStyle INFO = new ErrorStyle(2);
   public static final DVType INTEGER = new DVType(1, "int");
   public static final Condition LESS_EQUAL = new Condition(7, "x <= {0}");
   public static final Condition LESS_THAN = new Condition(5, "x < {0}");
   public static final DVType LIST = new DVType(3, "list");
   private static final int MAX_COLUMNS = 255;
   private static final int MAX_ROWS = 65535;
   private static final int MAX_VALIDATION_LIST_LENGTH = 254;
   public static final Condition NOT_BETWEEN = new Condition(1, "!({0} <= x <= {1}");
   public static final Condition NOT_EQUAL = new Condition(3, "x != {0}");
   private static final int SHOW_ERROR_MASK = 524288;
   private static final int SHOW_PROMPT_MASK = 262144;
   public static final ErrorStyle STOP = new ErrorStyle(0);
   private static final int STRING_LIST_GIVEN_MASK = 128;
   private static final int SUPPRESS_ARROW_MASK = 512;
   public static final DVType TEXT_LENGTH = new DVType(6, "strlen");
   public static final DVType TIME = new DVType(5, "time");
   public static final ErrorStyle WARNING = new ErrorStyle(1);
   private static Logger logger = Logger.getLogger(DVParser.class);
   private int column1;
   private int column2;
   private Condition condition;
   private boolean copied;
   private boolean emptyCellsAllowed;
   private ErrorStyle errorStyle;
   private String errorText;
   private String errorTitle;
   private boolean extendedCellsValidation;
   private FormulaParser formula1;
   private String formula1String;
   private FormulaParser formula2;
   private String formula2String;
   private String promptText;
   private String promptTitle;
   private int row1;
   private int row2;
   private boolean showError;
   private boolean showPrompt;
   private boolean stringListGiven;
   private boolean suppressArrow;
   private DVType type;

   public DVParser(double var1, double var3, Condition var5) {
      this.copied = false;
      this.type = DECIMAL;
      this.errorStyle = STOP;
      this.condition = var5;
      this.extendedCellsValidation = false;
      this.stringListGiven = false;
      this.emptyCellsAllowed = true;
      this.suppressArrow = false;
      this.showPrompt = true;
      this.showError = true;
      this.promptTitle = "\u0000";
      this.errorTitle = "\u0000";
      this.promptText = "\u0000";
      this.errorText = "\u0000";
      this.formula1String = DECIMAL_FORMAT.format(var1);
      if (!Double.isNaN(var3)) {
         this.formula2String = DECIMAL_FORMAT.format(var3);
      }

   }

   public DVParser(int var1, int var2, int var3, int var4) {
      this.copied = false;
      this.type = LIST;
      this.errorStyle = STOP;
      this.condition = BETWEEN;
      this.extendedCellsValidation = false;
      this.stringListGiven = false;
      this.emptyCellsAllowed = true;
      this.suppressArrow = false;
      this.showPrompt = true;
      this.showError = true;
      this.promptTitle = "\u0000";
      this.errorTitle = "\u0000";
      this.promptText = "\u0000";
      this.errorText = "\u0000";
      StringBuffer var5 = new StringBuffer();
      CellReferenceHelper.getCellReference(var1, var2, var5);
      var5.append(':');
      CellReferenceHelper.getCellReference(var3, var4, var5);
      this.formula1String = var5.toString();
   }

   public DVParser(String var1) {
      if (var1.length() == 0) {
         this.copied = false;
         this.type = FORMULA;
         this.errorStyle = STOP;
         this.condition = EQUAL;
         this.extendedCellsValidation = false;
         this.stringListGiven = false;
         this.emptyCellsAllowed = false;
         this.suppressArrow = false;
         this.showPrompt = true;
         this.showError = true;
         this.promptTitle = "\u0000";
         this.errorTitle = "\u0000";
         this.promptText = "\u0000";
         this.errorText = "\u0000";
         this.formula1String = "\"\"";
      } else {
         this.copied = false;
         this.type = LIST;
         this.errorStyle = STOP;
         this.condition = BETWEEN;
         this.extendedCellsValidation = false;
         this.stringListGiven = false;
         this.emptyCellsAllowed = true;
         this.suppressArrow = false;
         this.showPrompt = true;
         this.showError = true;
         this.promptTitle = "\u0000";
         this.errorTitle = "\u0000";
         this.promptText = "\u0000";
         this.errorText = "\u0000";
         this.formula1String = var1;
      }
   }

   public DVParser(Collection var1) {
      this.copied = false;
      this.type = LIST;
      this.errorStyle = STOP;
      this.condition = BETWEEN;
      this.extendedCellsValidation = false;
      this.stringListGiven = true;
      this.emptyCellsAllowed = true;
      this.suppressArrow = false;
      this.showPrompt = true;
      this.showError = true;
      this.promptTitle = "\u0000";
      this.errorTitle = "\u0000";
      this.promptText = "\u0000";
      this.errorText = "\u0000";
      if (var1.size() == 0) {
         logger.warn("no validation strings - ignoring");
      }

      Iterator var3 = var1.iterator();
      StringBuffer var2 = new StringBuffer();
      var2.append(var3.next().toString());

      while(var3.hasNext()) {
         var2.append('\u0000');
         var2.append(' ');
         var2.append(var3.next().toString());
      }

      if (var2.length() > 254) {
         logger.warn("Validation list exceeds maximum number of characters - truncating");
         var2.delete(254, var2.length());
      }

      var2.insert(0, '"');
      var2.append('"');
      this.formula1String = var2.toString();
   }

   public DVParser(DVParser var1) {
      this.copied = true;
      this.type = var1.type;
      this.errorStyle = var1.errorStyle;
      this.condition = var1.condition;
      this.stringListGiven = var1.stringListGiven;
      this.emptyCellsAllowed = var1.emptyCellsAllowed;
      this.suppressArrow = var1.suppressArrow;
      this.showPrompt = var1.showPrompt;
      this.showError = var1.showError;
      this.promptTitle = var1.promptTitle;
      this.promptText = var1.promptText;
      this.errorTitle = var1.errorTitle;
      this.errorText = var1.errorText;
      this.extendedCellsValidation = var1.extendedCellsValidation;
      this.row1 = var1.row1;
      this.row2 = var1.row2;
      this.column1 = var1.column1;
      this.column2 = var1.column2;
      String var2 = var1.formula1String;
      if (var2 != null) {
         this.formula1String = var2;
         this.formula2String = var1.formula2String;
      } else {
         FormulaException var10000;
         label36: {
            boolean var10001;
            FormulaParser var6;
            try {
               this.formula1String = var1.formula1.getFormula();
               var6 = var1.formula2;
            } catch (FormulaException var5) {
               var10000 = var5;
               var10001 = false;
               break label36;
            }

            String var7;
            if (var6 != null) {
               try {
                  var7 = var6.getFormula();
               } catch (FormulaException var4) {
                  var10000 = var4;
                  var10001 = false;
                  break label36;
               }
            } else {
               var7 = null;
            }

            try {
               this.formula2String = var7;
               return;
            } catch (FormulaException var3) {
               var10000 = var3;
               var10001 = false;
            }
         }

         FormulaException var8 = var10000;
         logger.warn("Cannot parse validation formula:  " + var8.getMessage());
      }

   }

   public DVParser(byte[] var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4) {
      boolean var11 = true;
      boolean var10;
      if (var3 != null) {
         var10 = true;
      } else {
         var10 = false;
      }

      Assert.verify(var10);
      this.copied = false;
      int var5 = IntegerHelper.getInt(var1[0], var1[1], var1[2], var1[3]);
      this.type = DVType.getType(var5 & 15);
      this.errorStyle = ErrorStyle.getErrorStyle((var5 & 112) >> 4);
      this.condition = Condition.getCondition((15728640 & var5) >> 20);
      if ((var5 & 128) != 0) {
         var10 = true;
      } else {
         var10 = false;
      }

      this.stringListGiven = var10;
      if ((var5 & 256) != 0) {
         var10 = true;
      } else {
         var10 = false;
      }

      this.emptyCellsAllowed = var10;
      if ((var5 & 512) != 0) {
         var10 = true;
      } else {
         var10 = false;
      }

      this.suppressArrow = var10;
      if ((262144 & var5) != 0) {
         var10 = true;
      } else {
         var10 = false;
      }

      this.showPrompt = var10;
      if ((var5 & 524288) != 0) {
         var10 = true;
      } else {
         var10 = false;
      }

      this.showError = var10;
      int var6 = IntegerHelper.getInt(var1[4], var1[5]);
      var5 = 7;
      if (var6 > 0 && var1[6] == 0) {
         this.promptTitle = StringHelper.getString(var1, var6, 7, var4);
         var5 = var6 + 3 + 4;
      } else if (var6 > 0) {
         this.promptTitle = StringHelper.getUnicodeString(var1, var6, 7);
         var5 = 4 + var6 * 2 + 3;
      }

      label139: {
         var6 = IntegerHelper.getInt(var1[var5], var1[var5 + 1]);
         if (var6 > 0 && var1[var5 + 2] == 0) {
            this.errorTitle = StringHelper.getString(var1, var6, var5 + 3, var4);
         } else {
            if (var6 <= 0) {
               var5 += 3;
               break label139;
            }

            this.errorTitle = StringHelper.getUnicodeString(var1, var6, var5 + 3);
            var6 *= 2;
         }

         var5 += var6 + 3;
      }

      label140: {
         var6 = IntegerHelper.getInt(var1[var5], var1[var5 + 1]);
         if (var6 > 0 && var1[var5 + 2] == 0) {
            this.promptText = StringHelper.getString(var1, var6, var5 + 3, var4);
         } else {
            if (var6 <= 0) {
               var5 += 3;
               break label140;
            }

            this.promptText = StringHelper.getUnicodeString(var1, var6, var5 + 3);
            var6 *= 2;
         }

         var5 += var6 + 3;
      }

      label141: {
         var6 = IntegerHelper.getInt(var1[var5], var1[var5 + 1]);
         if (var6 > 0 && var1[var5 + 2] == 0) {
            this.errorText = StringHelper.getString(var1, var6, var5 + 3, var4);
         } else {
            if (var6 <= 0) {
               var5 += 3;
               break label141;
            }

            this.errorText = StringHelper.getUnicodeString(var1, var6, var5 + 3);
            var6 *= 2;
         }

         var5 += var6 + 3;
      }

      var6 = IntegerHelper.getInt(var1[var5], var1[var5 + 1]);
      var5 += 4;
      int var8 = var5 + var6;
      int var7 = IntegerHelper.getInt(var1[var8], var1[var8 + 1]);
      var8 += 4;
      int var9 = var8 + var7 + 2;
      this.row1 = IntegerHelper.getInt(var1[var9], var1[var9 + 1]);
      var9 += 2;
      this.row2 = IntegerHelper.getInt(var1[var9], var1[var9 + 1]);
      var9 += 2;
      this.column1 = IntegerHelper.getInt(var1[var9], var1[var9 + 1]);
      var9 += 2;
      var9 = IntegerHelper.getInt(var1[var9], var1[var9 + 1]);
      this.column2 = var9;
      var10 = var11;
      if (this.row1 == this.row2) {
         var10 = var11;
         if (this.column1 == var9) {
            var10 = false;
         }
      }

      this.extendedCellsValidation = var10;

      FormulaException var10000;
      label134: {
         EmptyCell var12;
         boolean var10001;
         try {
            var12 = new EmptyCell(this.column1, this.row1);
         } catch (FormulaException var17) {
            var10000 = var17;
            var10001 = false;
            break label134;
         }

         if (var6 != 0) {
            try {
               byte[] var14 = new byte[var6];
               System.arraycopy(var1, var5, var14, 0, var6);
               FormulaParser var13 = new FormulaParser(var14, var12, var2, var3, var4, ParseContext.DATA_VALIDATION);
               this.formula1 = var13;
               var13.parse();
            } catch (FormulaException var16) {
               var10000 = var16;
               var10001 = false;
               break label134;
            }
         }

         if (var7 == 0) {
            return;
         }

         try {
            byte[] var20 = new byte[var7];
            System.arraycopy(var1, var8, var20, 0, var7);
            FormulaParser var19 = new FormulaParser(var20, var12, var2, var3, var4, ParseContext.DATA_VALIDATION);
            this.formula2 = var19;
            var19.parse();
            return;
         } catch (FormulaException var15) {
            var10000 = var15;
            var10001 = false;
         }
      }

      FormulaException var18 = var10000;
      logger.warn(var18.getMessage() + " for cells " + CellReferenceHelper.getCellReference(this.column1, this.row1) + "-" + CellReferenceHelper.getCellReference(this.column2, this.row2));
   }

   public boolean copied() {
      return this.copied;
   }

   public void extendCellValidation(int var1, int var2) {
      this.row2 = this.row1 + var2;
      this.column2 = this.column1 + var1;
      this.extendedCellsValidation = true;
   }

   public boolean extendedCellsValidation() {
      return this.extendedCellsValidation;
   }

   public byte[] getData() {
      FormulaParser var3 = this.formula1;
      byte[] var6;
      if (var3 != null) {
         var6 = var3.getBytes();
      } else {
         var6 = new byte[0];
      }

      FormulaParser var4 = this.formula2;
      byte[] var7;
      if (var4 != null) {
         var7 = var4.getBytes();
      } else {
         var7 = new byte[0];
      }

      byte[] var5 = new byte[this.promptTitle.length() * 2 + 4 + 3 + this.errorTitle.length() * 2 + 3 + this.promptText.length() * 2 + 3 + this.errorText.length() * 2 + 3 + var6.length + 2 + var7.length + 2 + 4 + 10];
      int var2 = this.type.getValue() | 0 | this.errorStyle.getValue() << 4 | this.condition.getValue() << 20;
      int var1 = var2;
      if (this.stringListGiven) {
         var1 = var2 | 128;
      }

      var2 = var1;
      if (this.emptyCellsAllowed) {
         var2 = var1 | 256;
      }

      var1 = var2;
      if (this.suppressArrow) {
         var1 = var2 | 512;
      }

      var2 = var1;
      if (this.showPrompt) {
         var2 = var1 | 262144;
      }

      var1 = var2;
      if (this.showError) {
         var1 = var2 | 524288;
      }

      IntegerHelper.getFourBytes(var1, var5, 0);
      IntegerHelper.getTwoBytes(this.promptTitle.length(), var5, 4);
      var5[6] = 1;
      StringHelper.getUnicodeBytes(this.promptTitle, var5, 7);
      var1 = 7 + this.promptTitle.length() * 2;
      IntegerHelper.getTwoBytes(this.errorTitle.length(), var5, var1);
      var1 += 2;
      var5[var1] = 1;
      ++var1;
      StringHelper.getUnicodeBytes(this.errorTitle, var5, var1);
      var1 += this.errorTitle.length() * 2;
      IntegerHelper.getTwoBytes(this.promptText.length(), var5, var1);
      var1 += 2;
      var5[var1] = 1;
      ++var1;
      StringHelper.getUnicodeBytes(this.promptText, var5, var1);
      var1 += this.promptText.length() * 2;
      IntegerHelper.getTwoBytes(this.errorText.length(), var5, var1);
      var1 += 2;
      var5[var1] = 1;
      ++var1;
      StringHelper.getUnicodeBytes(this.errorText, var5, var1);
      var1 += this.errorText.length() * 2;
      IntegerHelper.getTwoBytes(var6.length, var5, var1);
      var1 += 4;
      System.arraycopy(var6, 0, var5, var1, var6.length);
      var1 += var6.length;
      IntegerHelper.getTwoBytes(var7.length, var5, var1);
      var1 += 4;
      System.arraycopy(var7, 0, var5, var1, var7.length);
      var1 += var7.length;
      IntegerHelper.getTwoBytes(1, var5, var1);
      var1 += 2;
      IntegerHelper.getTwoBytes(this.row1, var5, var1);
      var1 += 2;
      IntegerHelper.getTwoBytes(this.row2, var5, var1);
      var1 += 2;
      IntegerHelper.getTwoBytes(this.column1, var5, var1);
      IntegerHelper.getTwoBytes(this.column2, var5, var1 + 2);
      return var5;
   }

   public int getFirstColumn() {
      return this.column1;
   }

   public int getFirstRow() {
      return this.row1;
   }

   public int getLastColumn() {
      return this.column2;
   }

   public int getLastRow() {
      return this.row2;
   }

   String getValidationFormula() throws FormulaException {
      if (this.type == LIST) {
         return this.formula1.getFormula();
      } else {
         String var2 = this.formula1.getFormula();
         FormulaParser var1 = this.formula2;
         String var3;
         if (var1 != null) {
            var3 = var1.getFormula();
         } else {
            var3 = null;
         }

         return this.condition.getConditionString(var2, var3) + "; x " + this.type.getDescription();
      }
   }

   public void insertColumn(int var1) {
      FormulaParser var3 = this.formula1;
      if (var3 != null) {
         var3.columnInserted(0, var1, true);
      }

      var3 = this.formula2;
      if (var3 != null) {
         var3.columnInserted(0, var1, true);
      }

      int var2 = this.column1;
      if (var2 >= var1) {
         this.column1 = var2 + 1;
      }

      var2 = this.column2;
      if (var2 >= var1 && var2 != 255) {
         this.column2 = var2 + 1;
      }

   }

   public void insertRow(int var1) {
      FormulaParser var3 = this.formula1;
      if (var3 != null) {
         var3.rowInserted(0, var1, true);
      }

      var3 = this.formula2;
      if (var3 != null) {
         var3.rowInserted(0, var1, true);
      }

      int var2 = this.row1;
      if (var2 >= var1) {
         this.row1 = var2 + 1;
      }

      var2 = this.row2;
      if (var2 >= var1 && var2 != 65535) {
         this.row2 = var2 + 1;
      }

   }

   public void removeColumn(int var1) {
      FormulaParser var3 = this.formula1;
      if (var3 != null) {
         var3.columnRemoved(0, var1, true);
      }

      var3 = this.formula2;
      if (var3 != null) {
         var3.columnRemoved(0, var1, true);
      }

      int var2 = this.column1;
      if (var2 > var1) {
         this.column1 = var2 - 1;
      }

      var2 = this.column2;
      if (var2 >= var1 && var2 != 255) {
         this.column2 = var2 - 1;
      }

   }

   public void removeRow(int var1) {
      FormulaParser var3 = this.formula1;
      if (var3 != null) {
         var3.rowRemoved(0, var1, true);
      }

      var3 = this.formula2;
      if (var3 != null) {
         var3.rowRemoved(0, var1, true);
      }

      int var2 = this.row1;
      if (var2 > var1) {
         this.row1 = var2 - 1;
      }

      var2 = this.row2;
      if (var2 >= var1) {
         this.row2 = var2 - 1;
      }

   }

   public void setCell(int var1, int var2, ExternalSheet var3, WorkbookMethods var4, WorkbookSettings var5) throws FormulaException {
      if (!this.extendedCellsValidation) {
         this.row1 = var2;
         this.row2 = var2;
         this.column1 = var1;
         this.column2 = var1;
         FormulaParser var6 = new FormulaParser(this.formula1String, var3, var4, var5, ParseContext.DATA_VALIDATION);
         this.formula1 = var6;
         var6.parse();
         if (this.formula2String != null) {
            FormulaParser var7 = new FormulaParser(this.formula2String, var3, var4, var5, ParseContext.DATA_VALIDATION);
            this.formula2 = var7;
            var7.parse();
         }

      }
   }

   public static class Condition {
      private static Condition[] types = new Condition[0];
      private MessageFormat format;
      private int value;

      Condition(int var1, String var2) {
         this.value = var1;
         this.format = new MessageFormat(var2);
         Condition[] var3 = types;
         Condition[] var4 = new Condition[var3.length + 1];
         types = var4;
         System.arraycopy(var3, 0, var4, 0, var3.length);
         types[var3.length] = this;
      }

      static Condition getCondition(int var0) {
         Condition var2 = null;
         int var1 = 0;

         while(true) {
            Condition[] var3 = types;
            if (var1 >= var3.length || var2 != null) {
               return var2;
            }

            Condition var4 = var3[var1];
            if (var4.value == var0) {
               var2 = var4;
            }

            ++var1;
         }
      }

      public String getConditionString(String var1, String var2) {
         return this.format.format(new String[]{var1, var2});
      }

      public int getValue() {
         return this.value;
      }
   }

   public static class DVType {
      private static DVType[] types = new DVType[0];
      private String desc;
      private int value;

      DVType(int var1, String var2) {
         this.value = var1;
         this.desc = var2;
         DVType[] var4 = types;
         DVType[] var3 = new DVType[var4.length + 1];
         types = var3;
         System.arraycopy(var4, 0, var3, 0, var4.length);
         types[var4.length] = this;
      }

      static DVType getType(int var0) {
         DVType var2 = null;
         int var1 = 0;

         while(true) {
            DVType[] var3 = types;
            if (var1 >= var3.length || var2 != null) {
               return var2;
            }

            DVType var4 = var3[var1];
            if (var4.value == var0) {
               var2 = var4;
            }

            ++var1;
         }
      }

      public String getDescription() {
         return this.desc;
      }

      public int getValue() {
         return this.value;
      }
   }

   public static class ErrorStyle {
      private static ErrorStyle[] types = new ErrorStyle[0];
      private int value;

      ErrorStyle(int var1) {
         this.value = var1;
         ErrorStyle[] var2 = types;
         ErrorStyle[] var3 = new ErrorStyle[var2.length + 1];
         types = var3;
         System.arraycopy(var2, 0, var3, 0, var2.length);
         types[var2.length] = this;
      }

      static ErrorStyle getErrorStyle(int var0) {
         ErrorStyle var2 = null;
         int var1 = 0;

         while(true) {
            ErrorStyle[] var3 = types;
            if (var1 >= var3.length || var2 != null) {
               return var2;
            }

            ErrorStyle var4 = var3[var1];
            if (var4.value == var0) {
               var2 = var4;
            }

            ++var1;
         }
      }

      public int getValue() {
         return this.value;
      }
   }
}
