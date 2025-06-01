package jxl.biff.formula;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import jxl.WorkbookSettings;
import jxl.biff.WorkbookMethods;
import jxl.common.Logger;

class StringFormulaParser implements Parser {
   private static Logger logger = Logger.getLogger(StringFormulaParser.class);
   private Stack arguments;
   private ExternalSheet externalSheet;
   private String formula;
   private WorkbookMethods nameTable;
   private ParseContext parseContext;
   private String parsedFormula;
   private ParseItem root;
   private WorkbookSettings settings;

   public StringFormulaParser(String var1, ExternalSheet var2, WorkbookMethods var3, WorkbookSettings var4, ParseContext var5) {
      this.formula = var1;
      this.settings = var4;
      this.externalSheet = var2;
      this.nameTable = var3;
      this.parseContext = var5;
   }

   private ArrayList getTokens() throws FormulaException {
      ArrayList var2 = new ArrayList();
      Yylex var3 = new Yylex(new StringReader(this.formula));
      var3.setExternalSheet(this.externalSheet);
      var3.setNameTable(this.nameTable);

      IOException var10000;
      label45: {
         ParseItem var1;
         boolean var10001;
         try {
            var1 = var3.yylex();
         } catch (IOException var6) {
            var10000 = var6;
            var10001 = false;
            break label45;
         } catch (Error var7) {
            var10001 = false;
            throw new FormulaException(FormulaException.LEXICAL_ERROR, this.formula + " at char  " + var3.getPos());
         }

         while(true) {
            if (var1 == null) {
               return var2;
            }

            try {
               var2.add(var1);
               var1 = var3.yylex();
            } catch (IOException var4) {
               var10000 = var4;
               var10001 = false;
               break;
            } catch (Error var5) {
               var10001 = false;
               throw new FormulaException(FormulaException.LEXICAL_ERROR, this.formula + " at char  " + var3.getPos());
            }
         }
      }

      IOException var8 = var10000;
      logger.warn(var8.toString());
      return var2;
   }

   private void handleFunction(StringFunction var1, Iterator var2, Stack var3) throws FormulaException {
      ParseItem var13 = this.parseCurrent(var2);
      if (var1.getFunction(this.settings) != Function.UNKNOWN) {
         if (var1.getFunction(this.settings) == Function.SUM && this.arguments == null) {
            Attribute var14 = new Attribute(var1, this.settings);
            var14.add(var13);
            var3.push(var14);
         } else {
            Function var8 = var1.getFunction(this.settings);
            Function var9 = Function.IF;
            int var7 = 0;
            int var6 = 0;
            int var5 = 0;
            int var4 = 0;
            VariableArgFunction var11;
            if (var8 == var9) {
               Attribute var16 = new Attribute(var1, this.settings);
               var11 = new VariableArgFunction(this.settings);

               for(var5 = this.arguments.size(); var4 < var5; ++var4) {
                  var11.add((ParseItem)this.arguments.get(var4));
               }

               var16.setIfConditions(var11);
               var3.push(var16);
            } else if (var1.getFunction(this.settings).getNumArgs() == 255) {
               Stack var19 = this.arguments;
               if (var19 == null) {
                  byte var17 = (byte)var7;
                  if (var13 != null) {
                     var17 = 1;
                  }

                  var11 = new VariableArgFunction(var1.getFunction(this.settings), var17, this.settings);
                  if (var13 != null) {
                     var11.add(var13);
                  }

                  var3.push(var11);
               } else {
                  var7 = var19.size();
                  VariableArgFunction var15 = new VariableArgFunction(var1.getFunction(this.settings), var7, this.settings);
                  ParseItem[] var12 = new ParseItem[var7];
                  var4 = 0;

                  while(true) {
                     var5 = var6;
                     if (var4 >= var7) {
                        while(var5 < var7) {
                           var15.add(var12[var5]);
                           ++var5;
                        }

                        var3.push(var15);
                        this.arguments.clear();
                        this.arguments = null;
                        break;
                     }

                     var12[var7 - var4 - 1] = (ParseItem)this.arguments.pop();
                     ++var4;
                  }
               }

            } else {
               BuiltInFunction var18 = new BuiltInFunction(var1.getFunction(this.settings), this.settings);
               var6 = var1.getFunction(this.settings).getNumArgs();
               if (var6 == 1) {
                  var18.add(var13);
               } else {
                  label101: {
                     Stack var10 = this.arguments;
                     if (var10 != null || var6 == 0) {
                        var4 = var5;
                        if (var10 == null) {
                           break label101;
                        }

                        if (var6 == var10.size()) {
                           var4 = var5;
                           break label101;
                        }
                     }

                     throw new FormulaException(FormulaException.INCORRECT_ARGUMENTS);
                  }

                  while(var4 < var6) {
                     var18.add((ParseItem)this.arguments.get(var4));
                     ++var4;
                  }
               }

               var3.push(var18);
            }
         }
      } else {
         throw new FormulaException(FormulaException.UNRECOGNIZED_FUNCTION);
      }
   }

   private void handleOperand(Operand var1, Stack var2) {
      boolean var3 = var1 instanceof IntegerValue;
      if (!var3) {
         var2.push(var1);
      } else {
         if (var3) {
            IntegerValue var4 = (IntegerValue)var1;
            if (!var4.isOutOfRange()) {
               var2.push(var4);
            } else {
               var2.push(new DoubleValue(var4.getValue()));
            }
         }

      }
   }

   private ParseItem parseCurrent(Iterator var1) throws FormulaException {
      Stack var10 = new Stack();
      Stack var9 = new Stack();
      Object var6 = null;
      boolean var2 = false;
      Stack var3 = null;

      Stack var4;
      for(ParseItem var5 = null; var1.hasNext() && !var2; var3 = var4) {
         ParseItem var7 = (ParseItem)var1.next();
         var7.setParseContext(this.parseContext);
         if (var7 instanceof Operand) {
            this.handleOperand((Operand)var7, var10);
            var4 = var3;
         } else if (var7 instanceof StringFunction) {
            this.handleFunction((StringFunction)var7, var1, var10);
            var4 = var3;
         } else {
            Operator var13;
            if (var7 instanceof Operator) {
               Operator var8 = (Operator)var7;
               var13 = var8;
               if (var8 instanceof StringOperator) {
                  StringOperator var14 = (StringOperator)var8;
                  if (!var10.isEmpty() && !(var5 instanceof Operator)) {
                     var13 = var14.getBinaryOperator();
                  } else {
                     var13 = var14.getUnaryOperator();
                  }
               }

               if (var9.empty()) {
                  var9.push(var13);
                  var4 = var3;
               } else {
                  Operator var15 = (Operator)var9.peek();
                  if (var13.getPrecedence() < var15.getPrecedence()) {
                     var9.push(var13);
                     var4 = var3;
                  } else if (var13.getPrecedence() == var15.getPrecedence() && var13 instanceof UnaryOperator) {
                     var9.push(var13);
                     var4 = var3;
                  } else {
                     var9.pop();
                     var15.getOperands(var10);
                     var10.push(var15);
                     var9.push(var13);
                     var4 = var3;
                  }
               }
            } else if (!(var7 instanceof ArgumentSeparator)) {
               if (var7 instanceof OpenParentheses) {
                  ParseItem var17 = this.parseCurrent(var1);
                  Parenthesis var16 = new Parenthesis();
                  var17.setParent(var16);
                  var16.add(var17);
                  var10.push(var16);
                  var4 = var3;
               } else {
                  var4 = var3;
                  if (var7 instanceof CloseParentheses) {
                     var2 = true;
                     var4 = var3;
                  }
               }
            } else {
               while(!var9.isEmpty()) {
                  var13 = (Operator)var9.pop();
                  var13.getOperands(var10);
                  var10.push(var13);
               }

               var4 = var3;
               if (var3 == null) {
                  var4 = new Stack();
               }

               var4.push(var10.pop());
               var10.clear();
            }
         }

         var5 = var7;
      }

      while(!var9.isEmpty()) {
         Operator var11 = (Operator)var9.pop();
         var11.getOperands(var10);
         var10.push(var11);
      }

      ParseItem var12 = (ParseItem)var6;
      if (!var10.empty()) {
         var12 = (ParseItem)var10.pop();
      }

      if (var3 != null && var12 != null) {
         var3.push(var12);
      }

      this.arguments = var3;
      if (!var10.empty() || !var9.empty()) {
         logger.warn("Formula " + this.formula + " has a non-empty parse stack");
      }

      return var12;
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
      byte[] var2 = this.root.getBytes();
      byte[] var1 = var2;
      if (this.root.isVolatile()) {
         var1 = new byte[var2.length + 4];
         System.arraycopy(var2, 0, var1, 4, var2.length);
         var1[0] = Token.ATTRIBUTE.getCode();
         var1[1] = 1;
      }

      return var1;
   }

   public String getFormula() {
      if (this.parsedFormula == null) {
         StringBuffer var1 = new StringBuffer();
         this.root.getString(var1);
         this.parsedFormula = var1.toString();
      }

      return this.parsedFormula;
   }

   public boolean handleImportedCellReferences() {
      this.root.handleImportedCellReferences();
      return this.root.isValid();
   }

   public void parse() throws FormulaException {
      this.root = this.parseCurrent(this.getTokens().iterator());
   }

   public void rowInserted(int var1, int var2, boolean var3) {
      this.root.rowInserted(var1, var2, var3);
   }

   public void rowRemoved(int var1, int var2, boolean var3) {
      this.root.rowRemoved(var1, var2, var3);
   }
}
