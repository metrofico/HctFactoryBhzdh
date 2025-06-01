package com.hzbhd.canbus.car_cus._283.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.bean.MainMediaBean;
import java.util.List;

public class MainMediaAdapter extends RecyclerView.Adapter {
   private List lists;
   private Context mContext;
   private OnItemClickListener mOnItemClickListener;

   public MainMediaAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.lists = var2;
   }

   public int getItemCount() {
      return this.lists.size();
   }

   // $FF: synthetic method
   void lambda$onBindViewHolder$0$com_hzbhd_canbus_car_cus__283_adapter_MainMediaAdapter(int var1, View var2) {
      OnItemClickListener var3 = this.mOnItemClickListener;
      if (var3 != null) {
         var3.click(var1, ((MainMediaBean)this.lists.get(var1)).getmComponentName());
      }

   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      var1.imageView.setImageResource(((MainMediaBean)this.lists.get(var2)).getImage());
      var1.textView.setText(((MainMediaBean)this.lists.get(var2)).getText());
      var1.itemView.setOnClickListener(new MainMediaAdapter$$ExternalSyntheticLambda0(this, var2));
      if (this.getItemCount() - 1 == var2) {
         var1.lineView.setVisibility(8);
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(this.mContext).inflate(2131558435, var1, false));
   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public interface OnItemClickListener {
      void click(int var1, ComponentName var2);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imageView;
      private View lineView;
      private TextView textView;
      final MainMediaAdapter this$0;

      public ViewHolder(MainMediaAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.textView = (TextView)var2.findViewById(2131363519);
         this.imageView = (ImageView)var2.findViewById(2131362472);
         this.lineView = var2.findViewById(2131362757);
      }
   }
}
