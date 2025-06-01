package com.microntek.controlsettings;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public abstract class HTwoStatePreference extends Preference {
   public boolean mChecked;
   private boolean mCheckedSet;
   private boolean mDisableDependentsState;
   private boolean mSendClickAccessibilityEvent;
   private CharSequence mSummaryOff;
   private CharSequence mSummaryOn;
   public boolean mpreChecked;

   public HTwoStatePreference(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public HTwoStatePreference(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public HTwoStatePreference(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean isChecked() {
      return this.mChecked ^ true;
   }

   protected void onClick() {
      super.onClick();
      boolean var1 = this.isChecked() ^ true;
      this.mSendClickAccessibilityEvent = true;
      if (this.callChangeListener(var1)) {
         this.setChecked1(var1);
      }
   }

   protected Object onGetDefaultValue(TypedArray var1, int var2) {
      return var1.getBoolean(var2, false);
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (var1 != null && var1.getClass().equals(SavedState.class)) {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.setChecked(var2.checked);
      } else {
         super.onRestoreInstanceState((Parcelable)null);
      }
   }

   protected Parcelable onSaveInstanceState() {
      Parcelable var1 = super.onSaveInstanceState();
      if (this.isPersistent()) {
         return var1;
      } else {
         SavedState var2 = new SavedState(var1);
         var2.checked = this.isChecked();
         return var2;
      }
   }

   protected void onSetInitialValue(boolean var1, Object var2) {
      if (var1) {
         var1 = this.getPersistedBoolean(this.mChecked);
      } else {
         var1 = (Boolean)var2;
      }

      this.setChecked(var1);
   }

   public void setChecked(boolean var1) {
      boolean var2;
      if (this.mChecked != var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2 || !this.mCheckedSet) {
         this.mChecked = var1;
         this.mCheckedSet = true;
         if (var2) {
            this.notifyDependencyChange(this.shouldDisableDependents());
            this.notifyChanged();
         }
      }

   }

   public void setChecked1(boolean var1) {
      this.mpreChecked = var1;
   }

   public void setDisableDependentsState(boolean var1) {
      this.mDisableDependentsState = var1;
   }

   public void setSummaryOff(CharSequence var1) {
      this.mSummaryOff = var1;
      if (!this.isChecked()) {
         this.notifyChanged();
      }

   }

   public void setSummaryOn(CharSequence var1) {
      this.mSummaryOn = var1;
      if (this.isChecked()) {
         this.notifyChanged();
      }

   }

   public boolean shouldDisableDependents() {
      boolean var1 = this.mDisableDependentsState;
      boolean var3 = true;
      if (var1) {
         var1 = this.mChecked;
      } else if (!this.mChecked) {
         var1 = true;
      } else {
         var1 = false;
      }

      boolean var2 = var3;
      if (!var1) {
         if (super.shouldDisableDependents()) {
            var2 = var3;
         } else {
            var2 = false;
         }
      }

      return var2;
   }

   void syncSummaryView(View var1) {
      TextView var5 = (TextView)var1.findViewById(16908304);
      if (var5 != null) {
         boolean var2;
         boolean var3;
         CharSequence var4;
         label35: {
            label40: {
               var3 = true;
               if (this.mChecked) {
                  var4 = this.mSummaryOn;
                  if (var4 != null) {
                     var5.setText(var4);
                     break label40;
                  }
               }

               var2 = var3;
               if (this.mChecked) {
                  break label35;
               }

               var4 = this.mSummaryOff;
               var2 = var3;
               if (var4 == null) {
                  break label35;
               }

               var5.setText(var4);
            }

            var2 = false;
         }

         var3 = var2;
         if (var2) {
            var4 = this.getSummary();
            var3 = var2;
            if (var4 != null) {
               var5.setText(var4);
               var3 = false;
            }
         }

         byte var6 = 8;
         if (!var3) {
            var6 = 0;
         }

         if (var6 != var5.getVisibility()) {
            var5.setVisibility(var6);
         }
      }

   }

   static class SavedState extends BaseSavedState {
      public static final Creator CREATOR = new Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      boolean checked;

      public SavedState(Parcel var1) {
         super(var1);
         int var2 = var1.readInt();
         boolean var3 = true;
         if (var2 != 1) {
            var3 = false;
         }

         this.checked = var3;
      }

      public SavedState(Parcelable var1) {
         super(var1);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.checked);
      }
   }
}
