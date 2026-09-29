package com.mycompany.mvptrabalho.presenter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

/**
 * Converte valores do modelo em texto para a View e vice-versa. A View
 * passiva só trabalha com String; quem converte é o Presenter.
 */
public final class Formatador {

    private static final Locale BRASIL = Locale.of("pt", "BR");
    // "uuuu" + STRICT rejeita datas inexistentes, como 31/02/2026.
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    private Formatador() {
    }

    /** 25.0 vira "25,00" e 1234.5 vira "1.234,50". null vira "" (valor ainda não calculado). */
    public static String decimal(Double valor) {
        return valor == null ? "" : String.format(BRASIL, "%,.2f", valor);
    }

    /** 45.0 vira "R$ 45,00". */
    public static String moeda(Double valor) {
        return valor == null ? "" : "R$ " + decimal(valor);
    }

    public static String data(LocalDate data) {
        return data == null ? "" : data.format(FORMATO_DATA);
    }

    /**
     * Converte o texto digitado ("12,50", "R$ 1.234,56" ou "12.5") em Double.
     * Texto vazio vira null, para o serviço acusar campo obrigatório.
     *
     * @throws IllegalArgumentException se o texto não for um número
     */
    public static Double paraDouble(String texto, String nomeCampo) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        String limpo = texto.replace("R$", "").replace("%", "").trim();
        if (limpo.contains(",")) {
            limpo = limpo.replace(".", "").replace(",", ".");
        }
        if (!limpo.matches("-?\\d+(\\.\\d+)?")) {
            throw new IllegalArgumentException(nomeCampo + " inválido: \"" + texto.trim()
                    + "\". Informe um número, por exemplo 12,50.");
        }
        try {
            return Double.valueOf(limpo);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nomeCampo + " inválido: \"" + texto.trim() + "\".", e);
        }
    }

    /**
     * Converte "dd/mm/aaaa" em LocalDate. Texto vazio vira null.
     *
     * @throws IllegalArgumentException se a data for inválida
     */
    public static LocalDate paraData(String texto, String nomeCampo) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(texto.trim(), FORMATO_DATA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(nomeCampo + " inválida: \"" + texto.trim()
                    + "\". Use o formato dd/mm/aaaa.", e);
        }
    }
}
