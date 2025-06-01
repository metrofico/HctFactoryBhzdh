package com.hzbhd.canbus.car_cus._277.ui_set;

import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import java.util.List;

public class VehicleMonitorPageUiSet {
   private List list;
   private OnConfirmDialogListener mOnSettingConfirmDialogListener;
   private OnSettingItemTouchListener mOnSettingItemTouchListener;
   private OnSettingItemClickListener onSettingItemClickListener;
   private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
   private OnSettingItemSelectListener onSettingItemSelectListener;
   private OnSettingPageStatusListener onSettingPageStatusListener;

   public List getList() {
      return this.list;
   }

   public OnSettingItemClickListener getOnSettingItemClickListener() {
      return this.onSettingItemClickListener;
   }

   public OnSettingItemSeekbarSelectListener getOnSettingItemSeekbarSelectListener() {
      return this.onSettingItemSeekbarSelectListener;
   }

   public OnSettingItemSelectListener getOnSettingItemSelectListener() {
      return this.onSettingItemSelectListener;
   }

   public OnSettingPageStatusListener getOnSettingPageStatusListener() {
      return this.onSettingPageStatusListener;
   }

   public OnConfirmDialogListener getmOnSettingConfirmDialogListener() {
      return this.mOnSettingConfirmDialogListener;
   }

   public OnSettingItemTouchListener getmOnSettingItemTouchListener() {
      return this.mOnSettingItemTouchListener;
   }

   public void setList(List var1) {
      this.list = var1;
   }

   public void setOnSettingItemClickListener(OnSettingItemClickListener var1) {
      this.onSettingItemClickListener = var1;
   }

   public void setOnSettingItemSeekbarSelectListener(OnSettingItemSeekbarSelectListener var1) {
      this.onSettingItemSeekbarSelectListener = var1;
   }

   public void setOnSettingItemSelectListener(OnSettingItemSelectListener var1) {
      this.onSettingItemSelectListener = var1;
   }

   public void setOnSettingPageStatusListener(OnSettingPageStatusListener var1) {
      this.onSettingPageStatusListener = var1;
   }

   public void setmOnSettingConfirmDialogListener(OnConfirmDialogListener var1) {
      this.mOnSettingConfirmDialogListener = var1;
   }

   public void setmOnSettingItemTouchListener(OnSettingItemTouchListener var1) {
      this.mOnSettingItemTouchListener = var1;
   }

   public static class ListBean {
      private boolean isSelected;
      private List itemList;
      private int rowNumber;
      private String titleSrn;

      public List getItemList() {
         return this.itemList;
      }

      public int getRowNumber() {
         return this.rowNumber;
      }

      public String getTitleSrn() {
         return this.titleSrn;
      }

      public boolean isIsSelected() {
         return this.isSelected;
      }

      public void setIsSelected(boolean var1) {
         this.isSelected = var1;
      }

      public void setItemList(List var1) {
         this.itemList = var1;
      }

      public void setRowNumber(int var1) {
         this.rowNumber = var1;
      }

      public void setTitleSrn(String var1) {
         this.titleSrn = var1;
      }

      public static class ItemListBean {
         private String confirmTis;
         private int max;
         private int progress;
         private int selectIndex;
         private int style;
         private String titleSrn;
         private String unit;
         private Object value;
         private List valueSrnArray;

         public String getConfirmTis() {
            return this.confirmTis;
         }

         public int getMax() {
            return this.max;
         }

         public int getProgress() {
            return this.progress;
         }

         public int getSelectIndex() {
            return this.selectIndex;
         }

         public int getStyle() {
            return this.style;
         }

         public String getTitleSrn() {
            return this.titleSrn;
         }

         public String getUnit() {
            return this.unit;
         }

         public Object getValue() {
            return this.value;
         }

         public List getValueSrnArray() {
            return this.valueSrnArray;
         }

         public void setConfirmTis(String var1) {
            this.confirmTis = var1;
         }

         public void setMax(int var1) {
            this.max = var1;
         }

         public void setProgress(int var1) {
            this.progress = var1;
         }

         public void setSelectIndex(int var1) {
            this.selectIndex = var1;
         }

         public void setStyle(int var1) {
            this.style = var1;
         }

         public void setTitleSrn(String var1) {
            this.titleSrn = var1;
         }

         public void setUnit(String var1) {
            this.unit = var1;
         }

         public void setValue(Object var1) {
            this.value = var1;
         }

         public void setValueSrnArray(List var1) {
            this.valueSrnArray = var1;
         }
      }
   }
}
