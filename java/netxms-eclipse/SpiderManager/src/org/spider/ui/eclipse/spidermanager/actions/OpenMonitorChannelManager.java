package org.spider.ui.eclipse.spidermanager.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.spider.ui.eclipse.spidermanager.Messages;
import org.spider.ui.eclipse.spidermanager.views.MonitorChannelManagerView;
import org.netxms.ui.eclipse.tools.MessageDialogHelper;

public class OpenMonitorChannelManager implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	public void run(IAction action) {
		// TODO Auto-generated method stub
		if (window != null) {
			try {
				window.getActivePage().showView(MonitorChannelManagerView.ID,
						"MonitorChannel", IWorkbenchPage.VIEW_ACTIVATE); //$NON-NLS-1$
			} catch (PartInitException e) {
				MessageDialogHelper.openError(
						window.getShell(),
						Messages.get().OpenUserManager_Error,
						Messages.get().OpenUserManager_ErrorText
								+ e.getMessage());
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}
}
