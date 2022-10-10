package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="GROUPS")
public class Groups {
	@javax.persistence.Id
	public int Id;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@NotNull
	@Column(name="group_name", nullable=false)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
    @Column(name="group_name")
	public String groupName;

}
