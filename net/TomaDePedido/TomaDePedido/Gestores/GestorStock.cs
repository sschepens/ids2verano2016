namespace TomaDePedido.Gestores
{
    using System;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación con el módulo de Stock
    /// </summary>
    public class GestorStock : IGestorStock
    {
        public void CancelarPedido(int codigoDetalle)
        {
            throw new NotImplementedException();
        }

        public void PedirCerveza(IDetalleCerveza detalle)
        {
            throw new NotImplementedException();
        }
    }
}
