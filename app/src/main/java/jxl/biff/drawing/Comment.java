package jxl.biff.drawing;

import java.io.IOException;
import jxl.WorkbookSettings;
import jxl.biff.ContinueRecord;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.write.biff.File;

public class Comment implements DrawingGroupObject {
   private static Logger logger = Logger.getLogger(Comment.class);
   private int blipId;
   private int column;
   private String commentText;
   private DrawingData drawingData;
   private DrawingGroup drawingGroup;
   private int drawingNumber;
   private EscherContainer escherData;
   private ContinueRecord formatting;
   private double height;
   private boolean initialized;
   private MsoDrawingRecord mso;
   private MsoDrawingRecord msoDrawingRecord;
   private NoteRecord note;
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

   public Comment(String var1, int var2, int var3) {
      this.initialized = true;
      this.origin = Origin.WRITE;
      this.column = var2;
      this.row = var3;
      this.referenceCount = 1;
      this.type = ShapeType.TEXT_BOX;
      this.commentText = var1;
      this.width = 3.0;
      this.height = 4.0;
   }

   public Comment(DrawingGroupObject var1, DrawingGroup var2, WorkbookSettings var3) {
      this.initialized = false;
      Comment var5 = (Comment)var1;
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
      this.note = var5.note;
      this.width = var5.width;
      this.height = var5.height;
      this.workbookSettings = var3;
   }

   public Comment(MsoDrawingRecord var1, ObjRecord var2, DrawingData var3, DrawingGroup var4, WorkbookSettings var5) {
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
      if (this.msoDrawingRecord != null) {
         var6 = var7;
         if (this.objRecord != null) {
            var6 = true;
         }
      }

      Assert.verify(var6);
      if (!this.initialized) {
         this.initialize();
      }

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
         logger.warn("client anchor not found");
      } else {
         this.column = (int)var7.getX1() - 1;
         this.row = (int)var7.getY1() + 1;
         this.width = var7.getX2() - var7.getX1();
         this.height = var7.getY2() - var7.getY1();
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
      return this.note.getColumn();
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
      return this.note.getRow();
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
         if (this.spContainer == null) {
            this.spContainer = new SpContainer();
            Sp var1 = new Sp(this.type, this.shapeId, 2560);
            this.spContainer.add(var1);
            Opt var2 = new Opt();
            var2.addProperty(344, false, false, 0);
            var2.addProperty(385, false, false, 134217808);
            var2.addProperty(387, false, false, 134217808);
            var2.addProperty(959, false, false, 131074);
            this.spContainer.add(var2);
            ClientAnchor var3 = new ClientAnchor((double)this.column + 1.3, Math.max(0.0, (double)this.row - 0.6), (double)this.column + 1.3 + this.width, (double)this.row + this.height, 1);
            this.spContainer.add(var3);
            ClientData var4 = new ClientData();
            this.spContainer.add(var4);
            ClientTextBox var5 = new ClientTextBox();
            this.spContainer.add(var5);
         }

         return this.spContainer;
      }
   }

   public String getText() {
      if (this.commentText == null) {
         boolean var1;
         if (this.text != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         Assert.verify(var1);
         byte[] var2 = this.text.getData();
         if (var2[0] == 0) {
            this.commentText = StringHelper.getString(var2, var2.length - 1, 1, this.workbookSettings);
         } else {
            this.commentText = StringHelper.getUnicodeString(var2, (var2.length - 1) / 2, 1);
         }
      }

      return this.commentText;
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
      return this.commentText.hashCode();
   }

   public boolean isFirst() {
      return this.msoDrawingRecord.isFirst();
   }

   public boolean isFormObject() {
      return true;
   }

   public void setCommentText(String var1) {
      this.commentText = var1;
      if (this.origin == Origin.READ) {
         this.origin = Origin.READ_WRITE;
      }

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

   public void setNote(NoteRecord var1) {
      this.note = var1;
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
         MsoDrawingRecord var3 = this.mso;
         if (var3 != null) {
            var1.write(var3);
         }

         var1.write(this.txo);
         var1.write(this.text);
         ContinueRecord var4 = this.formatting;
         if (var4 != null) {
            var1.write(var4);
         }

      } else {
         var1.write(new ObjRecord(this.objectId, ObjRecord.EXCELNOTE));
         var1.write(new MsoDrawingRecord((new ClientTextBox()).getData()));
         var1.write(new TextObjectRecord(this.getText()));
         byte[] var2 = new byte[this.commentText.length() * 2 + 1];
         var2[0] = 1;
         StringHelper.getUnicodeBytes(this.commentText, var2, 1);
         var1.write(new ContinueRecord(var2));
         var2 = new byte[16];
         IntegerHelper.getTwoBytes(0, var2, 0);
         IntegerHelper.getTwoBytes(0, var2, 2);
         IntegerHelper.getTwoBytes(this.commentText.length(), var2, 8);
         IntegerHelper.getTwoBytes(0, var2, 10);
         var1.write(new ContinueRecord(var2));
      }
   }

   public void writeTailRecords(File var1) throws IOException {
      if (this.origin == Origin.READ) {
         var1.write(this.note);
      } else {
         var1.write(new NoteRecord(this.column, this.row, this.objectId));
      }
   }
}
