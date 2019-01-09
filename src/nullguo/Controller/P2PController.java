package nullguo.Controller;

import com.alibaba.fastjson.JSON;
import nullguo.config.Config;
import nullguo.pojo.FileList;
import nullguo.util.HttpSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 郭江彬 on 2019/1/2 0002.
 */
@Controller
@RequestMapping(value = "p2p")
public class P2PController {
    @RequestMapping("/addFile")
    @ResponseBody
    public String addFile(String address, String filename) {
        Config.list.addFile(address, filename);
        return JSON.toJSONString(Config.list);
    }
    @RequestMapping("/addList")
    @ResponseBody
    public String addList(String remoteData){
        FileList remoteList= JSON.parseObject(remoteData,FileList.class);
        Config.list.addList(remoteList);
        return JSON.toJSONString(Config.list);
    }
    @RequestMapping("/get")
    @ResponseBody
    public String get() {
        return JSON.toJSONString(Config.list);
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        HttpSender.addFile(Config.address,"aa","dsa11");
        HttpSender.addList(Config.address);
        return JSON.toJSONString(Config.list);
    }
}
