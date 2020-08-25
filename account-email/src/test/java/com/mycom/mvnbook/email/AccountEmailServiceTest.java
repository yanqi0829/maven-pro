package com.mycom.mvnbook.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;


public class AccountEmailServiceTest {
    //GreenMail为测试邮件服务器 GreenMail 是最适合做单元测试的，它本身并不会发送邮件，但支持SMTP, POP3, IMAP
    private GreenMail greenMail;

    @Before
    public void startMailServer() throws Exception {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.setUser("test@my.com", "123456");
        greenMail.start();
    }

    @Test
    public void testSendMail() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");
        String subject = "Test";
        String htmlText = "<h3>Test</h3>";
        accountEmailService.sendEmail("test22@my.com", subject, htmlText);
        //邮件发送后 再使用GreenMail进行检查 表示接收一封邮件最多等待2秒
        greenMail.waitForIncomingEmail(2000, 1);
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
      assertEquals(1,receivedMessages.length);
      assertEquals(subject,receivedMessages[0].getSubject());
    }

    @After
    public void stopMailServer() {
        greenMail.stop();
    }
}
