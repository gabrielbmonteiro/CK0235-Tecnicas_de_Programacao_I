package View;


import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Model.Calculos;
/**
 * Classe usada para plotar o gráfico (histograma) em uma janela.
 *
 * @author Victor Medeiro Martins
 */
public class MostrarChart {
    /**
     * Configura e estiliza o gráfico (histograma) utilizando as infomações da coluna selecionada
     * pelo usuário e plota-o em uma janela.
     *
     * @param nomeDaColuna O nome da coluna selecionada pelo usuário.
     * @param calculos As informações da coluna.
     */
    public static void abrirGrafico(String nomeDaColuna, Calculos calculos) {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title("Comparação").xAxisTitle(nomeDaColuna).yAxisTitle("Frequência").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setPlotContentSize(0.8);

        List<String> dadosX = new ArrayList<>();
        List<Number> dadosY = new ArrayList<>();

        for (int i = 0; i < calculos.getEixoY1().length; i++) {
            dadosX.add(calculos.getEixoX().get(i));
            dadosY.add(calculos.getEixoY2(i));
        }

        chart.addSeries(nomeDaColuna, dadosX, dadosY)
                .setChartCategorySeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar)
                .setFillColor(Color.GREEN);

        SwingUtilities.invokeLater(() -> {
            JFrame chartFrame = new JFrame("Chart");
            chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            chartFrame.getContentPane().add(new XChartPanel<>(chart));
            chartFrame.pack();
            chartFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            chartFrame.setVisible(true);
        });
    }
}