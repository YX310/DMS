package com.gxm.dts.controller;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.implement.*;
import com.gxm.dts.util.Constant;
import com.gxm.dts.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static com.gxm.dts.util.Constant.*;
import static com.gxm.dts.util.Utils.applyInfo;


/**
 * @类名 IndexController
 * @描述 一个对控制页面操作的类
 * @时间 2022-3-22
 * @创建者 guanxiaomin
 */
@Controller
public class IndexController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ProjectServiceImpl projectServiceImpl;
    @Autowired
    private DefectServiceImpl defectServiceImpl;
    @Autowired
    private ProjectController projectController;
    @Autowired
    private DemandServiceImpl demandServiceImpl;
    @Autowired
    private UpdateRecordServiceImpl updateRecordServiceImpl;

    @GetMapping(value = {"","/login"})
    public String toLogin(HttpSession session, Model model) {
        if (DEBUG) System.out.println("ERROR_INFO");
        model.addAttribute(ERROR_INFO, session.getAttribute(ERROR_INFO));
        session.setAttribute(ERROR_INFO, null);

        return "client/login";
    }

    @GetMapping(value = {"/index"})
    public String toIndex(){
        return "client/index";
    }

    @GetMapping(value = {"/register"})
    public String toRegister(HttpSession session,Model model) {
        model.addAttribute(ERROR_INFO, session.getAttribute(ERROR_INFO));
        session.setAttribute(ERROR_INFO, null);
        return "client/register";
    }

    @GetMapping(value = {"/unauthorizedPage"})
    public String toUnauthorizedPage(Model model) {
        model.addAttribute(ERROR_INFO, "请先进入项目~");
        return "public/unauthorizedPage";
    }

    //(前台)用户信息
    @RequestMapping("/toMe")
    public String toMe(Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        String updatePerson = user.getUsername();
        List<UpdateRecord> updateRecords = updateRecordServiceImpl.selectUpdateRecordWithUpdatePerson(updatePerson);
        model.addAttribute("user", user);
        model.addAttribute("updateRecords",updateRecords);
        return "client/me";
    }

    //(前台)用户信息
    @GetMapping("/toUpdateMe")
    public String toUpdateMe(Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        model.addAttribute("user", user);
        return "client/meUpdate";
    }

    //(前台)修改用户信息
    @RequestMapping("/updateMe")
    public String updateUserWithId(HttpServletRequest request,
                                   User user){
        HttpSession session = request.getSession(true);
        userServiceImpl.updateMeWithId(user);
        session.setAttribute("head_img", user.getHead_img());
        System.out.println(user);
        return "redirect:/toMe?id=" + user.getUser_id();
    }

    //获取当前所选项目id
    @GetMapping(value = "/getProjectId")
    public String getProjectId(HttpServletRequest request,
                               @RequestParam("projectId") Integer id) {
        Project p  = projectServiceImpl.getProjectId(id);
        if (id == null) {
            return "client/home";
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("projectId", p.getProject_id());
            return projectController.getProjectById(request, id);
        }
    }

    @RequestMapping(value = {"/toOverview"})
    public String toOverView(HttpSession session, Integer id, Model model){
        Project project = projectServiceImpl.getProjectId(id);
        model.addAttribute("project", project);
        List<Defect> defects = defectServiceImpl.selectDefectWithProjectId(id);
        List<Demand> demands = demandServiceImpl.selectDemandWithProjectId(id);
        int closedDefectNum = 0;
        int allDefectNum = defects.size();
        int closedDemandNum = 0;
        int allDemandNum = demands.size();
        for (Defect defect : defects) {
            if ("已关闭".equals(defect.getDefect_state())) {
                closedDefectNum++;
            }
        }
        for (Demand demand : demands) {
            if ("已关闭".equals(demand.getDemand_state())) {
                closedDemandNum++;
            }
        }
        model.addAttribute("allDefectNum", allDefectNum);
        model.addAttribute("closedDefectNum", closedDefectNum);
        model.addAttribute("allDemandNum", allDemandNum);
        model.addAttribute("closedDemandNum", closedDemandNum);
        if (DEBUG) System.out.println(project);
        List<User> projectMember = projectServiceImpl.findProjectMemberByProjectId(id);
        if (DEBUG) System.out.println("projectMember.size" + projectMember.size());
        List<String> PM = new ArrayList<>();
        List<String> RD = new ArrayList<>();
        List<String> QA = new ArrayList<>();
//        Map<String, List<String>> map = new HashMap<>();
//        map.put("项目经理", new ArrayList<>());
//        map.put("开发", new ArrayList<>());
//        map.put("测试", new ArrayList<>());
        for(User user : projectMember) {
//            map.get(user.getUser_role()).add(user.getUsername());
            if (user.getUser_role().equals("项目经理")) {PM.add(user.getUsername());}
            if (user.getUser_role().equals("开发")) {RD.add(user.getUsername());}
            if (user.getUser_role().equals("测试")) {QA.add(user.getUsername());}
        }
        model.addAttribute("PMName", PM);
        model.addAttribute("RDName", RD);
        model.addAttribute("QAName", QA);

        model.addAttribute("allUpdateRecord", updateRecordServiceImpl.selectUpdateRecordWithProjectId(project.getProject_id()));

        // for edit project
        applyInfo(session, model, UPDATE_INFO);
        return "client/overview";
    }

    @GetMapping(value = "/toWorkbench")
    public String toWorkbench(HttpServletRequest request, Integer id) {
        List<TaskProfile> list = new ArrayList<>();
        list.addAll(extractTask(defectServiceImpl.selectDefectDesignatedPersonWithUserId(id)));
        list.addAll(extractTask(demandServiceImpl.selectDemandDesignatedPersonWithUserId(id)));
        Collections.sort(list);

        List<TaskProfile> list2 = new ArrayList<>();
        list2.addAll(extractTask(demandServiceImpl.selectDemandCreatorWithUserId(id)));
        list2.addAll(extractTask(defectServiceImpl.selectDefectCreatorWithUserId(id)));
        Collections.sort(list2);

        request.setAttribute("data4", subList(list, 10)); //指派给我的任务
        request.setAttribute("data5", subList(list2, 10));//已报告的任务
        return "client/workbench";
    }

    private List<TaskProfile> subList(List<TaskProfile> list, int size) {
        if (list.size() >= 10) return list.subList(0, 10);
        return list;
    }

    /** Extract task from DefectProject and DemandProject.
     *
     * @param list any list can be resolved.
     *
     * @return a resolved list.
     */

    private List<TaskProfile> extractTask(List<?> list) {
        if (list.size() == 0) return Collections.emptyList();
        List<TaskProfile> res = new ArrayList<>();
        list.forEach(item -> {
            if (item instanceof DefectProject) {
                DefectProject defectProject = (DefectProject) item;
                res.add(new TaskProfile(Integer.parseInt(defectProject.getDefect_id()),
                        defectProject.getProject_id(),
                        defectProject.getProject_name(),
                        defectProject.getDefect_name(),
                        defectProject.getDefect_state()));
            }
            if (item instanceof DemandProject) {
                DemandProject demandProject = (DemandProject) item;
                res.add(new TaskProfile(demandProject.getDemand_id(),
                        demandProject.getProject_id(),
                        demandProject.getProject_name(),
                        demandProject.getDemand_name(),
                        demandProject.getDemand_state()));
            }
        });
        return res;
    }

    //用户搜索
    @ResponseBody
    @RequestMapping("/searchAllList")
    public List<SearchContent> deleteProject(String getSearchInput) {
        System.out.println("getSearchInput: " + getSearchInput);
        List<SearchContent> searchContentList = new ArrayList<>();
        addContentByList(searchContentList, defectServiceImpl.selectDefect(getSearchInput), "toUpdateDefect");
        addContentByList(searchContentList, demandServiceImpl.selectDemand(getSearchInput), "toUpdateDemand");
        addContentByList(searchContentList, projectServiceImpl.selectProject(getSearchInput), "toOverview");
        addContentByList(searchContentList, userServiceImpl.selectUser(getSearchInput), "toMe");
        return searchContentList;
    }

    private void addContentByList(List<SearchContent> searchContentList, List<SearchContent> item, String type) {
        if (item != null) {
//            for(Map.Entry<String, Integer> entry : item.entrySet()) {
//                entry.getKey();
//                entry.getValue();
//            }
            for (SearchContent searchContent : item) {
                searchContent.setType(type);
                System.out.println(searchContent.toString());
                searchContentList.add(searchContent);
            }
        }
    }
}
