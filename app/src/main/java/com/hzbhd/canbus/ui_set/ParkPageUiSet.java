package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import java.util.List;

public class ParkPageUiSet {
   private int cusVideoHeight;
   private int cusVideoStartX;
   private int cusVideoStartY;
   private int cusVideoWidth;
   private boolean isCusVideoSize;
   private boolean isHaveLeftRightRadar = false;
   private boolean isShowCusPanoramicView;
   private boolean isShowCustomBaseLine = false;
   private boolean isShowCustomTrack = false;
   private boolean isShowPanoramic = false;
   private boolean isShowRadar = false;
   private boolean isShowRadarDistance = false;
   private boolean isShowRadarLocation = false;
   private boolean isShowTrack = false;
   private OnBackCameraStatusListener onBackCameraStatusListener;
   private OnPanoramicItemClickListener onPanoramicItemClickListener;
   private OnPanoramicItemTouchListener onPanoramicItemTouchListener;
   private List panoramicBtnList;

   public int getCusVideoHeight() {
      return this.cusVideoHeight;
   }

   public int getCusVideoStartX() {
      return this.cusVideoStartX;
   }

   public int getCusVideoStartY() {
      return this.cusVideoStartY;
   }

   public int getCusVideoWidth() {
      return this.cusVideoWidth;
   }

   public OnBackCameraStatusListener getOnBackCameraStatusListener() {
      return this.onBackCameraStatusListener;
   }

   public OnPanoramicItemClickListener getOnPanoramicItemClickListener() {
      return this.onPanoramicItemClickListener;
   }

   public OnPanoramicItemTouchListener getOnPanoramicItemTouchListener() {
      return this.onPanoramicItemTouchListener;
   }

   public List getPanoramicBtnList() {
      return this.panoramicBtnList;
   }

   public boolean isCusVideoSize() {
      return this.isCusVideoSize;
   }

   public boolean isHaveLeftRightRadar() {
      return this.isHaveLeftRightRadar;
   }

   public boolean isIsShowRadar() {
      return this.isShowRadar;
   }

   public boolean isIsShowRadarDistance() {
      return this.isShowRadarDistance;
   }

   public boolean isIsShowRadarLocation() {
      return this.isShowRadarLocation;
   }

   public boolean isIsShowTrack() {
      return this.isShowTrack;
   }

   public boolean isShowCusPanoramicView() {
      return this.isShowCusPanoramicView;
   }

   public boolean isShowCustomBaseLine() {
      return this.isShowCustomBaseLine;
   }

   public boolean isShowCustomTrack() {
      return this.isShowCustomTrack;
   }

   public boolean isShowPanoramic() {
      return this.isShowPanoramic;
   }

   public boolean isShowRadar() {
      return this.isShowRadar;
   }

   public void setCusVideoHeight(int var1) {
      this.cusVideoHeight = var1;
   }

   public void setCusVideoStartX(int var1) {
      this.cusVideoStartX = var1;
   }

   public void setCusVideoStartY(int var1) {
      this.cusVideoStartY = var1;
   }

   public void setCusVideoWidth(int var1) {
      this.cusVideoWidth = var1;
   }

   public void setOnBackCameraStatusListener(OnBackCameraStatusListener var1) {
      this.onBackCameraStatusListener = var1;
   }

   public void setOnPanoramicItemClickListener(OnPanoramicItemClickListener var1) {
      this.onPanoramicItemClickListener = var1;
   }

   public void setOnPanoramicItemTouchListener(OnPanoramicItemTouchListener var1) {
      this.onPanoramicItemTouchListener = var1;
   }

   public void setPanoramicBtnList(List var1) {
      this.panoramicBtnList = var1;
   }

   public void setShowCusPanoramicView(boolean var1) {
      this.isShowCusPanoramicView = var1;
   }

   public void setShowCustomBaseLine(boolean var1) {
      this.isShowCustomBaseLine = var1;
   }

   public void setShowCustomTrack(boolean var1) {
      this.isShowCustomTrack = var1;
   }

   public void setShowPanoramic(boolean var1) {
      this.isShowPanoramic = var1;
   }

   public void setShowRadar(boolean var1) {
      this.isShowRadar = var1;
   }

   public void setShowRadarDistance(boolean var1) {
      this.isShowRadarDistance = var1;
   }

   public void setShowRadarLocation(boolean var1) {
      this.isShowRadarLocation = var1;
   }

   public void setShowTrack(boolean var1) {
      this.isShowTrack = var1;
   }

   public static class Bean {
      private String imgRes;
      private boolean isSelect;
      private int style;
      private String titleSrn;

      public Bean(int var1, String var2, String var3) {
         this.style = var1;
         this.titleSrn = var2;
         this.imgRes = var3;
      }

      public String getImgRes() {
         return this.imgRes;
      }

      public int getStyle() {
         return this.style;
      }

      public String getTitleSrn() {
         return this.titleSrn;
      }

      public boolean isSelect() {
         return this.isSelect;
      }

      public void setSelect(boolean var1) {
         this.isSelect = var1;
      }

      public void setTitleSrn(String var1) {
         this.titleSrn = var1;
      }
   }
}
