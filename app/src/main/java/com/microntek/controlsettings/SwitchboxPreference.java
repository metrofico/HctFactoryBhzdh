package com.microntek.controlsettings;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Locale;

public class SwitchboxPreference extends Preference {
   private boolean isBtnEnabled;
   private boolean isEntriesSwap;
   private ImageButton mBt_next;
   private ImageButton mBt_pre;
   private TextView mContent_text;
   private CharSequence[] mEntries;
   private int mEntryIndex;
   private CharSequence[] mEntryValues;
   private String mSummary;
   private String mValue;

   public SwitchboxPreference(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public SwitchboxPreference(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mValue = "0";
      this.isEntriesSwap = false;
      this.isBtnEnabled = true;
      TypedArray var3 = var1.obtainStyledAttributes(var2, R$styleable.SwitchboxPreference, 0, 0);
      this.mEntries = var3.getTextArray(1);
      this.mEntryValues = var3.getTextArray(3);
      this.isEntriesSwap = var3.getBoolean(2, false);
      this.isBtnEnabled = var3.getBoolean(0, true);
      this.setEntriesSwap(this.isEntriesSwap);
      var3.recycle();
      this.setWidgetLayoutResource(2131230730);
   }

   // $FF: synthetic method
   static int access$208(SwitchboxPreference var0) {
      int var1 = var0.mEntryIndex++;
      return var1;
   }

   // $FF: synthetic method
   static int access$210(SwitchboxPreference var0) {
      int var1 = var0.mEntryIndex--;
      return var1;
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
      ((TextView)((ViewGroup)((ViewGroup)var1).getChildAt(1)).getChildAt(0)).setTextSize(18.0F);
      this.mBt_pre = (ImageButton)var1.findViewById(2131165194);
      this.mBt_next = (ImageButton)var1.findViewById(2131165193);
      this.mContent_text = (TextView)var1.findViewById(2131165207);
      this.mEntryIndex = this.getValueIndex();
      if (this.mEntryIndex == -1) {
         this.mEntryIndex = 0;
      }

      this.mContent_text.setFocusableInTouchMode(true);
      this.mContent_text.setFocusable(true);
      this.mContent_text.setEllipsize(TruncateAt.MARQUEE);
      this.mContent_text.setOnTouchListener(new View.OnTouchListener(this) {
         final SwitchboxPreference this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            this.this$0.mContent_text.requestFocus();
            return true;
         }
      });
      this.mBt_pre.setOnClickListener(new View.OnClickListener(this) {
         final SwitchboxPreference this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mEntryValues != null) {
               if (this.this$0.mEntryIndex > 0) {
                  SwitchboxPreference.access$210(this.this$0);
               } else {
                  this.this$0.mEntryIndex = 0;
               }

               String var2 = this.this$0.mEntryValues[this.this$0.mEntryIndex].toString();
               if (this.this$0.callChangeListener(var2)) {
                  this.this$0.setValue1(var2);
               }
            }

         }
      });
      this.mBt_next.setOnClickListener(new View.OnClickListener(this) {
         final SwitchboxPreference this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.mEntryValues != null) {
               if (this.this$0.mEntryIndex < this.this$0.mEntryValues.length - 1) {
                  SwitchboxPreference.access$208(this.this$0);
               } else {
                  SwitchboxPreference var2 = this.this$0;
                  var2.mEntryIndex = var2.mEntryValues.length - 1;
               }

               String var3 = this.this$0.mEntryValues[this.this$0.mEntryIndex].toString();
               if (this.this$0.callChangeListener(var3)) {
                  this.this$0.setValue1(var3);
               }
            }

         }
      });
      CharSequence[] var2 = this.mEntryValues;
      if (var2 != null) {
         String var3 = var2[this.mEntryIndex].toString();
         if (this.callChangeListener(var3)) {
            this.setValue(var3);
         }
      }

      this.setBtnEnabled(this.isBtnEnabled);
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

   public void setBtnEnabled(boolean var1) {
      if (!var1) {
         ImageButton var2 = this.mBt_pre;
         if (var2 != null) {
            var2.setVisibility(4);
         }

         var2 = this.mBt_next;
         if (var2 != null) {
            var2.setVisibility(4);
         }
      }

   }

   public void setEntries(int var1) {
      this.setEntries(this.getContext().getResources().getTextArray(var1));
   }

   public void setEntries(CharSequence[] var1) {
      this.mEntries = var1;
      this.setEntriesSwap(this.isEntriesSwap);
   }

   public void setEntriesSwap(boolean var1) {
      CharSequence[] var3 = this.mEntries;
      if (var3 != null && var1) {
         var3 = (CharSequence[])var3.clone();

         for(int var2 = 0; var2 < var3.length; ++var2) {
            this.mEntries[var3.length - 1 - var2] = var3[var2];
         }
      }

   }

   public void setEntryValues(int var1) {
      this.setEntryValues(this.getContext().getResources().getTextArray(var1));
   }

   public void setEntryValues(CharSequence[] var1) {
      this.mEntryValues = var1;
      var1 = this.mEntryValues;
      if (var1 != null && var1.length > 0) {
         this.mValue = var1[0].toString();
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

   public void setValue(int var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("");
      var2.append(var1);
      this.setValue(var2.toString());
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

   }

   public void setValue1(String var1) {
      this.mValue = var1;
      this.mEntryIndex = this.getValueIndex();
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
