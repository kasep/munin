package org.munin.plugin.jmx;

import org.munin.plugin.jmx.AbstractAnnotationGraphsProvider.Graph;

@Graph(title = "MemorythresholdUsageCount", vlabel = "count", info = "Returns the number of times that the memory usage has crossed the usage threshold.")
public class MemorythresholdUsageCount extends AbstractAnnotationGraphsProvider {

	private String[] gcValues;

	@Override
	public void prepareValues() throws Exception {
		GetUsageThresholdCount collector = new GetUsageThresholdCount(
				connection);
		gcValues = collector.GC();
	}

	@Field(info = "UsageThresholdCount for Tenured Gen", position = 1)
	public String tenuredGen() {
		return gcValues[0];
	}

	@Field(info = "UsageThresholdCount for Perm Gen", position = 2)
	public String permGen() {
		return gcValues[1];
	}

	public static void main(String args[]) {
		runGraph(new MemorythresholdUsageCount(), args);
	}
}
