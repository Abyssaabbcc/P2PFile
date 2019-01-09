package nullguo.Controller;

import com.alibaba.fastjson.JSON;
import nullguo.config.Config;
import nullguo.util.HttpSender;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by 郭江彬 on 2019/1/2 0002.
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {
    @RequestMapping(value = "/upload")
    public String upload(@RequestParam MultipartFile uploadFile)throws Exception{
        String filename=uploadFile.getOriginalFilename();
        File file=new File(Config.base,filename);
        uploadFile.transferTo(file);
        HttpSender.addAllFile(Config.address,filename);
        return "upload";
    }
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam String filename)throws Exception{
        File file=new File(Config.base,filename);
        byte[] body = null;
        //获取文件
        InputStream is = new FileInputStream(file);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        //设置文件类型
        headers.add("Content-Disposition", "attchement;filename=" + filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //返回数据
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);
        return entity;
    }
    @RequestMapping(value = "/downloadList")
    public String downloadList(Model model){
        model.addAttribute("list",Config.list.list);
        return "downloadList";
    }
}
