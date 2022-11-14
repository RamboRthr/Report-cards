package escola.ebisco.projetoboletins.Services;


import escola.ebisco.projetoboletins.Domain.Classroom;
import escola.ebisco.projetoboletins.Domain.Student;
import escola.ebisco.projetoboletins.Repo.ClassroomRepository;
import escola.ebisco.projetoboletins.Repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @GetMapping("/getAll")
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity insertStudent(@RequestBody Student student){
        studentRepository.save(student);
        setStudentIntoClassroom(student);
        return ResponseEntity.accepted().build();
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@RequestParam("id") Long id){
        if (studentRepository.findById(id).isPresent()) {
            String name = studentRepository.findById(id).get().getName();
            studentRepository.deleteById(id);
            return new ResponseEntity<String>("Student" + name + " deleted", HttpStatus.OK);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    @RequestMapping(value = "/byId")
    public Optional<Student> getStudentById(@RequestParam("id") Long id){
        return studentRepository.findById(id);
    }
    @RequestMapping(value = "/studentByName", method = RequestMethod.GET)
    public List<Student> getStudentByName(@RequestParam("name") String name){
        return studentRepository.findByName(name);
    }
    @RequestMapping(value = "/byClassroomId", method = RequestMethod.GET)
    public List<Student> getStudentByClassroomId(@RequestParam("classroomId") Long classroomId){
        return studentRepository.findByClassroomId(classroomId);
    }
    @RequestMapping(value = "/minFrequency", method = RequestMethod.GET)
    public List<Student> getStudentByMinFrequency(@RequestParam("min") double min){
        return studentRepository.findByFrequencyGreaterThanEqual(min);
    }

    private void setStudentIntoClassroom(Student student){
        Optional<Classroom> c = classroomRepository.findById(student.getClassroomId());
        Classroom classroom;
        if (c.isPresent()){
            classroom = c.get();
            classroom.update();
            classroomRepository.save(classroom);
        }
        else {
            System.out.println("Student registered in inexisting classroom.");
        }

    }

}
