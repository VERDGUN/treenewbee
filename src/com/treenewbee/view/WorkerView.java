package com.treenewbee.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Link;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WorkerView extends ViewPart {

	public static final String ID = "com.shxt.supersystem.view.WorkerView"; //$NON-NLS-1$

	public WorkerView() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/1374565556177_\u526F\u672C.jpg"));
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		
		Link link = new Link(container, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				
				try {
					page.showView(PowerView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
				page.hideView(page.findView(WorkerView.ID));
			}
			
		});
		link.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		link.setBounds(89, 124, 171, 45);
		link.setText("<a>\u7528\u6237\u6743\u9650\u8BBE\u7F6E</a>");
		
		Link link_1 = new Link(container, 0);
		link_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchPage page = getSite().getPage();
				
				try {
					page.showView(WorkerInfoView.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
				page.hideView(page.findView(WorkerView.ID));
			}
		});
		link_1.setText("<a>\u5458 \u5DE5 \u8D44 \u6599</a>");
		link_1.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.NORMAL));
		link_1.setBounds(89, 340, 145, 45);
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(266, 366, 80, 2);
		
		Label label_1 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(266, 142, 80, 2);
		
		Label label_2 = new Label(container, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(344, 102, 2, 84);
		
		Label label_3 = new Label(container, SWT.SEPARATOR);
		label_3.setBounds(344, 309, 2, 114);
		
		Label label_4 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(344, 102, 64, 2);
		
		Label label_5 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setBounds(344, 184, 64, 2);
		
		Label label_6 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_6.setBounds(344, 309, 64, 2);
		
		Label label_7 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_7.setBounds(344, 366, 64, 2);
		
		Label label_8 = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_8.setBounds(344, 421, 64, 2);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		lblNewLabel.setBounds(414, 90, 71, 30);
		lblNewLabel.setText("\u7BA1\u7406\u5458");
		
		Label label_9 = new Label(container, SWT.NONE);
		label_9.setText("\u6536\u94F6\u5458");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_9.setBounds(414, 164, 71, 30);
		
		Label label_10 = new Label(container, SWT.NONE);
		label_10.setText("\u589E\u52A0\u5458\u5DE5");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_10.setBounds(414, 297, 105, 30);
		
		Label label_11 = new Label(container, SWT.NONE);
		label_11.setText("\u4FEE\u6539\u8D44\u6599");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_11.setBounds(414, 355, 90, 30);
		
		Label label_12 = new Label(container, SWT.NONE);
		label_12.setText("\u5220\u9664\u5458\u5DE5");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 16, SWT.NORMAL));
		label_12.setBounds(414, 408, 90, 30);
		
		Label label_13 = new Label(container, SWT.NONE);
		label_13.setImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/VISTA\u6C34\u6676\u56FE\u6807\u4E0B\u8F7D10_\u526F\u672C.png"));
		label_13.setBounds(491, 77, 64, 54);
		
		Label label_14 = new Label(container, SWT.NONE);
		label_14.setImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/VISTA\u6C34\u6676\u56FE\u6807\u4E0B\u8F7D10_\u526F\u672C.png"));
		label_14.setBounds(491, 152, 64, 54);
		
		Label label_15 = new Label(container, SWT.NONE);
		label_15.setImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/VISTA\u6C34\u6676\u56FE\u6807\u4E0B\u8F7D10_\u526F\u672C.png"));
		label_15.setBounds(522, 284, 64, 54);
		
		Label label_16 = new Label(container, SWT.NONE);
		label_16.setImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/VISTA\u6C34\u6676\u56FE\u6807\u4E0B\u8F7D10_\u526F\u672C.png"));
		label_16.setBounds(522, 340, 64, 54);
		
		Label label_17 = new Label(container, SWT.NONE);
		label_17.setImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/VISTA\u6C34\u6676\u56FE\u6807\u4E0B\u8F7D10_\u526F\u672C.png"));
		label_17.setBounds(522, 396, 64, 54);
		
		Label label_18 = new Label(container, SWT.NONE);
		label_18.setImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/VISTA\u6C34\u6676\u56FE\u6807\u4E0B\u8F7D10_\u526F\u672C.png"));
		label_18.setBounds(21, 115, 64, 54);
		
		Label label_19 = new Label(container, SWT.NONE);
		label_19.setImage(SWTResourceManager.getImage(WorkerView.class, "/com/shxt/supersystem/picture/VISTA\u6C34\u6676\u56FE\u6807\u4E0B\u8F7D10_\u526F\u672C.png"));
		label_19.setBounds(21, 331, 64, 54);

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
