package Fadda_Hyan;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

/*
 * This class is created to test some of the method of Email class using JUnit testing framework.
 * As the class Email is abstract class so we have inherited this Email class in EmailMock class
 * By doing that, we are able to create constructor of EmailMock class and assign it to Email class reference as below:
 * 
 * Email email1 = new EmailMock();
 * 
 * EmailMock works as Mock class and this is how we are able to call the actual methods of Email class.
 * 
 */

public class TestEmailClass {

	// This method is created to test the "getSocketConnectionTimeout()" method
	@Test
	public void TestGetSocketConnectionTimeout() {
		Email email = new EmailMock();

		// It asserts that two longs are equal
		assertEquals(60000, email.getSocketConnectionTimeout());
	}
// This method is created to test the "getSentDate()" method
	@Test
	public void TestGetSentDate() {
		Email email1 = new EmailMock();

		email1.setSentDate(new Date());

		assertEquals(new Date(), email1.getSentDate());

		Email email2 = new EmailMock();
		email2.setSentDate(null);

		// It asserts that two Date objects are equal
		assertEquals(new Date(), email2.getSentDate());
	}
// This method is created to test the "addBcc(String... emails)" method
	@Test
	public void TestAddBcc() throws EmailException, AddressException {
		Email email = new EmailMock();

		String bccArr[] = new String[2];

		bccArr[0] = new String("abc@example.com");
		bccArr[1] = new String("xyz@example.com");

		email.addBcc(bccArr);

		assertEquals(new InternetAddress("abc@example.com"), email.getBccAddresses().get(0));
		assertEquals(new InternetAddress("xyz@example.com"), email.getBccAddresses().get(1));

		// It asserts that count of BCC addresses are equal
		assertEquals(bccArr.length, email.getBccAddresses().size());
	}
// This method is created to test the "addCc(String... emails)" method
	@Test
	public void TestAddcc() throws EmailException, AddressException {
		Email email = new EmailMock();

		String ccAddresses[] = new String[3];

		ccAddresses[0] = new String("john@example.com");
		ccAddresses[1] = new String("harry@example.com");
		ccAddresses[2] = new String("roy@example.com");

		email.addCc(ccAddresses);

		assertEquals(new InternetAddress("john@example.com"), email.getCcAddresses().get(0));
		assertEquals(new InternetAddress("harry@example.com"), email.getCcAddresses().get(1));
		assertEquals(new InternetAddress("roy@example.com"), email.getCcAddresses().get(2));

		// It asserts that count of CC addresses are equal
		assertEquals(ccAddresses.length, email.getCcAddresses().size());

	}
// This method is created to test the "setFrom(String email)" method
	@Test
	public void TestSetFrom() throws EmailException, AddressException {
		Email email = new EmailMock();

		email.setFrom("alex@example.com");

		// It asserts that two From addresses are equal
		assertEquals(new InternetAddress("alex@example.com"), email.getFromAddress());
	}

// This method is created to test the "setHostName(String email)" method
	@Test
	public void TestGetHostName() throws EmailException, AddressException {
		Email email = new EmailMock();

		email.setHostName("vip.example.com");

		// It asserts that two Host names are equal
		assertEquals("vip.example.com", email.getHostName());
	}

// This method is created to test the "addHeader(String name, String value)"
	// method
	@Test
	public void TestAddHeader() throws EmailException, MessagingException {
		Email email = new EmailMock();

		String name[] = new String[] { "X-Priority", "X-Mailer", "Disposition-Notification-To" };
		String value[] = new String[] { "3", "Outlook", "abc@example.com" };

		email.addHeader(name[0], value[0]);
		email.addHeader(name[1], value[1]);

	}
// This method is created to test the "addReplyTo(String email, String name)"
	// method
	@Test
	public void TestAddReplyTo() throws EmailException {
		Email email = new EmailMock();
		email.addReplyTo("riyan@example.com", "Riyan");
		email.addReplyTo("aliyana@example.com", "Aliyana");

		// It asserts that two Email reply to addressers are equal
		assertEquals(2, email.getReplyToAddresses().size());
		assertEquals("riyan@example.com", email.getReplyToAddresses().get(0).getAddress());
		assertEquals("aliyana@example.com", email.getReplyToAddresses().get(1).getAddress());

	}
// This method is created to test the "buildMimeMessage()" method
	@Test
	public void TestBuildMimeMessage() throws EmailException, MessagingException {
		Email email = new EmailMock();
		email.setHostName("vip.example.com");
		email.setFrom("ziana@example.com");
		email.addTo("jerry@example.com");
		email.setSubject("Sample email");

		email.buildMimeMessage();

		MimeMessage mimeMessage = email.getMimeMessage();

		// It asserts that two mimeMessages are equal by checking their from address,
		// subject and all recipients
		assertEquals(new InternetAddress("ziana@example.com"), mimeMessage.getFrom()[0]);
		assertEquals("Sample email", mimeMessage.getSubject());
		assertEquals(new InternetAddress("jerry@example.com"), mimeMessage.getAllRecipients()[0]);
	}
// This method is created to test the "getMailSession()" method
	@Test
	public void TestGetMailSession() throws EmailException {
		Email email = new EmailMock();

		Properties properties = new Properties(System.getProperties());

		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", "vip.example.com");

		Session session = Session.getInstance(properties, null);

		email.setMailSession(session);

		// It asserts that two Email sessions are equal
		assertEquals(session, email.getMailSession());

	}

}

