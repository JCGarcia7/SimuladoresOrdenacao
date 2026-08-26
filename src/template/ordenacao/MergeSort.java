package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;

/**
 * Algoritmo de ordenação Merge Sort.
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class MergeSort {
    
    private MergeSort() {
    }
    
    public static void mergeSort( int [] merge, List<EstadoOrdenacao> copias ){
        
        salvarEstadoOrdenacao(merge, -1, -1, -1, copias);
        mergeSort(merge, 0, merge.length - 1, copias);
    }

    private static void mergeSort(int[] merge, int inicio, int fim, List<EstadoOrdenacao> copias) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSort(merge, inicio, meio, copias);
            mergeSort(merge, meio + 1, fim, copias);

            int[] auxiliar = new int[fim - inicio + 1];

            int i = inicio;
            int j = meio + 1;
            int k = 0;

            while (i <= meio && j <= fim) {

                salvarEstadoOrdenacao(merge, i, j, -1, copias);

                if (merge[i] <= merge[j]) {
                    auxiliar[k] = merge[i];
                    i++;
                } else {
                    auxiliar[k] = merge[j];
                    j++;
                }

                k++;
            }

            while (i <= meio) {
                auxiliar[k] = merge[i];
                i++;
                k++;
            }

            while (j <= fim) {
                auxiliar[k] = merge[j];
                j++;
                k++;
            }

            for (k = 0; k < auxiliar.length; k++) {
                merge[inicio + k] = auxiliar[k];
                salvarEstadoOrdenacao(merge, inicio + k, -1, -1, copias);
            }
        }
    }
    
}
