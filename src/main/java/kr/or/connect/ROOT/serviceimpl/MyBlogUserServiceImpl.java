// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBlogUserServiceImpl.java

package kr.or.connect.ROOT.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.ROOT.dao.UserDao;
import kr.or.connect.ROOT.dto.UserDto;
import kr.or.connect.ROOT.service.MyBlogUserService;
import kr.or.connect.ROOT.util.SHA256Util;

@Service
public class MyBlogUserServiceImpl implements MyBlogUserService{
	
	@Autowired
	UserDao userdao;

	@Override
	@Transactional(readOnly=false)
	public UserDto addUser(UserDto userdto) {
		
		String salt = SHA256Util.generateSalt();
		userdto.setUserSalt(salt);
		
		String userPw = userdto.getUserPw();
		userPw = SHA256Util.getEncrypt(userPw, salt);
		
		userdto.setUserPw(userPw);
		
		userdao.insert_User(userdto);
		
		return userdto;
	}

    public UserDto modifyUser(UserDto userdto)
    {
        return null;
    }

    public UserDto getUser(String userId)
    {
        return null;
    }

    @Override
	@Transactional(readOnly=false)
	public UserDto loginUser(String login_userId, String login_userPw) {
		
		UserDto userdto = userdao.select_UserId(login_userId);
		
		if(userdto != null) {
			
			String salt = userdto.getUserSalt();
			String userPw = SHA256Util.getEncrypt(login_userPw, salt);
			
			if(userdao.select_User(login_userId, userPw) != null) {
				// status 1 = 로그인 성공
				userdto.setUserStatus(1);
			}else if(userdao.select_User(login_userId, userPw) == null){
				// status 2 = ID 존재 PW 오류
				userdto.setUserStatus(2);
			}
			return userdto;
		}else{
			
			return null;
			
		}
	}

    @Override
	@Transactional(readOnly=false)
	public int userCheck(String userId) {
		
		int count = userdao.select_User_Check(userId);
		
		return count;
	}
 
	@Override
	@Transactional(readOnly=false)
	public int userNameCheck(String userName) {
		
		int count = userdao.select_User_Name_Check(userName);
		
		return count;
	}
 
	@Override
	@Transactional(readOnly=false)
	public int userEmailCheck(String userEmail) {
		
		int count = userdao.select_User_Email_Check(userEmail);
		
		return count;
	}
    
}
