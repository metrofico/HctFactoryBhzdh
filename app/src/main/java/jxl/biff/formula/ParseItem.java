package jxl.biff.formula;

import jxl.common.Logger;

abstract class ParseItem {
   private static Logger logger = Logger.getLogger(ParseItem.class);
   private boolean alternateCode = false;
   private ParseItem parent;
   private ParseContext parseContext;
   private boolean valid = true;
   private boolean volatileFunction = false;

   public ParseItem() {
      this.parseContext = ParseContext.DEFAULT;
   }

   abstract void adjustRelativeCellReferences(int var1, int var2);

   abstract void columnInserted(int var1, int var2, boolean var3);

   abstract void columnRemoved(int var1, int var2, boolean var3);

   abstract byte[] getBytes();

   protected final ParseContext getParseContext() {
      return this.parseContext;
   }

   abstract void getString(StringBuffer var1);

   abstract void handleImportedCellReferences();

   final boolean isValid() {
      return this.valid;
   }

   final boolean isVolatile() {
      return this.volatileFunction;
   }

   abstract void rowInserted(int var1, int var2, boolean var3);

   abstract void rowRemoved(int var1, int var2, boolean var3);

   protected void setAlternateCode() {
      this.alternateCode = true;
   }

   protected final void setInvalid() {
      this.valid = false;
      ParseItem var1 = this.parent;
      if (var1 != null) {
         var1.setInvalid();
      }

   }

   protected void setParent(ParseItem var1) {
      this.parent = var1;
   }

   protected void setParseContext(ParseContext var1) {
      this.parseContext = var1;
   }

   protected void setVolatile() {
      this.volatileFunction = true;
      ParseItem var1 = this.parent;
      if (var1 != null && !var1.isVolatile()) {
         this.parent.setVolatile();
      }

   }

   protected final boolean useAlternateCode() {
      return this.alternateCode;
   }
}
