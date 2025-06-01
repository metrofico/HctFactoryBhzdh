package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleCursorAdapter extends ResourceCursorAdapter {
   private CursorToStringConverter mCursorToStringConverter;
   protected int[] mFrom;
   String[] mOriginalFrom;
   private int mStringConversionColumn = -1;
   protected int[] mTo;
   private ViewBinder mViewBinder;

   @Deprecated
   public SimpleCursorAdapter(Context var1, int var2, Cursor var3, String[] var4, int[] var5) {
      super(var1, var2, var3);
      this.mTo = var5;
      this.mOriginalFrom = var4;
      this.findColumns(var3, var4);
   }

   public SimpleCursorAdapter(Context var1, int var2, Cursor var3, String[] var4, int[] var5, int var6) {
      super(var1, var2, var3, var6);
      this.mTo = var5;
      this.mOriginalFrom = var4;
      this.findColumns(var3, var4);
   }

   private void findColumns(Cursor var1, String[] var2) {
      if (var1 != null) {
         int var4 = var2.length;
         int[] var5 = this.mFrom;
         if (var5 == null || var5.length != var4) {
            this.mFrom = new int[var4];
         }

         for(int var3 = 0; var3 < var4; ++var3) {
            this.mFrom[var3] = var1.getColumnIndexOrThrow(var2[var3]);
         }
      } else {
         this.mFrom = null;
      }

   }

   public void bindView(View var1, Context var2, Cursor var3) {
      ViewBinder var8 = this.mViewBinder;
      int[] var9 = this.mTo;
      int var5 = var9.length;
      int[] var10 = this.mFrom;

      for(int var4 = 0; var4 < var5; ++var4) {
         View var11 = var1.findViewById(var9[var4]);
         if (var11 != null) {
            boolean var6;
            if (var8 != null) {
               var6 = var8.setViewValue(var11, var3, var10[var4]);
            } else {
               var6 = false;
            }

            if (!var6) {
               String var7 = var3.getString(var10[var4]);
               String var12 = var7;
               if (var7 == null) {
                  var12 = "";
               }

               if (var11 instanceof TextView) {
                  this.setViewText((TextView)var11, var12);
               } else {
                  if (!(var11 instanceof ImageView)) {
                     throw new IllegalStateException(var11.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
                  }

                  this.setViewImage((ImageView)var11, var12);
               }
            }
         }
      }

   }

   public void changeCursorAndColumns(Cursor var1, String[] var2, int[] var3) {
      this.mOriginalFrom = var2;
      this.mTo = var3;
      this.findColumns(var1, var2);
      super.changeCursor(var1);
   }

   public CharSequence convertToString(Cursor var1) {
      CursorToStringConverter var3 = this.mCursorToStringConverter;
      if (var3 != null) {
         return var3.convertToString(var1);
      } else {
         int var2 = this.mStringConversionColumn;
         return (CharSequence)(var2 > -1 ? var1.getString(var2) : super.convertToString(var1));
      }
   }

   public CursorToStringConverter getCursorToStringConverter() {
      return this.mCursorToStringConverter;
   }

   public int getStringConversionColumn() {
      return this.mStringConversionColumn;
   }

   public ViewBinder getViewBinder() {
      return this.mViewBinder;
   }

   public void setCursorToStringConverter(CursorToStringConverter var1) {
      this.mCursorToStringConverter = var1;
   }

   public void setStringConversionColumn(int var1) {
      this.mStringConversionColumn = var1;
   }

   public void setViewBinder(ViewBinder var1) {
      this.mViewBinder = var1;
   }

   public void setViewImage(ImageView var1, String var2) {
      try {
         var1.setImageResource(Integer.parseInt(var2));
      } catch (NumberFormatException var4) {
         var1.setImageURI(Uri.parse(var2));
      }

   }

   public void setViewText(TextView var1, String var2) {
      var1.setText(var2);
   }

   public Cursor swapCursor(Cursor var1) {
      this.findColumns(var1, this.mOriginalFrom);
      return super.swapCursor(var1);
   }

   public interface CursorToStringConverter {
      CharSequence convertToString(Cursor var1);
   }

   public interface ViewBinder {
      boolean setViewValue(View var1, Cursor var2, int var3);
   }
}
