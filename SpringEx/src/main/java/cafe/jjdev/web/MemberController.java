package cafe.jjdev.web;

import java.util.List;

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
	
	//ȸ������������
	@RequestMapping(value="/test")
	public String test() {
		return "test";
	}
	
	@RequestMapping(value="/memberList")
	public String memberList(Model model) {
		System.out.println("memberList ��û...");
		List<Member> list = memberDao.selectMemberList();
		model.addAttribute("list", list);
		//DB���� ����Ʈ�� get...
		return "memberList";
	}
	
	@RequestMapping(value="/addMember", method=RequestMethod.POST)
	//command��ü�� �̿��ϴ� ��� <--�������� ���� ���� ������ ���
	public String addMember(MemberRequest memberRequest) { //Member�� member�� ����ص� �ȴ�. �Ʒ� �ڵ�� ���� 
		System.out.println(memberRequest);
		//DB�Է�
		memberService.addMember(memberRequest);
		return "redirect:/memberList"; //response.sendRedirect("/memberList") <--memberList���û
	}
	
	//@RequestParam�� �̿��ؼ� ������ ���� �̿� <-�ѵΰ��� ���� ������ ���
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
	public String getMember(Model model) { //�Ű������� ���� �޴´�. (�������� ���� ������ش�.) 
		Member member = memberDao.selectMemberOne(1); //1�� admin�� ������ ȣ��
		model.addAttribute("member",member);
		return "getMember";
	}
}
