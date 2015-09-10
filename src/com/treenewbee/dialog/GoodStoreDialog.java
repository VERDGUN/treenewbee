package com.treenewbee.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GoodStoreDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private String goodID;
	private List<List<String>> list = new ArrayList<List<String>>();
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GoodStoreDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String goodID) {
		this.goodID = goodID;
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT i.good_id,g.good_name,SUM(i.storage_num) FROM import_good i JOIN good g ON g.good_id = i.good_id GROUP BY i.good_id HAVING i.good_id = '" + goodID + "'";
		list = jt.query(sql);
		
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 190);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(43, 10, 82, 27);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(43, 43, 82, 27);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u5E93\u5B58\u603B\u91CF\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(43, 76, 82, 27);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(123, 125, 134, 27);
		btnNewButton.setText("\u786E\u5B9A");
		
		Label label_3 = new Label(shell, SWT.BORDER);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(131, 10, 153, 27);
		label_3.setText(list.get(0).get(0));
		
		Label label_4 = new Label(shell, SWT.BORDER);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(131, 43, 153, 27);
		label_4.setText(list.get(0).get(1));
		
		Label label_5 = new Label(shell, SWT.BORDER);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(131, 76, 153, 27);
		label_5.setText(list.get(0).get(2));

	}

}
