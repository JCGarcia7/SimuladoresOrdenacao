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
 * @author julio
 */

public class Main extends EngineFrame {
    
    private record EstadoOrdenacao(int[] a, int i, int j, int menor){};
    
    private int[] a;
    private List<EstadoOrdenacao> copias;
    private int copiaAtual;
    
    private double tempoParaMudar;
    private double contadorTempo;
    
    
    
    public Main() { 
        super(1400, 720, "Simuladores Ordenação", 60, true);  
        
    }
    

    @Override
    public void create() {
      a = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copias =new ArrayList<>();
        copiaAtual = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        selectionSort(a, copias);
    }

    @Override
    public void update( double delta ) {
        
        contadorTempo += delta;
        
        if(contadorTempo >= tempoParaMudar){
            if (copiaAtual < copias.size() - 1 ){
            copiaAtual++;
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
        
        
        desenharEstadoOrdenacao( copias.get(copiaAtual) );
        
    }

    private void desenharEstadoOrdenacao( EstadoOrdenacao e) {
        int [] a = e.a;
        int tamanho = 25;
        int espacamento = 5;
        int iniX = 10;
        int iniY = getScreenHeight()/ 2 - 30;
        
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
        for(int i = 1; i < tamanho; i++){
            int j = i;
            while(j > 0 && insert[j - 1] > insert[j]){
                trocar(insert, j-1, j);
                j--;
            }
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
        a = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copias =new ArrayList<>();
        copiaAtual = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        selectionSort(a, copias);
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
