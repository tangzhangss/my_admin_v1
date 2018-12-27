package zyrs.xyz.obadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zyrs.xyz.obadmin.bean.Ob;
import zyrs.xyz.obadmin.bean.Wxapp;
import zyrs.xyz.obadmin.service.ObService;
import zyrs.xyz.obadmin.service.WxappService;

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
