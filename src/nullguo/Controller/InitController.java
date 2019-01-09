package nullguo.Controller;

import nullguo.config.Config;
import nullguo.util.HttpSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 郭江彬 on 2019/1/2 0002.
 */
@Controller
@RequestMapping(value = "init")
public class InitController {
    @RequestMapping("init")
    public String init(String localAddress,String remoteAddress){
        Config.address=localAddress;
        if(!remoteAddress.equals("0")){
            HttpSender.addList(remoteAddress);
        }
        Config.list.initFileList();
        System.out.println("正在启动:"+"本地地址为:"+localAddress+",远程端点的地址为:"+remoteAddress);
        return "upload";
    }
}
