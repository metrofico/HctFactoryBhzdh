package jxl.biff.drawing;

import jxl.common.Logger;

class BStoreContainer extends EscherContainer {
   private static Logger logger = Logger.getLogger(BStoreContainer.class);
   private int numBlips;

   public BStoreContainer() {
      super(EscherRecordType.BSTORE_CONTAINER);
   }

   public BStoreContainer(EscherRecordData var1) {
      super(var1);
      this.numBlips = this.getInstance();
   }

   public BlipStoreEntry getDrawing(int var1) {
      return (BlipStoreEntry)this.getChildren()[var1];
   }

   public int getNumBlips() {
      return this.numBlips;
   }

   void setNumBlips(int var1) {
      this.numBlips = var1;
      this.setInstance(var1);
   }
}
