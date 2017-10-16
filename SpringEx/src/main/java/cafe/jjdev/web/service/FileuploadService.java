package cafe.jjdev.web.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cafe.jjdev.web.FileRequest;

@Service
public class FileuploadService {
	public int fileupload(FileRequest filerequest) {
		MultipartFile file = filerequest.getFile();
		String fileName = file.getOriginalFilename();
		int pos = fileName.lastIndexOf("."); //<-- file�ȿ��� ������ ���� ��ġ�� ã���ּ��� (text.txt)
		String ext = fileName.substring(pos+1); //Ȯ������ �̸��� �˰� �;(txt) 
		System.out.println(fileName);
		System.out.println(ext+"<--ext");
		
		UUID uuid = UUID.randomUUID(); //<--���ڿ��� �ٲ���� �Ѵ�.
		String name = uuid.toString();
		name = name.replaceAll("-", ""); //<--�۴�� �κ��� �����ִ� �ڵ�(name�� 000-00000-00000-0000) �۴�� �κ��� �����ش�.
		System.out.println(name + "<--name(uuid�� ���ڿ��� �ٲ۰�)");
		System.out.println(name+"."+ext);
		
		File destfile = new File("c:/upload/"+name+"."+ext); //������
		try {
			file.transferTo(destfile); //<--try/catch�� ����ó�� ���� (file�̸��ȿ� ������(destfile)�� ī�� ���ش�)
			FileVo fileVo = new FileVo(); //ĳġ�� �ۿ� �ᵵ �ȴ�.
			fileVo.setFileTitle(filerequest.getFileTitle());
			fileVo.setFilePath("c:/upload/"+name+"."+ext); 
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1. ������ ��������
		//2. FileRequestŸ���� -->FileVoŸ������ ����
		//3. FileDao.insertFile()�޼��� ȣ��
		return 0;
	}
}
