package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import androidx.cardview.R;

public class CardView extends FrameLayout {
   private static final int[] COLOR_BACKGROUND_ATTR = new int[]{16842801};
   private static final CardViewImpl IMPL;
   private final CardViewDelegate mCardViewDelegate;
   private boolean mCompatPadding;
   final Rect mContentPadding;
   private boolean mPreventCornerOverlap;
   final Rect mShadowBounds;
   int mUserSetMinHeight;
   int mUserSetMinWidth;

   static {
      if (VERSION.SDK_INT >= 21) {
         IMPL = new CardViewApi21Impl();
      } else if (VERSION.SDK_INT >= 17) {
         IMPL = new CardViewApi17Impl();
      } else {
         IMPL = new CardViewBaseImpl();
      }

      IMPL.initStatic();
   }

   public CardView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CardView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.cardViewStyle);
   }

   public CardView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      Rect var8 = new Rect();
      this.mContentPadding = var8;
      this.mShadowBounds = new Rect();
      CardViewDelegate var7 = new CardViewDelegate(this) {
         private Drawable mCardBackground;
         final CardView this$0;

         {
            this.this$0 = var1;
         }

         public Drawable getCardBackground() {
            return this.mCardBackground;
         }

         public View getCardView() {
            return this.this$0;
         }

         public boolean getPreventCornerOverlap() {
            return this.this$0.getPreventCornerOverlap();
         }

         public boolean getUseCompatPadding() {
            return this.this$0.getUseCompatPadding();
         }

         public void setCardBackground(Drawable var1) {
            this.mCardBackground = var1;
            this.this$0.setBackgroundDrawable(var1);
         }

         public void setMinWidthHeightInternal(int var1, int var2) {
            if (var1 > this.this$0.mUserSetMinWidth) {
               this.this$0.setMinimumWidth(var1);
            }

            if (var2 > this.this$0.mUserSetMinHeight) {
               this.this$0.setMinimumHeight(var2);
            }

         }

         public void setShadowPadding(int var1, int var2, int var3, int var4) {
            this.this$0.mShadowBounds.set(var1, var2, var3, var4);
            CardView var5 = this.this$0;
            var5.setPadding(var1 + var5.mContentPadding.left, var2 + this.this$0.mContentPadding.top, var3 + this.this$0.mContentPadding.right, var4 + this.this$0.mContentPadding.bottom);
         }
      };
      this.mCardViewDelegate = var7;
      TypedArray var9 = var1.obtainStyledAttributes(var2, R.styleable.CardView, var3, R.style.CardView);
      ColorStateList var10;
      if (var9.hasValue(R.styleable.CardView_cardBackgroundColor)) {
         var10 = var9.getColorStateList(R.styleable.CardView_cardBackgroundColor);
      } else {
         TypedArray var11 = this.getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
         var3 = var11.getColor(0, 0);
         var11.recycle();
         float[] var12 = new float[3];
         Color.colorToHSV(var3, var12);
         if (var12[2] > 0.5F) {
            var3 = this.getResources().getColor(R.color.cardview_light_background);
         } else {
            var3 = this.getResources().getColor(R.color.cardview_dark_background);
         }

         var10 = ColorStateList.valueOf(var3);
      }

      float var6 = var9.getDimension(R.styleable.CardView_cardCornerRadius, 0.0F);
      float var5 = var9.getDimension(R.styleable.CardView_cardElevation, 0.0F);
      float var4 = var9.getDimension(R.styleable.CardView_cardMaxElevation, 0.0F);
      this.mCompatPadding = var9.getBoolean(R.styleable.CardView_cardUseCompatPadding, false);
      this.mPreventCornerOverlap = var9.getBoolean(R.styleable.CardView_cardPreventCornerOverlap, true);
      var3 = var9.getDimensionPixelSize(R.styleable.CardView_contentPadding, 0);
      var8.left = var9.getDimensionPixelSize(R.styleable.CardView_contentPaddingLeft, var3);
      var8.top = var9.getDimensionPixelSize(R.styleable.CardView_contentPaddingTop, var3);
      var8.right = var9.getDimensionPixelSize(R.styleable.CardView_contentPaddingRight, var3);
      var8.bottom = var9.getDimensionPixelSize(R.styleable.CardView_contentPaddingBottom, var3);
      if (var5 > var4) {
         var4 = var5;
      }

      this.mUserSetMinWidth = var9.getDimensionPixelSize(R.styleable.CardView_android_minWidth, 0);
      this.mUserSetMinHeight = var9.getDimensionPixelSize(R.styleable.CardView_android_minHeight, 0);
      var9.recycle();
      IMPL.initialize(var7, var1, var10, var6, var5, var4);
   }

   public ColorStateList getCardBackgroundColor() {
      return IMPL.getBackgroundColor(this.mCardViewDelegate);
   }

   public float getCardElevation() {
      return IMPL.getElevation(this.mCardViewDelegate);
   }

   public int getContentPaddingBottom() {
      return this.mContentPadding.bottom;
   }

   public int getContentPaddingLeft() {
      return this.mContentPadding.left;
   }

   public int getContentPaddingRight() {
      return this.mContentPadding.right;
   }

   public int getContentPaddingTop() {
      return this.mContentPadding.top;
   }

   public float getMaxCardElevation() {
      return IMPL.getMaxElevation(this.mCardViewDelegate);
   }

   public boolean getPreventCornerOverlap() {
      return this.mPreventCornerOverlap;
   }

   public float getRadius() {
      return IMPL.getRadius(this.mCardViewDelegate);
   }

   public boolean getUseCompatPadding() {
      return this.mCompatPadding;
   }

   protected void onMeasure(int var1, int var2) {
      CardViewImpl var4 = IMPL;
      if (!(var4 instanceof CardViewApi21Impl)) {
         int var3 = MeasureSpec.getMode(var1);
         if (var3 == Integer.MIN_VALUE || var3 == 1073741824) {
            var1 = MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil((double)var4.getMinWidth(this.mCardViewDelegate)), MeasureSpec.getSize(var1)), var3);
         }

         var3 = MeasureSpec.getMode(var2);
         if (var3 == Integer.MIN_VALUE || var3 == 1073741824) {
            var2 = MeasureSpec.makeMeasureSpec(Math.max((int)Math.ceil((double)var4.getMinHeight(this.mCardViewDelegate)), MeasureSpec.getSize(var2)), var3);
         }

         super.onMeasure(var1, var2);
      } else {
         super.onMeasure(var1, var2);
      }

   }

   public void setCardBackgroundColor(int var1) {
      IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(var1));
   }

   public void setCardBackgroundColor(ColorStateList var1) {
      IMPL.setBackgroundColor(this.mCardViewDelegate, var1);
   }

   public void setCardElevation(float var1) {
      IMPL.setElevation(this.mCardViewDelegate, var1);
   }

   public void setContentPadding(int var1, int var2, int var3, int var4) {
      this.mContentPadding.set(var1, var2, var3, var4);
      IMPL.updatePadding(this.mCardViewDelegate);
   }

   public void setMaxCardElevation(float var1) {
      IMPL.setMaxElevation(this.mCardViewDelegate, var1);
   }

   public void setMinimumHeight(int var1) {
      this.mUserSetMinHeight = var1;
      super.setMinimumHeight(var1);
   }

   public void setMinimumWidth(int var1) {
      this.mUserSetMinWidth = var1;
      super.setMinimumWidth(var1);
   }

   public void setPadding(int var1, int var2, int var3, int var4) {
   }

   public void setPaddingRelative(int var1, int var2, int var3, int var4) {
   }

   public void setPreventCornerOverlap(boolean var1) {
      if (var1 != this.mPreventCornerOverlap) {
         this.mPreventCornerOverlap = var1;
         IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
      }

   }

   public void setRadius(float var1) {
      IMPL.setRadius(this.mCardViewDelegate, var1);
   }

   public void setUseCompatPadding(boolean var1) {
      if (this.mCompatPadding != var1) {
         this.mCompatPadding = var1;
         IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
      }

   }
}
