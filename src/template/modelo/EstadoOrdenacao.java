package template.modelo;

/**
 * Representa um estado (uma "foto") de um vetor durante o processo de
 * ordenação, guardando também as posições em destaque naquele instante.
 * 
 * @author Júlio César Dias Garcia e João Guilherme Eickhoff
 */
public record EstadoOrdenacao(int[] a, int i, int j, int menor) {
}
