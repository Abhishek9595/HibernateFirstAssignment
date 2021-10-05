package com.te.hibernate.assignmentone;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "std")
public class Student {
	@Id
	@Column(name = "roll_no")
	private int roll;
	@Column(name = "std_name")
	private String name;
	@Column(name = "std_contact")
	private long contact;
	@Column(name = "std_standard")
	private int standard;
}
