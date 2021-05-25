package codigos;

public class Pedido {

    public float calculaTaxaDesconto (boolean primeiraCompra, String tipoCliente, float valorCompra) {
        if (valorCompra > 500) //1
            return 15; // 2 -> folha
        if (tipoCliente.equals("ouro")) // 3
            return 15; // 4 -> folha
        if (tipoCliente.equals("prata")) // 5
            return  10; // 6 -> folha
        if (primeiraCompra) // 7
            return 10; // 8 -> folha
        if (valorCompra > 400) // 9
            return 10; // 10 -> folha
        if (valorCompra > 200) // 11
            return 5; // 12 -> folha
        if (tipoCliente.equals("bronze")) // 13
            return 5; // 14 -> folha

        return 0; // 15 -> folha
    }
}
