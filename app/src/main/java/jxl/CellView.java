package jxl;

public final class CellView {
   private boolean autosize;
   private boolean depUsed;
   private int dimension;
   private jxl.format.CellFormat format;
   private boolean hidden;
   private int size;

   public CellView() {
      this.hidden = false;
      this.depUsed = false;
      this.dimension = 1;
      this.size = 1;
      this.autosize = false;
   }

   public CellView(CellView var1) {
      this.hidden = var1.hidden;
      this.depUsed = var1.depUsed;
      this.dimension = var1.dimension;
      this.size = var1.size;
      this.autosize = var1.autosize;
   }

   public boolean depUsed() {
      return this.depUsed;
   }

   public int getDimension() {
      return this.dimension;
   }

   public jxl.format.CellFormat getFormat() {
      return this.format;
   }

   public int getSize() {
      return this.size;
   }

   public boolean isAutosize() {
      return this.autosize;
   }

   public boolean isHidden() {
      return this.hidden;
   }

   public void setAutosize(boolean var1) {
      this.autosize = var1;
   }

   public void setDimension(int var1) {
      this.dimension = var1;
      this.depUsed = true;
   }

   public void setFormat(jxl.format.CellFormat var1) {
      this.format = var1;
   }

   public void setHidden(boolean var1) {
      this.hidden = var1;
   }

   public void setSize(int var1) {
      this.size = var1;
      this.depUsed = false;
   }
}
