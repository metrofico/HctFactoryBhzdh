package com.hzbhd.canbus.car_cus._277;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._277.adapter.DiagnosisLvAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DialogUtil {
   private static DialogUtil mDialogUtil;
   public static boolean mHasAdded;
   private AlertDialog mAlertDialog;
   private boolean mCanCancel;
   private List mDiagnosisList;
   private DiagnosisLvAdapter mDiagnosisLvAdapter;
   private Timer mTimer;
   private TimerTask mTimerTask;
   private View mView;
   private WindowManager mWindowManager;

   private void finishTimer() {
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

      Log.i("ljq", "finishTimer: ");
   }

   public static DialogUtil getInstance() {
      if (mDialogUtil == null) {
         mDialogUtil = new DialogUtil();
      }

      return mDialogUtil;
   }

   private void startDiagnosisCheck() {
      this.finishTimer();
      this.mTimerTask = new TimerTask(this) {
         final DialogUtil this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            if (DialogUtil.mHasAdded) {
               this.this$0.mWindowManager.removeView(this.this$0.mView);
               DialogUtil.mHasAdded = false;
            }

            this.this$0.finishTimer();
         }
      };
      this.startTimer(5000L);
   }

   private void startTiemr(long var1, int var3) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1, (long)var3);
      }
   }

   private void startTimer(long var1) {
      if (this.mTimerTask != null) {
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         this.mTimer.schedule(this.mTimerTask, var1);
         Log.i("ljq", "startTimer: ");
      }
   }

   public void showDiagnosisWindow(Context var1, List var2, boolean var3) {
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      WindowManager.LayoutParams var4 = new WindowManager.LayoutParams();
      var4.type = 2010;
      var4.flags = 16777512;
      var4.x = 0;
      var4.y = 0;
      var4.width = -1;
      var4.height = -1;
      if (this.mDiagnosisList == null) {
         this.mDiagnosisList = new ArrayList();
      }

      this.mDiagnosisList.clear();
      this.mDiagnosisList.addAll(var2);
      if (mHasAdded) {
         if (var3) {
            this.startDiagnosisCheck();
         }

         this.mDiagnosisLvAdapter.notifyDataSetChanged();
      } else {
         if (this.mView == null) {
            this.mView = LayoutInflater.from(var1).inflate(2131558420, (ViewGroup)null);
            View.OnClickListener var5 = new View.OnClickListener(this) {
               final DialogUtil this$0;

               {
                  this.this$0 = var1;
               }

               public void onClick(View var1) {
                  int var2 = var1.getId();
                  if (var2 == 2131362146 || var2 == 2131363189) {
                     this.this$0.finishTimer();
                     this.this$0.mWindowManager.removeView(this.this$0.mView);
                     DialogUtil.mHasAdded = false;
                  }

               }
            };
            this.mView.findViewById(2131362146).setOnClickListener(var5);
         }

         RecyclerView var7 = (RecyclerView)this.mView.findViewById(2131363211);
         var7.setLayoutManager(new LinearLayoutManager(var1));
         DiagnosisLvAdapter var6 = new DiagnosisLvAdapter(var1, this.mDiagnosisList);
         this.mDiagnosisLvAdapter = var6;
         var7.setAdapter(var6);
         this.mWindowManager.addView(this.mView, var4);
         this.startDiagnosisCheck();
         mHasAdded = true;
      }

      if (var2.size() == 0 && mHasAdded) {
         this.mWindowManager.removeView(this.mView);
         mHasAdded = false;
      }

   }

   public void showListDialog(Context var1, String[] var2, ListDialogCallBak var3) {
      View var4 = LayoutInflater.from(var1).inflate(2131558422, (ViewGroup)null);
      ListView var5 = (ListView)var4.findViewById(2131362821);
      var5.setAdapter(new ArrayAdapter(var1, 2131558421, var2));
      var5.setOnItemClickListener(new AdapterView.OnItemClickListener(this, var3) {
         final DialogUtil this$0;
         final ListDialogCallBak val$listDialogCallBak;

         {
            this.this$0 = var1;
            this.val$listDialogCallBak = var2;
         }

         public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
            ListDialogCallBak var6 = this.val$listDialogCallBak;
            if (var6 != null) {
               var6.callBack(var3);
            }

            this.this$0.mAlertDialog.dismiss();
         }
      });
      AlertDialog var6 = (new AlertDialog.Builder(var1, 2131820748)).setView(var4).create();
      this.mAlertDialog = var6;
      var6.show();
   }

   public interface ListDialogCallBak {
      void callBack(int var1);
   }
}
