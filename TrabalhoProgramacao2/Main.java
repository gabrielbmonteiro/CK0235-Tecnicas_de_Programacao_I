import java.util.List;

public class Main {
    public static void main(String[] args) {
      
        Mesa mesa = new MesaPura();
        imprimirSituacaoMesa(mesa);
      
        Rodinhas rodinhas = new Rodinhas();
        mesa.adicionarDecoracao(rodinhas);
        imprimirSituacaoMesa(mesa);
      
        RedePingPong redePingPong = new RedePingPong();
        Toalha toalha = new Toalha();
        mesa.adicionarDecoracao(redePingPong);
        mesa.adicionarDecoracao(toalha);
        imprimirSituacaoMesa(mesa);

        mesa.removerDecoracao(redePingPong);
        imprimirSituacaoMesa(mesa);
    }

    public static void imprimirSituacaoMesa(Mesa mesa) {
        System.out.println("Situação da Mesa:");
        List<Decoracao> decoracoes = mesa.getDecoracoes();
        if (decoracoes.isEmpty()) {
            System.out.println("Mesa está sem decorações.");
        } else {
            for (Decoracao decoracao : decoracoes) {
                System.out.println(decoracao.getDescricao());
            }
        }
        System.out.println("-------------------");
    }
}
