package br.com.dio.trademapclone

import androidx.lifecycle.ViewModel
import br.com.dio.trademapclone.repository.AcaoRepository

class MainViewModel(private val acaoRepository: AcaoRepository) : ViewModel() {

    private val conectorMqtt = ConectorMqtt()

    fun consumirAcoes() {
        conectorMqtt.start { acao ->
            acaoRepository.salvar(acao)
        }
    }

    fun pararCosumirAcoes() {
        conectorMqtt.desligar()
    }

}