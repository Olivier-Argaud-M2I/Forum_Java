package fr.m2i.forum.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="role_privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> privilegeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public Role() {
    }

    public Role(Integer id, String name, List<Privilege> privilegeList) {
        this.id = id;
        this.name = name;
        this.privilegeList = privilegeList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
//                ", privilegeList=" + privilegeList +
                '}';
    }
}
