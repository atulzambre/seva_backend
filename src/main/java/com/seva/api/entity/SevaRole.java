package com.seva.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the seva_role database table.
 */
@Entity
@Table(name = "seva_role")
@NamedQuery(name = "SevaRole.findAll", query = "SELECT s FROM SevaRole s")
public class SevaRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    //bi-directional many-to-one association to SevaUserRole
    @OneToMany(mappedBy = "sevaRole")
    private List<SevaUserRole> sevaUserRoles;

    public SevaRole() {
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SevaUserRole> getSevaUserRoles() {
        return this.sevaUserRoles;
    }

    public void setSevaUserRoles(List<SevaUserRole> sevaUserRoles) {
        this.sevaUserRoles = sevaUserRoles;
    }

    public SevaUserRole addSevaUserRole(SevaUserRole sevaUserRole) {
        getSevaUserRoles().add(sevaUserRole);
        sevaUserRole.setSevaRole(this);

        return sevaUserRole;
    }

    public SevaUserRole removeSevaUserRole(SevaUserRole sevaUserRole) {
        getSevaUserRoles().remove(sevaUserRole);
        sevaUserRole.setSevaRole(null);

        return sevaUserRole;
    }

}