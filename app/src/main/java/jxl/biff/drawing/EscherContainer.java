package jxl.biff.drawing;

import java.util.ArrayList;
import java.util.Iterator;
import jxl.common.Logger;

class EscherContainer extends EscherRecord {
   private static Logger logger = Logger.getLogger(EscherContainer.class);
   private ArrayList children;
   private boolean initialized;

   public EscherContainer(EscherRecordData var1) {
      super(var1);
      this.initialized = false;
      this.children = new ArrayList();
   }

   protected EscherContainer(EscherRecordType var1) {
      super(var1);
      this.setContainer(true);
      this.children = new ArrayList();
   }

   private void initialize() {
      int var1 = this.getPos() + 8;

      Object var5;
      for(int var2 = Math.min(this.getPos() + this.getLength(), this.getStreamLength()); var1 < var2; var1 += ((EscherRecord)var5).getLength()) {
         EscherRecordData var3 = new EscherRecordData(this.getEscherStream(), var1);
         EscherRecordType var4 = var3.getType();
         if (var4 == EscherRecordType.DGG) {
            var5 = new Dgg(var3);
         } else if (var4 == EscherRecordType.DG) {
            var5 = new Dg(var3);
         } else if (var4 == EscherRecordType.BSTORE_CONTAINER) {
            var5 = new BStoreContainer(var3);
         } else if (var4 == EscherRecordType.SPGR_CONTAINER) {
            var5 = new SpgrContainer(var3);
         } else if (var4 == EscherRecordType.SP_CONTAINER) {
            var5 = new SpContainer(var3);
         } else if (var4 == EscherRecordType.SPGR) {
            var5 = new Spgr(var3);
         } else if (var4 == EscherRecordType.SP) {
            var5 = new Sp(var3);
         } else if (var4 == EscherRecordType.CLIENT_ANCHOR) {
            var5 = new ClientAnchor(var3);
         } else if (var4 == EscherRecordType.CLIENT_DATA) {
            var5 = new ClientData(var3);
         } else if (var4 == EscherRecordType.BSE) {
            var5 = new BlipStoreEntry(var3);
         } else if (var4 == EscherRecordType.OPT) {
            var5 = new Opt(var3);
         } else if (var4 == EscherRecordType.SPLIT_MENU_COLORS) {
            var5 = new SplitMenuColors(var3);
         } else if (var4 == EscherRecordType.CLIENT_TEXT_BOX) {
            var5 = new ClientTextBox(var3);
         } else {
            var5 = new EscherAtom(var3);
         }

         this.children.add(var5);
      }

      this.initialized = true;
   }

   public void add(EscherRecord var1) {
      this.children.add(var1);
   }

   public EscherRecord[] getChildren() {
      if (!this.initialized) {
         this.initialize();
      }

      ArrayList var1 = this.children;
      return (EscherRecord[])var1.toArray(new EscherRecord[var1.size()]);
   }

   byte[] getData() {
      if (!this.initialized) {
         this.initialize();
      }

      byte[] var1 = new byte[0];
      Iterator var3 = this.children.iterator();

      while(var3.hasNext()) {
         byte[] var4 = ((EscherRecord)var3.next()).getData();
         if (var4 != null) {
            byte[] var2 = new byte[var1.length + var4.length];
            System.arraycopy(var1, 0, var2, 0, var1.length);
            System.arraycopy(var4, 0, var2, var1.length, var4.length);
            var1 = var2;
         }
      }

      return this.setHeaderData(var1);
   }

   public void remove(EscherRecord var1) {
      this.children.remove(var1);
   }
}
