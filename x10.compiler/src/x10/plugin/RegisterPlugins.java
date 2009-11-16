/*
 *
 * (C) Copyright IBM Corporation 2006-2008.
 *
 *  This file is part of X10 Language.
 *
 */

package x10.plugin;

import polyglot.frontend.Job;
import polyglot.frontend.SourceGoal_c;
import x10.ExtensionInfo;

public class RegisterPlugins extends SourceGoal_c {

	protected ExtensionInfo extInfo;
	
	public RegisterPlugins(Job job) {
		super("RegisterPlugins", job);
		this.extInfo = (ExtensionInfo) job.extensionInfo();
	}

	public boolean runTask() {
		for (CompilerPlugin plugin : extInfo.plugins().values()) {
			plugin.register(extInfo, job());
		}

		return true;
	}
}
