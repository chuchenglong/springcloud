package controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author ChenglongChu
 * @description
 * @create 2018/05/04 15:01
 */
@Controller
public class FileUploadController {

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam(value="file", required = true)MultipartFile file) throws Exception{
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }
}
