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
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @GetMapping
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity insertStudent(@RequestBody Student student){
        studentRepository.save(student);
        setStudentIntoClassroom(student);

        return ResponseEntity.accepted().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){
        String name = null;
        for (Student student: this.getAll()) {
            if (Objects.equals(student.getId(), id)){
                name = student.getName();
            }
        }
        studentRepository.deleteById(id);
        return new ResponseEntity<String>("Student" + name + " deleted", HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id){
        return studentRepository.findById(id);
    }
    @GetMapping
    @RequestMapping("/byName")
    public List<Student> getStudentByName(@RequestBody String name){
        return studentRepository.findByName(name);
    }
    @RequestMapping("/byClassroomId")
    @GetMapping
    public List<Student> getStudentByClassroomId(@RequestBody Long id){
        return studentRepository.findByClassroomId(id);
    }
    @RequestMapping("/minFrequency")
    @GetMapping
    public List<Student> getStudentByMinFrequency(@RequestBody double min){
        return studentRepository.findByFrequencyGreaterThanEqual(min);
    }
    private void setStudentIntoClassroom(Student student){
        Optional<Classroom> c = classroomRepository.findById(student.getClassroomId());
        Classroom classroom;
        if (c.isPresent()){
            classroom = c.get();
            //classroom.getStudents().add(student);
            classroom.update();
            classroomRepository.save(classroom);

        }
        else {
            System.out.println("Student registered in inexisting classroom.");
        }

    }
}
