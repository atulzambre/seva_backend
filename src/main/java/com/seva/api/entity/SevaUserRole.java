package com.seva.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the seva_user_role database table.
 * 
 */
@Entity
@Table(name="seva_user_role")
@NamedQuery(name="SevaUserRole.findAll", query="SELECT s FROM SevaUserRole s")
public class SevaUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sur_id",columnDefinition = "serial")
	private Long surId;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sur_created_date")
	private Date surCreatedDate;


	//bi-directional many-to-one association to SevaRole
	@ManyToOne
	@JoinColumn(name="role_id")
	private SevaRole sevaRole;

	//bi-directional many-to-one association to SevaUser
	@ManyToOne
	@JoinColumn(name="su_id")
	private SevaUser sevaUser;

	public SevaUserRole() {
	}

	public Date getSurCreatedDate() {
		return surCreatedDate;
	}

	public void setSurCreatedDate(Date surCreatedDate) {
		this.surCreatedDate = surCreatedDate;
	}

	public Long getSurId() {
		return this.surId;
	}

	public void setSurId(Long surId) {
		this.surId = surId;
	}

	public SevaRole getSevaRole() {
		return this.sevaRole;
	}

	public void setSevaRole(SevaRole sevaRole) {
		this.sevaRole = sevaRole;
	}

	public SevaUser getSevaUser() {
		return this.sevaUser;
	}

	public void setSevaUser(SevaUser sevaUser) {
		this.sevaUser = sevaUser;
	}

}