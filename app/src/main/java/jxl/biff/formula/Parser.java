package jxl.biff.formula;

interface Parser {
   void adjustRelativeCellReferences(int var1, int var2);

   void columnInserted(int var1, int var2, boolean var3);

   void columnRemoved(int var1, int var2, boolean var3);

   byte[] getBytes();

   String getFormula();

   boolean handleImportedCellReferences();

   void parse() throws FormulaException;

   void rowInserted(int var1, int var2, boolean var3);

   void rowRemoved(int var1, int var2, boolean var3);
}
