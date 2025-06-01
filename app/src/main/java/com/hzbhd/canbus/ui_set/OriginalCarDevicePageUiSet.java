package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalSongItemClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import java.util.List;

public class OriginalCarDevicePageUiSet {
   private boolean isHavePlayTimeSeekBar = true;
   private boolean isHaveSongList;
   private boolean isSongListShowIndex = true;
   private boolean isTopBtnCanScroll = false;
   private List items;
   private OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListeners;
   private OnOriginalCarDeviceBackPressedListener onOriginalCarDeviceBackPressedListener;
   private OnOriginalCarDevicePageStatusListener onOriginalCarDevicePageStatusListener;
   private OnOriginalSongItemClickListener onOriginalSongItemClickListener;
   private OnOriginalTopBtnClickListener onOriginalTopBtnClickListeners;
   private String[] rowBottomBtnAction;
   private String[] rowTopBtnAction;

   public List getItems() {
      return this.items;
   }

   public OnOriginalBottomBtnClickListener getOnOriginalBottomBtnClickListeners() {
      return this.onOriginalBottomBtnClickListeners;
   }

   public OnOriginalCarDeviceBackPressedListener getOnOriginalCarDeviceBackPressedListener() {
      return this.onOriginalCarDeviceBackPressedListener;
   }

   public OnOriginalCarDevicePageStatusListener getOnOriginalCarDevicePageStatusListener() {
      return this.onOriginalCarDevicePageStatusListener;
   }

   public OnOriginalSongItemClickListener getOnOriginalSongItemClickListener() {
      return this.onOriginalSongItemClickListener;
   }

   public OnOriginalTopBtnClickListener getOnOriginalTopBtnClickListeners() {
      return this.onOriginalTopBtnClickListeners;
   }

   public String[] getRowBottomBtnAction() {
      return this.rowBottomBtnAction;
   }

   public String[] getRowTopBtnAction() {
      return this.rowTopBtnAction;
   }

   public boolean isHavePlayTimeSeekBar() {
      return this.isHavePlayTimeSeekBar;
   }

   public boolean isHaveSongList() {
      return this.isHaveSongList;
   }

   public boolean isSongListShowIndex() {
      return this.isSongListShowIndex;
   }

   public boolean isTopBtnCanScroll() {
      return this.isTopBtnCanScroll;
   }

   public void setHavePlayTimeSeekBar(boolean var1) {
      this.isHavePlayTimeSeekBar = var1;
   }

   public void setHaveSongList(boolean var1) {
      this.isHaveSongList = var1;
   }

   public void setItems(List var1) {
      this.items = var1;
   }

   public void setOnOriginalBottomBtnClickListeners(OnOriginalBottomBtnClickListener var1) {
      this.onOriginalBottomBtnClickListeners = var1;
   }

   public void setOnOriginalCarDeviceBackPressedListener(OnOriginalCarDeviceBackPressedListener var1) {
      this.onOriginalCarDeviceBackPressedListener = var1;
   }

   public void setOnOriginalCarDevicePageStatusListener(OnOriginalCarDevicePageStatusListener var1) {
      this.onOriginalCarDevicePageStatusListener = var1;
   }

   public void setOnOriginalSongItemClickListener(OnOriginalSongItemClickListener var1) {
      this.onOriginalSongItemClickListener = var1;
   }

   public void setOnOriginalTopBtnClickListeners(OnOriginalTopBtnClickListener var1) {
      this.onOriginalTopBtnClickListeners = var1;
   }

   public void setRowBottomBtnAction(String[] var1) {
      this.rowBottomBtnAction = var1;
   }

   public void setRowTopBtnAction(String[] var1) {
      this.rowTopBtnAction = var1;
   }

   public void setSongListShowIndex(boolean var1) {
      this.isSongListShowIndex = var1;
   }

   public void setTopBtnCanScroll(boolean var1) {
      this.isTopBtnCanScroll = var1;
   }

   public static class Item {
      private String imageId;
      private String titleSrn;
      private String value;

      public Item(String var1, String var2, String var3) {
         this.imageId = var1;
         this.titleSrn = var2;
         this.value = var3;
      }

      public String getImageId() {
         return this.imageId;
      }

      public String getTitleSrn() {
         return this.titleSrn;
      }

      public String getValue() {
         return this.value;
      }

      public void setImageId(String var1) {
         this.imageId = var1;
      }

      public void setTitleSrn(String var1) {
         this.titleSrn = var1;
      }

      public void setValue(String var1) {
         this.value = var1;
      }
   }
}
