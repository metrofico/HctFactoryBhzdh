package com.hzbhd.canbus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.entity.SongListEntity;
import java.util.List;

public class SongListLvAdapter extends RecyclerView.Adapter {
   private boolean mIsSonglistShowIndex;
   private SongItemClickInterface mItemClickInterface;
   private List mList;

   public SongListLvAdapter(List var1, SongItemClickInterface var2, boolean var3) {
      this.mItemClickInterface = var2;
      this.mList = var1;
      this.mIsSonglistShowIndex = var3;
   }

   public int getItemCount() {
      return this.mList.size();
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public void onBindViewHolder(ViewHolder var1, int var2) {
      String var3 = var2 + 1 + ". ";
      if (!this.mIsSonglistShowIndex) {
         var3 = "";
      }

      var1.tvTitle.setText(var3 + ((SongListEntity)this.mList.get(var2)).getTitle());
      var1.relativeLayout.setOnClickListener(new View.OnClickListener(this, var2) {
         final SongListLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public void onClick(View var1) {
            this.this$0.mItemClickInterface.onSongItemClick(this.val$position);
         }
      });
      var1.relativeLayout.setOnLongClickListener(new View.OnLongClickListener(this, var2) {
         final SongListLvAdapter this$0;
         final int val$position;

         {
            this.this$0 = var1;
            this.val$position = var2;
         }

         public boolean onLongClick(View var1) {
            this.this$0.mItemClickInterface.onSongItemLongClick(this.val$position);
            return true;
         }
      });
      if (((SongListEntity)this.mList.get(var2)).isSelected()) {
         var1.ivRightIcon.setVisibility(0);
      } else {
         var1.ivRightIcon.setVisibility(8);
      }

      if (((SongListEntity)this.mList.get(var2)).isEnable()) {
         var1.relativeLayout.setEnabled(true);
         var1.relativeLayout.setAlpha(1.0F);
      } else {
         var1.relativeLayout.setEnabled(false);
         var1.relativeLayout.setAlpha(0.1F);
      }

   }

   public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return new ViewHolder(this, LayoutInflater.from(var1.getContext()).inflate(2131558783, var1, false));
   }

   public interface SongItemClickInterface {
      void onSongItemClick(int var1);

      void onSongItemLongClick(int var1);
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView ivRightIcon;
      private RelativeLayout relativeLayout;
      final SongListLvAdapter this$0;
      private TextView tvTitle;

      ViewHolder(SongListLvAdapter var1, View var2) {
         super(var2);
         this.this$0 = var1;
         this.relativeLayout = (RelativeLayout)var2.findViewById(2131362786);
         this.tvTitle = (TextView)var2.findViewById(2131363710);
         this.ivRightIcon = (ImageView)var2.findViewById(2131362625);
      }
   }
}
