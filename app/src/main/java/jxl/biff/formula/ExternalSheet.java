package jxl.biff.formula;

import jxl.read.biff.BOFRecord;

public interface ExternalSheet {
   int getExternalSheetIndex(int var1);

   int getExternalSheetIndex(String var1);

   String getExternalSheetName(int var1);

   int getLastExternalSheetIndex(int var1);

   int getLastExternalSheetIndex(String var1);

   BOFRecord getWorkbookBof();
}
