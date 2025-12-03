package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRemoveHandler {
	private static final Logger log = LoggerFactory.getLogger(FileRemoveHandler.class);
	
	// 파일경로, 파일이름 => 파일을 생성
	// 생성한 파일과 같은 파일이 존재하면 삭제
	// 경로, 파일이름은 컨트롤에서 매개변수로 전달 => 리턴 boolean 
	
	public boolean deleteFile(String savePath, String fileName) {
		
		boolean isDel = false;
		
		// 파일 객체 생성 / 썸네일 객체 생성
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+fileName);
		File removeThumbFile = new File(fileDir+File.separator+"th_"+fileName);
		
		// 파일 존재 여부 확인
		if(removeFile.exists()) {
			isDel = removeFile.delete();  // 같은 이름의 파일을 삭제
			log.info(">>>> removeFile>> {}",isDel);
			if(isDel && removeThumbFile.exists()) {
				isDel = removeThumbFile.delete();
				log.info(">>>> removeThumbFile>> {}",isDel);
			}
		}
		
		return isDel;
	}

}
