package com.seva.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the seva_user database table.
 */
@Entity
@Table(name = "seva_user")
@NamedQuery(name = "SevaUser.findAll", query = "SELECT s FROM SevaUser s")
public class SevaUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "su_id", columnDefinition = "serial")
    private Long suId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "su_created_date")
    private Date suCreatedDate;

    @Column(name = "su_email")
    private String suEmail;

    @Column(name = "su_name")
    private String suName;

    @Column(name = "su_password")
    private String suPassword;

    @Column(name = "su_phone")
    private Long suPhone;

    //bi-directional many-to-one association to SevaUserRole
    @OneToMany(mappedBy = "sevaUser")
    private List<SevaUserRole> sevaUserRoles;

    //bi-directional many-to-one association to SevaUserVerification
    @OneToMany(mappedBy = "sevaUser")
    private List<SevaUserVerification> sevaUserVerifications;

    public SevaUser() {
    }

    public List<SevaUserVerification> getSevaUserVerifications() {
        return sevaUserVerifications;
    }

    public void setSevaUserVerifications(List<SevaUserVerification> sevaUserVerifications) {
        this.sevaUserVerifications = sevaUserVerifications;
    }

    public Long getSuId() {
        return this.suId;
    }

    public void setSuId(Long suId) {
        this.suId = suId;
    }

    public Date getSuCreatedDate() {
        return this.suCreatedDate;
    }

    public void setSuCreatedDate(Date suCreatedDate) {
        this.suCreatedDate = suCreatedDate;
    }

    public String getSuEmail() {
        return this.suEmail;
    }

    public void setSuEmail(String suEmail) {
        this.suEmail = suEmail;
    }

    public String getSuName() {
        return this.suName;
    }

    public void setSuName(String suName) {
        this.suName = suName;
    }

    public String getSuPassword() {
        return this.suPassword;
    }

    public void setSuPassword(String suPassword) {
        this.suPassword = suPassword;
    }

    public Long getSuPhone() {
        return this.suPhone;
    }

    public void setSuPhone(Long suPhone) {
        this.suPhone = suPhone;
    }

    public List<SevaUserRole> getSevaUserRoles() {
        return this.sevaUserRoles;
    }

    public void setSevaUserRoles(List<SevaUserRole> sevaUserRoles) {
        this.sevaUserRoles = sevaUserRoles;
    }

    public SevaUserRole addSevaUserRole(SevaUserRole sevaUserRole) {
        getSevaUserRoles().add(sevaUserRole);
        sevaUserRole.setSevaUser(this);

        return sevaUserRole;
    }

    public SevaUserRole removeSevaUserRole(SevaUserRole sevaUserRole) {
        getSevaUserRoles().remove(sevaUserRole);
        sevaUserRole.setSevaUser(null);

        return sevaUserRole;
    }

}