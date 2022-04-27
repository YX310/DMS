package com.gxm.dts.controller;

import com.github.pagehelper.PageInfo;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.implement.DefectServiceImpl;
import com.gxm.dts.service.implement.FileUploadServiceImpl;
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
public class DefectController {
    @Autowired
    private DefectServiceImpl defectServiceImpl;
    @Autowired(required = false)
    private FileUploadController fileUploadController;
    @Autowired
    private UpdateRecordServiceImpl updateRecordServiceImpl;

    @GetMapping(value = "/toDefectList")
    public String toDefectList(HttpServletRequest request,
                               Integer id) {
        if (Constant.DEBUG) System.out.println("project id: " + id);
        List<Defect> list = defectServiceImpl.selectDefectWithProjectId(id);
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
        String prefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        fileUploadController.saveFile(files, Integer.parseInt(defect.getDefect_id()), prefix, IS_DEFECT);
        if (Constant.DEBUG) System.out.println("defect: " + files.length);
        return "redirect:/toDefectList?id=" + defect.getProject_id();
    }

    //查询（修改）缺陷信息
    @RequestMapping("/toUpdateDefect")
    public String toUpdateDefect(HttpSession session,
                                 HttpServletRequest request,
                                 String id,
                                 Model model) {
        Defect defect = defectServiceImpl.getDefectId(id);
        DefectProject defectProject =  defectServiceImpl.selectProjectMessageByDefectId(id);//根据缺陷id查找项目信息
        model.addAttribute("defect", defect);
        model.addAttribute("defectProject",defectProject);
        model.addAttribute("fileUpload", fileUploadController.selectFileUpload(Integer.parseInt(id), IS_DEFECT));

        session.setAttribute("defect", defect);
        session.setAttribute("defectProject", defectProject);
        List<UpdateRecord> updateRecords = updateRecordServiceImpl.selectUpdateRecordWithAssocId(Integer.parseInt(id));
        StringBuilder updateContent = new StringBuilder();
        for (UpdateRecord updateRecord : updateRecords) updateContent.append(updateRecord.getRecord_content());
        model.addAttribute("updateContent", updateContent);
        request.setAttribute("updateDefects", updateRecords);
        return "client/defectUpdate";
    }

    //修改缺陷信息
    @RequestMapping("/updateDefect")
    public String updateDefectWithId(HttpServletRequest request,
                                     HttpSession session,
                                     Defect defect,
                                     UpdateRecord updateRecord,
                                     @RequestParam("defect_file") MultipartFile[] files) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        defect.setUpdate_time(format);
        defectServiceImpl.updateDefectWithId(defect);
        updateRecord.setUpdate_time(System.currentTimeMillis());
        //        HttpSession session = request.getSession(true);
        updateRecord.setAssoc_id(Integer.parseInt(defect.getDefect_id()));
        updateRecord.setIs_defect(IS_DEFECT);
        updateRecord.setProject_id(Integer.parseInt(defect.getProject_id()));
        updateRecord.setAssoc_title(defect.getDefect_name());
        if (DEBUG) System.out.println("id: " + updateRecord.getAssoc_id());
        if (DEBUG) System.out.println("id: " + updateRecord.getUpdate_time());
        Object object = session.getAttribute("defect");

        String prefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
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
            }
        }
        // 处理搜索场景下无project id
        Object projectID = session.getAttribute(SESSION_PROJECT_ID);
        if (projectID == null) session.setAttribute(SESSION_PROJECT_ID, defect.getProject_id());
        return "redirect:/toDefectList?id=" + defect.getProject_id(); //redirect重定向
    }

    // 删除文件
    @RequestMapping("/deleteDefectFile")
    public String deleteDefectFile(int id, int fileId) {
        fileUploadController.deleteFile(fileId);
        return "redirect:/toUpdateDefect?id=" + id;
    }

    //删除缺陷
    @RequestMapping("/deleteDefect")
    public String deleteDefect(HttpServletRequest request,
                             String id) {
        Object object = request.getSession().getAttribute(SESSION_PROJECT_ID);
        int projectId = object != null ? (int) object : Integer.parseInt("");
        defectServiceImpl.deleteDefectWithId(id);
        return "redirect:/toDefectList?id=" + projectId; //redirect重定向
    }

}

