package nullguo.pojo;

import com.alibaba.fastjson.JSON;
import nullguo.config.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 郭江彬 on 2019/1/2 0002.
 */
public class FileList {
    public HashMap<String,ArrayList<String>> list=new HashMap<>();
    public  void addFile(String address,String filename){
        if(list.containsKey(address)){
            list.get(address).add(filename);
        }else {
            ArrayList<String> fileList=new ArrayList<>();
            fileList.add(filename);
            list.put(address,fileList);
        }
    }
    public void clear(){
        list.clear();
    }
    public void addList(FileList remoteList){
        for(String address:remoteList.list.keySet()){
            list.put(address,remoteList.list.get(address));
        }
    }
    public void remoteAddress(String address){
        list.remove(address);
    }
    public void initFileList(){
        if(Config.init==false){
            ArrayList<String> localList=new ArrayList<>();
            File root=new File(Config.base);
            for(File file:root.listFiles()){
                localList.add(file.getName());
            }
            list.put(Config.address,localList);
            Config.init=true;
            show();
        }
    }
    public void show(){
        System.out.println("show!!"+JSON.toJSONString(list));
    }
}
