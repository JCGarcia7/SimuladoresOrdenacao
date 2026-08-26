package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.trocar;

/**
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class SelectionSort {
    
    private SelectionSort() {
    }
    
    public static void selectionSort(int[] a, List<EstadoOrdenacao> copias){
        
        salvarEstadoOrdenacao(a, -1, -1, -1, copias);
        
        for (int i = 0; i < a.length; i++){
            int menor = i;
            for (int j = i + 1; j < a.length; j++){
                if (a[menor] > a[j]) {
                    menor = j;
                }
                salvarEstadoOrdenacao(a, i, j, menor, copias);
            }
            trocar (a, i, menor);
            
            salvarEstadoOrdenacao(a, i, -1, menor, copias);
            salvarEstadoOrdenacao(a, i, -1, -1, copias);
        }
    }
    
}
