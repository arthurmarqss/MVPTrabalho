package com.mycompany.mvptrabalho.presenter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

public final class Formatador {

    private static final Locale BRASIL = Locale.of("pt", "BR");
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    private Formatador() {
    }

    public static String decimal(Double valor) {
        return valor == null ? "" : String.format(BRASIL, "%,.2f", valor);
    }

    public static String moeda(Double valor) {
        return valor == null ? "" : "R$ " + decimal(valor);
    }

    public static String data(LocalDate data) {
        return data == null ? "" : data.format(FORMATO_DATA);
    }

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
