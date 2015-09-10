package com.treenewbee.view;

import java.util.Date;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import com.treenewbee.shell.ManagerShell;

import org.eclipse.swt.widgets.Button;

public class ManagerInfoView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.ManagerInfoView"; //$NON-NLS-1$
	private Label lblTime;
	private Composite composite;

	public ManagerInfoView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(ManagerInfoView.class, "/com/shxt/supersystem/picture/\u84DD\u8272_\u526F\u672C.jpg"));
		container.setBackground(SWTResourceManager.getColor(135, 206, 235));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		composite = new Composite(container, SWT.BORDER);
		composite.setBounds(10, 2, 313, 30);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(10, 2, 184, 24);
		label.setText("\u7248\u6743\u5F52\u5C5E\uFF1A\u9EA6\u515C\u515C\u8F6F\u4EF6\u516C\u53F8");
		
		Composite composite_1 = new Composite(container, SWT.BORDER);
		composite_1.setBounds(329, 2, 233, 30);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("\u7BA1\u7406\u5458\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(10, 2, 60, 26);
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(76, 2, 77, 26);
		label_2.setText(ManagerShell.ID);
		
		Composite composite_2 = new Composite(container, SWT.BORDER);
		composite_2.setBounds(568, 2, 320, 30);
		
		lblTime = new Label(composite_2, SWT.NONE);
		lblTime.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblTime.setBounds(10, 2, 209, 26);
		
		Composite composite_3 = new Composite(container, SWT.BORDER);
		composite_3.setBounds(894, 2, 341, 30);
		
		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setText("\u6309\uFF1F\u6309\u94AE\u67E5\u770B\u64CD\u4F5C\u5E2E\u52A9\u6587\u6863");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(10, 2, 193, 26);
		
		Button button = new Button(container, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(ManagerInfoView.class, "/com/shxt/supersystem/picture/3_\u526F\u672C.png"));
		button.setBounds(1250, 1, 42, 30);
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setImage(SWTResourceManager.getImage(ManagerInfoView.class, "/com/shxt/supersystem/picture/transparenc_ystal_016_\u526F\u672C.png"));
		button_1.setBounds(1292, 0, 58, 32);
		
		Thread thread = new Thread() {
			public void run() {
				while (true) {
					composite.getDisplay();
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							lblTime.setText(new Date().toLocaleString());
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();


		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
