package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.bean.TmpsChooseBean;
import java.util.List;

public class TmpsAdapter extends RecyclerView.Adapter {
   private List lists;
   private Context mContext;
   private OnItemClickListener mOnItemClickListener;

   public TmpsAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.lists = var2;
   }

   public int getItemCount() {
      return this.lists.size();
   }

   // $FF: synthetic method
   void lambda$onBindViewHolder$0$com_hzbhd_canbus_car_cus__283_adapter_TmpsAdapter(int var1, View var2) {
      OnItemClickListener var3 = this.mOnItemClickListener;
      if (var3 != null) {
         var3.click(var1);
      }

   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.imageView.setImageResource(((TmpsChooseBean)this.lists.get(var2)).getImage());
      var1.textView.setText(((TmpsChooseBean)this.lists.get(var2)).getText());
      var1.itemLayout.setOnClickListener(new TmpsAdapter$$ExternalSyntheticLambda0(this, var2));
   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(this.mContext).inflate(2131558482, var1, false));
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public interface OnItemClickListener {
      void click(int var1);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private RelativeLayout itemLayout;
      private TextView textView;
      final TmpsAdapter this$0;

      public ViewHolder(TmpsAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.itemLayout = (RelativeLayout)var2.findViewById(2131362522);
         this.imageView = (ImageView)var2.findViewById(2131362472);
         this.textView = (TextView)var2.findViewById(2131363519);
      }
   }
}
