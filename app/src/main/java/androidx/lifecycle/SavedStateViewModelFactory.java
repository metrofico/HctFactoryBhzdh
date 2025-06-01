package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class SavedStateViewModelFactory extends ViewModelProvider.KeyedFactory {
   private static final Class[] ANDROID_VIEWMODEL_SIGNATURE = new Class[]{Application.class, SavedStateHandle.class};
   private static final Class[] VIEWMODEL_SIGNATURE = new Class[]{SavedStateHandle.class};
   private final Application mApplication;
   private final Bundle mDefaultArgs;
   private final ViewModelProvider.Factory mFactory;
   private final Lifecycle mLifecycle;
   private final SavedStateRegistry mSavedStateRegistry;

   public SavedStateViewModelFactory(Application var1, SavedStateRegistryOwner var2) {
      this(var1, var2, (Bundle)null);
   }

   public SavedStateViewModelFactory(Application var1, SavedStateRegistryOwner var2, Bundle var3) {
      this.mSavedStateRegistry = var2.getSavedStateRegistry();
      this.mLifecycle = var2.getLifecycle();
      this.mDefaultArgs = var3;
      this.mApplication = var1;
      Object var4;
      if (var1 != null) {
         var4 = ViewModelProvider.AndroidViewModelFactory.getInstance(var1);
      } else {
         var4 = ViewModelProvider.NewInstanceFactory.getInstance();
      }

      this.mFactory = (ViewModelProvider.Factory)var4;
   }

   private static Constructor findMatchingConstructor(Class var0, Class[] var1) {
      Constructor[] var5 = var0.getConstructors();
      int var3 = var5.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         Constructor var4 = var5[var2];
         if (Arrays.equals(var1, var4.getParameterTypes())) {
            return var4;
         }
      }

      return null;
   }

   public ViewModel create(Class var1) {
      String var2 = var1.getCanonicalName();
      if (var2 != null) {
         return this.create(var2, var1);
      } else {
         throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
      }
   }

   public ViewModel create(String var1, Class var2) {
      boolean var3 = AndroidViewModel.class.isAssignableFrom(var2);
      Constructor var4;
      if (var3 && this.mApplication != null) {
         var4 = findMatchingConstructor(var2, ANDROID_VIEWMODEL_SIGNATURE);
      } else {
         var4 = findMatchingConstructor(var2, VIEWMODEL_SIGNATURE);
      }

      if (var4 == null) {
         return this.mFactory.create(var2);
      } else {
         IllegalAccessException var24;
         label64: {
            InstantiationException var23;
            label63: {
               InvocationTargetException var10000;
               label62: {
                  SavedStateHandleController var5;
                  boolean var10001;
                  ViewModel var19;
                  label61: {
                     var5 = SavedStateHandleController.create(this.mSavedStateRegistry, this.mLifecycle, var1, this.mDefaultArgs);
                     if (var3) {
                        Application var18;
                        try {
                           var18 = this.mApplication;
                        } catch (IllegalAccessException var15) {
                           var24 = var15;
                           var10001 = false;
                           break label64;
                        } catch (InstantiationException var16) {
                           var23 = var16;
                           var10001 = false;
                           break label63;
                        } catch (InvocationTargetException var17) {
                           var10000 = var17;
                           var10001 = false;
                           break label62;
                        }

                        if (var18 != null) {
                           try {
                              var19 = (ViewModel)var4.newInstance(var18, var5.getHandle());
                              break label61;
                           } catch (IllegalAccessException var12) {
                              var24 = var12;
                              var10001 = false;
                              break label64;
                           } catch (InstantiationException var13) {
                              var23 = var13;
                              var10001 = false;
                              break label63;
                           } catch (InvocationTargetException var14) {
                              var10000 = var14;
                              var10001 = false;
                              break label62;
                           }
                        }
                     }

                     try {
                        var19 = (ViewModel)var4.newInstance(var5.getHandle());
                     } catch (IllegalAccessException var9) {
                        var24 = var9;
                        var10001 = false;
                        break label64;
                     } catch (InstantiationException var10) {
                        var23 = var10;
                        var10001 = false;
                        break label63;
                     } catch (InvocationTargetException var11) {
                        var10000 = var11;
                        var10001 = false;
                        break label62;
                     }
                  }

                  try {
                     var19.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", var5);
                     return var19;
                  } catch (IllegalAccessException var6) {
                     var24 = var6;
                     var10001 = false;
                     break label64;
                  } catch (InstantiationException var7) {
                     var23 = var7;
                     var10001 = false;
                     break label63;
                  } catch (InvocationTargetException var8) {
                     var10000 = var8;
                     var10001 = false;
                  }
               }

               InvocationTargetException var20 = var10000;
               throw new RuntimeException("An exception happened in constructor of " + var2, var20.getCause());
            }

            InstantiationException var21 = var23;
            throw new RuntimeException("A " + var2 + " cannot be instantiated.", var21);
         }

         IllegalAccessException var22 = var24;
         throw new RuntimeException("Failed to access " + var2, var22);
      }
   }

   void onRequery(ViewModel var1) {
      SavedStateHandleController.attachHandleIfNeeded(var1, this.mSavedStateRegistry, this.mLifecycle);
   }
}
