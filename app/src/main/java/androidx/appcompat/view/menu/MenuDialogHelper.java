package androidx.appcompat.view.menu;

import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.R;
import androidx.appcompat.app.AlertDialog;

class MenuDialogHelper implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, MenuPresenter.Callback {
   private AlertDialog mDialog;
   private MenuBuilder mMenu;
   ListMenuPresenter mPresenter;
   private MenuPresenter.Callback mPresenterCallback;

   public MenuDialogHelper(MenuBuilder var1) {
      this.mMenu = var1;
   }

   public void dismiss() {
      AlertDialog var1 = this.mDialog;
      if (var1 != null) {
         var1.dismiss();
      }

   }

   public void onClick(DialogInterface var1, int var2) {
      this.mMenu.performItemAction((MenuItemImpl)this.mPresenter.getAdapter().getItem(var2), 0);
   }

   public void onCloseMenu(MenuBuilder var1, boolean var2) {
      if (var2 || var1 == this.mMenu) {
         this.dismiss();
      }

      MenuPresenter.Callback var3 = this.mPresenterCallback;
      if (var3 != null) {
         var3.onCloseMenu(var1, var2);
      }

   }

   public void onDismiss(DialogInterface var1) {
      this.mPresenter.onCloseMenu(this.mMenu, true);
   }

   public boolean onKey(DialogInterface var1, int var2, KeyEvent var3) {
      if (var2 == 82 || var2 == 4) {
         if (var3.getAction() == 0 && var3.getRepeatCount() == 0) {
            Window var5 = this.mDialog.getWindow();
            if (var5 != null) {
               View var6 = var5.getDecorView();
               if (var6 != null) {
                  KeyEvent.DispatcherState var7 = var6.getKeyDispatcherState();
                  if (var7 != null) {
                     var7.startTracking(var3, this);
                     return true;
                  }
               }
            }
         } else if (var3.getAction() == 1 && !var3.isCanceled()) {
            Window var4 = this.mDialog.getWindow();
            if (var4 != null) {
               View var8 = var4.getDecorView();
               if (var8 != null) {
                  KeyEvent.DispatcherState var9 = var8.getKeyDispatcherState();
                  if (var9 != null && var9.isTracking(var3)) {
                     this.mMenu.close(true);
                     var1.dismiss();
                     return true;
                  }
               }
            }
         }
      }

      return this.mMenu.performShortcut(var2, var3, 0);
   }

   public boolean onOpenSubMenu(MenuBuilder var1) {
      MenuPresenter.Callback var2 = this.mPresenterCallback;
      return var2 != null ? var2.onOpenSubMenu(var1) : false;
   }

   public void setPresenterCallback(MenuPresenter.Callback var1) {
      this.mPresenterCallback = var1;
   }

   public void show(IBinder var1) {
      MenuBuilder var3 = this.mMenu;
      AlertDialog.Builder var2 = new AlertDialog.Builder(var3.getContext());
      ListMenuPresenter var4 = new ListMenuPresenter(var2.getContext(), R.layout.abc_list_menu_item_layout);
      this.mPresenter = var4;
      var4.setCallback(this);
      this.mMenu.addMenuPresenter(this.mPresenter);
      var2.setAdapter(this.mPresenter.getAdapter(), this);
      View var7 = var3.getHeaderView();
      if (var7 != null) {
         var2.setCustomTitle(var7);
      } else {
         var2.setIcon(var3.getHeaderIcon()).setTitle(var3.getHeaderTitle());
      }

      var2.setOnKeyListener(this);
      AlertDialog var5 = var2.create();
      this.mDialog = var5;
      var5.setOnDismissListener(this);
      WindowManager.LayoutParams var6 = this.mDialog.getWindow().getAttributes();
      var6.type = 1003;
      if (var1 != null) {
         var6.token = var1;
      }

      var6.flags |= 131072;
      this.mDialog.show();
   }
}
