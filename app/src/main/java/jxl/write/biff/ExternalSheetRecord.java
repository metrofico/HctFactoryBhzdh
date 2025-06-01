package jxl.write.biff;

import java.util.ArrayList;
import java.util.Iterator;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class ExternalSheetRecord extends WritableRecordData {
   private byte[] data;
   private ArrayList xtis;

   public ExternalSheetRecord() {
      super(Type.EXTERNSHEET);
      this.xtis = new ArrayList();
   }

   public ExternalSheetRecord(jxl.read.biff.ExternalSheetRecord var1) {
      super(Type.EXTERNSHEET);
      this.xtis = new ArrayList(var1.getNumRecords());

      for(int var2 = 0; var2 < var1.getNumRecords(); ++var2) {
         XTI var3 = new XTI(var1.getSupbookIndex(var2), var1.getFirstTabIndex(var2), var1.getLastTabIndex(var2));
         this.xtis.add(var3);
      }

   }

   public byte[] getData() {
      int var2 = this.xtis.size();
      int var1 = 2;
      byte[] var3 = new byte[var2 * 6 + 2];
      IntegerHelper.getTwoBytes(this.xtis.size(), var3, 0);

      for(Iterator var5 = this.xtis.iterator(); var5.hasNext(); var1 += 6) {
         XTI var4 = (XTI)var5.next();
         IntegerHelper.getTwoBytes(var4.supbookIndex, var3, var1);
         IntegerHelper.getTwoBytes(var4.firstTab, var3, var1 + 2);
         IntegerHelper.getTwoBytes(var4.lastTab, var3, var1 + 4);
      }

      return var3;
   }

   public int getFirstTabIndex(int var1) {
      return ((XTI)this.xtis.get(var1)).firstTab;
   }

   int getIndex(int var1, int var2) {
      Iterator var6 = this.xtis.iterator();
      boolean var4 = false;
      int var3 = 0;

      XTI var5;
      while(var6.hasNext() && !var4) {
         var5 = (XTI)var6.next();
         if (var5.supbookIndex == var1 && var5.firstTab == var2) {
            var4 = true;
         } else {
            ++var3;
         }
      }

      if (!var4) {
         var5 = new XTI(var1, var2, var2);
         this.xtis.add(var5);
         var3 = this.xtis.size() - 1;
      }

      return var3;
   }

   public int getLastTabIndex(int var1) {
      return ((XTI)this.xtis.get(var1)).lastTab;
   }

   public int getSupbookIndex(int var1) {
      return ((XTI)this.xtis.get(var1)).supbookIndex;
   }

   void sheetInserted(int var1) {
      Iterator var2 = this.xtis.iterator();

      while(var2.hasNext()) {
         ((XTI)var2.next()).sheetInserted(var1);
      }

   }

   void sheetRemoved(int var1) {
      Iterator var2 = this.xtis.iterator();

      while(var2.hasNext()) {
         ((XTI)var2.next()).sheetRemoved(var1);
      }

   }

   private static class XTI {
      int firstTab;
      int lastTab;
      int supbookIndex;

      XTI(int var1, int var2, int var3) {
         this.supbookIndex = var1;
         this.firstTab = var2;
         this.lastTab = var3;
      }

      void sheetInserted(int var1) {
         int var2 = this.firstTab;
         if (var2 >= var1) {
            this.firstTab = var2 + 1;
         }

         var2 = this.lastTab;
         if (var2 >= var1) {
            this.lastTab = var2 + 1;
         }

      }

      void sheetRemoved(int var1) {
         if (this.firstTab == var1) {
            this.firstTab = 0;
         }

         if (this.lastTab == var1) {
            this.lastTab = 0;
         }

         int var2 = this.firstTab;
         if (var2 > var1) {
            this.firstTab = var2 - 1;
         }

         var2 = this.lastTab;
         if (var2 > var1) {
            this.lastTab = var2 - 1;
         }

      }
   }
}
