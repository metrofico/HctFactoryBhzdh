package androidx.recyclerview.widget;

import java.util.List;

public abstract class ListAdapter extends RecyclerView.Adapter {
   final AsyncListDiffer mDiffer;
   private final AsyncListDiffer.ListListener mListener;

   protected ListAdapter(AsyncDifferConfig var1) {
      AsyncListDiffer.ListListener var2 = new AsyncListDiffer.ListListener(this) {
         final ListAdapter this$0;

         {
            this.this$0 = var1;
         }

         public void onCurrentListChanged(List var1, List var2) {
            this.this$0.onCurrentListChanged(var1, var2);
         }
      };
      this.mListener = var2;
      AsyncListDiffer var3 = new AsyncListDiffer(new AdapterListUpdateCallback(this), var1);
      this.mDiffer = var3;
      var3.addListListener(var2);
   }

   protected ListAdapter(DiffUtil.ItemCallback var1) {
      AsyncListDiffer.ListListener var2 = new AsyncListDiffer.ListListener(this) {
         final ListAdapter this$0;

         {
            this.this$0 = var1;
         }

         public void onCurrentListChanged(List var1, List var2) {
            this.this$0.onCurrentListChanged(var1, var2);
         }
      };
      this.mListener = var2;
      AsyncListDiffer var3 = new AsyncListDiffer(new AdapterListUpdateCallback(this), (new AsyncDifferConfig.Builder(var1)).build());
      this.mDiffer = var3;
      var3.addListListener(var2);
   }

   public List getCurrentList() {
      return this.mDiffer.getCurrentList();
   }

   protected Object getItem(int var1) {
      return this.mDiffer.getCurrentList().get(var1);
   }

   public int getItemCount() {
      return this.mDiffer.getCurrentList().size();
   }

   public void onCurrentListChanged(List var1, List var2) {
   }

   public void submitList(List var1) {
      this.mDiffer.submitList(var1);
   }

   public void submitList(List var1, Runnable var2) {
      this.mDiffer.submitList(var1, var2);
   }
}
