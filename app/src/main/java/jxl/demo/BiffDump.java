package jxl.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import jxl.WorkbookSettings;
import jxl.biff.Type;
import jxl.read.biff.BiffException;
import jxl.read.biff.BiffRecordReader;
import jxl.read.biff.Record;

class BiffDump {
   private static final int bytesPerLine = 16;
   private int bofs;
   private int fontIndex;
   private BiffRecordReader reader;
   private HashMap recordNames;
   private BufferedWriter writer;
   private int xfIndex;

   public BiffDump(File var1, OutputStream var2) throws IOException, BiffException {
      this.writer = new BufferedWriter(new OutputStreamWriter(var2));
      FileInputStream var3 = new FileInputStream(var1);
      this.reader = new BiffRecordReader(new jxl.read.biff.File(var3, new WorkbookSettings()));
      this.buildNameHash();
      this.dump();
      this.writer.flush();
      this.writer.close();
      var3.close();
   }

   private void buildNameHash() {
      HashMap var1 = new HashMap(50);
      this.recordNames = var1;
      var1.put(Type.BOF, "BOF");
      this.recordNames.put(Type.EOF, "EOF");
      this.recordNames.put(Type.FONT, "FONT");
      this.recordNames.put(Type.SST, "SST");
      this.recordNames.put(Type.LABELSST, "LABELSST");
      this.recordNames.put(Type.WRITEACCESS, "WRITEACCESS");
      this.recordNames.put(Type.FORMULA, "FORMULA");
      this.recordNames.put(Type.FORMULA2, "FORMULA");
      this.recordNames.put(Type.XF, "XF");
      this.recordNames.put(Type.MULRK, "MULRK");
      this.recordNames.put(Type.NUMBER, "NUMBER");
      this.recordNames.put(Type.BOUNDSHEET, "BOUNDSHEET");
      this.recordNames.put(Type.CONTINUE, "CONTINUE");
      this.recordNames.put(Type.FORMAT, "FORMAT");
      this.recordNames.put(Type.EXTERNSHEET, "EXTERNSHEET");
      this.recordNames.put(Type.INDEX, "INDEX");
      this.recordNames.put(Type.DIMENSION, "DIMENSION");
      this.recordNames.put(Type.ROW, "ROW");
      this.recordNames.put(Type.DBCELL, "DBCELL");
      this.recordNames.put(Type.BLANK, "BLANK");
      this.recordNames.put(Type.MULBLANK, "MULBLANK");
      this.recordNames.put(Type.RK, "RK");
      this.recordNames.put(Type.RK2, "RK");
      this.recordNames.put(Type.COLINFO, "COLINFO");
      this.recordNames.put(Type.LABEL, "LABEL");
      this.recordNames.put(Type.SHAREDFORMULA, "SHAREDFORMULA");
      this.recordNames.put(Type.CODEPAGE, "CODEPAGE");
      this.recordNames.put(Type.WINDOW1, "WINDOW1");
      this.recordNames.put(Type.WINDOW2, "WINDOW2");
      this.recordNames.put(Type.MERGEDCELLS, "MERGEDCELLS");
      this.recordNames.put(Type.HLINK, "HLINK");
      this.recordNames.put(Type.HEADER, "HEADER");
      this.recordNames.put(Type.FOOTER, "FOOTER");
      this.recordNames.put(Type.INTERFACEHDR, "INTERFACEHDR");
      this.recordNames.put(Type.MMS, "MMS");
      this.recordNames.put(Type.INTERFACEEND, "INTERFACEEND");
      this.recordNames.put(Type.DSF, "DSF");
      this.recordNames.put(Type.FNGROUPCOUNT, "FNGROUPCOUNT");
      this.recordNames.put(Type.COUNTRY, "COUNTRY");
      this.recordNames.put(Type.TABID, "TABID");
      this.recordNames.put(Type.PROTECT, "PROTECT");
      this.recordNames.put(Type.SCENPROTECT, "SCENPROTECT");
      this.recordNames.put(Type.OBJPROTECT, "OBJPROTECT");
      this.recordNames.put(Type.WINDOWPROTECT, "WINDOWPROTECT");
      this.recordNames.put(Type.PASSWORD, "PASSWORD");
      this.recordNames.put(Type.PROT4REV, "PROT4REV");
      this.recordNames.put(Type.PROT4REVPASS, "PROT4REVPASS");
      this.recordNames.put(Type.BACKUP, "BACKUP");
      this.recordNames.put(Type.HIDEOBJ, "HIDEOBJ");
      this.recordNames.put(Type.NINETEENFOUR, "1904");
      this.recordNames.put(Type.PRECISION, "PRECISION");
      this.recordNames.put(Type.BOOKBOOL, "BOOKBOOL");
      this.recordNames.put(Type.STYLE, "STYLE");
      this.recordNames.put(Type.EXTSST, "EXTSST");
      this.recordNames.put(Type.REFRESHALL, "REFRESHALL");
      this.recordNames.put(Type.CALCMODE, "CALCMODE");
      this.recordNames.put(Type.CALCCOUNT, "CALCCOUNT");
      this.recordNames.put(Type.NAME, "NAME");
      this.recordNames.put(Type.MSODRAWINGGROUP, "MSODRAWINGGROUP");
      this.recordNames.put(Type.MSODRAWING, "MSODRAWING");
      this.recordNames.put(Type.OBJ, "OBJ");
      this.recordNames.put(Type.USESELFS, "USESELFS");
      this.recordNames.put(Type.SUPBOOK, "SUPBOOK");
      this.recordNames.put(Type.LEFTMARGIN, "LEFTMARGIN");
      this.recordNames.put(Type.RIGHTMARGIN, "RIGHTMARGIN");
      this.recordNames.put(Type.TOPMARGIN, "TOPMARGIN");
      this.recordNames.put(Type.BOTTOMMARGIN, "BOTTOMMARGIN");
      this.recordNames.put(Type.HCENTER, "HCENTER");
      this.recordNames.put(Type.VCENTER, "VCENTER");
      this.recordNames.put(Type.ITERATION, "ITERATION");
      this.recordNames.put(Type.DELTA, "DELTA");
      this.recordNames.put(Type.SAVERECALC, "SAVERECALC");
      this.recordNames.put(Type.PRINTHEADERS, "PRINTHEADERS");
      this.recordNames.put(Type.PRINTGRIDLINES, "PRINTGRIDLINES");
      this.recordNames.put(Type.SETUP, "SETUP");
      this.recordNames.put(Type.SELECTION, "SELECTION");
      this.recordNames.put(Type.STRING, "STRING");
      this.recordNames.put(Type.FONTX, "FONTX");
      this.recordNames.put(Type.IFMT, "IFMT");
      this.recordNames.put(Type.WSBOOL, "WSBOOL");
      this.recordNames.put(Type.GRIDSET, "GRIDSET");
      this.recordNames.put(Type.REFMODE, "REFMODE");
      this.recordNames.put(Type.GUTS, "GUTS");
      this.recordNames.put(Type.EXTERNNAME, "EXTERNNAME");
      this.recordNames.put(Type.FBI, "FBI");
      this.recordNames.put(Type.CRN, "CRN");
      this.recordNames.put(Type.HORIZONTALPAGEBREAKS, "HORIZONTALPAGEBREAKS");
      this.recordNames.put(Type.VERTICALPAGEBREAKS, "VERTICALPAGEBREAKS");
      this.recordNames.put(Type.DEFAULTROWHEIGHT, "DEFAULTROWHEIGHT");
      this.recordNames.put(Type.TEMPLATE, "TEMPLATE");
      this.recordNames.put(Type.PANE, "PANE");
      this.recordNames.put(Type.SCL, "SCL");
      this.recordNames.put(Type.PALETTE, "PALETTE");
      this.recordNames.put(Type.PLS, "PLS");
      this.recordNames.put(Type.OBJPROJ, "OBJPROJ");
      this.recordNames.put(Type.DEFCOLWIDTH, "DEFCOLWIDTH");
      this.recordNames.put(Type.ARRAY, "ARRAY");
      this.recordNames.put(Type.WEIRD1, "WEIRD1");
      this.recordNames.put(Type.BOOLERR, "BOOLERR");
      this.recordNames.put(Type.SORT, "SORT");
      this.recordNames.put(Type.BUTTONPROPERTYSET, "BUTTONPROPERTYSET");
      this.recordNames.put(Type.NOTE, "NOTE");
      this.recordNames.put(Type.TXO, "TXO");
      this.recordNames.put(Type.DV, "DV");
      this.recordNames.put(Type.DVAL, "DVAL");
      this.recordNames.put(Type.SERIES, "SERIES");
      this.recordNames.put(Type.SERIESLIST, "SERIESLIST");
      this.recordNames.put(Type.SBASEREF, "SBASEREF");
      this.recordNames.put(Type.CONDFMT, "CONDFMT");
      this.recordNames.put(Type.CF, "CF");
      this.recordNames.put(Type.FILTERMODE, "FILTERMODE");
      this.recordNames.put(Type.AUTOFILTER, "AUTOFILTER");
      this.recordNames.put(Type.AUTOFILTERINFO, "AUTOFILTERINFO");
      this.recordNames.put(Type.XCT, "XCT");
      this.recordNames.put(Type.UNKNOWN, "???");
   }

   private void dump() throws IOException {
      for(boolean var1 = true; this.reader.hasNext() && var1; var1 = this.writeRecord(this.reader.next())) {
      }

   }

   private void writeByte(byte var1, StringBuffer var2) {
      String var3 = Integer.toHexString(var1 & 255);
      if (var3.length() == 1) {
         var2.append('0');
      }

      var2.append(var3);
   }

   private boolean writeRecord(Record var1) throws IOException {
      int var10 = this.reader.getPos();
      int var9 = var1.getCode();
      boolean var13;
      if (this.bofs == 0 && var1.getType() != Type.BOF) {
         var13 = false;
      } else {
         var13 = true;
      }

      if (!var13) {
         return var13;
      } else {
         if (var1.getType() == Type.BOF) {
            ++this.bofs;
         }

         if (var1.getType() == Type.EOF) {
            --this.bofs;
         }

         StringBuffer var14 = new StringBuffer();
         this.writeSixDigitValue(var10, var14);
         var14.append(" [");
         var14.append(this.recordNames.get(var1.getType()));
         var14.append("]");
         var14.append("  (0x");
         var14.append(Integer.toHexString(var9));
         var14.append(")");
         if (var9 == Type.XF.value) {
            var14.append(" (0x");
            var14.append(Integer.toHexString(this.xfIndex));
            var14.append(")");
            ++this.xfIndex;
         }

         int var8;
         if (var9 == Type.FONT.value) {
            var8 = this.fontIndex;
            if (var8 == 4) {
               this.fontIndex = var8 + 1;
            }

            var14.append(" (0x");
            var14.append(Integer.toHexString(this.fontIndex));
            var14.append(")");
            ++this.fontIndex;
         }

         this.writer.write(var14.toString());
         this.writer.newLine();
         byte var5 = (byte)(var9 & 255);
         byte var2 = (byte)((var9 & '\uff00') >> 8);
         byte var4 = (byte)(var1.getLength() & 255);
         byte var3 = (byte)((var1.getLength() & '\uff00') >> 8);
         byte[] var16 = var1.getData();
         int var11 = var16.length + 4;
         byte[] var15 = new byte[var11];
         System.arraycopy(new byte[]{var5, var2, var4, var3}, 0, var15, 0, 4);
         System.arraycopy(var16, 0, var15, 4, var16.length);
         var8 = 0;

         while(var8 < var11) {
            var14 = new StringBuffer();
            this.writeSixDigitValue(var10 + var8, var14);
            var14.append("   ");
            int var12 = Math.min(16, var11 - var8);

            for(var9 = 0; var9 < var12; ++var9) {
               this.writeByte(var15[var9 + var8], var14);
               var14.append(' ');
            }

            if (var12 < 16) {
               for(var9 = 0; var9 < 16 - var12; ++var9) {
                  var14.append("   ");
               }
            }

            var14.append("  ");

            for(var9 = 0; var9 < var12; ++var9) {
               char var6;
               label71: {
                  char var7 = (char)var15[var9 + var8];
                  if (var7 >= ' ') {
                     var6 = var7;
                     if (var7 <= 'z') {
                        break label71;
                     }
                  }

                  var6 = '.';
               }

               var14.append(var6);
            }

            var8 += var12;
            this.writer.write(var14.toString());
            this.writer.newLine();
         }

         return var13;
      }
   }

   private void writeSixDigitValue(int var1, StringBuffer var2) {
      String var3 = Integer.toHexString(var1);

      for(var1 = 6; var1 > var3.length(); --var1) {
         var2.append('0');
      }

      var2.append(var3);
   }
}
