package cn.fbmms;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//实现Excel导入数据库核心类，读取Excel表中所有的数据，操作数据（查询、更新）
public class KFService {
    /**
     * 查询数据库中Student表中所有的数据
     */
    public static List<KeyFrames> getAllByDb(){
        List<KeyFrames> list = new ArrayList<KeyFrames>();
        DBhelper db = new DBhelper();
        Calendar cal=Calendar.getInstance();
        // 当前年
        int year = cal.get(Calendar.YEAR);
        // 当前月
        int month = cal.get(Calendar.MONTH) + 1;
        // 当前日
        int day = cal.get(Calendar.DATE)-1;
        // 创建可写入的Excel工作簿
        String cdate = ""+year+"-"+(month>10?month:"0"+month)+"-"+(day>10?day:"0"+day);
        String sql = "select id,cnt_date,cnt_time,sum_cnt,fiveframe_cnt,fiveframe_rate,sixframe_cnt,sixframe_rate,keyframe_cnt,keyframe_rate from fsk_keyframes where cnt_date = '"+cdate+"'";
        ResultSet rs = db.Search(sql, null);
        try {
            while(rs.next()){
                String id = rs.getString("id");
                String cnt_date = rs.getString("cnt_date");
                String cnt_time = rs.getString("cnt_time");
                int sum_cnt = rs.getInt("sum_cnt");
                int fiveframe_cnt = rs.getInt("fiveframe_cnt");
                double fiveframe_rate = rs.getDouble("fiveframe_rate");
                int sixframe_cnt = rs.getInt("sixframe_cnt");
                double sixframe_rate = rs.getDouble("sixframe_rate");
                int keyframe_cnt = rs.getInt("keyframe_cnt");
                double keyframe_rate = rs.getDouble("keyframe_rate");
                list.add(new KeyFrames(id,cnt_date,cnt_time,sum_cnt,fiveframe_cnt,fiveframe_rate,sixframe_cnt,sixframe_rate,keyframe_cnt,keyframe_rate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 查询指定目录中Excel表格中所有数据
     * @param
     * @return
     */
    public static List<KeyFrames> getAllByExcel(String file){
        List<KeyFrames> list = new ArrayList<KeyFrames>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet("Test Shee 1"); //或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            System.out.println("clos:"+clos+" rows:"+rows);
            for(int i=1;i<rows;i++){
                int j=0;
                //第一个是列数，第二个是行数
                String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                String cnt_date=rs.getCell(j++, i).getContents();
                String cnt_time=rs.getCell(j++, i).getContents();
                String sum_cnt=rs.getCell(j++, i).getContents();
                String fiveframe_cnt=rs.getCell(j++, i).getContents();
                String fiveframe_rate=rs.getCell(j++, i).getContents();
                String sixframe_cnt=rs.getCell(j++, i).getContents();
                String sixframe_rate=rs.getCell(j++, i).getContents();
                String keyframe_cnt=rs.getCell(j++, i).getContents();
                String keyframe_rate=rs.getCell(j++, i).getContents();

                System.out.println("id='" + id + '\'' +
                        ", cnt_date='" + cnt_date + '\'' +
                        ", cnt_time='" + cnt_time + '\'' +
                        ", sum_cnt=" + sum_cnt +
                        ", fiveframe_cnt=" + fiveframe_cnt +
                        ", fiveframe_rate=" + fiveframe_rate +
                        ", sixframe_cnt=" + sixframe_cnt +
                        ", sixframe_rate=" + sixframe_rate +
                        ", keyframe_cnt=" + keyframe_cnt +
                        ", keyframe_rate=" + keyframe_rate );
                list.add(new KeyFrames(id,cnt_date,cnt_time,Integer.parseInt(sum_cnt),Integer.parseInt(fiveframe_cnt),
                        Double.parseDouble(fiveframe_rate),Integer.parseInt(sixframe_cnt),Double.parseDouble(sixframe_rate),
                        Integer.parseInt(keyframe_cnt),Double.parseDouble(keyframe_rate)));
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据主键id判断是否存在该数据
     * @param id
     * @return
     */
    public static boolean isExist(int id){
        try {
            DBhelper db=new DBhelper();
            ResultSet rs=db.Search("select * from fsk_keyframes where id=?", new String[]{id+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
