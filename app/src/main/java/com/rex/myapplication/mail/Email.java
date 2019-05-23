package com.rex.myapplication.mail;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2018-11-02 2:26 PM.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class Email {

    //    private static String EMAIL_CONFIG = "email_config.properties";
    private Properties emailProp = new Properties();
    static String name = "m13756017116@163.com";//发送用户邮箱
    static String pwd = "lyf3835620";//发送用户密码
    //    static String username = "m13756017116@163.com";//接收用户邮箱
    static String username = "liyufeng@syswin.com";//接收用户邮箱
    static String title = "java会议邀请2";//发送邮件主题     测试测试测试主题
    static String text = "我是Content内容";//发送邮件内容        测试测试测试内容
    static String startTimew = "2018-10-23 11:00";//开始时间
    static String endTimew = "2018-10-23 12:00";//结束时间
    //需要转换的时间
    static String startTime = getUtc(startTimew);
    static String endTime = getUtc(endTimew);

    public Email() {
        //        InputStream is = getClass().getResourceAsStream("/"+EMAIL_CONFIG);
        //        try {
        //            emailProp.load(is);
        //        } catch (IOException e) {
        //            // TODO Auto-generated catch block
        //            e.printStackTrace();
        //        }
    }

    private class EmailAuthenticator extends Authenticator {

        protected PasswordAuthentication getPasswordAuthentication() {
            String userId = emailProp.getProperty("userId", name);
            String password = emailProp.getProperty("password", pwd);
            return new PasswordAuthentication(userId, password);
        }
    }

    public void send() throws Exception {

        try {
            String fromEmail = emailProp.getProperty("fromEmail", name);
            String toEmail = emailProp.getProperty("toEmail", username);
            Properties props = new Properties();
            try {
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.host", "smtp.163.com");
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.ssl", "true");
                props.setProperty("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
                props.setProperty("mail.smtp.socketFactory.fallback", "false");

            } catch (Exception e) {
                e.printStackTrace();
            }

            Session session;
            Authenticator authenticator = new EmailAuthenticator();
            session = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(title);
            StringBuffer buffer = new StringBuffer();
            buffer.append("BEGIN:VCALENDAR\n"
                    + "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n"
                    + "VERSION:2.0\n"
                    + "METHOD:REQUEST\n"
                    + "BEGIN:VEVENT\n"
                    + "ATTENDEE;"
                    + "ROLE=REQ-PARTICIPANT;"
                    + "RSVP=TRUE:MAILTO:" + toEmail + "\n"
                    + "ORGANIZER:MAILTO:" + fromEmail + "\n"
                    + "DTSTART:" + startTime + "\n"
                    + "DTEND:" + endTime + "\n"
                    + "LOCATION:Conference room\n" + "UID:" + UUID.randomUUID().toString() + "\n"
                    //如果id相同的话，outlook会认为是同一个会议请求，所以使用uuid。
                    + "CATEGORIES:什么鬼\n"
                    + "DESCRIPTION:" + text + "\n\n"
                    + "SUMMARY:Test meeting request\n"
                    + "PRIORITY:5\n" + "CLASS:PUBLIC\n"
                    + "BEGIN:VALARM\n" + "TRIGGER:-PT15M\n" + "ACTION:DISPLAY\n"
                    + "DESCRIPTION:哈哈\n" + "END:VALARM\n" + "END:VEVENT\n" + "END:VCALENDAR");
            BodyPart messageBodyPart = new MimeBodyPart();
            // 测试下来如果不这么转换的话，会以纯文本的形式发送过去，
            //如果没有method=REQUEST;charset=\"UTF-8\"，outlook会议附件的形式存在，而不是直接打开就是一个会议请求
            messageBodyPart.setDataHandler(new DataHandler(
                    new ByteArrayDataSource(buffer.toString(),
                            "text/calendar;method=REQUEST;charset=UTF-8")));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException me) {
            me.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //转utc时间
    public static String getUtc(String str) {
        //String str = "2015-10-22 15:05";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long millionSeconds = 0;
        try {
            millionSeconds = sdf.parse(str).getTime();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }//毫秒

        long currentTime = millionSeconds - 8 * 60 * 60 * 1000;//utc时间差8小时
        Date date = new Date(currentTime);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化日期
        String nowTime = "";
        nowTime = df.format(date);

        String utcTime = nowTime.replace("-", "").replace(" ", "T").replace(":", "");//转换utc时间
        return utcTime;
    }

    public static void main(String[] args) {
        try {
            Email email = new Email();
            email.send();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
