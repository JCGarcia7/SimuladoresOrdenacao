package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Simuladores de algoritmosde ordenação.
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */

public class Main extends EngineFrame {
    
    private record EstadoOrdenacao(int[] a, int i, int j, int menor){}
    
    private int[] aSelection;
    private int[] aInsertion;
    private int[] aBubble;
    private int[] aShell;
    private int[] aMerge;
    private int[] aQuick;
    private List<EstadoOrdenacao> copiasSelection;
    private List<EstadoOrdenacao> copiasInsertion;
    private List<EstadoOrdenacao> copiasBubble;
    private List<EstadoOrdenacao> copiasShell;
    private List<EstadoOrdenacao> copiasMerge;
    private List<EstadoOrdenacao> copiasQuick;
    private int copiaAtualSelection;
    private int copiaAtualInsertion;
    private int copiaAtualBubble;
    private int copiaAtualShell;
    private int copiaAtualMerge;
    private int copiaAtualQuick;
    
    private double tempoParaMudar;
    private double contadorTempo;
    
    
    
    public Main() { 
        super(1310, 720, "Simuladores Ordenação", 60, true);  
        
    }
    

    @Override
    public void create() {
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        copiaAtualQuick = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        aSelection = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasSelection = new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        aInsertion = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasInsertion = new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
        
        aBubble = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasBubble = new ArrayList<>();
        bubbleSort(aBubble, copiasBubble);
        
        aShell = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasShell = new ArrayList<>();
        shellSort(aShell, copiasShell);
        
        aMerge = new int [] { 9, 5, 4, 1, 2, 7, 6, 8, 3, 10 };
        copiasMerge = new ArrayList<>();
        mergeSort(aMerge, copiasMerge);
        
        aQuick = new int [] { 9, 5, 4, 1, 2, 7, 6, 8, 3, 10 };
        copiasQuick = new ArrayList<>();
        quickSort(aQuick, copiasQuick);
        
    
    }

    @Override
    public void update( double delta ) {
        
        contadorTempo += delta;
        
        if(contadorTempo >= tempoParaMudar){
            
            if (copiaAtualSelection < copiasSelection.size() - 1 ){
                copiaAtualSelection++;
            }
            if (copiaAtualInsertion < copiasInsertion.size() - 1){
                copiaAtualInsertion++;
            }
            if (copiaAtualBubble < copiasBubble.size() - 1){
                copiaAtualBubble++;
            }
            if (copiaAtualShell < copiasShell.size() - 1){
                copiaAtualShell++;
            }
            if (copiaAtualMerge < copiasMerge.size() - 1 ){
                copiaAtualMerge++;
            }
            if (copiaAtualQuick < copiasQuick.size() - 1 ){
                copiaAtualQuick++;
            }
            contadorTempo = 0;
        }
        
        if (isKeyPressed(KEY_R)){
            resetar();
        }
        
        
        
    }
  
    @Override
    public void draw() {
        
        clearBackground( WHITE );  
        
        drawText("Selection Sort", 20, 10, 20, DARKPURPLE);
        drawText("Insertion Sort", 345, 10, 20, DARKPURPLE);
        drawText("Bubble Sort", 670, 10, 20, DARKPURPLE);
        drawText("Shell Sort", 995, 10, 20, DARKPURPLE);
        drawText("Merge Sort", 20, 370, 20, DARKPURPLE);
        drawText("Quick Sort", 345, 370, 20, DARKPURPLE);
        
        desenharEstadoOrdenacao( copiasSelection.get(copiaAtualSelection), 20, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasInsertion.get(copiaAtualInsertion), 345,  getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasBubble.get(copiaAtualBubble), 670, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasShell.get(copiaAtualShell), 995, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasMerge.get(copiaAtualMerge), 20, getScreenHeight() / 2 + 330);
        desenharEstadoOrdenacao(copiasQuick.get(copiaAtualQuick), 345, getScreenHeight() / 2 + 330);
    }

    private void desenharEstadoOrdenacao( EstadoOrdenacao e, int iniX, int iniY) {
        int [] a = e.a;
        int tamanho = 25;
        int espacamento = 5;
        
        
        int tamanhoQuadrado = 0;
        tamanhoQuadrado = 5 + (tamanho + espacamento) * a.length;
        drawRectangle(iniX - 5, iniY - getScreenHeight() / 2 + 60, tamanhoQuadrado, tamanhoQuadrado, BLACK);
        
        for(int i = 0; i < a.length; i++){
            
            int v = a[i];
            int altura = v * tamanho;
            
            fillRectangle(iniX + i * (tamanho + espacamento), iniY - altura, tamanho, altura, DARKPURPLE);
            
            
            
        }
        if (e.i >= 0){ 
            fillCircle(iniX + e.i * (tamanho + espacamento) + tamanho / 2, iniY - a[e.i] * tamanho - 10, 5, ORANGE);
        }
        if (e.j >= 0){
            fillCircle(iniX + e.j * (tamanho + espacamento) + tamanho / 2, iniY - a[e.j] * tamanho - 20, 5, BLUE);
        }
        if (e.menor >= 0){
            fillCircle(iniX + e.menor * (tamanho + espacamento) + tamanho / 2, iniY - a[e.menor] * tamanho - 30, 5, RED);   
        }

    }
    
    private void selectionSort(int[] a, List<EstadoOrdenacao> copias){
        
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
    
    private void trocar(int[] a, int p1, int p2){
        int t = a[p1];
        a[p1] = a[p2];
        a[p2] = t;
    }
    
    private void insertionSort(int[] insert, List<EstadoOrdenacao> copias){
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
    
    private void bubbleSort(int[] bubble, List<EstadoOrdenacao> copias){
        int tamanho = bubble.length;
        int i = 0;
        boolean trocado;
        salvarEstadoOrdenacao(bubble, i, -1, -1, copias);
        do {
            trocado = false;
            for(int j = tamanho - 1; j > i; j--){
                if (bubble[j-1] > bubble[j]){
                    salvarEstadoOrdenacao(bubble, i, j, j - 1, copias);
                    trocar(bubble, j-1, j);
                    trocado = true;
                }
            }
            i++;
             salvarEstadoOrdenacao(bubble, i, -1, -1, copias);
        } while (trocado && i < tamanho);
        
    }
    
    private void shellSort(int[] shell, List<EstadoOrdenacao> copias){
        int h = 1;
        int tamanho = shell.length;
        salvarEstadoOrdenacao(shell, -1, -1, -1, copias);
        while( h < tamanho / 3){
            h = 3 * h + 1;
            salvarEstadoOrdenacao(shell, h, -1, -1, copias);
        }
        while (h >= 1){
            for (int i = h; i < tamanho; i++){
                int j = i;
                while(j >= h && shell[j-h] > shell[j]){
                    salvarEstadoOrdenacao(shell, h, j-h, j, copias);
                    trocar(shell, j-h, j);
                    j = j - h;
                }
            }
            h = h / 3;
            salvarEstadoOrdenacao(shell, h, -1, -1, copias);
        }
    }
    
    private void mergeSort( int [] merge, List<EstadoOrdenacao> copias ){
        
        salvarEstadoOrdenacao(merge, -1, -1, -1, copias);
        mergeSort(merge, 0, merge.length - 1, copias);
    }

    private void mergeSort(int[] merge, int inicio, int fim, List<EstadoOrdenacao> copias) {
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
    
    private void quickSort ( int [] quick, List<EstadoOrdenacao> copias){
        salvarEstadoOrdenacao(quick, -1, -1, -1, copias);
        quickSort(quick, 0, quick.length - 1, copias);
    }
    
    private int particao ( int[] quick, int inicio, int fim, List<EstadoOrdenacao> copias){
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
    
    private void quickSort ( int[] quick, int inicio, int fim, List<EstadoOrdenacao> copias ){
        if (inicio < fim){
            
            int meio = particao(quick, inicio, fim, copias);
            
            quickSort(quick, inicio, meio - 1, copias);
            quickSort(quick, meio + 1, fim, copias);
        }
    }
    
    private void salvarEstadoOrdenacao(int[] origem, int posI, int posJ, int posMenor, List<EstadoOrdenacao> copias){
        int[] copia = new int[origem.length];
        for(int i = 0; i < copia.length; i++){
            copia[i] = origem[i];
        }
        
        EstadoOrdenacao estadoCopia = new EstadoOrdenacao(copia, posI, posJ, posMenor);
        copias.add(estadoCopia);   
    }
    
    private void resetar(){
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        aSelection = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasSelection =new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        aInsertion = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasInsertion =new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
        
        aBubble = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasBubble =new ArrayList<>();
        bubbleSort(aBubble, copiasBubble);
        
        aShell = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasShell =new ArrayList<>();
        shellSort(aShell, copiasShell);
        
        aMerge = new int [] { 9, 5, 4, 1, 2, 7, 6, 8, 3, 10 };
        copiasMerge = new ArrayList<>();
        mergeSort(aMerge, copiasMerge);
        
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}