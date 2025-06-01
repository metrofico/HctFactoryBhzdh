package jxl.biff.formula;

import java.util.Stack;
import jxl.Cell;
import jxl.WorkbookSettings;
import jxl.biff.WorkbookMethods;
import jxl.common.Assert;
import jxl.common.Logger;

class TokenFormulaParser implements Parser {
   private static Logger logger = Logger.getLogger(TokenFormulaParser.class);
   private WorkbookMethods nameTable;
   private ParseContext parseContext;
   private int pos;
   private Cell relativeTo;
   private ParseItem root;
   private WorkbookSettings settings;
   private byte[] tokenData;
   private Stack tokenStack;
   private ExternalSheet workbook;

   public TokenFormulaParser(byte[] var1, Cell var2, ExternalSheet var3, WorkbookMethods var4, WorkbookSettings var5, ParseContext var6) {
      this.tokenData = var1;
      boolean var7 = false;
      this.pos = 0;
      this.relativeTo = var2;
      this.workbook = var3;
      this.nameTable = var4;
      this.tokenStack = new Stack();
      this.settings = var5;
      this.parseContext = var6;
      if (this.nameTable != null) {
         var7 = true;
      }

      Assert.verify(var7);
   }

   private void addOperator(Operator var1) {
      var1.getOperands(this.tokenStack);
      this.tokenStack.push(var1);
   }

   private void handleMemoryFunction(SubExpression var1) throws FormulaException {
      int var2 = this.pos;
      this.pos = var2 + var1.read(this.tokenData, var2);
      Stack var4 = this.tokenStack;
      this.tokenStack = new Stack();
      this.parseSubExpression(var1.getLength());
      ParseItem[] var3 = new ParseItem[this.tokenStack.size()];

      for(var2 = 0; !this.tokenStack.isEmpty(); ++var2) {
         var3[var2] = (ParseItem)this.tokenStack.pop();
      }

      var1.setSubExpression(var3);
      this.tokenStack = var4;
      var4.push(var1);
   }

   private void parseSubExpression(int var1) throws FormulaException {
      Stack var7 = new Stack();
      int var2 = this.pos;

      while(true) {
         int var3 = this.pos;
         if (var3 >= var2 + var1) {
            return;
         }

         byte var4 = this.tokenData[var3];
         this.pos = var3 + 1;
         Token var6 = Token.getToken(var4);
         if (var6 == Token.UNKNOWN) {
            throw new FormulaException(FormulaException.UNRECOGNIZED_TOKEN, var4);
         }

         boolean var5;
         if (var6 != Token.UNKNOWN) {
            var5 = true;
         } else {
            var5 = false;
         }

         Assert.verify(var5);
         if (var6 == Token.REF) {
            CellReference var9 = new CellReference(this.relativeTo);
            var3 = this.pos;
            this.pos = var3 + var9.read(this.tokenData, var3);
            this.tokenStack.push(var9);
         } else if (var6 == Token.REFERR) {
            CellReferenceError var10 = new CellReferenceError();
            var3 = this.pos;
            this.pos = var3 + var10.read(this.tokenData, var3);
            this.tokenStack.push(var10);
         } else if (var6 == Token.ERR) {
            ErrorConstant var11 = new ErrorConstant();
            var3 = this.pos;
            this.pos = var3 + var11.read(this.tokenData, var3);
            this.tokenStack.push(var11);
         } else if (var6 == Token.REFV) {
            SharedFormulaCellReference var12 = new SharedFormulaCellReference(this.relativeTo);
            var3 = this.pos;
            this.pos = var3 + var12.read(this.tokenData, var3);
            this.tokenStack.push(var12);
         } else if (var6 == Token.REF3D) {
            CellReference3d var13 = new CellReference3d(this.relativeTo, this.workbook);
            var3 = this.pos;
            this.pos = var3 + var13.read(this.tokenData, var3);
            this.tokenStack.push(var13);
         } else if (var6 == Token.AREA) {
            Area var14 = new Area();
            var3 = this.pos;
            this.pos = var3 + var14.read(this.tokenData, var3);
            this.tokenStack.push(var14);
         } else if (var6 == Token.AREAV) {
            SharedFormulaArea var15 = new SharedFormulaArea(this.relativeTo);
            var3 = this.pos;
            this.pos = var3 + var15.read(this.tokenData, var3);
            this.tokenStack.push(var15);
         } else if (var6 == Token.AREA3D) {
            Area3d var16 = new Area3d(this.workbook);
            var3 = this.pos;
            this.pos = var3 + var16.read(this.tokenData, var3);
            this.tokenStack.push(var16);
         } else if (var6 == Token.NAME) {
            Name var17 = new Name();
            var3 = this.pos;
            this.pos = var3 + var17.read(this.tokenData, var3);
            var17.setParseContext(this.parseContext);
            this.tokenStack.push(var17);
         } else if (var6 == Token.NAMED_RANGE) {
            NameRange var18 = new NameRange(this.nameTable);
            var3 = this.pos;
            this.pos = var3 + var18.read(this.tokenData, var3);
            var18.setParseContext(this.parseContext);
            this.tokenStack.push(var18);
         } else if (var6 == Token.INTEGER) {
            IntegerValue var19 = new IntegerValue();
            var3 = this.pos;
            this.pos = var3 + var19.read(this.tokenData, var3);
            this.tokenStack.push(var19);
         } else if (var6 == Token.DOUBLE) {
            DoubleValue var20 = new DoubleValue();
            var3 = this.pos;
            this.pos = var3 + var20.read(this.tokenData, var3);
            this.tokenStack.push(var20);
         } else if (var6 == Token.BOOL) {
            BooleanValue var21 = new BooleanValue();
            var3 = this.pos;
            this.pos = var3 + var21.read(this.tokenData, var3);
            this.tokenStack.push(var21);
         } else if (var6 == Token.STRING) {
            StringValue var22 = new StringValue(this.settings);
            var3 = this.pos;
            this.pos = var3 + var22.read(this.tokenData, var3);
            this.tokenStack.push(var22);
         } else if (var6 == Token.MISSING_ARG) {
            MissingArg var23 = new MissingArg();
            var3 = this.pos;
            this.pos = var3 + var23.read(this.tokenData, var3);
            this.tokenStack.push(var23);
         } else if (var6 == Token.UNARY_PLUS) {
            UnaryPlus var24 = new UnaryPlus();
            var3 = this.pos;
            this.pos = var3 + var24.read(this.tokenData, var3);
            this.addOperator(var24);
         } else if (var6 == Token.UNARY_MINUS) {
            UnaryMinus var25 = new UnaryMinus();
            var3 = this.pos;
            this.pos = var3 + var25.read(this.tokenData, var3);
            this.addOperator(var25);
         } else if (var6 == Token.PERCENT) {
            Percent var26 = new Percent();
            var3 = this.pos;
            this.pos = var3 + var26.read(this.tokenData, var3);
            this.addOperator(var26);
         } else if (var6 == Token.SUBTRACT) {
            Subtract var27 = new Subtract();
            var3 = this.pos;
            this.pos = var3 + var27.read(this.tokenData, var3);
            this.addOperator(var27);
         } else if (var6 == Token.ADD) {
            Add var28 = new Add();
            var3 = this.pos;
            this.pos = var3 + var28.read(this.tokenData, var3);
            this.addOperator(var28);
         } else if (var6 == Token.MULTIPLY) {
            Multiply var29 = new Multiply();
            var3 = this.pos;
            this.pos = var3 + var29.read(this.tokenData, var3);
            this.addOperator(var29);
         } else if (var6 == Token.DIVIDE) {
            Divide var30 = new Divide();
            var3 = this.pos;
            this.pos = var3 + var30.read(this.tokenData, var3);
            this.addOperator(var30);
         } else if (var6 == Token.CONCAT) {
            Concatenate var31 = new Concatenate();
            var3 = this.pos;
            this.pos = var3 + var31.read(this.tokenData, var3);
            this.addOperator(var31);
         } else if (var6 == Token.POWER) {
            Power var32 = new Power();
            var3 = this.pos;
            this.pos = var3 + var32.read(this.tokenData, var3);
            this.addOperator(var32);
         } else if (var6 == Token.LESS_THAN) {
            LessThan var33 = new LessThan();
            var3 = this.pos;
            this.pos = var3 + var33.read(this.tokenData, var3);
            this.addOperator(var33);
         } else if (var6 == Token.LESS_EQUAL) {
            LessEqual var34 = new LessEqual();
            var3 = this.pos;
            this.pos = var3 + var34.read(this.tokenData, var3);
            this.addOperator(var34);
         } else if (var6 == Token.GREATER_THAN) {
            GreaterThan var35 = new GreaterThan();
            var3 = this.pos;
            this.pos = var3 + var35.read(this.tokenData, var3);
            this.addOperator(var35);
         } else if (var6 == Token.GREATER_EQUAL) {
            GreaterEqual var36 = new GreaterEqual();
            var3 = this.pos;
            this.pos = var3 + var36.read(this.tokenData, var3);
            this.addOperator(var36);
         } else if (var6 == Token.NOT_EQUAL) {
            NotEqual var37 = new NotEqual();
            var3 = this.pos;
            this.pos = var3 + var37.read(this.tokenData, var3);
            this.addOperator(var37);
         } else if (var6 == Token.EQUAL) {
            Equal var38 = new Equal();
            var3 = this.pos;
            this.pos = var3 + var38.read(this.tokenData, var3);
            this.addOperator(var38);
         } else if (var6 == Token.PARENTHESIS) {
            Parenthesis var39 = new Parenthesis();
            var3 = this.pos;
            this.pos = var3 + var39.read(this.tokenData, var3);
            this.addOperator(var39);
         } else {
            Attribute var40;
            if (var6 == Token.ATTRIBUTE) {
               var40 = new Attribute(this.settings);
               var3 = this.pos;
               this.pos = var3 + var40.read(this.tokenData, var3);
               if (var40.isSum()) {
                  this.addOperator(var40);
               } else if (var40.isIf()) {
                  var7.push(var40);
               }
            } else if (var6 == Token.FUNCTION) {
               BuiltInFunction var41 = new BuiltInFunction(this.settings);
               var3 = this.pos;
               this.pos = var3 + var41.read(this.tokenData, var3);
               this.addOperator(var41);
            } else if (var6 == Token.FUNCTIONVARARG) {
               VariableArgFunction var8 = new VariableArgFunction(this.settings);
               var3 = this.pos;
               this.pos = var3 + var8.read(this.tokenData, var3);
               if (var8.getFunction() != Function.ATTRIBUTE) {
                  this.addOperator(var8);
               } else {
                  var8.getOperands(this.tokenStack);
                  if (var7.empty()) {
                     var40 = new Attribute(this.settings);
                  } else {
                     var40 = (Attribute)var7.pop();
                  }

                  var40.setIfConditions(var8);
                  this.tokenStack.push(var40);
               }
            } else if (var6 == Token.MEM_FUNC) {
               this.handleMemoryFunction(new MemFunc());
            } else if (var6 == Token.MEM_AREA) {
               this.handleMemoryFunction(new MemArea());
            }
         }
      }
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      this.root.adjustRelativeCellReferences(var1, var2);
   }

   public void columnInserted(int var1, int var2, boolean var3) {
      this.root.columnInserted(var1, var2, var3);
   }

   public void columnRemoved(int var1, int var2, boolean var3) {
      this.root.columnRemoved(var1, var2, var3);
   }

   public byte[] getBytes() {
      return this.root.getBytes();
   }

   public String getFormula() {
      StringBuffer var1 = new StringBuffer();
      this.root.getString(var1);
      return var1.toString();
   }

   public boolean handleImportedCellReferences() {
      this.root.handleImportedCellReferences();
      return this.root.isValid();
   }

   public void parse() throws FormulaException {
      this.parseSubExpression(this.tokenData.length);
      this.root = (ParseItem)this.tokenStack.pop();
      Assert.verify(this.tokenStack.empty());
   }

   public void rowInserted(int var1, int var2, boolean var3) {
      this.root.rowInserted(var1, var2, var3);
   }

   public void rowRemoved(int var1, int var2, boolean var3) {
      this.root.rowRemoved(var1, var2, var3);
   }
}
