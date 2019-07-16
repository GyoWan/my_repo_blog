// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBlogUserController.java

package kr.or.connect.ROOT.controller;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.ROOT.dto.LoginDto;
import kr.or.connect.ROOT.dto.UserCheckDto;
import kr.or.connect.ROOT.dto.UserDto;
import kr.or.connect.ROOT.service.MyBlogUserService;

@Controller
public class MyBlogUserController {

	@Autowired
	MyBlogUserService myblogUserService;

	@RequestMapping(value="/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm(HttpServletRequest request, ModelMap model) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		HttpSession session = request.getSession();
 
		if (session.getAttribute("login_userName") == null) {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair keyPair = generator.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
 
			session.setAttribute("_RSA_WEB_Key_", privateKey); // 세션에 RSA 개인키를 세션에 저장한다.
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
 
			model.addAttribute("RSAModulus", publicKeyModulus); // 로그인 폼에 Input Hidden에 값을 셋팅하기위해서
			model.addAttribute("RSAExponent", publicKeyExponent); // 로그인 폼에 Input Hidden에 값을 셋팅하기위해서
		}
		
		return "loginForm";
	}

	@PostMapping(path = "/login")
	@ResponseBody
	public UserDto login(UserDto userdto, HttpServletRequest request, @RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "userPw", required = true) String userPw, HttpSession session, ModelMap modelMap){
 
		PrivateKey privateKey = (PrivateKey) session.getAttribute("_RSA_WEB_Key_");  //로그인전에 세션에 저장된 개인키를 가져온다.
 
		
		if(privateKey != null) {
			
			String _uid = decryptRsa(privateKey, userId);
			String _pwd = decryptRsa(privateKey, userPw);
			userdto = myblogUserService.loginUser(_uid, _pwd);
			try {
				if(userdto != null && userdto.getUserStatus() == 1) {
					
					String login_userName = userdto.getUserName();
					
					session.setAttribute("login_userName", login_userName);
					
					if(userdto.getUserAdmin() == 100) {
						session.setAttribute("isAdmin", true);
					}else {
						session.setAttribute("isAdmin", false);
					}
					
				}
			}catch(Exception e) {
				
			}
			
		}
		
		return userdto;
	}
	
	@GetMapping(path = "/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("isAmdin");
		session.removeAttribute("login_userName");
		
		return "redirect:main";
		
	}

	@GetMapping(path = "/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping(path = "/join")
	public String join(@ModelAttribute UserDto userdto) {
		
		myblogUserService.addUser(userdto);
		
		return "redirect:main";
	}

	@GetMapping(path = "/logincheck")
	@ResponseBody
	public LoginDto logincheck(HttpSession session, LoginDto logindto) {
		if (session.getAttribute("login_userName") != null)
			logindto.setLogin_Check(true);
		else
			logindto.setLogin_Check(false);
		return logindto;
	}

	@GetMapping(path = "/idcheck")
	@ResponseBody
	public UserCheckDto idcheck(UserCheckDto usercheckdto, @RequestParam(name = "userId", required = true) String userId) {
		
		usercheckdto.setUserCheckCount(myblogUserService.userCheck(userId));
		
		return usercheckdto;
	}
	
	@GetMapping(path = "/namecheck")
	@ResponseBody
	public UserCheckDto namecheck(UserCheckDto usercheckdto, @RequestParam(name = "userName", required = true) String userName) {
		
		usercheckdto.setUserCheckCount(myblogUserService.userNameCheck(userName));
		
		return usercheckdto;
	}
	
	@GetMapping(path = "/emailcheck")
	@ResponseBody
	public UserCheckDto emailcheck(UserCheckDto usercheckdto, @RequestParam(name = "userEmail", required = true) String userEmail) {
		
		usercheckdto.setUserCheckCount(myblogUserService.userEmailCheck(userEmail));
		
		return usercheckdto;
	}

	public String decryptRsa(PrivateKey privateKey, String securedValue) {
		String decryptedValue = "";
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			/**
			 * 암호화 된 값은 byte 배열이다. 이를 문자열 폼으로 전송하기 위해 16진 문자열(hex)로 변경한다. 서버측에서도 값을 받을 때 hex
			 * 문자열을 받아서 이를 다시 byte 배열로 바꾼 뒤에 복호화 과정을 수행한다.
			 */
			byte[] encryptedBytes = hexToByteArray(securedValue);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
		} catch (Exception e) {
			System.out.println("decryptRsa Exception Error : " + e.getMessage());
		}
		return decryptedValue;
	}
 
	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() % 2 != 0) {
			return new byte[] {};
		}
 
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i += 2) {
			byte value = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
			bytes[(int) Math.floor(i / 2)] = value;
		}
		return bytes;
	}

}
