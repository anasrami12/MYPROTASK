package com.app.myprotask.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_projects")
public class UserProject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user_project")
	private Long idUserProject;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@Column(name = "join_date")
	private Date joinDate;

	@Column(name = "exit_date")
	private Date exitDate;

	@Column(name = "is_active")
	private boolean isActive;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

	public UserProject() {
	}

	public UserProject(User user, Project project, Role role) {
		this.user = user;
		this.project = project;
		this.joinDate = generateLocalDate();
		this.isActive = true;
		this.role = role;
	}

	/**
	 * @return the current date
	 */
	private Date generateLocalDate() {
		return Date.valueOf(LocalDate.now());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getExitDate() {
		return exitDate;
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Long getIdUserProject() {
		return idUserProject;
	}

	@Override
	public String toString() {
		return "UserProject [idUserProject=" + idUserProject + ", user=" + user + ", project=" + project + ", joinDate="
				+ joinDate + ", exitDate=" + exitDate + ", isActive=" + isActive + ", role=" + role + "]";
	}

}
