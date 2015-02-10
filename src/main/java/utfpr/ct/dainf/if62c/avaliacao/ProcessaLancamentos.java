package utfpr.ct.dainf.if62c.avaliacao;
import utfpr.ct.dainf.if62c.avaliacao.Lancamento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.GregorianCalendar;
import utfpr.ct.dainf.if62c.avaliacao.LancamentoComparator;

/**
 * IF62C Fundamentos de Programação 2
 * Avaliação parcial.
 * @author 
 */
public class ProcessaLancamentos {
    private BufferedReader reader;

    public ProcessaLancamentos(File arquivo) throws FileNotFoundException{
       reader = new BufferedReader(new FileReader(arquivo));
    }

    public ProcessaLancamentos(String path) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(new File(path)));
    }
    
    private String getNextLine() throws IOException {
        return reader.readLine();
    }
    
    private Lancamento processaLinha(String linha) {
        Lancamento l;
        Integer conta = Integer.valueOf(linha.substring(0, 6));;
        int ano = Integer.valueOf(linha.substring(6, 10));
        int mes = Integer.valueOf(linha.substring(10,12));
        int dia = Integer.valueOf(linha.substring(12,14));
        GregorianCalendar g = new GregorianCalendar(ano,mes,dia);
        Date data = new Date(g.getTimeInMillis());
        String descricao = linha.substring(14, 74);
        double reais = Double.valueOf(linha.substring(74,84));
        double centavos = Double.valueOf(linha.substring(84,86))*0.01;
        Double valor = reais + centavos;
        
        l = new Lancamento(conta,data,descricao,valor);
        
        return l;
    }
    
    private Lancamento getNextLancamento() throws IOException {
        
        if(this.getNextLine() == null)
            return null;
        return this.processaLinha(this.getNextLine());
    }
    
    public List<Lancamento> getLancamentos() throws IOException {
        ArrayList<Lancamento> l = new ArrayList<>();
        while(this.getNextLine()!=null){
            l.add(this.getNextLancamento());
        }
        l.sort(new LancamentoComparator());
        return l.subList(0, l.size());
    }
    
}
