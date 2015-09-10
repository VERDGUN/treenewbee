package com.treenewbee.dialog;

import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CheckImportDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CheckImportDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(444, 215);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(72, 20, 82, 27);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(72, 63, 82, 27);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u5E93\u5B58\u603B\u91CF\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(72, 106, 82, 27);
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button.setText("\u786E\u5B9A");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(157, 150, 156, 27);
		
		text = new Text(shell, SWT.BORDER);
		text.setFocus();
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 13) {
					JdbcTool jt = new JdbcTool();
					
					String sql = "SELECT good_name FROM good WHERE good_id = '" + text.getText() + "'";
					List<List<String>> list = jt.query(sql);
					if(list.size() == 0) {
						MessageBox box = new MessageBox(CheckImportDialog.this.shell);
						box.setMessage("无此商品，请重新输入！");
						box.open();
						return;
					}
					text_1.setText(list.get(0).get(0));
					
					sql = "SELECT SUM(storage_num) FROM import_good where good_id = '" + text.getText() + "'";
					list = jt.query(sql);
					text_2.setText(list.get(0).get(0));
				}
				
				
				
			}
		});
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(160, 17, 153, 30);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(160, 60, 153, 30);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setBounds(160, 103, 153, 30);

	}

}
