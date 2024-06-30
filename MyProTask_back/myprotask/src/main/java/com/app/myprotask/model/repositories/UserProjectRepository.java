package com.app.myprotask.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.myprotask.model.UserProject;

/**
 * Repository where we will have all the necessary queries about the UserProject
 * table.
 * 
 */
@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

	/**
	 * @param idUser
	 * @param idProject
	 * @return id_user_project by user and project
	 */
	@Query(value = "SELECT id_user_project FROM user_projects WHERE user_id = ?1 AND project_id = ?2", nativeQuery = true)
	Long displayIdUserProjectByIdUserIdProject(Long idUser, Long idProject);

	/**
	 * @param idProject
	 * @return all active users projects of project
	 */
	@Query(value = "SELECT * FROM user_projects WHERE project_id = ?1 AND is_active = true ORDER BY role_id = 3 DESC", nativeQuery = true)
	List<UserProject> displayActiveUserProjectByIdProject(Long idProject);

	/**
	 * @param idProject
	 * @return count UserProjects in the specific project
	 */
	@Query(value = "SELECT COUNT(*) FROM user_projects WHERE project_id = ?1 AND is_active = true", nativeQuery = true)
	Integer countActiveUserProjectByIdProject(Long idProject);

	/**
	 * 
	 * @param idProject
	 * @return all projects from one specific project
	 */
	@Query(value = "SELECT * FROM user_projects WHERE project_id = ?1 ORDER BY role_id, CASE WHEN is_active = 1 THEN 0 ELSE 1 END, is_active DESC", nativeQuery = true)
	List<UserProject> displayUserProjectByProjectId(Long idProject);

	/**
	 * 
	 * @param idUser
	 * @return all projects from one specific user
	 */
	@Query(value = "SELECT * FROM user_projects WHERE user_id = ?1", nativeQuery = true)
	List<UserProject> displayUserProjectByUserId(Long idUser);

	/**
	 * View ADMIN
	 * 
	 * @return all the information of users and projects they have participated in,
	 *         sorted by their active status
	 */
	@Query(value = "SELECT up.* FROM user_projects up, projects p " + "WHERE up.project_id = p.id_project "
			+ "AND p.is_active = true " + "ORDER BY up.is_active", nativeQuery = true)
	List<UserProject> displayActiveUserProject();

	/**
	 * View USER
	 * 
	 * @return all users and projects they have participated in, as long as the
	 *         introduced user participates in the project, sorted by their active
	 *         status
	 */
	@Query(value = "SELECT up.* FROM user_projects up, projects p " + "WHERE p.id_project = up.project_id "
			+ "AND p.is_active = true " + "AND up.user_id = ?1 " + "ORDER BY up.is_active", nativeQuery = true)
	List<UserProject> displayActiveUserProjectByUserId(Long idUser);

}
