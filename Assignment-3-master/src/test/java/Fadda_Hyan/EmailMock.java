package Fadda_Hyan;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

/*
 * This class extends/inherits the Email class to acquire the properties and behaviors of Email class 
 * 
 */
public class EmailMock extends Email {

	// implementing required abstract method
	@Override
	public Email setMsg(String msg) throws EmailException {
		return null;
	}

}
