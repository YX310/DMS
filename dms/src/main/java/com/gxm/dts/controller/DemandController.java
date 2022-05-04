package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.implement.DemandServiceImpl;
import com.gxm.dts.service.implement.FileUploadServiceImpl;
import com.gxm.dts.service.implement.ProjectServiceImpl;
import com.gxm.dts.service.implement.UpdateRecordServiceImpl;
import com.gxm.dts.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.gxm.dts.util.Constant.*;

@Controller
public class DemandController {
    @Autowired
    private DemandServiceImpl demandServiceImpl;
    @Autowired
    private FileUploadController fileUploadController;
    @Autowired
    private UpdateRecordServiceImpl updateRecordServiceImpl;
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

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

    // 需求详情查询
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

    //新建需求
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
        if (projectServiceImpl.selectMemberId(demand.getDesignated_person(), demand.getProject_id()) == null){
            return "redirect:/toAddDemand";
        }
        demandServiceImpl.addDemand(demand);
        if (Constant.DEBUG) System.out.println("demand: " + demand.toString());
        String prefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        fileUploadController.saveFile(files, demand.getDemand_id(), prefix, !IS_DEFECT);
        if (Constant.DEBUG) System.out.println("demand: " + files.length);
        return "redirect:/toDemandList?id=" + demand.getProject_id();
    }

    //查看（修改）需求信息
    @RequestMapping("/toUpdateDemand")
    public String toUpdateDemand(HttpSession session,
                                 HttpServletRequest request,
                                 int id,
                                 Model model) {
        Object userId = session.getAttribute(SESSION_USER_ID);
        Demand demand = demandServiceImpl.getDemandId(id);
        if (userId == null || !projectServiceImpl.checkUserInProject((Integer) userId, demand.getProject_id())) {
            return "redirect:/unauthorizedPage";
        }
        DemandProject demandProject =  demandServiceImpl.selectProjectMessageByDemandId(id);//根据需求id查找项目信息
        model.addAttribute("demand", demand);
        model.addAttribute("demandProject",demandProject);
        model.addAttribute("fileUpload", fileUploadController.selectFileUpload(id, !IS_DEFECT));

        session.setAttribute("demand", demand);
        session.setAttribute("demandProject", demandProject);
        List<UpdateRecord> updateRecords = updateRecordServiceImpl.selectUpdateRecordWithAssocId(id);
        StringBuilder updateContent = new StringBuilder();
        for (UpdateRecord updateRecord : updateRecords) updateContent.append(updateRecord.getRecord_content());
        model.addAttribute("updateContent", updateContent);
        request.setAttribute("updateDemands", updateRecords);
        // 处理搜索场景下无project_id
        Object projectID = session.getAttribute(SESSION_PROJECT_ID);
        System.out.println("SESSION_PROJECT_ID"+SESSION_PROJECT_ID);
        if (projectID == null) session.setAttribute(SESSION_PROJECT_ID, demand.getProject_id());
        return "client/demandUpdate";
    }

    //修改需求信息
    @RequestMapping("/updateDemand")
    public String updateDemandWithId(HttpServletRequest request,
                                     HttpSession session,
                                     Demand demand,
                                     UpdateRecord updateRecord,
                                     @RequestParam("defect_file") MultipartFile[] files) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        demand.setUpdate_time(format);
        demandServiceImpl.updateDemandWithId(demand);
        updateRecord.setUpdate_time(System.currentTimeMillis());
        Object object = session.getAttribute("demand");
        updateRecord.setAssoc_id(demand.getDemand_id());
        updateRecord.setIs_defect(!IS_DEFECT);
        updateRecord.setProject_id(demand.getProject_id());
        updateRecord.setAssoc_title(demand.getDemand_name());
        System.out.println("id: " + updateRecord.getAssoc_id());
        System.out.println("id: " + updateRecord.getUpdate_time());
        System.out.println("id: " + updateRecord.getRecord_content());
        System.out.println("id: " + updateRecord.getRecord_content());
        String prefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        if (DEBUG) System.out.println("getDefect_id: " + demand.getDemand_id());
        demand.setDemand_document(fileUploadController.saveFile(files, demand.getDemand_id(), prefix, !IS_DEFECT));//把修改的文件名存到文档修改的变量里

        if (object != null) {
            Demand oldDemand = (Demand) object;
            String updateContent = oldDemand.demandDiff(demand);
            if (!("").equals(updateContent)) {
                updateRecord.setRecord_content(updateContent);
                updateRecordServiceImpl.addUpdateRecord(updateRecord);
            }
        }
        // 处理搜索场景下无project_id
        Object projectID = session.getAttribute(SESSION_PROJECT_ID);
        if (projectID == null) session.setAttribute(SESSION_PROJECT_ID, demand.getProject_id());
        return "redirect:/toDemandList?id=" + demand.getProject_id(); //redirect重定向
    }

    // 删除文件
    @RequestMapping("/deleteDemandFile")
    public String deleteDemandFile(int id, int fileId) {
        fileUploadController.deleteFile(fileId);
        return "redirect:/toUpdateDemand?id=" + id;
    }

    //删除需求
    @RequestMapping("/deleteDemand")
    public String deleteDemand(HttpServletRequest request,
                             int id) {
        Object object = request.getSession().getAttribute(SESSION_PROJECT_ID);
        int projectId = object != null ? (int) object : Integer.parseInt("");
        demandServiceImpl.deleteDemandWithId(id);
        return "redirect:/toDemandList?id=" + projectId; //redirect重定向
    }
}
