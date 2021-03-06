package com.mysite.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.service.BoardService;
import com.mysite.vo.BoardVo;
import com.mysite.vo.PageVo;


@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model,int currNo) {
		PageVo pageVo=boardService.getPage(currNo);		
		List<BoardVo> list=boardService.getlist(currNo,pageVo);
		
		model.addAttribute("list", list);
		model.addAttribute("page", pageVo);
		model.addAttribute("kwd" );
		
		return "board/list";
	}
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public String read(BoardVo boardvo,Model model,@RequestParam("flag") int flag) {
		BoardVo vo=boardService.getboard(boardvo,flag);
		model.addAttribute("vo", vo);
		return "board/read";
	}
	
	@RequestMapping(value="/writeform",method=RequestMethod.GET)
	public String writeform() {
		return "board/writeform";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardvo) {
		boardService.insert(boardvo);
		return "redirect:/board/list?currNo=1";
	}
	
	@RequestMapping(value="/modifyform",method=RequestMethod.GET)
	public String modifyform(BoardVo boardvo,Model model) {
		BoardVo vo=boardService.getboardinfo(boardvo);
		model.addAttribute("vo", vo);
		return "board/modifyform";
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo boardvo,@RequestParam("currNo") int currNo,@RequestParam("kwd") String kwd) throws UnsupportedEncodingException {
		boardService.update(boardvo);
		kwd=URLEncoder.encode(kwd, "UTF-8");
		return "redirect:/board/read?flag=0&no="+boardvo.getNo()+"&currNo="+currNo+"&kwd="+kwd;	//kwd에 한글로 보낼시 받는 입장에서 ?? 로 받음
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("no") int no ) {
		boardService.delete(no);
		return "redirect:/board/list?currNo=1";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(@RequestParam("kwd") String name,int currNo,Model model ) {		
		List<BoardVo> list=boardService.search(currNo,name);
		model.addAttribute("list", list);
		PageVo pageVo=boardService.searchPage(currNo,name);
		
		model.addAttribute("page", pageVo);
		model.addAttribute("kwd", name);	
		return "board/list";
	}
	
	
}
