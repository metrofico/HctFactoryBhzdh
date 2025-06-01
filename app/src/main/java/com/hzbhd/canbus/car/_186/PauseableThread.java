package com.hzbhd.canbus.car._186;

class PauseableThread extends Thread {
   private boolean isPaused;
   private int selectPos;

   public void pauseThread() {
      synchronized(this){}

      try {
         this.isPaused = true;
      } finally {
         ;
      }

   }

   public void resumeThread() {
      synchronized(this){}

      try {
         this.isPaused = false;
         this.notify();
      } finally {
         ;
      }

   }

   public void run() {
      // $FF: Couldn't be decompiled
   }

   public void setSelectPos(int var1) {
      synchronized(this){}

      try {
         this.selectPos = var1;
      } finally {
         ;
      }

   }
}
