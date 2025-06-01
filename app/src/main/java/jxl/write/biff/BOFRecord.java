package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class BOFRecord extends WritableRecordData {
   public static final SheetBOF sheet = new SheetBOF();
   public static final WorkbookGlobalsBOF workbookGlobals = new WorkbookGlobalsBOF();
   private byte[] data;

   public BOFRecord(SheetBOF var1) {
      super(Type.BOF);
      this.data = new byte[]{0, 6, 16, 0, -14, 21, -52, 7, 0, 0, 0, 0, 6, 0, 0, 0};
   }

   public BOFRecord(WorkbookGlobalsBOF var1) {
      super(Type.BOF);
      this.data = new byte[]{0, 6, 5, 0, -14, 21, -52, 7, 0, 0, 0, 0, 6, 0, 0, 0};
   }

   public byte[] getData() {
      return this.data;
   }

   private static class SheetBOF {
      private SheetBOF() {
      }

      // $FF: synthetic method
      SheetBOF(Object var1) {
         this();
      }
   }

   private static class WorkbookGlobalsBOF {
      private WorkbookGlobalsBOF() {
      }

      // $FF: synthetic method
      WorkbookGlobalsBOF(Object var1) {
         this();
      }
   }
}
