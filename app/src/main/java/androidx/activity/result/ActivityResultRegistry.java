package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public abstract class ActivityResultRegistry {
   private static final int INITIAL_REQUEST_CODE_VALUE = 65536;
   private static final String KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS = "KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS";
   private static final String KEY_COMPONENT_ACTIVITY_PENDING_RESULTS = "KEY_COMPONENT_ACTIVITY_PENDING_RESULT";
   private static final String KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT = "KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT";
   private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS = "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS";
   private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_RCS = "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS";
   private static final String LOG_TAG = "ActivityResultRegistry";
   final transient Map mKeyToCallback = new HashMap();
   private final Map mKeyToLifecycleContainers = new HashMap();
   final Map mKeyToRc = new HashMap();
   ArrayList mLaunchedKeys = new ArrayList();
   final Map mParsedPendingResults = new HashMap();
   final Bundle mPendingResults = new Bundle();
   private Random mRandom = new Random();
   private final Map mRcToKey = new HashMap();

   private void bindRcKey(int var1, String var2) {
      this.mRcToKey.put(var1, var2);
      this.mKeyToRc.put(var2, var1);
   }

   private void doDispatch(String var1, int var2, Intent var3, CallbackAndContract var4) {
      if (var4 != null && var4.mCallback != null) {
         var4.mCallback.onActivityResult(var4.mContract.parseResult(var2, var3));
      } else {
         this.mParsedPendingResults.remove(var1);
         this.mPendingResults.putParcelable(var1, new ActivityResult(var2, var3));
      }

   }

   private int generateRandomNumber() {
      int var1 = this.mRandom.nextInt(2147418112);

      while(true) {
         var1 += 65536;
         if (!this.mRcToKey.containsKey(var1)) {
            return var1;
         }

         var1 = this.mRandom.nextInt(2147418112);
      }
   }

   private int registerKey(String var1) {
      Integer var3 = (Integer)this.mKeyToRc.get(var1);
      if (var3 != null) {
         return var3;
      } else {
         int var2 = this.generateRandomNumber();
         this.bindRcKey(var2, var1);
         return var2;
      }
   }

   public final boolean dispatchResult(int var1, int var2, Intent var3) {
      String var4 = (String)this.mRcToKey.get(var1);
      if (var4 == null) {
         return false;
      } else {
         this.mLaunchedKeys.remove(var4);
         this.doDispatch(var4, var2, var3, (CallbackAndContract)this.mKeyToCallback.get(var4));
         return true;
      }
   }

   public final boolean dispatchResult(int var1, Object var2) {
      String var4 = (String)this.mRcToKey.get(var1);
      if (var4 == null) {
         return false;
      } else {
         this.mLaunchedKeys.remove(var4);
         CallbackAndContract var3 = (CallbackAndContract)this.mKeyToCallback.get(var4);
         if (var3 != null && var3.mCallback != null) {
            var3.mCallback.onActivityResult(var2);
         } else {
            this.mPendingResults.remove(var4);
            this.mParsedPendingResults.put(var4, var2);
         }

         return true;
      }
   }

   public abstract void onLaunch(int var1, ActivityResultContract var2, Object var3, ActivityOptionsCompat var4);

   public final void onRestoreInstanceState(Bundle var1) {
      if (var1 != null) {
         ArrayList var4 = var1.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
         ArrayList var3 = var1.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
         if (var3 != null && var4 != null) {
            this.mLaunchedKeys = var1.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            this.mRandom = (Random)var1.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
            this.mPendingResults.putAll(var1.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));

            for(int var2 = 0; var2 < var3.size(); ++var2) {
               String var5 = (String)var3.get(var2);
               if (this.mKeyToRc.containsKey(var5)) {
                  Integer var6 = (Integer)this.mKeyToRc.remove(var5);
                  if (!this.mPendingResults.containsKey(var5)) {
                     this.mRcToKey.remove(var6);
                  }
               }

               this.bindRcKey((Integer)var4.get(var2), (String)var3.get(var2));
            }
         }

      }
   }

   public final void onSaveInstanceState(Bundle var1) {
      var1.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList(this.mKeyToRc.values()));
      var1.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList(this.mKeyToRc.keySet()));
      var1.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList(this.mLaunchedKeys));
      var1.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle)this.mPendingResults.clone());
      var1.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.mRandom);
   }

   public final ActivityResultLauncher register(String var1, ActivityResultContract var2, ActivityResultCallback var3) {
      int var4 = this.registerKey(var1);
      this.mKeyToCallback.put(var1, new CallbackAndContract(var3, var2));
      if (this.mParsedPendingResults.containsKey(var1)) {
         Object var5 = this.mParsedPendingResults.get(var1);
         this.mParsedPendingResults.remove(var1);
         var3.onActivityResult(var5);
      }

      ActivityResult var6 = (ActivityResult)this.mPendingResults.getParcelable(var1);
      if (var6 != null) {
         this.mPendingResults.remove(var1);
         var3.onActivityResult(var2.parseResult(var6.getResultCode(), var6.getData()));
      }

      return new ActivityResultLauncher(this, var1, var4, var2) {
         final ActivityResultRegistry this$0;
         final ActivityResultContract val$contract;
         final String val$key;
         final int val$requestCode;

         {
            this.this$0 = var1;
            this.val$key = var2;
            this.val$requestCode = var3;
            this.val$contract = var4;
         }

         public ActivityResultContract getContract() {
            return this.val$contract;
         }

         public void launch(Object var1, ActivityOptionsCompat var2) {
            this.this$0.mLaunchedKeys.add(this.val$key);
            Integer var5 = (Integer)this.this$0.mKeyToRc.get(this.val$key);
            ActivityResultRegistry var4 = this.this$0;
            int var3;
            if (var5 != null) {
               var3 = var5;
            } else {
               var3 = this.val$requestCode;
            }

            var4.onLaunch(var3, this.val$contract, var1, var2);
         }

         public void unregister() {
            this.this$0.unregister(this.val$key);
         }
      };
   }

   public final ActivityResultLauncher register(String var1, LifecycleOwner var2, ActivityResultContract var3, ActivityResultCallback var4) {
      Lifecycle var7 = var2.getLifecycle();
      if (!var7.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
         int var5 = this.registerKey(var1);
         LifecycleContainer var6 = (LifecycleContainer)this.mKeyToLifecycleContainers.get(var1);
         LifecycleContainer var8 = var6;
         if (var6 == null) {
            var8 = new LifecycleContainer(var7);
         }

         var8.addObserver(new LifecycleEventObserver(this, var1, var4, var3) {
            final ActivityResultRegistry this$0;
            final ActivityResultCallback val$callback;
            final ActivityResultContract val$contract;
            final String val$key;

            {
               this.this$0 = var1;
               this.val$key = var2;
               this.val$callback = var3;
               this.val$contract = var4;
            }

            public void onStateChanged(LifecycleOwner var1, Lifecycle.Event var2) {
               if (Lifecycle.Event.ON_START.equals(var2)) {
                  this.this$0.mKeyToCallback.put(this.val$key, new CallbackAndContract(this.val$callback, this.val$contract));
                  if (this.this$0.mParsedPendingResults.containsKey(this.val$key)) {
                     Object var3 = this.this$0.mParsedPendingResults.get(this.val$key);
                     this.this$0.mParsedPendingResults.remove(this.val$key);
                     this.val$callback.onActivityResult(var3);
                  }

                  ActivityResult var4 = (ActivityResult)this.this$0.mPendingResults.getParcelable(this.val$key);
                  if (var4 != null) {
                     this.this$0.mPendingResults.remove(this.val$key);
                     this.val$callback.onActivityResult(this.val$contract.parseResult(var4.getResultCode(), var4.getData()));
                  }
               } else if (Lifecycle.Event.ON_STOP.equals(var2)) {
                  this.this$0.mKeyToCallback.remove(this.val$key);
               } else if (Lifecycle.Event.ON_DESTROY.equals(var2)) {
                  this.this$0.unregister(this.val$key);
               }

            }
         });
         this.mKeyToLifecycleContainers.put(var1, var8);
         return new ActivityResultLauncher(this, var1, var5, var3) {
            final ActivityResultRegistry this$0;
            final ActivityResultContract val$contract;
            final String val$key;
            final int val$requestCode;

            {
               this.this$0 = var1;
               this.val$key = var2;
               this.val$requestCode = var3;
               this.val$contract = var4;
            }

            public ActivityResultContract getContract() {
               return this.val$contract;
            }

            public void launch(Object var1, ActivityOptionsCompat var2) {
               this.this$0.mLaunchedKeys.add(this.val$key);
               Integer var4 = (Integer)this.this$0.mKeyToRc.get(this.val$key);
               ActivityResultRegistry var5 = this.this$0;
               int var3;
               if (var4 != null) {
                  var3 = var4;
               } else {
                  var3 = this.val$requestCode;
               }

               var5.onLaunch(var3, this.val$contract, var1, var2);
            }

            public void unregister() {
               this.this$0.unregister(this.val$key);
            }
         };
      } else {
         throw new IllegalStateException("LifecycleOwner " + var2 + " is attempting to register while current state is " + var7.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
      }
   }

   final void unregister(String var1) {
      if (!this.mLaunchedKeys.contains(var1)) {
         Integer var2 = (Integer)this.mKeyToRc.remove(var1);
         if (var2 != null) {
            this.mRcToKey.remove(var2);
         }
      }

      this.mKeyToCallback.remove(var1);
      if (this.mParsedPendingResults.containsKey(var1)) {
         Log.w("ActivityResultRegistry", "Dropping pending result for request " + var1 + ": " + this.mParsedPendingResults.get(var1));
         this.mParsedPendingResults.remove(var1);
      }

      if (this.mPendingResults.containsKey(var1)) {
         Log.w("ActivityResultRegistry", "Dropping pending result for request " + var1 + ": " + this.mPendingResults.getParcelable(var1));
         this.mPendingResults.remove(var1);
      }

      LifecycleContainer var3 = (LifecycleContainer)this.mKeyToLifecycleContainers.get(var1);
      if (var3 != null) {
         var3.clearObservers();
         this.mKeyToLifecycleContainers.remove(var1);
      }

   }

   private static class CallbackAndContract {
      final ActivityResultCallback mCallback;
      final ActivityResultContract mContract;

      CallbackAndContract(ActivityResultCallback var1, ActivityResultContract var2) {
         this.mCallback = var1;
         this.mContract = var2;
      }
   }

   private static class LifecycleContainer {
      final Lifecycle mLifecycle;
      private final ArrayList mObservers;

      LifecycleContainer(Lifecycle var1) {
         this.mLifecycle = var1;
         this.mObservers = new ArrayList();
      }

      void addObserver(LifecycleEventObserver var1) {
         this.mLifecycle.addObserver(var1);
         this.mObservers.add(var1);
      }

      void clearObservers() {
         Iterator var1 = this.mObservers.iterator();

         while(var1.hasNext()) {
            LifecycleEventObserver var2 = (LifecycleEventObserver)var1.next();
            this.mLifecycle.removeObserver(var2);
         }

         this.mObservers.clear();
      }
   }
}
