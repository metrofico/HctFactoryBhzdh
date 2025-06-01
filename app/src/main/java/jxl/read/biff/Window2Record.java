package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class Window2Record extends RecordData {
   public static final Biff7 biff7 = new Biff7();
   private static Logger logger = Logger.getLogger(Window2Record.class);
   private boolean displayZeroValues;
   private boolean frozenNotSplit;
   private boolean frozenPanes;
   private int normalMagnification;
   private int pageBreakPreviewMagnification;
   private boolean pageBreakPreviewMode;
   private boolean selected;
   private boolean showGridLines;

   public Window2Record(Record var1) {
      super(var1);
      byte[] var5 = var1.getData();
      boolean var4 = false;
      int var2 = IntegerHelper.getInt(var5[0], var5[1]);
      boolean var3;
      if ((var2 & 512) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.selected = var3;
      if ((var2 & 2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.showGridLines = var3;
      if ((var2 & 8) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.frozenPanes = var3;
      if ((var2 & 16) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.displayZeroValues = var3;
      if ((var2 & 256) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.frozenNotSplit = var3;
      var3 = var4;
      if ((var2 & 2048) != 0) {
         var3 = true;
      }

      this.pageBreakPreviewMode = var3;
      this.pageBreakPreviewMagnification = IntegerHelper.getInt(var5[10], var5[11]);
      this.normalMagnification = IntegerHelper.getInt(var5[12], var5[13]);
   }

   public Window2Record(Record var1, Biff7 var2) {
      super(var1);
      byte[] var6 = var1.getData();
      boolean var5 = false;
      int var3 = IntegerHelper.getInt(var6[0], var6[1]);
      boolean var4;
      if ((var3 & 512) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.selected = var4;
      if ((var3 & 2) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.showGridLines = var4;
      if ((var3 & 8) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.frozenPanes = var4;
      if ((var3 & 16) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.displayZeroValues = var4;
      if ((var3 & 256) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.frozenNotSplit = var4;
      var4 = var5;
      if ((var3 & 2048) != 0) {
         var4 = true;
      }

      this.pageBreakPreviewMode = var4;
   }

   public boolean getDisplayZeroValues() {
      return this.displayZeroValues;
   }

   public boolean getFrozen() {
      return this.frozenPanes;
   }

   public boolean getFrozenNotSplit() {
      return this.frozenNotSplit;
   }

   public int getNormalMagnificaiton() {
      return this.normalMagnification;
   }

   public int getPageBreakPreviewMagnificaiton() {
      return this.pageBreakPreviewMagnification;
   }

   public boolean getShowGridLines() {
      return this.showGridLines;
   }

   public boolean isPageBreakPreview() {
      return this.pageBreakPreviewMode;
   }

   public boolean isSelected() {
      return this.selected;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }
}
