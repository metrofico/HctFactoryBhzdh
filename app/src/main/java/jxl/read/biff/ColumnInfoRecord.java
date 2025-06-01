package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.Type;

public class ColumnInfoRecord extends RecordData {
   private boolean collapsed;
   private byte[] data;
   private int endColumn;
   private boolean hidden;
   private int outlineLevel;
   private int startColumn;
   private int width;
   private int xfIndex;

   ColumnInfoRecord(Record var1) {
      super(Type.COLINFO);
      byte[] var5 = var1.getData();
      this.data = var5;
      boolean var4 = false;
      this.startColumn = IntegerHelper.getInt(var5[0], var5[1]);
      var5 = this.data;
      this.endColumn = IntegerHelper.getInt(var5[2], var5[3]);
      var5 = this.data;
      this.width = IntegerHelper.getInt(var5[4], var5[5]);
      var5 = this.data;
      this.xfIndex = IntegerHelper.getInt(var5[6], var5[7]);
      var5 = this.data;
      int var2 = IntegerHelper.getInt(var5[8], var5[9]);
      boolean var3;
      if ((var2 & 1) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.hidden = var3;
      this.outlineLevel = (var2 & 1792) >> 8;
      var3 = var4;
      if ((var2 & 4096) != 0) {
         var3 = true;
      }

      this.collapsed = var3;
   }

   public boolean getCollapsed() {
      return this.collapsed;
   }

   public int getEndColumn() {
      return this.endColumn;
   }

   public boolean getHidden() {
      return this.hidden;
   }

   public int getOutlineLevel() {
      return this.outlineLevel;
   }

   public int getStartColumn() {
      return this.startColumn;
   }

   public int getWidth() {
      return this.width;
   }

   public int getXFIndex() {
      return this.xfIndex;
   }
}
