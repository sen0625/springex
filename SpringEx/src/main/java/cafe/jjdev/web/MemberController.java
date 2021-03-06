package cafe.jjdev.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.web.service.Member;
import cafe.jjdev.web.service.MemberDao;
import cafe.jjdev.web.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberService memberService;
	
	//로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();          
		return "redirect:/login";
	}
			
	//로그인폼
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpSession session) {
		System.out.println("login폼");
		if(session.getAttribute("loginMember")==null) {
			return "login";
		}
		return "redirect:/test";
	}
	
	//로그인처리
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Member member, HttpSession session) {
		System.out.println("login처리");
		//로그인 프로세스가 진행되어야 함
		Member loginMember = memberDao.login(member);
		if(loginMember == null) {
			return "redirect:/login";
		} else {
			// session 로그인 정보를 저장
			session.setAttribute("loginMember", loginMember);
			return "redirect:/test";
		}
	}
	
	//회원전용페이지
	@RequestMapping(value="/test")
	public String test(HttpSession session) {
		System.out.println("회원전용페이지");
		if(session.getAttribute("loginMember")==null) {
			return "redirect:/login";
		}
		return "test";
	}
	
	@RequestMapping(value="/memberList")
	public String memberList(Model model) {
		System.out.println("memberList 요청...");
		List<Member> list = memberDao.selectMemberList();
		model.addAttribute("list", list);
		//DB에서 리스트를 get...
		return "memberList";
	}
	
	@RequestMapping(value="/addMember", method=RequestMethod.POST)
	//command객체를 이용하는 방법 <--여러개의 많은 값을 받을때 사용
	public String addMember(MemberRequest memberRequest) { //Member의 member를 사용해도 된다. 아래 코드와 동일 
		System.out.println(memberRequest);
		//DB입력
		memberService.addMember(memberRequest);
		return "redirect:/memberList"; //response.sendRedirect("/memberList") <--memberList재요청
	}
	
	//@RequestParam을 이용해서 각각의 값을 이용 <-한두개의 값을 받을때 사용
	/*@RequestMapping(value="/addMember", method=RequestMethod.POST)
	public String addMember(@RequestParam(value="memberId") String memberId
							, @RequestParam(value="memberpw") String memberpw
							, @RequestParam(value="memberName") String memberName) {
		System.out.println("memberId : "+memberId);
		System.out.println("memberpw : "+memberpw);
		System.out.println("memberName : "+memberName);
		return "";
	}*/
	
	@RequestMapping(value="/addMember", method=RequestMethod.GET)
	public String addMember() {
		return "addMember";
	}
	
	@RequestMapping("/getMember") 
	public String getMember(Model model) { //매개변수로 모델을 받는다. (스프링이 모델을 만들어준다.) 
		Member member = memberDao.selectMemberOne(1); //1번 admin의 정보를 호출
		model.addAttribute("member",member);
		return "getMember";
	}
}
