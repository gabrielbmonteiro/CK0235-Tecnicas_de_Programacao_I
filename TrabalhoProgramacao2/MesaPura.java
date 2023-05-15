import java.util.ArrayList;
import java.util.List;

public class MesaPura implements Mesa {
    private List<Decoracao> decoracoes;

    public MesaPura() {
        this.decoracoes = new ArrayList<>();
    }

    public void adicionarDecoracao(Decoracao decoracao) {
        decoracoes.add(decoracao);
    }

    public void removerDecoracao(Decoracao decoracao) {
        decoracoes.remove(decoracao);
    }

    public List<Decoracao> getDecoracoes() {
        return decoracoes;
    }
}
