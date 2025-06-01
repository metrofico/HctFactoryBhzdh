package nfore.android.bt.pbap;

import java.io.Serializable;
import java.util.List;

public class VCardList implements Serializable {
   private List vcardPacks;

   public List getVcardPacks() {
      return this.vcardPacks;
   }

   public void setVcardPacks(List var1) {
      this.vcardPacks = var1;
   }
}
