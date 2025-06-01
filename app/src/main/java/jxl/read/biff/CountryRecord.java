package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

public class CountryRecord extends RecordData {
   private static Logger logger = Logger.getLogger(CountryRecord.class);
   private int language;
   private int regionalSettings;

   public CountryRecord(Record var1) {
      super(var1);
      byte[] var2 = var1.getData();
      this.language = IntegerHelper.getInt(var2[0], var2[1]);
      this.regionalSettings = IntegerHelper.getInt(var2[2], var2[3]);
   }

   public int getLanguageCode() {
      return this.language;
   }

   public int getRegionalSettingsCode() {
      return this.regionalSettings;
   }
}
