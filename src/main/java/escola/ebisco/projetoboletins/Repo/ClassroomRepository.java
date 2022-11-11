package escola.ebisco.projetoboletins.Repo;

import escola.ebisco.projetoboletins.Domain.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByNbrStudents(Integer nbrStudents);
    List<Classroom> findByStudents(String name);

}
