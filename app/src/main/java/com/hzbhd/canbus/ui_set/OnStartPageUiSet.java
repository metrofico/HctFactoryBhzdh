package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener;
import com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener;

public class OnStartPageUiSet {
   private String[] btnAction;
   private OnOnStarPhoneMoreInfoClickListener mOnOnStarPhoneMoreInfoClickListener;
   private OnOnStartStatusChangeListener mOnOnStartStatusChangeListener;
   private OnOnStarClickListener onOnStarClickListener;

   public String[] getBtnAction() {
      return this.btnAction;
   }

   public OnOnStarClickListener getOnOnStarClickListener() {
      return this.onOnStarClickListener;
   }

   public OnOnStarPhoneMoreInfoClickListener getOnOnStarPhoneMoreInfoClickListener() {
      return this.mOnOnStarPhoneMoreInfoClickListener;
   }

   public OnOnStartStatusChangeListener getOnOnStartStatusChangeListener() {
      return this.mOnOnStartStatusChangeListener;
   }

   public void setBtnAction(String[] var1) {
      this.btnAction = var1;
   }

   public void setOnOnStarClickListener(OnOnStarClickListener var1) {
      this.onOnStarClickListener = var1;
   }

   public void setOnOnStartStatusChangeListener(OnOnStartStatusChangeListener var1) {
      this.mOnOnStartStatusChangeListener = var1;
   }

   public void setmOnOnStarPhoneMoreInfoClickListener(OnOnStarPhoneMoreInfoClickListener var1) {
      this.mOnOnStarPhoneMoreInfoClickListener = var1;
   }
}
