package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class SavedStateHandle {
   private static final Class[] ACCEPTABLE_CLASSES;
   private static final String KEYS = "keys";
   private static final String VALUES = "values";
   private final Map mLiveDatas = new HashMap();
   final Map mRegular;
   private final SavedStateRegistry.SavedStateProvider mSavedStateProvider = new SavedStateRegistry.SavedStateProvider(this) {
      final SavedStateHandle this$0;

      {
         this.this$0 = var1;
      }

      public Bundle saveState() {
         Iterator var1 = (new HashMap(this.this$0.mSavedStateProviders)).entrySet().iterator();

         while(var1.hasNext()) {
            Map.Entry var3 = (Map.Entry)var1.next();
            Bundle var2 = ((SavedStateRegistry.SavedStateProvider)var3.getValue()).saveState();
            this.this$0.set((String)var3.getKey(), var2);
         }

         Set var7 = this.this$0.mRegular.keySet();
         ArrayList var5 = new ArrayList(var7.size());
         ArrayList var6 = new ArrayList(var5.size());
         Iterator var4 = var7.iterator();

         while(var4.hasNext()) {
            String var8 = (String)var4.next();
            var5.add(var8);
            var6.add(this.this$0.mRegular.get(var8));
         }

         Bundle var9 = new Bundle();
         var9.putParcelableArrayList("keys", var5);
         var9.putParcelableArrayList("values", var6);
         return var9;
      }
   };
   final Map mSavedStateProviders = new HashMap();

   static {
      Class var2 = Boolean.TYPE;
      Class var7 = Double.TYPE;
      Class var5 = Integer.TYPE;
      Class var9 = Long.TYPE;
      Class var8 = Byte.TYPE;
      Class var6 = Character.TYPE;
      Class var3 = Float.TYPE;
      Class var4 = Short.TYPE;
      Class var0;
      if (VERSION.SDK_INT >= 21) {
         var0 = Size.class;
      } else {
         var0 = Integer.TYPE;
      }

      Class var1;
      if (VERSION.SDK_INT >= 21) {
         var1 = SizeF.class;
      } else {
         var1 = Integer.TYPE;
      }

      ACCEPTABLE_CLASSES = new Class[]{var2, boolean[].class, var7, double[].class, var5, int[].class, var9, long[].class, String.class, String[].class, Binder.class, Bundle.class, var8, byte[].class, var6, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, var3, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, var4, short[].class, SparseArray.class, var0, var1};
   }

   public SavedStateHandle() {
      this.mRegular = new HashMap();
   }

   public SavedStateHandle(Map var1) {
      this.mRegular = new HashMap(var1);
   }

   static SavedStateHandle createHandle(Bundle var0, Bundle var1) {
      if (var0 == null && var1 == null) {
         return new SavedStateHandle();
      } else {
         HashMap var3 = new HashMap();
         if (var1 != null) {
            Iterator var5 = var1.keySet().iterator();

            while(var5.hasNext()) {
               String var4 = (String)var5.next();
               var3.put(var4, var1.get(var4));
            }
         }

         if (var0 == null) {
            return new SavedStateHandle(var3);
         } else {
            ArrayList var7 = var0.getParcelableArrayList("keys");
            ArrayList var6 = var0.getParcelableArrayList("values");
            if (var7 != null && var6 != null && var7.size() == var6.size()) {
               for(int var2 = 0; var2 < var7.size(); ++var2) {
                  var3.put((String)var7.get(var2), var6.get(var2));
               }

               return new SavedStateHandle(var3);
            } else {
               throw new IllegalStateException("Invalid bundle passed as restored state");
            }
         }
      }
   }

   private MutableLiveData getLiveDataInternal(String var1, boolean var2, Object var3) {
      MutableLiveData var4 = (MutableLiveData)this.mLiveDatas.get(var1);
      if (var4 != null) {
         return var4;
      } else {
         SavingStateLiveData var5;
         if (this.mRegular.containsKey(var1)) {
            var5 = new SavingStateLiveData(this, var1, this.mRegular.get(var1));
         } else if (var2) {
            var5 = new SavingStateLiveData(this, var1, var3);
         } else {
            var5 = new SavingStateLiveData(this, var1);
         }

         this.mLiveDatas.put(var1, var5);
         return var5;
      }
   }

   private static void validateValue(Object var0) {
      if (var0 != null) {
         Class[] var3 = ACCEPTABLE_CLASSES;
         int var2 = var3.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            if (var3[var1].isInstance(var0)) {
               return;
            }
         }

         throw new IllegalArgumentException("Can't put value with type " + var0.getClass() + " into saved state");
      }
   }

   public void clearSavedStateProvider(String var1) {
      this.mSavedStateProviders.remove(var1);
   }

   public boolean contains(String var1) {
      return this.mRegular.containsKey(var1);
   }

   public Object get(String var1) {
      return this.mRegular.get(var1);
   }

   public MutableLiveData getLiveData(String var1) {
      return this.getLiveDataInternal(var1, false, (Object)null);
   }

   public MutableLiveData getLiveData(String var1, Object var2) {
      return this.getLiveDataInternal(var1, true, var2);
   }

   public Set keys() {
      HashSet var1 = new HashSet(this.mRegular.keySet());
      var1.addAll(this.mSavedStateProviders.keySet());
      var1.addAll(this.mLiveDatas.keySet());
      return var1;
   }

   public Object remove(String var1) {
      Object var2 = this.mRegular.remove(var1);
      SavingStateLiveData var3 = (SavingStateLiveData)this.mLiveDatas.remove(var1);
      if (var3 != null) {
         var3.detach();
      }

      return var2;
   }

   SavedStateRegistry.SavedStateProvider savedStateProvider() {
      return this.mSavedStateProvider;
   }

   public void set(String var1, Object var2) {
      validateValue(var2);
      MutableLiveData var3 = (MutableLiveData)this.mLiveDatas.get(var1);
      if (var3 != null) {
         var3.setValue(var2);
      } else {
         this.mRegular.put(var1, var2);
      }

   }

   public void setSavedStateProvider(String var1, SavedStateRegistry.SavedStateProvider var2) {
      this.mSavedStateProviders.put(var1, var2);
   }

   static class SavingStateLiveData extends MutableLiveData {
      private SavedStateHandle mHandle;
      private String mKey;

      SavingStateLiveData(SavedStateHandle var1, String var2) {
         this.mKey = var2;
         this.mHandle = var1;
      }

      SavingStateLiveData(SavedStateHandle var1, String var2, Object var3) {
         super(var3);
         this.mKey = var2;
         this.mHandle = var1;
      }

      void detach() {
         this.mHandle = null;
      }

      public void setValue(Object var1) {
         SavedStateHandle var2 = this.mHandle;
         if (var2 != null) {
            var2.mRegular.put(this.mKey, var1);
         }

         super.setValue(var1);
      }
   }
}
