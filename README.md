# CK0235 - Técnicas de Programação I ☕

Este repositório reúne os projetos, trabalhos práticos e a aplicação final desenvolvidos para a disciplina de **Técnicas de Programação I (CK0235)** da Universidade Federal do Ceará (UFC). 

O foco central das atividades consiste na aplicação prática dos pilares da **Programação Orientada a Objetos (POO)** em Java, tratamento de fluxos de arquivos (I/O), tratamento de exceções, padrões de projeto (*Design Patterns*) e construção de interfaces gráficas (GUI).

---

## 🚀 Estrutura de Projetos e Trabalhos

O repositório está estruturado em três módulos de desenvolvimento incremental:

### 📄 Trabalho Prático 01: Manipulação de Ficheiros e Cruzamento de Dados (`TrabalhoProgramacao1`)
Focado no processamento de arquivos de texto e alocação dinâmica de coleções com a API de `java.util`.
* **Objetivo:** Ler dois arquivos de entrada distintos (`file1.txt` e `file2.txt`) contendo listas de CPFs e nomes correspondentes.
* **Algoritmo:** A classe `MergeCPFNames` executa o cruzamento, validação e mesclagem (*merge*) das informações estruturadas de forma eficiente.
* **Saída:** Gravação automatizada dos dados unificados em um novo ficheiro formatado de saída (`output.txt`).

### 🏓 Trabalho Prático 02: Padrão de Projeto Decorator (`TrabalhoProgramacao2`)
Demonstração prática de extensibilidade de comportamento e composição de objetos em detrimento da herança rígida, implementando o **Design Pattern Decorator**.
* **Modelo de Domínio:** Uma interface base `Mesa` e sua implementação concreta `MesaPura`.
* **Componentes Dinâmicos (Decorators):** A classe abstrata `Decoracao` serve como base para envolver o objeto e adicionar funcionalidades em tempo de execução de forma flexível.
* **Decorações Implementadas:**
  * `Rodinhas`: Adiciona mobilidade à mesa.
  * `Toalha`: Adiciona estética/proteção à mesa.
  * `RedePingPong`: Transforma a mesa convencional em uma mesa esportiva de Ping-Pong.
* **Execução:** O fluxo gerencia a adição e remoção em tempo real destas decorações na mesa através da listagem ativa de comportamentos.

### 📊 Trabalho Final: Analisador Estatístico de Dados com Interface Gráfica Swing e XChart (`Trabalho Final`)
Uma aplicação desktop completa com arquitetura **MVC (Model-View-Controller)** para importação, processamento matemático e visualização de matrizes numéricas e tabelas de dados.
* **Componente Model (`Model.Calculos`):** Motor matemático encarregado de ler coleções bidimensionais e computar métricas estatísticas descritivas sobre os dados:
  * Média Aritmética
  * Moda (valores mais frequentes)
  * Mediana (separação de metade superior e inferior)
  * Valores Extremos (Máximo e Mínimo)
* **Componente Controller (`Controller.BotaoArquivo`, `Controller.Tabela`):** Gerenciador de eventos com `ActionListener`. Controla a abertura do seletor nativo de ficheiros (`JFileChooser`) e faz o parsing seguro da estrutura de dados.
* **Componente View (`View.MostraJTable`, `View.MostrarChart`):** Interface robusta em **Java Swing**:
  * Apresentação tabular interativa dos dados e resultados estatísticos por coluna através de um `JTable`.
  * Geração e plotagem gráfica automatizada dos eixos configurados (X e Y) integrando a biblioteca externa **XChart (v3.8.4)**.

---

## 📂 Organização de Diretórios
```text
├── Trabalho Prático 01/             # Manipulação de Ficheiros
│   ├── Main.java                    # Ponto de entrada (File IO Merge)
│   ├── MergeCPFNames.java           # Lógica de processamento de CPFs
│   ├── file1.txt / file2.txt        # Ficheiros de teste de entrada
│   └── output.txt                   # Resultado gerado da união
│
├── Trabalho Prático 02/             # Padrão Decorator
│   ├── Main.java                    # Instanciação dinâmica da Mesa
│   ├── Mesa.java / MesaPura.java    # Interfaces e bases do componente
│   ├── Decoracao.java               # Classe abstrata do Decorator
│   └── Rodinhas.java / Toalha.java  # Decoradores concretos
│
└── Trabalho Final/                  # Aplicação GUI Completa
    ├── Lista_de_funcionalidades_implementadas.pdf
    ├── src/
    │   ├── Main.java                # Inicializador do Sistema
    │   ├── Controller/              # BotaoArquivo.java e Tabela.java
    │   ├── Model/                   # Calculos.java (Estatística)
    │   ├── View/                    # MostraJTable.java e MostrarChart.java
    │   └── lib/                     # Biblioteca Externa XChart (.jar)
    └── JavaDocs/                    # Documentação técnica gerada do projeto
```
---

## 🔧 Como Compilar e Executar
### Pré-requisitos
* Java Development Kit (JDK): Versão 11 ou superior recomendada.

### Executando os Trabalhos de Console (Trabalhos 1 e 2)
Navegue até o diretório do trabalho desejado através do terminal e execute:

```bash
# Compilar:
javac Main.java

# Executar:
java Main
```
### Executando o Trabalho Final (GUI)
Para o funcionamento correto da interface e dos gráficos, inclua o arquivo .jar da biblioteca XChart no classpath de compilação:

```bash
# A partir do diretório raiz do 'Trabalho Final/src':
javac -cp "lib/xchart-3.8.4/xchart-3.8.4.jar" Main.java Controller/*.java Model/*.java View/*.java

# Executar a Aplicação Swing:
java -cp ".:lib/xchart-3.8.4/xchart-3.8.4.jar" Main
```
