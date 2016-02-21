/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 
package quartz.examples.example9;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wkratzer
 */
public class Job1Listener implements JobListener {

  private static Logger _log = LoggerFactory.getLogger(Job1Listener.class);

  public String getName() {
    return "job1_to_job2";
  }

  public void jobToBeExecuted(JobExecutionContext inContext) {
    _log.info("Job1Listener jobToBeExecuted job触发之前");
  }

  public void jobExecutionVetoed(JobExecutionContext inContext) {
    _log.info("Job1Listener jobExecutionVetoed 错过触发job");
  }

  public void jobWasExecuted(JobExecutionContext inContext, JobExecutionException inException) {
    _log.info("Job1Listener jobWasExecuted job执行之后");

    // Simple job #2
//    JobDetail job2 = newJob(SimpleJob2.class).withIdentity("job2").build();

    // Trigger trigger = newTrigger().withIdentity("job2Trigger").startNow().build();

  /*  try {
      // schedule the job to run!
      inContext.getScheduler().scheduleJob(job2, trigger);
    } catch (SchedulerException e) {
      _log.warn("Unable to schedule job2!");
      e.printStackTrace();
    }*/

  }

}
