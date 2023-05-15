import java.io.*;
import java.util.*;

public class MergeCPFNames {
    //função para mesclar os arquivos
    public List<String> mergeFiles(String file1, String file2) {
        List<String> mergedList = new ArrayList<>();

        try {
            //lê o primeiro arquivo
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            String line = reader1.readLine();
            while (line != null) {
                mergedList.add(line);
                line = reader1.readLine();
            }
            reader1.close();

            //lê o segundo arquivo
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            line = reader2.readLine();
            while (line != null) {
                mergedList.add(line);
                line = reader2.readLine();
            }
            reader2.close();

            //remove as linhas duplicadas
            Set<String> uniqueLines = new HashSet<>(mergedList);
            mergedList.clear();
            mergedList.addAll(uniqueLines);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mergedList;
    }

    //função para escrever no arquivo de saída
    public void writeToFile(String outputFileName, List<String> lines) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
