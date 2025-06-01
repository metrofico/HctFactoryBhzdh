package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;

public final class AppCompatImageButton$InspectionCompanion implements InspectionCompanion {
   private int mBackgroundTintId;
   private int mBackgroundTintModeId;
   private boolean mPropertiesMapped = false;
   private int mTintId;
   private int mTintModeId;

   public void mapProperties(PropertyMapper var1) {
      this.mBackgroundTintId = var1.mapObject("backgroundTint", R.attr.backgroundTint);
      this.mBackgroundTintModeId = var1.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
      this.mTintId = var1.mapObject("tint", R.attr.tint);
      this.mTintModeId = var1.mapObject("tintMode", R.attr.tintMode);
      this.mPropertiesMapped = true;
   }

   public void readProperties(AppCompatImageButton var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readObject(this.mBackgroundTintId, var1.getBackgroundTintList());
         var2.readObject(this.mBackgroundTintModeId, var1.getBackgroundTintMode());
         var2.readObject(this.mTintId, var1.getImageTintList());
         var2.readObject(this.mTintModeId, var1.getImageTintMode());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
