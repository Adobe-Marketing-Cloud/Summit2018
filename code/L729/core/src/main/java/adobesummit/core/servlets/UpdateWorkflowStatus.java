package adobesummit.core.servlets;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = { "process.label=" + "Update assets with status", })
public class UpdateWorkflowStatus implements WorkflowProcess {

	private static final Logger log = LoggerFactory.getLogger(UpdateWorkflowStatus.class);

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	@Override
	public final void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args)
			throws WorkflowException {

		/* Get the Workflow Payload */

		// Get the Workflow data (the data that is being passed through for this work
		// item)

		final WorkflowData workflowData = workItem.getWorkflowData();
		final String type = workflowData.getPayloadType();

		// Check if the payload is a path in the JCR; The other (less common) type is
		// JCR_UUID
		if (!"JCR_PATH".equalsIgnoreCase(type)) {
			return;
		}
		// Get the path to the JCR resource from the payload
		final String path = workflowData.getPayload().toString();

		log.info("Path {}", path);

		/* Get Workflow Process Arguments */

		// These args are specified on the Workflow Model using this WF Process.

		/* Process Args */
		// These are free-form textfields; the values of these may need to be parsed
		// based on
		// expected in put formats
		String processArgs = args.get("PROCESS_ARGS", "default value");
		log.info("Process args {}", processArgs);
		String[] proccesArgsVals = StringUtils.split(processArgs, ",");

		log.info("Process args valid? " + (proccesArgsVals != null && proccesArgsVals.length == 2));

		ResourceResolver rr = null;

		try {

			rr = resourceResolverFactory.getAdministrativeResourceResolver(null);
			Resource folder = rr.getResource(path);

			if (folder == null) {
				log.info("folder == null");
			} else {
				log.info("folder != null {}", folder.getPath());
			}

			if (folder != null && proccesArgsVals != null && proccesArgsVals.length == 2) {
				log.info("In info....");

				Iterator<Resource> it = folder.listChildren();

				if (!it.hasNext()) {
					log.info("No children");
				}

				while (it.hasNext()) {
					Resource child = it.next();
					log.info("child {}", child.getPath());
					if (!child.getName().equals("jcr:content") && !child.getName().equals("rep:policy")) {

						ModifiableValueMap mvp = child.getChild("jcr:content").adaptTo(ModifiableValueMap.class);
						mvp.put("status", proccesArgsVals[0]);
						mvp.put("statusColor", proccesArgsVals[1]);
						rr.commit();
					}

				}

			}

		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			if (rr != null && rr.isLive()) {
				rr.close();
			}
		}

	}

}
