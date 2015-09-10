package com.treenewbee.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BaseView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.BaseView"; //$NON-NLS-1$

	public BaseView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		container.setBackgroundImage(SWTResourceManager.getImage(BaseView.class, "/com/shxt/supersystem/picture/1374565556177_\u526F\u672C.jpg"));
		container.setBackground(SWTResourceManager.getColor(230, 230, 250));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setImage(SWTResourceManager.getImage(BaseView.class, "/com/shxt/supersystem/picture/24_\u526F\u672C.png"));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				
				try {
					page.showView(GoodInfoView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
				page.hideView(page.findView(BaseView.ID));
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 18, SWT.NORMAL));
		btnNewButton.setBounds(234, 219, 214, 63);
		btnNewButton.setText("\u5546\u54C1\u57FA\u672C\u8D44\u6599");
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setImage(SWTResourceManager.getImage(BaseView.class, "/com/shxt/supersystem/picture/\u56FE\u72473_\u526F\u672C.png"));
		label_5.setBounds(480, 218, 64, 64);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_2.setBounds(550, 239, 84, 32);
		label_2.setText("\u6DD8\u6C70\u5546\u54C1");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(BaseView.class, "/com/shxt/supersystem/picture/\u56FE\u72473_\u526F\u672C2.png"));
		label_4.setBounds(321, 154, 64, 64);
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setImage(SWTResourceManager.getImage(BaseView.class, "/com/shxt/supersystem/picture/\u56FE\u72473_\u526F\u672C1.png"));
		label_6.setBounds(164, 219, 64, 64);
		
		Label label_7 = new Label(container, SWT.NONE);
		label_7.setImage(SWTResourceManager.getImage(BaseView.class, "/com/shxt/supersystem/picture/\u56FE\u72473_\u526F\u672C3.png"));
		label_7.setBounds(321, 288, 64, 64);
		
		Label label_8 = new Label(container, SWT.NONE);
		label_8.setText("\u6DFB\u52A0\u5546\u54C1");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_8.setBounds(310, 116, 84, 32);
		
		Label label_9 = new Label(container, SWT.NONE);
		label_9.setText("\u4FEE\u6539\u8D44\u6599");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_9.setBounds(69, 239, 84, 32);
		
		Label label_10 = new Label(container, SWT.NONE);
		label_10.setText("\u4EA7\u54C1\u5206\u7C7B");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		label_10.setBounds(310, 364, 84, 32);

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
