package cn.zy2018.myadmin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/util")
public class Util2Controller {
    /**
     * 获取实时长传进度
     * @param request
     * @return
     */
    @RequestMapping("upload_percent")
    public int getUploadPercent(HttpServletRequest request, @RequestParam(value = "id",defaultValue = "0",required = false) int id){
        HttpSession session = request.getSession();
        Integer p = (Integer)session.getAttribute("upload_percent_"+id);
        int percent = p == null?0:p;
        if(percent>=100){
            session.setAttribute("upload_percent_"+id,0);
        }
        return percent;
    }
}
