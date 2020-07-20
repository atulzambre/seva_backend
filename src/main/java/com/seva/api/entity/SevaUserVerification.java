package com.seva.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the seva_user_verification database table.
 * 
 */
@Entity
@Table(name="seva_user_verification")
@NamedQuery(name="SevaUserVerification.findAll", query="SELECT s FROM SevaUserVerification s")
public class SevaUserVerification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="suv_id",columnDefinition = "serial")
	private Integer suvId;

	@Column(name="suv_code")
	private Integer suvCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="suv_created_date")
	private Date suvCreatedDate;

	//bi-directional many-to-one association to SevaUser
	@ManyToOne
	@JoinColumn(name="su_id")
	private SevaUser sevaUser;

	public SevaUserVerification() {
	}

	public Integer getSuvId() {
		return this.suvId;
	}

	public void setSuvId(Integer suvId) {
		this.suvId = suvId;
	}

	public Integer getSuvCode() {
		return this.suvCode;
	}

	public void setSuvCode(Integer suvCode) {
		this.suvCode = suvCode;
	}

	public Date getSuvCreatedDate() {
		return this.suvCreatedDate;
	}

	public void setSuvCreatedDate(Date suvCreatedDate) {
		this.suvCreatedDate = suvCreatedDate;
	}

	public SevaUser getSevaUser() {
		return this.sevaUser;
	}

	public void setSevaUser(SevaUser sevaUser) {
		this.sevaUser = sevaUser;
	}

}