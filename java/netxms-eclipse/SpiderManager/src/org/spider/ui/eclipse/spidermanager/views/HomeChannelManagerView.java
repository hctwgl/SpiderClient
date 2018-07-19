package org.spider.ui.eclipse.spidermanager.views;

import java.io.IOException;

import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.action.*;
import org.eclipse.ui.*;
import org.eclipse.swt.SWT;
import org.netxms.client.NXCException;
import org.netxms.client.SessionListener;
import org.netxms.client.SessionNotification;
import org.netxms.ui.eclipse.jobs.ConsoleJob;
import org.netxms.ui.eclipse.logviewer.views.LogViewer;
import org.netxms.ui.eclipse.spidermanager.dialogs.CreateHomeChannelDialog;
import org.netxms.ui.eclipse.spidermanager.dialogs.EditHomeChannelDialog;
import org.spider.client.HomeChannelObject;
import org.spider.ui.eclipse.spidermanager.Activator;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class HomeChannelManagerView extends LogViewer {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.spider.ui.eclipse.spidermanager.views.HomeChannelManager";

	private Action actAddHomeChannel;
	private Action actEditHomeChannel;
	private Action actDeleteHomeChannel;
	private Action actViewHomeChanne;
	private Action actReloadChannel;
	private SessionListener sessionListener;

	public static final int COLUMN_CHANNEL_ID 		= 0;
	public static final int COLUMN_CHANNEL_NAME 	= 1;
	public static final int COLUMN_GOOGLE_ACCOUNT 	= 2;
	public static final int COLUMN_VIDEO_NUMBER 	= 3;
	public static final int COLUMN_VIEW_NUMBER 		= 4;
	public static final int COLUMN_SUBCRIBER	 	= 5;
	public static final int COLUMN_DATE_CREATED	 	= 6;
	public static final int COLUMN_STATUS		 	= 7;
	public static final int COLUMN_NOTE			 	= 8;

	public HomeChannelManagerView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		super.createPartControl(parent);
		// Listener for server's notifications
		sessionListener = new SessionListener() {
			@Override
			public void notificationHandler(final SessionNotification n) {
				if (n.getCode() == SessionNotification.HOME_CHANNEL_CHANGED) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							doQuery();
						}
					});
				}
			}
		};
		// Request server to lock user database, and on success refresh view
		new ConsoleJob("", this,
				Activator.PLUGIN_ID, null) {
			@Override
			protected void runInternal(IProgressMonitor monitor)
					throws Exception {
				runInUIThread(new Runnable() {
					@Override
					public void run() {
						session.addListener(sessionListener);
					}
				});
			}

			@Override
			protected void jobFailureHandler() {
				runInUIThread(new Runnable() {
					@Override
					public void run() {
						HomeChannelManagerView.this.getViewSite().getPage()
						.hideView(HomeChannelManagerView.this);
					}
				});
			}

			@Override
			protected String getErrorMessage() {
				return "Open home channel error!";
			}
		}.start();
	}

	@Override
	protected void fillLocalPullDown(IMenuManager manager) {
		super.fillLocalPullDown(manager);
		manager.add(actAddHomeChannel);
		manager.add(new Separator());
		manager.add(actEditHomeChannel);
		manager.add(new Separator());
		manager.add(actDeleteHomeChannel);
	}
	@Override
	protected void fillContextMenu(IMenuManager manager) {
		super.fillContextMenu(manager);
		manager.add(actAddHomeChannel);
		manager.add(actEditHomeChannel);
		manager.add(actDeleteHomeChannel);
		manager.add(new Separator());
		manager.add(actReloadChannel);
		manager.add(actViewHomeChanne);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	@Override
	protected void fillLocalToolBar(IToolBarManager manager) {
		super.fillLocalToolBar(manager);
		manager.add(actReloadChannel);
		manager.add(actAddHomeChannel);
		manager.add(actEditHomeChannel);
		manager.add(actDeleteHomeChannel);
	}


	@Override
	protected void createActions() {
		// TODO Auto-generated method stub
		super.createActions();
		actAddHomeChannel = new Action("Add new home channel", 
				Activator.getImageDescriptor("icons/account_add.png")) {
			public void run() {
				addHomeChannel();
			}
		};
		actAddHomeChannel.setToolTipText("Add new home channel");

		actEditHomeChannel = new Action("Edit home channel", 
				Activator.getImageDescriptor("icons/account_edit.png")) {
			public void run() {
				modifyHomeChannel();
			}
		};
		actEditHomeChannel.setToolTipText("Edit home channel");

		actDeleteHomeChannel = new Action("Delete home channel", 
				Activator.getImageDescriptor("icons/account_delete.png")) {
			public void run() {
				try {
					deleteHomeChannel();
				} catch (IOException | NXCException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		actDeleteHomeChannel.setToolTipText("Delete home channel");

		actViewHomeChanne = new Action("View home channel", 
				Activator.getImageDescriptor("icons/eye_16x16.png")) {
			public void run() {
				browseHomeChannel();
			}
		};
		actViewHomeChanne.setToolTipText("View home channel");
		
		actReloadChannel = new Action("Reload channel info", 
				Activator.getImageDescriptor("icons/eye_16x16.png")) {
			public void run() {
				
			}
		};
		actReloadChannel.setToolTipText("Reload channel info");
	}


	private void addHomeChannel()
	{
		final CreateHomeChannelDialog dlg = new CreateHomeChannelDialog(getViewSite().getShell());
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Create home channel",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.createHomeCHannel(dlg.getcId(), dlg.getGoogleUser());
				}

				@Override
				protected String getErrorMessage() {
					return "Can not create home channel";
				}
			}.start();
		}
	}

	private void modifyHomeChannel()
	{
		final TableItem[] selection = viewer.getTable().getSelection();
		if(selection.length <= 0)
		{
			MessageBox dialog =
					new MessageBox(getViewSite().getShell(), SWT.ICON_WARNING | SWT.OK);
			dialog.setText("Warning");
			dialog.setMessage("You must select at least one item to delete!");
			dialog.open();
			return;
		}
		HomeChannelObject selectedObj = new HomeChannelObject(
				selection[0].getText(COLUMN_CHANNEL_ID),
				selection[0].getText(COLUMN_CHANNEL_NAME),
				selection[0].getText(COLUMN_GOOGLE_ACCOUNT),
				Integer.parseInt(selection[0].getText(COLUMN_VIDEO_NUMBER)),
				Integer.parseInt(selection[0].getText(COLUMN_VIEW_NUMBER)),
				Integer.parseInt(selection[0].getText(COLUMN_SUBCRIBER)),
				Long.parseLong(selection[0].getText(COLUMN_DATE_CREATED)),
				Integer.parseInt(selection[0].getText(COLUMN_STATUS)),
				selection[0].getText(COLUMN_NOTE)
				);

		final EditHomeChannelDialog dlg = new EditHomeChannelDialog(getViewSite().getShell(), selectedObj);
		if (dlg.open() == Window.OK) {
			new ConsoleJob("Edit home channel",
					this, Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					session.modifyHomeCHannel(dlg.getcId(), dlg.getGoogleUser());
				}

				@Override
				protected String getErrorMessage() {
					return "Can not edit home channel";
				}
			}.start();
		}
	}

	private void deleteHomeChannel() throws IOException, NXCException
	{
		final IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if(selection.size() <= 0)
		{
			MessageBox dialog =
					new MessageBox(getViewSite().getShell(), SWT.ICON_WARNING | SWT.OK);
			dialog.setText("Warning");
			dialog.setMessage("You must select at least one item to delete!");
			dialog.open();
			return;
		}

		MessageBox dialog =
				new MessageBox(getViewSite().getShell(), SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
		dialog.setText("Confirm to delete item");
		dialog.setMessage("Do you really want to do delete this item?");
		if (dialog.open() == SWT.OK) {
			new ConsoleJob("Delete mapping channel", this,
					Activator.PLUGIN_ID, null) {
				@Override
				protected void runInternal(IProgressMonitor monitor)
						throws Exception {
					for (Object object : selection.toList()) {
						String channelId = ((org.netxms.client.TableRow)object).get(COLUMN_CHANNEL_ID).getValue();
						session.deleteHomeChannel(channelId);
						refreshData();
					}
				}

				@Override
				protected String getErrorMessage() {
					return "Can not delete mapping channel";
				}
			}.start();
		}
	}

	private void browseHomeChannel()
	{
		final TableItem[] selection = viewer.getTable().getSelection();
		if(selection.length <= 0)
		{
			MessageBox dialog =
					new MessageBox(getViewSite().getShell(), SWT.ICON_WARNING | SWT.OK);
			dialog.setText("Warning");
			dialog.setMessage("You must select at least one item to delete!");
			dialog.open();
			return;
		}
		String channelId = selection[0].getText(COLUMN_CHANNEL_ID);
		System.out.println("Channel Id = " + channelId);
		Program.launch("https://www.youtube.com/channel/" + channelId);
	}
}