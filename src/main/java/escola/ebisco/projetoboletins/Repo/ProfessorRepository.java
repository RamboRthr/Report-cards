package escola.ebisco.projetoboletins.Repo;

import escola.ebisco.projetoboletins.Domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
