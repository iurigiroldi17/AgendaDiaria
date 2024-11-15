import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendaDiaria extends JFrame {
    private JTextField txtCompromisso;
    private JSpinner txtDataHora;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JTable txtTabela;

    private JTable tabela;
    private DefaultTableModel model;

    public AgendaDiaria() {
        setTitle("Agenda Diária");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        txtCompromisso = new JTextField(20);
        txtDataHora = new JSpinner(new SpinnerDateModel());
        txtDataHora.setEditor(new JSpinner.DateEditor(txtDataHora, "dd/MM/yyyy HH:mm"));
        btnAdicionar = new JButton("Adicionar Compromisso");
        btnRemover = new JButton("Remover Compromisso");

        model = new DefaultTableModel(new Object[]{"Data/Hora", "Compromisso"}, 0);
        tabela = new JTable(model);

        add(new JLabel("Compromisso:"));
        add(txtCompromisso);
        add(new JLabel("Data/Hora:"));
        add(txtDataHora);
        add(btnAdicionar);
        add(new JScrollPane(tabela));
        add(btnRemover);

        btnAdicionar.addActionListener(e -> adicionarCompromisso());
        btnRemover.addActionListener(e -> removerCompromisso());
    }

    private void adicionarCompromisso() {
        String compromisso = txtCompromisso.getText();
        Date dataHora = (Date) txtDataHora.getValue();
        if (!compromisso.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            model.addRow(new Object[]{sdf.format(dataHora), compromisso});
            txtCompromisso.setText("");
        }
    }

    private void removerCompromisso() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        }
    }

    public static void main(String[] args) {
        new AgendaDiaria().setVisible(true);
    }
}
