package androidx.lifecycle;

import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;

public class MediatorLiveData extends MutableLiveData {
   private SafeIterableMap mSources = new SafeIterableMap();

   public void addSource(LiveData var1, Observer var2) {
      Source var3 = new Source(var1, var2);
      Source var4 = (Source)this.mSources.putIfAbsent(var1, var3);
      if (var4 != null && var4.mObserver != var2) {
         throw new IllegalArgumentException("This source was already added with the different observer");
      } else if (var4 == null) {
         if (this.hasActiveObservers()) {
            var3.plug();
         }

      }
   }

   protected void onActive() {
      Iterator var1 = this.mSources.iterator();

      while(var1.hasNext()) {
         ((Source)((Map.Entry)var1.next()).getValue()).plug();
      }

   }

   protected void onInactive() {
      Iterator var1 = this.mSources.iterator();

      while(var1.hasNext()) {
         ((Source)((Map.Entry)var1.next()).getValue()).unplug();
      }

   }

   public void removeSource(LiveData var1) {
      Source var2 = (Source)this.mSources.remove(var1);
      if (var2 != null) {
         var2.unplug();
      }

   }

   private static class Source implements Observer {
      final LiveData mLiveData;
      final Observer mObserver;
      int mVersion = -1;

      Source(LiveData var1, Observer var2) {
         this.mLiveData = var1;
         this.mObserver = var2;
      }

      public void onChanged(Object var1) {
         if (this.mVersion != this.mLiveData.getVersion()) {
            this.mVersion = this.mLiveData.getVersion();
            this.mObserver.onChanged(var1);
         }

      }

      void plug() {
         this.mLiveData.observeForever(this);
      }

      void unplug() {
         this.mLiveData.removeObserver(this);
      }
   }
}
