package com.dbaa.controller;

import com.dbaa.entity.Mail;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.MimeMessage;
import java.io.File;
@Controller
public class MailController {
    private static Logger LOG = LoggerFactory.getLogger(MailController.class);
    @Autowired
    JavaMailSender jms;

    //发送文本消息，不带附件
    @RequestMapping("/send")
    public String send(@RequestBody Mail mail) {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(mail.getSender());
        //接收者
        mainMessage.setTo(mail.getReceiver());
        //发送的标题
        mainMessage.setSubject(mail.getSubject());
        //发送的内容
        mainMessage.setText(mail.getText());
        jms.send(mainMessage);
        return "true";
    }

    //带附件发送，可多个附件 图片，doc都可以发送
    @GetMapping("/sendFile")
    public void sendAttachmentsMail(@RequestBody Mail mail) {

        String[] fileArray = {"C:\\Users\\67123\\Desktop\\1.PNG"};//此处的路径？

        MimeMessage message = jms.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mail.getSender());
            helper.setTo(mail.getReceiver());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText());
            //验证文件数据是否为空
            if (null != fileArray) {
                FileSystemResource file = null;
                for (int i = 0; i < fileArray.length; i++) {
                    //添加附件
                    file = new FileSystemResource(fileArray[i]);
                    helper.addAttachment(fileArray[i].substring(fileArray[i].lastIndexOf(File.separator)), file);
                }
            }
            jms.send(message);
            System.out.println("带附件的邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }
    }
}
