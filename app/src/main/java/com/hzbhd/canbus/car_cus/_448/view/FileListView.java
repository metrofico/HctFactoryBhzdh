package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.hzbhd.canbus.car_cus._448.DvrObserver;
import com.hzbhd.canbus.car_cus._448.DvrSender;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._448.Interface.DvrDataInterface;
import com.hzbhd.canbus.car_cus._448.data.DvrData;
import java.util.List;

public class FileListView extends LinearLayout implements DvrDataInterface {
   private ActionCallback actionCallback;
   private FileItemView file_1;
   private FileItemView file_2;
   private FileItemView file_3;
   private FileItemView file_4;
   private FileItemView file_5;
   private FileItemView file_6;
   private KeyButton lock_btn;
   private KeyButton next_btn;
   private KeyButton prev_btn;
   private List selectedFiles;
   private View view;

   public FileListView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public FileListView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public FileListView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      View var4 = LayoutInflater.from(var1).inflate(2131558605, this, true);
      this.view = var4;
      this.prev_btn = (KeyButton)var4.findViewById(2131362993);
      this.lock_btn = (KeyButton)this.view.findViewById(2131362815);
      this.next_btn = (KeyButton)this.view.findViewById(2131362897);
      this.prev_btn.setTextValue("上一页");
      this.lock_btn.setTextValue("加锁/解锁");
      this.next_btn.setTextValue("下一页");
      this.initFileView();
   }

   private byte[] getBytes(List var1) {
      int var2 = (Integer)var1.get(0);
      int var12 = (Integer)var1.get(1);
      int var8 = (Integer)var1.get(2);
      int var9 = (Integer)var1.get(3);
      int var10 = (Integer)var1.get(4);
      int var7 = (Integer)var1.get(5);
      int var4 = (Integer)var1.get(6);
      int var11 = (Integer)var1.get(7);
      int var17 = (Integer)var1.get(8);
      int var16 = (Integer)var1.get(9);
      int var3 = (Integer)var1.get(10);
      int var19 = (Integer)var1.get(11);
      int var5 = (Integer)var1.get(12);
      int var6 = (Integer)var1.get(13);
      int var14 = (Integer)var1.get(14);
      int var18 = (Integer)var1.get(15);
      int var20 = (Integer)var1.get(16);
      int var13 = (Integer)var1.get(17);
      int var15 = (Integer)var1.get(18);
      return new byte[]{114, -1, (byte)var2, (byte)var12, (byte)var8, (byte)var9, (byte)var10, (byte)var7, (byte)var4, (byte)var11, (byte)var17, (byte)var16, (byte)var3, (byte)var19, (byte)var5, (byte)var6, (byte)var14, (byte)var18, (byte)var20, (byte)var13, (byte)var15};
   }

   private void initFileView() {
      this.file_1 = (FileItemView)this.view.findViewById(2131362236);
      this.file_2 = (FileItemView)this.view.findViewById(2131362237);
      this.file_3 = (FileItemView)this.view.findViewById(2131362238);
      this.file_4 = (FileItemView)this.view.findViewById(2131362239);
      this.file_5 = (FileItemView)this.view.findViewById(2131362240);
      this.file_6 = (FileItemView)this.view.findViewById(2131362241);
      this.file_1.getAction(new ActionCallback(this) {
         final FileListView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            DvrSender.send(this.this$0.getBytes(DvrData.file1NameList));
            this.this$0.saveNowFile(DvrData.file1NameList, true);
            this.this$0.file_1.setSelect(true);
            this.this$0.file_2.setSelect(false);
            this.this$0.file_3.setSelect(false);
            this.this$0.file_4.setSelect(false);
            this.this$0.file_5.setSelect(false);
            this.this$0.file_6.setSelect(false);
            this.this$0.actionCallback.toDo("PLAY");
         }
      });
      this.file_2.getAction(new ActionCallback(this) {
         final FileListView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            DvrSender.send(this.this$0.getBytes(DvrData.file2NameList));
            this.this$0.saveNowFile(DvrData.file2NameList, true);
            this.this$0.file_1.setSelect(false);
            this.this$0.file_2.setSelect(true);
            this.this$0.file_3.setSelect(false);
            this.this$0.file_4.setSelect(false);
            this.this$0.file_5.setSelect(false);
            this.this$0.file_6.setSelect(false);
            this.this$0.actionCallback.toDo("PLAY");
         }
      });
      this.file_3.getAction(new ActionCallback(this) {
         final FileListView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            DvrSender.send(this.this$0.getBytes(DvrData.file3NameList));
            this.this$0.saveNowFile(DvrData.file3NameList, true);
            this.this$0.file_1.setSelect(false);
            this.this$0.file_2.setSelect(false);
            this.this$0.file_3.setSelect(true);
            this.this$0.file_4.setSelect(false);
            this.this$0.file_5.setSelect(false);
            this.this$0.file_6.setSelect(false);
            this.this$0.actionCallback.toDo("PLAY");
         }
      });
      this.file_4.getAction(new ActionCallback(this) {
         final FileListView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            DvrSender.send(this.this$0.getBytes(DvrData.file4NameList));
            this.this$0.saveNowFile(DvrData.file4NameList, true);
            this.this$0.file_1.setSelect(false);
            this.this$0.file_2.setSelect(false);
            this.this$0.file_3.setSelect(false);
            this.this$0.file_4.setSelect(true);
            this.this$0.file_5.setSelect(false);
            this.this$0.file_6.setSelect(false);
            this.this$0.actionCallback.toDo("PLAY");
         }
      });
      this.file_5.getAction(new ActionCallback(this) {
         final FileListView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            DvrSender.send(this.this$0.getBytes(DvrData.file5NameList));
            this.this$0.saveNowFile(DvrData.file5NameList, true);
            this.this$0.file_1.setSelect(false);
            this.this$0.file_2.setSelect(false);
            this.this$0.file_3.setSelect(false);
            this.this$0.file_4.setSelect(false);
            this.this$0.file_5.setSelect(true);
            this.this$0.file_6.setSelect(false);
            this.this$0.actionCallback.toDo("PLAY");
         }
      });
      this.file_6.getAction(new ActionCallback(this) {
         final FileListView this$0;

         {
            this.this$0 = var1;
         }

         public void toDo(Object var1) {
            DvrSender.send(this.this$0.getBytes(DvrData.file6NameList));
            this.this$0.saveNowFile(DvrData.file6NameList, true);
            this.this$0.file_1.setSelect(false);
            this.this$0.file_2.setSelect(false);
            this.this$0.file_3.setSelect(false);
            this.this$0.file_4.setSelect(false);
            this.this$0.file_5.setSelect(false);
            this.this$0.file_6.setSelect(true);
            this.this$0.actionCallback.toDo("PLAY");
         }
      });
   }

   private boolean isPlaying(List var1, List var2) {
      if (var1 != null && var2 != null && var2.size() == var1.size()) {
         for(int var3 = 0; var3 < var1.size(); ++var3) {
            if (var1.get(var3) != var2.get(var3)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private String makeFileName(List var1, int var2) {
      if (var1.size() == 0) {
         return "NONE";
      } else {
         String var3;
         String var4;
         String var5;
         label73: {
            String var6 = "JPG";
            var3 = "PIC";
            var4 = "TS";
            var5 = "REC";
            if (var2 == 1) {
               var4 = var3;
               if (DvrData.file1Lock.equals("UNLOCK")) {
                  var4 = "REC";
               }

               var5 = var6;
               var3 = var4;
               if (!DvrData.file1Type.equals("VIDEO")) {
                  break label73;
               }

               var3 = var4;
            } else if (var2 == 2) {
               var4 = var3;
               if (DvrData.file2Lock.equals("UNLOCK")) {
                  var4 = "REC";
               }

               var5 = var6;
               var3 = var4;
               if (!DvrData.file2Type.equals("VIDEO")) {
                  break label73;
               }

               var3 = var4;
            } else if (var2 == 3) {
               var4 = var3;
               if (DvrData.file3Lock.equals("UNLOCK")) {
                  var4 = "REC";
               }

               var5 = var6;
               var3 = var4;
               if (!DvrData.file3Type.equals("VIDEO")) {
                  break label73;
               }

               var3 = var4;
            } else if (var2 == 4) {
               var4 = var3;
               if (DvrData.file4Lock.equals("UNLOCK")) {
                  var4 = "REC";
               }

               var5 = var6;
               var3 = var4;
               if (!DvrData.file4Type.equals("VIDEO")) {
                  break label73;
               }

               var3 = var4;
            } else if (var2 == 5) {
               var4 = var3;
               if (DvrData.file5Lock.equals("UNLOCK")) {
                  var4 = "REC";
               }

               var5 = var6;
               var3 = var4;
               if (!DvrData.file5Type.equals("VIDEO")) {
                  break label73;
               }

               var3 = var4;
            } else {
               if (var2 != 6) {
                  return var5 + "_" + ((Integer)var1.get(0) - 48) + "" + ((Integer)var1.get(1) - 48) + "" + ((Integer)var1.get(2) - 48) + "" + ((Integer)var1.get(3) - 48) + "" + ((Integer)var1.get(4) - 48) + "" + ((Integer)var1.get(5) - 48) + "" + ((Integer)var1.get(6) - 48) + "" + ((Integer)var1.get(7) - 48) + "_" + ((Integer)var1.get(8) - 48) + "" + ((Integer)var1.get(9) - 48) + "" + ((Integer)var1.get(10) - 48) + "" + ((Integer)var1.get(11) - 48) + "" + ((Integer)var1.get(12) - 48) + "" + ((Integer)var1.get(13) - 48) + "_" + ((Integer)var1.get(14) - 48) + "" + ((Integer)var1.get(15) - 48) + "" + ((Integer)var1.get(16) - 48) + "" + ((Integer)var1.get(17) - 48) + "" + ((Integer)var1.get(18) - 48) + "." + var4;
               }

               var4 = var3;
               if (DvrData.file6Lock.equals("UNLOCK")) {
                  var4 = "REC";
               }

               var5 = var6;
               var3 = var4;
               if (!DvrData.file6Type.equals("VIDEO")) {
                  break label73;
               }

               var3 = var4;
            }

            var5 = "TS";
         }

         var4 = var5;
         var5 = var3;
         return var5 + "_" + ((Integer)var1.get(0) - 48) + "" + ((Integer)var1.get(1) - 48) + "" + ((Integer)var1.get(2) - 48) + "" + ((Integer)var1.get(3) - 48) + "" + ((Integer)var1.get(4) - 48) + "" + ((Integer)var1.get(5) - 48) + "" + ((Integer)var1.get(6) - 48) + "" + ((Integer)var1.get(7) - 48) + "_" + ((Integer)var1.get(8) - 48) + "" + ((Integer)var1.get(9) - 48) + "" + ((Integer)var1.get(10) - 48) + "" + ((Integer)var1.get(11) - 48) + "" + ((Integer)var1.get(12) - 48) + "" + ((Integer)var1.get(13) - 48) + "_" + ((Integer)var1.get(14) - 48) + "" + ((Integer)var1.get(15) - 48) + "" + ((Integer)var1.get(16) - 48) + "" + ((Integer)var1.get(17) - 48) + "" + ((Integer)var1.get(18) - 48) + "." + var4;
      }
   }

   private void updateUi() {
      Log.d("fxHouError", "刷新数据BBBBBBBBBB");
      boolean var5 = DvrData.file1Type.equals("VIDEO");
      int var3 = 2131231587;
      int var1;
      if (var5) {
         var1 = 2131231587;
      } else {
         var1 = 2131231584;
      }

      var5 = DvrData.file1Lock.equals("UNLOCK");
      int var4 = 2131231579;
      int var2;
      if (var5) {
         var2 = 2131231579;
      } else {
         var2 = 2131100046;
      }

      this.file_1.setData(var1, this.makeFileName(DvrData.file1NameList, 1), var2);
      if (DvrData.file2Type.equals("VIDEO")) {
         var1 = 2131231587;
      } else {
         var1 = 2131231584;
      }

      if (DvrData.file2Lock.equals("UNLOCK")) {
         var2 = 2131231579;
      } else {
         var2 = 2131100046;
      }

      this.file_2.setData(var1, this.makeFileName(DvrData.file2NameList, 2), var2);
      if (DvrData.file3Type.equals("VIDEO")) {
         var1 = 2131231587;
      } else {
         var1 = 2131231584;
      }

      if (DvrData.file3Lock.equals("UNLOCK")) {
         var2 = 2131231579;
      } else {
         var2 = 2131100046;
      }

      this.file_3.setData(var1, this.makeFileName(DvrData.file3NameList, 3), var2);
      if (DvrData.file4Type.equals("VIDEO")) {
         var1 = 2131231587;
      } else {
         var1 = 2131231584;
      }

      if (DvrData.file4Lock.equals("UNLOCK")) {
         var2 = 2131231579;
      } else {
         var2 = 2131100046;
      }

      this.file_4.setData(var1, this.makeFileName(DvrData.file4NameList, 4), var2);
      if (DvrData.file5Type.equals("VIDEO")) {
         var1 = 2131231587;
      } else {
         var1 = 2131231584;
      }

      if (DvrData.file5Lock.equals("UNLOCK")) {
         var2 = 2131231579;
      } else {
         var2 = 2131100046;
      }

      this.file_5.setData(var1, this.makeFileName(DvrData.file5NameList, 5), var2);
      if (DvrData.file6Type.equals("VIDEO")) {
         var1 = var3;
      } else {
         var1 = 2131231584;
      }

      if (DvrData.file6Lock.equals("UNLOCK")) {
         var2 = var4;
      } else {
         var2 = 2131100046;
      }

      this.file_6.setData(var1, this.makeFileName(DvrData.file6NameList, 6), var2);
      if (DvrData.numberOfFiles == 0) {
         this.file_1.setVisibility(8);
         this.file_2.setVisibility(8);
         this.file_3.setVisibility(8);
         this.file_4.setVisibility(8);
         this.file_5.setVisibility(8);
         this.file_6.setVisibility(8);
      } else if (DvrData.numberOfFiles == 1) {
         this.file_1.setVisibility(0);
         this.file_2.setVisibility(8);
         this.file_3.setVisibility(8);
         this.file_4.setVisibility(8);
         this.file_5.setVisibility(8);
         this.file_6.setVisibility(8);
      } else if (DvrData.numberOfFiles == 2) {
         this.file_1.setVisibility(0);
         this.file_2.setVisibility(0);
         this.file_3.setVisibility(8);
         this.file_4.setVisibility(8);
         this.file_5.setVisibility(8);
         this.file_6.setVisibility(8);
      } else if (DvrData.numberOfFiles == 3) {
         this.file_1.setVisibility(0);
         this.file_2.setVisibility(0);
         this.file_3.setVisibility(0);
         this.file_4.setVisibility(8);
         this.file_5.setVisibility(8);
         this.file_6.setVisibility(8);
      } else if (DvrData.numberOfFiles == 4) {
         this.file_1.setVisibility(0);
         this.file_2.setVisibility(0);
         this.file_3.setVisibility(0);
         this.file_4.setVisibility(0);
         this.file_5.setVisibility(8);
         this.file_6.setVisibility(8);
      } else if (DvrData.numberOfFiles == 5) {
         this.file_1.setVisibility(0);
         this.file_2.setVisibility(0);
         this.file_3.setVisibility(0);
         this.file_4.setVisibility(0);
         this.file_5.setVisibility(0);
         this.file_6.setVisibility(8);
      } else if (DvrData.numberOfFiles == 6) {
         this.file_1.setVisibility(0);
         this.file_2.setVisibility(0);
         this.file_3.setVisibility(0);
         this.file_4.setVisibility(0);
         this.file_5.setVisibility(0);
         this.file_6.setVisibility(0);
      }

      this.file_1.setPlaying(this.isPlaying(DvrData.file1NameList, DvrData.filePlayingList));
      this.file_2.setPlaying(this.isPlaying(DvrData.file2NameList, DvrData.filePlayingList));
      this.file_3.setPlaying(this.isPlaying(DvrData.file3NameList, DvrData.filePlayingList));
      this.file_4.setPlaying(this.isPlaying(DvrData.file4NameList, DvrData.filePlayingList));
      this.file_5.setPlaying(this.isPlaying(DvrData.file5NameList, DvrData.filePlayingList));
      this.file_6.setPlaying(this.isPlaying(DvrData.file6NameList, DvrData.filePlayingList));
   }

   public void dataChange(String var1) {
      if (var1.equals("play.back.mode")) {
         this.updateUi();
      }

   }

   public void getAction(ActionCallback var1) {
      this.actionCallback = var1;
      this.prev_btn.getEventUpAction(new ActionCallback(this, var1) {
         final FileListView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("PREV");
            this.this$0.file_1.setSelect(false);
            this.this$0.file_2.setSelect(false);
            this.this$0.file_3.setSelect(false);
            this.this$0.file_4.setSelect(false);
            this.this$0.file_5.setSelect(false);
            this.this$0.file_6.setSelect(false);
         }
      });
      this.lock_btn.getEventUpAction(new ActionCallback(this, var1) {
         final FileListView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("ADD_LOCK");
         }
      });
      this.next_btn.getEventUpAction(new ActionCallback(this, var1) {
         final FileListView this$0;
         final ActionCallback val$actionCallback;

         {
            this.this$0 = var1;
            this.val$actionCallback = var2;
         }

         public void toDo(Object var1) {
            this.val$actionCallback.toDo("NEXT");
            this.this$0.file_1.setSelect(false);
            this.this$0.file_2.setSelect(false);
            this.this$0.file_3.setSelect(false);
            this.this$0.file_4.setSelect(false);
            this.this$0.file_5.setSelect(false);
            this.this$0.file_6.setSelect(false);
         }
      });
   }

   protected void onFinishInflate() {
      super.onFinishInflate();
      this.updateUi();
      DvrObserver.getInstance().register(this);
   }

   public void saveNowFile(List var1, boolean var2) {
      if (var2) {
         this.selectedFiles = var1;
      } else {
         this.selectedFiles = null;
         this.file_1.setSelect(false);
         this.file_2.setSelect(false);
         this.file_3.setSelect(false);
         this.file_4.setSelect(false);
         this.file_5.setSelect(false);
         this.file_6.setSelect(false);
      }

   }
}
