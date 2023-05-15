package Model;

import Controller.Tabela;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Comparator;

/**
 * Classe responsavél por calcular média, moda, mediana, max e min de uma coluna.
 *
 * @author Gabriel Batista Monteiro
 * @author Albert Moren Paulino da Anunciação
 */
public class Calculos {
    /**
     * Estrutura de dados responsavel por armazenar os valores.
     */
    private final String[][] dados;
    /**
     * Vetor de inteiros.
     */
    private int[] eixo_Y;

    /**
     * Lista do tipo String.
     */
    private List<String> eixo_X;

    /**
     * Metodo retorna os valores do eixo_y com base na posição desejada.
     *
     * @param i um valor do tipo inteiro.
     * @return Valor da posição desejada do vetor.
     */
    public int getEixoY2(int i) {
        return eixo_Y[i];
    }

    /**
     * Metodo que retorna os array completo.
     *
     * @return um array primitivo de inteiros.
     */
    public int[] getEixoY1() {
        return eixo_Y;
    }

    /**
     * Metodo que retorna os valores do eixo X.
     *
     * @return uma lista do tipo String.
     */
    public List<String> getEixoX() {
        return eixo_X;
    }

    /**
     * Construtor da classe.
     *
     * @param tabela uma instancia da classe Tabela.
     * @throws FileNotFoundException Se ocorrer um erro ao obter os dados da tabela.
     */
    public Calculos(Tabela tabela) throws FileNotFoundException {
        this.dados = tabela.getDados();
    }
    /**
     * Basicamente, o método verifica os tipos de dados presentes na coluna desejada
     * e executa uma análise específica com base nesses tipos.
     *
     * @param colunaDesejada Um o número da coluna selecionada pelo usuário.
     */
    public void executarCalculos(int colunaDesejada) {
        List<String> valores = new ArrayList<>();

        for (String[] dado : dados) {
            String valor = dado[colunaDesejada];
            valores.add(valor);
        }

        List<Object> valoresConvertidos = converterValores(valores);

        boolean contemFloats = contemFloats(valoresConvertidos);
        boolean contemInteiros = contemInteiros(valoresConvertidos);
        boolean contemStrings = contemStrings(valoresConvertidos);

        if (contemFloats && !contemInteiros && !contemStrings) {
            List<Float> floats = converterParaFloats(valoresConvertidos);
            analyzeFloatData(floats);
        } else if (contemInteiros && !contemFloats && !contemStrings) {
            List<Integer> inteiros = converterParaInteiros(valoresConvertidos);
            analyzeIntegerData(inteiros);
        } else if (contemStrings && !contemFloats && !contemInteiros) {
            List<String> strings = converterParaStrings(valoresConvertidos);
            analyzeStringData(strings);
        } else {
            System.out.println("A lista contém uma combinação inválida de tipos de dados.");
        }
    }

    /**
     * O método realiza a conversão dos valores de string para inteiros, floats ou strings, mantendo
     * o controle dos tipos de dados presentes nas strings e retornando uma lista com os valores
     * convertidos de acordo com esses tipos.
     *
     * @param valores Uma lista contendo valores do tipo String.
     * @return Uma lista dos mesmos valores convertidos em ints, floats ou Strings, dependendo do caso.
     */
    private static List<Object> converterValores(List<String> valores) {
        boolean contemFloats = true;
        boolean contemInteiros = true;

        List<Object> valoresConvertidos = new ArrayList<>();

        for (String valor : valores) {
            try {
                int intValue = Integer.parseInt(valor);
                valoresConvertidos.add(intValue);

                if (Integer.toString(intValue).equals(valor)) {
                    contemFloats = false;
                }
            } catch (NumberFormatException e) {
                try {
                    float floatValue = Float.parseFloat(valor);
                    valoresConvertidos.add(floatValue);
                    contemInteiros = false;
                } catch (NumberFormatException ex) {
                    valoresConvertidos.add(valor);
                    contemFloats = false;
                    contemInteiros = false;
                }
            }
        }

        if (contemFloats && !contemInteiros) {
            List<Float> floats = new ArrayList<>();
            for (Object valor : valoresConvertidos) {
                if (valor instanceof Float) {
                    floats.add((Float) valor);
                }
            }
            return new ArrayList<>(floats);
        } else if (contemInteiros && !contemFloats) {
            List<Integer> inteiros = new ArrayList<>();
            for (Object valor : valoresConvertidos) {
                if (valor instanceof Integer) {
                    inteiros.add((Integer) valor);
                }
            }
            return new ArrayList<>(inteiros);
        } else {
            return valoresConvertidos;
        }
    }

    /**
     * Esse método verifica se uma lista de valores contém elementos do tipo float ou double.
     *
     * @param valores Uma lista de valores do tipo Object.
     * @return Um boolean.
     */
    private static boolean contemFloats(List<Object> valores) {
        for (Object valor : valores) {
            if (valor instanceof Float || valor instanceof Double) {
                return true;
            }
        }
        return false;
    }
    /**
     * O método percorre a lista de valores genéricos e realiza as conversões necessárias para transformar
     * os valores em valores de ponto flutuante, armazenando-os em uma lista separada.
     *
     * @param valores Uma lista do tipo Object.
     * @return Uma lista do tipo Float.
     */
    private static List<Float> converterParaFloats(List<Object> valores) {
        List<Float> floats = new ArrayList<>();
        for (Object valor : valores) {
            if (valor instanceof Integer) {
                floats.add(((Integer) valor).floatValue());
            } else if (valor instanceof Float) {
                floats.add((Float) valor);
            } else if (valor instanceof Double) {
                floats.add(((Double) valor).floatValue());
            }
        }
        return floats;
    }

    /**
     * Esse método verifica se uma lista de valores contém elementos do tipo int.
     *
     * @param valores Uma lista do tipo Object
     * @return um boolean.
     */
    private static boolean contemInteiros(List<Object> valores) {
        for (Object valor : valores) {
            if (valor instanceof Integer) {
                return true;
            }
        }
        return false;
    }

    /**
     * O método percorre os valores genéricos e os converte para o tipo inteiro, armazenando-os em
     * uma nova lista que é retornada como resultado da conversão.
     *
     * @param valores Uma lista do tipo object.
     * @return Uma lista do tipo inteiro.
     */
    private static List<Integer> converterParaInteiros(List<Object> valores) {
        List<Integer> inteiros = new ArrayList<>();
        for (Object valor : valores) {
            inteiros.add((Integer) valor);
        }
        return inteiros;
    }

    /**
     * Esse método verifica se uma lista de valores contém elementos do tipo String.
     *
     * @param valores Uma lista do tipo objetc.
     * @return Um boolean.
     */
    private static boolean contemStrings(List<Object> valores) {
        for (Object valor : valores) {
            if (valor instanceof String) {
                return true;
            }
        }
        return false;
    }
    /**
     * Esse método realiza a conversão dos objetos para o tipo String, permitindo que se
     * obtenha uma lista contendo apenas os valores como strings.
     *
     * @param valores Uma lista do tipo object.
     * @return Ums lista do tipo String.
     */
    private static List<String> converterParaStrings(List<Object> valores) {
        List<String> strings = new ArrayList<>();
        for (Object valor : valores) {
            strings.add((String) valor);
        }
        return strings;
    }
    /**
     * O método realiza uma análise estatística dos dados numéricos, do tipo float, calculando
     * e exibindo a média, moda, variância, desvio padrão, máximo e mínimo dos valores.
     * Além de receber um vetor de int contendo a frequencia dos dados em cada coluna (eixo_Y)
     * e uma lista de strings contendo os intervalo dos valores em cada coluna (eixo_X).
     *
     * @param numericData Uma lista do tipo float.
     */
    private void analyzeFloatData(List<Float> numericData) {
        int n = numericData.size();
        float sum = 0;
        float max = Float.MIN_VALUE;
        float min = Float.MAX_VALUE;

        Map<Float, Integer> frequencies = new HashMap<>();
        int maxFrequency = 0;

        for (float value : numericData) {
            sum += value;

            if (value > max) {
                max = value;
            }

            if (value < min) {
                min = value;
            }

            int frequency = frequencies.getOrDefault(value, 0) + 1;
            frequencies.put(value, frequency);

            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        float media = sum / n;
        float variance = calculateVariance(numericData, media);
        float standardDeviation = (float) Math.sqrt(variance);
        List<Float> moda = findModa(frequencies, maxFrequency);

        int k = (int) Math.ceil(1 + 3.222 * Math.log(n));
        float At = max - min;
        float h = At / k;

        this.eixo_Y = drawHistogram(frequencies, min, k, h);
        this.eixo_X = intervals(min, k, h);

        System.out.println("\nMédia: " + media);
        System.out.println("Moda: " + moda);
        System.out.println("Variância: " + variance);
        System.out.println("Desvio Padrão: " + standardDeviation);
        System.out.println("Máximo: " + max);
        System.out.println("Mínimo: " + min);
    }
    /**
     * O método realiza uma análise estatística dos dados inteiros, calculando e
     * exibindo a média, moda, variância, desvio padrão, máximo e mínimo dos valores.
     * Além de receber um vetor de int contendo a frequencia dos dados em cada coluna (eixo_Y)
     * e uma lista de strings contendo o valor de cada coluna (eixo_X).
     *
     * @param numericData Uma lista do tipo inteiro.
     */
    private void analyzeIntegerData(List<Integer> numericData) {
        int n = numericData.size();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int value : numericData) {
            sum += value;

            if (value > max) {
                max = value;
            }

            if (value < min) {
                min = value;
            }
        }

        double media = (double) sum / n;
        double variance = calculateVariance(numericData, media);
        double standardDeviation = Math.sqrt(variance);

        Map<Integer, Integer> frequencies = new HashMap<>();
        int maxFrequency = 0;
        int moda = 0;

        for (int value : numericData) {
            int frequency = frequencies.getOrDefault(value, 0) + 1;
            frequencies.put(value, frequency);

            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                moda = value;
            }
        }

        this.eixo_Y = drawColumnChart(frequencies);
        this.eixo_X = intervals(frequencies);

        List<String> aux1 = new ArrayList<>(eixo_X);
        Comparator<String> comparadorNumerico = Comparator.comparingInt(Integer::parseInt);
        eixo_X.sort(comparadorNumerico);
        int[] aux2 = new int[eixo_Y.length];
        for(int i = 0; i < eixo_X.size(); i++)
        {
            String string = eixo_X.get(i);
            int index = aux1.indexOf(string);
            aux2[i] = eixo_Y[index];
        }
        eixo_Y = aux2;

        System.out.println("\nMédia: " + media);
        System.out.println("Moda: " + moda);
        System.out.println("Variância: " + variance);
        System.out.println("Desvio Padrão: " + standardDeviation);
        System.out.println("Máximo: " + max);
        System.out.println("Mínimo: " + min);
    }

    /**
     * O método analisa a frequência dos valores de string, exibe um gráfico de colunas
     * com as frequências e imprime a moda dos valores.
     * Além de receber um vetor de int contendo a frequencia dos dados em cada coluna (eixo_Y)
     * e uma lista de strings contendo o valor de cada coluna (eixo_X).
     *
     * @param stringData Uma lista do tipo String.
     */
    private void analyzeStringData(List<String> stringData) {
        Map<String, Integer> frequencies = new HashMap<>();
        int maxFrequency = 0;

        for (String value : stringData) {
            int frequency = frequencies.getOrDefault(value, 0) + 1;
            frequencies.put(value, frequency);
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        this.eixo_Y = drawColumnChart(frequencies);
        this.eixo_X = intervals(frequencies);

        List<String> moda = findModaStrings(stringData);
        System.out.println("\nModa: " + moda);
    }
    /**
     * O método percorre as frequências de valores em um mapa e armazena a quantidade de
     * ocorrências de cada dado num vetor de int.
     *
     * @param frequencies Uma map do tipo inteiro.
     * @return Um vetor de inteiros.
     */
    private static int[] drawColumnChart(Map<?, Integer> frequencies) {
        int[] column =new int[frequencies.size()];
        int index = 0;
        for (Map.Entry<?, Integer> entry : frequencies.entrySet()) {
            //String value = entry.getKey().toString();
            int frequency = entry.getValue();
            column[index] = frequency;
            index++;
        }
        return column;
    }

    /**
     * Esse método extrai as chaves de um mapa de frequências e as retorna como uma lista
     * de strings, representando os valores presentes no mapa.
     *
     * @param frequencies Uma map do tipo inteiro.
     * @return Uma lista do tipo String.
     */
    private static List<String> intervals(Map<?, Integer> frequencies) {
        List<String> intervals = new ArrayList<>();
        for (Map.Entry<?, Integer> entry : frequencies.entrySet()) {
            String value = entry.getKey().toString();
            intervals.add(value);
        }
        return intervals;
    }
    /**
     * Este metodo calcula os intervalos de frequencias, com base nos valores recebidos e,
     * com isso, calcula a frequencia dos dados dentro de cada um desses intervalos e então
     * armazena essas frequencias num vetor de int.
     *
     * @param frequencies mapa do tipo inteiro.
     * @param min O valor mínimo.
     * @param k O número de intevalos.
     * @param h O tamanho dos intervalos.
     * @return Um vetor do tipo inteiro.
     */
    private static int[] drawHistogram(Map<?, Integer> frequencies, float min, int k, float h) {
        float lowerBound = min;
        float upperBound = lowerBound + h;

        int[] histogram = new int[k];

        for (int i = 0; i < k - 1; i++) {
            int frequency = frequenciesInRange(frequencies, lowerBound, upperBound);
            histogram[i] = frequency;
            lowerBound = upperBound;
            upperBound += h;
        }

        int frequency = frequenciesInRange(frequencies, lowerBound, upperBound);
        histogram[k-1] = frequency + 1;

        return histogram;
    }

    /**
     * Recebe o valor mínimo, o número de intervalos desejado e o tamanho do intervalo e, com isso,
     * cria intervalos com base nos valores e frequências fornecidos.
     *
     * @param min O valor mínimo.
     * @param k O número de intervalos.
     * @param h O tamanho dos intervalos.
     * @return Uma lista de Strings com os intervalos.
     *
     */
    private static List<String> intervals(float min, int k, float h) {
        List<String> intervals = new ArrayList<>();
        float lowerBound = min;
        float upperBound = lowerBound + h;

        for (int i = 0; i < k - 1; i++) {
            String interval = String.format("[%.2f, %.2f)", lowerBound, upperBound);
            intervals.add(interval);

            lowerBound = upperBound;
            upperBound += h;
        }

        String interval = String.format("[%.2f, %.2f]", lowerBound, upperBound);
        intervals.add(interval);

        return intervals;
    }

    /**
     * O método conta a frequência dos valores dentro de um determinado
     * intervalo com base em um mapa de frequências, retornando o número
     * total de ocorrências dos valores nesse intervalo.
     *
     * @param frequencies Um mapa contendo as frequencias de cada valor da coluna.
     * @param lowerBound O limite inferior do intervalo.
     * @param upperBound O limite superior do intervalo.
     * @return Um valor do tipo inteiro.
     */
    private static int frequenciesInRange(Map<?, Integer> frequencies, float lowerBound, float upperBound) {
        int frequency = 0;
        for (Map.Entry<?, Integer> entry : frequencies.entrySet()) {
            float value = Float.parseFloat(entry.getKey().toString());
            if (value >= lowerBound && value < upperBound) {
                frequency += entry.getValue();
            }
        }
        return frequency;
    }

    /**
     * O método calcula a variância dos dados numéricos, que é
     * uma medida de dispersão dos valores em relação à média.
     *
     * @param  numericData Uma lista contendo os dados numéricos da coluna no tipo float.
     * @param media A média dos valores da coluna.
     * @return Um valor do tipo float que representa a variância dos valores da coluna.
     */
    private static float calculateVariance(List<Float> numericData, float media) {
        float sum = 0;
        for (float value : numericData) {
            sum += Math.pow(value - media, 2);
        }
        return sum / numericData.size();
    }

    /**
     * O método calcula a variância dos valores numéricos em relação à média
     * fornecida, medindo a dispersão dos valores em relação à média.
     *
     * @param numericData Uma lista contendo os dados numéricos da coluna no tipo int.
     * @param media A média dos valores da coluna.
     * @return Um valor do tipo double que representa a variância dos valores da coluna.
     */
    private static double calculateVariance(List<Integer> numericData, double media) {
        double sum = 0;
        for (int value : numericData) {
            sum += Math.pow(value - media, 2);
        }
        return sum / numericData.size();
    }

    /**
     * O método encontra e retorna os valores moda (valores com a maior frequência)
     * com base nas frequências registradas em um mapa.
     *
     * @param frequencies, Um mapa contendo as frequências de cada valor da coluna.
     * @param maxFrequency, A maior frequência dentre os dados da coluna.
     * @return uma lista do tipo float contendo a/as modas da coluna.
     */
    private static List<Float> findModa(Map<?, Integer> frequencies, int maxFrequency) {
        List<Float> moda = new ArrayList<>();
        for (Map.Entry<?, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                moda.add(Float.parseFloat(entry.getKey().toString()));
            }
        }
        return moda;
    }

    /**
     * O método encontra os valores de texto que ocorrem com mais frequência
     * na lista, ou seja, a moda dos valores de texto.
     *
     * @param stringData Uma lista de String que estão na coluna.
     * @return Uma lista de String contendo a/as modas da coluna.
     *
     */
    private static List<String> findModaStrings(List<String> stringData) {
        Map<String, Integer> frequencies = new HashMap<>();
        int maxFrequency = 0;

        for (String value : stringData) {
            int frequency = frequencies.getOrDefault(value, 0) + 1;
            frequencies.put(value, frequency);

            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        List<String> moda = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                moda.add(entry.getKey());
            }
        }

        return moda;
    }

}