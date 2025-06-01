package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.R;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
   private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
   public static final int HORIZONTAL = 0;
   private static final int INDEX_BOTTOM = 2;
   private static final int INDEX_CENTER_VERTICAL = 0;
   private static final int INDEX_FILL = 3;
   private static final int INDEX_TOP = 1;
   public static final int SHOW_DIVIDER_BEGINNING = 1;
   public static final int SHOW_DIVIDER_END = 4;
   public static final int SHOW_DIVIDER_MIDDLE = 2;
   public static final int SHOW_DIVIDER_NONE = 0;
   public static final int VERTICAL = 1;
   private static final int VERTICAL_GRAVITY_COUNT = 4;
   private boolean mBaselineAligned;
   private int mBaselineAlignedChildIndex;
   private int mBaselineChildTop;
   private Drawable mDivider;
   private int mDividerHeight;
   private int mDividerPadding;
   private int mDividerWidth;
   private int mGravity;
   private int[] mMaxAscent;
   private int[] mMaxDescent;
   private int mOrientation;
   private int mShowDividers;
   private int mTotalLength;
   private boolean mUseLargestChild;
   private float mWeightSum;

   public LinearLayoutCompat(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public LinearLayoutCompat(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public LinearLayoutCompat(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mBaselineAligned = true;
      this.mBaselineAlignedChildIndex = -1;
      this.mBaselineChildTop = 0;
      this.mGravity = 8388659;
      TintTypedArray var5 = TintTypedArray.obtainStyledAttributes(var1, var2, R.styleable.LinearLayoutCompat, var3, 0);
      ViewCompat.saveAttributeDataForStyleable(this, var1, R.styleable.LinearLayoutCompat, var2, var5.getWrappedTypeArray(), var3, 0);
      var3 = var5.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
      if (var3 >= 0) {
         this.setOrientation(var3);
      }

      var3 = var5.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
      if (var3 >= 0) {
         this.setGravity(var3);
      }

      boolean var4 = var5.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
      if (!var4) {
         this.setBaselineAligned(var4);
      }

      this.mWeightSum = var5.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
      this.mBaselineAlignedChildIndex = var5.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
      this.mUseLargestChild = var5.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
      this.setDividerDrawable(var5.getDrawable(R.styleable.LinearLayoutCompat_divider));
      this.mShowDividers = var5.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
      this.mDividerPadding = var5.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
      var5.recycle();
   }

   private void forceUniformHeight(int var1, int var2) {
      int var4 = MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824);

      for(int var3 = 0; var3 < var1; ++var3) {
         View var6 = this.getVirtualChildAt(var3);
         if (var6.getVisibility() != 8) {
            LayoutParams var7 = (LayoutParams)var6.getLayoutParams();
            if (var7.height == -1) {
               int var5 = var7.width;
               var7.width = var6.getMeasuredWidth();
               this.measureChildWithMargins(var6, var2, 0, var4, 0);
               var7.width = var5;
            }
         }
      }

   }

   private void forceUniformWidth(int var1, int var2) {
      int var4 = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824);

      for(int var3 = 0; var3 < var1; ++var3) {
         View var7 = this.getVirtualChildAt(var3);
         if (var7.getVisibility() != 8) {
            LayoutParams var6 = (LayoutParams)var7.getLayoutParams();
            if (var6.width == -1) {
               int var5 = var6.height;
               var6.height = var7.getMeasuredHeight();
               this.measureChildWithMargins(var7, var4, 0, var2, 0);
               var6.height = var5;
            }
         }
      }

   }

   private void setChildFrame(View var1, int var2, int var3, int var4, int var5) {
      var1.layout(var2, var3, var4 + var2, var5 + var3);
   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      return var1 instanceof LayoutParams;
   }

   void drawDividersHorizontal(Canvas var1) {
      int var4 = this.getVirtualChildCount();
      boolean var5 = ViewUtils.isLayoutRtl(this);

      int var2;
      int var3;
      View var6;
      LayoutParams var7;
      for(var2 = 0; var2 < var4; ++var2) {
         var6 = this.getVirtualChildAt(var2);
         if (var6 != null && var6.getVisibility() != 8 && this.hasDividerBeforeChildAt(var2)) {
            var7 = (LayoutParams)var6.getLayoutParams();
            if (var5) {
               var3 = var6.getRight() + var7.rightMargin;
            } else {
               var3 = var6.getLeft() - var7.leftMargin - this.mDividerWidth;
            }

            this.drawVerticalDivider(var1, var3);
         }
      }

      if (this.hasDividerBeforeChildAt(var4)) {
         label37: {
            var6 = this.getVirtualChildAt(var4 - 1);
            if (var6 == null) {
               if (var5) {
                  var2 = this.getPaddingLeft();
                  break label37;
               }

               var2 = this.getWidth() - this.getPaddingRight();
               var3 = this.mDividerWidth;
            } else {
               var7 = (LayoutParams)var6.getLayoutParams();
               if (!var5) {
                  var2 = var6.getRight() + var7.rightMargin;
                  break label37;
               }

               var2 = var6.getLeft() - var7.leftMargin;
               var3 = this.mDividerWidth;
            }

            var2 -= var3;
         }

         this.drawVerticalDivider(var1, var2);
      }

   }

   void drawDividersVertical(Canvas var1) {
      int var3 = this.getVirtualChildCount();

      int var2;
      LayoutParams var4;
      View var5;
      for(var2 = 0; var2 < var3; ++var2) {
         var5 = this.getVirtualChildAt(var2);
         if (var5 != null && var5.getVisibility() != 8 && this.hasDividerBeforeChildAt(var2)) {
            var4 = (LayoutParams)var5.getLayoutParams();
            this.drawHorizontalDivider(var1, var5.getTop() - var4.topMargin - this.mDividerHeight);
         }
      }

      if (this.hasDividerBeforeChildAt(var3)) {
         var5 = this.getVirtualChildAt(var3 - 1);
         if (var5 == null) {
            var2 = this.getHeight() - this.getPaddingBottom() - this.mDividerHeight;
         } else {
            var4 = (LayoutParams)var5.getLayoutParams();
            var2 = var5.getBottom() + var4.bottomMargin;
         }

         this.drawHorizontalDivider(var1, var2);
      }

   }

   void drawHorizontalDivider(Canvas var1, int var2) {
      this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, var2, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, this.mDividerHeight + var2);
      this.mDivider.draw(var1);
   }

   void drawVerticalDivider(Canvas var1, int var2) {
      this.mDivider.setBounds(var2, this.getPaddingTop() + this.mDividerPadding, this.mDividerWidth + var2, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
      this.mDivider.draw(var1);
   }

   protected LayoutParams generateDefaultLayoutParams() {
      int var1 = this.mOrientation;
      if (var1 == 0) {
         return new LayoutParams(-2, -2);
      } else {
         return var1 == 1 ? new LayoutParams(-1, -2) : null;
      }
   }

   public LayoutParams generateLayoutParams(AttributeSet var1) {
      return new LayoutParams(this.getContext(), var1);
   }

   protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      return new LayoutParams(var1);
   }

   public int getBaseline() {
      if (this.mBaselineAlignedChildIndex < 0) {
         return super.getBaseline();
      } else {
         int var2 = this.getChildCount();
         int var1 = this.mBaselineAlignedChildIndex;
         if (var2 > var1) {
            View var5 = this.getChildAt(var1);
            int var3 = var5.getBaseline();
            if (var3 == -1) {
               if (this.mBaselineAlignedChildIndex == 0) {
                  return -1;
               } else {
                  throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
               }
            } else {
               var2 = this.mBaselineChildTop;
               var1 = var2;
               if (this.mOrientation == 1) {
                  int var4 = this.mGravity & 112;
                  var1 = var2;
                  if (var4 != 48) {
                     if (var4 != 16) {
                        if (var4 != 80) {
                           var1 = var2;
                        } else {
                           var1 = this.getBottom() - this.getTop() - this.getPaddingBottom() - this.mTotalLength;
                        }
                     } else {
                        var1 = var2 + (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.mTotalLength) / 2;
                     }
                  }
               }

               return var1 + ((LayoutParams)var5.getLayoutParams()).topMargin + var3;
            }
         } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
         }
      }
   }

   public int getBaselineAlignedChildIndex() {
      return this.mBaselineAlignedChildIndex;
   }

   int getChildrenSkipCount(View var1, int var2) {
      return 0;
   }

   public Drawable getDividerDrawable() {
      return this.mDivider;
   }

   public int getDividerPadding() {
      return this.mDividerPadding;
   }

   public int getDividerWidth() {
      return this.mDividerWidth;
   }

   public int getGravity() {
      return this.mGravity;
   }

   int getLocationOffset(View var1) {
      return 0;
   }

   int getNextLocationOffset(View var1) {
      return 0;
   }

   public int getOrientation() {
      return this.mOrientation;
   }

   public int getShowDividers() {
      return this.mShowDividers;
   }

   View getVirtualChildAt(int var1) {
      return this.getChildAt(var1);
   }

   int getVirtualChildCount() {
      return this.getChildCount();
   }

   public float getWeightSum() {
      return this.mWeightSum;
   }

   protected boolean hasDividerBeforeChildAt(int var1) {
      boolean var2 = false;
      boolean var3 = false;
      boolean var4 = false;
      if (var1 == 0) {
         var2 = var4;
         if ((this.mShowDividers & 1) != 0) {
            var2 = true;
         }

         return var2;
      } else if (var1 == this.getChildCount()) {
         if ((this.mShowDividers & 4) != 0) {
            var2 = true;
         }

         return var2;
      } else {
         var2 = var3;
         if ((this.mShowDividers & 2) != 0) {
            --var1;

            while(true) {
               var2 = var3;
               if (var1 < 0) {
                  break;
               }

               if (this.getChildAt(var1).getVisibility() != 8) {
                  var2 = true;
                  break;
               }

               --var1;
            }
         }

         return var2;
      }
   }

   public boolean isBaselineAligned() {
      return this.mBaselineAligned;
   }

   public boolean isMeasureWithLargestChildEnabled() {
      return this.mUseLargestChild;
   }

   void layoutHorizontal(int var1, int var2, int var3, int var4) {
      boolean var20 = ViewUtils.isLayoutRtl(this);
      int var10 = this.getPaddingTop();
      int var12 = var4 - var2;
      int var13 = this.getPaddingBottom();
      int var14 = this.getPaddingBottom();
      int var8 = this.getVirtualChildCount();
      var2 = this.mGravity;
      var4 = var2 & 112;
      boolean var19 = this.mBaselineAligned;
      int[] var24 = this.mMaxAscent;
      int[] var23 = this.mMaxDescent;
      var2 = GravityCompat.getAbsoluteGravity(8388615 & var2, ViewCompat.getLayoutDirection(this));
      boolean var18 = true;
      if (var2 != 1) {
         if (var2 != 5) {
            var2 = this.getPaddingLeft();
         } else {
            var2 = this.getPaddingLeft() + var3 - var1 - this.mTotalLength;
         }
      } else {
         var2 = this.getPaddingLeft() + (var3 - var1 - this.mTotalLength) / 2;
      }

      byte var6;
      int var7;
      if (var20) {
         var7 = var8 - 1;
         var6 = -1;
      } else {
         var7 = 0;
         var6 = 1;
      }

      int var5 = 0;
      var3 = var4;

      for(var4 = var10; var5 < var8; ++var5) {
         int var16 = var7 + var6 * var5;
         View var21 = this.getVirtualChildAt(var16);
         if (var21 == null) {
            var2 += this.measureNullChild(var16);
         } else if (var21.getVisibility() == 8) {
            var18 = true;
         } else {
            int var15 = var21.getMeasuredWidth();
            int var17 = var21.getMeasuredHeight();
            LayoutParams var22 = (LayoutParams)var21.getLayoutParams();
            int var9;
            if (var19 && var22.height != -1) {
               var9 = var21.getBaseline();
            } else {
               var9 = -1;
            }

            int var11 = var22.gravity;
            var1 = var11;
            if (var11 < 0) {
               var1 = var3;
            }

            var1 &= 112;
            if (var1 != 16) {
               if (var1 != 48) {
                  if (var1 != 80) {
                     var1 = var4;
                  } else {
                     var11 = var12 - var13 - var17 - var22.bottomMargin;
                     var1 = var11;
                     if (var9 != -1) {
                        var1 = var21.getMeasuredHeight();
                        var1 = var11 - (var23[2] - (var1 - var9));
                     }
                  }
               } else {
                  var11 = var22.topMargin + var4;
                  var1 = var11;
                  if (var9 != -1) {
                     var1 = var11 + (var24[1] - var9);
                  }
               }
            } else {
               var1 = (var12 - var10 - var14 - var17) / 2 + var4 + var22.topMargin - var22.bottomMargin;
            }

            var18 = true;
            var9 = var2;
            if (this.hasDividerBeforeChildAt(var16)) {
               var9 = var2 + this.mDividerWidth;
            }

            var2 = var22.leftMargin + var9;
            this.setChildFrame(var21, var2 + this.getLocationOffset(var21), var1, var15, var17);
            var9 = var22.rightMargin;
            var1 = this.getNextLocationOffset(var21);
            var5 += this.getChildrenSkipCount(var21, var16);
            var2 += var15 + var9 + var1;
         }
      }

   }

   void layoutVertical(int var1, int var2, int var3, int var4) {
      int var5 = this.getPaddingLeft();
      int var10 = var3 - var1;
      int var7 = this.getPaddingRight();
      int var8 = this.getPaddingRight();
      int var9 = this.getVirtualChildCount();
      int var6 = this.mGravity;
      var1 = var6 & 112;
      if (var1 != 16) {
         if (var1 != 80) {
            var1 = this.getPaddingTop();
         } else {
            var1 = this.getPaddingTop() + var4 - var2 - this.mTotalLength;
         }
      } else {
         var1 = this.getPaddingTop() + (var4 - var2 - this.mTotalLength) / 2;
      }

      for(var2 = 0; var2 < var9; ++var2) {
         View var13 = this.getVirtualChildAt(var2);
         if (var13 == null) {
            var3 = var1 + this.measureNullChild(var2);
         } else {
            var3 = var1;
            if (var13.getVisibility() != 8) {
               int var12 = var13.getMeasuredWidth();
               int var11 = var13.getMeasuredHeight();
               LayoutParams var14 = (LayoutParams)var13.getLayoutParams();
               var4 = var14.gravity;
               var3 = var4;
               if (var4 < 0) {
                  var3 = var6 & 8388615;
               }

               label40: {
                  var3 = GravityCompat.getAbsoluteGravity(var3, ViewCompat.getLayoutDirection(this)) & 7;
                  if (var3 != 1) {
                     if (var3 != 5) {
                        var3 = var14.leftMargin + var5;
                        break label40;
                     }

                     var3 = var10 - var7 - var12;
                     var4 = var14.rightMargin;
                  } else {
                     var3 = (var10 - var5 - var8 - var12) / 2 + var5 + var14.leftMargin;
                     var4 = var14.rightMargin;
                  }

                  var3 -= var4;
               }

               var4 = var1;
               if (this.hasDividerBeforeChildAt(var2)) {
                  var4 = var1 + this.mDividerHeight;
               }

               var1 = var4 + var14.topMargin;
               this.setChildFrame(var13, var3, var1 + this.getLocationOffset(var13), var12, var11);
               var3 = var14.bottomMargin;
               var4 = this.getNextLocationOffset(var13);
               var2 += this.getChildrenSkipCount(var13, var2);
               var1 += var11 + var3 + var4;
               continue;
            }
         }

         var1 = var3;
      }

   }

   void measureChildBeforeLayout(View var1, int var2, int var3, int var4, int var5, int var6) {
      this.measureChildWithMargins(var1, var3, var4, var5, var6);
   }

   void measureHorizontal(int var1, int var2) {
      this.mTotalLength = 0;
      int var16 = this.getVirtualChildCount();
      int var23 = MeasureSpec.getMode(var1);
      int var22 = MeasureSpec.getMode(var2);
      if (this.mMaxAscent == null || this.mMaxDescent == null) {
         this.mMaxAscent = new int[4];
         this.mMaxDescent = new int[4];
      }

      int[] var27 = this.mMaxAscent;
      int[] var26 = this.mMaxDescent;
      var27[3] = -1;
      var27[2] = -1;
      var27[1] = -1;
      var27[0] = -1;
      var26[3] = -1;
      var26[2] = -1;
      var26[1] = -1;
      var26[0] = -1;
      boolean var24 = this.mBaselineAligned;
      boolean var25 = this.mUseLargestChild;
      int var14 = 1073741824;
      boolean var15;
      if (var23 == 1073741824) {
         var15 = true;
      } else {
         var15 = false;
      }

      int var8 = 0;
      int var7 = 0;
      int var9 = var7;
      int var6 = var7;
      int var11 = var7;
      int var12 = var7;
      int var13 = var7;
      int var10 = var7;
      boolean var5 = true;

      float var3;
      int var17;
      int var18;
      LayoutParams var28;
      View var29;
      for(var3 = 0.0F; var8 < var16; var14 = var17) {
         label323: {
            var29 = this.getVirtualChildAt(var8);
            if (var29 == null) {
               this.mTotalLength += this.measureNullChild(var8);
            } else {
               if (var29.getVisibility() != 8) {
                  if (this.hasDividerBeforeChildAt(var8)) {
                     this.mTotalLength += this.mDividerWidth;
                  }

                  label360: {
                     var28 = (LayoutParams)var29.getLayoutParams();
                     var3 += var28.weight;
                     if (var23 == var14 && var28.width == 0 && var28.weight > 0.0F) {
                        if (var15) {
                           this.mTotalLength += var28.leftMargin + var28.rightMargin;
                        } else {
                           var14 = this.mTotalLength;
                           this.mTotalLength = Math.max(var14, var28.leftMargin + var14 + var28.rightMargin);
                        }

                        if (!var24) {
                           var12 = 1;
                           break label360;
                        }

                        var14 = MeasureSpec.makeMeasureSpec(0, 0);
                        var29.measure(var14, var14);
                        var14 = var7;
                     } else {
                        if (var28.width == 0 && var28.weight > 0.0F) {
                           var28.width = -2;
                           var14 = 0;
                        } else {
                           var14 = Integer.MIN_VALUE;
                        }

                        if (var3 == 0.0F) {
                           var17 = this.mTotalLength;
                        } else {
                           var17 = 0;
                        }

                        this.measureChildBeforeLayout(var29, var8, var1, var17, var2, 0);
                        if (var14 != Integer.MIN_VALUE) {
                           var28.width = var14;
                        }

                        var17 = var29.getMeasuredWidth();
                        if (var15) {
                           this.mTotalLength += var28.leftMargin + var17 + var28.rightMargin + this.getNextLocationOffset(var29);
                        } else {
                           var14 = this.mTotalLength;
                           this.mTotalLength = Math.max(var14, var14 + var17 + var28.leftMargin + var28.rightMargin + this.getNextLocationOffset(var29));
                        }

                        var14 = var7;
                        if (var25) {
                           var14 = Math.max(var17, var7);
                        }
                     }

                     var7 = var14;
                  }

                  int var19 = 1073741824;
                  boolean var33;
                  if (var22 != 1073741824 && var28.height == -1) {
                     var33 = true;
                     var10 = 1;
                  } else {
                     var33 = false;
                  }

                  var18 = var28.topMargin + var28.bottomMargin;
                  var17 = var29.getMeasuredHeight() + var18;
                  int var20 = View.combineMeasuredStates(var13, var29.getMeasuredState());
                  int var21;
                  if (var24) {
                     var21 = var29.getBaseline();
                     if (var21 != -1) {
                        if (var28.gravity < 0) {
                           var13 = this.mGravity;
                        } else {
                           var13 = var28.gravity;
                        }

                        var13 = ((var13 & 112) >> 4 & -2) >> 1;
                        var27[var13] = Math.max(var27[var13], var21);
                        var26[var13] = Math.max(var26[var13], var17 - var21);
                     }
                  }

                  var21 = Math.max(var9, var17);
                  if (var5 && var28.height == -1) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  if (var28.weight > 0.0F) {
                     if (!var33) {
                        var18 = var17;
                     }

                     var9 = Math.max(var11, var18);
                  } else {
                     if (!var33) {
                        var18 = var17;
                     }

                     var6 = Math.max(var6, var18);
                     var9 = var11;
                  }

                  var14 = this.getChildrenSkipCount(var29, var8) + var8;
                  var13 = var20;
                  var11 = var9;
                  var9 = var21;
                  var8 = var19;
                  break label323;
               }

               var8 += this.getChildrenSkipCount(var29, var8);
            }

            var17 = var8;
            var8 = var14;
            var14 = var17;
         }

         var17 = var8;
         var8 = var14 + 1;
      }

      if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(var16)) {
         this.mTotalLength += this.mDividerWidth;
      }

      var8 = var27[1];
      if (var8 != -1 || var27[0] != -1 || var27[2] != -1 || var27[3] != -1) {
         var9 = Math.max(var9, Math.max(var27[3], Math.max(var27[0], Math.max(var8, var27[2]))) + Math.max(var26[3], Math.max(var26[0], Math.max(var26[1], var26[2]))));
      }

      var14 = var9;
      if (var25) {
         label336: {
            if (var23 != Integer.MIN_VALUE) {
               var14 = var9;
               if (var23 != 0) {
                  break label336;
               }
            }

            this.mTotalLength = 0;
            var8 = 0;

            while(true) {
               var14 = var9;
               if (var8 >= var16) {
                  break;
               }

               var29 = this.getVirtualChildAt(var8);
               if (var29 == null) {
                  this.mTotalLength += this.measureNullChild(var8);
               } else if (var29.getVisibility() == 8) {
                  var8 += this.getChildrenSkipCount(var29, var8);
               } else {
                  var28 = (LayoutParams)var29.getLayoutParams();
                  if (var15) {
                     this.mTotalLength += var28.leftMargin + var7 + var28.rightMargin + this.getNextLocationOffset(var29);
                  } else {
                     var14 = this.mTotalLength;
                     this.mTotalLength = Math.max(var14, var14 + var7 + var28.leftMargin + var28.rightMargin + this.getNextLocationOffset(var29));
                  }
               }

               ++var8;
            }
         }
      }

      var8 = this.mTotalLength + this.getPaddingLeft() + this.getPaddingRight();
      this.mTotalLength = var8;
      var18 = View.resolveSizeAndState(Math.max(var8, this.getSuggestedMinimumWidth()), var1, 0);
      var17 = (16777215 & var18) - this.mTotalLength;
      if (var12 != 0 || var17 != 0 && var3 > 0.0F) {
         float var4 = this.mWeightSum;
         if (var4 > 0.0F) {
            var3 = var4;
         }

         var27[3] = -1;
         var27[2] = -1;
         var27[1] = -1;
         var27[0] = -1;
         var26[3] = -1;
         var26[2] = -1;
         var26[1] = -1;
         var26[0] = -1;
         this.mTotalLength = 0;
         var11 = -1;
         var9 = var13;
         var13 = 0;
         boolean var31 = var5;
         var7 = var16;
         int var30 = var9;
         var9 = var6;

         for(var6 = var17; var13 < var7; ++var13) {
            View var35 = this.getVirtualChildAt(var13);
            if (var35 != null && var35.getVisibility() != 8) {
               LayoutParams var36 = (LayoutParams)var35.getLayoutParams();
               var4 = var36.weight;
               if (var4 > 0.0F) {
                  var14 = (int)((float)var6 * var4 / var3);
                  var17 = getChildMeasureSpec(var2, this.getPaddingTop() + this.getPaddingBottom() + var36.topMargin + var36.bottomMargin, var36.height);
                  if (var36.width == 0 && var23 == 1073741824) {
                     if (var14 > 0) {
                        var12 = var14;
                     } else {
                        var12 = 0;
                     }

                     var35.measure(MeasureSpec.makeMeasureSpec(var12, 1073741824), var17);
                  } else {
                     var16 = var35.getMeasuredWidth() + var14;
                     var12 = var16;
                     if (var16 < 0) {
                        var12 = 0;
                     }

                     var35.measure(MeasureSpec.makeMeasureSpec(var12, 1073741824), var17);
                  }

                  var30 = View.combineMeasuredStates(var30, var35.getMeasuredState() & -16777216);
                  var3 -= var4;
                  var6 -= var14;
               }

               if (var15) {
                  this.mTotalLength += var35.getMeasuredWidth() + var36.leftMargin + var36.rightMargin + this.getNextLocationOffset(var35);
               } else {
                  var12 = this.mTotalLength;
                  this.mTotalLength = Math.max(var12, var35.getMeasuredWidth() + var12 + var36.leftMargin + var36.rightMargin + this.getNextLocationOffset(var35));
               }

               boolean var32;
               if (var22 != 1073741824 && var36.height == -1) {
                  var32 = true;
               } else {
                  var32 = false;
               }

               var17 = var36.topMargin + var36.bottomMargin;
               var16 = var35.getMeasuredHeight() + var17;
               var14 = Math.max(var11, var16);
               if (var32) {
                  var11 = var17;
               } else {
                  var11 = var16;
               }

               var11 = Math.max(var9, var11);
               if (var31 && var36.height == -1) {
                  var31 = true;
               } else {
                  var31 = false;
               }

               if (var24) {
                  var12 = var35.getBaseline();
                  if (var12 != -1) {
                     if (var36.gravity < 0) {
                        var9 = this.mGravity;
                     } else {
                        var9 = var36.gravity;
                     }

                     var9 = ((var9 & 112) >> 4 & -2) >> 1;
                     var27[var9] = Math.max(var27[var9], var12);
                     var26[var9] = Math.max(var26[var9], var16 - var12);
                  }
               }

               var9 = var11;
               var11 = var14;
            }
         }

         this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
         var6 = var27[1];
         if (var6 == -1 && var27[0] == -1 && var27[2] == -1 && var27[3] == -1) {
            var6 = var11;
         } else {
            var6 = Math.max(var11, Math.max(var27[3], Math.max(var27[0], Math.max(var6, var27[2]))) + Math.max(var26[3], Math.max(var26[0], Math.max(var26[1], var26[2]))));
         }

         var11 = var6;
         var6 = var9;
         var13 = var30;
         var5 = var31;
         var8 = var7;
         var7 = var11;
      } else {
         var9 = Math.max(var6, var11);
         if (var25 && var23 != 1073741824) {
            for(var6 = 0; var6 < var16; ++var6) {
               View var34 = this.getVirtualChildAt(var6);
               if (var34 != null && var34.getVisibility() != 8 && ((LayoutParams)var34.getLayoutParams()).weight > 0.0F) {
                  var34.measure(MeasureSpec.makeMeasureSpec(var7, 1073741824), MeasureSpec.makeMeasureSpec(var34.getMeasuredHeight(), 1073741824));
               }
            }
         }

         var8 = var16;
         var7 = var14;
         var6 = var9;
      }

      if (var5 || var22 == 1073741824) {
         var6 = var7;
      }

      this.setMeasuredDimension(var18 | var13 & -16777216, View.resolveSizeAndState(Math.max(var6 + this.getPaddingTop() + this.getPaddingBottom(), this.getSuggestedMinimumHeight()), var2, var13 << 16));
      if (var10 != 0) {
         this.forceUniformHeight(var8, var1);
      }

   }

   int measureNullChild(int var1) {
      return 0;
   }

   void measureVertical(int var1, int var2) {
      this.mTotalLength = 0;
      int var14 = this.getVirtualChildCount();
      int var20 = MeasureSpec.getMode(var1);
      int var15 = MeasureSpec.getMode(var2);
      int var21 = this.mBaselineAlignedChildIndex;
      boolean var23 = this.mUseLargestChild;
      int var6 = 0;
      int var13 = 0;
      int var9 = var13;
      int var5 = var13;
      int var8 = var13;
      int var11 = var13;
      int var12 = var13;
      int var10 = var13;
      float var3 = 0.0F;

      boolean var7;
      int var16;
      int var17;
      int var18;
      int var19;
      for(var7 = true; var11 < var14; ++var11) {
         View var25 = this.getVirtualChildAt(var11);
         if (var25 == null) {
            this.mTotalLength += this.measureNullChild(var11);
         } else if (var25.getVisibility() == 8) {
            var11 += this.getChildrenSkipCount(var25, var11);
         } else {
            if (this.hasDividerBeforeChildAt(var11)) {
               this.mTotalLength += this.mDividerHeight;
            }

            LayoutParams var24 = (LayoutParams)var25.getLayoutParams();
            var3 += var24.weight;
            if (var15 == 1073741824 && var24.height == 0 && var24.weight > 0.0F) {
               var12 = this.mTotalLength;
               this.mTotalLength = Math.max(var12, var24.topMargin + var12 + var24.bottomMargin);
               var12 = 1;
            } else {
               if (var24.height == 0 && var24.weight > 0.0F) {
                  var24.height = -2;
                  var16 = 0;
               } else {
                  var16 = Integer.MIN_VALUE;
               }

               if (var3 == 0.0F) {
                  var17 = this.mTotalLength;
               } else {
                  var17 = 0;
               }

               this.measureChildBeforeLayout(var25, var11, var1, 0, var2, var17);
               if (var16 != Integer.MIN_VALUE) {
                  var24.height = var16;
               }

               var16 = var25.getMeasuredHeight();
               var17 = this.mTotalLength;
               this.mTotalLength = Math.max(var17, var17 + var16 + var24.topMargin + var24.bottomMargin + this.getNextLocationOffset(var25));
               if (var23) {
                  var9 = Math.max(var16, var9);
               }
            }

            if (var21 >= 0 && var21 == var11 + 1) {
               this.mBaselineChildTop = this.mTotalLength;
            }

            if (var11 < var21 && var24.weight > 0.0F) {
               throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
            }

            boolean var29;
            if (var20 != 1073741824 && var24.width == -1) {
               var29 = true;
               var10 = 1;
            } else {
               var29 = false;
            }

            var18 = var24.leftMargin + var24.rightMargin;
            var17 = var25.getMeasuredWidth() + var18;
            var13 = Math.max(var13, var17);
            var19 = View.combineMeasuredStates(var6, var25.getMeasuredState());
            boolean var26;
            if (var7 && var24.width == -1) {
               var26 = true;
            } else {
               var26 = false;
            }

            int var27;
            if (var24.weight > 0.0F) {
               if (var29) {
                  var17 = var18;
               }

               var5 = Math.max(var5, var17);
               var27 = var8;
            } else {
               if (var29) {
                  var17 = var18;
               }

               var27 = Math.max(var8, var17);
            }

            var17 = this.getChildrenSkipCount(var25, var11);
            var8 = var27;
            var11 += var17;
            var7 = var26;
            var6 = var19;
         }
      }

      if (this.mTotalLength > 0 && this.hasDividerBeforeChildAt(var14)) {
         this.mTotalLength += this.mDividerHeight;
      }

      View var30;
      LayoutParams var31;
      if (var23 && (var15 == Integer.MIN_VALUE || var15 == 0)) {
         this.mTotalLength = 0;

         for(var11 = 0; var11 < var14; ++var11) {
            var30 = this.getVirtualChildAt(var11);
            if (var30 == null) {
               this.mTotalLength += this.measureNullChild(var11);
            } else if (var30.getVisibility() == 8) {
               var11 += this.getChildrenSkipCount(var30, var11);
            } else {
               var31 = (LayoutParams)var30.getLayoutParams();
               var16 = this.mTotalLength;
               this.mTotalLength = Math.max(var16, var16 + var9 + var31.topMargin + var31.bottomMargin + this.getNextLocationOffset(var30));
            }
         }
      }

      var11 = this.mTotalLength + this.getPaddingTop() + this.getPaddingBottom();
      this.mTotalLength = var11;
      var17 = View.resolveSizeAndState(Math.max(var11, this.getSuggestedMinimumHeight()), var2, 0);
      var11 = (16777215 & var17) - this.mTotalLength;
      if (var12 != 0 || var11 != 0 && var3 > 0.0F) {
         float var4 = this.mWeightSum;
         if (var4 > 0.0F) {
            var3 = var4;
         }

         this.mTotalLength = 0;
         var9 = var11;
         var5 = var6;
         var11 = 0;
         var6 = var9;

         for(var9 = var13; var11 < var14; ++var11) {
            var30 = this.getVirtualChildAt(var11);
            if (var30.getVisibility() != 8) {
               var31 = (LayoutParams)var30.getLayoutParams();
               var4 = var31.weight;
               if (var4 > 0.0F) {
                  var13 = (int)((float)var6 * var4 / var3);
                  var21 = this.getPaddingLeft();
                  var18 = this.getPaddingRight();
                  var16 = var31.leftMargin;
                  var19 = var31.rightMargin;
                  int var22 = var31.width;
                  var12 = var6 - var13;
                  var16 = getChildMeasureSpec(var1, var21 + var18 + var16 + var19, var22);
                  if (var31.height == 0 && var15 == 1073741824) {
                     if (var13 > 0) {
                        var6 = var13;
                     } else {
                        var6 = 0;
                     }

                     var30.measure(var16, MeasureSpec.makeMeasureSpec(var6, 1073741824));
                  } else {
                     var13 += var30.getMeasuredHeight();
                     var6 = var13;
                     if (var13 < 0) {
                        var6 = 0;
                     }

                     var30.measure(var16, MeasureSpec.makeMeasureSpec(var6, 1073741824));
                  }

                  var5 = View.combineMeasuredStates(var5, var30.getMeasuredState() & -256);
                  var3 -= var4;
                  var6 = var12;
               }

               var16 = var31.leftMargin + var31.rightMargin;
               var13 = var30.getMeasuredWidth() + var16;
               var12 = Math.max(var9, var13);
               boolean var28;
               if (var20 != 1073741824 && var31.width == -1) {
                  var28 = true;
               } else {
                  var28 = false;
               }

               if (var28) {
                  var9 = var16;
               } else {
                  var9 = var13;
               }

               var8 = Math.max(var8, var9);
               if (var7 && var31.width == -1) {
                  var7 = true;
               } else {
                  var7 = false;
               }

               var9 = this.mTotalLength;
               this.mTotalLength = Math.max(var9, var30.getMeasuredHeight() + var9 + var31.topMargin + var31.bottomMargin + this.getNextLocationOffset(var30));
               var9 = var12;
            }
         }

         this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
         var6 = var5;
         var5 = var8;
      } else {
         var8 = Math.max(var8, var5);
         if (var23 && var15 != 1073741824) {
            for(var5 = 0; var5 < var14; ++var5) {
               var30 = this.getVirtualChildAt(var5);
               if (var30 != null && var30.getVisibility() != 8 && ((LayoutParams)var30.getLayoutParams()).weight > 0.0F) {
                  var30.measure(MeasureSpec.makeMeasureSpec(var30.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(var9, 1073741824));
               }
            }
         }

         var5 = var8;
         var9 = var13;
      }

      if (var7 || var20 == 1073741824) {
         var5 = var9;
      }

      this.setMeasuredDimension(View.resolveSizeAndState(Math.max(var5 + this.getPaddingLeft() + this.getPaddingRight(), this.getSuggestedMinimumWidth()), var1, var6), var17);
      if (var10 != 0) {
         this.forceUniformWidth(var14, var2);
      }

   }

   protected void onDraw(Canvas var1) {
      if (this.mDivider != null) {
         if (this.mOrientation == 1) {
            this.drawDividersVertical(var1);
         } else {
            this.drawDividersHorizontal(var1);
         }

      }
   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent var1) {
      super.onInitializeAccessibilityEvent(var1);
      var1.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
   }

   public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo var1) {
      super.onInitializeAccessibilityNodeInfo(var1);
      var1.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      if (this.mOrientation == 1) {
         this.layoutVertical(var2, var3, var4, var5);
      } else {
         this.layoutHorizontal(var2, var3, var4, var5);
      }

   }

   protected void onMeasure(int var1, int var2) {
      if (this.mOrientation == 1) {
         this.measureVertical(var1, var2);
      } else {
         this.measureHorizontal(var1, var2);
      }

   }

   public void setBaselineAligned(boolean var1) {
      this.mBaselineAligned = var1;
   }

   public void setBaselineAlignedChildIndex(int var1) {
      if (var1 >= 0 && var1 < this.getChildCount()) {
         this.mBaselineAlignedChildIndex = var1;
      } else {
         throw new IllegalArgumentException("base aligned child index out of range (0, " + this.getChildCount() + ")");
      }
   }

   public void setDividerDrawable(Drawable var1) {
      if (var1 != this.mDivider) {
         this.mDivider = var1;
         boolean var2 = false;
         if (var1 != null) {
            this.mDividerWidth = var1.getIntrinsicWidth();
            this.mDividerHeight = var1.getIntrinsicHeight();
         } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
         }

         if (var1 == null) {
            var2 = true;
         }

         this.setWillNotDraw(var2);
         this.requestLayout();
      }
   }

   public void setDividerPadding(int var1) {
      this.mDividerPadding = var1;
   }

   public void setGravity(int var1) {
      if (this.mGravity != var1) {
         int var2 = var1;
         if ((8388615 & var1) == 0) {
            var2 = var1 | 8388611;
         }

         var1 = var2;
         if ((var2 & 112) == 0) {
            var1 = var2 | 48;
         }

         this.mGravity = var1;
         this.requestLayout();
      }

   }

   public void setHorizontalGravity(int var1) {
      var1 &= 8388615;
      int var2 = this.mGravity;
      if ((8388615 & var2) != var1) {
         this.mGravity = var1 | -8388616 & var2;
         this.requestLayout();
      }

   }

   public void setMeasureWithLargestChildEnabled(boolean var1) {
      this.mUseLargestChild = var1;
   }

   public void setOrientation(int var1) {
      if (this.mOrientation != var1) {
         this.mOrientation = var1;
         this.requestLayout();
      }

   }

   public void setShowDividers(int var1) {
      if (var1 != this.mShowDividers) {
         this.requestLayout();
      }

      this.mShowDividers = var1;
   }

   public void setVerticalGravity(int var1) {
      var1 &= 112;
      int var2 = this.mGravity;
      if ((var2 & 112) != var1) {
         this.mGravity = var1 | var2 & -113;
         this.requestLayout();
      }

   }

   public void setWeightSum(float var1) {
      this.mWeightSum = Math.max(0.0F, var1);
   }

   public boolean shouldDelayChildPressedState() {
      return false;
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface DividerMode {
   }

   public static class LayoutParams extends LinearLayout.LayoutParams {
      public LayoutParams(int var1, int var2) {
         super(var1, var2);
      }

      public LayoutParams(int var1, int var2, float var3) {
         super(var1, var2, var3);
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
      }

      public LayoutParams(ViewGroup.LayoutParams var1) {
         super(var1);
      }

      public LayoutParams(ViewGroup.MarginLayoutParams var1) {
         super(var1);
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface OrientationMode {
   }
}
