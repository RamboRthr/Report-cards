package escola.ebisco.projetoboletins.Services;

import escola.ebisco.projetoboletins.Domain.Classroom;
import escola.ebisco.projetoboletins.Domain.Professor;
import escola.ebisco.projetoboletins.Repo.ClassroomRepository;
import escola.ebisco.projetoboletins.Repo.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    @Transactional
    public ResponseEntity saveOrUpdate(@RequestBody Professor professor){
        professorRepository.save(professor);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody Professor professor){
        professorRepository.update(professor.getId(), professor.getName(), professor.getSalary());
        return ResponseEntity.accepted().build();
    }
    @DeleteMapping
    public ResponseEntity delete(@RequestParam("id") Long id){
        professorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

