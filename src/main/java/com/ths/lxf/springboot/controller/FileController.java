package com.ths.lxf.springboot.controller;

import com.ths.lxf.springboot.common.ErrorResultModel;
import com.ths.lxf.springboot.common.MyException;
import com.ths.lxf.springboot.common.RestModel;
import com.ths.lxf.springboot.util.HttpUtils;
import com.ths.lxf.springboot.util.StringUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

@RestController
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(@RequestParam("attach") MultipartFile multipartFile, HttpServletResponse response) {
        RestModel restModel = new RestModel();
        try {
            if (StringUtil.isNull(multipartFile)) {
                throw new MyException("文件不能为空！");
            }
            logger.info("原文件名为：" + multipartFile.getOriginalFilename());
            logger.info("文件类型为：" + multipartFile.getContentType());
            multipartFile.transferTo(new File("C:\\Users\\viruser.v-desktop\\IdeaProjects\\springboot\\src\\main\\resources\\static\\fileUrl" + multipartFile.getOriginalFilename()));
        } catch (MyException e) {
            restModel.setMsg(e.getMessage());
            restModel.setMsgCode(1);
            logger.error("上传失败", e);
            HttpUtils.renderData(response, restModel);
        } catch (Exception e) {
            HttpUtils.renderData(response, new ErrorResultModel(e));
            logger.error("上传失败", e);
        }
    }
}
