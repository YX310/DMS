package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.implement.DefectServiceImpl;
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
public class DefectController {
    @Autowired
    private DefectServiceImpl defectServiceImpl;
    @Autowired(required = false)
    private FileUploadController fileUploadController;
    @Autowired
    private UpdateRecordServiceImpl updateRecordServiceImpl;
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping(value = "/toDefectList")
    public String toDefectList(HttpServletRequest request,
                               Integer id,
                               Model model) {
        if (Constant.DEBUG) System.out.println("project id: " + id);
        List<Defect> list = defectServiceImpl.selectDefectWithProjectId(id);
        request.setAttribute("data3", list);

        // add for tips
        HttpSession session =  request.getSession(true);
        applyInfo(session, model, CREATE_INFO);
        applyInfo(session, model, UPDATE_INFO);
        applyInfo(session, model, DELETE_INFO);
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

    // ??????????????????
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

    //????????????
    @RequestMapping("/toAddDefect")
    public String toAddDefect(HttpSession session,
                              Model model) {
        applyInfo(session, model, ERROR_INFO);
        return "client/addDefect";
    }

    @PostMapping("/addDefect")
    public String add(HttpServletRequest request,
                      Defect defect,
                      @RequestParam("defect_file") MultipartFile[] files) {
        if (("").equals(defect.getStart_date())) defect.setStart_date(null);
        if (("").equals(defect.getFinish_date())) defect.setFinish_date(null);
        System.out.println(defect.getDesignated_person());
        System.out.println(defect.getProject_id());
        HttpSession session = request.getSession(true);
        if (projectServiceImpl.selectMemberId(defect.getDesignated_person(), defect.getProject_id()) == null) {
            session.setAttribute(ERROR_INFO, "??????????????????????????????");
            return "redirect:/toAddDefect";
        }
        defectServiceImpl.addDefect(defect);
        if (Constant.DEBUG) System.out.println("defect: " + defect.toString());
        String prefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        fileUploadController.saveFile(files, Integer.parseInt(defect.getDefect_id()), prefix, IS_DEFECT);
        if (Constant.DEBUG) System.out.println("defect: " + files.length);
        session.setAttribute(CREATE_INFO, "");
        return "redirect:/toDefectList?id=" + defect.getProject_id();
    }

    //??????????????????????????????
    @RequestMapping("/toUpdateDefect")
    public String toUpdateDefect(HttpServletRequest request,
                                 String id,
                                 Model model) {
        HttpSession session = request.getSession(true);
        Object userId = session.getAttribute(SESSION_USER_ID);
        System.out.println(id);
        System.out.println(userId);
        Defect defect = defectServiceImpl.getDefectId(id);
        System.out.println(defect);
        if (userId == null || !projectServiceImpl.checkUserInProject((Integer) userId, defect.getProject_id())) {
            return "redirect:/unauthorizedPage";
        }
        DefectProject defectProject =  defectServiceImpl.selectProjectMessageByDefectId(id);//????????????id??????????????????
        model.addAttribute("defect", defect);
        model.addAttribute("defectProject", defectProject);
        model.addAttribute("fileUpload", fileUploadController.selectFileUpload(Integer.parseInt(id), IS_DEFECT));

        session.setAttribute("defect", defect);
        session.setAttribute("defectProject", defectProject);
        List<UpdateRecord> updateRecords = updateRecordServiceImpl.selectUpdateRecordWithAssocId(Integer.parseInt(id), IS_DEFECT);
        StringBuilder updateContent = new StringBuilder();
        for (UpdateRecord updateRecord : updateRecords) updateContent.append(updateRecord.getRecord_content());
        model.addAttribute("updateContent", updateContent);
        request.setAttribute("updateDefects", updateRecords);
        // ????????????????????????projectId
        Object projectID = session.getAttribute(SESSION_PROJECT_ID);
        System.out.println("SESSION_PROJECT_ID" + SESSION_PROJECT_ID);
        if (projectID == null) session.setAttribute(SESSION_PROJECT_ID, defect.getProject_id());
        applyInfo(session, model, ERROR_INFO);
        return "client/defectUpdate";
    }

    //??????????????????
    @RequestMapping("/updateDefect")
    public String updateDefectWithId(HttpServletRequest request,
                                     Defect defect,
                                     UpdateRecord updateRecord,
                                     @RequestParam("defect_file") MultipartFile[] files) {
        HttpSession session = request.getSession(true);
        if (projectServiceImpl.selectMemberId(defect.getDesignated_person(), defect.getProject_id()) == null) {
            session.setAttribute(ERROR_INFO, "??????????????????????????????");
            return "redirect:/toUpdateDefect?id=" + defect.getDefect_id();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        defect.setUpdate_time(format);
        defectServiceImpl.updateDefectWithId(defect);
        updateRecord.setUpdate_time(System.currentTimeMillis());
        updateRecord.setAssoc_id(Integer.parseInt(defect.getDefect_id()));
        updateRecord.setIs_defect(IS_DEFECT);
        updateRecord.setProject_id(defect.getProject_id());
        updateRecord.setAssoc_title(defect.getDefect_name());
        if (DEBUG) System.out.println("id: " + updateRecord.getAssoc_id());
        if (DEBUG) System.out.println("id: " + updateRecord.getUpdate_time());
        Object object = session.getAttribute("defect");

        String prefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";// ???????????????/ip?????????
        if (DEBUG) System.out.println("getDefect_id: " + defect.getDefect_id());
        defect.setDefect_document(fileUploadController.saveFile(files, Integer.parseInt(defect.getDefect_id()), prefix, IS_DEFECT));
        if (object != null) {
            Defect oldDefect = (Defect) object;
            if (DEBUG) System.out.println(oldDefect.getDefect_document());
            if (DEBUG) System.out.println(defect.getDefect_document());
            String updateContent = oldDefect.defectDiff(defect);
            if (!("").equals(updateContent)) {
                updateRecord.setRecord_content(updateContent);
                updateRecordServiceImpl.addUpdateRecord(updateRecord);
                session.setAttribute(UPDATE_INFO, "");
            }
        }
        // ????????????????????????project id
        Object projectID = session.getAttribute(SESSION_PROJECT_ID);
        if (projectID == null) session.setAttribute(SESSION_PROJECT_ID, defect.getProject_id());
        return "redirect:/toDefectList?id=" + defect.getProject_id(); //redirect?????????
    }

    // ????????????
    @RequestMapping("/deleteDefectFile")
    public String deleteDefectFile(HttpSession session,
                                   int id,
                                   int fileId) {
        fileUploadController.deleteFile(fileId);
        return "redirect:/toUpdateDefect?id=" + id;
    }

    //????????????
    @RequestMapping("/deleteDefect")
    public String deleteDefect(HttpSession session,
                             String id) {
        Object object = session.getAttribute(SESSION_PROJECT_ID);
        int projectId = object != null ? (int) object : Integer.parseInt("");
        defectServiceImpl.deleteDefectWithId(id);
        updateRecordServiceImpl.deleteUpdateRecordWithAssocId(Integer.parseInt(id), IS_DEFECT);
        session.setAttribute(DELETE_INFO, "");
        return "redirect:/toDefectList?id=" + projectId; //redirect?????????
    }

}

