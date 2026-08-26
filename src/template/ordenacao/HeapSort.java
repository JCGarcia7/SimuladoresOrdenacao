package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.trocar;

/**
 * Algoritmo de ordenação Heap Sort.
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class HeapSort {
    
    private HeapSort() {
    }
    
    public static void heapSort ( int [] heap, List<EstadoOrdenacao> copias){
        salvarEstadoOrdenacao(heap, -1, -1, -1, copias);
        
        int tamanho = heap.length;
        
        for (int i = tamanho / 2 - 1; i >= 0; i--){
            heapify(heap, tamanho, i, copias);
        }
        
        for (int i = tamanho - 1; i > 0; i--){
            salvarEstadoOrdenacao(heap, 0, i, -1, copias);
            trocar(heap, 0, i);
            salvarEstadoOrdenacao(heap, 0, i, -1, copias);
            
            heapify(heap, i, 0, copias);
        }
        
        salvarEstadoOrdenacao(heap, -1, -1, -1, copias);
    }
    
    private static void heapify ( int [] heap, int tamanho, int raiz, List<EstadoOrdenacao> copias){
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;
        
        if (esquerda < tamanho){
            salvarEstadoOrdenacao(heap, raiz, esquerda, maior, copias);
            
            if (heap[esquerda] > heap[maior]){
                maior = esquerda;
            }
        }
        
        if (direita < tamanho){
            salvarEstadoOrdenacao(heap, raiz, direita, maior, copias);
            
            if (heap[direita] > heap[maior]){
                maior = direita;
            }
        }
        
        if (maior != raiz){
            salvarEstadoOrdenacao(heap, raiz, maior, maior, copias);
            trocar(heap, raiz, maior);
            salvarEstadoOrdenacao(heap, raiz, maior, maior, copias);
            
            heapify(heap, tamanho, maior, copias);
        }
    }
    
}
