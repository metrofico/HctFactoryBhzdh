package nfore.android.bt.a2dp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAvrcpHelper extends SQLiteOpenHelper {
   private static boolean D;
   private static final String DATABASE_NAME = "avrcp.db";
   private static final int DATABASE_VERSION = 1;
   private static final String TAG = "nFore_DBAvrcpHelper";
   public final String TABLE_FOLDER_ITEMS = "FolderItems";
   public final String TABLE_MEDIA_ITEMS = "MediaItems";
   public final String TABLE_MEDIA_PLAYER_ITEMS = "MediaPlayerItems";
   public String clearFolderItems = "delete from FolderItems;";
   public String clearMediaItems = "delete from MediaItems where ScopeId = ";
   public String clearMediaPlayerItems = "delete from MediaPlayerItems;";
   public String insertFolderItems = "insert into FolderItems value(?, ?, ?, ?, ?);";
   public String insertMediaItems = "insert into MediaItems(UIDcounter, UID, MediaType, Name) value(?, ?, ?, ?);";
   public String insertMediaPlayerItems = "insert into MediaPlayerItems value(?, ?, ?, ?, ?, ?, ?);";
   public String selectFolderItems = "select * from FolderItems order by FolderType, Name;";
   public String selectMediaItems = "select * from MediaItems where ScopeId = ? order by MediaType, Name;";
   public String selectMediaPlayerItems = "select * from MediaPlayerItems order by MajorPlayerType, Name;";
   public String updateMediaItems = "update MediaItems set Title = ? where uid = ?";

   public DbAvrcpHelper(Context var1) {
      super(var1, "avrcp.db", (SQLiteDatabase.CursorFactory)null, 1);
   }

   public void onCreate(SQLiteDatabase var1) {
      if (D) {
         Log.d("nFore_DBAvrcpHelper", "+++ Begin of onCreate() +++");
      }

      var1.execSQL("create table if not exists MediaPlayerItems( _ID INTEGER primary key autoincrement, UIDcounter smallint, PlayerId smallint, MajorPlayerType blob, PlayerSubType int, PlayStatus blob, FeatureBitMask blob, Name nvarchar(20));");
      var1.execSQL("create table if not exists FolderItems(_ID INTEGER primary key autoincrement, UIDcounter smallint, UID bigint, FolderType blob, IsPlayable blob, Name nvarchar(20));");
      var1.execSQL("create table if not exists MediaItems(_ID INTEGER primary key autoincrement, ScopeId smallint, UIDcounter smallint, UID bigint, MediaType blob, Name nvarchar(20), Title nvarchar(20), Artist nvarchar(20), Album nvarchar(20), TrackNumber nvarchar(5), TotalTracks nvarchar(6), Genre nvarchar(10), Time_ms nvarchar(10));");
   }

   public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
      if (D) {
         Log.d("nFore_DBAvrcpHelper", "+++ Begin of onUpgrade() +++");
      }

   }
}
