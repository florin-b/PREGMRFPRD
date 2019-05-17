package tiparire.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import tiparire.enums.EnumTipDocument;
import tiparire.enums.EnumTipTransport;
import tiparire.filtering.TransportFilter;
import tiparire.model.Database;
import tiparire.model.Document;
import tiparire.model.EnumLogonStatus;
import tiparire.model.TipDocumentAfisat;
import tiparire.model.UserInfo;
import tiparire.model.Utils;
import tiparire.sorting.ClientComparator;
import tiparire.sorting.DataComparator;
import tiparire.sorting.DocumentChainedComparator;
import tiparire.sorting.NrMasinaComparator;
import tiparire.sorting.SoferComparator;

public class MainFrame extends JFrame implements LogonListener, DepartamentListener, DataListener, ToolbarListener,
		PrintListener, TipDocumentListener, SelectAllItemsListener {

	private static final long serialVersionUID = 1L;

	private LogonDialog logonDialog;
	private TablePanel tablePanel;
	private Toolbar toolbar;

	private DepartamentDialog departDialog;
	private TipDocumentDialog tipDocDialog;
	CreateDocument doc;
	Database db;

	private EnumTipDocument tipDocument = EnumTipDocument.TRANSFER;

	private JRadioButtonMenuItem rbMenuSortData;

	private JRadioButtonMenuItem rbMenuTransf;
	private JRadioButtonMenuItem rbMenuTranspToate;

	public MainFrame() {
		super("Tiparire documente");
		setLayout(new BorderLayout());

		this.tablePanel = new TablePanel();

		this.toolbar = new Toolbar();

		this.db = new Database(this);
		this.db.setDataListener(this);

		this.doc = new CreateDocument();
		this.doc.setPrintListener(this);

		this.db.setTipDocument(EnumTipDocument.TRANSFER);
		this.departDialog = new DepartamentDialog(this);
		this.departDialog.setListener(this);

		this.tipDocDialog = new TipDocumentDialog(this);
		this.tipDocDialog.setListener(this);

		this.toolbar.setToolbarListener(this);

		add(new JScrollPane(this.tablePanel), "Center");

		add(this.toolbar, "North");

		this.logonDialog = new LogonDialog(this);
		this.logonDialog.setLogonListener(this);
		this.logonDialog.setVisible(true);

		setMinimumSize(new Dimension('?', '?'));
		setSize(900, 600);

		setDefaultCloseOperation(3);
		setLocationRelativeTo(null);

		setVisible(true);
		setJMenuBar(createMenuBar());
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu actionMenu = new JMenu("Actiuni");
		JMenu displayMenu = new JMenu("Afiseaza");
		JMenu tipDocMenu = new JMenu("Tip document");
		JMenu tipDocSort = new JMenu("Ordoneaza");
		JMenu tipTransport = new JMenu("Transport");

		JMenuItem departItem = new JMenuItem("Departament");
		JMenuItem exitItem = new JMenuItem("Iesire");

		rbMenuTransf = new JRadioButtonMenuItem("Transfer");
		rbMenuTransf.setSelected(true);

		JRadioButtonMenuItem rbMenuDistrib = new JRadioButtonMenuItem("Distributie");

		JRadioButtonMenuItem rbMenuToate = new JRadioButtonMenuItem("Toate");

		ButtonGroup group = new ButtonGroup();
		group.add(rbMenuTransf);
		group.add(rbMenuDistrib);
		group.add(rbMenuToate);

		tipDocMenu.add(rbMenuTransf);
		tipDocMenu.add(rbMenuDistrib);
		tipDocMenu.add(rbMenuToate);

		rbMenuSortData = new JRadioButtonMenuItem("Data");
		rbMenuSortData.setSelected(true);

		JRadioButtonMenuItem rbMenuSortSofer = new JRadioButtonMenuItem("Sofer");
		JRadioButtonMenuItem rbMenuSortMasina = new JRadioButtonMenuItem("Masina");
		JRadioButtonMenuItem rbMenuSortClient = new JRadioButtonMenuItem("Client");

		ButtonGroup groupSort = new ButtonGroup();
		groupSort.add(rbMenuSortData);
		groupSort.add(rbMenuSortSofer);
		groupSort.add(rbMenuSortMasina);
		groupSort.add(rbMenuSortClient);

		tipDocSort.add(rbMenuSortData);
		tipDocSort.add(rbMenuSortSofer);
		tipDocSort.add(rbMenuSortMasina);
		tipDocSort.add(rbMenuSortClient);

		rbMenuTranspToate = new JRadioButtonMenuItem("Toate");
		rbMenuTranspToate.setSelected(true);
		JRadioButtonMenuItem rbMenuTranspTrap = new JRadioButtonMenuItem("Arabesque");
		JRadioButtonMenuItem rbMenuTranspTcli = new JRadioButtonMenuItem("Client");
		JRadioButtonMenuItem rbMenuTranspTert = new JRadioButtonMenuItem("Tert");

		ButtonGroup groupFiltru = new ButtonGroup();
		groupFiltru.add(rbMenuTranspToate);
		groupFiltru.add(rbMenuTranspTrap);
		groupFiltru.add(rbMenuTranspTcli);
		groupFiltru.add(rbMenuTranspTert);

		tipTransport.add(rbMenuTranspToate);
		tipTransport.add(rbMenuTranspTrap);
		tipTransport.add(rbMenuTranspTcli);
		tipTransport.add(rbMenuTranspTert);

		actionMenu.add(departItem);
		actionMenu.add(exitItem);
		menuBar.add(actionMenu);
		menuBar.add(displayMenu);
		menuBar.add(tipDocMenu);
		menuBar.add(tipDocSort);
		menuBar.add(tipTransport);

		JMenuItem tipDocItem = new JMenuItem("Documente");
		displayMenu.add(tipDocItem);

		displayMenu.setMnemonic(KeyEvent.VK_F);

		departItem.setMnemonic(KeyEvent.VK_D);
		actionMenu.setMnemonic(KeyEvent.VK_A);
		exitItem.setMnemonic(KeyEvent.VK_X);

		setMenuTransfListener(rbMenuTransf);
		setMenuDistribListener(rbMenuDistrib);
		setMenuToateListener(rbMenuToate);

		setMenuSortData(rbMenuSortData);
		setMenuSortSofer(rbMenuSortSofer);
		setMenuSortMasina(rbMenuSortMasina);
		setMenuSortClient(rbMenuSortClient);

		setMenuFiltruTrap(rbMenuTranspTrap);
		setMenuFiltruTcli(rbMenuTranspTcli);
		setMenuFiltruTert(rbMenuTranspTert);
		setMenuFiltruToate(rbMenuTranspToate);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		departItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				departDialog.setDepartaments();
				departDialog.setFiliale();
				departDialog.setVisible(true);

			}
		});

		tipDocItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipDocDialog.setVisible(true);
				tipDocDialog.setTipDocument(tipDocument);

			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		return menuBar;
	}

	private void setMenuTransfListener(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				db.setTipDocument(EnumTipDocument.TRANSFER);
				db.getDocumenteNetiparite();
				toolbar.setTipDocument("Documente netiparite - transfer");

			}
		});
	}

	private void setMenuDistribListener(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				db.setTipDocument(EnumTipDocument.DISTRIBUTIE);
				db.getDocumenteNetiparite();
				toolbar.setTipDocument("Documente netiparite - distributie");

			}
		});
	}

	private void setMenuToateListener(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				db.setTipDocument(EnumTipDocument.TOATE);
				db.getDocumenteNetiparite();
				toolbar.setTipDocument("Documente netiparite - toate");

			}
		});
	}

	private void setMenuSortData(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Document> documente = db.getDocumente();

				Comparator[] comparators = new Comparator[] { new DataComparator() };

				DocumentChainedComparator<Document> chainedComparators = new DocumentChainedComparator<Document>(
						comparators);

				Collections.sort(documente, chainedComparators);

				tablePanel.setData(documente);
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

	private void setMenuSortSofer(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Document> documente = db.getDocumente();

				Comparator[] comparators = new Comparator[] { new SoferComparator() };

				DocumentChainedComparator<Document> chainedComparators = new DocumentChainedComparator<Document>(
						comparators);

				Collections.sort(documente, chainedComparators);

				tablePanel.setData(documente);
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

	private void setMenuSortMasina(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Document> documente = db.getDocumente();

				Comparator[] comparators = new Comparator[] { new NrMasinaComparator() };

				DocumentChainedComparator<Document> chainedComparators = new DocumentChainedComparator<Document>(
						comparators);

				Collections.sort(documente, chainedComparators);

				tablePanel.setData(documente);
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

	private void setMenuSortClient(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Document> documente = db.getDocumente();

				Comparator[] comparators = new Comparator[] { new ClientComparator() };

				DocumentChainedComparator<Document> chainedComparators = new DocumentChainedComparator<>(comparators);

				Collections.sort(documente, chainedComparators);

				tablePanel.setData(documente);
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

	public void logonSucceeded() {

		if (UserInfo.getInstance().getLogonStatus().equals("Status_3")) {

			logonDialog.setVisible(false);

			TipDocumentAfisat.getInstance().setNetiparit(true);
			if (Utils.getDepartCode(UserInfo.getInstance().getDepart()).equals("00")) {
				departDialog.setDepartaments();
				departDialog.setFiliale();
				departDialog.setVisible(true);

			} else {

				toolbar.setDepartamentString(Utils.getFullDepartName(UserInfo.getInstance().getDepart()));
				db.setTipDocument(EnumTipDocument.TRANSFER);
				db.getDocumenteNetiparite();
			}

			setVisible(true);

			toolbar.setNumeGest(UserInfo.getInstance().getNume());
			toolbar.setTipDocument("Documente netiparite - transfer");

		} else {
			setVisible(false);

			String logStatus = EnumLogonStatus.valueOf(UserInfo.getInstance().getLogonStatus()).getStatusName();
			logonDialog.setLogonStatus(logStatus);
			logonDialog.setVisible(true);

		}

	}

	public void departamentSelected(String departament, List<Document> listDocumente) {

		toolbar.setDepartamentString(departament);

		tablePanel.setData(listDocumente);
		tablePanel.autoresizeTableRowHeight();
		db.setDocumente(listDocumente);

	}

	public void dataReceived() {
		tablePanel.setData(db.getDocumente());
		tablePanel.autoresizeTableRowHeight();

	}

	public void refreshEventOccured() {
		if (TipDocumentAfisat.getInstance().isNetiparit())
			db.getDocumenteNetiparite();

	}

	public void printEventOccured() {
		doc.setDocumente(db.getDocumente());
		doc.printDocument();
	}

	public void printFinished() {
		if (TipDocumentAfisat.getInstance().isNetiparit())
			db.getDocumenteNetiparite();
		else
			db.getDocumenteTiparite(TipDocumentAfisat.getInstance().getDataTiparire());

	}

	public void tipDocumentSelected(List<Document> listDocumente) {

		if (TipDocumentAfisat.getInstance().isNetiparit()) {
			toolbar.setTipDocument("Documente netiparite - toate");
		} else {
			toolbar.setTipDocument("Documente tiparite");
		}

		tablePanel.setData(listDocumente);
		tablePanel.autoresizeTableRowHeight();
		db.setDocumente(listDocumente);

	}

	public void selectAllItems(boolean selectAll) {

		List<Document> documente = db.getDocumente();

		for (int i = 0; i < documente.size(); i++) {
			if (selectAll) {
				documente.get(i).setSeTipareste("1");
			} else {
				documente.get(i).setSeTipareste("0");
			}
		}

		dataReceived();

	}

	private void setMenuFiltruTrap(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				List<Document> filteretList = new TransportFilter().getFilteredData(EnumTipTransport.TRAP,
						db.getDocumente());

				tablePanel.setData(filteretList);
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

	private void setMenuFiltruTcli(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Document> filteretList = new TransportFilter().getFilteredData(EnumTipTransport.TCLI,
						db.getDocumente());

				tablePanel.setData(filteretList);
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

	private void setMenuFiltruTert(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Document> filteretList = new TransportFilter().getFilteredData(EnumTipTransport.TERT,
						db.getDocumente());

				tablePanel.setData(filteretList);
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

	private void setMenuFiltruToate(JRadioButtonMenuItem rbMenuItem) {
		rbMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tablePanel.setData(db.getDocumente());
				tablePanel.autoresizeTableRowHeight();

			}
		});
	}

}
