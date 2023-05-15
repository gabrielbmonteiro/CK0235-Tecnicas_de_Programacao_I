import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file1 = "file1.txt"; //nome do primeiro arquivo
        String file2 = "file2.txt"; //nome do segundo arquivo
        String outputFileName = "output.txt"; //nome do arquivo de saída

        MergeCPFNames mergeCPFNames = new MergeCPFNames();
        List<String> mergedList = mergeCPFNames.mergeFiles(file1, file2); //chama a função para mesclar os arquivos

        mergeCPFNames.writeToFile(outputFileName, mergedList); //escreve o resultado no arquivo de saída
    }
}
