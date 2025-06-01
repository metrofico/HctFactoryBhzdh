package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.ActionProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class ActivityChooserView extends ViewGroup implements ActivityChooserModel.ActivityChooserModelClient {
   private final View mActivityChooserContent;
   private final Drawable mActivityChooserContentBackground;
   final ActivityChooserViewAdapter mAdapter;
   private final Callbacks mCallbacks;
   private int mDefaultActionButtonContentDescription;
   final FrameLayout mDefaultActivityButton;
   private final ImageView mDefaultActivityButtonImage;
   final FrameLayout mExpandActivityOverflowButton;
   private final ImageView mExpandActivityOverflowButtonImage;
   int mInitialActivityCount;
   private boolean mIsAttachedToWindow;
   boolean mIsSelectingDefaultActivity;
   private final int mListPopupMaxWidth;
   private ListPopupWindow mListPopupWindow;
   final DataSetObserver mModelDataSetObserver;
   PopupWindow.OnDismissListener mOnDismissListener;
   private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
   ActionProvider mProvider;

   public ActivityChooserView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public ActivityChooserView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public ActivityChooserView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mModelDataSetObserver = new DataSetObserver(this) {
         final ActivityChooserView this$0;

         {
            this.this$0 = var1;
         }

         public void onChanged() {
            super.onChanged();
            this.this$0.mAdapter.notifyDataSetChanged();
         }

         public void onInvalidated() {
            super.onInvalidated();
            this.this$0.mAdapter.notifyDataSetInvalidated();
         }
      };
      this.mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener(this) {
         final ActivityChooserView this$0;

         {
            this.this$0 = var1;
         }

         public void onGlobalLayout() {
            if (this.this$0.isShowingPopup()) {
               if (!this.this$0.isShown()) {
                  this.this$0.getListPopupWindow().dismiss();
               } else {
                  this.this$0.getListPopupWindow().show();
                  if (this.this$0.mProvider != null) {
                     this.this$0.mProvider.subUiVisibilityChanged(true);
                  }
               }
            }

         }
      };
      this.mInitialActivityCount = 4;
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.ActivityChooserView, var3, 0);
      ViewCompat.saveAttributeDataForStyleable(this, var1, R.styleable.ActivityChooserView, var2, var4, var3, 0);
      this.mInitialActivityCount = var4.getInt(R.styleable.ActivityChooserView_initialActivityCount, 4);
      Drawable var7 = var4.getDrawable(R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
      var4.recycle();
      LayoutInflater.from(this.getContext()).inflate(R.layout.abc_activity_chooser_view, this, true);
      Callbacks var9 = new Callbacks(this);
      this.mCallbacks = var9;
      View var5 = this.findViewById(R.id.activity_chooser_view_content);
      this.mActivityChooserContent = var5;
      this.mActivityChooserContentBackground = var5.getBackground();
      FrameLayout var11 = (FrameLayout)this.findViewById(R.id.default_activity_button);
      this.mDefaultActivityButton = var11;
      var11.setOnClickListener(var9);
      var11.setOnLongClickListener(var9);
      this.mDefaultActivityButtonImage = (ImageView)var11.findViewById(R.id.image);
      var11 = (FrameLayout)this.findViewById(R.id.expand_activities_button);
      var11.setOnClickListener(var9);
      var11.setAccessibilityDelegate(new View.AccessibilityDelegate(this) {
         final ActivityChooserView this$0;

         {
            this.this$0 = var1;
         }

         public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfo var2) {
            super.onInitializeAccessibilityNodeInfo(var1, var2);
            AccessibilityNodeInfoCompat.wrap(var2).setCanOpenPopup(true);
         }
      });
      var11.setOnTouchListener(new ForwardingListener(this, var11) {
         final ActivityChooserView this$0;

         {
            this.this$0 = var1;
         }

         public ShowableListMenu getPopup() {
            return this.this$0.getListPopupWindow();
         }

         protected boolean onForwardingStarted() {
            this.this$0.showPopup();
            return true;
         }

         protected boolean onForwardingStopped() {
            this.this$0.dismissPopup();
            return true;
         }
      });
      this.mExpandActivityOverflowButton = var11;
      ImageView var10 = (ImageView)var11.findViewById(R.id.image);
      this.mExpandActivityOverflowButtonImage = var10;
      var10.setImageDrawable(var7);
      ActivityChooserViewAdapter var8 = new ActivityChooserViewAdapter(this);
      this.mAdapter = var8;
      var8.registerDataSetObserver(new DataSetObserver(this) {
         final ActivityChooserView this$0;

         {
            this.this$0 = var1;
         }

         public void onChanged() {
            super.onChanged();
            this.this$0.updateAppearance();
         }
      });
      Resources var6 = var1.getResources();
      this.mListPopupMaxWidth = Math.max(var6.getDisplayMetrics().widthPixels / 2, var6.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
   }

   public boolean dismissPopup() {
      if (this.isShowingPopup()) {
         this.getListPopupWindow().dismiss();
         ViewTreeObserver var1 = this.getViewTreeObserver();
         if (var1.isAlive()) {
            var1.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
         }
      }

      return true;
   }

   public ActivityChooserModel getDataModel() {
      return this.mAdapter.getDataModel();
   }

   ListPopupWindow getListPopupWindow() {
      if (this.mListPopupWindow == null) {
         ListPopupWindow var1 = new ListPopupWindow(this.getContext());
         this.mListPopupWindow = var1;
         var1.setAdapter(this.mAdapter);
         this.mListPopupWindow.setAnchorView(this);
         this.mListPopupWindow.setModal(true);
         this.mListPopupWindow.setOnItemClickListener(this.mCallbacks);
         this.mListPopupWindow.setOnDismissListener(this.mCallbacks);
      }

      return this.mListPopupWindow;
   }

   public boolean isShowingPopup() {
      return this.getListPopupWindow().isShowing();
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      ActivityChooserModel var1 = this.mAdapter.getDataModel();
      if (var1 != null) {
         var1.registerObserver(this.mModelDataSetObserver);
      }

      this.mIsAttachedToWindow = true;
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      ActivityChooserModel var1 = this.mAdapter.getDataModel();
      if (var1 != null) {
         var1.unregisterObserver(this.mModelDataSetObserver);
      }

      ViewTreeObserver var2 = this.getViewTreeObserver();
      if (var2.isAlive()) {
         var2.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
      }

      if (this.isShowingPopup()) {
         this.dismissPopup();
      }

      this.mIsAttachedToWindow = false;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      this.mActivityChooserContent.layout(0, 0, var4 - var2, var5 - var3);
      if (!this.isShowingPopup()) {
         this.dismissPopup();
      }

   }

   protected void onMeasure(int var1, int var2) {
      View var4 = this.mActivityChooserContent;
      int var3 = var2;
      if (this.mDefaultActivityButton.getVisibility() != 0) {
         var3 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(var2), 1073741824);
      }

      this.measureChild(var4, var1, var3);
      this.setMeasuredDimension(var4.getMeasuredWidth(), var4.getMeasuredHeight());
   }

   public void setActivityChooserModel(ActivityChooserModel var1) {
      this.mAdapter.setDataModel(var1);
      if (this.isShowingPopup()) {
         this.dismissPopup();
         this.showPopup();
      }

   }

   public void setDefaultActionButtonContentDescription(int var1) {
      this.mDefaultActionButtonContentDescription = var1;
   }

   public void setExpandActivityOverflowButtonContentDescription(int var1) {
      String var2 = this.getContext().getString(var1);
      this.mExpandActivityOverflowButtonImage.setContentDescription(var2);
   }

   public void setExpandActivityOverflowButtonDrawable(Drawable var1) {
      this.mExpandActivityOverflowButtonImage.setImageDrawable(var1);
   }

   public void setInitialActivityCount(int var1) {
      this.mInitialActivityCount = var1;
   }

   public void setOnDismissListener(PopupWindow.OnDismissListener var1) {
      this.mOnDismissListener = var1;
   }

   public void setProvider(ActionProvider var1) {
      this.mProvider = var1;
   }

   public boolean showPopup() {
      if (!this.isShowingPopup() && this.mIsAttachedToWindow) {
         this.mIsSelectingDefaultActivity = false;
         this.showPopupUnchecked(this.mInitialActivityCount);
         return true;
      } else {
         return false;
      }
   }

   void showPopupUnchecked(int var1) {
      if (this.mAdapter.getDataModel() != null) {
         this.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
         byte var2;
         if (this.mDefaultActivityButton.getVisibility() == 0) {
            var2 = 1;
         } else {
            var2 = 0;
         }

         int var3 = this.mAdapter.getActivityCount();
         if (var1 != Integer.MAX_VALUE && var3 > var1 + var2) {
            this.mAdapter.setShowFooterView(true);
            this.mAdapter.setMaxActivityCount(var1 - 1);
         } else {
            this.mAdapter.setShowFooterView(false);
            this.mAdapter.setMaxActivityCount(var1);
         }

         ListPopupWindow var5 = this.getListPopupWindow();
         if (!var5.isShowing()) {
            if (!this.mIsSelectingDefaultActivity && var2 != 0) {
               this.mAdapter.setShowDefaultActivity(false, false);
            } else {
               this.mAdapter.setShowDefaultActivity(true, (boolean)var2);
            }

            var5.setContentWidth(Math.min(this.mAdapter.measureContentWidth(), this.mListPopupMaxWidth));
            var5.show();
            ActionProvider var4 = this.mProvider;
            if (var4 != null) {
               var4.subUiVisibilityChanged(true);
            }

            var5.getListView().setContentDescription(this.getContext().getString(R.string.abc_activitychooserview_choose_application));
            var5.getListView().setSelector(new ColorDrawable(0));
         }

      } else {
         throw new IllegalStateException("No data model. Did you call #setDataModel?");
      }
   }

   void updateAppearance() {
      if (this.mAdapter.getCount() > 0) {
         this.mExpandActivityOverflowButton.setEnabled(true);
      } else {
         this.mExpandActivityOverflowButton.setEnabled(false);
      }

      int var1 = this.mAdapter.getActivityCount();
      int var2 = this.mAdapter.getHistorySize();
      if (var1 != 1 && (var1 <= 1 || var2 <= 0)) {
         this.mDefaultActivityButton.setVisibility(8);
      } else {
         this.mDefaultActivityButton.setVisibility(0);
         ResolveInfo var3 = this.mAdapter.getDefaultActivity();
         PackageManager var4 = this.getContext().getPackageManager();
         this.mDefaultActivityButtonImage.setImageDrawable(var3.loadIcon(var4));
         if (this.mDefaultActionButtonContentDescription != 0) {
            CharSequence var5 = var3.loadLabel(var4);
            String var6 = this.getContext().getString(this.mDefaultActionButtonContentDescription, new Object[]{var5});
            this.mDefaultActivityButton.setContentDescription(var6);
         }
      }

      if (this.mDefaultActivityButton.getVisibility() == 0) {
         this.mActivityChooserContent.setBackgroundDrawable(this.mActivityChooserContentBackground);
      } else {
         this.mActivityChooserContent.setBackgroundDrawable((Drawable)null);
      }

   }

   private class ActivityChooserViewAdapter extends BaseAdapter {
      private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
      private static final int ITEM_VIEW_TYPE_COUNT = 3;
      private static final int ITEM_VIEW_TYPE_FOOTER = 1;
      public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
      public static final int MAX_ACTIVITY_COUNT_UNLIMITED = Integer.MAX_VALUE;
      private ActivityChooserModel mDataModel;
      private boolean mHighlightDefaultActivity;
      private int mMaxActivityCount;
      private boolean mShowDefaultActivity;
      private boolean mShowFooterView;
      final ActivityChooserView this$0;

      ActivityChooserViewAdapter(ActivityChooserView var1) {
         this.this$0 = var1;
         this.mMaxActivityCount = 4;
      }

      public int getActivityCount() {
         return this.mDataModel.getActivityCount();
      }

      public int getCount() {
         int var2 = this.mDataModel.getActivityCount();
         int var1 = var2;
         if (!this.mShowDefaultActivity) {
            var1 = var2;
            if (this.mDataModel.getDefaultActivity() != null) {
               var1 = var2 - 1;
            }
         }

         var2 = Math.min(var1, this.mMaxActivityCount);
         var1 = var2;
         if (this.mShowFooterView) {
            var1 = var2 + 1;
         }

         return var1;
      }

      public ActivityChooserModel getDataModel() {
         return this.mDataModel;
      }

      public ResolveInfo getDefaultActivity() {
         return this.mDataModel.getDefaultActivity();
      }

      public int getHistorySize() {
         return this.mDataModel.getHistorySize();
      }

      public Object getItem(int var1) {
         int var2 = this.getItemViewType(var1);
         if (var2 != 0) {
            if (var2 == 1) {
               return null;
            } else {
               throw new IllegalArgumentException();
            }
         } else {
            var2 = var1;
            if (!this.mShowDefaultActivity) {
               var2 = var1;
               if (this.mDataModel.getDefaultActivity() != null) {
                  var2 = var1 + 1;
               }
            }

            return this.mDataModel.getActivity(var2);
         }
      }

      public long getItemId(int var1) {
         return (long)var1;
      }

      public int getItemViewType(int var1) {
         return this.mShowFooterView && var1 == this.getCount() - 1 ? 1 : 0;
      }

      public boolean getShowDefaultActivity() {
         return this.mShowDefaultActivity;
      }

      public View getView(int var1, View var2, ViewGroup var3) {
         int var4 = this.getItemViewType(var1);
         View var5;
         if (var4 != 0) {
            if (var4 != 1) {
               throw new IllegalArgumentException();
            } else {
               if (var2 != null) {
                  var5 = var2;
                  if (var2.getId() == 1) {
                     return var5;
                  }
               }

               var5 = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, var3, false);
               var5.setId(1);
               ((TextView)var5.findViewById(R.id.title)).setText(this.this$0.getContext().getString(R.string.abc_activity_chooser_view_see_all));
               return var5;
            }
         } else {
            label37: {
               if (var2 != null) {
                  var5 = var2;
                  if (var2.getId() == R.id.list_item) {
                     break label37;
                  }
               }

               var5 = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, var3, false);
            }

            PackageManager var6 = this.this$0.getContext().getPackageManager();
            ImageView var8 = (ImageView)var5.findViewById(R.id.icon);
            ResolveInfo var7 = (ResolveInfo)this.getItem(var1);
            var8.setImageDrawable(var7.loadIcon(var6));
            ((TextView)var5.findViewById(R.id.title)).setText(var7.loadLabel(var6));
            if (this.mShowDefaultActivity && var1 == 0 && this.mHighlightDefaultActivity) {
               var5.setActivated(true);
            } else {
               var5.setActivated(false);
            }

            return var5;
         }
      }

      public int getViewTypeCount() {
         return 3;
      }

      public int measureContentWidth() {
         int var5 = this.mMaxActivityCount;
         this.mMaxActivityCount = Integer.MAX_VALUE;
         int var2 = 0;
         int var4 = MeasureSpec.makeMeasureSpec(0, 0);
         int var3 = MeasureSpec.makeMeasureSpec(0, 0);
         int var6 = this.getCount();
         int var1 = 0;

         for(View var7 = null; var2 < var6; ++var2) {
            var7 = this.getView(var2, var7, (ViewGroup)null);
            var7.measure(var4, var3);
            var1 = Math.max(var1, var7.getMeasuredWidth());
         }

         this.mMaxActivityCount = var5;
         return var1;
      }

      public void setDataModel(ActivityChooserModel var1) {
         ActivityChooserModel var2 = this.this$0.mAdapter.getDataModel();
         if (var2 != null && this.this$0.isShown()) {
            var2.unregisterObserver(this.this$0.mModelDataSetObserver);
         }

         this.mDataModel = var1;
         if (var1 != null && this.this$0.isShown()) {
            var1.registerObserver(this.this$0.mModelDataSetObserver);
         }

         this.notifyDataSetChanged();
      }

      public void setMaxActivityCount(int var1) {
         if (this.mMaxActivityCount != var1) {
            this.mMaxActivityCount = var1;
            this.notifyDataSetChanged();
         }

      }

      public void setShowDefaultActivity(boolean var1, boolean var2) {
         if (this.mShowDefaultActivity != var1 || this.mHighlightDefaultActivity != var2) {
            this.mShowDefaultActivity = var1;
            this.mHighlightDefaultActivity = var2;
            this.notifyDataSetChanged();
         }

      }

      public void setShowFooterView(boolean var1) {
         if (this.mShowFooterView != var1) {
            this.mShowFooterView = var1;
            this.notifyDataSetChanged();
         }

      }
   }

   private class Callbacks implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
      final ActivityChooserView this$0;

      Callbacks(ActivityChooserView var1) {
         this.this$0 = var1;
      }

      private void notifyOnDismissListener() {
         if (this.this$0.mOnDismissListener != null) {
            this.this$0.mOnDismissListener.onDismiss();
         }

      }

      public void onClick(View var1) {
         if (var1 == this.this$0.mDefaultActivityButton) {
            this.this$0.dismissPopup();
            ResolveInfo var3 = this.this$0.mAdapter.getDefaultActivity();
            int var2 = this.this$0.mAdapter.getDataModel().getActivityIndex(var3);
            Intent var4 = this.this$0.mAdapter.getDataModel().chooseActivity(var2);
            if (var4 != null) {
               var4.addFlags(524288);
               this.this$0.getContext().startActivity(var4);
            }
         } else {
            if (var1 != this.this$0.mExpandActivityOverflowButton) {
               throw new IllegalArgumentException();
            }

            this.this$0.mIsSelectingDefaultActivity = false;
            ActivityChooserView var5 = this.this$0;
            var5.showPopupUnchecked(var5.mInitialActivityCount);
         }

      }

      public void onDismiss() {
         this.notifyOnDismissListener();
         if (this.this$0.mProvider != null) {
            this.this$0.mProvider.subUiVisibilityChanged(false);
         }

      }

      public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
         int var6 = ((ActivityChooserViewAdapter)var1.getAdapter()).getItemViewType(var3);
         if (var6 != 0) {
            if (var6 != 1) {
               throw new IllegalArgumentException();
            }

            this.this$0.showPopupUnchecked(Integer.MAX_VALUE);
         } else {
            this.this$0.dismissPopup();
            if (this.this$0.mIsSelectingDefaultActivity) {
               if (var3 > 0) {
                  this.this$0.mAdapter.getDataModel().setDefaultActivity(var3);
               }
            } else {
               if (!this.this$0.mAdapter.getShowDefaultActivity()) {
                  ++var3;
               }

               Intent var7 = this.this$0.mAdapter.getDataModel().chooseActivity(var3);
               if (var7 != null) {
                  var7.addFlags(524288);
                  this.this$0.getContext().startActivity(var7);
               }
            }
         }

      }

      public boolean onLongClick(View var1) {
         if (var1 == this.this$0.mDefaultActivityButton) {
            if (this.this$0.mAdapter.getCount() > 0) {
               this.this$0.mIsSelectingDefaultActivity = true;
               ActivityChooserView var2 = this.this$0;
               var2.showPopupUnchecked(var2.mInitialActivityCount);
            }

            return true;
         } else {
            throw new IllegalArgumentException();
         }
      }
   }

   public static class InnerLayout extends LinearLayout {
      private static final int[] TINT_ATTRS = new int[]{16842964};

      public InnerLayout(Context var1, AttributeSet var2) {
         super(var1, var2);
         TintTypedArray var3 = TintTypedArray.obtainStyledAttributes(var1, var2, TINT_ATTRS);
         this.setBackgroundDrawable(var3.getDrawable(0));
         var3.recycle();
      }
   }
}
