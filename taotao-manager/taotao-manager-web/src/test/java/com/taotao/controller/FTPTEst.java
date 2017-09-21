package com.taotao.controller;

import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FTPTEst {

    @Test
    public void testFtpClient() throws Exception{
//        创建一个FTPClient对象
        FTPClient client = new FTPClient();
//        使用FTPClient对象进行连接
        client.connect("192.168.1.103",21);
//        登录FTP服务器
        client.login("lab1007","lab1007");
        client.setFileType(FTP.BINARY_FILE_TYPE);
//        上传文件
        try(FileInputStream fileInputStream = new FileInputStream(new File("/home/nutao/下载/a.jpg"))) {
            client.storeFile("rr/a.jpg",fileInputStream);
        }
//        关闭连接
        client.logout();
    }

    @Test
    public void testFtpUtil() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(new File("/home/nutao/下载/a.jpg"));
        FtpUtil.uploadFile("192.168.1.103",21,"lab1007","lab1007","/home/lab1007/project","/2017/09/19","a.jpg",fileInputStream);
    }
}
