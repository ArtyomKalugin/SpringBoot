package ru.stud.kpfu.kalugin.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @Size(min = 8, max = 64, message = "Password should contain from 8 to 64 symbols")
    @Column(nullable = false, length = 64)
    private String password;

    @Column(length = 64)
    private String verificationCode;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Appeal> appeals;

    private boolean enabled;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "group_id")
//    private Group group;

//    @OneToOne
//    @JoinColumn(name = "passport_id")
//    private Passport passport;

//    public Group getGroup() {
//        return group;
//    }

//    public void setGroup(Group group) {
//        this.group = group;
//    }
//
//    public Passport getPassport() {
//        return passport;
//    }
//
//    public void setPassport(Passport passport) {
//        this.passport = passport;
//    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User() {}

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Appeal> getAppeals() {
        return appeals;
    }

    public void setAppeals(List<Appeal> appeals) {
        this.appeals = appeals;
    }

    public String getVerificationCode(String wefefw) {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public User(String name, String email, String password, List<Appeal> appeals, String verificationCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.appeals = appeals;
        this.verificationCode = verificationCode;
    }

    public User(Integer id,String name, String email, String password, List<Appeal> appeals, String verificationCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.appeals = appeals;
        this.verificationCode = verificationCode;
    }
}
