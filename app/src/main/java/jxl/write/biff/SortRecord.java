package jxl.write.biff;

import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class SortRecord extends WritableRecordData {
   private String column1Name;
   private String column2Name;
   private String column3Name;
   private boolean sortCaseSensitive;
   private boolean sortColumns;
   private boolean sortKey1Desc;
   private boolean sortKey2Desc;
   private boolean sortKey3Desc;

   public SortRecord(String var1, String var2, String var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8) {
      super(Type.SORT);
      this.column1Name = var1;
      this.column2Name = var2;
      this.column3Name = var3;
      this.sortColumns = var4;
      this.sortKey1Desc = var5;
      this.sortKey2Desc = var6;
      this.sortKey3Desc = var7;
      this.sortCaseSensitive = var8;
   }

   public byte[] getData() {
      int var2 = this.column1Name.length() * 2 + 5 + 1;
      int var1 = var2;
      if (this.column2Name.length() > 0) {
         var1 = var2 + this.column2Name.length() * 2 + 1;
      }

      var2 = var1;
      if (this.column3Name.length() > 0) {
         var2 = var1 + this.column3Name.length() * 2 + 1;
      }

      byte[] var3 = new byte[var2 + 1];
      byte var4 = this.sortColumns;
      var1 = var4;
      if (this.sortKey1Desc) {
         var1 = var4 | 2;
      }

      var2 = var1;
      if (this.sortKey2Desc) {
         var2 = var1 | 4;
      }

      var1 = var2;
      if (this.sortKey3Desc) {
         var1 = var2 | 8;
      }

      var2 = var1;
      if (this.sortCaseSensitive) {
         var2 = var1 | 16;
      }

      var3[0] = (byte)var2;
      var3[2] = (byte)this.column1Name.length();
      var3[3] = (byte)this.column2Name.length();
      var3[4] = (byte)this.column3Name.length();
      var3[5] = 1;
      StringHelper.getUnicodeBytes(this.column1Name, var3, 6);
      var2 = this.column1Name.length() * 2 + 6;
      var1 = var2;
      if (this.column2Name.length() > 0) {
         var1 = var2 + 1;
         var3[var2] = 1;
         StringHelper.getUnicodeBytes(this.column2Name, var3, var1);
         var1 += this.column2Name.length() * 2;
      }

      if (this.column3Name.length() > 0) {
         var3[var1] = 1;
         StringHelper.getUnicodeBytes(this.column3Name, var3, var1 + 1);
         this.column3Name.length();
      }

      return var3;
   }
}
