package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
   static final int HORIZ_POSITION_LEFT = 0;
   static final int HORIZ_POSITION_RIGHT = 1;
   private static final int ITEM_LAYOUT;
   static final int SUBMENU_TIMEOUT_MS = 200;
   private View mAnchorView;
   private final View.OnAttachStateChangeListener mAttachStateChangeListener = new View.OnAttachStateChangeListener(this) {
      final CascadingMenuPopup this$0;

      {
         this.this$0 = var1;
      }

      public void onViewAttachedToWindow(View var1) {
      }

      public void onViewDetachedFromWindow(View var1) {
         if (this.this$0.mTreeObserver != null) {
            if (!this.this$0.mTreeObserver.isAlive()) {
               this.this$0.mTreeObserver = var1.getViewTreeObserver();
            }

            this.this$0.mTreeObserver.removeGlobalOnLayoutListener(this.this$0.mGlobalLayoutListener);
         }

         var1.removeOnAttachStateChangeListener(this);
      }
   };
   private final Context mContext;
   private int mDropDownGravity = 0;
   private boolean mForceShowIcon;
   final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener(this) {
      final CascadingMenuPopup this$0;

      {
         this.this$0 = var1;
      }

      public void onGlobalLayout() {
         if (this.this$0.isShowing() && this.this$0.mShowingMenus.size() > 0 && !((CascadingMenuInfo)this.this$0.mShowingMenus.get(0)).window.isModal()) {
            View var1 = this.this$0.mShownAnchorView;
            if (var1 != null && var1.isShown()) {
               Iterator var2 = this.this$0.mShowingMenus.iterator();

               while(var2.hasNext()) {
                  ((CascadingMenuInfo)var2.next()).window.show();
               }
            } else {
               this.this$0.dismiss();
            }
         }

      }
   };
   private boolean mHasXOffset;
   private boolean mHasYOffset;
   private int mLastPosition;
   private final MenuItemHoverListener mMenuItemHoverListener = new MenuItemHoverListener(this) {
      final CascadingMenuPopup this$0;

      {
         this.this$0 = var1;
      }

      public void onItemHoverEnter(MenuBuilder var1, MenuItem var2) {
         Handler var8 = this.this$0.mSubMenuHoverHandler;
         CascadingMenuInfo var7 = null;
         var8.removeCallbacksAndMessages((Object)null);
         int var4 = this.this$0.mShowingMenus.size();
         int var3 = 0;

         while(true) {
            if (var3 >= var4) {
               var3 = -1;
               break;
            }

            if (var1 == ((CascadingMenuInfo)this.this$0.mShowingMenus.get(var3)).menu) {
               break;
            }

            ++var3;
         }

         if (var3 != -1) {
            ++var3;
            if (var3 < this.this$0.mShowingMenus.size()) {
               var7 = (CascadingMenuInfo)this.this$0.mShowingMenus.get(var3);
            }

            Runnable var9 = new Runnable(this, var7, var2, var1) {
               final <undefinedtype> this$1;
               final MenuItem val$item;
               final MenuBuilder val$menu;
               final CascadingMenuInfo val$nextInfo;

               {
                  this.this$1 = var1;
                  this.val$nextInfo = var2;
                  this.val$item = var3;
                  this.val$menu = var4;
               }

               public void run() {
                  if (this.val$nextInfo != null) {
                     this.this$1.this$0.mShouldCloseImmediately = true;
                     this.val$nextInfo.menu.close(false);
                     this.this$1.this$0.mShouldCloseImmediately = false;
                  }

                  if (this.val$item.isEnabled() && this.val$item.hasSubMenu()) {
                     this.val$menu.performItemAction(this.val$item, 4);
                  }

               }
            };
            long var5 = SystemClock.uptimeMillis();
            this.this$0.mSubMenuHoverHandler.postAtTime(var9, var1, var5 + 200L);
         }
      }

      public void onItemHoverExit(MenuBuilder var1, MenuItem var2) {
         this.this$0.mSubMenuHoverHandler.removeCallbacksAndMessages(var1);
      }
   };
   private final int mMenuMaxWidth;
   private PopupWindow.OnDismissListener mOnDismissListener;
   private final boolean mOverflowOnly;
   private final List mPendingMenus = new ArrayList();
   private final int mPopupStyleAttr;
   private final int mPopupStyleRes;
   private MenuPresenter.Callback mPresenterCallback;
   private int mRawDropDownGravity = 0;
   boolean mShouldCloseImmediately;
   private boolean mShowTitle;
   final List mShowingMenus = new ArrayList();
   View mShownAnchorView;
   final Handler mSubMenuHoverHandler;
   ViewTreeObserver mTreeObserver;
   private int mXOffset;
   private int mYOffset;

   static {
      ITEM_LAYOUT = R.layout.abc_cascading_menu_item_layout;
   }

   public CascadingMenuPopup(Context var1, View var2, int var3, int var4, boolean var5) {
      this.mContext = var1;
      this.mAnchorView = var2;
      this.mPopupStyleAttr = var3;
      this.mPopupStyleRes = var4;
      this.mOverflowOnly = var5;
      this.mForceShowIcon = false;
      this.mLastPosition = this.getInitialMenuPosition();
      Resources var6 = var1.getResources();
      this.mMenuMaxWidth = Math.max(var6.getDisplayMetrics().widthPixels / 2, var6.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
      this.mSubMenuHoverHandler = new Handler();
   }

   private MenuPopupWindow createPopupWindow() {
      MenuPopupWindow var1 = new MenuPopupWindow(this.mContext, (AttributeSet)null, this.mPopupStyleAttr, this.mPopupStyleRes);
      var1.setHoverListener(this.mMenuItemHoverListener);
      var1.setOnItemClickListener(this);
      var1.setOnDismissListener(this);
      var1.setAnchorView(this.mAnchorView);
      var1.setDropDownGravity(this.mDropDownGravity);
      var1.setModal(true);
      var1.setInputMethodMode(2);
      return var1;
   }

   private int findIndexOfAddedMenu(MenuBuilder var1) {
      int var3 = this.mShowingMenus.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         if (var1 == ((CascadingMenuInfo)this.mShowingMenus.get(var2)).menu) {
            return var2;
         }
      }

      return -1;
   }

   private MenuItem findMenuItemForSubmenu(MenuBuilder var1, MenuBuilder var2) {
      int var4 = var1.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         MenuItem var5 = var1.getItem(var3);
         if (var5.hasSubMenu() && var2 == var5.getSubMenu()) {
            return var5;
         }
      }

      return null;
   }

   private View findParentViewForSubmenu(CascadingMenuInfo var1, MenuBuilder var2) {
      MenuItem var11 = this.findMenuItemForSubmenu(var1.menu, var2);
      if (var11 == null) {
         return null;
      } else {
         ListView var7 = var1.getListView();
         ListAdapter var8 = var7.getAdapter();
         boolean var6 = var8 instanceof HeaderViewListAdapter;
         int var3 = 0;
         int var4;
         MenuAdapter var10;
         if (var6) {
            HeaderViewListAdapter var9 = (HeaderViewListAdapter)var8;
            var4 = var9.getHeadersCount();
            var10 = (MenuAdapter)var9.getWrappedAdapter();
         } else {
            var10 = (MenuAdapter)var8;
            var4 = 0;
         }

         int var5 = var10.getCount();

         while(true) {
            if (var3 >= var5) {
               var3 = -1;
               break;
            }

            if (var11 == var10.getItem(var3)) {
               break;
            }

            ++var3;
         }

         if (var3 == -1) {
            return null;
         } else {
            var3 = var3 + var4 - var7.getFirstVisiblePosition();
            return var3 >= 0 && var3 < var7.getChildCount() ? var7.getChildAt(var3) : null;
         }
      }
   }

   private int getInitialMenuPosition() {
      int var2 = ViewCompat.getLayoutDirection(this.mAnchorView);
      byte var1 = 1;
      if (var2 == 1) {
         var1 = 0;
      }

      return var1;
   }

   private int getNextMenuPosition(int var1) {
      List var2 = this.mShowingMenus;
      ListView var3 = ((CascadingMenuInfo)var2.get(var2.size() - 1)).getListView();
      int[] var5 = new int[2];
      var3.getLocationOnScreen(var5);
      Rect var4 = new Rect();
      this.mShownAnchorView.getWindowVisibleDisplayFrame(var4);
      if (this.mLastPosition == 1) {
         return var5[0] + var3.getWidth() + var1 > var4.right ? 0 : 1;
      } else {
         return var5[0] - var1 < 0 ? 1 : 0;
      }
   }

   private void showMenu(MenuBuilder var1) {
      LayoutInflater var9 = LayoutInflater.from(this.mContext);
      MenuAdapter var6 = new MenuAdapter(var1, var9, this.mOverflowOnly, ITEM_LAYOUT);
      if (!this.isShowing() && this.mForceShowIcon) {
         var6.setForceShowIcon(true);
      } else if (this.isShowing()) {
         var6.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(var1));
      }

      int var3 = measureIndividualMenuWidth(var6, (ViewGroup)null, this.mContext, this.mMenuMaxWidth);
      MenuPopupWindow var8 = this.createPopupWindow();
      var8.setAdapter(var6);
      var8.setContentWidth(var3);
      var8.setDropDownGravity(this.mDropDownGravity);
      View var7;
      CascadingMenuInfo var13;
      if (this.mShowingMenus.size() > 0) {
         List var12 = this.mShowingMenus;
         var13 = (CascadingMenuInfo)var12.get(var12.size() - 1);
         var7 = this.findParentViewForSubmenu(var13, var1);
      } else {
         var13 = null;
         var7 = null;
      }

      if (var7 != null) {
         var8.setTouchModal(false);
         var8.setEnterTransition((Object)null);
         int var2 = this.getNextMenuPosition(var3);
         boolean var4;
         if (var2 == 1) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.mLastPosition = var2;
         int var5;
         if (VERSION.SDK_INT >= 26) {
            var8.setAnchorView(var7);
            var2 = 0;
            var5 = 0;
         } else {
            int[] var11 = new int[2];
            this.mAnchorView.getLocationOnScreen(var11);
            int[] var10 = new int[2];
            var7.getLocationOnScreen(var10);
            if ((this.mDropDownGravity & 7) == 5) {
               var11[0] += this.mAnchorView.getWidth();
               var10[0] += var7.getWidth();
            }

            var5 = var10[0] - var11[0];
            var2 = var10[1] - var11[1];
         }

         label62: {
            label61: {
               if ((this.mDropDownGravity & 5) == 5) {
                  if (var4) {
                     break label61;
                  }

                  var3 = var7.getWidth();
               } else if (var4) {
                  var3 = var7.getWidth();
                  break label61;
               }

               var3 = var5 - var3;
               break label62;
            }

            var3 += var5;
         }

         var8.setHorizontalOffset(var3);
         var8.setOverlapAnchor(true);
         var8.setVerticalOffset(var2);
      } else {
         if (this.mHasXOffset) {
            var8.setHorizontalOffset(this.mXOffset);
         }

         if (this.mHasYOffset) {
            var8.setVerticalOffset(this.mYOffset);
         }

         var8.setEpicenterBounds(this.getEpicenterBounds());
      }

      CascadingMenuInfo var15 = new CascadingMenuInfo(var8, var1, this.mLastPosition);
      this.mShowingMenus.add(var15);
      var8.show();
      ListView var16 = var8.getListView();
      var16.setOnKeyListener(this);
      if (var13 == null && this.mShowTitle && var1.getHeaderTitle() != null) {
         FrameLayout var17 = (FrameLayout)var9.inflate(R.layout.abc_popup_menu_header_item_layout, var16, false);
         TextView var14 = (TextView)var17.findViewById(16908310);
         var17.setEnabled(false);
         var14.setText(var1.getHeaderTitle());
         var16.addHeaderView(var17, (Object)null, false);
         var8.show();
      }

   }

   public void addMenu(MenuBuilder var1) {
      var1.addMenuPresenter(this, this.mContext);
      if (this.isShowing()) {
         this.showMenu(var1);
      } else {
         this.mPendingMenus.add(var1);
      }

   }

   protected boolean closeMenuOnSubMenuOpened() {
      return false;
   }

   public void dismiss() {
      int var1 = this.mShowingMenus.size();
      if (var1 > 0) {
         CascadingMenuInfo[] var3 = (CascadingMenuInfo[])this.mShowingMenus.toArray(new CascadingMenuInfo[var1]);
         --var1;

         for(; var1 >= 0; --var1) {
            CascadingMenuInfo var2 = var3[var1];
            if (var2.window.isShowing()) {
               var2.window.dismiss();
            }
         }
      }

   }

   public boolean flagActionItems() {
      return false;
   }

   public ListView getListView() {
      ListView var1;
      if (this.mShowingMenus.isEmpty()) {
         var1 = null;
      } else {
         List var2 = this.mShowingMenus;
         var1 = ((CascadingMenuInfo)var2.get(var2.size() - 1)).getListView();
      }

      return var1;
   }

   public boolean isShowing() {
      int var1 = this.mShowingMenus.size();
      boolean var3 = false;
      boolean var2 = var3;
      if (var1 > 0) {
         var2 = var3;
         if (((CascadingMenuInfo)this.mShowingMenus.get(0)).window.isShowing()) {
            var2 = true;
         }
      }

      return var2;
   }

   public void onCloseMenu(MenuBuilder var1, boolean var2) {
      int var3 = this.findIndexOfAddedMenu(var1);
      if (var3 >= 0) {
         int var4 = var3 + 1;
         if (var4 < this.mShowingMenus.size()) {
            ((CascadingMenuInfo)this.mShowingMenus.get(var4)).menu.close(false);
         }

         CascadingMenuInfo var5 = (CascadingMenuInfo)this.mShowingMenus.remove(var3);
         var5.menu.removeMenuPresenter(this);
         if (this.mShouldCloseImmediately) {
            var5.window.setExitTransition((Object)null);
            var5.window.setAnimationStyle(0);
         }

         var5.window.dismiss();
         var3 = this.mShowingMenus.size();
         if (var3 > 0) {
            this.mLastPosition = ((CascadingMenuInfo)this.mShowingMenus.get(var3 - 1)).position;
         } else {
            this.mLastPosition = this.getInitialMenuPosition();
         }

         if (var3 == 0) {
            this.dismiss();
            MenuPresenter.Callback var7 = this.mPresenterCallback;
            if (var7 != null) {
               var7.onCloseMenu(var1, true);
            }

            ViewTreeObserver var6 = this.mTreeObserver;
            if (var6 != null) {
               if (var6.isAlive()) {
                  this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
               }

               this.mTreeObserver = null;
            }

            this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
            this.mOnDismissListener.onDismiss();
         } else if (var2) {
            ((CascadingMenuInfo)this.mShowingMenus.get(0)).menu.close(false);
         }

      }
   }

   public void onDismiss() {
      int var2 = this.mShowingMenus.size();
      int var1 = 0;

      CascadingMenuInfo var3;
      while(true) {
         if (var1 >= var2) {
            var3 = null;
            break;
         }

         var3 = (CascadingMenuInfo)this.mShowingMenus.get(var1);
         if (!var3.window.isShowing()) {
            break;
         }

         ++var1;
      }

      if (var3 != null) {
         var3.menu.close(false);
      }

   }

   public boolean onKey(View var1, int var2, KeyEvent var3) {
      if (var3.getAction() == 1 && var2 == 82) {
         this.dismiss();
         return true;
      } else {
         return false;
      }
   }

   public void onRestoreInstanceState(Parcelable var1) {
   }

   public Parcelable onSaveInstanceState() {
      return null;
   }

   public boolean onSubMenuSelected(SubMenuBuilder var1) {
      Iterator var2 = this.mShowingMenus.iterator();

      CascadingMenuInfo var3;
      do {
         if (!var2.hasNext()) {
            if (var1.hasVisibleItems()) {
               this.addMenu(var1);
               MenuPresenter.Callback var4 = this.mPresenterCallback;
               if (var4 != null) {
                  var4.onOpenSubMenu(var1);
               }

               return true;
            }

            return false;
         }

         var3 = (CascadingMenuInfo)var2.next();
      } while(var1 != var3.menu);

      var3.getListView().requestFocus();
      return true;
   }

   public void setAnchorView(View var1) {
      if (this.mAnchorView != var1) {
         this.mAnchorView = var1;
         this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(var1));
      }

   }

   public void setCallback(MenuPresenter.Callback var1) {
      this.mPresenterCallback = var1;
   }

   public void setForceShowIcon(boolean var1) {
      this.mForceShowIcon = var1;
   }

   public void setGravity(int var1) {
      if (this.mRawDropDownGravity != var1) {
         this.mRawDropDownGravity = var1;
         this.mDropDownGravity = GravityCompat.getAbsoluteGravity(var1, ViewCompat.getLayoutDirection(this.mAnchorView));
      }

   }

   public void setHorizontalOffset(int var1) {
      this.mHasXOffset = true;
      this.mXOffset = var1;
   }

   public void setOnDismissListener(PopupWindow.OnDismissListener var1) {
      this.mOnDismissListener = var1;
   }

   public void setShowTitle(boolean var1) {
      this.mShowTitle = var1;
   }

   public void setVerticalOffset(int var1) {
      this.mHasYOffset = true;
      this.mYOffset = var1;
   }

   public void show() {
      if (!this.isShowing()) {
         Iterator var2 = this.mPendingMenus.iterator();

         while(var2.hasNext()) {
            this.showMenu((MenuBuilder)var2.next());
         }

         this.mPendingMenus.clear();
         View var3 = this.mAnchorView;
         this.mShownAnchorView = var3;
         if (var3 != null) {
            boolean var1;
            if (this.mTreeObserver == null) {
               var1 = true;
            } else {
               var1 = false;
            }

            ViewTreeObserver var4 = var3.getViewTreeObserver();
            this.mTreeObserver = var4;
            if (var1) {
               var4.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
            }

            this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
         }

      }
   }

   public void updateMenuView(boolean var1) {
      Iterator var2 = this.mShowingMenus.iterator();

      while(var2.hasNext()) {
         toMenuAdapter(((CascadingMenuInfo)var2.next()).getListView().getAdapter()).notifyDataSetChanged();
      }

   }

   private static class CascadingMenuInfo {
      public final MenuBuilder menu;
      public final int position;
      public final MenuPopupWindow window;

      public CascadingMenuInfo(MenuPopupWindow var1, MenuBuilder var2, int var3) {
         this.window = var1;
         this.menu = var2;
         this.position = var3;
      }

      public ListView getListView() {
         return this.window.getListView();
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface HorizPosition {
   }
}
