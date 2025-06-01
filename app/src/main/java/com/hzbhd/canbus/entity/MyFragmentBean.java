package com.hzbhd.canbus.entity;

import android.app.Fragment;

public class MyFragmentBean {
   private Fragment fragment;
   private String title;

   public MyFragmentBean(Fragment var1, String var2) {
      this.fragment = var1;
      this.title = var2;
   }

   public Fragment getFragment() {
      return this.fragment;
   }

   public String getTitle() {
      return this.title;
   }

   public void setFragment(Fragment var1) {
      this.fragment = var1;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }
}
