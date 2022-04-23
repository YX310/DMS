package com.gxm.dts.controller;
import com.gxm.dts.mapper.DefectMapper;
import com.gxm.dts.mapper.ProjectMapper;
import com.gxm.dts.model.domain.*;
import com.gxm.dts.service.implement.DefectServiceImpl;
import com.gxm.dts.service.implement.DemandServiceImpl;
import com.gxm.dts.service.implement.ProjectServiceImpl;
import com.gxm.dts.service.implement.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;


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

    @GetMapping(value = {"","/login"})
    public String toLogin(){
        return "client/login";
    }

    @GetMapping(value = {"/index"})
    public String toIndex(){
        return "client/index";
    }

    @GetMapping(value = {"/register"})
    public String toRegister(){
        return "client/register";
    }

    //(前台)用户信息
    @GetMapping("/toMe")
    public String toMe(HttpServletRequest request, Integer id, Model model){
        User user = userServiceImpl.getUserId(id);
        model.addAttribute("user", user);
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
    public String updateUserWithId(User user){
        userServiceImpl.updateMeWithId(user);
        return "client/me";
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

    @GetMapping(value = {"/toOverview"})
    public String toOverView(HttpServletRequest request, Integer id, Model model){
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
        System.out.println(project);
        List<User> projectMember = projectServiceImpl.findProjectMemberByProjectId(id);
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
        return "client/overview";
    }

    @GetMapping(value = "/toWorkbench")
    public String toWorkbench(HttpServletRequest request, Integer id) {
        List<DefectProject> list = defectServiceImpl.selectDesignatedPersonWithUserId(id);
        List<DefectProject> list2 = defectServiceImpl.selectDefectCreatorWithUserId(id);
        request.setAttribute("data4", list); //指派给我的任务
        request.setAttribute("data5", list2);//已报告的任务
        return "client/workbench";
    }

    //用户搜索
    @ResponseBody
    @RequestMapping("/searchAllList")
    public List<SearchContent> deleteProject(String getSearchInput) {
        System.out.println("getSearchInput: " + getSearchInput);
        List<SearchContent> searchContentList = new ArrayList<>();
        addContentByList(searchContentList, defectServiceImpl.selectDefect(getSearchInput), "toUpdateDefect");
//        addContentByList(searchContentList, demandServiceImpl.selectDemand(), "demand");
//        addContentByList(searchContentList, userServiceImpl.selectUser(), "user");
//        addContentByList(searchContentList, projectServiceImpl.selectProject(), "project");
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
