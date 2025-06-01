package jxl.biff.drawing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.read.biff.Record;
import jxl.write.biff.File;

public class DrawingGroup implements EscherStream {
   private static Logger logger = Logger.getLogger(DrawingGroup.class);
   private BStoreContainer bstoreContainer;
   private byte[] drawingData;
   private int drawingGroupId;
   private ArrayList drawings;
   private boolean drawingsOmitted;
   private EscherContainer escherData;
   private HashMap imageFiles;
   private boolean initialized;
   private int maxObjectId;
   private int maxShapeId;
   private int numBlips;
   private int numCharts;
   private Origin origin;

   public DrawingGroup(DrawingGroup var1) {
      this.drawingData = var1.drawingData;
      this.escherData = var1.escherData;
      this.bstoreContainer = var1.bstoreContainer;
      this.initialized = var1.initialized;
      this.drawingData = var1.drawingData;
      this.escherData = var1.escherData;
      this.bstoreContainer = var1.bstoreContainer;
      this.numBlips = var1.numBlips;
      this.numCharts = var1.numCharts;
      this.drawingGroupId = var1.drawingGroupId;
      this.drawingsOmitted = var1.drawingsOmitted;
      this.origin = var1.origin;
      this.imageFiles = (HashMap)var1.imageFiles.clone();
      this.maxObjectId = var1.maxObjectId;
      this.maxShapeId = var1.maxShapeId;
      this.drawings = new ArrayList();
   }

   public DrawingGroup(Origin var1) {
      this.origin = var1;
      boolean var2;
      if (var1 == Origin.WRITE) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.initialized = var2;
      this.drawings = new ArrayList();
      this.imageFiles = new HashMap();
      this.drawingsOmitted = false;
      this.maxObjectId = 1;
      this.maxShapeId = 1024;
   }

   private void addData(byte[] var1) {
      byte[] var3 = this.drawingData;
      byte[] var2;
      if (var3 == null) {
         var2 = new byte[var1.length];
         this.drawingData = var2;
         System.arraycopy(var1, 0, var2, 0, var1.length);
      } else {
         var2 = new byte[var3.length + var1.length];
         System.arraycopy(var3, 0, var2, 0, var3.length);
         System.arraycopy(var1, 0, var2, this.drawingData.length, var1.length);
         this.drawingData = var2;
      }
   }

   private BStoreContainer getBStoreContainer() {
      if (this.bstoreContainer == null) {
         if (!this.initialized) {
            this.initialize();
         }

         EscherRecord[] var1 = this.escherData.getChildren();
         if (var1.length > 1 && var1[1].getType() == EscherRecordType.BSTORE_CONTAINER) {
            this.bstoreContainer = (BStoreContainer)var1[1];
         }
      }

      return this.bstoreContainer;
   }

   private void initialize() {
      boolean var2 = false;
      EscherRecordData var3 = new EscherRecordData(this, 0);
      Assert.verify(var3.isContainer());
      EscherContainer var4 = new EscherContainer(var3);
      this.escherData = var4;
      boolean var1;
      if (var4.getLength() == this.drawingData.length) {
         var1 = true;
      } else {
         var1 = false;
      }

      Assert.verify(var1);
      var1 = var2;
      if (this.escherData.getType() == EscherRecordType.DGG_CONTAINER) {
         var1 = true;
      }

      Assert.verify(var1);
      this.initialized = true;
   }

   public void add(Chart var1) {
      ++this.numCharts;
   }

   public void add(DrawingGroupObject var1) {
      if (this.origin == Origin.READ) {
         this.origin = Origin.READ_WRITE;
         BStoreContainer var4 = this.getBStoreContainer();
         EscherRecord[] var5 = this.escherData.getChildren();
         boolean var3 = false;
         this.drawingGroupId = ((Dgg)var5[0]).getCluster(1).drawingGroupId - this.numBlips - 1;
         int var2;
         if (var4 != null) {
            var2 = var4.getNumBlips();
         } else {
            var2 = 0;
         }

         this.numBlips = var2;
         if (var4 != null) {
            if (var2 == var4.getNumBlips()) {
               var3 = true;
            }

            Assert.verify(var3);
         }
      }

      if (!(var1 instanceof Drawing)) {
         ++this.maxObjectId;
         ++this.maxShapeId;
         var1.setDrawingGroup(this);
         var1.setObjectId(this.maxObjectId, this.numBlips + 1, this.maxShapeId);
         if (this.drawings.size() > this.maxObjectId) {
            logger.warn("drawings length " + this.drawings.size() + " exceeds the max object id " + this.maxObjectId);
         }

      } else {
         Drawing var7 = (Drawing)var1;
         Drawing var6 = (Drawing)this.imageFiles.get(var1.getImageFilePath());
         if (var6 == null) {
            ++this.maxObjectId;
            ++this.maxShapeId;
            this.drawings.add(var7);
            var7.setDrawingGroup(this);
            var7.setObjectId(this.maxObjectId, this.numBlips + 1, this.maxShapeId);
            ++this.numBlips;
            this.imageFiles.put(var7.getImageFilePath(), var7);
         } else {
            var6.setReferenceCount(var6.getReferenceCount() + 1);
            var7.setDrawingGroup(this);
            var7.setObjectId(var6.getObjectId(), var6.getBlipId(), var6.getShapeId());
         }

      }
   }

   public void add(MsoDrawingGroupRecord var1) {
      this.addData(var1.getData());
   }

   public void add(Record var1) {
      this.addData(var1.getData());
   }

   final void addDrawing(DrawingGroupObject var1) {
      this.drawings.add(var1);
      this.maxObjectId = Math.max(this.maxObjectId, var1.getObjectId());
      this.maxShapeId = Math.max(this.maxShapeId, var1.getShapeId());
   }

   public byte[] getData() {
      return this.drawingData;
   }

   byte[] getImageData(int var1) {
      int var2 = this.getBStoreContainer().getNumBlips();
      this.numBlips = var2;
      boolean var4 = false;
      boolean var3;
      if (var1 <= var2) {
         var3 = true;
      } else {
         var3 = false;
      }

      label15: {
         Assert.verify(var3);
         if (this.origin != Origin.READ) {
            var3 = var4;
            if (this.origin != Origin.READ_WRITE) {
               break label15;
            }
         }

         var3 = true;
      }

      Assert.verify(var3);
      return ((BlipStoreEntry)this.getBStoreContainer().getChildren()[var1 - 1]).getImageData();
   }

   final int getNumberOfBlips() {
      return this.numBlips;
   }

   public boolean hasDrawingsOmitted() {
      return this.drawingsOmitted;
   }

   public void remove(DrawingGroupObject var1) {
      if (this.getBStoreContainer() != null) {
         if (this.origin == Origin.READ) {
            this.origin = Origin.READ_WRITE;
            this.numBlips = this.getBStoreContainer().getNumBlips();
            this.drawingGroupId = ((Dgg)this.escherData.getChildren()[0]).getCluster(1).drawingGroupId - this.numBlips - 1;
         }

         BlipStoreEntry var2 = (BlipStoreEntry)this.getBStoreContainer().getChildren()[var1.getBlipId() - 1];
         var2.dereference();
         if (var2.getReferenceCount() == 0) {
            this.getBStoreContainer().remove(var2);
            Iterator var4 = this.drawings.iterator();

            while(var4.hasNext()) {
               DrawingGroupObject var3 = (DrawingGroupObject)var4.next();
               if (var3.getBlipId() > var1.getBlipId()) {
                  var3.setObjectId(var3.getObjectId(), var3.getBlipId() - 1, var3.getShapeId());
               }
            }

            --this.numBlips;
         }

      }
   }

   public void setDrawingsOmitted(MsoDrawingRecord var1, ObjRecord var2) {
      this.drawingsOmitted = true;
      if (var2 != null) {
         this.maxObjectId = Math.max(this.maxObjectId, var2.getObjectId());
      }

   }

   public void updateData(DrawingGroup var1) {
      this.drawingsOmitted = var1.drawingsOmitted;
      this.maxObjectId = var1.maxObjectId;
      this.maxShapeId = var1.maxShapeId;
   }

   public void write(File var1) throws IOException {
      Origin var5 = this.origin;
      Origin var4 = Origin.WRITE;
      int var2 = 0;
      DggContainer var8;
      Dgg var9;
      if (var5 == var4) {
         var8 = new DggContainer();
         int var3 = this.numBlips;
         var9 = new Dgg(this.numCharts + var3 + 1, var3);
         var9.addCluster(1, 0);
         var9.addCluster(this.numBlips + 1, 0);
         var8.add(var9);
         BStoreContainer var7 = new BStoreContainer();
         Iterator var10 = this.drawings.iterator();

         while(var10.hasNext()) {
            Object var6 = var10.next();
            if (var6 instanceof Drawing) {
               var7.add(new BlipStoreEntry((Drawing)var6));
               ++var2;
            }
         }

         if (var2 > 0) {
            var7.setNumBlips(var2);
            var8.add(var7);
         }

         var8.add(new Opt());
         var8.add(new SplitMenuColors());
         this.drawingData = var8.getData();
      } else if (this.origin == Origin.READ_WRITE) {
         var8 = new DggContainer();
         var2 = this.numBlips;
         var9 = new Dgg(this.numCharts + var2 + 1, var2);
         var9.addCluster(1, 0);
         var9.addCluster(this.drawingGroupId + this.numBlips + 1, 0);
         var8.add(var9);
         BStoreContainer var11 = new BStoreContainer();
         var11.setNumBlips(this.numBlips);
         BStoreContainer var12 = this.getBStoreContainer();
         if (var12 != null) {
            EscherRecord[] var13 = var12.getChildren();

            for(var2 = 0; var2 < var13.length; ++var2) {
               var11.add((BlipStoreEntry)var13[var2]);
            }
         }

         Iterator var15 = this.drawings.iterator();

         while(var15.hasNext()) {
            DrawingGroupObject var16 = (DrawingGroupObject)var15.next();
            if (var16 instanceof Drawing) {
               Drawing var17 = (Drawing)var16;
               if (var17.getOrigin() == Origin.WRITE) {
                  var11.add(new BlipStoreEntry(var17));
               }
            }
         }

         var8.add(var11);
         Opt var14 = new Opt();
         var14.addProperty(191, false, false, 524296);
         var14.addProperty(385, false, false, 134217737);
         var14.addProperty(448, false, false, 134217792);
         var8.add(var14);
         var8.add(new SplitMenuColors());
         this.drawingData = var8.getData();
      }

      var1.write(new MsoDrawingGroupRecord(this.drawingData));
   }
}
