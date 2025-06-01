package jxl.biff.drawing;

import jxl.common.Logger;

class EscherAtom extends EscherRecord {
   private static Logger logger = Logger.getLogger(EscherAtom.class);

   public EscherAtom(EscherRecordData var1) {
      super(var1);
   }

   protected EscherAtom(EscherRecordType var1) {
      super(var1);
   }

   byte[] getData() {
      logger.warn("escher atom getData called on object of type " + this.getClass().getName() + " code " + Integer.toString(this.getType().getValue(), 16));
      return null;
   }
}
