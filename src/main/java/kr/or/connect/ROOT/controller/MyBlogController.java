// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyBlogController.java

package kr.or.connect.ROOT.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.ROOT.dto.BoardDto;
import kr.or.connect.ROOT.dto.CommentDto;
import kr.or.connect.ROOT.dto.CommentPagingDto;
import kr.or.connect.ROOT.dto.GoodDto;
import kr.or.connect.ROOT.dto.SelectListDto;
import kr.or.connect.ROOT.dto.SendGoodDto;
import kr.or.connect.ROOT.service.MyBlogService;
import net.sf.json.JSONArray;

@Controller
public class MyBlogController {

	@Autowired
	MyBlogService myblogService;

	@GetMapping(path = "/main")
	public String main() {
		return "redirect:list";
	}

	@GetMapping(path = "/selectlist")
	@ResponseBody
	public List<SelectListDto> select() {
		
		List<SelectListDto> selectlistdto = myblogService.getSelectList(0);
		
		return selectlistdto;
	}

	@GetMapping(path = "/list")
	public String list(@RequestParam(name = "nowPageNum", required = false, defaultValue="1") int nowPageNum, @RequestParam(name = "bType", required = false, defaultValue="100") int bType, @RequestParam(name = "sContent", required = false, defaultValue="") String sContent, ModelMap model) {
		
		List<BoardDto> list = myblogService.getBoard(nowPageNum, bType, sContent);
		
		int count = myblogService.getBoardCount(bType, sContent);
		
		int pageCount = count / MyBlogService.LIMIT + (count % MyBlogService.LIMIT != 0 ? 1 : 0);
		int pageBlock = MyBlogService.LIMIT;
		int startPageBlock = ((nowPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPageBlock = (startPageBlock + pageBlock) - 1;
		
		if (endPageBlock > pageCount) {
			endPageBlock = pageCount;
		}
		
		listFiltering(list);
		
		model.addAttribute("list", list);
		model.addAttribute("bType", bType);
		model.addAttribute("nowPageNum", nowPageNum);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("startPageBlock", startPageBlock);
		model.addAttribute("endPageBlock", endPageBlock);
		model.addAttribute("currentPageNum", nowPageNum);
		model.addAttribute("sContent", sContent);
		
		return "main";
	}

	@GetMapping(path = "/writeForm")
	public String writeForm(HttpSession session, ModelMap model) {
		
		if (session.getAttribute("login_userName") != null) {
			
			List<SelectListDto> list = myblogService.getSelectList(1);
			model.addAttribute("selectlist", list);
			
			return "writeForm";
		} else {
			return "redirect:main";
		}
		
	}

	@PostMapping(path = "/write")
	public String write(@ModelAttribute BoardDto boarddto, HttpSession session) {
		
		if (boarddto.getbContent().contains("<img")) {
			boarddto.setbImgTag(1);
		}
		
		String str = replaceHtmlEntity(boarddto.getbContent(), "TextareatoDB");
		boarddto.setbContent(str);
		
		myblogService.addBoard(boarddto);
		
		return "redirect:main";
	}

	@GetMapping(path = "/modifyForm")
	public String modifyForm(BoardDto boarddto, @RequestParam(name = "bId", required = true) int bId, @RequestParam(name = "nowPageNum", required = true) int nowPageNum, ModelMap model) {
		
		BoardDto modifyContent = myblogService.getviewContent(bId);
		List<SelectListDto> list = myblogService.getSelectList(1);
		
		model.addAttribute("nowPageNum", nowPageNum);
		model.addAttribute("selectlist", list);
		model.addAttribute("modifyContent", modifyContent);
		
		return "modifyForm";
	}

	@PostMapping(path = "/modify")
	public String modify(@ModelAttribute BoardDto boarddto, @RequestParam(name = "nowPageNum", required = true) int nowPageNum, @RequestParam(name = "bId", required = true) int bId) {
		
		if (boarddto.getbContent().contains("<img")) {
			boarddto.setbImgTag(1);
		}
		
		String str = replaceHtmlEntity(boarddto.getbContent(), "TextareatoDB");
		
		boarddto.setbContent(str);
		
		myblogService.modifyBoard(boarddto);
		
		return "redirect:viewForm?bId=" + bId + "&nowPageNum=" + nowPageNum;
	}

	@GetMapping(path = "/viewForm")
	public String viewForm(HttpSession session, @RequestParam(name = "bType", required = false, defaultValue = "100") int bType, @RequestParam(name = "cnowPageNum", required = false, defaultValue = "1") int cnowPageNum, @RequestParam(name = "bId", required = true) int bId, @RequestParam(name = "nowPageNum", required = false, defaultValue = "1") int nowPageNum, @RequestParam(name = "sContent", required = false, defaultValue = "") String sContent,
			ModelMap model) {
		
		BoardDto viewContent = myblogService.getviewContent(bId);
		List<CommentDto> viewComment = myblogService.getComment(cnowPageNum, bId);
		List<SelectListDto> selectlist = myblogService.getSelectList(1);
		
		String str = replaceHtmlEntity(viewContent.getbContent(), "DBtoView");
		
		if (viewContent.getbType() == 5) {
			
			int imgTagCount = StringUtils.countOccurrencesOf(str, "<img");
			
			List<String> imgSrcList = new ArrayList<>();
			
			if (imgTagCount >= 2) {
				Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
				
				Matcher matcher = pattern.matcher(str); 
				
				while(matcher.find()) {
					imgSrcList.add(matcher.group(1));
				}
				
				pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
				
				matcher = pattern.matcher(str); 
				
				while(matcher.find()) {
					str = matcher.replaceAll("");
				}

				model.addAttribute("imgSrcList", JSONArray.fromObject(imgSrcList));
			}
		}
		
		viewContent.setbContent(str);
		
		int commentCount = myblogService.getCommentCount(bId);
		int cpageCount = commentCount / 20 + (commentCount % 20 != 0 ? 1 : 0);
		int cpageBlock = 10;
		int cstartPageBlock = ((cnowPageNum - 1) / cpageBlock) * cpageBlock + 1;
		int cendPageBlock = (cstartPageBlock + cpageBlock) - 1;
		if (cendPageBlock > cpageCount)
			cendPageBlock = cpageCount;
		
		List<GoodDto> list = myblogService.getGoodAccount(bId, (String) session.getAttribute("login_userName"));
		
		if (list.isEmpty()) {
			model.addAttribute("goodAccountCheck", false);
		}else {
			model.addAttribute("goodAccountCheck", true);
		}
		
		int goodCount = myblogService.getGoodCount(bId);
		
		ConvertDateFormat(viewComment);
		
		model.addAttribute("viewContent", viewContent);
		model.addAttribute("viewComment", viewComment);
		model.addAttribute("commentCount", commentCount);
		model.addAttribute("goodCount", goodCount);
		model.addAttribute("selectlist", selectlist);
		model.addAttribute("nowPageNum", nowPageNum);
		model.addAttribute("bType", bType);
		model.addAttribute("sContent", sContent);
		model.addAttribute("cpageCount", cpageCount);
		model.addAttribute("cpageBlock", cpageBlock);
		model.addAttribute("cstartPageBlock", cstartPageBlock);
		model.addAttribute("cendPageBlock", cendPageBlock);
		model.addAttribute("ccurrentPageNum", cnowPageNum);
		
		return "viewPage";
	}

	@GetMapping(path = "/imgview")
	public String imgview(ModelMap model, @RequestParam(name = "htmls", required = true) String htmls) {
		
		model.addAttribute("htmls", htmls);
		
		return "imageView";
	}

	@GetMapping(path = "/delete")
	public String delete(@RequestParam(name = "bId", required = true) int bId) {
		
		myblogService.deleteBoard(bId);
		
		return "redirect:main";
	}

	@GetMapping(path = "/comment")
	@ResponseBody
	public CommentDto comment(@ModelAttribute CommentDto commentdto) {
		
		CommentDto result = myblogService.addComment(commentdto);
		int commentCount = myblogService.getCommentCount(commentdto.getbId());
		
		commentdto.setCommentCount(commentCount);
		
		return result;
	}

	@GetMapping(path = "/commentlist")
	@ResponseBody
	public List<CommentDto> commentlist(@RequestParam(name = "bId", required = true) int bId, @RequestParam(name = "cnowPageNum", required = true) int cnowPageNum) {
		
		List<CommentDto> list = myblogService.getComment(cnowPageNum, bId);
		ConvertDateFormat(list);
		
		return list;
	}

	@GetMapping(path = "/commentmodify")
	@ResponseBody
	public CommentDto commentmodify(@ModelAttribute CommentDto pcommentdto) {
		
		CommentDto result = myblogService.modifyComment(pcommentdto);
		
		return result;
	}

	@GetMapping(path = "/commentdelete")
	@ResponseBody
	public CommentDto commentdelete(CommentDto commentdto, @RequestParam(name = "bId", required = true) int bId, @RequestParam(name = "cId", required = true) int cId) {
		
		myblogService.deleteComment(bId, cId);
		int commentCount = myblogService.getCommentCount(bId);
		
		commentdto.setbId(bId);
		commentdto.setCommentCount(commentCount);
		
		return commentdto;
	}

	@GetMapping(path = "/paging")
	@ResponseBody
	public CommentPagingDto paging(CommentPagingDto commentpagingdto, @RequestParam(name = "cnowPageNum", required = false, defaultValue = "1") int cnowPageNum, @RequestParam(name = "bId", required = true) int bId) {
		
		int commentCount = myblogService.getCommentCount(bId);
		int cpageCount = commentCount / 20 + (commentCount % 20 != 0 ? 1 : 0);
		int cpageBlock = 10;
		int cstartPageBlock = ((cnowPageNum - 1) / cpageBlock) * cpageBlock + 1;
		int cendPageBlock = (cstartPageBlock + cpageBlock) - 1;
		
		if (cendPageBlock > cpageCount) {
			cendPageBlock = cpageCount;
		}
		
		commentpagingdto.setCcurrentPageNum(cnowPageNum);
		commentpagingdto.setCendPageBlock(cendPageBlock);
		commentpagingdto.setCpageBlock(cpageBlock);
		commentpagingdto.setCpageCount(cpageCount);
		commentpagingdto.setCstartPageBlock(cstartPageBlock);
		
		return commentpagingdto;
	}

	@GetMapping(path = "/good")
	@ResponseBody
	public SendGoodDto good(SendGoodDto sendgooddto, @ModelAttribute GoodDto gooddto) {
		
		List<GoodDto> list = myblogService.getGoodAccount(gooddto.getbId(), gooddto.getgAccount());
		
		if (list.isEmpty()) {
			myblogService.modifyGoodCount(0, gooddto.getbId());
			myblogService.addGoodAccount(gooddto);
			
			sendgooddto.setGoodAccountCheck(false);
		} else {
			myblogService.modifyGoodCount(1, gooddto.getbId());
			myblogService.deleteGoodAccount(gooddto.getbId(), gooddto.getgAccount());
			
			sendgooddto.setGoodAccountCheck(true);
		}
		
		int goodCount = myblogService.getGoodCount(gooddto.getbId());
		
		sendgooddto.setGoodCount(goodCount);
		
		return sendgooddto;
	}

	public void ConvertDateFormat(List<CommentDto> list) {
		
		SimpleDateFormat sdf = null;
		String strDate = "";
		
		for (CommentDto cdate : list) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strDate = sdf.format(cdate.getcMod_Date());
			cdate.setStrDate(strDate);
		}

	}

	public void listFiltering(List<BoardDto> list) {
		
		for (BoardDto title : list) {
			
			String str = title.getbTitle();
			String ellipsisTitle = "";
			
			int titleLength;
			
			if (title.getbType() == 3) {
				titleLength = 22;
			}else {
				titleLength = 35;
			}
			
			int strByte = 0;
			
			for (int x = 0; x < str.length(); x++) {
				
				strByte += ((int)str.charAt(x) > 128) ? 2 : 1;
				
				if (strByte > titleLength) {
					ellipsisTitle = str.substring(0, x) + "...";
					title.setbTitle(ellipsisTitle);
					break;
				}
			}

		}

		for (BoardDto content : list) {
			
			String str = content.getbContent();
			int contentLength = 200;
			int strByte = 0;
			
			String allPattern = "<[^>]*>";
			
			str = str.replaceAll(allPattern, "");
			str = replaceHtmlEntity(str, "DBtoView");
			
			for (int x = 0; x < str.length(); x++) {
				
				strByte += ((int)str.charAt(x) > 128) ? 2 : 1;
				
				if (strByte > contentLength) {
					str = str.substring(0, x) + "...";
					content.setbContent(str);
					break;
				}
				content.setbContent(str);
			}

		}

	}

	public String replaceHtmlEntity(String str, String wayCheck) {
		if (wayCheck == "DBtoView") {
			str = str.replaceAll("&amp;lt;", "&lt;");
			str = str.replaceAll("&amp;gt;", "&gt;");
		} else if (wayCheck == "TextareatoDB") {
			str = str.replaceAll("&lt;", "&amp;lt;");
			str = str.replaceAll("&gt;", "&amp;gt;");
		}
		return str;
	}
}
