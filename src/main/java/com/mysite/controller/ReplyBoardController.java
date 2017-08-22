package com.mysite.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.ReplyBoardService;
import com.mysite.vo.PageVo;
import com.mysite.vo.ReplyBoardVo;
import com.mysite.vo.UserVo;
import com.mysite.vo.BoardVo;

@Controller
@RequestMapping("replyboard")
public class ReplyBoardController {

	@Autowired
	private ReplyBoardService replyboardService;
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model,int currNo) {
		PageVo pageVo=replyboardService.getPage(currNo);		
		List<ReplyBoardVo> list=replyboardService.getlist(currNo,pageVo);
		
		model.addAttribute("list", list);
		model.addAttribute("page", pageVo);
		model.addAttribute("kwd" );
		
		return "replyboard/list";
	}
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public String read(ReplyBoardVo replyboardvo,Model model,@RequestParam("flag") int flag,@RequestParam("kwd") String kwd) {
		System.out.println(kwd);
		ReplyBoardVo vo=replyboardService.getboard(replyboardvo,flag);
		model.addAttribute("vo", vo);
		return "replyboard/read";
	}
	
	@RequestMapping(value="/writeform",method=RequestMethod.GET)
	public String writeform() {
		return "replyboard/writeform";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@ModelAttribute ReplyBoardVo replyboardvo) {
		replyboardService.insert(replyboardvo);
		return "redirect:/replyboard/list?currNo=1";
	}
	
	@RequestMapping(value="/modifyform",method=RequestMethod.GET)
	public String modifyform(ReplyBoardVo replyboardvo,Model model) {
		ReplyBoardVo vo=replyboardService.getboardinfo(replyboardvo);
		model.addAttribute("vo", vo);
		return "replyboard/modifyform";
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(@ModelAttribute ReplyBoardVo replyboardvo,@RequestParam("currNo") int currNo,@RequestParam("kwd") String kwd) throws UnsupportedEncodingException {
		System.out.println(kwd);
		replyboardService.update(replyboardvo);
		System.out.println("redirect:/replyboard/read?flag=0&no="+replyboardvo.getNo()+"&currNo="+currNo+"&kwd="+kwd);
		kwd=URLEncoder.encode(kwd, "UTF-8");
		return "redirect:/replyboard/read?flag=0&no="+replyboardvo.getNo()+"&currNo="+currNo+"&kwd="+kwd;	//kwd에 한글로 보낼시 받는 입장에서 ?? 로 받음 => 한글로 코딩하여 해결해야 함
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("no") int no,
						@RequestParam("groupNo") int groupNo,
						@RequestParam("depth") int depth,
						@RequestParam("orderNo") int orderNo) {
		System.out.println("no: "+no);
		System.out.println("groupNo: "+groupNo);
		System.out.println("depth: "+depth);
		System.out.println("orderNo: "+orderNo);
		replyboardService.delete(no,groupNo,depth,orderNo);
		return "redirect:/replyboard/list?currNo=1";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(@RequestParam("kwd") String name,int currNo,Model model ) {		
		List<ReplyBoardVo> list=replyboardService.search(currNo,name);
		model.addAttribute("list", list);
		PageVo pageVo=replyboardService.searchPage(currNo,name);
		
		model.addAttribute("page", pageVo);
		model.addAttribute("kwd", name);	
		return "replyboard/list";
	}
	
	@RequestMapping(value="/replyform",method=RequestMethod.GET)
	public String replyform() {
		return "replyboard/replyform";
	}
	
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public String reply(@ModelAttribute ReplyBoardVo vo,@RequestParam("num") int no,HttpSession httpsession) {
		System.out.println("reply도착");
		UserVo userVo=(UserVo)httpsession.getAttribute("authUser");
		int userNo=userVo.getNo();
		ReplyBoardVo replyboardvo=replyboardService.inforeply(no);
		System.out.println(replyboardvo);
		replyboardvo.setUserNo(userNo);
		replyboardvo.setContent(vo.getContent());
		replyboardvo.setTitle(vo.getTitle());
		System.out.println(replyboardvo);
		replyboardService.reply(replyboardvo);
		System.out.println(replyboardvo);
		return "redirect:/replyboard/list?currNo=1";
	}
	
	
}
