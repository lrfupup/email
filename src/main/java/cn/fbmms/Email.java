package cn.fbmms;

import org.junit.Test;

import java.io.File;
import java.util.*;

import static cn.fbmms.mailUtils.SendAttachMail;
import static cn.fbmms.mailUtils.sendSimpleMail;

public class Email{
    public static void main(String[] args) {
        //发送方信息配置
        Map<String,Object> map = new HashMap();
        map.put("host","smtp.feishu.cn");
        map.put("mailFrom","liruifeng@elihr.cn");
        map.put("passwd","ZVjBV1aFH58ZA6fd");
        map.put("mailTo","liruifeng@elihr.cn");
        //标题和内容配置
        map.put("mailTittle","关键帧统计");
        map.put("mailContent","关键帧统计");
        List<File> attaches = new ArrayList();
        FromDbToExcel.getData();
        Calendar cal=Calendar.getInstance();
        // 当前年
        int year = cal.get(Calendar.YEAR);
        // 当前月
        int month = cal.get(Calendar.MONTH) + 1;
        // 当前日
        int day = cal.get(Calendar.DATE)-1;
        // 创建可写入的Excel工作簿
        String fileName = "/opt/jar/data/"+year+"年"+month+"月"+day+"日"+"关键帧统计.xls";
        File attach1 = new File(fileName);
        attaches.add(attach1);
        //发送带附件邮件
        SendAttachMail(map,attaches);
    }

}