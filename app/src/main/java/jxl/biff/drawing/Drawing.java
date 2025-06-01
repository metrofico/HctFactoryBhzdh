package jxl.biff.drawing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.CellView;
import jxl.Image;
import jxl.Sheet;
import jxl.common.Assert;
import jxl.common.LengthConverter;
import jxl.common.LengthUnit;
import jxl.common.Logger;

public class Drawing implements DrawingGroupObject, Image {
   private static final double DEFAULT_FONT_SIZE = 10.0;
   public static ImageAnchorProperties MOVE_AND_SIZE_WITH_CELLS = new ImageAnchorProperties(1);
   public static ImageAnchorProperties MOVE_WITH_CELLS = new ImageAnchorProperties(2);
   public static ImageAnchorProperties NO_MOVE_OR_SIZE_WITH_CELLS = new ImageAnchorProperties(3);
   private static Logger logger = Logger.getLogger(Drawing.class);
   private int blipId;
   private DrawingData drawingData;
   private DrawingGroup drawingGroup;
   private int drawingNumber;
   private EscherContainer escherData;
   private double height;
   private ImageAnchorProperties imageAnchorProperties;
   private byte[] imageData;
   private File imageFile;
   private boolean initialized;
   private MsoDrawingRecord msoDrawingRecord;
   private ObjRecord objRecord;
   private int objectId;
   private Origin origin;
   private PNGReader pngReader;
   private EscherContainer readSpContainer;
   private int referenceCount;
   private int shapeId;
   private Sheet sheet;
   private ShapeType type;
   private double width;
   private double x;
   private double y;

   public Drawing(double var1, double var3, double var5, double var7, File var9) {
      this.imageFile = var9;
      this.initialized = true;
      this.origin = Origin.WRITE;
      this.x = var1;
      this.y = var3;
      this.width = var5;
      this.height = var7;
      this.referenceCount = 1;
      this.imageAnchorProperties = MOVE_WITH_CELLS;
      this.type = ShapeType.PICTURE_FRAME;
   }

   public Drawing(double var1, double var3, double var5, double var7, byte[] var9) {
      this.imageData = var9;
      this.initialized = true;
      this.origin = Origin.WRITE;
      this.x = var1;
      this.y = var3;
      this.width = var5;
      this.height = var7;
      this.referenceCount = 1;
      this.imageAnchorProperties = MOVE_WITH_CELLS;
      this.type = ShapeType.PICTURE_FRAME;
   }

   protected Drawing(DrawingGroupObject var1, DrawingGroup var2) {
      this.initialized = false;
      Drawing var4 = (Drawing)var1;
      boolean var3;
      if (var4.origin == Origin.READ) {
         var3 = true;
      } else {
         var3 = false;
      }

      Assert.verify(var3);
      this.msoDrawingRecord = var4.msoDrawingRecord;
      this.objRecord = var4.objRecord;
      this.initialized = false;
      this.origin = Origin.READ;
      this.drawingData = var4.drawingData;
      this.drawingGroup = var2;
      this.drawingNumber = var4.drawingNumber;
      var2.addDrawing(this);
   }

   public Drawing(MsoDrawingRecord var1, ObjRecord var2, DrawingData var3, DrawingGroup var4, Sheet var5) {
      this.drawingGroup = var4;
      this.msoDrawingRecord = var1;
      this.drawingData = var3;
      this.objRecord = var2;
      this.sheet = var5;
      boolean var7 = false;
      this.initialized = false;
      this.origin = Origin.READ;
      this.drawingData.addData(this.msoDrawingRecord.getData());
      this.drawingNumber = this.drawingData.getNumDrawings() - 1;
      this.drawingGroup.addDrawing(this);
      boolean var6 = var7;
      if (var1 != null) {
         var6 = var7;
         if (var2 != null) {
            var6 = true;
         }
      }

      Assert.verify(var6);
      this.initialize();
   }

   private double getHeightInPoints() {
      Sheet var9 = this.sheet;
      double var1 = 0.0;
      if (var9 == null) {
         logger.warn("calculating image height:  sheet is null");
         return 0.0;
      } else {
         double var3 = this.y;
         int var8 = (int)var3;
         int var7 = (int)Math.ceil(var3 + this.height) - 1;
         var3 = (double)this.sheet.getRowView(var8).getSize();
         int var6 = 0;
         int var5;
         if (var7 != var8) {
            var5 = this.sheet.getRowView(var7).getSize();
         } else {
            var5 = 0;
         }

         while(var6 < var7 - var8 - 1) {
            var1 += (double)this.sheet.getRowView(var8 + 1 + var6).getSize();
            ++var6;
         }

         return (var1 + var3 + (double)var5) / 20.0;
      }
   }

   private PNGReader getPngReader() {
      PNGReader var1 = this.pngReader;
      if (var1 != null) {
         return var1;
      } else {
         byte[] var3;
         if (this.origin != Origin.READ && this.origin != Origin.READ_WRITE) {
            try {
               var3 = this.getImageBytes();
            } catch (IOException var2) {
               logger.warn("Could not read image file");
               var3 = new byte[0];
            }
         } else {
            var3 = this.getImageData();
         }

         var1 = new PNGReader(var3);
         this.pngReader = var1;
         var1.read();
         return this.pngReader;
      }
   }

   private EscherContainer getReadSpContainer() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.readSpContainer;
   }

   private double getWidthInPoints() {
      if (this.sheet == null) {
         logger.warn("calculating image width:  sheet is null");
         return 0.0;
      } else {
         double var1 = this.x;
         int var14 = (int)var1;
         int var15 = (int)Math.ceil(var1 + this.width) - 1;
         CellView var16 = this.sheet.getColumnView(var14);
         int var13 = var16.getSize();
         double var5 = this.x;
         double var7 = (double)var14;
         double var3 = (double)var13;
         if (var16.getFormat() != null) {
            var1 = (double)var16.getFormat().getFont().getPointSize();
         } else {
            var1 = 10.0;
         }

         var7 = (1.0 - (var5 - var7)) * var3 * 0.59 * var1 / 256.0;
         if (var15 != var14) {
            var16 = this.sheet.getColumnView(var15);
            var13 = var16.getSize();
            double var11 = this.x;
            double var9 = this.width;
            var3 = (double)var15;
            var5 = (double)var13;
            if (var16.getFormat() != null) {
               var1 = (double)var16.getFormat().getFont().getPointSize();
            } else {
               var1 = 10.0;
            }

            var1 = (var11 + var9 - var3) * var5 * 0.59 * var1 / 256.0;
         } else {
            var1 = 0.0;
         }

         var13 = 0;

         for(var3 = 0.0; var13 < var15 - var14 - 1; ++var13) {
            var16 = this.sheet.getColumnView(var14 + 1 + var13);
            if (var16.getFormat() != null) {
               var5 = (double)var16.getFormat().getFont().getPointSize();
            } else {
               var5 = 10.0;
            }

            var3 += (double)var16.getSize() * 0.59 * var5 / 256.0;
         }

         return var3 + var7 + var1;
      }
   }

   private void initialize() {
      EscherContainer var3 = this.drawingData.getSpContainer(this.drawingNumber);
      this.readSpContainer = var3;
      int var1 = 0;
      boolean var2;
      if (var3 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      Assert.verify(var2);
      EscherRecord[] var4 = this.readSpContainer.getChildren();
      Sp var5 = (Sp)this.readSpContainer.getChildren()[0];
      this.shapeId = var5.getShapeId();
      this.objectId = this.objRecord.getObjectId();
      ShapeType var6 = ShapeType.getType(var5.getShapeType());
      this.type = var6;
      if (var6 == ShapeType.UNKNOWN) {
         logger.warn("Unknown shape type");
      }

      Opt var7 = (Opt)this.readSpContainer.getChildren()[1];
      if (var7.getProperty(260) != null) {
         this.blipId = var7.getProperty(260).value;
      }

      if (var7.getProperty(261) != null) {
         this.imageFile = new File(var7.getProperty(261).stringValue);
      } else if (this.type == ShapeType.PICTURE_FRAME) {
         logger.warn("no filename property for drawing");
         this.imageFile = new File(Integer.toString(this.blipId));
      }

      ClientAnchor var8;
      for(var8 = null; var1 < var4.length && var8 == null; ++var1) {
         if (var4[var1].getType() == EscherRecordType.CLIENT_ANCHOR) {
            var8 = (ClientAnchor)var4[var1];
         }
      }

      if (var8 == null) {
         logger.warn("client anchor not found");
      } else {
         this.x = var8.getX1();
         this.y = var8.getY1();
         this.width = var8.getX2() - this.x;
         this.height = var8.getY2() - this.y;
         this.imageAnchorProperties = ImageAnchorProperties.getImageAnchorProperties(var8.getProperties());
      }

      if (this.blipId == 0) {
         logger.warn("linked drawings are not supported");
      }

      this.initialized = true;
   }

   public final int getBlipId() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.blipId;
   }

   public double getColumn() {
      return this.getX();
   }

   public DrawingGroup getDrawingGroup() {
      return this.drawingGroup;
   }

   public double getHeight() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.height;
   }

   public double getHeight(LengthUnit var1) {
      return this.getHeightInPoints() * LengthConverter.getConversionFactor(LengthUnit.POINTS, var1);
   }

   public double getHorizontalResolution(LengthUnit var1) {
      return (double)this.getPngReader().getHorizontalResolution() / LengthConverter.getConversionFactor(LengthUnit.METRES, var1);
   }

   protected ImageAnchorProperties getImageAnchor() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.imageAnchorProperties;
   }

   public byte[] getImageBytes() throws IOException {
      if (this.origin != Origin.READ && this.origin != Origin.READ_WRITE) {
         Origin var4 = this.origin;
         Origin var5 = Origin.WRITE;
         boolean var3 = true;
         boolean var2;
         if (var4 == var5) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         File var6 = this.imageFile;
         if (var6 == null) {
            if (this.imageData != null) {
               var2 = var3;
            } else {
               var2 = false;
            }

            Assert.verify(var2);
            return this.imageData;
         } else {
            int var1 = (int)var6.length();
            byte[] var8 = new byte[var1];
            FileInputStream var7 = new FileInputStream(this.imageFile);
            var7.read(var8, 0, var1);
            var7.close();
            return var8;
         }
      } else {
         return this.getImageData();
      }
   }

   public byte[] getImageData() {
      boolean var1;
      if (this.origin != Origin.READ && this.origin != Origin.READ_WRITE) {
         var1 = false;
      } else {
         var1 = true;
      }

      Assert.verify(var1);
      if (!this.initialized) {
         this.initialize();
      }

      return this.drawingGroup.getImageData(this.blipId);
   }

   public File getImageFile() {
      return this.imageFile;
   }

   public String getImageFilePath() {
      File var2 = this.imageFile;
      if (var2 == null) {
         int var1 = this.blipId;
         String var3;
         if (var1 != 0) {
            var3 = Integer.toString(var1);
         } else {
            var3 = "__new__image__";
         }

         return var3;
      } else {
         return var2.getPath();
      }
   }

   public int getImageHeight() {
      return this.getPngReader().getHeight();
   }

   public int getImageWidth() {
      return this.getPngReader().getWidth();
   }

   public MsoDrawingRecord getMsoDrawingRecord() {
      return this.msoDrawingRecord;
   }

   public final int getObjectId() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.objectId;
   }

   public Origin getOrigin() {
      return this.origin;
   }

   public int getReferenceCount() {
      return this.referenceCount;
   }

   public double getRow() {
      return this.getY();
   }

   public int getShapeId() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.shapeId;
   }

   public EscherContainer getSpContainer() {
      if (!this.initialized) {
         this.initialize();
      }

      if (this.origin == Origin.READ) {
         return this.getReadSpContainer();
      } else {
         SpContainer var6 = new SpContainer();
         var6.add(new Sp(this.type, this.shapeId, 2560));
         Opt var7 = new Opt();
         var7.addProperty(260, true, false, this.blipId);
         if (this.type == ShapeType.PICTURE_FRAME) {
            File var5 = this.imageFile;
            String var8;
            if (var5 != null) {
               var8 = var5.getPath();
            } else {
               var8 = "";
            }

            var7.addProperty(261, true, true, var8.length() * 2, var8);
            var7.addProperty(447, false, false, 65536);
            var7.addProperty(959, false, false, 524288);
            var6.add(var7);
         }

         double var3 = this.x;
         double var1 = this.y;
         var6.add(new ClientAnchor(var3, var1, var3 + this.width, var1 + this.height, this.imageAnchorProperties.getValue()));
         var6.add(new ClientData());
         return var6;
      }
   }

   public ShapeType getType() {
      return this.type;
   }

   public double getVerticalResolution(LengthUnit var1) {
      return (double)this.getPngReader().getVerticalResolution() / LengthConverter.getConversionFactor(LengthUnit.METRES, var1);
   }

   public double getWidth() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.width;
   }

   public double getWidth(LengthUnit var1) {
      return this.getWidthInPoints() * LengthConverter.getConversionFactor(LengthUnit.POINTS, var1);
   }

   public double getX() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.x;
   }

   public double getY() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.y;
   }

   public boolean isFirst() {
      return this.msoDrawingRecord.isFirst();
   }

   public boolean isFormObject() {
      return false;
   }

   public void removeRow(int var1) {
      double var4 = this.y;
      double var2 = (double)var1;
      if (var4 > var2) {
         this.setY(var2);
      }

   }

   public void setDrawingGroup(DrawingGroup var1) {
      this.drawingGroup = var1;
   }

   public void setHeight(double var1) {
      if (this.origin == Origin.READ) {
         if (!this.initialized) {
            this.initialize();
         }

         this.origin = Origin.READ_WRITE;
      }

      this.height = var1;
   }

   protected void setImageAnchor(ImageAnchorProperties var1) {
      this.imageAnchorProperties = var1;
      if (this.origin == Origin.READ) {
         this.origin = Origin.READ_WRITE;
      }

   }

   public final void setObjectId(int var1, int var2, int var3) {
      this.objectId = var1;
      this.blipId = var2;
      this.shapeId = var3;
      if (this.origin == Origin.READ) {
         this.origin = Origin.READ_WRITE;
      }

   }

   public void setReferenceCount(int var1) {
      this.referenceCount = var1;
   }

   public void setWidth(double var1) {
      if (this.origin == Origin.READ) {
         if (!this.initialized) {
            this.initialize();
         }

         this.origin = Origin.READ_WRITE;
      }

      this.width = var1;
   }

   public void setX(double var1) {
      if (this.origin == Origin.READ) {
         if (!this.initialized) {
            this.initialize();
         }

         this.origin = Origin.READ_WRITE;
      }

      this.x = var1;
   }

   public void setY(double var1) {
      if (this.origin == Origin.READ) {
         if (!this.initialized) {
            this.initialize();
         }

         this.origin = Origin.READ_WRITE;
      }

      this.y = var1;
   }

   public void writeAdditionalRecords(jxl.write.biff.File var1) throws IOException {
      if (this.origin == Origin.READ) {
         var1.write(this.objRecord);
      } else {
         var1.write(new ObjRecord(this.objectId, ObjRecord.PICTURE));
      }
   }

   public void writeTailRecords(jxl.write.biff.File var1) throws IOException {
   }

   protected static class ImageAnchorProperties {
      private static ImageAnchorProperties[] o = new ImageAnchorProperties[0];
      private int value;

      ImageAnchorProperties(int var1) {
         this.value = var1;
         ImageAnchorProperties[] var3 = o;
         ImageAnchorProperties[] var2 = new ImageAnchorProperties[var3.length + 1];
         o = var2;
         System.arraycopy(var3, 0, var2, 0, var3.length);
         o[var3.length] = this;
      }

      static ImageAnchorProperties getImageAnchorProperties(int var0) {
         ImageAnchorProperties var3 = Drawing.MOVE_AND_SIZE_WITH_CELLS;
         int var1 = 0;

         ImageAnchorProperties var2;
         while(true) {
            ImageAnchorProperties[] var4 = o;
            var2 = var3;
            if (var1 >= var4.length) {
               break;
            }

            if (var4[var1].getValue() == var0) {
               var2 = o[var1];
               break;
            }

            ++var1;
         }

         return var2;
      }

      int getValue() {
         return this.value;
      }
   }
}
