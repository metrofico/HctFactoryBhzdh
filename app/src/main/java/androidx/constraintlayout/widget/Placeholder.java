package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

public class Placeholder extends View {
   private View mContent = null;
   private int mContentId = -1;
   private int mEmptyVisibility = 4;

   public Placeholder(Context var1) {
      super(var1);
      this.init((AttributeSet)null);
   }

   public Placeholder(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.init(var2);
   }

   public Placeholder(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.init(var2);
   }

   public Placeholder(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3);
      this.init(var2);
   }

   private void init(AttributeSet var1) {
      super.setVisibility(this.mEmptyVisibility);
      this.mContentId = -1;
      if (var1 != null) {
         TypedArray var5 = this.getContext().obtainStyledAttributes(var1, R.styleable.ConstraintLayout_placeholder);
         int var3 = var5.getIndexCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            int var4 = var5.getIndex(var2);
            if (var4 == R.styleable.ConstraintLayout_placeholder_content) {
               this.mContentId = var5.getResourceId(var4, this.mContentId);
            } else if (var4 == R.styleable.ConstraintLayout_placeholder_emptyVisibility) {
               this.mEmptyVisibility = var5.getInt(var4, this.mEmptyVisibility);
            }
         }
      }

   }

   public View getContent() {
      return this.mContent;
   }

   public int getEmptyVisibility() {
      return this.mEmptyVisibility;
   }

   public void onDraw(Canvas var1) {
      if (this.isInEditMode()) {
         var1.drawRGB(223, 223, 223);
         Paint var5 = new Paint();
         var5.setARGB(255, 210, 210, 210);
         var5.setTextAlign(Align.CENTER);
         var5.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
         Rect var4 = new Rect();
         var1.getClipBounds(var4);
         var5.setTextSize((float)var4.height());
         int var2 = var4.height();
         int var3 = var4.width();
         var5.setTextAlign(Align.LEFT);
         var5.getTextBounds("?", 0, 1, var4);
         var1.drawText("?", (float)var3 / 2.0F - (float)var4.width() / 2.0F - (float)var4.left, (float)var2 / 2.0F + (float)var4.height() / 2.0F - (float)var4.bottom, var5);
      }

   }

   public void setContentId(int var1) {
      if (this.mContentId != var1) {
         View var2 = this.mContent;
         if (var2 != null) {
            var2.setVisibility(0);
            ((ConstraintLayout.LayoutParams)this.mContent.getLayoutParams()).isInPlaceholder = false;
            this.mContent = null;
         }

         this.mContentId = var1;
         if (var1 != -1) {
            var2 = ((View)this.getParent()).findViewById(var1);
            if (var2 != null) {
               var2.setVisibility(8);
            }
         }

      }
   }

   public void setEmptyVisibility(int var1) {
      this.mEmptyVisibility = var1;
   }

   public void updatePostMeasure(ConstraintLayout var1) {
      if (this.mContent != null) {
         ConstraintLayout.LayoutParams var2 = (ConstraintLayout.LayoutParams)this.getLayoutParams();
         ConstraintLayout.LayoutParams var3 = (ConstraintLayout.LayoutParams)this.mContent.getLayoutParams();
         var3.widget.setVisibility(0);
         var2.widget.setWidth(var3.widget.getWidth());
         var2.widget.setHeight(var3.widget.getHeight());
         var3.widget.setVisibility(8);
      }
   }

   public void updatePreLayout(ConstraintLayout var1) {
      if (this.mContentId == -1 && !this.isInEditMode()) {
         this.setVisibility(this.mEmptyVisibility);
      }

      View var2 = var1.findViewById(this.mContentId);
      this.mContent = var2;
      if (var2 != null) {
         ((ConstraintLayout.LayoutParams)var2.getLayoutParams()).isInPlaceholder = true;
         this.mContent.setVisibility(0);
         this.setVisibility(0);
      }

   }
}
