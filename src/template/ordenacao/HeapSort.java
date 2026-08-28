package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.trocar;

/**
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class HeapSort {
    
    private HeapSort() {
    }
    
    public static void heapSort(int[] heap, List<EstadoOrdenacao> copias){
        int n = heap.length - 1;
        salvarEstadoOrdenacao(heap, -1, -1, -1, copias);
        for (int k = (n - 1) / 2; k >= 0; k--){
            sink(heap, k, n, copias);
        }
        while (n > 0){
            trocar(heap, 0, n);
            salvarEstadoOrdenacao(heap, 0, n, -1, copias);
            n--;
            sink(heap, 0, n, copias);
        }
        
        salvarEstadoOrdenacao(heap, -1, -1, -1, copias);
    
    }
    
    private static void sink (int[] heap, int k, int n, List<EstadoOrdenacao> copias){
        while (2*k + 1 <= n){
            int j = 2*k + 1;
            if (j < n && heap[j] < heap[j + 1]){
                j++;
            }
            if (heap[k] >= heap[j]){
                break;
            }
            trocar(heap, k, j); 
            salvarEstadoOrdenacao(heap, k, n, j, copias);
            k = j;
        }
    }
            
}
