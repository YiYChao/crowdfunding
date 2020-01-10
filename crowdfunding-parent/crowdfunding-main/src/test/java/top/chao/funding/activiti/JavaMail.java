package top.chao.funding.activiti;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import top.chao.funding.util.DesUtil;

public class JavaMail {

	@Test
	public void sendMail() throws Exception {
		// 使用JAVA程序发送邮件

		@SuppressWarnings("resource")
		ApplicationContext application = new ClassPathXmlApplicationContext("spring/spring-*.xml");

		// 邮件发送器，由Spring框架提供的
		JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) application.getBean("sendMail");

		javaMailSender.setDefaultEncoding("UTF-8");
		MimeMessage mail = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail);
		helper.setSubject("激活邮件");
		
		StringBuilder content = new StringBuilder();
		String param = "jihuoma";
		param = DesUtil.encrypt(param, "abcdefghijklmnopquvwxyz");
		content.append("<a href='http://www.yizhipai888.top/test/act.do?p=" + param + "'>激活链接</a>");
		
		helper.setText(content.toString(), true);
		helper.setFrom("yizhipai888@163.com");
		helper.setTo("yiychao@foxmail.com");
		javaMailSender.send(mail);

	}

}
