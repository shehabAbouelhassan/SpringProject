package io.dewe.pdt.services;

import io.dewe.pdt.domain.Project;
import io.dewe.pdt.exceptions.ProjectIdException;
import io.dewe.pdt.repositories.ProjectRepository;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());;
            return projectRepository.save(project);

        }catch(Exception e){
            throw new ProjectIdException("Project ID'"+project.getProjectIdentifier().toUpperCase()+"' already exists");

        }
        //Logic

    }
    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId+ "' does not  exists");

        }
        return project;

    }
    public Iterable<Project> findallProjects(){
        return projectRepository.findAll();
    }
public void deleteProjectByIdentifier(String projectid){
        Project project = projectRepository.findByProjectIdentifier(projectid.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Cannot delete Project with ID'" + projectid +"'. This project does not exists");
        }
        projectRepository.delete(project);
}
}