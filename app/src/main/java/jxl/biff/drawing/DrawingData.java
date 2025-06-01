package jxl.biff.drawing;

import java.util.ArrayList;
import jxl.common.Assert;
import jxl.common.Logger;

public class DrawingData implements EscherStream {
   private static Logger logger = Logger.getLogger(DrawingData.class);
   private byte[] drawingData = null;
   private boolean initialized = false;
   private int numDrawings = 0;
   private EscherRecord[] spContainers;

   private void getSpContainers(EscherContainer var1, ArrayList var2) {
      EscherRecord[] var4 = var1.getChildren();

      for(int var3 = 0; var3 < var4.length; ++var3) {
         if (var4[var3].getType() == EscherRecordType.SP_CONTAINER) {
            var2.add(var4[var3]);
         } else if (var4[var3].getType() == EscherRecordType.SPGR_CONTAINER) {
            this.getSpContainers((EscherContainer)var4[var3], var2);
         } else {
            logger.warn("Spgr Containers contains a record other than Sp/Spgr containers");
         }
      }

   }

   private void initialize() {
      byte var3 = 0;
      EscherRecordData var5 = new EscherRecordData(this, 0);
      Assert.verify(var5.isContainer());
      EscherContainer var8 = new EscherContainer(var5);
      var8.getChildren();
      EscherRecord[] var6 = var8.getChildren();
      var8 = null;

      int var1;
      for(var1 = 0; var1 < var6.length && var8 == null; ++var1) {
         EscherRecord var7 = var6[var1];
         if (var7.getType() == EscherRecordType.SPGR_CONTAINER) {
            var8 = (EscherContainer)var7;
         }
      }

      boolean var4;
      if (var8 != null) {
         var4 = true;
      } else {
         var4 = false;
      }

      Assert.verify(var4);
      var6 = var8.getChildren();
      boolean var2 = false;

      for(var1 = var3; var1 < var6.length && !var2; ++var1) {
         if (var6[var1].getType() == EscherRecordType.SPGR_CONTAINER) {
            var2 = true;
         }
      }

      if (!var2) {
         this.spContainers = var6;
      } else {
         ArrayList var10 = new ArrayList();
         this.getSpContainers(var8, var10);
         EscherRecord[] var9 = new EscherRecord[var10.size()];
         this.spContainers = var9;
         this.spContainers = (EscherRecord[])var10.toArray(var9);
      }

      this.initialized = true;
   }

   public void addData(byte[] var1) {
      this.addRawData(var1);
      ++this.numDrawings;
   }

   public void addRawData(byte[] var1) {
      byte[] var2 = this.drawingData;
      if (var2 == null) {
         this.drawingData = var1;
      } else {
         byte[] var3 = new byte[var2.length + var1.length];
         System.arraycopy(var2, 0, var3, 0, var2.length);
         System.arraycopy(var1, 0, var3, this.drawingData.length, var1.length);
         this.drawingData = var3;
         this.initialized = false;
      }
   }

   public byte[] getData() {
      return this.drawingData;
   }

   final int getNumDrawings() {
      return this.numDrawings;
   }

   EscherContainer getSpContainer(int var1) {
      if (!this.initialized) {
         this.initialize();
      }

      boolean var2 = true;
      ++var1;
      EscherRecord[] var3 = this.spContainers;
      if (var1 < var3.length) {
         EscherContainer var4 = (EscherContainer)var3[var1];
         if (var4 == null) {
            var2 = false;
         }

         Assert.verify(var2);
         return var4;
      } else {
         throw new DrawingDataException();
      }
   }
}
