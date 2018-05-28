package com.mamh.springmvc.demo.handlers;

import com.mamh.springmvc.demo.dao.DepartmentDao;
import com.mamh.springmvc.demo.dao.EmployeeDao;
import com.mamh.springmvc.demo.entities.Employee;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;


    @Autowired
    private ResourceBundleMessageSource messageSource;



    @RequestMapping("/testException")
    public String testException(@RequestParam(value = "i", defaultValue = "0") int i) {

        System.out.println("testException........" + (10 / i));

        return "success";
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam(value = "desc", required = false) String desc,
                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        System.out.println("testFileUpload.......\n");
        System.out.println("desc: " + desc);
        System.out.println("filename: " + file.getOriginalFilename());
        System.out.println("filesize: " + file.getSize());

        return "success";
    }


    @ResponseBody
    @RequestMapping("/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body) {
        System.out.println("testHttpMessageConverter.......\n" + body + "\n\n");

        return "hello world! " + new Date();
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson() {
        System.out.println("testJson.......");

        return employeeDao.getAll();
    }

    @RequestMapping("/testConverter")
    public String testConverter(@RequestParam("employee") Employee employee) {
        System.out.println("testConverter......." + employee);
        employeeDao.save(employee);
        return "emps";
    }


    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        //这个ID应该是post表单中传过来的。 这个方法作用到update（）方法之前，这个时候能获得ID的，然后从数据库中获得这个id对应的employee对象。
        //然后把它放到map中，键名是类名第一个字母小写。然后在update（Employee employee）方法中就能得到入参
        if (id != null) {
            map.put("employee", employeeDao.get(id));
        }

    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee) {
        System.out.println("update.........." + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        System.out.println("input..修改......" + id);
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        System.out.println("input.新建.........");
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());

        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Integer id) {
        System.out.println("delete.........." + id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }


    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee, BindingResult result) {
        System.out.println("save.........." + employee);
        System.out.println(result.getFieldError());

        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {

        map.put("employees", employeeDao.getAll());

        return "list";
    }

}
