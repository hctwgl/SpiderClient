/**
 * NetXMS - open source network management system
 * Copyright (C) 2003-2009 Victor Kirhenshtein
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.netxms.ui.eclipse.spidermanager.dialogs;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Combo;
import org.spider.base.SpiderCodes;
import org.spider.client.GoogleAccountObject;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.ResourceManager;

/**
 * User database object creation dialog
 * 
 */
public class EditGoogleAccoutDialog extends Dialog {
	GoogleAccountObject object;
	private Text txtUserName;
	private Text txtClientSecret;
	private Text txtClientId;
	private Text txtAppName;
	private Text txtApiKey;
	Combo cbAccountType;
	Button cbShowPass;

	private String userName;
	private String clientSecret;
	private String clientId;
	private String appName;
	private String apiKey;
	private int accountType;

	public EditGoogleAccoutDialog(Shell parentShell, GoogleAccountObject object) {
		super(parentShell);
		this.object = object;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);

		GridData gridData;
		dialogArea.setLayout(null);
		gridData = new GridData(GridData.VERTICAL_ALIGN_END);
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 4;
		gridData = new GridData(GridData.VERTICAL_ALIGN_END);
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;

		Group grpCreateNewAccount = new Group(dialogArea, SWT.NONE);
		grpCreateNewAccount.setText("Create new account");
		grpCreateNewAccount.setBounds(5, 10, 429, 236);

		Label lblUsername = new Label(grpCreateNewAccount, SWT.NONE);
		lblUsername.setAlignment(SWT.RIGHT);
		lblUsername.setText("User Name");
		lblUsername.setBounds(10, 31, 95, 17);

		txtUserName = new Text(grpCreateNewAccount, SWT.BORDER | SWT.READ_ONLY);
		txtUserName.setTextLimit(150);
		txtUserName.setBounds(111, 26, 310, 27);

		Label label_3 = new Label(grpCreateNewAccount, SWT.NONE);
		label_3.setAlignment(SWT.RIGHT);
		label_3.setText("API Key");
		label_3.setBounds(10, 64, 95, 17);

		txtApiKey = new Text(grpCreateNewAccount, SWT.BORDER | SWT.PASSWORD);
		txtApiKey.setTextLimit(145);
		txtApiKey.setBounds(111, 59, 310, 27);

		Label lblAccountType = new Label(grpCreateNewAccount, SWT.NONE);
		lblAccountType.setAlignment(SWT.RIGHT);
		lblAccountType.setText("Account Type");
		lblAccountType.setBounds(10, 201, 95, 17);

		cbAccountType = new Combo(grpCreateNewAccount, SWT.NONE);
		cbAccountType.setItems(new String[] {"Helper", "SEO", "Adsend"});
		cbAccountType.setBounds(111, 196, 310, 29);
		cbAccountType.select(0);

		Label lblClientId = new Label(grpCreateNewAccount, SWT.NONE);
		lblClientId.setBounds(10, 97, 95, 17);
		lblClientId.setText("Client Id");
		lblClientId.setAlignment(SWT.RIGHT);

		txtClientId = new Text(grpCreateNewAccount, SWT.BORDER | SWT.PASSWORD);
		txtClientId.setBounds(111, 92, 310, 27);
		txtClientId.setTextLimit(145);

		Label label_1 = new Label(grpCreateNewAccount, SWT.NONE);
		label_1.setBounds(10, 130, 95, 17);
		label_1.setAlignment(SWT.RIGHT);
		label_1.setText("Client Secret");

		txtClientSecret = new Text(grpCreateNewAccount, SWT.BORDER | SWT.PASSWORD);
		txtClientSecret.setBounds(111, 125, 310, 27);
		txtClientSecret.setTextLimit(145);

		Label label_2 = new Label(grpCreateNewAccount, SWT.NONE);
		label_2.setBounds(10, 163, 95, 17);
		label_2.setAlignment(SWT.RIGHT);
		label_2.setText("App Name");

		txtAppName = new Text(grpCreateNewAccount, SWT.BORDER);
		txtAppName.setBounds(111, 158, 310, 27);
		txtAppName.setTextLimit(40);

		cbShowPass = new Button(dialogArea, SWT.CHECK);
		cbShowPass.setImage(ResourceManager.getPluginImage("org.spider.ui.eclipse.spidermanager", "icons/eye_16x16.png"));
		cbShowPass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(cbShowPass.getSelection())
				{
					txtApiKey.setEchoChar((char)0);
					txtClientId.setEchoChar((char)0);
					txtClientSecret.setEchoChar((char)0);
					
				}else{
					txtApiKey.setEchoChar('*');
					txtClientId.setEchoChar('*');
					txtClientSecret.setEchoChar('*');
				}
			}
		});
		cbShowPass.setBounds(440, 10, 26, 24);
		initData();
		return dialogArea;
	}

	private void initData()
	{
		txtUserName.setText(object.getUserName());
		txtClientSecret.setText(object.getClientSecret());
		txtClientId.setText(object.getClientId());
		txtApiKey.setText(object.getApi());
		txtAppName.setText(object.getAppName());
		switch (object.getAccountType()) {
		case SpiderCodes.ACCOUNT_HELPER:
			cbAccountType.setText("Helper");
			break;
		case SpiderCodes.ACCOUNT_SEO:
			cbAccountType.setText("SEO");
			break;
		case SpiderCodes.ACCOUNT_ADSEND:
			cbAccountType.setText("Adsend");
			break;
		default:
			break;
		}
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Edit google account");
	}

	@Override
	protected void okPressed() {
		userName = txtUserName.getText();
		clientSecret = txtClientSecret.getText();
		clientId = txtClientId.getText();
		appName = txtAppName.getText();
		apiKey = txtApiKey.getText();

		if(userName == null || userName.isEmpty())
		{
			MessageBox dialog =
					new MessageBox(getShell(), SWT.ERROR | SWT.OK);
			dialog.setText("Error");
			dialog.setMessage("User name must not empty!");
			dialog.open();
			return;	
		}
		if(cbAccountType.getText().equals("Helper"))
		{
			accountType = SpiderCodes.ACCOUNT_HELPER;
		}else if(cbAccountType.getText().equals("SEO")){
			accountType = SpiderCodes.ACCOUNT_SEO;
		}else{
			accountType = SpiderCodes.ACCOUNT_ADSEND;
		}
		super.okPressed();
	}

	public String getUserName() {
		return userName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getAppName() {
		return appName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public int getAccountType() {
		return accountType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
