package androidx.appcompat.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.ShowableListMenu;

public class PopupMenu {
   private final View mAnchor;
   private final Context mContext;
   private View.OnTouchListener mDragListener;
   private final MenuBuilder mMenu;
   OnMenuItemClickListener mMenuItemClickListener;
   OnDismissListener mOnDismissListener;
   final MenuPopupHelper mPopup;

   public PopupMenu(Context var1, View var2) {
      this(var1, var2, 0);
   }

   public PopupMenu(Context var1, View var2, int var3) {
      this(var1, var2, var3, R.attr.popupMenuStyle, 0);
   }

   public PopupMenu(Context var1, View var2, int var3, int var4, int var5) {
      this.mContext = var1;
      this.mAnchor = var2;
      MenuBuilder var6 = new MenuBuilder(var1);
      this.mMenu = var6;
      var6.setCallback(new MenuBuilder.Callback(this) {
         final PopupMenu this$0;

         {
            this.this$0 = var1;
         }

         public boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2) {
            return this.this$0.mMenuItemClickListener != null ? this.this$0.mMenuItemClickListener.onMenuItemClick(var2) : false;
         }

         public void onMenuModeChange(MenuBuilder var1) {
         }
      });
      MenuPopupHelper var7 = new MenuPopupHelper(var1, var6, var2, false, var4, var5);
      this.mPopup = var7;
      var7.setGravity(var3);
      var7.setOnDismissListener(new PopupWindow.OnDismissListener(this) {
         final PopupMenu this$0;

         {
            this.this$0 = var1;
         }

         public void onDismiss() {
            if (this.this$0.mOnDismissListener != null) {
               this.this$0.mOnDismissListener.onDismiss(this.this$0);
            }

         }
      });
   }

   public void dismiss() {
      this.mPopup.dismiss();
   }

   public View.OnTouchListener getDragToOpenListener() {
      if (this.mDragListener == null) {
         this.mDragListener = new ForwardingListener(this, this.mAnchor) {
            final PopupMenu this$0;

            {
               this.this$0 = var1;
            }

            public ShowableListMenu getPopup() {
               return this.this$0.mPopup.getPopup();
            }

            protected boolean onForwardingStarted() {
               this.this$0.show();
               return true;
            }

            protected boolean onForwardingStopped() {
               this.this$0.dismiss();
               return true;
            }
         };
      }

      return this.mDragListener;
   }

   public int getGravity() {
      return this.mPopup.getGravity();
   }

   public Menu getMenu() {
      return this.mMenu;
   }

   public MenuInflater getMenuInflater() {
      return new SupportMenuInflater(this.mContext);
   }

   ListView getMenuListView() {
      return !this.mPopup.isShowing() ? null : this.mPopup.getListView();
   }

   public void inflate(int var1) {
      this.getMenuInflater().inflate(var1, this.mMenu);
   }

   public void setForceShowIcon(boolean var1) {
      this.mPopup.setForceShowIcon(var1);
   }

   public void setGravity(int var1) {
      this.mPopup.setGravity(var1);
   }

   public void setOnDismissListener(OnDismissListener var1) {
      this.mOnDismissListener = var1;
   }

   public void setOnMenuItemClickListener(OnMenuItemClickListener var1) {
      this.mMenuItemClickListener = var1;
   }

   public void show() {
      this.mPopup.show();
   }

   public interface OnDismissListener {
      void onDismiss(PopupMenu var1);
   }

   public interface OnMenuItemClickListener {
      boolean onMenuItemClick(MenuItem var1);
   }
}
