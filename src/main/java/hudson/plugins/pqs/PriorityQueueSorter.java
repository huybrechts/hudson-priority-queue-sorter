package hudson.plugins.pqs;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.Queue.BuildableItem;
import hudson.model.Queue.Task;
import hudson.model.queue.QueueSorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Extension
public class PriorityQueueSorter extends QueueSorter implements
		Comparator<BuildableItem> {

	@Override
	public void sortBuildableItems(List<BuildableItem> buildables) {
		Collections.sort(buildables, this);
	}

	public int compare(BuildableItem o1, BuildableItem o2) {
		int p1 = getPriority(o1);
		int p2 = getPriority(o2);
		
		if (p1 < p2) return -1;
		if (p1 == p2) return 0;
		return 1;
	}

	private int getPriority(BuildableItem item) {
		Task task = item.task;
		if (task instanceof AbstractProject<?, ?>) {
			QueuePriorityProperty qpp = ((AbstractProject<?, ?>) task)
					.getProperty(QueuePriorityProperty.class);
			if (qpp != null) {
				return qpp.getPriority();
			}
		}

		return QueuePriorityProperty.getDefaultPriority();
	}

}
