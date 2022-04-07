package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.service.implement.DefectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DefectController {
    @Autowired
    private DefectMapper defectMapper;
    @Autowired
    private DefectServiceImpl defectServiceImpl;

    @GetMapping(value = "/defect_list")
    public String toDefect_list(HttpServletRequest request) {
        List<Defect> list=defectMapper.selectDefectWithPage();
        request.setAttribute("data3", list);
        return this.Defect(request, 1, 5);
    }

    @GetMapping("/page3/{p}")
    public String Defect(HttpServletRequest request,@PathVariable("p") int page3,
                       @RequestParam(value = "count", defaultValue = "5") int count){
        PageInfo<Defect> list = defectServiceImpl.selectDefectWithPage(page3, count);
        request.setAttribute("data3", list);
        request.setAttribute("page3", page3);
        request.setAttribute("count",defectServiceImpl.selectDefectWithPage(page3, count).getPages());
        return "client/defect_list";
    }

    // 缺陷详情查询
    @GetMapping(value = "/defect/{id}")
    public String getDefectById(@PathVariable("id") String id, HttpServletRequest request){
        Defect defect = defectServiceImpl.selectDefectWithId(id);
        if(defect!=null){
            request.setAttribute("defect",defect);
            return "client/index";
        }else {
            return "client/defect_list";
        }
    }

    //新建缺陷
    @RequestMapping("/toAddDefect")
    public String toAddDefect(){
        return "client/add_defect";
    }

    @RequestMapping("/addDefect")
    public String add(Defect defect){
        defectServiceImpl.addDefect(defect);
        return "redirect:/defect_list";
    }

    //更新（修改）缺陷信息
    @RequestMapping("/toUpdateDefect")
    public String toUpdateDefect(String id, Model model){
        Defect defect = defectServiceImpl.getDefectId(id);
        model.addAttribute("defect",defect);
        return "client/defect_update";
    }
    //修改缺陷信息
    @RequestMapping("/updateDefect")
    public String updateDefectWithId(Defect defect){
        defectServiceImpl.updateDefectWithId(defect);
        return "redirect:/defect_list"; //redirect重定向
    }

    //删除缺陷
    @RequestMapping("/deleteDefect")
    public String deleteUser(String id){
        defectServiceImpl.deleteDefectWithId(id);
        return "redirect:/defect_list"; //redirect重定向
    }

}

