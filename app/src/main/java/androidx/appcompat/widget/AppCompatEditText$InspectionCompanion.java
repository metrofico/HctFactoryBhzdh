package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;

public final class AppCompatEditText$InspectionCompanion implements InspectionCompanion {
   private int mBackgroundTintId;
   private int mBackgroundTintModeId;
   private boolean mPropertiesMapped = false;

   public void mapProperties(PropertyMapper var1) {
      this.mBackgroundTintId = var1.mapObject("backgroundTint", R.attr.backgroundTint);
      this.mBackgroundTintModeId = var1.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
      this.mPropertiesMapped = true;
   }

   public void readProperties(AppCompatEditText var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readObject(this.mBackgroundTintId, var1.getBackgroundTintList());
         var2.readObject(this.mBackgroundTintModeId, var1.getBackgroundTintMode());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
