package com.treenewbee.dialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import com.treenewbee.util.JdbcTool;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GoodImportDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private String goodID;
	private String goodName;
	private Text text_4;
	private Text text_5;
	private List<List<String>> list = new ArrayList<List<String>>();

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public GoodImportDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open(String goodID, String goodName) {
		this.goodID = goodID;
		this.goodName = goodName;
		
		JdbcTool jt = new JdbcTool();
		String sql = "SELECT good_provider, good_providertel FROM good WHERE good_id = '" + goodID + "'";
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
		shell.setSize(391, 308);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(62, 10, 75, 20);
		label.setText("\u5546\u54C1\u7F16\u7801\uFF1A");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(62, 42, 75, 20);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u8FDB\u8D27\u6570\u91CF\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(62, 152, 75, 20);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("\u8FC7\u671F\u65E5\u671F\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(62, 192, 75, 20);
		
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(143, 7, 141, 23);
		text.setText(goodID);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_1.setBounds(143, 42, 141, 23);
		text_1.setText(goodName);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_2.setBounds(143, 149, 118, 23);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_3.setBounds(143, 189, 118, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Date now = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = sdf.format(now);
				
				JdbcTool jt = new JdbcTool();
				String sql = "INSERT INTO import_good (import_date, good_id, import_num, shelve_num, storage_num, good_out_date) VALUES ('" + nowtime + "', '" + goodID + "', '" + text_2.getText() + "', 0, '" + text_2.getText() + "', '" + text_3.getText() + "')";
				jt.update(sql);
				
				sql = "UPDATE good SET good_provider = '"+ text_4.getText() +"',good_providertel = '" + text_5.getText() + "' WHERE good_id = '" + goodID + "'";
				jt.update(sql);
				
				shell.close();
			}
		});
		btnNewButton.setBounds(115, 232, 130, 36);
		btnNewButton.setText("\u786E\u5B9A");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("(\u683C\u5F0F:2013-01-01)");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(267, 193, 118, 23);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("\u4F9B\u5E94\u5546\u5BB6\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(62, 80, 75, 20);
		
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(62, 114, 75, 20);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_4.setBounds(143, 77, 141, 23);
		text_4.setText(list.get(0).get(0));
		
		text_5 = new Text(shell, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text_5.setBounds(143, 114, 118, 23);
		text_5.setText(list.get(0).get(1));

	}
}