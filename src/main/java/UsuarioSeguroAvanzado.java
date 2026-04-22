public class UsuarioSeguroAvanzado {
    // Atributos
    private String username;
    private String password;
    private int intentosFallidos;
    private boolean bloqueado;
    private int maxIntentos;
    private boolean accesoExitoso;

    // 2. Constructor
    public UsuarioSeguroAvanzado(String username, String password, int maxIntentos) {
        this.username = username;
        this.password = password;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.accesoExitoso = false;
        // Si es 0 o negativo, por defecto es 3
        this.maxIntentos = (maxIntentos <= 0) ? 3 : maxIntentos;
    }

    //metodo de accesso
    public String getUsername() {
        return username;
    }

    //cuantos errores lleva
    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    //si el usuario esta bloqueado o no
    public boolean isBloqueado() {
        return bloqueado;
    }

    //saber el limete de intentos
    public int getMaxIntentos() {
        return maxIntentos;
    }

    //saber si entro con exito
    public boolean isAccesoExitoso() {
        return accesoExitoso;
    }

    //Autenticacion
    public boolean autenticar(String passwordIngresada) {
        if (this.bloqueado) return false;

        if (this.password.equals(passwordIngresada)) {
            this.intentosFallidos = 0;
            this.accesoExitoso = true;
            return true;
        } else {
            this.intentosFallidos++;
            if (this.intentosFallidos >= this.maxIntentos) {
                this.bloqueado = true;
            }
            return false;
        }
    }

    public void reiniciarAcceso() {
        this.intentosFallidos = 0;
        this.bloqueado = false;
    }

    public boolean cambiarPassword(String actual, String nueva) {
        // Reglas: No bloqueado, clave actual correcta y nueva clave segura
        if (this.bloqueado) return false;
        if (!this.password.equals(actual)) return false;
        if (!validarPasswordSegura(nueva)) return false;

        this.password = nueva;
        return true;
    }

    public boolean validarPasswordSegura(String nueva) {
        // Mínimo 8 caracteres, 1 mayúscula y 1 número
        if (nueva.length() < 8) return false;

        boolean tieneMayuscula = false;
        boolean tieneNumero = false;

        for (char c : nueva.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            if (Character.isDigit(c)) tieneNumero = true;
        }

        return tieneMayuscula && tieneNumero;
    }
}