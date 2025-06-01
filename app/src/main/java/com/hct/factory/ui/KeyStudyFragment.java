package com.hct.factory.ui;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.microntek.CarManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class KeyStudyFragment extends Fragment {
   private static final int KEYNUM = 40;
   private CarManager mCarManager = null;

   private void setParameters(String var1) {
      this.mCarManager.setParameters(var1);
   }

   public void onActivityCreated(@Nullable Bundle var1) {
      super.onActivityCreated(var1);
   }

   public void onCreate(@Nullable Bundle var1) {
      super.onCreate(var1);
      this.mCarManager = new CarManager();
   }

   @Nullable
   public View onCreateView(LayoutInflater var1, @Nullable ViewGroup var2, @Nullable Bundle var3) {
      View var6 = var1.inflate(2131230732, var2, false);
      Button var8 = (Button)var6.findViewById(2131099817);
      Button var7 = (Button)var6.findViewById(2131099928);
      Button var5 = (Button)var6.findViewById(2131099818);
      Button var4 = (Button)var6.findViewById(2131099929);
      var8.setOnClickListener(new View.OnClickListener(this) {
         final KeyStudyFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            Intent var2 = new Intent();
            var2.setComponent(new ComponentName("com.hct.factory", "com.hct.factory.PannelKeyStudy"));
            var2.putExtra("common", "hctpannel");
            this.this$0.getActivity().startActivity(var2);
         }
      });
      var7.setOnClickListener(new View.OnClickListener(this) {
         final KeyStudyFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            Intent var2 = new Intent();
            var2.setComponent(new ComponentName("com.hct.factory", "com.hct.factory.TouchKeyStudy"));
            var2.putExtra("common", "hcttouch");
            this.this$0.getActivity().startActivity(var2);
         }
      });
      var5.setOnClickListener(new View.OnClickListener(this) {
         final KeyStudyFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            String var2 = this.this$0.getString(2131296386).toString();
            (new AlertDialog.Builder(this.this$0.getActivity())).setTitle(var2).setPositiveButton(this.this$0.getString(17039379).toString(), new DialogInterface.OnClickListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
                  StringBuffer var5 = new StringBuffer();
                  int[] var3 = new int[120];

                  for(var2 = 0; var2 < var3.length; ++var2) {
                     var5.append(var3[var2]);
                     if (var2 != var3.length - 1) {
                        var5.append(",");
                     }
                  }

                  KeyStudyFragment var4 = this.this$1.this$0;
                  StringBuilder var6 = new StringBuilder();
                  var6.append("cfg_key=");
                  var6.append(var5.toString());
                  var4.setParameters(var6.toString());
               }
            }).setNegativeButton(this.this$0.getString(17039369).toString(), new DialogInterface.OnClickListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
               }
            }).show();
         }
      });
      var4.setOnClickListener(new View.OnClickListener(this) {
         final KeyStudyFragment this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            String var2 = this.this$0.getString(2131296386).toString();
            (new AlertDialog.Builder(this.this$0.getActivity())).setTitle(var2).setPositiveButton(this.this$0.getString(17039379).toString(), new DialogInterface.OnClickListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
                  byte[] var3 = new byte[90];
                  StringBuffer var5 = new StringBuffer();

                  for(var2 = 0; var2 < var3.length; ++var2) {
                     var5.append(var3[var2] & 255);
                     if (var2 != var3.length - 1) {
                        var5.append(",");
                     }
                  }

                  KeyStudyFragment var4 = this.this$1.this$0;
                  StringBuilder var6 = new StringBuilder();
                  var6.append("cfg_touch=");
                  var6.append(var5.toString());
                  var4.setParameters(var6.toString());
               }
            }).setNegativeButton(this.this$0.getString(17039369).toString(), new DialogInterface.OnClickListener(this) {
               final <undefinedtype> this$1;

               {
                  this.this$1 = var1;
               }

               public void onClick(DialogInterface var1, int var2) {
               }
            }).show();
         }
      });
      return var6;
   }

   public void onDestroy() {
      super.onDestroy();
   }
}
