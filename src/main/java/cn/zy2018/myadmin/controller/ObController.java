package cn.zy2018.myadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.zy2018.myadmin.entity.Ob;
import cn.zy2018.myadmin.entity.Wxapp;
import cn.zy2018.myadmin.service.ObService;
import cn.zy2018.myadmin.service.WxappService;

@Controller
@RequestMapping("/ob")
public class ObController {

    @Autowired
    private ObService obService;
    @Autowired
    private WxappService wxappService;



    @RequestMapping("modify_logo")
    @ResponseBody
    public void modifyLogo(Ob ob){
        obService.modifyLogo(ob.getId(),ob.getLogo());
    }

    @RequestMapping("add_or_update_wxapp")
    @ResponseBody
    public void addOrUpdateWxapp(Wxapp wxapp){
        wxappService.addOrUpdateWxapp(wxapp);
    }

}
