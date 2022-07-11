package com.dev.wackr.Tables;

import java.time.LocalDate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {

	public Task() {
		this.createdOn = LocalDate.now();
	}

	/**
	 * Unique Company Id
	 */
	@Id
	@Column(name = "TASK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO) // Some are public for easier debugging REMEMBER TO CHANGE TO A
													// BETTER SYSTEM!!
	public long UniqueTaskID;

	@Column(name = "TASK_NAME")
	public String taskName;

	@Column(name = "CREATED_ON")
	private LocalDate createdOn;

	@Column(name = "DUE_DATE")
	private LocalDate dueDate;

	@Column(name = "TASK_DESCRIPTION")
	public String TaskDescription;

	@ManyToMany(mappedBy = "Tasks")
	private Set<User> UserOnJob;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BillingId")
	private Billing Bill;

	@JsonIgnore
	@Nullable
	@ManyToOne
	@JoinColumn (name = "CustomerID")
	public Customer customer;
	/*-----------------------------------   Getters and Setters --------------------------*/

	public long getUniqueTasID() {
		return UniqueTaskID;
	}

	public void setUniqueTaskID(long TaskID) {
		this.UniqueTaskID = TaskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String TaskName) {
		this.taskName = TaskName;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate() {
		this.dueDate = dueDate;
	}

	public String getTaskDescription() {
		return TaskDescription;
	}

	public void setTaskDescription(String TaskDescription) {
		this.TaskDescription = TaskDescription;
	}

	public Set<User> getUsersOnJob() {
		return UserOnJob;
	}

	public void setUsersOnJob() {
		this.UserOnJob = UserOnJob;
	}

}
