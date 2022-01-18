package com.gov.student.config;



import com.gov.student.core.User;
import com.gov.student.dto.Jwtuser;

public final class  JwtUserFactory {

	private JwtUserFactory()
	{
		
    }
	
	 public static Jwtuser create(User user, String passwordEncoder) {
	        return new Jwtuser(
	        		null, 
	        		passwordEncoder, 
	        		passwordEncoder, 
	        		null, 
	        		false);

}
}
