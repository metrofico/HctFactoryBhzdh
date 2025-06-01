package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;

public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
   static final int GENERATED_ITEM_PADDING = 4;
   static final int MIN_CELL_SIZE = 56;
   private static final String TAG = "ActionMenuView";
   private MenuPresenter.Callback mActionMenuPresenterCallback;
   private boolean mFormatItems;
   private int mFormatItemsWidth;
   private int mGeneratedItemPadding;
   private MenuBuilder mMenu;
   MenuBuilder.Callback mMenuBuilderCallback;
   private int mMinCellSize;
   OnMenuItemClickListener mOnMenuItemClickListener;
   private Context mPopupContext;
   private int mPopupTheme;
   private ActionMenuPresenter mPresenter;
   private boolean mReserveOverflow;

   public ActionMenuView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public ActionMenuView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.setBaselineAligned(false);
      float var3 = var1.getResources().getDisplayMetrics().density;
      this.mMinCellSize = (int)(56.0F * var3);
      this.mGeneratedItemPadding = (int)(var3 * 4.0F);
      this.mPopupContext = var1;
      this.mPopupTheme = 0;
   }

   static int measureChildForCells(View var0, int var1, int var2, int var3, int var4) {
      LayoutParams var10 = (LayoutParams)var0.getLayoutParams();
      int var6 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(var3) - var4, MeasureSpec.getMode(var3));
      ActionMenuItemView var9;
      if (var0 instanceof ActionMenuItemView) {
         var9 = (ActionMenuItemView)var0;
      } else {
         var9 = null;
      }

      boolean var8 = true;
      boolean var11;
      if (var9 != null && var9.hasText()) {
         var11 = true;
      } else {
         var11 = false;
      }

      byte var12 = 2;
      if (var2 > 0 && (!var11 || var2 >= 2)) {
         var0.measure(MeasureSpec.makeMeasureSpec(var2 * var1, Integer.MIN_VALUE), var6);
         int var7 = var0.getMeasuredWidth();
         int var5 = var7 / var1;
         var2 = var5;
         if (var7 % var1 != 0) {
            var2 = var5 + 1;
         }

         if (var11 && var2 < 2) {
            var2 = var12;
         }
      } else {
         var2 = 0;
      }

      if (var10.isOverflowButton || !var11) {
         var8 = false;
      }

      var10.expandable = var8;
      var10.cellsUsed = var2;
      var0.measure(MeasureSpec.makeMeasureSpec(var1 * var2, 1073741824), var6);
      return var2;
   }

   private void onMeasureExactFormat(int var1, int var2) {
      int var12 = MeasureSpec.getMode(var2);
      int var5 = MeasureSpec.getSize(var1);
      int var6 = MeasureSpec.getSize(var2);
      var1 = this.getPaddingLeft();
      int var7 = this.getPaddingRight();
      int var15 = this.getPaddingTop() + this.getPaddingBottom();
      int var19 = getChildMeasureSpec(var2, var15, -2);
      int var14 = var5 - (var1 + var7);
      var1 = this.mMinCellSize;
      int var13 = var14 / var1;
      if (var13 == 0) {
         this.setMeasuredDimension(var14, 0);
      } else {
         int var21 = var1 + var14 % var1 / var13;
         int var20 = this.getChildCount();
         var5 = 0;
         int var9 = 0;
         var7 = var9;
         int var8 = var9;
         long var23 = 0L;
         int var10 = var9;
         int var11 = var9;

         View var31;
         LayoutParams var32;
         for(var1 = var13; var9 < var20; var8 = var2) {
            var31 = this.getChildAt(var9);
            if (var31.getVisibility() == 8) {
               var2 = var8;
            } else {
               boolean var22 = var31 instanceof ActionMenuItemView;
               ++var11;
               if (var22) {
                  var2 = this.mGeneratedItemPadding;
                  var31.setPadding(var2, 0, var2, 0);
               }

               var32 = (LayoutParams)var31.getLayoutParams();
               var32.expanded = false;
               var32.extraPixels = 0;
               var32.cellsUsed = 0;
               var32.expandable = false;
               var32.leftMargin = 0;
               var32.rightMargin = 0;
               if (var22 && ((ActionMenuItemView)var31).hasText()) {
                  var22 = true;
               } else {
                  var22 = false;
               }

               var32.preventEdgeOffset = var22;
               if (var32.isOverflowButton) {
                  var2 = 1;
               } else {
                  var2 = var1;
               }

               var13 = measureChildForCells(var31, var21, var2, var19, var15);
               var10 = Math.max(var10, var13);
               var2 = var8;
               if (var32.expandable) {
                  var2 = var8 + 1;
               }

               if (var32.isOverflowButton) {
                  var7 = 1;
               }

               var1 -= var13;
               var5 = Math.max(var5, var31.getMeasuredHeight());
               if (var13 == 1) {
                  var23 |= (long)(1 << var9);
               }
            }

            ++var9;
         }

         boolean var36;
         if (var7 != 0 && var11 == 2) {
            var36 = true;
         } else {
            var36 = false;
         }

         boolean var34 = false;
         var13 = var1;
         boolean var37 = var36;

         boolean var33;
         LayoutParams var38;
         while(true) {
            if (var8 <= 0 || var13 <= 0) {
               var33 = var34;
               var2 = var5;
               break;
            }

            var14 = Integer.MAX_VALUE;
            int var17 = 0;
            int var16 = 0;

            long var25;
            long var27;
            for(var27 = 0L; var16 < var20; var27 = var25) {
               var38 = (LayoutParams)this.getChildAt(var16).getLayoutParams();
               int var18;
               if (!var38.expandable) {
                  var1 = var17;
                  var18 = var14;
                  var25 = var27;
               } else if (var38.cellsUsed < var14) {
                  var18 = var38.cellsUsed;
                  var25 = 1L << var16;
                  var1 = 1;
               } else {
                  var1 = var17;
                  var18 = var14;
                  var25 = var27;
                  if (var38.cellsUsed == var14) {
                     var1 = var17 + 1;
                     var25 = var27 | 1L << var16;
                     var18 = var14;
                  }
               }

               ++var16;
               var17 = var1;
               var14 = var18;
            }

            var33 = var34;
            var2 = var5;
            var23 |= var27;
            if (var17 > var13) {
               break;
            }

            for(var1 = 0; var1 < var20; ++var1) {
               var31 = this.getChildAt(var1);
               var32 = (LayoutParams)var31.getLayoutParams();
               long var29 = (long)(1 << var1);
               if ((var27 & var29) == 0L) {
                  var25 = var23;
                  if (var32.cellsUsed == var14 + 1) {
                     var25 = var23 | var29;
                  }

                  var23 = var25;
               } else {
                  if (var37 && var32.preventEdgeOffset && var13 == 1) {
                     var5 = this.mGeneratedItemPadding;
                     var31.setPadding(var5 + var21, 0, var5, 0);
                  }

                  ++var32.cellsUsed;
                  var32.expanded = true;
                  --var13;
               }
            }

            var5 = var5;
            var34 = true;
         }

         boolean var35;
         if (var7 == 0 && var11 == 1) {
            var35 = true;
         } else {
            var35 = false;
         }

         if (var13 <= 0 || var23 == 0L || var13 >= var11 - 1 && !var35 && var10 <= 1) {
            var35 = var33;
         } else {
            float var4 = (float)Long.bitCount(var23);
            if (!var35) {
               float var3 = var4;
               if ((var23 & 1L) != 0L) {
                  var3 = var4;
                  if (!((LayoutParams)this.getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                     var3 = var4 - 0.5F;
                  }
               }

               var5 = var20 - 1;
               var4 = var3;
               if ((var23 & (long)(1 << var5)) != 0L) {
                  var4 = var3;
                  if (!((LayoutParams)this.getChildAt(var5).getLayoutParams()).preventEdgeOffset) {
                     var4 = var3 - 0.5F;
                  }
               }
            }

            if (var4 > 0.0F) {
               var7 = (int)((float)(var13 * var21) / var4);
            } else {
               var7 = 0;
            }

            var8 = 0;

            while(true) {
               var35 = var33;
               if (var8 >= var20) {
                  break;
               }

               if ((var23 & (long)(1 << var8)) == 0L) {
                  var35 = var33;
               } else {
                  var31 = this.getChildAt(var8);
                  var32 = (LayoutParams)var31.getLayoutParams();
                  if (var31 instanceof ActionMenuItemView) {
                     var32.extraPixels = var7;
                     var32.expanded = true;
                     if (var8 == 0 && !var32.preventEdgeOffset) {
                        var32.leftMargin = -var7 / 2;
                     }

                     var35 = true;
                  } else if (var32.isOverflowButton) {
                     var32.extraPixels = var7;
                     var32.expanded = true;
                     var32.rightMargin = -var7 / 2;
                     var35 = true;
                  } else {
                     if (var8 != 0) {
                        var32.leftMargin = var7 / 2;
                     }

                     var35 = var33;
                     if (var8 != var20 - 1) {
                        var32.rightMargin = var7 / 2;
                        var35 = var33;
                     }
                  }
               }

               ++var8;
               var33 = var35;
            }
         }

         if (var35) {
            for(var1 = 0; var1 < var20; ++var1) {
               View var39 = this.getChildAt(var1);
               var38 = (LayoutParams)var39.getLayoutParams();
               if (var38.expanded) {
                  var39.measure(MeasureSpec.makeMeasureSpec(var38.cellsUsed * var21 + var38.extraPixels, 1073741824), var19);
               }
            }
         }

         if (var12 == 1073741824) {
            var2 = var6;
         }

         this.setMeasuredDimension(var14, var2);
      }
   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      return var1 instanceof LayoutParams;
   }

   public void dismissPopupMenus() {
      ActionMenuPresenter var1 = this.mPresenter;
      if (var1 != null) {
         var1.dismissPopupMenus();
      }

   }

   public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent var1) {
      return false;
   }

   protected LayoutParams generateDefaultLayoutParams() {
      LayoutParams var1 = new LayoutParams(-2, -2);
      var1.gravity = 16;
      return var1;
   }

   public LayoutParams generateLayoutParams(AttributeSet var1) {
      return new LayoutParams(this.getContext(), var1);
   }

   protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      if (var1 != null) {
         LayoutParams var2;
         if (var1 instanceof LayoutParams) {
            var2 = new LayoutParams((LayoutParams)var1);
         } else {
            var2 = new LayoutParams(var1);
         }

         if (var2.gravity <= 0) {
            var2.gravity = 16;
         }

         return var2;
      } else {
         return this.generateDefaultLayoutParams();
      }
   }

   public LayoutParams generateOverflowButtonLayoutParams() {
      LayoutParams var1 = this.generateDefaultLayoutParams();
      var1.isOverflowButton = true;
      return var1;
   }

   public Menu getMenu() {
      if (this.mMenu == null) {
         Context var2 = this.getContext();
         MenuBuilder var1 = new MenuBuilder(var2);
         this.mMenu = var1;
         var1.setCallback(new MenuBuilderCallback(this));
         ActionMenuPresenter var3 = new ActionMenuPresenter(var2);
         this.mPresenter = var3;
         var3.setReserveOverflow(true);
         ActionMenuPresenter var5 = this.mPresenter;
         Object var4 = this.mActionMenuPresenterCallback;
         if (var4 == null) {
            var4 = new ActionMenuPresenterCallback();
         }

         var5.setCallback((MenuPresenter.Callback)var4);
         this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
         this.mPresenter.setMenuView(this);
      }

      return this.mMenu;
   }

   public Drawable getOverflowIcon() {
      this.getMenu();
      return this.mPresenter.getOverflowIcon();
   }

   public int getPopupTheme() {
      return this.mPopupTheme;
   }

   public int getWindowAnimations() {
      return 0;
   }

   protected boolean hasSupportDividerBeforeChildAt(int var1) {
      boolean var3 = false;
      if (var1 == 0) {
         return false;
      } else {
         View var4 = this.getChildAt(var1 - 1);
         View var5 = this.getChildAt(var1);
         boolean var2 = var3;
         if (var1 < this.getChildCount()) {
            var2 = var3;
            if (var4 instanceof ActionMenuChildView) {
               var2 = false | ((ActionMenuChildView)var4).needsDividerAfter();
            }
         }

         var3 = var2;
         if (var1 > 0) {
            var3 = var2;
            if (var5 instanceof ActionMenuChildView) {
               var3 = var2 | ((ActionMenuChildView)var5).needsDividerBefore();
            }
         }

         return var3;
      }
   }

   public boolean hideOverflowMenu() {
      ActionMenuPresenter var2 = this.mPresenter;
      boolean var1;
      if (var2 != null && var2.hideOverflowMenu()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void initialize(MenuBuilder var1) {
      this.mMenu = var1;
   }

   public boolean invokeItem(MenuItemImpl var1) {
      return this.mMenu.performItemAction(var1, 0);
   }

   public boolean isOverflowMenuShowPending() {
      ActionMenuPresenter var2 = this.mPresenter;
      boolean var1;
      if (var2 != null && var2.isOverflowMenuShowPending()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isOverflowMenuShowing() {
      ActionMenuPresenter var2 = this.mPresenter;
      boolean var1;
      if (var2 != null && var2.isOverflowMenuShowing()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isOverflowReserved() {
      return this.mReserveOverflow;
   }

   public void onConfigurationChanged(Configuration var1) {
      super.onConfigurationChanged(var1);
      ActionMenuPresenter var2 = this.mPresenter;
      if (var2 != null) {
         var2.updateMenuView(false);
         if (this.mPresenter.isOverflowMenuShowing()) {
            this.mPresenter.hideOverflowMenu();
            this.mPresenter.showOverflowMenu();
         }
      }

   }

   public void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      this.dismissPopupMenus();
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      if (!this.mFormatItems) {
         super.onLayout(var1, var2, var3, var4, var5);
      } else {
         int var9 = this.getChildCount();
         int var8 = (var5 - var3) / 2;
         int var11 = this.getDividerWidth();
         int var10 = var4 - var2;
         var2 = var10 - this.getPaddingRight() - this.getPaddingLeft();
         var1 = ViewUtils.isLayoutRtl(this);
         var4 = 0;
         byte var16 = 0;

         int var6;
         int var7;
         LayoutParams var14;
         View var15;
         for(var3 = 0; var4 < var9; ++var4) {
            var15 = this.getChildAt(var4);
            if (var15.getVisibility() != 8) {
               var14 = (LayoutParams)var15.getLayoutParams();
               if (var14.isOverflowButton) {
                  var6 = var15.getMeasuredWidth();
                  var5 = var6;
                  if (this.hasSupportDividerBeforeChildAt(var4)) {
                     var5 = var6 + var11;
                  }

                  int var12 = var15.getMeasuredHeight();
                  if (var1) {
                     var7 = this.getPaddingLeft() + var14.leftMargin;
                     var6 = var7 + var5;
                  } else {
                     var6 = this.getWidth() - this.getPaddingRight() - var14.rightMargin;
                     var7 = var6 - var5;
                  }

                  int var13 = var8 - var12 / 2;
                  var15.layout(var7, var13, var6, var12 + var13);
                  var2 -= var5;
                  var16 = 1;
               } else {
                  var2 -= var15.getMeasuredWidth() + var14.leftMargin + var14.rightMargin;
                  this.hasSupportDividerBeforeChildAt(var4);
                  ++var3;
               }
            }
         }

         View var17;
         if (var9 == 1 && var16 == 0) {
            var17 = this.getChildAt(0);
            var2 = var17.getMeasuredWidth();
            var3 = var17.getMeasuredHeight();
            var4 = var10 / 2 - var2 / 2;
            var5 = var8 - var3 / 2;
            var17.layout(var4, var5, var2 + var4, var3 + var5);
         } else {
            var3 -= var16 ^ 1;
            if (var3 > 0) {
               var2 /= var3;
            } else {
               var2 = 0;
            }

            var5 = Math.max(0, var2);
            if (var1) {
               var3 = this.getWidth() - this.getPaddingRight();

               for(var2 = 0; var2 < var9; var3 = var4) {
                  var15 = this.getChildAt(var2);
                  var14 = (LayoutParams)var15.getLayoutParams();
                  var4 = var3;
                  if (var15.getVisibility() != 8) {
                     if (var14.isOverflowButton) {
                        var4 = var3;
                     } else {
                        var3 -= var14.rightMargin;
                        var7 = var15.getMeasuredWidth();
                        var4 = var15.getMeasuredHeight();
                        var6 = var8 - var4 / 2;
                        var15.layout(var3 - var7, var6, var3, var4 + var6);
                        var4 = var3 - (var7 + var14.leftMargin + var5);
                     }
                  }

                  ++var2;
               }
            } else {
               var3 = this.getPaddingLeft();

               for(var2 = 0; var2 < var9; var3 = var4) {
                  var17 = this.getChildAt(var2);
                  LayoutParams var18 = (LayoutParams)var17.getLayoutParams();
                  var4 = var3;
                  if (var17.getVisibility() != 8) {
                     if (var18.isOverflowButton) {
                        var4 = var3;
                     } else {
                        var6 = var3 + var18.leftMargin;
                        var3 = var17.getMeasuredWidth();
                        var4 = var17.getMeasuredHeight();
                        var7 = var8 - var4 / 2;
                        var17.layout(var6, var7, var6 + var3, var4 + var7);
                        var4 = var6 + var3 + var18.rightMargin + var5;
                     }
                  }

                  ++var2;
               }
            }

         }
      }
   }

   protected void onMeasure(int var1, int var2) {
      boolean var6 = this.mFormatItems;
      boolean var5;
      if (MeasureSpec.getMode(var1) == 1073741824) {
         var5 = true;
      } else {
         var5 = false;
      }

      this.mFormatItems = var5;
      if (var6 != var5) {
         this.mFormatItemsWidth = 0;
      }

      int var3 = MeasureSpec.getSize(var1);
      if (this.mFormatItems) {
         MenuBuilder var7 = this.mMenu;
         if (var7 != null && var3 != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = var3;
            var7.onItemsChanged(true);
         }
      }

      int var4 = this.getChildCount();
      if (this.mFormatItems && var4 > 0) {
         this.onMeasureExactFormat(var1, var2);
      } else {
         for(var3 = 0; var3 < var4; ++var3) {
            LayoutParams var8 = (LayoutParams)this.getChildAt(var3).getLayoutParams();
            var8.rightMargin = 0;
            var8.leftMargin = 0;
         }

         super.onMeasure(var1, var2);
      }

   }

   public MenuBuilder peekMenu() {
      return this.mMenu;
   }

   public void setExpandedActionViewsExclusive(boolean var1) {
      this.mPresenter.setExpandedActionViewsExclusive(var1);
   }

   public void setMenuCallbacks(MenuPresenter.Callback var1, MenuBuilder.Callback var2) {
      this.mActionMenuPresenterCallback = var1;
      this.mMenuBuilderCallback = var2;
   }

   public void setOnMenuItemClickListener(OnMenuItemClickListener var1) {
      this.mOnMenuItemClickListener = var1;
   }

   public void setOverflowIcon(Drawable var1) {
      this.getMenu();
      this.mPresenter.setOverflowIcon(var1);
   }

   public void setOverflowReserved(boolean var1) {
      this.mReserveOverflow = var1;
   }

   public void setPopupTheme(int var1) {
      if (this.mPopupTheme != var1) {
         this.mPopupTheme = var1;
         if (var1 == 0) {
            this.mPopupContext = this.getContext();
         } else {
            this.mPopupContext = new ContextThemeWrapper(this.getContext(), var1);
         }
      }

   }

   public void setPresenter(ActionMenuPresenter var1) {
      this.mPresenter = var1;
      var1.setMenuView(this);
   }

   public boolean showOverflowMenu() {
      ActionMenuPresenter var2 = this.mPresenter;
      boolean var1;
      if (var2 != null && var2.showOverflowMenu()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public interface ActionMenuChildView {
      boolean needsDividerAfter();

      boolean needsDividerBefore();
   }

   private static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
      ActionMenuPresenterCallback() {
      }

      public void onCloseMenu(MenuBuilder var1, boolean var2) {
      }

      public boolean onOpenSubMenu(MenuBuilder var1) {
         return false;
      }
   }

   public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
      @ExportedProperty
      public int cellsUsed;
      @ExportedProperty
      public boolean expandable;
      boolean expanded;
      @ExportedProperty
      public int extraPixels;
      @ExportedProperty
      public boolean isOverflowButton;
      @ExportedProperty
      public boolean preventEdgeOffset;

      public LayoutParams(int var1, int var2) {
         super(var1, var2);
         this.isOverflowButton = false;
      }

      LayoutParams(int var1, int var2, boolean var3) {
         super(var1, var2);
         this.isOverflowButton = var3;
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
      }

      public LayoutParams(ViewGroup.LayoutParams var1) {
         super(var1);
      }

      public LayoutParams(LayoutParams var1) {
         super((ViewGroup.LayoutParams)var1);
         this.isOverflowButton = var1.isOverflowButton;
      }
   }

   private class MenuBuilderCallback implements MenuBuilder.Callback {
      final ActionMenuView this$0;

      MenuBuilderCallback(ActionMenuView var1) {
         this.this$0 = var1;
      }

      public boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2) {
         boolean var3;
         if (this.this$0.mOnMenuItemClickListener != null && this.this$0.mOnMenuItemClickListener.onMenuItemClick(var2)) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      public void onMenuModeChange(MenuBuilder var1) {
         if (this.this$0.mMenuBuilderCallback != null) {
            this.this$0.mMenuBuilderCallback.onMenuModeChange(var1);
         }

      }
   }

   public interface OnMenuItemClickListener {
      boolean onMenuItemClick(MenuItem var1);
   }
}
