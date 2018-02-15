package adobesummit.core.service.impl;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import adobesummit.core.service.Version;


@Component(service=Version.class)
public class VersionImpl implements Version {
	
	private static String VERSION;
	
	@Activate
	public void activate(ComponentContext context) {
		if ( context != null && context.getBundleContext() != null && context.getBundleContext().getBundle() != null) {
			VERSION = context.getBundleContext().getBundle().getVersion().toString();
		}
	}
	
	@Override
	public String getVersionInfo() {
		return VERSION;
	}


}
