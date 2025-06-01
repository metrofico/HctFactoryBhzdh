package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;

public final class AppCompatCheckedTextView$InspectionCompanion implements InspectionCompanion {
   private int mBackgroundTintId;
   private int mBackgroundTintModeId;
   private int mCheckMarkTintId;
   private int mCheckMarkTintModeId;
   private boolean mPropertiesMapped = false;

   public void mapProperties(PropertyMapper var1) {
      this.mBackgroundTintId = var1.mapObject("backgroundTint", R.attr.backgroundTint);
      this.mBackgroundTintModeId = var1.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
      this.mCheckMarkTintId = var1.mapObject("checkMarkTint", R.attr.checkMarkTint);
      this.mCheckMarkTintModeId = var1.mapObject("checkMarkTintMode", R.attr.checkMarkTintMode);
      this.mPropertiesMapped = true;
   }

   public void readProperties(AppCompatCheckedTextView var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readObject(this.mBackgroundTintId, var1.getBackgroundTintList());
         var2.readObject(this.mBackgroundTintModeId, var1.getBackgroundTintMode());
         var2.readObject(this.mCheckMarkTintId, var1.getCheckMarkTintList());
         var2.readObject(this.mCheckMarkTintModeId, var1.getCheckMarkTintMode());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
