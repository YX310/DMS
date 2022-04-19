package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.Demand;
import com.gxm.dts.model.domain.DemandFile;
import com.gxm.dts.service.implement.DemandServiceImpl;
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
public class DemandController {
    @Autowired
    private DemandServiceImpl demandServiceImpl;
    @Value("${web.upload-path}")
    private String uploadPath;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    @GetMapping(value = "/toDemandList")
    public String toDemandList(HttpServletRequest request,
                               Integer id) {
        if (Constant.DEBUG) System.out.println("project id: " + id);
        List<Demand> list = demandServiceImpl.selectDemandWithProjectId(id);
        request.setAttribute("data4", list);
        return this.Demand(request, 1, 5);
    }

    @GetMapping("/page4/{p}")
    public String Demand(HttpServletRequest request,
                         @PathVariable("p") int page,
                         @RequestParam(value = "count", defaultValue = "5") int count) {
        Object object = request.getSession(true).getAttribute(SESSION_PROJECT_ID);
        PageInfo<Demand> list =
                object != null ? demandServiceImpl.selectDemandWithPage(page, count, (Integer) object) : new PageInfo<>();
        request.setAttribute("data4", list);
        request.setAttribute("page4", page);
        request.setAttribute("count", list.getPages());
        return "client/demandList";
    }

    // 缺陷详情查询
    @GetMapping(value = "/demand/{id}")
    public String getDemandById(HttpServletRequest request,
                                @PathVariable("id") int id) {
        Demand demand = demandServiceImpl.selectDemandWithId(id);
        if (demand != null) {
            request.setAttribute("demand", demand);
            return "client/index";
        } else {
            return "client/demandList";
        }
    }

    //新建缺陷
    @RequestMapping("/toAddDemand")
    public String toAddDemand(){
        return "client/addDemand";
    }

    @PostMapping("/addDemand")
    public String add(HttpServletRequest request,
                      Demand demand,
                      @RequestParam("demand_file") MultipartFile[] files) {
        if (("").equals(demand.getStart_date())) demand.setStart_date(null);
        if (("").equals(demand.getFinish_date())) demand.setFinish_date(null);
        demandServiceImpl.addDemand(demand);
        if (Constant.DEBUG) System.out.println("demand: " + demand.toString());
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
                if (Constant.DEBUG) System.out.println("demand: " + file.getOriginalFilename());
                String oldName = file.getOriginalFilename();
                if (oldName == null) oldName = "";
                String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
                try {
                    file.transferTo(new File(folder, newName));
                    String fileRes = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + format + newName;
                    if (Constant.DEBUG) System.out.println("fileRes: " + fileRes);
                    DemandFile demandFile = new DemandFile();
                    demandFile.setDemand_id(demand.getDemand_id());
                    demandFile.setFile_path(fileRes);
                    demandServiceImpl.addDemandFile(demandFile);
                    if (Constant.DEBUG) System.out.println("fileRes: " + ++i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (Constant.DEBUG) System.out.println("demand: " + files.length);
        return "redirect:/toDemandList?id=" + demand.getProject_id();
    }

    //更新（修改）缺陷信息
    @RequestMapping("/toUpdateDemand")
    public String toUpdateDemand(int id, Model model) {
        Demand demand = demandServiceImpl.getDemandId(id);
        model.addAttribute("demand", demand);
        return "client/demandUpdate";
    }

    //修改缺陷信息
    @RequestMapping("/updateDemand")
    public String updateDemandWithId(Demand demand) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        demand.setUpdate_time(format);
        demandServiceImpl.updateDemandWithId(demand);
        return "redirect:/toDemandList?id=" + demand.getProject_id(); //redirect重定向
    }

    //删除缺陷
    @RequestMapping("/deleteDemand")
    public String deleteDemand(HttpServletRequest request,
                             int id) {
        Object object = request.getSession().getAttribute(SESSION_PROJECT_ID);
        int projectId = object != null ? (int) object : Integer.parseInt("");
        demandServiceImpl.deleteDemandWithId(id);
        return "redirect:/toDemandList?id=" + projectId; //redirect重定向
    }


}
