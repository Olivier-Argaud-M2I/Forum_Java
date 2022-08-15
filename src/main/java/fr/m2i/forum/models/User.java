package fr.m2i.forum.models;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@NamedQueries({
        @NamedQuery(name="selectAllUser", query="SELECT user FROM User user"),
        @NamedQuery(name="findUserById", query="SELECT user FROM User user WHERE id = :id"),
        @NamedQuery(name="findUserByUsername", query="SELECT user FROM User user WHERE user.userName = :username"),
        @NamedQuery(name="deleteUserById",query ="DELETE FROM User WHERE id = :id")

})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role role;

    @ManyToMany
    @JoinTable(
            name="user_privilege",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> privilegeList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(Integer id, String userName, String password, String firstName, String lastName, Role role, List<Privilege> privilegeList) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.privilegeList = privilegeList;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public List<String> getPrivileges(){
        List<String> privileges = new ArrayList<>();
        for (Privilege privilege:getRole().getPrivilegeList()) {
            privileges.add(privilege.getName());
        }
//        for (Privilege privilege:getPrivilegeList()) {
//            privileges.add(privilege);
//        }
        return privileges;
    }

    public Boolean hasPrivilege(String privilege){
        return getPrivileges().contains(privilege);
    }

    public Boolean hasPrivilegeDeleteTopic(){
        return getPrivileges().contains("deleteTopic");
    }
}
