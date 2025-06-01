package jxl.biff;

import java.io.IOException;
import jxl.write.biff.File;

public class AutoFilter {
   private AutoFilterRecord autoFilter;
   private AutoFilterInfoRecord autoFilterInfo;
   private FilterModeRecord filterMode;

   public AutoFilter(FilterModeRecord var1, AutoFilterInfoRecord var2) {
      this.filterMode = var1;
      this.autoFilterInfo = var2;
   }

   public void add(AutoFilterRecord var1) {
      this.autoFilter = var1;
   }

   public void write(File var1) throws IOException {
      FilterModeRecord var2 = this.filterMode;
      if (var2 != null) {
         var1.write(var2);
      }

      AutoFilterInfoRecord var3 = this.autoFilterInfo;
      if (var3 != null) {
         var1.write(var3);
      }

      AutoFilterRecord var4 = this.autoFilter;
      if (var4 != null) {
         var1.write(var4);
      }

   }
}
