package escola.ebisco.projetoboletins.Services;

import escola.ebisco.projetoboletins.Domain.Classroom;
import escola.ebisco.projetoboletins.Repo.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classroom")
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @GetMapping
    public List<Classroom> getAll(){
        return classroomRepository.findAll();
    }
    @GetMapping
    @RequestMapping(value = "/byId")
    public Classroom getById(@RequestParam() Long id){ return classroomRepository.findById(id).get();};

    @PostMapping
    public ResponseEntity insert(@RequestBody Classroom classroom){
        classroomRepository.save(classroom);
        return ResponseEntity.accepted().build();
    }
    @GetMapping(value = "/byNbrStudents")
    public List<Classroom> getByNbrStudents(@RequestParam("nbr") Integer nbr){
        return classroomRepository.findByNbrStudents(nbr);
    }

    @GetMapping
    @RequestMapping(value = "/byName")
    public List<Classroom> getByStudents(@RequestParam("name") String name){
        return classroomRepository.findByStudents(name);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam("id") Long id){
        classroomRepository.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    @RequestMapping(value = "/mathNoteBetween")
    public List<Classroom> getByMathNoteBetween(@RequestParam("min") double min, @RequestParam("max") double max){
        return classroomRepository.findByMathMeanNoteBetween(min, max);
    }
}
