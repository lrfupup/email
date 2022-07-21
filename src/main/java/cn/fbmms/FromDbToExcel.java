package cn.fbmms;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.Calendar;
import java.util.List;

public class FromDbToExcel {
    public static void getData() {
        try {
            WritableWorkbook wwb = null;

            Calendar cal=Calendar.getInstance();
            // 当前年
            int year = cal.get(Calendar.YEAR);
            // 当前月
            int month = cal.get(Calendar.MONTH) + 1;
            // 当前日
            int day = cal.get(Calendar.DATE)-1;
            // 创建可写入的Excel工作簿
            String fileName = "/opt/jar/data/"+year+"年"+month+"月"+day+"日"+"关键帧统计.xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);

            // 创建工作表
            WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

            //查询数据库中所有的数据
            List<KeyFrames> list= KFService.getAllByDb();
            //要插入到的Excel表格的行号，默认从0开始
            Label labelcntDate= new Label(0, 0, "日期");
            Label labelcntTime= new Label(1, 0, "时间");
            Label labelsumCnt= new Label(2, 0, "总数");
            Label labelfiveCnt= new Label(3, 0, "五帧数");
            Label labelfiveRate= new Label(4, 0, "五帧比例");
            Label labelsixCnt= new Label(5, 0, "六帧数");
            Label labelsixRate= new Label(6, 0, "六帧比例");
            Label labelkeyCnt= new Label(7, 0, "关键帧数");
            Label labelkeyRate= new Label(8, 0, "关键帧比例");

            ws.addCell(labelcntDate);
            ws.addCell(labelcntTime);
            ws.addCell(labelsumCnt);
            ws.addCell(labelfiveCnt);
            ws.addCell(labelfiveRate);
            ws.addCell(labelsixCnt);
            ws.addCell(labelsixRate);
            ws.addCell(labelkeyCnt);
            ws.addCell(labelkeyRate);

            for (int i = 0; i < list.size(); i++) {

                Label labelcntDate_i= new Label(0, i+1, list.get(i).getCnt_date());
                Label labelcntTime_i= new Label(1, i+1, list.get(i).getCnt_time());
                Label labelsumCnt_i= new Label(2, i+1, list.get(i).getSum_cnt()+"");
                Label labelfiveCnt_i= new Label(3, i+1, list.get(i).getFiveframe_cnt()+"");
                Label labelfiveRate_i= new Label(4, i+1, list.get(i).getFiveframe_rate()+"");
                Label labelsixCnt_i= new Label(5, i+1, list.get(i).getSixframe_cnt()+"");
                Label labelsixRate_i= new Label(6, i+1, list.get(i).getSixframe_rate()+"");
                Label labelkeyCnt_i= new Label(7, i+1, list.get(i).getKeyframe_cnt()+"");
                Label labelkeyRate_i= new Label(8, i+1, list.get(i).getKeyframe_rate()+"");

                ws.addCell(labelcntDate_i);
                ws.addCell(labelcntTime_i);
                ws.addCell(labelsumCnt_i);
                ws.addCell(labelfiveCnt_i);
                ws.addCell(labelfiveRate_i);
                ws.addCell(labelsixCnt_i);
                ws.addCell(labelsixRate_i);
                ws.addCell(labelkeyCnt_i);
                ws.addCell(labelkeyRate_i);
            }

            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            System.out.println("数据导出成功!");
            wwb.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
