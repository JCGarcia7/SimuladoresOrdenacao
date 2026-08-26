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
    
    private record EstadoOrdenacao(int[] a, int i, int j, int menor){};
    
    private int[] aSelection;
    private int[] aInsertion;
    private int[] aBubble;
    private int[] aShell;
    private List<EstadoOrdenacao> copiasSelection;
    private List<EstadoOrdenacao> copiasInsertion;
    private List<EstadoOrdenacao> copiasBubble;
    private List<EstadoOrdenacao> copiasShell;
    private int copiaAtualSelection;
    private int copiaAtualInsertion;
    private int copiaAtualBubble;
    private int copiaAtualShell;
    
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
        
        
        desenharEstadoOrdenacao( copiasSelection.get(copiaAtualSelection), 20, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasInsertion.get(copiaAtualInsertion), 345,  getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasBubble.get(copiaAtualBubble), 670,  getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasShell.get(copiaAtualShell), 995,  getScreenHeight() / 2 - 30 );
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
        
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
