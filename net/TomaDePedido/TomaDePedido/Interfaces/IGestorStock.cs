namespace TomaDePedido.Interfaces
{
    public interface IGestorStock
    {
        /// <summary>
        /// Le envia el pedido de cerveza a Stock para que actualice la cantidad de la misma
        /// </summary>
        /// <param name="">Detalle del pedido de Cerveza</param>
        void PedirCerveza(IDetalleCerveza detalle);

        /// <summary>
        /// Le envía a Stock un código de detalle para que revierta su pedido y se actualice la cantidad de la cerveza pedida
        /// </summary>
        /// <param name="codigoDetalle">Codigo de Detalle</param>
        void CancelarPedido(int codigoDetalle);
    }
}