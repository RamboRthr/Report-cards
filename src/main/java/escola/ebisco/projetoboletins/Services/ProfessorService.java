package escola.ebisco.projetoboletins.Services;

import escola.ebisco.projetoboletins.Domain.Classroom;
import escola.ebisco.projetoboletins.Domain.Professor;
import escola.ebisco.projetoboletins.Repo.ClassroomRepository;
import escola.ebisco.projetoboletins.Repo.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    ClassroomRepository classroomRepository;
    @GetMapping
    public List<Professor> getAll(){
        return professorRepository.findAll();
    }
    @PostMapping
    public ResponseEntity insertProfessor(@RequestBody Professor professor){
        professorRepository.save(professor);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @RequestMapping(value = "/setClassroom")
    public ResponseEntity setClassroom(@RequestParam("professorId") Long professorId, @RequestParam("classroomId") Long classroomId){
        if (professorRepository.findById(professorId).isPresent() && classroomRepository.findById(classroomId).isPresent()){
            Professor professor = professorRepository.findById(professorId).get();
            professor.addClassroom(classroomRepository.findById(classroomId).get());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
