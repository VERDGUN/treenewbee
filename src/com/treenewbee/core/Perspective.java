package com.treenewbee.core;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.treenewbee.shell.CashierShell;
import com.treenewbee.shell.ManagerShell;
import com.treenewbee.view.BaseView;
import com.treenewbee.view.CashierView;
import com.treenewbee.view.ManagerInfoView;
import com.treenewbee.view.ManagerView;
import com.treenewbee.view.WelcomeView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setFixed(true);
		layout.setEditorAreaVisible(false);
		if(ManagerShell.openSuccess) {
			
			layout.addStandaloneView(ManagerInfoView.ID, false, IPageLayout.BOTTOM, 0.96f, layout.getEditorArea());
			layout.addStandaloneView(ManagerView.ID, false, IPageLayout.LEFT, 0.25f, layout.getEditorArea());
			
			IFolderLayout folder = layout.createFolder("folder", IPageLayout.LEFT, 0.25f,layout.getEditorArea());
			folder.addPlaceholder("com.treenewbee.view.*");
			folder.addView(WelcomeView.ID);
			
		}
		if(CashierShell.openSuccess) {
			layout.addStandaloneView(CashierView.ID, false, layout.TOP, 1f, layout.getEditorArea());
		}
	}
}
