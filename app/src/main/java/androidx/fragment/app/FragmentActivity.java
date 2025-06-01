package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.core.app.ActivityCompat;
import androidx.core.app.SharedElementCallback;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;

public class FragmentActivity extends ComponentActivity implements ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompat.RequestPermissionsRequestCodeValidator {
   static final String FRAGMENTS_TAG = "android:support:fragments";
   boolean mCreated;
   final LifecycleRegistry mFragmentLifecycleRegistry = new LifecycleRegistry(this);
   final FragmentController mFragments = FragmentController.createController(new HostCallbacks(this));
   boolean mResumed;
   boolean mStopped = true;

   public FragmentActivity() {
      this.init();
   }

   public FragmentActivity(int var1) {
      super(var1);
      this.init();
   }

   private void init() {
      this.getSavedStateRegistry().registerSavedStateProvider("android:support:fragments", new SavedStateRegistry.SavedStateProvider(this) {
         final FragmentActivity this$0;

         {
            this.this$0 = var1;
         }

         public Bundle saveState() {
            Bundle var1 = new Bundle();
            this.this$0.markFragmentsCreated();
            this.this$0.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
            Parcelable var2 = this.this$0.mFragments.saveAllState();
            if (var2 != null) {
               var1.putParcelable("android:support:fragments", var2);
            }

            return var1;
         }
      });
      this.addOnContextAvailableListener(new OnContextAvailableListener(this) {
         final FragmentActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onContextAvailable(Context var1) {
            this.this$0.mFragments.attachHost((Fragment)null);
            Bundle var2 = this.this$0.getSavedStateRegistry().consumeRestoredStateForKey("android:support:fragments");
            if (var2 != null) {
               Parcelable var3 = var2.getParcelable("android:support:fragments");
               this.this$0.mFragments.restoreSaveState(var3);
            }

         }
      });
   }

   private static boolean markState(FragmentManager var0, Lifecycle.State var1) {
      Iterator var4 = var0.getFragments().iterator();
      boolean var2 = false;

      while(var4.hasNext()) {
         Fragment var5 = (Fragment)var4.next();
         if (var5 != null) {
            boolean var3 = var2;
            if (var5.getHost() != null) {
               var3 = var2 | markState(var5.getChildFragmentManager(), var1);
            }

            var2 = var3;
            if (var5.mViewLifecycleOwner != null) {
               var2 = var3;
               if (var5.mViewLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                  var5.mViewLifecycleOwner.setCurrentState(var1);
                  var2 = true;
               }
            }

            if (var5.mLifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
               var5.mLifecycleRegistry.setCurrentState(var1);
               var2 = true;
            }
         }
      }

      return var2;
   }

   final View dispatchFragmentsOnCreateView(View var1, String var2, Context var3, AttributeSet var4) {
      return this.mFragments.onCreateView(var1, var2, var3, var4);
   }

   public void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      super.dump(var1, var2, var3, var4);
      var3.print(var1);
      var3.print("Local FragmentActivity ");
      var3.print(Integer.toHexString(System.identityHashCode(this)));
      var3.println(" State:");
      String var5 = var1 + "  ";
      var3.print(var5);
      var3.print("mCreated=");
      var3.print(this.mCreated);
      var3.print(" mResumed=");
      var3.print(this.mResumed);
      var3.print(" mStopped=");
      var3.print(this.mStopped);
      if (this.getApplication() != null) {
         LoaderManager.getInstance(this).dump(var5, var2, var3, var4);
      }

      this.mFragments.getSupportFragmentManager().dump(var1, var2, var3, var4);
   }

   public FragmentManager getSupportFragmentManager() {
      return this.mFragments.getSupportFragmentManager();
   }

   @Deprecated
   public LoaderManager getSupportLoaderManager() {
      return LoaderManager.getInstance(this);
   }

   void markFragmentsCreated() {
      while(markState(this.getSupportFragmentManager(), Lifecycle.State.CREATED)) {
      }

   }

   protected void onActivityResult(int var1, int var2, Intent var3) {
      this.mFragments.noteStateNotSaved();
      super.onActivityResult(var1, var2, var3);
   }

   @Deprecated
   public void onAttachFragment(Fragment var1) {
   }

   public void onConfigurationChanged(Configuration var1) {
      this.mFragments.noteStateNotSaved();
      super.onConfigurationChanged(var1);
      this.mFragments.dispatchConfigurationChanged(var1);
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
      this.mFragments.dispatchCreate();
   }

   public boolean onCreatePanelMenu(int var1, Menu var2) {
      return var1 == 0 ? super.onCreatePanelMenu(var1, var2) | this.mFragments.dispatchCreateOptionsMenu(var2, this.getMenuInflater()) : super.onCreatePanelMenu(var1, var2);
   }

   public View onCreateView(View var1, String var2, Context var3, AttributeSet var4) {
      View var5 = this.dispatchFragmentsOnCreateView(var1, var2, var3, var4);
      return var5 == null ? super.onCreateView(var1, var2, var3, var4) : var5;
   }

   public View onCreateView(String var1, Context var2, AttributeSet var3) {
      View var4 = this.dispatchFragmentsOnCreateView((View)null, var1, var2, var3);
      return var4 == null ? super.onCreateView(var1, var2, var3) : var4;
   }

   protected void onDestroy() {
      super.onDestroy();
      this.mFragments.dispatchDestroy();
      this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
   }

   public void onLowMemory() {
      super.onLowMemory();
      this.mFragments.dispatchLowMemory();
   }

   public boolean onMenuItemSelected(int var1, MenuItem var2) {
      if (super.onMenuItemSelected(var1, var2)) {
         return true;
      } else if (var1 != 0) {
         return var1 != 6 ? false : this.mFragments.dispatchContextItemSelected(var2);
      } else {
         return this.mFragments.dispatchOptionsItemSelected(var2);
      }
   }

   public void onMultiWindowModeChanged(boolean var1) {
      this.mFragments.dispatchMultiWindowModeChanged(var1);
   }

   protected void onNewIntent(Intent var1) {
      this.mFragments.noteStateNotSaved();
      super.onNewIntent(var1);
   }

   public void onPanelClosed(int var1, Menu var2) {
      if (var1 == 0) {
         this.mFragments.dispatchOptionsMenuClosed(var2);
      }

      super.onPanelClosed(var1, var2);
   }

   protected void onPause() {
      super.onPause();
      this.mResumed = false;
      this.mFragments.dispatchPause();
      this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
   }

   public void onPictureInPictureModeChanged(boolean var1) {
      this.mFragments.dispatchPictureInPictureModeChanged(var1);
   }

   protected void onPostResume() {
      super.onPostResume();
      this.onResumeFragments();
   }

   @Deprecated
   protected boolean onPrepareOptionsPanel(View var1, Menu var2) {
      return super.onPreparePanel(0, var1, var2);
   }

   public boolean onPreparePanel(int var1, View var2, Menu var3) {
      return var1 == 0 ? this.onPrepareOptionsPanel(var2, var3) | this.mFragments.dispatchPrepareOptionsMenu(var3) : super.onPreparePanel(var1, var2, var3);
   }

   public void onRequestPermissionsResult(int var1, String[] var2, int[] var3) {
      this.mFragments.noteStateNotSaved();
      super.onRequestPermissionsResult(var1, var2, var3);
   }

   protected void onResume() {
      this.mFragments.noteStateNotSaved();
      super.onResume();
      this.mResumed = true;
      this.mFragments.execPendingActions();
   }

   protected void onResumeFragments() {
      this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
      this.mFragments.dispatchResume();
   }

   protected void onStart() {
      this.mFragments.noteStateNotSaved();
      super.onStart();
      this.mStopped = false;
      if (!this.mCreated) {
         this.mCreated = true;
         this.mFragments.dispatchActivityCreated();
      }

      this.mFragments.execPendingActions();
      this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
      this.mFragments.dispatchStart();
   }

   public void onStateNotSaved() {
      this.mFragments.noteStateNotSaved();
   }

   protected void onStop() {
      super.onStop();
      this.mStopped = true;
      this.markFragmentsCreated();
      this.mFragments.dispatchStop();
      this.mFragmentLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
   }

   public void setEnterSharedElementCallback(SharedElementCallback var1) {
      ActivityCompat.setEnterSharedElementCallback(this, var1);
   }

   public void setExitSharedElementCallback(SharedElementCallback var1) {
      ActivityCompat.setExitSharedElementCallback(this, var1);
   }

   public void startActivityFromFragment(Fragment var1, Intent var2, int var3) {
      this.startActivityFromFragment(var1, var2, var3, (Bundle)null);
   }

   public void startActivityFromFragment(Fragment var1, Intent var2, int var3, Bundle var4) {
      if (var3 == -1) {
         ActivityCompat.startActivityForResult(this, var2, -1, var4);
      } else {
         var1.startActivityForResult(var2, var3, var4);
      }
   }

   @Deprecated
   public void startIntentSenderFromFragment(Fragment var1, IntentSender var2, int var3, Intent var4, int var5, int var6, int var7, Bundle var8) throws IntentSender.SendIntentException {
      if (var3 == -1) {
         ActivityCompat.startIntentSenderForResult(this, var2, var3, var4, var5, var6, var7, var8);
      } else {
         var1.startIntentSenderForResult(var2, var3, var4, var5, var6, var7, var8);
      }
   }

   public void supportFinishAfterTransition() {
      ActivityCompat.finishAfterTransition(this);
   }

   @Deprecated
   public void supportInvalidateOptionsMenu() {
      this.invalidateOptionsMenu();
   }

   public void supportPostponeEnterTransition() {
      ActivityCompat.postponeEnterTransition(this);
   }

   public void supportStartPostponedEnterTransition() {
      ActivityCompat.startPostponedEnterTransition(this);
   }

   @Deprecated
   public final void validateRequestPermissionsRequestCode(int var1) {
   }

   class HostCallbacks extends FragmentHostCallback implements ViewModelStoreOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, FragmentOnAttachListener {
      final FragmentActivity this$0;

      public HostCallbacks(FragmentActivity var1) {
         super(var1);
         this.this$0 = var1;
      }

      public ActivityResultRegistry getActivityResultRegistry() {
         return this.this$0.getActivityResultRegistry();
      }

      public Lifecycle getLifecycle() {
         return this.this$0.mFragmentLifecycleRegistry;
      }

      public OnBackPressedDispatcher getOnBackPressedDispatcher() {
         return this.this$0.getOnBackPressedDispatcher();
      }

      public ViewModelStore getViewModelStore() {
         return this.this$0.getViewModelStore();
      }

      public void onAttachFragment(FragmentManager var1, Fragment var2) {
         this.this$0.onAttachFragment(var2);
      }

      public void onDump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
         this.this$0.dump(var1, var2, var3, var4);
      }

      public View onFindViewById(int var1) {
         return this.this$0.findViewById(var1);
      }

      public FragmentActivity onGetHost() {
         return this.this$0;
      }

      public LayoutInflater onGetLayoutInflater() {
         return this.this$0.getLayoutInflater().cloneInContext(this.this$0);
      }

      public int onGetWindowAnimations() {
         Window var2 = this.this$0.getWindow();
         int var1;
         if (var2 == null) {
            var1 = 0;
         } else {
            var1 = var2.getAttributes().windowAnimations;
         }

         return var1;
      }

      public boolean onHasView() {
         Window var2 = this.this$0.getWindow();
         boolean var1;
         if (var2 != null && var2.peekDecorView() != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean onHasWindowAnimations() {
         boolean var1;
         if (this.this$0.getWindow() != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean onShouldSaveFragmentState(Fragment var1) {
         return this.this$0.isFinishing() ^ true;
      }

      public boolean onShouldShowRequestPermissionRationale(String var1) {
         return ActivityCompat.shouldShowRequestPermissionRationale(this.this$0, var1);
      }

      public void onSupportInvalidateOptionsMenu() {
         this.this$0.supportInvalidateOptionsMenu();
      }
   }
}
