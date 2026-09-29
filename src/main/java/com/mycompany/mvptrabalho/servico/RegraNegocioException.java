package com.mycompany.mvptrabalho.servico;

/**
 * Exceção verificada (checked) lançada quando uma operação válida em seus
 * dados é proibida por uma regra do negócio, por exemplo: calcular preços
 * antes de 10 dias ou excluir uma categoria com produtos.
 *
 * Dados de entrada inválidos (campo vazio, valor negativo...) usam
 * IllegalArgumentException.
 */
public class RegraNegocioException extends Exception {

    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }

    public RegraNegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
