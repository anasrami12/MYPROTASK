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
@Table(name = "evaluations")
public class Evaluation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evaluation")
	private Long idEvaluations;

	@Column(name = "team_work", nullable = false)
	private Integer teamWork;

	@Column(name = "individual_work", nullable = false)
	private Integer individualWork;

	@Column(name = "initiative", nullable = false)
	private Integer initiative;

	@Column(name = "problem_resolution", nullable = false)
	private Integer problemResolution;

	@Column(name = "observation")
	private String observation;

	@Column(name = "date", nullable = false)
	private Date date;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_project_id")
	private UserProject userProject;

	public Evaluation() {
	}

	public Evaluation(Integer teamWork, Integer individualWork, Integer initiative, Integer problemResolution,
			String observation, UserProject userProject) {
		this.teamWork = teamWork;
		this.individualWork = individualWork;
		this.initiative = initiative;
		this.problemResolution = problemResolution;
		this.observation = observation;
		this.date = generateLocalDate();
		this.userProject = userProject;
	}

	public Evaluation(Integer teamWork, Integer individualWork, Integer initiative, Integer problemResolution,
			String observation, Date date, UserProject userProject) {
		this.teamWork = teamWork;
		this.individualWork = individualWork;
		this.initiative = initiative;
		this.problemResolution = problemResolution;
		this.observation = observation;
		this.date = date;
		this.userProject = userProject;
	}

	/**
	 * @return the current date
	 */
	private Date generateLocalDate() {
		return Date.valueOf(LocalDate.now());
	}

	public Integer getTeamWork() {
		return teamWork;
	}

	public void setTeamWork(Integer teamWork) {
		this.teamWork = teamWork;
	}

	public Integer getIndividualWork() {
		return individualWork;
	}

	public void setIndividualWork(Integer individualWork) {
		this.individualWork = individualWork;
	}

	public Integer getInitiative() {
		return initiative;
	}

	public void setInitiative(Integer initiative) {
		this.initiative = initiative;
	}

	public Integer getProblemResolution() {
		return problemResolution;
	}

	public void setProblemResolution(Integer problemResolution) {
		this.problemResolution = problemResolution;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserProject getUserProject() {
		return userProject;
	}

	public void setUserProject(UserProject userProject) {
		this.userProject = userProject;
	}

	public Long getIdEvaluations() {
		return idEvaluations;
	}

	@Override
	public String toString() {
		return "Evaluation [idEvaluations=" + idEvaluations + ", teamWork=" + teamWork + ", individualWork=" + individualWork
				+ ", iniciative=" + initiative + ", problemResolution=" + problemResolution + ", observation="
				+ observation + ", date=" + date + ", userProject=" + userProject + "]";
	}

}
