package jxl.biff.drawing;

import jxl.WorkbookSettings;
import jxl.biff.ByteData;
import jxl.biff.IndexMapping;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.common.Assert;
import jxl.common.Logger;
import jxl.read.biff.File;

public class Chart implements ByteData, EscherStream {
   private static final Logger logger = Logger.getLogger(Chart.class);
   private byte[] data;
   private DrawingData drawingData;
   private int drawingNumber;
   private int endpos;
   private File file;
   private boolean initialized;
   private MsoDrawingRecord msoDrawingRecord;
   private ObjRecord objRecord;
   private int startpos;
   private WorkbookSettings workbookSettings;

   public Chart(MsoDrawingRecord var1, ObjRecord var2, DrawingData var3, int var4, int var5, File var6, WorkbookSettings var7) {
      this.msoDrawingRecord = var1;
      this.objRecord = var2;
      this.startpos = var4;
      this.endpos = var5;
      this.file = var6;
      this.workbookSettings = var7;
      boolean var9 = true;
      if (var1 != null) {
         this.drawingData = var3;
         var3.addData(var1.getRecord().getData());
         this.drawingNumber = this.drawingData.getNumDrawings() - 1;
      }

      boolean var8;
      label25: {
         this.initialized = false;
         if (var1 != null) {
            var8 = var9;
            if (var2 != null) {
               break label25;
            }
         }

         if (var1 == null && var2 == null) {
            var8 = var9;
         } else {
            var8 = false;
         }
      }

      Assert.verify(var8);
   }

   private void initialize() {
      File var2 = this.file;
      int var1 = this.startpos;
      this.data = var2.read(var1, this.endpos - var1);
      this.initialized = true;
   }

   public byte[] getBytes() {
      if (!this.initialized) {
         this.initialize();
      }

      return this.data;
   }

   public byte[] getData() {
      return this.msoDrawingRecord.getRecord().getData();
   }

   MsoDrawingRecord getMsoDrawingRecord() {
      return this.msoDrawingRecord;
   }

   ObjRecord getObjRecord() {
      return this.objRecord;
   }

   EscherContainer getSpContainer() {
      return this.drawingData.getSpContainer(this.drawingNumber);
   }

   public void rationalize(IndexMapping var1, IndexMapping var2, IndexMapping var3) {
      if (!this.initialized) {
         this.initialize();
      }

      int var4 = 0;

      while(true) {
         byte[] var10 = this.data;
         if (var4 >= var10.length) {
            return;
         }

         int var5 = IntegerHelper.getInt(var10[var4], var10[var4 + 1]);
         var10 = this.data;
         int var7 = IntegerHelper.getInt(var10[var4 + 2], var10[var4 + 3]);
         Type var11 = Type.getType(var5);
         if (var11 == Type.FONTX) {
            var10 = this.data;
            var5 = var4 + 4;
            IntegerHelper.getTwoBytes(var2.getNewIndex(IntegerHelper.getInt(var10[var5], var10[var4 + 5])), this.data, var5);
         } else if (var11 == Type.FBI) {
            var10 = this.data;
            var5 = var4 + 12;
            IntegerHelper.getTwoBytes(var2.getNewIndex(IntegerHelper.getInt(var10[var5], var10[var4 + 13])), this.data, var5);
         } else if (var11 == Type.IFMT) {
            var10 = this.data;
            var5 = var4 + 4;
            IntegerHelper.getTwoBytes(var3.getNewIndex(IntegerHelper.getInt(var10[var5], var10[var4 + 5])), this.data, var5);
         } else if (var11 == Type.ALRUNS) {
            var10 = this.data;
            int var8 = IntegerHelper.getInt(var10[var4 + 4], var10[var4 + 5]);
            int var6 = var4 + 6;

            for(var5 = 0; var5 < var8; ++var5) {
               var10 = this.data;
               int var9 = var6 + 2;
               IntegerHelper.getTwoBytes(var2.getNewIndex(IntegerHelper.getInt(var10[var9], var10[var6 + 3])), this.data, var9);
               var6 += 4;
            }
         }

         var4 += var7 + 4;
      }
   }
}
