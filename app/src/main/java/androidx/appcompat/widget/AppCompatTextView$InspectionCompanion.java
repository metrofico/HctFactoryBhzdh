package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;
import java.util.function.IntFunction;

public final class AppCompatTextView$InspectionCompanion implements InspectionCompanion {
   private int mAutoSizeMaxTextSizeId;
   private int mAutoSizeMinTextSizeId;
   private int mAutoSizeStepGranularityId;
   private int mAutoSizeTextTypeId;
   private int mBackgroundTintId;
   private int mBackgroundTintModeId;
   private int mDrawableTintId;
   private int mDrawableTintModeId;
   private boolean mPropertiesMapped = false;

   public void mapProperties(PropertyMapper var1) {
      this.mAutoSizeMaxTextSizeId = var1.mapInt("autoSizeMaxTextSize", R.attr.autoSizeMaxTextSize);
      this.mAutoSizeMinTextSizeId = var1.mapInt("autoSizeMinTextSize", R.attr.autoSizeMinTextSize);
      this.mAutoSizeStepGranularityId = var1.mapInt("autoSizeStepGranularity", R.attr.autoSizeStepGranularity);
      this.mAutoSizeTextTypeId = var1.mapIntEnum("autoSizeTextType", R.attr.autoSizeTextType, new IntFunction(this) {
         final AppCompatTextView$InspectionCompanion this$0;

         {
            this.this$0 = var1;
         }

         public String apply(int var1) {
            if (var1 != 0) {
               return var1 != 1 ? String.valueOf(var1) : "uniform";
            } else {
               return "none";
            }
         }
      });
      this.mBackgroundTintId = var1.mapObject("backgroundTint", R.attr.backgroundTint);
      this.mBackgroundTintModeId = var1.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
      this.mDrawableTintId = var1.mapObject("drawableTint", R.attr.drawableTint);
      this.mDrawableTintModeId = var1.mapObject("drawableTintMode", R.attr.drawableTintMode);
      this.mPropertiesMapped = true;
   }

   public void readProperties(AppCompatTextView var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readInt(this.mAutoSizeMaxTextSizeId, var1.getAutoSizeMaxTextSize());
         var2.readInt(this.mAutoSizeMinTextSizeId, var1.getAutoSizeMinTextSize());
         var2.readInt(this.mAutoSizeStepGranularityId, var1.getAutoSizeStepGranularity());
         var2.readIntEnum(this.mAutoSizeTextTypeId, var1.getAutoSizeTextType());
         var2.readObject(this.mBackgroundTintId, var1.getBackgroundTintList());
         var2.readObject(this.mBackgroundTintModeId, var1.getBackgroundTintMode());
         var2.readObject(this.mDrawableTintId, var1.getCompoundDrawableTintList());
         var2.readObject(this.mDrawableTintModeId, var1.getCompoundDrawableTintMode());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
