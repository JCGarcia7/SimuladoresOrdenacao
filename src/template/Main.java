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
    private List<EstadoOrdenacao> copiasSelection;
    private List<EstadoOrdenacao> copiasInsertion;
    private int copiaAtualSelection;
    private int copiaAtualInsertion;
    
    private double tempoParaMudar;
    private double contadorTempo;
    
    
    
    public Main() { 
        super(1400, 720, "Simuladores Ordenação", 60, true);  
        
    }
    

    @Override
    public void create() {
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        aSelection = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasSelection =new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        aInsertion = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasInsertion =new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
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
            contadorTempo = 0;
        }
        
        if (isKeyPressed(KEY_R)){
            resetar();
        }
        
        
        
    }
  
    @Override
    public void draw() {
        
        clearBackground( WHITE );  
        
        
        desenharEstadoOrdenacao( copiasSelection.get(copiaAtualSelection), 10, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasInsertion.get(copiaAtualInsertion), 315,  getScreenHeight() / 2 - 30 );
        
    }

    private void desenharEstadoOrdenacao( EstadoOrdenacao e, int iniX, int iniY) {
        int [] a = e.a;
        int tamanho = 25;
        int espacamento = 5;
        
        
        int tamanhoQuadrado = 0;
        tamanhoQuadrado = 5 + (tamanho + espacamento) * a.length;
        drawRectangle(iniX - 5, 30, tamanhoQuadrado, tamanhoQuadrado, BLACK);
        
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
                trocar(insert, j-1, j);
                salvarEstadoOrdenacao(insert, i, j, j - 1, copias);
                j--;
            }
            salvarEstadoOrdenacao(insert, i, -1, -1, copias);
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
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        aSelection = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasSelection =new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        aInsertion = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasInsertion =new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
        
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
