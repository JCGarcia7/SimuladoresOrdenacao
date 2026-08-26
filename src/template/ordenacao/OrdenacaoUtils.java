package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;

/**
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class OrdenacaoUtils {
    
    private OrdenacaoUtils() {
    }
    
    public static void trocar(int[] a, int p1, int p2){
        int t = a[p1];
        a[p1] = a[p2];
        a[p2] = t;
    }
    
    public static void salvarEstadoOrdenacao(int[] origem, int posI, int posJ, int posMenor, List<EstadoOrdenacao> copias){
        int[] copia = new int[origem.length];
        for(int i = 0; i < copia.length; i++){
            copia[i] = origem[i];
        }
        
        EstadoOrdenacao estadoCopia = new EstadoOrdenacao(copia, posI, posJ, posMenor);
        copias.add(estadoCopia);   
    }
    
}
