package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider {
   private static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";
   private final Factory mFactory;
   private final ViewModelStore mViewModelStore;

   public ViewModelProvider(ViewModelStore var1, Factory var2) {
      this.mFactory = var2;
      this.mViewModelStore = var1;
   }

   public ViewModelProvider(ViewModelStoreOwner var1) {
      ViewModelStore var2 = var1.getViewModelStore();
      Object var3;
      if (var1 instanceof HasDefaultViewModelProviderFactory) {
         var3 = ((HasDefaultViewModelProviderFactory)var1).getDefaultViewModelProviderFactory();
      } else {
         var3 = ViewModelProvider.NewInstanceFactory.getInstance();
      }

      this((ViewModelStore)var2, (Factory)var3);
   }

   public ViewModelProvider(ViewModelStoreOwner var1, Factory var2) {
      this(var1.getViewModelStore(), var2);
   }

   public ViewModel get(Class var1) {
      String var2 = var1.getCanonicalName();
      if (var2 != null) {
         return this.get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + var2, var1);
      } else {
         throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
      }
   }

   public ViewModel get(String var1, Class var2) {
      ViewModel var3 = this.mViewModelStore.get(var1);
      if (var2.isInstance(var3)) {
         Factory var4 = this.mFactory;
         if (var4 instanceof OnRequeryFactory) {
            ((OnRequeryFactory)var4).onRequery(var3);
         }

         return var3;
      } else {
         Factory var6 = this.mFactory;
         ViewModel var5;
         if (var6 instanceof KeyedFactory) {
            var5 = ((KeyedFactory)var6).create(var1, var2);
         } else {
            var5 = var6.create(var2);
         }

         this.mViewModelStore.put(var1, var5);
         return var5;
      }
   }

   public static class AndroidViewModelFactory extends NewInstanceFactory {
      private static AndroidViewModelFactory sInstance;
      private Application mApplication;

      public AndroidViewModelFactory(Application var1) {
         this.mApplication = var1;
      }

      public static AndroidViewModelFactory getInstance(Application var0) {
         if (sInstance == null) {
            sInstance = new AndroidViewModelFactory(var0);
         }

         return sInstance;
      }

      public ViewModel create(Class var1) {
         if (AndroidViewModel.class.isAssignableFrom(var1)) {
            try {
               ViewModel var2 = (ViewModel)var1.getConstructor(Application.class).newInstance(this.mApplication);
               return var2;
            } catch (NoSuchMethodException var3) {
               throw new RuntimeException("Cannot create an instance of " + var1, var3);
            } catch (IllegalAccessException var4) {
               throw new RuntimeException("Cannot create an instance of " + var1, var4);
            } catch (InstantiationException var5) {
               throw new RuntimeException("Cannot create an instance of " + var1, var5);
            } catch (InvocationTargetException var6) {
               throw new RuntimeException("Cannot create an instance of " + var1, var6);
            }
         } else {
            return super.create(var1);
         }
      }
   }

   public interface Factory {
      ViewModel create(Class var1);
   }

   abstract static class KeyedFactory extends OnRequeryFactory implements Factory {
      public ViewModel create(Class var1) {
         throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
      }

      public abstract ViewModel create(String var1, Class var2);
   }

   public static class NewInstanceFactory implements Factory {
      private static NewInstanceFactory sInstance;

      static NewInstanceFactory getInstance() {
         if (sInstance == null) {
            sInstance = new NewInstanceFactory();
         }

         return sInstance;
      }

      public ViewModel create(Class var1) {
         try {
            ViewModel var2 = (ViewModel)var1.newInstance();
            return var2;
         } catch (InstantiationException var3) {
            throw new RuntimeException("Cannot create an instance of " + var1, var3);
         } catch (IllegalAccessException var4) {
            throw new RuntimeException("Cannot create an instance of " + var1, var4);
         }
      }
   }

   static class OnRequeryFactory {
      void onRequery(ViewModel var1) {
      }
   }
}
