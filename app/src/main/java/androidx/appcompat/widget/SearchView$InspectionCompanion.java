package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;

public final class SearchView$InspectionCompanion implements InspectionCompanion {
   private int mIconifiedByDefaultId;
   private int mImeOptionsId;
   private int mMaxWidthId;
   private boolean mPropertiesMapped = false;
   private int mQueryHintId;

   public void mapProperties(PropertyMapper var1) {
      this.mImeOptionsId = var1.mapInt("imeOptions", 16843364);
      this.mMaxWidthId = var1.mapInt("maxWidth", 16843039);
      this.mIconifiedByDefaultId = var1.mapBoolean("iconifiedByDefault", R.attr.iconifiedByDefault);
      this.mQueryHintId = var1.mapObject("queryHint", R.attr.queryHint);
      this.mPropertiesMapped = true;
   }

   public void readProperties(SearchView var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readInt(this.mImeOptionsId, var1.getImeOptions());
         var2.readInt(this.mMaxWidthId, var1.getMaxWidth());
         var2.readBoolean(this.mIconifiedByDefaultId, var1.isIconfiedByDefault());
         var2.readObject(this.mQueryHintId, var1.getQueryHint());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
