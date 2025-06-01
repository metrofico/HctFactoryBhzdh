package jxl.biff;

import jxl.Sheet;

public interface WorkbookMethods {
   String getName(int var1) throws NameRangeException;

   int getNameIndex(String var1);

   Sheet getReadSheet(int var1);
}
