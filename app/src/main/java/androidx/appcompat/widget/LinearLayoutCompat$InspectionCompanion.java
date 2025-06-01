package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

public final class LinearLayoutCompat$InspectionCompanion implements InspectionCompanion {
   private int mBaselineAlignedChildIndexId;
   private int mBaselineAlignedId;
   private int mDividerId;
   private int mDividerPaddingId;
   private int mGravityId;
   private int mMeasureWithLargestChildId;
   private int mOrientationId;
   private boolean mPropertiesMapped = false;
   private int mShowDividersId;
   private int mWeightSumId;

   public void mapProperties(PropertyMapper var1) {
      this.mBaselineAlignedId = var1.mapBoolean("baselineAligned", 16843046);
      this.mBaselineAlignedChildIndexId = var1.mapInt("baselineAlignedChildIndex", 16843047);
      this.mGravityId = var1.mapGravity("gravity", 16842927);
      this.mOrientationId = var1.mapIntEnum("orientation", 16842948, new IntFunction(this) {
         final LinearLayoutCompat$InspectionCompanion this$0;

         {
            this.this$0 = var1;
         }

         public String apply(int var1) {
            if (var1 != 0) {
               return var1 != 1 ? String.valueOf(var1) : "vertical";
            } else {
               return "horizontal";
            }
         }
      });
      this.mWeightSumId = var1.mapFloat("weightSum", 16843048);
      this.mDividerId = var1.mapObject("divider", R.attr.divider);
      this.mDividerPaddingId = var1.mapInt("dividerPadding", R.attr.dividerPadding);
      this.mMeasureWithLargestChildId = var1.mapBoolean("measureWithLargestChild", R.attr.measureWithLargestChild);
      this.mShowDividersId = var1.mapIntFlag("showDividers", R.attr.showDividers, new IntFunction(this) {
         final LinearLayoutCompat$InspectionCompanion this$0;

         {
            this.this$0 = var1;
         }

         public Set apply(int var1) {
            HashSet var2 = new HashSet();
            if (var1 == 0) {
               var2.add("none");
            }

            if (var1 == 1) {
               var2.add("beginning");
            }

            if (var1 == 2) {
               var2.add("middle");
            }

            if (var1 == 4) {
               var2.add("end");
            }

            return var2;
         }
      });
      this.mPropertiesMapped = true;
   }

   public void readProperties(LinearLayoutCompat var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readBoolean(this.mBaselineAlignedId, var1.isBaselineAligned());
         var2.readInt(this.mBaselineAlignedChildIndexId, var1.getBaselineAlignedChildIndex());
         var2.readGravity(this.mGravityId, var1.getGravity());
         var2.readIntEnum(this.mOrientationId, var1.getOrientation());
         var2.readFloat(this.mWeightSumId, var1.getWeightSum());
         var2.readObject(this.mDividerId, var1.getDividerDrawable());
         var2.readInt(this.mDividerPaddingId, var1.getDividerPadding());
         var2.readBoolean(this.mMeasureWithLargestChildId, var1.isMeasureWithLargestChildEnabled());
         var2.readIntFlag(this.mShowDividersId, var1.getShowDividers());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
