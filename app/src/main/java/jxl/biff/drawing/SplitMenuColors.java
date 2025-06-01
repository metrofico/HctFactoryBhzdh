package jxl.biff.drawing;

class SplitMenuColors extends EscherAtom {
   private byte[] data;

   public SplitMenuColors() {
      super(EscherRecordType.SPLIT_MENU_COLORS);
      this.setVersion(0);
      this.setInstance(4);
      this.data = new byte[]{13, 0, 0, 8, 12, 0, 0, 8, 23, 0, 0, 8, -9, 0, 0, 16};
   }

   public SplitMenuColors(EscherRecordData var1) {
      super(var1);
   }

   byte[] getData() {
      return this.setHeaderData(this.data);
   }
}
