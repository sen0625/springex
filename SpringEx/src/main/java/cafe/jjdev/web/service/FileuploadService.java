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
		int pos = fileName.lastIndexOf("."); //<-- file안에서 마지막 점의 위치를 찾아주세요 (text.txt)
		String ext = fileName.substring(pos+1); //확장자의 이름을 알고 싶어서(txt) 
		System.out.println(fileName);
		System.out.println(ext+"<--ext");
		
		UUID uuid = UUID.randomUUID(); //<--문자열로 바꿔줘야 한다.
		String name = uuid.toString();
		name = name.replaceAll("-", ""); //<--작대기 부분을 없애주는 코드(name에 000-00000-00000-0000) 작대기 부분을 없애준다.
		System.out.println(name + "<--name(uuid를 문자열로 바꾼거)");
		System.out.println(name+"."+ext);
		
		File destfile = new File("c:/upload/"+name+"."+ext); //빈파일
		try {
			file.transferTo(destfile); //<--try/catch로 예외처리 원래 (file이름안에 빈파일(destfile)을 카피 해준다)
			FileVo fileVo = new FileVo(); //캐치문 밖에 써도 된다.
			fileVo.setFileTitle(filerequest.getFileTitle());
			fileVo.setFilePath("c:/upload/"+name+"."+ext); 
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1. 파일을 폴더저장
		//2. FileRequest타입을 -->FileVo타입으로 변경
		//3. FileDao.insertFile()메서드 호출
		return 0;
	}
}
