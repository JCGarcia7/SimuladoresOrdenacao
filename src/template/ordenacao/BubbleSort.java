package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.trocar;

/**
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class BubbleSort {
    
    private BubbleSort() {
    }
    
    public static void bubbleSort(int[] bubble, List<EstadoOrdenacao> copias){
        int tamanho = bubble.length;
        int i = 0;
        boolean trocado;
        salvarEstadoOrdenacao(bubble, i, -1, -1, copias);
        do {
            trocado = false;
            for(int j = tamanho - 1; j > i; j--){
                if (bubble[j-1] > bubble[j]){
                    trocar(bubble, j-1, j);
                    salvarEstadoOrdenacao(bubble, j, j- 1, -1, copias);
                    trocado = true;
                }
            }
            i++;
        } while (trocado && i < tamanho);
        
        salvarEstadoOrdenacao(bubble, -1, -1, -1, copias);
        
    }
    
}
