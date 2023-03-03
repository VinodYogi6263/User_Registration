package com.ss.email;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ss.constant.Constants_Message;
import com.ss.entity.User_Entity;
import com.ss.exception.Internal_Exception;
import com.ss.repository.User_Repository;

@Service
public class scheduling_Email {
	
	@Autowired
	Email_Send email_Send;
	
	@Autowired
	User_Repository user_Repository;
	
	@Scheduled(cron = "* * */24 * * *")
	public void send()
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH,-15);
		java.util.Date time = c.getTime();
		java.sql.Date sqlDate = new java.sql.Date(time.getTime());
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(sqlDate));
		List<User_Entity> user_List = user_Repository.findAll();
		try {
		for( User_Entity user :user_List)
		{	
		if(0==user.getPassword_updated_at().compareTo(date))
		{
			email_Send.send(user.getEmail(),Constants_Message.EMAIL_BODY+ " "+ user.getFirst_name()+" " + user.getLast_name());
		}
		}	
	}
		catch (Exception e)
		{
		throw new Internal_Exception(Constants_Message.INTERNAL_EXCEPTION_MESSAGE);
		}
}
}