package com.pichincha.test.constants;

public enum MensajeError {

    SALDO_NO_DISPONIBLE("Saldo no disponible para realizar este movimiento"),
    CUENTA_NO_EXISTE("La cuenta no existe"),
    MOVIMIENTO_INVALIDO("El valor del movimiento no es válido");

    private final String mensaje;

    MensajeError(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
