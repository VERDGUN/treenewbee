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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.treenewbee.util.JdbcTool;

public class GoodShelveDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	protected String goodID;
//	private List<List<String>> list = new ArrayList<List<String>>();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GoodShelveDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String goodID) {
		this.goodID = goodID;
		
		
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
		shell.setSize(447, 236);
		shell.setText("\u4E0A\u67B6\u5546\u54C1\u67E5\u770B");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(61, 34, 82, 27);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(61, 75, 82, 27);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u67B6\u4E0A\u603B\u91CF\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(61, 119, 82, 27);
		
		Label label_3 = new Label(shell, SWT.BORDER);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(180, 33, 153, 27);
		label_3.setText(goodID);
		
		Label label_4 = new Label(shell, SWT.BORDER);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(180, 118, 153, 27);
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT SUM(shelve_num) FROM import_good where good_id = '" + goodID + "'";
		List<List<String>> list = jt.query(sql);
		label_4.setText(list.get(0).get(0));
		
		Label label_5 = new Label(shell, SWT.BORDER);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(180, 74, 153, 27);
		sql = "SELECT good_name FROM good WHERE good_id = '" + goodID + "'";
		list = jt.query(sql);
		label_5.setText(list.get(0).get(0));
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button.setText("\u786E\u5B9A");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(138, 171, 134, 27);

	}

}
