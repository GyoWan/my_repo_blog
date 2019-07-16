// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyblogMailController.java

package kr.or.connect.ROOT.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.ROOT.dto.UserCheckDto;
import kr.or.connect.ROOT.util.SHA256Util;

@Controller
public class MyblogMailController
{
	
	@GetMapping(path = "/sendmail")
	@ResponseBody
	public UserCheckDto sendmail(HttpSession session, UserCheckDto usercheckdto,
			@RequestParam(name = "userEmail", required = true) String userEmail) {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = ""; // 본인의 아이디 입력
		String hostSMTPpwd = ""; // 비밀번호 입력
 
		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "rydhkstptkd@naver.com"; // 보내는 사람 eamil
		String fromName = "Admin"; // 보내는 사람 이름
		String subject = "[StagBeetle 이메일 인증번호] 이메일을 인증해 주세요."; // 제목
 
		// 받는 사람 E-Mail 주소
		String mail = userEmail; // 받는 사람 email
 
		String numStr = "";
 
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); // SMTP 포트 번호 입력
 
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			numStr = numberGen(6);
			email.setHtmlMsg("<h2>안녕하세요. 이메일 인증이 필요합니다.</h2>" + "<h3> 인증번호 : " + numStr + "</h3>"
					+ "<p>화면에 보이는 인증번호를 회원 가입 페이지의 Email_인증번호 란에 입력 후 인증하기 버튼을 클릭해 주세요.</p>"); // 본문 내용
			email.send();
 
			String salt = SHA256Util.generateSalt();
			session.setAttribute("emailSalt", salt);
 
			numStr = SHA256Util.getEncrypt(numStr, salt);
			usercheckdto.setAuthenticationNumber(numStr);
		} catch (Exception e) {
			System.out.println(e);
		}
 
		return usercheckdto;
	}
 
	@PostMapping(path = "/emailnumcheck")
	@ResponseBody
	public UserCheckDto emailnumcheck(HttpSession session, UserCheckDto usercheckdto,
			@RequestParam(name = "authenticationNumber", required = true) String authenticationNumber,
			@RequestParam(name = "saveAuthenticationNumber", required = true) String saveAuthenticationNumber) {
 
		String numStr = SHA256Util.getEncrypt(authenticationNumber, session.getAttribute("emailSalt").toString());
 
		usercheckdto.setAuthenticationNumber("0");
		if (numStr.equals(saveAuthenticationNumber)) {
			usercheckdto.setAuthNumberCheck(true);
		} else {
			usercheckdto.setAuthNumberCheck(false);
		}
 
		return usercheckdto;
	}
 
	public String numberGen(int length) {
 
		Random rand = new Random();
		String numStr = "";
 
		for (int i = 0; i < length; i++) {
 
			String randomNum = Integer.toString(rand.nextInt(10));
 
			numStr += randomNum;
 
		}
		return numStr;
	}
}
