package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.trocar;

/**
 * Algoritmo de ordenação Quick Sort.
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class QuickSort {
    
    private QuickSort() {
    }
    
    public static void quickSort ( int [] quick, List<EstadoOrdenacao> copias){
        salvarEstadoOrdenacao(quick, -1, -1, -1, copias);
        quickSort(quick, 0, quick.length - 1, copias);
    }
    
    private static int particao ( int[] quick, int inicio, int fim, List<EstadoOrdenacao> copias){
        int pivo = quick[inicio];
        int i = inicio + 1;
        int j = fim;
        
        while (true){
            
            while (i <= fim && quick[i] < pivo){
                salvarEstadoOrdenacao(quick, i, j, inicio, copias);
                i++;
            }
            
            while (j > inicio && quick[j] > pivo){
                salvarEstadoOrdenacao(quick, i, j, inicio, copias);
                j--;
            }
            
            if (i >= j){
                break;
            }
            
            salvarEstadoOrdenacao(quick, i, j, inicio, copias);
            trocar(quick, i, j);
            salvarEstadoOrdenacao(quick, i, j, inicio, copias);
            
            i++;
            j--;
        }
        
        trocar(quick, inicio, j);
        salvarEstadoOrdenacao(quick, inicio, j, j, copias);
        
        return j;
    }
    
    private static void quickSort ( int[] quick, int inicio, int fim, List<EstadoOrdenacao> copias ){
        if (inicio < fim){
            
            int meio = particao(quick, inicio, fim, copias);
            
            quickSort(quick, inicio, meio - 1, copias);
            quickSort(quick, meio + 1, fim, copias);
        }
    }
    
}
