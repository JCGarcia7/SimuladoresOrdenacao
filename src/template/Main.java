package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import br.com.davidbuzatto.jsge.imgui.GuiComponent;
import br.com.davidbuzatto.jsge.imgui.GuiLabel;
import java.util.ArrayList;
import java.util.List;
import template.modelo.EstadoOrdenacao;
import static template.ordenacao.BubbleSort.bubbleSort;
import static template.ordenacao.HeapSort.heapSort;
import static template.ordenacao.InsertionSort.insertionSort;
import static template.ordenacao.MergeSort.mergeSort;
import static template.ordenacao.QuickSort.quickSort;
import static template.ordenacao.SelectionSort.selectionSort;
import static template.ordenacao.ShellSort.shellSort;

/**
 * Simuladores de algoritmosde ordenação.
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */

public class Main extends EngineFrame {
    
    private int[] aSelection;
    private int[] aInsertion;
    private int[] aBubble;
    private int[] aShell;
    private int[] aMerge;
    private int[] aQuick;
    private int[] aHeap;
    private List<EstadoOrdenacao> copiasSelection;
    private List<EstadoOrdenacao> copiasInsertion;
    private List<EstadoOrdenacao> copiasBubble;
    private List<EstadoOrdenacao> copiasShell;
    private List<EstadoOrdenacao> copiasMerge;
    private List<EstadoOrdenacao> copiasQuick;
    private List<EstadoOrdenacao> copiasHeap;
    private int copiaAtualSelection;
    private int copiaAtualInsertion;
    private int copiaAtualBubble;
    private int copiaAtualShell;
    private int copiaAtualMerge;
    private int copiaAtualQuick;
    private int copiaAtualHeap;
    
    int tamanhoQuadrado;
    
    private double tempoParaMudar;
    private double contadorTempo;
    
    private List<GuiComponent> components;
    private GuiButton SelectionButton;

    
    public Main() { 
        super(1310, 720, "Simuladores Ordenação", 60, true);  
        
    }
    

    @Override
    public void create() {
        useAsDependencyForIMGUI();
        components = new ArrayList<>();
        
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        copiaAtualQuick = 0;
        copiaAtualHeap = 0;
        
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
        
        aHeap = new int [] { 9, 5, 4, 1, 2, 7, 6, 8, 3, 10 };
        copiasHeap = new ArrayList<>();
        heapSort (aHeap, copiasHeap);
        
        SelectionButton = new GuiButton(15, 90, 305, 305, "salve ");
        components.add(SelectionButton);
        
      
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
            if (copiaAtualHeap < copiasHeap.size() - 1 ){
                copiaAtualHeap++;
            }
            contadorTempo = 0;
        }
        
        if (isKeyPressed(KEY_R)){
            resetar();
        }
        
        if (isKeyPressed(KEY_ONE)){
            System.out.println(" botao apertou e foi bacana");
        }
        
    }
  
    @Override
    public void draw() {
        
        clearBackground( WHITE );  
        
        drawText("Selection Sort", 20, 10, 20, DARKPURPLE);
        drawText("Insertion Sort", 345, 10, 20, DARKPURPLE);
        drawText("Bubble Sort", 670, 10, 20, DARKPURPLE);
        drawText("Shell Sort", 995, 10, 20, DARKPURPLE);
        drawText("Merge Sort", getScreenWidth() / 2 - 476, 665, 20, DARKPURPLE);
        drawText("Quick Sort", getScreenWidth() / 2 - 151, 665, 20, DARKPURPLE);
        drawText("Heap Sort", getScreenWidth() / 2 + 174, 665, 20, DARKPURPLE);
        
        desenharEstadoOrdenacao( copiasSelection.get(copiaAtualSelection), 20, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasInsertion.get(copiaAtualInsertion), 345,  getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasBubble.get(copiaAtualBubble), 670, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasShell.get(copiaAtualShell), 995, getScreenHeight() / 2 - 30 );
        desenharEstadoOrdenacao(copiasMerge.get(copiaAtualMerge), getScreenWidth() / 2 - 476, getScreenHeight() - 65);
        desenharEstadoOrdenacao(copiasQuick.get(copiaAtualQuick), getScreenWidth() / 2 - 151, getScreenHeight() - 65);
        desenharEstadoOrdenacao(copiasHeap.get(copiaAtualHeap), getScreenWidth() / 2 + 174, getScreenHeight() - 65);
    }

    private void desenharEstadoOrdenacao( EstadoOrdenacao e, int iniX, int iniY) {
        int [] a = e.a();
        int tamanho = 25;
        int espacamento = 5;
        
        
        tamanhoQuadrado = 0;
        tamanhoQuadrado = 5 + (tamanho + espacamento) * a.length;
        drawRectangle(iniX - 5, iniY - getScreenHeight() / 2 + 60, tamanhoQuadrado, tamanhoQuadrado, BLACK);
        
        for(int i = 0; i < a.length; i++){
            
            int v = a[i];
            int altura = v * tamanho;
            
            fillRectangle(iniX + i * (tamanho + espacamento), iniY - altura, tamanho, altura, DARKPURPLE);
            
            
            
        }
        if (e.i() >= 0){ 
            fillCircle(iniX + e.i() * (tamanho + espacamento) + tamanho / 2, iniY - a[e.i()] * tamanho - 10, 5, ORANGE);
        }
        if (e.j() >= 0){
            fillCircle(iniX + e.j() * (tamanho + espacamento) + tamanho / 2, iniY - a[e.j()] * tamanho - 20, 5, BLUE);
        }
        if (e.menor() >= 0){
            fillCircle(iniX + e.menor() * (tamanho + espacamento) + tamanho / 2, iniY - a[e.menor()] * tamanho - 30, 5, RED);   
        }

    }
    
    private void resetar(){
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        copiaAtualQuick = 0;
        copiaAtualHeap = 0;
        
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
        
        aQuick = new int [] { 9, 5, 4, 1, 2, 7, 6, 8, 3, 10 };
        copiasQuick = new ArrayList<>();
        quickSort(aQuick, copiasQuick);
        
        aHeap = new int [] { 9, 5, 4, 1, 2, 7, 6, 8, 3, 10 };
        copiasHeap = new ArrayList<>();
        heapSort(aHeap, copiasHeap);
        
    }
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
