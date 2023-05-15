import java.util.List;

public interface Mesa {
    void adicionarDecoracao(Decoracao decoracao);
    void removerDecoracao(Decoracao decoracao);
    List<Decoracao> getDecoracoes();
}
