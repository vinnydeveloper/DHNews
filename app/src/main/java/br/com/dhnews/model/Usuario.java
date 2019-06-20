package br.com.dhnews.model;

import android.widget.TextView;

public class Usuario {

    private TextView emailUsuario;
    private TextView senhaUsuario;

    public Usuario() {
    }

    public Usuario(TextView emailUsuario, TextView senhaUsuario) {
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public TextView getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(TextView emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public TextView getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(TextView senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
}
