package ezenweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    private String uploadPath = "C:\\Users\\504\\Desktop\\ezen_2023B_WEB2_hyoseong\\build\\resources\\main\\static\\uploadimg\\";

// ========================= 이미지 업로드 ========================= //
    public String fileUpload(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");
        File file = new File(uploadPath+fileName);
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
        return fileName;
    }
}
