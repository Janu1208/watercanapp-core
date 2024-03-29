package com.revature.service;


import com.google.protobuf.ServiceException;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;
import com.revature.exception.DBException;
import com.revature.exception.ValidatorException;
import com.revature.model.User;
import com.revature.util.ErrorConstants;
import com.revature.validator.UserValidator;


public class UserServices {
	UserDAO udao=new UserDAOImp();
/**
 * 
 * @param user
 * @throws Exception
 */
public void register(User user) throws Exception
{
	try {
		UserValidator.validateBeforeRegistration(user);
		/*UserValidator.validName(user.getName());
		UserValidator.passwordValidation(user.getName(),user.getPassword());
		UserValidator.validPhoneNumber(user.getPhoneNumber());*/
		try {
			udao.register(user);
		} catch (DBException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	} catch (ValidatorException e1) {
		e1.printStackTrace();
		throw new ServiceException(e1.getMessage());
	}
	
}
public User login(String phoneNumber,String password) throws Exception 
{
	User user=null;
	
     try {
    	 user=udao.login(phoneNumber,password);
    	 if (user== null) {
    		 throw new Exception(ErrorConstants.LOGIN);
    	 }
	} catch (DBException e) {
		e.printStackTrace();
		throw new Exception(e);
	}
	return user ;
	
}

}