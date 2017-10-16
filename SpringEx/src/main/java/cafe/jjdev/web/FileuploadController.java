package cafe.jjdev.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.web.service.FileuploadService;

@Controller
public class FileuploadController {
	@Autowired
	private FileuploadService fileuploadService;
	
	//file upload��
	@RequestMapping(value="/fileAdd", method=RequestMethod.GET)
	public String fileAdd() {
		return "fileAdd";
	}
	//file upload �׼�
	@RequestMapping(value="/fileAdd", method=RequestMethod.POST)
	public String fileAdd(FileRequest fileRequest) {
		System.out.println(fileRequest + "<--fileRequest");
		fileuploadService.fileupload(fileRequest);
		//1.������ ������ �����ϴ� ����
		//2.����� ������ �̸��� ���������� FileVo
		return "";
	}
	/*public String fileAdd(@RequestParam(value="fileTitle") String fileTitle, MultipartFile file) {
		// 1. ������ ������ �����ϴ� ����
		return ""; 
	}*/
}
