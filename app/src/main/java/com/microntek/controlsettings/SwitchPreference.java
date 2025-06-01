package com.microntek.controlsettings;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Locale;

public class SwitchPreference extends Preference {
   private int getLine;
   private String getValuedate;
   private ImageButton mBt_next;
   private ImageButton mBt_pre;
   private TextView mContent_text;
   private CharSequence[] mEntries;
   private int mEntryIndex;
   private CharSequence[] mEntryValues;
   private String mSummary;
   private String mValue;

   public SwitchPreference(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SwitchPreference(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.getLine = -1;
      TypedArray var3 = var1.obtainStyledAttributes(var2, R$styleable.SwitchboxPreference, 0, 0);
      this.mEntries = var3.getTextArray(1);
      this.mEntryValues = var3.getTextArray(3);
      var3.recycle();
      this.setWidgetLayoutResource(2131230730);
   }

   private int getValueIndex() {
      return this.findIndexOfValue(this.mValue);
   }

   public int findIndexOfValue(String var1) {
      if (var1 != null) {
         CharSequence[] var3 = this.mEntryValues;
         if (var3 != null) {
            for(int var2 = var3.length - 1; var2 >= 0; --var2) {
               if (this.mEntryValues[var2].equals(var1)) {
                  return var2;
               }
            }
         }
      }

      return -1;
   }

   public CharSequence getEntry() {
      int var1 = this.getValueIndex();
      CharSequence var3;
      if (var1 >= 0) {
         CharSequence[] var2 = this.mEntries;
         if (var2 != null) {
            var3 = var2[var1];
            return var3;
         }
      }

      var3 = null;
      return var3;
   }

   public CharSequence getSummary() {
      CharSequence var1 = this.getEntry();
      String var2 = this.mSummary;
      return (CharSequence)(var2 != null && var1 != null ? String.format(Locale.US, var2, var1) : super.getSummary());
   }

   public String getValue() {
      return this.mValue;
   }

   protected void onBindView(View var1) {
      super.onBindView(var1);
      ViewGroup var2 = (ViewGroup)var1;
      ((TextView)((ViewGroup)var2.getChildAt(1)).getChildAt(0)).setTextSize(18.0F);
      if (this.getLine != -1 || !this.isSelectable()) {
         var2.setBackgroundResource(2131099649);
      }

      this.mBt_pre = (ImageButton)var1.findViewById(2131165194);
      this.mBt_next = (ImageButton)var1.findViewById(2131165193);
      this.mBt_pre.setVisibility(8);
      this.mBt_next.setVisibility(8);
      this.mContent_text = (TextView)var1.findViewById(2131165207);
      this.mEntryIndex = this.getValueIndex();
      if (this.mEntryIndex == -1) {
         this.mEntryIndex = 0;
      }

      CharSequence[] var3 = this.mEntryValues;
      if (var3 != null) {
         String var4 = var3[this.mEntryIndex].toString();
         if (this.callChangeListener(var4)) {
            this.setValue(var4);
         }
      }

   }

   protected Object onGetDefaultValue(TypedArray var1, int var2) {
      return var1.getString(var2);
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (var1 != null && var1.getClass().equals(SavedState.class)) {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.setValue(var2.value);
      } else {
         super.onRestoreInstanceState(var1);
      }
   }

   protected Parcelable onSaveInstanceState() {
      Parcelable var1 = super.onSaveInstanceState();
      if (this.isPersistent()) {
         return var1;
      } else {
         SavedState var2 = new SavedState(var1);
         var2.value = this.getValue();
         return var2;
      }
   }

   protected void onSetInitialValue(boolean var1, Object var2) {
      String var3;
      if (var1) {
         var3 = this.getPersistedString(this.mValue);
      } else {
         var3 = (String)var2;
      }

      this.setValue(var3);
   }

   public void setLine(int var1) {
      if (var1 == 1) {
         try {
            this.getLine = 1;
         } catch (Exception var3) {
            this.getLine = -1;
         }
      }

   }

   public void setSummary(CharSequence var1) {
      super.setSummary(var1);
      if (var1 == null && this.mSummary != null) {
         this.mSummary = null;
      } else if (var1 != null && !var1.equals(this.mSummary)) {
         this.mSummary = var1.toString();
      }

   }

   public void setValue(String var1) {
      this.mValue = var1;
      this.mEntryIndex = this.getValueIndex();
      if (this.mContent_text != null) {
         int var2 = this.mEntryIndex;
         if (var2 != -1) {
            var1 = this.mEntries[var2].toString();
            this.mContent_text.setText(var1);
         }
      }

      var1 = this.getValuedate;
      if (var1 != null) {
         this.setValue("0", var1);
      }

   }

   public void setValue(String var1, String var2) {
      this.getValuedate = var2;
      this.mValue = var1;
      this.mEntryIndex = this.getValueIndex();
      if (this.mContent_text != null) {
         int var3 = this.mEntryIndex;
         if (var3 != -1) {
            this.mEntries[var3].toString();
            if (this.getValuedate == null) {
               this.getValuedate = "------";
            }

            TextView var5 = this.mContent_text;
            StringBuilder var4 = new StringBuilder();
            var4.append("");
            var4.append(this.getValuedate);
            var5.setText(var4.toString());
         }
      }

   }

   private static class SavedState extends BaseSavedState {
      public static final Creator CREATOR = new Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      String value;

      public SavedState(Parcel var1) {
         super(var1);
         this.value = var1.readString();
      }

      public SavedState(Parcelable var1) {
         super(var1);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeString(this.value);
      }
   }
}
