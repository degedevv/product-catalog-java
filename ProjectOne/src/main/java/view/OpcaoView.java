package view;

import javax.swing.*;

public class OpcaoView {

    public static Opcao select(){
        Opcao ret = (Opcao) JOptionPane.showInputDialog(
                null, // componente pai. Como nao temos sera null
                "Selecione uma opção",
                "Menu",
                JOptionPane.QUESTION_MESSAGE,
                null, //icone

                Opcao.values(), // Número da opção
                Opcao.CADASTRAR_PRODUTOR);

        return ret != null ? ret : Opcao.ENCERRAR_SISTEMA;


    }

}
