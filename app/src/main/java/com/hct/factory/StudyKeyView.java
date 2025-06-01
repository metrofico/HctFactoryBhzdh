package com.hct.factory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StudyKeyView extends LinearLayout {
   private LinearLayout mBg;
   private int mBgId = 0;
   private int mIndex = -1;
   private int mLong = 0;
   private ImageView mLongIco;
   private int mShort = 0;
   private ImageView mShortIco;
   private int mValue = 0;
   private int mX = -1;
   private int mY = -1;

   public StudyKeyView(Context var1) {
      super(var1, (AttributeSet)null);
   }

   public StudyKeyView(Context var1, AttributeSet var2) {
      super(var1, var2);
      View var3 = LayoutInflater.from(var1).inflate(2131230741, this, true);
      this.mBg = (LinearLayout)var3.findViewById(2131099677);
      this.mShortIco = (ImageView)var3.findViewById(2131099856);
      this.mLongIco = (ImageView)var3.findViewById(2131099806);
   }

   public void ClearAllData() {
      this.mValue = 0;
      this.mShort = 0;
      this.mLong = 0;
      this.mIndex = -1;
      this.mBgId = 0;
      this.mX = -1;
      this.mY = -1;
      this.mShortIco.setImageResource(0);
      this.mLongIco.setImageResource(0);
      this.setVisibility(4);
   }

   public View getBgView() {
      return this.mShortIco;
   }

   public int getButtonbg() {
      return this.mBgId;
   }

   public int getKeyIndex() {
      return this.mIndex;
   }

   public int getKeyLongIR() {
      return this.mLong;
   }

   public int getKeyShortIR() {
      return this.mShort;
   }

   public int getKeyVaule() {
      return this.mValue;
   }

   public int getTouchX() {
      return this.mX;
   }

   public int getTouchY() {
      return this.mY;
   }

   public void setButtonbg(int var1) {
      this.mBgId = var1;
      this.mBg.setBackgroundResource(var1);
   }

   public void setKeyIndex(int var1) {
      this.mIndex = var1;
   }

   public void setKeyLongIR(int var1) {
      this.mLong = var1;
   }

   public void setKeyShortIR(int var1) {
      this.mShort = var1;
   }

   public void setKeyVaule(int var1) {
      this.mValue = var1;
   }

   public void setLongIco(int var1) {
      this.mLongIco.setImageResource(var1);
   }

   public void setShortIco(int var1) {
      this.mShortIco.setImageResource(var1);
   }

   public void setTouchX(int var1) {
      this.mX = var1;
   }

   public void setTouchY(int var1) {
      this.mY = var1;
   }

   public interface StudyKeyFun {
      void onCilck(View var1);
   }
}
