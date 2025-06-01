package jxl.biff.drawing;

import java.io.BufferedWriter;
import java.io.IOException;

public class EscherDisplay {
   private EscherStream stream;
   private BufferedWriter writer;

   public EscherDisplay(EscherStream var1, BufferedWriter var2) {
      this.stream = var1;
      this.writer = var2;
   }

   private void displayContainer(EscherContainer var1, int var2) throws IOException {
      this.displayRecord(var1, var2);
      int var3 = var2 + 1;
      EscherRecord[] var5 = var1.getChildren();

      for(var2 = 0; var2 < var5.length; ++var2) {
         EscherRecord var4 = var5[var2];
         if (var4.getEscherData().isContainer()) {
            this.displayContainer((EscherContainer)var4, var3);
         } else {
            this.displayRecord(var4, var3);
         }
      }

   }

   private void displayRecord(EscherRecord var1, int var2) throws IOException {
      this.indent(var2);
      EscherRecordType var3 = var1.getType();
      this.writer.write(Integer.toString(var3.getValue(), 16));
      this.writer.write(" - ");
      if (var3 == EscherRecordType.DGG_CONTAINER) {
         this.writer.write("Dgg Container");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.BSTORE_CONTAINER) {
         this.writer.write("BStore Container");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.DG_CONTAINER) {
         this.writer.write("Dg Container");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.SPGR_CONTAINER) {
         this.writer.write("Spgr Container");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.SP_CONTAINER) {
         this.writer.write("Sp Container");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.DGG) {
         this.writer.write("Dgg");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.BSE) {
         this.writer.write("Bse");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.DG) {
         Dg var4 = new Dg(var1.getEscherData());
         this.writer.write("Dg:  drawing id " + var4.getDrawingId() + " shape count " + var4.getShapeCount());
         this.writer.newLine();
      } else if (var3 == EscherRecordType.SPGR) {
         this.writer.write("Spgr");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.SP) {
         Sp var5 = new Sp(var1.getEscherData());
         this.writer.write("Sp:  shape id " + var5.getShapeId() + " shape type " + var5.getShapeType());
         this.writer.newLine();
      } else if (var3 == EscherRecordType.OPT) {
         Opt var7 = new Opt(var1.getEscherData());
         Opt.Property var6 = var7.getProperty(260);
         Opt.Property var8 = var7.getProperty(261);
         this.writer.write("Opt (value, stringValue): ");
         if (var6 != null) {
            this.writer.write("260: " + var6.value + ", " + var6.stringValue + ";");
         }

         if (var8 != null) {
            this.writer.write("261: " + var8.value + ", " + var8.stringValue + ";");
         }

         this.writer.newLine();
      } else if (var3 == EscherRecordType.CLIENT_ANCHOR) {
         this.writer.write("Client Anchor");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.CLIENT_DATA) {
         this.writer.write("Client Data");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.CLIENT_TEXT_BOX) {
         this.writer.write("Client Text Box");
         this.writer.newLine();
      } else if (var3 == EscherRecordType.SPLIT_MENU_COLORS) {
         this.writer.write("Split Menu Colors");
         this.writer.newLine();
      } else {
         this.writer.write("???");
         this.writer.newLine();
      }

   }

   private void indent(int var1) throws IOException {
      for(int var2 = 0; var2 < var1 * 2; ++var2) {
         this.writer.write(32);
      }

   }

   public void display() throws IOException {
      this.displayContainer(new EscherContainer(new EscherRecordData(this.stream, 0)), 0);
   }
}
