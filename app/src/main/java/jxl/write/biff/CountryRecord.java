package jxl.write.biff;

import jxl.biff.CountryCode;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class CountryRecord extends WritableRecordData {
   private int language;
   private int regionalSettings;

   public CountryRecord(CountryCode var1, CountryCode var2) {
      super(Type.COUNTRY);
      this.language = var1.getValue();
      this.regionalSettings = var2.getValue();
   }

   public CountryRecord(jxl.read.biff.CountryRecord var1) {
      super(Type.COUNTRY);
      this.language = var1.getLanguageCode();
      this.regionalSettings = var1.getRegionalSettingsCode();
   }

   public byte[] getData() {
      byte[] var1 = new byte[4];
      IntegerHelper.getTwoBytes(this.language, var1, 0);
      IntegerHelper.getTwoBytes(this.regionalSettings, var1, 2);
      return var1;
   }
}
