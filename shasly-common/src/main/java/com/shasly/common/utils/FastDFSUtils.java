package com.shasly.common.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

public class FastDFSUtils {
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private StorageServer storageServer;
    private StorageClient1 storageClient1;

    public FastDFSUtils(String configLocation) {
        if(configLocation.startsWith("classpath")){
            configLocation = configLocation.replace("classpath:",getClass().getResource("/").getPath());
            configLocation = configLocation.replaceAll("%20"," ") ;
            try {
                ClientGlobal.init(configLocation);
                trackerClient = new TrackerClient();
                trackerServer = trackerClient.getConnection();
                storageClient1 = new StorageClient1(trackerServer,storageServer);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
    }

    public String fileUpload(byte[] bytes,String ext_name){
        return fileUpload(bytes,ext_name,null);
    }

    public String fileUpload(byte[] bytes, String ext_name, NameValuePair[] valuePairs) {
        String fileAddress = null;
        try {
            String[] strings = storageClient1.upload_appender_file(bytes, ext_name, valuePairs);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strings.length; i++) {
                stringBuffer.append(strings[i]);
                if(i==0){
                    stringBuffer.append("/");
                }
            }
            fileAddress = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return fileAddress;
    }
}
