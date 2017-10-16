package cafe.jjdev.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.tracing.dtrace.Attributes;

import cafe.jjdev.web.service.Board;
import cafe.jjdev.web.service.BoardDao;
@Controller
public class BoardController {
	
	@Autowired
	private BoardDao boardDao;
	
	// �Է�(�׼�) ��û
    @RequestMapping(value="/boardAdd", method = RequestMethod.POST)
    public String boardAdd(Board board) {
        System.out.println(board);
        boardDao.insertBoard(board);
        return "redirect:/boardList"; // ���Է��� "/boardList"�� �����̷�Ʈ(���û)
    }
    
    @RequestMapping(value="/boardAdd", method = RequestMethod.GET)
    public String boardAdd() {
        System.out.println("boardAdd �� ��û");
        return "boardAdd";
    }
 
}