package hudson.plugins.pqs;

import hudson.Extension;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import hudson.model.AbstractProject;
import hudson.model.Job;

import org.kohsuke.stapler.DataBoundConstructor;

public class QueuePriorityProperty extends JobProperty<AbstractProject<?,?>> {

	private final int priority;

	@DataBoundConstructor
	public QueuePriorityProperty(int priority) {
		super();
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
	
	@Extension
	public static class DescriptorImpl extends JobPropertyDescriptor {

		@Override
		public String getDisplayName() {
			return "Job Priority";
		}
		
		@Override
		public boolean isApplicable(Class<? extends Job> jobType) {
			return AbstractProject.class.isAssignableFrom(jobType);
		}
		
	}

	public static int getDefaultPriority() {
		return 3;
	}
	
}
