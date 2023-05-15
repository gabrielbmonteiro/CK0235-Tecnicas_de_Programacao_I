package Controller;

import View.MostraJTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe usada para criar um botão o qual permite que o usuário selecione o arquivo que deseja visualizar.
 *
 * @author Guilherme Gomes Botelho
 */
public class BotaoArquivo
{
    /**
     *  Arquivo que o usuário selecionou.
     */
    private File arquivoSelecionado;

    /**
     *  Cria uma nova instância da classe BotaoArquivo, em seguida, cria uma nova janela a qual permitirá que
     *  o usuário clique em um botão e selecione o arquivo de sua escolha, se o arquivo for válido, cria-se
     *  um outra janela com a tabela de dados(JTable) e a janela atual é fechada. Se não for válido imprime
     *  "Erro na leitura do arquivo. Tente novamente escolher um arquivo válido.".
     */
    public BotaoArquivo()
    {
        JFrame janela = new JFrame("Escolha um arquivo");
        JButton botao = new JButton("Selecionar arquivo");
        botao.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Tabela arquivoTabela;
                escolherArquivo();
                try {
                    arquivoTabela = new Tabela();
                    arquivoTabela.lerArquivo(arquivoSelecionado);
                } catch (FileNotFoundException | ArrayIndexOutOfBoundsException | NullPointerException error) {
                    System.out.println("Erro na leitura do arquivo. Tente novamente escolher um arquivo válido.");
                    return;
                }
                MostraJTable view = new MostraJTable(arquivoTabela);
                view.criarInterface();
                janela.dispose();
            }
        });
        janela.getContentPane().add(botao, BorderLayout.CENTER);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    /**
     *  Esse método abre o selecionador de arquivos e checa se o usuário escolheu é válido, se sim,
     *  ele guarda o arquivo selecionado. Senão, ele imprime "Erro no arquivo escolhido".
     */
    public void escolherArquivo()
    {
        JFileChooser escolhedorDeArquivo = new JFileChooser();
        int resultado = escolhedorDeArquivo.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION)
        {
            this.arquivoSelecionado = escolhedorDeArquivo.getSelectedFile();
        }
        else
        {
            System.out.println("Erro no arquivo escolhido");
            this.arquivoSelecionado = null;
        }
    }
}
