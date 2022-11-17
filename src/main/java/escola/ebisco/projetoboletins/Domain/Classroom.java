package escola.ebisco.projetoboletins.Domain;


import org.springframework.lang.Nullable;
import org.springframework.lang.UsesSunMisc;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classroom_id;
    @Nullable
    private Integer nbrStudents;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "classroomId")
    private List<Student> students;

    @Nullable
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "classrooms")
    private Set<Professor> professors;

    private double mathMeanNote;

    private double portugueseMeanNote;

    private double germanMeanNote;

    public Classroom(){

    }

    public Classroom(Long id) {
        this.classroom_id = id;
    }

    /*public Classroom(Long id, Integer nbrStudents, List<Student> students, double mathMeanNote, double portugueseMeanNote, double germanMeanNote) {
        this.id = id;
        this.nbrStudents = nbrStudents;
        this.students = students;
        this.mathMeanNote = mathMeanNote;
        this.portugueseMeanNote = portugueseMeanNote;
        this.germanMeanNote = germanMeanNote;
    }*/

    public Long getId() {
        return classroom_id;
    }

    public void setId(Long id) {
        this.classroom_id = id;
    }

    public Integer getNbrStudents() {
        return nbrStudents;
    }

    public void setNbrStudents(@Nullable Integer nbrStudents) {
        this.nbrStudents = nbrStudents;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public double getMathMeanNote() {
        return mathMeanNote;
    }

    public void setMathMeanNote(double mathMeanNote) {
        this.mathMeanNote = mathMeanNote;
    }

    public double getPortugueseMeanNote() {
        return portugueseMeanNote;
    }

    public void setPortugueseMeanNote(double portugueseMeanNote) {
        this.portugueseMeanNote = portugueseMeanNote;
    }

    public double getGermanMeanNote() {
        return germanMeanNote;
    }

    public void setGermanMeanNote(double germanMeanNote) {
        this.germanMeanNote = germanMeanNote;
    }
    public void setMeanNotes(){
        double sumMath = 0;
        double sumPortuguese = 0;
        double sumGerman = 0;

        for (Student std:this.students) {
            sumMath += std.getMathematics();
            sumPortuguese += std.getPortuguese();
            sumGerman += std.getGerman();
        }

        this.mathMeanNote = sumMath / nbrStudents;
        this.portugueseMeanNote = sumPortuguese / nbrStudents;
        this.germanMeanNote = sumGerman / nbrStudents;
    }

    public void update(){
        this.setNbrStudents(this.students.size());
        this.setMeanNotes();
    }
}
