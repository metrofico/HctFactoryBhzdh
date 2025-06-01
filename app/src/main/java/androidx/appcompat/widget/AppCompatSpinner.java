package androidx.appcompat.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.MeasureSpec;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
   private static final int[] ATTRS_ANDROID_SPINNERMODE = new int[]{16843505};
   private static final int MAX_ITEMS_MEASURED = 15;
   private static final int MODE_DIALOG = 0;
   private static final int MODE_DROPDOWN = 1;
   private static final int MODE_THEME = -1;
   private static final String TAG = "AppCompatSpinner";
   private final AppCompatBackgroundHelper mBackgroundTintHelper;
   int mDropDownWidth;
   private ForwardingListener mForwardingListener;
   private SpinnerPopup mPopup;
   private final Context mPopupContext;
   private final boolean mPopupSet;
   private SpinnerAdapter mTempAdapter;
   final Rect mTempRect;

   public AppCompatSpinner(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public AppCompatSpinner(Context var1, int var2) {
      this(var1, (AttributeSet)null, R.attr.spinnerStyle, var2);
   }

   public AppCompatSpinner(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.spinnerStyle);
   }

   public AppCompatSpinner(Context var1, AttributeSet var2, int var3) {
      this(var1, var2, var3, -1);
   }

   public AppCompatSpinner(Context var1, AttributeSet var2, int var3, int var4) {
      this(var1, var2, var3, var4, (Resources.Theme)null);
   }

   public AppCompatSpinner(Context param1, AttributeSet param2, int param3, int param4, Resources.Theme param5) {
      // $FF: Couldn't be decompiled
   }

   int compatMeasureContentWidth(SpinnerAdapter var1, Drawable var2) {
      int var6 = 0;
      if (var1 == null) {
         return 0;
      } else {
         int var10 = MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
         int var8 = MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
         int var3 = Math.max(0, this.getSelectedItemPosition());
         int var9 = Math.min(var1.getCount(), var3 + 15);
         int var4 = Math.max(0, var3 - (15 - (var9 - var3)));
         View var11 = null;

         int var5;
         for(var3 = 0; var4 < var9; var6 = var5) {
            int var7 = var1.getItemViewType(var4);
            var5 = var6;
            if (var7 != var6) {
               var11 = null;
               var5 = var7;
            }

            var11 = var1.getView(var4, var11, this);
            if (var11.getLayoutParams() == null) {
               var11.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }

            var11.measure(var10, var8);
            var3 = Math.max(var3, var11.getMeasuredWidth());
            ++var4;
         }

         var4 = var3;
         if (var2 != null) {
            var2.getPadding(this.mTempRect);
            var4 = var3 + this.mTempRect.left + this.mTempRect.right;
         }

         return var4;
      }
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      AppCompatBackgroundHelper var1 = this.mBackgroundTintHelper;
      if (var1 != null) {
         var1.applySupportBackgroundTint();
      }

   }

   public int getDropDownHorizontalOffset() {
      SpinnerPopup var1 = this.mPopup;
      if (var1 != null) {
         return var1.getHorizontalOffset();
      } else {
         return VERSION.SDK_INT >= 16 ? super.getDropDownHorizontalOffset() : 0;
      }
   }

   public int getDropDownVerticalOffset() {
      SpinnerPopup var1 = this.mPopup;
      if (var1 != null) {
         return var1.getVerticalOffset();
      } else {
         return VERSION.SDK_INT >= 16 ? super.getDropDownVerticalOffset() : 0;
      }
   }

   public int getDropDownWidth() {
      if (this.mPopup != null) {
         return this.mDropDownWidth;
      } else {
         return VERSION.SDK_INT >= 16 ? super.getDropDownWidth() : 0;
      }
   }

   final SpinnerPopup getInternalPopup() {
      return this.mPopup;
   }

   public Drawable getPopupBackground() {
      SpinnerPopup var1 = this.mPopup;
      if (var1 != null) {
         return var1.getBackground();
      } else {
         return VERSION.SDK_INT >= 16 ? super.getPopupBackground() : null;
      }
   }

   public Context getPopupContext() {
      return this.mPopupContext;
   }

   public CharSequence getPrompt() {
      SpinnerPopup var1 = this.mPopup;
      CharSequence var2;
      if (var1 != null) {
         var2 = var1.getHintText();
      } else {
         var2 = super.getPrompt();
      }

      return var2;
   }

   public ColorStateList getSupportBackgroundTintList() {
      AppCompatBackgroundHelper var1 = this.mBackgroundTintHelper;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getSupportBackgroundTintList();
      } else {
         var2 = null;
      }

      return var2;
   }

   public PorterDuff.Mode getSupportBackgroundTintMode() {
      AppCompatBackgroundHelper var1 = this.mBackgroundTintHelper;
      PorterDuff.Mode var2;
      if (var1 != null) {
         var2 = var1.getSupportBackgroundTintMode();
      } else {
         var2 = null;
      }

      return var2;
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      SpinnerPopup var1 = this.mPopup;
      if (var1 != null && var1.isShowing()) {
         this.mPopup.dismiss();
      }

   }

   protected void onMeasure(int var1, int var2) {
      super.onMeasure(var1, var2);
      if (this.mPopup != null && MeasureSpec.getMode(var1) == Integer.MIN_VALUE) {
         this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.compatMeasureContentWidth(this.getAdapter(), this.getBackground())), MeasureSpec.getSize(var1)), this.getMeasuredHeight());
      }

   }

   public void onRestoreInstanceState(Parcelable var1) {
      SavedState var2 = (SavedState)var1;
      super.onRestoreInstanceState(var2.getSuperState());
      if (var2.mShowDropdown) {
         ViewTreeObserver var3 = this.getViewTreeObserver();
         if (var3 != null) {
            var3.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(this) {
               final AppCompatSpinner this$0;

               {
                  this.this$0 = var1;
               }

               public void onGlobalLayout() {
                  if (!this.this$0.getInternalPopup().isShowing()) {
                     this.this$0.showPopup();
                  }

                  ViewTreeObserver var1 = this.this$0.getViewTreeObserver();
                  if (var1 != null) {
                     if (VERSION.SDK_INT >= 16) {
                        var1.removeOnGlobalLayoutListener(this);
                     } else {
                        var1.removeGlobalOnLayoutListener(this);
                     }
                  }

               }
            });
         }
      }

   }

   public Parcelable onSaveInstanceState() {
      SavedState var2 = new SavedState(super.onSaveInstanceState());
      SpinnerPopup var3 = this.mPopup;
      boolean var1;
      if (var3 != null && var3.isShowing()) {
         var1 = true;
      } else {
         var1 = false;
      }

      var2.mShowDropdown = var1;
      return var2;
   }

   public boolean onTouchEvent(MotionEvent var1) {
      ForwardingListener var2 = this.mForwardingListener;
      return var2 != null && var2.onTouch(this, var1) ? true : super.onTouchEvent(var1);
   }

   public boolean performClick() {
      SpinnerPopup var1 = this.mPopup;
      if (var1 != null) {
         if (!var1.isShowing()) {
            this.showPopup();
         }

         return true;
      } else {
         return super.performClick();
      }
   }

   public void setAdapter(SpinnerAdapter var1) {
      if (!this.mPopupSet) {
         this.mTempAdapter = var1;
      } else {
         super.setAdapter(var1);
         if (this.mPopup != null) {
            Context var3 = this.mPopupContext;
            Context var2 = var3;
            if (var3 == null) {
               var2 = this.getContext();
            }

            this.mPopup.setAdapter(new DropDownAdapter(var1, var2.getTheme()));
         }

      }
   }

   public void setBackgroundDrawable(Drawable var1) {
      super.setBackgroundDrawable(var1);
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.onSetBackgroundDrawable(var1);
      }

   }

   public void setBackgroundResource(int var1) {
      super.setBackgroundResource(var1);
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.onSetBackgroundResource(var1);
      }

   }

   public void setDropDownHorizontalOffset(int var1) {
      SpinnerPopup var2 = this.mPopup;
      if (var2 != null) {
         var2.setHorizontalOriginalOffset(var1);
         this.mPopup.setHorizontalOffset(var1);
      } else if (VERSION.SDK_INT >= 16) {
         super.setDropDownHorizontalOffset(var1);
      }

   }

   public void setDropDownVerticalOffset(int var1) {
      SpinnerPopup var2 = this.mPopup;
      if (var2 != null) {
         var2.setVerticalOffset(var1);
      } else if (VERSION.SDK_INT >= 16) {
         super.setDropDownVerticalOffset(var1);
      }

   }

   public void setDropDownWidth(int var1) {
      if (this.mPopup != null) {
         this.mDropDownWidth = var1;
      } else if (VERSION.SDK_INT >= 16) {
         super.setDropDownWidth(var1);
      }

   }

   public void setPopupBackgroundDrawable(Drawable var1) {
      SpinnerPopup var2 = this.mPopup;
      if (var2 != null) {
         var2.setBackgroundDrawable(var1);
      } else if (VERSION.SDK_INT >= 16) {
         super.setPopupBackgroundDrawable(var1);
      }

   }

   public void setPopupBackgroundResource(int var1) {
      this.setPopupBackgroundDrawable(AppCompatResources.getDrawable(this.getPopupContext(), var1));
   }

   public void setPrompt(CharSequence var1) {
      SpinnerPopup var2 = this.mPopup;
      if (var2 != null) {
         var2.setPromptText(var1);
      } else {
         super.setPrompt(var1);
      }

   }

   public void setSupportBackgroundTintList(ColorStateList var1) {
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.setSupportBackgroundTintList(var1);
      }

   }

   public void setSupportBackgroundTintMode(PorterDuff.Mode var1) {
      AppCompatBackgroundHelper var2 = this.mBackgroundTintHelper;
      if (var2 != null) {
         var2.setSupportBackgroundTintMode(var1);
      }

   }

   void showPopup() {
      if (VERSION.SDK_INT >= 17) {
         this.mPopup.show(this.getTextDirection(), this.getTextAlignment());
      } else {
         this.mPopup.show(-1, -1);
      }

   }

   class DialogPopup implements SpinnerPopup, DialogInterface.OnClickListener {
      private ListAdapter mListAdapter;
      AlertDialog mPopup;
      private CharSequence mPrompt;
      final AppCompatSpinner this$0;

      DialogPopup(AppCompatSpinner var1) {
         this.this$0 = var1;
      }

      public void dismiss() {
         AlertDialog var1 = this.mPopup;
         if (var1 != null) {
            var1.dismiss();
            this.mPopup = null;
         }

      }

      public Drawable getBackground() {
         return null;
      }

      public CharSequence getHintText() {
         return this.mPrompt;
      }

      public int getHorizontalOffset() {
         return 0;
      }

      public int getHorizontalOriginalOffset() {
         return 0;
      }

      public int getVerticalOffset() {
         return 0;
      }

      public boolean isShowing() {
         AlertDialog var2 = this.mPopup;
         boolean var1;
         if (var2 != null) {
            var1 = var2.isShowing();
         } else {
            var1 = false;
         }

         return var1;
      }

      public void onClick(DialogInterface var1, int var2) {
         this.this$0.setSelection(var2);
         if (this.this$0.getOnItemClickListener() != null) {
            this.this$0.performItemClick((View)null, var2, this.mListAdapter.getItemId(var2));
         }

         this.dismiss();
      }

      public void setAdapter(ListAdapter var1) {
         this.mListAdapter = var1;
      }

      public void setBackgroundDrawable(Drawable var1) {
         Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
      }

      public void setHorizontalOffset(int var1) {
         Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
      }

      public void setHorizontalOriginalOffset(int var1) {
         Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
      }

      public void setPromptText(CharSequence var1) {
         this.mPrompt = var1;
      }

      public void setVerticalOffset(int var1) {
         Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
      }

      public void show(int var1, int var2) {
         if (this.mListAdapter != null) {
            AlertDialog.Builder var4 = new AlertDialog.Builder(this.this$0.getPopupContext());
            CharSequence var3 = this.mPrompt;
            if (var3 != null) {
               var4.setTitle(var3);
            }

            AlertDialog var5 = var4.setSingleChoiceItems((ListAdapter)this.mListAdapter, this.this$0.getSelectedItemPosition(), this).create();
            this.mPopup = var5;
            ListView var6 = var5.getListView();
            if (VERSION.SDK_INT >= 17) {
               var6.setTextDirection(var1);
               var6.setTextAlignment(var2);
            }

            this.mPopup.show();
         }
      }
   }

   private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
      private SpinnerAdapter mAdapter;
      private ListAdapter mListAdapter;

      public DropDownAdapter(SpinnerAdapter var1, Resources.Theme var2) {
         this.mAdapter = var1;
         if (var1 instanceof ListAdapter) {
            this.mListAdapter = (ListAdapter)var1;
         }

         if (var2 != null) {
            if (VERSION.SDK_INT >= 23 && var1 instanceof android.widget.ThemedSpinnerAdapter) {
               android.widget.ThemedSpinnerAdapter var4 = (android.widget.ThemedSpinnerAdapter)var1;
               if (var4.getDropDownViewTheme() != var2) {
                  var4.setDropDownViewTheme(var2);
               }
            } else if (var1 instanceof ThemedSpinnerAdapter) {
               ThemedSpinnerAdapter var3 = (ThemedSpinnerAdapter)var1;
               if (var3.getDropDownViewTheme() == null) {
                  var3.setDropDownViewTheme(var2);
               }
            }
         }

      }

      public boolean areAllItemsEnabled() {
         ListAdapter var1 = this.mListAdapter;
         return var1 != null ? var1.areAllItemsEnabled() : true;
      }

      public int getCount() {
         SpinnerAdapter var2 = this.mAdapter;
         int var1;
         if (var2 == null) {
            var1 = 0;
         } else {
            var1 = var2.getCount();
         }

         return var1;
      }

      public View getDropDownView(int var1, View var2, ViewGroup var3) {
         SpinnerAdapter var4 = this.mAdapter;
         if (var4 == null) {
            var2 = null;
         } else {
            var2 = var4.getDropDownView(var1, var2, var3);
         }

         return var2;
      }

      public Object getItem(int var1) {
         SpinnerAdapter var2 = this.mAdapter;
         Object var3;
         if (var2 == null) {
            var3 = null;
         } else {
            var3 = var2.getItem(var1);
         }

         return var3;
      }

      public long getItemId(int var1) {
         SpinnerAdapter var4 = this.mAdapter;
         long var2;
         if (var4 == null) {
            var2 = -1L;
         } else {
            var2 = var4.getItemId(var1);
         }

         return var2;
      }

      public int getItemViewType(int var1) {
         return 0;
      }

      public View getView(int var1, View var2, ViewGroup var3) {
         return this.getDropDownView(var1, var2, var3);
      }

      public int getViewTypeCount() {
         return 1;
      }

      public boolean hasStableIds() {
         SpinnerAdapter var2 = this.mAdapter;
         boolean var1;
         if (var2 != null && var2.hasStableIds()) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isEmpty() {
         boolean var1;
         if (this.getCount() == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isEnabled(int var1) {
         ListAdapter var2 = this.mListAdapter;
         return var2 != null ? var2.isEnabled(var1) : true;
      }

      public void registerDataSetObserver(DataSetObserver var1) {
         SpinnerAdapter var2 = this.mAdapter;
         if (var2 != null) {
            var2.registerDataSetObserver(var1);
         }

      }

      public void unregisterDataSetObserver(DataSetObserver var1) {
         SpinnerAdapter var2 = this.mAdapter;
         if (var2 != null) {
            var2.unregisterDataSetObserver(var1);
         }

      }
   }

   class DropdownPopup extends ListPopupWindow implements SpinnerPopup {
      ListAdapter mAdapter;
      private CharSequence mHintText;
      private int mOriginalHorizontalOffset;
      private final Rect mVisibleRect;
      final AppCompatSpinner this$0;

      public DropdownPopup(AppCompatSpinner var1, Context var2, AttributeSet var3, int var4) {
         super(var2, var3, var4);
         this.this$0 = var1;
         this.mVisibleRect = new Rect();
         this.setAnchorView(var1);
         this.setModal(true);
         this.setPromptPosition(0);
         this.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var1) {
            final DropdownPopup this$1;
            final AppCompatSpinner val$this$0;

            {
               this.this$1 = var1;
               this.val$this$0 = var2;
            }

            public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
               this.this$1.this$0.setSelection(var3);
               if (this.this$1.this$0.getOnItemClickListener() != null) {
                  this.this$1.this$0.performItemClick(var2, var3, this.this$1.mAdapter.getItemId(var3));
               }

               this.this$1.dismiss();
            }
         });
      }

      void computeContentWidth() {
         Drawable var8 = this.getBackground();
         int var1 = 0;
         if (var8 != null) {
            var8.getPadding(this.this$0.mTempRect);
            if (ViewUtils.isLayoutRtl(this.this$0)) {
               var1 = this.this$0.mTempRect.right;
            } else {
               var1 = -this.this$0.mTempRect.left;
            }
         } else {
            Rect var9 = this.this$0.mTempRect;
            this.this$0.mTempRect.right = 0;
            var9.left = 0;
         }

         int var7 = this.this$0.getPaddingLeft();
         int var6 = this.this$0.getPaddingRight();
         int var5 = this.this$0.getWidth();
         if (this.this$0.mDropDownWidth == -2) {
            int var4 = this.this$0.compatMeasureContentWidth((SpinnerAdapter)this.mAdapter, this.getBackground());
            int var3 = this.this$0.getContext().getResources().getDisplayMetrics().widthPixels - this.this$0.mTempRect.left - this.this$0.mTempRect.right;
            int var2 = var4;
            if (var4 > var3) {
               var2 = var3;
            }

            this.setContentWidth(Math.max(var2, var5 - var7 - var6));
         } else if (this.this$0.mDropDownWidth == -1) {
            this.setContentWidth(var5 - var7 - var6);
         } else {
            this.setContentWidth(this.this$0.mDropDownWidth);
         }

         if (ViewUtils.isLayoutRtl(this.this$0)) {
            var1 += var5 - var6 - this.getWidth() - this.getHorizontalOriginalOffset();
         } else {
            var1 += var7 + this.getHorizontalOriginalOffset();
         }

         this.setHorizontalOffset(var1);
      }

      public CharSequence getHintText() {
         return this.mHintText;
      }

      public int getHorizontalOriginalOffset() {
         return this.mOriginalHorizontalOffset;
      }

      boolean isVisibleToUser(View var1) {
         boolean var2;
         if (ViewCompat.isAttachedToWindow(var1) && var1.getGlobalVisibleRect(this.mVisibleRect)) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public void setAdapter(ListAdapter var1) {
         super.setAdapter(var1);
         this.mAdapter = var1;
      }

      public void setHorizontalOriginalOffset(int var1) {
         this.mOriginalHorizontalOffset = var1;
      }

      public void setPromptText(CharSequence var1) {
         this.mHintText = var1;
      }

      public void show(int var1, int var2) {
         boolean var3 = this.isShowing();
         this.computeContentWidth();
         this.setInputMethodMode(2);
         super.show();
         ListView var4 = this.getListView();
         var4.setChoiceMode(1);
         if (VERSION.SDK_INT >= 17) {
            var4.setTextDirection(var1);
            var4.setTextAlignment(var2);
         }

         this.setSelection(this.this$0.getSelectedItemPosition());
         if (!var3) {
            ViewTreeObserver var5 = this.this$0.getViewTreeObserver();
            if (var5 != null) {
               ViewTreeObserver.OnGlobalLayoutListener var6 = new ViewTreeObserver.OnGlobalLayoutListener(this) {
                  final DropdownPopup this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void onGlobalLayout() {
                     DropdownPopup var1 = this.this$1;
                     if (!var1.isVisibleToUser(var1.this$0)) {
                        this.this$1.dismiss();
                     } else {
                        this.this$1.computeContentWidth();
                        this.this$1.show();
                     }

                  }
               };
               var5.addOnGlobalLayoutListener(var6);
               this.setOnDismissListener(new PopupWindow.OnDismissListener(this, var6) {
                  final DropdownPopup this$1;
                  final ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;

                  {
                     this.this$1 = var1;
                     this.val$layoutListener = var2;
                  }

                  public void onDismiss() {
                     ViewTreeObserver var1 = this.this$1.this$0.getViewTreeObserver();
                     if (var1 != null) {
                        var1.removeGlobalOnLayoutListener(this.val$layoutListener);
                     }

                  }
               });
            }

         }
      }
   }

   static class SavedState extends View.BaseSavedState {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      boolean mShowDropdown;

      SavedState(Parcel var1) {
         super(var1);
         boolean var2;
         if (var1.readByte() != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.mShowDropdown = var2;
      }

      SavedState(Parcelable var1) {
         super(var1);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeByte((byte)this.mShowDropdown);
      }
   }

   interface SpinnerPopup {
      void dismiss();

      Drawable getBackground();

      CharSequence getHintText();

      int getHorizontalOffset();

      int getHorizontalOriginalOffset();

      int getVerticalOffset();

      boolean isShowing();

      void setAdapter(ListAdapter var1);

      void setBackgroundDrawable(Drawable var1);

      void setHorizontalOffset(int var1);

      void setHorizontalOriginalOffset(int var1);

      void setPromptText(CharSequence var1);

      void setVerticalOffset(int var1);

      void show(int var1, int var2);
   }
}
