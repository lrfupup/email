package cn.fbmms;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class mailUtils {
    /**
     * 发送简单的文本邮件
     * @param contentMap  邮件配置和邮件内容
     */
    public static void sendSimpleMail(Map<String, Object> contentMap){
        //获取系统属性
        Properties pros = new Properties();
        pros.setProperty("mail.transport.protocol", "smtp");// 使用的协议
        pros.setProperty("mail.smtp.host", contentMap.get("host").toString());// 发件人的邮箱的SMTP服务器地址
        pros.setProperty("mail.smtp.starttls.enable","true");
        pros.setProperty("mail.smtp.auth", "true");// 需要请求认证
        pros.setProperty("mail.smtp.port", "587");// 需要请求认证

        //设置邮件服务器
        Session session = Session.getInstance(pros);

        //创建默认的MimeMessage 对象
        MimeMessage msg = new MimeMessage(session);
        try {
            //邮件发送人
            msg.setFrom(contentMap.get("mailFrom").toString());
            //邮件接收人
            msg.setRecipients(Message.RecipientType.TO, contentMap.get("mailTo").toString());
            //邮件标题头
            msg.setSubject(contentMap.get("mailTittle").toString());
            //邮件发送日期
            msg.setSentDate(new Date());
            //邮件文本内容
            msg.setContent(contentMap.get("mailContent").toString(), "text/html;charset=UTF-8");
            //保存邮件
            msg.saveChanges();
            Transport.send(msg, contentMap.get("mailFrom").toString(), contentMap.get("passwd").toString());
        }catch (Exception mex){
            mex.printStackTrace();
        }
    }
    /**
     * 发送带有附件的邮件
     * @param contentMap  邮件配置和邮件内容
     * @param attachments  邮件附件
     * @return MimeMessage
     */

    public static void SendAttachMail(Map<String, Object> contentMap, List<File> attachments){
        //获取系统属性
        Properties pros = new Properties();
        pros.setProperty("mail.transport.protocol", "smtp");// 使用的协议
        pros.setProperty("mail.smtp.host", contentMap.get("host").toString());// 发件人的邮箱的SMTP服务器地址
        pros.setProperty("mail.smtp.starttls.enable","true");
        pros.setProperty("mail.smtp.auth", "true");// 需要请求认证
        pros.setProperty("mail.smtp.port", "587");// 需要请求认证

        //设置邮件服务器
        Session session = Session.getInstance(pros);
        // 创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            //邮件发送人
            mimeMessage.setFrom(contentMap.get("mailFrom").toString());
            //邮件接收人
            mimeMessage.setRecipients(Message.RecipientType.TO, contentMap.get("mailTo").toString());
            //邮件主题
            mimeMessage.setSubject(contentMap.get("mailTittle").toString());
            //创建多重消息
            MimeMultipart multipart = new MimeMultipart();
            //邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(contentMap.get("mailContent").toString(),"text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);
            //邮件附件
            if(attachments != null) {
                for(File attachment : attachments) {
                    BodyPart attachmentPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachment);
                    attachmentPart.setDataHandler(new DataHandler(source));
                    //避免中文乱码的处理
                    attachmentPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                    multipart.addBodyPart(attachmentPart);
                }
            }
            mimeMessage.setContent(multipart,"UTF-8");
            //保存邮件
            mimeMessage.saveChanges();
            Transport.send(mimeMessage, contentMap.get("mailFrom").toString(), contentMap.get("passwd").toString());
        }
        catch (Exception mex){
            mex.printStackTrace();
        }
    }
}
