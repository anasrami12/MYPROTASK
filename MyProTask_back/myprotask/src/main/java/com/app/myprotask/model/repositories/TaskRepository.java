package com.app.myprotask.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.myprotask.model.Task;

/**
 * 
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	/**
	 * @param idUser
	 * @return all tasks from all projects in which the given user participates
	 */
	@Query(value = "SELECT t.* FROM tasks t, projects p, user_projects up "
			+ "WHERE up.user_id = ?1 "
			+ "AND p.id_project = up.project_id "
			+ "AND t.project_id = p.id_project "
			+ "ORDER BY CASE WHEN priority = 'HIGH' "
			+ "THEN 1 WHEN priority = 'MID' THEN 2 "
			+ "WHEN priority = 'LOW' THEN 3 END, t.name", nativeQuery = true)
	List<Task> displayTasksByProjectsByIdUser(Long idUser);

	/**
	 * @param idProject
	 * @return count active tasks the specified project ordered by priority and name
	 */
	@Query(value = "SELECT COUNT(*) FROM tasks " + "WHERE project_id = ?1 "
			+ "AND is_active = true", nativeQuery = true)
	Integer countActiveTasksByIdProject(Long idProject);

	/**
	 * @param idProject
	 * @return all project tasks
	 */
	@Query(value = "SELECT * FROM tasks WHERE project_id = ?1", nativeQuery = true)
	List<Task> displayTasksByIdProject(Long idProject);

	/**
	 * @param idUser
	 * @return all tasks active for projects active by User ID order by priority
	 */
	@Query(value = "SELECT t.* FROM tasks t, projects p, user_tasks ut " + "WHERE t.id_task = ut.task_id "
			+ "AND p.id_project = t.project_id " + "AND ut.user_id = ?1 " + "AND t.is_active = true "
			+ "AND p.is_active = true " + "AND ut.is_active "
			+ "ORDER BY CASE WHEN priority = 'HIGH' THEN 1 WHEN priority = 'MID' THEN 2 WHEN priority = 'LOW' THEN 3 END, t.name", nativeQuery = true)
	List<Task> displayActiveTasksActiveProjectByIdUser(Long idUser);

	/**
	 * @return List of tasks of the user ordered by project id
	 */
	@Query(value = "SELECT t.* FROM tasks t, user_tasks ut " + "WHERE t.id_task = ut.task_id " + "AND ut.user_id = ?1 "
			+ "ORDER BY project_id", nativeQuery = true)
	List<Task> displayTasksByIdUser(Long idUser);

	/**
	 * @param idUser
	 * @return all tasks in which the specified user is participating and ordered by
	 *         priority and name
	 */
	@Query(value = "SELECT t.* FROM tasks t, user_tasks ut " + "WHERE t.id_task = ut.task_id " + "AND ut.user_id = ?1 "
			+ "AND ut.is_active = true "
			+ "ORDER BY CASE WHEN priority = 'HIGH' THEN 1 WHEN priority = 'MID' THEN 2 WHEN priority = 'LOW' THEN 3 END, t.name", nativeQuery = true)
	List<Task> displayActiveTasksByIdUser(Long idUser);

	/**
	 * @param idProject
	 * @return all active tasks the specified project ordered by priority and name
	 */
	@Query(value = "SELECT * FROM tasks " + "WHERE project_id = ?1 " + "AND is_active = true "
			+ "ORDER BY CASE WHEN priority = 'HIGH' THEN 1 WHEN priority = 'MID' THEN 2 WHEN priority = 'LOW' THEN 3 END, name", nativeQuery = true)
	List<Task> displayActiveTasksByIdProject(Long idProject);
}
