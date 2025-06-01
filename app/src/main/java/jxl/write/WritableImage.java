package jxl.write;

import java.io.File;
import jxl.biff.drawing.Drawing;
import jxl.biff.drawing.DrawingGroup;
import jxl.biff.drawing.DrawingGroupObject;

public class WritableImage extends Drawing {
   public static ImageAnchorProperties MOVE_AND_SIZE_WITH_CELLS;
   public static ImageAnchorProperties MOVE_WITH_CELLS;
   public static ImageAnchorProperties NO_MOVE_OR_SIZE_WITH_CELLS;

   static {
      MOVE_AND_SIZE_WITH_CELLS = Drawing.MOVE_AND_SIZE_WITH_CELLS;
      MOVE_WITH_CELLS = Drawing.MOVE_WITH_CELLS;
      NO_MOVE_OR_SIZE_WITH_CELLS = Drawing.NO_MOVE_OR_SIZE_WITH_CELLS;
   }

   public WritableImage(double var1, double var3, double var5, double var7, File var9) {
      super(var1, var3, var5, var7, var9);
   }

   public WritableImage(double var1, double var3, double var5, double var7, byte[] var9) {
      super(var1, var3, var5, var7, var9);
   }

   public WritableImage(DrawingGroupObject var1, DrawingGroup var2) {
      super(var1, var2);
   }

   public double getColumn() {
      return super.getX();
   }

   public double getHeight() {
      return super.getHeight();
   }

   public ImageAnchorProperties getImageAnchor() {
      return super.getImageAnchor();
   }

   public byte[] getImageData() {
      return super.getImageData();
   }

   public File getImageFile() {
      return super.getImageFile();
   }

   public double getRow() {
      return super.getY();
   }

   public double getWidth() {
      return super.getWidth();
   }

   public void setColumn(double var1) {
      super.setX(var1);
   }

   public void setHeight(double var1) {
      super.setHeight(var1);
   }

   public void setImageAnchor(ImageAnchorProperties var1) {
      super.setImageAnchor(var1);
   }

   public void setRow(double var1) {
      super.setY(var1);
   }

   public void setWidth(double var1) {
      super.setWidth(var1);
   }
}
