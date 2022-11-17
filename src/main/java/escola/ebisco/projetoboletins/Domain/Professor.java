package escola.ebisco.projetoboletins.Domain;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professor_id;
    private String name;
    private Double salary;
    @Nullable
    @ManyToMany
    @JoinTable(
            name = "professor_classroom",
            joinColumns = {
                    @JoinColumn(name = "professor_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "classroom_id")
            }

    )
    private Set<Classroom> classrooms;

    public Professor(Long professorId) {
        this.professor_id = professorId;
    }

    public Professor(){

    }

    public Long getProfessorId() {
        return professor_id;
    }

    public void setProfessorId(Long professorId) {
        this.professor_id = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Set<Classroom> getClassrooms() {
        return classrooms;
    }

    public void addClassroom(Classroom classroom) {
        this.classrooms.add(classroom);
    }
}
