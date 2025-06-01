package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.ViewCompat;
import androidx.core.widget.PopupWindowCompat;
import java.lang.reflect.Method;

public class ListPopupWindow implements ShowableListMenu {
   private static final boolean DEBUG = false;
   static final int EXPAND_LIST_TIMEOUT = 250;
   public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
   public static final int INPUT_METHOD_NEEDED = 1;
   public static final int INPUT_METHOD_NOT_NEEDED = 2;
   public static final int MATCH_PARENT = -1;
   public static final int POSITION_PROMPT_ABOVE = 0;
   public static final int POSITION_PROMPT_BELOW = 1;
   private static final String TAG = "ListPopupWindow";
   public static final int WRAP_CONTENT = -2;
   private static Method sGetMaxAvailableHeightMethod;
   private static Method sSetClipToWindowEnabledMethod;
   private static Method sSetEpicenterBoundsMethod;
   private ListAdapter mAdapter;
   private Context mContext;
   private boolean mDropDownAlwaysVisible;
   private View mDropDownAnchorView;
   private int mDropDownGravity;
   private int mDropDownHeight;
   private int mDropDownHorizontalOffset;
   DropDownListView mDropDownList;
   private Drawable mDropDownListHighlight;
   private int mDropDownVerticalOffset;
   private boolean mDropDownVerticalOffsetSet;
   private int mDropDownWidth;
   private int mDropDownWindowLayoutType;
   private Rect mEpicenterBounds;
   private boolean mForceIgnoreOutsideTouch;
   final Handler mHandler;
   private final ListSelectorHider mHideSelector;
   private AdapterView.OnItemClickListener mItemClickListener;
   private AdapterView.OnItemSelectedListener mItemSelectedListener;
   int mListItemExpandMaximum;
   private boolean mModal;
   private DataSetObserver mObserver;
   private boolean mOverlapAnchor;
   private boolean mOverlapAnchorSet;
   PopupWindow mPopup;
   private int mPromptPosition;
   private View mPromptView;
   final ResizePopupRunnable mResizePopupRunnable;
   private final PopupScrollListener mScrollListener;
   private Runnable mShowDropDownRunnable;
   private final Rect mTempRect;
   private final PopupTouchInterceptor mTouchInterceptor;

   static {
      if (VERSION.SDK_INT <= 28) {
         try {
            sSetClipToWindowEnabledMethod = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
         } catch (NoSuchMethodException var3) {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
         }

         try {
            sSetEpicenterBoundsMethod = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
         } catch (NoSuchMethodException var2) {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
         }
      }

      if (VERSION.SDK_INT <= 23) {
         try {
            sGetMaxAvailableHeightMethod = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
         } catch (NoSuchMethodException var1) {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
         }
      }

   }

   public ListPopupWindow(Context var1) {
      this(var1, (AttributeSet)null, R.attr.listPopupWindowStyle);
   }

   public ListPopupWindow(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.listPopupWindowStyle);
   }

   public ListPopupWindow(Context var1, AttributeSet var2, int var3) {
      this(var1, var2, var3, 0);
   }

   public ListPopupWindow(Context var1, AttributeSet var2, int var3, int var4) {
      this.mDropDownHeight = -2;
      this.mDropDownWidth = -2;
      this.mDropDownWindowLayoutType = 1002;
      this.mDropDownGravity = 0;
      this.mDropDownAlwaysVisible = false;
      this.mForceIgnoreOutsideTouch = false;
      this.mListItemExpandMaximum = Integer.MAX_VALUE;
      this.mPromptPosition = 0;
      this.mResizePopupRunnable = new ResizePopupRunnable(this);
      this.mTouchInterceptor = new PopupTouchInterceptor(this);
      this.mScrollListener = new PopupScrollListener(this);
      this.mHideSelector = new ListSelectorHider(this);
      this.mTempRect = new Rect();
      this.mContext = var1;
      this.mHandler = new Handler(var1.getMainLooper());
      TypedArray var6 = var1.obtainStyledAttributes(var2, R.styleable.ListPopupWindow, var3, var4);
      this.mDropDownHorizontalOffset = var6.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
      int var5 = var6.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
      this.mDropDownVerticalOffset = var5;
      if (var5 != 0) {
         this.mDropDownVerticalOffsetSet = true;
      }

      var6.recycle();
      AppCompatPopupWindow var7 = new AppCompatPopupWindow(var1, var2, var3, var4);
      this.mPopup = var7;
      var7.setInputMethodMode(1);
   }

   private int buildDropDown() {
      DropDownListView var6 = this.mDropDownList;
      boolean var5 = true;
      int var1;
      int var2;
      LinearLayout.LayoutParams var13;
      if (var6 == null) {
         Context var7 = this.mContext;
         this.mShowDropDownRunnable = new Runnable(this) {
            final ListPopupWindow this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               View var1 = this.this$0.getAnchorView();
               if (var1 != null && var1.getWindowToken() != null) {
                  this.this$0.show();
               }

            }
         };
         var6 = this.createDropDownListView(var7, this.mModal ^ true);
         this.mDropDownList = var6;
         Drawable var8 = this.mDropDownListHighlight;
         if (var8 != null) {
            var6.setSelector(var8);
         }

         this.mDropDownList.setAdapter(this.mAdapter);
         this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
         this.mDropDownList.setFocusable(true);
         this.mDropDownList.setFocusableInTouchMode(true);
         this.mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) {
            final ListPopupWindow this$0;

            {
               this.this$0 = var1;
            }

            public void onItemSelected(AdapterView var1, View var2, int var3, long var4) {
               if (var3 != -1) {
                  DropDownListView var6 = this.this$0.mDropDownList;
                  if (var6 != null) {
                     var6.setListSelectionHidden(false);
                  }
               }

            }

            public void onNothingSelected(AdapterView var1) {
            }
         });
         this.mDropDownList.setOnScrollListener(this.mScrollListener);
         AdapterView.OnItemSelectedListener var10 = this.mItemSelectedListener;
         if (var10 != null) {
            this.mDropDownList.setOnItemSelectedListener(var10);
         }

         Object var11 = this.mDropDownList;
         View var16 = this.mPromptView;
         if (var16 != null) {
            LinearLayout var12 = new LinearLayout(var7);
            var12.setOrientation(1);
            LinearLayout.LayoutParams var9 = new LinearLayout.LayoutParams(-1, 0, 1.0F);
            var1 = this.mPromptPosition;
            if (var1 != 0) {
               if (var1 != 1) {
                  Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
               } else {
                  var12.addView((View)var11, var9);
                  var12.addView(var16);
               }
            } else {
               var12.addView(var16);
               var12.addView((View)var11, var9);
            }

            var2 = this.mDropDownWidth;
            if (var2 >= 0) {
               var1 = Integer.MIN_VALUE;
            } else {
               var2 = 0;
               var1 = 0;
            }

            var16.measure(MeasureSpec.makeMeasureSpec(var2, var1), 0);
            var13 = (LinearLayout.LayoutParams)var16.getLayoutParams();
            var1 = var16.getMeasuredHeight() + var13.topMargin + var13.bottomMargin;
            var11 = var12;
         } else {
            var1 = 0;
         }

         this.mPopup.setContentView((View)var11);
      } else {
         ViewGroup var15 = (ViewGroup)this.mPopup.getContentView();
         View var14 = this.mPromptView;
         if (var14 != null) {
            var13 = (LinearLayout.LayoutParams)var14.getLayoutParams();
            var1 = var14.getMeasuredHeight() + var13.topMargin + var13.bottomMargin;
         } else {
            var1 = 0;
         }
      }

      Drawable var17 = this.mPopup.getBackground();
      int var3;
      if (var17 != null) {
         var17.getPadding(this.mTempRect);
         var2 = this.mTempRect.top + this.mTempRect.bottom;
         var3 = var2;
         if (!this.mDropDownVerticalOffsetSet) {
            this.mDropDownVerticalOffset = -this.mTempRect.top;
            var3 = var2;
         }
      } else {
         this.mTempRect.setEmpty();
         var3 = 0;
      }

      if (this.mPopup.getInputMethodMode() != 2) {
         var5 = false;
      }

      int var4 = this.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset, var5);
      if (!this.mDropDownAlwaysVisible && this.mDropDownHeight != -1) {
         var2 = this.mDropDownWidth;
         int var10001;
         if (var2 != -2) {
            if (var2 != -1) {
               var2 = MeasureSpec.makeMeasureSpec(var2, 1073741824);
            } else {
               var10001 = this.mTempRect.left + this.mTempRect.right;
               var2 = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - var10001, 1073741824);
            }
         } else {
            var10001 = this.mTempRect.left + this.mTempRect.right;
            var2 = MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - var10001, Integer.MIN_VALUE);
         }

         var4 = this.mDropDownList.measureHeightOfChildrenCompat(var2, 0, -1, var4 - var1, -1);
         var2 = var1;
         if (var4 > 0) {
            var2 = var1 + var3 + this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom();
         }

         return var4 + var2;
      } else {
         return var4 + var3;
      }
   }

   private int getMaxAvailableHeight(View var1, int var2, boolean var3) {
      if (VERSION.SDK_INT > 23) {
         return this.mPopup.getMaxAvailableHeight(var1, var2, var3);
      } else {
         Method var5 = sGetMaxAvailableHeightMethod;
         if (var5 != null) {
            try {
               int var4 = (Integer)var5.invoke(this.mPopup, var1, var2, var3);
               return var4;
            } catch (Exception var6) {
               Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
         }

         return this.mPopup.getMaxAvailableHeight(var1, var2);
      }
   }

   private static boolean isConfirmKey(int var0) {
      boolean var1;
      if (var0 != 66 && var0 != 23) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private void removePromptView() {
      View var1 = this.mPromptView;
      if (var1 != null) {
         ViewParent var2 = var1.getParent();
         if (var2 instanceof ViewGroup) {
            ((ViewGroup)var2).removeView(this.mPromptView);
         }
      }

   }

   private void setPopupClipToScreenEnabled(boolean var1) {
      if (VERSION.SDK_INT <= 28) {
         Method var2 = sSetClipToWindowEnabledMethod;
         if (var2 != null) {
            try {
               var2.invoke(this.mPopup, var1);
            } catch (Exception var3) {
               Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
         }
      } else {
         this.mPopup.setIsClippedToScreen(var1);
      }

   }

   public void clearListSelection() {
      DropDownListView var1 = this.mDropDownList;
      if (var1 != null) {
         var1.setListSelectionHidden(true);
         var1.requestLayout();
      }

   }

   public View.OnTouchListener createDragToOpenListener(View var1) {
      return new ForwardingListener(this, var1) {
         final ListPopupWindow this$0;

         {
            this.this$0 = var1;
         }

         public ListPopupWindow getPopup() {
            return this.this$0;
         }
      };
   }

   DropDownListView createDropDownListView(Context var1, boolean var2) {
      return new DropDownListView(var1, var2);
   }

   public void dismiss() {
      this.mPopup.dismiss();
      this.removePromptView();
      this.mPopup.setContentView((View)null);
      this.mDropDownList = null;
      this.mHandler.removeCallbacks(this.mResizePopupRunnable);
   }

   public View getAnchorView() {
      return this.mDropDownAnchorView;
   }

   public int getAnimationStyle() {
      return this.mPopup.getAnimationStyle();
   }

   public Drawable getBackground() {
      return this.mPopup.getBackground();
   }

   public Rect getEpicenterBounds() {
      Rect var1;
      if (this.mEpicenterBounds != null) {
         var1 = new Rect(this.mEpicenterBounds);
      } else {
         var1 = null;
      }

      return var1;
   }

   public int getHeight() {
      return this.mDropDownHeight;
   }

   public int getHorizontalOffset() {
      return this.mDropDownHorizontalOffset;
   }

   public int getInputMethodMode() {
      return this.mPopup.getInputMethodMode();
   }

   public ListView getListView() {
      return this.mDropDownList;
   }

   public int getPromptPosition() {
      return this.mPromptPosition;
   }

   public Object getSelectedItem() {
      return !this.isShowing() ? null : this.mDropDownList.getSelectedItem();
   }

   public long getSelectedItemId() {
      return !this.isShowing() ? Long.MIN_VALUE : this.mDropDownList.getSelectedItemId();
   }

   public int getSelectedItemPosition() {
      return !this.isShowing() ? -1 : this.mDropDownList.getSelectedItemPosition();
   }

   public View getSelectedView() {
      return !this.isShowing() ? null : this.mDropDownList.getSelectedView();
   }

   public int getSoftInputMode() {
      return this.mPopup.getSoftInputMode();
   }

   public int getVerticalOffset() {
      return !this.mDropDownVerticalOffsetSet ? 0 : this.mDropDownVerticalOffset;
   }

   public int getWidth() {
      return this.mDropDownWidth;
   }

   public boolean isDropDownAlwaysVisible() {
      return this.mDropDownAlwaysVisible;
   }

   public boolean isInputMethodNotNeeded() {
      boolean var1;
      if (this.mPopup.getInputMethodMode() == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isModal() {
      return this.mModal;
   }

   public boolean isShowing() {
      return this.mPopup.isShowing();
   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      if (this.isShowing() && var1 != 62 && (this.mDropDownList.getSelectedItemPosition() >= 0 || !isConfirmKey(var1))) {
         int var6 = this.mDropDownList.getSelectedItemPosition();
         boolean var5 = this.mPopup.isAboveAnchor() ^ true;
         ListAdapter var8 = this.mAdapter;
         int var3 = Integer.MAX_VALUE;
         int var4 = Integer.MIN_VALUE;
         if (var8 != null) {
            boolean var7 = var8.areAllItemsEnabled();
            if (var7) {
               var3 = 0;
            } else {
               var3 = this.mDropDownList.lookForSelectablePosition(0, true);
            }

            if (var7) {
               var4 = var8.getCount() - 1;
            } else {
               var4 = this.mDropDownList.lookForSelectablePosition(var8.getCount() - 1, false);
            }
         }

         if (var5 && var1 == 19 && var6 <= var3 || !var5 && var1 == 20 && var6 >= var4) {
            this.clearListSelection();
            this.mPopup.setInputMethodMode(1);
            this.show();
            return true;
         }

         this.mDropDownList.setListSelectionHidden(false);
         if (this.mDropDownList.onKeyDown(var1, var2)) {
            this.mPopup.setInputMethodMode(2);
            this.mDropDownList.requestFocusFromTouch();
            this.show();
            if (var1 == 19 || var1 == 20 || var1 == 23 || var1 == 66) {
               return true;
            }
         } else if (var5 && var1 == 20) {
            if (var6 == var4) {
               return true;
            }
         } else if (!var5 && var1 == 19 && var6 == var3) {
            return true;
         }
      }

      return false;
   }

   public boolean onKeyPreIme(int var1, KeyEvent var2) {
      if (var1 == 4 && this.isShowing()) {
         View var3 = this.mDropDownAnchorView;
         KeyEvent.DispatcherState var4;
         if (var2.getAction() == 0 && var2.getRepeatCount() == 0) {
            var4 = var3.getKeyDispatcherState();
            if (var4 != null) {
               var4.startTracking(var2, this);
            }

            return true;
         }

         if (var2.getAction() == 1) {
            var4 = var3.getKeyDispatcherState();
            if (var4 != null) {
               var4.handleUpEvent(var2);
            }

            if (var2.isTracking() && !var2.isCanceled()) {
               this.dismiss();
               return true;
            }
         }
      }

      return false;
   }

   public boolean onKeyUp(int var1, KeyEvent var2) {
      if (this.isShowing() && this.mDropDownList.getSelectedItemPosition() >= 0) {
         boolean var3 = this.mDropDownList.onKeyUp(var1, var2);
         if (var3 && isConfirmKey(var1)) {
            this.dismiss();
         }

         return var3;
      } else {
         return false;
      }
   }

   public boolean performItemClick(int var1) {
      if (this.isShowing()) {
         if (this.mItemClickListener != null) {
            DropDownListView var4 = this.mDropDownList;
            View var2 = var4.getChildAt(var1 - var4.getFirstVisiblePosition());
            ListAdapter var3 = var4.getAdapter();
            this.mItemClickListener.onItemClick(var4, var2, var1, var3.getItemId(var1));
         }

         return true;
      } else {
         return false;
      }
   }

   public void postShow() {
      this.mHandler.post(this.mShowDropDownRunnable);
   }

   public void setAdapter(ListAdapter var1) {
      DataSetObserver var2 = this.mObserver;
      if (var2 == null) {
         this.mObserver = new PopupDataSetObserver(this);
      } else {
         ListAdapter var3 = this.mAdapter;
         if (var3 != null) {
            var3.unregisterDataSetObserver(var2);
         }
      }

      this.mAdapter = var1;
      if (var1 != null) {
         var1.registerDataSetObserver(this.mObserver);
      }

      DropDownListView var4 = this.mDropDownList;
      if (var4 != null) {
         var4.setAdapter(this.mAdapter);
      }

   }

   public void setAnchorView(View var1) {
      this.mDropDownAnchorView = var1;
   }

   public void setAnimationStyle(int var1) {
      this.mPopup.setAnimationStyle(var1);
   }

   public void setBackgroundDrawable(Drawable var1) {
      this.mPopup.setBackgroundDrawable(var1);
   }

   public void setContentWidth(int var1) {
      Drawable var2 = this.mPopup.getBackground();
      if (var2 != null) {
         var2.getPadding(this.mTempRect);
         this.mDropDownWidth = this.mTempRect.left + this.mTempRect.right + var1;
      } else {
         this.setWidth(var1);
      }

   }

   public void setDropDownAlwaysVisible(boolean var1) {
      this.mDropDownAlwaysVisible = var1;
   }

   public void setDropDownGravity(int var1) {
      this.mDropDownGravity = var1;
   }

   public void setEpicenterBounds(Rect var1) {
      if (var1 != null) {
         var1 = new Rect(var1);
      } else {
         var1 = null;
      }

      this.mEpicenterBounds = var1;
   }

   public void setForceIgnoreOutsideTouch(boolean var1) {
      this.mForceIgnoreOutsideTouch = var1;
   }

   public void setHeight(int var1) {
      if (var1 < 0 && -2 != var1 && -1 != var1) {
         throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
      } else {
         this.mDropDownHeight = var1;
      }
   }

   public void setHorizontalOffset(int var1) {
      this.mDropDownHorizontalOffset = var1;
   }

   public void setInputMethodMode(int var1) {
      this.mPopup.setInputMethodMode(var1);
   }

   void setListItemExpandMax(int var1) {
      this.mListItemExpandMaximum = var1;
   }

   public void setListSelector(Drawable var1) {
      this.mDropDownListHighlight = var1;
   }

   public void setModal(boolean var1) {
      this.mModal = var1;
      this.mPopup.setFocusable(var1);
   }

   public void setOnDismissListener(PopupWindow.OnDismissListener var1) {
      this.mPopup.setOnDismissListener(var1);
   }

   public void setOnItemClickListener(AdapterView.OnItemClickListener var1) {
      this.mItemClickListener = var1;
   }

   public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener var1) {
      this.mItemSelectedListener = var1;
   }

   public void setOverlapAnchor(boolean var1) {
      this.mOverlapAnchorSet = true;
      this.mOverlapAnchor = var1;
   }

   public void setPromptPosition(int var1) {
      this.mPromptPosition = var1;
   }

   public void setPromptView(View var1) {
      boolean var2 = this.isShowing();
      if (var2) {
         this.removePromptView();
      }

      this.mPromptView = var1;
      if (var2) {
         this.show();
      }

   }

   public void setSelection(int var1) {
      DropDownListView var2 = this.mDropDownList;
      if (this.isShowing() && var2 != null) {
         var2.setListSelectionHidden(false);
         var2.setSelection(var1);
         if (var2.getChoiceMode() != 0) {
            var2.setItemChecked(var1, true);
         }
      }

   }

   public void setSoftInputMode(int var1) {
      this.mPopup.setSoftInputMode(var1);
   }

   public void setVerticalOffset(int var1) {
      this.mDropDownVerticalOffset = var1;
      this.mDropDownVerticalOffsetSet = true;
   }

   public void setWidth(int var1) {
      this.mDropDownWidth = var1;
   }

   public void setWindowLayoutType(int var1) {
      this.mDropDownWindowLayoutType = var1;
   }

   public void show() {
      int var2 = this.buildDropDown();
      boolean var6 = this.isInputMethodNotNeeded();
      PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
      boolean var7 = this.mPopup.isShowing();
      boolean var5 = true;
      int var1;
      int var3;
      PopupWindow var8;
      if (var7) {
         if (!ViewCompat.isAttachedToWindow(this.getAnchorView())) {
            return;
         }

         var3 = this.mDropDownWidth;
         if (var3 == -1) {
            var1 = -1;
         } else {
            var1 = var3;
            if (var3 == -2) {
               var1 = this.getAnchorView().getWidth();
            }
         }

         var3 = this.mDropDownHeight;
         if (var3 == -1) {
            if (!var6) {
               var2 = -1;
            }

            byte var11;
            if (var6) {
               var8 = this.mPopup;
               if (this.mDropDownWidth == -1) {
                  var11 = -1;
               } else {
                  var11 = 0;
               }

               var8.setWidth(var11);
               this.mPopup.setHeight(0);
            } else {
               var8 = this.mPopup;
               if (this.mDropDownWidth == -1) {
                  var11 = -1;
               } else {
                  var11 = 0;
               }

               var8.setWidth(var11);
               this.mPopup.setHeight(-1);
            }
         } else if (var3 != -2) {
            var2 = var3;
         }

         var8 = this.mPopup;
         if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
            var5 = false;
         }

         var8.setOutsideTouchable(var5);
         PopupWindow var9 = this.mPopup;
         View var12 = this.getAnchorView();
         var3 = this.mDropDownHorizontalOffset;
         int var4 = this.mDropDownVerticalOffset;
         if (var1 < 0) {
            var1 = -1;
         }

         if (var2 < 0) {
            var2 = -1;
         }

         var9.update(var12, var3, var4, var1, var2);
      } else {
         var3 = this.mDropDownWidth;
         if (var3 == -1) {
            var1 = -1;
         } else {
            var1 = var3;
            if (var3 == -2) {
               var1 = this.getAnchorView().getWidth();
            }
         }

         var3 = this.mDropDownHeight;
         if (var3 == -1) {
            var2 = -1;
         } else if (var3 != -2) {
            var2 = var3;
         }

         this.mPopup.setWidth(var1);
         this.mPopup.setHeight(var2);
         this.setPopupClipToScreenEnabled(true);
         var8 = this.mPopup;
         if (!this.mForceIgnoreOutsideTouch && !this.mDropDownAlwaysVisible) {
            var5 = true;
         } else {
            var5 = false;
         }

         var8.setOutsideTouchable(var5);
         this.mPopup.setTouchInterceptor(this.mTouchInterceptor);
         if (this.mOverlapAnchorSet) {
            PopupWindowCompat.setOverlapAnchor(this.mPopup, this.mOverlapAnchor);
         }

         if (VERSION.SDK_INT <= 28) {
            Method var13 = sSetEpicenterBoundsMethod;
            if (var13 != null) {
               try {
                  var13.invoke(this.mPopup, this.mEpicenterBounds);
               } catch (Exception var10) {
                  Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", var10);
               }
            }
         } else {
            this.mPopup.setEpicenterBounds(this.mEpicenterBounds);
         }

         PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
         this.mDropDownList.setSelection(-1);
         if (!this.mModal || this.mDropDownList.isInTouchMode()) {
            this.clearListSelection();
         }

         if (!this.mModal) {
            this.mHandler.post(this.mHideSelector);
         }
      }

   }

   private class ListSelectorHider implements Runnable {
      final ListPopupWindow this$0;

      ListSelectorHider(ListPopupWindow var1) {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.clearListSelection();
      }
   }

   private class PopupDataSetObserver extends DataSetObserver {
      final ListPopupWindow this$0;

      PopupDataSetObserver(ListPopupWindow var1) {
         this.this$0 = var1;
      }

      public void onChanged() {
         if (this.this$0.isShowing()) {
            this.this$0.show();
         }

      }

      public void onInvalidated() {
         this.this$0.dismiss();
      }
   }

   private class PopupScrollListener implements AbsListView.OnScrollListener {
      final ListPopupWindow this$0;

      PopupScrollListener(ListPopupWindow var1) {
         this.this$0 = var1;
      }

      public void onScroll(AbsListView var1, int var2, int var3, int var4) {
      }

      public void onScrollStateChanged(AbsListView var1, int var2) {
         if (var2 == 1 && !this.this$0.isInputMethodNotNeeded() && this.this$0.mPopup.getContentView() != null) {
            this.this$0.mHandler.removeCallbacks(this.this$0.mResizePopupRunnable);
            this.this$0.mResizePopupRunnable.run();
         }

      }
   }

   private class PopupTouchInterceptor implements View.OnTouchListener {
      final ListPopupWindow this$0;

      PopupTouchInterceptor(ListPopupWindow var1) {
         this.this$0 = var1;
      }

      public boolean onTouch(View var1, MotionEvent var2) {
         int var5 = var2.getAction();
         int var3 = (int)var2.getX();
         int var4 = (int)var2.getY();
         if (var5 == 0 && this.this$0.mPopup != null && this.this$0.mPopup.isShowing() && var3 >= 0 && var3 < this.this$0.mPopup.getWidth() && var4 >= 0 && var4 < this.this$0.mPopup.getHeight()) {
            this.this$0.mHandler.postDelayed(this.this$0.mResizePopupRunnable, 250L);
         } else if (var5 == 1) {
            this.this$0.mHandler.removeCallbacks(this.this$0.mResizePopupRunnable);
         }

         return false;
      }
   }

   private class ResizePopupRunnable implements Runnable {
      final ListPopupWindow this$0;

      ResizePopupRunnable(ListPopupWindow var1) {
         this.this$0 = var1;
      }

      public void run() {
         if (this.this$0.mDropDownList != null && ViewCompat.isAttachedToWindow(this.this$0.mDropDownList) && this.this$0.mDropDownList.getCount() > this.this$0.mDropDownList.getChildCount() && this.this$0.mDropDownList.getChildCount() <= this.this$0.mListItemExpandMaximum) {
            this.this$0.mPopup.setInputMethodMode(2);
            this.this$0.show();
         }

      }
   }
}
