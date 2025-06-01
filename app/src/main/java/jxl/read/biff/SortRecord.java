package jxl.read.biff;

import jxl.biff.RecordData;
import jxl.biff.StringHelper;
import jxl.biff.Type;

public class SortRecord extends RecordData {
   private String col1Name;
   private int col1Size;
   private String col2Name;
   private int col2Size;
   private String col3Name;
   private int col3Size;
   private byte optionFlags;
   private boolean sortCaseSensitive;
   private boolean sortColumns;
   private boolean sortKey1Desc;
   private boolean sortKey2Desc;
   private boolean sortKey3Desc;

   public SortRecord(Record var1) {
      super(Type.SORT);
      boolean var7 = false;
      this.sortColumns = false;
      this.sortKey1Desc = false;
      this.sortKey2Desc = false;
      this.sortKey3Desc = false;
      this.sortCaseSensitive = false;
      byte[] var8 = var1.getData();
      byte var2 = var8[0];
      this.optionFlags = var2;
      boolean var6;
      if ((var2 & 1) != 0) {
         var6 = true;
      } else {
         var6 = false;
      }

      this.sortColumns = var6;
      if ((var2 & 2) != 0) {
         var6 = true;
      } else {
         var6 = false;
      }

      this.sortKey1Desc = var6;
      if ((var2 & 4) != 0) {
         var6 = true;
      } else {
         var6 = false;
      }

      this.sortKey2Desc = var6;
      if ((var2 & 8) != 0) {
         var6 = true;
      } else {
         var6 = false;
      }

      this.sortKey3Desc = var6;
      var6 = var7;
      if ((var2 & 16) != 0) {
         var6 = true;
      }

      this.sortCaseSensitive = var6;
      int var3 = var8[2];
      this.col1Size = var3;
      this.col2Size = var8[3];
      this.col3Size = var8[4];
      if (var8[5] == 0) {
         this.col1Name = new String(var8, 6, this.col1Size);
         var3 = this.col1Size;
      } else {
         this.col1Name = StringHelper.getUnicodeString(var8, var3, 6);
         var3 = this.col1Size * 2;
      }

      var3 += 6;
      int var5 = this.col2Size;
      int var4;
      if (var5 > 0) {
         var4 = var3 + 1;
         if (var8[var3] == 0) {
            this.col2Name = new String(var8, var4, this.col2Size);
            var3 = var4 + this.col2Size;
         } else {
            this.col2Name = StringHelper.getUnicodeString(var8, var5, var4);
            var3 = var4 + this.col2Size * 2;
         }
      } else {
         this.col2Name = "";
      }

      var5 = this.col3Size;
      if (var5 > 0) {
         var4 = var3 + 1;
         if (var8[var3] == 0) {
            this.col3Name = new String(var8, var4, this.col3Size);
         } else {
            this.col3Name = StringHelper.getUnicodeString(var8, var5, var4);
         }
      } else {
         this.col3Name = "";
      }

   }

   public boolean getSortCaseSensitive() {
      return this.sortCaseSensitive;
   }

   public String getSortCol1Name() {
      return this.col1Name;
   }

   public String getSortCol2Name() {
      return this.col2Name;
   }

   public String getSortCol3Name() {
      return this.col3Name;
   }

   public boolean getSortColumns() {
      return this.sortColumns;
   }

   public boolean getSortKey1Desc() {
      return this.sortKey1Desc;
   }

   public boolean getSortKey2Desc() {
      return this.sortKey2Desc;
   }

   public boolean getSortKey3Desc() {
      return this.sortKey3Desc;
   }
}
