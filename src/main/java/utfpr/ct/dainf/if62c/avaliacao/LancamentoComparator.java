package utfpr.ct.dainf.if62c.avaliacao;
import java.util.Comparator;
import utfpr.ct.dainf.if62c.avaliacao.Lancamento;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class LancamentoComparator implements Comparator<Lancamento> {
        
    public LancamentoComparator(){
        
    }
    
    @Override
    public int compare(Lancamento o1, Lancamento o2){
        if(o1.getConta().intValue() == o2.getConta().intValue()){
            long time = o1.getData().getTime() - o2.getData().getTime();
            int t = (int)time;
            return t;
        }
        return o1.getConta() - o2.getConta();
    }
}
