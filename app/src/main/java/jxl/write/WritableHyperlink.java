package jxl.write;

import java.io.File;
import java.net.URL;
import jxl.Hyperlink;
import jxl.write.biff.HyperlinkRecord;

public class WritableHyperlink extends HyperlinkRecord implements Hyperlink {
   public WritableHyperlink(int var1, int var2, int var3, int var4, File var5) {
      super(var1, var2, var3, var4, (File)var5, (String)null);
   }

   public WritableHyperlink(int var1, int var2, int var3, int var4, File var5, String var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public WritableHyperlink(int var1, int var2, int var3, int var4, String var5, WritableSheet var6, int var7, int var8, int var9, int var10) {
      super(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
   }

   public WritableHyperlink(int var1, int var2, int var3, int var4, URL var5) {
      this(var1, var2, var3, var4, (URL)var5, (String)null);
   }

   public WritableHyperlink(int var1, int var2, int var3, int var4, URL var5, String var6) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public WritableHyperlink(int var1, int var2, File var3) {
      this(var1, var2, var1, var2, (File)var3, (String)null);
   }

   public WritableHyperlink(int var1, int var2, File var3, String var4) {
      this(var1, var2, var1, var2, var3, var4);
   }

   public WritableHyperlink(int var1, int var2, String var3, WritableSheet var4, int var5, int var6) {
      this(var1, var2, var1, var2, var3, var4, var5, var6, var5, var6);
   }

   public WritableHyperlink(int var1, int var2, URL var3) {
      this(var1, var2, var1, var2, var3);
   }

   public WritableHyperlink(Hyperlink var1, WritableSheet var2) {
      super(var1, var2);
   }

   public void setDescription(String var1) {
      super.setContents(var1);
   }

   public void setFile(File var1) {
      super.setFile(var1);
   }

   public void setLocation(String var1, WritableSheet var2, int var3, int var4, int var5, int var6) {
      super.setLocation(var1, var2, var3, var4, var5, var6);
   }

   public void setURL(URL var1) {
      super.setURL(var1);
   }
}
