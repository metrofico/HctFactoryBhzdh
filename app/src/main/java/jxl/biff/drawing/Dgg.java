package jxl.biff.drawing;

import java.util.ArrayList;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class Dgg extends EscherAtom {
   private static Logger logger = Logger.getLogger(Dgg.class);
   private ArrayList clusters;
   private byte[] data;
   private int drawingsSaved;
   private int maxShapeId;
   private int numClusters;
   private int shapesSaved;

   public Dgg(int var1, int var2) {
      super(EscherRecordType.DGG);
      this.shapesSaved = var1;
      this.drawingsSaved = var2;
      this.clusters = new ArrayList();
   }

   public Dgg(EscherRecordData var1) {
      super(var1);
      this.clusters = new ArrayList();
      byte[] var4 = this.getBytes();
      int var2 = 0;
      this.maxShapeId = IntegerHelper.getInt(var4[0], var4[1], var4[2], var4[3]);
      this.numClusters = IntegerHelper.getInt(var4[4], var4[5], var4[6], var4[7]);
      this.shapesSaved = IntegerHelper.getInt(var4[8], var4[9], var4[10], var4[11]);
      this.drawingsSaved = IntegerHelper.getInt(var4[12], var4[13], var4[14], var4[15]);

      for(int var3 = 16; var2 < this.numClusters; ++var2) {
         Cluster var5 = new Cluster(IntegerHelper.getInt(var4[var3], var4[var3 + 1]), IntegerHelper.getInt(var4[var3 + 2], var4[var3 + 3]));
         this.clusters.add(var5);
         var3 += 4;
      }

   }

   void addCluster(int var1, int var2) {
      Cluster var3 = new Cluster(var1, var2);
      this.clusters.add(var3);
   }

   Cluster getCluster(int var1) {
      return (Cluster)this.clusters.get(var1);
   }

   byte[] getData() {
      int var1 = this.clusters.size();
      this.numClusters = var1;
      int var2 = 16;
      byte[] var4 = new byte[var1 * 4 + 16];
      this.data = var4;
      int var3 = this.shapesSaved;
      var1 = 0;
      IntegerHelper.getFourBytes(var3 + 1024, var4, 0);
      IntegerHelper.getFourBytes(this.numClusters, this.data, 4);
      IntegerHelper.getFourBytes(this.shapesSaved, this.data, 8);
      IntegerHelper.getFourBytes(1, this.data, 12);

      while(var1 < this.numClusters) {
         Cluster var5 = (Cluster)this.clusters.get(var1);
         IntegerHelper.getTwoBytes(var5.drawingGroupId, this.data, var2);
         IntegerHelper.getTwoBytes(var5.shapeIdsUsed, this.data, var2 + 2);
         var2 += 4;
         ++var1;
      }

      return this.setHeaderData(this.data);
   }

   int getDrawingsSaved() {
      return this.drawingsSaved;
   }

   int getShapesSaved() {
      return this.shapesSaved;
   }

   static final class Cluster {
      int drawingGroupId;
      int shapeIdsUsed;

      Cluster(int var1, int var2) {
         this.drawingGroupId = var1;
         this.shapeIdsUsed = var2;
      }
   }
}
