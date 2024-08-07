package au.com.riosoftware.task.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_user")
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
