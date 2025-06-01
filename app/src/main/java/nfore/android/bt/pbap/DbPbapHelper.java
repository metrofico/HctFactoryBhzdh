package nfore.android.bt.pbap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DbPbapHelper extends SQLiteOpenHelper {
   private static final String CALLHISTORY_CONTENT = "CallHistory";
   public static final String[] CALLHISTORY_FIELD = new String[]{"_id", "FullName", "FirstName", "LastName", "CellPhone_Address", "StorageType", "PhoneType", "PhoneNumber", "HistoryDate", "HistoryTime"};
   private static final String DATABASE_NAME = "db_pbap";
   private static final int DATABASE_VERSION = 1;
   private static final String PHONEBOOK_CONTENT = "PhoneBookContent";
   public static final String[] PHONEBOOK_CONTENT_FIELD = new String[]{"_id", "FullName", "FirstName", "LastName", "First_StreetAddress", "First_CityNameAddress", "First_FederalStateAddress", "First_ZipCodeAddress", "First_CountryAddress", "Second_StreetAddress", "Second_CityNameAddress", "Second_FederalStateAddress", "Second_ZipCodeAddress", "Second_CountryAddress", "CellPhone_Address", "StorageType"};
   private static final String PHONENUMBER_DETAIL = "PhoneNumberDetail";
   public static final String[] PHONENUMBER_DETAIL_FIELD = new String[]{"_id", "Content_ID", "Type", "Number"};
   private static final String PHONE_TYPE = "PhoneType";
   public static final String[] PHONE_TYPE_FIELD = new String[]{"Type", "TypeName"};
   private final boolean D = true;
   private final String SQL_DELETE_CONTACTER = "delete from PhoneBookContent where FullName = ?";
   private final String SQL_DELETE_PHONENUMBER = "delete from PhoneNumberDetail where Number = ?";
   private final String SQL_DELETE_PHONENUMBER_BY_FULLNAME = "delete from PhoneNumberDetail where Content_ID in (select _id from PhoneBookContent where FullName = ?)";
   private final String SQL_EXPRESS_TOTAL = "select a.FullName from PhoneBookContent a where a.CellPhone_Address=? and StorageType=? group by a.FullName";
   private final String SQL_QUERY_CALLHISTORY_BY_ADDRESS_STORAGETYPE = "select a._id, a.FullName, a.StorageType, a.PhoneNumber, a.PhoneType, a.HistoryDate, a.HistoryTime from CallHistory a where a.CellPhone_Address = ? and a.StorageType=? order by ";
   private final String SQL_QUERY_CALLHISTORY_BY_SPECIFIED_COLUMNS = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?";
   private final String SQL_QUERY_CONTACTER = "select FullName from PhoneBookContent where _id = (select Content_ID from PhoneNumberDetail where Number like ? limit 1)";
   private final String SQL_QUERY_CONTACTERS = "select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName) and StorageType=? and CellPhone_Address = ? order by ";
   private final String SQL_QUERY_FULLNAME_BY_PHONENUM_CELLPHONEADDRESS = "select FullName from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number = ?) and CellPhone_Address = ?";
   private final String SQL_QUERY_PHONEBOOKCONTENT = "select * from PhoneBookContent where FullName like ? and StorageType = ? and CellPhone_Address = ?";
   private final String SQL_QUERY_PHONEBOOKCONTENT_BY_PHONENUM = "select * from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number like ?) and StorageType = ? and CellPhone_Address = ?";
   private final String SQL_QUERY_PHONEDATA_BY_PAGE = "select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName limit 10 offset ?) and StorageType=? and CellPhone_Address = ? order by FullName";
   private final String SQL_QUERY_PHONENUMBERDETAIL = "select a.*, b.TypeName from PhoneNumberDetail.a inner join PhoneType b on a.Type = b.Type where Number = ?";
   private final String SQL_QUERY_PHONETYPE_NAME = "select TypeName from PhoneType where Type = ? ";
   private final String SQL_QUERY_PHONE_BY_CONTENT_ID = "select a.*, b.TypeName as TypeName from PhoneNumberDetail a inner join PhoneType b on a.Type = b.Type where a.Content_ID = ?";
   private String TAG = "nfore DBHelper";
   private Context m_context;

   public DbPbapHelper(Context var1) {
      super(var1, "db_pbap", (SQLiteDatabase.CursorFactory)null, 1);
      this.m_context = var1;
   }

   private List collectionData(Cursor var1) {
      ArrayList var7 = new ArrayList();
      if (var1.moveToFirst()) {
         do {
            VCardPack var6 = new VCardPack();
            var6.setFullName(var1.getString(var1.getColumnIndex("FullName")));
            var6.set_id(var1.getInt(var1.getColumnIndex("_id")));
            PhoneInfo var3 = new PhoneInfo();
            HashSet var4 = new HashSet();
            String var5 = var1.getString(var1.getColumnIndex("StorageType"));
            int var2 = Integer.parseInt(var5);
            if (var2 < 3 && var2 > 0) {
               var3.setPhoneNumber(var1.getString(var1.getColumnIndex("Number")));
               var3.setPhoneType(var1.getString(var1.getColumnIndex("Type")));
            } else if (var2 >= 3 && var2 <= 5) {
               var3.setPhoneNumber(var1.getString(var1.getColumnIndex("PhoneNumber")));
               var3.setPhoneType(var1.getString(var1.getColumnIndex("PhoneType")));
               var6.setHistoryDate(var1.getString(var1.getColumnIndex("HistoryDate")));
               var6.setHistoryTime(var1.getString(var1.getColumnIndex("HistoryTime")));
            }

            var4.add(var3);
            var6.setPhoneNumbers(var4);
            var6.setStorageType(var5);
            var7.add(var6);
         } while(var1.moveToNext());
      }

      return var7;
   }

   private Cursor queryContacterByFullName(SQLiteDatabase var1, String var2, int var3, String var4) {
      return var1.rawQuery("select * from PhoneBookContent where FullName like ? and StorageType = ? and CellPhone_Address = ?", new String[]{var2 + "%", "" + var3, var4});
   }

   private Cursor queryContacterByPhoneNum(SQLiteDatabase var1, String var2, int var3, String var4) {
      return var1.rawQuery("select * from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number like ?) and StorageType = ? and CellPhone_Address = ?", new String[]{"%" + var2 + "%", "" + var3, var4});
   }

   public VCardList callHistoryBySpecifiedColumns(SQLiteDatabase var1, int var2, String var3, String var4, String var5, String var6) {
      ArrayList var7 = new ArrayList();
      var7.add(String.valueOf(var2));
      var7.add(var3);
      var2 = var4.trim().length();
      var3 = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?";
      if (var2 > 0) {
         var3 = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?" + " and a.HistoryDate like ?";
         var7.add("%" + var4 + "%");
      }

      var4 = var3;
      if (var5.trim().length() > 0) {
         var4 = var3 + " and a.HistoryTime like ?";
         var7.add("%" + var5 + "%");
      }

      var3 = var4;
      if (var6.trim().length() > 0) {
         var3 = var4 + " and a.PhoneNumber like ?";
         var7.add("%" + var6 + "%");
      }

      Cursor var9 = var1.rawQuery(var3, (String[])var7.toArray(new String[var7.size()]));
      List var8 = this.collectionData(var9);
      var9.close();
      VCardList var10 = new VCardList();
      var10.setVcardPacks(var8);
      return var10;
   }

   public void deleteAllTableContent(SQLiteDatabase var1) {
      var1.delete("PhoneNumberDetail", (String)null, (String[])null);
      var1.delete("PhoneBookContent", (String)null, (String[])null);
      var1.delete("CallHistory", (String)null, (String[])null);
   }

   public void deleteCallHistoryById(SQLiteDatabase var1, int var2) {
      var1.delete("CallHistory", "_id=?", new String[]{"" + var2});
   }

   public int deleteCallHistoryInfo(SQLiteDatabase var1, String var2, int var3) {
      return var1.delete("CallHistory", "CellPhone_Address=? and StorageType=?", new String[]{var2, String.valueOf(var3)});
   }

   public void deleteContacterByFullName(SQLiteDatabase var1, String var2) {
      var1.execSQL("delete from PhoneBookContent where FullName = ?", new String[]{var2});
   }

   public void deleteContacterById(SQLiteDatabase var1, int var2) {
      var1.delete("PhoneNumberDetail", "Content_ID=?", new String[]{"" + var2});
      var1.delete("PhoneBookContent", "_id=?", new String[]{"" + var2});
   }

   public void deletePhoneNumber(SQLiteDatabase var1, String var2) {
      var1.execSQL("delete from PhoneNumberDetail where Number = ?", new String[]{var2});
   }

   public void deletePhoneNumberByFullName(SQLiteDatabase var1, String var2) {
      var1.execSQL("delete from PhoneNumberDetail where Content_ID in (select _id from PhoneBookContent where FullName = ?)", new String[]{var2});
   }

   public int deleteVcardInfo(SQLiteDatabase var1, String var2, int var3) {
      byte var6 = 0;
      Cursor var7 = var1.query("PhoneBookContent", new String[]{"_id"}, "CellPhone_Address=? and StorageType=?", new String[]{var2, String.valueOf(var3)}, (String)null, (String)null, (String)null);
      int var4;
      int var5;
      if (var7.getCount() > 0 && var7.moveToNext()) {
         var5 = 0;

         do {
            var4 = var5 + var1.delete("PhoneNumberDetail", "Content_ID=?", new String[]{var7.getString(0)});
            var5 = var4;
         } while(var7.moveToNext());
      } else {
         var4 = 0;
      }

      var5 = var6;
      if (var4 >= 0) {
         var5 = 0 + var1.delete("PhoneBookContent", "CellPhone_Address=? and StorageType=?", new String[]{var2, String.valueOf(var3)});
      }

      var7.close();
      return var5;
   }

   public void insertCallHistoryInfo(SQLiteDatabase var1, VCardPack var2, int var3) {
      ContentValues var5 = new ContentValues();
      if (var2 != null) {
         var5.put("FullName", var2.getFullName());
         var5.put("FirstName", var2.getFirstName());
         var5.put("LastName", var2.getLastName());
         var5.put("CellPhone_Address", var2.getCellPhone_Address());
         var5.put("StorageType", var3);
         Iterator var6 = var2.getPhoneNumbers().iterator();

         while(var6.hasNext()) {
            PhoneInfo var4 = (PhoneInfo)var6.next();
            var5.put("PhoneType", var4.getPhoneType());
            var5.put("PhoneNumber", var4.getPhoneNumber());
         }

         var5.put("HistoryDate", var2.getHistoryDate());
         var5.put("HistoryTime", var2.getHistoryTime());
         var1.insert("CallHistory", (String)null, var5);
      }

   }

   public int insertNumberType(SQLiteDatabase var1, ArrayList var2) {
      int var3 = 0;
      long var4 = -1L;

      while(true) {
         try {
            if (var3 >= var2.size()) {
               break;
            }

            HashMap var9 = (HashMap)var2.get(var3);
            ContentValues var8 = new ContentValues();
            var8.put("Type", (String)var9.get("Type"));
            var8.put("TypeName", (String)var9.get("TypeName"));
            var4 = var1.insert("PhoneType", (String)null, var8);
         } catch (Exception var10) {
            var10.printStackTrace();
            Log.d(this.TAG, var10.getMessage());
            break;
         }

         ++var3;
      }

      return (int)var4;
   }

   public void insertPhoneType(SQLiteDatabase param1) {
      // $FF: Couldn't be decompiled
   }

   public void insertVcardInfo(SQLiteDatabase var1, VCardPack var2, int var3) {
      ContentValues var6 = new ContentValues();
      if (var2 != null) {
         var6.put("FullName", var2.getFullName());
         var6.put("FirstName", var2.getFirstName());
         var6.put("LastName", var2.getLastName());
         var6.put("First_StreetAddress", var2.getFirst_StreetAddress());
         var6.put("First_CityNameAddress", var2.getFirst_CityNameAddress());
         var6.put("First_FederalStateAddress", var2.getFirst_FederalStateAddress());
         var6.put("First_ZipCodeAddress", var2.getFirst_ZipCodeAddress());
         var6.put("First_CountryAddress", var2.getFirst_CountryAddress());
         var6.put("Second_StreetAddress", var2.getSecond_StreetAddress());
         var6.put("Second_CityNameAddress", var2.getSecond_CityNameAddress());
         var6.put("Second_FederalStateAddress", var2.getSecond_FederalStateAddress());
         var6.put("Second_ZipCodeAddress", var2.getSecond_ZipCodeAddress());
         var6.put("Second_CountryAddress", var2.getSecond_CountryAddress());
         var6.put("CellPhone_Address", var2.getCellPhone_Address());
         var6.put("StorageType", var3);
         long var4 = var1.insert("PhoneBookContent", (String)null, var6);
         Iterator var8 = var2.getPhoneNumbers().iterator();

         while(var8.hasNext()) {
            PhoneInfo var7 = (PhoneInfo)var8.next();
            ContentValues var9 = new ContentValues();
            var9.put("Content_ID", var4);
            var9.put("Type", var7.getPhoneType());
            var9.put("Number", var7.getPhoneNumber());
            var1.insert("PhoneNumberDetail", (String)null, var9);
         }
      }

   }

   public Cursor isEmptyPhoneType(SQLiteDatabase var1) {
      return var1.rawQuery("select count(*) as amount from PhoneType", (String[])null);
   }

   public void onCreate(SQLiteDatabase var1) {
      StringBuilder var3 = new StringBuilder("CREATE TABLE IF NOT EXISTS PhoneBookContent (");
      String[] var2 = PHONEBOOK_CONTENT_FIELD;
      var1.execSQL(var3.append(var2[0]).append(" INTEGER primary key autoincrement, ").append(var2[1]).append(" varchar(16), ").append(var2[2]).append(" varchar(8), ").append(var2[3]).append(" varchar(8), ").append(var2[4]).append(" varchar(20), ").append(var2[5]).append(" varchar(12), ").append(var2[6]).append(" varchar(12), ").append(var2[7]).append(" varchar(12), ").append(var2[8]).append(" varchar(30), ").append(var2[9]).append(" varchar(20), ").append(var2[10]).append(" varchar(12), ").append(var2[11]).append(" varchar(12), ").append(var2[12]).append(" varchar(12), ").append(var2[13]).append(" varchar(30), ").append(var2[14]).append(" varchar(30), ").append(var2[15]).append(" varchar(10) ").append(");").toString());
      var3 = new StringBuilder("CREATE TABLE IF NOT EXISTS PhoneNumberDetail (");
      String[] var4 = PHONENUMBER_DETAIL_FIELD;
      var1.execSQL(var3.append(var4[0]).append(" INTEGER primary key autoincrement, ").append(var4[1]).append(" INTEGER, ").append(var4[2]).append(" nvarchar(5), ").append(var4[3]).append(" nvarchar(20),").append("FOREIGN KEY(").append(var4[1]).append(") REFERENCES ").append("PhoneBookContent").append("(").append(var2[0]).append(") ").append(");").toString());
      StringBuilder var5 = new StringBuilder("CREATE TABLE IF NOT EXISTS PhoneType (");
      String[] var6 = PHONE_TYPE_FIELD;
      var1.execSQL(var5.append(var6[0]).append(" nvarchar(5) , ").append(var6[1]).append(" nvarchar(26) );").toString());
      var5 = new StringBuilder("CREATE TABLE IF NOT EXISTS CallHistory (");
      var6 = CALLHISTORY_FIELD;
      var1.execSQL(var5.append(var6[0]).append(" INTEGER primary key autoincrement, ").append(var6[1]).append(" nvarchar(16), ").append(var6[2]).append(" nvarchar(8), ").append(var6[3]).append(" nvarchar(8), ").append(var6[4]).append(" nvarchar(30) not null, ").append(var6[5]).append(" nvarchar(10) not null, ").append(var6[6]).append(" nvarchar(5) not null, ").append(var6[7]).append(" nvarchar(20) not null, ").append(var6[8]).append(" nvarchar(8) not null, ").append(var6[9]).append(" nvarchar(6) not null);").toString());
      this.insertPhoneType(var1);
   }

   public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
   }

   public List queryCallHistoryByAddressAndStorageType(SQLiteDatabase var1, String var2, String var3, String var4) {
      Cursor var10 = var1.rawQuery("select a._id, a.FullName, a.StorageType, a.PhoneNumber, a.PhoneType, a.HistoryDate, a.HistoryTime from CallHistory a where a.CellPhone_Address = ? and a.StorageType=? order by " + var4, new String[]{var2, var3});
      List var9 = this.collectionData(var10);
      var10.close();
      Iterator var8 = var9.iterator();

      while(var8.hasNext()) {
         VCardPack var12 = (VCardPack)var8.next();
         new HashSet();

         Cursor var7;
         for(int var5 = 0; var5 < var12.getPhoneNumbers().size(); ++var5) {
            for(Iterator var6 = var12.getPhoneNumbers().iterator(); var6.hasNext(); var7.close()) {
               PhoneInfo var11 = (PhoneInfo)var6.next();
               var7 = this.queryPhoneTypeName(var1, var11.getPhoneType());
               if (var7.moveToNext()) {
                  var11.setPhoneTypeName(var7.getString(0));
               }
            }
         }
      }

      return var9;
   }

   public Cursor queryContacterByPhoneNumAndAddress(SQLiteDatabase var1, String var2, String var3) {
      return var1.rawQuery("select FullName from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number = ?) and CellPhone_Address = ?", new String[]{var2, var3});
   }

   public VCardList queryContacterInfo(SQLiteDatabase var1, String var2, String var3, int var4, String var5) {
      boolean var8 = true;
      boolean var6;
      if (var2 != null && var2.trim().length() > 0) {
         var6 = true;
      } else {
         var6 = false;
      }

      boolean var7;
      if (var3 != null && var3.trim().length() > 0) {
         var7 = true;
      } else {
         var7 = false;
      }

      if (!var6 || !var7) {
         var8 = false;
      }

      HashSet var9 = null;
      Cursor var13;
      if (var6) {
         var13 = this.queryContacterByFullName(var1, var2, var4, var5);
      } else {
         var13 = var9;
         if (var7) {
            var13 = this.queryContacterByPhoneNum(var1, var3, var4, var5);
         }
      }

      ArrayList var14 = new ArrayList();
      if (var13 != null && var13.getCount() > 0) {
         while(var13.moveToNext()) {
            Cursor var11 = this.queryPhoneNumberByContentId(var1, var13.getString(var13.getColumnIndex("_id")));
            int var15 = var11.getColumnIndex("Number");
            var4 = var11.getColumnIndex("TypeName");
            var9 = new HashSet();

            while(var11.moveToNext() && (!var8 || var11.getString(var15).trim().indexOf(var3) != -1)) {
               PhoneInfo var10 = new PhoneInfo();
               var10.setPhoneNumber(var11.getString(var15));
               var10.setPhoneTypeName(var11.getString(var4));
               var9.add(var10);
            }

            if (var9.size() > 0) {
               VCardPack var16 = new VCardPack(var13);
               var16.setPhoneNumbers(var9);
               var14.add(var16);
            }
         }
      }

      VCardList var12 = new VCardList();
      var12.setVcardPacks(var14);
      return var12;
   }

   public List queryContactersInfo(SQLiteDatabase var1, String var2, String var3, String var4) {
      Cursor var10 = var1.rawQuery("select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName) and StorageType=? and CellPhone_Address = ? order by " + var4, new String[]{var2, var3, var3, var2});
      List var9 = this.collectionData(var10);
      var10.close();
      Iterator var12 = var9.iterator();

      while(var12.hasNext()) {
         VCardPack var6 = (VCardPack)var12.next();
         new HashSet();

         Cursor var8;
         for(int var5 = 0; var5 < var6.getPhoneNumbers().size(); ++var5) {
            for(Iterator var7 = var6.getPhoneNumbers().iterator(); var7.hasNext(); var8.close()) {
               PhoneInfo var11 = (PhoneInfo)var7.next();
               var8 = this.queryPhoneTypeName(var1, var11.getPhoneType());
               if (var8.moveToNext()) {
                  var11.setPhoneTypeName(var8.getString(0));
               }
            }
         }
      }

      return var9;
   }

   public String queryNameByPhoneNum(String var1) {
      SQLiteDatabase var2 = this.getReadableDatabase();
      Cursor var3 = var2.rawQuery("select FullName from PhoneBookContent where _id = (select Content_ID from PhoneNumberDetail where Number like ? limit 1)", new String[]{var1 + "%"});
      if (var3.getCount() > 0 && var3.moveToFirst()) {
         var1 = var3.getString(0);
      } else {
         var1 = "N/A";
      }

      var3.close();
      var2.close();
      return var1;
   }

   public Cursor queryNumberDetailByPhoneNumber(SQLiteDatabase var1, String var2) {
      return var1.rawQuery("select a.*, b.TypeName from PhoneNumberDetail.a inner join PhoneType b on a.Type = b.Type where Number = ?", new String[]{var2});
   }

   public List queryPhoneDataByPage(SQLiteDatabase var1, String var2, int var3, int var4, String var5) {
      Cursor var11 = var1.rawQuery("select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName limit 10 offset ?) and StorageType=? and CellPhone_Address = ? order by FullName", new String[]{var2, var5, String.valueOf(var3 * var4), var5, var2});
      List var10 = this.collectionData(var11);
      var11.close();
      Iterator var6 = var10.iterator();

      while(var6.hasNext()) {
         VCardPack var9 = (VCardPack)var6.next();
         new HashSet();

         for(var3 = 0; var3 < var9.getPhoneNumbers().size(); ++var3) {
            for(Iterator var7 = var9.getPhoneNumbers().iterator(); var7.hasNext(); var11.close()) {
               PhoneInfo var8 = (PhoneInfo)var7.next();
               var11 = this.queryPhoneTypeName(var1, var8.getPhoneType());
               if (var11.moveToNext()) {
                  var8.setPhoneTypeName(var11.getString(0));
               }
            }
         }
      }

      return var10;
   }

   public Cursor queryPhoneNumberByContentId(SQLiteDatabase var1, String var2) {
      return var1.rawQuery("select a.*, b.TypeName as TypeName from PhoneNumberDetail a inner join PhoneType b on a.Type = b.Type where a.Content_ID = ?", new String[]{var2});
   }

   public Cursor queryPhoneTypeName(SQLiteDatabase var1, String var2) {
      String var3 = var2;
      if (var2 == null) {
         var3 = "C";
      }

      return var1.rawQuery("select TypeName from PhoneType where Type = ? ", new String[]{var3});
   }

   public int queryTotalAmount(SQLiteDatabase var1, String var2, String var3) {
      Cursor var5 = var1.rawQuery("select a.FullName from PhoneBookContent a where a.CellPhone_Address=? and StorageType=? group by a.FullName", new String[]{var2, var3});
      int var4 = var5.getCount();
      var5.close();
      return var4;
   }
}
