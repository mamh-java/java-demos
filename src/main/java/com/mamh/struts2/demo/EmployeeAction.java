package com.mamh.struts2.demo;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.RequestAware;

import java.util.Map;

public class EmployeeAction implements RequestAware, ModelDriven<Employee>, Preparable {
    private Dao dao = new Dao();
    private Map<String, Object> request;

    private Employee employee = null;
    private Integer employeeId;

    public void setEmployeeId(Integer id) {
        this.employeeId = id;
    }

    public Employee getModel() {
        return employee;
    }

    public void prepareUpdate() {
        employee = new Employee();
    }

    public String update() {
        System.out.println("update................");
        dao.update(employee);
        return "update";
    }

    public void prepareEdit() {
        employee = dao.get(employeeId);
        System.out.println("=prepareEdit==" + employeeId);
    }

    public String edit() {
        System.out.println("edit...........");
        return "edit";
    }

    public String list() {
        request.put("emps", dao.getEmployees());
        System.out.println("list....................");
        return "list";
    }

    public void prepareSave() {
        employee = new Employee();
    }

    public String save() {
        System.out.println("save.....................");
        dao.save(employee);

        return "save";
    }

    public void prepareDelete() {
        System.out.println("=prepareDelete=" + employeeId);
    }

    public String delete() {
        System.out.println("delete......................");
        dao.delete(employeeId);
        return "delete";
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }


    public void prepare() throws Exception {
        System.out.println("prepare.................");
    }
}
