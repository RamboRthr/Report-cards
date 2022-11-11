package escola.ebisco.projetoboletins.Services;

import escola.ebisco.projetoboletins.Domain.Classroom;
import escola.ebisco.projetoboletins.Repo.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("classroom")
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;

    @GetMapping
    public List<Classroom> getAll(){
        return classroomRepository.findAll();
    }
    @GetMapping(value = "/{id}")
    public Classroom getById(@PathVariable Long id){ return classroomRepository.findById(id).get();};

    @PostMapping
    public ResponseEntity insert(@RequestBody Classroom classroom){
        classroomRepository.save(classroom);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    @RequestMapping("/getByNbrStudents")
    public List<Classroom> getByNbrStudents(@RequestBody Integer nbr){
        return classroomRepository.findByNbrStudents(nbr);
    }

    @GetMapping
    @RequestMapping("/getClassroomByName")
    public List<Classroom> getByStudents(@RequestBody String name){
        return classroomRepository.findByStudents(name);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        classroomRepository.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    @RequestMapping("/mathNoteBetween")
    public List<Classroom> getByMathNoteBetween(@RequestBody List<Double> minAndMax){
        double min = Double.parseDouble(minAndMax.get(0).toString());
        double max = Double.parseDouble(minAndMax.get(1).toString());
        return classroomRepository.findByMathMeanNoteBetween(min, max);
    }
}
