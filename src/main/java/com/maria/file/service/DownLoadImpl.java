package com.maria.file.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
public class DownLoadImpl extends AbstractView {
	
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	
    	log.info("다운로드 파일 output");
        String fileName = null;
        File file = (File) map.get("fileName");
 
        res.setContentType("application/download;");
        int length = (int) file.length();
        res.setContentLength(length);
 
        // 익스플로러 인지 확인
        String userAgent = req.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1;
 
        if (ie) {
            fileName = URLEncoder.encode((String)map.get("fileTargetName"), "utf-8").replace("+",  "%20");
        } else {
            fileName = new String(((String)map.get("fileTargetName")).getBytes("utf-8"), "iso-8859-1").replace("+", "%20");
        }
 
        res.setHeader("Content-Disposition", "attachment;" + " filename=\"" + fileName + "\";"); 
        OutputStream out = res.getOutputStream();
        FileInputStream fis = null;
 
        try {
            int temp;
            fis = new FileInputStream(file);
            while ((temp = fis.read()) != -1) {
                out.write(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}