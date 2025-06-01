package androidx.lifecycle;

import androidx.arch.core.util.Function;

public class Transformations {
   private Transformations() {
   }

   public static LiveData map(LiveData var0, Function var1) {
      MediatorLiveData var2 = new MediatorLiveData();
      var2.addSource(var0, new Observer(var2, var1) {
         final Function val$mapFunction;
         final MediatorLiveData val$result;

         {
            this.val$result = var1;
            this.val$mapFunction = var2;
         }

         public void onChanged(Object var1) {
            this.val$result.setValue(this.val$mapFunction.apply(var1));
         }
      });
      return var2;
   }

   public static LiveData switchMap(LiveData var0, Function var1) {
      MediatorLiveData var2 = new MediatorLiveData();
      var2.addSource(var0, new Observer(var1, var2) {
         LiveData mSource;
         final MediatorLiveData val$result;
         final Function val$switchMapFunction;

         {
            this.val$switchMapFunction = var1;
            this.val$result = var2;
         }

         public void onChanged(Object var1) {
            LiveData var3 = (LiveData)this.val$switchMapFunction.apply(var1);
            LiveData var2 = this.mSource;
            if (var2 != var3) {
               if (var2 != null) {
                  this.val$result.removeSource(var2);
               }

               this.mSource = var3;
               if (var3 != null) {
                  this.val$result.addSource(var3, new Observer(this) {
                     final <undefinedtype> this$0;

                     {
                        this.this$0 = var1;
                     }

                     public void onChanged(Object var1) {
                        this.this$0.val$result.setValue(var1);
                     }
                  });
               }

            }
         }
      });
      return var2;
   }
}
