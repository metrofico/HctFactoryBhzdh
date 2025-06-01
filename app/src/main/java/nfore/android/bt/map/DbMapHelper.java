package nfore.android.bt.map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DbMapHelper extends SQLiteOpenHelper {
   public static final String DATABASE_NAME = "db_map";
   private static final int DATABASE_VERSION = 1;
   public static final String TABLE_NAME = "MessageContent";
   private final String SQL_DELETE_All_MESSAGE = "delete from MessageContent";
   private final String SQL_DELETE_FOLDER = "delete from MessageContent where macAddress = ? and folder = ?";
   private final String SQL_DELETE_MESSAGE = "delete from MessageContent where macAddress = ?";
   private final String SQL_DELETE_MESSAGE_BY_FOLDER = "delete from MessageContent where folder = ? and handle = ? and macAddress = ?";
   private final String SQL_DELETE_ONE_MESSAGE = "delete from MessageContent where macAddress = ? and handle = ? and datetime=?";
   private final String SQL_MESSAGE = "select * from MessageContent where condition = ?";
   private final String SQL_SELECT_MESSAGE = "select * from MessageContent where macAddress = ?";
   private final String SQL_SELECT_MESSGE_BY_FOLDER_AND_HANDLE = "select * from MessageContent where folder = ? and handle = ? and macAddress = ?";
   private final String SQL_SELECT_ONE_MESSAGE = "select * from MessageContent where macAddress = ? and handle = ? and folder = ?";
   private String TAG = "DbMapHelper";
   private String _id = "_id";
   private String datetime = "datetime";
   private String folder = "folder";
   private String handle = "handle";
   private Object helper;
   private Context m_context;
   private String macAddress = "macAddress";
   private String message = "message";
   private String read = "read";
   private String recipient_addressing = "recipient_addressing";
   private String sender_addressing = "sender_addressing";
   private String sender_name = "sender_name";
   private String size = "size";
   private String subject = "subject";

   public DbMapHelper(Context var1) {
      super(var1, "db_map", (SQLiteDatabase.CursorFactory)null, 1);
      this.m_context = var1;
   }

   public void deleteAllMessage(SQLiteDatabase var1, String var2) {
      var1.execSQL("delete from MessageContent", new String[]{var2});
   }

   public void deleteAllTableContent(SQLiteDatabase var1) {
      var1.delete("MessageContent", (String)null, (String[])null);
   }

   public void deleteMessageByFolderAndHandle(SQLiteDatabase var1, String var2, String var3, String var4) {
      var1.execSQL("delete from MessageContent where folder = ? and handle = ? and macAddress = ?", new String[]{var2, var3, var4});
   }

   public void deleteMessageByMacAddress(SQLiteDatabase var1, String var2) {
      var1.execSQL("delete from MessageContent where macAddress = ?", new String[]{var2});
   }

   public void deleteMessageByMacAddressAndFolder(SQLiteDatabase var1, String var2, String var3) {
      var1.execSQL("delete from MessageContent where macAddress = ? and folder = ?", new String[]{var2, var3});
   }

   public void deleteOneMessageByMacAddress(SQLiteDatabase var1, String var2) {
      var1.execSQL("delete from MessageContent where macAddress = ? and handle = ? and datetime=?", new String[]{var2});
   }

   public void insertMessageInfo(SQLiteDatabase var1, MsgOutline var2) {
      Log.e(this.TAG, "insertMessageInfo");
      ContentValues var3 = new ContentValues();
      if (var2 != null) {
         Log.e(this.TAG, "insertMessageInfo " + var2.getHandle());
         var3.put("Folder", var2.getFolder());
         var3.put("Handle", var2.getHandle());
         var3.put("Subject", var2.getSubject());
         var3.put("Message", var2.getMessage());
         var3.put("Datetime", var2.getDatetime());
         var3.put("Sender_Name", var2.getSender_name());
         var3.put("Sender_Addressing", var2.getSender_addressing());
         var3.put("Recipient_Addressing", var2.getRecipient_addressing());
         var3.put("Size", var2.getSize());
         var3.put("Read", var2.getRead());
         var3.put("macAddress", var2.getMacAddress());
         var1.insert("MessageContent", (String)null, var3);
      }

   }

   public boolean isMessageInDatabase(SQLiteDatabase var1, String var2, String var3, String var4) {
      Log.e(this.TAG, "Piggy Check isMessageInDatabase address : " + var2 + " handle : " + var3 + " folder : " + var4);
      if (!var4.equals("inbox") && !var4.equals("sent")) {
         Log.e(this.TAG, "folder parameter error !");
         return false;
      } else {
         Cursor var6 = var1.rawQuery("select * from MessageContent where macAddress = ? and handle = ? and folder = ?", new String[]{var2, var3, var4});
         Log.e(this.TAG, "Piggy Check isMessageInDatabase cursor count : " + var6.getCount());
         var6.moveToFirst();
         var2 = var6.getString(var6.getColumnIndex("_id"));
         var3 = var6.getString(var6.getColumnIndex("handle"));
         String var5 = var6.getString(var6.getColumnIndex("datetime"));
         Log.e(this.TAG, "Piggy Check ID : " + var2 + " handle : " + var3 + " DateTime : " + var5);
         if (var6.getCount() == 1) {
            return true;
         } else if (var6.getCount() > 1) {
            Log.e(this.TAG, "Piggy Check have same handle same folder in database. don't know why .");
            return true;
         } else {
            return false;
         }
      }
   }

   public void onCreate(SQLiteDatabase var1) {
      Log.d(this.TAG, "onCreate");
      var1.execSQL("CREATE TABLE IF NOT EXISTS MessageContent (" + this._id + " Integer primary key autoincrement, " + this.folder + " varchar(6), " + this.handle + " varchar(256), " + this.subject + " varchar(256), " + this.message + " varchar(256), " + this.datetime + " varchar(256), " + this.sender_name + " varchar(256), " + this.sender_addressing + " varchar(256), " + this.recipient_addressing + " varchar(256), " + this.size + " varchar(256), " + this.read + " varchar(3), " + this.macAddress + " varchar(17)) ");
   }

   public void onUpdate(SQLiteDatabase var1, String var2) {
   }

   public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
      var1.execSQL("DROP TABLE IF EXISTS config");
      this.onCreate(var1);
   }

   public Cursor queryMessage(SQLiteDatabase var1, String var2) {
      return var1.rawQuery("select * from MessageContent where condition = ?", new String[]{var2});
   }

   public Cursor queryMessageByMacAddress(SQLiteDatabase var1, String var2) {
      return var1.rawQuery("select * from MessageContent where macAddress = ?", new String[]{var2});
   }

   public MsgOutline queryMessageByfolderAndHandle(SQLiteDatabase var1, String var2, String var3, String var4) {
      Cursor var6 = var1.rawQuery("select * from MessageContent where folder = ? and handle = ? and macAddress = ?", new String[]{var2, var3, var4});
      MsgOutline var5 = new MsgOutline();
      var6.moveToFirst();
      var5.setFolder(var6.getString(1));
      var5.setHandle(var6.getString(2));
      var5.setSubject(var6.getString(3));
      var5.setMessage(var6.getString(4));
      var5.setDatetime(var6.getString(5));
      var5.setSender_name(var6.getString(6));
      var5.setSender_addressing(var6.getString(7));
      var5.setRecipient_addressing(var6.getString(8));
      var5.setSize(var6.getString(9));
      var5.setRead(var6.getString(10));
      var6.close();
      return var5;
   }

   public ArrayList queryMessageInfo(String var1, SQLiteDatabase var2) {
      Cursor var6 = var2.query("MessageContent", new String[]{var1}, (String)null, (String[])null, (String)null, (String)null, (String)null);
      int var4 = var6.getCount();
      ArrayList var5 = new ArrayList();
      Log.e(this.TAG, "Piggy Check rows counts : " + var4);
      if (var4 != 0) {
         for(int var3 = 0; var3 < var4; ++var3) {
            if (var3 == 0) {
               var6.moveToFirst();
            } else {
               var6.moveToNext();
            }

            var5.add(var6.getString(0));
         }

         var6.close();
      }

      return var5;
   }

   public Cursor queryOneMessageByMacAddress(SQLiteDatabase var1, String var2, String var3, String var4) {
      if (!var4.equals("inbox") && !var4.equals("sent")) {
         Log.e(this.TAG, "folder parameter error !");
         return null;
      } else {
         return var1.rawQuery("select * from MessageContent where macAddress = ? and handle = ? and folder = ?", new String[]{var2, var3, var4});
      }
   }
}
