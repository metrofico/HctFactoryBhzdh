package com.microntek.controlsettings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.android.internal.R.styleable;

public class HCheckBoxPreference extends HTwoStatePreference {
   private int getLine;
   private final Listener mListener;
   private CharSequence mSwitchOff;
   private CharSequence mSwitchOn;

   public HCheckBoxPreference(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public HCheckBoxPreference(Context var1, AttributeSet var2) {
      this(var1, var2, 16843629);
   }

   public HCheckBoxPreference(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.getLine = -1;
      this.mListener = new Listener(this);
      TypedArray var4 = var1.obtainStyledAttributes(var2, styleable.SwitchPreference, var3, 0);
      this.setSummaryOn(var4.getString(0));
      this.setSummaryOff(var4.getString(1));
      this.setDisableDependentsState(var4.getBoolean(2, false));
      var4.recycle();
   }

   protected void onBindView(View var1) {
      super.onBindView(var1);
      ViewGroup var3 = (ViewGroup)var1;
      ((TextView)((ViewGroup)var3.getChildAt(1)).getChildAt(0)).setTextSize(18.0F);
      if (this.getLine != -1) {
         var3.setBackgroundResource(2131099649);
      }

      View var4 = var1.findViewById(16908352);
      if (var4 != null && var4 instanceof Checkable) {
         boolean var2 = var4 instanceof Switch;
         if (var2) {
            ((Switch)var4).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)null);
         }

         ((Checkable)var4).setChecked(super.mChecked);
         if (var2) {
            Switch var5 = (Switch)var4;
            var5.setTextOn(this.mSwitchOn);
            var5.setTextOff(this.mSwitchOff);
            var5.setOnCheckedChangeListener(this.mListener);
         }
      }

      this.syncSummaryView(var1);
   }

   private class Listener implements CompoundButton.OnCheckedChangeListener {
      final HCheckBoxPreference this$0;

      private Listener(HCheckBoxPreference var1) {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      Listener(HCheckBoxPreference var1, Object var2) {
         this(var1);
      }

      public void onCheckedChanged(CompoundButton var1, boolean var2) {
         if (!this.this$0.callChangeListener(var2)) {
            var1.setChecked(var2 ^ true);
         } else {
            this.this$0.setChecked(var2);
         }
      }
   }
}
