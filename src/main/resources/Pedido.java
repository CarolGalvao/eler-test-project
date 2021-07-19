package codigos;

//bronze = 1
//prata = 2
//ouro = 3

public class Pedido {

    public float calculaTaxaDesconto (boolean primeiraCompra, int tipoCliente, float valorCompra) {
        if (valorCompra > 500)
            return 15;
        if (tipoCliente == 3)
            return 15;
        if (tipoCliente == 2)
            return  10;
        if (primeiraCompra)
            return 10;
        if (valorCompra > 400)
            return 10;
        if (valorCompra > 200)
            return 5;
        if (tipoCliente == 1)
            return 5;

        return 0;
    }
}
