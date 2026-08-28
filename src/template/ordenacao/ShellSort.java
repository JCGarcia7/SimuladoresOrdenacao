package template.ordenacao;

import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.salvarEstadoOrdenacao;
import static template.ordenacao.OrdenacaoUtils.trocar;

/**
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public class ShellSort {
    
    private ShellSort() {
    }
    
    public static void shellSort(int[] shell, List<EstadoOrdenacao> copias){
        int h = 1;
        int tamanho = shell.length;
        salvarEstadoOrdenacao(shell, -1, -1, -1, copias);
        while( h < tamanho / 3){
            h = 3 * h + 1;
        }
        while (h >= 1){
            for (int i = h; i < tamanho; i++){
                int j = i;
                while(j >= h && shell[j-h] > shell[j]){
                    trocar(shell, j-h, j);
                    salvarEstadoOrdenacao(shell, j-h, j, h, copias);
                    j = j - h;
                }
            }
            h = h / 3;
        }
        salvarEstadoOrdenacao(shell, -1, -1, -1, copias);
    }
    
}
