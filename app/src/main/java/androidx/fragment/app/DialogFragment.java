package androidx.fragment.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;

public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
   private static final String SAVED_BACK_STACK_ID = "android:backStackId";
   private static final String SAVED_CANCELABLE = "android:cancelable";
   private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
   private static final String SAVED_INTERNAL_DIALOG_SHOWING = "android:dialogShowing";
   private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
   private static final String SAVED_STYLE = "android:style";
   private static final String SAVED_THEME = "android:theme";
   public static final int STYLE_NORMAL = 0;
   public static final int STYLE_NO_FRAME = 2;
   public static final int STYLE_NO_INPUT = 3;
   public static final int STYLE_NO_TITLE = 1;
   private int mBackStackId = -1;
   private boolean mCancelable = true;
   private boolean mCreatingDialog;
   private Dialog mDialog;
   private boolean mDialogCreated = false;
   private Runnable mDismissRunnable = new Runnable(this) {
      final DialogFragment this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.mOnDismissListener.onDismiss(this.this$0.mDialog);
      }
   };
   private boolean mDismissed;
   private Handler mHandler;
   private Observer mObserver = new Observer(this) {
      final DialogFragment this$0;

      {
         this.this$0 = var1;
      }

      public void onChanged(LifecycleOwner var1) {
         if (var1 != null && this.this$0.mShowsDialog) {
            View var2 = this.this$0.requireView();
            if (var2.getParent() != null) {
               throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }

            if (this.this$0.mDialog != null) {
               if (FragmentManager.isLoggingEnabled(3)) {
                  Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + this.this$0.mDialog);
               }

               this.this$0.mDialog.setContentView(var2);
            }
         }

      }
   };
   private DialogInterface.OnCancelListener mOnCancelListener = new DialogInterface.OnCancelListener(this) {
      final DialogFragment this$0;

      {
         this.this$0 = var1;
      }

      public void onCancel(DialogInterface var1) {
         if (this.this$0.mDialog != null) {
            DialogFragment var2 = this.this$0;
            var2.onCancel(var2.mDialog);
         }

      }
   };
   private DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener(this) {
      final DialogFragment this$0;

      {
         this.this$0 = var1;
      }

      public void onDismiss(DialogInterface var1) {
         if (this.this$0.mDialog != null) {
            DialogFragment var2 = this.this$0;
            var2.onDismiss(var2.mDialog);
         }

      }
   };
   private boolean mShownByMe;
   private boolean mShowsDialog = true;
   private int mStyle = 0;
   private int mTheme = 0;
   private boolean mViewDestroyed;

   public DialogFragment() {
   }

   public DialogFragment(int var1) {
      super(var1);
   }

   private void dismissInternal(boolean var1, boolean var2) {
      if (!this.mDismissed) {
         this.mDismissed = true;
         this.mShownByMe = false;
         Dialog var3 = this.mDialog;
         if (var3 != null) {
            var3.setOnDismissListener((DialogInterface.OnDismissListener)null);
            this.mDialog.dismiss();
            if (!var2) {
               if (Looper.myLooper() == this.mHandler.getLooper()) {
                  this.onDismiss(this.mDialog);
               } else {
                  this.mHandler.post(this.mDismissRunnable);
               }
            }
         }

         this.mViewDestroyed = true;
         if (this.mBackStackId >= 0) {
            this.getParentFragmentManager().popBackStack(this.mBackStackId, 1);
            this.mBackStackId = -1;
         } else {
            FragmentTransaction var4 = this.getParentFragmentManager().beginTransaction();
            var4.remove(this);
            if (var1) {
               var4.commitAllowingStateLoss();
            } else {
               var4.commit();
            }
         }

      }
   }

   private void prepareDialog(Bundle var1) {
      if (this.mShowsDialog) {
         if (!this.mDialogCreated) {
            try {
               this.mCreatingDialog = true;
               Dialog var4 = this.onCreateDialog(var1);
               this.mDialog = var4;
               if (this.mShowsDialog) {
                  this.setupDialog(var4, this.mStyle);
                  Context var5 = this.getContext();
                  if (var5 instanceof Activity) {
                     this.mDialog.setOwnerActivity((Activity)var5);
                  }

                  this.mDialog.setCancelable(this.mCancelable);
                  this.mDialog.setOnCancelListener(this.mOnCancelListener);
                  this.mDialog.setOnDismissListener(this.mOnDismissListener);
                  this.mDialogCreated = true;
               } else {
                  this.mDialog = null;
               }
            } finally {
               this.mCreatingDialog = false;
            }
         }

      }
   }

   FragmentContainer createFragmentContainer() {
      return new FragmentContainer(this, super.createFragmentContainer()) {
         final DialogFragment this$0;
         final FragmentContainer val$fragmentContainer;

         {
            this.this$0 = var1;
            this.val$fragmentContainer = var2;
         }

         public View onFindViewById(int var1) {
            return this.val$fragmentContainer.onHasView() ? this.val$fragmentContainer.onFindViewById(var1) : this.this$0.onFindViewById(var1);
         }

         public boolean onHasView() {
            boolean var1;
            if (!this.val$fragmentContainer.onHasView() && !this.this$0.onHasView()) {
               var1 = false;
            } else {
               var1 = true;
            }

            return var1;
         }
      };
   }

   public void dismiss() {
      this.dismissInternal(false, false);
   }

   public void dismissAllowingStateLoss() {
      this.dismissInternal(true, false);
   }

   public Dialog getDialog() {
      return this.mDialog;
   }

   public boolean getShowsDialog() {
      return this.mShowsDialog;
   }

   public int getTheme() {
      return this.mTheme;
   }

   public boolean isCancelable() {
      return this.mCancelable;
   }

   public void onAttach(Context var1) {
      super.onAttach(var1);
      this.getViewLifecycleOwnerLiveData().observeForever(this.mObserver);
      if (!this.mShownByMe) {
         this.mDismissed = false;
      }

   }

   public void onCancel(DialogInterface var1) {
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.mHandler = new Handler();
      boolean var2;
      if (this.mContainerId == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.mShowsDialog = var2;
      if (var1 != null) {
         this.mStyle = var1.getInt("android:style", 0);
         this.mTheme = var1.getInt("android:theme", 0);
         this.mCancelable = var1.getBoolean("android:cancelable", true);
         this.mShowsDialog = var1.getBoolean("android:showsDialog", this.mShowsDialog);
         this.mBackStackId = var1.getInt("android:backStackId", -1);
      }

   }

   public Dialog onCreateDialog(Bundle var1) {
      if (FragmentManager.isLoggingEnabled(3)) {
         Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
      }

      return new Dialog(this.requireContext(), this.getTheme());
   }

   public void onDestroyView() {
      super.onDestroyView();
      Dialog var1 = this.mDialog;
      if (var1 != null) {
         this.mViewDestroyed = true;
         var1.setOnDismissListener((DialogInterface.OnDismissListener)null);
         this.mDialog.dismiss();
         if (!this.mDismissed) {
            this.onDismiss(this.mDialog);
         }

         this.mDialog = null;
         this.mDialogCreated = false;
      }

   }

   public void onDetach() {
      super.onDetach();
      if (!this.mShownByMe && !this.mDismissed) {
         this.mDismissed = true;
      }

      this.getViewLifecycleOwnerLiveData().removeObserver(this.mObserver);
   }

   public void onDismiss(DialogInterface var1) {
      if (!this.mViewDestroyed) {
         if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
         }

         this.dismissInternal(true, true);
      }

   }

   View onFindViewById(int var1) {
      Dialog var2 = this.mDialog;
      return var2 != null ? var2.findViewById(var1) : null;
   }

   public LayoutInflater onGetLayoutInflater(Bundle var1) {
      LayoutInflater var2 = super.onGetLayoutInflater(var1);
      if (this.mShowsDialog && !this.mCreatingDialog) {
         this.prepareDialog(var1);
         if (FragmentManager.isLoggingEnabled(2)) {
            Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
         }

         Dialog var3 = this.mDialog;
         LayoutInflater var5 = var2;
         if (var3 != null) {
            var5 = var2.cloneInContext(var3.getContext());
         }

         return var5;
      } else {
         if (FragmentManager.isLoggingEnabled(2)) {
            String var4 = "getting layout inflater for DialogFragment " + this;
            if (!this.mShowsDialog) {
               Log.d("FragmentManager", "mShowsDialog = false: " + var4);
            } else {
               Log.d("FragmentManager", "mCreatingDialog = true: " + var4);
            }
         }

         return var2;
      }
   }

   boolean onHasView() {
      return this.mDialogCreated;
   }

   public void onSaveInstanceState(Bundle var1) {
      super.onSaveInstanceState(var1);
      Dialog var4 = this.mDialog;
      if (var4 != null) {
         Bundle var5 = var4.onSaveInstanceState();
         var5.putBoolean("android:dialogShowing", false);
         var1.putBundle("android:savedDialogState", var5);
      }

      int var2 = this.mStyle;
      if (var2 != 0) {
         var1.putInt("android:style", var2);
      }

      var2 = this.mTheme;
      if (var2 != 0) {
         var1.putInt("android:theme", var2);
      }

      boolean var3 = this.mCancelable;
      if (!var3) {
         var1.putBoolean("android:cancelable", var3);
      }

      var3 = this.mShowsDialog;
      if (!var3) {
         var1.putBoolean("android:showsDialog", var3);
      }

      var2 = this.mBackStackId;
      if (var2 != -1) {
         var1.putInt("android:backStackId", var2);
      }

   }

   public void onStart() {
      super.onStart();
      Dialog var1 = this.mDialog;
      if (var1 != null) {
         this.mViewDestroyed = false;
         var1.show();
         View var2 = this.mDialog.getWindow().getDecorView();
         ViewTreeLifecycleOwner.set(var2, this);
         ViewTreeViewModelStoreOwner.set(var2, this);
         ViewTreeSavedStateRegistryOwner.set(var2, this);
      }

   }

   public void onStop() {
      super.onStop();
      Dialog var1 = this.mDialog;
      if (var1 != null) {
         var1.hide();
      }

   }

   public void onViewStateRestored(Bundle var1) {
      super.onViewStateRestored(var1);
      if (this.mDialog != null && var1 != null) {
         var1 = var1.getBundle("android:savedDialogState");
         if (var1 != null) {
            this.mDialog.onRestoreInstanceState(var1);
         }
      }

   }

   void performCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
      super.performCreateView(var1, var2, var3);
      if (this.mView == null && this.mDialog != null && var3 != null) {
         Bundle var4 = var3.getBundle("android:savedDialogState");
         if (var4 != null) {
            this.mDialog.onRestoreInstanceState(var4);
         }
      }

   }

   public final Dialog requireDialog() {
      Dialog var1 = this.getDialog();
      if (var1 != null) {
         return var1;
      } else {
         throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
      }
   }

   public void setCancelable(boolean var1) {
      this.mCancelable = var1;
      Dialog var2 = this.mDialog;
      if (var2 != null) {
         var2.setCancelable(var1);
      }

   }

   public void setShowsDialog(boolean var1) {
      this.mShowsDialog = var1;
   }

   public void setStyle(int var1, int var2) {
      if (FragmentManager.isLoggingEnabled(2)) {
         Log.d("FragmentManager", "Setting style and theme for DialogFragment " + this + " to " + var1 + ", " + var2);
      }

      this.mStyle = var1;
      if (var1 == 2 || var1 == 3) {
         this.mTheme = 16973913;
      }

      if (var2 != 0) {
         this.mTheme = var2;
      }

   }

   public void setupDialog(Dialog var1, int var2) {
      if (var2 != 1 && var2 != 2) {
         if (var2 != 3) {
            return;
         }

         Window var3 = var1.getWindow();
         if (var3 != null) {
            var3.addFlags(24);
         }
      }

      var1.requestWindowFeature(1);
   }

   public int show(FragmentTransaction var1, String var2) {
      this.mDismissed = false;
      this.mShownByMe = true;
      var1.add(this, var2);
      this.mViewDestroyed = false;
      int var3 = var1.commit();
      this.mBackStackId = var3;
      return var3;
   }

   public void show(FragmentManager var1, String var2) {
      this.mDismissed = false;
      this.mShownByMe = true;
      FragmentTransaction var3 = var1.beginTransaction();
      var3.add(this, var2);
      var3.commit();
   }

   public void showNow(FragmentManager var1, String var2) {
      this.mDismissed = false;
      this.mShownByMe = true;
      FragmentTransaction var3 = var1.beginTransaction();
      var3.add(this, var2);
      var3.commitNow();
   }
}
