package jxl.biff.drawing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.common.Assert;
import jxl.common.Logger;

public class Drawing2 implements DrawingGroupObject {
   private static Logger logger = Logger.getLogger(Drawing.class);
   private int blipId;
   private DrawingData drawingData;
   private DrawingGroup drawingGroup;
   private int drawingNumber;
   private EscherContainer escherData;
   private double height;
   private byte[] imageData;
   private File imageFile;
   private boolean initialized;
   private MsoDrawingRecord msoDrawingRecord;
   private int objectId;
   private Origin origin;
   private EscherContainer readSpContainer;
   private int referenceCount;
   private int shapeId;
   private ShapeType type;
   private double width;
   private double x;
   private double y;

   public Drawing2(double var1, double var3, double var5, double var7, File var9) {
      this.imageFile = var9;
      this.initialized = true;
      this.origin = Origin.WRITE;
      this.x = var1;
      this.y = var3;
      this.width = var5;
      this.height = var7;
      this.referenceCount = 1;
      this.type = ShapeType.PICTURE_FRAME;
   }

   public Drawing2(double var1, double var3, double var5, double var7, byte[] var9) {
      this.imageData = var9;
      this.initialized = true;
      this.origin = Origin.WRITE;
      this.x = var1;
      this.y = var3;
      this.width = var5;
      this.height = var7;
      this.referenceCount = 1;
      this.type = ShapeType.PICTURE_FRAME;
   }

   protected Drawing2(DrawingGroupObject var1, DrawingGroup var2) {
      this.initialized = false;
      Drawing2 var4 = (Drawing2)var1;
      boolean var3;
      if (var4.origin == Origin.READ) {
         var3 = true;
      } else {
         var3 = false;
      }

      Assert.verify(var3);
      this.msoDrawingRecord = var4.msoDrawingRecord;
      this.initialized = false;
      this.origin = Origin.READ;
      this.drawingData = var4.drawingData;
      this.drawingGroup = var2;
      this.drawingNumber = var4.drawingNumber;
      var2.addDrawing(this);
   }

   public Drawing2(MsoDrawingRecord var1, DrawingData var2, DrawingGroup var3) {
      this.drawingGroup = var3;
      this.msoDrawingRecord = var1;
      this.drawingData = var2;
      boolean var4 = false;
      this.initialized = false;
      this.origin = Origin.READ;
      this.drawingData.addRawData(this.msoDrawingRecord.getData());
      this.drawingGroup.addDrawing(this);
      if (var1 != null) {
         var4 = true;
      }

      Assert.verify(var4);
      this.initialize();
   }

   private EscherContainer getReadSpContainer() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.readSpContainer;
   }

   private void initialize() {
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

   public byte[] getImageBytes() throws IOException {
      boolean var3 = false;
      Assert.verify(false);
      if (this.origin != Origin.READ && this.origin != Origin.READ_WRITE) {
         boolean var2;
         if (this.origin == Origin.WRITE) {
            var2 = true;
         } else {
            var2 = false;
         }

         Assert.verify(var2);
         File var4 = this.imageFile;
         if (var4 == null) {
            var2 = var3;
            if (this.imageData != null) {
               var2 = true;
            }

            Assert.verify(var2);
            return this.imageData;
         } else {
            int var1 = (int)var4.length();
            byte[] var5 = new byte[var1];
            FileInputStream var6 = new FileInputStream(this.imageFile);
            var6.read(var5, 0, var1);
            var6.close();
            return var5;
         }
      } else {
         return this.getImageData();
      }
   }

   public byte[] getImageData() {
      boolean var1 = false;
      Assert.verify(false);
      if (this.origin == Origin.READ || this.origin == Origin.READ_WRITE) {
         var1 = true;
      }

      Assert.verify(var1);
      if (!this.initialized) {
         this.initialize();
      }

      return this.drawingGroup.getImageData(this.blipId);
   }

   public String getImageFilePath() {
      Assert.verify(false);
      return null;
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

      boolean var1;
      if (this.origin == Origin.READ) {
         var1 = true;
      } else {
         var1 = false;
      }

      Assert.verify(var1);
      return this.getReadSpContainer();
   }

   public ShapeType getType() {
      return this.type;
   }

   public double getWidth() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.width;
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
   }

   public void writeTailRecords(jxl.write.biff.File var1) throws IOException {
   }
}
