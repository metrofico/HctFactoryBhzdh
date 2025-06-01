package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;

public final class AppCompatCheckBox$InspectionCompanion implements InspectionCompanion {
   private int mBackgroundTintId;
   private int mBackgroundTintModeId;
   private int mButtonTintId;
   private int mButtonTintModeId;
   private boolean mPropertiesMapped = false;

   public void mapProperties(PropertyMapper var1) {
      this.mBackgroundTintId = var1.mapObject("backgroundTint", R.attr.backgroundTint);
      this.mBackgroundTintModeId = var1.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
      this.mButtonTintId = var1.mapObject("buttonTint", R.attr.buttonTint);
      this.mButtonTintModeId = var1.mapObject("buttonTintMode", R.attr.buttonTintMode);
      this.mPropertiesMapped = true;
   }

   public void readProperties(AppCompatCheckBox var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readObject(this.mBackgroundTintId, var1.getBackgroundTintList());
         var2.readObject(this.mBackgroundTintModeId, var1.getBackgroundTintMode());
         var2.readObject(this.mButtonTintId, var1.getButtonTintList());
         var2.readObject(this.mButtonTintModeId, var1.getButtonTintMode());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
