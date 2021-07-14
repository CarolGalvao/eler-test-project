package codigos;

public class Pedido {

    public float calculaTaxaDesconto (boolean primeiraCompra, String tipoCliente, float valorCompra) {
        if (valorCompra > 500)
            return 15;
        if (tipoCliente.equals("ouro"))
            return 15;
        if (tipoCliente.equals("prata"))
            return  10;
        if (primeiraCompra)
            return 10;
        if (valorCompra > 400)
            return 10;
        if (valorCompra > 200)
            return 5;
        if (tipoCliente.equals("bronze"))
            return 5;

        return 0;
    }
}
