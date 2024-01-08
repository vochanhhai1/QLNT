package com.example.tt_app.View;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tt_app.model.DataClass;
import com.example.tt_app.model.DataNguoithue;
import com.example.tt_app.model.DataPhong;


public class dbmanager extends SQLiteOpenHelper {
    private static final String dbname = "dbdichvutest21.db";
    private static Context context;

    public dbmanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry = "create table tbl_dichvu (id_dichvu integer primary key autoincrement,uploadtopic text, uploaddonvido text, uploaddichvu int, uploadnote text, uploadhinhanh Blod)";
        sqLiteDatabase.execSQL(qry);
        sqLiteDatabase.execSQL("create Table users(id_user integer primary key autoincrement,hovaten text,email TEXT, sodienthoai int,password TEXT)");
        sqLiteDatabase.execSQL("create Table tbl_phong(maphong integer primary key autoincrement,phong text,chiphithue integer,dientich integer,songuoithue integer,tiencoc integer,doituong text,anhphong text,gianuoc int,giadien int,mota text,lydo text)");
        sqLiteDatabase.execSQL("create Table tbl_nguoithue1(id_nguoithue integer primary key autoincrement,hovaten text,sodienthoai int,chonphong TEXT ,email TEXT,ngaysinh DATE,cmnd int,ngaycap DATE,noicap TEXT,diachi TEXT, anhcm TEXT,maphong integer, FOREIGN KEY(maphong) REFERENCES tbl_phong(maphong))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String qry = "DROP TABLE IF EXISTS tbl_dichvu";
        sqLiteDatabase.execSQL(qry);
        sqLiteDatabase.execSQL("drop Table if exists users");
        sqLiteDatabase.execSQL("drop Table if exists tbl_nguoithue1");
        sqLiteDatabase.execSQL("drop Table if exists tbl_phong");
        onCreate(sqLiteDatabase);
    }

    public String insertData_dichvu(String uploadtopic, String uploaddonvido, Integer uploaddichvu, String uploadnote, byte[] uploadhinhanh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uploadtopic", uploadtopic);
        cv.put("uploaddonvido", uploaddonvido);
        cv.put("uploaddichvu", uploaddichvu);
        cv.put("uploadnote", uploadnote);
        cv.put("uploadhinhanh", uploadhinhanh);

        float res = db.insert("tbl_dichvu", null, cv);

        if (res == -1)
            return "Thêm thất bại";
        else
            return "Thêm thành công!!";

    }

    public void insertDataPhong(String phong, Integer chiphithue, Integer dientich, Integer songuoithue, Integer tiencoc, String doituong, String anhphong,String mota, String lydo, Integer gianuoc, Integer giadien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("phong", phong);
        cv.put("chiphithue", chiphithue);
        cv.put("dientich", dientich);
        cv.put("songuoithue", songuoithue);
        cv.put("tiencoc", tiencoc);
        cv.put("doituong", doituong);
        cv.put("anhphong", anhphong);
        cv.put("mota", mota);
        cv.put("lydo", lydo);
        cv.put("gianuoc", gianuoc);
        cv.put("giadien", giadien);


        db.insert("tbl_phong", null, cv);

    }

    public void insertData_nguoithue(String hovaten, Integer sodienthoai, String chonphong, String email, String ngaysinh, Integer cmnd, String ngaycap, String noicap, String diachi, String anhcm) {
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv=new ContentValues();
//        cv.put("hovaten",hovaten);
//        cv.put("sodienthoai",sodienthoai);
//        cv.put("chonphong",chonphong);
//        cv.put("email",email);
//        cv.put("ngaysinh",ngaysinh);
//        cv.put("cmnd",cmnd);
//        cv.put("ngaycap",ngaycap);
//        cv.put("noicap",noicap);
//        cv.put("diachi",diachi);
//        cv.put("anhcm",anhcm);
//        cv.put("maphong",maphong);

        String insertData = "INSERT INTO tbl_nguoithue1 (hovaten, sodienthoai, chonphong, email, ngaysinh, cmnd, ngaycap, noicap, diachi, anhcm, maphong) " +
                "VALUES ('" + hovaten + "', '" + sodienthoai + "', '" + chonphong + "', '" + email + "', '" + ngaysinh + "', '" + cmnd + "', '" + ngaycap + "', '" + noicap + "', '" + diachi + "', '" + anhcm + "'," +
                " (SELECT maphong FROM tbl_phong WHERE phong = '" + chonphong + "'))";

        db.execSQL(insertData);
//        float res=db.insert("tbl_nguoithue1",null,cv);

//        if(res==-1)
//            return "Thêm thất bại";
//        else
//            return  "Thêm thành công!!";


    }

    public Boolean insertData_user(String hovaten, String email, int sodienthoai, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hovaten", hovaten);
        contentValues.put("email", email);
        contentValues.put("sodienthoai", sodienthoai);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updatePassword(Integer id, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = MyDatabase.update("users", contentValues, "id =?", new String[]{id.toString()});
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void UpdateDichvu(DataClass dataClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uploadtopic", dataClass.getUploadTopic());
        cv.put("uploaddonvido", dataClass.getUploadDonvido());
        cv.put("uploaddichvu", dataClass.getUploadDichvu());
        cv.put("uploadnote", dataClass.getUploadNote());
        cv.put("uploadhinhanh", dataClass.getUploadhinhanh());

        db.update("tbl_dichvu", cv, "id_dichvu = '" + dataClass.getId_dichvu() + "'", null);

//        String sql = "UPDATE tbl_dichvu SET uploadtopic='" + dataClass.getUploadTopic() +
//                "', uploaddonvido='" + dataClass.getUploadDonvido() + "', uploaddichvu='" + dataClass.getUploadDichvu()+
//                "', uploadnote='" + dataClass.getUploadNote()+ "', uploadhinhanh='" + dataClass.getUploadhinhanh()+
//                "' WHERE uploadtopic='" + dataClass.getUploadTopic() + "'";
//        db.execSQL(sql);
        db.close();
    }
//    Integer maphong,String phong, Integer chiphithue, Integer dientich, Integer songuoithue, Integer tiencoc, String doituong, String anhphong, String mota, String lydo, Integer gianuoc,Integer giadien
    public void updateDataPhong(DataPhong dataPhong) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("phong", dataPhong.getPhong());
        cv.put("chiphithue", dataPhong.getChiphithue());
        cv.put("dientich", dataPhong.getDientich());
        cv.put("songuoithue", dataPhong.getSonguoithue());
        cv.put("tiencoc", dataPhong.getTiencoc());
        cv.put("doituong", dataPhong.getDoituong());
        cv.put("anhphong", dataPhong.getAnhphong());
        cv.put("mota", dataPhong.getMota());
        cv.put("lydo", dataPhong.getLydo());
        cv.put("gianuoc", dataPhong.getGianuoc());
        cv.put("giadien", dataPhong.getGiadien());


        db.update("tbl_phong", cv,"maphong = '"+dataPhong.getMaphong() +"'",null);
    }

    public void UpdateNguoithue(DataNguoithue dataNguoithue) {
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("hovaten", dataNguoithue.getHovaten());
//        cv.put("sodienthoai", dataNguoithue.getSodienthoai());
//        cv.put("chonphong", dataNguoithue.getChonphong());
//        cv.put("email", dataNguoithue.getEmail());
//        cv.put("ngaysinh", dataNguoithue.getNgaysinh());
//        cv.put("cmnd", dataNguoithue.getCmnd());
//        cv.put("ngaycap", dataNguoithue.getNgaycap());
//        cv.put("noicap", dataNguoithue.getNoicap());
//        cv.put("diachi", dataNguoithue.getDiachi());
//        cv.put("anhcm", dataNguoithue.getAnhcm());
//
//        db.update("tbl_nguoithue1", cv, "id_nguoithue = '" + dataNguoithue.getId_dichvu() + "'", null);
//        db.close();

        String updateData = "UPDATE  tbl_nguoithue1 " +
                "SET hovaten ='" + dataNguoithue.getHovaten() + "'," +
                "sodienthoai= '" + dataNguoithue.getSodienthoai() + "'," +
                "chonphong ='" + dataNguoithue.getChonphong() + "', " +
                "email ='" + dataNguoithue.getEmail() + "'," +
                "ngaysinh= '" + dataNguoithue.getNgaysinh() + "'," +
                "cmnd= '" + dataNguoithue.getCmnd() + "'," +
                " ngaycap='" + dataNguoithue.getNgaycap() + "'," +
                "noicap= '" + dataNguoithue.getNoicap() + "'," +
                "diachi='" + dataNguoithue.getDiachi() + "'," +
                "anhcm= '" + dataNguoithue.getAnhcm() + "'," +
                "maphong= '" + dataNguoithue.getChonphong() + "'" +
                "WHERE id_nguoithue='"+dataNguoithue.getId_dichvu()+"'";
        db.execSQL(updateData);
    }

    public void DeleteDichvu(DataClass dataClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM tbl_dichvu WHERE id_dichvu='" + dataClass.getId_dichvu() + "'";
        db.execSQL(sql);

        db.close();
//        if (sql==-1)
//        {
//            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
//        }else
//        {
//            Toast.makeText(context, "succesfully", Toast.LENGTH_SHORT).show();
//        }

    }
    public void DeletePhong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM tbl_phong WHERE maphong='" + id + "'";
        db.execSQL(sql);
        db.close();
    }
    public void DeleteNguoithue(DataNguoithue dataNguoithue) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM tbl_nguoithue1 WHERE id_nguoithue='" + dataNguoithue.getId_dichvu() + "'";
        db.execSQL(sql);
        db.close();
    }

    public Cursor readNguoiThuetoPhong(int maphong) {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT tbl_nguoithue1.*, tbl_phong.phong " +
                "FROM tbl_nguoithue1 " +
                "JOIN tbl_phong ON tbl_nguoithue1.maphong = tbl_phong.maphong " +
                "WHERE tbl_phong.maphong = " + maphong;
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }
    public Cursor ReadDoiTuongToPhong(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT doituong FROM tbl_phong WHERE maphong = " + id;
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }
    public Cursor CountNguoiThueInPhong(int maphong) {
        SQLiteDatabase db = this.getWritableDatabase();
            String qry = "SELECT COUNT(tbl_nguoithue1.id_nguoithue) AS songuoi\n" +
                    "FROM tbl_nguoithue1 \n" +
                    "JOIN tbl_phong ON tbl_nguoithue1.maphong = tbl_phong.maphong \n" +
                    "WHERE tbl_phong.maphong = " + maphong  +
                    "\n GROUP BY tbl_phong.phong";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }

    public Cursor readTenPhong() {
        SQLiteDatabase db = this.getWritableDatabase();
        // String qry = "select tbl_phong.phong from tbl_phong join tbl_nguoithue1 on tbl_phong.maphong = tbl_nguoithue1.maphong ";
        String qry = "select phong from tbl_phong";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }

    public Cursor readalldatausers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from users";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;

    }


    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_dichvu";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;

    }
    public Cursor readalldataNguoithue() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT * FROM tbl_nguoithue1 WHERE chonphong != ''";;
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }

    public Cursor readalldataNguoithuerong() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "SELECT * FROM tbl_nguoithue1 WHERE chonphong = ''";;
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }

    public Cursor readalldataPhong() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = "select * from tbl_phong";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;

    }
}