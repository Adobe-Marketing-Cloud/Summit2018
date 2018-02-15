package adobesummit.core.hc;

import org.apache.sling.hc.api.HealthCheck;
import org.apache.sling.hc.api.Result;
import org.apache.sling.hc.util.FormattingResultLog;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import adobesummit.core.service.Version;

@Component(service = HealthCheck.class,
property = { HealthCheck.NAME + "=HCExample",
		     HealthCheck.TAGS + "=hcexample",
		     HealthCheck.MBEAN_NAME + "=hcexample" })
public class HealthCheckExample implements HealthCheck {

	// use this url to execute it in the console
	// /system/console/healthcheck?tags=hcexample&debug=true&overrideGlobalTimeout=

	// you can also see the healthcheck in the operations dashbaord
	// /libs/granite/operations/content/healthreports/healthreportlist.html

	@Reference
	private Version mySimpleService;

	@Override
	public Result execute() {
		final FormattingResultLog resultLog = new FormattingResultLog();

		resultLog.debug("Starting with the healthcheck");
		if (mySimpleService != null) {
			resultLog.info("MySimpleService is available {}", mySimpleService);
			if (mySimpleService.getVersionInfo() != null) {
				resultLog.info("Version info is retrieved {}", mySimpleService.getVersionInfo());
			}
		} else {
			resultLog.critical("Not able to inject MySimpleService");
		}

		return new Result(resultLog);
	}
}
