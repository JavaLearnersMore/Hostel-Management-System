package com.hostel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hostel.entity.Student;
import com.hostel.service.StudentService;

@Controller
@RequestMapping("/admin")
public class AdminStudentController {

    @Autowired
    private StudentService studentService;

  //  // 👉 list
  //  @GetMapping("/students")
  //  public String getStudents(Model model) {
   //     model.addAttribute("students", studentService.getAllStudents());
   //     return "admin/students";
   // }

    // 👉 DELETE
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteById(id);

        return "redirect:/admin/students?deleted=true";
    }
    
 // 👉 Edit page open
    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Student student = studentService.getById(id);

        model.addAttribute("student", student);

        return "admin/edit-student";
    }

    // 👉 Update
    @PostMapping("/students/update")
    public String updateStudent(@ModelAttribute Student student) {

        studentService.save(student);

        return "redirect:/admin/students?updated=true";
    }
}
