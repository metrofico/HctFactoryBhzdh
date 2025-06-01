package jxl.biff.drawing;

import java.io.IOException;
import jxl.WorkbookSettings;
import jxl.biff.ContinueRecord;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.write.biff.File;

public class CheckBox implements DrawingGroupObject {
   private static Logger logger = Logger.getLogger(CheckBox.class);
   private int blipId;
   private int column;
   private DrawingData drawingData;
   private DrawingGroup drawingGroup;
   private int drawingNumber;
   private EscherContainer escherData;
   private ContinueRecord formatting;
   private double height;
   private boolean initialized;
   private MsoDrawingRecord mso;
   private MsoDrawingRecord msoDrawingRecord;
   private ObjRecord objRecord;
   private int objectId;
   private Origin origin;
   private EscherContainer readSpContainer;
   private int referenceCount;
   private int row;
   private int shapeId;
   private EscherContainer spContainer;
   private ContinueRecord text;
   private TextObjectRecord txo;
   private ShapeType type;
   private double width;
   private WorkbookSettings workbookSettings;

   public CheckBox() {
      this.initialized = true;
      this.origin = Origin.WRITE;
      this.referenceCount = 1;
      this.type = ShapeType.HOST_CONTROL;
   }

   public CheckBox(DrawingGroupObject var1, DrawingGroup var2, WorkbookSettings var3) {
      this.initialized = false;
      CheckBox var5 = (CheckBox)var1;
      boolean var4;
      if (var5.origin == Origin.READ) {
         var4 = true;
      } else {
         var4 = false;
      }

      Assert.verify(var4);
      this.msoDrawingRecord = var5.msoDrawingRecord;
      this.objRecord = var5.objRecord;
      this.initialized = false;
      this.origin = Origin.READ;
      this.drawingData = var5.drawingData;
      this.drawingGroup = var2;
      this.drawingNumber = var5.drawingNumber;
      var2.addDrawing(this);
      this.mso = var5.mso;
      this.txo = var5.txo;
      this.text = var5.text;
      this.formatting = var5.formatting;
      this.workbookSettings = var3;
   }

   public CheckBox(MsoDrawingRecord var1, ObjRecord var2, DrawingData var3, DrawingGroup var4, WorkbookSettings var5) {
      this.drawingGroup = var4;
      this.msoDrawingRecord = var1;
      this.drawingData = var3;
      this.objRecord = var2;
      boolean var7 = false;
      this.initialized = false;
      this.workbookSettings = var5;
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

   private EscherContainer getReadSpContainer() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.readSpContainer;
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
      this.objectId = this.objRecord.getObjectId();
      this.shapeId = var5.getShapeId();
      ShapeType var6 = ShapeType.getType(var5.getShapeType());
      this.type = var6;
      if (var6 == ShapeType.UNKNOWN) {
         logger.warn("Unknown shape type");
      }

      ClientAnchor var7;
      for(var7 = null; var1 < var4.length && var7 == null; ++var1) {
         if (var4[var1].getType() == EscherRecordType.CLIENT_ANCHOR) {
            var7 = (ClientAnchor)var4[var1];
         }
      }

      if (var7 == null) {
         logger.warn("Client anchor not found");
      } else {
         this.column = (int)var7.getX1();
         this.row = (int)var7.getY1();
      }

      this.initialized = true;
   }

   public void addMso(MsoDrawingRecord var1) {
      this.mso = var1;
      this.drawingData.addRawData(var1.getData());
   }

   public final int getBlipId() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.blipId;
   }

   public int getColumn() {
      return 0;
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

   public byte[] getImageBytes() {
      Assert.verify(false);
      return null;
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

   public int getRow() {
      return 0;
   }

   public final int getShapeId() {
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
         SpContainer var5 = new SpContainer();
         var5.add(new Sp(this.type, this.shapeId, 2560));
         Opt var6 = new Opt();
         var6.addProperty(127, false, false, 17039620);
         var6.addProperty(191, false, false, 524296);
         var6.addProperty(511, false, false, 524288);
         var6.addProperty(959, false, false, 131072);
         var5.add(var6);
         int var3 = this.column;
         double var1 = (double)var3;
         int var4 = this.row;
         var5.add(new ClientAnchor(var1, (double)var4, (double)(var3 + 1), (double)(var4 + 1), 1));
         var5.add(new ClientData());
         return var5;
      }
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

      return (double)this.column;
   }

   public double getY() {
      if (!this.initialized) {
         this.initialize();
      }

      return (double)this.row;
   }

   public int hashCode() {
      return this.getClass().getName().hashCode();
   }

   public boolean isFirst() {
      return this.msoDrawingRecord.isFirst();
   }

   public boolean isFormObject() {
      return false;
   }

   public void setDrawingGroup(DrawingGroup var1) {
      this.drawingGroup = var1;
   }

   public void setFormatting(ContinueRecord var1) {
      this.formatting = var1;
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

   public void setText(ContinueRecord var1) {
      this.text = var1;
   }

   public void setTextObject(TextObjectRecord var1) {
      this.txo = var1;
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

      this.column = (int)var1;
   }

   public void setY(double var1) {
      if (this.origin == Origin.READ) {
         if (!this.initialized) {
            this.initialize();
         }

         this.origin = Origin.READ_WRITE;
      }

      this.row = (int)var1;
   }

   public void writeAdditionalRecords(File var1) throws IOException {
      if (this.origin == Origin.READ) {
         var1.write(this.objRecord);
         MsoDrawingRecord var2 = this.mso;
         if (var2 != null) {
            var1.write(var2);
         }

         var1.write(this.txo);
         var1.write(this.text);
         ContinueRecord var3 = this.formatting;
         if (var3 != null) {
            var1.write(var3);
         }

      } else {
         var1.write(new ObjRecord(this.objectId, ObjRecord.CHECKBOX));
         logger.warn("Writing of additional records for checkboxes not implemented");
      }
   }

   public void writeTailRecords(File var1) {
   }
}
