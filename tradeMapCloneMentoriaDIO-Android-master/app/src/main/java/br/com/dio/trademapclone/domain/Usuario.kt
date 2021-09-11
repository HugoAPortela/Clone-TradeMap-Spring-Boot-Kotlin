package br.com.dio.trademapclone.domain

data class Usuario(
    val login: String,
    val senha: String,
    val email: String,
    val nome: String,
    val acoesFavoritas: List<Acao>,
    val ativo: Boolean
)
