package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import br.com.davidbuzatto.jsge.imgui.GuiComponent;
import br.com.davidbuzatto.jsge.imgui.GuiLabel;
import br.com.davidbuzatto.jsge.math.Vector2;
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
    
    private int[] casoMedio;
    private int[] piorCaso;
    private int[] melhorCaso;
    private int[] arrayAtual;
    private int[] arrayTemp;
    
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
    
    //private List<GuiComponent> components;
    //private GuiButton SelectionButton;

    
    public Main() { 
        super(1310, 720, "Simuladores Ordenação", 60, true);  
        
    }
    

    @Override
    public void create() {
        //useAsDependencyForIMGUI();
        //components = new ArrayList<>();
        
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        copiaAtualQuick = 0;
        copiaAtualHeap = 0;
        
        tamanhoQuadrado = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        arrayAtual = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        
        aSelection = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasSelection = new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        //SelectionButton = new GuiButton(15, 90, 305, 305, " ");
        //components.add(SelectionButton);
        
        aInsertion = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasInsertion = new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
        
        aBubble = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasBubble = new ArrayList<>();
        bubbleSort(aBubble, copiasBubble);
        
        aShell = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasShell = new ArrayList<>();
        shellSort(aShell, copiasShell);
        
        aMerge = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};;
        copiasMerge = new ArrayList<>();
        mergeSort(aMerge, copiasMerge);
        
        aQuick = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasQuick = new ArrayList<>();
        quickSort(aQuick, copiasQuick);
        
        aHeap = new int[]{9, 5, 4, 1, 2, 7, 6, 8, 3, 10};
        copiasHeap = new ArrayList<>();
        heapSort (aHeap, copiasHeap);

        
      
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
        if (isKeyPressed(KEY_A)){
            resetarCasoMedio();
        }
        if (isKeyPressed(KEY_W)){
            resetarPiorCaso();
        }
        if (isKeyPressed(KEY_M)){
            resetarMelhorCaso();
        }
        
        if (isKeyPressed(KEY_R)){
            resetar();
        }
        
        //if (SelectionButton.isMousePressed()){
        //    System.out.println(" botao apertou");
        //}
        
    }
  
    @Override
    public void draw() {
        
        clearBackground( WHITE );  
        
        desenharEstadoOrdenacao( copiasSelection.get(copiaAtualSelection), 20, getScreenHeight() / 2 - 30, "SelectionSort" );
        desenharEstadoOrdenacao(copiasInsertion.get(copiaAtualInsertion), 345,  getScreenHeight() / 2 - 30, "InsertionSort" );
        desenharEstadoOrdenacao(copiasBubble.get(copiaAtualBubble), 670, getScreenHeight() / 2 - 30, "BubbleSort" );
        desenharEstadoOrdenacao(copiasShell.get(copiaAtualShell), 995, getScreenHeight() / 2 - 30, "ShellSort" );
        desenharEstadoOrdenacao(copiasMerge.get(copiaAtualMerge), getScreenWidth() / 2 - 476, getScreenHeight() - 65, "MergeSort");
        desenharEstadoOrdenacao(copiasQuick.get(copiaAtualQuick), getScreenWidth() / 2 - 151, getScreenHeight() - 65, "QuickSort");
        desenharEstadoOrdenacao(copiasHeap.get(copiaAtualHeap), getScreenWidth() / 2 + 174, getScreenHeight() - 65, "HeapSort");
    }

    private void desenharEstadoOrdenacao( EstadoOrdenacao e, int iniX, int iniY, String nomeOrdenacao) {
        int [] a = e.a();
        int tamanho = 25;
        int espacamento = 5;
        
        if(iniY < getScreenHeight() / 2){
            drawText(nomeOrdenacao, iniX, 10, 20, DARKPURPLE);
        } else {
            drawText(nomeOrdenacao, iniX, 665, 20, DARKPURPLE);
        }
                
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
    
    private void resetarPiorCaso(){
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        copiaAtualQuick = 0;
        copiaAtualHeap = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        aSelection = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        copiasSelection =new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        aInsertion = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        copiasInsertion =new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
        
        aBubble = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        copiasBubble =new ArrayList<>();
        bubbleSort(aBubble, copiasBubble);
        
        aShell = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        copiasShell =new ArrayList<>();
        shellSort(aShell, copiasShell);
        
        aMerge = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        copiasMerge = new ArrayList<>();
        mergeSort(aMerge, copiasMerge);
        
        aQuick = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        copiasQuick = new ArrayList<>();
        quickSort(aQuick, copiasQuick);
        
        aHeap = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        copiasHeap = new ArrayList<>();
        heapSort(aHeap, copiasHeap);
    }
    
    private void resetarMelhorCaso(){
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        copiaAtualQuick = 0;
        copiaAtualHeap = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        aSelection = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        copiasSelection =new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        aInsertion = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        copiasInsertion =new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
        
        aBubble = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        copiasBubble =new ArrayList<>();
        bubbleSort(aBubble, copiasBubble);
        
        aShell = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        copiasShell =new ArrayList<>();
        shellSort(aShell, copiasShell);
        
        aMerge = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        copiasMerge = new ArrayList<>();
        mergeSort(aMerge, copiasMerge);
        
        aQuick = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        copiasQuick = new ArrayList<>();
        quickSort(aQuick, copiasQuick);
        
        aHeap = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        copiasHeap = new ArrayList<>();
        heapSort(aHeap, copiasHeap);
    }
    
    private void resetarCasoMedio(){
        copiaAtualSelection = 0;
        copiaAtualInsertion = 0;
        copiaAtualBubble = 0;
        copiaAtualShell = 0;
        copiaAtualMerge = 0;
        copiaAtualQuick = 0;
        copiaAtualHeap = 0;
        
        tempoParaMudar = 0.2;
        contadorTempo = 0;
        
        aSelection = new int[]{5, 2, 9, 1, 7, 6, 10, 3, 8, 4};
        copiasSelection =new ArrayList<>();
        selectionSort(aSelection, copiasSelection);
        
        aInsertion = new int[]{5, 2, 9, 1, 7, 6, 10, 3, 8, 4};
        copiasInsertion =new ArrayList<>();
        insertionSort(aInsertion, copiasInsertion);
        
        aBubble = new int[]{5, 2, 9, 1, 7, 6, 10, 3, 8, 4};
        copiasBubble =new ArrayList<>();
        bubbleSort(aBubble, copiasBubble);
        
        aShell = new int[]{5, 2, 9, 1, 7, 6, 10, 3, 8, 4};
        copiasShell =new ArrayList<>();
        shellSort(aShell, copiasShell);
        
        aMerge = new int[]{5, 2, 9, 1, 7, 6, 10, 3, 8, 4};
        copiasMerge = new ArrayList<>();
        mergeSort(aMerge, copiasMerge);
        
        aQuick = new int[]{5, 2, 9, 1, 7, 6, 10, 3, 8, 4};
        copiasQuick = new ArrayList<>();
        quickSort(aQuick, copiasQuick);
        
        aHeap = new int[]{5, 2, 9, 1, 7, 6, 10, 3, 8, 4};
        copiasHeap = new ArrayList<>();
        heapSort(aHeap, copiasHeap);
    }
        
        
    
    public static void main( String[] args ) {
        new Main();
    }
    
}
