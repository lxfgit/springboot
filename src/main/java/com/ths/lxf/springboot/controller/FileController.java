package com.ths.lxf.springboot.controller;

import com.ths.lxf.springboot.common.ErrorResultModel;
import com.ths.lxf.springboot.common.MyException;
import com.ths.lxf.springboot.common.ResultModel;
import com.ths.lxf.springboot.util.HttpUtils;
import com.ths.lxf.springboot.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(@RequestParam("attach") MultipartFile multipartFile, HttpServletResponse response) {
        ResultModel resultModel = new ResultModel();
        try {
            if (StringUtil.isNull(multipartFile)) {
                throw new MyException("文件不能为空！");
            }
            logger.info("原文件名为：" + multipartFile.getOriginalFilename());
            logger.info("文件类型为：" + multipartFile.getContentType());
            String fileUrl = "C:\\Users\\viruser.v-desktop\\IdeaProjects\\springboot\\src\\main\\resources\\static\\fileUrl\\";
            multipartFile.transferTo(new File(fileUrl + System.currentTimeMillis() + "." +
                    multipartFile.getContentType().split("/")[1]));
            logger.info("文件已上传至：" + fileUrl);
        } catch (MyException e) {
            resultModel.setMsg(e.getMessage());
            resultModel.setMsgCode(1);
            logger.error("上传失败", e);
            HttpUtils.renderData(response, resultModel);
        } catch (Exception e) {
            HttpUtils.renderData(response, new ErrorResultModel(e));
            logger.error("上传失败", e);
        }
    }
}
