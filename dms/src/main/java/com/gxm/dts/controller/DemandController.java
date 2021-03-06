package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.implement.DemandServiceImpl;
import com.gxm.dts.service.implement.ProjectServiceImpl;
import com.gxm.dts.service.implement.UpdateRecordServiceImpl;
import com.gxm.dts.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.gxm.dts.util.Constant.*;
import static com.gxm.dts.util.Utils.applyInfo;

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
                               Integer id,
                               Model model) {
        if (Constant.DEBUG) System.out.println("project id: " + id);
        List<Demand> list = demandServiceImpl.selectDemandWithProjectId(id);
        request.setAttribute("data4", list);

        // add for tips
        HttpSession session =  request.getSession(true);
        applyInfo(session, model, CREATE_INFO);
        applyInfo(session, model, UPDATE_INFO);
        applyInfo(session, model, DELETE_INFO);
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

    // ??????????????????
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

    //????????????
    @RequestMapping("/toAddDemand")
    public String toAddDemand(HttpSession session,
                              Model model) {
        applyInfo(session, model, ERROR_INFO);
        return "client/addDemand";
    }

    @PostMapping("/addDemand")
    public String add(HttpServletRequest request,
                      Demand demand,
                      @RequestParam("demand_file") MultipartFile[] files) {
        if (("").equals(demand.getStart_date())) demand.setStart_date(null);
        if (("").equals(demand.getFinish_date())) demand.setFinish_date(null);
        HttpSession session = request.getSession(true);
        if (projectServiceImpl.selectMemberId(demand.getDesignated_person(), demand.getProject_id()) == null) {
            session.setAttribute(ERROR_INFO, "?????????????????????");
            return "redirect:/toAddDemand";
        }
        demandServiceImpl.addDemand(demand);
        if (Constant.DEBUG) System.out.println("demand: " + demand.toString());
        String prefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        fileUploadController.saveFile(files, demand.getDemand_id(), prefix, !IS_DEFECT);
        if (Constant.DEBUG) System.out.println("demand: " + files.length);
        session.setAttribute(CREATE_INFO, "");
        return "redirect:/toDemandList?id=" + demand.getProject_id();
    }

    //??????????????????????????????
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
        DemandProject demandProject =  demandServiceImpl.selectProjectMessageByDemandId(id);//????????????id??????????????????
        model.addAttribute("demand", demand);
        model.addAttribute("demandProject",demandProject);
        model.addAttribute("fileUpload", fileUploadController.selectFileUpload(id, !IS_DEFECT));

        session.setAttribute("demand", demand);
        session.setAttribute("demandProject", demandProject);
        List<UpdateRecord> updateRecords = updateRecordServiceImpl.selectUpdateRecordWithAssocId(id, !IS_DEFECT);
        StringBuilder updateContent = new StringBuilder();
        for (UpdateRecord updateRecord : updateRecords) updateContent.append(updateRecord.getRecord_content());
        model.addAttribute("updateContent", updateContent);
        request.setAttribute("updateDemands", updateRecords);
        // ????????????????????????project_id
        Object projectID = session.getAttribute(SESSION_PROJECT_ID);
        System.out.println("SESSION_PROJECT_ID"+SESSION_PROJECT_ID);
        if (projectID == null) session.setAttribute(SESSION_PROJECT_ID, demand.getProject_id());
        applyInfo(session, model, ERROR_INFO);
        return "client/demandUpdate";
    }

    //??????????????????
    @RequestMapping("/updateDemand")
    public String updateDemandWithId(HttpServletRequest request,
                                     HttpSession session,
                                     Demand demand,
                                     UpdateRecord updateRecord,
                                     @RequestParam("defect_file") MultipartFile[] files) {
        if (projectServiceImpl.selectMemberId(demand.getDesignated_person(), demand.getProject_id()) == null) {
            session.setAttribute(ERROR_INFO, "??????????????????????????????");
            return "redirect:/toUpdateDemand?id=" + demand.getDemand_id();
        }
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
        demand.setDemand_document(fileUploadController.saveFile(files, demand.getDemand_id(), prefix, !IS_DEFECT));//???????????????????????????????????????????????????

        if (object != null) {
            Demand oldDemand = (Demand) object;
            String updateContent = oldDemand.demandDiff(demand);
            if (!("").equals(updateContent)) {
                updateRecord.setRecord_content(updateContent);
                updateRecordServiceImpl.addUpdateRecord(updateRecord);
                session.setAttribute(UPDATE_INFO, "");
            }
        }
        // ????????????????????????project_id
        Object projectID = session.getAttribute(SESSION_PROJECT_ID);
        if (projectID == null) session.setAttribute(SESSION_PROJECT_ID, demand.getProject_id());
        return "redirect:/toDemandList?id=" + demand.getProject_id(); //redirect?????????
    }

    // ????????????
    @RequestMapping("/deleteDemandFile")
    public String deleteDemandFile(HttpSession session,
                                   int id,
                                   int fileId) {
        fileUploadController.deleteFile(fileId);
         return "redirect:/toUpdateDemand?id=" + id;
    }

    //????????????
    @RequestMapping("/deleteDemand")
    public String deleteDemand(HttpSession session,
                             int id) {
        Object object = session.getAttribute(SESSION_PROJECT_ID);
        int projectId = object != null ? (int) object : Integer.parseInt("");
        demandServiceImpl.deleteDemandWithId(id);
        updateRecordServiceImpl.deleteUpdateRecordWithAssocId(id, !IS_DEFECT);
        session.setAttribute(DELETE_INFO, "");
        return "redirect:/toDemandList?id=" + projectId; //redirect?????????
    }
}
