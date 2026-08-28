package template.ordenacao;

import br.com.davidbuzatto.jsge.math.MathUtils;
import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;

/**
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class MergeSort {
    
    private MergeSort() {
    }
    
    public static void mergeSort( int [] merge, List<EstadoOrdenacao> copias ){
        int tamanho = merge.length;
        int[] tempMerge = new int[tamanho];
        salvarEstadoOrdenacao(merge, -1, -1, -1, copias);
        cimaBaixo(merge, 0, tamanho - 1, tempMerge, copias);
        salvarEstadoOrdenacao(merge, -1, -1, -1, copias);
    }

    private static void cimaBaixo(int[] merge, int inicio, int fim, int[] tempMerge, List<EstadoOrdenacao> copias) {
        int meio;
        if (inicio < fim){
            meio = (inicio + fim) / 2;
            cimaBaixo(merge, inicio, meio, tempMerge, copias);
            cimaBaixo(merge, meio + 1, fim, tempMerge, copias);
            intercala(merge, inicio, meio, fim, tempMerge, copias );
            salvarEstadoOrdenacao(merge, inicio, meio, fim, copias);
        }
    }

    private static void intercala(int[] merge, int inicio, int meio, int fim, int[] tempMerge, List<EstadoOrdenacao> copias){
        int i = inicio;
        int j = meio + 1;
        for (int k = inicio; k <= fim; k++){
            tempMerge[k] = merge[k];
        }
        for (int k = inicio; k <= fim; k++){
            if (i > meio){
                merge[k] = tempMerge[j++];
            } else if (j > fim){
                merge[k] = tempMerge[i++];
            } else if (tempMerge[j] < tempMerge[i]){
                merge[k] = tempMerge[j++];
            } else {
                merge[k] = tempMerge[i++];
            }
            salvarEstadoOrdenacao(merge, k, -1, -1, copias);
        }
    
    
    }
    
}
