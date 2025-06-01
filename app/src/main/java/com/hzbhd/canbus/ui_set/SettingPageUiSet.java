package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSetTextListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import java.util.List;

public class SettingPageUiSet {
   private List list;
   private OnConfirmDialogListener onSettingConfirmDialogListener;
   private OnSettingItemClickListener onSettingItemClickListener;
   private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener;
   private OnSettingItemSeekbarSetTextListener onSettingItemSeekbarSetTextListener;
   private OnSettingItemSelectListener onSettingItemSelectListener;
   private OnSettingItemSwitchListener onSettingItemSwitchListener;
   private OnSettingItemTouchListener onSettingItemTouchListener;
   private OnSettingPageStatusListener onSettingPageStatusListener;

   public List getList() {
      return this.list;
   }

   public OnConfirmDialogListener getOnSettingConfirmDialogListener() {
      return this.onSettingConfirmDialogListener;
   }

   public OnSettingItemClickListener getOnSettingItemClickListener() {
      return this.onSettingItemClickListener;
   }

   public OnSettingItemSeekbarSelectListener getOnSettingItemSeekbarSelectListener() {
      return this.onSettingItemSeekbarSelectListener;
   }

   public OnSettingItemSeekbarSetTextListener getOnSettingItemSeekbarSetTextListener() {
      return this.onSettingItemSeekbarSetTextListener;
   }

   public OnSettingItemSelectListener getOnSettingItemSelectListener() {
      return this.onSettingItemSelectListener;
   }

   public OnSettingItemSwitchListener getOnSettingItemSwitchListener() {
      return this.onSettingItemSwitchListener;
   }

   public OnSettingItemTouchListener getOnSettingItemTouchListener() {
      return this.onSettingItemTouchListener;
   }

   public OnSettingPageStatusListener getOnSettingPageStatusListener() {
      return this.onSettingPageStatusListener;
   }

   public void setList(List var1) {
      this.list = var1;
   }

   public void setOnSettingConfirmDialogListener(OnConfirmDialogListener var1) {
      this.onSettingConfirmDialogListener = var1;
   }

   public void setOnSettingItemClickListener(OnSettingItemClickListener var1) {
      this.onSettingItemClickListener = var1;
   }

   public void setOnSettingItemSeekbarSelectListener(OnSettingItemSeekbarSelectListener var1) {
      this.onSettingItemSeekbarSelectListener = var1;
   }

   public void setOnSettingItemSeekbarSetTextListener(OnSettingItemSeekbarSetTextListener var1) {
      this.onSettingItemSeekbarSetTextListener = var1;
   }

   public void setOnSettingItemSelectListener(OnSettingItemSelectListener var1) {
      this.onSettingItemSelectListener = var1;
   }

   public void setOnSettingItemSwitchListener(OnSettingItemSwitchListener var1) {
      this.onSettingItemSwitchListener = var1;
   }

   public void setOnSettingItemTouchListener(OnSettingItemTouchListener var1) {
      this.onSettingItemTouchListener = var1;
   }

   public void setOnSettingPageStatusListener(OnSettingPageStatusListener var1) {
      this.onSettingPageStatusListener = var1;
   }

   public static class ListBean {
      private boolean isSelected;
      private List itemList;
      private String titleSrn;

      public List getItemList() {
         return this.itemList;
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

      public void setTitleSrn(String var1) {
         this.titleSrn = var1;
      }

      public static class ItemListBean {
         private String confirmTis;
         private boolean enable = true;
         private int id;
         private boolean isProgressDraggable = true;
         private boolean isValueStr;
         private int max;
         private int min;
         private int progress = 0;
         private int selectIndex = 0;
         private int style;
         private String titleSrn;
         private String unit = "null_value";
         private Object value;
         private List valueSrnArray;

         public String getConfirmTis() {
            return this.confirmTis;
         }

         public int getId() {
            return this.id;
         }

         public int getMax() {
            return this.max;
         }

         public int getMin() {
            return this.min;
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

         public boolean isEnable() {
            return this.enable;
         }

         public boolean isProgressDraggable() {
            return this.isProgressDraggable;
         }

         public boolean isValueStr() {
            return this.isValueStr;
         }

         public void setConfirmTis(String var1) {
            this.confirmTis = var1;
         }

         public void setEnable(boolean var1) {
            this.enable = var1;
         }

         public void setMax(int var1) {
            this.max = var1;
         }

         public void setMin(int var1) {
            this.min = var1;
         }

         public void setProgress(int var1) {
            this.progress = var1;
         }

         public void setProgressDraggable(boolean var1) {
            this.isProgressDraggable = var1;
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

         public void setValueStr(boolean var1) {
            this.isValueStr = var1;
         }
      }
   }
}
