package View;

import Controller.BotaoArquivo;
import Controller.Tabela;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import Model.Calculos;

/**
 * Classe usada para mostrar a JTable, detectar se ela foi alterada e,
 * se for alterada, modificar os valores na tabela final.
 *
 * @author Guilherme Gomes Botelho
 */
public class MostraJTable
{
    /**
     * Tabela de dados obtida a partir do arquivo recebido.
     */
    private final Tabela tabelaDados;

    /**
     * Classe de cálculos para obter as informações da coluna.
     */
    private Calculos calculos;

    /**
     * Recebe uma Tabela para ser manipulada nesta classe e cria uma nova instância
     * de uma classe Calculos a qual será utilizada para obter as informações das
     * colunas futuramente.
     *
     * @param arquivoTabela Uma Controller.Tabela de dados
     */
    public MostraJTable(Tabela arquivoTabela)
    {
        this.tabelaDados = arquivoTabela;
        try
        {
            this.calculos = new Calculos(arquivoTabela);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Erro na leitura do arquivo");
        }
    }

    /**
     * Cria uma janela, em seguida, mostra uma tabela (JTable) na qual
     * as células podem ser editadas. Depois, adiciona alguns "Listeners" (observadores) à
     * tabela, assim, toda vez que o usuário clicar em uma coluna ou alterar
     * qualquer dado de uma coluna, serão printados no console todas as informações dos
     * valores da coluna clicada (média, moda, variância, desvio padrão, máximo e mínimo)
     * e será mostrado o gráfico da distribuição desses dados.
     * Adicionalmente, são criados dois botões, um deles salva a tabela, com todas as
     * alterações feitas pelo usuário, em um novo arquivo e outro botão permite que
     * o usuário escolha um novo arquivo para leitura sem fechar o programa.
     */
    public void criarInterface()
    {
        JFrame janela = new JFrame("Controller.Tabela Interativa");
        JButton botaoSalvar = new JButton("Salvar tabela em um novo arquivo");
        JButton botaoTrocar = new JButton(("Selecionar novo arquivo"));
        ActionListener trocarListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                janela.dispose();
                BotaoArquivo outro_botao = new BotaoArquivo();
            }
        };
        botaoTrocar.addActionListener(trocarListener);
        ActionListener salvarListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tabelaDados.criarNovoArquivo(tabelaDados);
            }
        };
        botaoSalvar.addActionListener(salvarListener);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DefaultTableModel modelo = new DefaultTableModel(tabelaDados.getDados(), tabelaDados.getNomes());
        JTable tabela = new JTable(modelo);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean isSelected,
                                                           boolean hasFocus, int linha, int coluna) {
                Component c = super.getTableCellRendererComponent(tabela, valor, false, hasFocus, linha, coluna);
                c.setBackground(tabela.getBackground());
                return c;
            }
        };
        tabela.setSelectionBackground(tabela.getBackground());
        tabela.setDefaultRenderer(Object.class, renderer);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < tabela.getColumnCount(); i++)
        {
            tabela.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        tabela.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                int colunaSelecionada = tabela.getSelectedColumn();
                calculos.executarCalculos(colunaSelecionada);
                if (colunaSelecionada != -1){
                    String nomeDaColuna = tabela.getColumnName(colunaSelecionada);
                    MostrarChart.abrirGrafico(nomeDaColuna, calculos);
                }
            }
        });

        modelo.addTableModelListener(new TableModelListener()
        {
            public void tableChanged(TableModelEvent e)
            {
                int row = e.getFirstRow();
                int colunaSelecionada = e.getColumn();
                if (row != TableModelEvent.HEADER_ROW && colunaSelecionada != TableModelEvent.ALL_COLUMNS)
                {
                    String newData = String.valueOf(modelo.getValueAt(row, colunaSelecionada));
                    tabelaDados.alterarDado(row, colunaSelecionada, newData);
                    calculos.executarCalculos(colunaSelecionada);
                    String nomeDaColuna = tabela.getColumnName(colunaSelecionada);
                    MostrarChart.abrirGrafico(nomeDaColuna, calculos);
                }
            }
        });
        JScrollPane scroll = new JScrollPane(tabela);
        janela.add(botaoTrocar, BorderLayout.NORTH);
        janela.add(botaoSalvar, BorderLayout.SOUTH);
        janela.add(scroll, BorderLayout.CENTER);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}
