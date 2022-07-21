package cn.fbmms;

import java.util.Calendar;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test_MailTrigger {
    public static void main(String [] args) {

        String to = "liruifeng@elihr.cn";//change accordingly

        String from = "liruifeng@elihr.cn";//change accordingly
        final String username = "liruifeng@elihr.cn";//change accordingly
        final String password = "ZVjBV1aFH58ZA6fd";//change accordingly

        String host = "smtp.feishu.cn";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            FromDbToExcel.getData();

            Calendar cal=Calendar.getInstance();
            // 当前年
            int year = cal.get(Calendar.YEAR);
            // 当前月
            int month = cal.get(Calendar.MONTH) + 1;
            // 当前日
            int day = cal.get(Calendar.DATE);
            message.setSubject(year+"年"+month+"月"+day+"日关键帧统计");

            String fileName = "./data/"+year+"年"+month+"月"+day+"日"+"关键帧统计.xls";
            message.setFileName(fileName);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
