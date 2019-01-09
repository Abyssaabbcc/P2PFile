package nullguo.util;

import com.alibaba.fastjson.JSON;
import nullguo.config.Config;
import nullguo.pojo.FileList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 郭江彬 on 2019/1/2 0002.
 */
public class HttpSender {
    public static RestTemplate restTemplate;
    static{
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new Converter());


    }
    public static FileList getFileList(String address){
        ResponseEntity<String> responseEntity=restTemplate.getForEntity("http://"+address+"/p2p/get",String.class);
        return JSON.parseObject(responseEntity.getBody(),FileList.class);
    }
    public static void addFile(String remoteAddress,String address,String filename){
        restTemplate.getForEntity("http://"+remoteAddress+"/p2p/addFile?address="+address+"&"+"filename="+filename,String.class);
    }
    public static void addAllFile(String address,String filename){
        for(String remoteAddress: Config.list.list.keySet()){
            addFile(remoteAddress,address,filename);
        }
    }
    public static void addList(String remoteAddress){
        if(!Config.init){
            Config.list.initFileList();
        }
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("remoteData", JSON.toJSONString(Config.list));
        ResponseEntity<String> remoteData=restTemplate.postForEntity("http://"+remoteAddress+"/p2p/addList",requestEntity,String.class);
        Config.list.clear();
        Config.list.addList(JSON.parseObject(remoteData.getBody(),FileList.class));
    }
}
