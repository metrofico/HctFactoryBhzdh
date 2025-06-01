package jxl.biff;

import jxl.common.Logger;

public final class IndexMapping {
   private static Logger logger = Logger.getLogger(IndexMapping.class);
   private int[] newIndices;

   public IndexMapping(int var1) {
      this.newIndices = new int[var1];
   }

   public int getNewIndex(int var1) {
      return this.newIndices[var1];
   }

   public void setMapping(int var1, int var2) {
      this.newIndices[var1] = var2;
   }
}
