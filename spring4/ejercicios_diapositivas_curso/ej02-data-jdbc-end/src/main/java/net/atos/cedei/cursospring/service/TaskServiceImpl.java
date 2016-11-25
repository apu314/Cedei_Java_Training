package net.atos.cedei.cursospring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.atos.cedei.cursospring.dao.TaskDAO;
import net.atos.cedei.cursospring.domain.TaskEntity;
import net.atos.cedei.cursospring.transform.TaskTransform;
import net.atos.cedei.cursospring.vo.TaskVO;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO = null;

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    public TaskVO retrieveTask(Long id) {
        TaskEntity taskEntity = taskDAO.retrieveTask(id);
        TaskVO taskVO = TaskTransform.transformToVO(taskEntity);
        return taskVO;
    }
    
    @Override
    public List<TaskVO> retrieveTasks() {
        List<TaskEntity> taskEntities = taskDAO.retrieveTasks();
        List<TaskVO> taskVOs = TaskTransform.transformToVO(taskEntities);
        return taskVOs;
    }

    @Override
    public TaskVO createTask(TaskVO taskVO) {
        TaskEntity taskEntity = TaskTransform.transformToEntity(taskVO);
        TaskEntity taskEntityCreated = taskDAO.createTask(taskEntity);
        TaskVO taskVOCreated = TaskTransform.transformToVO(taskEntityCreated);
        return taskVOCreated;
    }

    @Override
    public void updateTask(TaskVO taskVO) {
        TaskEntity taskEntity = TaskTransform.transformToEntity(taskVO);
        taskDAO.updateTask(taskEntity);
    }

    @Override
    public List<TaskVO> findTasksByName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            name = "%";
        }
        List<TaskEntity> taskEntities = taskDAO.findTasksByName(name);
        List<TaskVO> taskVOs = TaskTransform.transformToVO(taskEntities);
        return taskVOs;
    }

}
