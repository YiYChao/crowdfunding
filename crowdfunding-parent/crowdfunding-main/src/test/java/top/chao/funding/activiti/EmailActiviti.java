package top.chao.funding.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring*.xml")
public class EmailActiviti {

	@Autowired
	private ProcessEngine processEngine;
	
	// 流程的定义与部署
	@Test
	public void deployActiviti() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("activiti/EmailProcess.bpmn").deploy();
		
		System.err.println("Deployment:" + deployment);
	}
	
	// 启动流程
	@Test
	public void startActiviti() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("myProcess").latestVersion().singleResult();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("ckcode", 1234);
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
		
		System.err.println("processInstance:" + processInstance.getProcessInstanceId());
	}
	
	
	// 完成任务
	@Test
	public void finishActiviti() {
		TaskService taskService = processEngine.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> department = taskQuery.taskAssignee("department").list();
		for (Task task : department) {
			System.err.println("department task:" + task.getProcessInstanceId() + task.getName());
			taskService.complete(task.getId());
			System.err.println("department 完成任务：" + task.getId());
		}
		
		List<Task> finance = taskQuery.taskAssignee("finance").list();
		for (Task task : finance) {
			System.err.println("finance task:" + task.getProcessInstanceId() + task.getName());
			taskService.complete(task.getId());
			System.err.println("finance 完成任务：" + task.getId());
		}
		
		List<Task> zhangsan = taskQuery.taskAssignee("zhangsan").list();
		for (Task task : zhangsan) {
			System.err.println("zhangsan task:" + task.getProcessInstanceId() + task.getName());
			taskService.complete(task.getId());
			System.err.println("zhangsan 完成任务：" + task.getId());
		}
		
		List<Task> lisi = taskQuery.taskAssignee("lisi").list();
		for (Task task : lisi) {
			System.err.println("lisi task:" + task.getProcessInstanceId() + task.getName());
			taskService.complete(task.getId());
			System.err.println("lisi 完成任务：" + task.getId());
		}
	}
	

	
}
