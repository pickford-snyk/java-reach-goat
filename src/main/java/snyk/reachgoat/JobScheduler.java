package io.snyk.reachgoat;

import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import org.quartz.Scheduler;
import org.quartz.Job;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.jobs.ee.mail.SendMailJob;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;

public class JobScheduler {
  class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
      System.out.println("Hello Job");
    }
  }

  private Executor executor;
  private int numJobs;

  public JobScheduler() {
    executor = Executors.newFixedThreadPool(2);
    numJobs = 10;
  }

  public Scheduler scheduleJobs() throws SchedulerException {
    Scheduler scheduler = createScheduler();
    for (int i = 0; i < numJobs; ++i) {
      Trigger trigger = newTrigger()
        .withIdentity("trigger", "group")
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(40)
            .repeatForever())
        .build();
      JobDetail job = newJob(HelloJob.class)
             .withIdentity(String.format("job %d", i))
             .build();
      scheduler.scheduleJob(job, trigger);
    }
    return scheduler;
  }

  private Scheduler createScheduler() throws SchedulerException {
    try {
      SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
      schedulerFactoryBean.setSchedulerName("scheduler");
      schedulerFactoryBean.setTaskExecutor(executor);
      schedulerFactoryBean.afterPropertiesSet(); 
      return schedulerFactoryBean.getObject();
    } catch (Exception e) {
      throw new SchedulerException("Failed to create Scheduler", e);
    }
  }
}
