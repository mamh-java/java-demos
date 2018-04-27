package com.mamh.ssh.demo.actions;

import com.mamh.ssh.demo.dao.DepartmentDao;
import com.mamh.ssh.demo.entities.Employee;
import com.mamh.ssh.demo.service.DepartmentService;
import com.mamh.ssh.demo.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.RequestAware;

import javax.persistence.Id;
import javax.xml.ws.RequestWrapper;
import java.util.Date;
import java.util.Map;

public class EmployeeAction extends ActionSupport implements RequestAware, ModelDriven<Employee>, Preparable {

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private Map<String, Object> request;

    private Employee model;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void prepareSave() {
        System.out.println("prepare save()..................");
        model = new Employee();
    }

    public String save() {
        model.setCreateTime(new Date());
        employeeService.saveOrUpdate(model);
        System.out.println("save............\n" + model);
        return "save";
    }

    public String input() {
        request.put("departments", departmentService.getAll());

        return INPUT;
    }

    public String list() {
        System.out.println("list..............");
        request.put("employees", employeeService.getAll());
        return "list";
    }

    public String delete() throws Exception {

        System.out.println("delete..............");
        employeeService.delete(id);
        return "delete";
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }


    @Override
    public Employee getModel() {
        return model;
    }

    @Override
    public void prepare() throws Exception {
        System.out.println("prepare().......................");
    }
}
