package jxl.biff.drawing;

class Spgr extends EscherAtom {
   private byte[] data;

   public Spgr() {
      super(EscherRecordType.SPGR);
      this.setVersion(1);
      this.data = new byte[16];
   }

   public Spgr(EscherRecordData var1) {
      super(var1);
   }

   byte[] getData() {
      return this.setHeaderData(this.data);
   }
}
