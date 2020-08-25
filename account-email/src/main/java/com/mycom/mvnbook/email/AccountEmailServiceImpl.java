package com.mycom.mvnbook.email;

import com.mycom.mvnbook.email.exception.AccountEmailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class AccountEmailServiceImpl implements AccountEmailService {
    //    来源于Spring FrameWork 帮助简化邮件发送的工具类库
    private JavaMailSender javaMailSender;
    private String systemEmail;

    public void sendEmail(String to, String subject, String htmlText) throws AccountEmailException {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
            msgHelper.setFrom(systemEmail);
            msgHelper.setTo(to);
            msgHelper.setSubject(subject);
            msgHelper.setText(htmlText,true);//true代表邮件内容为html格式
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            throw new AccountEmailException("Fail send email", e);
        }
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String getSystemEmail() {
        return systemEmail;
    }

    public void setSystemEmail(String systemEmail) {
        this.systemEmail = systemEmail;
    }
}
