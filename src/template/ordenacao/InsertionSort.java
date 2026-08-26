package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.trocar;

/**
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class InsertionSort {
    
    private InsertionSort() {
    }
    
    public static void insertionSort(int[] insert, List<EstadoOrdenacao> copias){
        int tamanho = insert.length;
        salvarEstadoOrdenacao(insert, -1, -1, -1, copias);
        for(int i = 1; i < tamanho; i++){
            int j = i;
            while(j > 0 && insert[j - 1] > insert[j]){
                salvarEstadoOrdenacao(insert, i, j, j - 1, copias);
                trocar(insert, j-1, j);
                j--;
            }
            salvarEstadoOrdenacao(insert, i, -1, -1, copias);
        }
    }
    
}
