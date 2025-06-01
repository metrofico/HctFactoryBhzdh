package com.microntek.controlsettings;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Locale;

public class OnSwitchPreference extends Preference {
   private int getLine;
   private String getValuedate;
   public Handler handler;
   private boolean isDow;
   private ImageButton mBt_next;
   private ImageButton mBt_pre;
   private TextView mContent_text;
   private CharSequence[] mEntries;
   private int mEntryIndex;
   private CharSequence[] mEntryValues;
   private String mSummary;
   private String mValue;

   public OnSwitchPreference(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public OnSwitchPreference(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.getLine = -1;
      this.handler = new Handler(this) {
         final OnSwitchPreference this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            int var2 = var1.what;
            if (var2 == 0) {
               this.this$0.setPre();
               this.this$0.handler.sendEmptyMessageDelayed(0, 200L);
            } else if (1 == var2) {
               this.this$0.setNext();
               this.this$0.handler.sendEmptyMessageDelayed(1, 200L);
            }

         }
      };
      TypedArray var3 = var1.obtainStyledAttributes(var2, R$styleable.SwitchboxPreference, 0, 0);
      this.mEntries = var3.getTextArray(1);
      this.mEntryValues = var3.getTextArray(3);
      var3.recycle();
      this.setWidgetLayoutResource(2131230730);
   }

   private int getValueIndex() {
      return this.findIndexOfValue(this.mValue);
   }

   private void setNext() {
      CharSequence[] var2 = this.mEntryValues;
      if (var2 != null) {
         int var1 = this.mEntryIndex;
         if (var1 < var2.length - 1) {
            this.mEntryIndex = var1 + 1;
         } else {
            this.mEntryIndex = var2.length - 1;
         }

         String var3 = this.mEntryValues[this.mEntryIndex].toString();
         if (this.callChangeListener(var3)) {
            this.setValue1(var3);
         }
      }

   }

   private void setPre() {
      if (this.mEntryValues != null) {
         int var1 = this.mEntryIndex;
         if (var1 > 0) {
            this.mEntryIndex = var1 - 1;
         } else {
            this.mEntryIndex = 0;
         }

         String var2 = this.mEntryValues[this.mEntryIndex].toString();
         if (this.callChangeListener(var2)) {
            this.setValue1(var2);
         }
      }

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

   public CharSequence[] getEntries() {
      return this.mEntries;
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

   public boolean getIsDow() {
      return this.isDow;
   }

   public CharSequence getSummary() {
      CharSequence var2 = this.getEntry();
      String var1 = this.mSummary;
      return (CharSequence)(var1 != null && var2 != null ? String.format(Locale.US, var1, var2) : super.getSummary());
   }

   public String getValue() {
      return this.mValue;
   }

   protected void onBindView(View var1) {
      super.onBindView(var1);
      ViewGroup var2 = (ViewGroup)var1;
      ((TextView)((ViewGroup)var2.getChildAt(1)).getChildAt(0)).setTextSize(18.0F);
      if (this.getLine != -1) {
         var2.setBackgroundResource(2131099649);
      }

      this.mBt_pre = (ImageButton)var1.findViewById(2131165194);
      this.mBt_next = (ImageButton)var1.findViewById(2131165193);
      this.mContent_text = (TextView)var1.findViewById(2131165207);
      this.mEntryIndex = this.getValueIndex();
      if (this.mEntryIndex == -1) {
         this.mEntryIndex = 0;
      }

      this.mBt_pre.setOnClickListener(new View.OnClickListener(this) {
         final OnSwitchPreference this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.handler.removeMessages(1);
            this.this$0.handler.removeMessages(0);
         }
      });
      this.mBt_pre.setOnTouchListener(new View.OnTouchListener(this) {
         final OnSwitchPreference this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            int var3 = var2.getAction();
            if (var3 != 3 && var3 != 1) {
               if (var3 == 0) {
                  this.this$0.handler.sendEmptyMessageDelayed(0, 1400L);
                  this.this$0.setIsDow(true);
               }
            } else {
               this.this$0.handler.removeMessages(0);
               this.this$0.setPre();
               this.this$0.setIsDow(false);
            }

            return false;
         }
      });
      this.mBt_next.setOnClickListener(new View.OnClickListener(this) {
         final OnSwitchPreference this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.handler.removeMessages(1);
            this.this$0.handler.removeMessages(0);
         }
      });
      this.mBt_next.setOnTouchListener(new View.OnTouchListener(this) {
         final OnSwitchPreference this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            int var3 = var2.getAction();
            if (var3 != 3 && var3 != 1) {
               if (var3 == 0) {
                  this.this$0.handler.sendEmptyMessageDelayed(1, 1400L);
                  this.this$0.setIsDow(true);
               }
            } else {
               this.this$0.handler.removeMessages(1);
               this.this$0.setNext();
               this.this$0.setIsDow(false);
            }

            return false;
         }
      });
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

   public void setIsDow(boolean var1) {
      this.isDow = var1;
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

            TextView var4 = this.mContent_text;
            StringBuilder var5 = new StringBuilder();
            var5.append("");
            var5.append(this.getValuedate);
            var4.setText(var5.toString());
         }
      }

   }

   public void setValue1(String var1) {
      this.mValue = var1;
      this.mEntryIndex = this.getValueIndex();
      this.persistString(var1);
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
