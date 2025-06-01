package com.microntek.controlsettings;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarPreference extends Preference implements SeekBar.OnSeekBarChangeListener {
   private int mMax;
   private int mProgress;
   private boolean mTrackingTouch;
   private TextView value;

   public SeekBarPreference(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SeekBarPreference(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public SeekBarPreference(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mProgress = 0;
      this.mMax = 100;
      this.mMax = var1.obtainStyledAttributes(var2, R$styleable.SeekBarPreference).getInteger(0, this.mMax);
      this.setMax(this.mMax);
      this.setWidgetLayoutResource(2131230729);
   }

   private void setProgress(int var1, boolean var2) {
      int var4 = this.mMax;
      int var3 = var1;
      if (var1 > var4) {
         var3 = var4;
      }

      var1 = var3;
      if (var3 < 0) {
         var1 = 0;
      }

      if (var2) {
         TextView var5 = this.value;
         if (var5 != null) {
            StringBuilder var6 = new StringBuilder();
            var6.append(var1);
            var6.append("");
            var5.setText(var6.toString());
         }
      }

      if (var1 != this.mProgress) {
         this.mProgress = var1;
         this.persistInt(var1);
         if (var2) {
            this.notifyChanged();
         }
      }

   }

   public int getProgress() {
      return this.mProgress;
   }

   protected void onBindView(View var1) {
      super.onBindView(var1);
      ((TextView)((ViewGroup)((ViewGroup)var1).getChildAt(1)).getChildAt(0)).setTextSize(18.0F);
      SeekBar var2 = (SeekBar)var1.findViewById(2131165260);
      var2.setMax(this.mMax);
      var2.setProgress(this.mProgress);
      var2.setEnabled(this.isEnabled());
      var2.setOnSeekBarChangeListener(this);
      this.value = (TextView)var1.findViewById(2131165267);
      this.value.setText(String.valueOf(this.mProgress));
      LinearLayout var4 = (LinearLayout)var1.findViewById(16908312);
      ViewGroup.LayoutParams var3 = var4.getLayoutParams();
      var3.width = this.getContext().getResources().getDisplayMetrics().widthPixels / 2;
      var4.setLayoutParams(var3);
   }

   public void onProgressChanged(SeekBar var1, int var2, boolean var3) {
      if (var3 && !this.mTrackingTouch) {
         this.syncProgress(var1);
      }

   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!var1.getClass().equals(SavedState.class)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.mProgress = var2.progress;
         this.mMax = var2.max;
         this.notifyChanged();
      }
   }

   protected Parcelable onSaveInstanceState() {
      Parcelable var1 = super.onSaveInstanceState();
      if (this.isPersistent()) {
         return var1;
      } else {
         SavedState var2 = new SavedState(var1);
         var2.progress = this.mProgress;
         var2.max = this.mMax;
         return var2;
      }
   }

   protected void onSetInitialValue(boolean var1, Object var2) {
      int var3;
      if (var1) {
         var3 = this.getPersistedInt(this.mProgress);
      } else {
         var3 = (Integer)var2;
      }

      this.setProgress(var3);
   }

   public void onStartTrackingTouch(SeekBar var1) {
      this.mTrackingTouch = true;
   }

   public void onStopTrackingTouch(SeekBar var1) {
      this.mTrackingTouch = false;
      if (var1.getProgress() != this.mProgress) {
         this.syncProgress(var1);
      }

   }

   public void setMax(int var1) {
      if (var1 != this.mMax) {
         this.mMax = var1;
         this.notifyChanged();
      }

   }

   public void setProgress(int var1) {
      this.setProgress(var1, true);
   }

   void syncProgress(SeekBar var1) {
      int var2 = var1.getProgress();
      if (var2 != this.mProgress) {
         if (this.callChangeListener(var2)) {
            this.setProgress(var2, false);
         } else {
            var1.setProgress(this.mProgress);
         }
      }

   }

   private static class SavedState extends BaseSavedState {
      int max;
      int progress;

      public SavedState(Parcelable var1) {
         super(var1);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.progress);
         var1.writeInt(this.max);
      }
   }
}
