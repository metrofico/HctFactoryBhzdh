package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.appcompat.R;

public final class SwitchCompat$InspectionCompanion implements InspectionCompanion {
   private boolean mPropertiesMapped = false;
   private int mShowTextId;
   private int mSplitTrackId;
   private int mSwitchMinWidthId;
   private int mSwitchPaddingId;
   private int mTextOffId;
   private int mTextOnId;
   private int mThumbId;
   private int mThumbTextPaddingId;
   private int mThumbTintId;
   private int mThumbTintModeId;
   private int mTrackId;
   private int mTrackTintId;
   private int mTrackTintModeId;

   public void mapProperties(PropertyMapper var1) {
      this.mTextOffId = var1.mapObject("textOff", 16843045);
      this.mTextOnId = var1.mapObject("textOn", 16843044);
      this.mThumbId = var1.mapObject("thumb", 16843074);
      this.mShowTextId = var1.mapBoolean("showText", R.attr.showText);
      this.mSplitTrackId = var1.mapBoolean("splitTrack", R.attr.splitTrack);
      this.mSwitchMinWidthId = var1.mapInt("switchMinWidth", R.attr.switchMinWidth);
      this.mSwitchPaddingId = var1.mapInt("switchPadding", R.attr.switchPadding);
      this.mThumbTextPaddingId = var1.mapInt("thumbTextPadding", R.attr.thumbTextPadding);
      this.mThumbTintId = var1.mapObject("thumbTint", R.attr.thumbTint);
      this.mThumbTintModeId = var1.mapObject("thumbTintMode", R.attr.thumbTintMode);
      this.mTrackId = var1.mapObject("track", R.attr.track);
      this.mTrackTintId = var1.mapObject("trackTint", R.attr.trackTint);
      this.mTrackTintModeId = var1.mapObject("trackTintMode", R.attr.trackTintMode);
      this.mPropertiesMapped = true;
   }

   public void readProperties(SwitchCompat var1, PropertyReader var2) {
      if (this.mPropertiesMapped) {
         var2.readObject(this.mTextOffId, var1.getTextOff());
         var2.readObject(this.mTextOnId, var1.getTextOn());
         var2.readObject(this.mThumbId, var1.getThumbDrawable());
         var2.readBoolean(this.mShowTextId, var1.getShowText());
         var2.readBoolean(this.mSplitTrackId, var1.getSplitTrack());
         var2.readInt(this.mSwitchMinWidthId, var1.getSwitchMinWidth());
         var2.readInt(this.mSwitchPaddingId, var1.getSwitchPadding());
         var2.readInt(this.mThumbTextPaddingId, var1.getThumbTextPadding());
         var2.readObject(this.mThumbTintId, var1.getThumbTintList());
         var2.readObject(this.mThumbTintModeId, var1.getThumbTintMode());
         var2.readObject(this.mTrackId, var1.getTrackDrawable());
         var2.readObject(this.mTrackTintId, var1.getTrackTintList());
         var2.readObject(this.mTrackTintModeId, var1.getTrackTintMode());
      } else {
         throw new InspectionCompanion.UninitializedPropertyMapException();
      }
   }
}
