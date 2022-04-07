package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.service.implement.DefectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Value("${web.upload-path}")
    private String uploadPath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    //新建缺陷
    @RequestMapping("/toAddDefect")
    public String toAddDefect(){
        return "client/add_defect";
    }

    @PostMapping("/addDefect")
    public String add(HttpServletRequest request,
                      Defect defect,
                      @RequestParam("defect_file") MultipartFile[] files) {
        System.out.println("defect: " + defect.toString());
        // 检查是否上传文件
        if (files.length > 0 && !("").equals(files[0].getOriginalFilename())) {
            // 初始化日期和存储路径
            String format = sdf.format(new Date());
            File folder = new File(uploadPath + format);
            if (!folder.isDirectory()) {
                boolean res = folder.mkdirs();
                System.out.println("folder res: " + res);
            }

            // 初始化文件路径
            StringBuilder filePath = new StringBuilder();
            int i = 0;
            // 遍历存储
            for (MultipartFile file : files) {
                System.out.println("defect: " + file.getOriginalFilename());
                String oldName = file.getOriginalFilename();
                if (oldName == null) oldName = "";
                String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
                try {
                    file.transferTo(new File(folder, newName));
                    String fileRes = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + format + newName;
                    System.out.println("fileRes: " + fileRes);
                    filePath.append(fileRes).append(",");
                    System.out.println("fileRes: " + ++i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            filePath.setLength(filePath.length() - 1);
            defect.setDefect_document(filePath.toString());
        }
        defectServiceImpl.addDefect(defect);
        System.out.println("defect: " + files.length);
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

