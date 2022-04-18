package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.model.domain.Defect;
import com.gxm.dts.model.domain.DefectFile;
import com.gxm.dts.service.implement.DefectServiceImpl;
import com.gxm.dts.util.Constant;
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

import static com.gxm.dts.util.Constant.SESSION_PROJECT_ID;

@Controller
public class DefectController {
    @Autowired
    private DefectMapper defectMapper;
    @Autowired
    private DefectServiceImpl defectServiceImpl;

    @Value("${web.upload-path}")
    private String uploadPath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @GetMapping(value = "/toDefectList")
    public String toDefectList(HttpServletRequest request,
                               Integer id) {
        if (Constant.DEBUG) System.out.println("project id: " + id);
        List<Defect> list = defectMapper.selectDefectWithProjectId(id);
        request.setAttribute("data3", list);
        return this.Defect(request, 1, 5);
    }

    @GetMapping("/page3/{p}")
    public String Defect(HttpServletRequest request,
                         @PathVariable("p") int page,
                         @RequestParam(value = "count", defaultValue = "5") int count) {
        Object object = request.getSession(true).getAttribute(SESSION_PROJECT_ID);
        PageInfo<Defect> list =
                object != null ? defectServiceImpl.selectDefectWithPage(page, count, (Integer) object) : new PageInfo<>();
        request.setAttribute("data3", list);
        request.setAttribute("page3", page);
        request.setAttribute("count", list.getPages());
        return "client/defectList";
    }

    // 缺陷详情查询
    @GetMapping(value = "/defect/{id}")
    public String getDefectById(HttpServletRequest request,
                                @PathVariable("id") String id) {
        Defect defect = defectServiceImpl.selectDefectWithId(id);
        if (defect != null) {
            request.setAttribute("defect", defect);
            return "client/index";
        } else {
            return "client/defectList";
        }
    }

    //新建缺陷
    @RequestMapping("/toAddDefect")
    public String toAddDefect(){
        return "client/addDefect";
    }

    @PostMapping("/addDefect")
    public String add(HttpServletRequest request,
                      Defect defect,
                      @RequestParam("defect_file") MultipartFile[] files) {
        if (("").equals(defect.getStart_date())) defect.setStart_date(null);
        if (("").equals(defect.getFinish_date())) defect.setFinish_date(null);
        defectServiceImpl.addDefect(defect);
        if (Constant.DEBUG) System.out.println("defect: " + defect.toString());
        // 检查是否上传文件
        if (files.length > 0 && !("").equals(files[0].getOriginalFilename())) {
            // 初始化日期和存储路径
            String format = sdf.format(new Date());
            File folder = new File(uploadPath + format);
            if (!folder.isDirectory()) {
                boolean res = folder.mkdirs();
                if (Constant.DEBUG) System.out.println("folder res: " + res);
            }

            // 初始化文件路径
            int i = 0;
            // 遍历存储
            for (MultipartFile file : files) {
                if (Constant.DEBUG) System.out.println("defect: " + file.getOriginalFilename());
                String oldName = file.getOriginalFilename();
                if (oldName == null) oldName = "";
                String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
                try {
                    file.transferTo(new File(folder, newName));
                    String fileRes = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + format + newName;
                    if (Constant.DEBUG) System.out.println("fileRes: " + fileRes);
                    DefectFile defectFile = new DefectFile();
                    defectFile.setDefect_id(Integer.parseInt(defect.getDefect_id()));
                    defectFile.setFile_path(fileRes);
                    defectServiceImpl.addDefectFile(defectFile);
                    if (Constant.DEBUG) System.out.println("fileRes: " + ++i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (Constant.DEBUG) System.out.println("defect: " + files.length);
        return "redirect:/toDefectList?id=" + defect.getProject_id();
    }

    //更新（修改）缺陷信息
    @RequestMapping("/toUpdateDefect")
    public String toUpdateDefect(String id, Model model) {
        Defect defect = defectServiceImpl.getDefectId(id);
        model.addAttribute("defect", defect);
        return "client/defectUpdate";
    }

    //修改缺陷信息
    @RequestMapping("/updateDefect")
    public String updateDefectWithId(Defect defect) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        defect.setUpdate_time(format);
        defectServiceImpl.updateDefectWithId(defect);
        return "redirect:/toDefectList?id=" + defect.getProject_id(); //redirect重定向
    }

    //删除缺陷
    @RequestMapping("/deleteDefect")
    public String deleteUser(HttpServletRequest request,
                             String id) {
        Object object = request.getSession().getAttribute(SESSION_PROJECT_ID);
        int projectId = object != null ? (int) object : Integer.parseInt("");
        defectServiceImpl.deleteDefectWithId(id);
        return "redirect:/toDefectList?id=" + projectId; //redirect重定向
    }
}

